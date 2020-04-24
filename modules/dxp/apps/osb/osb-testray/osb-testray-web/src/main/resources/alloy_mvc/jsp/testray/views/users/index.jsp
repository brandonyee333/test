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

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/testray/views/start.jspf" %>

<liferay-util:include page="/alloy_mvc/jsp/testray/views/header.jsp" servletContext="<%= application %>">
	<liferay-util:param name="title" value="manage-users" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<div class="testray-card">
	<testray:table-toolbar
		title="users"
	>
		<testray:filter>
			<testray:filter-element
				label="name"
				name="name"
				selectedLabel="name-x"
				type="text"
			/>
		</testray:filter>

		<c:if test='${testrayPermission:containsClassAction(themeDisplay, userClass, "create")}'>
			<portlet:renderURL var="createUserURL">
				<portlet:param name="controller" value="users" />
				<portlet:param name="action" value="create" />
			</portlet:renderURL>

			<aui:button href="${createUserURL}" icon="icon-plus" primary="${true}" value="new-user" />
		</c:if>
	</testray:table-toolbar>

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-available-users"
		id="usersSearchContainer"
		iteratorURL="${portletURL}"
		total="${usersCount}"
	>
		<liferay-ui:search-container-results
			results="${users}"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.portal.kernel.model.User"
			escapedModel="${true}"
			keyProperty="userId"
			modelVar="user"
		>
			<portlet:renderURL var="editUserURL">
				<portlet:param name="controller" value="users" />
				<portlet:param name="action" value="edit" />
				<portlet:param name="id" value="${user.userId}" />
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				cssClass="table-column-main"
				href="${editUserURL}"
				name="name"
				property="fullName"
			/>

			<liferay-ui:search-container-column-text
				href="${editUserURL}"
				name="screen-name"
				property="screenName"
			/>

			<liferay-ui:search-container-column-text
				href="${editUserURL}"
				name="email-address"
				property="emailAddress"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>