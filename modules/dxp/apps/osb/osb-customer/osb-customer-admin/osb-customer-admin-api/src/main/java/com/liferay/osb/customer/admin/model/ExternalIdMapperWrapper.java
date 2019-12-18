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

package com.liferay.osb.customer.admin.model;

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
 * This class is a wrapper for {@link ExternalIdMapper}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExternalIdMapper
 * @generated
 */
public class ExternalIdMapperWrapper
	implements ExternalIdMapper, ModelWrapper<ExternalIdMapper> {

	public ExternalIdMapperWrapper(ExternalIdMapper externalIdMapper) {
		_externalIdMapper = externalIdMapper;
	}

	@Override
	public Class<?> getModelClass() {
		return ExternalIdMapper.class;
	}

	@Override
	public String getModelClassName() {
		return ExternalIdMapper.class.getName();
	}

	@Override
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

	@Override
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

	@Override
	public Object clone() {
		return new ExternalIdMapperWrapper(
			(ExternalIdMapper)_externalIdMapper.clone());
	}

	@Override
	public int compareTo(ExternalIdMapper externalIdMapper) {
		return _externalIdMapper.compareTo(externalIdMapper);
	}

	/**
	 * Returns the fully qualified class name of this external ID mapper.
	 *
	 * @return the fully qualified class name of this external ID mapper
	 */
	@Override
	public String getClassName() {
		return _externalIdMapper.getClassName();
	}

	/**
	 * Returns the class name ID of this external ID mapper.
	 *
	 * @return the class name ID of this external ID mapper
	 */
	@Override
	public long getClassNameId() {
		return _externalIdMapper.getClassNameId();
	}

	/**
	 * Returns the class pk of this external ID mapper.
	 *
	 * @return the class pk of this external ID mapper
	 */
	@Override
	public long getClassPK() {
		return _externalIdMapper.getClassPK();
	}

	/**
	 * Returns the create date of this external ID mapper.
	 *
	 * @return the create date of this external ID mapper
	 */
	@Override
	public Date getCreateDate() {
		return _externalIdMapper.getCreateDate();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _externalIdMapper.getExpandoBridge();
	}

	/**
	 * Returns the external ID of this external ID mapper.
	 *
	 * @return the external ID of this external ID mapper
	 */
	@Override
	public String getExternalId() {
		return _externalIdMapper.getExternalId();
	}

	/**
	 * Returns the external ID mapper ID of this external ID mapper.
	 *
	 * @return the external ID mapper ID of this external ID mapper
	 */
	@Override
	public long getExternalIdMapperId() {
		return _externalIdMapper.getExternalIdMapperId();
	}

	/**
	 * Returns the modified date of this external ID mapper.
	 *
	 * @return the modified date of this external ID mapper
	 */
	@Override
	public Date getModifiedDate() {
		return _externalIdMapper.getModifiedDate();
	}

	/**
	 * Returns the primary key of this external ID mapper.
	 *
	 * @return the primary key of this external ID mapper
	 */
	@Override
	public long getPrimaryKey() {
		return _externalIdMapper.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _externalIdMapper.getPrimaryKeyObj();
	}

	/**
	 * Returns the type of this external ID mapper.
	 *
	 * @return the type of this external ID mapper
	 */
	@Override
	public int getType() {
		return _externalIdMapper.getType();
	}

	@Override
	public int hashCode() {
		return _externalIdMapper.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _externalIdMapper.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _externalIdMapper.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _externalIdMapper.isNew();
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a external ID mapper model instance should use the <code>ExternalIdMapper</code> interface instead.
	 */
	@Override
	public void persist() {
		_externalIdMapper.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_externalIdMapper.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_externalIdMapper.setClassName(className);
	}

	/**
	 * Sets the class name ID of this external ID mapper.
	 *
	 * @param classNameId the class name ID of this external ID mapper
	 */
	@Override
	public void setClassNameId(long classNameId) {
		_externalIdMapper.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this external ID mapper.
	 *
	 * @param classPK the class pk of this external ID mapper
	 */
	@Override
	public void setClassPK(long classPK) {
		_externalIdMapper.setClassPK(classPK);
	}

	/**
	 * Sets the create date of this external ID mapper.
	 *
	 * @param createDate the create date of this external ID mapper
	 */
	@Override
	public void setCreateDate(Date createDate) {
		_externalIdMapper.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_externalIdMapper.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_externalIdMapper.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_externalIdMapper.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the external ID of this external ID mapper.
	 *
	 * @param externalId the external ID of this external ID mapper
	 */
	@Override
	public void setExternalId(String externalId) {
		_externalIdMapper.setExternalId(externalId);
	}

	/**
	 * Sets the external ID mapper ID of this external ID mapper.
	 *
	 * @param externalIdMapperId the external ID mapper ID of this external ID mapper
	 */
	@Override
	public void setExternalIdMapperId(long externalIdMapperId) {
		_externalIdMapper.setExternalIdMapperId(externalIdMapperId);
	}

	/**
	 * Sets the modified date of this external ID mapper.
	 *
	 * @param modifiedDate the modified date of this external ID mapper
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_externalIdMapper.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_externalIdMapper.setNew(n);
	}

	/**
	 * Sets the primary key of this external ID mapper.
	 *
	 * @param primaryKey the primary key of this external ID mapper
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_externalIdMapper.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_externalIdMapper.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the type of this external ID mapper.
	 *
	 * @param type the type of this external ID mapper
	 */
	@Override
	public void setType(int type) {
		_externalIdMapper.setType(type);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<ExternalIdMapper>
		toCacheModel() {

		return _externalIdMapper.toCacheModel();
	}

	@Override
	public ExternalIdMapper toEscapedModel() {
		return new ExternalIdMapperWrapper(_externalIdMapper.toEscapedModel());
	}

	@Override
	public String toString() {
		return _externalIdMapper.toString();
	}

	@Override
	public ExternalIdMapper toUnescapedModel() {
		return new ExternalIdMapperWrapper(
			_externalIdMapper.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _externalIdMapper.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ExternalIdMapperWrapper)) {
			return false;
		}

		ExternalIdMapperWrapper externalIdMapperWrapper =
			(ExternalIdMapperWrapper)obj;

		if (Objects.equals(
				_externalIdMapper, externalIdMapperWrapper._externalIdMapper)) {

			return true;
		}

		return false;
	}

	@Override
	public ExternalIdMapper getWrappedModel() {
		return _externalIdMapper;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _externalIdMapper.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _externalIdMapper.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_externalIdMapper.resetOriginalValues();
	}

	private final ExternalIdMapper _externalIdMapper;

}