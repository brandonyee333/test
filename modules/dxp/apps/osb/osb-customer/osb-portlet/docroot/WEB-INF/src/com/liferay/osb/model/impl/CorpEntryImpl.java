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

package com.liferay.osb.model.impl;

import com.liferay.compat.portal.kernel.util.LocalizationUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.Country;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Organization;
import com.liferay.portal.service.AddressLocalServiceUtil;
import com.liferay.portal.service.CountryServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.OrganizationLocalServiceUtil;

/**
 * @author Douglas Wong
 * @author Ryan Park
 * @author Joan Kim
 */
public class CorpEntryImpl extends CorpEntryBaseImpl {

	public CorpEntryImpl() {
	}

	public Address getAddress() throws PortalException, SystemException {
		if (_address == null) {
			_address = AddressLocalServiceUtil.getAddress(getAddressId());
		}

		return _address;
	}

	@AutoEscape
	public String getCity() {
		try {
			Address address = getAddress();

			return address.getCity();
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	public String getCountryCode() {
		try {
			Address address = getAddress();

			Country country = CountryServiceUtil.getCountry(
				address.getCountryId());

			return country.getA2();
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	public long getCountryId() {
		try {
			Address address = getAddress();

			return address.getCountryId();
		}
		catch (Exception e) {
			return 0;
		}
	}

	public String getDefaultLanguageId() {
		String[] availableLocales = LocalizationUtil.getAvailableLocales(
			getDescription());

		if (availableLocales.length > 0) {
			return LocalizationUtil.getDefaultLocale(getDescription());
		}
		else {
			return null;
		}
	}

	public Group getGroup() throws PortalException, SystemException {
		if (getOrganizationId() > 0) {
			return GroupLocalServiceUtil.getOrganizationGroup(
				OSBConstants.COMPANY_ID, getOrganizationId());
		}

		return null;
	}

	public long getGroupId() throws PortalException, SystemException {
		Group group = getGroup();

		if (group != null) {
			return group.getGroupId();
		}
		else {
			return 0;
		}
	}

	public Organization getOrganization()
		throws PortalException, SystemException {

		if (getOrganizationId() > 0) {
			return OrganizationLocalServiceUtil.getOrganization(
				getOrganizationId());
		}

		return null;
	}

	public long getRegionId() {
		try {
			Address address = getAddress();

			return address.getRegionId();
		}
		catch (Exception e) {
			return 0;
		}
	}

	@AutoEscape
	public String getStreet1() {
		try {
			Address address = getAddress();

			return address.getStreet1();
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	@AutoEscape
	public String getStreet2() {
		try {
			Address address = getAddress();

			return address.getStreet2();
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	@AutoEscape
	public String getStreet3() {
		try {
			Address address = getAddress();

			return address.getStreet3();
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	@AutoEscape
	public String getZip() {
		try {
			Address address = getAddress();

			return address.getZip();
		}
		catch (Exception e) {
			return StringPool.BLANK;
		}
	}

	private Address _address;

}