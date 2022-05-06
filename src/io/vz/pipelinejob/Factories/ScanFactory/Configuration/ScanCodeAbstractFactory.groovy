package io.vz.pipelinejob.Factories.ScanFactory.Configuration

//abstract factory class
public abstract class ScanCodeAbstractFactory {
    abstract ScanCode getScanner(String type) ;
}
