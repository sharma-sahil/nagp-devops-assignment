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
    			git poll:true, credentialsId: '34ed3503-6786-4e1f-97e6-d275a8699c06', 
    			url: 'https://github.com/sharma-sahil/nagp-devops-assignment.git'
    		}
    	}
    	// analyse the code in sonarqube
    	stage('Sonar Analysis'){
    		steps {
    		// SonarQube Server -> Name of SonarQube configured in Jenkins configuration
          		withSonarQubeEnv('SonarQube Server') { 
    			  bat "mvn clean package sonar:sonar \
  				  -Dsonar.host.url=http://localhost:9000 \
  				  -Dsonar.login=ef20c6a45405c47d7f27d996de0d83d8e852f44f"
          		}
    		}
    	}
    	// check if the quality gate passed or not
    	stage("Quality Gate") {
        steps {
          // for this step to work, need to setup a web hook in sonarqube
          timeout(time: 1, unit: 'HOURS') {
            waitForQualityGate abortPipeline: true
          }
        }
      }
       // while pushing to artifactory it runs mvn clean install
       // so seaprate build step is not required
      stage('Build and publish artifactory') {
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
      // step to build and push docker image
      stage('Build & Push docker image') {
        steps {
          script{
            // dockerHubCredentials -> id of docker credentials configured in the Jenkins Credentials
            withCredentials([usernamePassword( credentialsId: 'dockerHubCredentials', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
              // build the docker image
              bat "docker build -t sharmasahil95/devops-test:${env.BUILD_ID} ."
              // login to docker hub
              bat "docker login -u ${USERNAME} -p ${PASSWORD}"
              // push latest image to docker hub
              bat "docker push sharmasahil95/devops-test:${env.BUILD_ID}"
              }
            }
          }
        }
        // run the image in docker container
  	  stage('Deploy') {
        steps {
          script{
            try {
                // stop already running container
                bat "docker stop SpringMvcMaven"
                // remove the old container
                bat "docker container rm SpringMvcMaven"
              } catch(Exception err){
                // do nothing
                // added exception handling to prevent pipeline from failing 
                // when the pipeline is run first time, the container will not be up and the above steps will throw exception
              } finally {
                // start a new container
                bat "docker run -d -p 8888:8080 --name SpringMvcMaven sharmasahil95/devops-test:${env.BUILD_ID}"
              }
            }
          }
        }
    }
}