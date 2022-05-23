#!/usr/bin/env groovy
package io.vz.pipelinejob.Factories.BuildFactory.Configuration

import io.vz.pipelinejob.Factories.BuildFactory.AEMBuilder
import io.vz.pipelinejob.Model.*

//Factory class extending abstract factory to generate object of concrete class
public class BuildFactory extends BuildAbstractFactory {
    private def steps
    def pipelineConst
    BuildFactory(steps) {
        this.steps = steps
        this.pipelineConst = new PipelineConstants()
    }
    @Override
    public BuildCode getBuilder(String applicationType) {
        //Get the builder based on the application type
        if (applicationType.equalsIgnoreCase(this.pipelineConst.APP_TYPE_AEM)) {
            return new AEMBuilder(steps);
        }
        return null;
    }
}












