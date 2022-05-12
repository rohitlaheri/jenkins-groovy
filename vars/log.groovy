import java.util.*;
def now = new Date()

def info(message) {
    echo "INFO: ${now} -- ${message}"
}

def warning(message) {
    echo "WARNING: ${now} -- ${message}"
}

def error(message) {
    echo "WARNING: ${now} -- ${message}"
}