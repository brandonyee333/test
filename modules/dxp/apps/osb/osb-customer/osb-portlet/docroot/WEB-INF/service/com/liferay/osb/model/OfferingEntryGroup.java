/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.model;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Lin Cui
 * @author Amos Fong
 */
public class OfferingEntryGroup {

	public OfferingEntryGroup() {
	}

	public void addOfferingEntry(OfferingEntry offeringEntry)
		throws PortalException {

		if (_offeringEntries.isEmpty()) {
			initCommon(offeringEntry);
		}

		_offeringEntries.add(offeringEntry);
		_offeringEntryIds.add(offeringEntry.getOfferingEntryId());

		if ((_startDate == null) ||
			_startDate.after(offeringEntry.getStartDate())) {

			_actualStartDate = offeringEntry.getActualStartDate();
			_startDate = offeringEntry.getStartDate();
		}

		int licenseKeysCount = offeringEntry.getLicenseKeysCount();

		_licenseKeysCount += licenseKeysCount;

		if (offeringEntry.isLicenses()) {
			_quantity += offeringEntry.getQuantity();

			if (licenseKeysCount < offeringEntry.getQuantity()) {
				_availableLicenseOfferingEntries.add(offeringEntry);

				if (_availableLicenseOfferingEntry == null) {
					_availableLicenseOfferingEntry = offeringEntry;
				}
			}
		}

		if (offeringEntry.isSupportTickets()) {
			_availableSupportOfferingEntries.add(offeringEntry);

			if (_availableSupportOfferingEntry == null) {
				_availableSupportOfferingEntry = offeringEntry;
			}
		}
	}

	public AccountEntry getAccountEntry() {
		return _accountEntry;
	}

	public Date getActualStartDate() {
		if (_actualStartDate == null) {
			return _startDate;
		}

		return _actualStartDate;
	}

	public List<OfferingEntry> getAvailableLicenseOfferingEntries() {
		return _availableLicenseOfferingEntries;
	}

	public OfferingEntry getAvailableLicenseOfferingEntry() {
		return _availableLicenseOfferingEntry;
	}

	public List<OfferingEntry> getAvailableSupportOfferingEntries() {
		return _availableSupportOfferingEntries;
	}

	public OfferingEntry getAvailableSupportOfferingEntry() {
		return _availableSupportOfferingEntry;
	}

	public OfferingEntry getFirstOfferingEntry() {
		return _offeringEntries.get(0);
	}

	public String getKey() {
		return _key;
	}

	public int getLicenseKeysCount() {
		return _licenseKeysCount;
	}

	public long getLicenseLifetime() {
		return _licenseLifetime;
	}

	public List<OfferingEntry> getOfferingEntries() {
		return _offeringEntries;
	}

	public Set<Long> getOfferingEntryIds() {
		return _offeringEntryIds;
	}

	public ProductEntry getProductEntry() {
		return _productEntry;
	}

	public int getQuantity() {
		return _quantity;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public int getStatus() {
		return _status;
	}

	public Date getSupportEndDate() {
		return _supportEndDate;
	}

	public long getSupportLifetime() {
		return _supportLifetime;
	}

	public SupportResponse getSupportResponse() {
		return _supportResponse;
	}

	public int getType() {
		return _type;
	}

	public long getUserId() {
		return _userId;
	}

	public String getUserName() {
		return _userName;
	}

	public int getVersion() {
		return _version;
	}

	public boolean hasAvailableServers() {
		if ((_availableLicenseOfferingEntry != null) &&
			(_licenseKeysCount < _quantity)) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasAvailableSupportTickets() {
		if (_availableSupportOfferingEntry != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isSupportTickets() {
		return _supportTickets;
	}

	protected void initCommon(OfferingEntry offeringEntry)
		throws PortalException {

		_accountEntry = offeringEntry.getAccountEntry();
		_key = offeringEntry.getKey();
		_licenseLifetime = offeringEntry.getLicenseLifetime();
		_productEntry = offeringEntry.getProductEntry();
		_status = offeringEntry.getStatus();
		_supportEndDate = offeringEntry.getSupportEndDate();
		_supportLifetime = offeringEntry.getSupportLifetime();
		_supportResponse = offeringEntry.getSupportResponse();
		_supportTickets = offeringEntry.isSupportTickets();
		_type = offeringEntry.getType();
		_userId = offeringEntry.getUserId();
		_userName = offeringEntry.getUserName();
		_version = offeringEntry.getVersion();
	}

	private AccountEntry _accountEntry;
	private Date _actualStartDate;
	private List<OfferingEntry> _availableLicenseOfferingEntries =
		new ArrayList<>();
	private OfferingEntry _availableLicenseOfferingEntry;
	private List<OfferingEntry> _availableSupportOfferingEntries =
		new ArrayList<>();
	private OfferingEntry _availableSupportOfferingEntry;
	private String _key;
	private int _licenseKeysCount;
	private long _licenseLifetime;
	private List<OfferingEntry> _offeringEntries = new ArrayList<>();
	private Set<Long> _offeringEntryIds = new HashSet<>();
	private ProductEntry _productEntry;
	private int _quantity;
	private Date _startDate;
	private int _status;
	private Date _supportEndDate;
	private long _supportLifetime;
	private SupportResponse _supportResponse;
	private boolean _supportTickets;
	private int _type;
	private long _userId;
	private String _userName;
	private int _version;

}