package io.vz.pipelinejob.Factories.ScanFactory.Configuration

import io.vz.pipelinejob.Factories.ScanFactory.BlackDuckScan
import io.vz.pipelinejob.Factories.ScanFactory.SonarQubeScan

public class ScanCodeFactory extends ScanCodeAbstractFactory {
    private def steps

    ScanCodeFactory(steps) {
        this.steps = steps
    }
    @Override
    public ScanCode getScanner(String type) {
        if (type.equalsIgnoreCase("sonarqube")) {
            return new SonarQubeScan(steps)
        } else if (type.equalsIgnoreCase("blackduck")) {
            return new BlackDuckScan(steps)
        }
        return null
    }
}