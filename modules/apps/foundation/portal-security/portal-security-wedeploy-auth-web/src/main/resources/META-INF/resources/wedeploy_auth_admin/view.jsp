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
SearchContainer<WeDeployAuthApp> weDeployAuthAppEntriesSearchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, currentURLObj, null, "no-entries-were-found");

weDeployAuthAppEntriesSearchContainer.setResults(WeDeployAuthAppLocalServiceUtil.getEntries());

List<WeDeployAuthApp> weDeployAuthAppEntries = weDeployAuthAppEntriesSearchContainer.getResults();
%>

<aui:nav-bar markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<portlet:renderURL var="weDeployAuthAppEntriesURL" />

		<aui:nav-item
			href="<%= weDeployAuthAppEntriesURL %>"
			label="WeDeploy Auth App Entries"
		/>
	</aui:nav>
</aui:nav-bar>

<div class="container-fluid-1280">
	<aui:form action="<%= currentURL %>" method="get" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

		<liferay-ui:search-container
			id="weDeployAuthAppEntries"
			searchContainer="<%= weDeployAuthAppEntriesSearchContainer %>"
		>
			<liferay-ui:search-container-results
				results="<%= weDeployAuthAppEntriesSearchContainer.getResults() %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthApp"
				keyProperty="weDeployAuthAppId"
				modelVar="weDeployAuthApp"
			>
				<liferay-ui:search-container-column-text
					name="name"
					value="<%= weDeployAuthApp.getName() %>"
				/>

				<liferay-ui:search-container-column-text
					name="client-id"
					value="<%= weDeployAuthApp.getClientId() %>"
				/>

				<liferay-ui:search-container-column-text
					name="client-secret"
					value="<%= weDeployAuthApp.getClientSecret() %>"
				/>

				<liferay-ui:search-container-column-date
					name="create-date"
					value="<%= weDeployAuthApp.getCreateDate() %>"
				/>

				<liferay-ui:search-container-column-date
					name="modified-date"
					value="<%= weDeployAuthApp.getModifiedDate() %>"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator markupView="lexicon" searchContainer="<%= weDeployAuthAppEntriesSearchContainer %>" />
		</liferay-ui:search-container>
	</aui:form>
</div>