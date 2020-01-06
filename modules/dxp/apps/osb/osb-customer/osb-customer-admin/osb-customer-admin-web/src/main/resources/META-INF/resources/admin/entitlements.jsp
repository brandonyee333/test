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