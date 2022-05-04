package pipelinejob.Factories

import pipelinejob.ADT.AemPipelineParameters

//concrete class implementing interface BuildAemCode
public class NonProdBuild implements BuildAemCode {
    def steps
    def NonProdBuild(steps) {this.steps = steps}

    private AemPipelineParameters _parameters
    public NonProdBuild(AemPipelineParameters parameters){
        _parameters = parameters;
    }

    //region Methods for build steps --start
    def scmCheckout()
    {
        print "git checkout......"
    }
    def mavenBuild()
    {
        print "Performing maven build"
        return "mavenBuild"
    }
    def pushToArtifact()
    {
        print "pushing to artifact: https://artifact.url.com"

    }

    //endregion

    @Override
     public void runBuild() {
        print("Inside NonProdBuild::runBuild() method.")
        steps.echo "echo from class"
        scmCheckout();
        mavenBuild();
        pushToArtifact();
    }
}