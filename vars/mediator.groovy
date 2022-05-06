#!/usr/bin/groovy

def call() {
    pipeline {
        agent any
        stages {
            stage('scm checkout'){
                steps{
                    checkout([$class: 'GitSCM', branches: [[name: '*/dev']], extensions: [], userRemoteConfigs: [[url: '']]])
                }
            }

            stage('build') {
                steps {
                    script {
                        buildTasks.call()
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