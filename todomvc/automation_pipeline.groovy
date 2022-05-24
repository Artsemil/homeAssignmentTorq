pipeline {
    agent { node { label 'ec2' } }
    options { timestamps() }
    tools {
        maven 'Maven-3.3.9'
        jdk 'openjdk-12'
    }

    stages {
        stage('clean') {
            steps {
                cleanWs()
            }
        }

        stage('git') {
            steps {
                script {

                }
                git branch: "${params.AutomationBranch}", url: 'https://github.com/Artsemil/homeAssignmentTorq'
            }
        }

        stage('Tests') {
            steps {
                script {
                    configFileProvider([configFile(fileId: 'mavensettings', variable: 'settingsFile')]) {
                        sh '''#!/bin/bash
                           docker-compose up -d --scale chrome=${nodesAmount} 
                           mvn clean --settings $settingsFile -Dmaven.test.failure.ignore=true -DsuiteFile=${testXML} install'''
                    }
                }
            }
        }
    }

    post {
        always {
            step([$class: 'Publisher'])
            script {
                sh '''#!/bin/bash
                      docker-compose down'''


                /*reporting*/
            }
        }
    }
}