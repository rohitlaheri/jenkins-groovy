#!/usr/bin/groovy
package pipelinejob.ADT

import pipelinejob.Constants

public class AemPipelineParameters {
    String imageTag
    String previousTarget
    AemPipelineParameters(String pReleaseBranch, String previousTarget, String image_tag) {
        releaseBranch = pReleaseBranch
        this.imageTag = image_tag
        this.previousTarget = previousTarget
    }

    final String releaseBranch

   /* def String[] getAllDeploymentPackageZipFiles() {
        return ['Zip1, Zip2'] as String[]
    }
*/
    def String getDeploymentTarget() {
        if (previousTarget == '') {
            return Constants.DevEnv
        }
        else if (previousTarget == 'DIT')
            return Constants.SIT1
        else
            return Constants.PRD1
    }
    def String getArtifactoryUrl()
    {
         if(imageTag.equalsIgnoreCase('latest'))
         {
             return "create-artifactory-url"
         }
         else
             return imageTag
    }
}