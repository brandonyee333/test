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

<%@ include file="/marketplace/init.jsp" %>

<%
long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

AppVersion appVersion = appEntry.getApprovedAppVersion();

List<AppPackage> appPackages = AppPackageLocalServiceUtil.getAppPackages(appVersion.getAppVersionId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

AppPackage appPackage = null;

int compatibility = ParamUtil.getInteger(request, "compatibility");

if (compatibility > 0) {
	appPackage = AppPackageLocalServiceUtil.fetchAppPackage(appVersion.getAppVersionId(), compatibility);
}

if (appPackage == null) {
	appPackage = appPackages.get(0);

	compatibility = appPackage.getCompatibility();
}
%>

<div class="app-entry-security-manager">
	<div class="callout-b-head">
		<div class="callout-content">
			<h2 class="title">
				<liferay-ui:message key="access-control" />
			</h2>
		</div>
	</div>

	<div class="callout-e pseudo-portlet-content">
		<div class="pacl">
			<div>
				<aui:select name="supported-framework-versions" onChange="location.href = this.value;">

					<%
					PortletURL appPackageURL = renderResponse.createRenderURL();

					appPackageURL.setParameter(mvcPathParam, "/marketplace/view_app_entry.jsp");
					appPackageURL.setParameter("tabs1", "access-control");
					appPackageURL.setParameter("appEntryId", String.valueOf(appEntry.getAppEntryId()));
					appPackageURL.setParameter("appVersionId", String.valueOf(appVersion.getAppVersionId()));

					for (AppPackage curAppPackage : appPackages) {
						appPackageURL.setParameter("compatibility", String.valueOf(curAppPackage.getCompatibility()));
					%>

						<aui:option label="<%= PortalReleaseUtil.getVersionName(curAppPackage) %>" selected="<%= compatibility == curAppPackage.getCompatibility() %>" value="<%= appPackageURL.toString() %>" />

					<%
					}
					%>

				</aui:select>
			</div>

			<%
			int appPackagePluginsCount = AppPackagePluginLocalServiceUtil.getAppPackagePluginsCount(appPackage.getAppPackageId());
			int paclDisabledAppPackagePluginsCount = AppPackagePluginLocalServiceUtil.getAppPackagePluginsCount(appPackage.getAppPackageId(), false);

			List<AppPackagePlugin> paclEnabledAppPackagePlugins = AppPackagePluginLocalServiceUtil.getAppPackagePlugins(appPackage.getAppPackageId(), true);
			%>

			<div class="portlet-msg-info">
				<liferay-ui:message arguments='<%= new Object[] {paclDisabledAppPackagePluginsCount, appPackagePluginsCount, "/documentation/liferay-portal/6.1/development/-/ai/lp-6-1-dgen11-plugin-security-management-0"} %>' key="x-of-x-plugins-have-liferays-pacl-security-manager-disabled" />
			</div>

			<div class="security-manager-container">
				<liferay-ui:panel-container extended="<%= false %>" id="assetPublisherSelectionStylePanelContainer">

					<%
					for (int i = 0; i < paclEnabledAppPackagePlugins.size(); i++) {
						AppPackagePlugin appPackagePlugin = paclEnabledAppPackagePlugins.get(i);

						String pluginName = LanguageUtil.format(pageContext, "plugin-x", i + 1);
					%>

						<liferay-ui:panel collapsible="<%= true %>" defaultState="closed" extended="<%= true %>" title="<%= pluginName %>">

							<%
							UnicodeProperties paclProperties = null;

							try {
								paclProperties = appPackagePlugin.getPACLProperties();
							}
							catch (Exception e) {
								continue;
							}

							Set<String> keys = new TreeSet<String>(paclProperties.keySet());

							for (String key : keys) {
							%>

								<div>
									<liferay-ui:message key="<%= key %>" />
								</div>

								<ul>

									<%
									String[] values = StringUtil.split(paclProperties.getProperty(key));

									for (String value : values) {
									%>

										<li>
											<%= HtmlUtil.escape(value) %>
										</li>

									<%
									}
									%>

								</ul>

							<%
							}
							%>

						</liferay-ui:panel>

					<%
					}
					%>

				</liferay-ui:panel-container>
			</div>
		</div>
	</div>
</div>