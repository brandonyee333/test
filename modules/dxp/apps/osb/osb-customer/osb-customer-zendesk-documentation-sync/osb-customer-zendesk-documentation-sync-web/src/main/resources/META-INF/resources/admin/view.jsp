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
%>

<aui:nav-bar markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="zendesk-categories" selected="<%= true %>" />
	</aui:nav>
</aui:nav-bar>

<div class="container-fluid-1280">
	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-zendesk-categories"
		headerNames="documentation-guide-zip-file,zendesk-id"
		iteratorURL="<%= portletURL %>"
	>

		<%
		searchContainer.setResults(ZendeskCategoryLocalServiceUtil.getZendeskCategories(searchContainer.getStart(), searchContainer.getEnd()));
		searchContainer.setTotal(ZendeskCategoryLocalServiceUtil.getZendeskCategoriesCount());
		%>

		<liferay-ui:search-container-row
			className="com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory"
			escapedModel="<%= true %>"
			keyProperty="zendeskCategoryId"
			modelVar="zendeskCategory"
		>
			<portlet:renderURL var="rowURL">
				<portlet:param name="mvcPath" value="/admin/edit_zendesk_category.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="zendeskCategoryId" value="<%= String.valueOf(zendeskCategory.getZendeskCategoryId()) %>" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="documentation-guide-zip-file"
				property="documentationKey"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="zendesk-id"
				property="remoteId"
			/>

			<liferay-ui:search-container-column-text
				align="right"
			>
				<liferay-ui:icon-menu>
					<portlet:renderURL var="documentationImportURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
						<portlet:param name="mvcPath" value="/admin/documentation_import.jsp" />
						<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
						<portlet:param name="zendeskCategoryId" value="<%= String.valueOf(zendeskCategory.getZendeskCategoryId()) %>" />
					</portlet:renderURL>

					<liferay-ui:icon
						message="import-documentation-guide"
						url="<%= documentationImportURL %>"
					/>

					<c:if test="<%= ZendeskSectionLocalServiceUtil.getZendeskSectionsCount(zendeskCategory.getZendeskCategoryId()) <= 0 %>">
						<portlet:actionURL name="deleteZendeskCategory" var="deleteZendeskCategoryURL">
							<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
							<portlet:param name="zendeskCategoryId" value="<%= String.valueOf(zendeskCategory.getZendeskCategoryId()) %>" />
						</portlet:actionURL>

						<liferay-ui:icon-delete
							message="remove"
							url="<%= deleteZendeskCategoryURL %>"
						/>
					</c:if>
				</liferay-ui:icon-menu>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<liferay-frontend:add-menu>
	<portlet:renderURL var="editZendeskCategoryURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="mvcPath" value="/admin/edit_zendesk_category.jsp" />
		<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
	</portlet:renderURL>

	<liferay-frontend:add-menu-item
		title='<%= LanguageUtil.get(request, "add-zendesk-category") %>'
		url="<%= editZendeskCategoryURL.toString() %>"
	/>
</liferay-frontend:add-menu>