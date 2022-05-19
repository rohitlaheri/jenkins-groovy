import java.util.*;
import io.vz.pipelinejob.Util.Log


def info(message) {
    Log log = new Log(steps)
    log.info(message)
}

def warning(message) {
    Log log = new Log(steps)
    log.warning(message)
}

def debug(message) {
    Log log = new Log(steps)
    log.debug(message)
}

def error(message) {
    Log log = new Log(steps)
    log.error(message)
}

