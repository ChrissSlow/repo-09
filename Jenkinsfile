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
				// Compiling and building Tomcat
				sh 'cd Tomcat && mvn clean compile'		
				sh 'cd Tomcat && mvn assembly:single'
				
				// Compiling and building OwnProgram
				sh 'cd OwnProgram && mvn clean compile'		
				sh 'cd OwnProgram && mvn assembly:single'
            }
			post {
                success {
					// Archive JAR from Tomcat
                    archiveArtifacts(artifacts: 'Tomcat/target/lsd-app-1.0-SNAPSHOT-jar-with-dependencies.jar', allowEmptyArchive: true)
					
					// Archive JAR from OwnProgram
					archiveArtifacts(artifacts: 'OwnProgram/target/lsd-ownprogram-oldProgram-jar-with-dependencies.jar', allowEmptyArchive: true)
                }
            }
        }
        stage('Test'){
            steps {
				// Testing Tomcat
                sh 'cd Tomcat && mvn test'
				
				// Testing OwnProgram
				sh 'cd OwnProgram && mvn test'
            }		
			post {
           		success {
					// Run Emma for Tomcat
                    sh 'cd Tomcat && mvn emma:emma'
					publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'Tomcat/target/site/emma/', reportFiles: 'index.html', reportName: 'Emma Code Coverage Report for Tomcat', reportTitles: ''])
				}
			}
        }
		stage('Reports') {                         
			steps {
				// Running FindBugs
				sh 'cd Tomcat && mvn findbugs:findbugs'					
				
				// Running Checkstyle
				sh 'cd Tomcat && mvn checkstyle:checkstyle -Dcheckstyle.config.location="${WORKSPACE}/Tomcat/checkstyle.xml"' 				
			}
			steps{
				// Running FindBugs
				sh 'cd OwnProgram && mvn findbugs:findbugs'					
				
				// Running Checkstyle
				sh 'cd OwnProgram && mvn checkstyle:checkstyle -Dcheckstyle.config.location="${WORKSPACE}/Tomcat/checkstyle.xml"' 	
			}
			post{
				success{
					findbugs canComputeNew: false, defaultEncoding: '', excludePattern: '', healthy: '', includePattern: '', pattern: '**/findbugsXml.xml', unHealthy: ''
					checkstyle canRunOnFailed: true, defaultEncoding: '', healthy: '', pattern: '**/checkstyle.xml', unHealthy: ''	
				}
			}
		}
        stage('Deploy') {
			steps{
				// Deploying Tomcat
				sh 'cp Tomcat/target/lsd-app-1.0-SNAPSHOT-jar-with-dependencies.jar /var/lib/jenkins/TomcatJars'

				// Deploying OwnProgram
				sh 'cp OwnProgram/target/lsd-ownprogram-oldProgram-jar-with-dependencies.jar /var/lib/jenkins/OwnProgramJars'
			}
		}
    }
}
