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

import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Marcellus Tavares
 */
@Table
public class ItemRecommendation implements Persistable<String> {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ItemRecommendation)) {
			return false;
		}

		ItemRecommendation itemRecommendation = (ItemRecommendation)obj;

		if (Objects.equals(_id, itemRecommendation._id) &&
			Objects.equals(_itemId, itemRecommendation._itemId) &&
			Objects.equals(_jobId, itemRecommendation._jobId) &&
			Objects.equals(
				_recommendedItemIds, itemRecommendation._recommendedItemIds)) {

			return true;
		}

		return false;
	}

	@AccessType(AccessType.Type.PROPERTY)
	@Id
	@Override
	public String getId() {
		return _id;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getItemId() {
		return _itemId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public String getJobId() {
		return _jobId;
	}

	@AccessType(AccessType.Type.PROPERTY)
	public List<String> getRecommendedItemIds() {
		return _recommendedItemIds;
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id, _itemId, _jobId, _recommendedItemIds);
	}

	@Override
	public boolean isNew() {
		if ((_id == null) || ((_isNew != null) && _isNew)) {
			return true;
		}

		return false;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setIsNew(Boolean isNew) {
		_isNew = isNew;
	}

	public void setItemId(String itemId) {
		_itemId = itemId;
	}

	public void setJobId(String jobId) {
		_jobId = jobId;
	}

	public void setRecommendedItemIds(List<String> recommendedItemIds) {
		_recommendedItemIds = recommendedItemIds;
	}

	@Transient
	private String _id;

	@Transient
	private Boolean _isNew;

	@Transient
	private String _itemId;

	@Transient
	private String _jobId;

	@Transient
	private List<String> _recommendedItemIds;

}