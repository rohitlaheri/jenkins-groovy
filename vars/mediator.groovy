#!/usr/bin/groovy

def call(param) {
    def tempParam = param
    def buildStage= false

    pipeline {
        agent any
        stages {
            stage('scm checkout'){
                steps{
                    //checkout([$class: 'GitSCM', branches: [[name: '*/dev']], extensions: [], userRemoteConfigs: [[url: '']]])
                    script {
                        log.info 'Starting'
                        log.warning 'Nothing to do!'
                    }
                    log.info "git checkout"
                }
            }
            stage('build') {
                when {
                    expression { buildStage == true }
                }
                steps {
                    script {
                        echo tempParam
                        //buildTasks.call()
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