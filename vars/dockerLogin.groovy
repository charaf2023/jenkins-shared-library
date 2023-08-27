
#!/usr/bin/env groovy
import com.example.Docker

def call(String credentialsId,String repo) {
    if (!repo) repo=""
    return new Docker(this).dockerLogin(credentialsId,repo)
}