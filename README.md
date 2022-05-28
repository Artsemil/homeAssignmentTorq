# Home Assignment Torq

### How do I get set up? ###

#### Locally
* Install JAVA 11 and maven
* In the file.properties file set BROWSER=CHROME.
* run command in terminal: mvn clean -Dmaven.test.failure.ignore=true -DsuiteFile=SanityTest.xml install

#### In the Docker
* Install Docker
* In the file.properties file set BROWSER=CHROME_DOCKER.
* run command in terminal: docker-compose up -d
* run command in terminal: mvn clean -Dmaven.test.failure.ignore=true -DsuiteFile=SanityTest.xml install


### Concurrent execution ###
Can be done using TestNG and Docker
* thread-count parameter in the .xml file with tests
* nodesAmount variable in the pipeline

### CExecution within a CI/CD pipeline ###
Created automation_pipeline.groovy with variables that should be set up in the Jenkins