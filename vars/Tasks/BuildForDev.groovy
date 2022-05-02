#!/usr/bin/groovy

import pipelinejob.ADT.AemPipelineParameters


/*import pipelinejob.ADT.AemPipelineParameters
import pipelinejob.Factories.AEMBuildAbstractFactory
import pipelinejob.Factories.BuildAemCode
import pipelinejob.Factories.FactoryProducer



public class BuildForDev {
    public static void main(String[] args) {
        //get AEMBuild factory
        AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory()
        //get an object of aemBuildFactory for dev env
        BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
        buildAem.runBuild()
        buildAem.pus

    }
}*/

@NonCPS
/*def buildAem()
{
    Script.Factories.AEMBuildAbstractFactory aemBuildFactory = Script.Factories.FactoryProducer.getFactory()
    Script.Factories.BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
    buildAem.runBuild()
}
return this*/


def callParam()
{
    def param = new AemPipelineParameters('release2.2.0', '', 'latest' )
    println("inside the call param")
    def aurl = param.getDeploymentTarget()
    println(aurl)
}
return this