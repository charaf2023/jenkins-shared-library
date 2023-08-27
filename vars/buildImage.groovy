#!/usr/bin/env groovy

def call(String imageName) {
    echo "stage of building docker image in $BRANCH_NAME"
    withCredentials([usernamePassword(credentialsId:'docker-hub-credentials	',passwordVariable:'PASS',usernameVariable:'USER')]){
        sh "docker build -t $imageName ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push $imageName"
    }
}