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

import aQute.bnd.annotation.ProviderType;

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
 * This class is a wrapper for {@link OfferingBundle}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingBundle
 * @generated
 */
@ProviderType
public class OfferingBundleWrapper implements OfferingBundle,
	ModelWrapper<OfferingBundle> {
	public OfferingBundleWrapper(OfferingBundle offeringBundle) {
		_offeringBundle = offeringBundle;
	}

	@Override
	public Class<?> getModelClass() {
		return OfferingBundle.class;
	}

	@Override
	public String getModelClassName() {
		return OfferingBundle.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("offeringBundleId", getOfferingBundleId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("name", getName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long offeringBundleId = (Long)attributes.get("offeringBundleId");

		if (offeringBundleId != null) {
			setOfferingBundleId(offeringBundleId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
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

	@Override
	public OfferingBundle toEscapedModel() {
		return new OfferingBundleWrapper(_offeringBundle.toEscapedModel());
	}

	@Override
	public OfferingBundle toUnescapedModel() {
		return new OfferingBundleWrapper(_offeringBundle.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _offeringBundle.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _offeringBundle.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _offeringBundle.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _offeringBundle.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<OfferingBundle> toCacheModel() {
		return _offeringBundle.toCacheModel();
	}

	@Override
	public int compareTo(OfferingBundle offeringBundle) {
		return _offeringBundle.compareTo(offeringBundle);
	}

	@Override
	public int hashCode() {
		return _offeringBundle.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _offeringBundle.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new OfferingBundleWrapper((OfferingBundle)_offeringBundle.clone());
	}

	/**
	* Returns the name of this offering bundle.
	*
	* @return the name of this offering bundle
	*/
	@Override
	public java.lang.String getName() {
		return _offeringBundle.getName();
	}

	/**
	* Returns the user name of this offering bundle.
	*
	* @return the user name of this offering bundle
	*/
	@Override
	public java.lang.String getUserName() {
		return _offeringBundle.getUserName();
	}

	/**
	* Returns the user uuid of this offering bundle.
	*
	* @return the user uuid of this offering bundle
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _offeringBundle.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _offeringBundle.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _offeringBundle.toXmlString();
	}

	/**
	* Returns the create date of this offering bundle.
	*
	* @return the create date of this offering bundle
	*/
	@Override
	public Date getCreateDate() {
		return _offeringBundle.getCreateDate();
	}

	@Override
	public java.util.List<OfferingDefinition> getOfferingDefinitions() {
		return _offeringBundle.getOfferingDefinitions();
	}

	/**
	* Returns the company ID of this offering bundle.
	*
	* @return the company ID of this offering bundle
	*/
	@Override
	public long getCompanyId() {
		return _offeringBundle.getCompanyId();
	}

	/**
	* Returns the offering bundle ID of this offering bundle.
	*
	* @return the offering bundle ID of this offering bundle
	*/
	@Override
	public long getOfferingBundleId() {
		return _offeringBundle.getOfferingBundleId();
	}

	/**
	* Returns the primary key of this offering bundle.
	*
	* @return the primary key of this offering bundle
	*/
	@Override
	public long getPrimaryKey() {
		return _offeringBundle.getPrimaryKey();
	}

	/**
	* Returns the user ID of this offering bundle.
	*
	* @return the user ID of this offering bundle
	*/
	@Override
	public long getUserId() {
		return _offeringBundle.getUserId();
	}

	@Override
	public void persist() {
		_offeringBundle.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_offeringBundle.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this offering bundle.
	*
	* @param companyId the company ID of this offering bundle
	*/
	@Override
	public void setCompanyId(long companyId) {
		_offeringBundle.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this offering bundle.
	*
	* @param createDate the create date of this offering bundle
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_offeringBundle.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_offeringBundle.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_offeringBundle.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_offeringBundle.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the name of this offering bundle.
	*
	* @param name the name of this offering bundle
	*/
	@Override
	public void setName(java.lang.String name) {
		_offeringBundle.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_offeringBundle.setNew(n);
	}

	/**
	* Sets the offering bundle ID of this offering bundle.
	*
	* @param offeringBundleId the offering bundle ID of this offering bundle
	*/
	@Override
	public void setOfferingBundleId(long offeringBundleId) {
		_offeringBundle.setOfferingBundleId(offeringBundleId);
	}

	/**
	* Sets the primary key of this offering bundle.
	*
	* @param primaryKey the primary key of this offering bundle
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_offeringBundle.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_offeringBundle.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this offering bundle.
	*
	* @param userId the user ID of this offering bundle
	*/
	@Override
	public void setUserId(long userId) {
		_offeringBundle.setUserId(userId);
	}

	/**
	* Sets the user name of this offering bundle.
	*
	* @param userName the user name of this offering bundle
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_offeringBundle.setUserName(userName);
	}

	/**
	* Sets the user uuid of this offering bundle.
	*
	* @param userUuid the user uuid of this offering bundle
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_offeringBundle.setUserUuid(userUuid);
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

		if (Objects.equals(_offeringBundle,
					offeringBundleWrapper._offeringBundle)) {
			return true;
		}

		return false;
	}

	@Override
	public OfferingBundle getWrappedModel() {
		return _offeringBundle;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _offeringBundle.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _offeringBundle.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_offeringBundle.resetOriginalValues();
	}

	private final OfferingBundle _offeringBundle;
}