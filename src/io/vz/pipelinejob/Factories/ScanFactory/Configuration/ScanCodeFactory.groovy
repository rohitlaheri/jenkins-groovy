package io.vz.pipelinejob.Factories.ScanFactory.Configuration

import io.vz.pipelinejob.Factories.ScanFactory.BlackDuckScan
import io.vz.pipelinejob.Factories.ScanFactory.SonarQubeScan
import io.vz.pipelinejob.Model.*

public class ScanCodeFactory extends ScanCodeAbstractFactory {
    private def steps
    def pipelineConst

    ScanCodeFactory(steps) {
        this.steps = steps
        this.pipelineConst = new PipelineConstants()
    }
    @Override
    public ScanCode getScanner(String type) {
        if (type.equalsIgnoreCase(this.pipelineConst.SONARQUBE)) {
            return new SonarQubeScan(steps)
        } else if (type.equalsIgnoreCase(this.pipelineConst.BLACKDUCK)) {
            return new BlackDuckScan(steps)
        }
        return null
    }
}