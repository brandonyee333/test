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

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/view");
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item href="<%= portletURL.toString() %>" label="open-source-projects" selected="<%= true %>" />
	</aui:nav>
</aui:nav-bar>

<div class="container-fluid-1280 main-content-body">
	<liferay-ui:search-container
		emptyResultsMessage="no-open-source-projects-were-found"
		id="docProjects"
		iteratorURL="<%= portletURL %>"
	>
		<liferay-ui:search-container-results>

			<%
			total = DocProjectLocalServiceUtil.getDocProjectsCount();

			searchContainer.setTotal(total);

			results = DocProjectLocalServiceUtil.getDocProjects(searchContainer.getStart(), searchContainer.getEnd());

			searchContainer.setResults(results);
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.osb.community.doc.project.model.DocProject"
			keyProperty="docProjectId"
			modelVar="docProject"
		>
			<liferay-portlet:renderURL varImpl="rowURL">
				<portlet:param name="mvcRenderCommandName" value="/edit_doc_project" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="docProjectId" value="<%= String.valueOf(docProject.getDocProjectId()) %>" />
			</liferay-portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="name"
				orderable="<%= false %>"
				property="name"
			/>

			<liferay-ui:search-container-column-text
				name="creator"
				orderable="<%= false %>"
				property="userName"
			/>

			<liferay-ui:search-container-column-date
				name="create-date"
				orderable="<%= false %>"
				property="createDate"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="list"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>

	<portlet:renderURL var="addDocProjectURL">
		<portlet:param name="mvcRenderCommandName" value="/edit_doc_project" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
	</portlet:renderURL>

	<liferay-frontend:add-menu>
		<liferay-frontend:add-menu-item
			title='<%= LanguageUtil.get(request, "add-open-source-project") %>'
			url="<%= addDocProjectURL %>"
		/>
	</liferay-frontend:add-menu>
</div>