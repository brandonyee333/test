FROM elasticsearch:7.9.2

ENV TAKE_FILE_OWNERSHIP true

RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install --batch repository-s3

COPY --chown=elasticsearch:root docker-resources/usr/share /usr/share
COPY --chown=elasticsearch:root docker-resources/usr/tmp /usr/tmp

RUN yum -y install patch && \
    patch /usr/local/bin/docker-entrypoint.sh /usr/tmp/docker-entrypoint.patch