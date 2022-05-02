/*
import Script.Factories.AEMBuildAbstractFactory
import Script.Factories.BuildAemCode
import Script.Factories.FactoryProducer
*/

/*public class BuildForDev {
    public static void main(String[] args) {
        //get AEMBuild factory
        AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory()
        //get an object of aemBuildFactory for dev env
        BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
        buildAem.runBuild()

    }
}*/

def buildAem()
{
    Script.Factories.AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory()
    Script.Factories.BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
    buildAem.runBuild()
}
return this