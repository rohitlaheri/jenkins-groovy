package io.vz.pipelinejob.Helpers

import groovy.json.JsonSlurperClassic
/*import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;*/

public class CommonHelper implements  Serializable{
    def jsonParser
    def steps
//    def jObject

    CommonHelper(steps){
       // this.jsonParser= new JsonSlurperClassic()
        this.steps = steps


    }

    public def getModuleConfig(){
        this.steps.writeFile file:'configuration.json', text:libraryResource("configuration.json")
        this.steps.sh "ls"
        def jObject =  jsonParse(this.steps.readFile("configuration.json"))
        steps.echo "inside get module"
        steps.echo jObject
        steps.sh "cat configuration.json"
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
    @NonCPS
    def jsonParse(def json) {
        new groovy.json.JsonSlurperClassic().parseText(json)
    }
}

