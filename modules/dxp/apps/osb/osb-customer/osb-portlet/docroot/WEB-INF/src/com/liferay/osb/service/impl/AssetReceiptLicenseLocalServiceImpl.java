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
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.NoSuchAssetReceiptLicenseException;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.model.AssetReceipt;
import com.liferay.osb.model.AssetReceiptLicense;
import com.liferay.osb.model.AssetReceiptSupport;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.service.base.AssetReceiptLicenseLocalServiceBaseImpl;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Junction;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 * @author Ryan Park
 * @author Joan Kim
 */
public class AssetReceiptLicenseLocalServiceImpl
	extends AssetReceiptLicenseLocalServiceBaseImpl {

	public AssetReceiptLicense addAssetReceiptLicense(
			long userId, long assetReceiptId, long assetLicenseId,
			long assetEntryId, long ownerClassNameId, long ownerClassPK,
			long productClassNameId, long productClassPK, String productId,
			Date startDate, Date endDate)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		AssetLicense assetLicense = assetLicensePersistence.findByPrimaryKey(
			assetLicenseId);

		if (productClassNameId ==
				PortalUtil.getClassNameId(AppEntry.class)) {

			AppEntry appEntry = appEntryPersistence.findByPrimaryKey(
				productClassPK);

			AppVersion appVersion = appEntry.getApprovedAppVersion();

			productClassNameId = PortalUtil.getClassNameId(
				AppVersion.class.getName());
			productClassPK = appVersion.getAppVersionId();
		}

		Date now = new Date();

		long assetReceiptLicenseId = counterLocalService.increment();

		AssetReceiptLicense assetReceiptLicense =
			assetReceiptLicensePersistence.create(assetReceiptLicenseId);

		assetReceiptLicense.setUserId(userId);
		assetReceiptLicense.setUserName(user.getFullName());
		assetReceiptLicense.setCreateDate(now);
		assetReceiptLicense.setModifiedDate(now);
		assetReceiptLicense.setAssetReceiptId(assetReceiptId);
		assetReceiptLicense.setAssetLicenseId(assetLicenseId);
		assetReceiptLicense.setAssetEntryId(assetEntryId);
		assetReceiptLicense.setOwnerClassNameId(ownerClassNameId);
		assetReceiptLicense.setOwnerClassPK(ownerClassPK);
		assetReceiptLicense.setProductClassNameId(productClassNameId);
		assetReceiptLicense.setProductClassPK(productClassPK);
		assetReceiptLicense.setProductId(productId);

		if (startDate == null) {
			startDate = now;
		}

		assetReceiptLicense.setStartDate(startDate);

		if (endDate == null) {
			endDate = new Date(
				startDate.getTime() + assetLicense.getLifetime());
		}

		assetReceiptLicense.setEndDate(endDate);

		assetReceiptLicense.setUsageType(assetLicense.getUsageType());
		assetReceiptLicense.setLicenseType(assetLicense.getLicenseType());
		assetReceiptLicense.setLicenseTypeAllotment(
			assetLicense.getLicenseTypeAllotment());
		assetReceiptLicense.setLicenseLifetime(assetLicense.getLifetime());

		assetReceiptLicensePersistence.update(assetReceiptLicense, false);

		return assetReceiptLicense;
	}

	@Override
	public AssetReceiptLicense deleteAssetReceiptLicense(
			AssetReceiptLicense assetReceiptLicense)
		throws PortalException, SystemException {

		// Asset receipt license

		List<LicenseKey> licenseKeys = licenseKeyPersistence.findByARLI_A(
			assetReceiptLicense.getAssetReceiptLicenseId(), true);

		for (LicenseKey licenseKey : licenseKeys) {
			licenseKeyLocalService.updateLicenseKey(
				OSBConstants.USER_DEFAULT_USER_ID, licenseKey.getLicenseKeyId(),
				assetReceiptLicense.getAssetReceiptLicenseId(), false);
		}

		assetReceiptLicensePersistence.remove(assetReceiptLicense);

		// Asset receipt supports

		List<AssetReceiptSupport> assetReceiptSupports =
			assetReceiptSupportLocalService.getAssetReceiptSupports(
				assetReceiptLicense.getAssetReceiptId(),
				AssetReceiptLicense.class.getName(),
				assetReceiptLicense.getAssetReceiptLicenseId(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (AssetReceiptSupport assetReceiptSupport : assetReceiptSupports) {
			assetReceiptSupportLocalService.deleteAssetReceiptSupport(
				assetReceiptSupport);
		}

		return assetReceiptLicense;
	}

	@Override
	public AssetReceiptLicense deleteAssetReceiptLicense(
			long assetReceiptLicenseId)
		throws PortalException, SystemException {

		AssetReceiptLicense assetReceiptLicense =
			assetReceiptLicensePersistence.findByPrimaryKey(
				assetReceiptLicenseId);

		return deleteAssetReceiptLicense(assetReceiptLicense);
	}

	public List<AssetReceiptLicense> getActiveAssetReceiptLicenses(
			long assetReceiptId, int usageType, int start, int end)
		throws SystemException {

		Date now = new Date();

		return assetReceiptLicensePersistence.findByARI_LtSD_GtED_UT(
			assetReceiptId, now, now, usageType, start, end);
	}

	public int getActiveAssetReceiptLicensesCount(long assetReceiptId)
		throws SystemException {

		Date now = new Date();

		return assetReceiptLicensePersistence.countByARI_LtSD_GtED(
			assetReceiptId, now, now);
	}

	public int getActiveAssetReceiptLicensesCount(
			long assetReceiptId, int usageType)
		throws SystemException {

		Date now = new Date();

		return assetReceiptLicensePersistence.countByARI_LtSD_GtED_UT(
			assetReceiptId, now, now, usageType);
	}

	public long getActiveAssetReceiptLicenseTypeAllotment(
			long assetReceiptId, int usageType)
		throws SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			AssetReceiptLicense.class);

		Junction junction = RestrictionsFactoryUtil.conjunction();

		Property assetReceiptIdProperty = PropertyFactoryUtil.forName(
			"assetReceiptId");

		junction.add(assetReceiptIdProperty.eq(assetReceiptId));

		Date now = new Date();

		Property startDateProperty = PropertyFactoryUtil.forName("startDate");

		junction.add(startDateProperty.lt(now));

		Property endDateProperty = PropertyFactoryUtil.forName("endDate");

		junction.add(endDateProperty.gt(now));

		Property usageTypeProperty = PropertyFactoryUtil.forName("usageType");

		junction.add(usageTypeProperty.eq(usageType));

		dynamicQuery.add(junction);

		ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

		projectionList.add(
			ProjectionFactoryUtil.sum("licenseTypeAllotment"),
			"totalLicenseTypeAllotment");

		dynamicQuery = dynamicQuery.setProjection(projectionList);

		List<Long> results = dynamicQuery(dynamicQuery, 0, 1);

		Long result = results.get(0);

		if (result != null) {
			return result.longValue();
		}
		else {
			return 0;
		}
	}

	public AssetReceiptLicense getAssetReceiptLicense(String uuid)
		throws PortalException, SystemException {

		List<AssetReceiptLicense> assetReceiptLicenses =
			assetReceiptLicensePersistence.findByUuid(uuid);

		if (assetReceiptLicenses.isEmpty()) {
			throw new NoSuchAssetReceiptLicenseException();
		}
		else {
			return assetReceiptLicenses.get(0);
		}
	}

	public List<AssetReceiptLicense> getAssetReceiptLicenses(
			long assetReceiptId)
		throws SystemException {

		return assetReceiptLicensePersistence.findByAssetReceiptId(
			assetReceiptId);
	}

	public List<AssetReceiptLicense> getAssetReceiptLicenses(
			long assetReceiptId, int start, int end, OrderByComparator obc)
		throws SystemException {

		return assetReceiptLicensePersistence.findByAssetReceiptId(
			assetReceiptId, start, end, obc);
	}

	public List<AssetReceiptLicense> getAssetReceiptLicenses(
			String ownerClassName, long ownerClassPK, int start, int end)
		throws SystemException {

		long ownerClassNameId = PortalUtil.getClassNameId(ownerClassName);

		return assetReceiptLicenseFinder.findByOCN_OCP(
			ownerClassNameId, ownerClassPK, start, end);
	}

	public List<AssetReceiptLicense> getAssetReceiptLicenses(
			String ownerClassName, long ownerClassPK, String productId)
		throws SystemException {

		long ownerClassNameId = PortalUtil.getClassNameId(ownerClassName);

		return assetReceiptLicensePersistence.findByOCN_OCP_PI(
			ownerClassNameId, ownerClassPK, productId);
	}

	public List<AssetReceiptLicense> getAssetReceiptLicenses(
			String ownerClassName, long ownerClassPK, String productClassName,
			int start, int end)
		throws SystemException {

		long ownerClassNameId = PortalUtil.getClassNameId(ownerClassName);
		long productClassNameId = PortalUtil.getClassNameId(productClassName);

		return assetReceiptLicensePersistence.findByOCN_OCP_PCN(
			ownerClassNameId, ownerClassPK, productClassNameId, start, end);
	}

	public List<AssetReceiptLicense> getAssetReceiptLicenses(
			String ownerClassName, long ownerClassPK, String productId,
			int licenseType, long licenseLifetime, int start, int end)
		throws SystemException {

		long ownerClassNameId = PortalUtil.getClassNameId(ownerClassName);

		return assetReceiptLicensePersistence.findByOCN_OCP_PI_LT_LL(
			ownerClassNameId, ownerClassPK, productId, licenseType,
			licenseLifetime, start, end);
	}

	public int getAssetReceiptLicensesCount(long assetReceiptId)
		throws SystemException {

		return assetReceiptLicensePersistence.countByAssetReceiptId(
			assetReceiptId);
	}

	public int getAssetReceiptLicensesCount(
			long ownerClassNameId, long ownerClassPK, String productId)
		throws SystemException {

		return assetReceiptLicensePersistence.countByOCN_OCP_PI(
			ownerClassNameId, ownerClassPK, productId);
	}

	public int getAssetReceiptLicensesCount(
			String ownerClassName, long ownerClassPK)
		throws SystemException {

		long ownerClassNameId = PortalUtil.getClassNameId(ownerClassName);

		return assetReceiptLicenseFinder.countByOCN_OCP(
			ownerClassNameId, ownerClassPK);
	}

	public int getAssetReceiptLicensesCount(
			String ownerClassName, long ownerClassPK, String productClassName)
		throws SystemException {

		long ownerClassNameId = PortalUtil.getClassNameId(ownerClassName);
		long productClassNameId = PortalUtil.getClassNameId(productClassName);

		return assetReceiptLicensePersistence.countByOCN_OCP_PCN(
			ownerClassNameId, ownerClassPK, productClassNameId);
	}

	public int getAssetReceiptLicensesCount(
			String ownerClassName, long ownerClassPK, String productId,
			int licenseType, long licenseLifetime)
		throws SystemException {

		long ownerClassNameId = PortalUtil.getClassNameId(ownerClassName);

		return assetReceiptLicensePersistence.countByOCN_OCP_PI_LT_LL(
			ownerClassNameId, ownerClassPK, productId, licenseType,
			licenseLifetime);
	}

	public List<AssetReceiptLicense> getRenewedAssetReceiptLicenses(
			long assetReceiptId, int usageType, int start, int end)
		throws SystemException {

		return assetReceiptLicensePersistence.findByARI_GtSD_UT(
			assetReceiptId, new Date(), usageType, start, end);
	}

	public int getRenewedAssetReceiptLicensesCount(
			long assetReceiptId, int usageType)
		throws SystemException {

		return assetReceiptLicensePersistence.countByARI_GtSD_UT(
			assetReceiptId, new Date(), usageType);
	}

	public long getRenewedAssetReceiptLicenseTypeAllotment(
			long assetReceiptId, int usageType)
		throws SystemException {

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			AssetReceiptLicense.class);

		Junction junction = RestrictionsFactoryUtil.conjunction();

		Property assetReceiptIdProperty = PropertyFactoryUtil.forName(
			"assetReceiptId");

		junction.add(assetReceiptIdProperty.eq(assetReceiptId));

		Date now = new Date();

		Property startDateProperty = PropertyFactoryUtil.forName("startDate");

		junction.add(startDateProperty.gt(now));

		Property usageTypeProperty = PropertyFactoryUtil.forName("usageType");

		junction.add(usageTypeProperty.eq(usageType));

		dynamicQuery.add(junction);

		ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

		projectionList.add(
			ProjectionFactoryUtil.sum("licenseTypeAllotment"),
			"totalLicenseTypeAllotment");

		dynamicQuery = dynamicQuery.setProjection(projectionList);

		List<Long> results = dynamicQuery(dynamicQuery, 0, 1);

		Long result = results.get(0);

		if (result != null) {
			return result.longValue();
		}
		else {
			return 0;
		}
	}

	public boolean hasAssetReceiptLicense(long assetLicenseId)
		throws SystemException {

		int count = assetReceiptLicensePersistence.countByAssetLicenseId(
			assetLicenseId);

		if (count > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public List<AssetReceiptLicense> updateAssetReceiptLicenses(
			long assetReceiptId, int usageType, Date startDate, Date endDate)
		throws PortalException, SystemException {

		// Asset receipt license

		Date now = new Date();

		List<AssetReceiptLicense> assetReceiptLicenses =
			assetReceiptLicensePersistence.findByARI_LtSD_GtED_UT(
				assetReceiptId, now, now, usageType);

		for (AssetReceiptLicense assetReceiptLicense : assetReceiptLicenses) {
			assetReceiptLicense.setModifiedDate(now);

			if (endDate != null) {
				assetReceiptLicense.setEndDate(endDate);
			}

			if (startDate != null) {
				assetReceiptLicense.setStartDate(startDate);
			}

			assetReceiptLicensePersistence.update(assetReceiptLicense, false);
		}

		// Asset receipt support

		AssetReceipt assetReceipt = assetReceiptPersistence.findByPrimaryKey(
			assetReceiptId);

		long classNameId = PortalUtil.getClassNameId(AppEntry.class);

		if (classNameId == assetReceipt.getProductClassNameId()) {
			AppEntry appEntry = appEntryPersistence.fetchByPrimaryKey(
				assetReceipt.getProductClassPK());

			AppVersion appVersion = appEntry.getApprovedAppVersion();

			if (appVersion.isSupported()) {
				endDate = new Date(
					startDate.getTime() +
					AssetLicenseConstants.LIFETIME_SUBSCRIPTION);

				assetReceiptSupportLocalService.updateAssetReceiptSupports(
					assetReceiptId, usageType, startDate, endDate);
			}
		}

		return assetReceiptLicenses;
	}

}