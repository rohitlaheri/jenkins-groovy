#!/usr/bin/groovy
import io.vz.pipelinejob.Factories.BuildFactory.AEMBuilder

def call(param) {
    def tempParam = param
    pipeline {
        agent any
        stages {
            stage('scm checkout'){
                steps{
                    //checkout([$class: 'GitSCM', branches: [[name: '*/dev']], extensions: [], userRemoteConfigs: [[url: '']]])
                    echo "git checkout"
                }
            }

            stage('build') {
                steps {
                    script {
                        echo tempParam
                        // add list of Params before passing
                        buildTasks.call(AEMBuilder) // will need to pass AEM build params at this phase.
                    }
                }
            }
            /*stage('scan') {
                steps {
                    script {
                        scanTasks.call() //  will need to know if we are to run blackduck scan or any other scans here
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