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
List<AssetEntry> assetEntriesNewApps = MarketplaceUtil.getAssetListAssetEntries(AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, AssetListConstants.TYPE_NEW_APPS, true, 0, 4);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter(mvcPathParam, "/marketplace/view_app_entry.jsp");
%>

<div class="app-entries-weekly-stats marketplace">
	<div class="weekly-stats-head">
		<a class="btn" href="https://twitter.com/LiferayApps" target="_blank"><liferay-ui:message key="follow-updates" /></a>

		<h2>
			<liferay-ui:message key="weekly-stats" />
		</h2>
	</div>

	<div class="weekly-stats-body">
		<c:if test="<%= !assetEntriesNewApps.isEmpty() %>">
			<div class="new-apps">
				<h3>
					<liferay-ui:message key="new-apps" />
				</h3>

				<ul>

					<%
					for (AssetEntry assetEntry : assetEntriesNewApps) {
						AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(assetEntry.getClassPK());

						portletURL.setParameter("appEntryId", String.valueOf(appEntry.getAppEntryId()));
					%>

						<li>
							<span class="title">
								<a href="<%= HtmlUtil.escapeAttribute(portletURL.toString()) %>" title="<%= appEntry.getTitle() %>"><%= HtmlUtil.escape(appEntry.getTitle()) %></a>
							</span>

							<span class="info"><%= HtmlUtil.escape(appEntry.getDeveloperEntryName()) %></span>
						</li>

					<%
					}
					%>

				</ul>
			</div>
		</c:if>

		<%
		List<AssetEntry> assetEntriesUpdatedApps = MarketplaceUtil.getAssetListAssetEntries(AssetCategoryConstants.DEFAULT_PARENT_CATEGORY_ID, AssetListConstants.TYPE_APP_UPDATES, true, 0, 4);
		%>

		<c:if test="<%= !assetEntriesUpdatedApps.isEmpty() %>">
			<div class="updated-apps">
				<h3>
					<liferay-ui:message key="updated-apps" />
				</h3>

				<ul>

					<%
					for (AssetEntry assetEntry : assetEntriesUpdatedApps) {
						AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(assetEntry.getClassPK());

						portletURL.setParameter("appEntryId", String.valueOf(appEntry.getAppEntryId()));
					%>

						<li>
							<span class="title">
								<a href="<%= HtmlUtil.escapeAttribute(portletURL.toString()) %>" title="<%= appEntry.getTitle() %>"><%= HtmlUtil.escape(appEntry.getTitle()) %></a>
							</span>

							<span class="info"><%= HtmlUtil.escape(appEntry.getDeveloperEntryName()) %></span>
						</li>

					<%
					}
					%>

				</ul>
			</div>
		</c:if>

		<div class="weekly-stats-graph">
		</div>
	</div>
</div>

<aui:script use="aui-io">
	<liferay-portlet:renderURL var="appEntriesWeeklyStatsChartURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
		<portlet:param name="<%= mvcPathParam %>" value="/marketplace/app_entries_weekly_stats_chart.jsp" />
	</liferay-portlet:renderURL>

	A.one('.app-entries-weekly-stats.marketplace .weekly-stats-graph').plug(
		A.Plugin.IO,
		{
			uri: '<%= appEntriesWeeklyStatsChartURL %>'
		}
	);
</aui:script>