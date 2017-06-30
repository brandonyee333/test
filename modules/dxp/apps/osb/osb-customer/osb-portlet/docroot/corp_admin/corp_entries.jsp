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

<aui:form action="<%= portletURL %>" cssClass="jsp-corp-entries" method="get" name="fm">
	<liferay-portlet:renderURLParams varImpl="portletURL" />

	<aui:fieldset>
		<liferay-ui:search-container
			iteratorURL="<%= portletURL %>"
			searchContainer="<%= new CorpEntrySearch(renderRequest, portletURL) %>"
		>

			<%
			CorpEntrySearchTerms searchTerms = (CorpEntrySearchTerms)searchContainer.getSearchTerms();
			%>

			<liferay-ui:search-container-results
				results="<%= CorpEntryLocalServiceUtil.search(searchTerms.getName(), searchContainer.getStart(), searchContainer.getEnd(), searchContainer.getOrderByComparator()) %>"
				total="<%= CorpEntryLocalServiceUtil.searchCount(searchTerms.getName()) %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.model.CorpEntry"
				escapedModel="<%= true %>"
				keyProperty="corpEntryId"
				modelVar="corpEntry"
			>
				<liferay-portlet:renderURL var="rowURL">
					<portlet:param name="mvcPath" value="/corp_admin/edit_corp_entry.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="corpEntryId" value="<%= String.valueOf(corpEntry.getCorpEntryId()) %>" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					orderable="<%= true %>"
					property="name"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="relationship-types"
				>

					<%
					List<String> values = new ArrayList<String>();

					DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.fetchDeveloperEntry(corpEntry.getDossieraAccountKey());

					if (developerEntry != null) {
						values.add(LanguageUtil.get(pageContext, "marketplace-developer"));
					}
					%>

					<c:choose>
						<c:when test="<%= !values.isEmpty() %>">
							<%= StringUtil.merge(ListUtil.sort(values), StringPool.COMMA_AND_SPACE) %>
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="none" />
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="create-date"
					orderable="<%= true %>"
					value="<%= shortDateFormatDate.format(corpEntry.getCreateDate()) %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="status"
					orderable="<%= true %>"
					value="<%= LanguageUtil.get(pageContext, WorkflowConstants.toLabel(corpEntry.getStatus())) %>"
				/>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/corp_admin/corp_entry_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<%
			CorpEntryDisplayTerms displayTerms = (CorpEntryDisplayTerms)searchContainer.getDisplayTerms();
			%>

			<aui:input cssClass="search-bar" inlineField="<%= true %>" label="" name="<%= displayTerms.NAME %>" size="30" value="<%= displayTerms.getName() %>" />

			<aui:button type="submit" value="search" />

			<aui:button-row>
				<liferay-portlet:renderURL var="addCorpEntryURL">
					<portlet:param name="mvcPath" value="/corp_admin/add_corp_entry.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
				</liferay-portlet:renderURL>

				<aui:button href="<%= addCorpEntryURL %>" value="add-business-account" />
			</aui:button-row>

			<div class="separator"><!-- --></div>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</aui:fieldset>
</aui:form>