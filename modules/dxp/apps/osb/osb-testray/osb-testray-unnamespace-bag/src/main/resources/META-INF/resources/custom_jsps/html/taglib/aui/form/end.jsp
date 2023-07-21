<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
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