package pipelinejob.Factories

//Create a Factory generator class to get factories
public class FactoryProducer {
    public static AEMBuildAbstractFactory getFactory(){    
        print "Returning Factory......"
        return new AEMBuildFactory();
        print "Returned Factory......"
    }
}
