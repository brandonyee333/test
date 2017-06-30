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

package com.liferay.osb.marketplaceadmin.util;

import com.liferay.compat.portal.kernel.util.Time;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.service.AppEntryLocalServiceUtil;
import com.liferay.osb.service.AssetStatsDayLocalServiceUtil;
import com.liferay.osb.service.AssetStatsMonthLocalServiceUtil;
import com.liferay.osb.service.AssetStatsWeekLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.comparator.AppEntryDeveloperNameComparator;
import com.liferay.osb.util.comparator.AppEntryModifiedDateComparator;
import com.liferay.osb.util.comparator.AppEntryStatusVersionDateComparator;
import com.liferay.osb.util.comparator.AppEntryTitleComparator;
import com.liferay.osb.util.comparator.CorpEntryCreateDateComparator;
import com.liferay.osb.util.comparator.CorpEntryNameComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.portlet.RenderResponse;
import javax.portlet.ResourceURL;

/**
 * @author Douglas Wong
 * @author Joan Kim
 */
public class MarketplaceAdminUtil {

	public static final int MARKETPLACE_ASSET_ENTRY_TYPE_APP = 1;

	public static final int MARKETPLACE_ASSET_ENTRY_TYPE_BANNER = 2;

	public static final int MARKETPLACE_ASSET_ENTRY_TYPE_MEDIUM_SIZED_AD = 3;

	public static OrderByComparator getAppEntryOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator orderByComparator = null;

		if (orderByCol.equals("developer")) {
			orderByComparator = new AppEntryDeveloperNameComparator(orderByAsc);
		}
		else if (orderByCol.equals("modified-date")) {
			orderByComparator = new AppEntryModifiedDateComparator(orderByAsc);
		}
		else if (orderByCol.equals("status-date")) {
			orderByComparator = new AppEntryStatusVersionDateComparator(
				orderByAsc);
		}
		else {
			orderByComparator = new AppEntryTitleComparator(orderByAsc);
		}

		return orderByComparator;
	}

	public static List<JSONObject> getAssetStatsJSONObjects(
			Calendar startCalendar, Calendar endCalendar,
			LinkedHashMap<String, Object> params, int start, int end,
			String orderByCol, String orderByType)
		throws SystemException {

		long interval =
			endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();

		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();

		if (interval > (Time.MONTH * 5)) {
			jsonObjects =
				AssetStatsMonthLocalServiceUtil.getAssetStatsMonthJSONObjects(
					startCalendar.get(Calendar.MONTH),
					startCalendar.get(Calendar.YEAR),
					endCalendar.get(Calendar.MONTH),
					endCalendar.get(Calendar.YEAR), params, start, end,
					orderByCol, orderByType);
		}
		else if (interval > (Time.WEEK * 5)) {
			jsonObjects =
				AssetStatsWeekLocalServiceUtil.getAssetStatsWeekJSONObjects(
					startCalendar.get(Calendar.WEEK_OF_YEAR),
					startCalendar.get(Calendar.YEAR),
					endCalendar.get(Calendar.WEEK_OF_YEAR),
					endCalendar.get(Calendar.YEAR), params, start, end,
					orderByCol, orderByType);
		}
		else {
			jsonObjects =
				AssetStatsDayLocalServiceUtil.getAssetStatsDayJSONObjects(
					startCalendar.get(Calendar.DAY_OF_YEAR),
					startCalendar.get(Calendar.YEAR),
					endCalendar.get(Calendar.DAY_OF_YEAR),
					endCalendar.get(Calendar.YEAR), params, start, end,
					orderByCol, orderByType);
		}

		Iterator<JSONObject> itr = jsonObjects.iterator();

		while (itr.hasNext()) {
			JSONObject jsonObject = itr.next();

			long classNameId = jsonObject.getLong("classNameId");
			long classPK = jsonObject.getLong("classPK");

			if (AppEntryLocalServiceUtil.fetchAppEntry(classPK) == null) {
				itr.remove();

				_log.error(
					"Unable to get app entry with class name " +
						PortalUtil.getClassName(classNameId) + " and class " +
							" primary key " + classPK);
			}
		}

		return jsonObjects;
	}

	public static int getAssetStatsJSONObjectsCount(
			Calendar startCalendar, Calendar endCalendar,
			LinkedHashMap<String, Object> params)
		throws SystemException {

		long interval =
			endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();

		if (interval > (Time.MONTH * 5)) {
			return AssetStatsMonthLocalServiceUtil.
				getAssetStatsMonthJSONObjectsCount(
					startCalendar.get(Calendar.MONTH),
					startCalendar.get(Calendar.YEAR),
					endCalendar.get(Calendar.MONTH),
					endCalendar.get(Calendar.YEAR), params);
		}
		else if (interval > (Time.WEEK * 5)) {
			return AssetStatsWeekLocalServiceUtil.
				getAssetStatsWeekJSONObjectsCount(
					startCalendar.get(Calendar.WEEK_OF_YEAR),
					startCalendar.get(Calendar.YEAR),
					endCalendar.get(Calendar.WEEK_OF_YEAR),
					endCalendar.get(Calendar.YEAR), params);
		}

		return AssetStatsDayLocalServiceUtil.getAssetStatsDayJSONObjectsCount(
			startCalendar.get(Calendar.DAY_OF_YEAR),
			startCalendar.get(Calendar.YEAR),
			endCalendar.get(Calendar.DAY_OF_YEAR),
			endCalendar.get(Calendar.YEAR), params);
	}

	public static OrderByComparator getCorpEntryOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator orderByComparator = null;

		if (orderByCol.equals("create-date")) {
			orderByComparator = new CorpEntryCreateDateComparator(orderByAsc);
		}
		else {
			orderByComparator = new CorpEntryNameComparator(orderByAsc);
		}

		return orderByComparator;
	}

	public static String getMarketplaceAssetEntryIconURL(
			AssetEntry assetEntry, RenderResponse renderResponse)
		throws PortalException, SystemException {

		if (assetEntry == null) {
			return StringPool.BLANK;
		}

		String className = assetEntry.getClassName();

		if (className.equals(AppEntry.class.getName())) {
			AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
				assetEntry.getClassPK());

			long iconImageId = appEntry.getIconImageId();

			if (iconImageId > 0) {
				ResourceURL resourceURL = renderResponse.createResourceURL();

				resourceURL.setParameter(
					"assetAttachmentId", String.valueOf(iconImageId));
				resourceURL.setResourceID("serveIcon");

				return resourceURL.toString();
			}
		}
		else if (className.equals(JournalArticle.class.getName())) {
			JournalArticle journalArticle =
				JournalArticleLocalServiceUtil.getLatestArticle(
					assetEntry.getClassPK());

			try {
				Document document = SAXReaderUtil.read(
					journalArticle.getContent());

				Element rootElement = document.getRootElement();

				List<Element> elements = rootElement.elements(
					"dynamic-element");

				for (Element element : elements) {
					String name = element.attributeValue("name");

					if (name.equals("image")) {
						return element.elementTextTrim("dynamic-content");
					}
				}
			}
			catch (DocumentException de) {
			}
		}

		return StringPool.BLANK;
	}

	public static String getMarketplaceAssetEntryStatusLabel(
			AssetEntry assetEntry)
		throws PortalException, SystemException {

		if (assetEntry == null) {
			return StringPool.BLANK;
		}

		String className = assetEntry.getClassName();

		if (className.equals(AppEntry.class.getName())) {
			AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(
				assetEntry.getClassPK());

			return WorkflowConstants.toLabel(appEntry.getStatus());
		}
		else if (className.equals(JournalArticle.class.getName())) {
			JournalArticle journalArticle =
				JournalArticleLocalServiceUtil.getLatestArticle(
					assetEntry.getClassPK());

			return WorkflowConstants.toLabel(journalArticle.getStatus());
		}

		return StringPool.BLANK;
	}

	public static int getMarketplaceAssetEntryType(AssetEntry assetEntry)
		throws PortalException, SystemException {

		String className = assetEntry.getClassName();

		if (className.equals(AppEntry.class.getName())) {
			return MARKETPLACE_ASSET_ENTRY_TYPE_APP;
		}
		else if (className.equals(JournalArticle.class.getName())) {
			JournalArticle journalArticle =
				JournalArticleLocalServiceUtil.getLatestArticle(
					assetEntry.getClassPK());

			String journalTemplateId = journalArticle.getTemplateId();

			if (journalTemplateId.equals(
					OSBConstants.JOURNAL_TEMPLATE_MARKETPLACE_BANNER_ID)) {

				return MARKETPLACE_ASSET_ENTRY_TYPE_BANNER;
			}
			else if (journalTemplateId.equals(
						OSBConstants.
							JOURNAL_TEMPLATE_MARKETPLACE_MEDIUM_AD_ID)) {

				return MARKETPLACE_ASSET_ENTRY_TYPE_MEDIUM_SIZED_AD;
			}
		}

		return 0;
	}

	public static String getMarketplaceAssetEntryTypeLabel(
			AssetEntry assetEntry)
		throws PortalException, SystemException {

		if (assetEntry == null) {
			return StringPool.BLANK;
		}

		int assetEntryType = getMarketplaceAssetEntryType(assetEntry);

		return getMarketplaceAssetEntryTypeLabel(assetEntryType);
	}

	public static String getMarketplaceAssetEntryTypeLabel(int type) {
		if (type == MARKETPLACE_ASSET_ENTRY_TYPE_APP) {
			return "app";
		}
		else if (type == MARKETPLACE_ASSET_ENTRY_TYPE_BANNER) {
			return "banner";
		}
		else if (type == MARKETPLACE_ASSET_ENTRY_TYPE_MEDIUM_SIZED_AD) {
			return "medium-sized-ad";
		}
		else {
			return StringPool.BLANK;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MarketplaceAdminUtil.class);

}