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
			post {
           		success {
                    sh 'cd Tomcat && mvn emma:emma'
					publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: 'tomcat/target/site/emma', reportFiles: 'index.html', reportName: 'Emma Code Coverage Report', reportTitles: ''])
				}
			}
        }
		stage('Reports') {                         
			steps {
				sh 'cd Tomcat && mvn findbugs:findbugs'           
				findbugs canComputeNew: false, defaultEncoding: '', excludePattern: '', healthy: '', includePattern: '', pattern: 'Tomcat/target/findbugsXml.xml', unHealthy: ''
									
				sh 'cd Tomcat && mvn checkstyle:checkstyle -Dcheckstyle.config.location="${WORKSPACE}/Tomcat/checkstyle.xml"'           
				checkstyle canRunOnFailed: true, defaultEncoding: '', healthy: '', pattern: '', unHealthy: ''
}
}
        stage('Deploy') {
			steps{
				sh 'cp Tomcat/target/lsd-app-1.0-SNAPSHOT-jar-with-dependencies.jar /var/lib/jenkins/TomcatJars'
			}
		}
    }
}
