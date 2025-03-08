FROM openjdk:21-jdk-slim

WORKDIR /app

COPY . /app

RUN javac TCP/src/*.java

CMD ["java", "-cp", "TCP/src", "TCPServer"]