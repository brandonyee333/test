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

<%@ include file="/marketplace_server/init.jsp" %>

<%
String remoteMVCPath = ParamUtil.getString(request, "remoteMVCPath", "/marketplace/view.jsp");
%>

<c:choose>
	<c:when test='<%= remoteMVCPath.startsWith("/marketplace_server/") || remoteMVCPath.startsWith("/marketplace_server/") || windowState.equals(LiferayWindowState.EXCLUSIVE) %>'>

		<%
		if (remoteMVCPath.startsWith("/marketplace_server/")) {
			remoteMVCPath = remoteMVCPath.replace("/marketplace_server", "/marketplace_server");
		}
		%>

		<liferay-util:include page="<%= remoteMVCPath %>" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>
		<div class="marketplace-server view">
			<liferay-util:include page="/marketplace_navigation/view.jsp" servletContext="<%= application %>"  />

			<%
			if (!remoteMVCPath.startsWith("/marketplace/") || remoteMVCPath.contains(StringPool.DOUBLE_PERIOD)) {
				remoteMVCPath = "/marketplace/view.jsp";
			}
			%>

			<liferay-util:include page="<%= remoteMVCPath %>" servletContext="<%= application %>" />
		</div>
	</c:otherwise>
</c:choose>

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

	var MarketplaceUtil = {

		openMarketplacePurchased: function() {
			Liferay.MarketplaceMessenger.postMessage(
				{
					cmd: 'goto',
					panel: 'purchased'
				}
			);
		},

		openMarketplaceStore: function(appEntryId) {
			Liferay.MarketplaceMessenger.postMessage(
				{
					cmd: 'goto',
					panel: 'store',
					appEntryId: appEntryId
				}
			);
		}

	};

	Liferay.MarketplaceUtil = MarketplaceUtil;
</aui:script>