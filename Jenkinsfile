pipeline {
    agent any
    stages {
    	stage('Checkout'){
    		steps {
    			git credentialsId: '3b07e190-29a4-4a0f-bee5-bcaa05992cf4', 
    			url: 'https://github.com/sharma-sahil/nagp-devops-assignment.git'
    		}
    	}
    
        stage('Build') {
            steps {
                sh "/usr/local/src/apache-maven/bin/mvn clean install"
            }
        }
    }
}