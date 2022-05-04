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
        print "git checkout......"
    }
    def mavenBuild()
    {
        print "Performing maven build"
    }
    def pushToArtifact()
    {
        print "pushing to artifact: https://artifact.url.com"
        script {

        }
    }

    //endregion

    @Override
     public void runBuild() {
        print("Inside NonProdBuild::runBuild() method.")
        sh "export NAME='Rohit'"
        scmCheckout();
        mavenBuild();
        pushToArtifact();
    }
}