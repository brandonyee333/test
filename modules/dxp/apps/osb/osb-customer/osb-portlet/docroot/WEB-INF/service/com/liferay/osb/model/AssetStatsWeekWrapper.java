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
 * This class is a wrapper for {@link AssetStatsWeek}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetStatsWeek
 * @generated
 */
public class AssetStatsWeekWrapper implements AssetStatsWeek,
	ModelWrapper<AssetStatsWeek> {
	public AssetStatsWeekWrapper(AssetStatsWeek assetStatsWeek) {
		_assetStatsWeek = assetStatsWeek;
	}

	public Class<?> getModelClass() {
		return AssetStatsWeek.class;
	}

	public String getModelClassName() {
		return AssetStatsWeek.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetStatsWeekId", getAssetStatsWeekId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("week", getWeek());
		attributes.put("year", getYear());
		attributes.put("viewCount", getViewCount());
		attributes.put("downloadCount", getDownloadCount());
		attributes.put("purchaseCount", getPurchaseCount());
		attributes.put("currencyCodeRevenues", getCurrencyCodeRevenues());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetStatsWeekId = (Long)attributes.get("assetStatsWeekId");

		if (assetStatsWeekId != null) {
			setAssetStatsWeekId(assetStatsWeekId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer week = (Integer)attributes.get("week");

		if (week != null) {
			setWeek(week);
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
	* Returns the primary key of this asset stats week.
	*
	* @return the primary key of this asset stats week
	*/
	public long getPrimaryKey() {
		return _assetStatsWeek.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset stats week.
	*
	* @param primaryKey the primary key of this asset stats week
	*/
	public void setPrimaryKey(long primaryKey) {
		_assetStatsWeek.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the asset stats week ID of this asset stats week.
	*
	* @return the asset stats week ID of this asset stats week
	*/
	public long getAssetStatsWeekId() {
		return _assetStatsWeek.getAssetStatsWeekId();
	}

	/**
	* Sets the asset stats week ID of this asset stats week.
	*
	* @param assetStatsWeekId the asset stats week ID of this asset stats week
	*/
	public void setAssetStatsWeekId(long assetStatsWeekId) {
		_assetStatsWeek.setAssetStatsWeekId(assetStatsWeekId);
	}

	/**
	* Returns the fully qualified class name of this asset stats week.
	*
	* @return the fully qualified class name of this asset stats week
	*/
	public java.lang.String getClassName() {
		return _assetStatsWeek.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_assetStatsWeek.setClassName(className);
	}

	/**
	* Returns the class name ID of this asset stats week.
	*
	* @return the class name ID of this asset stats week
	*/
	public long getClassNameId() {
		return _assetStatsWeek.getClassNameId();
	}

	/**
	* Sets the class name ID of this asset stats week.
	*
	* @param classNameId the class name ID of this asset stats week
	*/
	public void setClassNameId(long classNameId) {
		_assetStatsWeek.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this asset stats week.
	*
	* @return the class p k of this asset stats week
	*/
	public long getClassPK() {
		return _assetStatsWeek.getClassPK();
	}

	/**
	* Sets the class p k of this asset stats week.
	*
	* @param classPK the class p k of this asset stats week
	*/
	public void setClassPK(long classPK) {
		_assetStatsWeek.setClassPK(classPK);
	}

	/**
	* Returns the week of this asset stats week.
	*
	* @return the week of this asset stats week
	*/
	public int getWeek() {
		return _assetStatsWeek.getWeek();
	}

	/**
	* Sets the week of this asset stats week.
	*
	* @param week the week of this asset stats week
	*/
	public void setWeek(int week) {
		_assetStatsWeek.setWeek(week);
	}

	/**
	* Returns the year of this asset stats week.
	*
	* @return the year of this asset stats week
	*/
	public int getYear() {
		return _assetStatsWeek.getYear();
	}

	/**
	* Sets the year of this asset stats week.
	*
	* @param year the year of this asset stats week
	*/
	public void setYear(int year) {
		_assetStatsWeek.setYear(year);
	}

	/**
	* Returns the view count of this asset stats week.
	*
	* @return the view count of this asset stats week
	*/
	public long getViewCount() {
		return _assetStatsWeek.getViewCount();
	}

	/**
	* Sets the view count of this asset stats week.
	*
	* @param viewCount the view count of this asset stats week
	*/
	public void setViewCount(long viewCount) {
		_assetStatsWeek.setViewCount(viewCount);
	}

	/**
	* Returns the download count of this asset stats week.
	*
	* @return the download count of this asset stats week
	*/
	public long getDownloadCount() {
		return _assetStatsWeek.getDownloadCount();
	}

	/**
	* Sets the download count of this asset stats week.
	*
	* @param downloadCount the download count of this asset stats week
	*/
	public void setDownloadCount(long downloadCount) {
		_assetStatsWeek.setDownloadCount(downloadCount);
	}

	/**
	* Returns the purchase count of this asset stats week.
	*
	* @return the purchase count of this asset stats week
	*/
	public long getPurchaseCount() {
		return _assetStatsWeek.getPurchaseCount();
	}

	/**
	* Sets the purchase count of this asset stats week.
	*
	* @param purchaseCount the purchase count of this asset stats week
	*/
	public void setPurchaseCount(long purchaseCount) {
		_assetStatsWeek.setPurchaseCount(purchaseCount);
	}

	/**
	* Returns the currency code revenues of this asset stats week.
	*
	* @return the currency code revenues of this asset stats week
	*/
	public java.lang.String getCurrencyCodeRevenues() {
		return _assetStatsWeek.getCurrencyCodeRevenues();
	}

	/**
	* Sets the currency code revenues of this asset stats week.
	*
	* @param currencyCodeRevenues the currency code revenues of this asset stats week
	*/
	public void setCurrencyCodeRevenues(java.lang.String currencyCodeRevenues) {
		_assetStatsWeek.setCurrencyCodeRevenues(currencyCodeRevenues);
	}

	public boolean isNew() {
		return _assetStatsWeek.isNew();
	}

	public void setNew(boolean n) {
		_assetStatsWeek.setNew(n);
	}

	public boolean isCachedModel() {
		return _assetStatsWeek.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_assetStatsWeek.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _assetStatsWeek.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _assetStatsWeek.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetStatsWeek.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetStatsWeek.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetStatsWeek.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetStatsWeekWrapper((AssetStatsWeek)_assetStatsWeek.clone());
	}

	public int compareTo(com.liferay.osb.model.AssetStatsWeek assetStatsWeek) {
		return _assetStatsWeek.compareTo(assetStatsWeek);
	}

	@Override
	public int hashCode() {
		return _assetStatsWeek.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AssetStatsWeek> toCacheModel() {
		return _assetStatsWeek.toCacheModel();
	}

	public com.liferay.osb.model.AssetStatsWeek toEscapedModel() {
		return new AssetStatsWeekWrapper(_assetStatsWeek.toEscapedModel());
	}

	public com.liferay.osb.model.AssetStatsWeek toUnescapedModel() {
		return new AssetStatsWeekWrapper(_assetStatsWeek.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetStatsWeek.toString();
	}

	public java.lang.String toXmlString() {
		return _assetStatsWeek.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetStatsWeek.persist();
	}

	public java.util.Date getStartDate(java.util.TimeZone timeZone,
		java.util.Locale locale) {
		return _assetStatsWeek.getStartDate(timeZone, locale);
	}

	public long getStats(int statsType) {
		return _assetStatsWeek.getStats(statsType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetStatsWeekWrapper)) {
			return false;
		}

		AssetStatsWeekWrapper assetStatsWeekWrapper = (AssetStatsWeekWrapper)obj;

		if (Validator.equals(_assetStatsWeek,
					assetStatsWeekWrapper._assetStatsWeek)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AssetStatsWeek getWrappedAssetStatsWeek() {
		return _assetStatsWeek;
	}

	public AssetStatsWeek getWrappedModel() {
		return _assetStatsWeek;
	}

	public void resetOriginalValues() {
		_assetStatsWeek.resetOriginalValues();
	}

	private AssetStatsWeek _assetStatsWeek;
}