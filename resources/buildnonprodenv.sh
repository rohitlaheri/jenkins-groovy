#!/bin/bash

set -xv

usage() {
 
   tput bold; echo "Artifact Name should be of the below format"
   tput bold; echo "B6MV_ECOMMERCE/2018.01.23/aem/wcms-ui-apps-1.0.1.zip.12221120.2"
   echo  "OR"
   tput bold; echo "NV4592_TYPEBMWAUTOMATION/AEM/6.3/AEM-6.3-Service-Pack-1-6.3.1.zip"
   echo  "OR"
   tput bold; echo "CV9V_ONEDIGITALMOBILE/2017.08.08/CommonAkka/commonakka-1.0.zip.07271235.53"
   echo  "OR"
   tput bold; echo "CV9V_1DPROSPECT/2017.08.08/CommonAkka/commonakka-1.0.zip.07271235.53"
   tput sgr0
   exit 101
  
}


if [[ -z "${artifactory_url}" ]]
then
    usage
fi

if [[ ! "^${artifactory_url}" =~ "NV4592_TYPEBMWAUTOMATION" ]] && [[ ! "^${artifactory_url}" =~ "CV9V_ONEDIGITALMOBILE" ]] && [[ ! "^${artifactory_url}" =~ "CV9V_1DCOMMON" ]]  && [[ ! "^${artifactory_url}" =~ "B6MV_ECOMMERCE" ]] && [[ ! "^${artifactory_url}" =~ "HIVV_SOE" ]]  
then
    usage
fi

echo SERVER_DNS=${env}.ebiz.verizon.com > deploy.props
echo SERVER_USERNAME=svc-aws_cicd_np >> deploy.props


## Capture the Version Variables
REPO_URL="https://oneartifactoryprod.verizon.com/artifactory"
artifactory_url="${REPO_URL}/${artifactory_url}"
#uservice_base=`echo "${artifact_name}" | awk -F/ '{print $NF}' | awk -F[-,.] '{print $1}'`
package=`echo "${artifactory_url}" | awk -F/ '{print $NF}'`

if [[ -z "${package}" ]]
then
    usage
fi


#echo SERVER_DNS=${env}od-app1.ebiz.verizon.com > deploy.props
echo BUILD_USER_ID="kalluva" >> deploy.props
echo BUILD_USER_EMAIL="vamsi.kalluri@verizonwireless.com" >> deploy.props
echo env=$(echo ${env}) >> deploy.props
echo artifactory_url=$(echo ${artifactory_url}) >> deploy.props
echo package=$(echo ${package}) >> deploy.props
#echo artifact_name=$(echo ${artifact_name}) >> deploy.props
echo SOE_ARTIFACTORY_PASSWORD=$(echo ${SOE_ARTIFACTORY_PASSWORD}) >> deploy.props
echo AEM_PASSWORD=$(echo ${AEM_PASSWORD}) >> deploy.props