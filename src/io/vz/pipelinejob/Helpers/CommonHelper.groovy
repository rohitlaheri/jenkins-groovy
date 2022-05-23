package io.vz.pipelinejob.Helpers

import groovy.json.JsonSlurper
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class CommonHelper implements  Serializable{
    def jsonParser
    def steps
    CommonHelper(steps){
        this.jsonParser= new JsonSlurper()
        this.steps = steps
    }

    public def getModuleConfig(){
        steps.echo "inside get module"
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
    public jsonParser() {
        // parsing file "JSONExample.json"
        Object ob = new JSONParser().parse(new FileReader("configuration.json"));
        // typecasting ob to JSONObject
        JSONObject js = (JSONObject) ob;
        steps.echo js

    }
}

