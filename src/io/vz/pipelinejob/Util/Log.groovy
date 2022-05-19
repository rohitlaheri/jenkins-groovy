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

    def error(message) {
        printDetails("ERROR", message)
    }

    private def printDetails(level, message) {
        def now = new Date()
        steps.echo "[${level}] [${now}]: " message
    }

}