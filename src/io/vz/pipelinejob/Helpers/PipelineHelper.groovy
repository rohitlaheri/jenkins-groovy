package io.vz.pipelinejob.Helper
import io.vz.*
public class PipelineHelper{
    def steps
    def commonHelper
    PipelineHelper(steps) {
        this.steps = steps
        this.commonHelper=new CommonHelper()
    }
    def setAEMModule(String moduleName){
        def modules=this.commonHelper.getModuleConfiguartion()
        def module = modules.find{name == moduleName}
        // Need to handle null - Default case (Module not found)
        env.REPO = module.repo
        env.CORE_MODULE = module.coreModule
        env.APPLICATION_MODULE = module.applicationModule
        env.UI_MODULE = module.uiModule
        env.MVN_TARGET_PATH = module.mvnTargetPath
        env.ARTI_REPO = module.artiRepo
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