<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<liferay-ui:icon
	id="delete"
	message="delete"
	url="javascript:;"
/>

<%
Layout selLayout = layoutsAdminDisplayContext.getSelLayout();

PortletURL deleteRedirectURL = PortletURLUtil.clone(layoutsAdminDisplayContext.getRedirectURL(), renderResponse);

deleteRedirectURL.setParameter("selPlid", String.valueOf(selLayout.getParentPlid()));
%>

<portlet:actionURL name="deleteLayout" var="deleteLayoutURL">
	<portlet:param name="mvcPath" value="/view.jsp" />
	<portlet:param name="redirect" value="<%= deleteRedirectURL.toString() %>" />
	<portlet:param name="selPlid" value="<%= String.valueOf(layoutsAdminDisplayContext.getSelPlid()) %>" />
</portlet:actionURL>

<aui:script use="aui-base">
	A.one('#<portlet:namespace />delete').on(
		'click',
		function() {
			if (confirm('<%= UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-the-selected-page") %>')) {
				submitForm(document.hrefFm, '<%= deleteLayoutURL %>');
			}
		}
	);
</aui:script>