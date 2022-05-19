package io.vz.pipelinejob.Factories.ScanFactory

import io.vz.pipelinejob.Factories.ScanFactory.Configuration.ScanCode

public class BlackDuckScan implements ScanCode {
    def steps
    def BlackDuckScan(steps) {this.steps = steps}

    //sh '''
		//	echo "Inside the Blackduck stage"
         //   [ -d $WORKSPACE/bd_temp ] || mkdir $WORKSPACE/bd_temp
          //  cd $WORKSPACE/bd_temp
          //  curl -O https://oneartifactoryprod.verizon.com/artifactory/GXZV_DISTRO/dev/bd_build.cmd
           // . $WORKSPACE/bd_temp/bd_build.cmd
    //'''

    @Override
    public void runScan() {
        print("Inside BlackDuckScan::runScan() method.")
        steps.echo "echo from class"

    }
}


