node {
  agent any
  triggers {
    pollSCM('H/2 * * * *') 
  }          
  stages{
    stage('Checkout') {
      steps{
        script{
            git poll:true, credentialsId: '34ed3503-6786-4e1f-97e6-d275a8699c06', 
            url: 'https://github.com/sharma-sahil/nagp-devops-assignment.git'
        }
      }
    }
    stage('Sonar Analysis'){
      steps {
        script{
          withSonarQubeEnv('SonarQube Server') { 
            sh "mvn clean package sonar:sonar \
              -Dsonar.host.url=http://localhost:9000 \
              -Dsonar.login=ef20c6a45405c47d7f27d996de0d83d8e852f44f"
          }
        }
      }
    }
    stage("Quality Gate") {
      steps {
          script{
          timeout(time: 1, unit: 'HOURS') {
            waitForQualityGate abortPipeline: true
          }
        }
      }
    }
    stage('Build') {
      steps {
        script{
          bat "mvn clean install"
        }
      }
    }
    stage('Push to artifactory') {
      steps {
        script{
            def server = Artifactory.server 'artifactoryServer'
            // def buildInfo = Artifactory.newBuildInfo()
            // buildInfo.env.capture = true
            // buildInfo.env.collect()
            def rtMaven = Artifactory.newMavenBuild()
            rtMaven.tool = 'M3'
            rtMaven.deployer releaseRepo: 'workshop', snapshotRepo:'workshop', server: server
            rtMaven.run pom: 'pom.xml', goals: 'clean install'
            // , buildInfo: buildInfo
            // server.publishBuildInfo buildInfo
          }
        }
      }
    }     
  } 
