package io.vz.pipelinejob.Factories.CheckOutFactory.Configuration

import io.vz.pipelinejob.Factories.CheckOutFactory.*
import io.vz.pipelinejob.Model.*
public class CheckOutFactory extends CheckOutAbstractFactory {
    private def steps
    def pipelineConst
    CheckOutFactory(steps) {
        this.steps = steps
        //this.pipelineConst = new pipelineConstants()
    }
    @Override
    public CheckOutCode getCheckOutFactory(String type) {
        //steps.echo this.pipelineConst.GIT
        //def repoType = this.pipelineConst.GIT
        if (type.equalsIgnoreCase("GIT")) {
            return new GitCheckOut(steps)
        }
        return null
    }
}