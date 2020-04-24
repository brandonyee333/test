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
	<liferay-util:param name="title" value="testflow" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testrayTask}" model="<%= TestrayTask.class %>" />

<portlet:actionURL var="addTestrayTaskURL">
	<portlet:param name="controller" value="tasks" />
	<portlet:param name="action" value="add" />
	<portlet:param name="testrayBuildId" value="${param.testrayBuildId}" />
</portlet:actionURL>

<div class="testray-card">
	<aui:form action="${addTestrayTaskURL}" method="post" name="createTaskForm" onSubmit="event.preventDefault(); ${htmlNamespace}submit();">
		<portlet:renderURL var="viewTestrayRunsURL">
			<portlet:param name="controller" value="runs" />
			<portlet:param name="action" value="index" />
			<portlet:param name="testrayBuildId" value="${param.testrayBuildId}" />

			<testray:filter-preference
				value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "runs", "index", testrayProjectId)}'
			/>
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="${viewTestrayRunsURL}" />

		<aui:input name="userIds" type="hidden" value="" />

		<aui:input autoFocus="${true}" name="name" />

		<aui:fieldset id="testrayCaseType">
			<aui:field-wrapper cssClass="case-type-wrapper" label="case-type" name="case-type" required="${true}">
				<c:forEach items="${testrayCaseTypes}" var="testrayCaseType">
					<div class="case-type-checkbox-wrapper">
						<aui:input cssClass="case-type" label="${testrayCaseType.name}" name="testrayCaseType${testrayCaseType.testrayCaseTypeId}" type="checkbox" />
					</div>
				</c:forEach>
			</aui:field-wrapper>
		</aui:fieldset>

		<aui:input name="testrayCaseTypeIds" type="hidden" value="${param.testrayCaseTypeIds}" />

		<h3 class="section-title">
			<liferay-ui:message key="Users" />
		</h3>

		<aui:button-row>
			<portlet:renderURL var="selectUsersURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="controller" value="tasks" />
				<portlet:param name="action" value="selectUsers" />
				<portlet:param name="testrayBuildId" value="${param.testrayBuildId}" />
			</portlet:renderURL>

			<c:set value='${AlloyLanguageUtil.getUnicode("select-users")}' var="selectUsersURLTitle" />

			<c:set value="javascript:Liferay.Testray.openWindow('${selectUsersURL}', '${selectUsersURLTitle}', 1000)" var="selectUsersURL" />

			<aui:button onClick="${selectUsersURL}" value="assign-users" />

			<portlet:renderURL var="getUserGroupUsersURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="controller" value="tasks" />
				<portlet:param name="action" value="index" />
				<portlet:param name="format" value="js" />
			</portlet:renderURL>

			<portlet:renderURL var="viewUserGroupsURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
				<portlet:param name="controller" value="tasks" />
				<portlet:param name="action" value="selectUserGroups" />
				<portlet:param name="redirect" value="${getUserGroupUsersURL}" />
				<portlet:param name="testrayBuildId" value="${param.testrayBuildId}" />
			</portlet:renderURL>

			<c:set value='${AlloyLanguageUtil.getUnicode("select-user-groups")}' var="viewUserGroupsURLTitle" />

			<c:set value="javascript:Liferay.Testray.openWindow('${viewUserGroupsURL}', '${viewUserGroupsURLTitle}', 1000)" var="viewUserGroupsURL" />

			<aui:button onClick="${viewUserGroupsURL}" value="assign-user-groups" />
		</aui:button-row>

		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-linked-users"
			headerNames="first-name,last-name,screen-name,email-address,user-groups,remove"
			total="${fn:length(users)}"
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
					property="firstName"
				/>

				<liferay-ui:search-container-column-text
					property="lastName"
				/>

				<liferay-ui:search-container-column-text
					property="screenName"
				/>

				<liferay-ui:search-container-column-text
					property="emailAddress"
				/>

				<liferay-ui:search-container-column-text
					name="user-groups"
					value="<%= TestrayUtil.getUserGroupsString(user) %>"
				/>

				<liferay-ui:search-container-column-text
					align="right"
				>
					<liferay-ui:icon
						id="${user.userId}"
						image="delete"
						label="<%= true %>"
						message="remove"
						onClick="${htmlNamespace}removeUser(this);"
						url="javascript:;"
					/>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator
				paginate="${false}"
			/>
		</liferay-ui:search-container>

		<aui:button-row>
			<aui:button type="submit" />

			<portlet:renderURL var="viewTestrayTasksURL">
				<portlet:param name="controller" value="tasks" />
				<portlet:param name="action" value="index" />

				<testray:filter-preference
					value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "tasks", "index", testrayProjectId)}'
				/>
			</portlet:renderURL>

			<aui:button href="${not empty param.redirect ? param.redirect : viewTestrayTasksURL}" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script use="liferay-form,testray-base">
	var testrayForm = Liferay.Form.get('${htmlNamespace}createTaskForm');

	var testrayFormNode = testrayForm.formNode;
	var testrayFormValidator = testrayForm.formValidator;

	testrayFormValidator.after(
		'submit',
		function() {
			if (!${htmlNamespace}validateCreateTaskForm(false)) {
				var Util = Liferay.Util;

				var submitButton = testrayFormNode.all('button[type="submit"]');

				Util.enableFormButtons(submitButton);

				submitButton.setStyle('opacity', '1');

				var checkboxes = testrayFormNode.all('input[type="checkbox"]');

				Util.toggleDisabled(checkboxes, false);

				var checkboxInput = checkboxes.item(0);

				if (checkboxInput) {
					var checkboxFieldsetNode = checkboxInput.ancestor('fieldset');

					if (checkboxFieldsetNode) {
						Util.toggleDisabled(checkboxFieldsetNode, false);
					}
				}
			}
		}
	);
</aui:script>

<aui:script>
	Liferay.on(
		'portletReady',
		function(event) {
			if (event.portletId == '${TestrayPortletKeys.TESTRAY}') {
				${htmlNamespace}setFormInput();
			}
		}
	);

	function ${htmlNamespace}removeEmptyResultsMessage(searchContainer) {
		var removeNode = function(node) {
			var nodeText = node.text().trim();

			var langKey = '<liferay-ui:message key="there-are-no-linked-users" />';

			if (nodeText === langKey) {
				node.remove();
			}
		};

		var searchContainerParentContainer = searchContainer._parentContainer;

		if (searchContainerParentContainer) {
			var previousNode = searchContainerParentContainer.previous();

			if (previousNode) {
				removeNode(previousNode);
			}
		}

		var searchContainerTable = searchContainer._table;

		if (searchContainerTable) {
			var tableData = searchContainerTable.one('.table-data');

			if (tableData) {
				var firstRow = tableData.get('firstElementChild');

				if (firstRow) {
					removeNode(firstRow);
				}
			}
		}
	}

	function ${htmlNamespace}validateCreateTaskForm(showAlerts) {
		var formValid = true;

		if (!document.createTaskForm.testrayCaseTypeIds.value) {
			formValid = false;

			if (showAlerts) {
				Liferay.Testray.addAlert(
					{
						message: '<liferay-ui:message key="please-mark-at-least-one-case-type-for-processing" />',
						type: 'danger'
					}
				);
			}
		}

		if (!document.createTaskForm.userIds.value) {
			formValid = false;

			if (showAlerts) {
				Liferay.Testray.addAlert(
					{
						message: '<liferay-ui:message key="please-mark-at-least-one-user-or-user-group-for-assignment" />',
						type: 'danger'
					}
				);
			}
		}

		return formValid;
	}

	Liferay.provide(
		window,
		'${htmlNamespace}buildSearchContainer',
		function(users) {
			var searchContainer = Liferay.SearchContainer.get('${htmlNamespace}usersSearchContainer');

			${htmlNamespace}removeEmptyResultsMessage(searchContainer);

			var searchContainerData = searchContainer.getData();

			users.forEach(
				function(user) {
					if (searchContainerData.indexOf(user.userId.toString()) < 0) {
						var removeLink = '<span><a href="javascript:;" id="' + user.userId + '" onclick="${htmlNamespace}removeUser(this);"><img src="${themeDisplay.pathThemeImages}/common/delete.png"><span><liferay-ui:message key="remove" /></span></a></span>';

						searchContainer.addRow([user.firstName, user.lastName, user.screenName, user.emailAddress, user.userGroupsString, removeLink], user.userId);

						searchContainer.syncUI();
					}
				}
			);

			Liferay.Testray.setFormInput(
				{
					formName: 'createTaskForm',
					inputId: '${htmlNamespace}userIds',
					val: searchContainer.getData()
				}
			);
		},
		['liferay-search-container']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}removeUser',
		function(element) {
			var searchContainer = Liferay.SearchContainer.get('${htmlNamespace}usersSearchContainer');

			var elementId = element.id;

			var userId = elementId.replace('${htmlNamespace}', '');

			searchContainer.deleteRow(element, userId);

			Liferay.Testray.setFormInput(
				{
					formName: 'createTaskForm',
					inputId: '${htmlNamespace}userIds',
					val: searchContainer.getData()
				}
			);
		},
		['liferay-search-container', 'testray-base']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}setFormInput',
		function() {
			var searchContainer = Liferay.SearchContainer.get('${htmlNamespace}usersSearchContainer');

			Liferay.Testray.setFormInput(
				{
					formName: 'createTaskForm',
					inputId: '${htmlNamespace}userIds',
					val: searchContainer.getData()
				}
			);
		},
		['liferay-search-container', 'testray-base']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}submit',
		function() {
			Liferay.Testray.combineCheckboxValues('${htmlNamespace}testrayCaseType', 'testrayCaseType', 'case-type', 'testrayCaseTypeIds');

			if (${htmlNamespace}validateCreateTaskForm(true)) {
				submitForm(document.${htmlNamespace}createTaskForm);
			}
		},
		['testray-base']
	);
</aui:script>