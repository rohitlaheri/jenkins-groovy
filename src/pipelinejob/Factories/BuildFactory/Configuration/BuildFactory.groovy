#!/usr/bin/env groovy
package pipelinejob.Factories.BuildFactory.Configuration

import pipelinejob.Factories.BuildFactory.AEMBuilder
import pipelinejob.Factories.BuildFactory.ReactBuilder

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
        } else if (applicationType.equalsIgnoreCase("REACT")) {
            return new ReactBuilder(steps);
        }
        return null;
    }
}













