"# OLS REST API" 
OLS Client Access Link:
https://ols-app.netlify.com/
<br/>
https://github.com/sandeeprgurav/ols-client

OLS Rest API Access:
https://localhost:8443/swagger/index.html
<br/>
https://github.com/sandeeprgurav/ols-rest-api

OLS Serverless API:
https://github.com/sandeeprgurav/serverless-stack-api


Netlify Account:
https://app.netlify.com/teams/sandeeprgurav/sites

Seed Account:
https://console.seed.run/sandeeprgurav1/serverless-stack-api

Jekins Access:
http://3.134.234.45:8082/


Access Linux :
connect locally ec2 instance:
goto ols-key.pem drive :C:\Sandeep\Career\aws
ssh -i ols-key.pem ec2-user@3.134.234.45

-------------------------------------------------------------------------------------------------------------------------
SSL certifiacation: 
https://www.thomasvitale.com/https-spring-boot-ssl-certificate/

/var/lib/jenkins/workspace/ols-rest-api

Check all runing processes:
sudo netstat -plten |grep java


sudo keytool -genkeypair -alias tomcat -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore keystore.p12 -validity 3650

------------------------------------------------------------------------------------------------------------
How to install Jenkins on Amazon AWS EC2 Linux | 8 Steps
https://www.youtube.com/watch?v=jmm8DsosBqw

1. How to download and install Jenkins on aws ec2 linux
2. How to access Jenkins from browser
3. How to start and stop Jenkins
4. How to start Jenkins on different port
5. How to uninstall Jenkins

Step 1 : Connect to your Linux machine

Step 2: Update Packages
   sudo yum update

Step 3 : Check Java is installed. If not install java  
   java -version
   sudo yum install java-1.8.0
   
   and JDK 
   sudo yum install java-1.8.0-openjdk-devel
   
   Find JDK path 
  ls -l /etc/alternatives/java

   
   export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.222.b10-0.amzn2.0.1.x86_64/jre/bin/java

   To check and select one out of multiple java versions available
   sudo /usr/sbin/alternatives --config java

Step 4 : Download latest Jenkins code package
   sudo wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins-ci.org/redhat/jenkins.repo

Step 5 : Import a key file from Jenkins-CI to enable installation from the package
   sudo rpm --import http://pkg.jenkins-ci.org/redhat/jenkins-ci.org.key

Step 6 : Install Jenkins
   sudo yum install jenkins

Step 7 : Start jenkins
   sudo service jenkins start
   
curl http://127.0.0.1:8080 to check on linux cmd    

Add Jenkins user to root group:
sudo usermod -a -G root jenkins
Make Jenkins listen to all external IPs by editing file /etc/sysconfig/jenkins and changing the JENKINS_LISTEN_ADDRESS="0.0.0.0"

Step 8 : Access Jenkins server using the public DNS of your ec2 on port 8080
   http://{ec2-public-dns}:8080
   example : http://3.134.234.45:8082/

Note : Here you might have to allow port 8080 in your security group settings
                     

Useful tips

To start jenkins on a diff port
Update port number in /etc/sysconfig/jenkins


To fetch initial admin password
 sudo su -
 cd /var/lib/jenkins/secrets/
 cat initialAdminPassword
0bcbbcab7f984af7b4171b55e9201d04
To stop Jenkins
 sudo service jenkins stop

To uninstall Jenkins
 sudo service jenkins stop
 sudo yum remove jenkins
 sudo rm -r /var/lib/jenkins


