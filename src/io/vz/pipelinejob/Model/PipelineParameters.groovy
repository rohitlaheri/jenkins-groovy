#!/usr/bin/groovy
package io.vz.pipelinejob.Model

public class PipelineParameters {
    String imageTag
    String previousTarget

    PipelineParameters(String pReleaseBranch, String previous_Target, String image_tag) {
        releaseBranch = pReleaseBranch
        this.imageTag = image_tag
        this.previousTarget = previous_Target
    }

    final String releaseBranch

    public def getArtifactoryUrl()
    {
         if(imageTag.equalsIgnoreCase('latest'))
         {

             return "create-artifactory-url"
         }
         else{
             return imageTag
         }

    }
}