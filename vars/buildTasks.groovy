#!/usr/bin/groovy


def call()
{
    echo "inside build"
    pipeline {
        agent any
        stages {
            stage("build from lib") {
                steps {
                    script {
                        echo "testing from lib"
                    }
                }

            }
        }
    }
    /*AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory()
    BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
    buildAem.runBuild()*/
}
return this


/*def callParam()
{
    def param = new AemPipelineParameters('release2.2.0', 'DIT', 'latest' )
    println("inside the call param")

    def aurl = param.releaseBranch
    println(aurl)
}*/
//return this
