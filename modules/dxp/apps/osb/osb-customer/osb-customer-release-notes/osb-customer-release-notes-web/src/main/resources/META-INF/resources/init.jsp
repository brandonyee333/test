<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.osb.customer.release.notes.jira.configuration.ReleaseNotesConfigurationValues" %><%@
page import="com.liferay.osb.customer.release.notes.jira.exception.DuplicateJIRAIssueKeysException" %><%@
page import="com.liferay.osb.customer.release.notes.jira.exception.NoSuchJIRAProjectException" %><%@
page import="com.liferay.osb.customer.release.notes.jira.exception.RequiredJIRAIssueKeysException" %><%@
page import="com.liferay.osb.customer.release.notes.jira.exception.RequiredNameException" %><%@
page import="com.liferay.osb.customer.release.notes.jira.model.JIRAProject" %><%@
page import="com.liferay.osb.customer.release.notes.jira.model.JIRAProjectVersion" %><%@
page import="com.liferay.osb.customer.release.notes.jira.service.JIRAProjectLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.release.notes.jira.service.JIRAProjectVersionLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.release.notes.jira.util.ReleaseNotesUtil" %><%@
page import="com.liferay.osb.customer.release.notes.model.ReleaseNotes" %><%@
page import="com.liferay.osb.customer.release.notes.service.ReleaseNotesLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.release.notes.web.search.ReleaseNotesDisplayTerms" %><%@
page import="com.liferay.osb.customer.release.notes.web.search.ReleaseNotesSearch" %><%@
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

<portlet:defineObjects />

<%
String currentURL = PortalUtil.getCurrentURL(request);

String jiraProjectKey = portletPreferences.getValue("jiraProjectKey", "LPE");
String jiraProjectVersionNamePrefix = portletPreferences.getValue("jiraProjectVersionNamePrefix", StringPool.BLANK);

DateFormat dateFormatDateTime = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
%>