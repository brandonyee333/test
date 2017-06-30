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
 * This class is a wrapper for {@link ExternalIdMapper}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ExternalIdMapper
 * @generated
 */
public class ExternalIdMapperWrapper implements ExternalIdMapper,
	ModelWrapper<ExternalIdMapper> {
	public ExternalIdMapperWrapper(ExternalIdMapper externalIdMapper) {
		_externalIdMapper = externalIdMapper;
	}

	public Class<?> getModelClass() {
		return ExternalIdMapper.class;
	}

	public String getModelClassName() {
		return ExternalIdMapper.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("externalIdMapperId", getExternalIdMapperId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("type", getType());
		attributes.put("externalId", getExternalId());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long externalIdMapperId = (Long)attributes.get("externalIdMapperId");

		if (externalIdMapperId != null) {
			setExternalIdMapperId(externalIdMapperId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
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

		String externalId = (String)attributes.get("externalId");

		if (externalId != null) {
			setExternalId(externalId);
		}
	}

	/**
	* Returns the primary key of this external ID mapper.
	*
	* @return the primary key of this external ID mapper
	*/
	public long getPrimaryKey() {
		return _externalIdMapper.getPrimaryKey();
	}

	/**
	* Sets the primary key of this external ID mapper.
	*
	* @param primaryKey the primary key of this external ID mapper
	*/
	public void setPrimaryKey(long primaryKey) {
		_externalIdMapper.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the external ID mapper ID of this external ID mapper.
	*
	* @return the external ID mapper ID of this external ID mapper
	*/
	public long getExternalIdMapperId() {
		return _externalIdMapper.getExternalIdMapperId();
	}

	/**
	* Sets the external ID mapper ID of this external ID mapper.
	*
	* @param externalIdMapperId the external ID mapper ID of this external ID mapper
	*/
	public void setExternalIdMapperId(long externalIdMapperId) {
		_externalIdMapper.setExternalIdMapperId(externalIdMapperId);
	}

	/**
	* Returns the create date of this external ID mapper.
	*
	* @return the create date of this external ID mapper
	*/
	public java.util.Date getCreateDate() {
		return _externalIdMapper.getCreateDate();
	}

	/**
	* Sets the create date of this external ID mapper.
	*
	* @param createDate the create date of this external ID mapper
	*/
	public void setCreateDate(java.util.Date createDate) {
		_externalIdMapper.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this external ID mapper.
	*
	* @return the modified date of this external ID mapper
	*/
	public java.util.Date getModifiedDate() {
		return _externalIdMapper.getModifiedDate();
	}

	/**
	* Sets the modified date of this external ID mapper.
	*
	* @param modifiedDate the modified date of this external ID mapper
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_externalIdMapper.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the fully qualified class name of this external ID mapper.
	*
	* @return the fully qualified class name of this external ID mapper
	*/
	public java.lang.String getClassName() {
		return _externalIdMapper.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_externalIdMapper.setClassName(className);
	}

	/**
	* Returns the class name ID of this external ID mapper.
	*
	* @return the class name ID of this external ID mapper
	*/
	public long getClassNameId() {
		return _externalIdMapper.getClassNameId();
	}

	/**
	* Sets the class name ID of this external ID mapper.
	*
	* @param classNameId the class name ID of this external ID mapper
	*/
	public void setClassNameId(long classNameId) {
		_externalIdMapper.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this external ID mapper.
	*
	* @return the class p k of this external ID mapper
	*/
	public long getClassPK() {
		return _externalIdMapper.getClassPK();
	}

	/**
	* Sets the class p k of this external ID mapper.
	*
	* @param classPK the class p k of this external ID mapper
	*/
	public void setClassPK(long classPK) {
		_externalIdMapper.setClassPK(classPK);
	}

	/**
	* Returns the type of this external ID mapper.
	*
	* @return the type of this external ID mapper
	*/
	public int getType() {
		return _externalIdMapper.getType();
	}

	/**
	* Sets the type of this external ID mapper.
	*
	* @param type the type of this external ID mapper
	*/
	public void setType(int type) {
		_externalIdMapper.setType(type);
	}

	/**
	* Returns the external ID of this external ID mapper.
	*
	* @return the external ID of this external ID mapper
	*/
	public java.lang.String getExternalId() {
		return _externalIdMapper.getExternalId();
	}

	/**
	* Sets the external ID of this external ID mapper.
	*
	* @param externalId the external ID of this external ID mapper
	*/
	public void setExternalId(java.lang.String externalId) {
		_externalIdMapper.setExternalId(externalId);
	}

	public boolean isNew() {
		return _externalIdMapper.isNew();
	}

	public void setNew(boolean n) {
		_externalIdMapper.setNew(n);
	}

	public boolean isCachedModel() {
		return _externalIdMapper.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_externalIdMapper.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _externalIdMapper.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _externalIdMapper.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_externalIdMapper.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _externalIdMapper.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_externalIdMapper.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new ExternalIdMapperWrapper((ExternalIdMapper)_externalIdMapper.clone());
	}

	public int compareTo(
		com.liferay.osb.model.ExternalIdMapper externalIdMapper) {
		return _externalIdMapper.compareTo(externalIdMapper);
	}

	@Override
	public int hashCode() {
		return _externalIdMapper.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.ExternalIdMapper> toCacheModel() {
		return _externalIdMapper.toCacheModel();
	}

	public com.liferay.osb.model.ExternalIdMapper toEscapedModel() {
		return new ExternalIdMapperWrapper(_externalIdMapper.toEscapedModel());
	}

	public com.liferay.osb.model.ExternalIdMapper toUnescapedModel() {
		return new ExternalIdMapperWrapper(_externalIdMapper.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _externalIdMapper.toString();
	}

	public java.lang.String toXmlString() {
		return _externalIdMapper.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_externalIdMapper.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ExternalIdMapperWrapper)) {
			return false;
		}

		ExternalIdMapperWrapper externalIdMapperWrapper = (ExternalIdMapperWrapper)obj;

		if (Validator.equals(_externalIdMapper,
					externalIdMapperWrapper._externalIdMapper)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public ExternalIdMapper getWrappedExternalIdMapper() {
		return _externalIdMapper;
	}

	public ExternalIdMapper getWrappedModel() {
		return _externalIdMapper;
	}

	public void resetOriginalValues() {
		_externalIdMapper.resetOriginalValues();
	}

	private ExternalIdMapper _externalIdMapper;
}