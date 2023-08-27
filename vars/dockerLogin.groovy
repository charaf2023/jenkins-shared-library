
#!/usr/bin/env groovy
import com.example.Docker

def call() {
    echo "in call"
    return new Docker(this).dockerLogin()
}