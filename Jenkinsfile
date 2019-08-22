pipeline {
    agent any
    stages {
    	stage('Checkout'){
    		steps {
    			git credentialsId: '34ed3503-6786-4e1f-97e6-d275a8699c06', 
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