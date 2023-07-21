<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<aui:script>
	var nestedPortlet = document.getElementById(
		'_<%= portletDisplay.getId() %>_main-content'
	);

	if (nestedPortlet != null) {
		nestedPortlet.removeAttribute('role');
	}
</aui:script>

<c:if test="<%= LayoutPermissionUtil.contains(permissionChecker, layout, ActionKeys.UPDATE) %>">
	<div class="alert alert-info hide" id="<portlet:namespace />nested-portlets-msg">
		<liferay-ui:message key="drag-applications-below-to-nest-them" />
	</div>

	<aui:script sandbox="<%= true %>">
		var portletWrapper = $('#p_p_id_<%= portletDisplay.getId() %>_');

		var nestedPortlet = portletWrapper.find('.portlet-boundary, .portlet-borderless-container');

		if (!nestedPortlet.length) {
			portletWrapper.find('#<portlet:namespace />nested-portlets-msg').first().removeClass('hide');
		}
	</aui:script>
</c:if>

<%
try {
	String templateId = (String)request.getAttribute(NestedPortletsWebKeys.TEMPLATE_ID + portletDisplay.getId());
	String templateContent = (String)request.getAttribute(NestedPortletsWebKeys.TEMPLATE_CONTENT + portletDisplay.getId());

	if (Validator.isNotNull(templateId) && Validator.isNotNull(templateContent)) {
		RuntimePageUtil.processTemplate(nestedPortletsDisplayContext.getLastForwardRequest(), response, new StringTemplateResource(templateId, templateContent));
	}
}
catch (Exception e) {
	_log.error("Cannot render Nested Portlets portlet", e);
}
finally {
	liferayPortletRequest.defineObjects(portletConfig, renderResponse);
}
%>

<%!
private static Log _log = LogFactoryUtil.getLog("com_liferay_nested_portlets_web.view_jsp");
%>