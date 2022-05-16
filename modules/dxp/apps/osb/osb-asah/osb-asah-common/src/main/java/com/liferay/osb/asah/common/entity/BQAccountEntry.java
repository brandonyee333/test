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

package com.liferay.osb.asah.common.entity;

import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Marcos Martins
 */
@Table
public class BQAccountEntry implements Persistable<String> {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQAccountEntry)) {
			return false;
		}

		BQAccountEntry bqAccountEntry = (BQAccountEntry)obj;

		if (Objects.equals(_accountEntryId, bqAccountEntry._accountEntryId) &&
			Objects.equals(_createDate, bqAccountEntry._createDate) &&
			Objects.equals(
				_defaultCPaymentMethodKey,
				bqAccountEntry._defaultCPaymentMethodKey) &&
			Objects.equals(_description, bqAccountEntry._description) &&
			Objects.equals(_domains, bqAccountEntry._domains) &&
			Objects.equals(_emailAddress, bqAccountEntry._emailAddress) &&
			Objects.equals(_id, bqAccountEntry._id) &&
			Objects.equals(_logoId, bqAccountEntry._logoId) &&
			Objects.equals(_modifiedDate, bqAccountEntry._modifiedDate) &&
			Objects.equals(_name, bqAccountEntry._name) &&
			Objects.equals(
				_parentAccountEntryId, bqAccountEntry._parentAccountEntryId) &&
			Objects.equals(_status, bqAccountEntry._status) &&
			Objects.equals(
				_taxExemptionCode, bqAccountEntry._taxExemptionCode) &&
			Objects.equals(_taxIdNumber, bqAccountEntry._taxIdNumber) &&
			Objects.equals(_type, bqAccountEntry._type)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getAccountEntryId() {
		return _accountEntryId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getDefaultCPaymentMethodKey() {
		return _defaultCPaymentMethodKey;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getDescription() {
		return _description;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getDomains() {
		return _domains;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getEmailAddress() {
		return _emailAddress;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public String getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getLogoId() {
		return _logoId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getName() {
		return _name;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getParentAccountEntryId() {
		return _parentAccountEntryId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Integer getStatus() {
		return _status;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getTaxExemptionCode() {
		return _taxExemptionCode;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getTaxIdNumber() {
		return _taxIdNumber;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getType() {
		return _type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_accountEntryId, _createDate, _defaultCPaymentMethodKey,
			_description, _domains, _emailAddress, _id, _logoId, _modifiedDate,
			_name, _parentAccountEntryId, _status, _taxExemptionCode,
			_taxIdNumber, _type);
	}

	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setAccountEntryId(Long accountEntryId) {
		_accountEntryId = accountEntryId;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setDefaultCPaymentMethodKey(String defaultCPaymentMethodKey) {
		_defaultCPaymentMethodKey = defaultCPaymentMethodKey;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public void setDomains(String domains) {
		_domains = domains;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setLogoId(Long logoId) {
		_logoId = logoId;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setName(String name) {
		_name = name;
	}

	public void setParentAccountEntryId(Long parentAccountEntryId) {
		_parentAccountEntryId = parentAccountEntryId;
	}

	public void setStatus(Integer status) {
		_status = status;
	}

	public void setTaxExemptionCode(String taxExemptionCode) {
		_taxExemptionCode = taxExemptionCode;
	}

	public void setTaxIdNumber(String taxIdNumber) {
		_taxIdNumber = taxIdNumber;
	}

	public void setType(String type) {
		_type = type;
	}

	@Transient
	private Long _accountEntryId;

	@Transient
	private Date _createDate;

	@Transient
	private String _defaultCPaymentMethodKey;

	@Transient
	private String _description;

	@Transient
	private String _domains;

	@Transient
	private String _emailAddress;

	@Transient
	private String _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private Long _logoId;

	@Transient
	private Date _modifiedDate;

	@Transient
	private String _name;

	@Transient
	private Long _parentAccountEntryId;

	@Transient
	private Integer _status;

	@Transient
	private String _taxExemptionCode;

	@Transient
	private String _taxIdNumber;

	@Transient
	private String _type;

}