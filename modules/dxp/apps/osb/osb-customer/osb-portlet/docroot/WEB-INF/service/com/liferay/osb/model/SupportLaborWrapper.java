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
 * This class is a wrapper for {@link SupportLabor}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SupportLabor
 * @generated
 */
public class SupportLaborWrapper implements SupportLabor,
	ModelWrapper<SupportLabor> {
	public SupportLaborWrapper(SupportLabor supportLabor) {
		_supportLabor = supportLabor;
	}

	public Class<?> getModelClass() {
		return SupportLabor.class;
	}

	public String getModelClassName() {
		return SupportLabor.class.getName();
	}

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

	/**
	* Returns the primary key of this support labor.
	*
	* @return the primary key of this support labor
	*/
	public long getPrimaryKey() {
		return _supportLabor.getPrimaryKey();
	}

	/**
	* Sets the primary key of this support labor.
	*
	* @param primaryKey the primary key of this support labor
	*/
	public void setPrimaryKey(long primaryKey) {
		_supportLabor.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the support labor ID of this support labor.
	*
	* @return the support labor ID of this support labor
	*/
	public long getSupportLaborId() {
		return _supportLabor.getSupportLaborId();
	}

	/**
	* Sets the support labor ID of this support labor.
	*
	* @param supportLaborId the support labor ID of this support labor
	*/
	public void setSupportLaborId(long supportLaborId) {
		_supportLabor.setSupportLaborId(supportLaborId);
	}

	/**
	* Returns the name of this support labor.
	*
	* @return the name of this support labor
	*/
	public java.lang.String getName() {
		return _supportLabor.getName();
	}

	/**
	* Sets the name of this support labor.
	*
	* @param name the name of this support labor
	*/
	public void setName(java.lang.String name) {
		_supportLabor.setName(name);
	}

	/**
	* Returns the description of this support labor.
	*
	* @return the description of this support labor
	*/
	public java.lang.String getDescription() {
		return _supportLabor.getDescription();
	}

	/**
	* Sets the description of this support labor.
	*
	* @param description the description of this support labor
	*/
	public void setDescription(java.lang.String description) {
		_supportLabor.setDescription(description);
	}

	/**
	* Returns the time zone ID of this support labor.
	*
	* @return the time zone ID of this support labor
	*/
	public java.lang.String getTimeZoneId() {
		return _supportLabor.getTimeZoneId();
	}

	/**
	* Sets the time zone ID of this support labor.
	*
	* @param timeZoneId the time zone ID of this support labor
	*/
	public void setTimeZoneId(java.lang.String timeZoneId) {
		_supportLabor.setTimeZoneId(timeZoneId);
	}

	/**
	* Returns the sun open of this support labor.
	*
	* @return the sun open of this support labor
	*/
	public int getSunOpen() {
		return _supportLabor.getSunOpen();
	}

	/**
	* Sets the sun open of this support labor.
	*
	* @param sunOpen the sun open of this support labor
	*/
	public void setSunOpen(int sunOpen) {
		_supportLabor.setSunOpen(sunOpen);
	}

	/**
	* Returns the sun close of this support labor.
	*
	* @return the sun close of this support labor
	*/
	public int getSunClose() {
		return _supportLabor.getSunClose();
	}

	/**
	* Sets the sun close of this support labor.
	*
	* @param sunClose the sun close of this support labor
	*/
	public void setSunClose(int sunClose) {
		_supportLabor.setSunClose(sunClose);
	}

	/**
	* Returns the mon open of this support labor.
	*
	* @return the mon open of this support labor
	*/
	public int getMonOpen() {
		return _supportLabor.getMonOpen();
	}

	/**
	* Sets the mon open of this support labor.
	*
	* @param monOpen the mon open of this support labor
	*/
	public void setMonOpen(int monOpen) {
		_supportLabor.setMonOpen(monOpen);
	}

	/**
	* Returns the mon close of this support labor.
	*
	* @return the mon close of this support labor
	*/
	public int getMonClose() {
		return _supportLabor.getMonClose();
	}

	/**
	* Sets the mon close of this support labor.
	*
	* @param monClose the mon close of this support labor
	*/
	public void setMonClose(int monClose) {
		_supportLabor.setMonClose(monClose);
	}

	/**
	* Returns the tue open of this support labor.
	*
	* @return the tue open of this support labor
	*/
	public int getTueOpen() {
		return _supportLabor.getTueOpen();
	}

	/**
	* Sets the tue open of this support labor.
	*
	* @param tueOpen the tue open of this support labor
	*/
	public void setTueOpen(int tueOpen) {
		_supportLabor.setTueOpen(tueOpen);
	}

	/**
	* Returns the tue close of this support labor.
	*
	* @return the tue close of this support labor
	*/
	public int getTueClose() {
		return _supportLabor.getTueClose();
	}

	/**
	* Sets the tue close of this support labor.
	*
	* @param tueClose the tue close of this support labor
	*/
	public void setTueClose(int tueClose) {
		_supportLabor.setTueClose(tueClose);
	}

	/**
	* Returns the wed open of this support labor.
	*
	* @return the wed open of this support labor
	*/
	public int getWedOpen() {
		return _supportLabor.getWedOpen();
	}

	/**
	* Sets the wed open of this support labor.
	*
	* @param wedOpen the wed open of this support labor
	*/
	public void setWedOpen(int wedOpen) {
		_supportLabor.setWedOpen(wedOpen);
	}

	/**
	* Returns the wed close of this support labor.
	*
	* @return the wed close of this support labor
	*/
	public int getWedClose() {
		return _supportLabor.getWedClose();
	}

	/**
	* Sets the wed close of this support labor.
	*
	* @param wedClose the wed close of this support labor
	*/
	public void setWedClose(int wedClose) {
		_supportLabor.setWedClose(wedClose);
	}

	/**
	* Returns the thu open of this support labor.
	*
	* @return the thu open of this support labor
	*/
	public int getThuOpen() {
		return _supportLabor.getThuOpen();
	}

	/**
	* Sets the thu open of this support labor.
	*
	* @param thuOpen the thu open of this support labor
	*/
	public void setThuOpen(int thuOpen) {
		_supportLabor.setThuOpen(thuOpen);
	}

	/**
	* Returns the thu close of this support labor.
	*
	* @return the thu close of this support labor
	*/
	public int getThuClose() {
		return _supportLabor.getThuClose();
	}

	/**
	* Sets the thu close of this support labor.
	*
	* @param thuClose the thu close of this support labor
	*/
	public void setThuClose(int thuClose) {
		_supportLabor.setThuClose(thuClose);
	}

	/**
	* Returns the fri open of this support labor.
	*
	* @return the fri open of this support labor
	*/
	public int getFriOpen() {
		return _supportLabor.getFriOpen();
	}

	/**
	* Sets the fri open of this support labor.
	*
	* @param friOpen the fri open of this support labor
	*/
	public void setFriOpen(int friOpen) {
		_supportLabor.setFriOpen(friOpen);
	}

	/**
	* Returns the fri close of this support labor.
	*
	* @return the fri close of this support labor
	*/
	public int getFriClose() {
		return _supportLabor.getFriClose();
	}

	/**
	* Sets the fri close of this support labor.
	*
	* @param friClose the fri close of this support labor
	*/
	public void setFriClose(int friClose) {
		_supportLabor.setFriClose(friClose);
	}

	/**
	* Returns the sat open of this support labor.
	*
	* @return the sat open of this support labor
	*/
	public int getSatOpen() {
		return _supportLabor.getSatOpen();
	}

	/**
	* Sets the sat open of this support labor.
	*
	* @param satOpen the sat open of this support labor
	*/
	public void setSatOpen(int satOpen) {
		_supportLabor.setSatOpen(satOpen);
	}

	/**
	* Returns the sat close of this support labor.
	*
	* @return the sat close of this support labor
	*/
	public int getSatClose() {
		return _supportLabor.getSatClose();
	}

	/**
	* Sets the sat close of this support labor.
	*
	* @param satClose the sat close of this support labor
	*/
	public void setSatClose(int satClose) {
		_supportLabor.setSatClose(satClose);
	}

	public boolean isNew() {
		return _supportLabor.isNew();
	}

	public void setNew(boolean n) {
		_supportLabor.setNew(n);
	}

	public boolean isCachedModel() {
		return _supportLabor.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_supportLabor.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _supportLabor.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _supportLabor.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_supportLabor.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _supportLabor.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_supportLabor.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SupportLaborWrapper((SupportLabor)_supportLabor.clone());
	}

	public int compareTo(com.liferay.osb.model.SupportLabor supportLabor) {
		return _supportLabor.compareTo(supportLabor);
	}

	@Override
	public int hashCode() {
		return _supportLabor.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.SupportLabor> toCacheModel() {
		return _supportLabor.toCacheModel();
	}

	public com.liferay.osb.model.SupportLabor toEscapedModel() {
		return new SupportLaborWrapper(_supportLabor.toEscapedModel());
	}

	public com.liferay.osb.model.SupportLabor toUnescapedModel() {
		return new SupportLaborWrapper(_supportLabor.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _supportLabor.toString();
	}

	public java.lang.String toXmlString() {
		return _supportLabor.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_supportLabor.persist();
	}

	public java.lang.String formatDayHours(java.util.Locale locale, int day) {
		return _supportLabor.formatDayHours(locale, day);
	}

	public java.lang.String formatTime(java.util.Locale locale, int day,
		int type) {
		return _supportLabor.formatTime(locale, day, type);
	}

	public java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _supportLabor.getSupportTeams();
	}

	public int getTime(int day, int type) {
		return _supportLabor.getTime(day, type);
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

		if (Validator.equals(_supportLabor, supportLaborWrapper._supportLabor)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public SupportLabor getWrappedSupportLabor() {
		return _supportLabor;
	}

	public SupportLabor getWrappedModel() {
		return _supportLabor;
	}

	public void resetOriginalValues() {
		_supportLabor.resetOriginalValues();
	}

	private SupportLabor _supportLabor;
}