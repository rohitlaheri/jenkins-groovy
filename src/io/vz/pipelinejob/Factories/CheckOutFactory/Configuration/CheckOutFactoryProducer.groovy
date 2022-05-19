package io.vz.pipelinejob.Factories.CheckOutFactory.Configuration

//Create a Factory generator class to get factories
public class CheckOutFactoryProducer {
    public static CheckOutAbstractFactory getFactory(steps){
        print "Returning Factory......"
        return new CheckOutFactory(steps)

    }
}