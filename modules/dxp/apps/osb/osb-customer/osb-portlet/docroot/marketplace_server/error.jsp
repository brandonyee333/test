<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<div class="error marketplace">
	<h1>
		<liferay-ui:message key="page-not-found-error-404" />
	</h1>

	<p><liferay-ui:message key="were-sorry-the-page-you-requested-was-not-found" /></p>

	<p class="comic">
		<img src="<%= PortalUtil.getPathContext(request) %>/images/error_404.png" />
	</p>
</div>

<aui:script position="inline" use="liferay-marketplace-messenger">
	Liferay.MarketplaceMessenger.init(
		{
			targetURI: window.parent.location.toString(),
			targetFrame: window.parent
		},
		{
			cmd: 'init',
			height: A.getBody().height() + 50
		}
	);

	A.on(
		'click',
		function(event) {
			Liferay.MarketplaceMessenger.postMessage(
				{
					cmd: 'resize',
					height: A.getBody().height() + 50
				}
			);
		}
	);
</aui:script>