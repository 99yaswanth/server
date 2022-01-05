pipeline{
    agent any
    parameters{
        string(name: 'Serverip', defaultValue: '', description: '')
    }
    stages{
        stage("tomcat installations"){
            steps{
                sh '''
                inputArray=$Serverip
                   echo $inputArray
                   IFS=',' read -r -a outputArray <<< "$inputArray"
                   for ip in ${outputArray[@]}
                   do
                   echo "server ip is : $ip"
                   done
                scp -o StrictHostKeyChecking=no -i /tmp/nvirginia1.pem /tmp/tomcatinstallation.sh ec2-user@$ip:/tmp
                ssh -o StrictHostKeyChecking=no -i /tmp/nvirginia1.pem ec2-user@$ip "sudo bash /tmp/tomcatinstallation.sh"
                  '''
            }
        }
    }
}