<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/alloy_mvc/jsp/testray/views/init.jsp" %>

<aui:script use="aui-base">
	var dialog = Liferay.Util.getWindow();

	if (dialog) {
		dialog.hide();

		A.on(
			'domready',
			function() {
				dialog.fire('visibleChange');
			}
		);
	}
</aui:script>