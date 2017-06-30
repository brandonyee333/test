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

<div class="app-entries-weekly-stats-chart marketplace">
	<div class="metric-graph" id="weeklyStatsGraph"></div>

	<div class="centered info weekly-stats-graph-info">
		<liferay-ui:message key="last-4-weeks" />
	</div>

	<%
	Calendar startCalendar = CalendarFactoryUtil.getCalendar(TimeZoneUtil.getTimeZone(StringPool.UTC));

	startCalendar.set(Calendar.DAY_OF_WEEK, 1);
	startCalendar.set(Calendar.MINUTE, 0);
	startCalendar.set(Calendar.SECOND, 0);
	startCalendar.set(Calendar.MILLISECOND, 0);

	startCalendar.add(Calendar.DATE, -28);

	Calendar endCalendar = CalendarFactoryUtil.getCalendar(TimeZoneUtil.getTimeZone(StringPool.UTC));

	endCalendar.set(Calendar.DAY_OF_WEEK, 1);
	endCalendar.set(Calendar.MINUTE, 0);
	endCalendar.set(Calendar.SECOND, 0);
	endCalendar.set(Calendar.MILLISECOND, 0);

	endCalendar.add(Calendar.DATE, -7);

	JSONObject jsonObject = AssetStatsWeekLocalServiceUtil.getAssetStatsWeekJSONObject(startCalendar.get(Calendar.WEEK_OF_YEAR), startCalendar.get(Calendar.YEAR), endCalendar.get(Calendar.WEEK_OF_YEAR), endCalendar.get(Calendar.YEAR), null);
	%>

	<div class="weekly-stats-info">
		<div class="weekly-downloads">
			<div class="callout-f-head">
				<div class="callout-content">
					<liferay-ui:message key="downloads" />
				</div>
			</div>

			<div class="callout-f">
				<div class="callout-content">
					<%= jsonObject.getInt(AssetStatsConstants.STATS_TYPE_DOWNLOAD_COUNT_KEY) %>
				</div>
			</div>
		</div>

		<div class="weekly-views">
			<div class="callout-f-head">
				<div class="callout-content">
					<liferay-ui:message key="views" />
				</div>
			</div>

			<div class="callout-f">
				<div class="callout-content">
					<%= jsonObject.getInt(AssetStatsConstants.STATS_TYPE_VIEW_COUNT_KEY) %>
				</div>
			</div>
		</div>
	</div>
</div>

<aui:script use="charts">
	var chart = new A.Chart(
		{
			categoryKey: 'date',
			categoryType: 'category',
			dataProvider: <%= _getAssetStatsWeeks(startCalendar, endCalendar, pageContext) %>,
			interactionType: 'planar',
			render: '#weeklyStatsGraph',
			styles: {
				graph: {
					background: {
						border: {
							alpha: 0
						},
						fill: {
							alpha: 0
						}
					}
				},
				series: {
					'<liferay-ui:message key="downloads" unicode="<%= true %>" />': {
						line: {
							alpha: .75,
							color: '#24A22B',
							weight: 2
						},
						marker: {
							border: {
								color: '#24A22B',
								weight: 2
							},
							fill: {
								color: '#FFF'
							},
							height: 5,
							width: 5
						}
					},
					'<liferay-ui:message key="views" unicode="<%= true %>" />': {
						line: {
							alpha: .75,
							color: '#F3980A',
							weight: 2
						},
						marker: {
							border: {
								color: '#F3980A',
								weight: 2
							},
							fill: {
								color: '#FFF'
							},
							height: 5,
							width: 5
						}
					}
				}
			}
		}
	);
</aui:script>

<%!
private JSONArray _getAssetStatsWeeks(Calendar startCalendar, Calendar endCalendar, PageContext pageContext) throws Exception {
	JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

	while (startCalendar.compareTo(endCalendar) <= 0) {
		JSONObject assetStatsWeekJSONObject = AssetStatsWeekLocalServiceUtil.getAssetStatsWeekJSONObject(startCalendar.get(Calendar.WEEK_OF_YEAR), startCalendar.get(Calendar.YEAR), startCalendar.get(Calendar.WEEK_OF_YEAR), startCalendar.get(Calendar.YEAR), null);

		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("date", LanguageUtil.format(pageContext, "week-of-x", dateFormat.format(startCalendar.getTime())));
		jsonObject.put(LanguageUtil.get(pageContext, "downloads"), assetStatsWeekJSONObject.getInt(AssetStatsConstants.STATS_TYPE_DOWNLOAD_COUNT_KEY));
		jsonObject.put(LanguageUtil.get(pageContext, "views"), assetStatsWeekJSONObject.getInt(AssetStatsConstants.STATS_TYPE_VIEW_COUNT_KEY));

		jsonArray.put(jsonObject);

		startCalendar.add(Calendar.DATE, 7);
	}

	return jsonArray;
}
%>