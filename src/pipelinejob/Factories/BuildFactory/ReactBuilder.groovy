package pipelinejob.Factories.BuildFactory

import pipelinejob.Factories.BuildFactory.Configuration.BuildCode

//concrete class implementing interface BuildCode to Build React code
public class ReactBuilder implements BuildCode {
    def steps
    def ReactBuilder(steps) {this.steps = steps}

    @Override
    public void runBuild() {
        steps.echo "Inside ProductionBuild::runBuild() method."
        System.out.println("Inside ProductionBuild::runBuild() method.");

    }
}