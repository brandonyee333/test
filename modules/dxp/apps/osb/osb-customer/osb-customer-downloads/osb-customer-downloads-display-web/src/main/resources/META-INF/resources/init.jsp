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

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.dynamic.data.mapping.storage.Field" %><%@
page import="com.liferay.dynamic.data.mapping.storage.Fields" %><%@
page import="com.liferay.journal.model.JournalArticle" %><%@
page import="com.liferay.journal.model.JournalArticleDisplay" %><%@
page import="com.liferay.journal.service.JournalArticleLocalServiceUtil" %><%@
page import="com.liferay.journal.util.JournalConverter" %><%@
page import="com.liferay.osb.customer.downloads.display.web.internal.constants.DownloadsDisplayWebKeys" %><%@
page import="com.liferay.osb.customer.downloads.display.web.internal.display.context.DownloadsDisplayContext" %><%@
page import="com.liferay.portal.kernel.portlet.PortletRequestModel" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
DownloadsDisplayContext downloadsDisplayContext = new DownloadsDisplayContext(renderRequest, renderResponse);

JournalConverter journalConverter = (JournalConverter)renderRequest.getAttribute(JournalConverter.class.getName());
%>