package io.vz.pipelinejob.Helpers

import groovy.json.JsonSlurper
/*import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;*/

public class CommonHelper implements  Serializable{
    def jsonParser
    def steps
//    def jObject

    CommonHelper(steps){
        this.jsonParser= new JsonSlurper()
        this.steps = steps


    }

    public def getModuleConfig(){
        this.steps.writeFile file:'configuration.json', text:this.steps.libraryResource("configuration.json")
        this.steps.sh "ls"
        def jObject =  this.jsonParser(this.steps.readFile("configuration.json"))
        steps.echo "inside get module"
        steps.echo jObject
        steps.sh "cat configuration.json"
        //def deserializedJson= this.jsonParser.parse(new File('configuration.json'))
        //return deserializedJson.module.AEMModule;
    }
    public def getAEMDevServerConfiguration(){
        def deserializedJson= this.jsonParser.parse(new File('configuration.json'))
        return deserializedJson.AEMServer.DevServer;
    }
    public def getAEMQAServerConfiguration(){
        def deserializedJson= this.jsonParser.parse(new File('configuration.json'))
        return deserializedJson.AEMServer.QAServer;
    }
    /*@NonCPS
    def jsonParse(def json) {
        new groovy.json.JsonSlurperClassic().parseText(json)
    }*/
}

