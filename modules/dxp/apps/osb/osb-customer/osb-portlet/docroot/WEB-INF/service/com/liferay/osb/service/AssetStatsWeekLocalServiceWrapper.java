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

package com.liferay.osb.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AssetStatsWeekLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetStatsWeekLocalService
 * @generated
 */
public class AssetStatsWeekLocalServiceWrapper
	implements AssetStatsWeekLocalService,
		ServiceWrapper<AssetStatsWeekLocalService> {
	public AssetStatsWeekLocalServiceWrapper(
		AssetStatsWeekLocalService assetStatsWeekLocalService) {
		_assetStatsWeekLocalService = assetStatsWeekLocalService;
	}

	/**
	* Adds the asset stats week to the database. Also notifies the appropriate model listeners.
	*
	* @param assetStatsWeek the asset stats week
	* @return the asset stats week that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsWeek addAssetStatsWeek(
		com.liferay.osb.model.AssetStatsWeek assetStatsWeek)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.addAssetStatsWeek(assetStatsWeek);
	}

	/**
	* Creates a new asset stats week with the primary key. Does not add the asset stats week to the database.
	*
	* @param assetStatsWeekId the primary key for the new asset stats week
	* @return the new asset stats week
	*/
	public com.liferay.osb.model.AssetStatsWeek createAssetStatsWeek(
		long assetStatsWeekId) {
		return _assetStatsWeekLocalService.createAssetStatsWeek(assetStatsWeekId);
	}

	/**
	* Deletes the asset stats week with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetStatsWeekId the primary key of the asset stats week
	* @return the asset stats week that was removed
	* @throws PortalException if a asset stats week with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsWeek deleteAssetStatsWeek(
		long assetStatsWeekId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.deleteAssetStatsWeek(assetStatsWeekId);
	}

	/**
	* Deletes the asset stats week from the database. Also notifies the appropriate model listeners.
	*
	* @param assetStatsWeek the asset stats week
	* @return the asset stats week that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsWeek deleteAssetStatsWeek(
		com.liferay.osb.model.AssetStatsWeek assetStatsWeek)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.deleteAssetStatsWeek(assetStatsWeek);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetStatsWeekLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AssetStatsWeek fetchAssetStatsWeek(
		long assetStatsWeekId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.fetchAssetStatsWeek(assetStatsWeekId);
	}

	/**
	* Returns the asset stats week with the primary key.
	*
	* @param assetStatsWeekId the primary key of the asset stats week
	* @return the asset stats week
	* @throws PortalException if a asset stats week with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsWeek getAssetStatsWeek(
		long assetStatsWeekId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.getAssetStatsWeek(assetStatsWeekId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset stats weeks.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset stats weeks
	* @param end the upper bound of the range of asset stats weeks (not inclusive)
	* @return the range of asset stats weeks
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetStatsWeek> getAssetStatsWeeks(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.getAssetStatsWeeks(start, end);
	}

	/**
	* Returns the number of asset stats weeks.
	*
	* @return the number of asset stats weeks
	* @throws SystemException if a system exception occurred
	*/
	public int getAssetStatsWeeksCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.getAssetStatsWeeksCount();
	}

	/**
	* Updates the asset stats week in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetStatsWeek the asset stats week
	* @return the asset stats week that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsWeek updateAssetStatsWeek(
		com.liferay.osb.model.AssetStatsWeek assetStatsWeek)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.updateAssetStatsWeek(assetStatsWeek);
	}

	/**
	* Updates the asset stats week in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetStatsWeek the asset stats week
	* @param merge whether to merge the asset stats week with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset stats week that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetStatsWeek updateAssetStatsWeek(
		com.liferay.osb.model.AssetStatsWeek assetStatsWeek, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.updateAssetStatsWeek(assetStatsWeek,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _assetStatsWeekLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetStatsWeekLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetStatsWeekLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.AssetStatsWeek fetchAssetStatsWeek(
		java.lang.String className, long classPK, int week, int year)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.fetchAssetStatsWeek(className,
			classPK, week, year);
	}

	public com.liferay.portal.kernel.json.JSONObject getAssetStatsWeekJSONObject(
		int startDateWeek, int startDateYear, int endDateWeek, int endDateYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.getAssetStatsWeekJSONObject(startDateWeek,
			startDateYear, endDateWeek, endDateYear, params);
	}

	public java.util.List<com.liferay.portal.kernel.json.JSONObject> getAssetStatsWeekJSONObjects(
		int startDateWeek, int startDateYear, int endDateWeek, int endDateYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, java.lang.String orderByCol,
		java.lang.String orderByType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.getAssetStatsWeekJSONObjects(startDateWeek,
			startDateYear, endDateWeek, endDateYear, params, start, end,
			orderByCol, orderByType);
	}

	public int getAssetStatsWeekJSONObjectsCount(int startDateWeek,
		int startDateYear, int endDateWeek, int endDateYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.getAssetStatsWeekJSONObjectsCount(startDateWeek,
			startDateYear, endDateWeek, endDateYear, params);
	}

	public com.liferay.portal.kernel.json.JSONObject getAssetStatsWeekRevenueJSONObject(
		int startDateWeek, int startDateYear, int endDateWeek, int endDateYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		java.lang.String currencyCode)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.getAssetStatsWeekRevenueJSONObject(startDateWeek,
			startDateYear, endDateWeek, endDateYear, params, currencyCode);
	}

	public java.util.List<com.liferay.osb.model.AssetStatsWeek> getAssetStatsWeeks(
		int startDateWeek, int startDateYear, int endDateWeek, int endDateYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.getAssetStatsWeeks(startDateWeek,
			startDateYear, endDateWeek, endDateYear, params, start, end, obc);
	}

	public int getAssetStatsWeeksCount(int startDateWeek, int startDateYear,
		int endDateWeek, int endDateYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.getAssetStatsWeeksCount(startDateWeek,
			startDateYear, endDateWeek, endDateYear, params);
	}

	public com.liferay.osb.model.AssetStatsWeek updateAssetStatsWeek(
		java.lang.String className, long classPK, int week, int year,
		long viewCount, long downloadCount, long purchaseCount,
		java.lang.String currencyCodeRevenues)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetStatsWeekLocalService.updateAssetStatsWeek(className,
			classPK, week, year, viewCount, downloadCount, purchaseCount,
			currencyCodeRevenues);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AssetStatsWeekLocalService getWrappedAssetStatsWeekLocalService() {
		return _assetStatsWeekLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAssetStatsWeekLocalService(
		AssetStatsWeekLocalService assetStatsWeekLocalService) {
		_assetStatsWeekLocalService = assetStatsWeekLocalService;
	}

	public AssetStatsWeekLocalService getWrappedService() {
		return _assetStatsWeekLocalService;
	}

	public void setWrappedService(
		AssetStatsWeekLocalService assetStatsWeekLocalService) {
		_assetStatsWeekLocalService = assetStatsWeekLocalService;
	}

	private AssetStatsWeekLocalService _assetStatsWeekLocalService;
}