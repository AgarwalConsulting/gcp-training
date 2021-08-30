FROM hub.docker.com/openjdk:11.0.12-jdk AS builder
WORKDIR /app
COPY . .
# RUN apt update
# RUN apt install -y openjdk-11-jdk
RUN ls
RUN javac Hello.java

FROM mcr.microsoft.com/java/jre:11-zulu-alpine
WORKDIR /app
COPY --from=builder /app/. .
CMD ["java", "Hello"]
# RUN javac
