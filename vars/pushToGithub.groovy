#!/usr/bin/env groovy
import com.example.Docker
def call(String repo) {
    return new Docker(this).pushToGithub(repo)
}