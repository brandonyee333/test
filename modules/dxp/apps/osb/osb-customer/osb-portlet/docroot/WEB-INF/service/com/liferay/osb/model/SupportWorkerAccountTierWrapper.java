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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SupportWorkerAccountTier}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportWorkerAccountTier
 * @generated
 */
public class SupportWorkerAccountTierWrapper implements SupportWorkerAccountTier,
	ModelWrapper<SupportWorkerAccountTier> {
	public SupportWorkerAccountTierWrapper(
		SupportWorkerAccountTier supportWorkerAccountTier) {
		_supportWorkerAccountTier = supportWorkerAccountTier;
	}

	public Class<?> getModelClass() {
		return SupportWorkerAccountTier.class;
	}

	public String getModelClassName() {
		return SupportWorkerAccountTier.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportWorkerAccountTierId",
			getSupportWorkerAccountTierId());
		attributes.put("supportWorkerId", getSupportWorkerId());
		attributes.put("accountTier", getAccountTier());

		return attributes;
	}

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

	/**
	* Returns the primary key of this support worker account tier.
	*
	* @return the primary key of this support worker account tier
	*/
	public long getPrimaryKey() {
		return _supportWorkerAccountTier.getPrimaryKey();
	}

	/**
	* Sets the primary key of this support worker account tier.
	*
	* @param primaryKey the primary key of this support worker account tier
	*/
	public void setPrimaryKey(long primaryKey) {
		_supportWorkerAccountTier.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the support worker account tier ID of this support worker account tier.
	*
	* @return the support worker account tier ID of this support worker account tier
	*/
	public long getSupportWorkerAccountTierId() {
		return _supportWorkerAccountTier.getSupportWorkerAccountTierId();
	}

	/**
	* Sets the support worker account tier ID of this support worker account tier.
	*
	* @param supportWorkerAccountTierId the support worker account tier ID of this support worker account tier
	*/
	public void setSupportWorkerAccountTierId(long supportWorkerAccountTierId) {
		_supportWorkerAccountTier.setSupportWorkerAccountTierId(supportWorkerAccountTierId);
	}

	/**
	* Returns the support worker ID of this support worker account tier.
	*
	* @return the support worker ID of this support worker account tier
	*/
	public long getSupportWorkerId() {
		return _supportWorkerAccountTier.getSupportWorkerId();
	}

	/**
	* Sets the support worker ID of this support worker account tier.
	*
	* @param supportWorkerId the support worker ID of this support worker account tier
	*/
	public void setSupportWorkerId(long supportWorkerId) {
		_supportWorkerAccountTier.setSupportWorkerId(supportWorkerId);
	}

	/**
	* Returns the account tier of this support worker account tier.
	*
	* @return the account tier of this support worker account tier
	*/
	public int getAccountTier() {
		return _supportWorkerAccountTier.getAccountTier();
	}

	/**
	* Sets the account tier of this support worker account tier.
	*
	* @param accountTier the account tier of this support worker account tier
	*/
	public void setAccountTier(int accountTier) {
		_supportWorkerAccountTier.setAccountTier(accountTier);
	}

	public boolean isNew() {
		return _supportWorkerAccountTier.isNew();
	}

	public void setNew(boolean n) {
		_supportWorkerAccountTier.setNew(n);
	}

	public boolean isCachedModel() {
		return _supportWorkerAccountTier.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_supportWorkerAccountTier.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _supportWorkerAccountTier.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _supportWorkerAccountTier.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_supportWorkerAccountTier.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _supportWorkerAccountTier.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_supportWorkerAccountTier.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SupportWorkerAccountTierWrapper((SupportWorkerAccountTier)_supportWorkerAccountTier.clone());
	}

	public int compareTo(
		com.liferay.osb.model.SupportWorkerAccountTier supportWorkerAccountTier) {
		return _supportWorkerAccountTier.compareTo(supportWorkerAccountTier);
	}

	@Override
	public int hashCode() {
		return _supportWorkerAccountTier.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.SupportWorkerAccountTier> toCacheModel() {
		return _supportWorkerAccountTier.toCacheModel();
	}

	public com.liferay.osb.model.SupportWorkerAccountTier toEscapedModel() {
		return new SupportWorkerAccountTierWrapper(_supportWorkerAccountTier.toEscapedModel());
	}

	public com.liferay.osb.model.SupportWorkerAccountTier toUnescapedModel() {
		return new SupportWorkerAccountTierWrapper(_supportWorkerAccountTier.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _supportWorkerAccountTier.toString();
	}

	public java.lang.String toXmlString() {
		return _supportWorkerAccountTier.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportWorkerAccountTier.persist();
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

		if (Validator.equals(_supportWorkerAccountTier,
					supportWorkerAccountTierWrapper._supportWorkerAccountTier)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public SupportWorkerAccountTier getWrappedSupportWorkerAccountTier() {
		return _supportWorkerAccountTier;
	}

	public SupportWorkerAccountTier getWrappedModel() {
		return _supportWorkerAccountTier;
	}

	public void resetOriginalValues() {
		_supportWorkerAccountTier.resetOriginalValues();
	}

	private SupportWorkerAccountTier _supportWorkerAccountTier;
}