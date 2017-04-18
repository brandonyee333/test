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
long roleLiferayEmployeeId = GetterUtil.getLong(renderRequest.getAttribute("OSB_ROLE_LIFERAY_EMPLOYEE_ID"));

String displayStyle = ParamUtil.getString(request, "displayStyle", "list");
String orderByCol = ParamUtil.getString(request, "orderByCol", "modified-date");
String orderByType = ParamUtil.getString(request, "orderByType", "asc");

OrderByComparator<User> orderByComparator = UsersAdminUtil.getUserOrderByComparator(orderByCol, orderByType);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/journal/select_user");
%>

<aui:nav-bar cssClass="collapse-basic-search" markupView="lexicon">
	<aui:nav cssClass="navbar-nav">
		<aui:nav-item label="user" selected="<%= true %>" />
	</aui:nav>

	<aui:nav-bar-search>
		<aui:form action="<%= portletURL.toString() %>" name="searchFm">
			<liferay-ui:input-search markupView="lexicon" />
		</aui:form>
	</aui:nav-bar-search>
</aui:nav-bar>

<liferay-frontend:management-bar>
	<liferay-frontend:management-bar-buttons>
		<liferay-frontend:management-bar-filters>
			<liferay-frontend:management-bar-navigation
				navigationKeys='<%= new String[] {"all"} %>'
				portletURL="<%= portletURL %>"
			/>

			<%
			SearchContainer userSearch = new UserSearch(renderRequest, portletURL);

			userSearch.setOrderByCol(orderByCol);
			userSearch.setOrderByType(orderByType);
			userSearch.setOrderByComparator(orderByComparator);

			request.setAttribute(WebKeys.SEARCH_CONTAINER, userSearch);
			%>

			<liferay-frontend:management-bar-sort
				orderByCol="<%= userSearch.getOrderByCol() %>"
				orderByType="<%= userSearch.getOrderByType() %>"
				orderColumns='<%= new String[] {"screen-name"} %>'
				portletURL="<%= portletURL %>"
			/>
		</liferay-frontend:management-bar-filters>

		<liferay-frontend:management-bar-display-buttons
			displayViews='<%= new String[] {"list"} %>'
			portletURL="<%= portletURL %>"
			selectedDisplayStyle="<%= displayStyle %>"
		/>
	</liferay-frontend:management-bar-buttons>
</liferay-frontend:management-bar>

<aui:form action="<%= portletURL.toString() %>" cssClass="container-fluid-1280" method="post" name="selectUserFm">
	<liferay-ui:search-container
		searchContainer="<%= userSearch %>"
		var="userSearchContainer"
	>

		<%
		LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();

		userParams.put("inherit", Boolean.TRUE);
		userParams.put("usersRoles", roleLiferayEmployeeId);
		%>

		<liferay-ui:user-search-container-results userParams="<%= userParams %>" />

		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.User"
			escapedModel="<%= true %>"
			keyProperty="userId"
			modelVar="user"
			rowIdProperty="screenName"
		>
			<liferay-ui:search-container-column-text
				name="name"
				property="fullName"
			/>

			<liferay-ui:search-container-column-text
				name="screen-name"
				property="screenName"
			/>

			<liferay-ui:search-container-column-text
				align="right"
			>

				<%
				Map<String, Object> data = new HashMap<String, Object>();

				data.put("userId", user.getUserId());
				data.put("userName", user.getFullName());
				%>

				<aui:button
					cssClass="selector-button"
					data="<%= data %>"
					value="choose"
				/>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator displayStyle="<%= displayStyle %>" markupView="lexicon" />
	</liferay-ui:search-container>
</aui:form>

<aui:script use="aui-base">
	var Util = Liferay.Util;

	A.one('#<portlet:namespace />selectUserFm').delegate(
		'click',
		function(event) {
			var result = Util.getAttributes(event.currentTarget, 'data-');

			Util.getOpener().Liferay.fire('<portlet:namespace />selectUser', result);

			Util.getWindow().hide();
		},
		'.selector-button'
	);
</aui:script>