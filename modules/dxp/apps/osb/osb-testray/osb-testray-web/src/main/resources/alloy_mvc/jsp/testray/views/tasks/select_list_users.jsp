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
	<liferay-util:param name="title" value="Assigned Users" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<portlet:renderURL var="selectListUsersURL">
	<portlet:param name="controller" value="tasks" />
	<portlet:param name="action" value="selectListUsers" />
	<portlet:param name="testrayBuildId" value="${param.testrayBuildId}" />
	<portlet:param name="testrayTaskId" value="${param.testrayTaskId}" />
</portlet:renderURL>

<div class="hide" id="${htmlNamespace}userSearchAlertContainer">
	<div class="alert alert-error">
		<liferay-ui:message key="your-request-failed-to-complete" />
	</div>
</div>

<portlet:actionURL var="addTestrayTaskUsersURL">
	<portlet:param name="controller" value="tasks" />
	<portlet:param name="action" value="updateUsers" />
	<portlet:param name="id" value="${param.testrayTaskId}" />
</portlet:actionURL>

<aui:form action="${selectListUsersURL}" method="post" name="fm2" onSubmit="event.preventDefault(); ${htmlNamespace}submit();">
	<aui:input name="redirect" type="hidden" value="${selectListUsersURL}" />

	<aui:input name="testrayBuildId" type="hidden" value="${param.testrayBuildId}" />
	<aui:input name="userIds" type="hidden" value="" />

	<div class="${popup ? "spacing-footer spacing-toolbar testray-modal-content" : StringPool.BLANK}">
		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-available-users"
			id="usersSearchContainer"
			iteratorURL="${portletURL}"
			rowChecker="${rowChecker}"
			total="${usersCount}"
		>
			<liferay-ui:search-container-results
				results="${assignedUsers}"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.model.User"
				escapedModel="${true}"
				keyProperty="userId"
				modelVar="user"
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
					name="email-address"
					property="emailAddress"
				/>

				<c:set value="<%= TestrayUtil.getUserGroupsString(user) %>" var="userGroupsString" />

				<liferay-ui:search-container-column-text
					name="user-groups"
					value="${userGroupsString}"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</div>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>