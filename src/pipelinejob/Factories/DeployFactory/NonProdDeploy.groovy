package pipelinejob.Factories.DeployFactory

import pipelinejob.ADT.AemPipelineParameters

//concrete class implementing interface DeployAemCode
public class NonProdDeploy implements DeployAemCode {
    def steps
    def NonProdDeploy(steps) {this.steps = steps}

    private AemPipelineParameters _parameters
    public NonProdDeploy(AemPipelineParameters parameters){
        _parameters = parameters;
    }

    //region Methods for deploy steps --start
    def initializeEnvSetup()
    {
        print "initialize environment......"
        if("${params.BRANCH_NAME}".contains("release")){
            env.REL_VERSION = "${params.BRANCH_NAME}".substring("${params.BRANCH_NAME}".lastIndexOf('/') + 1, "${params.BRANCH_NAME}".length())
        }else{
            env.REL_VERSION = "${params.BRANCH_NAME}".substring("${params.BRANCH_NAME}".lastIndexOf('-') + 1, "${params.BRANCH_NAME}".length())
        }

        if (params.deployEnv.contains(Constants.DIT1) || params.deployEnv.contains(Constants.SIT1)) {
            DEV_ENV = Constants.aem_nsd1E
        }
        if (params.deployEnv.contains(Constants.SIT1) && params.bgTraffic == Constants.GREEN) {
            QA_ENV = Constants.aem_nsq1E
        }
        if (params.deployEnv.contains(Constants.SIT1) && params.bgTraffic == Constants.BLUE) {
            QA_ENV = Constants.aem_nsq1bgE
        }
        if (params.deployEnv.contains(Constants.DIT2) || params.deployEnv.contains(Constants.SIT2)) {
            DEV_ENV = Constants.aem_nsd2E
        }
        if (params.deployEnv.contains(Constants.SIT2) && params.bgTraffic == Constants.GREEN) {
            QA_ENV = Constants.aem_nsq2E
        }
        if (params.deployEnv.contains(Constants.SIT2) && params.bgTraffic == Constants.BLUE) {
            QA_ENV = Constants.aem_nsq2bgE
        }
        if (params.deployEnv.contains(Constants.DIT3) || params.deployEnv.contains(Constants.SIT3)) {
            DEV_ENV = Constants.aem_nsd3E
        }
        if (params.deployEnv.contains(Constants.SIT3) && params.bgTraffic == Constants.GREEN) {
            QA_ENV = Constants.aem_nsq3E
        }
        if (params.deployEnv.contains(Constants.SIT3) && params.bgTraffic == Constants.BLUE) {
            QA_ENV = Constants.aem_nsq3bgE
        }
        if (params.deployEnv.contains(Constants.DIT4) || params.deployEnv.contains(Constants.SIT4)) {
            DEV_ENV = Constants.aem_nsd4E
        }
        if (params.deployEnv.contains(Constants.SIT4) && params.bgTraffic == Constants.GREEN) {
            QA_ENV = Constants.aem_nsq4E
        }
        if (params.deployEnv.contains(Constants.SIT4) && params.bgTraffic == Constants.BLUE) {
            QA_ENV = Constants.aem_nsq4bgE
        }

    }
    def DITDeploy()
    {
        print "Performing DIT environment......"
    }
    def DITCacheClear()
    {
        print "Performing DIT env cache clear"

    }
    def SITDeploy()
    {
        print "Performing SIT environment......"
    }
    def SITCacheClear()
    {
        print "Performing SIT env cache clear"

    }

    //endregion

    @Override
     public void runDeploy() {
        print("Inside NonProdDeploy::runDeploy() method.")
        steps.echo "echo from class"
        initializeEnvSetup();
        DITDeploy();
        DITCacheClear();
        SITDeploy();
        SITCacheClear();
    }
}