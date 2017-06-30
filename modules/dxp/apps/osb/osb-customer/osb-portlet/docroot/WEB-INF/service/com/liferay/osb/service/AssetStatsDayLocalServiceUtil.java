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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the asset stats day local service. This utility wraps {@link com.liferay.osb.service.impl.AssetStatsDayLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetStatsDayLocalService
 * @see com.liferay.osb.service.base.AssetStatsDayLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AssetStatsDayLocalServiceImpl
 * @generated
 */
public class AssetStatsDayLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AssetStatsDayLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the asset stats day to the database. Also notifies the appropriate model listeners.
	*
	* @param assetStatsDay the asset stats day
	* @return the asset stats day that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsDay addAssetStatsDay(
		com.liferay.osb.model.AssetStatsDay assetStatsDay)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAssetStatsDay(assetStatsDay);
	}

	/**
	* Creates a new asset stats day with the primary key. Does not add the asset stats day to the database.
	*
	* @param assetStatsDayId the primary key for the new asset stats day
	* @return the new asset stats day
	*/
	public static com.liferay.osb.model.AssetStatsDay createAssetStatsDay(
		long assetStatsDayId) {
		return getService().createAssetStatsDay(assetStatsDayId);
	}

	/**
	* Deletes the asset stats day with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetStatsDayId the primary key of the asset stats day
	* @return the asset stats day that was removed
	* @throws PortalException if a asset stats day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsDay deleteAssetStatsDay(
		long assetStatsDayId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAssetStatsDay(assetStatsDayId);
	}

	/**
	* Deletes the asset stats day from the database. Also notifies the appropriate model listeners.
	*
	* @param assetStatsDay the asset stats day
	* @return the asset stats day that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsDay deleteAssetStatsDay(
		com.liferay.osb.model.AssetStatsDay assetStatsDay)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAssetStatsDay(assetStatsDay);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.osb.model.AssetStatsDay fetchAssetStatsDay(
		long assetStatsDayId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAssetStatsDay(assetStatsDayId);
	}

	/**
	* Returns the asset stats day with the primary key.
	*
	* @param assetStatsDayId the primary key of the asset stats day
	* @return the asset stats day
	* @throws PortalException if a asset stats day with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsDay getAssetStatsDay(
		long assetStatsDayId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetStatsDay(assetStatsDayId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset stats daies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset stats daies
	* @param end the upper bound of the range of asset stats daies (not inclusive)
	* @return the range of asset stats daies
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetStatsDay> getAssetStatsDaies(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetStatsDaies(start, end);
	}

	/**
	* Returns the number of asset stats daies.
	*
	* @return the number of asset stats daies
	* @throws SystemException if a system exception occurred
	*/
	public static int getAssetStatsDaiesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetStatsDaiesCount();
	}

	/**
	* Updates the asset stats day in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetStatsDay the asset stats day
	* @return the asset stats day that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsDay updateAssetStatsDay(
		com.liferay.osb.model.AssetStatsDay assetStatsDay)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAssetStatsDay(assetStatsDay);
	}

	/**
	* Updates the asset stats day in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetStatsDay the asset stats day
	* @param merge whether to merge the asset stats day with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset stats day that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsDay updateAssetStatsDay(
		com.liferay.osb.model.AssetStatsDay assetStatsDay, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAssetStatsDay(assetStatsDay, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.osb.model.AssetStatsDay fetchAssetStatsDay(
		java.lang.String className, long classPK, int day, int year)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAssetStatsDay(className, classPK, day, year);
	}

	public static com.liferay.portal.kernel.json.JSONObject getAssetStatsDayJSONObject(
		int startDateDay, int startDateYear, int endDateDay, int endDateYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetStatsDayJSONObject(startDateDay, startDateYear,
			endDateDay, endDateYear, params);
	}

	public static java.util.List<com.liferay.portal.kernel.json.JSONObject> getAssetStatsDayJSONObjects(
		int startDateDay, int startDateYear, int endDateDay, int endDateYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, java.lang.String orderByCol,
		java.lang.String orderByType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetStatsDayJSONObjects(startDateDay, startDateYear,
			endDateDay, endDateYear, params, start, end, orderByCol, orderByType);
	}

	public static int getAssetStatsDayJSONObjectsCount(int startDateDay,
		int startDateYear, int endDateDay, int endDateYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetStatsDayJSONObjectsCount(startDateDay,
			startDateYear, endDateDay, endDateYear, params);
	}

	public static com.liferay.portal.kernel.json.JSONObject getAssetStatsDayRevenueJSONObject(
		int startDateDay, int startDateYear, int endDateDay, int endDateYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		java.lang.String currencyCode)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetStatsDayRevenueJSONObject(startDateDay,
			startDateYear, endDateDay, endDateYear, params, currencyCode);
	}

	public static java.util.List<com.liferay.osb.model.AssetStatsDay> getAssetStatsDays(
		int startDateDay, int startDateYear, int endDateDay, int endDateYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetStatsDays(startDateDay, startDateYear, endDateDay,
			endDateYear, params, start, end, obc);
	}

	public static int getAssetStatsDaysCount(int startDateDay,
		int startDateYear, int endDateDay, int endDateYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetStatsDaysCount(startDateDay, startDateYear,
			endDateDay, endDateYear, params);
	}

	public static com.liferay.osb.model.AssetStatsDay updateAssetStatsDay(
		java.lang.String className, long classPK, int day, int year,
		long viewCount, long downloadCount, long purchaseCount,
		java.lang.String currencyCodeRevenues)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAssetStatsDay(className, classPK, day, year,
			viewCount, downloadCount, purchaseCount, currencyCodeRevenues);
	}

	public static void clearService() {
		_service = null;
	}

	public static AssetStatsDayLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AssetStatsDayLocalService.class.getName());

			if (invokableLocalService instanceof AssetStatsDayLocalService) {
				_service = (AssetStatsDayLocalService)invokableLocalService;
			}
			else {
				_service = new AssetStatsDayLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AssetStatsDayLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AssetStatsDayLocalService service) {
	}

	private static AssetStatsDayLocalService _service;
}