package io.vz.pipelinejob.Helpers

public class PipelineHelper implements  Serializable{
    def steps
    def commonHelper
    PipelineHelper(steps) {
        this.steps = steps
        this.commonHelper=new CommonHelper(this.steps)
    }
    def setAEMModule(String moduleName){
        steps.echo "calling get module"
        def modules=this.commonHelper.getModuleConfig()
        def matchedModule = modules.find{module -> module.name == moduleName}
        steps.echo "matched Module " +matchedModule
        // Need to handle null - Default case (Module not found)

        steps.echo "Repo" +  steps.env.REPO
        steps.echo "coreModule" +  steps.env.CORE_MODULE
        steps.echo "applicationModule" +  steps.env.APPLICATION_MODULE
        steps.echo "uiModule" +  steps.env.UI_MODULE
        steps.echo "MVN_TARGET_PATH" +  steps.env.MVN_TARGET_PATH
        steps.echo "ARTI_REPO" +  steps.env.ARTI_REPO

        steps.env.REPO = matchedModule.repo
        steps.env.CORE_MODULE = matchedModule.coreModule
        steps.env.APPLICATION_MODULE = matchedModule.applicationModule
        steps.env.UI_MODULE = matchedModule.uiModule
        steps.env.MVN_TARGET_PATH = matchedModule.mvnTargetPath
        steps.env.ARTI_REPO = matchedModule.artiRepo

        steps.echo "Post Repo" +  steps.env.REPO
        steps.echo "Post coreModule" +  steps.env.CORE_MODULE
        steps.echo "Post applicationModule" +  steps.env.APPLICATION_MODULE
        steps.echo "Post uiModule" +  steps.env.UI_MODULE
        steps.echo "Post MVN_TARGET_PATH" +  steps.env.MVN_TARGET_PATH
        steps.echo "Post ARTI_REPO" +  steps.env.ARTI_REPO
    }
    def getAEMDevServer(String deployEnv,String bgTraffic) {
        def servers=this.commonHelper.getAEMDevServerConfiguration()
        def server = modules.find{devEnvironmentName == deployEnv || stagingEnvironmentName == deployEnv}
        return server.serverName
    }
    def getAEMQAServer(String deployEnv,String bgTraffic) {
        def servers=this.commonHelper.getAEMQAServerConfiguration()
        def server = modules.find{stagingEnvironmentName == deployEnv && traffic == bgTraffic}
        return server.serverName
    }
    def useMail(Map args) {
        steps.echo "${args.SEND_TO}-${args.SUBJECT}-${args.MESSAGE}"
        steps.retry(3){
            steps.mail (to: args.SEND_TO,
                    subject: args.SUBJECT,
                    body: args.MESSAGE)
        }
    }

    def getReleaseVersion(String branchName) {
        def releaseVersion
        if (branchName == pipelineConstants.RELEASE) {
            releaseVersion = branchName.substring(branchName.lastIndexOf('/') + 1, branchName.length())
        } else {
            releaseVersion = branchName.substring(branchName.lastIndexOf('-') + 1, branchName.length())
        }
        return releaseVersion
    }
}