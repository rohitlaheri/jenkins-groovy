package pipelinejob.Factories.BuildFactory.Configuration

//abstract factory class
public abstract class AEMBuildAbstractFactory {
    abstract BuildAemCode getBuild(String environment) ;
}



