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

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.util.BeanUtils;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Rachael Koestartyo
 */
@Table
public class Account implements Persistable<Long> {

	public Account() {
	}

	public Account(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Account)) {
			return false;
		}

		Account account = (Account)obj;

		if (Objects.equals(_accountPK, account._accountPK) &&
			Objects.equals(_createDate, account._createDate) &&
			Objects.equals(_dataSourceId, account._dataSourceId) &&
			Objects.equals(_id, account._id) &&
			Objects.equals(_modifiedDate, account._modifiedDate)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getAccountPK() {
		return _accountPK;
	}

	@JsonAlias("activeIndividualsCount")
	@JsonProperty("activeIndividualCount")
	public Long getActiveIndividualsCount() {
		return _activeIndividualsCount;
	}

	@JsonProperty("activitiesCount")
	public Long getActivitiesCount() {
		return _activitiesCount;
	}

	@JsonProperty("activitiesCounts")
	public Set<AccountActivityCount> getActivitiesCounts() {
		return _accountActivitiesCounts;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonAlias("createDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateCreated")
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getDataSourceId() {
		return _dataSourceId;
	}

	@JsonIgnore
	@MappedCollection(idColumn = "ownerid")
	public Set<Field> getFields() {
		return _fields;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	@Override
	public Long getId() {
		return _id;
	}

	@JsonProperty("individualCount")
	public Long getIndividualCount() {
		return _individualCount;
	}

	@JsonProperty("individualCounts")
	public Set<AccountIndividualCount> getIndividualCounts() {
		return _accountIndividualCounts;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonAlias("modifiedDate")
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("dateModified")
	public Date getModifiedDate() {
		if (_modifiedDate == null) {
			return null;
		}

		return new Date(_modifiedDate.getTime());
	}

	@JsonProperty("organization")
	public Organization getOrganization() {
		return new Organization(_fields);
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_accountPK, _createDate, _dataSourceId, _id, _modifiedDate);
	}

	@JsonIgnore
	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setAccountPK(String accountPK) {
		_accountPK = accountPK;
	}

	public void setActiveIndividualsCount(Long activeIndividualsCount) {
		_activeIndividualsCount = activeIndividualsCount;
	}

	public void setActivitiesCount(Long activitiesCount) {
		_activitiesCount = activitiesCount;
	}

	public void setActivitiesCounts(
		Set<AccountActivityCount> accountActivitiesCounts) {

		_accountActivitiesCounts = accountActivitiesCounts;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setFields(Set<Field> fields) {
		_fields = fields;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIndividualCount(Long individualCount) {
		_individualCount = individualCount;
	}

	public void setIndividualCounts(
		Set<AccountIndividualCount> accountIndividualCounts) {

		_accountIndividualCounts = accountIndividualCounts;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class AccountActivityCount {

		public AccountActivityCount() {
		}

		public AccountActivityCount(Long activitiesCount, Long channelId) {
			_activitiesCount = activitiesCount;
			_channelId = channelId;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof AccountActivityCount)) {
				return false;
			}

			AccountActivityCount accountActivityCount =
				(AccountActivityCount)obj;

			if (Objects.equals(
					_activitiesCount, accountActivityCount._activitiesCount) &&
				Objects.equals(_channelId, accountActivityCount._channelId)) {

				return true;
			}

			return false;
		}

		@JsonProperty("activitiesCount")
		public Long getActivitiesCount() {
			return _activitiesCount;
		}

		@JsonProperty("channelId")
		@JsonSerialize(using = ToStringSerializer.class)
		public Long getChannelId() {
			return _channelId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_channelId, _activitiesCount);
		}

		public void setActivitiesCount(Long activitiesCount) {
			_activitiesCount = activitiesCount;
		}

		public void setChannelId(Long channelId) {
			_channelId = channelId;
		}

		@Transient
		private Long _activitiesCount;

		@Transient
		private Long _channelId;

	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class AccountIndividualCount {

		public AccountIndividualCount() {
		}

		public AccountIndividualCount(Long channelId, Long individualCount) {
			_channelId = channelId;
			_individualCount = individualCount;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof AccountIndividualCount)) {
				return false;
			}

			AccountIndividualCount accountIndividualCount =
				(AccountIndividualCount)obj;

			if (Objects.equals(_channelId, accountIndividualCount._channelId) &&
				Objects.equals(
					_individualCount,
					accountIndividualCount._individualCount)) {

				return true;
			}

			return false;
		}

		@JsonProperty("channelId")
		@JsonSerialize(using = ToStringSerializer.class)
		public Long getChannelId() {
			return _channelId;
		}

		@JsonProperty("individualCount")
		public Long getIndividualCount() {
			return _individualCount;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_channelId, _individualCount);
		}

		public void setChannelId(Long channelId) {
			_channelId = channelId;
		}

		public void setIndividualCount(Long individualCount) {
			_individualCount = individualCount;
		}

		@Transient
		private Long _channelId;

		@Transient
		private Long _individualCount;

	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Organization {

		public Organization() {
		}

		public Organization(Set<Field> fields) {
			for (Field field : fields) {
				_fieldMap.put(
					field.getName(), Collections.singletonList(field));
			}
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof Organization)) {
				return false;
			}

			Organization organization = (Organization)obj;

			if (Objects.equals(_fieldMap, organization._fieldMap)) {
				return true;
			}

			return false;
		}

		@JsonAnyGetter
		public Map<String, Object> getField() {
			return _fieldMap;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_fieldMap);
		}

		public void setFieldMap(Map<String, Object> fieldMap) {
			_fieldMap = fieldMap;
		}

		private Map<String, Object> _fieldMap = new HashMap<>();

	}

	@Transient
	private Set<AccountActivityCount> _accountActivitiesCounts =
		new HashSet<>();

	@Transient
	private Set<AccountIndividualCount> _accountIndividualCounts =
		new HashSet<>();

	@Transient
	private String _accountPK;

	@Transient
	private Long _activeIndividualsCount;

	@Transient
	private Long _activitiesCount;

	@Transient
	private Date _createDate = new Date();

	@Transient
	private Long _dataSourceId;

	@Transient
	private Set<Field> _fields = new HashSet<>();

	@Transient
	private Long _id;

	@Transient
	private Long _individualCount;

	@Transient
	private Boolean _isNew;

	@Transient
	private Date _modifiedDate = new Date();

}