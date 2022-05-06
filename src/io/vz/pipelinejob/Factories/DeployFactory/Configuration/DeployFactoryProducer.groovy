package io.vz.pipelinejob.Factories.DeployFactory.Configuration

//Create a Factory generator class to get factories
public class DeployFactoryProducer {
    public static DeployAbstractFactory getFactory(steps){
        print "Returning Factory......"
        return new DeployFactory(steps);
        print "Returned Factory......"
    }
}
