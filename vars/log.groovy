import java.util.*;

def info(message) {
    def now = new Date()
    echo "INFO: ${now} -- ${message}"
}

def warning(message) {
    def now = new Date()
    echo "WARNING: ${now} -- ${message}"
}

def error(message) {
    def now = new Date()
    echo "WARNING: ${now} -- ${message}"
}