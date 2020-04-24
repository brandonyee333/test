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

<c:set value='${requestScope["testray:filter:filterElements"]}' var="filterElements" />
<c:set value='${requestScope["testray:filter:pinnable"]}' var="pinnable" />

<div class="filter-container">
	<div class="filter-selected-container">
		<c:forEach items="${filterElements}" var="filterElement">
			<c:choose>
				<c:when test='${(filterElement.type == "boolean") && (not testray:isNull(param[filterElement.name]))}'>
					<span class="filter-selected">
						<liferay-ui:message key="${param[filterElement.name] ? filterElement.selectedLabel : filterElement.nullableLabel}" />
					</span>
				</c:when>
				<c:when test='${(filterElement.type == "checkboxes") && (not testray:isNull(param[filterElement.name]))}'>
					<span class="filter-selected">
						<c:if test="${not empty filterElement.labelHelper}">
							<c:set var="label">
								<c:forEach items="${fn:split(param[filterElement.name], StringPool.COMMA)}" var="value" varStatus="status">
									<liferay-ui:message key="${filterElement.labelHelper.getStatusLabel(value)}" />${status.last ? "" : StringPool.COMMA_AND_SPACE}
								</c:forEach>
							</c:set>
						</c:if>

						<liferay-ui:message arguments="${(not empty filterElement.labelHelper) ? label : param[filterElement.name]}" key="${filterElement.selectedLabel}" />
					</span>
				</c:when>
				<c:when test='${(filterElement.type == "date") && (not testray:isNull(param[filterElement.name]))}'>
					<span class="filter-selected">
						<liferay-ui:message arguments="${fn:escapeXml(param[filterElement.name])}" key="${filterElement.selectedLabel}" />
					</span>
				</c:when>
				<c:when test='${(filterElement.type == "select-model") && (not testray:isNull(param[filterElement.name]))}'>
					<span class="filter-selected">
						<c:choose>
							<c:when test="${filterElement.nullable && (param[filterElement.name] == 0)}">
								<liferay-ui:message arguments="${filterElement.nullableLabel}" key="${filterElement.selectedLabel}" />
							</c:when>
							<c:when test="${param[filterElement.name] > 0}">
								<c:set value="${filterElement.getModel(param[filterElement.name])}" var="model" />

								<liferay-ui:message arguments="${model[filterElement.optionLabelProperty]}" key="${filterElement.selectedLabel}" />
							</c:when>
						</c:choose>
					</span>
				</c:when>
				<c:when test='${(filterElement.type == "select-models") && (not testray:isNull(param[filterElement.name]))}'>
					<span class="filter-selected">
						<c:set var="arguments">
							<c:forEach items="${filterElement.selectedValue}" var="value" varStatus="status">
								<c:set value="${filterElement.getModel(value)}" var="model" />

								${model[filterElement.optionLabelProperty]}${status.last ? "" : ","}
							</c:forEach>
						</c:set>

						<liferay-ui:message arguments="${arguments}" key="${filterElement.selectedLabel}" />
					</span>
				</c:when>
				<c:when test='${(filterElement.type == "text") && (not testray:isNull(param[filterElement.name]))}'>
					<span class="filter-selected">
						<liferay-ui:message arguments="${fn:escapeXml(param[filterElement.name])}" key="${filterElement.selectedLabel}" />
					</span>
				</c:when>
				<c:when test='${filterElement.type == "textarea"}'>
					<c:set value="${filterElement.name}BlankOnly" var="nullableParam" />

					<c:choose>
						<c:when test='${filterElement.nullable && (param[nullableParam] == "true")}'>
							<span class="filter-selected">
								<liferay-ui:message key="${filterElement.nullableLabel}" />
							</span>
						</c:when>
						<c:when test="${not testray:isNull(param[filterElement.name])}">
							<span class="filter-selected">
								<liferay-ui:message arguments="${fn:escapeXml(param[filterElement.name])}" key="${filterElement.selectedLabel}" />
							</span>
						</c:when>
					</c:choose>
				</c:when>
			</c:choose>
		</c:forEach>

		<c:if test="${not empty param.templateStatus}">
			<span class="filter-selected">
				<liferay-ui:message arguments="${(param.templateStatus == TestrayBuildConstants.STATUS_DEFAULT) ? 'activated' : 'deactivated'}" key="status-x" />
			</span>
		</c:if>
	</div>

	<jsp:useBean
		class="java.util.HashMap"
		id="appliedFilters"
	/>

	<c:forEach items="${filterElements}" var="filterElement">
		<c:if test="${not testray:isNull(param[filterElement.name])}">
			<c:set property="${filterElement.name}" target="${appliedFilters}" value="${param[filterElement.name]}" />
		</c:if>
	</c:forEach>

	<c:if test="${pinnable}">
		<aui:button cssClass='filter-pin ${TestrayFilterUtil.isFilterCurrent(pageContext.request, appliedFilters, testrayProjectId) ? "filter-pin-set" : ""}' icon="icon-pushpin" id="filterPinButton" onClick="${htmlNamespace}updateFilterPreferences();" primary="${false}" value="" />
	</c:if>

	<aui:button cssClass="filter-toggle" icon="icon-filter" name="filterToggle" />
</div>

<div class="filter-popover hide">
	<div class="testray-filter-header">
		<aui:form cssClass="testray-filter-form" name="testrayFilterForm" onReset="${htmlNamespace}filterFormFields(this);">
			<aui:input cssClass="testray-filter-field" id="testrayFilterField" label="" name="filterTemplates" onInput="${htmlNamespace}filterFormFields(this);" placeholder="search-filters" />

			<aui:button cssClass="testray-filter-clear" icon="icon-remove" type="reset" value="" />
		</aui:form>
	</div>

	<form action="${portletURL}" cssClass="filter-form" id="${htmlNamespace}filterForm" method="get" name="${htmlNamespace}filterForm" onSubmit="event.preventDefault(); ${htmlNamespace}submitFilter();">
		<div class="popover-content testray-filter-content">
			<c:if test="${not empty param.delta}">
				<aui:input name="delta" type="hidden" value="${param.delta}" />
			</c:if>

			<aui:input name="p_p_state" type="hidden" value="<%= windowState %>" />

			<c:if test="${not empty orderByCol}">
				<aui:input name="orderByCol" type="hidden" value="${orderByCol}" />
			</c:if>

			<c:if test="${not empty orderByType}">
				<aui:input name="orderByType" type="hidden" value="${orderByType}" />
			</c:if>

			<ul class="testray-filter-list" id="${htmlNamespace}testrayFilterList">
				<li class="disabled hide no-results" id="${htmlNamespace}noResultsFilterMessage">
					<liferay-ui:message key="there-are-no-matching-results" />
				</li>