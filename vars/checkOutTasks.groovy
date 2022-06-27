#!/usr/bin/groovy

import io.vz.pipelinejob.Factories.CheckOutFactory.Configuration.*
def stages = []

def call(Map param, String parent)
{
    def repoType = param.scmType
    CheckOutAbstractFactory checkOutAbstractFactory = CheckOutFactoryProducer.getFactory(this)
    echo "Producer called"
    CheckOutCode checkOutCode = checkOutAbstractFactory.getCheckOutFactory(repoType)
    echo "Factory Generated"
    checkOutCode.checkOut(param)
    stages = ["displaytaske1","displaytaske2"]
    
    echo "STAGES = " + env.STAGES
    echo "Completed"
    echo "parent variable contains: " + parent
    //def ret = stages
    return generatePipeline('displaytaske1')
}


def generatePipeline(string) {
    return {
        when {
            allOf {
                expression {stg.contains('displaytaske1')}
            }
        }
        steps {
            script {
                displaytaske1.call()                                  
            }
        }
        
    }
}

