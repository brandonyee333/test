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

/**
 * @author Rachael Koestartyo
 */
public class BQIndividual {

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

	public String getAddresses() {
		return _addresses;
	}

	public Date getBirthday() {
		if (_birthday == null) {
			return null;
		}

		return new Date(_birthday.getTime());
	}

	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public JSONArray getFieldsJSONArray() {
		return _fieldsJSONArray;
	}

	public String getFirstName() {
		return _firstName;
	}

	public String getGender() {
		return _gender;
	}

	public String getId() {
		return _id;
	}

	public String getJobTitle() {
		return _jobTitle;
	}

	public String getLanguageId() {
		return _languageId;
	}

	public String getLastName() {
		return _lastName;
	}

	public String getMiddleName() {
		return _middleName;
	}

	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	public String getScreenName() {
		return _screenName;
	}

	public String getTimeZoneId() {
		return _timeZoneId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_emailAddress);
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

	private String _addresses;
	private Date _birthday;
	private Date _createDate;
	private String _emailAddress;
	private JSONArray _fieldsJSONArray;
	private String _firstName;
	private String _gender;
	private String _id;
	private String _jobTitle;
	private String _languageId;
	private String _lastName;
	private String _middleName;
	private Date _modifiedDate;
	private String _screenName;
	private String _timeZoneId;

}