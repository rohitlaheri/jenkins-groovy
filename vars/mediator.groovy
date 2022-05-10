#!/usr/bin/groovy
import io.vz.pipelinejob.Utils.Logger
def call(param) {
    def tempParam = param
    def buildStage= false
    def logger = new Logger(this, true)

    pipeline {
        agent any
        stages {
            stage('scm checkout'){
                steps{
                    //checkout([$class: 'GitSCM', branches: [[name: '*/dev']], extensions: [], userRemoteConfigs: [[url: '']]])
                    logger.debug("log checkout")
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