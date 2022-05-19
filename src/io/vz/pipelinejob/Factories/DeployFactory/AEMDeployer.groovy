package io.vz.pipelinejob.Factories.DeployFactory
// This is factory class to perform Deployment to the environments
import io.vz.pipelinejob.Model.PipelineParameters
import io.vz.pipelinejob.Factories.DeployFactory.Configuration.DeployCode
import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*

//concrete class implementing interface DeployCode to Deploy AEM code
public class AEMDeployer implements DeployCode {
    def steps
    def aemServer
    def mvnTargetPath
    def artiRepo
    def releaseVersion
    def artifactoryUrl
    def parameters
    def pipelineHelper
    def aemModule
    def aemPwd
    def artifactorySOEPass
    def oneDigitalArtifactPWD
    def aemDeployEnvInject
    def aemSetEnvForCache
    def buildNumber = BUILD_NUMBER as int;
    def jenkinsCredentials = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
            com.cloudbees.plugins.credentials.Credentials.class,
            Jenkins.instance,
            null,
            null
    );

    def AEMDeployer(steps,pipelineParameters) {
        this.steps = steps
        this.parameters=pipelineParameters
        pipelineHelper=new PipelineHelper(this.steps)
    }



    //region Methods for deploy steps --start
    def initializeEnvSetup(){
        print "initialize environment......"
        try{
            if (parameters.dev){
                aemServer = pipelineHelper.getAEMDevServer(parameters.deployEnv,parameters.bgTraffic);
            }
            if (parameters.qa){
                aemServer = pipelineHelper.getAEMQAServer(parameters.deployEnv,parameters.bgTraffic);
            }
            releaseVersion = pipelineHelper.getReleaseVersion(parameters.branchName);
            aemModule = pipelineHelper.setAEMModule(parameters.moduleName);
            print "aemModule details ${aemModule}";
        }catch (Exception e) {
            print "Error occurred :" + e
        }

    }


    def getBuildNumber()
    {
        if (buildNumber > 1) milestone(buildNumber - 1);
        milestone(buildNumber)
    }
    
    def getArtifactoryUrl()
    {
        if (parameters.imageTag == pipelineConstants.LATEST) {
                artifactoryUrl = "${pipelineConstants.HIVV_SOE}/${aemModule.artiRepo}/${pipelineConstants.ICON}/${parameters.branchName}/${aemModule.mvnTargetPath}-${aemModule.releaseVersion}.${pipelineConstants.ZIP}".getBuildNumber()
        } else {
            artifactoryUrl = parameters.imageTag
        }
    }

    def getJenkinsGlobalPWD(){
        for (credential in jenkinsCredentials) {
            if(credential.id == pipelineConstants.SOE_ARTIFACTORY_PASSWORD){
                artifactorySOEPass = credential.password
            }
            if (credential.id == pipelineConstants.AEM_PASSWORD){
                aemPwd = credential.password
            }
            if (credential.id == pipelineConstants.ONEDIGITAL_ARTIFACTORY_PASSWORD){
                oneDigitalArtifactPWD = credential.password
            }
        }
    }


    def runAnsible(Object aemDeployEnvInject){
        steps.sh """
                git branch: 'master', credentialsId: 'xxx', url: 'git@gitlab.verizon.com/IAAS.Cloud/aem_qa_deploy.git'
                ansiblePlaybook become: true, colorized: true, credentialsId: 'xxxx', disableHostKeyChecking: true, installation: 'ansible', 
                playbook: 'soe-aem-playbook.yml', extra-vars: '{"artifactoryUrl":"${aemDeployEnvInject.artifactoryUrl}","env":"${aemServer}","package":"${aemDeployEnvInject.package}","artifactorySOEPass":"${artifactorySOEPass}","aemPwd":"${aemPwd}"}'
                """
    }
    def cacheClear(Object aemServer)
    {
        print "Performing cache clear"
        //ansible playbook execution block incomplete. Need to know how to execute ansible plugin playbook
        steps.sh """
                git branch: 'master', credentialsId: 'xxx', url: 'git@gitlab.verizon.com/TYPEB_MW/nsa_nonprod.git'
                ansiblePlaybook become: true, colorized: true, credentialsId: 'xxxx', disableHostKeyChecking: true, installation: 'ansible', 
                playbook: 'nsa.yml', extra-vars: '{"filePath":"${pipelineConstants.FILE_PATH}","envName":"${aemServer}"}'
                """
    }

    def deploy()
    {
        try{
            if (parameters.dev || parameters.qa){
                getArtifactoryUrl()
                print "artifactoryUrl = " + getArtifactoryUrl()
                aemDeployEnvInject = sh(script: "resources/aemDeployEnv ${artifactoryUrl} ${aemServer}", returnStdout: true).toString().trim()
                runAnsible(aemDeployEnvInject)
                aemSetEnvForCache = sh(script: "resources/aemCacheClear ${pipelineConstants.FILE_PATH} ${aemServer}", returnStdout: true).toString().trim()
                cacheClear(aemSetEnvForCache);
            } else {
                // For PROD TBD
            }

        }catch (Exception e) {
            print "Error occurred :" + e
        }
    }

   /* def filePath() {
        List filePath = ['/etc/clientlibs/vcg','/content/vcg','/etc.clientlibs/vcg','all']
        return filePath
    }*/

    //endregion

    @Override
     public void runDeploy() {
        print("Inside NonProdDeploy::runDeploy() method.")
        steps.echo "echo from class"
        initializeEnvSetup();
        getBuildNumber();
        getArtifactoryUrl();
        deploy();
    }
}