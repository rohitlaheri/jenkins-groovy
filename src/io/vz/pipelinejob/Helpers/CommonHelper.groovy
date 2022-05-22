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
        def jsonConfig = jsonParse()
        steps.echo "printing jsonConfig"
        steps.echo jsonConfig
        //def deserializedJson= jsonParser.parse(new File('configuration.json'))
        return deserializedJson.module.AEMModule;
    }
    public def getAEMDevServerConfiguration(){
        //def deserializedJson=jsonParser.parse(new File('configuration.json'))
        def jsonConfig = jsonParse()
        return deserializedJson.AEMServer.DevServer;
    }
    public def getAEMQAServerConfiguration(){
       // def deserializedJson=jsonParser.parse(new File('configuration.json'))
        def jsonConfig = jsonParse()
        return deserializedJson.AEMServer.QAServer;
    }
    public def jsonConfigParse() {
        def jsonSlurper = new JsonSlurper()
        def jsonConfig = jsonSlurper.parse(new File('configuration.json'))
        return jsonConfig
    }
}
