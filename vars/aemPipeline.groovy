#!/usr/bin/groovy
// import dependent class from src directory

def call() {
    def code
    pipeline {
        agent any
        Stages {
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