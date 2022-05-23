package io.vz.pipelinejob.Factories.BuildFactory

//import io.vz.pipelinejob.Model.pipelineParameters
import io.vz.pipelinejob.Helpers.*
import io.vz.pipelinejob.Factories.BuildFactory.Configuration.BuildCode

//concrete class implementing interface BuildCode to Build AEM code
public class AEMBuilder implements BuildCode, Serializable {
    def parameters
    def pipelineHelper
    def steps
    AEMBuilder(steps) {
        this.steps = steps
        //this.parameters=pipelineParameters PipelineHelper
    }

    //region Methods for build steps --start
    def initializeEnvSetup(String moduleName){
        steps.echo "initialize environment......"
        pipelineHelper= new PipelineHelper(this.steps)

        try{
            this.steps.echo moduleName
            pipelineHelper.setAEMModule(moduleName);
            //print "aemModule details ${aemModule}";
        }catch (Exception e) {
            steps.echo "Error occurred :" + e
        }

    }
    def mavenBuild(boolean SRI, String module){
        if(SRI == true)
        {
            def SRI_CMD=" -DSRIFlag=enabled"
            steps.echo SRI_CMD
        }
        else {
            def SRI_CMD=" -DSRIFlag=disabled"
            steps.echo SRI_CMD
        }

        if(module == "ONEVZ-SOE-AEM-DIGITAL-NEXT") {
            steps.sh "mvn clean install"
        }
        //steps.sh "mvn clean install -X -pl \\${APPLICATION_MODULE},\\${UI_MODULE} -Dbaseline.skip=true -Dvault.useProxy=false  -Dsettings.security=settings-security.xml  -Dmaven.repo.local=$WORKSPACE/ui-repo -Drevision=22.04.100.${BUILD_NUMBER}  -DBuildNumber=${BUILD_NUMBER}"

        else
            steps.sh "mvn clean install -X -pl \\${CORE_MODULE},\\${APPLICATION_MODULE},\\${UI_MODULE} -Dbaseline.skip=true -Dvault.useProxy=false  -Dsettings.security=settings-security.xml  -Dmaven.repo.local=$WORKSPACE/ui-repo -Drevision=22.04.100.${BUILD_NUMBER}  $SRI_CMD -DBuildNumber=${BUILD_NUMBER}"
        //steps.sh 'rsync $WORKSPACE/ui-repo/* ~/.m2/repository/'
    }

    //endregion

    @Override
    public void runBuild(def param) {
        steps.echo "echo from class"
        boolean SRI_Flag = param.SRI
        String aemModuleName = param.aemModuleName
        steps.sh "ls"
        //def jObject = readJSON file: 'configuration.json'
        //steps.echo jObject
        initializeEnvSetup(aemModuleName)
        steps.sh "printenv"
        //mavenBuild(SRI_Flag, moduleName)
    }
}