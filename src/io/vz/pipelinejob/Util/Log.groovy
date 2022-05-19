package io.vz.pipelinejob.Util

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
        //println(level + ": " + now + ": " + message)
        def output = "[${level.label}] [${now}]: " << String.valueOf(body)
        steps.echo output.toString()
    }


}