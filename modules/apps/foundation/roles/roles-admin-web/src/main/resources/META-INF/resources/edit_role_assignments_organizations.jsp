<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String tabs3 = (String)request.getAttribute("edit_role_assignments.jsp-tabs3");

Role role = (Role)request.getAttribute("edit_role_assignments.jsp-role");

String displayStyle = (String)request.getAttribute("edit_role_assignments.jsp-displayStyle");

PortletURL portletURL = (PortletURL)request.getAttribute("edit_role_assignments.jsp-portletURL");

EmptyOnClickRowChecker rowChecker = new EmptyOnClickRowChecker(renderResponse);

if (tabs3.equals("available")) {
	rowChecker = new OrganizationRoleChecker(renderResponse, role);
}
%>

<liferay-ui:search-container
	id="assigneesSearch"
	rowChecker="<%= rowChecker %>"
	searchContainer="<%= new OrganizationSearch(renderRequest, portletURL) %>"
	var="organizationSearchContainer"
>

	<%
	OrganizationSearchTerms searchTerms = (OrganizationSearchTerms)organizationSearchContainer.getSearchTerms();

	if (!searchTerms.isSearch()) {
		organizationSearchContainer.setEmptyResultsMessageCssClass("taglib-empty-result-message-header-has-plus-btn");
	}

	long parentOrganizationId = OrganizationConstants.ANY_PARENT_ORGANIZATION_ID;

	LinkedHashMap<String, Object> organizationParams = new LinkedHashMap<String, Object>();

	if (tabs3.equals("current")) {
		organizationParams.put("organizationsRoles", Long.valueOf(role.getRoleId()));
	}
	%>

	<liferay-ui:organization-search-container-results
		forceDatabase="<%= true %>"
		organizationParams="<%= organizationParams %>"
		parentOrganizationId="<%= parentOrganizationId %>"
	/>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.model.Organization"
		escapedModel="<%= true %>"
		keyProperty="group.groupId"
		modelVar="organization"
	>
		<%@ include file="/organization_columns.jspf" %>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		displayStyle="<%= displayStyle %>"
		markupView="lexicon"
	/>
</liferay-ui:search-container>