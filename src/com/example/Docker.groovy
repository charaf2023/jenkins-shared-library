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
        script.sh "git remote set-url origin https://${script.USER}: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@github.com/${script.USER}/${repo}.git"
        script.sh "git add ."
        script.sh 'git commit -m "ci:version bump"'
        script.sh "git push origin HEAD:main"
        }
    }
}
