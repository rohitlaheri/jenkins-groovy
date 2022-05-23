package io.vz.pipelinejob.Factories.BuildFactory.Configuration

//interface BuildCode
public interface BuildCode extends Serializable {
    void runBuild(def param);
}