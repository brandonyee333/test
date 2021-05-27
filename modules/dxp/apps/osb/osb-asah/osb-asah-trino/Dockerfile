FROM trinodb/trino:353

COPY docker-resources/ /

USER root

RUN mkdir /data/hive-metastore
RUN chown -R trino:trino /data/hive-metastore