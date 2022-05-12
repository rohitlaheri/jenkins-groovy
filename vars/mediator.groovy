#!/usr/bin/groovy

def call(param) {
    def tempParam = param
    def buildStage= true

    pipeline {
        agent any
         options {
             warnError()
             timestamps()
        }
        stages {
            stage('scm checkout'){
                steps{
                    cleanWs()
                    checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/rohitlaheri/jenkins-groovy.git']]])
                    script {
                        log.info 'Git checkout'
                    }
                }
            }
            stage('build') {
                when {
                    expression { buildStage == true }
                }
                steps {
                    script {
                        script {
                            log.info tempParam
                        }
                        buildTasks.call()
                    }
                }
            }
            stage('scan') {
                steps {
                    script {
                        //scanTasks.call()
                        script {
                            log.info "Scan steps"
                        }
                    }
                }
            }
            stage('release') {
                steps {
                    script {
                        //deployTasks.call()
                        script {
                            log.info "Release step"
                        }
                    }
                }
            }
        }
    }
}