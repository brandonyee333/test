<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<iframe id="<portlet:namespace />iframe" scrolling="yes" src="<%= application.getContextPath() %>/marvel-proxy/app/marvel" style="bottom: 0px; height: 100%; left: 0px; min-height: 600px; overflow: hidden; overflow-x: hidden; overflow-y: hidden; position: relative; right: 0px; top: 0px; width: 100%;"></iframe>

<aui:script use="aui-autosize-iframe">
	var iframe = A.one('#<portlet:namespace />iframe');

	if (iframe) {
		iframe.plug(
			A.Plugin.AutosizeIframe,
			{
				monitorHeight: true
			}
		);
	}
</aui:script>