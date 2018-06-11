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

<%@ include file="/init.jsp" %>

<%@ page import="com.liferay.dynamic.data.mapping.service.DDMStructureServiceUtil" %><%@
page import="com.liferay.osb.customer.web.internal.constants.OSBJournalArticleConstants" %><%@
page import="com.liferay.osb.customer.web.internal.search.ArticleSearch" %><%@
page import="com.liferay.osb.customer.web.internal.search.ArticleSearchTerms" %><%@
page import="com.liferay.portal.kernel.service.RoleLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.util.HttpUtil" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %>

<%
String[] searchStructureIds = portletPreferences.getValues("searchStructureIds", new String[0]);

boolean liferayOrg = RoleLocalServiceUtil.hasUserRole(user.getUserId(), user.getCompanyId(), "liferay employee", true);
%>