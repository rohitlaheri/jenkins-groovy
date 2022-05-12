#!/usr/bin/groovy

def call(param) {
    def tempParam = param
    def buildStage= true

    pipeline {
        agent any
        stages {
            stage('scm checkout'){
                steps{
                    cleanWs()
                    checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/rohitlaheri/jenkins-groovy.git']]])
                    script {
                        log.info 'git checkout'
                        log.warning 'Nothing to do!'
                    }
                    echo "git checkout"
                }
            }
            stage('build') {
                when {
                    expression { buildStage == true }
                }
                steps {
                    script {
                        echo tempParam
                        buildTasks.call()
                    }
                }
            }
            stage('scan') {
                steps {
                    script {
                        //scanTasks.call()
                        echo "scan steps"
                    }
                }
            }
            stage('release') {
                steps {
                    script {
                        //deployTasks.call()
                        echo "release step"
                    }
                }
            }
        }
    }
}