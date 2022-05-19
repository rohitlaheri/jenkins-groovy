package io.vz.pipelinejob.Factories.CheckOutFactory

import io.vz.pipelinejob.Factories.CheckOutFactory.Configuration.CheckOutCode

public class GitLabCheckOut implements CheckOutCode {
    def steps

    def GitLabCheckOut(steps) { this.steps = steps }

    @Override
    public void checkOut(String repoURL, String repoBranch) {
        print("Inside Gitlab::Checkout() method.")
        //steps.echo "git checkOut print ${param.repoUrl}"
        //steps.echo "git checkOut print ${param.repoBranch}"
        //checkOutTasks([$class: 'GitSCM', branches: [[name: '*/dev']], extensions: [], userRemoteConfigs: [[url: repoUrl]]])
        //steps.checkout([$class: 'GitSCM', branches: [[name: $param.repoBranch]], extensions: [], userRemoteConfigs: [[credentialsId: 'gitlab', url: param.repoUrl]]])
        steps.checkout([$class: 'GitSCM', branches: [[name: repoBranch]], extensions: [], userRemoteConfigs: [[credentialsId: 'gitlab', url: repoUrl]]])

    }
}