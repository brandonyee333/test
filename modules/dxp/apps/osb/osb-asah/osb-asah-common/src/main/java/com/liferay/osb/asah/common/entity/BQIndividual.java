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
	public String getAddresses() {
		return _addresses;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getBirthday() {
		if (_birthday == null) {
			return null;
		}

		return new Date(_birthday.getTime());
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
	@Column("fields")
	public JSONArray getFieldsJSONArray() {
		return _fieldsJSONArray;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getFirstName() {
		return _firstName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getGender() {
		return _gender;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public String getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getJobTitle() {
		return _jobTitle;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getLanguageId() {
		return _languageId;
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

	@AccessType(AccessType.Type.PROPERTY)
	public String getTimeZoneId() {
		return _timeZoneId;
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

	public void setAddresses(String addresses) {
		_addresses = addresses;
	}

	public void setBirthday(Date birthday) {
		if (birthday != null) {
			_birthday = new Date(birthday.getTime());
		}
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setFieldsJSONArray(JSONArray fieldsJSONArray) {
		_fieldsJSONArray = fieldsJSONArray;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public void setGender(String gender) {
		_gender = gender;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setJobTitle(String jobTitle) {
		_jobTitle = jobTitle;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;
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

	public void setTimeZoneId(String timeZoneId) {
		_timeZoneId = timeZoneId;
	}

	@Transient
	private String _addresses;

	@Transient
	private Date _birthday;

	@Transient
	private Date _createDate;

	@Transient
	private String _emailAddress;

	@Transient
	private JSONArray _fieldsJSONArray;

	@Transient
	private String _firstName;

	@Transient
	private String _gender;

	@Transient
	private String _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private String _jobTitle;

	@Transient
	private String _languageId;

	@Transient
	private String _lastName;

	@Transient
	private String _middleName;

	@Transient
	private Date _modifiedDate;

	@Transient
	private String _screenName;

	@Transient
	private String _timeZoneId;

}