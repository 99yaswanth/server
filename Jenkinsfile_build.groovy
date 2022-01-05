pipeline{
    agent any
    parameters{
        string(name: 'BRANCH', defaultValue: '', description: '')
    }
    stages{
        stage("clone code"){
            steps{
                print("cloning the code")
                git branch: "${BRANCH}",
                url: 'https://github.com/99yaswanth/boxfuse-sample-java-war-hello.git' 
            }
        }
        stage("build"){
            steps{
                print("building the code")
                sh "mvn clean package"
                sh "ls -l"
            }
        }
        // stage("uploading artifacts to s3"){
        //     steps{
        //         println "upload artifacts to s3 bucket"
        //         sh "aws s3 cp target/hello-${BUILD_NUMBER}.war s3://yashwanth24/"

        //     }
        // }

    }
}