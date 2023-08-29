#!/usr/bin/env groovy
import com.example.Docker
def call(String token) {
    return new Docker(this).pushToGithub(token)
}