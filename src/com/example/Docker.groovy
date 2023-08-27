package com.example

class Docker implements Serializable {
    def script
    Docker(script) {
        this.script=script
    }

    def buildImage(String imageName) {
        script.echo "stage of building docker image in $script.BRANCH_NAME"
        script.sh "docker build -t $script.imageName ."
    }


    def login(String credentialsId,String repo) {
        script.withCredentials([script.usernamePassword(credentialsId:credentialsId,passwordVariable:'PASS',usernameVariable:'USER')]){
            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin $repo"
        }}


    def pushImage(String imageName) {
        script.sh "docker push $script.imageName"
    }
}
