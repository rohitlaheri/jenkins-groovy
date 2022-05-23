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
        this.steps = steps
    }

    public def getModuleConfig(){
        this.steps.writeFile file:'configuration.json', text:this.steps.libraryResource("configuration.json")
        this.steps.sh "ls"
        def jsonValue = this.steps.readFile("configuration.json")
        def jObject =  jsonParse(jsonValue)
        def matchedModule = jObject.module.AEMModule.find{a -> a.name == "onevz-soe-aem-digital-next"}
        steps.echo "matched Module " +matchedModule
        return jObject.module.AEMModule;
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

