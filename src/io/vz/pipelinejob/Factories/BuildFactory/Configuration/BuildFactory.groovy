#!/usr/bin/env groovy
package io.vz.pipelinejob.Factories.BuildFactory.Configuration

import io.vz.pipelinejob.Factories.BuildFactory.AEMBuilder

//Factory class extending abstract factory to generate object of concrete class
public class BuildFactory extends BuildAbstractFactory {
    private def steps

    BuildFactory(steps) {
        this.steps = steps
    }
    @Override
    public BuildCode getBuilder(String applicationType) {
        //Get the builder based on the application type
        if (applicationType.equalsIgnoreCase("AEM")) {
            return new AEMBuilder(steps);
        }
        return null;
    }
}












