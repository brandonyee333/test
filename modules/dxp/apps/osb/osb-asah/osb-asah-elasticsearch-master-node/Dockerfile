FROM elasticsearch:7.9.2

ENV TAKE_FILE_OWNERSHIP true

COPY docker-resources /

RUN yum -y install patch && \
    patch /usr/local/bin/docker-entrypoint.sh /usr/tmp/docker-entrypoint.patch

RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install --batch repository-s3

ADD ./elasticsearch.yml /usr/share/elasticsearch/config/elasticsearch.yml