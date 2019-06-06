
pipeline {
    agent {
        label 'docker'
    }
    tools {
        maven 'maven' 
        jdk 'jdk-8'
    }
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
    }
}
