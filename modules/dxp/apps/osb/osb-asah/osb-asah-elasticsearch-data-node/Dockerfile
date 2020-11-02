FROM elasticsearch:7.9.2

ENV TAKE_FILE_OWNERSHIP true

COPY ./docker-entrypoint.sh /usr/local/bin/docker-entrypoint.sh

RUN chmod +x /usr/local/bin/docker-entrypoint.sh
RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install --batch repository-s3

ADD ./elasticsearch.yml /usr/share/elasticsearch/config/elasticsearch.yml