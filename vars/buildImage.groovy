#!/usr/bin/env groovy

def call() {
    echo "stage of building docker image"
    withCredentials([usernamePassword(credentialsId:'docker-hub-credentials	',passwordVariable:'PASS',usernameVariable:'USER')]){
        sh "docker build -t charaf2023/java-maven-app:1.1 ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push charaf2023/java-maven-app:1.1"
    }
}