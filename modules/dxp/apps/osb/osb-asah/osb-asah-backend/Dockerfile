FROM adoptopenjdk/openjdk8-openj9:jdk8u232-b09_openj9-0.17.0-alpine-slim AS builder

WORKDIR /builder

COPY ./build/libs/*application.jar app.jar

RUN java -Djarmode=layertools -jar app.jar extract

FROM adoptopenjdk/openjdk8-openj9:jdk8u232-b09_openj9-0.17.0-alpine-slim

ARG LABEL_BUILD_DATE
ARG LABEL_VCS_REF

CMD ["sh", "-c", "java ${JAVA_OPTS} ${JAVA_OPTS_EXT} -XshowSettings:vm -XX:+PrintCommandLineFlags org.springframework.boot.loader.JarLauncher"]

ENV LABEL_BUILD_DATE=${LABEL_BUILD_DATE}
ENV LABEL_VCS_REF=${LABEL_VCS_REF}

EXPOSE 8080

USER root

WORKDIR /root

COPY --from=builder /builder/dependencies/ .
COPY --from=builder /builder/snapshot-dependencies/ .
COPY --from=builder /builder/spring-boot-loader/ .

COPY --from=builder /builder/application .