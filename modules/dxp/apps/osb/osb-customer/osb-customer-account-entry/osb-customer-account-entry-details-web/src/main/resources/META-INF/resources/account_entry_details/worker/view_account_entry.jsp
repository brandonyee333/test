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

<%@ include file="/init.jsp" %>

<%
AccountEntry accountEntry = (AccountEntry)renderRequest.getAttribute(AccountEntryDetailsWebKeys.ACCOUNT_ENTRY);

String tabs1 = ParamUtil.getString(request, "tabs1");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/view_account_entry");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));
%>

<h1>
	<%= HtmlUtil.escape(accountEntry.getName()) %>
</h1>

<liferay-ui:tabs
	names="overview,people,offerings,history"
	url="<%= portletURL.toString() %>"
/>

<div class="container-fluid-1280">
	<c:choose>
		<c:when test='<%= tabs1.equals("people") %>'>
		</c:when>
		<c:otherwise>
			<liferay-util:include page="/account_entry_details/worker/overview.jsp" servletContext="<%= application %>" />
		</c:otherwise>
	</c:choose>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />openDialog',
		function(title, url, popupId) {
			Liferay.Util.openWindow(
				{
					cache: false,
					dialog: {
						align: Liferay.Util.Window.ALIGN_CENTER,
						centered: true,
						height: 800,
						width: 870
					},
					id: popupId,
					title: title,
					uri: url
				}
			);
		},
		['aui-dialog', 'aui-overlay-manager', 'liferay-util-window']
	);
</aui:script>