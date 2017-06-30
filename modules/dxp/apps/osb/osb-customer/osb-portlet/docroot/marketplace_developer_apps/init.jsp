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
DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.getDeveloperEntryByGroupId(themeDisplay.getScopeGroupId());

boolean administrator = false;

if (developerEntry.isCompany()) {
	CorpEntry corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(developerEntry.getDossieraAccountKey());

	if (UserGroupRoleLocalServiceUtil.hasUserGroupRole(user.getUserId(), themeDisplay.getScopeGroupId(), OSBConstants.ROLE_OSB_CORP_ADMIN_ID)) {
		administrator = true;
	}
}
else {
	administrator = true;
}

boolean developer = false;

if (administrator && (developerEntry != null) && developerEntry.isApproved()) {
	developer = true;
}
%>