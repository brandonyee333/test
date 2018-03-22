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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link SupportWorkerAccountTier}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerAccountTier
 * @generated
 */
@ProviderType
public class SupportWorkerAccountTierWrapper implements SupportWorkerAccountTier,
	ModelWrapper<SupportWorkerAccountTier> {
	public SupportWorkerAccountTierWrapper(
		SupportWorkerAccountTier supportWorkerAccountTier) {
		_supportWorkerAccountTier = supportWorkerAccountTier;
	}

	@Override
	public Class<?> getModelClass() {
		return SupportWorkerAccountTier.class;
	}

	@Override
	public String getModelClassName() {
		return SupportWorkerAccountTier.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportWorkerAccountTierId",
			getSupportWorkerAccountTierId());
		attributes.put("supportWorkerId", getSupportWorkerId());
		attributes.put("accountTier", getAccountTier());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportWorkerAccountTierId = (Long)attributes.get(
				"supportWorkerAccountTierId");

		if (supportWorkerAccountTierId != null) {
			setSupportWorkerAccountTierId(supportWorkerAccountTierId);
		}

		Long supportWorkerId = (Long)attributes.get("supportWorkerId");

		if (supportWorkerId != null) {
			setSupportWorkerId(supportWorkerId);
		}

		Integer accountTier = (Integer)attributes.get("accountTier");

		if (accountTier != null) {
			setAccountTier(accountTier);
		}
	}

	@Override
	public SupportWorkerAccountTier toEscapedModel() {
		return new SupportWorkerAccountTierWrapper(_supportWorkerAccountTier.toEscapedModel());
	}

	@Override
	public SupportWorkerAccountTier toUnescapedModel() {
		return new SupportWorkerAccountTierWrapper(_supportWorkerAccountTier.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _supportWorkerAccountTier.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _supportWorkerAccountTier.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _supportWorkerAccountTier.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _supportWorkerAccountTier.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SupportWorkerAccountTier> toCacheModel() {
		return _supportWorkerAccountTier.toCacheModel();
	}

	@Override
	public int compareTo(SupportWorkerAccountTier supportWorkerAccountTier) {
		return _supportWorkerAccountTier.compareTo(supportWorkerAccountTier);
	}

	/**
	* Returns the account tier of this support worker account tier.
	*
	* @return the account tier of this support worker account tier
	*/
	@Override
	public int getAccountTier() {
		return _supportWorkerAccountTier.getAccountTier();
	}

	@Override
	public int hashCode() {
		return _supportWorkerAccountTier.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _supportWorkerAccountTier.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SupportWorkerAccountTierWrapper((SupportWorkerAccountTier)_supportWorkerAccountTier.clone());
	}

	@Override
	public java.lang.String toString() {
		return _supportWorkerAccountTier.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _supportWorkerAccountTier.toXmlString();
	}

	/**
	* Returns the primary key of this support worker account tier.
	*
	* @return the primary key of this support worker account tier
	*/
	@Override
	public long getPrimaryKey() {
		return _supportWorkerAccountTier.getPrimaryKey();
	}

	/**
	* Returns the support worker account tier ID of this support worker account tier.
	*
	* @return the support worker account tier ID of this support worker account tier
	*/
	@Override
	public long getSupportWorkerAccountTierId() {
		return _supportWorkerAccountTier.getSupportWorkerAccountTierId();
	}

	/**
	* Returns the support worker ID of this support worker account tier.
	*
	* @return the support worker ID of this support worker account tier
	*/
	@Override
	public long getSupportWorkerId() {
		return _supportWorkerAccountTier.getSupportWorkerId();
	}

	@Override
	public void persist() {
		_supportWorkerAccountTier.persist();
	}

	/**
	* Sets the account tier of this support worker account tier.
	*
	* @param accountTier the account tier of this support worker account tier
	*/
	@Override
	public void setAccountTier(int accountTier) {
		_supportWorkerAccountTier.setAccountTier(accountTier);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_supportWorkerAccountTier.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_supportWorkerAccountTier.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_supportWorkerAccountTier.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_supportWorkerAccountTier.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_supportWorkerAccountTier.setNew(n);
	}

	/**
	* Sets the primary key of this support worker account tier.
	*
	* @param primaryKey the primary key of this support worker account tier
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_supportWorkerAccountTier.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_supportWorkerAccountTier.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the support worker account tier ID of this support worker account tier.
	*
	* @param supportWorkerAccountTierId the support worker account tier ID of this support worker account tier
	*/
	@Override
	public void setSupportWorkerAccountTierId(long supportWorkerAccountTierId) {
		_supportWorkerAccountTier.setSupportWorkerAccountTierId(supportWorkerAccountTierId);
	}

	/**
	* Sets the support worker ID of this support worker account tier.
	*
	* @param supportWorkerId the support worker ID of this support worker account tier
	*/
	@Override
	public void setSupportWorkerId(long supportWorkerId) {
		_supportWorkerAccountTier.setSupportWorkerId(supportWorkerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SupportWorkerAccountTierWrapper)) {
			return false;
		}

		SupportWorkerAccountTierWrapper supportWorkerAccountTierWrapper = (SupportWorkerAccountTierWrapper)obj;

		if (Objects.equals(_supportWorkerAccountTier,
					supportWorkerAccountTierWrapper._supportWorkerAccountTier)) {
			return true;
		}

		return false;
	}

	@Override
	public SupportWorkerAccountTier getWrappedModel() {
		return _supportWorkerAccountTier;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _supportWorkerAccountTier.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _supportWorkerAccountTier.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_supportWorkerAccountTier.resetOriginalValues();
	}

	private final SupportWorkerAccountTier _supportWorkerAccountTier;
}