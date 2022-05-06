#!/usr/bin/groovy

import pipelinejob.Factories.BuildFactory.Configuration.*
import pipelinejob.Utils.*
import java.util.logging.ConsoleHandler
import java.util.logging.Level
import java.util.logging.Logger

def call()
{
    def logger = Logger.getLogger("")
    logger.setLevel(Level.FINEST)
    logger.addHandler (new ConsoleHandler())
    logger.info("This printed")

    def javaUtilLogger = new JavaUtilLoggerClass()
    javaUtilLogger.printAndReturnValue(3)
    echo "inside build"
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



