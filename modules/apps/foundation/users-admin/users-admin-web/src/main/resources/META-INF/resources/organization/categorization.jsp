<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long organizationId = ParamUtil.getLong(request, "organizationId");
%>

<aui:model-context bean="<%= OrganizationServiceUtil.fetchOrganization(organizationId) %>" model="<%= Organization.class %>" />

<liferay-ui:asset-categories-error />

<liferay-ui:asset-tags-error />

<aui:fieldset>
	<aui:input name="categories" type="assetCategories" />

	<aui:input name="tags" type="assetTags" />
</aui:fieldset>