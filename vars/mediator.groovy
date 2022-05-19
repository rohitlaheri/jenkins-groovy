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
                    updateGitlabCommitStatus name: 'Build', state: 'pending'
                    script {
                        try{
                            buildTasks.call(pipelineParams)
                            updateGitlabCommitStatus name: 'Build', state: 'success'
                        }
                        catch (e) {
                            updateGitlabCommitStatus name: 'Build', state: 'failed'
                        }
                    }
                }
            }
            stage('scan') {
                steps {
                    updateGitlabCommitStatus name: 'scan', state: 'pending'
                    script {
                         try{
                            scanTasks.call()
                            updateGitlabCommitStatus name: 'scan', state: 'success'
                           }
                        catch (e) {
                            updateGitlabCommitStatus name: 'scan', state: 'failed'
                        }
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
                    updateGitlabCommitStatus name: 'Quality gate', state: 'pending'
                    script {
                        try{
                            //waitForQualityGate abortPipeline: true
                            timeout(time: 1, unit: 'HOURS') {
                                def qualityGate = waitForQualityGate()
                                if (qualityGate.status == 'ERROR') {
                                    currentBuild.result = 'UNSTABLE'
                                }
                            }
                        updateGitlabCommitStatus name: 'Quality gate', state: 'success'
                        }catch (e) {
                            updateGitlabCommitStatus name: 'Quality gate', state: 'failed'
                        }
                    }

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
