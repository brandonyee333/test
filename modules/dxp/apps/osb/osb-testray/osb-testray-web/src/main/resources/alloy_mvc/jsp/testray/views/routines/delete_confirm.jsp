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
	<liferay-util:param name="title" value="delete-routine" />
</liferay-util:include>

<%@ include file="/alloy_mvc/jsp/testray/views/content_start.jspf" %>

<portlet:actionURL var="deleteTestrayRoutineURL">
	<portlet:param name="controller" value="routines" />
	<portlet:param name="action" value="delete" />
</portlet:actionURL>

<aui:form action="${deleteTestrayRoutineURL}" method="post" name="deleteTestrayRoutineForm">
	<aui:input name="id" type="hidden" value="${testrayRoutine.testrayRoutineId}" />

	<div class="${popup ? "spacing-footer testray-modal-content" : StringPool.BLANK}">
		<c:choose>
			<c:when test="${testrayBuildsCount > 0}">
				<div class="alert alert-danger">
					<liferay-ui:message key="there-are-builds-that-are-associated-with-this-routine.-deleting-this-routine-will-delete-all-builds-runs-results-and-tasks-associated-with-this-routine.-this-action-cannot-be-undone" />
				</div>

				<c:set value="<strong>|${testrayRoutine.name}|</strong>" var="argumentsString" />

				<c:set value="${fn:split(argumentsString, StringPool.PIPE)}" var="arguments" />

				<c:set value='${AlloyLanguageUtil.format("enter-the-name-to-permanently-delete-x", arguments)}' var="nameFieldLabel" />

				<aui:input autoFocus="${true}" label="${nameFieldLabel}" name="name" onInput="${htmlNamespace}setButtonState('deleteRoutineButton', 'name', '${testrayRoutine.name}')" required="${true}" />
			</c:when>
			<c:otherwise>
				<liferay-ui:message key="are-you-sure-you-want-to-delete-this" />
			</c:otherwise>
		</c:choose>
	</div>

	<aui:button-row cssClass='${popup ? "testray-modal-footer" : StringPool.BLANK}'>
		<aui:button cssClass="testray-danger-button" disabled="${testrayBuildsCount > 0}" id="deleteRoutineButton" type="submit" value="delete-routine" />

		<aui:button onClick="Liferay.Testray.closeWindow();" value="cancel" />
	</aui:button-row>
</aui:form>

<%@ include file="/alloy_mvc/jsp/testray/views/content_end.jspf" %>

<%@ include file="/alloy_mvc/jsp/testray/views/end.jspf" %>

<aui:script>
	function ${htmlNamespace}setButtonState(submitButtonId, inputFieldId, matchName) {
		var A = AUI();

		var inputField = A.one('#' + inputFieldId);
		var submitButton = A.one('#' + submitButtonId);

		if (inputField && submitButton) {
			var inputFieldValue = inputField.val();

			var disabled = (inputFieldValue !== matchName);

			Liferay.Util.toggleDisabled(submitButton, disabled);
		}
	}
</aui:script>