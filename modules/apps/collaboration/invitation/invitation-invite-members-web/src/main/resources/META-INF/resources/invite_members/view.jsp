<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
Group group = GroupLocalServiceUtil.getGroup(scopeGroupId);
%>

<c:choose>
	<c:when test="<%= group.isUser() %>">
		<liferay-ui:message key="this-application-will-only-function-when-placed-on-a-site-page" />
	</c:when>
	<c:when test="<%= GroupPermissionUtil.contains(permissionChecker, group.getGroupId(), ActionKeys.UPDATE) %>">
		<portlet:renderURL var="inviteURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
			<portlet:param name="mvcPath" value="/invite_members/view_invite.jsp" />
		</portlet:renderURL>

		<aui:a cssClass="btn btn-default" href="javascript:;" id="inviteMembersButton" label="invite-members-to-this-site" />

		<aui:script position="inline" use="aui-base">
			var inviteMembersButton = A.one('#<portlet:namespace />inviteMembersButton');

			inviteMembersButton.on(
				'click',
				function() {
					Liferay.Util.openWindow(
						{
							dialog: {
								cssClass: 'so-portlet-invite-members',
								destroyOnHide: true,
								width: 700
							},
							dialogIframe: {
								bodyCssClass: 'dialog-with-footer'
							},
							title: '<%= portletDisplay.getTitle() %>',
							uri: '<%= HtmlUtil.escapeJS(inviteURL) %>'
						}
					);
				}
			);
		</aui:script>
	</c:when>
	<c:otherwise>
		<aui:script use="aui-base">
			var portlet = A.one('#p_p_id<portlet:namespace />');

			if (portlet) {
				portlet.hide();
			}
		</aui:script>
	</c:otherwise>
</c:choose>