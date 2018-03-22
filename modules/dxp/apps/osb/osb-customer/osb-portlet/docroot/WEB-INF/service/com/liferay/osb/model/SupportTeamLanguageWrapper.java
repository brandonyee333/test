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
 * This class is a wrapper for {@link SupportTeamLanguage}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportTeamLanguage
 * @generated
 */
@ProviderType
public class SupportTeamLanguageWrapper implements SupportTeamLanguage,
	ModelWrapper<SupportTeamLanguage> {
	public SupportTeamLanguageWrapper(SupportTeamLanguage supportTeamLanguage) {
		_supportTeamLanguage = supportTeamLanguage;
	}

	@Override
	public Class<?> getModelClass() {
		return SupportTeamLanguage.class;
	}

	@Override
	public String getModelClassName() {
		return SupportTeamLanguage.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportTeamLanguageId", getSupportTeamLanguageId());
		attributes.put("supportTeamId", getSupportTeamId());
		attributes.put("languageId", getLanguageId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportTeamLanguageId = (Long)attributes.get(
				"supportTeamLanguageId");

		if (supportTeamLanguageId != null) {
			setSupportTeamLanguageId(supportTeamLanguageId);
		}

		Long supportTeamId = (Long)attributes.get("supportTeamId");

		if (supportTeamId != null) {
			setSupportTeamId(supportTeamId);
		}

		String languageId = (String)attributes.get("languageId");

		if (languageId != null) {
			setLanguageId(languageId);
		}
	}

	@Override
	public SupportTeamLanguage toEscapedModel() {
		return new SupportTeamLanguageWrapper(_supportTeamLanguage.toEscapedModel());
	}

	@Override
	public SupportTeamLanguage toUnescapedModel() {
		return new SupportTeamLanguageWrapper(_supportTeamLanguage.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _supportTeamLanguage.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _supportTeamLanguage.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _supportTeamLanguage.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _supportTeamLanguage.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SupportTeamLanguage> toCacheModel() {
		return _supportTeamLanguage.toCacheModel();
	}

	@Override
	public int compareTo(SupportTeamLanguage supportTeamLanguage) {
		return _supportTeamLanguage.compareTo(supportTeamLanguage);
	}

	@Override
	public int hashCode() {
		return _supportTeamLanguage.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _supportTeamLanguage.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SupportTeamLanguageWrapper((SupportTeamLanguage)_supportTeamLanguage.clone());
	}

	/**
	* Returns the language ID of this support team language.
	*
	* @return the language ID of this support team language
	*/
	@Override
	public java.lang.String getLanguageId() {
		return _supportTeamLanguage.getLanguageId();
	}

	@Override
	public java.lang.String toString() {
		return _supportTeamLanguage.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _supportTeamLanguage.toXmlString();
	}

	/**
	* Returns the primary key of this support team language.
	*
	* @return the primary key of this support team language
	*/
	@Override
	public long getPrimaryKey() {
		return _supportTeamLanguage.getPrimaryKey();
	}

	/**
	* Returns the support team ID of this support team language.
	*
	* @return the support team ID of this support team language
	*/
	@Override
	public long getSupportTeamId() {
		return _supportTeamLanguage.getSupportTeamId();
	}

	/**
	* Returns the support team language ID of this support team language.
	*
	* @return the support team language ID of this support team language
	*/
	@Override
	public long getSupportTeamLanguageId() {
		return _supportTeamLanguage.getSupportTeamLanguageId();
	}

	@Override
	public void persist() {
		_supportTeamLanguage.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_supportTeamLanguage.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_supportTeamLanguage.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_supportTeamLanguage.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_supportTeamLanguage.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the language ID of this support team language.
	*
	* @param languageId the language ID of this support team language
	*/
	@Override
	public void setLanguageId(java.lang.String languageId) {
		_supportTeamLanguage.setLanguageId(languageId);
	}

	@Override
	public void setNew(boolean n) {
		_supportTeamLanguage.setNew(n);
	}

	/**
	* Sets the primary key of this support team language.
	*
	* @param primaryKey the primary key of this support team language
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_supportTeamLanguage.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_supportTeamLanguage.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the support team ID of this support team language.
	*
	* @param supportTeamId the support team ID of this support team language
	*/
	@Override
	public void setSupportTeamId(long supportTeamId) {
		_supportTeamLanguage.setSupportTeamId(supportTeamId);
	}

	/**
	* Sets the support team language ID of this support team language.
	*
	* @param supportTeamLanguageId the support team language ID of this support team language
	*/
	@Override
	public void setSupportTeamLanguageId(long supportTeamLanguageId) {
		_supportTeamLanguage.setSupportTeamLanguageId(supportTeamLanguageId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SupportTeamLanguageWrapper)) {
			return false;
		}

		SupportTeamLanguageWrapper supportTeamLanguageWrapper = (SupportTeamLanguageWrapper)obj;

		if (Objects.equals(_supportTeamLanguage,
					supportTeamLanguageWrapper._supportTeamLanguage)) {
			return true;
		}

		return false;
	}

	@Override
	public SupportTeamLanguage getWrappedModel() {
		return _supportTeamLanguage;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _supportTeamLanguage.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _supportTeamLanguage.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_supportTeamLanguage.resetOriginalValues();
	}

	private final SupportTeamLanguage _supportTeamLanguage;
}