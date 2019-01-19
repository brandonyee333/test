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

<%@ page import="com.liferay.dynamic.data.mapping.model.DDMFormFieldType" %><%@
page import="com.liferay.dynamic.data.mapping.model.Value" %><%@
page import="com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue" %><%@
page import="com.liferay.dynamic.data.mapping.storage.DDMFormValues" %><%@
page import="com.liferay.dynamic.data.mapping.storage.Fields" %><%@
page import="com.liferay.journal.model.JournalArticle" %><%@
page import="com.liferay.journal.util.JournalConverter" %><%@
page import="com.liferay.osb.customer.downloads.display.web.internal.constants.DDMStructureConstants" %><%@
page import="com.liferay.osb.customer.downloads.display.web.internal.constants.DownloadsDisplayWebKeys" %><%@
page import="com.liferay.osb.customer.downloads.display.web.internal.display.context.DownloadsDisplayContext" %><%@
page import="com.liferay.osb.customer.downloads.display.web.internal.util.DDMFieldsUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.json.JSONArray" %><%@
page import="com.liferay.portal.kernel.json.JSONFactoryUtil" %><%@
page import="com.liferay.portal.kernel.json.JSONObject" %><%@
page import="com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.DateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortletKeys" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %>

<%@ page import="java.text.DateFormat" %><%@
page import="java.text.Format" %>

<%@ page import="java.util.Date" %><%@
page import="java.util.List" %><%@
page import="java.util.Locale" %><%@
page import="java.util.Map" %>

<%@ page import="javax.portlet.PortletPreferences" %><%@
page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
Format mediumDateFormatDate = FastDateFormatFactoryUtil.getDate(DateFormat.MEDIUM, LocaleUtil.US, timeZone);

DownloadsDisplayContext downloadsDisplayContext = new DownloadsDisplayContext(renderRequest, renderResponse);

JournalConverter journalConverter = (JournalConverter)renderRequest.getAttribute(JournalConverter.class.getName());
%>

<aui:script>
	window.DownloadsConstants = {
		namespace: '${renderResponse.namespace}'
	};
</aui:script>