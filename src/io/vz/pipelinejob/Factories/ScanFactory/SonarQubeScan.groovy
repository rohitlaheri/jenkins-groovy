package io.vz.pipelinejob.Factories.ScanFactory

import io.vz.pipelinejob.Factories.ScanFactory.Configuration.ScanCode

public class SonarQubeScan implements ScanCode, Serializable {
    def steps
    SonarQubeScan(steps) {this.steps = steps}

    def scanSonar(Map param){
        def sonarQubeEnv = param.sonarQubeEnv
        //def mrKey = steps.sh(returnStdout: true, script: "echo ${steps.env.BRANCH_NAME} | cut -d'-' -f2").trim()
        def prKey = steps.sh(returnStdout: true, script: "echo ${steps.env.gitlabMergeRequestIid}")
        def prBranchName = steps.sh(returnStdout: true, script: "echo ${steps.env.gitlabBranch}")
        //steps.echo "mrKey: ${mrKey}"
        steps.echo "mrKey: ${prKey}"
        steps.echo "mrKey: ${prBranchName}"
        steps.withSonarQubeEnv(sonarQubeEnv) {
            //steps.sh 'mvn clean package sonar:sonar -Dsonar.projectKey=my-aem-project -Dsonar.projectName=my-aem-project -Dsonar.issuesReport.xml.enable=true'
         steps.sh "mvn clean package sonar:sonar -X -Dsonar.host.url=https://a84b-173-71-125-24.ngrok.io -Dsonar.login=976b6274cf5f2bf30f80ca62064f1c0520032f6e -Dsonar.projectKey=prdecproj  -Dsonar.pullrequest.branch=${steps.env.gitlabBranch} -Dsonar.pullrequest.key=${steps.env.gitlabMergeRequestIid} -Dsonar.pullrequest.base=master -Dsonar.exclusions=core -Dsonar.forceAuthentication=true -Dsonar.verbose=true -Dsonar.coverage.jacoco.xmlReportPaths=bundles/core/target/site/jacoco/jacoco.xml -Dsonar.issuesReport.xml.enable=true -Dsonar.analysis.mode=publish -Dsonar.gitlab.unique_issue_per_inline=true"
           
        }
    }
    
    def scanSonarJava(Map param){
        def sonarQubeEnv = param.sonarQubeEnv
        //def mrKey = steps.sh(returnStdout: true, script: "echo ${steps.env.BRANCH_NAME} | cut -d'-' -f2").trim()
        def prKey = steps.sh(returnStdout: true, script: "echo ${steps.env.gitlabMergeRequestIid}")
        def prBranchName = steps.sh(returnStdout: true, script: "echo ${steps.env.gitlabBranch}")
        //steps.echo "mrKey: ${mrKey}"
        steps.echo "mrKey: ${prKey}"
        steps.echo "mrKey: ${prBranchName}"
        steps.withSonarQubeEnv(sonarQubeEnv) {
            //steps.sh 'mvn clean package sonar:sonar -Dsonar.projectKey=my-aem-project -Dsonar.projectName=my-aem-project -Dsonar.issuesReport.xml.enable=true'
         steps.sh "mvn --batch-mode verify sonar:sonar -X -Dsonar.host.url=https://a84b-173-71-125-24.ngrok.io -Dsonar.login=976b6274cf5f2bf30f80ca62064f1c0520032f6e -Dsonar.projectKey=prdecproj -Dsonar.issuesReport.console.enable=true -Dsonar.gitlab.url=https://gitlab.com/unifpip/jacoco-maven-unittestv2 -Dsonar.gitlab.user_token=glpat-V74B8PpNZXvrtVcQ3svA -Dsonar.gitlab.ignore_ssl=true -Dsonar.analysis.mode=preview -Dsonar.gitlab.commit_sha=${steps.env.gitlabMergeRequestLastCommit} -Dsonar.gitlab.project_id=${steps.env.gitlabMergeRequestTargetProjectId} -Dsonar.pullrequest.branch=${steps.env.gitlabBranch} -Dsonar.gitlab.ref_name=develop -Dsonar.pullrequest.key=${steps.env.gitlabMergeRequestIid} -Dsonar.pullrequest.base=${steps.env.gitlabTargetBranch} -Dsonar.forceAuthentication=true -Dsonar.verbose=true  -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml -Dsonar.java.binaries=target/** -Dsonar.scm.forceReloadAll=true -Dsonar.gitlab.unique_issue_per_inline=true -Dsonar.language=java -Dsonar.issuesReport.xml.enable=true -Dsonar.analysis.mode=publish"
           
        }
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
        if(param.java == true)
        {
            steps.echo "java true"
            scanSonarJava(param)
        }
        else {
            scanSonar(param)
        }
        
        
        //publishScanReport()

    }
}
