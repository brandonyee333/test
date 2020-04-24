/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.loop.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link LoopUserNotificationSubscription}.
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopUserNotificationSubscription
 * @generated
 */
@ProviderType
public class LoopUserNotificationSubscriptionWrapper
	implements LoopUserNotificationSubscription,
		ModelWrapper<LoopUserNotificationSubscription> {
	public LoopUserNotificationSubscriptionWrapper(
		LoopUserNotificationSubscription loopUserNotificationSubscription) {
		_loopUserNotificationSubscription = loopUserNotificationSubscription;
	}

	@Override
	public Class<?> getModelClass() {
		return LoopUserNotificationSubscription.class;
	}

	@Override
	public String getModelClassName() {
		return LoopUserNotificationSubscription.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("loopUserNotificationSubscriptionId",
			getLoopUserNotificationSubscriptionId());
		attributes.put("loopPersonId", getLoopPersonId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("deliveryType", getDeliveryType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long loopUserNotificationSubscriptionId = (Long)attributes.get(
				"loopUserNotificationSubscriptionId");

		if (loopUserNotificationSubscriptionId != null) {
			setLoopUserNotificationSubscriptionId(loopUserNotificationSubscriptionId);
		}

		Long loopPersonId = (Long)attributes.get("loopPersonId");

		if (loopPersonId != null) {
			setLoopPersonId(loopPersonId);
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
		return new LoopUserNotificationSubscriptionWrapper((LoopUserNotificationSubscription)_loopUserNotificationSubscription.clone());
	}

	@Override
	public int compareTo(
		LoopUserNotificationSubscription loopUserNotificationSubscription) {
		return _loopUserNotificationSubscription.compareTo(loopUserNotificationSubscription);
	}

	/**
	* Returns the fully qualified class name of this loop user notification subscription.
	*
	* @return the fully qualified class name of this loop user notification subscription
	*/
	@Override
	public String getClassName() {
		return _loopUserNotificationSubscription.getClassName();
	}

	/**
	* Returns the class name ID of this loop user notification subscription.
	*
	* @return the class name ID of this loop user notification subscription
	*/
	@Override
	public long getClassNameId() {
		return _loopUserNotificationSubscription.getClassNameId();
	}

	/**
	* Returns the class pk of this loop user notification subscription.
	*
	* @return the class pk of this loop user notification subscription
	*/
	@Override
	public long getClassPK() {
		return _loopUserNotificationSubscription.getClassPK();
	}

	/**
	* Returns the delivery type of this loop user notification subscription.
	*
	* @return the delivery type of this loop user notification subscription
	*/
	@Override
	public int getDeliveryType() {
		return _loopUserNotificationSubscription.getDeliveryType();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _loopUserNotificationSubscription.getExpandoBridge();
	}

	/**
	* Returns the loop person ID of this loop user notification subscription.
	*
	* @return the loop person ID of this loop user notification subscription
	*/
	@Override
	public long getLoopPersonId() {
		return _loopUserNotificationSubscription.getLoopPersonId();
	}

	/**
	* Returns the loop user notification subscription ID of this loop user notification subscription.
	*
	* @return the loop user notification subscription ID of this loop user notification subscription
	*/
	@Override
	public long getLoopUserNotificationSubscriptionId() {
		return _loopUserNotificationSubscription.getLoopUserNotificationSubscriptionId();
	}

	/**
	* Returns the primary key of this loop user notification subscription.
	*
	* @return the primary key of this loop user notification subscription
	*/
	@Override
	public long getPrimaryKey() {
		return _loopUserNotificationSubscription.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _loopUserNotificationSubscription.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _loopUserNotificationSubscription.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _loopUserNotificationSubscription.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _loopUserNotificationSubscription.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _loopUserNotificationSubscription.isNew();
	}

	@Override
	public void persist() {
		_loopUserNotificationSubscription.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_loopUserNotificationSubscription.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_loopUserNotificationSubscription.setClassName(className);
	}

	/**
	* Sets the class name ID of this loop user notification subscription.
	*
	* @param classNameId the class name ID of this loop user notification subscription
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_loopUserNotificationSubscription.setClassNameId(classNameId);
	}

	/**
	* Sets the class pk of this loop user notification subscription.
	*
	* @param classPK the class pk of this loop user notification subscription
	*/
	@Override
	public void setClassPK(long classPK) {
		_loopUserNotificationSubscription.setClassPK(classPK);
	}

	/**
	* Sets the delivery type of this loop user notification subscription.
	*
	* @param deliveryType the delivery type of this loop user notification subscription
	*/
	@Override
	public void setDeliveryType(int deliveryType) {
		_loopUserNotificationSubscription.setDeliveryType(deliveryType);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_loopUserNotificationSubscription.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_loopUserNotificationSubscription.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_loopUserNotificationSubscription.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the loop person ID of this loop user notification subscription.
	*
	* @param loopPersonId the loop person ID of this loop user notification subscription
	*/
	@Override
	public void setLoopPersonId(long loopPersonId) {
		_loopUserNotificationSubscription.setLoopPersonId(loopPersonId);
	}

	/**
	* Sets the loop user notification subscription ID of this loop user notification subscription.
	*
	* @param loopUserNotificationSubscriptionId the loop user notification subscription ID of this loop user notification subscription
	*/
	@Override
	public void setLoopUserNotificationSubscriptionId(
		long loopUserNotificationSubscriptionId) {
		_loopUserNotificationSubscription.setLoopUserNotificationSubscriptionId(loopUserNotificationSubscriptionId);
	}

	@Override
	public void setNew(boolean n) {
		_loopUserNotificationSubscription.setNew(n);
	}

	/**
	* Sets the primary key of this loop user notification subscription.
	*
	* @param primaryKey the primary key of this loop user notification subscription
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_loopUserNotificationSubscription.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_loopUserNotificationSubscription.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LoopUserNotificationSubscription> toCacheModel() {
		return _loopUserNotificationSubscription.toCacheModel();
	}

	@Override
	public LoopUserNotificationSubscription toEscapedModel() {
		return new LoopUserNotificationSubscriptionWrapper(_loopUserNotificationSubscription.toEscapedModel());
	}

	@Override
	public String toString() {
		return _loopUserNotificationSubscription.toString();
	}

	@Override
	public LoopUserNotificationSubscription toUnescapedModel() {
		return new LoopUserNotificationSubscriptionWrapper(_loopUserNotificationSubscription.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _loopUserNotificationSubscription.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopUserNotificationSubscriptionWrapper)) {
			return false;
		}

		LoopUserNotificationSubscriptionWrapper loopUserNotificationSubscriptionWrapper =
			(LoopUserNotificationSubscriptionWrapper)obj;

		if (Objects.equals(_loopUserNotificationSubscription,
					loopUserNotificationSubscriptionWrapper._loopUserNotificationSubscription)) {
			return true;
		}

		return false;
	}

	@Override
	public LoopUserNotificationSubscription getWrappedModel() {
		return _loopUserNotificationSubscription;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _loopUserNotificationSubscription.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _loopUserNotificationSubscription.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_loopUserNotificationSubscription.resetOriginalValues();
	}

	private final LoopUserNotificationSubscription _loopUserNotificationSubscription;
}