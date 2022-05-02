#!/usr/bin/env groovy
package pipelinejob.Factories
import pipelinejob.ADT.AemPipelineParameters

//interface BuildAemCode
public interface BuildAemCode {
    void runBuild();
}

//abstract factory class
public abstract class AEMBuildAbstractFactory {
    abstract BuildAemCode getBuild(String environment) ;
}

//Factory class extending abstract factory  to generate object of concrete class
public class AEMBuildFactory extends AEMBuildAbstractFactory {
    @Override
    public BuildAemCode getBuild(String environment) {
        if (environment.equalsIgnoreCase("dev")) {
            return new NonProdBuild();
        } else if (environment.equalsIgnoreCase("prod")) {
            return new ProductionBuild();
        }
        return null;
    }
}

//Create a Factory generator class to get factories
public class FactoryProducer {
    public static AEMBuildAbstractFactory getFactory(){
        return new AEMBuildFactory();
    }
}











