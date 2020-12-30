FROM google/cloud-sdk:280.0.0-alpine

RUN apk --update add openjdk8-jre
RUN gcloud components install --quiet beta pubsub-emulator
RUN echo ".level=WARN" > /logging.properties

CMD ["java", "-Djava.util.logging.config.file=/logging.properties", "-jar", "/google-cloud-sdk/platform/pubsub-emulator/lib/cloud-pubsub-emulator-0.1-SNAPSHOT-all.jar", "--host=0.0.0.0", "--port=8095", "--projects=liferaycloud-customer-ac"]