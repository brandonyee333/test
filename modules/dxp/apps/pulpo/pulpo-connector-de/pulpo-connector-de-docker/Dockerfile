FROM liferay/liferay-de:20170203072957391859963-db

RUN rm -f $LIFERAY_HOME/deploy/*.* \
	rm -f $LIFERAY_HOME/osgi/*/1975-london-theme* \
	rm -f $LIFERAY_HOME/osgi/*/fjord-theme* \
	rm -f $LIFERAY_HOME/osgi/*/porygon-theme* \
	rm -f $LIFERAY_HOME/osgi/*/westeros-bank-theme* \

	rm -f $LIFERAY_HOME/osgi/*/opensocial-portlet* \
	rm -f $LIFERAY_HOME/osgi/*/documentum-hook* \
	rm -f $LIFERAY_HOME/osgi/*/sharepoint-hook* \

	rm -f $LIFERAY_HOME/osgi/*/com.liferay.amazon.rankings* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.announcementss* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.asset* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.blogs* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.bookmarks* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.calendar* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.commerce* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.chat* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.comment* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.contacts* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.dictionary* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.dynamic.data.lists* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.document.library.cmis* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.document.library.google* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.document.library.item.selector* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.events* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.exportimport.resources.importer* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.flags* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.frontend.image.editor* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.invitation* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.knowledge.base* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.message.boards* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.mail* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.marketplace* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.mentions* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.microblogs* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.my.subscriptions* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.portal.mobile.device* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.portal.scripting* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.portal.workflow* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.polls* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.push* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.ratings* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.reports* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.screens* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.sync* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.shopping* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.social* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.twitter* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.trash.web* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.translator* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.unit.converter* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.weather* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.web.form* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.web.proxy* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.websocket* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.wiki* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.wsrp* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.wysiwyg* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.youtube* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.document.library.web* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.dynamic.data.mapping.type.* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.dynamic.data.mapping.web* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.google.maps.web* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.google.maps.web* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.map.common* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.map.openstreetmap* \
	rm -f $LIFERAY_HOME/osgi/*/com.liferay.product.navigation.simulation.device*

COPY init.sh /
COPY deploy/*.?ar $LIFERAY_HOME/osgi/modules/
COPY libs/*.?ar $LIFERAY_HOME/osgi/modules/
COPY log4j/*.xml $LIFERAY_HOME/osgi/log4j/
COPY jolokia/jolokia-agent.properties /jolokia/
COPY jolokia/lib/jolokia-agent.jar /jolokia/jolokia-agent.jar
COPY supervisord-tomcat.conf /etc/supervisor/conf.d/supervisord-tomcat.conf
COPY TokenDownloader.py /
COPY portal-ext.properties /liferay/portal-ext.properties

USER root

RUN mkdir $LIFERAY_HOME/data

RUN chmod a+x /init.sh
RUN chmod a+x /TokenDownloader.py
RUN apt-get update && apt-get install -y python-pip
RUN pip install requests
RUN apt install -y -t jessie-backports openjdk-8-jre-headless ca-certificates-java

ENTRYPOINT /init.sh

EXPOSE 8080
EXPOSE 8099
EXPOSE 8778
EXPOSE 11311
EXPOSE 5005