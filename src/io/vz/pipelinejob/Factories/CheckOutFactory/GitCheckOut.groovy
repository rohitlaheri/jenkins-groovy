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
        if (args.containsKey('shallow') && args.shallow) {
            steps.checkout([$class: 'GitSCM',
                            branches: [[name: args.branch]],
                            userRemoteConfigs: [
                                    [credentialsId:args.gitCredentialId ,url: args.repoURL]
                            ],
                            extensions: [
                                    [$class: 'CloneOption', depth: 1, noTags: true, honorRefspec: true, shallow: true]
                            ]])
        } else {
            steps.checkout([$class: 'GitSCM', branches: [[name: args.branch]], userRemoteConfigs: [
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
        steps.echo param.branch
        steps.echo param.repoURL
        checkoutGit(param)
    }
}