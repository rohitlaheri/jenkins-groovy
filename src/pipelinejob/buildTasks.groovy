package pipelinejob

import pipelinejob.Factories.AEMBuildAbstractFactory
import pipelinejob.Factories.BuildAemCode
import pipelinejob.Factories.FactoryProducer

/*public class buildTasks{
    public static void main (String[] args)
    {
        AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory()
        BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
        buildAem.runBuild()
    }
}*/

def buildAEM()
{
    echo "inside build"
    AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory()
    BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
    buildAem.runBuild()
}