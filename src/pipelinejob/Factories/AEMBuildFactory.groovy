#!/usr/bin/env groovy
package pipelinejob.Factories


//interface BuildAemCode
public interface BuildAemCode {
    void runBuild();
}

//concrete class implementing interface BuildAemCode
public class NonProdBuild implements BuildAemCode {
    /*private AemPipelineParameters _parameters
    public NonProdBuild(AemPipelineParameters parameters){
        _parameters = parameters;
    }*/

    //region Methods for build steps --start
    def scmCheckout()
    {
        println("git checkout......")
    }
    def mavenBuild()
    {
        println("Performing maven build")
    }
    def pushToArtifact()
    {
        print("pushing to artifact: https://artifact.url.com")
    }

    //endregion

    @Override
    public void runBuild() {
        System.out.println("Inside NonProdBuild::runBuild() method.");
        scmCheckout();
        mavenBuild();
        pushToArtifact();
    }

}

//concrete class implementing interface BuildAemCode
public class ProductionBuild implements BuildAemCode {
    @Override
    public void runBuild() {
        System.out.println("Inside ProductionBuild::runBuild() method.");
    }
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











