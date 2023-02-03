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

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Ivica Cardic
 */
@Table
public class BQAsset {

	public BQAsset() {
	}

	public BQAsset(Map<String, Object> source) {
		BeanUtils.copyProperties(source, this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if ((o == null) || (getClass() != o.getClass())) {
			return false;
		}

		BQAsset that = (BQAsset)o;

		if ((_views == that._views) && Objects.equals(_id, that._id) &&
			Objects.equals(_assetId, that._assetId) &&
			Objects.equals(_assetTitle, that._assetTitle) &&
			Objects.equals(_assetType, that._assetType) &&
			Objects.equals(_channelId, that._channelId) &&
			Objects.equals(_dataSourceId, that._dataSourceId) &&
			Objects.equals(_modifiedDate, that._modifiedDate)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getAssetId() {
		return _assetId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getAssetTitle() {
		return _assetTitle;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getAssetType() {
		return _assetType;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getChannelId() {
		return _channelId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Long getDataSourceId() {
		return _dataSourceId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	public String getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public Date getModifiedDate() {
		return new Date(_modifiedDate.getTime());
	}

	@AccessType(AccessType.Type.PROPERTY)
	public long getViews() {
		return _views;
	}

	@Override
	public int hashCode() {
		return Objects.hash(
			_id, _assetId, _assetTitle, _assetType, _channelId, _dataSourceId,
			_modifiedDate, _views);
	}

	public void setAssetId(String assetId) {
		_assetId = assetId;
	}

	public void setAssetTitle(String assetTitle) {
		_assetTitle = assetTitle;
	}

	public void setAssetType(String assetType) {
		_assetType = assetType;
	}

	public void setChannelId(Long channelId) {
		_channelId = channelId;
	}

	public void setDataSourceId(Long dataSourceId) {
		_dataSourceId = dataSourceId;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = new Date(modifiedDate.getTime());
	}

	public void setViews(long views) {
		_views = views;
	}

	private String _assetId;
	private String _assetTitle;
	private String _assetType;
	private Long _channelId;
	private Long _dataSourceId;
	private String _id;
	private Date _modifiedDate;
	private long _views;

}