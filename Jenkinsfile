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
    stage('Allure report') {
      steps {
        script {
          // Generate Allure history for trends
          sh "${MVN} allure:report"
          allure includeProperties: false, 
                 jdk: '', 
                 results: [[path: "target/allure-results"]],
                 reportBuildPolicy: 'ALWAYS',
                 properties: []
        }
      }
    }
    stage('Rerun Failures') {
      when {
        expression {
          fileExists('target/rerun.txt') && readFile('target/rerun.txt').trim()
        }
      }
      steps {
        sh "${MVN} test -Dtest=RunFailedTests -Dcucumber.features=@target/rerun.txt"
      }
    }
  }
  post {
    always {
      archiveArtifacts artifacts: 'target/allure-results/**', allowEmptyArchive: true
      junit 'target/surefire-reports/*.xml'
      cucumber buildStatus: 'UNSTABLE',
        fileExcludePattern: '',
        fileIncludePattern: '**/*.json',
        trendsLimit: 10
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
