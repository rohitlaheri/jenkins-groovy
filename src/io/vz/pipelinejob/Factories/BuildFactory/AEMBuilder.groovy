package io.vz.pipelinejob.Factories.BuildFactory

import io.vz.pipelinejob.Model.pipelineParameters
import io.vz.pipelinejob.Factories.BuildFactory.Configuration.BuildCode

//concrete class implementing interface BuildCode to Build AEM code
public class AEMBuilder implements BuildCode {
    def steps
    def AEMBuilder(steps) {
        this.steps = steps
    }

    /*private pipelineParameters _parameters
    public AEMBuilder(pipelineParameters parameters){
        _parameters = parameters;
    }*/

    //region Methods for build steps --start
    def execShell()
    {
        def proc ='./resources/testscript.sh -a "test" -b "sda" -c "dad"'.execute()
        steps.echo proc
    }
    //endregion

    @Override
     public void runBuild() {
        steps.log.info("Logger Test")
        steps.echo "echo from class"
        execShell();

    }
}