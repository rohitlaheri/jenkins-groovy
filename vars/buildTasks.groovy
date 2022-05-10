#!/usr/bin/groovy

import io.vz.pipelinejob.Factories.BuildFactory.Configuration.*
import io.vz.pipelinejob.Services.SingletonService
import io.vz.pipelinejob.Utils.Logger
import io.vz.pipelinejob.Utils.ILogger

def call()
{
    SingletonService.instance.add(Logger, new Logger(this, true))
    ILogger logger = SingletonService.instance.get(Logger)

    echo "inside build"
    logger.info("Logger printed this");
    BuildAbstractFactory aemBuildFactory = BuildFactoryProducer.getFactory(this)
    echo "Producer called"
    BuildCode buildAem = aemBuildFactory.getBuilder('AEM')
    echo "Factory Generated"
    buildAem.runBuild()
    echo "Completed"
    logger.info("Logger Completed");
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



