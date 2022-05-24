package io.vz.pipelinejob.Factories.ScanFactory

import io.vz.pipelinejob.Factories.ScanFactory.Configuration.ScanCode

public class SonarQubeScan implements ScanCode, Serializable {
    def steps
    SonarQubeScan(steps) {this.steps = steps}

    def scanSonar(Map param){
        //def sonarQubeEnv = param.sonarQubeEnv
        def mrKey = steps.sh(returnStdout: true, script: "echo ${steps.env.BRANCH_NAME} | cut -d'-' -f2").trim()
        def prKey = steps.sh(returnStdout: true, script: "echo ${steps.env.gitlabMergeRequestIid}")
        def prBranchName = steps.sh(returnStdout: true, script: "echo ${steps.env.gitlabBranch}")
        steps.echo "mrKey: ${mrKey}"
        steps.echo "mrKey: ${prKey}"
        teps.echo "mrKey: ${prBranchName}"
        /*steps.withSonarQubeEnv(sonarQubeEnv) {
            //steps.sh 'mvn clean package sonar:sonar -Dsonar.projectKey=my-aem-project -Dsonar.projectName=my-aem-project -Dsonar.issuesReport.xml.enable=true'
           steps.sh "mvn sonar:sonar -Dsonar.projectKey=${param.sonarProjectKey} -Dsonar.host.url=${param.sonarServerURL} " +
                   "-Dsonar.login=${param.sonarAuthKey} -Dsonar.forceAuthentication=true " +
                   "-Dsonar.pullrequest.branch=${env.BRANCH_NAME} -Dsonar.pullrequest.key=${mrKey}"
            *//*steps.sh "sonar:sonar -Dsonar.host.url=${SONAR_HOST_URL} "+
                    "-Dsonar.login=${SONAR_AUTH_TOKEN} -Dsonar.sources=src/main/java/ -Dsonar.test.exclusions=src/test/java -Dsonar.analysis.mode=publish -Dsonar.issuesReport.xml.enable=true "+
                    "-Dsonar.projectKey=VZW_HIVV_${SONAR_PROJECT_KEY} -Dsonar.branch.name=${Branch_Name} -Dsonar.projectName=VZW_HIVV_${SONAR_PROJECT_KEY} -Dsonar.projectVersion=1.0 -Dsonar.forceAuthentication=true\""
            *//*
        }*/
    }
    /**
     *  publish scan report -- wip
     * @return null
     */
    def publishScanReport(){
        try {
            def formattedDate = date.format("yyyy-MM-dd")
            println formattedDate
            def sonar_response = httpRequest acceptType: 'APPLICATION_JSON', authentication: '', consoleLogResponseBody: true, contentType: 'APPLICATION_JSON', outputFile: 'sonar_res.json', responseHandle: 'NONE', timeout: 60, url: "https://onesonarcloud.vpc.verizon.com/api/measures/search_history?from=${formattedDate}&branch=${BRANCH_NAME}&component=${SONAR_PROJECT_KEY}&metrics=coverage&ps=1000", validResponseCodes: '100:599'
            steps.sh """curl -s -u svc-wpsg-posbuild:xyz -T sonar_res.json https://oneartifactoryprod.verizon.com/artifactory/WPSG_POSAWS/NSA/config/sonar/VZW_${myVSAD}_${APP_NAME}_${BRANCH_NAME}_${formattedDate}.json"""
            def coverage_percentage = " "
            def get = 'cat sonar_res.json | jq -r \'.measures[].history[-1].value\''
            coverage_percentage = sh (script: get , returnStdout:true)
            println coverage_percentage

            echo "coverage percentage  : ${coverage_percentage}"
            if (coverage_percentage.trim() > QUALITY_THRESHOLD && coverage_percentage.trim() != "null"){
                // Need to put the Logic to send the report to SQL DB
            }
            else {
                echo "coverage percentage  : ${coverage_percentage}"
                echo "Quality Gate Failed: Aborting build"
                // Need to put the Logic to send the report to SQL DB

            }

        }catch (Throwable e) {
            echo "This is the exception ${e.toString()}"
        }
    }


    @Override
    public void runScan(Map param) {
        //steps.echo "echo from class"
        scanSonar(param)
        //publishScanReport()

    }
}
