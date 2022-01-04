pipeline{
    agent any
    parameters{
        string(name: 'Serverip', defaultValue: '', description: '')
    }
    stages{
        stage("tomcat installations"){
            steps{
                sh """
                scp -o StrictHostKeyChecking=no -i /tmp/nvirginia1.pem /tmp/tomcatinstallation.sh ec2-user@${serverip}:/tmp
                ssh -o StrictHostKeyChecking=no -i /tmp/nvirginnia1.pem ec2-user@${serverip} "sudo bash /tmp/tomcatinstallation.sh"
                  """
            }
        }
    }
}