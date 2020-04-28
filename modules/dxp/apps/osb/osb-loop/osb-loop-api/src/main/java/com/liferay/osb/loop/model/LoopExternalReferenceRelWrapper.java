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
 * This class is a wrapper for {@link LoopExternalReferenceRel}.
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopExternalReferenceRel
 * @generated
 */
public class LoopExternalReferenceRelWrapper
	implements LoopExternalReferenceRel,
			   ModelWrapper<LoopExternalReferenceRel> {

	public LoopExternalReferenceRelWrapper(
		LoopExternalReferenceRel loopExternalReferenceRel) {

		_loopExternalReferenceRel = loopExternalReferenceRel;
	}

	@Override
	public Class<?> getModelClass() {
		return LoopExternalReferenceRel.class;
	}

	@Override
	public String getModelClassName() {
		return LoopExternalReferenceRel.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put(
			"loopExternalReferenceRelId", getLoopExternalReferenceRelId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("externalReferenceName", getExternalReferenceName());
		attributes.put("externalReferencePK", getExternalReferencePK());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long loopExternalReferenceRelId = (Long)attributes.get(
			"loopExternalReferenceRelId");

		if (loopExternalReferenceRelId != null) {
			setLoopExternalReferenceRelId(loopExternalReferenceRelId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String externalReferenceName = (String)attributes.get(
			"externalReferenceName");

		if (externalReferenceName != null) {
			setExternalReferenceName(externalReferenceName);
		}

		String externalReferencePK = (String)attributes.get(
			"externalReferencePK");

		if (externalReferencePK != null) {
			setExternalReferencePK(externalReferencePK);
		}
	}

	@Override
	public Object clone() {
		return new LoopExternalReferenceRelWrapper(
			(LoopExternalReferenceRel)_loopExternalReferenceRel.clone());
	}

	@Override
	public int compareTo(LoopExternalReferenceRel loopExternalReferenceRel) {
		return _loopExternalReferenceRel.compareTo(loopExternalReferenceRel);
	}

	/**
	 * Returns the fully qualified class name of this loop external reference rel.
	 *
	 * @return the fully qualified class name of this loop external reference rel
	 */
	@Override
	public String getClassName() {
		return _loopExternalReferenceRel.getClassName();
	}

	/**
	 * Returns the class name ID of this loop external reference rel.
	 *
	 * @return the class name ID of this loop external reference rel
	 */
	@Override
	public long getClassNameId() {
		return _loopExternalReferenceRel.getClassNameId();
	}

	/**
	 * Returns the class pk of this loop external reference rel.
	 *
	 * @return the class pk of this loop external reference rel
	 */
	@Override
	public long getClassPK() {
		return _loopExternalReferenceRel.getClassPK();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _loopExternalReferenceRel.getExpandoBridge();
	}

	/**
	 * Returns the external reference name of this loop external reference rel.
	 *
	 * @return the external reference name of this loop external reference rel
	 */
	@Override
	public String getExternalReferenceName() {
		return _loopExternalReferenceRel.getExternalReferenceName();
	}

	/**
	 * Returns the external reference pk of this loop external reference rel.
	 *
	 * @return the external reference pk of this loop external reference rel
	 */
	@Override
	public String getExternalReferencePK() {
		return _loopExternalReferenceRel.getExternalReferencePK();
	}

	/**
	 * Returns the loop external reference rel ID of this loop external reference rel.
	 *
	 * @return the loop external reference rel ID of this loop external reference rel
	 */
	@Override
	public long getLoopExternalReferenceRelId() {
		return _loopExternalReferenceRel.getLoopExternalReferenceRelId();
	}

	/**
	 * Returns the primary key of this loop external reference rel.
	 *
	 * @return the primary key of this loop external reference rel
	 */
	@Override
	public long getPrimaryKey() {
		return _loopExternalReferenceRel.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _loopExternalReferenceRel.getPrimaryKeyObj();
	}

	@Override
	public int hashCode() {
		return _loopExternalReferenceRel.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _loopExternalReferenceRel.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _loopExternalReferenceRel.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _loopExternalReferenceRel.isNew();
	}

	@Override
	public void persist() {
		_loopExternalReferenceRel.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_loopExternalReferenceRel.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_loopExternalReferenceRel.setClassName(className);
	}

	/**
	 * Sets the class name ID of this loop external reference rel.
	 *
	 * @param classNameId the class name ID of this loop external reference rel
	 */
	@Override
	public void setClassNameId(long classNameId) {
		_loopExternalReferenceRel.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this loop external reference rel.
	 *
	 * @param classPK the class pk of this loop external reference rel
	 */
	@Override
	public void setClassPK(long classPK) {
		_loopExternalReferenceRel.setClassPK(classPK);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_loopExternalReferenceRel.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_loopExternalReferenceRel.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_loopExternalReferenceRel.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the external reference name of this loop external reference rel.
	 *
	 * @param externalReferenceName the external reference name of this loop external reference rel
	 */
	@Override
	public void setExternalReferenceName(String externalReferenceName) {
		_loopExternalReferenceRel.setExternalReferenceName(
			externalReferenceName);
	}

	/**
	 * Sets the external reference pk of this loop external reference rel.
	 *
	 * @param externalReferencePK the external reference pk of this loop external reference rel
	 */
	@Override
	public void setExternalReferencePK(String externalReferencePK) {
		_loopExternalReferenceRel.setExternalReferencePK(externalReferencePK);
	}

	/**
	 * Sets the loop external reference rel ID of this loop external reference rel.
	 *
	 * @param loopExternalReferenceRelId the loop external reference rel ID of this loop external reference rel
	 */
	@Override
	public void setLoopExternalReferenceRelId(long loopExternalReferenceRelId) {
		_loopExternalReferenceRel.setLoopExternalReferenceRelId(
			loopExternalReferenceRelId);
	}

	@Override
	public void setNew(boolean n) {
		_loopExternalReferenceRel.setNew(n);
	}

	/**
	 * Sets the primary key of this loop external reference rel.
	 *
	 * @param primaryKey the primary key of this loop external reference rel
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_loopExternalReferenceRel.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_loopExternalReferenceRel.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LoopExternalReferenceRel>
		toCacheModel() {

		return _loopExternalReferenceRel.toCacheModel();
	}

	@Override
	public LoopExternalReferenceRel toEscapedModel() {
		return new LoopExternalReferenceRelWrapper(
			_loopExternalReferenceRel.toEscapedModel());
	}

	@Override
	public String toString() {
		return _loopExternalReferenceRel.toString();
	}

	@Override
	public LoopExternalReferenceRel toUnescapedModel() {
		return new LoopExternalReferenceRelWrapper(
			_loopExternalReferenceRel.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _loopExternalReferenceRel.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopExternalReferenceRelWrapper)) {
			return false;
		}

		LoopExternalReferenceRelWrapper loopExternalReferenceRelWrapper =
			(LoopExternalReferenceRelWrapper)obj;

		if (Objects.equals(
				_loopExternalReferenceRel,
				loopExternalReferenceRelWrapper._loopExternalReferenceRel)) {

			return true;
		}

		return false;
	}

	@Override
	public LoopExternalReferenceRel getWrappedModel() {
		return _loopExternalReferenceRel;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _loopExternalReferenceRel.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _loopExternalReferenceRel.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_loopExternalReferenceRel.resetOriginalValues();
	}

	private final LoopExternalReferenceRel _loopExternalReferenceRel;

}