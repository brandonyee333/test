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

package com.liferay.osb.marketplacemetrics.portlet;

import com.liferay.compat.portal.kernel.util.ClassUtil;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.compat.util.bridges.mvc.MVCPortlet;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AssetAuditConstants;
import com.liferay.osb.model.AssetStatsConstants;
import com.liferay.osb.model.AssetStatsMonth;
import com.liferay.osb.model.AssetStatsWeek;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AssetAuditLocalServiceUtil;
import com.liferay.osb.service.AssetStatsDayLocalServiceUtil;
import com.liferay.osb.service.AssetStatsMonthLocalServiceUtil;
import com.liferay.osb.service.AssetStatsWeekLocalServiceUtil;
import com.liferay.osb.service.CorpEntryLocalServiceUtil;
import com.liferay.osb.service.DeveloperEntryLocalServiceUtil;
import com.liferay.osb.service.DeveloperEntryServiceUtil;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.osb.util.comparator.AssetStatsDayJSONObjectComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

import java.io.IOException;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

/**
 * @author Ryan Park
 * @author Haote Chou
 */
public class MarketplaceMetricsPortlet extends MVCPortlet {

	public void getAppMetrics(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long[] appEntryIds = ParamUtil.getLongValues(
			actionRequest, "appEntryIds");
		int startDateDay = ParamUtil.getInteger(actionRequest, "startDateDay");
		int startDateMonth = ParamUtil.getInteger(
			actionRequest, "startDateMonth");
		int startDateYear = ParamUtil.getInteger(
			actionRequest, "startDateYear");

		Calendar startCalendar = CalendarFactoryUtil.getCalendar(
			themeDisplay.getTimeZone(), themeDisplay.getLocale());

		startCalendar.set(Calendar.YEAR, startDateYear);
		startCalendar.set(Calendar.MONTH, startDateMonth);
		startCalendar.set(Calendar.DATE, startDateDay);
		startCalendar.set(Calendar.MINUTE, 0);
		startCalendar.set(Calendar.SECOND, 0);
		startCalendar.set(Calendar.MILLISECOND, 0);

		int endDateDay = ParamUtil.getInteger(actionRequest, "endDateDay");
		int endDateMonth = ParamUtil.getInteger(actionRequest, "endDateMonth");
		int endDateYear = ParamUtil.getInteger(actionRequest, "endDateYear");

		Calendar endCalendar = CalendarFactoryUtil.getCalendar(
			themeDisplay.getTimeZone(), themeDisplay.getLocale());

		endCalendar.set(Calendar.YEAR, endDateYear);
		endCalendar.set(Calendar.MONTH, endDateMonth);
		endCalendar.set(Calendar.DATE, endDateDay);
		endCalendar.set(Calendar.MINUTE, 0);
		endCalendar.set(Calendar.SECOND, 0);
		endCalendar.set(Calendar.MILLISECOND, 0);

		int interval = ParamUtil.getInteger(actionRequest, "interval");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			JSONArray jsonArray = null;

			if (appEntryIds.length == 0) {
				jsonArray = JSONFactoryUtil.createJSONArray();
			}
			else if (interval == AssetStatsConstants.INTERVAL_WEEK) {
				jsonArray = getAssetStatsWeek(
					themeDisplay, appEntryIds, startCalendar, endCalendar);
			}
			else if (interval == AssetStatsConstants.INTERVAL_MONTH) {
				jsonArray = getAssetStatsMonth(
					themeDisplay, appEntryIds, startCalendar, endCalendar);
			}

			jsonObject.put("data", jsonArray);
			jsonObject.put("message", "success");
		}
		catch (Exception e) {
			jsonObject.put("exception", ClassUtil.getClassName(e));
			jsonObject.put("message", "error");
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void getTopMetrics(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		int startDateDay = ParamUtil.getInteger(actionRequest, "startDateDay");
		int startDateMonth = ParamUtil.getInteger(
			actionRequest, "startDateMonth");
		int startDateYear = ParamUtil.getInteger(
			actionRequest, "startDateYear");

		Calendar startCalendar = CalendarFactoryUtil.getCalendar(
			themeDisplay.getTimeZone(), themeDisplay.getLocale());

		startCalendar.set(Calendar.YEAR, startDateYear);
		startCalendar.set(Calendar.MONTH, startDateMonth);
		startCalendar.set(Calendar.DATE, startDateDay);
		startCalendar.set(Calendar.MINUTE, 0);
		startCalendar.set(Calendar.SECOND, 0);
		startCalendar.set(Calendar.MILLISECOND, 0);

		int endDateDay = ParamUtil.getInteger(actionRequest, "endDateDay");
		int endDateMonth = ParamUtil.getInteger(actionRequest, "endDateMonth");
		int endDateYear = ParamUtil.getInteger(actionRequest, "endDateYear");

		Calendar endCalendar = CalendarFactoryUtil.getCalendar(
			themeDisplay.getTimeZone(), themeDisplay.getLocale());

		endCalendar.set(Calendar.YEAR, endDateYear);
		endCalendar.set(Calendar.MONTH, endDateMonth);
		endCalendar.set(Calendar.DATE, endDateDay);
		endCalendar.set(Calendar.MINUTE, 0);
		endCalendar.set(Calendar.SECOND, 0);
		endCalendar.set(Calendar.MILLISECOND, 0);

		int type = ParamUtil.getInteger(actionRequest, "type");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			JSONArray jsonArray = null;

			DeveloperEntry developerEntry =
				DeveloperEntryLocalServiceUtil.getDeveloperEntryByGroupId(
					themeDisplay.getScopeGroupId());

			if (type == AssetStatsConstants.STATS_TYPE_TOP_COMPANIES) {
				jsonArray = getTopCompaniesJSONArray(
					developerEntry, startCalendar, endCalendar);
			}
			else {
				jsonArray = getTopAssetsJSONArray(
					developerEntry, startCalendar, endCalendar, type);
			}

			jsonObject.put("categoryKey", "title");
			jsonObject.put("data", jsonArray);
			jsonObject.put("message", "success");

			if (type == AssetStatsConstants.STATS_TYPE_REVENUE_AMOUNT) {
				jsonObject.put("valueKey", "amount");
			}
			else {
				jsonObject.put("valueKey", "total");
			}
		}
		catch (Exception e) {
			jsonObject.put("exception", ClassUtil.getClassName(e));
			jsonObject.put("message", "error");
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void updateDeveloperEntryGoogleAnalyticsKey(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long developerEntryId = ParamUtil.getLong(
			actionRequest, "developerEntryId");

		String googleAnalyticsKey = ParamUtil.getString(
			actionRequest, "googleAnalyticsKey");

		DeveloperEntryServiceUtil.updateDeveloperEntryGoogleAnalyticsKey(
			developerEntryId, googleAnalyticsKey);
	}

	@Override
	protected void addSuccessMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		renderRequest.setAttribute(WebKeys.PORTLET_DECORATE, Boolean.FALSE);

		super.doDispatch(renderRequest, renderResponse);
	}

	protected JSONArray getAssetStatsMonth(
			ThemeDisplay themeDisplay, long[] appEntryIds,
			Calendar startCalendar, Calendar endCalendar)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		DateFormat dateFormat = DateFormat.getDateInstance(
			DateFormat.MEDIUM, themeDisplay.getLocale());

		startCalendar.set(Calendar.DATE, 1);

		while (startCalendar.compareTo(endCalendar) <= 0) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			long downloads = 0;
			long purchases = 0;
			long views = 0;

			for (long appEntryId : appEntryIds) {
				AssetStatsMonth assetStatsMonth =
					AssetStatsMonthLocalServiceUtil.fetchAssetStatsMonth(
						AppEntry.class.getName(), appEntryId,
						startCalendar.get(Calendar.MONTH),
						startCalendar.get(Calendar.YEAR));

				if (assetStatsMonth != null) {
					downloads += assetStatsMonth.getDownloadCount();
					purchases += assetStatsMonth.getPurchaseCount();
					views += assetStatsMonth.getViewCount();
				}
			}

			jsonObject.put("date", dateFormat.format(startCalendar.getTime()));
			jsonObject.put("downloads", downloads);
			jsonObject.put("purchases", purchases);
			jsonObject.put("views", views);

			jsonArray.put(jsonObject);

			startCalendar.add(Calendar.MONTH, 1);
		}

		return jsonArray;
	}

	protected JSONArray getAssetStatsWeek(
			ThemeDisplay themeDisplay, long[] appEntryIds,
			Calendar startCalendar, Calendar endCalendar)
		throws Exception {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		DateFormat dateFormat = DateFormat.getDateInstance(
			DateFormat.MEDIUM, themeDisplay.getLocale());

		startCalendar.setFirstDayOfWeek(Calendar.SUNDAY);

		startCalendar.set(
			Calendar.DAY_OF_WEEK, startCalendar.getFirstDayOfWeek());

		while (startCalendar.compareTo(endCalendar) <= 0) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			long downloads = 0;
			long purchases = 0;
			long views = 0;

			for (long appEntryId : appEntryIds) {
				AssetStatsWeek assetStatsWeek =
					AssetStatsWeekLocalServiceUtil.fetchAssetStatsWeek(
						AppEntry.class.getName(), appEntryId,
						startCalendar.get(Calendar.WEEK_OF_YEAR),
						startCalendar.get(Calendar.YEAR));

				if (assetStatsWeek != null) {
					downloads += assetStatsWeek.getDownloadCount();
					purchases += assetStatsWeek.getPurchaseCount();
					views += assetStatsWeek.getViewCount();
				}
			}

			jsonObject.put("date", dateFormat.format(startCalendar.getTime()));
			jsonObject.put("downloads", downloads);
			jsonObject.put("purchases", purchases);
			jsonObject.put("views", views);

			jsonArray.put(jsonObject);

			startCalendar.add(Calendar.DATE, 7);
		}

		return jsonArray;
	}

	protected JSONArray getTopAssetsJSONArray(
			DeveloperEntry developerEntry, Calendar startCalendar,
			Calendar endCalendar, int type)
		throws Exception {

		List<AppEntry> appEntries = AppEntryLocalServiceUtil.getAppEntries(
			developerEntry.getDeveloperEntryId(), WorkflowConstants.STATUS_ANY,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		List<JSONObject> jsonObjects = new ArrayList<JSONObject>(
			appEntries.size());

		for (AppEntry appEntry : appEntries) {
			JSONObject jsonObject = null;

			LinkedHashMap<String, Object> params =
				new LinkedHashMap<String, Object>();

			params.put(
				"classNameId", PortalUtil.getClassNameId(AppEntry.class));
			params.put("classPK", appEntry.getAppEntryId());

			if (type == AssetStatsConstants.STATS_TYPE_REVENUE_AMOUNT) {
				jsonObject =
					AssetStatsDayLocalServiceUtil.
						getAssetStatsDayRevenueJSONObject(
							startCalendar.get(Calendar.DAY_OF_YEAR),
							startCalendar.get(Calendar.YEAR),
							endCalendar.get(Calendar.DAY_OF_YEAR),
							endCalendar.get(Calendar.YEAR), params, "USD");
			}
			else {
				jsonObject =
					AssetStatsDayLocalServiceUtil.getAssetStatsDayJSONObject(
						startCalendar.get(Calendar.DAY_OF_YEAR),
						startCalendar.get(Calendar.YEAR),
						endCalendar.get(Calendar.DAY_OF_YEAR),
						endCalendar.get(Calendar.YEAR), params);
			}

			jsonObject.put("title", appEntry.getTitle());

			jsonObjects.add(jsonObject);
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		String statsTypeKey = AssetStatsConstants.getStatsTypeKey(type);

		Collections.sort(
			jsonObjects,
			new AssetStatsDayJSONObjectComparator(statsTypeKey, false));

		int i = 0;

		for (; i < jsonObjects.size(); i++) {
			JSONObject jsonObject = jsonObjects.get(i);

			if (jsonObject.getLong(statsTypeKey) <= 0) {
				return jsonArray;
			}

			JSONObject metricJSONObject = JSONFactoryUtil.createJSONObject();

			metricJSONObject.put("title", jsonObject.getString("title"));

			if (type == AssetStatsConstants.STATS_TYPE_REVENUE_AMOUNT) {
				metricJSONObject.put(
					"amount", jsonObject.getDouble(statsTypeKey));
			}
			else {
				metricJSONObject.put("total", jsonObject.getLong(statsTypeKey));
			}

			jsonArray.put(metricJSONObject);

			if ((jsonObjects.size() > 5) && (i >= 4)) {
				break;
			}
		}

		double total = 0;

		for (; i < jsonObjects.size(); i++) {
			JSONObject jsonObject = jsonObjects.get(i);

			total += jsonObject.getDouble(statsTypeKey);
		}

		if (total > 0) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("title", "other");

			if (type == AssetStatsConstants.STATS_TYPE_REVENUE_AMOUNT) {
				jsonObject.put("amount", total);
			}
			else {
				jsonObject.put("total", (long)total);
			}

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	protected JSONArray getTopCompaniesJSONArray(
			DeveloperEntry developerEntry, Calendar startCalendar,
			Calendar endCalendar)
		throws Exception {

		Date startDate = startCalendar.getTime();
		Date endDate = endCalendar.getTime();

		String className = StringPool.BLANK;
		long classPK = 0;

		if (developerEntry.isCompany()) {
			className = CorpEntry.class.getName();

			CorpEntry corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(
				developerEntry.getDossieraAccountKey());

			classPK = corpEntry.getCorpEntryId();
		}
		else if (developerEntry.isUser()) {
			className = User.class.getName();
			classPK = developerEntry.getUserId();
		}

		List<JSONObject> jsonObjects =
			AssetAuditLocalServiceUtil.getAssetAuditPurchaseCountJSONObjects(
				startDate, endDate, className, classPK, 0, 5);

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		int total = 0;

		for (JSONObject jsonObject : jsonObjects) {
			JSONObject metricJSONObject = JSONFactoryUtil.createJSONObject();

			metricJSONObject.put(
				"title", jsonObject.getString("legalEntityName"));
			metricJSONObject.put(
				"total", jsonObject.getLong("companyPurchaseCount"));

			total += metricJSONObject.getLong("companyPurchaseCount");

			jsonArray.put(metricJSONObject);
		}

		int count = AssetAuditLocalServiceUtil.getAssetAuditsCount(
			startDate, endDate, className, classPK,
			AssetAuditConstants.TYPE_PURCHASE);

		total = count - total;

		if (total > 0) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("title", "other");
			jsonObject.put("total", total);

			jsonArray.put(jsonObject);
		}

		return jsonArray;
	}

	@Override
	protected boolean isSessionErrorException(Throwable cause) {
		if (_log.isDebugEnabled()) {
			_log.debug(cause, cause);
		}

		return super.isSessionErrorException(cause);
	}

	private static Log _log = LogFactoryUtil.getLog(
		MarketplaceMetricsPortlet.class);

}