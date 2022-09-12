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

import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import org.json.JSONArray;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Rachael Koestartyo
 */
@Table
public class BQIndividual implements Persistable<String> {

	public BQIndividual() {
	}

	public BQIndividual(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BQIndividual)) {
			return false;
		}

		BQIndividual bqIndividual = (BQIndividual)obj;

		if (Objects.equals(_emailAddress, bqIndividual._emailAddress)) {
			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getEmailAddress() {
		return _emailAddress;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getEmailAddressHashed() {
		return _emailAddressHashed;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Column("fields")
	public JSONArray getFieldsJSONArray() {
		return _fieldsJSONArray;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getFirstName() {
		return _firstName;
	}

	@Id
	@Override
	public String getId() {
		return _emailAddress;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getLastName() {
		return _lastName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getMiddleName() {
		return _middleName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getScreenName() {
		return _screenName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_emailAddress);
	}

	@Override
	public boolean isNew() {
		if ((_isNew != null) && _isNew) {
			return true;
		}

		return false;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setEmailAddressHashed(String emailAddressHashed) {
		_emailAddressHashed = emailAddressHashed;
	}

	public void setFieldsJSONArray(JSONArray fieldsJSONArray) {
		_fieldsJSONArray = fieldsJSONArray;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public void setMiddleName(String middleName) {
		_middleName = middleName;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setScreenName(String screenName) {
		_screenName = screenName;
	}

	@Transient
	private Date _createDate;

	@Transient
	private String _emailAddress;

	@Transient
	private String _emailAddressHashed;

	@Transient
	private JSONArray _fieldsJSONArray;

	@Transient
	private String _firstName;

	@Transient
	private Boolean _isNew;

	@Transient
	private String _lastName;

	@Transient
	private String _middleName;

	@Transient
	private Date _modifiedDate;

	@Transient
	private String _screenName;

}