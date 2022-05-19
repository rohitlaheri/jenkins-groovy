import java.util.*;
import io.vz.pipelinejob.Util.Log
import io.vz.pipelinejob.Util.LogLevel

import static io.vz.pipelinejob.Util.LogLevel.ERROR
import static io.vz.pipelinejob.Util.LogLevel.WARNING
import static io.vz.pipelinejob.Util.LogLevel.INFO
import static io.vz.pipelinejob.Util.LogLevel.DEBUG

/*
def info(message) {
    Log log = new Log(steps)
    log.info(message)
}

def warning(message) {
    Log log = new Log(steps)
    log.warning(message)
}

def error(message) {
    Log log = new Log(steps)
    log.error(message)
}*/

def error(msg, dump = null) {
    message ERROR, msg, dump
}

def warn(msg, dump = null) {
    message WARNING, msg, dump
}

def info(msg, dump = null) {
    message INFO, msg, dump
}

def debug(msg, dump = null) {
    message DEBUG, msg, dump
}

def message(LogLevel level, Object body, Object dump) {
    /*if (BuildData.instance.logLevel.encompasses(level)) {*/
        def message = "[${level.label}] " << String.valueOf(body)
        if (dump) {
            message << "\n"
            if (dump instanceof Throwable) {
                StringWriter w = new StringWriter()
                //StringUtils.printStackTrace(dump, new PrintWriter(w))
                message.append(w.buffer)
            } /*else {
                message << new PrettyPrinter(dump).incrementIndent().toPrettyPrint()
            }*/
        }
        echo message.toString()
    /*}*/
}