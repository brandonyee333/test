<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
try {
	ShoppingCart cart = ShoppingUtil.getCart(renderRequest);

	ShoppingCartLocalServiceUtil.updateCart(cart.getUserId(), cart.getGroupId(), StringPool.BLANK, StringPool.BLANK, 0, false);
}
catch (Exception e) {
}
%>

<liferay-util:include page="/tabs1.jsp" servletContext="<%= application %>">
	<liferay-util:param name="tabs1" value="cart" />
</liferay-util:include>

<div class="container-fluid-1280">
	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<div class="alert alert-success">
				<liferay-ui:message key="thank-you-for-your-purchase" />
			</div>

			<%
			long orderId = ParamUtil.getLong(request, "orderId");

			ShoppingOrder order = ShoppingOrderLocalServiceUtil.fetchShoppingOrder(orderId);
			%>

			<c:choose>
				<c:when test="<%= order != null %>">
					<liferay-ui:message key="your-order-number-is" /> <strong><%= HtmlUtil.escape(order.getNumber()) %></strong>. <liferay-ui:message key="you-will-receive-an-email-shortly-with-your-order-summary-and-further-details" />
				</c:when>
				<c:otherwise>
					<liferay-ui:message key="your-order-was-already-processed.-please-check-your-email-for-your-order-summary-and-further-details" />
				</c:otherwise>
			</c:choose>
		</aui:fieldset>
	</aui:fieldset-group>

	<portlet:renderURL var="continueShoppingURL">
		<portlet:param name="mvcRenderCommandName" value="/shopping/view" />
	</portlet:renderURL>

	<aui:button-row>
		<aui:button cssClass="btn-lg" href="<%= continueShoppingURL.toString() %>" value="continue-shopping" />
	</aui:button-row>
</div>