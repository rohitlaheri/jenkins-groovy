package io.vz.pipelinejob.Factories.CheckOutFactory

import io.vz.pipelinejob.Factories.CheckOutFactory.Configuration.CheckOutCode

public class GitLabCheckOut implements CheckOutCode {
    def steps

    def GitLabCheckOut(steps) { this.steps = steps }


    def checkoutGitRepository(repoURL, repoBranch, credentialsId = 'gitlab', poll = true, timeout = 10, depth = 0, reference = ''){
        //def branch_name = reference ? 'FETCH_HEAD' : "*/${branch}"
            steps.checkout(
                    changelog:true,
                    poll: poll,
                    scm: [
                            $class: 'GitSCM',
                            branches: [[name: repoBranch]],
                            doGenerateSubmoduleConfigurations: false,
                            extensions: [
                                    [$class: 'CheckoutOption', timeout: timeout],
                                    [$class: 'CloneOption', depth: depth, noTags: false, shallow: depth > 0, timeout: timeout]],
                            submoduleCfg: [],
                            userRemoteConfigs: [[url: repoURL, credentialsId: credentialsId, refspec: reference]]]
            )

    }
    @Override
    public void checkOut(String repoURL, String repoBranch) {
        print("Inside Gitlab::Checkout() method.")
        //steps.echo "git checkOut print ${param.repoUrl}"
        //steps.echo "git checkOut print ${param.repoBranch}"
        //checkOutTasks([$class: 'GitSCM', branches: [[name: '*/dev']], extensions: [], userRemoteConfigs: [[url: repoUrl]]])
        //steps.checkout([$class: 'GitSCM', branches: [[name: $param.repoBranch]], extensions: [], userRemoteConfigs: [[credentialsId: 'gitlab', url: param.repoUrl]]])
        steps.echo repoBranch
        steps.echo repoURL
        checkoutGitRepository(repoURL, repoBranch, 'gitlab', true, 10, 0, '')
        //steps.checkout([$class: 'GitSCM', branches: [[name: repoBranch]], extensions: [], userRemoteConfigs: [[credentialsId: 'gitlab', url: repoURL]]])

    }
}