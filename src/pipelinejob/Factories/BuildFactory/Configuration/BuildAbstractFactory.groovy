package pipelinejob.Factories.BuildFactory.Configuration

//Abstract Factory class to get Builder based on Application Type
public abstract class BuildAbstractFactory {
    abstract BuildCode getBuilder(String applicationType) ;
}



