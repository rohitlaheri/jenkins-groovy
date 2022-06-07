#!/usr/bin/groovy

import io.vz.pipelinejob.Factories.CheckOutFactory.Configuration.*


def call(Map param)
{
    def repoType = param.scmType
    CheckOutAbstractFactory checkOutAbstractFactory = CheckOutFactoryProducer.getFactory(this)
    echo "Producer called"
    CheckOutCode checkOutCode = checkOutAbstractFactory.getCheckOutFactory(repoType)
    echo "Factory Generated"
    checkOutCode.checkOut(param)
    echo "Completed"
    def ret = "return from checkoutTasks"
    return ret
}



