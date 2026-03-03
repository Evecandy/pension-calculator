pipeline {
    agent any
    triggers {
        githubPush()
    }
    environment {
        BUILD_DIR = "built"
        REPO_URL = "https://github.com/Evecandy/pension-calculator.git"
        BRANCH = "main"
    }
    stages {
        stage('Checkout Code') {
            steps {
                git branch: "${BRANCH}",
                    credentialsId: 'github-pat',
                    url: "${REPO_URL}"
            }
        }
        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests=false'
            }
        }
        stage('Create built Directory') {
            steps {
                sh '''
                    mkdir -p ${BUILD_DIR}
                    cp target/*.jar ${BUILD_DIR}/
                '''
            }
        }
    }
    post {
        success {
            echo "Build successful. JAR stored in built/"
            archiveArtifacts artifacts: 'built/*.jar', fingerprint: true
        }
        failure {
            echo "Build failed. Check the logs above."
        }
    }
}
