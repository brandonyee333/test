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