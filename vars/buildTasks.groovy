#!/usr/bin/groovy
/*import  pipelinejob.Factories.AEMBuildAbstractFactory
import pipelinejob.Factories.**/
def call()
{
    echo "inside build"
    pipeline{
        agent any
        stages{
            stage('inspect'){
                steps{
                    sh "pwd"
                    sh "ls"
                }
            }
            stage('Build'){
                steps {
                    script {
                        echo "calling build Tasks"

                    }

                }

            }
            /*stage('execute'){
                steps{
                    script{
                        code.AemBuild()
                    }

                }
            }*/
        }
    }
  /*  AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory()
    BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
    buildAem.runBuild()*/
}

