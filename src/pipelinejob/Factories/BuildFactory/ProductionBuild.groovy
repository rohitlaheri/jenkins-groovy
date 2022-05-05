package pipelinejob.Factories.BuildFactory

import pipelinejob.Factories.BuildFactory.Configuration.BuildAemCode

//concrete class implementing interface BuildAemCode
public class ProductionBuild implements BuildAemCode {
    def steps
    def ProductionBuild(steps) {this.steps = steps}
    @Override
    public void runBuild() {
        steps.echo "Inside ProductionBuild::runBuild() method."
        System.out.println("Inside ProductionBuild::runBuild() method.");

    }
}