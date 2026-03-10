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
        stage('Build Docker Image') {
            when {
    expression { env.GIT_BRANCH == 'origin/main' }
}
            steps {
                sh "docker build -t evecandy3/pension-calculator:${BUILD_NUMBER} -t evecandy3/pension-calculator:latest ."
            }
        }
        stage('Push to Docker Hub') {
            when {
    expression { env.GIT_BRANCH == 'origin/main' }
}
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-credentials',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                    sh "docker push evecandy3/pension-calculator:${BUILD_NUMBER}"
                    sh "docker push evecandy3/pension-calculator:latest"
                }
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