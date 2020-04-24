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
	<liferay-util:param name="title" value="select-users" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<portlet:renderURL var="selectUsersURL">
	<portlet:param name="controller" value="tasks" />
	<portlet:param name="action" value="selectUsers" />
	<portlet:param name="testrayBuildId" value="${param.testrayBuildId}" />
	<portlet:param name="testrayTaskId" value="${param.testrayTaskId}" />
</portlet:renderURL>

<div class="${popup ? "testray-modal-toolbar" : StringPool.BLANK}">
	<aui:form action="${selectUsersURL}" method="get" name="fm" onSubmit="event.preventDefault(); ${htmlNamespace}searchUsers();">
		<aui:fieldset>
			<aui:input autoFocus="${true}" inlineField="${true}" label="" name="keywords" size="30" title="search-users" type="text" />

			<aui:button type="submit" value="search" />
		</aui:fieldset>
	</aui:form>
</div>

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

<aui:form action="${addTestrayTaskUsersURL}" method="post" name="fm2" onSubmit="event.preventDefault(); ${htmlNamespace}submit();">
	<aui:input name="redirect" type="hidden" value="${selectUsersURL}" />

	<aui:input name="testrayBuildId" type="hidden" value="${param.testrayBuildId}" />
	<aui:input name="userIds" type="hidden" value="" />

	<aui:button-row cssClass='${popup ? "testray-modal-footer" : StringPool.BLANK}'>
		<aui:button type="submit" value="select-users" />

		<aui:button onClick="Liferay.Testray.closeWindow();" value="cancel" />
	</aui:button-row>

	<div class="${popup ? "spacing-footer spacing-toolbar testray-modal-content" : StringPool.BLANK}">
		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-available-users"
			id="usersSearchContainer"
			iteratorURL="${portletURL}"
			rowChecker="${rowChecker}"
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

<aui:script>
	function ${htmlNamespace}getUsers(selectedUserIds) {
		var users = [];

		var usersJSONArray = ${usersJSONArray};

		for (var i = 0; i < usersJSONArray.length; i++) {
			var user = usersJSONArray[i];

			if (selectedUserIds.indexOf(user.userId.toString()) >= 0) {
				users.push(user);
			}
		}

		return users;
	}

	function ${htmlNamespace}searchUsers() {
		var keywords = document.${htmlNamespace}fm.${htmlNamespace}keywords.value;

		window.location.href = '${selectUsersURL}&${htmlNamespace}keywords=' + escape(keywords);
	}

	Liferay.provide(
		window,
		'${htmlNamespace}submit',
		function() {
			var A = AUI();

			var allRowIds = Liferay.Util.listCheckedExcept(document.${htmlNamespace}fm2, '${htmlNamespace}allRowIds');

			var opener = Liferay.Util.getOpener();

			if (opener && opener.${htmlNamespace}buildSearchContainer) {
				var users = ${htmlNamespace}getUsers(allRowIds.split(','));

				opener.${htmlNamespace}buildSearchContainer(users);

				var alertMessage = A.Lang.sub(
					'<liferay-ui:message key="x-users-were-added-successfully" />',
					[
						users.length
					]
				);

				opener.Liferay.Testray.addAlert(
					{
						containerId: '#${htmlNamespace}testrayAlertContainer',
						message: alertMessage,
						type: 'success'
					}
				);
			}
			else {
				document.${htmlNamespace}fm2.${htmlNamespace}userIds.value = allRowIds;

				submitForm(document.${htmlNamespace}fm2);
			}

			Liferay.Testray.closeWindow();
		},
		['liferay-util-list-fields', 'testray-base']
	);
</aui:script>