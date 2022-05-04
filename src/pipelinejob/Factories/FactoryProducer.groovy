package pipelinejob.Factories

//Create a Factory generator class to get factories
public class FactoryProducer {
    public static AEMBuildAbstractFactory getFactory(steps){
        print "Returning Factory......"
        return new AEMBuildFactory(steps);
        print "Returned Factory......"
    }
}
