package pipelinejob.Factories.DeployFactory

import pipelinejob.ADT.AemPipelineParameters
import pipelinejob.Factories.DeployFactory.Configuration.DeployCode

//concrete class implementing interface DeployCode to Deploy AEM code
public class AEMDeployer implements DeployCode {
    def steps
    def AEMDeployer(steps) {this.steps = steps}

    private AemPipelineParameters _parameters
    public AEMDeployer(AemPipelineParameters parameters){
        _parameters = parameters;
    }

    //region Methods for deploy steps --start

    //endregion

    @Override
     public void runDeploy() {
        print("Inside NonProdDeploy::runDeploy() method.")
        steps.echo "echo from class"
    }
}