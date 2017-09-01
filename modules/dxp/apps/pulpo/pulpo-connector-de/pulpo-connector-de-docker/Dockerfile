FROM liferay/liferay-de:20170203072957391859963-db
MAINTAINER Manuel de la Peña <manuel.delapenya@liferay.com>

COPY init.sh /
COPY deploy/*.?ar $LIFERAY_HOME/osgi/modules/
COPY libs/*.?ar $LIFERAY_HOME/osgi/modules/
COPY jolokia/jolokia-agent.properties /jolokia/
COPY jolokia/lib/jolokia-jvm-1.3.7-agent.jar /jolokia/jolokia-agent.jar
COPY supervisord-tomcat.conf /etc/supervisor/conf.d/supervisord-tomcat.conf
COPY TokenDownloader.py /
COPY portal-ext.properties /liferay/portal-ext.properties

USER root

RUN mkdir $LIFERAY_HOME/data

RUN chown -R liferay:liferay $LIFERAY_HOME
RUN chmod a+x /init.sh
RUN chmod a+x /TokenDownloader.py
RUN apt-get update && apt-get install -y python-pip
RUN pip install requests
RUN apt install -y -t jessie-backports openjdk-8-jre-headless ca-certificates-java

ENTRYPOINT /init.sh