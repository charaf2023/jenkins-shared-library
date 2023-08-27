#!/usr/bin/env groovy

def call() {
    echo "building the application... in $BRANCH_NAME"
    sh 'mvn package'
}

