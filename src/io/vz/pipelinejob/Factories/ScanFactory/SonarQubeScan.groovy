package io.vz.pipelinejob.Factories.ScanFactory

import io.vz.pipelinejob.Factories.ScanFactory.Configuration.ScanCode

public class SonarQubeScan implements ScanCode {
    def steps
    def SonarQubeScan(steps) {this.steps = steps}

    @Override
    public void runScan() {
        print("Inside SonarQubeScan::runScan() method.")
        steps.echo "echo from class"

    }
}

