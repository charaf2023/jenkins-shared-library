
#!/usr/bin/env groovy
import com.example.Docker

def call() {
     sh 'in call'
    return new Docker(this).dockerLogin()
}