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

<%@ page import="com.liferay.dynamic.data.mapping.model.DDMStructure" %><%@
page import="com.liferay.dynamic.data.mapping.service.DDMStructureServiceUtil" %><%@
page import="com.liferay.osb.customer.web.internal.constants.OSBJournalArticleConstants" %><%@
page import="com.liferay.osb.customer.web.internal.search.ArticleSearch" %><%@
page import="com.liferay.osb.customer.web.internal.search.ArticleSearchTerms" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.util.HttpUtil" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %>

<%
String[] searchStructureIds = portletPreferences.getValues("searchStructureIds", new String[0]);

boolean liferayOrg = RoleLocalServiceUtil.hasUserRole(user.getUserId(), user.getCompanyId(), "liferay employee", true);

NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
%>