package Script.Factories

//interface
public interface BuildAemCode {
    void runBuild();
}

//concrete class implementing interface
public class NonProdBuild implements BuildAemCode {
    @Override
    public void runBuild() {
        System.out.println("Inside NonProdBuild::runBuild() method.");
    }
}

//concrete class implementing interface
public class ProductionBuild implements BuildAemCode {
    @Override
    public void runBuild() {
        System.out.println("Inside ProductionBuild::runBuild" +
                "() method.");
    }
}


//abstract factory class
public abstract class AEMBuildAbstractFactory {
    abstract BuildAemCode getBuild(String environment) ;
}

//Factory class extending abstract factory  to generate object of concrete class
/*public class BuildAemCodeFactory extends AEMBuildFactory {
    @Override
    public BuildAemCode getBuild(String environment){
        if(environment.equalsIgnoreCase("dev")){
            return new NonProdBuild();
        }else if(environment.equalsIgnoreCase("prod")){
            return new ProductionBuild();
        }
        return null;
    }
}*/

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
public class FactoryProducer {
    public static AEMBuildAbstractFactory getFactory(){
        return new AEMBuildFactory();
    }
}
//Create a Factory generator class to get factories
/*
public class FactoryProducer {
    public static AEMBuildFactory getFactory(String environment){
        if(environment == 'prod')
            return new ProductionBuild();
        else
            return new ShapeFactory();
    }
}
*/
