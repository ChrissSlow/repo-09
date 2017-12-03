# Create the image from the Dockerfile
```
docker build -t tomcat_image:1.0.0 .
```
# Create and start the container 
## Use port 8081 on the hostmachine to access the tomcat
## Mount the created artifacts (jar, configurations and webapps) so the container just has to be restarted
```
docker run --name tomcat_container -p 8081:8080 -v /var/lib/jenkins/TomcatJars:/app/target:ro -v /var/lib/jenkins/conf:/app/conf:ro -v /var/lib/jenkins/webapps/:/app/webapps -d tomcat_image:1.0.0
```