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
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.util.CollectionUtils;

/**
 * @author Rachael Koestartyo
 */
@Table
public class Individual implements Persistable<Long> {

	public Individual() {
	}

	public Individual(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Individual)) {
			return false;
		}

		Individual individual = (Individual)obj;

		if (Objects.equals(_channelIds, individual._channelIds) &&
			Objects.equals(_createDate, individual._createDate) &&
			Objects.equals(
				_emailAddressHashed, individual._emailAddressHashed) &&
			Objects.equals(
				_firstEnrichmentDate, individual._firstEnrichmentDate) &&
			Objects.equals(_groupIds, individual._groupIds) &&
			Objects.equals(_id, individual._id) &&
			Objects.equals(
				_lastEnrichmentDate, individual._lastEnrichmentDate) &&
			Objects.equals(_modifiedDate, individual._modifiedDate) &&
			Objects.equals(_organizationIds, individual._organizationIds) &&
			Objects.equals(_roleIds, individual._roleIds) &&
			Objects.equals(_segmentIds, individual._segmentIds) &&
			Objects.equals(_teamIds, individual._teamIds) &&
			Objects.equals(_userGroupIds, individual._userGroupIds)) {

			return true;
		}

		return false;
	}

	@JsonIgnore
	public Long getActivitiesCount() {
		return _activitiesCount;
	}

	@JsonProperty("activitiesCounts")
	public Set<ActivitiesCount> getActivitiesCounts() {
		return _activitiesCounts;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Set<Long> getChannelIds() {
		return _channelIds;
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

	@JsonProperty("custom")
	public Demographics getCustomDemographics() {
		return new Demographics(_customFields);
	}

	@JsonIgnore
	@MappedCollection(idColumn = "ownerid")
	public Set<Field> getCustomFields() {
		return _customFields;
	}

	@JsonProperty("dataSourceAccountPKs")
	public Set<DataSourceAccountPK> getDataSourceAccountPKs() {
		return _dataSourceAccountPKs;
	}

	@JsonProperty("dataSourceIndividualPKs")
	public Set<DataSourceIndividualPK> getDataSourceIndividualPKs() {
		return _dataSourceIndividualPKs;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonIgnore
	@MappedCollection(idColumn = "individualid")
	public Set<DataSourceIndividual> getDataSourceIndividuals() {
		return _dataSourceIndividuals;
	}

	@JsonProperty("demographics")
	public Demographics getDemographics() {
		return new Demographics(_fields);
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getEmailAddressHashed() {
		return _emailAddressHashed;
	}

	@JsonIgnore
	@MappedCollection(idColumn = "ownerid")
	public Set<Field> getFields() {
		return _fields;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getFirstEnrichmentDate() {
		if (_firstEnrichmentDate == null) {
			return null;
		}

		return new Date(_firstEnrichmentDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Set<Long> getGroupIds() {
		return _groupIds;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	@Override
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonIgnore
	@MappedCollection(idColumn = "individualid")
	public Set<IndividualChannel> getIndividualChannels() {
		return _individualChannels;
	}

	@JsonIgnore
	public Date getLastActivityDate() {
		if (_lastActivityDate == null) {
			return null;
		}

		return new Date(_lastActivityDate.getTime());
	}

	@JsonProperty("lastActivityDates")
	public Set<LastActivityDate> getLastActivityDates() {
		return _lastActivityDates;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	public Date getLastEnrichmentDate() {
		if (_lastEnrichmentDate == null) {
			return null;
		}

		return new Date(_lastEnrichmentDate.getTime());
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

	@AccessType(AccessType.Type.PROPERTY)
	public Set<Long> getOrganizationIds() {
		return _organizationIds;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Set<Long> getRoleIds() {
		return _roleIds;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonProperty("individualSegmentIds")
	public Set<Long> getSegmentIds() {
		return _segmentIds;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Set<Long> getTeamIds() {
		return _teamIds;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Set<Long> getUserGroupIds() {
		return _userGroupIds;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_channelIds, _createDate, _emailAddressHashed, _firstEnrichmentDate,
			_groupIds, _id, _lastEnrichmentDate, _modifiedDate,
			_organizationIds, _roleIds, _segmentIds, _teamIds, _userGroupIds);
	}

	@JsonIgnore
	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setActivitiesCount(Long activitiesCount) {
		_activitiesCount = activitiesCount;
	}

	public void setActivitiesCounts(Set<ActivitiesCount> activitiesCounts) {
		_activitiesCounts = activitiesCounts;
	}

	public void setChannelIds(Set<Long> channelIds) {
		_channelIds = channelIds;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setCustomDemographics(Demographics demographics) {
		_customDemographics = demographics;
	}

	public void setCustomFields(Set<Field> fields) {
		_customFields = fields;
	}

	public void setDataSourceAccountPKs(
		Set<DataSourceAccountPK> dataSourceAccountPKs) {

		_dataSourceAccountPKs = dataSourceAccountPKs;

		for (DataSourceAccountPK dataSourceAccountPK : dataSourceAccountPKs) {
			Stream<DataSourceIndividual> dataSourceIndividualStream =
				_dataSourceIndividuals.stream();

			dataSourceIndividualStream.filter(
				dataSourceIndividual -> Objects.equals(
					dataSourceIndividual.getDataSourceId(),
					dataSourceAccountPK.getDataSourceId())
			).forEach(
				dataSourceIndividual -> dataSourceIndividual.setAccountPKs(
					dataSourceAccountPK.getAccountPKs())
			);
		}
	}

	public void setDataSourceIndividualPKs(
		Set<DataSourceIndividualPK> dataSourceIndividualPKs) {

		_dataSourceIndividualPKs = dataSourceIndividualPKs;

		for (DataSourceIndividualPK dataSourceIndividualPK :
				dataSourceIndividualPKs) {

			Stream<DataSourceIndividual> dataSourceIndividualStream =
				_dataSourceIndividuals.stream();

			dataSourceIndividualStream.filter(
				dataSourceIndividual -> Objects.equals(
					dataSourceIndividual.getDataSourceId(),
					dataSourceIndividualPK.getDataSourceId())
			).forEach(
				dataSourceIndividual -> dataSourceIndividual.setIndividualPKs(
					dataSourceIndividualPK.getIndividualPKs())
			);
		}
	}

	public void setDataSourceIndividuals(
		Set<DataSourceIndividual> dataSourceIndividuals) {

		_dataSourceIndividuals = dataSourceIndividuals;

		_dataSourceAccountPKs = SetUtil.map(
			_dataSourceIndividuals, DataSourceAccountPK::new);
		_dataSourceIndividualPKs = SetUtil.map(
			_dataSourceIndividuals, DataSourceIndividualPK::new);
	}

	public void setDemographics(Demographics demographics) {
		_demographics = demographics;
	}

	public void setEmailAddressHashed(String emailAddressHashed) {
		_emailAddressHashed = emailAddressHashed;
	}

	public void setFields(Set<Field> fields) {
		_fields = fields;
	}

	public void setFirstEnrichmentDate(Date firstEnrichmentDate) {
		if (firstEnrichmentDate != null) {
			_firstEnrichmentDate = new Date(firstEnrichmentDate.getTime());
		}
	}

	public void setGroupIds(Set<Long> groupIds) {
		_groupIds = groupIds;
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setIndividualChannels(
		Set<IndividualChannel> individualChannels) {

		_individualChannels = individualChannels;

		_activitiesCounts = SetUtil.map(
			_individualChannels, ActivitiesCount::new);
		_lastActivityDates = SetUtil.map(
			_individualChannels, LastActivityDate::new);
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setLastActivityDate(Date lastActivityDate) {
		if (lastActivityDate != null) {
			_lastActivityDate = new Date(lastActivityDate.getTime());
		}
	}

	public void setLastActivityDates(Set<LastActivityDate> lastActivityDates) {
		_lastActivityDates = lastActivityDates;
	}

	public void setLastEnrichmentDate(Date lastEnrichmentDate) {
		if (lastEnrichmentDate != null) {
			_lastEnrichmentDate = new Date(lastEnrichmentDate.getTime());
		}
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setOrganizationIds(Set<Long> organizationIds) {
		_organizationIds = organizationIds;
	}

	public void setRoleIds(Set<Long> roleIds) {
		_roleIds = roleIds;
	}

	public void setSegmentIds(Set<Long> segmentIds) {
		_segmentIds = segmentIds;
	}

	public void setTeamIds(Set<Long> teamIds) {
		_teamIds = teamIds;
	}

	public void setUserGroupIds(Set<Long> userGroupIds) {
		_userGroupIds = userGroupIds;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class ActivitiesCount {

		public ActivitiesCount() {
		}

		public ActivitiesCount(IndividualChannel individualChannel) {
			_activitiesCount = individualChannel.getActivitiesCount();
			_channelId = individualChannel.getChannelId();
		}

		public ActivitiesCount(Long activitiesCount, Long channelId) {
			_activitiesCount = activitiesCount;
			_channelId = channelId;
		}

		public ActivitiesCount(Map<String, Object> source) {
			BeanUtils.copyProperties(source, this);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof ActivitiesCount)) {
				return false;
			}

			ActivitiesCount activitiesCount = (ActivitiesCount)obj;

			if (Objects.equals(
					_activitiesCount, activitiesCount._activitiesCount) &&
				Objects.equals(_channelId, activitiesCount._channelId)) {

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
			return Objects.hash(_activitiesCount, _channelId);
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
	public static class DataSourceAccountPK {

		public DataSourceAccountPK() {
		}

		public DataSourceAccountPK(DataSourceIndividual dataSourceIndividual) {
			if (!CollectionUtils.isEmpty(
					dataSourceIndividual.getAccountPKs())) {

				_accountPKs = dataSourceIndividual.getAccountPKs();
				_dataSourceId = dataSourceIndividual.getDataSourceId();
			}
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof DataSourceAccountPK)) {
				return false;
			}

			DataSourceAccountPK dataSourceAccountPK = (DataSourceAccountPK)obj;

			if (Objects.equals(_accountPKs, dataSourceAccountPK._accountPKs) &&
				Objects.equals(
					_dataSourceId, dataSourceAccountPK._dataSourceId)) {

				return true;
			}

			return false;
		}

		@JsonProperty("accountPKs")
		public Set<String> getAccountPKs() {
			return _accountPKs;
		}

		@JsonProperty("dataSourceId")
		@JsonSerialize(using = ToStringSerializer.class)
		public Long getDataSourceId() {
			return _dataSourceId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_accountPKs, _dataSourceId);
		}

		public void setAccountPKs(Set<String> accountPKs) {
			_accountPKs = accountPKs;
		}

		public void setDataSourceId(Long dataSourceId) {
			_dataSourceId = dataSourceId;
		}

		@Transient
		private Set<String> _accountPKs = new HashSet<>();

		@Transient
		private Long _dataSourceId;

	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class DataSourceIndividualPK {

		public DataSourceIndividualPK() {
		}

		public DataSourceIndividualPK(
			DataSourceIndividual dataSourceIndividual) {

			if (!CollectionUtils.isEmpty(
					dataSourceIndividual.getIndividualPKs())) {

				_dataSourceId = dataSourceIndividual.getDataSourceId();
				_individualPKs = dataSourceIndividual.getIndividualPKs();
			}
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof DataSourceIndividualPK)) {
				return false;
			}

			DataSourceIndividualPK dataSourceIndividualPK =
				(DataSourceIndividualPK)obj;

			if (Objects.equals(
					_dataSourceId, dataSourceIndividualPK._dataSourceId) &&
				Objects.equals(
					_individualPKs, dataSourceIndividualPK._individualPKs)) {

				return true;
			}

			return false;
		}

		@JsonProperty("dataSourceId")
		@JsonSerialize(using = ToStringSerializer.class)
		public Long getDataSourceId() {
			return _dataSourceId;
		}

		@JsonProperty("individualPKs")
		public Set<String> getIndividualPKs() {
			return _individualPKs;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_dataSourceId, _individualPKs);
		}

		public void setDataSourceId(Long dataSourceId) {
			_dataSourceId = dataSourceId;
		}

		public void setIndividualPKs(Set<String> individualPKs) {
			_individualPKs = individualPKs;
		}

		@Transient
		private Long _dataSourceId;

		@Transient
		private Set<String> _individualPKs = new HashSet<>();

	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Demographics {

		public Demographics() {
		}

		public Demographics(Set<Field> fields) {
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

			if (!(obj instanceof Demographics)) {
				return false;
			}

			Demographics demographics = (Demographics)obj;

			if (Objects.equals(_fieldMap, demographics._fieldMap)) {
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

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class LastActivityDate {

		public LastActivityDate() {
		}

		public LastActivityDate(IndividualChannel individualChannel) {
			_channelId = individualChannel.getChannelId();
			_lastActivityDate = individualChannel.getLastActivityDate();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof LastActivityDate)) {
				return false;
			}

			LastActivityDate lastActivityDate = (LastActivityDate)obj;

			if (Objects.equals(_channelId, lastActivityDate._channelId) &&
				Objects.equals(
					_lastActivityDate, lastActivityDate._lastActivityDate)) {

				return true;
			}

			return false;
		}

		@JsonProperty("channelId")
		@JsonSerialize(using = ToStringSerializer.class)
		public Long getChannelId() {
			return _channelId;
		}

		@JsonFormat(
			pattern = DateUtil.PATTERN_ISO_8601,
			shape = JsonFormat.Shape.STRING, timezone = "UTC"
		)
		@JsonProperty("lastActivityDate")
		public Date getLastActivityDate() {
			if (_lastActivityDate == null) {
				return null;
			}

			return new Date(_lastActivityDate.getTime());
		}

		@Override
		public int hashCode() {
			return Objects.hash(_channelId, _lastActivityDate);
		}

		public void setChannelId(Long channelId) {
			_channelId = channelId;
		}

		public void setLastActivityDate(Date lastActivityDate) {
			if (lastActivityDate != null) {
				_lastActivityDate = new Date(lastActivityDate.getTime());
			}
		}

		@Transient
		private Long _channelId;

		@Transient
		private Date _lastActivityDate;

	}

	@Transient
	private Long _activitiesCount;

	@Transient
	private Set<ActivitiesCount> _activitiesCounts = new HashSet<>();

	@Transient
	private Set<Long> _channelIds = new HashSet<>();

	@Transient
	private Date _createDate;

	@Transient
	private Demographics _customDemographics;

	@Transient
	private Set<Field> _customFields = new HashSet<>();

	@Transient
	private Set<DataSourceAccountPK> _dataSourceAccountPKs = new HashSet<>();

	@Transient
	private Set<DataSourceIndividualPK> _dataSourceIndividualPKs =
		new HashSet<>();

	@Transient
	private Set<DataSourceIndividual> _dataSourceIndividuals = new HashSet<>();

	@Transient
	private Demographics _demographics;

	@Transient
	private String _emailAddressHashed;

	@Transient
	private Set<Field> _fields = new HashSet<>();

	@Transient
	private Date _firstEnrichmentDate;

	@Transient
	private Set<Long> _groupIds = new HashSet<>();

	@Transient
	private Long _id;

	@Transient
	private Set<IndividualChannel> _individualChannels = new HashSet<>();

	@Transient
	private Boolean _isNew;

	@Transient
	private Date _lastActivityDate;

	@Transient
	private Set<LastActivityDate> _lastActivityDates = new HashSet<>();

	@Transient
	private Date _lastEnrichmentDate;

	@Transient
	private Date _modifiedDate;

	@Transient
	private Set<Long> _organizationIds = new HashSet<>();

	@Transient
	private Set<Long> _roleIds = new HashSet<>();

	@Transient
	private Set<Long> _segmentIds = new HashSet<>();

	@Transient
	private Set<Long> _teamIds = new HashSet<>();

	@Transient
	private Set<Long> _userGroupIds = new HashSet<>();

}