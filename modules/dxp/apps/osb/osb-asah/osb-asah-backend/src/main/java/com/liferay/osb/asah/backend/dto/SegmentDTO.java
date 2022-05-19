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

package com.liferay.osb.asah.backend.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.liferay.osb.asah.common.date.DateUtil;
import com.liferay.osb.asah.common.entity.BQMembershipChange;
import com.liferay.osb.asah.common.entity.Segment;
import com.liferay.osb.asah.common.util.SetUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Inácio Nery
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("segments")
public class SegmentDTO {

	public SegmentDTO() {
	}

	public SegmentDTO(BQMembershipChange bqMembershipChange, Segment segment) {
		AuthorDTO authorDTO = new AuthorDTO(segment);

		if (!authorDTO.isEmpty()) {
			_authorDTO = authorDTO;
		}

		_activeIndividualsCount = 0L;
		_activitiesCount = 0L;

		if (bqMembershipChange != null) {
			_individualsCount = bqMembershipChange.getIndividualsCount();
			_knownIndividualsCount =
				bqMembershipChange.getKnownIndividualsCount();

			_anonymousIndividualsCount =
				_individualsCount - _knownIndividualsCount;
		}
		else {
			_anonymousIndividualsCount = 0L;
			_individualsCount = 0L;
			_knownIndividualsCount = 0L;
		}

		_channelId = StringUtil.get(segment.getChannelId(), null);
		_createDate = segment.getCreateDate();
		_filter = segment.getFilter();
		_filterMetadata = segment.getFilterMetadata();
		_id = StringUtil.get(segment.getId(), null);
		_includeAnonymousUsers = segment.getIncludeAnonymousUsers();
		_lastActivityDate = segment.getLastActivityDate();
		_modifiedDate = segment.getModifiedDate();
		_name = segment.getName();
		_referencedAssetDataSourceIds = SetUtil.map(
			segment.getReferencedAssetDataSourceIds(), String::valueOf);
		_referencedAssetIds = SetUtil.map(
			segment.getReferencedAssetIds(), String::valueOf);
		_referencedFieldMappingIds = SetUtil.map(
			segment.getReferencedFieldMappingIds(), String::valueOf);
		_referencedGroupIds = SetUtil.map(
			segment.getReferencedGroupIds(), String::valueOf);
		_referencedOrganizationIds = SetUtil.map(
			segment.getReferencedOrganizationIds(), String::valueOf);
		_referencedRoleIds = SetUtil.map(
			segment.getReferencedRoleIds(), String::valueOf);
		_referencedTeamIds = SetUtil.map(
			segment.getReferencedTeamIds(), String::valueOf);
		_referencedUserGroupIds = SetUtil.map(
			segment.getReferencedUserGroupIds(), String::valueOf);
		_referencedUserIds = SetUtil.map(
			segment.getReferencedUserIds(), String::valueOf);
		_scope = segment.getScope();
		_state = segment.getState();
		_status = segment.getStatus();
		_type = StringUtil.get(segment.getType(), null);
	}

	public SegmentDTO(
		Map<Long, BQMembershipChange> bqMembershipChangeMap,
		List<Segment> segments) {

		_segmentDTOs = SetUtil.map(
			segments,
			segment -> new SegmentDTO(
				bqMembershipChangeMap.getOrDefault(segment.getId(), null),
				segment));
	}

	public SegmentDTO(Set<SegmentDTO> segmentDTOs) {
		_segmentDTOs = segmentDTOs;
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

	@JsonAlias("anonymousIndividualsCount")
	@JsonProperty("anonymousIndividualCount")
	public Long getAnonymousIndividualsCount() {
		return _anonymousIndividualsCount;
	}

	@JsonProperty("author")
	public AuthorDTO getAuthorDTO() {
		return _authorDTO;
	}

	@JsonProperty("channelId")
	public String getChannelId() {
		return _channelId;
	}

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

	@JsonProperty("_embedded")
	public Map<String, Object> getEmbedded() {
		return _embedded;
	}

	@JsonProperty("filter")
	public String getFilter() {
		return _filter;
	}

	@JsonProperty("filterMetadata")
	public String getFilterMetadata() {
		return _filterMetadata;
	}

	@JsonProperty("id")
	public String getId() {
		return _id;
	}

	@JsonProperty("includeAnonymousUsers")
	public Boolean getIncludeAnonymousUsers() {
		return _includeAnonymousUsers;
	}

	@JsonAlias("individualsCount")
	@JsonProperty("individualCount")
	public Long getIndividualsCount() {
		return _individualsCount;
	}

	@JsonAlias("knownIndividualsCount")
	@JsonProperty("knownIndividualCount")
	public Long getKnownIndividualsCount() {
		return _knownIndividualsCount;
	}

	@JsonFormat(
		pattern = DateUtil.PATTERN_ISO_8601, shape = JsonFormat.Shape.STRING,
		timezone = "UTC"
	)
	@JsonProperty("lastActivityDate")
	public Date getLastActivityDate() {
		if (_lastActivityDate == null) {
			return null;
		}

		return new Date(_lastActivityDate.getTime());
	}

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

	@JsonProperty("name")
	public String getName() {
		return _name;
	}

	@JsonProperty("referencedAssetDataSourceIds")
	public Set<String> getReferencedAssetDataSourceIds() {
		return _referencedAssetDataSourceIds;
	}

	@JsonProperty("referencedAssetIds")
	public Set<String> getReferencedAssetIds() {
		return _referencedAssetIds;
	}

	@JsonProperty("referencedFieldMappingIds")
	public Set<String> getReferencedFieldMappingIds() {
		return _referencedFieldMappingIds;
	}

	@JsonProperty("referencedGroupIds")
	public Set<String> getReferencedGroupIds() {
		return _referencedGroupIds;
	}

	@JsonProperty("referencedOrganizationIds")
	public Set<String> getReferencedOrganizationIds() {
		return _referencedOrganizationIds;
	}

	@JsonProperty("referencedRoleIds")
	public Set<String> getReferencedRoleIds() {
		return _referencedRoleIds;
	}

	@JsonProperty("referencedTeamIds")
	public Set<String> getReferencedTeamIds() {
		return _referencedTeamIds;
	}

	@JsonProperty("referencedUserGroupIds")
	public Set<String> getReferencedUserGroupIds() {
		return _referencedUserGroupIds;
	}

	@JsonProperty("referencedUserIds")
	public Set<String> getReferencedUserIds() {
		return _referencedUserIds;
	}

	@JsonProperty("scope")
	public String getScope() {
		return _scope;
	}

	@JsonProperty("individual-segments")
	public Set<SegmentDTO> getSegmentDTOs() {
		return _segmentDTOs;
	}

	@JsonProperty("state")
	public String getState() {
		return _state;
	}

	@JsonProperty("status")
	public String getStatus() {
		return _status;
	}

	@JsonAlias("type")
	@JsonProperty("segmentType")
	public String getType() {
		return _type;
	}

	public void setActiveIndividualsCount(Long activeIndividualsCount) {
		_activeIndividualsCount = activeIndividualsCount;
	}

	public void setActivitiesCount(Long activitiesCount) {
		_activitiesCount = activitiesCount;
	}

	public void setAnonymousIndividualsCount(Long anonymousIndividualsCount) {
		_anonymousIndividualsCount = anonymousIndividualsCount;
	}

	public void setAuthorDTO(AuthorDTO authorDTO) {
		_authorDTO = authorDTO;
	}

	public void setChannelId(String channelId) {
		_channelId = channelId;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setEmbedded(Map<String, Object> embedded) {
		_embedded = embedded;
	}

	public void setFilter(String filter) {
		_filter = filter;
	}

	public void setFilterMetadata(String filterMetadata) {
		_filterMetadata = filterMetadata;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIncludeAnonymousUsers(Boolean includeAnonymousUsers) {
		_includeAnonymousUsers = includeAnonymousUsers;
	}

	public void setIndividualsCount(Long individualsCount) {
		_individualsCount = individualsCount;
	}

	public void setKnownIndividualsCount(Long knownIndividualsCount) {
		_knownIndividualsCount = knownIndividualsCount;
	}

	public void setLastActivityDate(Date lastActivityDate) {
		if (lastActivityDate != null) {
			_lastActivityDate = new Date(lastActivityDate.getTime());
		}
	}

	public void setModifiedDate(Date modifiedDate) {
		if (modifiedDate != null) {
			_modifiedDate = new Date(modifiedDate.getTime());
		}
	}

	public void setName(String name) {
		_name = name;
	}

	public void setReferencedAssetDataSourceIds(
		Set<String> referencedAssetDataSourceIds) {

		_referencedAssetDataSourceIds = referencedAssetDataSourceIds;
	}

	public void setReferencedAssetIds(Set<String> referencedAssetIds) {
		_referencedAssetIds = referencedAssetIds;
	}

	public void setReferencedFieldMappingIds(
		Set<String> referencedFieldMappingIds) {

		_referencedFieldMappingIds = referencedFieldMappingIds;
	}

	public void setReferencedGroupIds(Set<String> referencedGroupIds) {
		_referencedGroupIds = referencedGroupIds;
	}

	public void setReferencedOrganizationIds(
		Set<String> referencedOrganizationIds) {

		_referencedOrganizationIds = referencedOrganizationIds;
	}

	public void setReferencedRoleIds(Set<String> referencedRoleIds) {
		_referencedRoleIds = referencedRoleIds;
	}

	public void setReferencedTeamIds(Set<String> referencedTeamIds) {
		_referencedTeamIds = referencedTeamIds;
	}

	public void setReferencedUserGroupIds(Set<String> referencedUserGroupIds) {
		_referencedUserGroupIds = referencedUserGroupIds;
	}

	public void setReferencedUserIds(Set<String> referencedUserIds) {
		_referencedUserIds = referencedUserIds;
	}

	public void setScope(String scope) {
		_scope = scope;
	}

	public void setState(String state) {
		_state = state;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public void setType(String type) {
		_type = type;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class AuthorDTO {

		public AuthorDTO() {
		}

		public AuthorDTO(Segment segment) {
			_id = StringUtil.get(segment.getAuthorId(), null);
			_name = segment.getAuthorName();
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}

			if (!(obj instanceof AuthorDTO)) {
				return false;
			}

			AuthorDTO authorDTO = (AuthorDTO)obj;

			if (Objects.equals(_id, authorDTO._id) &&
				Objects.equals(_name, authorDTO._name)) {

				return true;
			}

			return false;
		}

		@JsonProperty("id")
		public String getId() {
			return _id;
		}

		@JsonProperty("name")
		public String getName() {
			return _name;
		}

		@Override
		public int hashCode() {
			return Objects.hash(_id, _name);
		}

		@JsonIgnore
		public boolean isEmpty() {
			return equals(new AuthorDTO());
		}

		public void setId(String id) {
			_id = id;
		}

		public void setName(String name) {
			_name = name;
		}

		private String _id;
		private String _name;

	}

	private Long _activeIndividualsCount;
	private Long _activitiesCount;
	private Long _anonymousIndividualsCount;
	private AuthorDTO _authorDTO;
	private String _channelId;
	private Date _createDate;
	private Map<String, Object> _embedded;
	private String _filter;
	private String _filterMetadata;
	private String _id;
	private Boolean _includeAnonymousUsers;
	private Long _individualsCount;
	private Long _knownIndividualsCount;
	private Date _lastActivityDate;
	private Date _modifiedDate;
	private String _name;
	private Set<String> _referencedAssetDataSourceIds;
	private Set<String> _referencedAssetIds;
	private Set<String> _referencedFieldMappingIds;
	private Set<String> _referencedGroupIds;
	private Set<String> _referencedOrganizationIds;
	private Set<String> _referencedRoleIds;
	private Set<String> _referencedTeamIds;
	private Set<String> _referencedUserGroupIds;
	private Set<String> _referencedUserIds;
	private String _scope;
	private Set<SegmentDTO> _segmentDTOs;
	private String _state;
	private String _status;
	private String _type;

}