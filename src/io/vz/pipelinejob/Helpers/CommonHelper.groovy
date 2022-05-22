package io.vz.pipelinejob.Helpers

import groovy.json.JsonSlurper

public class CommonHelper{
    //def jsonParser
    def steps
    CommonHelper(steps){
        /* def jsonParser=new JsonSlurper()
         this.jsonParser = jsonParser*/
        this.steps = steps
        this.steps.echo "test inside helper"

    }

    public def getModuleConfiguartion(){
        steps.echo "inside get module"
        def jsonConfig = jsonConfigParse()
        steps.echo "printing jsonConfig"
        steps.echo jsonConfig
        //def deserializedJson= jsonParser.parse(new File('configuration.json'))
        return jsonConfig.module.AEMModule;
    }
    public def getAEMDevServerConfiguration(){
        //def deserializedJson=jsonParser.parse(new File('configuration.json'))
        def jsonConfig = jsonConfigParse()
        return jsonConfig.AEMServer.DevServer;
    }
    public def getAEMQAServerConfiguration(){
       // def deserializedJson=jsonParser.parse(new File('configuration.json'))
        def jsonConfig = jsonConfigParse()
        return jsonConfig.AEMServer.QAServer;
    }
    public def jsonConfigParse() {
        steps.echo "json parse method"
        def jsonSlurper = new JsonSlurper()
        steps.sh "ls"
        steps.sh "cat configuration.json"
        def jsonConfig = jsonSlurper.parse(new File('configuration.json'))
        return jsonConfig
    }
}
