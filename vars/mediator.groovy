#!/usr/bin/groovy

def call(body) {
    def pipelineParams = [: ]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()
    def repoUrl = pipelineParams.repo

    pipeline {
        agent any
        stages {
            stage('scm checkout'){
                steps{
                    checkout([$class: 'GitSCM', branches: [[name: '*/dev']], extensions: [], userRemoteConfigs: [[url: repoUrl]]])
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
            stage('release') {
                steps {
                    script {
                        deployTasks.call()
                    }
                }
            }
        }
    }
}