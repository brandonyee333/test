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

<%
String assertionReqest = (String)request.getAttribute("assertionReqest");
%>

<div id="<portlet:namespace/>messageContainer"></div>

<aui:button id="startAuthentication" value="start" />

<aui:input name="responseJSON" showRequiredLabel="yes" type="hidden" />

<aui:script use="aui-base">
	Liferay.Loader.require(
		'<%=npmResolvedPackageName%>/js/yubico-webauthn/webAuthn',
		function (webauthn) {
			A.one('#<portlet:namespace />startAuthentication').on(
				'click',
				function (event) {
					webauthn.getAssertion(JSON.parse('<%=assertionReqest %>')).then(
						function (value) {
							var responseJSONInput = A.one(
								'#<portlet:namespace />responseJSON'
							);
							responseJSONInput.value = value.response;
						},
						function (reason) {
							var messageContainer = A.one(
								'#<portlet:namespace />messageContainer'
							);
							messageContainer.html(
								'<span class="alert alert-danger"><liferay-ui:message key="your-authenticator-was-unable-to-verify-your-credential" /></span>'
							);
						}
					);
				}
			);
		}
	);
</aui:script>