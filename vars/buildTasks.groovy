#!/usr/bin/groovy

import pipelinejob.Factories.*


def call()
{
    echo "inside build"
    AEMBuildAbstractFactory aemBuildFactory = FactoryProducer.getFactory()
    BuildAemCode buildAem = aemBuildFactory.getBuild('dev')
    buildAem.runBuild().print()

}
return this
