FROM elasticsearch:7.9.2

ENV TAKE_FILE_OWNERSHIP true

RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install --batch repository-s3

ADD ./elasticsearch.yml /usr/share/elasticsearch/config/elasticsearch.yml