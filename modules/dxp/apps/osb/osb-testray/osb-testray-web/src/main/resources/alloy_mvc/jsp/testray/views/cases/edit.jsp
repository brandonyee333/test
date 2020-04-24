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
	<liferay-util:param name="title" value="${testrayCase.name}" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testrayCase}" model="<%= TestrayCase.class %>" />

<portlet:actionURL var="updateTestrayCaseURL">
	<portlet:param name="controller" value="cases" />
	<portlet:param name="action" value="update" />
</portlet:actionURL>

<div class="testray-card testray-card-medium">
	<aui:form action="${updateTestrayCaseURL}" cssClass="testray-form" method="post" name="fm" onSubmit="event.preventDefault(); ${htmlNamespace}submit();">
		<portlet:renderURL var="viewTestrayCasesURL">
			<portlet:param name="controller" value="cases" />
			<portlet:param name="action" value="index" />
			<portlet:param name="testrayProjectId" value="${testrayProjectId}" />

			<testray:filter-preference
				value='${TestrayFilterUtil.getUserFilterPreferences(pageContext.request, "cases", "index", testrayProjectId)}'
			/>
		</portlet:renderURL>

		<portlet:renderURL var="viewTestraySuiteURL">
			<portlet:param name="controller" value="suites" />
			<portlet:param name="action" value="view" />
			<portlet:param name="id" value="${testraySuiteId}" />
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="${not empty param.redirect ? param.redirect : ((testraySuiteId <= 0) ? viewTestrayCasesURL : viewTestraySuiteURL)}" />

		<aui:input name="id" type="hidden" value="${testrayCase.testrayCaseId}" />

		<aui:input name="testrayComponentIds" type="hidden" />
		<aui:input name="testrayProjectId" type="hidden" />

		<aui:row cssClass="testray-form-section">
			<aui:col cssClass="testray-form-description" md="3">
				<h2>
					<liferay-ui:message key="case-name" />
				</h2>
			</aui:col>

			<aui:col cssClass="testray-form-content" md="9">
				<aui:input autoFocus="${true}" label="" name="name" placeholder="enter-the-case-name" />
			</aui:col>
		</aui:row>

		<aui:row cssClass="testray-form-section">
			<aui:col cssClass="testray-form-description" md="3">
				<h2>
					<liferay-ui:message key="details" />
				</h2>
			</aui:col>

			<aui:col cssClass="testray-form-content" md="9">
				<aui:select cssClass="testray-input-small" label="priority" name="priority" showEmptyOption="${false}">
					<aui:option label="5" selected="${testrayCase.priority == 5}" value="5" />
					<aui:option label="4" selected="${testrayCase.priority == 4}" value="4" />
					<aui:option label="3" selected="${testrayCase.priority == 3}" value="3" />
					<aui:option label="2" selected="${testrayCase.priority == 2}" value="2" />
					<aui:option label="1" selected="${testrayCase.priority == 1}" value="1" />
				</aui:select>

				<aui:select label="type" name="testrayCaseTypeId">
					<c:forEach items="${testrayCaseTypes}" var="testrayCaseType">
						<aui:option label="${testrayCaseType.name}" selected="${testrayCase.testrayCaseTypeId == testrayCaseType.testrayCaseTypeId}" value="${testrayCaseType.testrayCaseTypeId}" />
					</c:forEach>
				</aui:select>

				<aui:select label="main-component" name="testrayComponentId">
					<c:forEach items="${testrayComponents}" var="testrayComponent">
						<aui:option label="${testrayComponent.name}" selected="${testrayCase.testrayComponentId == testrayComponent.testrayComponentId}" value="${testrayComponent.testrayComponentId}" />
					</c:forEach>
				</aui:select>

				<c:if test="${!PortletPropsValues.TESTRAY_SIMPLIFIED_CASES}">
					<liferay-ui:input-move-boxes
						leftBoxName="availableTestrayComponentFields"
						leftList="${availableTestrayComponents}"
						leftReorder="<%= Boolean.FALSE.toString() %>"
						leftTitle="available"
						rightBoxName="currentTestrayComponentFields"
						rightList="${currentTestrayComponents}"
						rightTitle="current"
					/>
				</c:if>

				<aui:input bean="" cssClass="testray-input-small" min="0" name="estimatedDuration" type="number" value="${testrayCase.estimatedDuration}" />
			</aui:col>
		</aui:row>

		<aui:row cssClass="testray-form-section">
			<aui:col cssClass="testray-form-content" md="12">
				<c:set value="${TestrayRichTextConstants.TYPE_MARKDOWN},${TestrayRichTextConstants.TYPE_PLAIN_TEXT}" var="types" />

				<testray:rich-input
					label="description"
					name="description"
					selectedType="${testrayCase.descriptionType}"
					types="${fn:split(types, StringPool.COMMA)}"
					value="${testrayCase.description}"
				/>
			</aui:col>
		</aui:row>

		<aui:row cssClass="testray-form-section">
			<aui:col cssClass="testray-form-content" md="12">
				<testray:rich-input
					label="steps"
					name="steps"
					selectedType="${testrayCase.stepsType}"
					types="${TestrayRichTextConstants.TYPES}"
					value="${testrayCase.steps}"
				/>
			</aui:col>
		</aui:row>

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="${not empty param.redirect ? param.redirect : ((testraySuiteId <= 0) ? viewTestrayCasesURL : viewTestraySuiteURL)}" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	Liferay.provide(
		window,
		'${htmlNamespace}submit',
		function() {
			<c:if test="${!PortletPropsValues.TESTRAY_SIMPLIFIED_CASES}">
				document.${htmlNamespace}fm.${htmlNamespace}testrayComponentIds.value = Liferay.Util.listSelect(document.${htmlNamespace}fm.${htmlNamespace}currentTestrayComponentFields);
			</c:if>

			submitForm(document.${htmlNamespace}fm);
		},
		['liferay-util-list-fields']
	);
</aui:script>