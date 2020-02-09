FROM openjdk
ADD build/libs/tellsytest-0.0.1-SNAPSHOT.jar jarnik.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "jarnik.jar"]
