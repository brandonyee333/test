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
 * This class is a wrapper for {@link SupportTeamLanguage}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportTeamLanguage
 * @generated
 */
public class SupportTeamLanguageWrapper implements SupportTeamLanguage,
	ModelWrapper<SupportTeamLanguage> {
	public SupportTeamLanguageWrapper(SupportTeamLanguage supportTeamLanguage) {
		_supportTeamLanguage = supportTeamLanguage;
	}

	public Class<?> getModelClass() {
		return SupportTeamLanguage.class;
	}

	public String getModelClassName() {
		return SupportTeamLanguage.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportTeamLanguageId", getSupportTeamLanguageId());
		attributes.put("supportTeamId", getSupportTeamId());
		attributes.put("languageId", getLanguageId());

		return attributes;
	}

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

	/**
	* Returns the primary key of this support team language.
	*
	* @return the primary key of this support team language
	*/
	public long getPrimaryKey() {
		return _supportTeamLanguage.getPrimaryKey();
	}

	/**
	* Sets the primary key of this support team language.
	*
	* @param primaryKey the primary key of this support team language
	*/
	public void setPrimaryKey(long primaryKey) {
		_supportTeamLanguage.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the support team language ID of this support team language.
	*
	* @return the support team language ID of this support team language
	*/
	public long getSupportTeamLanguageId() {
		return _supportTeamLanguage.getSupportTeamLanguageId();
	}

	/**
	* Sets the support team language ID of this support team language.
	*
	* @param supportTeamLanguageId the support team language ID of this support team language
	*/
	public void setSupportTeamLanguageId(long supportTeamLanguageId) {
		_supportTeamLanguage.setSupportTeamLanguageId(supportTeamLanguageId);
	}

	/**
	* Returns the support team ID of this support team language.
	*
	* @return the support team ID of this support team language
	*/
	public long getSupportTeamId() {
		return _supportTeamLanguage.getSupportTeamId();
	}

	/**
	* Sets the support team ID of this support team language.
	*
	* @param supportTeamId the support team ID of this support team language
	*/
	public void setSupportTeamId(long supportTeamId) {
		_supportTeamLanguage.setSupportTeamId(supportTeamId);
	}

	/**
	* Returns the language ID of this support team language.
	*
	* @return the language ID of this support team language
	*/
	public java.lang.String getLanguageId() {
		return _supportTeamLanguage.getLanguageId();
	}

	/**
	* Sets the language ID of this support team language.
	*
	* @param languageId the language ID of this support team language
	*/
	public void setLanguageId(java.lang.String languageId) {
		_supportTeamLanguage.setLanguageId(languageId);
	}

	public boolean isNew() {
		return _supportTeamLanguage.isNew();
	}

	public void setNew(boolean n) {
		_supportTeamLanguage.setNew(n);
	}

	public boolean isCachedModel() {
		return _supportTeamLanguage.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_supportTeamLanguage.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _supportTeamLanguage.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _supportTeamLanguage.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_supportTeamLanguage.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _supportTeamLanguage.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_supportTeamLanguage.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SupportTeamLanguageWrapper((SupportTeamLanguage)_supportTeamLanguage.clone());
	}

	public int compareTo(
		com.liferay.osb.model.SupportTeamLanguage supportTeamLanguage) {
		return _supportTeamLanguage.compareTo(supportTeamLanguage);
	}

	@Override
	public int hashCode() {
		return _supportTeamLanguage.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.SupportTeamLanguage> toCacheModel() {
		return _supportTeamLanguage.toCacheModel();
	}

	public com.liferay.osb.model.SupportTeamLanguage toEscapedModel() {
		return new SupportTeamLanguageWrapper(_supportTeamLanguage.toEscapedModel());
	}

	public com.liferay.osb.model.SupportTeamLanguage toUnescapedModel() {
		return new SupportTeamLanguageWrapper(_supportTeamLanguage.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _supportTeamLanguage.toString();
	}

	public java.lang.String toXmlString() {
		return _supportTeamLanguage.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportTeamLanguage.persist();
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

		if (Validator.equals(_supportTeamLanguage,
					supportTeamLanguageWrapper._supportTeamLanguage)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public SupportTeamLanguage getWrappedSupportTeamLanguage() {
		return _supportTeamLanguage;
	}

	public SupportTeamLanguage getWrappedModel() {
		return _supportTeamLanguage;
	}

	public void resetOriginalValues() {
		_supportTeamLanguage.resetOriginalValues();
	}

	private SupportTeamLanguage _supportTeamLanguage;
}