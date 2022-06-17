package io.vz.pipelinejob.Factories.CheckOutFactory

import io.vz.pipelinejob.Factories.CheckOutFactory.Configuration.CheckOutCode

public class GitCheckOut implements CheckOutCode, Serializable  {
    def steps
    /**
     * Constructor call
     * @param steps
     * @return null
     */
    def GitCheckOut(steps) { this.steps = steps }

    /**
     * Checks out a git repository
     * @param {Map}    args                - Map of arguments
     * @param {String} args.url            - URL of the git repository
     * @param {String} args.branch         - branch name to checkout
     * @param {String} args.credentials_id - Jenkins credentials ID to use for git interaction
     *
     * @return null
     */
    def checkoutGit(Map args) {
        //def credentialsId = this.steps.scm.userRemoteConfigs[0].credentialsId
        //this.steps.echo credentialsId
        if (args.containsKey('shallow') && args.shallow) {
            this.steps.checkout([$class: 'GitSCM',
                            branches: [[name: args.branch]],
                            extensions: [
                                    [$class: 'PruneStaleBranch'],
                                    [$class: 'CleanCheckout'],
                                    [$class: 'PreBuildMerge', options:[fastForwardMode:'NO_FF', mergeRemote: env.gitlabTargetNamespace, mergeTarget: env.gitlabTargetBranch]]
                            ],
                            userRemoteConfigs: [
                                    [credentialsId:args.gitCredentialId ,url: args.repoURL]
                            ],
                            extensions: [
                                    [$class: 'CloneOption', depth: 1, noTags: true, honorRefspec: true, shallow: true]
                            ]])
        } else {
            this.steps.checkout([$class: 'GitSCM', branches: [[name: args.branch]], userRemoteConfigs: [
                    [credentialsId:args.gitCredentialId ,url: args.repoURL]
            ]])
        }
    }
    
    /**
     * Implementing checkout interface
     * @param param
     * @return null
     */
    @Override
    public void checkOut(Map param) {
        this.steps.echo param.branch
        this.steps.echo param.repoURL
        checkoutGit(param)
        def changeLogSets = this.steps.currentBuild.changeSets
        for(int i = 0; i < changeLogSets.size(); i++)
        {
            def entries = changeLogSets[i].items
            for (int j = 0; j < entries.length; j++) {
                this.steps.echo "chnages: " + entries[j].affectedFiles
            }
           
        }
       
    }
}
