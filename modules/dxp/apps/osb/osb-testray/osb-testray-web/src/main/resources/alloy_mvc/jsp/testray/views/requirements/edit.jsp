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
	<liferay-util:param name="title" value="${testrayRequirement.key}" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<aui:model-context bean="${testrayRequirement}" model="${testrayRequirementClass}" />

<portlet:actionURL var="updateTestrayRequirementURL">
	<portlet:param name="controller" value="requirements" />
	<portlet:param name="action" value="update" />
</portlet:actionURL>

<div class="testray-card testray-card-medium">
	<aui:form action="${updateTestrayRequirementURL}" cssClass="testray-form" method="post">
		<portlet:renderURL var="viewTestrayRequirementsURL">
			<portlet:param name="controller" value="requirements" />
			<portlet:param name="action" value="index" />
			<portlet:param name="testrayProjectId" value="${testrayRequirement.testrayProjectId}" />
		</portlet:renderURL>

		<aui:input name="redirect" type="hidden" value="${not empty param.redirect ? param.redirect : viewTestrayRequirementsURL}" />

		<aui:input name="id" type="hidden" value="${testrayRequirement.testrayRequirementId}" />
		<aui:input name="testrayProjectId" type="hidden" value="${testrayRequirement.testrayProjectId}" />

		<aui:input autoFocus="${true}" name="summary" />

		<aui:input name="linkURL" />

		<aui:input name="linkTitle" />

		<aui:select label="component" name="testrayComponentId">
			<c:forEach items="${testrayComponents}" var="testrayComponent">
				<aui:option label="${testrayComponent.name}" selected="${testrayRequirement.testrayComponentId == testrayComponent.testrayComponentId}" value="${testrayComponent.testrayComponentId}" />
			</c:forEach>
		</aui:select>

		<c:set value="${TestrayRichTextConstants.TYPE_MARKDOWN},${TestrayRichTextConstants.TYPE_PLAIN_TEXT}" var="types" />

		<aui:row cssClass="testray-form-section">
			<aui:col cssClass="testray-form-content" md="12">
				<testray:rich-input
					label="description"
					name="description"
					selectedType="${testrayRequirement.descriptionType}"
					types="${fn:split(types, StringPool.COMMA)}"
					value="${testrayRequirement.description}"
				/>
			</aui:col>
		</aui:row>

		<aui:row cssClass="testray-form-section">
			<aui:col cssClass="testray-form-content" md="12">
				<testray:rich-input
					label="goals"
					name="goals"
					selectedType="${testrayRequirement.goalsType}"
					types="${fn:split(types, StringPool.COMMA)}"
					value="${testrayRequirement.goals}"
				/>
			</aui:col>
		</aui:row>

		<aui:row cssClass="testray-form-section">
			<aui:col cssClass="testray-form-content" md="12">
				<testray:rich-input
					label="variations"
					name="variations"
					selectedType="${testrayRequirement.variationsType}"
					types="${fn:split(types, StringPool.COMMA)}"
					value="${testrayRequirement.variations}"
				/>
			</aui:col>
		</aui:row>

		<aui:button-row>
			<aui:button type="submit" />

			<aui:button href="${not empty param.redirect ? param.redirect : viewTestrayRequirementsURL}" value="cancel" />
		</aui:button-row>
	</aui:form>
</div>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>