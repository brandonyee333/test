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

import com.liferay.osb.AppPricingCountryException;
import com.liferay.osb.AppPricingPriceException;
import com.liferay.osb.model.AppEntry;
import com.liferay.osb.model.AppPricing;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.AssetLicenseConstants;
import com.liferay.osb.service.base.AppPricingLocalServiceBaseImpl;
import com.liferay.osb.util.comparator.AppPricingRankComparator;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Douglas Wong
 * @author Ryan Park
 */
public class AppPricingLocalServiceImpl extends AppPricingLocalServiceBaseImpl {

	public AppPricing addAppPricing(long userId, long appVersionId, String name)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		AppVersion appVersion = appVersionPersistence.findByPrimaryKey(
			appVersionId);

		long appPricingId = counterLocalService.increment();

		AppPricing appPricing = appPricingPersistence.create(appPricingId);

		appPricing.setUserId(userId);
		appPricing.setUserName(user.getFullName());
		appPricing.setCreateDate(now);
		appPricing.setModifiedDate(now);
		appPricing.setAppEntryId(appVersion.getAppEntryId());
		appPricing.setAppVersionId(appVersion.getAppVersionId());
		appPricing.setName(name);

		int count = appPricingPersistence.countByAppVersionId(appVersionId);

		appPricing.setRank(count + 1);

		appPricingPersistence.update(appPricing, false);

		return appPricing;
	}

	public void copyAppPricings(
			long sourceAppVersionId, long targetAppVersionId)
		throws PortalException, SystemException {

		Date now = new Date();

		AppVersion appVersion = appVersionPersistence.findByPrimaryKey(
			targetAppVersionId);

		List<AppPricing> appPricings = appPricingPersistence.findByAppVersionId(
			sourceAppVersionId);

		for (AppPricing appPricing : appPricings) {

			// App pricing

			long targetAppPricingId = counterLocalService.increment();

			AppPricing targetAppPricing = appPricingPersistence.create(
				targetAppPricingId);

			targetAppPricing.setUserId(appPricing.getUserId());
			targetAppPricing.setUserName(appPricing.getUserName());
			targetAppPricing.setCreateDate(now);
			targetAppPricing.setModifiedDate(now);
			targetAppPricing.setAppEntryId(appVersion.getAppEntryId());
			targetAppPricing.setAppVersionId(appVersion.getAppVersionId());
			targetAppPricing.setName(appPricing.getName());
			targetAppPricing.setCurrencyEntryId(
				appPricing.getCurrencyEntryId());
			targetAppPricing.setStandardSupportPrice(
				appPricing.getStandardSupportPrice());
			targetAppPricing.setDeveloperSupportPrice(
				appPricing.getDeveloperSupportPrice());
			targetAppPricing.setRank(appPricing.getRank());

			appPricingPersistence.update(targetAppPricing, false);

			// App pricing item

			appPricingItemLocalService.copyAppPricingItems(
				appPricing.getAppPricingId(),
				targetAppPricing.getAppPricingId());

			// Country app pricing

			countryAppPricingLocalService.copyCountryAppPricing(
				appPricing.getAppPricingId(),
				targetAppPricing.getAppPricingId());
		}
	}

	@Override
	public AppPricing deleteAppPricing(AppPricing appPricing)
		throws PortalException, SystemException {

		// App pricing

		appPricingPersistence.remove(appPricing);

		// App pricing items

		appPricingItemLocalService.deleteAppPricingItemByAppPricingId(
			appPricing.getAppPricingId());

		// Country app pricing

		countryAppPricingLocalService.deleteCountryAppPricingByAppPricingId(
			appPricing.getAppPricingId());

		return appPricing;
	}

	@Override
	public AppPricing deleteAppPricing(long appPricingId)
		throws PortalException, SystemException {

		AppPricing appPricing = appPricingPersistence.findByPrimaryKey(
			appPricingId);

		return deleteAppPricing(appPricing);
	}

	public void deleteAppPricingByAppVersionId(long appVersionId)
		throws PortalException, SystemException {

		List<AppPricing> appPricings = appPricingPersistence.findByAppVersionId(
			appVersionId);

		for (AppPricing appPricing : appPricings) {
			deleteAppPricing(appPricing);
		}
	}

	public List<AppPricing> getAppPricings(long appVersionId)
		throws SystemException {

		return appPricingPersistence.findByAppVersionId(
			appVersionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new AppPricingRankComparator());
	}

	public AppPricing updateAppPricing(
			long appPricingId, String name, long currencyEntryId,
			double standardSupportPrice, double developerSupportPrice)
		throws PortalException, SystemException {

		Date now = new Date();

		AppPricing appPricing = appPricingPersistence.findByPrimaryKey(
			appPricingId);

		appPricing.setModifiedDate(now);
		appPricing.setName(name);
		appPricing.setCurrencyEntryId(currencyEntryId);
		appPricing.setStandardSupportPrice(standardSupportPrice);
		appPricing.setDeveloperSupportPrice(developerSupportPrice);

		appPricingPersistence.update(appPricing, false);

		// App pricing items

		if (currencyEntryId > 0) {
			appPricingItemLocalService.updateAppPricingItems(
				appPricingId, currencyEntryId);
		}

		return appPricing;
	}

	public void validateAppPricings(long appVersionId)
		throws PortalException, SystemException {

		AppVersion appVersion = appVersionPersistence.findByPrimaryKey(
			appVersionId);

		AppEntry appEntry = appEntryPersistence.findByPrimaryKey(
			appVersion.getAppEntryId());

		List<AppPricing> appPricings = appPricingPersistence.findByAppVersionId(
			appVersionId);

		for (AppPricing appPricing : appPricings) {
			currencyEntryPersistence.findByPrimaryKey(
				appPricing.getCurrencyEntryId());

			if ((appEntry.getLicenseLifetime() ==
					AssetLicenseConstants.LIFETIME_PERPETUAL) &&
				appEntry.isSupported()) {

				if (appPricing.getStandardSupportPrice() <= 0) {
					throw new AppPricingPriceException();
				}

				if (appPricing.getDeveloperSupportPrice() <= 0) {
					throw new AppPricingPriceException();
				}
			}

			appPricingItemLocalService.validateAppPricingItems(
				appPricing.getAppPricingId());
		}

		int count = countryAppPricingPersistence.countByAppVersionId(
			appVersionId);

		if (count <= 0) {
			throw new AppPricingCountryException();
		}
	}

}