<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long categoryId = ParamUtil.getLong(request, "categoryId");
%>

<aui:model-context bean="<%= AssetCategoryLocalServiceUtil.fetchCategory(categoryId) %>" model="<%= AssetCategory.class %>" />

<aui:input autoFocus="<%= true %>" label="name" name="title" placeholder="name" />

<aui:input name="description" placeholder="description" />