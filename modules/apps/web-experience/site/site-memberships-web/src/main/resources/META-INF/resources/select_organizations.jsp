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

<%
String displayStyle = portalPreferences.getValue(SiteMembershipsPortletKeys.SITE_MEMBERSHIPS_ADMIN, "display-style", "icon");
String eventName = ParamUtil.getString(request, "eventName", liferayPortletResponse.getNamespace() + "selectOrganizations");
String orderByCol = ParamUtil.getString(request, "orderByCol", "name");
String orderByType = ParamUtil.getString(request, "orderByType", "asc");

PortletURL viewOrganizationsURL = renderResponse.createRenderURL();

viewOrganizationsURL.setParameter("mvcPath", "/select_organizations.jsp");
viewOrganizationsURL.setParameter("groupId", String.valueOf(siteMembershipsDisplayContext.getGroupId()));
viewOrganizationsURL.setParameter("eventName", eventName);

OrganizationSiteMembershipChecker rowChecker = new OrganizationSiteMembershipChecker(renderResponse, siteMembershipsDisplayContext.getGroup());

OrganizationSearch organizationSearch = new OrganizationSearch(renderRequest, PortletURLUtil.clone(viewOrganizationsURL, renderResponse));

OrganizationSearchTerms searchTerms = (OrganizationSearchTerms)organizationSearch.getSearchTerms();

LinkedHashMap<String, Object> organizationParams = new LinkedHashMap<String, Object>();

List<Organization> organizations = null;
int organizationsCount = 0;

Indexer<?> indexer = IndexerRegistryUtil.nullSafeGetIndexer(Organization.class);

if (indexer.isIndexerEnabled() && PropsValues.ORGANIZATIONS_SEARCH_WITH_INDEX) {
	organizationParams.put("expandoAttributes", searchTerms.getKeywords());

	BaseModelSearchResult<Organization> baseModelSearchResult = OrganizationLocalServiceUtil.searchOrganizations(themeDisplay.getCompanyId(), OrganizationConstants.ANY_PARENT_ORGANIZATION_ID, searchTerms.getKeywords(), organizationParams, organizationSearch.getStart(), organizationSearch.getEnd(), SortFactoryUtil.getSort(Organization.class, organizationSearch.getOrderByCol(), organizationSearch.getOrderByType()));

	organizations = baseModelSearchResult.getBaseModels();
	organizationsCount = baseModelSearchResult.getLength();
}
else {
	organizations = OrganizationLocalServiceUtil.search(themeDisplay.getCompanyId(), OrganizationConstants.ANY_PARENT_ORGANIZATION_ID, searchTerms.getKeywords(), searchTerms.getType(), searchTerms.getRegionIdObj(), searchTerms.getCountryIdObj(), organizationParams, organizationSearch.getStart(), organizationSearch.getEnd(), organizationSearch.getOrderByComparator());
	organizationsCount = OrganizationLocalServiceUtil.searchCount(themeDisplay.getCompanyId(), OrganizationConstants.ANY_PARENT_ORGANIZATION_ID, searchTerms.getKeywords(), searchTerms.getType(), searchTerms.getRegionIdObj(), searchTerms.getCountryIdObj(), organizationParams);
}

organizationSearch.setResults(organizations);
organizationSearch.setTotal(organizationsCount);
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="organizations" selected="<%= true %>" />
	</aui:nav>

	<c:if test="<%= (organizationsCount > 0) || searchTerms.isSearch() %>">
		<aui:nav-bar-search>
			<aui:form action="<%= viewOrganizationsURL %>" name="searchFm">
				<liferay-ui:input-search
					autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) %>"
					markupView="lexicon"
				/>
			</aui:form>
		</aui:nav-bar-search>
	</c:if>
</aui:nav-bar>

<liferay-frontend:management-bar
	disabled="<%= organizationsCount <= 0 %>"
	includeCheckBox="<%= true %>"
	searchContainerId="organizations"
>
	<liferay-frontend:management-bar-buttons>
		<liferay-portlet:actionURL name="changeDisplayStyle" varImpl="changeDisplayStyleURL">
			<portlet:param name="mvcPath" value="/select_organizations.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</liferay-portlet:actionURL>

		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"icon", "descriptive", "list"} %>'
			portletURL="<%= changeDisplayStyleURL %>"
			selectedDisplayStyle="<%= displayStyle %>"
		/>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-navigation
			navigationKeys='<%= new String[] {"all"} %>'
			portletURL="<%= PortletURLUtil.clone(viewOrganizationsURL, renderResponse) %>"
		/>

		<liferay-frontend:management-bar-sort
			orderByCol="<%= orderByCol %>"
			orderByType="<%= orderByType %>"
			orderColumns='<%= new String[] {"name", "type"} %>'
			portletURL="<%= PortletURLUtil.clone(viewOrganizationsURL, renderResponse) %>"
		/>
	</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>

<aui:form cssClass="container-fluid-1280" name="fm">
	<liferay-ui:search-container
		id="organizations"
		rowChecker="<%= rowChecker %>"
		searchContainer="<%= organizationSearch %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.Organization"
			escapedModel="<%= true %>"
			keyProperty="organizationId"
			modelVar="organization"
		>

			<%
			boolean selectOrganizations = true;
			%>

			<%@ include file="/organization_columns.jspf" %>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= displayStyle %>"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<aui:script use="liferay-search-container">
	var searchContainer = Liferay.SearchContainer.get('<portlet:namespace />organizations');

	searchContainer.on(
		'rowToggled',
		function(event) {
			Liferay.Util.getOpener().Liferay.fire(
				'<%= HtmlUtil.escapeJS(eventName) %>',
				{
					data: event.elements.allSelectedElements.getDOMNodes()
				}
			);
		}
	);
</aui:script>