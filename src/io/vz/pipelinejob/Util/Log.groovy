package io.vz.pipelinejob.Util

class Log implements Serializable {

    def steps

    Log(steps) {
        this.steps = steps
    }

    def info(message) {
        printDetails("INFO", msg)
    }

    def warning(message) {
        printDetails("WARNING", msg)
    }

    def error(message) {
        printDetails("ERROR", msg)
    }

    private def printDetails(level, msg) {
        def now = new Date()
        steps.echo level + ": " + now + ": " + msg
    }
}