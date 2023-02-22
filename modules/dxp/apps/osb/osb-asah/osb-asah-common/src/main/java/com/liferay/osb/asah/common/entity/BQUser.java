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

import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.osb.asah.common.model.BQDXPEntity;
import com.liferay.osb.asah.common.model.ExpandoField;
import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

/**
 * @author Marcos Martins
 */
public class BQUser implements BQDXPEntity {

	public BQUser() {
	}

	public BQUser(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	public Long getDataSourceId() {
		return _dataSourceId;
	}

	@Override
	public String getDataSourceName() {
		return _dataSourceName;
	}

	public String getDXPEntityType() {
		return DXPEntity.Type.USER.name();
	}

	@JsonProperty("userId")
	public Long getDXPUserId() {
		return _dxpUserId;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public String getEmailAddressHashed() {
		return _emailAddressHashed;
	}

	public List<ExpandoField> getExpandoFields() {
		return _expandoFields;
	}

	@JsonProperty("fields")
	public JSONArray getFieldsJSONArray() {
		return _fieldsJSONArray;
	}

	public String getFirstName() {
		return _firstName;
	}

	@Override
	public String getId() {
		return _id;
	}

	public String getIdFieldName() {
		return "userId";
	}

	public Long getIdFieldValue() {
		return _dxpUserId;
	}

	public String getJobTitle() {
		return _jobTitle;
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

	@Override
	public String getName() {
		return _firstName + " " + _lastName;
	}

	public String getScreenName() {
		return _screenName;
	}

	public String getUserName() {
		return _userName;
	}

	public String getUuid() {
		return _uuid;
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setDataSourceName(String dataSourceName) {
		_dataSourceName = dataSourceName;
	}

	public void setDXPUserId(Long dxpUserId) {
		_dxpUserId = dxpUserId;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setEmailAddressHashed(String emailAddressHashed) {
		_emailAddressHashed = emailAddressHashed;
	}

	public void setExpandoFields(List<ExpandoField> expandoFields) {
		_expandoFields = expandoFields;
	}

	public void setFieldsJSONArray(JSONArray fieldsJSONArray) {
		_fieldsJSONArray = fieldsJSONArray;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setJobTitle(String jobTitle) {
		_jobTitle = jobTitle;
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

	public void setUserName(String userName) {
		_userName = userName;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	private Long _dataSourceId;
	private String _dataSourceName;
	private Long _dxpUserId;
	private String _emailAddress;
	private String _emailAddressHashed;
	private List<ExpandoField> _expandoFields;
	private JSONArray _fieldsJSONArray;
	private String _firstName;
	private String _id;
	private String _jobTitle;
	private String _lastName;
	private String _middleName;
	private Date _modifiedDate;
	private String _screenName;
	private String _userName;
	private String _uuid;

}