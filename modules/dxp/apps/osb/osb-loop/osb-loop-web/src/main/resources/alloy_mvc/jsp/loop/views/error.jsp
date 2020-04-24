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