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

<%@ include file="/alloy_mvc/jsp/testray/views/suites/header.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<div class="testray-card testray-card-metadata-panel">
	<liferay-ui:panel-container
		cssClass="metadata-panel"
		extended="${false}"
		persistState="${true}"
	>
		<liferay-ui:panel
			collapsible="${true}"
			defaultState="closed"
			extended="${false}"
			id="additionInformationPanel"
			persistState="${true}"
			title="details"
		>
			<dl class="data-list dl-horizontal">
				<dt>
					<liferay-ui:message key="description" />
				</dt>
				<dd>
					<c:out value="${testraySuite.description}" />
				</dd>
				<dt>
					<liferay-ui:message key="create-date" />
				</dt>
				<dd>
					<testray:date
						value="${testraySuite.createDate}"
					/>
				</dd>
				<dt>
					<liferay-ui:message key="date-last-modified" />
				</dt>
				<dd>
					<testray:date
						value="${testraySuite.modifiedDate}"
					/>
				</dd>
				<dt>
					<liferay-ui:message key="created-by" />
				</dt>
				<dd>
					<c:out value="${testraySuite.userName}" />
				</dd>
			</dl>
		</liferay-ui:panel>
	</liferay-ui:panel-container>
</div>

<c:if test="${caseParametersMap != null}">
	<testray:table-toolbar
		title="cases"
	/>

	<div class="testray-card testray-card-metadata-panel">
		<liferay-ui:panel-container
			cssClass="metadata-panel"
			extended="${false}"
			persistState="${true}"
		>
			<liferay-ui:panel
				collapsible="${true}"
				defaultState="closed"
				extended="${false}"
				id="additionInformationPanel"
				persistState="${true}"
				title="case-parameters"
			>
				<dl class="data-list dl-horizontal">
					<dt>
						<liferay-ui:message key="case-types" />
					</dt>
					<dd>
						<c:out value='${caseParametersMap.get("testrayCaseTypeNames")}' />
					</dd>
					<dt>
						<liferay-ui:message key="components" />
					</dt>
					<dd>
						<c:out value='${caseParametersMap.get("testrayComponentNames")}' />
					</dd>
					<dt>
						<liferay-ui:message key="subcomponents" />
					</dt>
					<dd>
						<c:out value='${caseParametersMap.get("testraySubcomponentNames")}' />
					</dd>
					<dt>
						<liferay-ui:message key="priority" />
					</dt>
					<dd>
						<c:out value='${caseParametersMap.get("priority")}' />
					</dd>
					<dt>
						<liferay-ui:message key="requirements" />
					</dt>
					<dd>
						<c:out value='${caseParametersMap.get("testrayRequirementKeys")}' />
					</dd>
				</dl>
			</liferay-ui:panel>
		</liferay-ui:panel-container>
	</div>
</c:if>

<div class="testray-card">
	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-cases"
		id="suiteCasesSearchContainer"
		iteratorURL="${portletURL}"
		total="${testrayCasesCount}"
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
			<portlet:renderURL var="viewTestrayCaseURL">
				<portlet:param name="controller" value="cases" />
				<portlet:param name="action" value="view" />
				<portlet:param name="id" value="${testrayCase.testrayCaseId}" />
				<portlet:param name="testraySuiteId" value="${testraySuite.testraySuiteId}" />

				<testray:filter-preference
					value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "cases", "view", testrayProjectId)}'
				/>
			</portlet:renderURL>

			<liferay-ui:search-container-column-text
				href="${viewTestrayCaseURL}"
				property="priority"
			/>

			<c:set value="<%= TestrayComponentLocalServiceUtil.getTestrayComponent(testrayCase.getTestrayComponentId()) %>" var="testrayComponent" />

			<liferay-ui:search-container-column-text
				href="${viewTestrayCaseURL}"
				name="component"
				value="${testrayComponent.name}"
			/>

			<liferay-ui:search-container-column-text
				href="${viewTestrayCaseURL}"
				name="case"
				property="name"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script use="testray-context-menu">
	new Liferay.Testray.ContextMenu(
		{
			containerId: '${htmlNamespace}suiteCasesSearchContainer',
			menu: [
				{
					action: 'view',
					controller: 'cases',
					iconCssClass: 'icon-file',
					label: '<liferay-ui:message key="view" />',
					parameters: {
						testraySuiteId: ${testraySuite.testraySuiteId}
					},
					urlTemplate: '/{id}',
					userFilterPreferences: ${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "cases", "view", testrayCase.testrayProjectId)}
				},
				{
					action: 'edit',
					controller: 'cases',
					iconCssClass: 'icon-edit',
					label: '<liferay-ui:message key="edit" />',
					parameters: {
						testraySuiteId: ${testraySuite.testraySuiteId}
					},
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayCase, "edit")}
				},
				{
					action: 'removeCase',
					controller: 'suites',
					iconCssClass: 'icon-trash',
					label: '<liferay-ui:message key="delete" />',
					menuAction: 'delete',
					method: 'POST',
					urlTemplate: '/${testraySuite.testraySuiteId}/{action}/?${htmlNamespace}testrayCaseId={id}',
					visible: ${testrayPermission:containsModelAction(themeDisplay, testrayCase, "removeCase")}
				}
			],
			redirect: '${portletURL}'
		}
	).render();
</aui:script>