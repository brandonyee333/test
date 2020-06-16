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

package com.liferay.osb.loop.model;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link LoopUserNotificationEvent}.
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopUserNotificationEvent
 * @generated
 */
public class LoopUserNotificationEventWrapper
	implements LoopUserNotificationEvent,
			   ModelWrapper<LoopUserNotificationEvent> {

	public LoopUserNotificationEventWrapper(
		LoopUserNotificationEvent loopUserNotificationEvent) {

		_loopUserNotificationEvent = loopUserNotificationEvent;
	}

	@Override
	public Class<?> getModelClass() {
		return LoopUserNotificationEvent.class;
	}

	@Override
	public String getModelClassName() {
		return LoopUserNotificationEvent.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put(
			"loopUserNotificationEventId", getLoopUserNotificationEventId());
		attributes.put("createTime", getCreateTime());
		attributes.put("recipientUserId", getRecipientUserId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("groupClassNameId", getGroupClassNameId());
		attributes.put("groupClassPK", getGroupClassPK());
		attributes.put("groupKey", getGroupKey());
		attributes.put("type", getType());
		attributes.put("received", isReceived());
		attributes.put("opened", isOpened());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long loopUserNotificationEventId = (Long)attributes.get(
			"loopUserNotificationEventId");

		if (loopUserNotificationEventId != null) {
			setLoopUserNotificationEventId(loopUserNotificationEventId);
		}

		Long createTime = (Long)attributes.get("createTime");

		if (createTime != null) {
			setCreateTime(createTime);
		}

		Long recipientUserId = (Long)attributes.get("recipientUserId");

		if (recipientUserId != null) {
			setRecipientUserId(recipientUserId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long groupClassNameId = (Long)attributes.get("groupClassNameId");

		if (groupClassNameId != null) {
			setGroupClassNameId(groupClassNameId);
		}

		Long groupClassPK = (Long)attributes.get("groupClassPK");

		if (groupClassPK != null) {
			setGroupClassPK(groupClassPK);
		}

		Long groupKey = (Long)attributes.get("groupKey");

		if (groupKey != null) {
			setGroupKey(groupKey);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Boolean received = (Boolean)attributes.get("received");

		if (received != null) {
			setReceived(received);
		}

		Boolean opened = (Boolean)attributes.get("opened");

		if (opened != null) {
			setOpened(opened);
		}
	}

	@Override
	public Object clone() {
		return new LoopUserNotificationEventWrapper(
			(LoopUserNotificationEvent)_loopUserNotificationEvent.clone());
	}

	@Override
	public int compareTo(LoopUserNotificationEvent loopUserNotificationEvent) {
		return _loopUserNotificationEvent.compareTo(loopUserNotificationEvent);
	}

	/**
	 * Returns the fully qualified class name of this loop user notification event.
	 *
	 * @return the fully qualified class name of this loop user notification event
	 */
	@Override
	public String getClassName() {
		return _loopUserNotificationEvent.getClassName();
	}

	/**
	 * Returns the class name ID of this loop user notification event.
	 *
	 * @return the class name ID of this loop user notification event
	 */
	@Override
	public long getClassNameId() {
		return _loopUserNotificationEvent.getClassNameId();
	}

	/**
	 * Returns the class pk of this loop user notification event.
	 *
	 * @return the class pk of this loop user notification event
	 */
	@Override
	public long getClassPK() {
		return _loopUserNotificationEvent.getClassPK();
	}

	/**
	 * Returns the create time of this loop user notification event.
	 *
	 * @return the create time of this loop user notification event
	 */
	@Override
	public long getCreateTime() {
		return _loopUserNotificationEvent.getCreateTime();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _loopUserNotificationEvent.getExpandoBridge();
	}

	/**
	 * Returns the group class name ID of this loop user notification event.
	 *
	 * @return the group class name ID of this loop user notification event
	 */
	@Override
	public long getGroupClassNameId() {
		return _loopUserNotificationEvent.getGroupClassNameId();
	}

	/**
	 * Returns the group class pk of this loop user notification event.
	 *
	 * @return the group class pk of this loop user notification event
	 */
	@Override
	public long getGroupClassPK() {
		return _loopUserNotificationEvent.getGroupClassPK();
	}

	/**
	 * Returns the group key of this loop user notification event.
	 *
	 * @return the group key of this loop user notification event
	 */
	@Override
	public long getGroupKey() {
		return _loopUserNotificationEvent.getGroupKey();
	}

	/**
	 * Returns the loop user notification event ID of this loop user notification event.
	 *
	 * @return the loop user notification event ID of this loop user notification event
	 */
	@Override
	public long getLoopUserNotificationEventId() {
		return _loopUserNotificationEvent.getLoopUserNotificationEventId();
	}

	/**
	 * Returns the opened of this loop user notification event.
	 *
	 * @return the opened of this loop user notification event
	 */
	@Override
	public boolean getOpened() {
		return _loopUserNotificationEvent.getOpened();
	}

	/**
	 * Returns the primary key of this loop user notification event.
	 *
	 * @return the primary key of this loop user notification event
	 */
	@Override
	public long getPrimaryKey() {
		return _loopUserNotificationEvent.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _loopUserNotificationEvent.getPrimaryKeyObj();
	}

	/**
	 * Returns the received of this loop user notification event.
	 *
	 * @return the received of this loop user notification event
	 */
	@Override
	public boolean getReceived() {
		return _loopUserNotificationEvent.getReceived();
	}

	/**
	 * Returns the recipient user ID of this loop user notification event.
	 *
	 * @return the recipient user ID of this loop user notification event
	 */
	@Override
	public long getRecipientUserId() {
		return _loopUserNotificationEvent.getRecipientUserId();
	}

	/**
	 * Returns the recipient user uuid of this loop user notification event.
	 *
	 * @return the recipient user uuid of this loop user notification event
	 */
	@Override
	public String getRecipientUserUuid() {
		return _loopUserNotificationEvent.getRecipientUserUuid();
	}

	/**
	 * Returns the type of this loop user notification event.
	 *
	 * @return the type of this loop user notification event
	 */
	@Override
	public int getType() {
		return _loopUserNotificationEvent.getType();
	}

	@Override
	public int hashCode() {
		return _loopUserNotificationEvent.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _loopUserNotificationEvent.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _loopUserNotificationEvent.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _loopUserNotificationEvent.isNew();
	}

	/**
	 * Returns <code>true</code> if this loop user notification event is opened.
	 *
	 * @return <code>true</code> if this loop user notification event is opened; <code>false</code> otherwise
	 */
	@Override
	public boolean isOpened() {
		return _loopUserNotificationEvent.isOpened();
	}

	/**
	 * Returns <code>true</code> if this loop user notification event is received.
	 *
	 * @return <code>true</code> if this loop user notification event is received; <code>false</code> otherwise
	 */
	@Override
	public boolean isReceived() {
		return _loopUserNotificationEvent.isReceived();
	}

	@Override
	public void persist() {
		_loopUserNotificationEvent.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_loopUserNotificationEvent.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_loopUserNotificationEvent.setClassName(className);
	}

	/**
	 * Sets the class name ID of this loop user notification event.
	 *
	 * @param classNameId the class name ID of this loop user notification event
	 */
	@Override
	public void setClassNameId(long classNameId) {
		_loopUserNotificationEvent.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this loop user notification event.
	 *
	 * @param classPK the class pk of this loop user notification event
	 */
	@Override
	public void setClassPK(long classPK) {
		_loopUserNotificationEvent.setClassPK(classPK);
	}

	/**
	 * Sets the create time of this loop user notification event.
	 *
	 * @param createTime the create time of this loop user notification event
	 */
	@Override
	public void setCreateTime(long createTime) {
		_loopUserNotificationEvent.setCreateTime(createTime);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_loopUserNotificationEvent.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_loopUserNotificationEvent.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_loopUserNotificationEvent.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the group class name ID of this loop user notification event.
	 *
	 * @param groupClassNameId the group class name ID of this loop user notification event
	 */
	@Override
	public void setGroupClassNameId(long groupClassNameId) {
		_loopUserNotificationEvent.setGroupClassNameId(groupClassNameId);
	}

	/**
	 * Sets the group class pk of this loop user notification event.
	 *
	 * @param groupClassPK the group class pk of this loop user notification event
	 */
	@Override
	public void setGroupClassPK(long groupClassPK) {
		_loopUserNotificationEvent.setGroupClassPK(groupClassPK);
	}

	/**
	 * Sets the group key of this loop user notification event.
	 *
	 * @param groupKey the group key of this loop user notification event
	 */
	@Override
	public void setGroupKey(long groupKey) {
		_loopUserNotificationEvent.setGroupKey(groupKey);
	}

	/**
	 * Sets the loop user notification event ID of this loop user notification event.
	 *
	 * @param loopUserNotificationEventId the loop user notification event ID of this loop user notification event
	 */
	@Override
	public void setLoopUserNotificationEventId(
		long loopUserNotificationEventId) {

		_loopUserNotificationEvent.setLoopUserNotificationEventId(
			loopUserNotificationEventId);
	}

	@Override
	public void setNew(boolean n) {
		_loopUserNotificationEvent.setNew(n);
	}

	/**
	 * Sets whether this loop user notification event is opened.
	 *
	 * @param opened the opened of this loop user notification event
	 */
	@Override
	public void setOpened(boolean opened) {
		_loopUserNotificationEvent.setOpened(opened);
	}

	/**
	 * Sets the primary key of this loop user notification event.
	 *
	 * @param primaryKey the primary key of this loop user notification event
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_loopUserNotificationEvent.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_loopUserNotificationEvent.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets whether this loop user notification event is received.
	 *
	 * @param received the received of this loop user notification event
	 */
	@Override
	public void setReceived(boolean received) {
		_loopUserNotificationEvent.setReceived(received);
	}

	/**
	 * Sets the recipient user ID of this loop user notification event.
	 *
	 * @param recipientUserId the recipient user ID of this loop user notification event
	 */
	@Override
	public void setRecipientUserId(long recipientUserId) {
		_loopUserNotificationEvent.setRecipientUserId(recipientUserId);
	}

	/**
	 * Sets the recipient user uuid of this loop user notification event.
	 *
	 * @param recipientUserUuid the recipient user uuid of this loop user notification event
	 */
	@Override
	public void setRecipientUserUuid(String recipientUserUuid) {
		_loopUserNotificationEvent.setRecipientUserUuid(recipientUserUuid);
	}

	/**
	 * Sets the type of this loop user notification event.
	 *
	 * @param type the type of this loop user notification event
	 */
	@Override
	public void setType(int type) {
		_loopUserNotificationEvent.setType(type);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LoopUserNotificationEvent>
		toCacheModel() {

		return _loopUserNotificationEvent.toCacheModel();
	}

	@Override
	public LoopUserNotificationEvent toEscapedModel() {
		return new LoopUserNotificationEventWrapper(
			_loopUserNotificationEvent.toEscapedModel());
	}

	@Override
	public String toString() {
		return _loopUserNotificationEvent.toString();
	}

	@Override
	public LoopUserNotificationEvent toUnescapedModel() {
		return new LoopUserNotificationEventWrapper(
			_loopUserNotificationEvent.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _loopUserNotificationEvent.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopUserNotificationEventWrapper)) {
			return false;
		}

		LoopUserNotificationEventWrapper loopUserNotificationEventWrapper =
			(LoopUserNotificationEventWrapper)obj;

		if (Objects.equals(
				_loopUserNotificationEvent,
				loopUserNotificationEventWrapper._loopUserNotificationEvent)) {

			return true;
		}

		return false;
	}

	@Override
	public LoopUserNotificationEvent getWrappedModel() {
		return _loopUserNotificationEvent;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _loopUserNotificationEvent.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _loopUserNotificationEvent.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_loopUserNotificationEvent.resetOriginalValues();
	}

	private final LoopUserNotificationEvent _loopUserNotificationEvent;

}