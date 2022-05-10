package io.vz.pipelinejob.Factories.BuildFactory

import io.vz.pipelinejob.Model.pipelineParameters
import io.vz.pipelinejob.Factories.BuildFactory.Configuration.BuildCode
import io.vz.pipelinejob.Utils.ILogger

//concrete class implementing interface BuildCode to Build AEM code
public class AEMBuilder implements BuildCode {
    def steps
    private final ILogger logger

    def AEMBuilder(steps, logger) {
        this.steps = steps
        this.logger = logger
    }

    private pipelineParameters _parameters
    public AEMBuilder(pipelineParameters parameters){
        _parameters = parameters;
    }

    //region Methods for build steps --start

    //endregion

    @Override
     public void runBuild() {
        logger.debug("Log from class")
        steps.echo "echo from class"

    }
}