#!/usr/bin/groovy
package pipelinejob.ADT
import pipelinejob.pipelineConstants
public class AemPipelineParameters {
    String imageTag
    String previousTarget
    AemPipelineParameters(String pReleaseBranch, String previous_Target, String image_tag) {
        releaseBranch = pReleaseBranch
        this.imageTag = image_tag
        this.previousTarget = previous_Target
    }

    final String releaseBranch

    String[] getAllDeploymentPackageZipFiles() {
        return ['Zip1, Zip2'] as String[]
    }
    String getDeploymentTarget() {
        println("inside getDeploymentTarget")
        if (previousTarget == '') {
            return pipelineConstants.DevEnv
        }
        else if (previousTarget == 'DIT')
            return pipelineConstants.SIT1
        else
            return pipelineConstants.PRD1
    }
    public def getArtifactoryUrl()
    {
         if(imageTag.equalsIgnoreCase('latest'))
         {
             echo "artifactory url"
             return "create-artifactory-url"
         }
         else
             return imageTag
    }
}