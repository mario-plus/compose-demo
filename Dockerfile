FROM openjdk
MAINTAINER zxz
ADD /target/compose-demo-1.0-SNAPSHOT.jar  compose-demo.jar
EXPOSE 9999
ENTRYPOINT ["java","-jar","/compose-demo.jar"]
