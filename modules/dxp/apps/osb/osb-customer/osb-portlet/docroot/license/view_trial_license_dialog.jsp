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

<%@ include file="/init.jsp" %>

<liferay-ui:message key="thank-you-for-your-interest-in-liferay-dxp-a-request-to-renew-your-trial-has-been-sent-to-our-sales-team-who-will-contact-you-shortly" />

<aui:button-row cssClass="pull-right">
	<aui:button onClick='<%= renderResponse.getNamespace() + "closeDialog(0);" %>' value="close" />
</aui:button-row>