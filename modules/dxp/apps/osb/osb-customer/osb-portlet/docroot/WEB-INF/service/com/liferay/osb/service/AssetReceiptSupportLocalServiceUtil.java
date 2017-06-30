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
 * The utility for the asset receipt support local service. This utility wraps {@link com.liferay.osb.service.impl.AssetReceiptSupportLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptSupportLocalService
 * @see com.liferay.osb.service.base.AssetReceiptSupportLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AssetReceiptSupportLocalServiceImpl
 * @generated
 */
public class AssetReceiptSupportLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AssetReceiptSupportLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the asset receipt support to the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptSupport the asset receipt support
	* @return the asset receipt support that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport addAssetReceiptSupport(
		com.liferay.osb.model.AssetReceiptSupport assetReceiptSupport)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAssetReceiptSupport(assetReceiptSupport);
	}

	/**
	* Creates a new asset receipt support with the primary key. Does not add the asset receipt support to the database.
	*
	* @param assetReceiptSupportId the primary key for the new asset receipt support
	* @return the new asset receipt support
	*/
	public static com.liferay.osb.model.AssetReceiptSupport createAssetReceiptSupport(
		long assetReceiptSupportId) {
		return getService().createAssetReceiptSupport(assetReceiptSupportId);
	}

	/**
	* Deletes the asset receipt support with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptSupportId the primary key of the asset receipt support
	* @return the asset receipt support that was removed
	* @throws PortalException if a asset receipt support with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport deleteAssetReceiptSupport(
		long assetReceiptSupportId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAssetReceiptSupport(assetReceiptSupportId);
	}

	/**
	* Deletes the asset receipt support from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptSupport the asset receipt support
	* @return the asset receipt support that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport deleteAssetReceiptSupport(
		com.liferay.osb.model.AssetReceiptSupport assetReceiptSupport)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAssetReceiptSupport(assetReceiptSupport);
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

	public static com.liferay.osb.model.AssetReceiptSupport fetchAssetReceiptSupport(
		long assetReceiptSupportId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAssetReceiptSupport(assetReceiptSupportId);
	}

	/**
	* Returns the asset receipt support with the primary key.
	*
	* @param assetReceiptSupportId the primary key of the asset receipt support
	* @return the asset receipt support
	* @throws PortalException if a asset receipt support with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport getAssetReceiptSupport(
		long assetReceiptSupportId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetReceiptSupport(assetReceiptSupportId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset receipt supports.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset receipt supports
	* @param end the upper bound of the range of asset receipt supports (not inclusive)
	* @return the range of asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> getAssetReceiptSupports(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetReceiptSupports(start, end);
	}

	/**
	* Returns the number of asset receipt supports.
	*
	* @return the number of asset receipt supports
	* @throws SystemException if a system exception occurred
	*/
	public static int getAssetReceiptSupportsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetReceiptSupportsCount();
	}

	/**
	* Updates the asset receipt support in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptSupport the asset receipt support
	* @return the asset receipt support that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport updateAssetReceiptSupport(
		com.liferay.osb.model.AssetReceiptSupport assetReceiptSupport)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAssetReceiptSupport(assetReceiptSupport);
	}

	/**
	* Updates the asset receipt support in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptSupport the asset receipt support
	* @param merge whether to merge the asset receipt support with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset receipt support that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetReceiptSupport updateAssetReceiptSupport(
		com.liferay.osb.model.AssetReceiptSupport assetReceiptSupport,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAssetReceiptSupport(assetReceiptSupport, merge);
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

	public static com.liferay.osb.model.AssetReceiptSupport addAssetReceiptSupport(
		long userId, long assetReceiptId, long assetLicenseId,
		long assetEntryId, long ownerClassNameId, long ownerClassPK,
		long productClassNameId, long productClassPK,
		java.lang.String productId, java.util.Date startDate,
		java.util.Date endDate, int usageType, long supportLifetime)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addAssetReceiptSupport(userId, assetReceiptId,
			assetLicenseId, assetEntryId, ownerClassNameId, ownerClassPK,
			productClassNameId, productClassPK, productId, startDate, endDate,
			usageType, supportLifetime);
	}

	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> getActiveAssetReceiptSupports(
		long assetReceiptId, int usageType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getActiveAssetReceiptSupports(assetReceiptId, usageType,
			start, end);
	}

	public static int getActiveAssetReceiptSupportsCount(long assetReceiptId,
		int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getActiveAssetReceiptSupportsCount(assetReceiptId, usageType);
	}

	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> getAssetReceiptSupports(
		long assetReceiptId, java.lang.String productClassName,
		long productClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetReceiptSupports(assetReceiptId, productClassName,
			productClassPK, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> getRenewedAssetReceiptSupports(
		long assetReceiptId, int usageType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getRenewedAssetReceiptSupports(assetReceiptId, usageType,
			start, end);
	}

	public static int getRenewedAssetReceiptSupportsCount(long assetReceiptId,
		int usageType)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getRenewedAssetReceiptSupportsCount(assetReceiptId,
			usageType);
	}

	public static com.liferay.osb.model.AssetReceiptSupport updateAssetReceiptSupport(
		long assetReceiptSupportId, java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAssetReceiptSupport(assetReceiptSupportId, endDate);
	}

	public static java.util.List<com.liferay.osb.model.AssetReceiptSupport> updateAssetReceiptSupports(
		long assetReceiptId, int usageType, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAssetReceiptSupports(assetReceiptId, usageType,
			startDate, endDate);
	}

	public static void clearService() {
		_service = null;
	}

	public static AssetReceiptSupportLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AssetReceiptSupportLocalService.class.getName());

			if (invokableLocalService instanceof AssetReceiptSupportLocalService) {
				_service = (AssetReceiptSupportLocalService)invokableLocalService;
			}
			else {
				_service = new AssetReceiptSupportLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AssetReceiptSupportLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AssetReceiptSupportLocalService service) {
	}

	private static AssetReceiptSupportLocalService _service;
}