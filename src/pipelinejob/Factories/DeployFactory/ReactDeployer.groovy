package pipelinejob.Factories.DeployFactory

import pipelinejob.Factories.DeployFactory.Configuration.DeployCode

//concrete class implementing interface DeployCode to Deploy React code
public class ReactDeployer implements DeployCode {
    def steps
    def ReactDeployer(steps) {this.steps = steps}

    @Override
    public void runDeploy() {
        steps.echo "Inside ProductionDeploy::runDeploy() method."
        System.out.println("Inside ProductionDeploy::runDeploy() method.");

    }
}