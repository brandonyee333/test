<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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