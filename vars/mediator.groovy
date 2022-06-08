#!/usr/bin/groovy
@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.5.2' )
import static groovyx.net.http.ContentType.JSON
import groovyx.net.http.RESTClient
import groovy.util.slurpersupport.GPathResult
import static groovyx.net.http.ContentType.URLENC

def call(body) {

    def pipelineParams = [: ]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

    def basecamp = new RESTClient( "https://api.publicapis.org/entries".toString() )

    def response = basecamp.get(
                       path: "projects.json",
                       headers: ["User-Agent": "My basecamp application (myemail@domain.com)"]
                    )
    echo response.data.toString(2)
    /**
     *
     */
    def repoUrl = pipelineParams.repoURL ?: env.gitlabSourceRepoHttpUrl
    def repoBranch = pipelineParams.branch ?: env.gitlabSourceBranch

    pipeline {
        agent any
        tools {
            maven 'maven'
        }
        stages {
            stage('scm checkout'){
                steps{
                    cleanWs()
                    script {
                        // temp logging //
                        echo "url $repoUrl"
                        echo "branch: $repoBranch"
                        def responseFromTasks = checkOutTasks.call(pipelineParams)
                        echo "response checkout tasks: " + responseFromTasks
                        
                    }
                }
            }
            
            // stage('build') {
            //     steps {
            //         updateGitlabCommitStatus name: 'build', state: 'pending'
            //         script {
            //                 buildTasks.call(pipelineParams)
            //                 //updateGitlabCommitStatus name: 'build', state: 'success'


            //         }
            //     }
            // }
            // stage('scan') {
            //     steps {
            //         updateGitlabCommitStatus name: 'scan', state: 'pending'
            //         script {
            //              //try{
            //                 scanTasks.call(pipelineParams)
            //                 updateGitlabCommitStatus name: 'scan', state: 'success'
            //                //}
            //             /*catch (e) {*/
            //                 //echo $e
            //                 //updateGitlabCommitStatus name: 'scan', state: 'failed'
            //                 //currentBuild.result = 'FAILURE'
            //             //}
            //         }
            //     }
            // }
            // stage('upload to Artifactory') {
            //     steps {
            //         script {
            //             artiFactory.call(pipelineParams.SRI)
            //             //updateGitlabCommitStatus name: 'pipeline Succedded', state: 'success'
            //         }
            //     }
            // }
            
            // stage("Quality gate") {
            //     steps {
            //         updateGitlabCommitStatus name: 'Quality gate', state: 'pending'
            //         script {
            //             try{
            //                 //waitForQualityGate abortPipeline: true
            //                 timeout(time: 2, unit: 'MINUTES') {
            //                     def qualityGate = waitForQualityGate()
            //                     if (qualityGate.status == 'ERROR') {
            //                         currentBuild.result = 'FAILURE'
            //                         updateGitlabCommitStatus name: 'Quality gate', state: 'failed'
            //                     }
            //                     else {
            //                         updateGitlabCommitStatus name: 'Quality gate', state: 'success'
            //                         updateGitlabCommitStatus name: 'build', state: 'success'
            //                         currentBuild.result = 'SUCCESS'
            //                     }
            //                 }

            //             }catch (Exception e) {
            //                 updateGitlabCommitStatus name: 'Quality gate', state: 'failed'
            //                 updateGitlabCommitStatus name: 'build', state: 'success'
            //             }
            //         }

            //     }
            // }
            
            // stage('release') {
            //     steps {
            //         script {
            //             deployTasks.call()
            //         }
            //     }
            // }
        }
    }
}
