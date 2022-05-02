package pipelinejob.Factories

//Create a Factory generator class to get factories
public class FactoryProducer {
    public static final AEMBuildAbstractFactory getFactory(){
        return new AEMBuildFactory();
    }
}