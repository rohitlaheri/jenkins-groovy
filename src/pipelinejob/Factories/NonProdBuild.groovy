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
        echo "git checkout......"
    }
    def mavenBuild()
    {
        echo "Performing maven build"
    }
    def pushToArtifact()
    {
        echo "pushing to artifact: https://artifact.url.com"
    }

    //endregion

    @Override
     public void runBuild() {
        echo "Inside NonProdBuild::runBuild() method."
        scmCheckout();
        mavenBuild();
        pushToArtifact();
    }

}