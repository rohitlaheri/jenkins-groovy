package Script.ADT

import Script.Constants
import groovy.transform.CompileStatic


@CompileStatic
public class AemPipelineParameters {

    AemPipelineParameters(String pReleaseBranch, String previousTarget, String image_tag) {
        releaseBranch = pReleaseBranch
    }

    final String releaseBranch

    def String[] getAllDeploymentPackageZipFiles() {
        return ['Zip1, Zip2'] as String[]
    }

    def String getDeploymentTarget(String previousTarget) {
        if (previousTarget == '') {
            return Constants.DevEnv
        }
        else if (previousTarget == 'DIT')
            return Constants.SIT1
        else
            return Constants.PRD1
    }

    def String getArtifactoryUrl(String image_tag)
    {
         if(image_tag.equalsIgnoreCase('latest'))
         {
             return "create-artifactory-url"
         }
         else
             return image_tag
    }
}