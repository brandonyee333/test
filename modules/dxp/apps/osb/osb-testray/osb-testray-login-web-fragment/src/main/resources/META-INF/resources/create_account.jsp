<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div class="testray-login-wrapper">
	<div class="testray-login-container testray-login-container-wide">
		<img class="testray-logo" src="<%= PortalUtil.getPathContext(request) %>/images/testray-logo.svg" />

		<liferay-util:include page="/create_account.portal.jsp" servletContext="<%= application %>" />
	</div>
</div>