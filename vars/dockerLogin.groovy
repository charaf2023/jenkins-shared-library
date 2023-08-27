
#!/usr/bin/env groovy
import com.example.Docker

def call(String credentialsId) {
    return new Docker(this).dockerLogin(credentialsId)
}