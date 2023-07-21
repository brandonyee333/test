<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/loop/views/init.jsp" %>

<%@ include file="/alloy_mvc/jsp/loop/views/toolbar.jspf" %>

<div id="${renderResponse.namespace}loopSurfaceMain">
	<div id="${renderResponse.namespace}loopSurfaceMain-default">
		<div class="loop-ui-kit" id="${renderResponse.namespace}uiKit"></div>

		<aui:script use="aui-node">
			var RC = window.RC;

			RC.actions.setPageTitle('${LanguageUtil.get(request, "ui-kit")}');

			new RC.UIKit(
				{
					element: A.one('#${renderResponse.namespace}uiKit').getDOMNode()
				}
			);
		</aui:script>
	</div>
</div>