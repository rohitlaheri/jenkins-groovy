package pipelinejob.Factories

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