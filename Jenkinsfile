// all of the below code is writted considering that the system on which pipeline is running is a windows machine 
// if the pipeline has to be run on the linux server then some changes will be required.
// e.g. -> bat will be replaced with sh and you may also need some changes in the docker file as well

pipeline {
    agent any
    // to setup the trigger for polling any change in github
    triggers {
      pollSCM('H/2 * * * *') 
    }          
    stages {
      // checkout the latest code from the github
    	stage('Checkout'){
    		steps {
          // GitHubID -> Id of credential which are setup in the jenkins itself
    			git credentialsId: 'GitHubID', url: 'https://github.com/sharma-sahil/nagp-devops-assignment.git'
    		}
    	}
    	// analyse the code in sonarqube
    	// stage('Sonar Analysis'){
    	// 	steps {
    	// 	// SonarQube Server -> Name of SonarQube configured in Jenkins configuration
      //     		withSonarQubeEnv('SonarQube Server') { 
    	// 		  sh "mvn clean package sonar:sonar \
  		// 		  -Dsonar.host.url=http://localhost:9000 \
  		// 		  -Dsonar.login=ef20c6a45405c47d7f27d996de0d83d8e852f44f"
      //     		}
    	// 	}
    	// }
    	// // check if the quality gate passed or not
    	// stage("Quality Gate") {
      //   steps {
      //     // for this step to work, need to setup a web hook in sonarqube
      //     timeout(time: 1, unit: 'HOURS') {
      //       waitForQualityGate abortPipeline: true
      //     }
      //   }
      // }
       // while pushing to artifactory it runs mvn clean install
       // so seaprate build step is not required
      stage('Build and Push to artifactory') {
        steps {
          script{
           	// artifactoryServer -> Artifactory Server ID configured in jenkins configuration
           
            def server = Artifactory.server 'artifactoryServer'
            def buildInfo = Artifactory.newBuildInfo()
            buildInfo.env.capture = true
            buildInfo.env.collect()
            def rtMaven = Artifactory.newMavenBuild()
            
            // MavenTool -> name of maven installations under configure tools in jenkins
            rtMaven.tool = 'MavenTool'
            rtMaven.deployer releaseRepo: 'workshop', snapshotRepo:'workshop', server: server
            rtMaven.run pom: 'pom.xml', goals: 'clean install', buildInfo: buildInfo
            server.publishBuildInfo buildInfo
          }
        }
      }
      stage('Build docker image') {
        steps {
          sh "docker build -t sharmasahil95/devops-test ."
          }
        }
    }
}
