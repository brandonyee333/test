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
 * This class is a wrapper for {@link AssetStatsMonth}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetStatsMonth
 * @generated
 */
public class AssetStatsMonthWrapper implements AssetStatsMonth,
	ModelWrapper<AssetStatsMonth> {
	public AssetStatsMonthWrapper(AssetStatsMonth assetStatsMonth) {
		_assetStatsMonth = assetStatsMonth;
	}

	public Class<?> getModelClass() {
		return AssetStatsMonth.class;
	}

	public String getModelClassName() {
		return AssetStatsMonth.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetStatsMonthId", getAssetStatsMonthId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("month", getMonth());
		attributes.put("year", getYear());
		attributes.put("viewCount", getViewCount());
		attributes.put("downloadCount", getDownloadCount());
		attributes.put("purchaseCount", getPurchaseCount());
		attributes.put("currencyCodeRevenues", getCurrencyCodeRevenues());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetStatsMonthId = (Long)attributes.get("assetStatsMonthId");

		if (assetStatsMonthId != null) {
			setAssetStatsMonthId(assetStatsMonthId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Integer month = (Integer)attributes.get("month");

		if (month != null) {
			setMonth(month);
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
	* Returns the primary key of this asset stats month.
	*
	* @return the primary key of this asset stats month
	*/
	public long getPrimaryKey() {
		return _assetStatsMonth.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset stats month.
	*
	* @param primaryKey the primary key of this asset stats month
	*/
	public void setPrimaryKey(long primaryKey) {
		_assetStatsMonth.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the asset stats month ID of this asset stats month.
	*
	* @return the asset stats month ID of this asset stats month
	*/
	public long getAssetStatsMonthId() {
		return _assetStatsMonth.getAssetStatsMonthId();
	}

	/**
	* Sets the asset stats month ID of this asset stats month.
	*
	* @param assetStatsMonthId the asset stats month ID of this asset stats month
	*/
	public void setAssetStatsMonthId(long assetStatsMonthId) {
		_assetStatsMonth.setAssetStatsMonthId(assetStatsMonthId);
	}

	/**
	* Returns the fully qualified class name of this asset stats month.
	*
	* @return the fully qualified class name of this asset stats month
	*/
	public java.lang.String getClassName() {
		return _assetStatsMonth.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_assetStatsMonth.setClassName(className);
	}

	/**
	* Returns the class name ID of this asset stats month.
	*
	* @return the class name ID of this asset stats month
	*/
	public long getClassNameId() {
		return _assetStatsMonth.getClassNameId();
	}

	/**
	* Sets the class name ID of this asset stats month.
	*
	* @param classNameId the class name ID of this asset stats month
	*/
	public void setClassNameId(long classNameId) {
		_assetStatsMonth.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this asset stats month.
	*
	* @return the class p k of this asset stats month
	*/
	public long getClassPK() {
		return _assetStatsMonth.getClassPK();
	}

	/**
	* Sets the class p k of this asset stats month.
	*
	* @param classPK the class p k of this asset stats month
	*/
	public void setClassPK(long classPK) {
		_assetStatsMonth.setClassPK(classPK);
	}

	/**
	* Returns the month of this asset stats month.
	*
	* @return the month of this asset stats month
	*/
	public int getMonth() {
		return _assetStatsMonth.getMonth();
	}

	/**
	* Sets the month of this asset stats month.
	*
	* @param month the month of this asset stats month
	*/
	public void setMonth(int month) {
		_assetStatsMonth.setMonth(month);
	}

	/**
	* Returns the year of this asset stats month.
	*
	* @return the year of this asset stats month
	*/
	public int getYear() {
		return _assetStatsMonth.getYear();
	}

	/**
	* Sets the year of this asset stats month.
	*
	* @param year the year of this asset stats month
	*/
	public void setYear(int year) {
		_assetStatsMonth.setYear(year);
	}

	/**
	* Returns the view count of this asset stats month.
	*
	* @return the view count of this asset stats month
	*/
	public long getViewCount() {
		return _assetStatsMonth.getViewCount();
	}

	/**
	* Sets the view count of this asset stats month.
	*
	* @param viewCount the view count of this asset stats month
	*/
	public void setViewCount(long viewCount) {
		_assetStatsMonth.setViewCount(viewCount);
	}

	/**
	* Returns the download count of this asset stats month.
	*
	* @return the download count of this asset stats month
	*/
	public long getDownloadCount() {
		return _assetStatsMonth.getDownloadCount();
	}

	/**
	* Sets the download count of this asset stats month.
	*
	* @param downloadCount the download count of this asset stats month
	*/
	public void setDownloadCount(long downloadCount) {
		_assetStatsMonth.setDownloadCount(downloadCount);
	}

	/**
	* Returns the purchase count of this asset stats month.
	*
	* @return the purchase count of this asset stats month
	*/
	public long getPurchaseCount() {
		return _assetStatsMonth.getPurchaseCount();
	}

	/**
	* Sets the purchase count of this asset stats month.
	*
	* @param purchaseCount the purchase count of this asset stats month
	*/
	public void setPurchaseCount(long purchaseCount) {
		_assetStatsMonth.setPurchaseCount(purchaseCount);
	}

	/**
	* Returns the currency code revenues of this asset stats month.
	*
	* @return the currency code revenues of this asset stats month
	*/
	public java.lang.String getCurrencyCodeRevenues() {
		return _assetStatsMonth.getCurrencyCodeRevenues();
	}

	/**
	* Sets the currency code revenues of this asset stats month.
	*
	* @param currencyCodeRevenues the currency code revenues of this asset stats month
	*/
	public void setCurrencyCodeRevenues(java.lang.String currencyCodeRevenues) {
		_assetStatsMonth.setCurrencyCodeRevenues(currencyCodeRevenues);
	}

	public boolean isNew() {
		return _assetStatsMonth.isNew();
	}

	public void setNew(boolean n) {
		_assetStatsMonth.setNew(n);
	}

	public boolean isCachedModel() {
		return _assetStatsMonth.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_assetStatsMonth.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _assetStatsMonth.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _assetStatsMonth.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetStatsMonth.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetStatsMonth.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetStatsMonth.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetStatsMonthWrapper((AssetStatsMonth)_assetStatsMonth.clone());
	}

	public int compareTo(com.liferay.osb.model.AssetStatsMonth assetStatsMonth) {
		return _assetStatsMonth.compareTo(assetStatsMonth);
	}

	@Override
	public int hashCode() {
		return _assetStatsMonth.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.osb.model.AssetStatsMonth> toCacheModel() {
		return _assetStatsMonth.toCacheModel();
	}

	public com.liferay.osb.model.AssetStatsMonth toEscapedModel() {
		return new AssetStatsMonthWrapper(_assetStatsMonth.toEscapedModel());
	}

	public com.liferay.osb.model.AssetStatsMonth toUnescapedModel() {
		return new AssetStatsMonthWrapper(_assetStatsMonth.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetStatsMonth.toString();
	}

	public java.lang.String toXmlString() {
		return _assetStatsMonth.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetStatsMonth.persist();
	}

	public java.util.Date getStartDate(java.util.TimeZone timeZone,
		java.util.Locale locale) {
		return _assetStatsMonth.getStartDate(timeZone, locale);
	}

	public long getStats(int statsType) {
		return _assetStatsMonth.getStats(statsType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetStatsMonthWrapper)) {
			return false;
		}

		AssetStatsMonthWrapper assetStatsMonthWrapper = (AssetStatsMonthWrapper)obj;

		if (Validator.equals(_assetStatsMonth,
					assetStatsMonthWrapper._assetStatsMonth)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public AssetStatsMonth getWrappedAssetStatsMonth() {
		return _assetStatsMonth;
	}

	public AssetStatsMonth getWrappedModel() {
		return _assetStatsMonth;
	}

	public void resetOriginalValues() {
		_assetStatsMonth.resetOriginalValues();
	}

	private AssetStatsMonth _assetStatsMonth;
}