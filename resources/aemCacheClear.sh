#!/bin/bash
import static PipelineConstants

echo serverDNS=${aemServer}.ebiz.verizon.com > cache.props
echo serverUsername=pipelineConstants.SVC-AWS_CICD_NP >> cache.props
echo buildUserID=pipelineConstants.BUILD_USERID >> cache.props
echo buildUserEmail=pipelineConstants.BUILD_USER_EMAIL >> cache.props
echo env=$(echo ${aemServer}) >> cache.props
echo filePath=$(echo ${filePath}) >> cache.props