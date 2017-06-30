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
String tabs1 = ParamUtil.getString(request, "tabs1", "purchased");

long assetCategoryId = ParamUtil.getLong(request, "assetCategoryId");

String assetEntryTitle = ParamUtil.getString(request, "assetEntryTitle");

int compatibility = ParamUtil.getInteger(request, "compatibility");

long corpProjectId = ParamUtil.getInteger(request, "corpProjectId");

String ownerClassName = StringPool.BLANK;
long ownerClassPK = 0;

if ((corpProjectId > 0) && CorpProjectLocalServiceUtil.hasUserCorpProject(user.getUserId(), corpProjectId)) {
	ownerClassName = CorpProject.class.getName();
	ownerClassPK = corpProjectId;
}
else {
	ownerClassName = User.class.getName();
	ownerClassPK = user.getUserId();
}

boolean supportsHotDeploy = ParamUtil.getBoolean(request, "supportsHotDeploy");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/marketplace_server/view_purchased.jsp");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("corpProjectId", String.valueOf(corpProjectId));

request.setAttribute("view_purchased.jsp-compatibility", compatibility);
request.setAttribute("view_purchased.jsp-ownerClassName", ownerClassName);
request.setAttribute("view_purchased.jsp-ownerClassPK", ownerClassPK);
request.setAttribute("view_purchased.jsp-supportsHotDeploy", supportsHotDeploy);
request.setAttribute("view_purchased.jsp-tabs1", tabs1);
%>

<div class="portlet-msg-error aui-helper-hidden" id="<portlet:namespace />errorMessageContainer">
	<liferay-ui:message key="your-request-failed-to-complete" />
</div>

<div class="marketplace-server view-purchased">
	<c:if test="<%= !supportsHotDeploy %>">
		<div class="portlet-msg-info">
			<liferay-ui:message key="the-plugin-hot-deployer-is-disabled-on-your-server.-please-download-and-install-apps-manually" />
		</div>
	</c:if>

	<%
	String tabs1Names = "purchased,all-purchased";

	PortalRelease portalRelease = PortalReleaseLocalServiceUtil.getPortalRelease(compatibility);

	if (portalRelease.isEE()) {
		tabs1Names += ",ee";
	}

	if (_hasDeveloperEntry(themeDisplay.getUserId())) {
		tabs1Names += ",developer";
	}
	%>

	<c:if test='<%= !tabs1Names.equals("purchased") %>'>
		<liferay-ui:tabs
			names="<%= tabs1Names %>"
			param="tabs1"
			url="<%= portletURL.toString() %>"
		/>
	</c:if>

	<c:choose>
		<c:when test='<%= tabs1.equals("all-purchased") %>'>
			<%@ include file="/marketplace_server/all_purchased_apps.jspf" %>
		</c:when>
		<c:when test='<%= tabs1.equals("developer") %>'>
			<%@ include file="/marketplace_server/developer_apps.jspf" %>
		</c:when>
		<c:when test='<%= tabs1.equals("ee") %>'>
			<%@ include file="/marketplace_server/ee_apps.jspf" %>
		</c:when>
		<c:when test='<%= tabs1.equals("purchased") %>'>
			<%@ include file="/marketplace_server/purchased_apps.jspf" %>
		</c:when>
	</c:choose>
</div>

<c:if test="<%= supportsHotDeploy %>">
	<aui:script>
		Liferay.provide(
			window,
			'<portlet:namespace />createAppList',
			function(appList) {
				var marketplaceAppListController = new Liferay.MarketplaceAppListController(
					{
						agreement: '<liferay-ui:message key="agreement" />',
						agreementURL: '<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/marketplace_server/accept_contract_entry.jsp" /><portlet:param name="compatibility" value="<%= String.valueOf(compatibility) %>" /></liferay-portlet:renderURL>',
						appList: appList,
						getAppURL: '<liferay-portlet:actionURL name="getApp" />',
						getInstalledAppsURL: '<liferay-portlet:actionURL name="getInstalledApps" />',
						errorMessageContainer: AUI().one('#<portlet:namespace />errorMessageContainer'),
						namespace: '<portlet:namespace />',
						restartRequiredMessage: '<liferay-ui:message key="are-you-sure-you-want-to-install-this-app" unicode="<%= true %>" />',
						uninstallAppURL: '<liferay-portlet:actionURL name="uninstallApp" />',
						updateAllAppsURL: '<liferay-portlet:actionURL name="updateApps" />',
						updateAppURL: '<liferay-portlet:actionURL name="updateApp"><portlet:param name="unlicensed" value='<%= String.valueOf(tabs1.equals("developer")) %>' /></liferay-portlet:actionURL>'
					}
				);

				Liferay.MarketplaceAppListControllers = Liferay.MarketplaceAppListControllers || [];

				Liferay.MarketplaceAppListControllers.push(marketplaceAppListController);

				return Liferay.MarketplaceAppListControllers;
			},
			['liferay-marketplace-app-list-controller']
		);

		function <portlet:namespace />updateAppEntry(appEntryId, orderUuid, productEntryName) {
			if (!Liferay.MarketplaceAppListControllers) {
				return false;
			}

			var appListController;

			AUI().Array.some(
				Liferay.MarketplaceAppListControllers,
				function(curAppListController) {
					if (curAppListController.hasApp(appEntryId)) {
						appListController = curAppListController;
					}

					if (appListController) {
						return true;
					}

					return false;
				}
			);

			if (!appListController && (Liferay.MarketplaceAppListControllers.length > 0)) {
				appListController = Liferay.MarketplaceAppListControllers[0];
			}

			appListController.updateAppEntry(appEntryId, orderUuid, productEntryName);
		};

		function <portlet:namespace />updateAllAppEntries() {
			if (!Liferay.MarketplaceAppListControllers) {
				return false;
			}

			var appListController;

			AUI().Array.some(
				Liferay.MarketplaceAppListControllers,
				function(curAppListController) {
					if (curAppListController._updateAllAppsNode != null) {
						appListController = curAppListController;

						return true;
					}

					return false;
				}
			);

			if (!appListController && (Liferay.MarketplaceAppListControllers.length > 0)) {
				appListController = Liferay.MarketplaceAppListControllers[0];
			}

			appListController.updateAllApps();
		}
	</aui:script>

	<aui:script use="aui-base">
		var appLists = A.all('.marketplace-server .app-list');

		appLists.each(<portlet:namespace />createAppList);

		<%
		long updateAppEntryId = ParamUtil.getLong(request, "updateAppEntryId");
		String updateAppEntryOrderUuid = ParamUtil.getString(request, "updateAppEntryOrderUuid");
		String updateProductEntryName = ParamUtil.getString(request, "updateProductEntryName");
		%>

		<c:if test="<%= updateAppEntryId > 0 %>">
			<portlet:namespace />updateAppEntry('<%= updateAppEntryId %>', '<%= HtmlUtil.escape(updateAppEntryOrderUuid) %>', '<%= HtmlUtil.escape(updateProductEntryName) %>');
		</c:if>
	</aui:script>
</c:if>

<%!
private boolean _hasDeveloperEntry(long userId) throws Exception {
	List<CorpEntry> corpEntries = CorpEntryLocalServiceUtil.getUserCorpEntries(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

	for (CorpEntry corpEntry : corpEntries) {
		DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.fetchDeveloperEntry(corpEntry.getDossieraAccountKey());

		if ((developerEntry != null) && developerEntry.isApproved()) {
			return true;
		}
	}

	DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.fetchUserDeveloperEntry(userId);

	if ((developerEntry != null) && developerEntry.isApproved()) {
		return true;
	}

	return false;
}
%>