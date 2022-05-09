#!/usr/bin/groovy

def call(param) {
    def tempParam = param
    pipeline {
        agent any
        stages {
            stage('scm checkout'){
                steps{
                    //checkout([$class: 'GitSCM', branches: [[name: '*/dev']], extensions: [], userRemoteConfigs: [[url: '']]])
                }
            }

            stage('build') {
                steps {
                    script {
                        echo tempParam
                        //buildTasks.call()
                    }
                }
            }
            /*stage('scan') {
                steps {
                    script {
                        scanTasks.call()
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