package pipelinejob.Utils
import groovy.util.logging.Log

@Log
class JavaUtilLoggerClass
{
    public JavaUtilLoggerClass()
    {
        println "\njava.util.logging (${log.name}: ${log.class}):"
        log.info "${this.printAndReturnValue(1)}"
        log.finer "${this.printAndReturnValue(2)}"
    }

    public String printAndReturnValue(int newValue)
    {
        println "JDK: Print method invoked for ${newValue}"
        return "JDK: ${newValue}"
    }
}