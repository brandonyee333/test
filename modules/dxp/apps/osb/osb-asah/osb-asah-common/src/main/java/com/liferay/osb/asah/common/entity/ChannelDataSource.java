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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Inácio Nery
 */
@Table
public class ChannelDataSource {

	public ChannelDataSource() {
	}

	public ChannelDataSource(Long dataSourceId, Set<Long> groupIds) {
		_dataSourceId = dataSourceId;
		_groupIds = groupIds;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ChannelDataSource)) {
			return false;
		}

		ChannelDataSource channelDataSource = (ChannelDataSource)obj;

		if (Objects.equals(_dataSourceId, channelDataSource._dataSourceId) &&
			Objects.equals(_groupIds, channelDataSource._groupIds)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonProperty("id")
	@JsonSerialize(using = ToStringSerializer.class)
	public Long getDataSourceId() {
		return _dataSourceId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@JsonSerialize(contentUsing = ToStringSerializer.class)
	public Set<Long> getGroupIds() {
		return _groupIds;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_dataSourceId, _groupIds);
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setGroupIds(Set<Long> groupIds) {
		_groupIds = groupIds;
	}

	@Transient
	private Long _dataSourceId;

	@Transient
	private Set<Long> _groupIds;

}