#!/usr/bin/groovy

import io.vz.pipelinejob.Factories.BuildFactory.Configuration.*

def call()
{
    Log log = new Log(steps)

    log.info("Inside build")
    BuildAbstractFactory aemBuildFactory = BuildFactoryProducer.getFactory(this)
    log.info("Producer called")
    BuildCode buildAem = aemBuildFactory.getBuilder('AEM')
    log.info("Factory Generated")
    buildAem.runBuild()
    log.info("Completed")
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



