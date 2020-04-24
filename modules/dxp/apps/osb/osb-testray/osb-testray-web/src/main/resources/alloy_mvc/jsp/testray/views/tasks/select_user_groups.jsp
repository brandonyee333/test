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
	<liferay-util:param name="title" value="select-user-groups" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<portlet:renderURL var="selectUserGroupsURL">
	<portlet:param name="controller" value="tasks" />
	<portlet:param name="action" value="selectUserGroups" />
	<portlet:param name="testrayBuildId" value="${param.testrayBuildId}" />
</portlet:renderURL>

<div class="${popup ? "testray-modal-toolbar" : StringPool.BLANK}">
	<aui:form action="${selectUserGroupsURL}" method="get" name="fm" onSubmit="event.preventDefault(); ${htmlNamespace}searchUserGroups();">
		<aui:fieldset>
			<aui:input autoFocus="${true}" inlineField="${true}" label="" name="keywords" size="30" title="search-user-groups" type="text" />

			<aui:button type="submit" value="search" />
		</aui:fieldset>
	</aui:form>
</div>

<div class="hide" id="${htmlNamespace}userSearchAlertContainer">
	<div class="alert alert-error">
		<liferay-ui:message key="your-request-failed-to-complete" />
	</div>
</div>

<portlet:actionURL var="addTestrayTaskUrl">
	<portlet:param name="controller" value="tasks" />
	<portlet:param name="action" value="add" />
</portlet:actionURL>

<aui:form action="${addTestrayTaskUrl}" method="post" name="fm2" onSubmit="event.preventDefault(); ${htmlNamespace}submit();">
	<aui:input name="redirect" type="hidden" value="${selectUserGroupsURL}" />

	<aui:input name="addUserGroupIds" type="hidden" value="" />
	<aui:input name="testrayBuildId" type="hidden" value="${param.testrayBuildId}" />

	<aui:button-row cssClass='${popup ? "testray-modal-footer" : StringPool.BLANK}'>
		<aui:button type="submit" value="select-user-groups" />

		<aui:button onClick="Liferay.Testray.closeWindow();" value="cancel" />
	</aui:button-row>

	<div class="${popup ? "spacing-footer spacing-toolbar testray-modal-content" : StringPool.BLANK}">
		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-available-user-groups"
			id="userGroupsSearchContainer"
			iteratorURL="${portletURL}"
			rowChecker="${rowChecker}"
			total="${userGroupsCount}"
		>
			<liferay-ui:search-container-results
				results="${userGroups}"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.model.UserGroup"
				escapedModel="${true}"
				keyProperty="userGroupId"
				modelVar="userGroup"
			>
				<liferay-ui:search-container-column-text
					cssClass="user-group"
					href="${setTestrayAssignmentsURL}"
					name="name"
					property="name"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</div>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	function ${htmlNamespace}searchUserGroups() {
		var keywords = document.${htmlNamespace}fm.${htmlNamespace}keywords.value;

		window.location.href = '${selectUserGroupsURL}&${htmlNamespace}keywords=' + escape(keywords);
	}

	Liferay.provide(
		window,
		'${htmlNamespace}submit',
		function() {
			var allRowIds = Liferay.Util.listCheckedExcept(document.${htmlNamespace}fm2, '${htmlNamespace}allRowIds');

			if (${empty param.redirect}) {
				document.${htmlNamespace}fm2.${htmlNamespace}addUserGroupIds.value = allRowIds;

				submitForm(document.${htmlNamespace}fm2);

				Liferay.Testray.closeWindow();
			}
			else {
				window.location.href = '${param.redirect}&${htmlNamespace}userGroupIds=' + allRowIds;
			}
		},
		['liferay-util-list-fields', 'testray-base']
	);
</aui:script>