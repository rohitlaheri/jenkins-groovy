package Script.Tasks

import Script.ADT.AemPipelineParameters
import Script.Factories.AEMBuildAbstractFactory
import Script.Factories.BuildAemCode
import Script.Factories.FactoryProducer

public class BuildForDev {
    public static void main(String[] args) {
        //get AEMBuild factory
        AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory()
        //get an object of aemBuildFactory for dev env
        BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
        buildAem.runBuild()

    }
}

/*
def BuildForDev()
{
    AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory()
    BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
    buildAem.runBuild()
}*/
