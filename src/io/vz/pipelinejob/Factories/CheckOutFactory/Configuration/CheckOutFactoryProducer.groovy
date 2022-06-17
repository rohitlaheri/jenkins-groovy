package io.vz.pipelinejob.Factories.CheckOutFactory.Configuration

//Create a Factory generator class to get factories
public class CheckOutFactoryProducer implements Serializable {
    public static CheckOutAbstractFactory getFactory(steps){
        
        return new CheckOutFactory(steps)

    }
}
