#!/usr/bin/groovy

import pipelinejob.Factories.AEMBuildAbstractFactory
import pipelinejob.Factories.BuildAemCode
import pipelinejob.Factories.FactoryProducer
import pipelinejob.ADT.AemPipelineParameters

/*

public class BuildForDev {
    public static void main(String[] args) {
        //get AEMBuild factory
        AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory()
        //get an object of aemBuildFactory for dev env
        BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
        buildAem.runBuild()
    }
}
*/

@NonCPS
/*def buildAem()
{
    AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory()
    BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
    buildAem.runBuild()
}*/
//return this


def callParam()
{
    def param = new AemPipelineParameters('release2.2.0', 'DIT', 'latest' )
    println("inside the call param")

    def aurl = param.getDeploymentTarget()
    println(aurl)
}
return this
