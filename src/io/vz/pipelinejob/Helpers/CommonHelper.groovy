package io.vz.pipelinejob.Helpers

import groovy.json.JsonSlurper

public class CommonHelper implements  Serializable{
    def jsonParser
    def steps
    CommonHelper(steps){
        this.jsonParser= new JsonSlurper()
        this.steps = steps
        this.steps.echo "test inside helper"
    }

    public def getModuleConfiguartion(){
        def deserializedJson= this.jsonParser.parse(new File('configuration.json'))
        return deserializedJson.module.AEMModule;
    }
    public def getAEMDevServerConfiguration(){
        def deserializedJson= this.jsonParser.parse(new File('configuration.json'))
        return deserializedJson.AEMServer.DevServer;
    }
    public def getAEMQAServerConfiguration(){
        def deserializedJson= this.jsonParser.parse(new File('configuration.json'))
        return deserializedJson.AEMServer.QAServer;
    }
}

