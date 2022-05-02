#!/usr/bin/groovy
/*import Script.Factories.AEMBuildAbstractFactory
import Script.Factories.BuildAemCode
import Script.Factories.FactoryProducer*/
//import Script.ADT.AemPipelineParameters


/*public class BuildForDev {
    public static void main(String[] args) {
        //get AEMBuild factory
        AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory()
        //get an object of aemBuildFactory for dev env
        BuildAemCode buildAem = aemBuildFactory.getBuild('prod')
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
    def param = new Script.ADT.AemPipelineParameters('release2.2.0', 'DIT', 'latest' )
    return param.getArtifactoryUrl()

}
return this