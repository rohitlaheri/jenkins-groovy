#!/usr/bin/env groovy
package pipelinejob.Mediators
import pipelinejob.ADT.AemPipelineParameters

class AEMPipelineMediator
{
    AemPipelineParameters parameters;
    AEMPipelineMediator(String releaseBranch, String previousTarget, String imageTag) {
        //pipeline constructor
        AemPipelineParameters parameters = new AemPipelineParameters(releaseBranch, previousTarget, imageTag)
        this.parameters = parameters

    }

}