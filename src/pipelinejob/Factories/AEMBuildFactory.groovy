#!/usr/bin/env groovy
package pipelinejob.Factories

//Factory class extending abstract factory  to generate object of concrete class
public class AEMBuildFactory extends AEMBuildAbstractFactory {
    private def steps
    AEMBuildFactory(steps) {
        this.steps = steps
    }
    @Override
    public BuildAemCode getBuild(String environment) {
        if (environment.equalsIgnoreCase("dev")) {
            return new NonProdBuild(steps);
        } else if (environment.equalsIgnoreCase("prod")) {
            return new ProductionBuild(steps);
        }
        return null;
    }
}













