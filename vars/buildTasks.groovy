//#!/usr/bin/groovy

import pipelinejob.Factories.*


/*def call()
{
    echo "inside build"
    AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory()
    BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
    buildAem.runBuild()
}*/
//return this

public class buildTasks{
    public static void main (String[] args)
    {
        AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory()
        BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
        buildAem.runBuild()
    }
}