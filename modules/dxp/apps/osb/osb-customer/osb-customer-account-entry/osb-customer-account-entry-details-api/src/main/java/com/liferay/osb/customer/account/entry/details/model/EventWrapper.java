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

package com.liferay.osb.customer.account.entry.details.model;

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
 * This class is a wrapper for {@link Event}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Event
 * @generated
 */
public class EventWrapper implements Event, ModelWrapper<Event> {

	public EventWrapper(Event event) {
		_event = event;
	}

	@Override
	public Class<?> getModelClass() {
		return Event.class;
	}

	@Override
	public String getModelClassName() {
		return Event.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("eventId", getEventId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("occurDate", getOccurDate());
		attributes.put("accountEntryId", getAccountEntryId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("type", getType());
		attributes.put("typeClassNameId", getTypeClassNameId());
		attributes.put("typeClassPK", getTypeClassPK());
		attributes.put("title", getTitle());
		attributes.put("summary", getSummary());
		attributes.put("additionalInfo", getAdditionalInfo());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long eventId = (Long)attributes.get("eventId");

		if (eventId != null) {
			setEventId(eventId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date occurDate = (Date)attributes.get("occurDate");

		if (occurDate != null) {
			setOccurDate(occurDate);
		}

		Long accountEntryId = (Long)attributes.get("accountEntryId");

		if (accountEntryId != null) {
			setAccountEntryId(accountEntryId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Long typeClassNameId = (Long)attributes.get("typeClassNameId");

		if (typeClassNameId != null) {
			setTypeClassNameId(typeClassNameId);
		}

		Long typeClassPK = (Long)attributes.get("typeClassPK");

		if (typeClassPK != null) {
			setTypeClassPK(typeClassPK);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String summary = (String)attributes.get("summary");

		if (summary != null) {
			setSummary(summary);
		}

		String additionalInfo = (String)attributes.get("additionalInfo");

		if (additionalInfo != null) {
			setAdditionalInfo(additionalInfo);
		}
	}

	@Override
	public Object clone() {
		return new EventWrapper((Event)_event.clone());
	}

	@Override
	public int compareTo(Event event) {
		return _event.compareTo(event);
	}

	/**
	 * Returns the account entry ID of this event.
	 *
	 * @return the account entry ID of this event
	 */
	@Override
	public long getAccountEntryId() {
		return _event.getAccountEntryId();
	}

	/**
	 * Returns the additional info of this event.
	 *
	 * @return the additional info of this event
	 */
	@Override
	public String getAdditionalInfo() {
		return _event.getAdditionalInfo();
	}

	/**
	 * Returns the fully qualified class name of this event.
	 *
	 * @return the fully qualified class name of this event
	 */
	@Override
	public String getClassName() {
		return _event.getClassName();
	}

	/**
	 * Returns the class name ID of this event.
	 *
	 * @return the class name ID of this event
	 */
	@Override
	public long getClassNameId() {
		return _event.getClassNameId();
	}

	/**
	 * Returns the class pk of this event.
	 *
	 * @return the class pk of this event
	 */
	@Override
	public long getClassPK() {
		return _event.getClassPK();
	}

	/**
	 * Returns the create date of this event.
	 *
	 * @return the create date of this event
	 */
	@Override
	public Date getCreateDate() {
		return _event.getCreateDate();
	}

	/**
	 * Returns the event ID of this event.
	 *
	 * @return the event ID of this event
	 */
	@Override
	public long getEventId() {
		return _event.getEventId();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _event.getExpandoBridge();
	}

	/**
	 * Returns the occur date of this event.
	 *
	 * @return the occur date of this event
	 */
	@Override
	public Date getOccurDate() {
		return _event.getOccurDate();
	}

	/**
	 * Returns the primary key of this event.
	 *
	 * @return the primary key of this event
	 */
	@Override
	public long getPrimaryKey() {
		return _event.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _event.getPrimaryKeyObj();
	}

	/**
	 * Returns the summary of this event.
	 *
	 * @return the summary of this event
	 */
	@Override
	public String getSummary() {
		return _event.getSummary();
	}

	/**
	 * Returns the title of this event.
	 *
	 * @return the title of this event
	 */
	@Override
	public String getTitle() {
		return _event.getTitle();
	}

	/**
	 * Returns the type of this event.
	 *
	 * @return the type of this event
	 */
	@Override
	public int getType() {
		return _event.getType();
	}

	/**
	 * Returns the type class name ID of this event.
	 *
	 * @return the type class name ID of this event
	 */
	@Override
	public long getTypeClassNameId() {
		return _event.getTypeClassNameId();
	}

	/**
	 * Returns the type class pk of this event.
	 *
	 * @return the type class pk of this event
	 */
	@Override
	public long getTypeClassPK() {
		return _event.getTypeClassPK();
	}

	/**
	 * Returns the user ID of this event.
	 *
	 * @return the user ID of this event
	 */
	@Override
	public long getUserId() {
		return _event.getUserId();
	}

	/**
	 * Returns the user name of this event.
	 *
	 * @return the user name of this event
	 */
	@Override
	public String getUserName() {
		return _event.getUserName();
	}

	/**
	 * Returns the user uuid of this event.
	 *
	 * @return the user uuid of this event
	 */
	@Override
	public String getUserUuid() {
		return _event.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _event.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _event.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _event.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _event.isNew();
	}

	@Override
	public void persist() {
		_event.persist();
	}

	/**
	 * Sets the account entry ID of this event.
	 *
	 * @param accountEntryId the account entry ID of this event
	 */
	@Override
	public void setAccountEntryId(long accountEntryId) {
		_event.setAccountEntryId(accountEntryId);
	}

	/**
	 * Sets the additional info of this event.
	 *
	 * @param additionalInfo the additional info of this event
	 */
	@Override
	public void setAdditionalInfo(String additionalInfo) {
		_event.setAdditionalInfo(additionalInfo);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_event.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_event.setClassName(className);
	}

	/**
	 * Sets the class name ID of this event.
	 *
	 * @param classNameId the class name ID of this event
	 */
	@Override
	public void setClassNameId(long classNameId) {
		_event.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this event.
	 *
	 * @param classPK the class pk of this event
	 */
	@Override
	public void setClassPK(long classPK) {
		_event.setClassPK(classPK);
	}

	/**
	 * Sets the create date of this event.
	 *
	 * @param createDate the create date of this event
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_event.setCreateDate(createDate);
	}

	/**
	 * Sets the event ID of this event.
	 *
	 * @param eventId the event ID of this event
	 */
	@Override
	public void setEventId(long eventId) {
		_event.setEventId(eventId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_event.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_event.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_event.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_event.setNew(n);
	}

	/**
	 * Sets the occur date of this event.
	 *
	 * @param occurDate the occur date of this event
	 */
	@Override
	public void setOccurDate(Date occurDate) {
		_event.setOccurDate(occurDate);
	}

	/**
	 * Sets the primary key of this event.
	 *
	 * @param primaryKey the primary key of this event
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_event.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_event.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the summary of this event.
	 *
	 * @param summary the summary of this event
	 */
	@Override
	public void setSummary(String summary) {
		_event.setSummary(summary);
	}

	/**
	 * Sets the title of this event.
	 *
	 * @param title the title of this event
	 */
	@Override
	public void setTitle(String title) {
		_event.setTitle(title);
	}

	/**
	 * Sets the type of this event.
	 *
	 * @param type the type of this event
	 */
	@Override
	public void setType(int type) {
		_event.setType(type);
	}

	/**
	 * Sets the type class name ID of this event.
	 *
	 * @param typeClassNameId the type class name ID of this event
	 */
	@Override
	public void setTypeClassNameId(long typeClassNameId) {
		_event.setTypeClassNameId(typeClassNameId);
	}

	/**
	 * Sets the type class pk of this event.
	 *
	 * @param typeClassPK the type class pk of this event
	 */
	@Override
	public void setTypeClassPK(long typeClassPK) {
		_event.setTypeClassPK(typeClassPK);
	}

	/**
	 * Sets the user ID of this event.
	 *
	 * @param userId the user ID of this event
	 */
	@Override
	public void setUserId(long userId) {
		_event.setUserId(userId);
	}

	/**
	 * Sets the user name of this event.
	 *
	 * @param userName the user name of this event
	 */
	@Override
	public void setUserName(String userName) {
		_event.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this event.
	 *
	 * @param userUuid the user uuid of this event
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_event.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<Event> toCacheModel() {
		return _event.toCacheModel();
	}

	@Override
	public Event toEscapedModel() {
		return new EventWrapper(_event.toEscapedModel());
	}

	@Override
	public String toString() {
		return _event.toString();
	}

	@Override
	public Event toUnescapedModel() {
		return new EventWrapper(_event.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _event.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EventWrapper)) {
			return false;
		}

		EventWrapper eventWrapper = (EventWrapper)obj;

		if (Objects.equals(_event, eventWrapper._event)) {
			return true;
		}

		return false;
	}

	@Override
	public Event getWrappedModel() {
		return _event;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _event.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _event.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_event.resetOriginalValues();
	}

	private final Event _event;

}