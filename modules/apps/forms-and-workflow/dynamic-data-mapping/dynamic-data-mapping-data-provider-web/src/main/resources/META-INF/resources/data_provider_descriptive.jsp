<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

DDMDataProviderInstance ddmDataProviderInstance = (DDMDataProviderInstance)row.getObject();
%>

<liferay-ui:app-view-entry
	actionJsp="/data_provider_action.jsp"
	actionJspServletContext="<%= application %>"
	author="<%= ddmDataProviderInstance.getUserName() %>"
	createDate="<%= ddmDataProviderInstance.getCreateDate() %>"
	description="<%= ddmDataProviderInstance.getDescription(locale) %>"
	displayStyle="descriptive"
	groupId="<%= ddmDataProviderInstance.getGroupId() %>"
	markupView="lexicon"
	modifiedDate="<%= ddmDataProviderInstance.getModifiedDate() %>"
	rowCheckerId="<%= String.valueOf(ddmDataProviderInstance.getDataProviderInstanceId()) %>"
	rowCheckerName="<%= DDMDataProviderInstance.class.getSimpleName() %>"
	showCheckbox="<%= false %>"
	thumbnailDivStyle="height: 146px; width: 146px;"
	thumbnailSrc="<%= ddmDataProviderDisplayContext.getUserPortraitURL(ddmDataProviderInstance.getUserId()) %>"
	thumbnailStyle="max-height: 128px; max-width: 128px;"
	title="<%= ddmDataProviderInstance.getName(locale) %>"
	url="<%= (String)request.getAttribute(WebKeys.SEARCH_ENTRY_HREF) %>"
/>