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

package com.liferay.osb.service.impl;

import com.liferay.compat.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.compat.portal.kernel.util.BigDecimalUtil;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AssetStatsConstants;
import com.liferay.osb.model.AssetStatsDay;
import com.liferay.osb.model.AssetStatsDayConstants;
import com.liferay.osb.service.base.AssetStatsDayLocalServiceBaseImpl;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Order;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Peter Shin
 * @author Ryan Park
 * @author Joan Kim
 */
public class AssetStatsDayLocalServiceImpl
	extends AssetStatsDayLocalServiceBaseImpl {

	public AssetStatsDay fetchAssetStatsDay(
			String className, long classPK, int day, int year)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return assetStatsDayPersistence.fetchByC_C_D_Y(
			classNameId, classPK, day, year);
	}

	public JSONObject getAssetStatsDayJSONObject(
			int startDateDay, int startDateYear, int endDateDay,
			int endDateYear, LinkedHashMap<String, Object> params)
		throws SystemException {

		DynamicQuery dynamicQuery = buildProjectionDynamicQuery(
			startDateDay, startDateYear, endDateDay, endDateYear, params, true,
			null, null);

		List<Object[]> results = dynamicQuery(dynamicQuery, 0, 1);

		if ((results == null) || results.isEmpty()) {
			return JSONFactoryUtil.createJSONObject();
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		Object[] result = results.get(0);

		jsonObject.put(
			AssetStatsConstants.STATS_TYPE_DOWNLOAD_COUNT_KEY,
			GetterUtil.getLong(result[0]));
		jsonObject.put(
			AssetStatsConstants.STATS_TYPE_PURCHASE_COUNT_KEY,
			GetterUtil.getLong(result[1]));
		jsonObject.put(
			AssetStatsConstants.STATS_TYPE_VIEW_COUNT_KEY,
			GetterUtil.getLong(result[2]));

		return jsonObject;
	}

	public List<JSONObject> getAssetStatsDayJSONObjects(
			int startDateDay, int startDateYear, int endDateDay,
			int endDateYear, LinkedHashMap<String, Object> params, int start,
			int end, String orderByCol, String orderByType)
		throws SystemException {

		DynamicQuery dynamicQuery = buildProjectionDynamicQuery(
			startDateDay, startDateYear, endDateDay, endDateYear, params, false,
			orderByCol, orderByType);

		List<Object[]> results = dynamicQuery(dynamicQuery, start, end);

		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();

		for (Object[] result : results) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put(
				AssetStatsConstants.STATS_TYPE_DOWNLOAD_COUNT_KEY,
				GetterUtil.getLong(result[2]));
			jsonObject.put(
				AssetStatsConstants.STATS_TYPE_PURCHASE_COUNT_KEY,
				GetterUtil.getLong(result[3]));
			jsonObject.put(
				AssetStatsConstants.STATS_TYPE_VIEW_COUNT_KEY,
				GetterUtil.getLong(result[4]));

			jsonObject.put("classNameId", GetterUtil.getLong(result[0]));
			jsonObject.put("classPK", GetterUtil.getLong(result[1]));

			jsonObjects.add(jsonObject);
		}

		return jsonObjects;
	}

	public int getAssetStatsDayJSONObjectsCount(
			int startDateDay, int startDateYear, int endDateDay,
			int endDateYear, LinkedHashMap<String, Object> params)
		throws SystemException {

		DynamicQuery dynamicQuery = buildProjectionDynamicQuery(
			startDateDay, startDateYear, endDateDay, endDateYear, params, false,
			null, null);

		List<AssetStatsDay> assetStatsDays = dynamicQuery(dynamicQuery);

		return assetStatsDays.size();
	}

	public JSONObject getAssetStatsDayRevenueJSONObject(
			int startDateDay, int startDateYear, int endDateDay,
			int endDateYear, LinkedHashMap<String, Object> params,
			String currencyCode)
		throws SystemException {

		List<AssetStatsDay> assetStatsDays = getAssetStatsDays(
			startDateDay, startDateYear, endDateDay, endDateYear, params,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		double revenue = 0.0;

		for (AssetStatsDay assetStatsDay : assetStatsDays) {
			UnicodeProperties currencyCodeRevenuesProperties =
				new UnicodeProperties(true);

			currencyCodeRevenuesProperties.fastLoad(
				assetStatsDay.getCurrencyCodeRevenues());

			String amount = currencyCodeRevenuesProperties.getProperty(
				currencyCode, "0");

			revenue = BigDecimalUtil.add(revenue, new BigDecimal(amount));
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			AssetStatsConstants.STATS_TYPE_REVENUE_AMOUNT_KEY, revenue);

		return jsonObject;
	}

	public List<AssetStatsDay> getAssetStatsDays(
			int startDateDay, int startDateYear, int endDateDay,
			int endDateYear, LinkedHashMap<String, Object> params, int start,
			int end, OrderByComparator obc)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			startDateDay, startDateYear, endDateDay, endDateYear, params);

		return dynamicQuery(dynamicQuery, start, end, obc);
	}

	public int getAssetStatsDaysCount(
			int startDateDay, int startDateYear, int endDateDay,
			int endDateYear, LinkedHashMap<String, Object> params)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			startDateDay, startDateYear, endDateDay, endDateYear, params);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	public AssetStatsDay updateAssetStatsDay(
			String className, long classPK, int day, int year, long viewCount,
			long downloadCount, long purchaseCount, String currencyCodeRevenues)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		AssetStatsDay assetStatsDay = assetStatsDayPersistence.fetchByC_C_D_Y(
			classNameId, classPK, day, year);

		if (assetStatsDay == null) {
			assetStatsDay = assetStatsDayPersistence.create(
				counterLocalService.increment());

			assetStatsDay.setClassNameId(classNameId);
			assetStatsDay.setClassPK(classPK);
			assetStatsDay.setDay(day);
			assetStatsDay.setYear(year);
		}

		assetStatsDay.setViewCount(viewCount);
		assetStatsDay.setDownloadCount(downloadCount);
		assetStatsDay.setPurchaseCount(purchaseCount);
		assetStatsDay.setCurrencyCodeRevenues(currencyCodeRevenues);

		assetStatsDayPersistence.update(assetStatsDay, false);

		return assetStatsDay;
	}

	protected DynamicQuery buildDynamicQuery(
		int startDateDay, int startDateYear, int endDateDay, int endDateYear,
		LinkedHashMap<String, Object> params) {

		Junction junction = RestrictionsFactoryUtil.conjunction();

		Property dayProperty = PropertyFactoryUtil.forName("day");
		Property yearProperty = PropertyFactoryUtil.forName("year");

		if (startDateYear == endDateYear) {
			junction.add(dayProperty.ge(startDateDay));
			junction.add(dayProperty.le(endDateDay));
			junction.add(yearProperty.eq(startDateYear));
		}
		else {
			Junction conjunction1 = RestrictionsFactoryUtil.conjunction();

			conjunction1.add(dayProperty.ge(startDateDay));
			conjunction1.add(yearProperty.eq(startDateYear));

			Junction disjunction = RestrictionsFactoryUtil.disjunction();

			disjunction.add(conjunction1);

			Junction conjunction2 = RestrictionsFactoryUtil.conjunction();

			conjunction2.add(yearProperty.gt(startDateYear));
			conjunction2.add(yearProperty.lt(endDateYear));

			disjunction.add(conjunction2);

			Junction conjunction3 = RestrictionsFactoryUtil.conjunction();

			conjunction3.add(dayProperty.le(endDateDay));
			conjunction3.add(yearProperty.eq(endDateYear));

			disjunction.add(conjunction3);

			junction.add(disjunction);
		}

		if (params != null) {
			Property classNameIdProperty = PropertyFactoryUtil.forName(
				"classNameId");
			Property classPKProperty = PropertyFactoryUtil.forName("classPK");

			long classNameId = GetterUtil.getLong(params.get("classNameId"));
			long classPK = GetterUtil.getLong(params.get("classPK"));

			if (params.containsKey("classNameId") &&
				params.containsKey("classPK")) {

				junction.add(classNameIdProperty.eq(classNameId));
				junction.add(classPKProperty.eq(classPK));
			}
			else if (params.containsKey("classNameId")) {
				junction.add(classNameIdProperty.eq(classNameId));
			}

			if (params.containsKey("notClassNameId") &&
				params.containsKey("notClassPK")) {

				Junction disjunction = RestrictionsFactoryUtil.disjunction();

				disjunction.add(classNameIdProperty.ne(classNameId));
				disjunction.add(classPKProperty.ne(classPK));

				junction.add(disjunction);
			}
			else if (params.containsKey("notClassNameId")) {
				junction.add(classNameIdProperty.ne(classNameId));
			}

			if (params.containsKey("notDeveloperEntryId")) {
				Property developerEntryIdProperty = PropertyFactoryUtil.forName(
					"developerEntryId");

				long developerEntryId = GetterUtil.getLong(
					params.get("notDeveloperEntryId"));

				if (developerEntryId > 0) {
					DynamicQuery dynamicQuery =
						DynamicQueryFactoryUtil.forClass(AppEntry.class);

					dynamicQuery.add(
						developerEntryIdProperty.eq(developerEntryId));

					dynamicQuery.setProjection(
						ProjectionFactoryUtil.property("appEntryId"));

					junction.add(classPKProperty.notIn(dynamicQuery));
				}
			}
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			AssetStatsDay.class);

		return dynamicQuery.add(junction);
	}

	protected DynamicQuery buildProjectionDynamicQuery(
		int startDateDay, int startDateYear, int endDateDay, int endDateYear,
		LinkedHashMap<String, Object> params, boolean aggregateTotal,
		String orderByCol, String orderByType) {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			startDateDay, startDateYear, endDateDay, endDateYear, params);

		ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

		if (!aggregateTotal) {
			projectionList.add(
				ProjectionFactoryUtil.groupProperty("classNameId"));
			projectionList.add(ProjectionFactoryUtil.groupProperty("classPK"));
		}

		projectionList.add(
			ProjectionFactoryUtil.sum("downloadCount"),
			AssetStatsConstants.STATS_TYPE_DOWNLOAD_COUNT_KEY);
		projectionList.add(
			ProjectionFactoryUtil.sum("purchaseCount"),
			AssetStatsConstants.STATS_TYPE_PURCHASE_COUNT_KEY);
		projectionList.add(
			ProjectionFactoryUtil.sum("viewCount"),
			AssetStatsConstants.STATS_TYPE_VIEW_COUNT_KEY);

		dynamicQuery = dynamicQuery.setProjection(projectionList);

		String propertyName = AssetStatsConstants.STATS_TYPE_VIEW_COUNT_KEY;

		if (orderByCol != null) {
			if (orderByCol.equalsIgnoreCase(_ORDER_BY_COL_DOWNLOADS)) {
				propertyName =
					AssetStatsConstants.STATS_TYPE_DOWNLOAD_COUNT_KEY;
			}
			else if (orderByCol.equalsIgnoreCase(_ORDER_BY_COL_PURCHASES)) {
				propertyName =
					AssetStatsConstants.STATS_TYPE_PURCHASE_COUNT_KEY;
			}
		}

		Order primaryOrder = OrderFactoryUtil.desc(propertyName);

		if (orderByType != null) {
			if (orderByType.equalsIgnoreCase(_ORDER_BY_TYPE_ASC)) {
				primaryOrder = OrderFactoryUtil.asc(propertyName);
			}
		}

		dynamicQuery.addOrder(primaryOrder);
		dynamicQuery.addOrder(OrderFactoryUtil.desc("year"));
		dynamicQuery.addOrder(OrderFactoryUtil.desc("day"));

		return dynamicQuery;
	}

	private static final String _ORDER_BY_COL_DOWNLOADS =
		AssetStatsDayConstants.ORDER_BY_COL_DOWNLOADS;

	private static final String _ORDER_BY_COL_PURCHASES =
		AssetStatsDayConstants.ORDER_BY_COL_PURCHASES;

	private static final String _ORDER_BY_TYPE_ASC =
		AssetStatsDayConstants.ORDER_BY_TYPE_ASC;

}