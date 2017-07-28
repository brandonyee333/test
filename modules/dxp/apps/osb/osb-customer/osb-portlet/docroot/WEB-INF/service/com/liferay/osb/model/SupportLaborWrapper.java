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
 * This class is a wrapper for {@link SupportLabor}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportLabor
 * @generated
 */
@ProviderType
public class SupportLaborWrapper implements SupportLabor,
	ModelWrapper<SupportLabor> {
	public SupportLaborWrapper(SupportLabor supportLabor) {
		_supportLabor = supportLabor;
	}

	@Override
	public Class<?> getModelClass() {
		return SupportLabor.class;
	}

	@Override
	public String getModelClassName() {
		return SupportLabor.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("supportLaborId", getSupportLaborId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("timeZoneId", getTimeZoneId());
		attributes.put("sunOpen", getSunOpen());
		attributes.put("sunClose", getSunClose());
		attributes.put("monOpen", getMonOpen());
		attributes.put("monClose", getMonClose());
		attributes.put("tueOpen", getTueOpen());
		attributes.put("tueClose", getTueClose());
		attributes.put("wedOpen", getWedOpen());
		attributes.put("wedClose", getWedClose());
		attributes.put("thuOpen", getThuOpen());
		attributes.put("thuClose", getThuClose());
		attributes.put("friOpen", getFriOpen());
		attributes.put("friClose", getFriClose());
		attributes.put("satOpen", getSatOpen());
		attributes.put("satClose", getSatClose());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long supportLaborId = (Long)attributes.get("supportLaborId");

		if (supportLaborId != null) {
			setSupportLaborId(supportLaborId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String timeZoneId = (String)attributes.get("timeZoneId");

		if (timeZoneId != null) {
			setTimeZoneId(timeZoneId);
		}

		Integer sunOpen = (Integer)attributes.get("sunOpen");

		if (sunOpen != null) {
			setSunOpen(sunOpen);
		}

		Integer sunClose = (Integer)attributes.get("sunClose");

		if (sunClose != null) {
			setSunClose(sunClose);
		}

		Integer monOpen = (Integer)attributes.get("monOpen");

		if (monOpen != null) {
			setMonOpen(monOpen);
		}

		Integer monClose = (Integer)attributes.get("monClose");

		if (monClose != null) {
			setMonClose(monClose);
		}

		Integer tueOpen = (Integer)attributes.get("tueOpen");

		if (tueOpen != null) {
			setTueOpen(tueOpen);
		}

		Integer tueClose = (Integer)attributes.get("tueClose");

		if (tueClose != null) {
			setTueClose(tueClose);
		}

		Integer wedOpen = (Integer)attributes.get("wedOpen");

		if (wedOpen != null) {
			setWedOpen(wedOpen);
		}

		Integer wedClose = (Integer)attributes.get("wedClose");

		if (wedClose != null) {
			setWedClose(wedClose);
		}

		Integer thuOpen = (Integer)attributes.get("thuOpen");

		if (thuOpen != null) {
			setThuOpen(thuOpen);
		}

		Integer thuClose = (Integer)attributes.get("thuClose");

		if (thuClose != null) {
			setThuClose(thuClose);
		}

		Integer friOpen = (Integer)attributes.get("friOpen");

		if (friOpen != null) {
			setFriOpen(friOpen);
		}

		Integer friClose = (Integer)attributes.get("friClose");

		if (friClose != null) {
			setFriClose(friClose);
		}

		Integer satOpen = (Integer)attributes.get("satOpen");

		if (satOpen != null) {
			setSatOpen(satOpen);
		}

		Integer satClose = (Integer)attributes.get("satClose");

		if (satClose != null) {
			setSatClose(satClose);
		}
	}

	@Override
	public SupportLabor toEscapedModel() {
		return new SupportLaborWrapper(_supportLabor.toEscapedModel());
	}

	@Override
	public SupportLabor toUnescapedModel() {
		return new SupportLaborWrapper(_supportLabor.toUnescapedModel());
	}

	@Override
	public boolean isCachedModel() {
		return _supportLabor.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _supportLabor.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _supportLabor.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _supportLabor.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<SupportLabor> toCacheModel() {
		return _supportLabor.toCacheModel();
	}

	@Override
	public int compareTo(SupportLabor supportLabor) {
		return _supportLabor.compareTo(supportLabor);
	}

	/**
	* Returns the fri close of this support labor.
	*
	* @return the fri close of this support labor
	*/
	@Override
	public int getFriClose() {
		return _supportLabor.getFriClose();
	}

	/**
	* Returns the fri open of this support labor.
	*
	* @return the fri open of this support labor
	*/
	@Override
	public int getFriOpen() {
		return _supportLabor.getFriOpen();
	}

	/**
	* Returns the mon close of this support labor.
	*
	* @return the mon close of this support labor
	*/
	@Override
	public int getMonClose() {
		return _supportLabor.getMonClose();
	}

	/**
	* Returns the mon open of this support labor.
	*
	* @return the mon open of this support labor
	*/
	@Override
	public int getMonOpen() {
		return _supportLabor.getMonOpen();
	}

	/**
	* Returns the sat close of this support labor.
	*
	* @return the sat close of this support labor
	*/
	@Override
	public int getSatClose() {
		return _supportLabor.getSatClose();
	}

	/**
	* Returns the sat open of this support labor.
	*
	* @return the sat open of this support labor
	*/
	@Override
	public int getSatOpen() {
		return _supportLabor.getSatOpen();
	}

	/**
	* Returns the sun close of this support labor.
	*
	* @return the sun close of this support labor
	*/
	@Override
	public int getSunClose() {
		return _supportLabor.getSunClose();
	}

	/**
	* Returns the sun open of this support labor.
	*
	* @return the sun open of this support labor
	*/
	@Override
	public int getSunOpen() {
		return _supportLabor.getSunOpen();
	}

	/**
	* Returns the thu close of this support labor.
	*
	* @return the thu close of this support labor
	*/
	@Override
	public int getThuClose() {
		return _supportLabor.getThuClose();
	}

	/**
	* Returns the thu open of this support labor.
	*
	* @return the thu open of this support labor
	*/
	@Override
	public int getThuOpen() {
		return _supportLabor.getThuOpen();
	}

	@Override
	public int getTime(int day, int type) {
		return _supportLabor.getTime(day, type);
	}

	/**
	* Returns the tue close of this support labor.
	*
	* @return the tue close of this support labor
	*/
	@Override
	public int getTueClose() {
		return _supportLabor.getTueClose();
	}

	/**
	* Returns the tue open of this support labor.
	*
	* @return the tue open of this support labor
	*/
	@Override
	public int getTueOpen() {
		return _supportLabor.getTueOpen();
	}

	/**
	* Returns the wed close of this support labor.
	*
	* @return the wed close of this support labor
	*/
	@Override
	public int getWedClose() {
		return _supportLabor.getWedClose();
	}

	/**
	* Returns the wed open of this support labor.
	*
	* @return the wed open of this support labor
	*/
	@Override
	public int getWedOpen() {
		return _supportLabor.getWedOpen();
	}

	@Override
	public int hashCode() {
		return _supportLabor.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _supportLabor.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new SupportLaborWrapper((SupportLabor)_supportLabor.clone());
	}

	@Override
	public java.lang.String formatDayHours(java.util.Locale locale, int day) {
		return _supportLabor.formatDayHours(locale, day);
	}

	@Override
	public java.lang.String formatTime(java.util.Locale locale, int day,
		int type) {
		return _supportLabor.formatTime(locale, day, type);
	}

	/**
	* Returns the description of this support labor.
	*
	* @return the description of this support labor
	*/
	@Override
	public java.lang.String getDescription() {
		return _supportLabor.getDescription();
	}

	/**
	* Returns the name of this support labor.
	*
	* @return the name of this support labor
	*/
	@Override
	public java.lang.String getName() {
		return _supportLabor.getName();
	}

	/**
	* Returns the time zone ID of this support labor.
	*
	* @return the time zone ID of this support labor
	*/
	@Override
	public java.lang.String getTimeZoneId() {
		return _supportLabor.getTimeZoneId();
	}

	@Override
	public java.lang.String toString() {
		return _supportLabor.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _supportLabor.toXmlString();
	}

	@Override
	public java.util.List<SupportTeam> getSupportTeams()
		throws com.liferay.portal.kernel.exception.PortalException {
		return _supportLabor.getSupportTeams();
	}

	/**
	* Returns the primary key of this support labor.
	*
	* @return the primary key of this support labor
	*/
	@Override
	public long getPrimaryKey() {
		return _supportLabor.getPrimaryKey();
	}

	/**
	* Returns the support labor ID of this support labor.
	*
	* @return the support labor ID of this support labor
	*/
	@Override
	public long getSupportLaborId() {
		return _supportLabor.getSupportLaborId();
	}

	@Override
	public void persist() {
		_supportLabor.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_supportLabor.setCachedModel(cachedModel);
	}

	/**
	* Sets the description of this support labor.
	*
	* @param description the description of this support labor
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_supportLabor.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_supportLabor.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_supportLabor.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_supportLabor.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the fri close of this support labor.
	*
	* @param friClose the fri close of this support labor
	*/
	@Override
	public void setFriClose(int friClose) {
		_supportLabor.setFriClose(friClose);
	}

	/**
	* Sets the fri open of this support labor.
	*
	* @param friOpen the fri open of this support labor
	*/
	@Override
	public void setFriOpen(int friOpen) {
		_supportLabor.setFriOpen(friOpen);
	}

	/**
	* Sets the mon close of this support labor.
	*
	* @param monClose the mon close of this support labor
	*/
	@Override
	public void setMonClose(int monClose) {
		_supportLabor.setMonClose(monClose);
	}

	/**
	* Sets the mon open of this support labor.
	*
	* @param monOpen the mon open of this support labor
	*/
	@Override
	public void setMonOpen(int monOpen) {
		_supportLabor.setMonOpen(monOpen);
	}

	/**
	* Sets the name of this support labor.
	*
	* @param name the name of this support labor
	*/
	@Override
	public void setName(java.lang.String name) {
		_supportLabor.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_supportLabor.setNew(n);
	}

	/**
	* Sets the primary key of this support labor.
	*
	* @param primaryKey the primary key of this support labor
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_supportLabor.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_supportLabor.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the sat close of this support labor.
	*
	* @param satClose the sat close of this support labor
	*/
	@Override
	public void setSatClose(int satClose) {
		_supportLabor.setSatClose(satClose);
	}

	/**
	* Sets the sat open of this support labor.
	*
	* @param satOpen the sat open of this support labor
	*/
	@Override
	public void setSatOpen(int satOpen) {
		_supportLabor.setSatOpen(satOpen);
	}

	/**
	* Sets the sun close of this support labor.
	*
	* @param sunClose the sun close of this support labor
	*/
	@Override
	public void setSunClose(int sunClose) {
		_supportLabor.setSunClose(sunClose);
	}

	/**
	* Sets the sun open of this support labor.
	*
	* @param sunOpen the sun open of this support labor
	*/
	@Override
	public void setSunOpen(int sunOpen) {
		_supportLabor.setSunOpen(sunOpen);
	}

	/**
	* Sets the support labor ID of this support labor.
	*
	* @param supportLaborId the support labor ID of this support labor
	*/
	@Override
	public void setSupportLaborId(long supportLaborId) {
		_supportLabor.setSupportLaborId(supportLaborId);
	}

	/**
	* Sets the thu close of this support labor.
	*
	* @param thuClose the thu close of this support labor
	*/
	@Override
	public void setThuClose(int thuClose) {
		_supportLabor.setThuClose(thuClose);
	}

	/**
	* Sets the thu open of this support labor.
	*
	* @param thuOpen the thu open of this support labor
	*/
	@Override
	public void setThuOpen(int thuOpen) {
		_supportLabor.setThuOpen(thuOpen);
	}

	/**
	* Sets the time zone ID of this support labor.
	*
	* @param timeZoneId the time zone ID of this support labor
	*/
	@Override
	public void setTimeZoneId(java.lang.String timeZoneId) {
		_supportLabor.setTimeZoneId(timeZoneId);
	}

	/**
	* Sets the tue close of this support labor.
	*
	* @param tueClose the tue close of this support labor
	*/
	@Override
	public void setTueClose(int tueClose) {
		_supportLabor.setTueClose(tueClose);
	}

	/**
	* Sets the tue open of this support labor.
	*
	* @param tueOpen the tue open of this support labor
	*/
	@Override
	public void setTueOpen(int tueOpen) {
		_supportLabor.setTueOpen(tueOpen);
	}

	/**
	* Sets the wed close of this support labor.
	*
	* @param wedClose the wed close of this support labor
	*/
	@Override
	public void setWedClose(int wedClose) {
		_supportLabor.setWedClose(wedClose);
	}

	/**
	* Sets the wed open of this support labor.
	*
	* @param wedOpen the wed open of this support labor
	*/
	@Override
	public void setWedOpen(int wedOpen) {
		_supportLabor.setWedOpen(wedOpen);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SupportLaborWrapper)) {
			return false;
		}

		SupportLaborWrapper supportLaborWrapper = (SupportLaborWrapper)obj;

		if (Objects.equals(_supportLabor, supportLaborWrapper._supportLabor)) {
			return true;
		}

		return false;
	}

	@Override
	public SupportLabor getWrappedModel() {
		return _supportLabor;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _supportLabor.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _supportLabor.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_supportLabor.resetOriginalValues();
	}

	private final SupportLabor _supportLabor;
}