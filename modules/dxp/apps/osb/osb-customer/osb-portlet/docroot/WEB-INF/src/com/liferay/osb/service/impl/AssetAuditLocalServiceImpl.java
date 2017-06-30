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

import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AppVersionConstants;
import com.liferay.osb.model.AssetAudit;
import com.liferay.osb.model.AssetAuditConstants;
import com.liferay.osb.model.AssetStatsDay;
import com.liferay.osb.model.AssetStatsMonth;
import com.liferay.osb.model.AssetStatsWeek;
import com.liferay.osb.model.CorpEntry;
import com.liferay.osb.model.DeveloperEntry;
import com.liferay.osb.service.base.AssetAuditLocalServiceBaseImpl;
import com.liferay.osb.util.comparator.AssetAuditCreateDateComparator;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.async.Async;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.User;
import com.liferay.portlet.asset.model.AssetEntry;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 */
public class AssetAuditLocalServiceImpl extends AssetAuditLocalServiceBaseImpl {

	@Async
	public void addAssetAudit(
			long userId, String legalEntityName, String className, long classPK,
			int type, int domain, String currencyCode, double price)
		throws PortalException, SystemException {

		// Asset audit

		User user = userPersistence.findByPrimaryKey(userId);

		long classNameId = PortalUtil.getClassNameId(className);
		Date now = new Date();

		if (type == AssetAuditConstants.TYPE_VIEW) {
			if (user.isDefaultUser()) {
				return;
			}

			List<AssetAudit> assetAudits = assetAuditPersistence.findByU_C_C_T(
				userId, classNameId, classPK, type, 0, 1,
				new AssetAuditCreateDateComparator());

			if (!assetAudits.isEmpty()) {
				AssetAudit assetAudit = assetAudits.get(0);

				int daysBetween = DateUtil.getDaysBetween(
					assetAudit.getCreateDate(), now, user.getTimeZone());

				if (daysBetween == 0) {
					return;
				}
			}
		}

		long assetAuditId = counterLocalService.increment();

		AssetAudit assetAudit = assetAuditPersistence.create(assetAuditId);

		assetAudit.setCompanyId(user.getCompanyId());
		assetAudit.setUserId(user.getUserId());
		assetAudit.setUserName(user.getFullName());
		assetAudit.setCreateDate(now);
		assetAudit.setModifiedDate(now);
		assetAudit.setLegalEntityName(legalEntityName);
		assetAudit.setClassNameId(classNameId);
		assetAudit.setClassPK(classPK);

		if (className.equals(AppEntry.class.getName())) {
			long vendorClassNameId = 0;
			long vendorClassPK = 0;

			AppEntry appEntry = appEntryPersistence.findByPrimaryKey(classPK);

			DeveloperEntry developerEntry =
				developerEntryPersistence.findByPrimaryKey(
					appEntry.getDeveloperEntryId());

			if (developerEntry.isCompany()) {
				vendorClassNameId = PortalUtil.getClassNameId(CorpEntry.class);

				CorpEntry corpEntry =
					corpEntryPersistence.findByDossieraAccountKey(
						developerEntry.getDossieraAccountKey());

				vendorClassPK = corpEntry.getCorpEntryId();
			}
			else if (developerEntry.isUser()) {
				vendorClassNameId = PortalUtil.getClassNameId(User.class);
				vendorClassPK = developerEntry.getUserId();
			}

			assetAudit.setVendorClassNameId(vendorClassNameId);
			assetAudit.setVendorClassPK(vendorClassPK);
		}

		assetAudit.setType(type);
		assetAudit.setDomain(domain);
		assetAudit.setTime(now.getTime());
		assetAudit.setCurrencyCode(currencyCode);
		assetAudit.setPrice(price);

		assetAuditPersistence.update(assetAudit, false);

		// Asset recommendation sets

		assetRecommendationSetLocalService.updateAssetRecommendationSets(
			userId, className, classPK, type);

		// Asset

		incrementViewCounter(userId, className, classPK, type);
	}

	public void deleteAssetAudits() throws SystemException {
		assetAuditPersistence.removeAll();
	}

	public void deleteAssetAudits(String className, long classPK)
		throws SystemException {

		// Asset audits

		long classNameId = PortalUtil.getClassNameId(className);

		assetAuditPersistence.removeByC_C(classNameId, classPK);

		// Asset recommendation set

		assetRecommendationSetLocalService.deleteAssetRecommendationSet(
			className, classPK);

		// Asset stats

		List<AssetStatsDay> assetStatsDays = assetStatsDayPersistence.findByC_C(
			classNameId, classPK);

		for (AssetStatsDay assetStatsDay : assetStatsDays) {
			assetStatsDayLocalService.deleteAssetStatsDay(assetStatsDay);
		}

		List<AssetStatsMonth> assetStatsMonths =
			assetStatsMonthPersistence.findByC_C(classNameId, classPK);

		for (AssetStatsMonth assetStatsMonth : assetStatsMonths) {
			assetStatsMonthLocalService.deleteAssetStatsMonth(assetStatsMonth);
		}

		List<AssetStatsWeek> assetStatsWeeks =
			assetStatsWeekPersistence.findByC_C(classNameId, classPK);

		for (AssetStatsWeek assetStatsWeek : assetStatsWeeks) {
			assetStatsWeekLocalService.deleteAssetStatsWeek(assetStatsWeek);
		}
	}

	public List<JSONObject> getAssetAuditPurchaseCountJSONObjects(
			Date createDateGT, Date createDateLT, String vendorClassName,
			long vendorClassPK, int start, int end)
		throws SystemException {

		long vendorClassNameId = PortalUtil.getClassNameId(vendorClassName);

		List<Object[]> results = assetAuditFinder.findByGtCD_LtCD_VCNI_VCPK_T(
			createDateGT, createDateLT, vendorClassNameId, vendorClassPK,
			AssetAuditConstants.TYPE_PURCHASE, start, end);

		List<JSONObject> jsonObjects = new ArrayList<JSONObject>();

		for (Object[] result : results) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("legalEntityName", GetterUtil.getString(result[0]));
			jsonObject.put(
				"companyPurchaseCount", GetterUtil.getLong(result[1]));

			jsonObjects.add(jsonObject);
		}

		return jsonObjects;
	}

	public List<AssetAudit> getAssetAudits(
			Date createDateGE, Date createDateLE, String className,
			long classPK, int type, int start, int end, OrderByComparator obc)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		DynamicQuery dynamicQuery = buildDynamicQuery(
			createDateGE, createDateLE, classNameId, classPK, type);

		return dynamicQuery(dynamicQuery, start, end, obc);
	}

	public List<AssetAudit> getAssetAudits(
			int start, int end, OrderByComparator obc)
		throws SystemException {

		return assetAuditPersistence.findAll(start, end, obc);
	}

	public int getAssetAuditsCount() throws SystemException {
		return assetAuditPersistence.countAll();
	}

	public int getAssetAuditsCount(
			Date createDateGE, Date createDateLE, String className,
			long classPK, int type)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		DynamicQuery dynamicQuery = buildDynamicQuery(
			createDateGE, createDateLE, classNameId, classPK, type);

		return (int)dynamicQueryCount(dynamicQuery);
	}

	protected DynamicQuery buildDynamicQuery(
		Date createDateGE, Date createDateLE, long classNameId, long classPK,
		int type) {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			AssetAudit.class);

		Junction junction = RestrictionsFactoryUtil.conjunction();

		Property createDateProperty = PropertyFactoryUtil.forName("createDate");

		junction.add(createDateProperty.ge(createDateGE));
		junction.add(createDateProperty.le(createDateLE));

		Property classNameIdproperty = PropertyFactoryUtil.forName(
			"classNameId");

		junction.add(classNameIdproperty.eq(classNameId));

		Property classPKProperty = PropertyFactoryUtil.forName("classPK");

		junction.add(classPKProperty.eq(classPK));

		Property typeProperty = PropertyFactoryUtil.forName("type");

		junction.add(typeProperty.eq(type));

		return dynamicQuery.add(junction);
	}

	protected void incrementViewCounter(
			long userId, String className, long classPK, int type)
		throws PortalException, SystemException {

		if (type != AssetAuditConstants.TYPE_VIEW) {
			return;
		}

		if (!className.equals(AppEntry.class.getName())) {
			return;
		}

		long classNameId = PortalUtil.getClassNameId(className);

		AssetEntry assetEntry = assetEntryPersistence.fetchByC_C(
			classNameId, classPK);

		if (assetEntry == null) {
			return;
		}

		assetEntryLocalService.incrementViewCounter(
			userId, className, classPK, 1);

		AppVersion appVersion = appVersionLocalService.fetchAppVersion(
			classPK, AppVersionConstants.STATUSES_APPROVED, null);

		if (appVersion == null) {
			return;
		}

		assetEntryLocalService.incrementViewCounter(
			userId, AppVersion.class.getName(), appVersion.getAppVersionId(),
			1);
	}

}