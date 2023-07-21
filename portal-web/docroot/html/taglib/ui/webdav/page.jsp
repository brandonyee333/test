<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_webdav_page") + StringPool.UNDERLINE;

String path = (String)request.getAttribute("liferay-ui:webdav:path");
%>

<div class="taglib-webdav" id="<%= randomNamespace %>webdav">
	<a class="show-webdav" href="javascript:;"><liferay-ui:message key="access-from-desktop" /></a>

	<aui:input name="webDavURL" type="resource" value='<%= themeDisplay.getPortalURL() + themeDisplay.getPathContext() + "/webdav" + themeDisplay.getScopeGroup().getFriendlyURL() + path %>' />
</div>

<aui:script use="aui-base">
	var webdavDiv = A.one('#<%= randomNamespace %>webdav');

	if (webdavDiv) {
		var webdavLink = webdavDiv.all('.show-webdav');

		if (webdavLink) {
			webdavLink.on(
				'click',
				function(event) {
					webdavDiv.toggleClass('visible');
				}
			);
		}
	}
</aui:script>