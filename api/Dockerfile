FROM gradle:6.7.1-jdk11 AS build

WORKDIR /build
COPY . /build/

RUN java -version
RUN gradle build

FROM openjdk:11

WORKDIR  /app
RUN mkdir /app/uploads

COPY ./env-file-var.sh .
RUN chmod 755 env-file-var.sh

COPY ./application-env.yml application.yml
COPY --from=build /build/build/libs/*.jar /app/app.jar

ENTRYPOINT ["./env-file-var.sh", "java",  "-jar",  "app.jar"]
