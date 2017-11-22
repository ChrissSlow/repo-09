pipeline {
    agent any 
	
	triggers {
		pollSCM('*/5 * * * *')
	}
	
	tools{
		maven 'apache-maven-3.5.2'
		jdk 'java-sdk-1.8'
	}
    stages {
        stage('Build') { 
            steps {
				sh 'cd Tomcat && mvn clean compile'		
				sh 'cd Tomcat && mvn assembly:single'
            }
			post {
                success {
                    archiveArtifacts(artifacts: 'Tomcat/target/lsd-app-1.0-SNAPSHOT-jar-with-dependencies.jar', allowEmptyArchive: true)
                }
            }
        }
        stage('Test'){
            steps {
                sh 'cd Tomcat && mvn test'
            }
        }
		stage('Reports') {                         
			steps {
                sh 'cd Tomcat && mvn findbugs:findbugs'
                                   
                step([$class: 'FindBugsPublisher', pattern: 'Tomcat/target/findbugsXml.xml'])
			}
        }
        stage('Deploy') {
			steps{
				sh 'cp Tomcat/target/lsd-app-1.0-SNAPSHOT-jar-with-dependencies.jar ~/home/jenkinsDeployment'
			}
		}
    }
}