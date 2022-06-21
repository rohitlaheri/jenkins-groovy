#!/usr/bin/groovy


def call(body) {

    def pipelineParams = [: ]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()
    def stage1="parallel-1"
    def stage2="parallel-2"
    def parent="parent"
    def parallelStages = [:]
    def scanToRun = []
    def map = [
        "stage1","stage2","stage3"]
    /**
     *
     */
    def repoUrl = pipelineParams.repoURL ?: env.gitlabSourceRepoHttpUrl
    def repoBranch = pipelineParams.branch ?: env.gitlabSourceBranch

    pipeline {
        agent any
        tools {
            maven 'maven'
        }
        stages {
            stage('scm checkout'){
                steps{
                    cleanWs()
                    script {
                        // temp logging //
                        echo "url $repoUrl"
                        echo "branch: $repoBranch"
                        def responseFromTasks = checkOutTasks.call(pipelineParams, parent)
                        echo "response checkout tasks: " + responseFromTasks
                        
                    }
                }
            }
            stage(parent) {
                steps {
                    script {
                        map.each {entry ->
                            if (entry == 'stage1' || entry == 'stage2') {
                                scanToRun.add(entry)   
                            }
                        }
                         
                        scanToRun.each { scan ->
                            parallelStages[scan] = {
                                stage(scan) {
                                    
                                        echo "stage name " + scan
                                        sleep(10 * Math.random())
                                    }
                            }
                            
                        }
                         parallel parallelStages
                    }
                   
                }
                /*parallel {
                    stage(stage1) {
                        when {
                            expression {stage1 == "parallel-2"}
                        }
                        steps {
                            echo "rohit laheri"
                            sleep(20 * Math.random())
                        }
                    }
                    stage(stage2) {
                        when {
                            expression {stage1 == "parallel-1"}
                        }
                        steps {
                            echo "parallel name rohit"   
                        }
                    } 
                } */
            }
            // stage('build') {
            //     steps {
            //         updateGitlabCommitStatus name: 'build', state: 'pending'
            //         script {
            //                 buildTasks.call(pipelineParams)
            //                 //updateGitlabCommitStatus name: 'build', state: 'success'


            //         }
            //     }
            // }
            // stage('scan') {
            //     steps {
            //         updateGitlabCommitStatus name: 'scan', state: 'pending'
            //         script {
            //              //try{
            //                 scanTasks.call(pipelineParams)
            //                 updateGitlabCommitStatus name: 'scan', state: 'success'
            //                //}
            //             /*catch (e) {*/
            //                 //echo $e
            //                 //updateGitlabCommitStatus name: 'scan', state: 'failed'
            //                 //currentBuild.result = 'FAILURE'
            //             //}
            //         }
            //     }
            // }
            // stage('upload to Artifactory') {
            //     steps {
            //         script {
            //             artiFactory.call(pipelineParams.SRI)
            //             //updateGitlabCommitStatus name: 'pipeline Succedded', state: 'success'
            //         }
            //     }
            // }
            
            // stage("Quality gate") {
            //     steps {
            //         updateGitlabCommitStatus name: 'Quality gate', state: 'pending'
            //         script {
            //             try{
            //                 //waitForQualityGate abortPipeline: true
            //                 timeout(time: 2, unit: 'MINUTES') {
            //                     def qualityGate = waitForQualityGate()
            //                     if (qualityGate.status == 'ERROR') {
            //                         currentBuild.result = 'FAILURE'
            //                         updateGitlabCommitStatus name: 'Quality gate', state: 'failed'
            //                     }
            //                     else {
            //                         updateGitlabCommitStatus name: 'Quality gate', state: 'success'
            //                         updateGitlabCommitStatus name: 'build', state: 'success'
            //                         currentBuild.result = 'SUCCESS'
            //                     }
            //                 }

            //             }catch (Exception e) {
            //                 updateGitlabCommitStatus name: 'Quality gate', state: 'failed'
            //                 updateGitlabCommitStatus name: 'build', state: 'success'
            //             }
            //         }

            //     }
            // }
            
            // stage('release') {
            //     steps {
            //         script {
            //             deployTasks.call()
            //         }
            //     }
            // }
        }
    }
}
