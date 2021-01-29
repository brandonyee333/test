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

package com.liferay.osb.asah.common.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.MappedCollection;

/**
 * @author Inácio Nery
 */
public class Channel {

	public void addChannelDataSource(ChannelDataSource channelDataSource) {
		_channelDataSources.add(channelDataSource);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Channel)) {
			return false;
		}

		Channel channel = (Channel)obj;

		if (Objects.equals(_channelDataSources, channel._channelDataSources) &&
			Objects.equals(_createDate, channel._createDate) &&
			Objects.equals(_id, channel._id) &&
			Objects.equals(_name, channel._name)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@MappedCollection(idColumn = "channelid")
	public Set<ChannelDataSource> getChannelDataSources() {
		return _channelDataSources;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getCreateDate() {
		if (_createDate == null) {
			return null;
		}

		return new Date(_createDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	public Long getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getName() {
		return _name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_channelDataSources, _createDate, _id, _name);
	}

	public void setChannelDataSources(
		Set<ChannelDataSource> channelDataSources) {

		_channelDataSources = channelDataSources;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			_createDate = new Date(createDate.getTime());
		}
	}

	public void setId(Long id) {
		_id = id;
	}

	public void setName(String name) {
		_name = name;
	}

	@Transient
	private Set<ChannelDataSource> _channelDataSources = new HashSet<>();

	@Transient
	private Date _createDate = new Date();

	@Transient
	private Long _id;

	@Transient
	private String _name;

}