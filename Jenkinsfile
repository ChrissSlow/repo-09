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
					publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'Tomcat/target/site/emma', reportFiles: 'index.html', reportName: 'Emma Code Coverage Report', reportTitles: ''])
					
					// Run Emma for OwnProgram
					sh 'cd OwnProgram && mvn emma:emma'
					publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'OwnProgram/target/site/emma', reportFiles: 'index.html', reportName: 'Emma Code Coverage Report', reportTitles: ''])
				}
			}
        }
		stage('Reports') {                         
			steps {
				// Running FindBugs and Checkstyle for Tomcat
				sh 'cd Tomcat && mvn findbugs:findbugs'           
				findbugs canComputeNew: false, defaultEncoding: '', excludePattern: '', healthy: '', includePattern: '', pattern: 'Tomcat/target/findbugsXml.xml', unHealthy: ''		
				sh 'cd Tomcat && mvn checkstyle:checkstyle -Dcheckstyle.config.location="${WORKSPACE}/Tomcat/checkstyle.xml"'           
				checkstyle canRunOnFailed: true, defaultEncoding: '', healthy: '', pattern: '', unHealthy: ''
				
				// Running FindBugs and Checkstyle for OwnProgram
				sh 'cd Tomcat && mvn findbugs:findbugs'           
				findbugs canComputeNew: false, defaultEncoding: '', excludePattern: '', healthy: '', includePattern: '', pattern: 'OwnProgram/target/findbugsXml.xml', unHealthy: ''		
				sh 'cd Tomcat && mvn checkstyle:checkstyle -Dcheckstyle.config.location="${WORKSPACE}/OwnProgram/checkstyle.xml"'           
				checkstyle canRunOnFailed: true, defaultEncoding: '', healthy: '', pattern: '', unHealthy: ''
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
