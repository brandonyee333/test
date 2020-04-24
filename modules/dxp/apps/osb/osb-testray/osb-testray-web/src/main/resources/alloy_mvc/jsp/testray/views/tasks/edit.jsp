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
	<liferay-util:param name="title" value="edit" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testrayTaskComposite.testrayTask}" model="<%= TestrayTask.class %>" />

<portlet:actionURL var="updateTestrayTaskURL">
	<portlet:param name="controller" value="tasks" />
	<portlet:param name="action" value="update" />
	<portlet:param name="id" value="${testrayTaskComposite.testrayTaskId}" />
</portlet:actionURL>

<div class="testray-card">
	<aui:form action="${updateTestrayTaskURL}" method="post" name="editTaskForm" onSubmit="event.preventDefault(); ${htmlNamespace}submit();">
		<portlet:renderURL var="viewTestraySubtasksURL">
			<portlet:param name="controller" value="subtasks" />
			<portlet:param name="action" value="index" />
			<portlet:param name="testrayTaskId" value="${testrayTaskComposite.testrayTaskId}" />

			<testray:filter-preference
				value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "subtasks", "index", testrayProjectId)}'
			/>
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="${not empty param.redirect ? param.redirect : viewTestraySubtasksURL}" />

		<aui:input name="userIds" type="hidden" value="${assignedUserIdsString}" />

		<aui:input autoFocus="${true}" name="name" value="${testrayTaskComposite.name}" />

		<aui:fieldset id="testrayCaseType">
			<aui:field-wrapper cssClass="case-type-wrapper">
				<c:forEach items="${testrayCaseTypes}" var="testrayCaseType">
					<div class="case-type-checkbox-wrapper">
						<aui:input checked="${relevantTestrayCaseTypesIds.contains(testrayCaseType.testrayCaseTypeId)}" cssClass="case-type" disabled="${true}" label="${testrayCaseType.name}" name="testrayCaseType${testrayCaseType.testrayCaseTypeId}" type="checkbox" />
					</div>
				</c:forEach>
			</aui:field-wrapper>
		</aui:fieldset>

		<aui:input name="testrayCaseTypesIds" type="hidden" value="${param.testrayCaseTypesIds}" />

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
			total="${fn:length(testrayTaskComposite.assignedUsers)}"
		>
			<liferay-ui:search-container-results
				results="${testrayTaskComposite.assignedUsers}"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.kernel.model.User"
				escapedModel="${true}"
				keyProperty="userId"
				modelVar="user"
			>
				<liferay-ui:search-container-column-text
					name="first-name"
					property="firstName"
				/>

				<liferay-ui:search-container-column-text
					name="last-name"
					property="lastName"
				/>

				<liferay-ui:search-container-column-text
					name="screen-name"
					property="screenName"
				/>

				<liferay-ui:search-container-column-text
					name="email-address"
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

			<aui:button href="${not empty param.redirect ? param.redirect : viewTestraySubtasksURL}" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

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
					formName: 'editTaskForm',
					inputId: '${htmlNamespace}userIds',
					val: searchContainer.getData()
				}
			);
		},
		['liferay-search-container', 'testray-base']
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
					formName: 'editTaskForm',
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
					formName: 'editTaskForm',
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
			Liferay.Testray.combineCheckboxValues('${htmlNamespace}testrayCaseType', 'testrayCaseType', 'case-type', 'testrayCaseTypesIds');

			submitForm(document.${htmlNamespace}editTaskForm);
		},
		['testray-base']
	);
</aui:script>