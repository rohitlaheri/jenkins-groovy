package io.vz.pipelinejob.Helper

import groovy.json.JsonSlurper

public class CommonHelper{
    def jsonParser

    CommonHelper(){
        this.jsonParser=new JsonSlurper()
    }

    public def getModuleConfiguartion(){
        def deserializedJson=jsonParser.parse(new File('/resources/configuration.json'))
        return deserializedJson.module.AEMModule;
    }
    public def getAEMDevServerConfiguration(){
        def deserializedJson=jsonParser.parse(new File('/resources/configuration.json'))
        return deserializedJson.AEMServer.DevServer;
    }
    public def getAEMQAServerConfiguration(){
        def deserializedJson=jsonParser.parse(new File('/resources/configuration.json'))
        return deserializedJson.AEMServer.QAServer;
    }
}

