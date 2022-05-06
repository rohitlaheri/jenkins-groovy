#!/usr/bin/env groovy
package pipelinejob.Factories.DeployFactory.Configuration

import pipelinejob.Factories.DeployFactory.AEMDeployer
import pipelinejob.Factories.DeployFactory.ReactDeployer

//Factory class extending abstract factory to generate object of concrete class
public class DeployFactory extends DeployAbstractFactory {
    private def steps

    DeployFactory(steps) {
        this.steps = steps
    }
    @Override
    public DeployCode getDeployer(String applicationType) {
        if (applicationType.equalsIgnoreCase("AEM")) {
            return new AEMDeployer(steps);
        } else if (applicationType.equalsIgnoreCase("REACT")) {
            return new ReactDeployer(steps);
        }
        return null;
    }
}













