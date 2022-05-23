package io.vz.pipelinejob.Factories.CheckOutFactory.Configuration

import io.vz.pipelinejob.Factories.CheckOutFactory.GitLabCheckOut

public class CheckOutFactory extends CheckOutAbstractFactory {
    private def steps

    CheckOutFactory(steps) {
        this.steps = steps
    }
    @Override
    public CheckOutCode getCheckOutFactory(String type) {
        if (type.equalsIgnoreCase("GitLab")) {
            return new GitCheckOut(steps)
        }
        return null
    }
}