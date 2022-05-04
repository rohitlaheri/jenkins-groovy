#!/usr/bin/groovy

def call() {

    def code
    pipeline {
        agent any
        Stages {
            stage('scm checkout'){
                steps{
                    checkout([$class: 'GitSCM', branches: [[name: '*/dev']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/rohitlaheri/jenkins-groovy.git']]])
                }
            }
            Stage('buildTasks for Non-Prod')
                    {
                        Steps {
                            script {
                                code = load "src/pipelinejob/buildTasks.groovy"
                            }
                        }
                    }

            Stage('execute')
                    {
                        Steps {
                            script {
                                code.buildTasks
                            }
                        }
                    }

        }
    }
}