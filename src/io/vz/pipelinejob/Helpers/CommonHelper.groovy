package io.vz.pipelinejob.Helpers


public class CommonHelper implements  Serializable{
    def jsonParser
    def steps
//    def jObject

    CommonHelper(steps){
        this.steps = steps
    }

    public def getModuleConfig(){
        this.steps.writeFile file:'configuration.json', text:this.steps.libraryResource("configuration.json")
        //this.steps.sh "ls"
       // def jsonValue = this.steps.readFile("configuration.json")
        def get = 'cat configuration.json | jq -r \'.module.AEMModule\' | select(.name>"onevz-soe-aem-assisted-account-landing")'
        def JObject = steps.sh (script: get , returnStdout:true) 
        steps.echo "Module "+   JObject       
        // def selectedQuery= 'jq '.[] | select(.name==\'onevz-soe-aem-assisted-account-landing\') JObject'
        // def selected = steps.sh (script: selectedQuery , returnStdout:true) 
        // steps.echo "Selection " + selected
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

    /**
     * Craft the docker runner string
     *
     * @param {Map}     args              - Map of arguments
     * @param {String}  args.DOCKER_IMAGE - Full name of the docker image, e.g. "oneartifactorycloud.vpc.verizon.com:8091/vzw/gj3v/refapp:1.0.0"
     * @param {Boolean} [args.CACHE]      - Whether or not to cache the downloaded image.  (Default: False)
     *
     * @return {String} docker runner string like "docker run -i -rm -v ..."
     */
    static String getRunnerString(Map args) {
        Boolean cache_image = args.containsKey("CACHE") ? args.CACHE : false
        String cache_args
        if (cache_image) {
            cache_args = ""
        } else {
            cache_args = "--rm"
        }
        //return "docker run -i --privileged ${cache_args} -v \${WORKSPACE}/dist:/dist -v /usr/local/lib:/usr/local/lib -v /usr/bin/kubectl:/usr/local/bin/kubectl -v /usr/local/bin/kubectl-kluster:/usr/local/bin/kubectl-kluster -v /usr/local/bin/helm:/usr/local/bin/helm -v \${WORKSPACE}../../repository:/repository -v \${WORKSPACE}/.kube:/build/.kube -v \${WORKSPACE}:/build -w /build -v /var/run/docker.sock:/var/run/docker.sock:z -v /etc/kubernetes/plugins:/etc/kubernetes/plugins/ ${args.DOCKER_IMAGE}"
        return "docker run -i --privileged ${cache_args} -v \${WORKSPACE}/dist:/dist -v /usr/local/lib:/usr/local/lib -v /usr/bin/kubectl:/usr/local/bin/kubectl -v /usr/local/bin/kubectl-kluster:/usr/local/bin/kubectl-kluster -v /usr/local/bin/helm:/usr/local/bin/helm -v \${WORKSPACE}../../repository:/repository -v \${WORKSPACE}/.kube:/build/.kube -v \${WORKSPACE}:/build -w /build -v /var/run/docker.sock:/var/run/docker.sock:z -v /etc/kubernetes/plugins:/etc/kubernetes/plugins/ ${args.DOCKER_IMAGE}"

    }

    /**
     * Perform Docker login
     *
     * @param {Map}     args                  - Map of arguments
     * @param {String}  args.DOCKER_HOST      - The Artifactory host containing a Docker repository, e.g. - oneartifactorycloud.vpc.verizon.com:8091
     * @param {String}  args.DOCKER_CREDS_ID  - Credential ID to be used for interacting with the DOCKER_HOST
     *
     * @return null
     */
    def dockerLogin(Map args) {
        steps.withCredentials([
                [$class: 'UsernamePasswordMultiBinding', credentialsId: args.DOCKER_CREDS_ID,
                 usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']
        ]) {
            steps.retry(5) {
                steps.sh "docker login -u \${USERNAME} -p \${PASSWORD} ${args.DOCKER_HOST}"
            }
        }
    }

    /**
     * Sets up the docker runner by logging in, pulling the image, and returning the long docker runner string
     *
     * @param {Map}     args                 - Map of arguments
     * @param {String}  args.DOCKER_HOST     - The Artifactory host containing a Docker repository, e.g. - oneartifactorycloud.vpc.verizon.com:8091
     * @param {String}  args.DOCKER_CREDS_ID - Credential ID to be used for interacting with the DOCKER_HOST
     * @param {String}  args.DOCKER_ACCOUNT  - Account folder location inside Artifactory's Docker repo, e.g. - "vzw/gj3v"
     * @param {String}  args.BUILD_IMAGE     - Name and version of the build image, e.g. - `K8S_JAVA_BUILD:latest`
     *
     * @return Docker runner string
     */
    String setUpRunnerWithLogin(Map args) {
        steps.echo "***** Setting RUNNER using Jenkins build image *****"
        steps.retry(3) {
            dockerLogin(args)
            steps.sh "docker pull ${args.DOCKER_HOST}/${args.DOCKER_ACCOUNT}/${args.BUILD_IMAGE}"
        }
        // docker image is downloaded in the above command and then we can just pass the docker image construct string to the
        //getRunnerString and then perform docker log out
        return getRunnerString(DOCKER_IMAGE: "${args.DOCKER_HOST}/${args.DOCKER_ACCOUNT}/${args.BUILD_IMAGE}")
        dockerLogout(args)
    }
}

