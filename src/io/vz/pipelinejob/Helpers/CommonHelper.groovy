package io.vz.pipelinejob.Helpers


public class CommonHelper implements  Serializable{
    def jsonParser
    def steps
//    def jObject

    CommonHelper(steps){
        this.steps = steps
    }

    public def getModuleConfig(module){
        this.steps.writeFile file:'configuration.json', text:this.steps.libraryResource("configuration.json")
        def getname = 'cat configuration.json | jq  \'.module.AEMModule | .[] | select(.name == $module) | .name\''
        def JObjectname = steps.sh(script: getname , returnStdout:true)
        
        //this.steps.sh "ls"
        //def jsonValue = this.steps.readFile("configuration.json")
        //def jObject =  jsonParse(jsonValue)
        //return jObject.module.AEMModule;
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

