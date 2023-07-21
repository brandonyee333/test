<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/flags/init.jsp" %>

<%
String randomNamespace = StringUtil.randomId() + StringPool.UNDERLINE;

String className = (String)request.getAttribute("liferay-flags:flags:className");
long classPK = GetterUtil.getLong((String)request.getAttribute("liferay-flags:flags:classPK"));
String contentTitle = GetterUtil.getString((String)request.getAttribute("liferay-flags:flags:contentTitle"));
String contentURL = GetterUtil.getString((String)request.getAttribute("liferay-flags:flags:contentURL"));
boolean label = GetterUtil.getBoolean((String)request.getAttribute("liferay-flags:flags:label"), true);
String message = GetterUtil.getString((String)request.getAttribute("liferay-flags:flags:message"), "flag[action]");
long reportedUserId = GetterUtil.getLong((String)request.getAttribute("liferay-flags:flags:reportedUserId"));

if (Validator.isNull(contentURL)) {
	contentURL = currentURL;
}

String cssClass = randomNamespace;

if (!TrashUtil.isInTrash(className, classPK)) {
	cssClass = randomNamespace + " flag-enable";
}
%>

<div class="taglib-flags" title="<liferay-ui:message key='<%= !TrashUtil.isInTrash(className, classPK) ? message : "flags-are-disabled-because-this-entry-is-in-the-recycle-bin" %>' />">
	<liferay-ui:icon
		cssClass="<%= cssClass %>"
		iconCssClass="icon-flag"
		label="<%= label %>"
		message="<%= message %>"
		url='<%= !TrashUtil.isInTrash(className, classPK) ? "javascript:;" : null %>'
	/>
</div>

<c:if test="<%= !TrashUtil.isInTrash(className, classPK) %>">
	<c:choose>
		<c:when test="<%= flagsGroupServiceConfiguration.guestUsersEnabled() || themeDisplay.isSignedIn() %>">
			<aui:script use="aui-io-plugin-deprecated,aui-modal">
				var icon = A.one('.<%= randomNamespace %>');

				if (icon) {
					icon.on(
						'click',
						function() {
							var popup = Liferay.Util.Window.getWindow(
								{
									dialog: {
										destroyOnHide: true,
										height: 500,
										width: 400
									},
									title: '<%= UnicodeLanguageUtil.get(request, "report-inappropriate-content") %>'
								}
							);

							var data = Liferay.Util.ns(
								'<%= PortalUtil.getPortletNamespace(PortletKeys.FLAGS) %>',
								{
									className: '<%= className %>',
									classPK: '<%= classPK %>',
									contentTitle: '<%= HtmlUtil.escapeJS(contentTitle) %>',
									contentURL: '<%= HtmlUtil.escapeJS(contentURL) %>',
									reportedUserId: '<%= reportedUserId %>'
								}
							);

							popup.plug(
								A.Plugin.IO, {
									data: data,
									uri: '<liferay-portlet:renderURL portletName="<%= PortletKeys.FLAGS %>" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcRenderCommandName" value="/flags/edit_entry" /></liferay-portlet:renderURL>'
								}
							);
						}
					);
				}
			</aui:script>
		</c:when>
		<c:otherwise>
			<div id="<%= randomNamespace %>signIn" style="display: none;">
				<liferay-ui:message key="please-sign-in-to-flag-this-as-inappropriate" />
			</div>

			<aui:script use="aui-modal">
				var icon = A.one('.<%= randomNamespace %>');

				if (icon) {
					icon.on(
						'click',
						function(event) {
							var popup = Liferay.Util.Window.getWindow(
								{
									dialog: {
										bodyContent: A.one('#<%= randomNamespace %>signIn').html(),
										destroyOnHide: true,
										height: 400,
										width: 400
									},
									title: '<%= UnicodeLanguageUtil.get(request, "report-inappropriate-content") %>'
								}
							);

							event.preventDefault();
						}
					);
				}
			</aui:script>
		</c:otherwise>
	</c:choose>
</c:if>