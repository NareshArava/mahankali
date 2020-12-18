FROM openjdk:11
ENV MONGO_DATABASE=bookdb
ENV MONGO_URI=mongodb://mongo:2701/bookdb
ADD ./target/favourite-service-0.0.1-SNAPSHOT.jar /usr/src/favourite-service-0.0.1-SNAPSHOT.jar
EXPOSE 9898
WORKDIR usr/src

ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo:2701/bookdb","-jar","favourite-service-0.0.1-SNAPSHOT.jar"]
