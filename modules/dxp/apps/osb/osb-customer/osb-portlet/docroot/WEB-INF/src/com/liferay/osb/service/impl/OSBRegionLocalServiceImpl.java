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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.osb.service.base.OSBRegionLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.RegionCodeException;
import com.liferay.portal.kernel.exception.RegionNameException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Region;

import java.util.List;

/**
 * @author Alan Zhang
 */
public class OSBRegionLocalServiceImpl extends OSBRegionLocalServiceBaseImpl {

	public Region addRegion(
			long countryId, String regionCode, String name, boolean active)
		throws PortalException, SystemException {

		validate(0, countryId, regionCode, name);

		long regionId = counterLocalService.increment();

		Region region = regionPersistence.create(regionId);

		region.setCountryId(countryId);
		region.setRegionCode(regionCode);
		region.setName(name);
		region.setActive(active);

		return regionPersistence.update(region, false);
	}

	public Region deleteRegion(long regionId)
		throws PortalException, SystemException {

		return regionPersistence.remove(regionId);
	}

	public Region fetchRegion(long countryId, String name)
		throws SystemException {

		List<Region> regions = regionPersistence.findByC_A(countryId, true);

		for (Region region : regions) {
			if (name.equals(region.getName())) {
				return region;
			}
		}

		return null;
	}

	public Region getRegion(long regionId)
		throws PortalException, SystemException {

		return regionPersistence.findByPrimaryKey(regionId);
	}

	public List<Region> getRegions(long countryId, int start, int end)
		throws SystemException {

		return regionPersistence.findByCountryId(countryId, start, end);
	}

	public int getRegionsCount(long countryId) throws SystemException {
		return regionPersistence.countByCountryId(countryId);
	}

	public Region updateRegion(
			long regionId, long countryId, String regionCode, String name,
			boolean active)
		throws PortalException, SystemException {

		validate(regionId, countryId, regionCode, name);

		Region region = regionPersistence.findByPrimaryKey(regionId);

		region.setCountryId(countryId);
		region.setRegionCode(regionCode);
		region.setName(name);
		region.setActive(active);

		return regionPersistence.update(region, false);
	}

	protected void validate(
			long regionId, long countryId, String regionCode, String name)
		throws PortalException, SystemException {

		osbCountryLocalService.getCountry(countryId);

		if (Validator.isNull(regionCode)) {
			throw new RegionCodeException();
		}

		if (Validator.isNull(name)) {
			throw new RegionNameException();
		}

		Region region = regionPersistence.fetchByC_R(countryId, regionCode);

		if ((region != null) && (region.getRegionId() != regionId)) {
			throw new RegionCodeException();
		}
	}

}