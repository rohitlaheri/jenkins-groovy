#!/usr/bin/groovy

import io.vz.pipelinejob.Factories.ScanFactory.Configuration.*

def call(def param)
{
    def appType = param.appType

    ScanCodeAbstractFactory scanFactory = ScanCodeFactoryProducer.getFactory(this)
    echo "Producer called"
    ScanCode scanCode = scanFactory.getFactory(appType)
    echo "Factory Generated"
    scanCode.runScan(param)
    echo "Completed"
}