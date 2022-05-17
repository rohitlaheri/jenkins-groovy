#!/usr/bin/groovy

import io.vz.pipelinejob.Factories.BuildFactory.Configuration.*


def call(def param)
{
    def appType = param.appType
    //def shell = libraryResource 'configuration.json'
    writeFile file:'configuration.json', text:libraryResource("configuration.json")
    env.TestName="Rohit"
    echo " test env name ${TestName}"
    echo appType
    BuildAbstractFactory aemBuildFactory = BuildFactoryProducer.getFactory(this)
    echo "Producer called"
    BuildCode buildAem = aemBuildFactory.getBuilder(appType)
    echo "Factory Generated"
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



