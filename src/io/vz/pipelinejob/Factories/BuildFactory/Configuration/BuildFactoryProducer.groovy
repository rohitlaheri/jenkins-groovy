package io.vz.pipelinejob.Factories.BuildFactory.Configuration

//Create a Factory generator class to get factories
public class BuildFactoryProducer implements Serializable {
    public static BuildAbstractFactory getFactory(steps){
        print "Returning Factory......"
        return new BuildFactory(steps);
        print "Returned Factory......"
    }
}