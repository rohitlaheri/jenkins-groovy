package pipelinejob.Factories

//Create a Factory generator class to get factories
public class FactoryProducer {
    public static AEMBuildAbstractFactory getFactory(){    
        return new AEMBuildFactory();
    }
}
