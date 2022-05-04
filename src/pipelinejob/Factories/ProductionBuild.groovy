package pipelinejob.Factories

//concrete class implementing interface BuildAemCode
public class ProductionBuild implements BuildAemCode {
    @Override
    public void runBuild() {
        echo "Inside ProductionBuild::runBuild() method."
        System.out.println("Inside ProductionBuild::runBuild() method.");

    }
}