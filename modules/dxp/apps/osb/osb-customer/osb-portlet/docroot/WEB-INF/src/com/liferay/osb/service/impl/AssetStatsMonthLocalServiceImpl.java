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
import com.liferay.osb.model.AssetStatsMonth;
import com.liferay.osb.model.AssetStatsMonthConstants;
import com.liferay.osb.service.base.AssetStatsMonthLocalServiceBaseImpl;
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
public class AssetStatsMonthLocalServiceImpl
	extends AssetStatsMonthLocalServiceBaseImpl {

	public AssetStatsMonth fetchAssetStatsMonth(
			String className, long classPK, int month, int year)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return assetStatsMonthPersistence.fetchByC_C_M_Y(
			classNameId, classPK, month, year);
	}

	public JSONObject getAssetStatsMonthJSONObject(
			int startDateMonth, int startDateYear, int endDateMonth,
			int endDateYear, LinkedHashMap<String, Object> params)
		throws SystemException {

		DynamicQuery dynamicQuery = buildProjectionDynamicQuery(
			startDateMonth, startDateYear, endDateMonth, endDateYear, params,
			true, null, null);

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

	public List<JSONObject> getAssetStatsMonthJSONObjects(
			int startDateMonth, int startDateYear, int endDateMonth,
			int endDateYear, LinkedHashMap<String, Object> params, int start,
			int end, String orderByCol, String orderByType)
		throws SystemException {

		DynamicQuery dynamicQuery = buildProjectionDynamicQuery(
			startDateMonth, startDateYear, endDateMonth, endDateYear, params,
			false, orderByCol, orderByType);

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

	public int getAssetStatsMonthJSONObjectsCount(
			int startDateMonth, int startDateYear, int endDateMonth,
			int endDateYear, LinkedHashMap<String, Object> params)
		throws SystemException {

		DynamicQuery dynamicQuery = buildProjectionDynamicQuery(
			startDateMonth, startDateYear, endDateMonth, endDateYear, params,
			false, null, null);

		List<AssetStatsMonth> assetStatsMonths = dynamicQuery(dynamicQuery);

		return assetStatsMonths.size();
	}

	public JSONObject getAssetStatsMonthRevenueJSONObject(
			int startDateMonth, int startDateYear, int endDateMonth,
			int endDateYear, LinkedHashMap<String, Object> params,
			String currencyCode)
		throws SystemException {

		List<AssetStatsMonth> assetStatsMonths = getAssetStatsMonths(
			startDateMonth, startDateYear, endDateMonth, endDateYear, params,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		double revenue = 0.0;

		for (AssetStatsMonth assetStatsMonth : assetStatsMonths) {
			UnicodeProperties currencyCodeRevenuesProperties =
				new UnicodeProperties(true);

			currencyCodeRevenuesProperties.fastLoad(
				assetStatsMonth.getCurrencyCodeRevenues());

			String amount = currencyCodeRevenuesProperties.getProperty(
				currencyCode, "0");

			revenue = BigDecimalUtil.add(revenue, new BigDecimal(amount));
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put(
			AssetStatsConstants.STATS_TYPE_REVENUE_AMOUNT_KEY, revenue);

		return jsonObject;
	}

	public List<AssetStatsMonth> getAssetStatsMonths(
			int startDateMonth, int startDateYear, int endDateMonth,
			int endDateYear, LinkedHashMap<String, Object> params, int start,
			int end, OrderByComparator obc)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			startDateMonth, startDateYear, endDateMonth, endDateYear, params);

		return dynamicQuery(dynamicQuery, start, end, obc);
	}

	public int getAssetStatsMonthsCount(
			int startDateMonth, int startDateYear, int endDateMonth,
			int endDateYear, LinkedHashMap<String, Object> params)
		throws SystemException {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			startDateMonth, startDateYear, endDateMonth, endDateYear, params);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	public AssetStatsMonth updateAssetStatsMonth(
			String className, long classPK, int month, int year, long viewCount,
			long downloadCount, long purchaseCount, String currencyCodeRevenues)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		AssetStatsMonth assetStatsMonth =
			assetStatsMonthPersistence.fetchByC_C_M_Y(
				classNameId, classPK, month, year);

		if (assetStatsMonth == null) {
			assetStatsMonth = assetStatsMonthPersistence.create(
				counterLocalService.increment());

			assetStatsMonth.setClassNameId(classNameId);
			assetStatsMonth.setClassPK(classPK);
			assetStatsMonth.setMonth(month);
			assetStatsMonth.setYear(year);
		}

		assetStatsMonth.setViewCount(viewCount);
		assetStatsMonth.setDownloadCount(downloadCount);
		assetStatsMonth.setPurchaseCount(purchaseCount);
		assetStatsMonth.setCurrencyCodeRevenues(currencyCodeRevenues);

		assetStatsMonthPersistence.update(assetStatsMonth, false);

		return assetStatsMonth;
	}

	protected DynamicQuery buildDynamicQuery(
		int startDateMonth, int startDateYear, int endDateMonth,
		int endDateYear, LinkedHashMap<String, Object> params) {

		Junction junction = RestrictionsFactoryUtil.conjunction();

		Property monthProperty = PropertyFactoryUtil.forName("month");
		Property yearProperty = PropertyFactoryUtil.forName("year");

		if (startDateYear == endDateYear) {
			junction.add(monthProperty.ge(startDateMonth));
			junction.add(monthProperty.le(endDateMonth));
			junction.add(yearProperty.eq(startDateYear));
		}
		else {
			Junction conjunction1 = RestrictionsFactoryUtil.conjunction();

			conjunction1.add(monthProperty.ge(startDateMonth));
			conjunction1.add(yearProperty.eq(startDateYear));

			Junction disjunction = RestrictionsFactoryUtil.disjunction();

			disjunction.add(conjunction1);

			Junction conjunction2 = RestrictionsFactoryUtil.conjunction();

			conjunction2.add(yearProperty.gt(startDateYear));
			conjunction2.add(yearProperty.lt(endDateYear));

			disjunction.add(conjunction2);

			Junction conjunction3 = RestrictionsFactoryUtil.conjunction();

			conjunction3.add(monthProperty.le(endDateMonth));
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
			AssetStatsMonth.class);

		return dynamicQuery.add(junction);
	}

	protected DynamicQuery buildProjectionDynamicQuery(
		int startDateMonth, int startDateYear, int endDateMonth,
		int endDateYear, LinkedHashMap<String, Object> params,
		boolean aggregateTotal, String orderByCol, String orderByType) {

		DynamicQuery dynamicQuery = buildDynamicQuery(
			startDateMonth, startDateYear, endDateMonth, endDateYear, params);

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
		dynamicQuery.addOrder(OrderFactoryUtil.desc("month"));

		return dynamicQuery;
	}

	private static final String _ORDER_BY_COL_DOWNLOADS =
		AssetStatsMonthConstants.ORDER_BY_COL_DOWNLOADS;

	private static final String _ORDER_BY_COL_PURCHASES =
		AssetStatsMonthConstants.ORDER_BY_COL_PURCHASES;

	private static final String _ORDER_BY_TYPE_ASC =
		AssetStatsMonthConstants.ORDER_BY_TYPE_ASC;

}