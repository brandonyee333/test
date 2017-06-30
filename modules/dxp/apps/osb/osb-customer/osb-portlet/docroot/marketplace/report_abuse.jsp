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

<%
String className = ParamUtil.getString(request, "className");
long classPK = ParamUtil.getLong(request, "classPK");
String contentTitle = ParamUtil.getString(request, "contentTitle");
String contentURL = ParamUtil.getString(request, "contentURL");
long reportedUserId = ParamUtil.getLong(request, "reportedUserId");
%>

<div class="marketplace report-abuse">
	<div class="portlet-report-abuse" id="<portlet:namespace />reportAbusePopup">
		<aui:form method="post" name="reportAbuseForm">
			<p>
				<%= LanguageUtil.format(pageContext, "you-are-about-to-report-a-violation-of-our-x-terms-of-use.-all-reports-are-strictly-confidential", themeDisplay.getPathMain() + "/portal/terms_of_use") %>
			</p>

			<aui:input label="reason-for-the-report" name="reason" />

			<aui:button-row>
				<aui:button name="reportAbuseSubmit" type="submit" />
			</aui:button-row>
		</aui:form>
	</div>

	<div class="aui-helper-hidden" id="<portlet:namespace />confirmation">
		<p><strong><liferay-ui:message key="thank-you-for-your-report" /></strong></p>

		<p><%= LanguageUtil.format(pageContext, "although-we-cannot-disclose-our-final-decision,-we-do-review-every-report-and-appreciate-your-effort-to-make-sure-x-is-a-safe-environment-for-everyone", HtmlUtil.escape(company.getName())) %></p>
	</div>

	<div class="aui-helper-hidden" id="<portlet:namespace />error">
		<p><strong><liferay-ui:message key="an-error-occurred-while-sending-the-report.-please-try-again-in-a-few-minutes" /></strong></p>
	</div>
</div>

<aui:script use="aui-dialog">
	function <portlet:namespace />reportAbuse() {
		var reasonNode = A.one('#<portlet:namespace />reason');
		var reason = (reasonNode && reasonNode.val()) || '<%= UnicodeLanguageUtil.get(pageContext, "no-reason-specified") %>';

		var reportAbusePopupNode = A.one('#<portlet:namespace />reportAbusePopup');
		var errorMessageNode = A.one('#<portlet:namespace />error');
		var confirmationMessageNode = A.one('#<portlet:namespace />confirmation');

		var displayNode = function(node) {
			reportAbusePopupNode.hide();
			errorMessageNode.hide();
			confirmationMessageNode.hide();

			node.show();
		};

		A.io.request(
			'<portlet:actionURL name="reportAbuse" />',
			{
				data: {
					<portlet:namespace />className: '<%= HtmlUtil.escape(className) %>',
					<portlet:namespace />classPK: '<%= classPK %>',
					<portlet:namespace />contentTitle: '<%= HtmlUtil.escape(contentTitle) %>',
					<portlet:namespace />contentURL: '<%= HtmlUtil.escape(contentURL) %>',
					<portlet:namespace />reason: reason,
					<portlet:namespace />reportedUserId: '<%= reportedUserId %>'
				},
				on: {
					failure: function() {
						displayNode(errorMessageNode);
					},
					success: function() {
						displayNode(confirmationMessageNode);
					}
				}
			}
		);
	}

	Liferay.Util.focusFormField('#<portlet:namespace />reason');

	A.one('#<portlet:namespace />reportAbuseSubmit').on(
		'click',
		function(event) {
			<portlet:namespace />reportAbuse();

			event.halt();
		}
	);
</aui:script>