<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Group group = themeDisplay.getScopeGroup();
LayoutSet layoutSet = themeDisplay.getLayoutSet();
%>

<c:choose>
	<c:when test="<%= group.isUser() && layoutSet.isPrivateLayout() %>">

		<%
		long mbThreadId = ParamUtil.getLong(request, "mbThreadId");
		%>

		<div class="private-messaging-container" id="<portlet:namespace />privateMessagingContainer">
			<c:choose>
				<c:when test="<%= !themeDisplay.isSignedIn() %>">
					<liferay-ui:message key="please-sign-in-to-use-the-private-messaging-portlet" />
				</c:when>
				<c:when test="<%= (mbThreadId != 0) && PrivateMessagingUtil.isUserPartOfThread(user.getUserId(), mbThreadId) %>">
					<div class="thread">
						<%@ include file="/view_thread.jspf" %>
					</div>
				</c:when>
				<c:otherwise>
					<div class="messages">
						<%@ include file="/view_messages.jspf" %>
					</div>
				</c:otherwise>
			</c:choose>
		</div>

		<aui:script use="liferay-plugin-privatemessaging">
			new Liferay.PrivateMessaging(
				{
					baseActionURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), PortletRequest.ACTION_PHASE) %>',
					baseRenderURL: '<%= PortletURLFactoryUtil.create(request, portletDisplay.getId(), PortletRequest.RENDER_PHASE) %>',
					namespace: '<portlet:namespace />',
					portletId: '<%= portletDisplay.getId() %>'
				}
			);
		</aui:script>
	</c:when>
	<c:otherwise>
		<div class="alert alert-danger">
			<liferay-ui:message key="this-application-only-functions-when-placed-on-a-user's-private-page" />
		</div>
	</c:otherwise>
</c:choose>