/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.platform.portal;

/**
 * @author Ivica Cardic
 */
public class LCSProject {

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public long getAddressId() {
		return _addressId;
	}

	public boolean getArchived() {
		return _archived;
	}

	public String getContactEmailAddress() {
		return _contactEmailAddress;
	}

	public long getCorpProjectId() {
		return _corpProjectId;
	}

	public long getCreateTime() {
		return _createTime;
	}

	public long getLcsProjectId() {
		return _lcsProjectId;
	}

	public long getModifiedTime() {
		return _modifiedTime;
	}

	public String getName() {
		return _name;
	}

	public long getOrganizationId() {
		return _organizationId;
	}

	public String getPhoneNumber() {
		return _phoneNumber;
	}

	public String getSourceSystemName() {
		return _sourceSystemName;
	}

	public boolean isArchived() {
		return _archived;
	}

	public void setAccountEntryId(long accountEntryId) {
		_accountEntryId = accountEntryId;
	}

	public void setAddressId(long addressId) {
		_addressId = addressId;
	}

	public void setArchived(boolean archived) {
		_archived = archived;
	}

	public void setContactEmailAddress(String contactEmailAddress) {
		_contactEmailAddress = contactEmailAddress;
	}

	public void setCorpProjectId(long corpProjectId) {
		_corpProjectId = corpProjectId;
	}

	public void setCreateTime(long createTime) {
		_createTime = createTime;
	}

	public void setLcsProjectId(long lcsProjectId) {
		_lcsProjectId = lcsProjectId;
	}

	public void setModifiedTime(long modifiedTime) {
		_modifiedTime = modifiedTime;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setOrganizationId(long organizationId) {
		_organizationId = organizationId;
	}

	public void setPhoneNumber(String phoneNumber) {
		_phoneNumber = phoneNumber;
	}

	public void setSourceSystemName(String sourceSystemName) {
		_sourceSystemName = sourceSystemName;
	}

	private long _accountEntryId;
	private long _addressId;
	private boolean _archived;
	private String _contactEmailAddress;
	private long _corpProjectId;
	private long _createTime;
	private long _lcsProjectId;
	private long _modifiedTime;
	private String _name;
	private long _organizationId;
	private String _phoneNumber;
	private String _sourceSystemName;

}