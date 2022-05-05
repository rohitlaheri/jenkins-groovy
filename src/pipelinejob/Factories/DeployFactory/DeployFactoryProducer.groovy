package pipelinejob.Factories.DeployFactory

//Create a Factory generator class to get factories
public class DeployFactoryProducer {
    public static AEMDeployAbstractFactory getFactory(steps){
        print "Returning Factory......"
        return new AEMDeployFactory(steps);
        print "Returned Factory......"
    }
}
