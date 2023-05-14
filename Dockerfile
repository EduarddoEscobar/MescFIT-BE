FROM openjdk:17
EXPOSE 8080
ADD target/mescfit.jar mescfit.jar
ENTRYPOINT ["java", "-jar", "mescfit.jar"]
