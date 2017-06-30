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
 * This class is a wrapper for {@link AssetStatsDay}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetStatsDay
 * @generated
 */
public class AssetStatsDayWrapper implements AssetStatsDay,
	ModelWrapper<AssetStatsDay> {
	public AssetStatsDayWrapper(AssetStatsDay assetStatsDay) {
		_assetStatsDay = assetStatsDay;
	}

	public Class<?> getModelClass() {
		return AssetStatsDay.class;
	}

	public String getModelClassName() {
		return AssetStatsDay.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetStatsDayId", getAssetStatsDayId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("day", getDay());
		attributes.put("year", getYear());
		attributes.put("viewCount", getViewCount());
		attributes.put("downloadCount", getDownloadCount());
		attributes.put("purchaseCount", getPurchaseCount());
		attributes.put("currencyCodeRevenues", getCurrencyCodeRevenues());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetStatsDayId = (Long)attributes.get("assetStatsDayId");

		if (assetStatsDayId != null) {
			setAssetStatsDayId(assetStatsDayId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer day = (Integer)attributes.get("day");

		if (day != null) {
			setDay(day);
		}

		Integer year = (Integer)attributes.get("year");

		if (year != null) {
			setYear(year);
		}

		Long viewCount = (Long)attributes.get("viewCount");

		if (viewCount != null) {
			setViewCount(viewCount);
		}

		Long downloadCount = (Long)attributes.get("downloadCount");

		if (downloadCount != null) {
			setDownloadCount(downloadCount);
		}

		Long purchaseCount = (Long)attributes.get("purchaseCount");

		if (purchaseCount != null) {
			setPurchaseCount(purchaseCount);
		}

		String currencyCodeRevenues = (String)attributes.get(
				"currencyCodeRevenues");

		if (currencyCodeRevenues != null) {
			setCurrencyCodeRevenues(currencyCodeRevenues);
		}
	}

	/**
	* Returns the primary key of this asset stats day.
	*
	* @return the primary key of this asset stats day
	*/
	public long getPrimaryKey() {
		return _assetStatsDay.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset stats day.
	*
	* @param primaryKey the primary key of this asset stats day
	*/
	public void setPrimaryKey(long primaryKey) {
		_assetStatsDay.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the asset stats day ID of this asset stats day.
	*
	* @return the asset stats day ID of this asset stats day
	*/
	public long getAssetStatsDayId() {
		return _assetStatsDay.getAssetStatsDayId();
	}

	/**
	* Sets the asset stats day ID of this asset stats day.
	*
	* @param assetStatsDayId the asset stats day ID of this asset stats day
	*/
	public void setAssetStatsDayId(long assetStatsDayId) {
		_assetStatsDay.setAssetStatsDayId(assetStatsDayId);
	}

	/**
	* Returns the fully qualified class name of this asset stats day.
	*
	* @return the fully qualified class name of this asset stats day
	*/
	public java.lang.String getClassName() {
		return _assetStatsDay.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_assetStatsDay.setClassName(className);
	}

	/**
	* Returns the class name ID of this asset stats day.
	*
	* @return the class name ID of this asset stats day
	*/
	public long getClassNameId() {
		return _assetStatsDay.getClassNameId();
	}

	/**
	* Sets the class name ID of this asset stats day.
	*
	* @param classNameId the class name ID of this asset stats day
	*/
	public void setClassNameId(long classNameId) {
		_assetStatsDay.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this asset stats day.
	*
	* @return the class p k of this asset stats day
	*/
	public long getClassPK() {
		return _assetStatsDay.getClassPK();
	}

	/**
	* Sets the class p k of this asset stats day.
	*
	* @param classPK the class p k of this asset stats day
	*/
	public void setClassPK(long classPK) {
		_assetStatsDay.setClassPK(classPK);
	}

	/**
	* Returns the day of this asset stats day.
	*
	* @return the day of this asset stats day
	*/
	public int getDay() {
		return _assetStatsDay.getDay();
	}

	/**
	* Sets the day of this asset stats day.
	*
	* @param day the day of this asset stats day
	*/
	public void setDay(int day) {
		_assetStatsDay.setDay(day);
	}

	/**
	* Returns the year of this asset stats day.
	*
	* @return the year of this asset stats day
	*/
	public int getYear() {
		return _assetStatsDay.getYear();
	}

	/**
	* Sets the year of this asset stats day.
	*
	* @param year the year of this asset stats day
	*/
	public void setYear(int year) {
		_assetStatsDay.setYear(year);
	}

	/**
	* Returns the view count of this asset stats day.
	*
	* @return the view count of this asset stats day
	*/
	public long getViewCount() {
		return _assetStatsDay.getViewCount();
	}

	/**
	* Sets the view count of this asset stats day.
	*
	* @param viewCount the view count of this asset stats day
	*/
	public void setViewCount(long viewCount) {
		_assetStatsDay.setViewCount(viewCount);
	}

	/**
	* Returns the download count of this asset stats day.
	*
	* @return the download count of this asset stats day
	*/
	public long getDownloadCount() {
		return _assetStatsDay.getDownloadCount();
	}

	/**
	* Sets the download count of this asset stats day.
	*
	* @param downloadCount the download count of this asset stats day
	*/
	public void setDownloadCount(long downloadCount) {
		_assetStatsDay.setDownloadCount(downloadCount);
	}

	/**
	* Returns the purchase count of this asset stats day.
	*
	* @return the purchase count of this asset stats day
	*/
	public long getPurchaseCount() {
		return _assetStatsDay.getPurchaseCount();
	}

	/**
	* Sets the purchase count of this asset stats day.
	*
	* @param purchaseCount the purchase count of this asset stats day
	*/
	public void setPurchaseCount(long purchaseCount) {
		_assetStatsDay.setPurchaseCount(purchaseCount);
	}

	/**
	* Returns the currency code revenues of this asset stats day.
	*
	* @return the currency code revenues of this asset stats day
	*/
	public java.lang.String getCurrencyCodeRevenues() {
		return _assetStatsDay.getCurrencyCodeRevenues();
	}

	/**
	* Sets the currency code revenues of this asset stats day.
	*
	* @param currencyCodeRevenues the currency code revenues of this asset stats day
	*/
	public void setCurrencyCodeRevenues(java.lang.String currencyCodeRevenues) {
		_assetStatsDay.setCurrencyCodeRevenues(currencyCodeRevenues);
	}

	public boolean isNew() {
		return _assetStatsDay.isNew();
	}

	public void setNew(boolean n) {
		_assetStatsDay.setNew(n);
	}

	public boolean isCachedModel() {
		return _assetStatsDay.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_assetStatsDay.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _assetStatsDay.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _assetStatsDay.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetStatsDay.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetStatsDay.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetStatsDay.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetStatsDayWrapper((AssetStatsDay)_assetStatsDay.clone());
	}

	public int compareTo(com.liferay.osb.model.AssetStatsDay assetStatsDay) {
		return _assetStatsDay.compareTo(assetStatsDay);
	}

	@Override
	public int hashCode() {
		return _assetStatsDay.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AssetStatsDay> toCacheModel() {
		return _assetStatsDay.toCacheModel();
	}

	public com.liferay.osb.model.AssetStatsDay toEscapedModel() {
		return new AssetStatsDayWrapper(_assetStatsDay.toEscapedModel());
	}

	public com.liferay.osb.model.AssetStatsDay toUnescapedModel() {
		return new AssetStatsDayWrapper(_assetStatsDay.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetStatsDay.toString();
	}

	public java.lang.String toXmlString() {
		return _assetStatsDay.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetStatsDay.persist();
	}

	public java.util.Date getStartDate(java.util.TimeZone timeZone,
		java.util.Locale locale) {
		return _assetStatsDay.getStartDate(timeZone, locale);
	}

	public long getStats(int statsType) {
		return _assetStatsDay.getStats(statsType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetStatsDayWrapper)) {
			return false;
		}

		AssetStatsDayWrapper assetStatsDayWrapper = (AssetStatsDayWrapper)obj;

		if (Validator.equals(_assetStatsDay, assetStatsDayWrapper._assetStatsDay)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AssetStatsDay getWrappedAssetStatsDay() {
		return _assetStatsDay;
	}

	public AssetStatsDay getWrappedModel() {
		return _assetStatsDay;
	}

	public void resetOriginalValues() {
		_assetStatsDay.resetOriginalValues();
	}

	private AssetStatsDay _assetStatsDay;
}