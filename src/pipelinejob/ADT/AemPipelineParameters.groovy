#!/usr/bin/groovy
package pipelinejob.ADT



public class AemPipelineParameters {
    String imageTag
    String previousTarget
    AemPipelineParameters(String pReleaseBranch, String previous_Target, String image_tag) {
        releaseBranch = pReleaseBranch
        this.imageTag = image_tag
        this.previousTarget = previous_Target
    }

    final String releaseBranch

   /* def String[] getAllDeploymentPackageZipFiles() {
        return ['Zip1, Zip2'] as String[]
    }
*/
    /*def String getDeploymentTarget() {
        println("inside getDeploymentTarget")
        if (previousTarget == '') {
            return pipelineConstants.DevEnv
        }
        else if (previousTarget == 'DIT')
            return pipelineConstants.SIT1
        else
            return pipelineConstants.PRD1
    }*/
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