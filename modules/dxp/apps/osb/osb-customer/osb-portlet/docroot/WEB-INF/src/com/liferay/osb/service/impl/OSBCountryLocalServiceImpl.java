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
import com.liferay.osb.service.base.OSBCountryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.CountryA2Exception;
import com.liferay.portal.kernel.exception.CountryA3Exception;
import com.liferay.portal.kernel.exception.CountryIddException;
import com.liferay.portal.kernel.exception.CountryNameException;
import com.liferay.portal.kernel.exception.CountryNumberException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

/**
 * @author Alan Zhang
 */
public class OSBCountryLocalServiceImpl extends OSBCountryLocalServiceBaseImpl {

	public Country addCountry(
			String name, String a2, String a3, String number, String idd,
			boolean active)
		throws PortalException, SystemException {

		validate(0, name, a2, a3, number, idd);

		long countryId = counterLocalService.increment();

		Country country = countryPersistence.create(countryId);

		country.setName(name);
		country.setA2(a2);
		country.setA3(a3);
		country.setNumber(number);
		country.setIdd(idd);
		country.setActive(active);
		
		//TODO implement serviceContext how needed
		
		ServiceContext serviceContext = new ServiceContext();

		return countryPersistence.update(country, serviceContext);
	}

	public Country deleteCountry(long countryId)
		throws PortalException, SystemException {

		return countryPersistence.remove(countryId);
	}

	public List<Country> getCountries() throws SystemException {
		return countryPersistence.findByActive(true);
	}

	public List<Country> getCountries(int start, int end)
		throws SystemException {

		return countryPersistence.findAll(start, end);
	}

	public int getCountriesCount() throws SystemException {
		return countryPersistence.countAll();
	}

	public Country getCountry(long countryId)
		throws PortalException, SystemException {

		return countryPersistence.findByPrimaryKey(countryId);
	}

	public Country updateCountry(
			long countryId, String name, String a2, String a3, String number,
			String idd, boolean active)
		throws PortalException, SystemException {

		validate(countryId, name, a2, a3, number, idd);

		Country country = countryPersistence.findByPrimaryKey(countryId);

		country.setName(name);
		country.setA2(a2);
		country.setA3(a3);
		country.setNumber(number);
		country.setIdd(idd);
		country.setActive(active);
		
		//TODO implement serviceContext how needed
		
		ServiceContext serviceContext = new ServiceContext();

		return countryPersistence.update(country, serviceContext);
	}

	protected void validate(
			long countryId, String name, String a2, String a3, String number,
			String idd)
		throws PortalException, SystemException {

		if (Validator.isNull(name)) {
			throw new CountryNameException();
		}

		if (Validator.isNull(a2)) {
			throw new CountryA2Exception();
		}

		if (Validator.isNull(a3)) {
			throw new CountryA3Exception();
		}

		if (Validator.isNull(number)) {
			throw new CountryNumberException();
		}

		if (Validator.isNull(idd)) {
			throw new CountryIddException();
		}

		Country country = countryPersistence.fetchByName(name);

		if ((country != null) && (country.getCountryId() != countryId)) {
			throw new CountryNameException();
		}

		country = countryPersistence.fetchByA2(a2);

		if ((country != null) && (country.getCountryId() != countryId)) {
			throw new CountryA2Exception();
		}

		country = countryPersistence.fetchByA3(a3);

		if ((country != null) && (country.getCountryId() != countryId)) {
			throw new CountryA3Exception();
		}
	}

}