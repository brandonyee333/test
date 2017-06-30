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

import com.liferay.compat.portal.kernel.util.ArrayUtil;
import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.compat.portal.util.PortalUtil;
import com.liferay.osb.AssetLicenseClassNameException;
import com.liferay.osb.AssetLicenseLicenseTypeAllotmentException;
import com.liferay.osb.AssetLicenseLicenseTypeException;
import com.liferay.osb.AssetLicenseLifetimeException;
import com.liferay.osb.AssetLicenseStatusException;
import com.liferay.osb.AssetLicenseUsageTypeException;
import com.liferay.osb.model.AppPricingItem;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.service.base.AssetLicenseLocalServiceBaseImpl;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 * @author Ryan Park
 */
public class AssetLicenseLocalServiceImpl
	extends AssetLicenseLocalServiceBaseImpl {

	public AssetLicense addAssetLicense(
			long userId, String className, long classPK, String licenseId,
			String name, int usageType, int licenseType,
			long licenseTypeAllotment, long lifetime)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);
		Date now = new Date();

		validate(
			0, userId, classNameId, classPK, usageType, licenseType,
			licenseTypeAllotment, lifetime, WorkflowConstants.STATUS_APPROVED);

		long assetLicenseId = counterLocalService.increment();

		AssetLicense assetLicense = assetLicensePersistence.create(
			assetLicenseId);

		assetLicense.setUserId(userId);
		assetLicense.setCreateDate(now);
		assetLicense.setModifiedDate(now);
		assetLicense.setClassNameId(classNameId);
		assetLicense.setClassPK(classPK);

		licenseId = getLicenseId(assetLicense, licenseId, usageType);

		assetLicense.setLicenseId(licenseId);

		assetLicense.setName(name);
		assetLicense.setUsageType(usageType);
		assetLicense.setLicenseType(licenseType);
		assetLicense.setLicenseTypeAllotment(licenseTypeAllotment);
		assetLicense.setLifetime(lifetime);
		assetLicense.setStatus(WorkflowConstants.STATUS_APPROVED);

		assetLicensePersistence.update(assetLicense, false);

		return assetLicense;
	}

	public void copyAssetLicenses(
			String sourceClassName, long sourceClassPK, String targetClassName,
			long targetClassPK)
		throws PortalException, SystemException {

		long sourceClassNameId = PortalUtil.getClassNameId(sourceClassName);
		long targetClassNameId = PortalUtil.getClassNameId(targetClassName);
		Date now = new Date();

		if (sourceClassNameId != targetClassNameId) {
			throw new AssetLicenseClassNameException();
		}

		List<AssetLicense> assetLicenses = assetLicensePersistence.findByC_C(
			sourceClassNameId, sourceClassPK);

		for (AssetLicense assetLicense : assetLicenses) {
			long targetAssetLicenseId = counterLocalService.increment();

			AssetLicense targetAssetLicense = assetLicensePersistence.create(
				targetAssetLicenseId);

			targetAssetLicense.setUserId(assetLicense.getUserId());
			targetAssetLicense.setCreateDate(now);
			targetAssetLicense.setModifiedDate(now);
			targetAssetLicense.setClassNameId(targetClassNameId);
			targetAssetLicense.setClassPK(targetClassPK);
			targetAssetLicense.setLicenseId(assetLicense.getLicenseId());
			targetAssetLicense.setName(assetLicense.getName());
			targetAssetLicense.setUsageType(assetLicense.getUsageType());
			targetAssetLicense.setLicenseType(assetLicense.getLicenseType());
			targetAssetLicense.setLicenseTypeAllotment(
				assetLicense.getLicenseTypeAllotment());
			targetAssetLicense.setLifetime(assetLicense.getLifetime());

			assetLicensePersistence.update(targetAssetLicense, false);

			if (sourceClassName.equals(AppVersion.class.getName())) {
				List<AppPricingItem> appPricingItems =
					appPricingItemFinder.findByAVI_ALI(
						targetClassPK, assetLicense.getAssetLicenseId());

				for (AppPricingItem appPricingItem : appPricingItems) {
					appPricingItemLocalService.updateAppPricingItem(
						appPricingItem.getAppPricingItemId(),
						targetAssetLicense.getAssetLicenseId());
				}
			}
		}
	}

	@Override
	public AssetLicense deleteAssetLicense(AssetLicense assetLicense)
		throws SystemException {

		// Asset license

		assetLicensePersistence.remove(assetLicense);

		// App pricing items

		appPricingItemLocalService.deleteAppPricingItemByAssetLicenseId(
			assetLicense.getAssetLicenseId());

		return assetLicense;
	}

	@Override
	public AssetLicense deleteAssetLicense(long assetLicenseId)
		throws PortalException, SystemException {

		AssetLicense assetLicense = assetLicensePersistence.findByPrimaryKey(
			assetLicenseId);

		return deleteAssetLicense(assetLicense);
	}

	public void deleteAssetLicenses(long classNameId, long classPK)
		throws SystemException {

		List<AssetLicense> assetLicenses = assetLicensePersistence.findByC_C(
			classNameId, classPK);

		// Asset licenses

		assetLicensePersistence.removeByC_C(classNameId, classPK);

		// App pricing items

		for (AssetLicense assetLicense : assetLicenses) {
			appPricingItemLocalService.deleteAppPricingItemByAssetLicenseId(
				assetLicense.getAssetLicenseId());
		}
	}

	public void deleteAssetLicenses(String className, long classPK)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		deleteAssetLicenses(classNameId, classPK);
	}

	public AssetLicense fetchAssetLicense(
			long classNameId, long classPK, int usageType, int licenseType,
			long licenseTypeAllotment, int status)
		throws SystemException {

		return assetLicensePersistence.fetchByC_C_UT_LT_LTA_S_First(
			classNameId, classPK, usageType, licenseType, licenseTypeAllotment,
			status, null);
	}

	public AssetLicense fetchAssetLicense(
			String className, long classPK, int usageType, int licenseType,
			long licenseTypeAllotment, int status)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return fetchAssetLicense(
			classNameId, classPK, usageType, licenseType, licenseTypeAllotment,
			status);
	}

	public List<AssetLicense> getAssetLicenses(
			long classNameId, long classPK, int usageType, int licenseType,
			int status, int start, int end, OrderByComparator obc)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return assetLicensePersistence.findByC_C_UT_LT(
				classNameId, classPK, usageType, licenseType, start, end, obc);
		}
		else {
			return assetLicensePersistence.findByC_C_UT_LT_S(
				classNameId, classPK, usageType, licenseType, status, start,
				end, obc);
		}
	}

	public List<AssetLicense> getAssetLicenses(
			long classNameId, long classPK, int status, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		if (status == WorkflowConstants.STATUS_ANY) {
			return assetLicensePersistence.findByC_C(
				classNameId, classPK, start, end, obc);
		}
		else {
			return assetLicensePersistence.findByC_C_S(
				classNameId, classPK, status, start, end, obc);
		}
	}

	public List<AssetLicense> getAssetLicenses(
			String className, long classPK, int usageType, int licenseType,
			int status, int start, int end, OrderByComparator obc)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return getAssetLicenses(
			classNameId, classPK, usageType, licenseType, status, start, end,
			obc);
	}

	public List<AssetLicense> getAssetLicenses(
			String className, long classPK, int status, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		return getAssetLicenses(classNameId, classPK, status, start, end, obc);
	}

	public int getAssetLicensesCount(String className, long classPK, int status)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		if (status == WorkflowConstants.STATUS_ANY) {
			return assetLicensePersistence.countByC_C(classNameId, classPK);
		}
		else {
			return assetLicensePersistence.countByC_C_S(
				classNameId, classPK, status);
		}
	}

	public int getAssetLicensesCount(
			String className, long classPK, int usageType, int licenseType,
			int status)
		throws SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		if (status == WorkflowConstants.STATUS_ANY) {
			return assetLicensePersistence.countByC_C_UT_LT(
				classNameId, classPK, usageType, licenseType);
		}
		else {
			return assetLicensePersistence.countByC_C_UT_LT_S(
				classNameId, classPK, usageType, licenseType, status);
		}
	}

	public AssetLicense updateAssetLicense(
			long assetLicenseId, String name, int usageType, int licenseType,
			long licenseTypeAllotment, long lifetime, int status)
		throws PortalException, SystemException {

		AssetLicense assetLicense = assetLicensePersistence.findByPrimaryKey(
			assetLicenseId);

		validate(
			assetLicenseId, assetLicense.getClassNameId(),
			assetLicense.getClassPK(), usageType, licenseType,
			licenseTypeAllotment, lifetime, status);

		assetLicense.setModifiedDate(new Date());
		assetLicense.setName(name);
		assetLicense.setUsageType(usageType);
		assetLicense.setLicenseType(licenseType);
		assetLicense.setLicenseTypeAllotment(licenseTypeAllotment);
		assetLicense.setLifetime(lifetime);
		assetLicense.setStatus(status);

		assetLicensePersistence.update(assetLicense, false);

		return assetLicense;
	}

	public void updateAssetLicenses(
			long userId, String className, long classPK,
			List<AssetLicense> assetLicenses)
		throws PortalException, SystemException {

		long classNameId = PortalUtil.getClassNameId(className);

		List<AssetLicense> oldAssetLicenses = assetLicensePersistence.findByC_C(
			classNameId, classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		oldAssetLicenses = ListUtil.copy(oldAssetLicenses);

		for (AssetLicense assetLicense : assetLicenses) {
			AssetLicense oldAssetLicense =
				assetLicensePersistence.fetchByC_C_UT_LT_LTA_First(
					classNameId, classPK, assetLicense.getUsageType(),
					assetLicense.getLicenseType(),
					assetLicense.getLicenseTypeAllotment(), null);

			if (oldAssetLicense == null) {
				addAssetLicense(
					userId, className, classPK, assetLicense.getLicenseId(),
					assetLicense.getName(), assetLicense.getUsageType(),
					assetLicense.getLicenseType(),
					assetLicense.getLicenseTypeAllotment(),
					assetLicense.getLifetime());
			}
			else {
				oldAssetLicenses.remove(oldAssetLicense);

				updateAssetLicense(
					oldAssetLicense.getAssetLicenseId(), assetLicense.getName(),
					assetLicense.getUsageType(), assetLicense.getLicenseType(),
					assetLicense.getLicenseTypeAllotment(),
					assetLicense.getLifetime(),
					WorkflowConstants.STATUS_APPROVED);
			}
		}

		for (AssetLicense assetLicense : oldAssetLicenses) {
			if (assetLicense.isPurchased()) {
				assetLicense.setStatus(WorkflowConstants.STATUS_EXPIRED);
			}
			else {
				deleteAssetLicense(assetLicense);
			}
		}
	}

	protected String getLicenseId(
		AssetLicense assetLicense, String licenseId, int usageType) {

		if (Validator.isNotNull(licenseId)) {
			return licenseId;
		}

		StringBundler sb = new StringBundler(4);

		if (usageType == AssetLicenseConstants.USAGE_TYPE_DEVELOPER) {
			sb.append("Developer License ");
		}
		else if (usageType == AssetLicenseConstants.USAGE_TYPE_STANDARD) {
			sb.append("Standard License ");
		}
		else if (usageType == AssetLicenseConstants.USAGE_TYPE_TRIAL) {
			sb.append("Trial License ");
		}

		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(assetLicense.getAssetLicenseId());
		sb.append(StringPool.CLOSE_PARENTHESIS);

		return sb.toString();
	}

	protected void validate(
			long assetLicenseId, long classNameId, long classPK, int usageType,
			int licenseType, long licenseTypeAllotment, long lifetime,
			int status)
		throws PortalException, SystemException {

		if (assetLicenseId > 0) {
			assetLicensePersistence.findByPrimaryKey(assetLicenseId);
		}

		if (!ArrayUtil.contains(AssetLicenseConstants.USAGE_TYPES, usageType)) {
			throw new AssetLicenseUsageTypeException();
		}

		if (!ArrayUtil.contains(
				AssetLicenseConstants.LICENSE_TYPES, licenseType)) {

			throw new AssetLicenseLicenseTypeException();
		}

		if ((licenseTypeAllotment <= 0) &&
			(licenseType != AssetLicenseConstants.LICENSE_TYPE_UNLIMITED)) {

			throw new AssetLicenseLicenseTypeAllotmentException();
		}

		if (!ArrayUtil.contains(AssetLicenseConstants.LIFETIMES, lifetime)) {
			throw new AssetLicenseLifetimeException();
		}

		if ((status != WorkflowConstants.STATUS_APPROVED) &&
			(status != WorkflowConstants.STATUS_EXPIRED)) {

			throw new AssetLicenseStatusException();
		}
	}

	protected void validate(
			long assetLicenseId, long userId, long classNameId, long classPK,
			int usageType, int licenseType, long licenseTypeAllotment,
			long lifetime, int status)
		throws PortalException, SystemException {

		userPersistence.findByPrimaryKey(userId);

		validate(
			assetLicenseId, classNameId, classPK, usageType, licenseType,
			licenseTypeAllotment, lifetime, status);
	}

}