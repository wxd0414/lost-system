# JDK版本和你的项目JDK保持一致
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
# 复制打包好的jar到容器内
COPY target/lostsystem-*.jar app.jar

EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]