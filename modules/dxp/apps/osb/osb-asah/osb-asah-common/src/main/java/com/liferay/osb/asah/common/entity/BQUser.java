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

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.json.JSONUtil;
import com.liferay.osb.asah.common.model.BQDXPEntity;
import com.liferay.osb.asah.common.model.ExpandoField;
import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Marcos Martins
 */
@Table
public class BQUser implements BQDXPEntity, Persistable<String> {

	public BQUser() {
	}

	public BQUser(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getAccountId() {
		return _accountId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getBirthday() {
		if (_birthday == null) {
			return null;
		}

		return new Date(_birthday.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getClassNameId() {
		return _classNameId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getClassPK() {
		return _classPK;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getContactId() {
		return _contactId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Override
	public Long getDataSourceId() {
		return _dataSourceId;
	}

	@Override
	public String getDataSourceName() {
		return _dataSourceName;
	}

	@Override
	public String getDXPEntityType() {
		return DXPEntity.Type.USER.name();
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getEmailAddress() {
		return _emailAddress;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long[] getExpandoColumnIds() {
		return Arrays.copyOf(_expandoColumnIds, _expandoColumnIds.length);
	}

	public List<ExpandoField> getExpandoFields() {
		return _expandoFields;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String[] getExpandoValueIds() {
		return Arrays.copyOf(_expandoValueIds, _expandoValueIds.length);
	}

	@JsonProperty("fields")
	@Override
	public JSONObject getFieldsJSONObject() {
		return JSONUtil.put(
			"accountId", _accountId
		).put(
			"birthday", DateUtil.toString(_birthday)
		).put(
			"classNameId", _classNameId
		).put(
			"classPK", _classPK
		).put(
			"contactId", _contactId
		).put(
			"createDate", DateUtil.toString(_createDate)
		).put(
			"emailAddress", _emailAddress
		).put(
			"expandoColumnIds", _expandoColumnIds
		).put(
			"expandoValueIds", _expandoValueIds
		).put(
			"firstName", _firstName
		).put(
			"groupIds", _groupIds
		).put(
			"jobTitle", _jobTitle
		).put(
			"languageId", _languageId
		).put(
			"lastName", _lastName
		).put(
			"male", _male
		).put(
			"middleName", _middleName
		).put(
			"modifiedDate", DateUtil.toString(_modifiedDate)
		).put(
			"organizationIds", _organizationIds
		).put(
			"parentContactId", _parentContactId
		).put(
			"prefixId", _prefixId
		).put(
			"roleIds", _roleIds
		).put(
			"screenName", _screenName
		).put(
			"suffixId", _suffixId
		).put(
			"teamIds", _teamIds
		).put(
			"timeZoneId", _timeZoneId
		).put(
			"userGroupIds", _userGroupIds
		).put(
			"userId", _userId
		).put(
			"userNam", _userName
		).put(
			"uuid", _uuid
		);
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getFirstName() {
		return _firstName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long[] getGroupIds() {
		return Arrays.copyOf(_groupIds, _groupIds.length);
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public String getId() {
		return _id;
	}

	@Override
	public String getIdFieldName() {
		return "userId";
	}

	@Override
	public Long getIdFieldValue() {
		return _userId;
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
	public Boolean getMale() {
		return _male;
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

	@Override
	public String getName() {
		return _firstName + " " + _lastName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long[] getOrganizationIds() {
		return Arrays.copyOf(_organizationIds, _organizationIds.length);
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getParentContactId() {
		return _parentContactId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getPrefixId() {
		return _prefixId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long[] getRoleIds() {
		return Arrays.copyOf(_roleIds, _roleIds.length);
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getScreenName() {
		return _screenName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getSuffixId() {
		return _suffixId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long[] getTeamIds() {
		return Arrays.copyOf(_teamIds, _teamIds.length);
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getTimeZoneId() {
		return _timeZoneId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long[] getUserGroupIds() {
		return Arrays.copyOf(_userGroupIds, _userGroupIds.length);
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getUserId() {
		return _userId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getUserName() {
		return _userName;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getUuid() {
		return _uuid;
	}

	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setAccountId(Long accountId) {
		_accountId = accountId;
	}

	public void setBirthday(Date birthday) {
		if (birthday != null) {
			_birthday = new Date(birthday.getTime());
		}
	}

	public void setClassNameId(Long classNameId) {
		_classNameId = classNameId;
	}

	public void setClassPK(Long classPK) {
		_classPK = classPK;
	}

	public void setContactId(Long contactId) {
		_contactId = contactId;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	@Override
	public void setDataSourceName(String dataSourceName) {
		_dataSourceName = dataSourceName;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setExpandoColumnIds(Long[] expandoColumnIds) {
		_expandoColumnIds = Arrays.copyOf(
			expandoColumnIds, expandoColumnIds.length);
	}

	public void setExpandoFields(List<ExpandoField> expandoFields) {
		_expandoFields = expandoFields;
	}

	public void setExpandoValueIds(String[] expandoValueIds) {
		_expandoValueIds = Arrays.copyOf(
			expandoValueIds, expandoValueIds.length);
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public void setGroupIds(Long[] groupIds) {
		_groupIds = Arrays.copyOf(groupIds, groupIds.length);
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

	public void setMale(Boolean male) {
		_male = male;
	}

	public void setMiddleName(String middleName) {
		_middleName = middleName;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setOrganizationIds(Long[] organizationIds) {
		_organizationIds = Arrays.copyOf(
			organizationIds, organizationIds.length);
	}

	public void setParentContactId(Long parentContactId) {
		_parentContactId = parentContactId;
	}

	public void setPrefixId(String prefixId) {
		_prefixId = prefixId;
	}

	public void setRoleIds(Long[] roleIds) {
		_roleIds = Arrays.copyOf(roleIds, roleIds.length);
	}

	public void setScreenName(String screenName) {
		_screenName = screenName;
	}

	public void setSuffixId(String suffixId) {
		_suffixId = suffixId;
	}

	public void setTeamIds(Long[] teamIds) {
		_teamIds = Arrays.copyOf(teamIds, teamIds.length);
	}

	public void setTimeZoneId(String timeZoneId) {
		_timeZoneId = timeZoneId;
	}

	public void setUserGroupIds(Long[] userGroupIds) {
		_userGroupIds = Arrays.copyOf(userGroupIds, userGroupIds.length);
	}

	public void setUserId(Long userId) {
		_userId = userId;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	@Transient
	private Long _accountId;

	@Transient
	private Date _birthday;

	@Transient
	private Long _classNameId;

	@Transient
	private Long _classPK;

	@Transient
	private Long _contactId;

	@Transient
	private Date _createDate;

	@Transient
	private Long _dataSourceId;

	@Transient
	private String _dataSourceName;

	@Transient
	private String _emailAddress;

	@Transient
	private Long[] _expandoColumnIds = {};

	@Transient
	private List<ExpandoField> _expandoFields;

	@Transient
	private String[] _expandoValueIds = {};

	@Transient
	private String _firstName;

	@Transient
	private Long[] _groupIds = {};

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
	private Boolean _male;

	@Transient
	private String _middleName;

	@Transient
	private Date _modifiedDate;

	@Transient
	private Long[] _organizationIds = {};

	@Transient
	private Long _parentContactId;

	@Transient
	private String _prefixId;

	@Transient
	private Long[] _roleIds = {};

	@Transient
	private String _screenName;

	@Transient
	private String _suffixId;

	@Transient
	private Long[] _teamIds = {};

	@Transient
	private String _timeZoneId;

	@Transient
	private Long[] _userGroupIds = {};

	@Transient
	private Long _userId;

	@Transient
	private String _userName;

	@Transient
	private String _uuid;

}