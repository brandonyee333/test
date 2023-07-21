<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-ui:search-container
	emptyResultsMessage="no-organizations-were-found"
>
	<liferay-ui:search-container-results>

		<%
		DynamicQuery dynamicQuery = OrganizationLocalServiceUtil.dynamicQuery();

		dynamicQuery.add(RestrictionsFactoryUtil.like("name", EntitlementConstants.ORGANIZATION_NAME_PREFIX + StringPool.PERCENT));

		searchContainer.setResults(OrganizationLocalServiceUtil.dynamicQuery(dynamicQuery, searchContainer.getStart(), searchContainer.getEnd()));
		searchContainer.setTotal((int)OrganizationLocalServiceUtil.dynamicQueryCount(dynamicQuery));
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.portal.kernel.model.Organization"
		escapedModel="<%= true %>"
		keyProperty="organizationId"
		modelVar="organization"
	>
		<liferay-ui:search-container-column-text
			property="name"
		/>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/admin/organization_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
	/>
</liferay-ui:search-container>