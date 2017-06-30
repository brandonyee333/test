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
 * The utility for the app pricing item local service. This utility wraps {@link com.liferay.osb.service.impl.AppPricingItemLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppPricingItemLocalService
 * @see com.liferay.osb.service.base.AppPricingItemLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AppPricingItemLocalServiceImpl
 * @generated
 */
public class AppPricingItemLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AppPricingItemLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the app pricing item to the database. Also notifies the appropriate model listeners.
	*
	* @param appPricingItem the app pricing item
	* @return the app pricing item that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem addAppPricingItem(
		com.liferay.osb.model.AppPricingItem appPricingItem)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAppPricingItem(appPricingItem);
	}

	/**
	* Creates a new app pricing item with the primary key. Does not add the app pricing item to the database.
	*
	* @param appPricingItemId the primary key for the new app pricing item
	* @return the new app pricing item
	*/
	public static com.liferay.osb.model.AppPricingItem createAppPricingItem(
		long appPricingItemId) {
		return getService().createAppPricingItem(appPricingItemId);
	}

	/**
	* Deletes the app pricing item with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appPricingItemId the primary key of the app pricing item
	* @return the app pricing item that was removed
	* @throws PortalException if a app pricing item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem deleteAppPricingItem(
		long appPricingItemId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAppPricingItem(appPricingItemId);
	}

	/**
	* Deletes the app pricing item from the database. Also notifies the appropriate model listeners.
	*
	* @param appPricingItem the app pricing item
	* @return the app pricing item that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem deleteAppPricingItem(
		com.liferay.osb.model.AppPricingItem appPricingItem)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAppPricingItem(appPricingItem);
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

	public static com.liferay.osb.model.AppPricingItem fetchAppPricingItem(
		long appPricingItemId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAppPricingItem(appPricingItemId);
	}

	/**
	* Returns the app pricing item with the primary key.
	*
	* @param appPricingItemId the primary key of the app pricing item
	* @return the app pricing item
	* @throws PortalException if a app pricing item with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem getAppPricingItem(
		long appPricingItemId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppPricingItem(appPricingItemId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the app pricing items.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app pricing items
	* @param end the upper bound of the range of app pricing items (not inclusive)
	* @return the range of app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AppPricingItem> getAppPricingItems(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppPricingItems(start, end);
	}

	/**
	* Returns the number of app pricing items.
	*
	* @return the number of app pricing items
	* @throws SystemException if a system exception occurred
	*/
	public static int getAppPricingItemsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAppPricingItemsCount();
	}

	/**
	* Updates the app pricing item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appPricingItem the app pricing item
	* @return the app pricing item that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem updateAppPricingItem(
		com.liferay.osb.model.AppPricingItem appPricingItem)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAppPricingItem(appPricingItem);
	}

	/**
	* Updates the app pricing item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appPricingItem the app pricing item
	* @param merge whether to merge the app pricing item with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the app pricing item that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AppPricingItem updateAppPricingItem(
		com.liferay.osb.model.AppPricingItem appPricingItem, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAppPricingItem(appPricingItem, merge);
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

	public static void copyAppPricingItems(long sourceAppPricingId,
		long targetAppPricingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().copyAppPricingItems(sourceAppPricingId, targetAppPricingId);
	}

	public static void deleteAppPricingItemByAppPricingId(long appPricingId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAppPricingItemByAppPricingId(appPricingId);
	}

	public static void deleteAppPricingItemByAssetLicenseId(long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAppPricingItemByAssetLicenseId(assetLicenseId);
	}

	public static com.liferay.osb.model.AppPricingItem fetchAppPricingItem(
		long appPricingId, long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAppPricingItem(appPricingId, assetLicenseId);
	}

	public static com.liferay.osb.model.AppPricingItem fetchAppPricingItem(
		long appVersionId, long countryId, long assetLicenseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchAppPricingItem(appVersionId, countryId, assetLicenseId);
	}

	public static com.liferay.osb.model.AppPricingItem getAppPricingItem(
		long appVersionId, long countryId, long assetLicenseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAppPricingItem(appVersionId, countryId, assetLicenseId);
	}

	public static com.liferay.osb.model.AppPricingItem updateAppPricingItem(
		long appPricingItemId, long assetLicenseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAppPricingItem(appPricingItemId, assetLicenseId);
	}

	public static com.liferay.osb.model.AppPricingItem updateAppPricingItem(
		long appPricingId, long assetLicenseId, double price)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAppPricingItem(appPricingId, assetLicenseId, price);
	}

	public static void updateAppPricingItems(long appPricingId,
		long currencyEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateAppPricingItems(appPricingId, currencyEntryId);
	}

	public static void validateAppPricingItems(long appPricingId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().validateAppPricingItems(appPricingId);
	}

	public static void clearService() {
		_service = null;
	}

	public static AppPricingItemLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AppPricingItemLocalService.class.getName());

			if (invokableLocalService instanceof AppPricingItemLocalService) {
				_service = (AppPricingItemLocalService)invokableLocalService;
			}
			else {
				_service = new AppPricingItemLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AppPricingItemLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AppPricingItemLocalService service) {
	}

	private static AppPricingItemLocalService _service;
}