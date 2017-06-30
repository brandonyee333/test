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

<%@ include file="/marketplace_apps/init.jsp" %>

<div class="marketplace-apps view">

	<%
	long marketplacePlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE);
	%>

	<liferay-portlet:renderURL plid="<%= marketplacePlid %>" portletName="<%= OSBPortletKeys.OSB_MARKETPLACE %>" var="marketplaceURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
		<portlet:param name="mvcPath" value="/marketplace/view.jsp" />
	</liferay-portlet:renderURL>

	<liferay-ui:header
		backLabel="marketplace"
		backURL="<%= marketplaceURL %>"
		title="purchased-apps"
	/>

	<p>
		<liferay-ui:message key="we-recommend-that-you-log-into-your-portal-instance" />
	</p>

	<h2>
		<liferay-ui:message key="select-a-project" />
	</h2>

	<div class="card-container">

		<%
		List<CorpProject> corpProjects = CorpProjectLocalServiceUtil.getUserCorpProjects(user.getUserId());

		for (CorpProject corpProject : corpProjects) {
		%>

			<liferay-portlet:renderURL var="viewProjectAppEntriesURL">
				<portlet:param name="mvcPath" value="/marketplace_apps/view_app_entries.jsp" />
				<portlet:param name="ownerClassName" value="<%= CorpProject.class.getName() %>" />
				<portlet:param name="ownerClassPK" value="<%= String.valueOf(corpProject.getCorpProjectId()) %>" />
			</liferay-portlet:renderURL>

			<div class="card project" data-viewAppEntriesURL="<%= viewProjectAppEntriesURL %>">
				<div class="card-body">
					<div class="name">
						<%= HtmlUtil.escape(corpProject.getName()) %>
					</div>

					<div class="owner">
						<%= StringUtil.shorten(HtmlUtil.escape(PortalUtil.getUserName(corpProject)), 20, StringPool.BLANK) %>
					</div>

					<liferay-util:include page="/marketplace_apps/app_icons.jsp" servletContext="<%= application %>">
						<liferay-util:param name="ownerClassNameId" value="<%= String.valueOf(PortalUtil.getClassNameId(CorpProject.class)) %>" />
						<liferay-util:param name="ownerClassPK" value="<%= String.valueOf(corpProject.getCorpProjectId()) %>" />
						<liferay-util:param name="viewAppEntriesURL" value="<%= viewProjectAppEntriesURL %>" />
					</liferay-util:include>
				</div>
			</div>

		<%
		}
		%>

		<div class="cleared"><!-- --></div>

		<liferay-portlet:renderURL var="viewUserAppEntriesURL">
			<portlet:param name="mvcPath" value="/marketplace_apps/view_app_entries.jsp" />
			<portlet:param name="ownerClassName" value="<%= User.class.getName() %>" />
			<portlet:param name="ownerClassPK" value="<%= String.valueOf(themeDisplay.getUserId()) %>" />
		</liferay-portlet:renderURL>

		<div class="card user" data-viewAppEntriesURL="<%= viewUserAppEntriesURL %>">
			<div class="card-body">
				<div class="name">
					<liferay-ui:message key="personal-use" />
				</div>

				<div class="owner">
					<%= StringUtil.shorten(HtmlUtil.escape(user.getFullName()), 20, StringPool.BLANK) %>
				</div>

				<liferay-util:include page="/marketplace_apps/app_icons.jsp" servletContext="<%= application %>">
					<liferay-util:param name="ownerClassNameId" value="<%= String.valueOf(PortalUtil.getClassNameId(User.class)) %>" />
					<liferay-util:param name="ownerClassPK" value="<%= String.valueOf(themeDisplay.getUserId()) %>" />
					<liferay-util:param name="viewAppEntriesURL" value="<%= viewUserAppEntriesURL %>" />
				</liferay-util:include>
			</div>
		</div>

		<div class="cleared"><!-- --></div>
	</div>
</div>

<aui:script use="aui-base,aui-tooltip">
	var cardContainer = A.one('.marketplace-apps .card-container');

	cardContainer.delegate(
		'click',
		function(event) {
			var target = event.target;

			if (target.hasClass('app') || target.ancestor('.app')) {
				return;
			}

			window.location = event.currentTarget.attr('data-viewAppEntriesURL');
		},
		'.card'
	);

	cardContainer.delegate(
		'mouseover',
		function(event) {
			var currentTarget = event.currentTarget;

			if (currentTarget.hasClass('tooltip-enabled')) {
				return;
			}

			currentTarget.addClass('tooltip-enabled');

			currentTarget.all('.app').each(
				function(app) {
					new A.Tooltip(
						{
							align: {
								node: app,
								points: ['bl', 'tl']
							},
							anim: {
								show: false,
								hide: false
							},
							bodyContent: app.attr('data-appEntryTitle'),
							hideDelay: 0,
							trigger: app
						}
					).render();
				}
			);
		},
		'.card'
	);
</aui:script>