pipeline {
    agent any
    stages {
        stage('git pull') {
            steps {
               git credentialsId: 'gitee', url: 'https://gitee.com/mario-plus/compose-demo.git'
            }
        }

        stage("execute sh"){
            steps {

               sh '''
                echo "构建开始"
                mvn clean install -Dmaven.test.skip=true
                echo "构建成功"
                echo '初始化部署脚本'
                rm -rf /home/compose
                mkdir /home/compose
                chmod 777 /home/compose
                cp /var/lib/jenkins/workspace/$JOB_NAME/src/main/docker/docker-compose.yml /home/compose/
                cp /var/lib/jenkins/workspace/$JOB_NAME/src/main/docker/start.sh  /home/compose/
                cp /var/lib/jenkins/workspace/$JOB_NAME/src/main/docker/Dockerfile  /home/compose/
                cp /var/lib/jenkins/workspace/$JOB_NAME/target/compose-demo-1.0-SNAPSHOT.jar /home/compose/
                echo '初始化脚本完成'
                echo '执行shell脚本'
                cd /home/compose
                echo 'dos2unix格式化sh脚本'
                dos2unix start.sh
                sh start.sh
                echo 'shell脚本执行成功'
                echo "good job" '''

            }
        }

    }
}
