<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.journal.model.JournalArticle" %><%@
page import="com.liferay.journal.model.JournalArticleDisplay" %><%@
page import="com.liferay.journal.service.JournalArticleLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.release.tool.web.internal.display.context.ReleaseToolDisplayContext" %><%@
page import="com.liferay.osb.customer.release.tool.web.internal.search.FixPackSearcher" %><%@
page import="com.liferay.osb.customer.release.tool.web.internal.search.JiraIssueSearcher" %><%@
page import="com.liferay.portal.kernel.json.JSONObject" %><%@
page import="com.liferay.portal.kernel.portlet.PortletRequestModel" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
ReleaseToolDisplayContext releaseToolDisplayContext = new ReleaseToolDisplayContext(renderRequest, renderResponse);

FixPackSearcher fixPackSearcher = (FixPackSearcher)renderRequest.getAttribute(FixPackSearcher.class.getName());
JiraIssueSearcher jiraIssueSearcher = (JiraIssueSearcher)renderRequest.getAttribute(JiraIssueSearcher.class.getName());

String changelogJournalArticleId = portletPreferences.getValue("changelogJournalArticleId", null);
String highlightsJournalArticleId = portletPreferences.getValue("highlightsJournalArticleId", null);
String moduleChangesJournalArticleId = portletPreferences.getValue("moduleChangesJournalArticleId", null);
%>

<aui:script>
	window.ReleaseToolConstants = {
		namespace: '${renderResponse.namespace}'
	};
</aui:script>