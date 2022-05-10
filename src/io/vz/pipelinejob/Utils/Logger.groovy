package io.vz.pipelinejob.Utils

class Logger implements ILogger, Serializable {

    def steps
    private final boolean debugOn
    private final Map clockStore = [ : ]

    def Logger(steps, debug) {
        this.steps = steps
        this.debugOn = debug
    }

    String debug(String message) {
        if (debugOn) {
            message = "DEBUG: ${message}"
            info (message)
        } else {
            return ''
        }
    }

    String info(String message) {
        steps.echo message
        message
    }

    String warn(String message) {
        message = "WARN: ${message}"
        info (message)
    }

    String debugClocked(String component, String message = null) {
        debug(timedCall(component, message))
    }

    String infoClocked(String component, String message = null) {
        info(timedCall(component, message))
    }

    String warnClocked(String component, String message = null) {
        warn(timedCall(component, message))
    }

    boolean getDebugMode () {
        debugOn
    }

    String getOcDebugFlag () {
        return debugOn ? '--loglevel=5' : ''
    }

    String getShellScriptDebugFlag () {
        return debugOn ? '' : 'set +x'
    }

    String startClocked(String component) {
        timedCall (component)
    }

    @SuppressWarnings(['GStringAsMapKey', 'UnnecessaryElseStatement'])
    private def timedCall (String component, String message = null) {
        if (!component) {
            throw new IllegalArgumentException ("Component can't be null!")
        }
        def startTime = clockStore.get("${component}")
        if (startTime) {
            def timeDuration = System.currentTimeMillis() - startTime
            return "[${component}] ${message ?: ''} " +
                "(took ${timeDuration} ms)"
        } else {
            clockStore << ["${component}": System.currentTimeMillis()]
            return "[${component}] ${message ?: ''}"
        }
    }

}
