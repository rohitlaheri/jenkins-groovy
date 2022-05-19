package io.vz.pipelinejob.Factories.CheckOutFactory.Configuration

//abstract factory class
public abstract class CheckOutAbstractFactory {
    abstract CheckOutCode getCheckOutFactory(String type) ;
}