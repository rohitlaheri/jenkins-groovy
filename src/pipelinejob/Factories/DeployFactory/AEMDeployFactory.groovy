#!/usr/bin/env groovy
package pipelinejob.Factories.DeployFactory

//Factory class extending abstract factory  to generate object of concrete class
public class AEMDeployFactory extends AEMDeployAbstractFactory {
    private def steps

    AEMDeployFactory(steps) {
        this.steps = steps
    }
    @Override
    public DeployAemCode getDeploy(String environment) {
        if (environment.equalsIgnoreCase("dev")) {
            return new NonProdDeploy(steps);
        } else if (environment.equalsIgnoreCase("prod")) {
            return new ProductionDeploy(steps);
        }
        return null;
    }
}













