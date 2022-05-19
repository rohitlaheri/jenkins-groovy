#!/usr/bin/groovy

def call(body) {

    def pipelineParams = [: ]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()
    def repoUrl = pipelineParams.repoURL ?: env.gitlabSourceRepoHttpUrl
    def repoBranch = pipelineParams.branch ?: env.gitlabSourceBranch
    def repoType = pipelineParams.scmType

    //calling config.json from the resources dir
    //logice ti fetch mr branch

    pipeline {
        agent any
        tools {
            maven 'maven'
        }
        stages {
            stage('scm checkout'){
                steps{
                    script {
                        echo "url $repoUrl"
                        echo "branch: $repoBranch"
                        checkOutTasks.call(repoUrl,repoBranch,repoType)
                    }

                }
            }

            stage('build') {
                steps {
                    script {
                        buildTasks.call(pipelineParams)
                    }
                }
            }
            stage('scan') {
                steps {
                    updateGitlabCommitStatus name: 'Scan started', state: 'pending'
                    script {
                        scanTasks.call()
                        updateGitlabCommitStatus name: 'Scan Completed', state: 'success'
                    }
                }
            }
            /*stage('upload to Artifactory') {
                steps {
                    script {
                        artiFactory.call(pipelineParams.SRI)
                        //updateGitlabCommitStatus name: 'pipeline Succedded', state: 'success'
                    }
                }
            }*/
            
            stage("Quality gate") {
                steps {
                    updateGitlabCommitStatus name: 'Quality Gate response', state: 'pending'
                    //waitForQualityGate abortPipeline: true
                    timeout(time: 1, unit: 'HOURS') {
                        def qualityGate = waitForQualityGate()
                        if (qualityGate.status == 'ERROR') {
                            currentBuild.result = 'UNSTABLE'
                        }
                    }
                    updateGitlabCommitStatus name: 'Quality Gate response', state: 'success'
                    updateGitlabCommitStatus name: 'pipeline Succedded', state: 'success'
                }
            }
            
            /*stage('release') {
                steps {
                    script {
                        deployTasks.call()
                    }
                }
            }*/
        }
    }
}
