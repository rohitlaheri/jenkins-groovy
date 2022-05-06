package io.vz.pipelinejob.Factories.DeployFactory

import io.vz.pipelinejob.Model.PipelineParameters
import io.vz.pipelinejob.Factories.DeployFactory.Configuration.DeployCode

//concrete class implementing interface DeployCode to Deploy AEM code
public class AEMDeployer implements DeployCode {
    def steps
    def AEMDeployer(steps) {this.steps = steps}

    private PipelineParameters _parameters
    public AEMDeployer(PipelineParameters parameters){
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