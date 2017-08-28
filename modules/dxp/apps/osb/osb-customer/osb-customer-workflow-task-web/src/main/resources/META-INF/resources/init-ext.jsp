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

<%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.Role" %><%@
page import="com.liferay.portal.kernel.model.User" %><%@
page import="com.liferay.portal.kernel.search.Document" %><%@
page import="com.liferay.portal.kernel.search.Field" %><%@
page import="com.liferay.portal.kernel.search.BooleanQuery" %><%@
page import="com.liferay.portal.kernel.search.BooleanQueryFactoryUtil" %><%@
page import="com.liferay.portal.kernel.search.Hits" %><%@
page import="com.liferay.portal.kernel.search.IndexSearcherHelperUtil" %><%@
page import="com.liferay.portal.kernel.search.SearchContext" %><%@
page import="com.liferay.portal.kernel.search.SearchContextFactory" %><%@
page import="com.liferay.portal.kernel.search.SearchEngineUtil" %><%@
page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.UserLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowInstance" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowInstanceManagerUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowLogManagerUtil" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowTaskAssignee" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowTaskManagerUtil" %>

<%@ page import="java.io.Serializable" %>

<%@ page import="java.lang.String" %>

<%@ page import="java.util.HashMap" %><%@
page import="java.util.Map" %>