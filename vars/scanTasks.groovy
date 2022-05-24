#!/usr/bin/groovy

import io.vz.pipelinejob.Factories.ScanFactory.Configuration.*

def call(param)
{
    def scanType = param.scanType
    ScanCodeFactoryProducer scanCodeFactoryProducer =new ScanCodeFactoryProducer ();
    ScanCodeAbstractFactory scanFactory = scanCodeFactoryProducer.getFactory(this)
    echo "Producer called"
    ScanCode scanCode = scanFactory.getScanner(scanType)
    echo "Factory Generated"
    scanCode.runScan(param)
    echo "Completed"
}
