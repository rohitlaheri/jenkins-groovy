#!/usr/bin/groovy

import io.vz.pipelinejob.Factories.BuildFactory.Configuration.*
import io.vz.pipelinejob.Util.Log

def call()
{
    //Log log = new Log(steps)

    steps.log.info("Inside build")
    BuildAbstractFactory aemBuildFactory = BuildFactoryProducer.getFactory(this)
    steps.log.info("Producer called")
    BuildCode buildAem = aemBuildFactory.getBuilder('AEM')
    steps.log.info("Factory Generated")
    buildAem.runBuild()
    steps.log.info("Completed")
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


