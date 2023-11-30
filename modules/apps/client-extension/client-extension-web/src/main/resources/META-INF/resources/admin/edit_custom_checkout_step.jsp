<%@ page import="com.liferay.client.extension.type.CustomCheckoutStepCET" %><%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/admin/init.jsp" %>

<%
EditClientExtensionEntryDisplayContext<CustomCheckoutStepCET> editClientExtensionEntryDisplayContext = (EditClientExtensionEntryDisplayContext)renderRequest.getAttribute(ClientExtensionAdminWebKeys.EDIT_CLIENT_EXTENSION_ENTRY_DISPLAY_CONTEXT);

CustomCheckoutStepCET customCheckoutStepCET = editClientExtensionEntryDisplayContext.getCET();
%>

<aui:field-wrapper cssClass="form-group">
	<aui:input label="checkout-step-label" name="checkoutStepLabel" required="<%= true %>" type="text" value="<%= customCheckoutStepCET.getCheckoutStepLabel() %>" />

	<aui:input label="checkout-step-name" name="checkoutStepName" required="<%= true %>" type="text" value="<%= customCheckoutStepCET.getCheckoutStepName() %>" />

	<aui:input label="checkout-step-order" name="checkoutStepOrder" required="<%= true %>" type="text" value="<%= customCheckoutStepCET.getCheckoutStepOrder() %>" />
</aui:field-wrapper>
