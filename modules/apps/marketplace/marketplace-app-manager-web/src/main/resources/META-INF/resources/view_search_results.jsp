<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String keywords = ParamUtil.getString(request, "keywords");

String category = ParamUtil.getString(request, "category", "all-categories");
String state = ParamUtil.getString(request, "state", "all-statuses");

String orderByType = ParamUtil.getString(request, "orderByType", "asc");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/view_search_results.jsp");
portletURL.setParameter("category", category);
portletURL.setParameter("state", state);
portletURL.setParameter("orderByType", orderByType);

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "app-manager"), String.valueOf(renderResponse.createRenderURL()));
PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "search-results"), null);
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<portlet:renderURL var="viewURL" />

		<aui:nav-item href="<%= viewURL %>" label="search" selected="<%= true %>" />
	</aui:nav>

	<aui:nav-bar-search>
		<liferay-portlet:renderURL varImpl="searchURL">
			<portlet:param name="mvcPath" value="/view_search_results.jsp" />
		</liferay-portlet:renderURL>

		<aui:form action="<%= searchURL %>" method="get" name="fm1">
			<liferay-portlet:renderURLParams varImpl="searchURL" />

			<liferay-ui:input-search
				markupView="lexicon"
			/>
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<liferay-frontend:management-bar
	searchContainerId="appDisplays"
>
	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"descriptive"} %>'
			portletURL="<%= PortletURLUtil.clone(portletURL, liferayPortletResponse) %>"
			selectedDisplayStyle="descriptive"
		/>
	</liferay-frontend:management-bar-buttons>

	<liferay-frontend:management-bar-filters>
		<liferay-frontend:management-bar-sort
			orderByCol="title"
			orderByType="<%= orderByType %>"
			orderColumns='<%= new String[] {"title"} %>'
			portletURL="<%= PortletURLUtil.clone(portletURL, liferayPortletResponse) %>"
		/>
	</liferay-frontend:management-bar-filters>
</liferay-frontend:management-bar>

<div class="container-fluid-1280">
	<liferay-ui:breadcrumb
		showCurrentGroup="<%= false %>"
		showGuestGroup="<%= false %>"
		showLayout="<%= false %>"
		showParentGroups="<%= false %>"
	/>

	<liferay-ui:search-container
		emptyResultsMessage="no-results-were-found"
		id="appDisplays"
		iteratorURL="<%= portletURL %>"
	>
		<liferay-ui:search-container-results>

			<%
			List<Bundle> bundles = BundleManagerUtil.getBundles();

			results = MarketplaceAppManagerSearchUtil.getResults(bundles, keywords);

			results = ListUtil.sort(results, new MarketplaceAppManagerComparator(orderByType));

			int end = searchContainer.getEnd();

			if (end > results.size()) {
				end = results.size();
			}

			searchContainer.setResults(results.subList(searchContainer.getStart(), end));

			searchContainer.setTotal(results.size());
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="Object"
			modelVar="result"
		>
			<c:choose>
				<c:when test="<%= result instanceof AppDisplay %>">

					<%
					AppDisplay appDisplay = (AppDisplay)result;
					%>

					<%@ include file="/app_display_columns.jspf" %>
				</c:when>
				<c:when test="<%= result instanceof ModuleGroupDisplay %>">

					<%
					ModuleGroupDisplay moduleGroupDisplay = (ModuleGroupDisplay)result;
					%>

					<%@ include file="/module_group_display_columns.jspf" %>
				</c:when>
				<c:when test="<%= result instanceof Bundle %>">

					<%
					Bundle bundle = (Bundle)result;

					String app = StringPool.BLANK;
					String moduleGroup = StringPool.BLANK;
					%>

					<%@ include file="/bundle_columns.jspf" %>
				</c:when>
			</c:choose>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="descriptive"
			markupView="lexicon"
			resultRowSplitter="<%= new MarketplaceAppManagerResultRowSplitter() %>"
		/>
	</liferay-ui:search-container>
</div>