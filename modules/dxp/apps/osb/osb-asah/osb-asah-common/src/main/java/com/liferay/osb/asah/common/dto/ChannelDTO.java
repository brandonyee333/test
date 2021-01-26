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

package com.liferay.osb.asah.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import com.liferay.osb.asah.common.model.Channel;
import com.liferay.osb.asah.common.model.ChannelDataSource;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Inácio Nery
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName("channel")
public class ChannelDTO {

	public ChannelDTO(Channel channel) {
		_channelDataSourceDTOs = Stream.of(
			channel.getChannelDataSources()
		).flatMap(
			Set::stream
		).map(
			ChannelDataSourceDTO::new
		).collect(
			Collectors.toSet()
		);
		_createDate = channel.getCreateDate();
		_id = channel.getId();
		_name = channel.getName();
	}

	public ChannelDTO(
		Channel channel, Map<String, Set<DataSourceDTO>> dataSourceDTOs) {

		this(channel);

		_dataSourceDTOs = dataSourceDTOs;
	}

	public ChannelDTO(Channel channel, Set<Long> removedGroupIds) {
		_channelDTO = new ChannelDTO(channel);

		_removedGroupIds = removedGroupIds;
	}

	public ChannelDTO(List<Channel> channels) {
		_channelDTOs = Stream.of(
			channels
		).flatMap(
			List::stream
		).map(
			ChannelDTO::new
		).collect(
			Collectors.toSet()
		);
	}

	@JsonProperty("dataSources")
	public Set<ChannelDataSourceDTO> getChannelDataSourceDTOs() {
		return _channelDataSourceDTOs;
	}

	@JsonProperty("channel")
	public ChannelDTO getChannelDTO() {
		return _channelDTO;
	}

	@JsonProperty("channels")
	public Set<ChannelDTO> getChannelDTOs() {
		return _channelDTOs;
	}

	@JsonProperty("dateCreated")
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@JsonProperty("_embedded")
	public Map<String, Set<DataSourceDTO>> getDataSourceDTOs() {
		return _dataSourceDTOs;
	}

	@JsonProperty("id")
	public Long getId() {
		return _id;
	}

	@JsonProperty("name")
	public String getName() {
		return _name;
	}

	@JsonProperty("removedGroupIds")
	public Set<Long> getRemovedGroupIds() {
		return _removedGroupIds;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public static class ChannelDataSourceDTO {

		public ChannelDataSourceDTO(ChannelDataSource channelDataSource) {
			_dataSourceId = channelDataSource.getDataSourceId();
			_groupIds = channelDataSource.getGroupIds();
		}

		@JsonProperty("id")
		public Long getDataSourceId() {
			return _dataSourceId;
		}

		@JsonProperty("groupIds")
		public Set<Long> getGroupIds() {
			return _groupIds;
		}

		public void setDataSourceId(Long dataSourceId) {
			_dataSourceId = dataSourceId;
		}

		public void setGroupIds(Set<Long> groupIds) {
			_groupIds = groupIds;
		}

		private Long _dataSourceId;
		private Set<Long> _groupIds;

	}

	private Set<ChannelDataSourceDTO> _channelDataSourceDTOs;
	private ChannelDTO _channelDTO;
	private Set<ChannelDTO> _channelDTOs;
	private Date _createDate;
	private Map<String, Set<DataSourceDTO>> _dataSourceDTOs;
	private Long _id;
	private String _name;
	private Set<Long> _removedGroupIds;

}