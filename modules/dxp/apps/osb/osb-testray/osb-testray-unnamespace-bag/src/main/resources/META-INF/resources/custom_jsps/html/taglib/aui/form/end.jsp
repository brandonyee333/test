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

<%@ include file="/html/taglib/aui/form/init.jsp" %>

<liferay-util:include page="/html/taglib/aui/form/end.portal.jsp" servletContext="<%= application %>" />

<c:if test='<%= (portletResponse != null) && Objects.equals(portletResponse.getNamespace(), "_com_liferay_osb_testray_portlet_TestrayPortlet_") %>'>
	<aui:script use="liferay-form">
		Liferay.fire(
			'<%= namespace + HtmlUtil.escapeJS(name) %>formReady',
			{
				formName: '<%= namespace + HtmlUtil.escapeJS(name) %>'
			}
		);

		var testrayForm = Liferay.Form.get('<%= namespace + HtmlUtil.escapeJS(name) %>');

		if (testrayForm) {
			var testrayFormNode = testrayForm.formNode;
			var testrayFormValidator = testrayForm.formValidator;

			testrayFormValidator.on(
				'submit',
				function() {
					var submitButton = testrayFormNode.all('button[type="submit"]');

					Liferay.Util.disableFormButtons(submitButton, testrayForm);
				}
			);
		}
	</aui:script>
</c:if>