#!/usr/bin/env groovy
package pipelinejob.Mediators
import pipelinejob.ADT.AemPipelineParameters

class AEMPipelineMediator
{
    AemPipelineParameters parameters;
    AEMPipelineMediator(String releaseBranch, String previousTarget, String imageTag) {
        //pipeline constructor
        AemPipelineParameters parameters = new AemPipelineParameters(releaseBranch, previousTarget, imageTag)
        this.parameters = parameters
    }

    def runPipeline(AemPipelineParameters parameters) {
        pipeline {
            agent any
            Stages {
                Stage ('Build for Non-Prod')
                        {
                            Steps {
                                //What ever steps that need to be called to build for non-prod

                            }
                        }

                Stage ('Deploy to DIT')
                        {
                            Steps {
                                //What ever steps need to be called
                            }
                        }

                Stage ('Deploy to SIT1')
                        {
                            input {
                                message 'Should we continue to SIT1'
                            }

                            Steps {
                                // Whatever steps to deploy to SIT1
                            }
                        }

                Stage ('Deploy to SIT2')
                        {
                            input {
                                message 'Should we continue to SIT2'
                            }

                            Steps {
                                // Whatever steps to deploy to SIT2
                            }
                        }

                Stage ('Build for Prod')
                        {
                            Steps {
                                //What ever steps that need to be called to build for prod
                            }
                        }

                Stage ('Deploy to PRD CANARY')
                        {
                            input {
                                message 'Should we continue to CANARY'
                            }

                            Steps {
                                // Whatever steps to deploy to CANARY
                            }
                        }

                Stage ('Deploy to PRD EAST1')
                        {
                            input {
                                message 'Should we continue to EAST1'
                            }

                            Steps {
                                // Whatever steps to deploy to EAST
                            }
                        }

                Stage ('Deploy to PRD WEST')
                        {
                            input {
                                message 'Should we continue to WEST'
                            }

                            Steps {
                                // Whatever steps to deploy to WEST
                            }
                        }
            }
        }
    }

    public static  void main(String[] args)
    {

        AEMPipelineMediator aemMediator = new AEMPipelineMediator('release2.2.0','','latest');
        def _parameters = aemMediator.parameters
        aemMediator.runPipeline(_parameters);

    }
}