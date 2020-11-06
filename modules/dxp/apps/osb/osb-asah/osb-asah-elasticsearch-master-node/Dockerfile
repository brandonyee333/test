FROM elasticsearch:7.9.2

ENV TAKE_FILE_OWNERSHIP true

COPY --chown=elasticsearch:root docker-resources /

RUN yum -y install patch && \
    patch /usr/local/bin/docker-entrypoint.sh /usr/tmp/docker-entrypoint.patch
RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install --batch repository-s3