<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/permissions/init.jsp" %>

<aui:fieldset collapsed="<%= true %>" collapsible="<%= true %>" cssClass="options-group" label="permissions" markupView="lexicon">
	<span class="<%= labelCSSClass %>">
		<c:choose>
			<c:when test='<%= action.equals("publish") %>'>
				<liferay-ui:message key="publish-permissions" />
			</c:when>
			<c:when test='<%= action.equals("export") %>'>
				<liferay-ui:message key="export-permissions" />
			</c:when>
			<c:when test='<%= action.equals("import") %>'>
				<liferay-ui:message key="import-permissions" />
			</c:when>
		</c:choose>
	</span>

	<aui:input disabled="<%= disableInputs %>" label="<%= StringPool.BLANK %>" name="<%= PortletDataHandlerKeys.PERMISSIONS %>" type="toggle-switch" value="<%= MapUtil.getBoolean(parameterMap, PortletDataHandlerKeys.PERMISSIONS, false) %>" />

	<span class="<%= descriptionCSSClass %>">
		<c:choose>
			<c:when test="<%= global %>">
				<liferay-ui:message key="publish-global-permissions-help" />
			</c:when>
			<c:otherwise>
				<liferay-ui:message key="export-import-permissions-help" />
			</c:otherwise>
		</c:choose>
	</span>
</aui:fieldset>