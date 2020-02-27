FROM openjdk:8-jdk-alpine
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/cardconsumer
ADD @project.build.finalName@.jar /usr/local/cardconsumer/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
