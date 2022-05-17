#!/bin/bash

import static PipelineConstants
set -xv


if [[ -z "${artifactoryUrl}" ]]
then
    echo "Artifact Name should be of the standard format"
fi

if [[ ! "^${artifactoryUrl}" =~ pipelineConstants.NV4592_TYPEBMWAUTOMATION ]] && [[ ! "^${artifactoryUrl}" =~ pipelineConstants.CV9V_ONEDIGITALMOBILE ]] && [[ ! "^${artifactoryUrl}" =~ pipelineConstants.CV9V_1DCOMMON ]]  && [[ ! "^${artifactoryUrl}" =~ pipelineConstants.B6MV_ECOMMERCE ]] && [[ ! "^${artifactoryUrl}" =~ pipelineConstants.HIVV_SOE ]]
then
    usage
fi

echo serverDNS=${aemServer}.ebiz.verizon.com > deploy.props
echo serverUsername=pipelineConstants.SVC-AWS_CICD_NP >> deploy.props

## Capture the Version Variables
repoUrl=pipelineConstants.ARTIFACTORY_URL
artifactoryUrl="${repoUrl}/${artifactoryUrl}"
#uservice_base=`echo "${artifact_name}" | awk -F/ '{print $NF}' | awk -F[-,.] '{print $1}'`
package=`echo "${artifactoryUrl}" | awk -F/ '{print $NF}'`

if [[ -z "${package}" ]]
then
    echo "Artifact Name should be of the standard format"
fi


#echo serverDNS=${env}od-app1.ebiz.verizon.com > deploy.props
echo buildUserID=pipelineConstants.BUILD_USERID >> deploy.props
echo buildUserEmail=pipelineConstants.BUILD_USER_EMAIL >> deploy.props
echo env=$(echo ${aemServer}) >> deploy.props
echo artifactoryUrl=$(echo ${artifactoryUrl}) >> deploy.props
echo package=$(echo ${package}) >> deploy.props
#echo artifact_name=$(echo ${artifact_name}) >> deploy.props
echo soeArtifactoryPwd=$(echo ${soeArtifactoryPwd}) >> deploy.props
echo aemPwd=$(echo ${aemPwd}) >> deploy.props