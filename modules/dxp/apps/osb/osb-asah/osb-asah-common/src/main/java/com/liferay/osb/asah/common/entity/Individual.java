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
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.model.Field;
import com.liferay.osb.asah.common.util.BeanUtils;
import com.liferay.osb.asah.common.util.SetUtil;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

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

	public void addBQDataSourceUser(BQDataSourceUser bqDataSourceUser) {
		if (bqDataSourceUser == null) {
			return;
		}

		if (_bqDataSourceUsers == null) {
			_bqDataSourceUsers = new HashSet<>();
		}

		_bqDataSourceUsers.add(bqDataSourceUser);

		if (_dataSourceUserPKs == null) {
			_dataSourceUserPKs = new HashSet<>();
		}

		_dataSourceUserPKs.add(new DataSourceUserPK(bqDataSourceUser));
	}

	public void addChannelId(Long channelId) {
		if (_channelIds == null) {
			_channelIds = new HashSet<>();
		}

		_channelIds.add(channelId);
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
	@JsonIgnore
	@MappedCollection(idColumn = "userid")
	public Set<BQDataSourceUser> getBQDataSourceUsers() {
		return _bqDataSourceUsers;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonIgnore
	@MappedCollection(idColumn = "identityid")
	public Set<BQIdentityChannel> getBQIdentityChannels() {
		return _bqIdentityChannels;
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

	@AccessType(AccessType.Type.PROPERTY)
	@JsonIgnore
	@MappedCollection(idColumn = "ownerid")
	public Set<Field> getCustomFields() {
		Stream<Field> stream = _customFields.stream();

		return stream.filter(
			field -> StringUtils.equals(field.getContext(), "custom")
		).collect(
			Collectors.toSet()
		);
	}

	@JsonAlias("dataSourceUserPKs")
	@JsonProperty("dataSourceIndividualPKs")
	public Set<DataSourceUserPK> getDataSourceUserPKs() {
		return _dataSourceUserPKs;
	}

	@JsonProperty("demographics")
	public Demographics getDemographics() {
		return new Demographics(_fields);
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getEmailAddressHashed() {
		return _emailAddressHashed;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonIgnore
	@MappedCollection(idColumn = "ownerid")
	public Set<Field> getFields() {
		Stream<Field> stream = _fields.stream();

		return stream.filter(
			field -> !StringUtils.equals(field.getContext(), "custom")
		).collect(
			Collectors.toSet()
		);
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

	@JsonIgnore
	public Date getLastActivityDate() {
		if (_lastActivityDate == null) {
			return null;
		}

		return new Date(_lastActivityDate.getTime());
	}

	@JsonProperty("lastActivityDates")
	public Set<ActivityDate> getLastActivityDates() {
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

	@JsonProperty("previousActivityDates")
	public Set<ActivityDate> getPreviousActivityDates() {
		return _previousActivityDates;
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

	public void setBQDataSourceUsers(Set<BQDataSourceUser> bqDataSourceUsers) {
		_bqDataSourceUsers = bqDataSourceUsers;

		_dataSourceUserPKs = SetUtil.map(
			_bqDataSourceUsers, DataSourceUserPK::new);
	}

	public void setBQIdentityChannels(
		Set<BQIdentityChannel> bqIdentityChannels) {

		_bqIdentityChannels = bqIdentityChannels;

		_activitiesCounts = SetUtil.map(
			_bqIdentityChannels, ActivitiesCount::new);
		_lastActivityDates = SetUtil.map(
			_bqIdentityChannels, ActivityDate::new);

		_previousActivityDates = SetUtil.map(
			_bqIdentityChannels,
			bqIdentityChannel -> {
				if (Objects.isNull(
						bqIdentityChannel.getPreviousActivityDate())) {

					return null;
				}

				ActivityDate previousActivityDate = new ActivityDate();

				previousActivityDate.setChannelId(
					bqIdentityChannel.getChannelId());
				previousActivityDate.setActivityDate(
					bqIdentityChannel.getPreviousActivityDate());

				return previousActivityDate;
			});

		_previousActivityDates.remove(null);
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

		if (demographics != null) {
			_customFields = demographics._fields;
		}
	}

	public void setCustomFields(Set<Field> fields) {
		_customFields = fields;

		_customDemographics = new Demographics(fields);
	}

	public void setDataSourceUserPKs(Set<DataSourceUserPK> dataSourceUserPKs) {
		_dataSourceUserPKs = dataSourceUserPKs;

		for (DataSourceUserPK dataSourceUserPK : dataSourceUserPKs) {
			Stream<BQDataSourceUser> bqDataSourceUserStream =
				_bqDataSourceUsers.stream();

			bqDataSourceUserStream.filter(
				dataSourceUser -> Objects.equals(
					dataSourceUser.getDataSourceId(),
					dataSourceUserPK.getDataSourceId())
			).forEach(
				bqDataSourceUser -> bqDataSourceUser.setUserPKs(
					dataSourceUserPK.getUserPKs())
			);
		}
	}

	public void setDemographics(Demographics demographics) {
		_demographics = demographics;
		_fields = demographics._fields;
	}

	public void setEmailAddressHashed(String emailAddressHashed) {
		_emailAddressHashed = emailAddressHashed;
	}

	public void setFields(Set<Field> fields) {
		_fields = fields;

		_demographics = new Demographics(fields);
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

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setLastActivityDate(Date lastActivityDate) {
		if (lastActivityDate != null) {
			_lastActivityDate = new Date(lastActivityDate.getTime());
		}
	}

	public void setLastActivityDates(Set<ActivityDate> lastActivityDates) {
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

	public void setPreviousActivityDates(
		Set<ActivityDate> previousActivityDates) {

		_previousActivityDates = previousActivityDates;
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

		public ActivitiesCount(BQIdentityChannel bqIdentityChannel) {
			_activitiesCount = bqIdentityChannel.getActivitiesCount();
			_channelId = bqIdentityChannel.getChannelId();
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
	public static class ActivityDate {

		public ActivityDate() {
		}

		public ActivityDate(ActivityDate activityDate) {
			_activityDate = activityDate.getActivityDate();

			_channelId = activityDate.getChannelId();
		}

		public ActivityDate(BQIdentityChannel bqIdentityChannel) {
			_activityDate = bqIdentityChannel.getLastActivityDate();
			_channelId = bqIdentityChannel.getChannelId();
		}

		public ActivityDate(Date activityDate, Long channelId) {
			_channelId = channelId;

			_activityDate = new Date(activityDate.getTime());
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof ActivityDate)) {
				return false;
			}

			ActivityDate activityDate = (ActivityDate)obj;

			if (Objects.equals(_activityDate, activityDate._activityDate) &&
				Objects.equals(_channelId, activityDate._channelId)) {

				return true;
			}

			return false;
		}

		@JsonFormat(
			pattern = DateUtil.PATTERN_ISO_8601,
			shape = JsonFormat.Shape.STRING, timezone = "UTC"
		)
		@JsonProperty("lastActivityDate")
		public Date getActivityDate() {
			if (_activityDate == null) {
				return null;
			}

			return new Date(_activityDate.getTime());
		}

		@JsonProperty("channelId")
		@JsonSerialize(using = ToStringSerializer.class)
		public Long getChannelId() {
			return _channelId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_activityDate, _channelId);
		}

		public void setActivityDate(Date activityDate) {
			if (activityDate != null) {
				_activityDate = new Date(activityDate.getTime());
			}
		}

		public void setChannelId(Long channelId) {
			_channelId = channelId;
		}

		@Transient
		private Date _activityDate;

		@Transient
		private Long _channelId;

	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class DataSourceUserPK {

		public DataSourceUserPK() {
		}

		public DataSourceUserPK(BQDataSourceUser bqDataSourceUser) {
			if (!CollectionUtils.isEmpty(bqDataSourceUser.getUserPKs())) {
				_dataSourceId = bqDataSourceUser.getDataSourceId();
				_userPKs = bqDataSourceUser.getUserPKs();
			}
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof DataSourceUserPK)) {
				return false;
			}

			DataSourceUserPK dataSourceUserPK = (DataSourceUserPK)obj;

			if (Objects.equals(_dataSourceId, dataSourceUserPK._dataSourceId) &&
				Objects.equals(_userPKs, dataSourceUserPK._userPKs)) {

				return true;
			}

			return false;
		}

		@JsonProperty("dataSourceId")
		@JsonSerialize(using = ToStringSerializer.class)
		public Long getDataSourceId() {
			return _dataSourceId;
		}

		@JsonAlias("userPKs")
		@JsonProperty("individualPKs")
		public Set<String> getUserPKs() {
			return _userPKs;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_dataSourceId, _userPKs);
		}

		public void setDataSourceId(Long dataSourceId) {
			_dataSourceId = dataSourceId;
		}

		public void setUserPKs(Set<String> userPKs) {
			_userPKs = userPKs;
		}

		@Transient
		private Long _dataSourceId;

		@Transient
		private Set<String> _userPKs = new HashSet<>();

	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class Demographics {

		public Demographics() {
		}

		public Demographics(Set<Field> fields) {
			_fields = fields;
		}

		@JsonAnySetter
		public void addtField(String key, List<Field> fields) {
			Field field = fields.get(0);

			field.setName(key);

			_fields.add(field);
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

			if (Objects.equals(_fields, demographics._fields)) {
				return true;
			}

			return false;
		}

		@JsonAnyGetter
		public Map<String, List<Field>> getField() {
			Stream<Field> stream = _fields.stream();

			return stream.collect(
				Collectors.toMap(
					Field::getName, Collections::singletonList,
					(existing, replacement) -> replacement));
		}

		@JsonIgnore
		public Set<Field> getFields() {
			return _fields;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_fields);
		}

		private Set<Field> _fields = new HashSet<>();

	}

	@Transient
	private Long _activitiesCount;

	@Transient
	private Set<ActivitiesCount> _activitiesCounts = new HashSet<>();

	@Transient
	private Set<BQDataSourceUser> _bqDataSourceUsers = new HashSet<>();

	@Transient
	private Set<BQIdentityChannel> _bqIdentityChannels = new HashSet<>();

	@Transient
	private Set<Long> _channelIds = new HashSet<>();

	@Transient
	private Date _createDate;

	@Transient
	private Demographics _customDemographics;

	@Transient
	private Set<Field> _customFields = new HashSet<>();

	@Transient
	private Set<DataSourceUserPK> _dataSourceUserPKs = new HashSet<>();

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
	private Boolean _isNew;

	@Transient
	private Date _lastActivityDate;

	@Transient
	private Set<ActivityDate> _lastActivityDates = new HashSet<>();

	@Transient
	private Date _lastEnrichmentDate;

	@Transient
	private Date _modifiedDate;

	@Transient
	private Set<Long> _organizationIds = new HashSet<>();

	@Transient
	private Set<ActivityDate> _previousActivityDates = new HashSet<>();

	@Transient
	private Set<Long> _roleIds = new HashSet<>();

	@Transient
	private Set<Long> _segmentIds = new HashSet<>();

	@Transient
	private Set<Long> _teamIds = new HashSet<>();

	@Transient
	private Set<Long> _userGroupIds = new HashSet<>();

}