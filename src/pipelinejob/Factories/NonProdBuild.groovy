package pipelinejob.Factories

//concrete class implementing interface BuildAemCode
public class NonProdBuild implements BuildAemCode {
    /*private AemPipelineParameters _parameters
    public NonProdBuild(AemPipelineParameters parameters){
        _parameters = parameters;
    }*/

    //region Methods for build steps --start
    String scmCheckout()
    {
        echo "git checkout......"
    }
    String mavenBuild()
    {
        echo "Performing maven build"
    }
    String pushToArtifact()
    {
        echo "pushing to artifact: https://artifact.url.com"
    }

    //endregion

    @Override
     public String runBuild() {
        echo "Inside NonProdBuild::runBuild() method."
        scmCheckout();
        mavenBuild();
        pushToArtifact();
    }

}