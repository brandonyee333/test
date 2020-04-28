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
 * This class is a wrapper for {@link LoopUserNotificationRecord}.
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopUserNotificationRecord
 * @generated
 */
public class LoopUserNotificationRecordWrapper
	implements LoopUserNotificationRecord,
			   ModelWrapper<LoopUserNotificationRecord> {

	public LoopUserNotificationRecordWrapper(
		LoopUserNotificationRecord loopUserNotificationRecord) {

		_loopUserNotificationRecord = loopUserNotificationRecord;
	}

	@Override
	public Class<?> getModelClass() {
		return LoopUserNotificationRecord.class;
	}

	@Override
	public String getModelClassName() {
		return LoopUserNotificationRecord.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put(
			"loopUserNotificationRecordId", getLoopUserNotificationRecordId());
		attributes.put("userId", getUserId());
		attributes.put("createTime", getCreateTime());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("deliveryType", getDeliveryType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long loopUserNotificationRecordId = (Long)attributes.get(
			"loopUserNotificationRecordId");

		if (loopUserNotificationRecordId != null) {
			setLoopUserNotificationRecordId(loopUserNotificationRecordId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long createTime = (Long)attributes.get("createTime");

		if (createTime != null) {
			setCreateTime(createTime);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer deliveryType = (Integer)attributes.get("deliveryType");

		if (deliveryType != null) {
			setDeliveryType(deliveryType);
		}
	}

	@Override
	public Object clone() {
		return new LoopUserNotificationRecordWrapper(
			(LoopUserNotificationRecord)_loopUserNotificationRecord.clone());
	}

	@Override
	public int compareTo(
		LoopUserNotificationRecord loopUserNotificationRecord) {

		return _loopUserNotificationRecord.compareTo(
			loopUserNotificationRecord);
	}

	/**
	 * Returns the fully qualified class name of this loop user notification record.
	 *
	 * @return the fully qualified class name of this loop user notification record
	 */
	@Override
	public String getClassName() {
		return _loopUserNotificationRecord.getClassName();
	}

	/**
	 * Returns the class name ID of this loop user notification record.
	 *
	 * @return the class name ID of this loop user notification record
	 */
	@Override
	public long getClassNameId() {
		return _loopUserNotificationRecord.getClassNameId();
	}

	/**
	 * Returns the class pk of this loop user notification record.
	 *
	 * @return the class pk of this loop user notification record
	 */
	@Override
	public long getClassPK() {
		return _loopUserNotificationRecord.getClassPK();
	}

	/**
	 * Returns the create time of this loop user notification record.
	 *
	 * @return the create time of this loop user notification record
	 */
	@Override
	public long getCreateTime() {
		return _loopUserNotificationRecord.getCreateTime();
	}

	/**
	 * Returns the delivery type of this loop user notification record.
	 *
	 * @return the delivery type of this loop user notification record
	 */
	@Override
	public int getDeliveryType() {
		return _loopUserNotificationRecord.getDeliveryType();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _loopUserNotificationRecord.getExpandoBridge();
	}

	/**
	 * Returns the loop user notification record ID of this loop user notification record.
	 *
	 * @return the loop user notification record ID of this loop user notification record
	 */
	@Override
	public long getLoopUserNotificationRecordId() {
		return _loopUserNotificationRecord.getLoopUserNotificationRecordId();
	}

	/**
	 * Returns the primary key of this loop user notification record.
	 *
	 * @return the primary key of this loop user notification record
	 */
	@Override
	public long getPrimaryKey() {
		return _loopUserNotificationRecord.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _loopUserNotificationRecord.getPrimaryKeyObj();
	}

	/**
	 * Returns the user ID of this loop user notification record.
	 *
	 * @return the user ID of this loop user notification record
	 */
	@Override
	public long getUserId() {
		return _loopUserNotificationRecord.getUserId();
	}

	/**
	 * Returns the user uuid of this loop user notification record.
	 *
	 * @return the user uuid of this loop user notification record
	 */
	@Override
	public String getUserUuid() {
		return _loopUserNotificationRecord.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _loopUserNotificationRecord.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _loopUserNotificationRecord.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _loopUserNotificationRecord.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _loopUserNotificationRecord.isNew();
	}

	@Override
	public void persist() {
		_loopUserNotificationRecord.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_loopUserNotificationRecord.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_loopUserNotificationRecord.setClassName(className);
	}

	/**
	 * Sets the class name ID of this loop user notification record.
	 *
	 * @param classNameId the class name ID of this loop user notification record
	 */
	@Override
	public void setClassNameId(long classNameId) {
		_loopUserNotificationRecord.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this loop user notification record.
	 *
	 * @param classPK the class pk of this loop user notification record
	 */
	@Override
	public void setClassPK(long classPK) {
		_loopUserNotificationRecord.setClassPK(classPK);
	}

	/**
	 * Sets the create time of this loop user notification record.
	 *
	 * @param createTime the create time of this loop user notification record
	 */
	@Override
	public void setCreateTime(long createTime) {
		_loopUserNotificationRecord.setCreateTime(createTime);
	}

	/**
	 * Sets the delivery type of this loop user notification record.
	 *
	 * @param deliveryType the delivery type of this loop user notification record
	 */
	@Override
	public void setDeliveryType(int deliveryType) {
		_loopUserNotificationRecord.setDeliveryType(deliveryType);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_loopUserNotificationRecord.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_loopUserNotificationRecord.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_loopUserNotificationRecord.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the loop user notification record ID of this loop user notification record.
	 *
	 * @param loopUserNotificationRecordId the loop user notification record ID of this loop user notification record
	 */
	@Override
	public void setLoopUserNotificationRecordId(
		long loopUserNotificationRecordId) {

		_loopUserNotificationRecord.setLoopUserNotificationRecordId(
			loopUserNotificationRecordId);
	}

	@Override
	public void setNew(boolean n) {
		_loopUserNotificationRecord.setNew(n);
	}

	/**
	 * Sets the primary key of this loop user notification record.
	 *
	 * @param primaryKey the primary key of this loop user notification record
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_loopUserNotificationRecord.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_loopUserNotificationRecord.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the user ID of this loop user notification record.
	 *
	 * @param userId the user ID of this loop user notification record
	 */
	@Override
	public void setUserId(long userId) {
		_loopUserNotificationRecord.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this loop user notification record.
	 *
	 * @param userUuid the user uuid of this loop user notification record
	 */
	@Override
	public void setUserUuid(String userUuid) {
		_loopUserNotificationRecord.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel
		<LoopUserNotificationRecord> toCacheModel() {

		return _loopUserNotificationRecord.toCacheModel();
	}

	@Override
	public LoopUserNotificationRecord toEscapedModel() {
		return new LoopUserNotificationRecordWrapper(
			_loopUserNotificationRecord.toEscapedModel());
	}

	@Override
	public String toString() {
		return _loopUserNotificationRecord.toString();
	}

	@Override
	public LoopUserNotificationRecord toUnescapedModel() {
		return new LoopUserNotificationRecordWrapper(
			_loopUserNotificationRecord.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _loopUserNotificationRecord.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopUserNotificationRecordWrapper)) {
			return false;
		}

		LoopUserNotificationRecordWrapper loopUserNotificationRecordWrapper =
			(LoopUserNotificationRecordWrapper)obj;

		if (Objects.equals(
				_loopUserNotificationRecord,
				loopUserNotificationRecordWrapper.
					_loopUserNotificationRecord)) {

			return true;
		}

		return false;
	}

	@Override
	public LoopUserNotificationRecord getWrappedModel() {
		return _loopUserNotificationRecord;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _loopUserNotificationRecord.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _loopUserNotificationRecord.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_loopUserNotificationRecord.resetOriginalValues();
	}

	private final LoopUserNotificationRecord _loopUserNotificationRecord;

}