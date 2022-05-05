package pipelinejob.Factories.ScanFactory

import pipelinejob.Factories.ScanFactory.Configuration.ScanCode

public class BlackDuckScan implements ScanCode {
    def steps
    def BlackDuckScan(steps) {this.steps = steps}

    @Override
    public void runScan() {
        print("Inside BlackDuckScan::runScan() method.")
        steps.echo "echo from class"

    }
}


