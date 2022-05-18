#!/usr/bin/groovy

def call(body) {
    def pipelineParams = [: ]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()
    def repoUrl = pipelineParams.repo
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
                    //checkout([$class: 'GitSCM', branches: [[name: '*/dev']], extensions: [], userRemoteConfigs: [[url: repoUrl]]])
                    checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'gitlab', url: 'https://gitlab.com/test8620/vz-demo/my-aem-project.git']]])
                    echo "git checkout print $repoUrl"
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

                    }
                }
            }
            stage('upload to Artifactory') {
                steps {
                    script {
                        artiFactory.call(pipelineParams.SRI)
                        updateGitlabCommitStatus name: 'pipeline Succedded', state: 'success'
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