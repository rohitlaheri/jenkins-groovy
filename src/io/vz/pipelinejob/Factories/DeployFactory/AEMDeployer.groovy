package io.vz.pipelinejob.Factories.DeployFactory

import io.vz.pipelinejob.Model.*
import io.vz.pipelinejob.Factories.DeployFactory.Configuration.DeployCode
//import AEMPipelineHelper

//concrete class implementing interface DeployCode to Deploy AEM code
public class AEMDeployer implements DeployCode {
    def steps
    def publisher_server
    def repo
    def core_module
    def app_module
    def mvn_target_path
    def artifact_repo
    def ui_module
    def release_version
    def artifactory_url
    def AEMDeployer(steps) {this.steps = steps}
    //def aemhelper = new AEMPipelineHelper(this)

    private pipelineParameters _parameters
    public AEMDeployer(pipelineParameters parameters){
        _parameters = parameters;
    }



    //region Methods for deploy steps --start
    def initializeEnvSetup(){
        print "initialize environment......"
        publisher_server = aemhelper.getEnvSetup(_parameters.deploy_env,_parameters.bgtraffic);
    }

    def getRelVersion(){
        release_version = aemhelper.getRelVersion(_parameters.branch_name)
    }

    def getModule(){
        try{
            module_list = readJSON file: "/resources/configuration.json"
            module_list.each{entry ->
                if (entry.key == _parameters.module){
                    repo = entry.value.REPO
                    core_module = entry.value.CORE_MODULE
                    app_module = entry.value.APPLICATION_MODULE
                    ui_module = entry.value.UI_MODULE
                    mvn_target_path = entry.value.MVN_TARGET_PATH
                    artifact_repo = entry.value.ARTI_REPO
                }
            }
        } catch (Exception e) {
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
        if (_parameters.image_tag == Constant.LATEST) {
            artifactory_url = "${Constant.HIVV_SOE}/${artifact_repo}/${Constant.ICON}/${_parameters.branch_name}/${mvn_target_path}-${release_version}.${Constant.zip}".getBuildNumber()
        } else {
            artifactory_url = _parameters.image_tag
        }
    }
    
    def Deploy()
    {
        try{
            if (_parameters.dev){
                getArtifactoryUrl()
                print "artifactory_url = " + getArtifactoryUrl()
                //ansible playbook execution block incomplete
                def runbuildenv = sh(script: "resources/buildnonprodenv ${artifactory_url} ${publisher_server}", returnStdout: true).toString().trim()
                steps.sh """
                git branch: 'master', credentialsId: 'xxx', url: 'git@gitlab.verizon.com/IAAS.Cloud/aem_qa_deploy.git'
                ansiblePlaybook become: true, colorized: true, credentialsId: 'xxxx', disableHostKeyChecking: true, installation: 'ansible', 
                playbook: 'soe-aem-playbook.yml', extra-vars: '{"artifactory_url":"${artifactory_url}","env":"${publisher_server}","package":"${runbuildenv.package}","artifactory_soe_pass":"${runbuildenv.SOE_ARTIFACTORY_PASSWORD}","aem_pwd":"${runbuildenv.AEM_PASSWORD}"}'
                """
            }
        }catch (Exception e) {
            print "Error occurred :" + e
        }
    }
    def CacheClear()
    {
        print "Performing cache clear"
        
    }

    //endregion

    @Override
     public void runDeploy() {
        print("Inside NonProdDeploy::runDeploy() method.")
        steps.log.info "Log from class"
        initializeEnvSetup();
        getRelVersion();
        getModule();
        getBuildNumber();
        getArtifactoryUrl();
        Deploy();
        CacheClear();
    }
}