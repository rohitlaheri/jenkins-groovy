#!/usr/bin/groovy
package pipelinejob.Tasks

import ADT.*

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

    }
}*/
/*
@NonCPS
def buildAem()
{
    Script.Factories.AEMBuildAbstractFactory aemBuildFactory = Script.Factories.FactoryProducer.getFactory()
    Script.Factories.BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
    buildAem.runBuild()
}
return this
*/

def callParam()
{
    def param = new AemPipelineParameters('release2.2.0', 'DIT', 'latest' )
    return param.getArtifactoryUrl()
}
