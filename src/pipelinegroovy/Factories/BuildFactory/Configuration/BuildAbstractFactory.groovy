package pipelinegroovy.Factories.BuildFactory.Configuration

//abstract factory class
public abstract class BuildAbstractFactory {
    abstract BuildCode getBuild(String environment) ;
}



