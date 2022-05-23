#!/usr/bin/groovy

def call(body) {

    def pipelineParams = [: ]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

    def repoUrl = pipelineParams.repoURL ?: env.gitlabSourceRepoHttpUrl
    def repoBranch = pipelineParams.branch ?: env.gitlabSourceBranch
    //def repoType = pipelineParams.scmType
    //def requestID=pipelineParams.mergeRequestID

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
                        echo "url $repoUrl"
                        echo "branch: $repoBranch"
                        checkOutTasks.call(pipelineParams)
                    }
                }
            }

            stage('build') {
                steps {
                    updateGitlabCommitStatus name: 'build', state: 'pending'
                    script {
                            buildTasks.call(pipelineParams)
                            //updateGitlabCommitStatus name: 'build', state: 'success'


                    }
                }
            }
            /*stage('scan') {
                steps {
                    updateGitlabCommitStatus name: 'scan', state: 'pending'
                    script {
                         //try{
                            scanTasks.call()
                            //updateGitlabCommitStatus name: 'scan', state: 'success'
                           //}
                       *//* catch (e) {
                            echo $e
                            updateGitlabCommitStatus name: 'scan', state: 'failed'
                            currentBuild.result = 'FAILURE'
                        }*//*
                    }
                }
            }*/
            /*stage('upload to Artifactory') {
                steps {
                    script {
                        artiFactory.call(pipelineParams.SRI)
                        //updateGitlabCommitStatus name: 'pipeline Succedded', state: 'success'
                    }
                }
            }*/
            
            /*stage("Quality gate") {
                steps {
                    updateGitlabCommitStatus name: 'Quality gate', state: 'pending'
                    script {
                        try{
                            //waitForQualityGate abortPipeline: true
                            timeout(time: 1, unit: 'HOURS') {
                                def qualityGate = waitForQualityGate()
                                if (qualityGate.status == 'ERROR') {
                                    currentBuild.result = 'FAILURE'
                                    updateGitlabCommitStatus name: 'Quality gate', state: 'failed'
                                }
                                else {
                                    updateGitlabCommitStatus name: 'Quality gate', state: 'failed'
                                    updateGitlabCommitStatus name: 'build', state: 'success'
                                }
                            }

                        }catch (e) {
                            updateGitlabCommitStatus name: 'Quality gate', state: 'failed'
                        }
                    }

                }
            }*/
            
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
