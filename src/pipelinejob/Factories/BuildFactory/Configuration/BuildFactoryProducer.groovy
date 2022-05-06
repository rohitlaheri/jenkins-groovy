package pipelinejob.Factories.BuildFactory.Configuration

//Create a Factory generator class to get factories
public class BuildFactoryProducer {
    public static BuildAbstractFactory getFactory(steps){
        print "Returning Factory......"
        return new BuildFactory(steps);
        print "Returned Factory......"
    }
}
