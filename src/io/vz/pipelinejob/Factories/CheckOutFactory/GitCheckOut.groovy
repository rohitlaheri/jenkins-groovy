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
            /* def changeLogSets = steps.currentBuild.changeSets
            for (int i = 0; i < changeLogSets.size(); i++) {
                def entries = changeLogSets[i].items
                for (int j = 0; j < entries.length; j++) {
                    def entry = entries[j]
                    steps.echo "${entry.commitId} by ${entry.author} on ${new Date(entry.timestamp)}: ${entry.msg}"
                    def files = new ArrayList(entry.affectedFiles)
                    for (int k = 0; k < files.size(); k++) {
                        def file = files[k]
                        def filePath = file.path
                        steps.echo "changes:   ${file.path}"
                        if(filePath.contains("pom.xml"))
                        {
                        steps.echo "pomchanged: true"   
                        }
                        else
                            steps.echo "pomchnaged: false" */
                    /*  def lastSuccessfulHash = null
                    def lastSuccessfulBuild = currentBuild.rawBuild.getPreviousSuccessfulBuild()
                    if ( lastSuccessfulBuild ) {
                        def scmAction = build?.actions.find { action -> action instanceof jenkins.scm.api.SCMRevisionAction }
                        lastSuccessfulHash = scmAction?.revision?.hash
                        def currentCommit = commitHashForBuild( currentBuild.rawBuild )
                        if (lastSuccessfulHash) {
                            commits = sh(
                            script: "git rev-list $currentCommit \"^$lastSuccessfulHash\"",
                            returnStdout: true
                            ).split('\n')
                            steps.echo "Commits are: $commits" 
                    
                        }
                        } */
                
              /*  def local_branch = steps.sh (
                script: "git rev-parse --abbrev-ref HEAD",
                label: "Getting current branch name",
                returnStdout: true
            ).trim()
            steps.echo "Local branch is ${local_branch}"

            def base_branch = 'master' 
            // This is very naive.
            // In reality, you need a better way to find out what your base branch is.
            // One way is to have a file with a name of a base branch.
            // Another one is to invoke API, e.g. GitHub API, to find out base branch.
            // Use whatever works for you.
            steps.echo "Base branch is ${base_branch}"

            //steps.sh script: "git fetch origin --no-tags ${base_branch}", label: "Getting base branch"

            def git_diff = steps.sh (
                script: "git diff --name-only origin/${base_branch}..${local_branch}",
                returnStdout: true
            ).trim()
            steps.echo "git_diff" git_diff */
        
        def changedFiles = steps.pullRequest.files.collect {
            it.getFilename()
        }
        steps.echo "git_diff" changedFiles
        
        
        
    }
}
       
