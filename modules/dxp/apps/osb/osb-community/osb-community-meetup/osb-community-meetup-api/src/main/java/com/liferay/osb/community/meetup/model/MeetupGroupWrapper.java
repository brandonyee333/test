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

package com.liferay.osb.community.meetup.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link MeetupGroup}.
 * </p>
 *
 * @author Jamie Sammons
 * @see MeetupGroup
 * @generated
 */
public class MeetupGroupWrapper
	implements MeetupGroup, ModelWrapper<MeetupGroup> {

	public MeetupGroupWrapper(MeetupGroup meetupGroup) {
		_meetupGroup = meetupGroup;
	}

	@Override
	public Class<?> getModelClass() {
		return MeetupGroup.class;
	}

	@Override
	public String getModelClassName() {
		return MeetupGroup.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("meetupGroupId", getMeetupGroupId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("city", getCity());
		attributes.put("memberCount", getMemberCount());
		attributes.put("description", getDescription());
		attributes.put("url", getUrl());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long meetupGroupId = (Long)attributes.get("meetupGroupId");

		if (meetupGroupId != null) {
			setMeetupGroupId(meetupGroupId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String city = (String)attributes.get("city");

		if (city != null) {
			setCity(city);
		}

		Integer memberCount = (Integer)attributes.get("memberCount");

		if (memberCount != null) {
			setMemberCount(memberCount);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String url = (String)attributes.get("url");

		if (url != null) {
			setUrl(url);
		}
	}

	@Override
	public Object clone() {
		return new MeetupGroupWrapper((MeetupGroup)_meetupGroup.clone());
	}

	@Override
	public int compareTo(MeetupGroup meetupGroup) {
		return _meetupGroup.compareTo(meetupGroup);
	}

	/**
	 * Returns the city of this meetup group.
	 *
	 * @return the city of this meetup group
	 */
	@Override
	public String getCity() {
		return _meetupGroup.getCity();
	}

	/**
	 * Returns the company ID of this meetup group.
	 *
	 * @return the company ID of this meetup group
	 */
	@Override
	public long getCompanyId() {
		return _meetupGroup.getCompanyId();
	}

	/**
	 * Returns the create date of this meetup group.
	 *
	 * @return the create date of this meetup group
	 */
	@Override
	public Date getCreateDate() {
		return _meetupGroup.getCreateDate();
	}

	/**
	 * Returns the description of this meetup group.
	 *
	 * @return the description of this meetup group
	 */
	@Override
	public String getDescription() {
		return _meetupGroup.getDescription();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _meetupGroup.getExpandoBridge();
	}

	/**
	 * Returns the group ID of this meetup group.
	 *
	 * @return the group ID of this meetup group
	 */
	@Override
	public long getGroupId() {
		return _meetupGroup.getGroupId();
	}

	/**
	 * Returns the meetup group ID of this meetup group.
	 *
	 * @return the meetup group ID of this meetup group
	 */
	@Override
	public long getMeetupGroupId() {
		return _meetupGroup.getMeetupGroupId();
	}

	/**
	 * Returns the member count of this meetup group.
	 *
	 * @return the member count of this meetup group
	 */
	@Override
	public int getMemberCount() {
		return _meetupGroup.getMemberCount();
	}

	/**
	 * Returns the modified date of this meetup group.
	 *
	 * @return the modified date of this meetup group
	 */
	@Override
	public Date getModifiedDate() {
		return _meetupGroup.getModifiedDate();
	}

	/**
	 * Returns the name of this meetup group.
	 *
	 * @return the name of this meetup group
	 */
	@Override
	public String getName() {
		return _meetupGroup.getName();
	}

	/**
	 * Returns the primary key of this meetup group.
	 *
	 * @return the primary key of this meetup group
	 */
	@Override
	public long getPrimaryKey() {
		return _meetupGroup.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _meetupGroup.getPrimaryKeyObj();
	}

	/**
	 * Returns the url of this meetup group.
	 *
	 * @return the url of this meetup group
	 */
	@Override
	public String getUrl() {
		return _meetupGroup.getUrl();
	}

	@Override
	public int hashCode() {
		return _meetupGroup.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _meetupGroup.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _meetupGroup.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _meetupGroup.isNew();
	}

	@Override
	public void persist() {
		_meetupGroup.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_meetupGroup.setCachedModel(cachedModel);
	}

	/**
	 * Sets the city of this meetup group.
	 *
	 * @param city the city of this meetup group
	 */
	@Override
	public void setCity(String city) {
		_meetupGroup.setCity(city);
	}

	/**
	 * Sets the company ID of this meetup group.
	 *
	 * @param companyId the company ID of this meetup group
	 */
	@Override
	public void setCompanyId(long companyId) {
		_meetupGroup.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this meetup group.
	 *
	 * @param createDate the create date of this meetup group
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_meetupGroup.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this meetup group.
	 *
	 * @param description the description of this meetup group
	 */
	@Override
	public void setDescription(String description) {
		_meetupGroup.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_meetupGroup.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_meetupGroup.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_meetupGroup.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group ID of this meetup group.
	 *
	 * @param groupId the group ID of this meetup group
	 */
	@Override
	public void setGroupId(long groupId) {
		_meetupGroup.setGroupId(groupId);
	}

	/**
	 * Sets the meetup group ID of this meetup group.
	 *
	 * @param meetupGroupId the meetup group ID of this meetup group
	 */
	@Override
	public void setMeetupGroupId(long meetupGroupId) {
		_meetupGroup.setMeetupGroupId(meetupGroupId);
	}

	/**
	 * Sets the member count of this meetup group.
	 *
	 * @param memberCount the member count of this meetup group
	 */
	@Override
	public void setMemberCount(int memberCount) {
		_meetupGroup.setMemberCount(memberCount);
	}

	/**
	 * Sets the modified date of this meetup group.
	 *
	 * @param modifiedDate the modified date of this meetup group
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_meetupGroup.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the name of this meetup group.
	 *
	 * @param name the name of this meetup group
	 */
	@Override
	public void setName(String name) {
		_meetupGroup.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_meetupGroup.setNew(n);
	}

	/**
	 * Sets the primary key of this meetup group.
	 *
	 * @param primaryKey the primary key of this meetup group
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_meetupGroup.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_meetupGroup.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the url of this meetup group.
	 *
	 * @param url the url of this meetup group
	 */
	@Override
	public void setUrl(String url) {
		_meetupGroup.setUrl(url);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<MeetupGroup>
		toCacheModel() {

		return _meetupGroup.toCacheModel();
	}

	@Override
	public MeetupGroup toEscapedModel() {
		return new MeetupGroupWrapper(_meetupGroup.toEscapedModel());
	}

	@Override
	public String toString() {
		return _meetupGroup.toString();
	}

	@Override
	public MeetupGroup toUnescapedModel() {
		return new MeetupGroupWrapper(_meetupGroup.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _meetupGroup.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MeetupGroupWrapper)) {
			return false;
		}

		MeetupGroupWrapper meetupGroupWrapper = (MeetupGroupWrapper)obj;

		if (Objects.equals(_meetupGroup, meetupGroupWrapper._meetupGroup)) {
			return true;
		}

		return false;
	}

	@Override
	public MeetupGroup getWrappedModel() {
		return _meetupGroup;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _meetupGroup.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _meetupGroup.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_meetupGroup.resetOriginalValues();
	}

	private final MeetupGroup _meetupGroup;

}