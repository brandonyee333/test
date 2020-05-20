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
String callback = ParamUtil.getString(request, "callback");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/select_corp_project.jsp");
portletURL.setParameter("callback", callback);
%>

<aui:form action="<%= portletURL.toString() %>" method="post">
	<liferay-ui:tabs
		names="projects"
	/>

	<liferay-ui:search-container
		headerNames="name"
		searchContainer="<%= new CorpProjectSearch(renderRequest, portletURL) %>"
	>

		<%
		CorpProjectDisplayTerms displayTerms = (CorpProjectDisplayTerms)searchContainer.getDisplayTerms();
		CorpProjectSearchTerms searchTerms = (CorpProjectSearchTerms)searchContainer.getSearchTerms();

		searchContainer.setResults(CorpProjectLocalServiceUtil.getCorpProjects(searchTerms.getName(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()));
		searchContainer.setTotal(CorpProjectLocalServiceUtil.getCorpProjectsCount(searchTerms.getName()));
		%>

		<div class="search-form-container">
			<aui:input label="keywords" name="<%= displayTerms.NAME %>" size="20" value="<%= displayTerms.getName() %>" />

			<aui:button type="submit" value="search" />
		</div>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.CorpProject"
			escapedModel="<%= true %>"
			keyProperty="corpProjectId"
			modelVar="corpProject"
		>

			<%
			StringBundler sb = new StringBundler(8);

			sb.append("javascript:opener.");
			sb.append(renderResponse.getNamespace());
			sb.append(callback);
			sb.append("('");
			sb.append(corpProject.getUuid());
			sb.append("', '");
			sb.append(corpProject.getCorpProjectId());
			sb.append("', '");
			sb.append(UnicodeFormatter.toString(corpProject.getName()));
			sb.append("'); window.close();");
			%>

			<liferay-ui:search-container-column-text
				href="<%= sb.toString() %>"
				name="name"
				value="<%= corpProject.getName() %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>