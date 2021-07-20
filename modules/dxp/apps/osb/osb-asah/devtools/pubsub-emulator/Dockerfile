FROM google/cloud-sdk:348.0.0-alpine

RUN apk --update add openjdk8-jre
RUN echo ".level=WARN" > /logging.properties
RUN gcloud components install --quiet beta pubsub-emulator

CMD ["java", "-Djava.util.logging.config.file=/logging.properties", "-jar", "/google-cloud-sdk/platform/pubsub-emulator/lib/cloud-pubsub-emulator-0.4.1.jar", "--host=0.0.0.0", "--port=8095", "--projects=liferaycloud-customer-ac"]