#!/usr/bin/groovy

def call(boolean SRI) {
    if(SRI) {
        echo "push to artifactory with SRI flag"
    }
    else
        echo "push to artifactory with icon flag"
}