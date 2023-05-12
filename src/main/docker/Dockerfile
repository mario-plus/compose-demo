FROM eclipse/debian_jdk8
MAINTAINER zxz
VOLUME /home/compose
ADD compose-demo-1.0-SNAPSHOT.jar  compose-demo.jar
EXPOSE 9999
ENTRYPOINT ["java","-jar","compose-demo.jar"]
