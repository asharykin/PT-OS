FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY src /app/src
COPY lib /app/lib
RUN javac -d out -sourcepath src -cp lib/*.jar src/ru/mipt/filler/Application.java
CMD ["java", "-ea", "-cp", "out:lib/*", "ru.mipt.filler.Application"]