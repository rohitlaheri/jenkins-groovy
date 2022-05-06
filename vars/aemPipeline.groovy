#!/usr/bin/groovy

def call() {
    echo "pipeline call"

    def code
    def out
    def config = new HashMap()
    def bindings = getBinding()
    config.putAll(bindings.getVariables())
    out = config['out']


    pipeline {
        agent any
        stages {
            stage('scm checkout'){
                steps{
                    checkout([$class: 'GitSCM', branches: [[name: '*/dev']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/rohitlaheri/jenkins-groovy.git']]])
                }
            }

            stage('buildTasks for Non-Prod') {
                steps {
                    script {
                        //code = load "src/pipelinejob/buildTasks.groovy"
                        out.println "Printed from Non-Prod."
                        startup.call()
                        //buildTasks.paramInit()
                    }
                }
            }
           /* stage("Get Env Variables") {
                steps {
                    sh "printenv"
                }
            }*/
        }
    }
}