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

<c:set value='${requestScope["testray:breadcrumb:composites"]}' var="composites" />

<div class="breadcrumb-content">
	<c:choose>
		<c:when test='${(param.controller == "subtasks") || (param.controller == "tasks")}'>
			<portlet:renderURL var="viewTestrayTasksURL">
				<portlet:param name="controller" value="tasks" />
				<portlet:param name="action" value="index" />
			</portlet:renderURL>

			<aui:a cssClass="breadcrumb-section-icon link-icon testray-tooltip-trigger" href="${viewTestrayTasksURL}" title="view-tasks">
				<aui:icon image="tasks" />
			</aui:a>
		</c:when>
		<c:when test='${(param.controller == "users")}'>
			<portlet:renderURL var="viewUsersURL">
				<portlet:param name="controller" value="users" />
				<portlet:param name="action" value="index" />
			</portlet:renderURL>

			<aui:a cssClass="breadcrumb-section-icon link-icon testray-tooltip-trigger" href="${viewUsersURL}" title="manage-users">
				<aui:icon image="cog" />
			</aui:a>
		</c:when>
		<c:otherwise>
			<div class="breadcrumb-section-icon dropdown">
				<aui:a cssClass="dropdown-toggle testray-tooltip-trigger" data-toggle="dropdown" href="javascript:;" title="projects">
					<aui:icon cssClass="dropdown-icon" image="bar-chart" />

					<aui:icon cssClass="dropdown-caret" image="caret-down" />
				</aui:a>

				<ul class="dropdown-menu dropdown-menu-left testray-menu">
					<li class="testray-menu-item">
						<portlet:renderURL var="viewTestrayProjectsURL">
							<portlet:param name="controller" value="projects" />
							<portlet:param name="action" value="index" />
						</portlet:renderURL>

						<aui:a href="${viewTestrayProjectsURL}" label="project-directory" />
					</li>
					<li class="testray-menu-divider"></li>

					<c:forEach items="${testrayProjects}" var="testrayProject">
						<li class="testray-menu-item">
							<portlet:renderURL var="viewTestrayRoutinesURL">
								<portlet:param name="controller" value="routines" />
								<portlet:param name="action" value="index" />
								<portlet:param name="testrayProjectId" value="${testrayProject.testrayProjectId}" />

								<testray:filter-preference
									value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "routines", "index", testrayProject.testrayProjectId)}'
								/>
							</portlet:renderURL>

							<aui:a href="${viewTestrayRoutinesURL}" label="${fn:escapeXml(testrayProject.name)}" />
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:otherwise>
	</c:choose>

	<span class="breadcrumb-display-container" id="${htmlNamespace}breadcrumbDisplayContainer">
		<c:choose>
			<c:when test="${not empty composites}">
				<c:forEach items="${composites}" var="composite" varStatus="i">
					<c:set value="${AlloyLanguageUtil.get(composite.displayName)}" var="displayName" />

					<c:choose>
						<c:when test="${i.index < (composites.size() - 1)}">
							<aui:a cssClass="breadcrumb-name breadcrumb-parent ${composite.CSSClassName}" data-class-pk="${composite.classPK}" data-field-name="${composite.classNameFieldName}" href="${not empty composite ? composite.URL : StringPool.BLANK}" label="${displayName}" localizeLabel="${false}" title="${displayName}" />

							<span class="divider">${StringPool.FORWARD_SLASH}</span>
						</c:when>
						<c:otherwise>
							<span class="breadcrumb-current breadcrumb-name ${composite.CSSClassName}" data-class-pk="${composite.classPK}" data-field-name="${composite.classNameFieldName}" title="${displayName}">
								<liferay-ui:message key="${displayName}" />
							</span>

							<c:set value="${composite.CSSClassName}" var="currentBreadcrumbCSSClassName" />
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<liferay-ui:message key="error" />
			</c:otherwise>
		</c:choose>
	</span>
</div>

<div class="breadcrumb-finder-container breadcrumb-finder-navigator" id="${htmlNamespace}breadcrumbFinderContainer">
	<div class="breadcrumb-finder-overlay"></div>

	<div class="breadcrumb-finder-content" id="${htmlNamespace}breadcrumbFinderContent">
		<span class="selected-container" id="${htmlNamespace}selectedContainer"></span>

		<aui:input cssClass="breadcrumb-input-edit" label="" name="${htmlNamespace}breadcrumbInputEdit" type="text" wrapperCssClass="breadcrumb-input-edit-wrapper" />
	</div>
</div>

<aui:script use="testray-breadcrumb-finder">
	new Liferay.Testray.BreadcrumbFinder(
		{
			alertContainerId: '#${htmlNamespace}testrayAlertContainer',
			breadcrumbNode: '#${htmlNamespace}breadcrumbDisplayContainer',
			containerNode: '#${htmlNamespace}breadcrumbFinderContainer',
			contentNode: '#${htmlNamespace}breadcrumbFinderContent',
			enableNavigation: true,
			enableTooltips: true,
			inputNode: '#${htmlNamespace}breadcrumbInputEdit',
			selectedContainerNode: '#${htmlNamespace}selectedContainer'
		}
	).render();
</aui:script>