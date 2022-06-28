def generatePipeline(def repoUrl, def repoBranch, def pipelineParams) {
    script {       
        stage('scm checkout') {
            cleanWs()
            script {
                // temp logging //
                echo "url $repoUrl"
                echo "branch: $repoBranch"
                 def parent="parent"
                def responseFromTasks = checkOutTasks.call(pipelineParams, parent)
                //echo "response checkout tasks: " + responseFromTasks
                dynamicStages = responseFromTasks
                echo "dynamicStages = " + dynamicStages
            }
        }
        stage('display') {
            script {
                echo "repo-url " + repoUrl
                echo "repo-branch " + repoBranch
                echo "pipeline-param "+ pipelineParams
                displaytaske1.call()                                  
            }
        }
    }
}  

def call(def repoUrl, def repoBranch, def pipelineParams ) {
    
    generatePipeline(repoUrl, repoBranch, pipelineParams)
}