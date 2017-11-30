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
				sh 'cd Tomcat && mvn clean compile assembly:single'
				
				/*// Compiling and building OwnProgram
				sh 'cd OwnProgram && mvn clean compile assembly:single'*/
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
        /*stage('Test'){
            steps {
				catchError{
					// Smoke Tests for Tomcat
				 sh 'cd Tomcat && mvn test -Dtest=TestConnector'
				 sh 'cd Tomcat && mvn test -Dtest=TestSerializablePrincipal'
				 sh 'cd Tomcat && mvn test -Dtest=TestTomcat'
				 sh 'cd Tomcat && mvn test -Dtest=TestGroupChannelStartStop'
				 sh 'cd Tomcat && mvn test -Dtest=TestRemoteIpValve'
				 sh 'cd Tomcat && mvn test -Dtest=TestELArithmetic'
				 sh 'cd Tomcat && mvn test -Dtest=TestAttributeParser'
				 sh 'cd Tomcat && mvn test -Dtest=TestDateFormatCache'
				}
			}
			post {
				always{
					// Run Emma for Tomcat
                    sh 'cd Tomcat && mvn emma:emma'
					publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'Tomcat/target/site/emma/', reportFiles: 'index.html', reportName: 'Emma Code Coverage Report for Tomcat', reportTitles: ''])
				}
           		success {
					// Testing OwnProgram
					sh 'cd OwnProgram && mvn test'
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
			post{
				success{
					findbugs canComputeNew: false, defaultEncoding: '', excludePattern: '', healthy: '', includePattern: '', pattern: 'Tomcat/target/findbugsXml.xml', unHealthy: ''
					checkstyle canRunOnFailed: true, defaultEncoding: '', healthy: '', pattern: '', unHealthy: ''	
				}
			}
		}*/
        stage('Deploy') {
			steps{
				
				// Deploying Tomcat
				sh 'cp -r Tomcat/conf/ /var/lib/jenkins/conf'
				sh 'cp -r Tomcat/webapps/ /var/lib/jenkins/webapps'
				sh 'cp Tomcat/target/lsd-app-1.0-SNAPSHOT-jar-with-dependencies.jar /var/lib/jenkins/TomcatJars'

				// Deploying OwnProgram
				sh 'cp OwnProgram/target/lsd-ownprogram-oldProgram-jar-with-dependencies.jar /var/lib/jenkins/OwnProgramJars'
			}
		}
    }
}
