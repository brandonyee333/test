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

<aui:script use="testray-base">
	var ${htmlNamespace}opener = Liferay.Util.getOpener();

	if (${htmlNamespace}opener && ${htmlNamespace}opener.${htmlNamespace}buildSearchContainer) {
		${htmlNamespace}opener.${htmlNamespace}buildSearchContainer(${userGroupUsersJSONArray});

		Liferay.Testray.closeWindow();

		<c:set var="message">
			<c:choose>
				<c:when test="${userGroupUsersJSONArray.length() == 1}">
					<liferay-ui:message key="1-user-was-added-successfully" />
				</c:when>
				<c:otherwise>
					<liferay-ui:message arguments="${userGroupUsersJSONArray.length()}" key="x-users-were-added-successfully" />
				</c:otherwise>
			</c:choose>
		</c:set>

		${htmlNamespace}opener.Liferay.Testray.addAlert(
			{
				containerId: '#${htmlNamespace}testrayAlertContainer',
				message: '${message}',
				type: 'success'
			}
		);
	}
</aui:script>