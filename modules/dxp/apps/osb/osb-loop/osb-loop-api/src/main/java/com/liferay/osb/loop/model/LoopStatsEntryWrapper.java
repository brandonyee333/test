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
 * This class is a wrapper for {@link LoopStatsEntry}.
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopStatsEntry
 * @generated
 */
public class LoopStatsEntryWrapper
	implements LoopStatsEntry, ModelWrapper<LoopStatsEntry> {

	public LoopStatsEntryWrapper(LoopStatsEntry loopStatsEntry) {
		_loopStatsEntry = loopStatsEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return LoopStatsEntry.class;
	}

	@Override
	public String getModelClassName() {
		return LoopStatsEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("loopStatsEntryId", getLoopStatsEntryId());
		attributes.put("modifiedTime", getModifiedTime());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("score", getScore());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long loopStatsEntryId = (Long)attributes.get("loopStatsEntryId");

		if (loopStatsEntryId != null) {
			setLoopStatsEntryId(loopStatsEntryId);
		}

		Long modifiedTime = (Long)attributes.get("modifiedTime");

		if (modifiedTime != null) {
			setModifiedTime(modifiedTime);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Double score = (Double)attributes.get("score");

		if (score != null) {
			setScore(score);
		}
	}

	@Override
	public Object clone() {
		return new LoopStatsEntryWrapper(
			(LoopStatsEntry)_loopStatsEntry.clone());
	}

	@Override
	public int compareTo(LoopStatsEntry loopStatsEntry) {
		return _loopStatsEntry.compareTo(loopStatsEntry);
	}

	/**
	 * Returns the fully qualified class name of this loop stats entry.
	 *
	 * @return the fully qualified class name of this loop stats entry
	 */
	@Override
	public String getClassName() {
		return _loopStatsEntry.getClassName();
	}

	/**
	 * Returns the class name ID of this loop stats entry.
	 *
	 * @return the class name ID of this loop stats entry
	 */
	@Override
	public long getClassNameId() {
		return _loopStatsEntry.getClassNameId();
	}

	/**
	 * Returns the class pk of this loop stats entry.
	 *
	 * @return the class pk of this loop stats entry
	 */
	@Override
	public long getClassPK() {
		return _loopStatsEntry.getClassPK();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _loopStatsEntry.getExpandoBridge();
	}

	/**
	 * Returns the loop stats entry ID of this loop stats entry.
	 *
	 * @return the loop stats entry ID of this loop stats entry
	 */
	@Override
	public long getLoopStatsEntryId() {
		return _loopStatsEntry.getLoopStatsEntryId();
	}

	/**
	 * Returns the modified time of this loop stats entry.
	 *
	 * @return the modified time of this loop stats entry
	 */
	@Override
	public long getModifiedTime() {
		return _loopStatsEntry.getModifiedTime();
	}

	/**
	 * Returns the primary key of this loop stats entry.
	 *
	 * @return the primary key of this loop stats entry
	 */
	@Override
	public long getPrimaryKey() {
		return _loopStatsEntry.getPrimaryKey();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _loopStatsEntry.getPrimaryKeyObj();
	}

	/**
	 * Returns the score of this loop stats entry.
	 *
	 * @return the score of this loop stats entry
	 */
	@Override
	public double getScore() {
		return _loopStatsEntry.getScore();
	}

	@Override
	public int hashCode() {
		return _loopStatsEntry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _loopStatsEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _loopStatsEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _loopStatsEntry.isNew();
	}

	@Override
	public void persist() {
		_loopStatsEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_loopStatsEntry.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(String className) {
		_loopStatsEntry.setClassName(className);
	}

	/**
	 * Sets the class name ID of this loop stats entry.
	 *
	 * @param classNameId the class name ID of this loop stats entry
	 */
	@Override
	public void setClassNameId(long classNameId) {
		_loopStatsEntry.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this loop stats entry.
	 *
	 * @param classPK the class pk of this loop stats entry
	 */
	@Override
	public void setClassPK(long classPK) {
		_loopStatsEntry.setClassPK(classPK);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {

		_loopStatsEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_loopStatsEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_loopStatsEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	 * Sets the loop stats entry ID of this loop stats entry.
	 *
	 * @param loopStatsEntryId the loop stats entry ID of this loop stats entry
	 */
	@Override
	public void setLoopStatsEntryId(long loopStatsEntryId) {
		_loopStatsEntry.setLoopStatsEntryId(loopStatsEntryId);
	}

	/**
	 * Sets the modified time of this loop stats entry.
	 *
	 * @param modifiedTime the modified time of this loop stats entry
	 */
	@Override
	public void setModifiedTime(long modifiedTime) {
		_loopStatsEntry.setModifiedTime(modifiedTime);
	}

	@Override
	public void setNew(boolean n) {
		_loopStatsEntry.setNew(n);
	}

	/**
	 * Sets the primary key of this loop stats entry.
	 *
	 * @param primaryKey the primary key of this loop stats entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		_loopStatsEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_loopStatsEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	 * Sets the score of this loop stats entry.
	 *
	 * @param score the score of this loop stats entry
	 */
	@Override
	public void setScore(double score) {
		_loopStatsEntry.setScore(score);
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<LoopStatsEntry>
		toCacheModel() {

		return _loopStatsEntry.toCacheModel();
	}

	@Override
	public LoopStatsEntry toEscapedModel() {
		return new LoopStatsEntryWrapper(_loopStatsEntry.toEscapedModel());
	}

	@Override
	public String toString() {
		return _loopStatsEntry.toString();
	}

	@Override
	public LoopStatsEntry toUnescapedModel() {
		return new LoopStatsEntryWrapper(_loopStatsEntry.toUnescapedModel());
	}

	@Override
	public String toXmlString() {
		return _loopStatsEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LoopStatsEntryWrapper)) {
			return false;
		}

		LoopStatsEntryWrapper loopStatsEntryWrapper =
			(LoopStatsEntryWrapper)obj;

		if (Objects.equals(
				_loopStatsEntry, loopStatsEntryWrapper._loopStatsEntry)) {

			return true;
		}

		return false;
	}

	@Override
	public LoopStatsEntry getWrappedModel() {
		return _loopStatsEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _loopStatsEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _loopStatsEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_loopStatsEntry.resetOriginalValues();
	}

	private final LoopStatsEntry _loopStatsEntry;

}