package io.vz.pipelinejob.Factories.BuildFactory

import io.vz.pipelinejob.Helpers.*
import io.vz.pipelinejob.Factories.BuildFactory.Configuration.BuildCode
import io.vz.pipelinejob.Model.*

//concrete class implementing interface BuildCode to Build AEM code
public class AEMBuilder implements BuildCode, Serializable {
    def pipelineHelper
    def pipelineConst
    def steps
    AEMBuilder(steps) {
        this.steps = steps
        this.pipelineConst = new PipelineConstants()
    }

    //region Methods for build steps --start
    def initializeEnvSetup(String moduleName){
        steps.echo "initialize environment......"
        pipelineHelper= new PipelineHelper(this.steps)

        try{
            this.steps.echo moduleName
            pipelineHelper.setAEMModule(moduleName);
        }catch (Exception e) {
            steps.echo "Error occurred :" + e
        }

    }
    def mavenBuild(boolean SRI, String module){
       if(module.equalsIgnoreCase(this.pipelineConst.SOE_AEM_DIGITAL_NEXT)) {
            steps.sh "mvn clean install"
           steps.sh "npm install"
        }
        //steps.sh "mvn clean install -X -pl \\${APPLICATION_MODULE},\\${UI_MODULE} -Dbaseline.skip=true -Dvault.useProxy=false  -Dsettings.security=settings-security.xml  -Dmaven.repo.local=$WORKSPACE/ui-repo -Drevision=22.04.100.${BUILD_NUMBER}  -DBuildNumber=${BUILD_NUMBER}"

        else
           steps.sh ""
            //steps.sh "mvn clean install -X -pl \\${CORE_MODULE},\\${APPLICATION_MODULE},\\${UI_MODULE} -Dbaseline.skip=true -Dvault.useProxy=false  -Dsettings.security=settings-security.xml  -Dmaven.repo.local=$WORKSPACE/ui-repo -Drevision=22.04.100.${BUILD_NUMBER}  $SRI_CMD -DBuildNumber=${BUILD_NUMBER}"

    }
    //endregion

    @Override
    public void runBuild(def param) {
        boolean SRI_Flag = param.SRI
        String aemModuleName = param.aemModuleName
        initializeEnvSetup(aemModuleName)
        steps.sh "printenv"
        mavenBuild(SRI_Flag, aemModuleName)
    }
}
