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
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.osb.customer.release.notes.jira.exception.DuplicateJIRAIssueKeysException" %><%@
page import="com.liferay.osb.customer.release.notes.jira.exception.NoSuchJIRAProjectException" %><%@
page import="com.liferay.osb.customer.release.notes.jira.exception.RequiredJIRAIssueKeysException" %><%@
page import="com.liferay.osb.customer.release.notes.jira.exception.RequiredNameException" %><%@
page import="com.liferay.osb.customer.release.notes.jira.model.JIRAProject" %><%@
page import="com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion" %><%@
page import="com.liferay.osb.customer.release.notes.jira.service.JIRAProjectLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.release.notes.jira.service.JIRAProjectVersionLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.release.notes.model.ReleaseNotes" %><%@
page import="com.liferay.osb.customer.release.notes.service.ReleaseNotesLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.release.notes.web.internal.configuration.ReleaseNotesConfigurationValues" %><%@
page import="com.liferay.osb.customer.release.notes.web.internal.search.ReleaseNotesDisplayTerms" %><%@
page import="com.liferay.osb.customer.release.notes.web.internal.search.ReleaseNotesSearch" %><%@
page import="com.liferay.osb.customer.release.notes.web.internal.util.ReleaseNotesUtil" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.model.ModelHintsConstants" %><%@
page import="com.liferay.portal.kernel.util.ArrayUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %>

<%@ page import="java.text.DateFormat" %>

<%@ page import="java.util.List" %>

<%@ page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.ResourceURL" %><%@
page import="javax.portlet.WindowState" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String currentURL = PortalUtil.getCurrentURL(request);

String jiraProjectKey = portletPreferences.getValue("jiraProjectKey", "LPE");
String jiraProjectVersionNamePrefix = portletPreferences.getValue("jiraProjectVersionNamePrefix", StringPool.BLANK);

DateFormat dateFormatDateTime = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
%>