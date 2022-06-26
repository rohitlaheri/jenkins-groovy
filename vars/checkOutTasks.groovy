#!/usr/bin/groovy

import io.vz.pipelinejob.Factories.CheckOutFactory.Configuration.*
def stages = []

def call(Map param, String parent)
{
    def repoType = param.scmType
    CheckOutAbstractFactory checkOutAbstractFactory = CheckOutFactoryProducer.getFactory(this)
    echo "Producer called"
    CheckOutCode checkOutCode = checkOutAbstractFactory.getCheckOutFactory(repoType)
    echo "Factory Generated"
    checkOutCode.checkOut(param)
    stages = ["displaytaske1","displaytaske2"]
    env.STAGES = stages
    echo "STAGES = " + env.STAGES
    echo "Completed"
    echo "parent variable contains: " + parent
    def ret = "return from checkoutTasks"
    return ret
}



