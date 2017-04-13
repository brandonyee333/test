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
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil" %><%@
page import="com.liferay.asset.kernel.model.AssetCategory" %><%@
page import="com.liferay.asset.kernel.model.AssetRenderer" %><%@
page import="com.liferay.asset.kernel.model.AssetRendererFactory" %><%@
page import="com.liferay.asset.kernel.model.AssetVocabulary" %><%@
page import="com.liferay.asset.kernel.service.AssetCategoryLocalServiceUtil" %><%@
page import="com.liferay.asset.kernel.service.AssetCategoryServiceUtil" %><%@
page import="com.liferay.asset.kernel.service.AssetVocabularyLocalServiceUtil" %><%@
page import="com.liferay.asset.kernel.service.AssetVocabularyServiceUtil" %><%@
page import="com.liferay.journal.model.JournalArticle" %><%@
page import="com.liferay.journal.service.JournalArticleLocalServiceUtil" %><%@
page import="com.liferay.knowledge.base.constants.KBPortletKeys" %><%@
page import="com.liferay.knowledge.base.model.KBArticle" %><%@
page import="com.liferay.knowledge.base.service.KBArticleLocalServiceUtil" %><%@
page import="com.liferay.osb.customer.web.internal.asset.model.AssetCategoryDisplay" %><%@
page import="com.liferay.osb.customer.web.internal.constants.OSBCustomerConstants" %><%@
page import="com.liferay.osb.customer.web.internal.constants.OSBCustomerPortletKeys" %><%@
page import="com.liferay.osb.customer.web.internal.constants.OSBCustomerWebKeys" %><%@
page import="com.liferay.osb.customer.web.internal.search.ArticleSearchUtil" %><%@
page import="com.liferay.portal.kernel.dao.orm.QueryUtil" %><%@
page import="com.liferay.portal.kernel.log.Log" %><%@
page import="com.liferay.portal.kernel.log.LogFactoryUtil" %><%@
page import="com.liferay.portal.kernel.portlet.PortletURLFactoryUtil" %><%@
page import="com.liferay.portal.kernel.search.Document" %><%@
page import="com.liferay.portal.kernel.search.Field" %><%@
page import="com.liferay.portal.kernel.search.Hits" %><%@
page import="com.liferay.portal.kernel.search.SearchContext" %><%@
page import="com.liferay.portal.kernel.search.SearchContextFactory" %><%@
page import="com.liferay.portal.kernel.util.ArrayUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.StringPool" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %>

<%@ page import="java.text.NumberFormat" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.HashSet" %><%@
page import="java.util.List" %><%@
page import="java.util.Locale" %>

<%@ page import="javax.portlet.PortletRequest" %><%@
page import="javax.portlet.PortletURL" %><%@
page import="javax.portlet.WindowState" %>

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String currentURL = PortalUtil.getCurrentURL(request);
%>