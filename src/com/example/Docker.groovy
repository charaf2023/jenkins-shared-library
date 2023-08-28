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
        script.sh "git config --global user.name \"jenkins\""
        script.sh "git config --global user.email \"chatou992@gmail.com\""
        script.sh "git status"
        script.sh "git branch"
        script.sh "git config --list"
        script.sh "echo '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' | git remote set-url origin https://${script.USER}@github.com/${script.USER}/${repo}.git"
        script.sh "git add ."
        script.sh "git commit -m 'ci:version bump'"
        script.sh "git branch -a"
//        script.sh "echo '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' | git push origin HEAD:jenkins-shared-lib"
        }
    }
}
