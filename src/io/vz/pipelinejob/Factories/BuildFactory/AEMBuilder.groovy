package io.vz.pipelinejob.Factories.BuildFactory


import io.vz.pipelinejob.Factories.BuildFactory.Configuration.BuildCode

//concrete class implementing interface BuildCode to Build AEM code
public class AEMBuilder implements BuildCode {
    def steps
    //def request = libraryResource 'testscript.sh'
    def AEMBuilder(steps) {
        this.steps = steps
    }

    /*private pipelineParameters _parameters
    public AEMBuilder(pipelineParameters parameters){
        _parameters = parameters;
    }*/

    def execShell()
    {
        //def script = './testscript.sh'
        def std_out = new StringBuilder()
        def std_err = new StringBuilder()
        //steps.echo ls
        steps.sh "ls"
        steps.sh "ls src/io/vz/pipelinejob/Factories/BuildFactory/resources"
        //no longer needed- chmod'd via git index steps.sh "chmod +777 src/io/vz/pipelinejob/Factories/BuildFactory/resources"
        //steps.sh "./src/io/vz/pipelinejob/Factories/BuildFactory/resources/testscript.sh"
        def proc = 'sh ./src/io/vz/pipelinejob/Factories/BuildFactory/resources/testscript.sh'.execute()
        proc.waitForOrKill(1000)
        proc.consumeProcessOutput(std_out, std_err)

        //
        steps.echo std_out
    }

    //endregion

    @Override
     public void runBuild() {

        steps.echo "echo from class"
        //steps.echo ls
        //steps.sh "ls"
        //steps.sh "ls src/io/vz/pipelinejob/Factories/BuildFactory/resources"
        //steps.sh "./src/io/vz/pipelinejob/Factories/BuildFactory/resources/testscript.sh"

        execShell();

    }
}