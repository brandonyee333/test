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

<%@ include file="/corp_project_admin/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/corp_project_admin/view.jsp");
%>

<div class="corp-project-admin">
	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-projects"
		iteratorURL="<%= portletURL %>"
		searchContainer="<%= new CorpProjectSearch(renderRequest, portletURL) %>"
	>

		<%
		CorpProjectDisplayTerms displayTerms = (CorpProjectDisplayTerms)searchContainer.getDisplayTerms();
		CorpProjectSearchTerms searchTerms = (CorpProjectSearchTerms)searchContainer.getSearchTerms();
		%>

		<div class="search-form-container">
			<liferay-portlet:renderURL varImpl="searchURL">
				<portlet:param name="mvcPath" value="/corp_project_admin/view.jsp" />
			</liferay-portlet:renderURL>

			<aui:form action="<%= searchURL.toString() %>" method="get" name="fm">
				<liferay-portlet:renderURLParams varImpl="searchURL" />

				<aui:input label="keywords" name="<%= displayTerms.NAME %>" size="20" value="<%= displayTerms.getName() %>" />

				<aui:button type="submit" value="search" />
			</aui:form>
		</div>

		<div class="separator"><!-- --></div>

		<aui:button-row>
			<liferay-portlet:renderURL var="addCorpProjectURL">
				<portlet:param name="mvcPath" value="/corp_project_admin/edit_corp_project.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
			</liferay-portlet:renderURL>

			<aui:button href="<%= addCorpProjectURL %>" value="add-project" />
		</aui:button-row>

		<liferay-ui:search-container-results
			results="<%= CorpProjectLocalServiceUtil.getCorpProjects(searchTerms.getName(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
			total="<%= CorpProjectLocalServiceUtil.getCorpProjectsCount(searchTerms.getName()) %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.CorpProject"
			escapedModel="<%= true %>"
			keyProperty="corpProjectId"
			modelVar="corpProject"
		>
			<liferay-portlet:renderURL var="viewCorpProjectURL">
				<portlet:param name="mvcPath" value="/corp_project_admin/view_corp_project.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="corpProjectId" value="<%= String.valueOf(corpProject.getCorpProjectId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= viewCorpProjectURL %>"
				name="project-id"
				value="<%= String.valueOf(corpProject.getCorpProjectId()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= viewCorpProjectURL %>"
				name="project-name"
				value="<%= corpProject.getName() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= viewCorpProjectURL %>"
				name="create-date"
				value="<%= shortDateFormatDate.format(corpProject.getCreateDate()) %>"
			/>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/corp_project_admin/corp_project_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>