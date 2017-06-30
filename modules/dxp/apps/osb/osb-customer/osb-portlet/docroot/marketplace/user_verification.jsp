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

<%@ include file="/marketplace/init.jsp" %>

<div class="user-verification">
	<div class="aui-helper-hidden portlet-msg-error" id="<portlet:namespace />userVerificationError">
		<liferay-ui:message key="an-error-occurred-while-sending-the-verification-email-please-try-again-in-a-few-minutes" />
	</div>

	<div class="aui-helper-hidden portlet-msg-success" id="<portlet:namespace />userVerificationSuccess">
		<liferay-ui:message key="a-verification-email-was-sent" />
	</div>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />sendEmailAddressVerification',
		function() {
			var A = AUI();

			A.io.request(
				'<liferay-portlet:actionURL name="sendEmailAddressVerification" />',
				{
					on: {
						failure: function() {
							var errorMessageNode = A.one('#<portlet:namespace />userVerificationError');

							errorMessageNode.show();
						},
						success: function() {
							var successMessageNode = A.one('#<portlet:namespace />userVerificationSuccess');

							successMessageNode.show();

							<portlet:namespace />removeUserVerificationPopup();
						}
					}
				}
			);
		},
		['aui-io']
	);

	Liferay.provide(
		window,
		'<portlet:namespace />removeUserVerificationPopup',
		function() {
			var A = AUI();

			var popup = A.one('#<portlet:namespace />userVerificationPopup');

			if (popup) {
				popup.ancestor().removeChild(popup);
			}
		},
		['aui-base']
	);
</aui:script>

<aui:script use="aui-dialog,aui-io">
	var popup = new A.Dialog(
		{
			constrain2view: true,
			destroyOnClose: true,
			height: 167,
			id: '<portlet:namespace />userVerificationPopup',
			resizable: false,
			stack: true,
			width: 295,
			xy: [0, 56]
		}
	).plug(
		A.Plugin.IO,
		{
			uri: '<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="<%= mvcPathParam %>" value="/marketplace/user_verification_popup.jsp" /></liferay-portlet:renderURL>'
		}
	).render();

	popup.headerNode.hide();
</aui:script>