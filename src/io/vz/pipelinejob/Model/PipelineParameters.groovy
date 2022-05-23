#!/usr/bin/groovy
package io.vz.pipelinejob.Model

public class pipelineParameters {
    String imageTag
    String previousTarget

    pipelineParameters(steps) {
    }

    final String releaseBranch

    final String file_path
    final String branch_name
    final String bgtraffic
    final String module
    final String deploy_env

    final String env
    final String image_tag
    boolean skip_scans
    boolean force_build

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