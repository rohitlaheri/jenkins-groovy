#!/usr/bin/groovy

import io.vz.pipelinejob.Factories.BuildFactory.Configuration.*
import io.vz.pipelinejob.Util.Log

def call()
{
    Log log = new Log(steps)
    echo "inside build"
    log.info("Log: Inside build")
    BuildAbstractFactory aemBuildFactory = BuildFactoryProducer.getFactory(this)
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



