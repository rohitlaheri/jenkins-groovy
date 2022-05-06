package io.vz.pipelinejob.Factories.DeployFactory.Configuration

//Abstract Factory class to get Deployer based on Application Type
public abstract class DeployAbstractFactory {
    abstract DeployCode getDeployer(String applicationType) ;
}



