package io.vz.pipelinejob.Util

import static io.vz.pipelinejob.Util.LogLevel.ERROR
import static io.vz.pipelinejob.Util.LogLevel.INFO
import static io.vz.pipelinejob.Util.LogLevel.WARNING
import static io.vz.pipelinejob.Util.LogLevel.DEBUG

class Log implements Serializable {

    def steps

    Log(steps) {
        this.steps = steps
    }

    def info(message) {
        printDetails("INFO", message)
    }

    def warning(message) {
        printDetails("WARNING", message)
    }

    def debug(message) {
        printDetails("DEBUG", message)
    }

    def error(message) {
        printDetails("ERROR", message)
    }

    private def printDetails(level, message) {
        def now = new Date()
        //println(level + ": " + now + ": " + message)
        def output = "[${level.label}] [${now}]: " << String.valueOf(body)
        steps.echo output.toString()
    }


}