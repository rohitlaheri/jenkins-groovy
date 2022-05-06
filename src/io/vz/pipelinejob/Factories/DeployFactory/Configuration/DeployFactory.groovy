#!/usr/bin/env groovy
package io.vz.pipelinejob.Factories.DeployFactory.Configuration

import io.vz.pipelinejob.Factories.DeployFactory.AEMDeployer

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
        }
        return null;
    }
}













