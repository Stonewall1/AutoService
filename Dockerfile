FROM openjdk:11
COPY /build/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

#client side
#docker build -t stonewall1/autoservice .            (build image)
#docker tag autoservice stonewall1/autoservice       (changing tag, optional)
#docker push stonewall1/autoservice                  (pushing to repository on docker hub)

#server side
#docker pull stonewall1/autoservice                  (pull from repository)

#docker run -p 8080:8080 -d stonewall1/autoservice   (run)
#docker run -p 8082:8080 -d stonewall1/autoservice