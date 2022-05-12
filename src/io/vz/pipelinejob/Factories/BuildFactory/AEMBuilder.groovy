package io.vz.pipelinejob.Factories.BuildFactory

import groovy.util.logging.Slf4j
import io.vz.pipelinejob.Factories.BuildFactory.Configuration.BuildCode
import org.codehaus.groovy.runtime.StringBufferWriter
import groovy.util.Logging.log


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
        //def std_out = new StringBuffer()
        //def std_err = new StringBuffer()
        def proc = 'sh ./src/io/vz/pipelinejob/Factories/BuildFactory/resources/testscript.sh'.execute()
        proc.consumeProcessOutput(std_out, std_err)
        proc.waitForOrKill(1000)
        println std_out


        //
        //steps.echo std_out
    }

    //endregion

    @Override
    @Log
     class runBuild {

        Logger logger = Logger.getLogger("")
//        steps.log.info 'before log statement'
        String message = "I am a test info log"
        log.info "Hello World"()
//        steps.log.info 'after log statement'
//        steps.log.info 'Log from class'
        //log.info 'last log statement in AEMBuilder'
        //steps.echo ls
        //steps.sh "ls"
        //steps.sh "ls src/io/vz/pipelinejob/Factories/BuildFactory/resources"
        //steps.sh "./src/io/vz/pipelinejob/Factories/BuildFactory/resources/testscript.sh"

//        execShell();
    }
}