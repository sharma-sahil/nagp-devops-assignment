pipeline {
    agent any
    stages {
    	stage('Checkout'){
    		steps {
    			git poll:true, credentialsId: '34ed3503-6786-4e1f-97e6-d275a8699c06', 
    			url: 'https://github.com/sharma-sahil/nagp-devops-assignment.git'
    		}
    	}
    	stage('Sonar Analysis'){
    		steps {
    			bat"mvn sonar:sonar \
  				-Dsonar.host.url=http://localhost:9000 \
  				-Dsonar.login=ef20c6a45405c47d7f27d996de0d83d8e852f44f"
    		}
    	}
        stage('Build') {
            steps {
               bat "mvn clean install"
            }
        }
    }
}