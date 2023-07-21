<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/account_entry_details/init.jsp" %>

<aui:row>
	<aui:col width="<%= 100 %>">
		<liferay-util:include page="/account_entry_details/account_environments.jsp" servletContext="<%= application %>" />
	</aui:col>
</aui:row>