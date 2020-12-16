FROM google/cloud-sdk:280.0.0-alpine

CMD ["gcloud", "beta", "emulators", "pubsub", "start", "--data-dir=/var/pubsub", "--host-port=0.0.0.0:8095", "--log-http", "--project=liferaycloud-customer-ac" , "--user-output-enabled", "--verbosity=debug"]

EXPOSE 8095

RUN apk --update add openjdk8-jre
RUN gcloud components install --quiet beta pubsub-emulator
RUN mkdir -p /var/pubsub

VOLUME /var/pubsub