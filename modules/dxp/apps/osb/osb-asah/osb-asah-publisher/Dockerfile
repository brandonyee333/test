FROM adoptopenjdk/openjdk8-openj9:x86_64-alpine-jdk8u232-b09_openj9-0.17.0-slim

ARG LABEL_BUILD_DATE
ARG LABEL_VCS_REF

CMD ["sh", "-c", "java ${JAVA_OPTS} ${JAVA_OPTS_EXT} -XshowSettings:vm -XX:+PrintCommandLineFlags -jar app.jar"]

ENV LABEL_BUILD_DATE=${LABEL_BUILD_DATE}
ENV LABEL_VCS_REF=${LABEL_VCS_REF}

EXPOSE 8080

USER root

WORKDIR /root

COPY ./build/libs/*.jar app.jar