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
		<aui:row id="peopleIndex" />

		<aui:script use="aui-node">
			var RC = window.RC;

			RC.render(
				RC.LoopApp,
				{},
				A.one('#${renderResponse.namespace}peopleIndex').getDOMNode()
			);
		</aui:script>
	</div>
</div>