#!/usr/bin/groovy

import io.vz.pipelinejob.Factories.CheckOutFactory.Configuration.*


def call(def repoURL, def repoBranch)
{
    def sourceType = param.scmType
    echo sourceType
    //def repoURL = param.repoURL
    //def repoBranch = param.branch
    CheckOutAbstractFactory checkOutAbstractFactory = CheckOutFactoryProducer.getFactory(this)
    echo "Producer called"
    CheckOutCode checkOutCode = checkOutAbstractFactory.getCheckOutFactory(sourceType)
    echo "Factory Generated"
    checkOutCode.checkOut(repoURL,repoBranch)
    echo "Completed"
}



