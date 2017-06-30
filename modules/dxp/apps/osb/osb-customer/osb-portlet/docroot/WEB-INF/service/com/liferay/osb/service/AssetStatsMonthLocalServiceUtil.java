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
 * The utility for the asset stats month local service. This utility wraps {@link com.liferay.osb.service.impl.AssetStatsMonthLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetStatsMonthLocalService
 * @see com.liferay.osb.service.base.AssetStatsMonthLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AssetStatsMonthLocalServiceImpl
 * @generated
 */
public class AssetStatsMonthLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AssetStatsMonthLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the asset stats month to the database. Also notifies the appropriate model listeners.
	*
	* @param assetStatsMonth the asset stats month
	* @return the asset stats month that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsMonth addAssetStatsMonth(
		com.liferay.osb.model.AssetStatsMonth assetStatsMonth)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAssetStatsMonth(assetStatsMonth);
	}

	/**
	* Creates a new asset stats month with the primary key. Does not add the asset stats month to the database.
	*
	* @param assetStatsMonthId the primary key for the new asset stats month
	* @return the new asset stats month
	*/
	public static com.liferay.osb.model.AssetStatsMonth createAssetStatsMonth(
		long assetStatsMonthId) {
		return getService().createAssetStatsMonth(assetStatsMonthId);
	}

	/**
	* Deletes the asset stats month with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetStatsMonthId the primary key of the asset stats month
	* @return the asset stats month that was removed
	* @throws PortalException if a asset stats month with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsMonth deleteAssetStatsMonth(
		long assetStatsMonthId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAssetStatsMonth(assetStatsMonthId);
	}

	/**
	* Deletes the asset stats month from the database. Also notifies the appropriate model listeners.
	*
	* @param assetStatsMonth the asset stats month
	* @return the asset stats month that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsMonth deleteAssetStatsMonth(
		com.liferay.osb.model.AssetStatsMonth assetStatsMonth)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAssetStatsMonth(assetStatsMonth);
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

	public static com.liferay.osb.model.AssetStatsMonth fetchAssetStatsMonth(
		long assetStatsMonthId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAssetStatsMonth(assetStatsMonthId);
	}

	/**
	* Returns the asset stats month with the primary key.
	*
	* @param assetStatsMonthId the primary key of the asset stats month
	* @return the asset stats month
	* @throws PortalException if a asset stats month with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsMonth getAssetStatsMonth(
		long assetStatsMonthId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetStatsMonth(assetStatsMonthId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset stats months.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset stats months
	* @param end the upper bound of the range of asset stats months (not inclusive)
	* @return the range of asset stats months
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetStatsMonth> getAssetStatsMonths(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetStatsMonths(start, end);
	}

	/**
	* Returns the number of asset stats months.
	*
	* @return the number of asset stats months
	* @throws SystemException if a system exception occurred
	*/
	public static int getAssetStatsMonthsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetStatsMonthsCount();
	}

	/**
	* Updates the asset stats month in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetStatsMonth the asset stats month
	* @return the asset stats month that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsMonth updateAssetStatsMonth(
		com.liferay.osb.model.AssetStatsMonth assetStatsMonth)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAssetStatsMonth(assetStatsMonth);
	}

	/**
	* Updates the asset stats month in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetStatsMonth the asset stats month
	* @param merge whether to merge the asset stats month with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset stats month that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetStatsMonth updateAssetStatsMonth(
		com.liferay.osb.model.AssetStatsMonth assetStatsMonth, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAssetStatsMonth(assetStatsMonth, merge);
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

	public static com.liferay.osb.model.AssetStatsMonth fetchAssetStatsMonth(
		java.lang.String className, long classPK, int month, int year)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAssetStatsMonth(className, classPK, month, year);
	}

	public static com.liferay.portal.kernel.json.JSONObject getAssetStatsMonthJSONObject(
		int startDateMonth, int startDateYear, int endDateMonth,
		int endDateYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetStatsMonthJSONObject(startDateMonth, startDateYear,
			endDateMonth, endDateYear, params);
	}

	public static java.util.List<com.liferay.portal.kernel.json.JSONObject> getAssetStatsMonthJSONObjects(
		int startDateMonth, int startDateYear, int endDateMonth,
		int endDateYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, java.lang.String orderByCol,
		java.lang.String orderByType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetStatsMonthJSONObjects(startDateMonth,
			startDateYear, endDateMonth, endDateYear, params, start, end,
			orderByCol, orderByType);
	}

	public static int getAssetStatsMonthJSONObjectsCount(int startDateMonth,
		int startDateYear, int endDateMonth, int endDateYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetStatsMonthJSONObjectsCount(startDateMonth,
			startDateYear, endDateMonth, endDateYear, params);
	}

	public static com.liferay.portal.kernel.json.JSONObject getAssetStatsMonthRevenueJSONObject(
		int startDateMonth, int startDateYear, int endDateMonth,
		int endDateYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		java.lang.String currencyCode)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetStatsMonthRevenueJSONObject(startDateMonth,
			startDateYear, endDateMonth, endDateYear, params, currencyCode);
	}

	public static java.util.List<com.liferay.osb.model.AssetStatsMonth> getAssetStatsMonths(
		int startDateMonth, int startDateYear, int endDateMonth,
		int endDateYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetStatsMonths(startDateMonth, startDateYear,
			endDateMonth, endDateYear, params, start, end, obc);
	}

	public static int getAssetStatsMonthsCount(int startDateMonth,
		int startDateYear, int endDateMonth, int endDateYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetStatsMonthsCount(startDateMonth, startDateYear,
			endDateMonth, endDateYear, params);
	}

	public static com.liferay.osb.model.AssetStatsMonth updateAssetStatsMonth(
		java.lang.String className, long classPK, int month, int year,
		long viewCount, long downloadCount, long purchaseCount,
		java.lang.String currencyCodeRevenues)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAssetStatsMonth(className, classPK, month, year,
			viewCount, downloadCount, purchaseCount, currencyCodeRevenues);
	}

	public static void clearService() {
		_service = null;
	}

	public static AssetStatsMonthLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AssetStatsMonthLocalService.class.getName());

			if (invokableLocalService instanceof AssetStatsMonthLocalService) {
				_service = (AssetStatsMonthLocalService)invokableLocalService;
			}
			else {
				_service = new AssetStatsMonthLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AssetStatsMonthLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AssetStatsMonthLocalService service) {
	}

	private static AssetStatsMonthLocalService _service;
}