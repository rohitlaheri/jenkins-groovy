package pipelinejob.Factories.DeployFactory

//abstract factory class
public abstract class AEMDeployAbstractFactory {
    abstract DeployAemCode getDeploy(String environment) ;
}



