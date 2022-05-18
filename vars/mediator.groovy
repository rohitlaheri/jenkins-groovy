#!/usr/bin/groovy

def call(body) {
    def pipelineParams = [: ]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()
    def repoUrl = pipelineParams.repo
    //calling config.json from the resources dir

    pipeline {
        agent any
        tools {
            maven 'maven'
        }
        stages {
            stage('scm checkout'){
                steps{
                    //checkout([$class: 'GitSCM', branches: [[name: '*/dev']], extensions: [], userRemoteConfigs: [[url: repoUrl]]])
                    checkout([$class: 'GitSCM', branches: [[name: '*/rohitlaheri-master-patch-32334']], extensions: [], userRemoteConfigs: [[credentialsId: 'gitlab', url: 'https://gitlab.com/test8620/vz-demo/my-aem-project.git']]])
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
                    script {
                        scanTasks.call()
                    }
                }
            }
            stage('upload to Artifactory') {
                steps {
                    script {
                        artiFactory.call(pipelineParams.SRI)
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