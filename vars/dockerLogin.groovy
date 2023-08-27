#!/usr/bin/env groovy
import com.example.Docker

def call(String credentialsId,String repo="") {
    return new Docker(this).dockerLogin(credentialsId,repo)
}