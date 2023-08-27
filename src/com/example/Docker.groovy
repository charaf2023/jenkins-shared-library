package com.example

class Docker implements Serializable {
    def script
    Docker(script) {
        this.script=script
    }

    def buildImage(String imageName) {
        script.echo "stage of building docker image in $script.BRANCH_NAME"
        script.sh "docker build -t $imageName ."
    }


    def dockerLogin() {
        script.sh 'in func'
        script.echo "stage of login in $script.BRANCH_NAME"
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-credentials',passwordVariable:'PASS',usernameVariable:'USER')]){
            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin "
        }}


    def dockerPush(String imageName) {
        script.echo "stage of pushing docker image in $script.BRANCH_NAME"
        script.sh "docker push $imageName"
    }
}
