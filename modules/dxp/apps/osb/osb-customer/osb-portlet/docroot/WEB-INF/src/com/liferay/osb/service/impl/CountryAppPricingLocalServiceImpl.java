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

import com.liferay.osb.model.AppPricing;
import com.liferay.osb.model.AppVersion;
import com.liferay.osb.model.CountryAppPricing;
import com.liferay.osb.service.base.CountryAppPricingLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Douglas Wong
 * @author Ryan Park
 */
public class CountryAppPricingLocalServiceImpl
	extends CountryAppPricingLocalServiceBaseImpl {

	public void copyCountryAppPricing(
			long sourceAppPricingId, long targetAppPricingId)
		throws PortalException, SystemException {

		AppPricing appPricing = appPricingPersistence.findByPrimaryKey(
			targetAppPricingId);

		List<CountryAppPricing> countryAppPricings =
			countryAppPricingPersistence.findByAppPricingId(sourceAppPricingId);

		for (CountryAppPricing countryAppPricing : countryAppPricings) {
			long targetCountryAppPricingId = counterLocalService.increment();

			CountryAppPricing targetCountryAppPricing =
				countryAppPricingPersistence.create(targetCountryAppPricingId);

			targetCountryAppPricing.setAppEntryId(appPricing.getAppEntryId());
			targetCountryAppPricing.setAppVersionId(
				appPricing.getAppVersionId());
			targetCountryAppPricing.setAppPricingId(targetAppPricingId);
			targetCountryAppPricing.setCountryId(
				countryAppPricing.getCountryId());
			targetCountryAppPricing.setName(countryAppPricing.getName());

			countryAppPricingPersistence.update(targetCountryAppPricing, false);
		}
	}

	public void deleteCountryAppPricingByAppPricingId(long appPricingId)
		throws SystemException {

		countryAppPricingPersistence.removeByAppPricingId(appPricingId);
	}

	public void deleteCountryAppPricingByCountryId(long countryId)
		throws SystemException {

		countryAppPricingPersistence.removeByCountryId(countryId);
	}

	public void deleteCountryAppPricings(long appVersionId, long[] countryIds)
		throws PortalException, SystemException {

		for (long countryId : countryIds) {
			CountryAppPricing countryAppPricing =
				countryAppPricingPersistence.fetchByAVI_CI(
					appVersionId, countryId);

			if (countryAppPricing != null) {
				countryAppPricingPersistence.remove(countryAppPricing);
			}
		}
	}

	public CountryAppPricing fetchCountryAppPricing(
			long appVersionId, long countryId)
		throws SystemException {

		return countryAppPricingPersistence.fetchByAVI_CI(
			appVersionId, countryId);
	}

	public List<CountryAppPricing> getAppVersionCountryAppPricings(
			long appVersionId)
		throws SystemException {

		return countryAppPricingPersistence.findByAppVersionId(appVersionId);
	}

	public List<CountryAppPricing> getCountryAppPricings(long appPricingId)
		throws SystemException {

		return countryAppPricingPersistence.findByAppPricingId(appPricingId);
	}

	public CountryAppPricing updateCountryAppPricing(
			long appVersionId, long appPricingId, long countryId)
		throws PortalException, SystemException {

		AppVersion appVersion = appVersionPersistence.findByPrimaryKey(
			appVersionId);

		appPricingPersistence.findByPrimaryKey(appPricingId);

		CountryAppPricing countryAppPricing =
			countryAppPricingPersistence.fetchByAVI_CI(appVersionId, countryId);

		if (countryAppPricing == null) {
			long countryAppPricingId = counterLocalService.increment();

			countryAppPricing = countryAppPricingPersistence.create(
				countryAppPricingId);
		}

		countryAppPricing.setAppEntryId(appVersion.getAppEntryId());
		countryAppPricing.setAppVersionId(appVersion.getAppVersionId());
		countryAppPricing.setAppPricingId(appPricingId);
		countryAppPricing.setCountryId(countryId);

		Country country = countryPersistence.findByPrimaryKey(countryId);

		countryAppPricing.setName(country.getName());

		countryAppPricingPersistence.update(countryAppPricing, false);

		return countryAppPricing;
	}

	public List<CountryAppPricing> updateCountryAppPricings(
			long appVersionId, long appPricingId, long[] countryIds)
		throws PortalException, SystemException {

		List<CountryAppPricing> countryAppPricings =
			new ArrayList<CountryAppPricing>(countryIds.length);

		for (long countryId : countryIds) {
			CountryAppPricing countryAppPricing = updateCountryAppPricing(
				appVersionId, appPricingId, countryId);

			countryAppPricings.add(countryAppPricing);
		}

		return countryAppPricings;
	}

}