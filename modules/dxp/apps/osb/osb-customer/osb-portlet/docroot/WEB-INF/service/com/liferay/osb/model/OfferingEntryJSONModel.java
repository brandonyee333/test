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

package com.liferay.osb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public class OfferingEntryJSONModel extends OfferingEntryClp {

	@Override
	public AccountEntry getAccountEntry() {
		return _accountEntry;
	}

	@Override
	public Date getActualStartDate() {
		return _actualStartDate;
	}

	@Override
	public String getKey() {
		return _key;
	}

	@Override
	public List<LicenseKey> getLicenseKeys() {
		return _licenseKeys;
	}

	@Override
	public int getLicenseKeysCount() {
		return _licenseKeysCount;
	}

	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public int getTicketEntriesCount() {
		return _ticketEntriesCount;
	}

	public void setAccountEntry(AccountEntryClp accountEntry) {
		_accountEntry = accountEntry;
	}

	public void setActualStartDate(Date actualStartDate) {
		_actualStartDate = actualStartDate;
	}

	public void setKey(String key) {
		_key = key;
	}

	public void setLicenseKeys(List<LicenseKeyClp> licenseKeys) {
		_licenseKeys = new ArrayList<LicenseKey>(licenseKeys);
	}

	public void setLicenseKeysCount(int licenseKeysCount) {
		_licenseKeysCount = licenseKeysCount;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public void setTicketEntriesCount(int ticketEntriesCount) {
		_ticketEntriesCount = ticketEntriesCount;
	}

	private AccountEntry _accountEntry;
	private Date _actualStartDate;
	private String _key;
	private List<LicenseKey> _licenseKeys;
	private int _licenseKeysCount;
	private Date _startDate;
	private int _ticketEntriesCount;

}