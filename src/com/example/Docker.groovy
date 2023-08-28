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
        script.echo "stage of login in $script.BRANCH_NAME"
        script.withCredentials([script.usernamePassword(credentialsId: 'docker-hub-credentials',passwordVariable:'PASS',usernameVariable:'USER')]){
            script.sh "echo $script.PASS | docker login -u $script.USER --password-stdin "
        }}


    def dockerPush(String imageName) {
        script.echo "stage of pushing docker image in $script.BRANCH_NAME"
        script.sh "docker push $imageName"
    }

    def pushToGithub(String repo) {
        script.echo "stage of pushing a commit to github in $script.BRANCH_NAME"
        script.withCredentials([script.usernamePassword(credentialsId: 'github-credentials',passwordVariable:'PASS',usernameVariable:'USER')]){
        script.sh "git config --global user.name \"charaf2023\""
        script.sh "git config --global user.email \"charafeddine.toumi@inttic.dz\""
//        script.sh "git remote set-url origin https://${script.USER}:/${script.USER}/${repo}.git"

        script.sh "curl -u \"charaf2023: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\" https://github.com/charaf2023/devops.git\n"
        script.sh "git add ."
        script.sh "git commit -m 'ci:version bump'"
        script.sh "git push -u origin jenkins-shared-lib"
        }
    }
}
