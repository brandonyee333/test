FROM elasticsearch:7.8.1

ENV TAKE_FILE_OWNERSHIP true

RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install --batch repository-s3

ADD ./elasticsearch.yml /usr/share/elasticsearch/config/elasticsearch.yml