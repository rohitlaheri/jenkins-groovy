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
}