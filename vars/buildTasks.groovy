#!/usr/bin/groovy

import pipelinejob.Factories.AEMBuildAbstractFactory
import pipelinejob.Factories.BuildAemCode
import pipelinejob.Factories.FactoryProducer
import pipelinejob.ADT.AemPipelineParameters


def call()
{
    echo "inside buildAem"
    AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory()
    BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
    buildAem.runBuild()
}


//@NonCPS
/*def static AemBuild()
{
    println("inside buildAem")
    AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory()
    BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
    buildAem.runBuild()
}*/
//return this


/*def callParam()
{
    def param = new AemPipelineParameters('release2.2.0', 'DIT', 'latest' )
    println("inside the call param")

    def aurl = param.releaseBranch
    println(aurl)
}*/
//return this
