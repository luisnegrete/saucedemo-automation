pipeline {
  agent any
  /* Parámetros que aparecerán en "Build with Parameters" */
  parameters {
    string(name: 'THREADS', defaultValue: '4', description: 'Number of threads to pass to Maven (-Dthreads)')
    string(name: 'TAGS', defaultValue: '', description: 'Cucumber tags filter (e.g. @smoke). Leave empty to run all.')
    string(name: 'BRANCH', defaultValue: 'main', description: 'Git branch to build')
  }
  tools {
    jdk 'jdk17'     // nombre tal como lo registraste en Global Tool Config
    maven 'maven3'  // nombre tal como lo registraste
  }
  environment {
    MVN = "${tool 'maven3'}/bin/mvn"
  }
  stages {
    stage('Checkout') {
      steps {
        checkout([$class: 'GitSCM',
          branches: [[name: "*/${params.BRANCH}"]],
          userRemoteConfigs: [[url: 'https://github.com/luisnegrete/saucedemo-automation.git', credentialsId: 'github-credentials']]
        ])
      }
    }
    stage('Prepare - install dependencies and Playwright browsers') {
      steps {
        echo "Installing Maven dependencies (skip tests) and Playwright browsers..."
        sh "${MVN} clean install -DskipTests -B"
        // comando para instalar navegadores de Playwright Java
        sh "${MVN} -e exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args=\"install\""
      }
    }
    stage('Run tests') {
      steps {
        script {
          // construye comando maven dinámicamente
          def mvnCmd = "${MVN} test -Dthreads=${params.THREADS} -B -DskipTests=false"
          if (params.TAGS?.trim()) {
            // pasa el tag a cucumber
            mvnCmd += " -Dcucumber.filter.tags=\"${params.TAGS}\""
          }
          echo "Running tests: ${mvnCmd}"
          sh mvnCmd
        }
      }
    }
    stage('Allure report') {
      steps {
        // usa plugin de Allure de Jenkins
        allure includeProperties: false, jdk: '', results: [[path: "target/allure-results"]]
      }
    }
  }
  post {
    always {
      archiveArtifacts artifacts: 'target/allure-results/**', allowEmptyArchive: true
      junit 'target/surefire-reports/*.xml' // si generas surefire reports
      echo "Build finished. See console log and Allure report."
    }
    failure {
      mail to: 'tu-email@example.com', subject: "Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}", body: "See ${env.BUILD_URL}"
    }
  }
}