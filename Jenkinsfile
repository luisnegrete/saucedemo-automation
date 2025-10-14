pipeline {
  agent any
  /* Parámetros que aparecerán en "Build with Parameters" */
  parameters {
    string(name: 'THREADS', defaultValue: '4', description: 'Number of threads to pass to Maven (-Dthreads)')
    string(name: 'TAGS', defaultValue: '', description: 'Cucumber tags filter (e.g. @smoke). Leave empty to run all.')
    string(name: 'BRANCH', defaultValue: 'main', description: 'Git branch to build')
  }
  tools {
    jdk 'jdk17'     // Updated to match Java 17 requirement from pom.xml
    maven 'maven3'  // nombre tal como lo registraste
    allure 'allure-2.35.1' // Updated to Allure 2.35.1
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
        // Instalar navegadores de Playwright
        sh "${MVN} -e exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args=\"install\""
        // Instalar dependencias del sistema sin requerir sudo
        sh "docker run --rm -v ${WORKSPACE}:/app -w /app maven:3.8.6-eclipse-temurin-17 ${MVN} -e exec:java -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args=\"install-deps --dry-run\" | grep 'apt-get install' | sed 's/sudo //' | xargs -r apt-get install -y"
      }
    }
    stage('Run tests') {
      steps {
        script {
          // construye comando maven dinámicamente
          def mvnCmd = "${MVN} test -B -DskipTests=false"
          // Add proper parallel execution for Cucumber
          if (params.THREADS?.trim()) {
            mvnCmd += " -Dcucumber.execution.parallel.enabled=true -Dcucumber.execution.parallel.config.strategy=fixed -Dcucumber.execution.parallel.config.fixed.parallelism=${params.THREADS}"
          }
          if (params.TAGS?.trim()) {
            // Sanitize tags input and wrap in single quotes
            def sanitizedTags = params.TAGS.replaceAll(/[^a-zA-Z0-9@^,\s-]/, '')
            mvnCmd += " -Dcucumber.filter.tags='${sanitizedTags}'"
          }
          echo "Running tests: ${mvnCmd}"
          sh mvnCmd
        }
      }
    }
    stage('Rerun Failed Tests') {
      when {
        expression { fileExists('target/rerun.txt') }
      }
      steps {
        script {
          echo "Re-running failed tests from rerun.txt"
          def rerunCmd = "${MVN} test -B -Dtest=com.saucedemo.runners.RunFailedTests"
          
          if (params.THREADS?.trim()) {
            rerunCmd += " -Dcucumber.execution.parallel.enabled=true -Dcucumber.execution.parallel.config.strategy=fixed -Dcucumber.execution.parallel.config.fixed.parallelism=${params.THREADS}"
          }
          
          if (params.TAGS?.trim()) {
            def sanitizedTags = params.TAGS.replaceAll(/[^a-zA-Z0-9@^,\s-]/, '')
            rerunCmd += " -Dcucumber.filter.tags='${sanitizedTags}'"
          }
          
          echo "Rerunning tests with same parameters: ${rerunCmd}"
          sh rerunCmd
        }
      }
    }
    
    stage('Allure report') {
      steps {
        script {
          // Generate Allure history for trends
          sh "${MVN} allure:report"
          allure commandline: 'allure-2.35.1',
            includeProperties: false, 
            jdk: '', 
            results: [[path: "target/allure-results"]],
            reportBuildPolicy: 'ALWAYS',
            properties: [
              [key: 'allure.report.remove.attachments', value: 'true'], // Remove old attachments
              [key: 'allure.issues.tracker.pattern', value: ''] // Disable issue links
            ]
        }
      }
    }
  }
  post {
    always {
      archiveArtifacts artifacts: 'target/allure-results/**', allowEmptyArchive: true
      junit skipPublishingChecks: true, testResults: 'target/surefire-reports/*.xml' // Suprime el warning de Checks API
      cleanWs() // Clean workspace after build
    }
    failure {
      script {
        // Use Jenkins credentials for email instead of hardcoding
        emailext body: "See ${env.BUILD_URL}",
          recipientProviders: [[$class: 'DevelopersRecipientProvider']],
          subject: "Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
          to: '${DEFAULT_RECIPIENTS}' // Configure in Jenkins credentials
      }
    }
  }
}
