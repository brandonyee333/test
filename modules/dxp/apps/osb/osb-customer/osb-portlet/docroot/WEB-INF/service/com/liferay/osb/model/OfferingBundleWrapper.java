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

package com.liferay.osb.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link OfferingBundle}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OfferingBundle
 * @generated
 */
public class OfferingBundleWrapper implements OfferingBundle,
	ModelWrapper<OfferingBundle> {
	public OfferingBundleWrapper(OfferingBundle offeringBundle) {
		_offeringBundle = offeringBundle;
	}

	public Class<?> getModelClass() {
		return OfferingBundle.class;
	}

	public String getModelClassName() {
		return OfferingBundle.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("offeringBundleId", getOfferingBundleId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("name", getName());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long offeringBundleId = (Long)attributes.get("offeringBundleId");

		if (offeringBundleId != null) {
			setOfferingBundleId(offeringBundleId);
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

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}
	}

	/**
	* Returns the primary key of this offering bundle.
	*
	* @return the primary key of this offering bundle
	*/
	public long getPrimaryKey() {
		return _offeringBundle.getPrimaryKey();
	}

	/**
	* Sets the primary key of this offering bundle.
	*
	* @param primaryKey the primary key of this offering bundle
	*/
	public void setPrimaryKey(long primaryKey) {
		_offeringBundle.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the offering bundle ID of this offering bundle.
	*
	* @return the offering bundle ID of this offering bundle
	*/
	public long getOfferingBundleId() {
		return _offeringBundle.getOfferingBundleId();
	}

	/**
	* Sets the offering bundle ID of this offering bundle.
	*
	* @param offeringBundleId the offering bundle ID of this offering bundle
	*/
	public void setOfferingBundleId(long offeringBundleId) {
		_offeringBundle.setOfferingBundleId(offeringBundleId);
	}

	/**
	* Returns the user ID of this offering bundle.
	*
	* @return the user ID of this offering bundle
	*/
	public long getUserId() {
		return _offeringBundle.getUserId();
	}

	/**
	* Sets the user ID of this offering bundle.
	*
	* @param userId the user ID of this offering bundle
	*/
	public void setUserId(long userId) {
		_offeringBundle.setUserId(userId);
	}

	/**
	* Returns the user uuid of this offering bundle.
	*
	* @return the user uuid of this offering bundle
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundle.getUserUuid();
	}

	/**
	* Sets the user uuid of this offering bundle.
	*
	* @param userUuid the user uuid of this offering bundle
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_offeringBundle.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this offering bundle.
	*
	* @return the user name of this offering bundle
	*/
	public java.lang.String getUserName() {
		return _offeringBundle.getUserName();
	}

	/**
	* Sets the user name of this offering bundle.
	*
	* @param userName the user name of this offering bundle
	*/
	public void setUserName(java.lang.String userName) {
		_offeringBundle.setUserName(userName);
	}

	/**
	* Returns the create date of this offering bundle.
	*
	* @return the create date of this offering bundle
	*/
	public java.util.Date getCreateDate() {
		return _offeringBundle.getCreateDate();
	}

	/**
	* Sets the create date of this offering bundle.
	*
	* @param createDate the create date of this offering bundle
	*/
	public void setCreateDate(java.util.Date createDate) {
		_offeringBundle.setCreateDate(createDate);
	}

	/**
	* Returns the name of this offering bundle.
	*
	* @return the name of this offering bundle
	*/
	public java.lang.String getName() {
		return _offeringBundle.getName();
	}

	/**
	* Sets the name of this offering bundle.
	*
	* @param name the name of this offering bundle
	*/
	public void setName(java.lang.String name) {
		_offeringBundle.setName(name);
	}

	public boolean isNew() {
		return _offeringBundle.isNew();
	}

	public void setNew(boolean n) {
		_offeringBundle.setNew(n);
	}

	public boolean isCachedModel() {
		return _offeringBundle.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_offeringBundle.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _offeringBundle.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _offeringBundle.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_offeringBundle.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _offeringBundle.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_offeringBundle.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new OfferingBundleWrapper((OfferingBundle)_offeringBundle.clone());
	}

	public int compareTo(com.liferay.osb.model.OfferingBundle offeringBundle) {
		return _offeringBundle.compareTo(offeringBundle);
	}

	@Override
	public int hashCode() {
		return _offeringBundle.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.OfferingBundle> toCacheModel() {
		return _offeringBundle.toCacheModel();
	}

	public com.liferay.osb.model.OfferingBundle toEscapedModel() {
		return new OfferingBundleWrapper(_offeringBundle.toEscapedModel());
	}

	public com.liferay.osb.model.OfferingBundle toUnescapedModel() {
		return new OfferingBundleWrapper(_offeringBundle.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _offeringBundle.toString();
	}

	public java.lang.String toXmlString() {
		return _offeringBundle.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_offeringBundle.persist();
	}

	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _offeringBundle.getOfferingDefinitions();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof OfferingBundleWrapper)) {
			return false;
		}

		OfferingBundleWrapper offeringBundleWrapper = (OfferingBundleWrapper)obj;

		if (Validator.equals(_offeringBundle,
					offeringBundleWrapper._offeringBundle)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public OfferingBundle getWrappedOfferingBundle() {
		return _offeringBundle;
	}

	public OfferingBundle getWrappedModel() {
		return _offeringBundle;
	}

	public void resetOriginalValues() {
		_offeringBundle.resetOriginalValues();
	}

	private OfferingBundle _offeringBundle;
}