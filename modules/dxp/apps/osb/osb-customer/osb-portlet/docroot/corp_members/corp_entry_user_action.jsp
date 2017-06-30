<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

User user2 = (User)row.getObject();

CorpEntry corpEntry = (CorpEntry)row.getParameter("corpEntry");

DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.fetchDeveloperEntry(corpEntry.getDossieraAccountKey());
%>

<div class="actions roles" data-userId="<%= user2.getUserId() %>">
	<aui:input data-roleId="<%= OSBConstants.ROLE_OSB_CORP_ADMIN_ID %>" data-userId="<%= user2.getUserId() %>" disabled="<%= themeDisplay.getUserId() == user2.getUserId() %>" label="user-admin" name='<%= "userAdmin" + user2.getUserId() %>' type="checkbox" value="<%= CorpEntryLocalServiceUtil.hasUserCorpEntryRole(user2.getUserId(), corpEntry.getCorpEntryId(), OSBConstants.ROLE_OSB_CORP_ADMIN_ID) %>" />

	<c:if test="<%= ((developerEntry != null) && developerEntry.isApproved()) %>">
		<aui:input data-roleId="<%= OSBConstants.ROLE_OSB_CORP_DEVELOPER_ID %>" data-userId="<%= user2.getUserId() %>" label="developer" name='<%= "developer" + user2.getUserId() %>' type="checkbox" value="<%= CorpEntryLocalServiceUtil.hasUserCorpEntryRole(user2.getUserId(), corpEntry.getCorpEntryId(), OSBConstants.ROLE_OSB_CORP_DEVELOPER_ID) %>" />
	</c:if>
</div>

<c:if test="<%= user.getUserId() == user2.getUserId() %>">
	<aui:script use="aui-base">
		A.one('.corp-members .company-members .actions.roles input[data-userId=<%= user.getUserId() %>]').on(
			'change',
			function(event) {
				document.location.reload(true);
			}
		);
	</aui:script>
</c:if>