<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/loop/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/loop/views/toolbar.jspf" %>

<portlet:renderURL var="homeURL">
	<portlet:param name="controller" value="home" />
	<portlet:param name="action" value="index" />
</portlet:renderURL>

<div class="error-page">
	<div class="message">
		<div class="title">404</div>

		<liferay-ui:message key="we-cant-seem-to-find-what-you-are-looking-for" />

		<div class="custom-message">
			<%@ include file="/alloy_mvc/jsp/loop/views/custom_error.jspf" %>
		</div>

		<div class="footer">
			<a href="${homeURL}">
				<liferay-ui:message key="back-to-loop" />
			</a>
		</div>
	</div>

	<%@ include file="/images/error.svg" %>
</div>