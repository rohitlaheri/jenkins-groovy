#!/usr/bin/groovy

import io.vz.pipelinejob.Factories.BuildFactory.Configuration.*


def call(def param)
{
    def appType = param.appType
    def jObject = readJSON file: 'configuration.json'
    echo jObject
    //def shell = libraryResource 'configuration.json
    echo appType
    BuildAbstractFactory aemBuildFactory = BuildFactoryProducer.getFactory(this)
    echo "Producer called build"
    BuildCode buildAem = aemBuildFactory.getBuilder(appType)
    echo "Factory Generated build"
    buildAem.runBuild(param)
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


