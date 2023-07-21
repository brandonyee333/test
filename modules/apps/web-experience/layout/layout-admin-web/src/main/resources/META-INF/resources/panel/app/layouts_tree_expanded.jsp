<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div class="product-menu product-menu-expanded sidebar-body sidenav-fixed sidenav-menu-slider">
	<div class="panel">
		<div class="nav nav-equal-height">
			<liferay-util:include page="/panel/app/layouts_tree.jsp" servletContext="<%= application %>" />
		</div>
	</div>
</div>

<aui:script use="aui-base">
	var opener = Liferay.Util.getOpener();

	A.one('.product-menu').delegate(
		'click',
		function(event) {
			event.preventDefault();
			opener.document.location.href = event.currentTarget.get('href');
		},
		'a'
	);
</aui:script>