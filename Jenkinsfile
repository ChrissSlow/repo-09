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
				sh 'cd Tomcat && mvn clean emma:emma compile'			
            }
        }
        stage('Test'){
            steps {
                sh 'cd Tomcat && mvn test'
            }
        }
        stage('Deploy') {
            steps {
                sh 'cd Tomcat && mvn assembly:single'
            }
			post {
                success {
                    archiveArtifacts(artifacts: 'Tomcat/target/*.jar', allowEmptyArchive: true)
                }
            }
        }
    }
}
