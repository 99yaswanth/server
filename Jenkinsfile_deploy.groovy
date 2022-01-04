pipeline{
    agent any
    
    parameters { 
        string(name: 'SERVERIPS', defaultValue: '', description: '') 
        //string(name: 'BRANCH', defaultValue: '', description: '')
        string(name: 'BUILD', defaultValue: '', description: '')
    }
    stages{
        stage("Multiple servers")
        {
            steps {
                sh """
                    aws s3 cp s3://yashwanth24/hello-${BUILD}.war .
                    ls -l
                   inputArray=$SERVERIPS
                   echo $inputArray
                   IFS=',' read -r -a outputArray <<< "$inputArray"
                   for ip in \"${outputArray[@]}\"
                   do
                   echo "server ip is : $ip"
                   done

                 scp -o StrictHostKeyChecking=no -i /tmp/nvirginia1.pem hello-${BUILD}.war ec2-user@$ip:/tmp/
                 ssh -o StrictHostKeyChecking=no -i /tmp/nvirginia1.pem ec2-user@$ip "sudo cp /tmp/hello-${BUILD}.war /var/lib/tomcat/webapps"
                   
                   
                   """
            }
        }
    }
}
