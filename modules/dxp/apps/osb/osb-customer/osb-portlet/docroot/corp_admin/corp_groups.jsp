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

<%@ include file="/corp_admin/init.jsp" %>

<%
PortletURL portletURL = (PortletURL)request.getAttribute("view.jsp-portletURL");

pageContext.setAttribute("portletURL", portletURL);
%>

<aui:form action="<%= portletURL %>" cssClass="jsp-corp-groups" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="portletURL" />

	<aui:fieldset>
		<liferay-ui:search-container
			iteratorURL="<%= portletURL %>"
			searchContainer="<%= new CorpGroupSearch(renderRequest, portletURL) %>"
		>

			<%
			CorpGroupSearchTerms searchTerms = (CorpGroupSearchTerms)searchContainer.getSearchTerms();
			%>

			<liferay-ui:search-container-results
				results="<%= CorpGroupLocalServiceUtil.search(searchTerms.getName(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
				total="<%= CorpGroupLocalServiceUtil.searchCount(searchTerms.getName()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.model.CorpGroup"
				escapedModel="<%= true %>"
				keyProperty="corpGroupId"
				modelVar="corpGroup"
			>
				<liferay-portlet:renderURL var="rowURL">
					<portlet:param name="mvcPath" value="/corp_admin/edit_corp_group.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="corpGroupId" value="<%= String.valueOf(corpGroup.getCorpGroupId()) %>" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					orderable="<%= true %>"
					property="name"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="create-date"
					orderable="<%= true %>"
					value="<%= shortDateFormatDate.format(corpGroup.getCreateDate()) %>"
				/>
			</liferay-ui:search-container-row>

			<%
			CorpGroupDisplayTerms displayTerms = (CorpGroupDisplayTerms)searchContainer.getDisplayTerms();
			%>

			<aui:input cssClass="search-bar" inlineField="<%= true %>" label="" name="<%= displayTerms.NAME %>" size="30" value="<%= displayTerms.getName() %>" />

			<aui:button type="submit" value="search" />

			<aui:button-row>
				<liferay-portlet:renderURL var="addCorpGroupURL">
					<portlet:param name="mvcPath" value="/corp_admin/edit_corp_group.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</liferay-portlet:renderURL>

				<aui:button href="<%= addCorpGroupURL %>" value="add-business-group" />
			</aui:button-row>

			<div class="separator"><!-- --></div>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>