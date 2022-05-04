#!/usr/bin/groovy

import pipelinejob.Factories.*

def call()
{
    //def helper = new NonProdBuild(this)
    echo "inside build"
    AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory(this)
    echo "Producer called"
    BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
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


