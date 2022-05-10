#!/usr/bin/groovy

import io.vz.pipelinejob.Factories.BuildFactory.Configuration.*

def call()
{
    echo "inside build"
    BuildAbstractFactory aemBuildFactory = BuildFactoryProducer.getFactory(this, logger)
    echo "Producer called"
    BuildCode buildAem = aemBuildFactory.getBuilder('AEM')
    echo "Factory Generated"
    buildAem.runBuild()
    echo "Completed"
}
//return this

/*
public class buildTasks{
    public static void main (String[] args)
    {
        AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory()
        BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
        buildAem.runBuild()
    }
}*/



