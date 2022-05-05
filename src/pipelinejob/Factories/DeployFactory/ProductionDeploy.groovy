package pipelinejob.Factories.DeployFactory

//concrete class implementing interface DeployAemCode
public class ProductionDeploy implements DeployAemCode {
    def steps
    def ProductionDeploy(steps) {this.steps = steps}
    @Override
    public void runDeploy() {
        steps.echo "Inside ProductionDeploy::runDeploy() method."
        System.out.println("Inside ProductionDeploy::runDeploy() method.");

    }
}