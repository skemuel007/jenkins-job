node {
    def mvnHome 
    
    stage('Preparation') {
        git 'https://git.com/skemuel/<project>.git'
        mvnHome = tool 'M3'
    }

    stage('Build') {
        if (isUnix()) {
            sh "'${mvnHome}/bin/mvn' -Dmaven.test.failure.ignore clean package"
        } else {
            bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
        }
    }

    stage('Results') {
        junit '**/target/surefire-reports/Test-*.xml'
        archive 'target/*.jar'
    }
}