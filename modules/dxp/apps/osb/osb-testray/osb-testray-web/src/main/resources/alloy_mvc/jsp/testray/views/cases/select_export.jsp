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
	<liferay-util:param name="title" value="export-cases" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<portlet:actionURL var="exportTestrayCasesURL">
	<portlet:param name="controller" value="cases" />
	<portlet:param name="action" value="export" />
</portlet:actionURL>

<div class="testray-card">
	<aui:form action="${exportTestrayCasesURL}" method="post" name="exportCasesForm">
		<aui:input name="addTestrayCaseIds" type="hidden" value="${testrayCaseIds}" />

		<c:choose>
			<c:when test="${testrayCasesCount > 0}">
				<h3 class="section-title">
					<liferay-ui:message key="select-cases-to-export" />
				</h3>

				<aui:button-row>
					<portlet:renderURL var="selectTestrayCasesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
						<portlet:param name="controller" value="cases" />
						<portlet:param name="action" value="select" />
						<portlet:param name="scopeByProject" value="${true}" />
						<portlet:param name="testrayBuildId" value="0" />
						<portlet:param name="testrayProjectId" value="${testrayProjectId}" />

						<testray:filter-preference
							value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "cases", "select", testrayProjectId)}'
						/>
					</portlet:renderURL>

					<c:set value='${AlloyLanguageUtil.getUnicode("cases")}' var="selectTestrayCasesURLTitle" />

					<c:set value="javascript:Liferay.Testray.openWindow('${selectTestrayCasesURL}', '${selectTestrayCasesURLTitle}', 1000)" var="selectTestrayCasesURL" />

					<aui:button onClick="${selectTestrayCasesURL}" value="add-cases" />

					<portlet:renderURL var="viewTestrayCasesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
						<portlet:param name="controller" value="cases" />
						<portlet:param name="action" value="index" />
						<portlet:param name="format" value="js" />

						<testray:filter-preference
							value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "cases", "index", testrayProjectId)}'
						/>
					</portlet:renderURL>

					<portlet:actionURL var="refeshTestraySuitesURL">
						<portlet:param name="controller" value="suites" />
						<portlet:param name="action" value="refreshSuites" />
						<portlet:param name="redirect" value="${viewTestrayCasesURL}" />
					</portlet:actionURL>

					<portlet:renderURL var="selectTestraySuitesURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
						<portlet:param name="controller" value="suites" />
						<portlet:param name="action" value="select" />
						<portlet:param name="redirect" value="${refeshTestraySuitesURL}" />
						<portlet:param name="testrayProjectId" value="${testrayProjectId}" />
					</portlet:renderURL>

					<c:set value='${AlloyLanguageUtil.getUnicode("suites")}' var="selectTestraySuitesURLTitle" />

					<c:set value="javascript:Liferay.Testray.openWindow('${selectTestraySuitesURL}', '${selectTestraySuitesURLTitle}', 1000)" var="selectTestraySuitesURL" />

					<c:set var="noSuitesHelpMessage">
						<liferay-ui:message key="create-suites-if-you-want-to-use-them-to-link-cases" />
					</c:set>

					<span title='${(testraySuitesCount <= 0) ? noSuitesHelpMessage : StringPool.BLANK}'>
						<aui:button disabled="${testraySuitesCount <= 0}" onClick="${selectTestraySuitesURL}" value="add-suites" />
					</span>
				</aui:button-row>

				<liferay-ui:search-container
					emptyResultsMessage="there-are-no-linked-cases"
					headerNames="priority,component,name,remove"
					orderByCol="${testrayCasesOrderByCol}"
					orderByColParam="testrayCasesOrderByCol"
					orderByType="${testrayCasesOrderByType}"
					orderByTypeParam="testrayCasesOrderByType"
					total="${fn:length(testrayCases)}"
				>
					<liferay-ui:search-container-results
						results="${testrayCases}"
					/>

					<liferay-ui:search-container-row
						className="com.liferay.osb.testray.model.TestrayCase"
						escapedModel="${true}"
						keyProperty="testrayCaseId"
						modelVar="testrayCase"
					>
						<liferay-ui:search-container-column-text
							orderable="${true}"
							property="priority"
						/>

						<c:set value="<%= TestrayComponentLocalServiceUtil.getTestrayComponent(testrayCase.getTestrayComponentId()) %>" var="testrayComponent" />

						<liferay-ui:search-container-column-text
							name="component"
							value="${testrayComponent.name}"
						/>

						<liferay-ui:search-container-column-text
							orderable="${true}"
							orderableProperty="name_sortable"
							property="name"
						/>

						<liferay-ui:search-container-column-text
							align="right"
						>
							<liferay-ui:icon
								id="${testrayCase.testrayCaseId}"
								image="delete"
								label="<%= true %>"
								message="remove"
								onClick="${htmlNamespace}removeTestrayCase(this);"
								url="javascript:;"
							/>
						</liferay-ui:search-container-column-text>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator
						paginate="${true}"
					/>
				</liferay-ui:search-container>
			</c:when>
			<c:otherwise>
				<div class="alert alert-info">
					<liferay-ui:message key="no-cases-exist-to-export" />
				</div>
			</c:otherwise>
		</c:choose>

		<aui:button-row>
			<aui:button type="submit" value="export" />

			<aui:button href="${redirect}" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script>
	function ${htmlNamespace}removeEmptyResultsMessage(searchContainer) {
		var removeNode = function(node) {
			var nodeText = node.text().trim();

			var langKey = '<liferay-ui:message key="there-are-no-linked-cases" />';

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
		function(testrayCases) {
			var searchContainer = Liferay.SearchContainer.get('${htmlNamespace}testrayCasesSearchContainer');

			${htmlNamespace}removeEmptyResultsMessage(searchContainer);

			var searchContainerData = searchContainer.getData();

			testrayCases.forEach(
				function(testrayCase) {
					if (searchContainerData.indexOf(testrayCase.testrayCaseId.toString()) < 0) {
						var removeLink = '<span><a href="javascript:;" id=' + testrayCase.testrayCaseId + ' onclick="${htmlNamespace}removeTestrayCase(this);"><img src="${themeDisplay.pathThemeImages}/common/delete.png"><span><liferay-ui:message key="remove" /></span></a></span>';

						searchContainer.addRow([testrayCase.priority, testrayCase.mainComponent.name, testrayCase.name, removeLink], testrayCase.testrayCaseId);

						searchContainer.syncUI();
					}
				}
			);

			Liferay.Testray.setFormInput(
				{
					formName: 'exportCasesForm',
					inputId: '${htmlNamespace}addTestrayCaseIds',
					val: searchContainer.getData()
				}
			);
		},
		['liferay-search-container', 'testray-base']
	);

	Liferay.provide(
		window,
		'${htmlNamespace}removeTestrayCase',
		function(element) {
			var searchContainer = Liferay.SearchContainer.get('${htmlNamespace}testrayCasesSearchContainer');

			var elementId = element.id;

			var testrayCaseId = elementId.replace('${htmlNamespace}', '');

			searchContainer.deleteRow(element, testrayCaseId);

			Liferay.Testray.setFormInput(
				{
					formName: 'exportCasesForm',
					inputId: '${htmlNamespace}addTestrayCaseIds',
					val: searchContainer.getData()
				}
			);
		},
		['liferay-search-container', 'testray-base']
	);
</aui:script>

<aui:script use="liferay-search-container,testray-base">
	Liferay.on(
		'portletReady',
		function(event) {
			if (event.portletId == '${TestrayPortletKeys.TESTRAY}') {
				var searchContainer = Liferay.SearchContainer.get('${htmlNamespace}testrayCasesSearchContainer');

				Liferay.Testray.setFormInput(
					{
						formName: 'exportCasesForm',
						inputId: '${htmlNamespace}addTestrayCaseIds',
						val: searchContainer.getData()
					}
				);
			}
		}
	);
</aui:script>