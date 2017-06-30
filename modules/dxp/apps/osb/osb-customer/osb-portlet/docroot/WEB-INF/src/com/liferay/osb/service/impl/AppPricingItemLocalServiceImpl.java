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

import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.osb.AppPricingItemCurrencyEntryException;
import com.liferay.osb.AppPricingItemPriceException;
import com.liferay.osb.RequiredAppPricingItemException;
import com.liferay.osb.model.AppPricing;
import com.liferay.osb.model.AppPricingItem;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AssetLicense;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.model.CountryAppPricing;
import com.liferay.osb.model.CurrencyEntry;
import com.liferay.osb.service.base.AppPricingItemLocalServiceBaseImpl;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Douglas Wong
 * @author Ryan Park
 */
public class AppPricingItemLocalServiceImpl
	extends AppPricingItemLocalServiceBaseImpl {

	public void copyAppPricingItems(
			long sourceAppPricingId, long targetAppPricingId)
		throws PortalException, SystemException {

		AppPricing appPricing = appPricingPersistence.findByPrimaryKey(
			targetAppPricingId);

		List<AppPricingItem> appPricingItems =
			appPricingItemPersistence.findByAppPricingId(sourceAppPricingId);

		for (AppPricingItem appPricingItem : appPricingItems) {
			long targetAppPricingItemId = counterLocalService.increment();

			AppPricingItem targetAppPricingItem =
				appPricingItemPersistence.create(targetAppPricingItemId);

			targetAppPricingItem.setAppPricingId(targetAppPricingId);

			AssetLicense assetLicense =
				assetLicensePersistence.findByPrimaryKey(
					appPricingItem.getAssetLicenseId());

			AssetLicense targetAssetLicense =
				assetLicenseLocalService.fetchAssetLicense(
					assetLicense.getClassNameId(), appPricing.getAppVersionId(),
					assetLicense.getUsageType(), assetLicense.getLicenseType(),
					assetLicense.getLicenseTypeAllotment(),
					assetLicense.getStatus());

			if (targetAssetLicense == null) {
				continue;
			}

			targetAppPricingItem.setAssetLicenseId(
				targetAssetLicense.getAssetLicenseId());

			targetAppPricingItem.setCurrencyEntryId(
				appPricingItem.getCurrencyEntryId());
			targetAppPricingItem.setPrice(appPricingItem.getPrice());

			appPricingItemPersistence.update(targetAppPricingItem, false);
		}
	}

	public void deleteAppPricingItemByAppPricingId(long appPricingId)
		throws SystemException {

		appPricingItemPersistence.removeByAppPricingId(appPricingId);
	}

	public void deleteAppPricingItemByAssetLicenseId(long assetLicenseId)
		throws SystemException {

		appPricingItemPersistence.removeByAssetLicenseId(assetLicenseId);
	}

	public AppPricingItem fetchAppPricingItem(
			long appPricingId, long assetLicenseId)
		throws SystemException {

		return appPricingItemPersistence.fetchByAPI_ALI(
			appPricingId, assetLicenseId);
	}

	public AppPricingItem fetchAppPricingItem(
			long appVersionId, long countryId, long assetLicenseId)
		throws PortalException, SystemException {

		CountryAppPricing countryAppPricing =
			countryAppPricingPersistence.fetchByAVI_CI(appVersionId, countryId);

		if (countryAppPricing == null) {
			return null;
		}

		return appPricingItemPersistence.fetchByAPI_ALI(
			countryAppPricing.getAppPricingId(), assetLicenseId);
	}

	public AppPricingItem getAppPricingItem(
			long appVersionId, long countryId, long assetLicenseId)
		throws PortalException, SystemException {

		CountryAppPricing countryAppPricing =
			countryAppPricingPersistence.findByAVI_CI(appVersionId, countryId);

		return appPricingItemPersistence.findByAPI_ALI(
			countryAppPricing.getAppPricingId(), assetLicenseId);
	}

	public AppPricingItem updateAppPricingItem(
			long appPricingItemId, long assetLicenseId)
		throws PortalException, SystemException {

		AppPricingItem appPricingItem =
			appPricingItemPersistence.findByPrimaryKey(appPricingItemId);

		appPricingItem.setAssetLicenseId(assetLicenseId);

		appPricingItemPersistence.update(appPricingItem, false);

		return appPricingItem;
	}

	public AppPricingItem updateAppPricingItem(
			long appPricingId, long assetLicenseId, double price)
		throws PortalException, SystemException {

		validate(appPricingId, assetLicenseId, price);

		AppPricingItem appPricingItem =
			appPricingItemPersistence.fetchByAPI_ALI(
				appPricingId, assetLicenseId);

		if (appPricingItem == null) {
			long appPricingItemId = counterLocalService.increment();

			appPricingItem = appPricingItemPersistence.create(appPricingItemId);
		}

		appPricingItem.setAppPricingId(appPricingId);
		appPricingItem.setAssetLicenseId(assetLicenseId);

		AppPricing appPricing = appPricingPersistence.findByPrimaryKey(
			appPricingId);

		appPricingItem.setCurrencyEntryId(appPricing.getCurrencyEntryId());

		appPricingItem.setPrice(price);

		appPricingItemPersistence.update(appPricingItem, false);

		return appPricingItem;
	}

	public void updateAppPricingItems(long appPricingId, long currencyEntryId)
		throws PortalException, SystemException {

		List<AppPricingItem> appPricingItems =
			appPricingItemPersistence.findByAppPricingId(appPricingId);

		validate(appPricingId, currencyEntryId);

		for (AppPricingItem appPricingItem : appPricingItems) {
			appPricingItem.setCurrencyEntryId(currencyEntryId);

			appPricingItemPersistence.update(appPricingItem, false);
		}
	}

	public void validateAppPricingItems(long appPricingId)
		throws PortalException, SystemException {

		AppPricing appPricing = appPricingPersistence.findByPrimaryKey(
			appPricingId);

		CurrencyEntry currencyEntry = currencyEntryPersistence.findByPrimaryKey(
			appPricing.getCurrencyEntryId());

		if (!currencyEntry.isMarketplaceEnabled()) {
			throw new AppPricingItemCurrencyEntryException();
		}

		List<AssetLicense> assetLicenses =
			assetLicenseLocalService.getAssetLicenses(
				AppVersion.class.getName(), appPricing.getAppVersionId(),
				WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		assetLicenses = ListUtil.copy(assetLicenses);

		List<AppPricingItem> appPricingItems =
			appPricingItemPersistence.findByAppPricingId(appPricingId);

		for (AppPricingItem appPricingItem : appPricingItems) {
			AssetLicense assetLicense =
				assetLicensePersistence.findByPrimaryKey(
					appPricingItem.getAssetLicenseId());

			assetLicenses.remove(assetLicense);

			if (appPricingItem.getCurrencyEntryId() !=
					appPricing.getCurrencyEntryId()) {

				updateAppPricingItems(
					appPricingId, appPricing.getCurrencyEntryId());
			}

			if (currencyEntry.getMarketplaceMinPrice() >
					appPricingItem.getPrice()) {

				throw new AppPricingItemPriceException();
			}
		}

		for (AssetLicense assetLicense : assetLicenses) {
			if (assetLicense.getUsageType() !=
					AssetLicenseConstants.USAGE_TYPE_TRIAL) {

				throw new RequiredAppPricingItemException();
			}
		}
	}

	protected void validate(long appPricingId, long currencyEntryId)
		throws PortalException, SystemException {

		appPricingPersistence.findByPrimaryKey(appPricingId);

		currencyEntryPersistence.findByPrimaryKey(currencyEntryId);
	}

	protected void validate(
			long appPricingId, long assetLicenseId, double price)
		throws PortalException, SystemException {

		AppPricing appPricing = appPricingPersistence.findByPrimaryKey(
			appPricingId);

		CurrencyEntry currencyEntry = currencyEntryPersistence.findByPrimaryKey(
			appPricing.getCurrencyEntryId());

		if (!currencyEntry.isMarketplaceEnabled()) {
			throw new AppPricingItemCurrencyEntryException();
		}

		assetLicensePersistence.findByPrimaryKey(assetLicenseId);
	}

}