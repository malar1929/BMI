pipeline {
    agent any

    stages {
        stage('Build and smoke test') {
            steps {
                script {
                    if (isUnix()) {
                        sh '''
                            set -eu
                            test -f BMI.html
                            test -f BMI.css
                            mkdir -p build/classes
                            cp BMI.java build/Main.java
                            javac -d build/classes build/Main.java
                            printf '70\\n1.75\\n' | java "-Duser.language=en" "-Duser.country=US" -cp build/classes Main > build/java-smoke-test.txt
                            grep -Fq 'BMI: 22.9' build/java-smoke-test.txt
                            grep -Fq 'Category: Normal weight' build/java-smoke-test.txt
                        '''
                    } else {
                        bat '''
                            if not exist BMI.html exit /b 1
                            if not exist BMI.css exit /b 1
                            if not exist build\classes mkdir build\classes
                            copy /Y BMI.java build\Main.java >NUL
                            javac -d build\classes build\Main.java || exit /b 1
                            (echo 70&echo 1.75) | java "-Duser.language=en" "-Duser.country=US" -cp build\classes Main > build\java-smoke-test.txt
                            if errorlevel 1 exit /b 1
                            findstr /C:"BMI: 22.9" build\java-smoke-test.txt >NUL || exit /b 1
                            findstr /C:"Category: Normal weight" build\java-smoke-test.txt >NUL || exit /b 1
                        '''
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'BMI.html,BMI.css,build/**', fingerprint: true
        }
    }
}