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

<%@ include file="/init.jsp" %>

<%@ page import="com.liferay.journal.constants.JournalPortletKeys" %><%@
page import="com.liferay.osb.customer.web.internal.search.WorkflowTaskDisplayTerms" %><%@
page import="com.liferay.osb.customer.web.internal.search.WorkflowTaskSearch" %><%@
page import="com.liferay.osb.customer.web.internal.util.OSBCustomerWorkFlowTaskUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayPortletURL" %><%@
page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowHandler" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowInstance" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowLog" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowLogManagerUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowTask" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil" %><%@
page import="com.liferay.portal.kernel.workflow.comparator.WorkflowComparatorFactoryUtil" %>

<%@ page import="java.io.Serializable" %>

<%@ page import="java.text.DateFormat" %><%@
page import="java.text.Format" %>

<%
Format shortDateFormatDate = FastDateFormatFactoryUtil.getDate(DateFormat.SHORT, locale, timeZone);
%>