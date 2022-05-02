#!/usr/bin/env groovy
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
@NonCPS
def buildAem()
{
    Script.Factories.AEMBuildAbstractFactory aemBuildFactory = Script.Factories.FactoryProducer.getFactory()
    Script.Factories.BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
    buildAem.runBuild()
}
return this
*/
