#!/usr/bin/groovy

import io.vz.pipelinejob.Factories.ScanFactory.Configuration.*

def call()
{
    //def appType = param.appType

    ScanCodeAbstractFactory scanFactory = ScanCodeFactoryProducer.getFactory(this)
    echo "Producer called"
    ScanCode scanCode = scanFactory.getScanner('sonarqube')
    echo "Factory Generated"
    scanCode.runScan()
    echo "Completed"
}
