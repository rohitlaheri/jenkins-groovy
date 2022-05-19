package io.vz.pipelinejob.Factories.ScanFactory.Configuration

//Create a Factory generator class to get factories
public class ScanCodeFactoryProducer {
    public static ScanCodeAbstractFactory getFactory(steps){
        print "Returning Factory......"
        return new ScanCodeFactory(steps)

    }
}