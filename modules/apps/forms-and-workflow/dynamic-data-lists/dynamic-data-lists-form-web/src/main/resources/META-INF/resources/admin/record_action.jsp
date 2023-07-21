<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

DDLRecord record = (DDLRecord)row.getObject();
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:if test="<%= ddlFormAdminDisplayContext.isShowViewEntriesRecordSetIcon(ddlFormAdminDisplayContext.getRecordSet()) %>">
		<portlet:renderURL var="viewURL">
			<portlet:param name="mvcPath" value="/admin/view_record.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="recordId" value="<%= String.valueOf(record.getRecordId()) %>" />
			<portlet:param name="recordSetId" value="<%= String.valueOf(record.getRecordSetId()) %>" />
		</portlet:renderURL>

		<liferay-ui:icon
			message="view"
			url="<%= viewURL %>"
		/>
	</c:if>

	<c:if test="<%= ddlFormAdminDisplayContext.isShowDeleteRecordSetIcon(ddlFormAdminDisplayContext.getRecordSet()) %>">
		<portlet:actionURL name="deleteRecord" var="deleteURL">
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="recordId" value="<%= String.valueOf(record.getRecordId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			url="<%= deleteURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>