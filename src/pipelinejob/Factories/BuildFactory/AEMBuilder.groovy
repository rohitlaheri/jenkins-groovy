package pipelinejob.Factories.BuildFactory

import pipelinejob.constants
import pipelinejob.ADT.AemPipelineParameters
import pipelinejob.Factories.BuildFactory.Configuration.BuildCode

//concrete class implementing interface BuildCode to Build AEM code
public class AEMBuilder implements BuildCode {
    def steps
    def AEMBuilder(steps) {this.steps = steps}

    private AemPipelineParameters _parameters
    public AEMBuilder(AemPipelineParameters parameters){
        _parameters = parameters;
    }

    //region Methods for build steps --start

    //endregion

    @Override
     public void runBuild() {
        println("Inside NonProdBuild::runBuild() method.")
        println(constants.MYCONSTANT)
        steps.echo "echo from class"
    }
}