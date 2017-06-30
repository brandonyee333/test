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
 * This class is a wrapper for {@link TrainingImportLog}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       TrainingImportLog
 * @generated
 */
public class TrainingImportLogWrapper implements TrainingImportLog,
	ModelWrapper<TrainingImportLog> {
	public TrainingImportLogWrapper(TrainingImportLog trainingImportLog) {
		_trainingImportLog = trainingImportLog;
	}

	public Class<?> getModelClass() {
		return TrainingImportLog.class;
	}

	public String getModelClassName() {
		return TrainingImportLog.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("trainingImportLogId", getTrainingImportLogId());
		attributes.put("type", getType());
		attributes.put("importDate", getImportDate());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long trainingImportLogId = (Long)attributes.get("trainingImportLogId");

		if (trainingImportLogId != null) {
			setTrainingImportLogId(trainingImportLogId);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Date importDate = (Date)attributes.get("importDate");

		if (importDate != null) {
			setImportDate(importDate);
		}
	}

	/**
	* Returns the primary key of this training import log.
	*
	* @return the primary key of this training import log
	*/
	public long getPrimaryKey() {
		return _trainingImportLog.getPrimaryKey();
	}

	/**
	* Sets the primary key of this training import log.
	*
	* @param primaryKey the primary key of this training import log
	*/
	public void setPrimaryKey(long primaryKey) {
		_trainingImportLog.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the training import log ID of this training import log.
	*
	* @return the training import log ID of this training import log
	*/
	public long getTrainingImportLogId() {
		return _trainingImportLog.getTrainingImportLogId();
	}

	/**
	* Sets the training import log ID of this training import log.
	*
	* @param trainingImportLogId the training import log ID of this training import log
	*/
	public void setTrainingImportLogId(long trainingImportLogId) {
		_trainingImportLog.setTrainingImportLogId(trainingImportLogId);
	}

	/**
	* Returns the type of this training import log.
	*
	* @return the type of this training import log
	*/
	public int getType() {
		return _trainingImportLog.getType();
	}

	/**
	* Sets the type of this training import log.
	*
	* @param type the type of this training import log
	*/
	public void setType(int type) {
		_trainingImportLog.setType(type);
	}

	/**
	* Returns the import date of this training import log.
	*
	* @return the import date of this training import log
	*/
	public java.util.Date getImportDate() {
		return _trainingImportLog.getImportDate();
	}

	/**
	* Sets the import date of this training import log.
	*
	* @param importDate the import date of this training import log
	*/
	public void setImportDate(java.util.Date importDate) {
		_trainingImportLog.setImportDate(importDate);
	}

	public boolean isNew() {
		return _trainingImportLog.isNew();
	}

	public void setNew(boolean n) {
		_trainingImportLog.setNew(n);
	}

	public boolean isCachedModel() {
		return _trainingImportLog.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_trainingImportLog.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _trainingImportLog.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _trainingImportLog.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_trainingImportLog.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _trainingImportLog.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_trainingImportLog.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new TrainingImportLogWrapper((TrainingImportLog)_trainingImportLog.clone());
	}

	public int compareTo(
		com.liferay.osb.model.TrainingImportLog trainingImportLog) {
		return _trainingImportLog.compareTo(trainingImportLog);
	}

	@Override
	public int hashCode() {
		return _trainingImportLog.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.TrainingImportLog> toCacheModel() {
		return _trainingImportLog.toCacheModel();
	}

	public com.liferay.osb.model.TrainingImportLog toEscapedModel() {
		return new TrainingImportLogWrapper(_trainingImportLog.toEscapedModel());
	}

	public com.liferay.osb.model.TrainingImportLog toUnescapedModel() {
		return new TrainingImportLogWrapper(_trainingImportLog.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _trainingImportLog.toString();
	}

	public java.lang.String toXmlString() {
		return _trainingImportLog.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_trainingImportLog.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TrainingImportLogWrapper)) {
			return false;
		}

		TrainingImportLogWrapper trainingImportLogWrapper = (TrainingImportLogWrapper)obj;

		if (Validator.equals(_trainingImportLog,
					trainingImportLogWrapper._trainingImportLog)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public TrainingImportLog getWrappedTrainingImportLog() {
		return _trainingImportLog;
	}

	public TrainingImportLog getWrappedModel() {
		return _trainingImportLog;
	}

	public void resetOriginalValues() {
		_trainingImportLog.resetOriginalValues();
	}

	private TrainingImportLog _trainingImportLog;
}