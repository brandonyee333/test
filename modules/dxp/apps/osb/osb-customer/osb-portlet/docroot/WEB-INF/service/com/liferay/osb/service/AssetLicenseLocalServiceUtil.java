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
 * The utility for the asset license local service. This utility wraps {@link com.liferay.osb.service.impl.AssetLicenseLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetLicenseLocalService
 * @see com.liferay.osb.service.base.AssetLicenseLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AssetLicenseLocalServiceImpl
 * @generated
 */
public class AssetLicenseLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AssetLicenseLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the asset license to the database. Also notifies the appropriate model listeners.
	*
	* @param assetLicense the asset license
	* @return the asset license that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense addAssetLicense(
		com.liferay.osb.model.AssetLicense assetLicense)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAssetLicense(assetLicense);
	}

	/**
	* Creates a new asset license with the primary key. Does not add the asset license to the database.
	*
	* @param assetLicenseId the primary key for the new asset license
	* @return the new asset license
	*/
	public static com.liferay.osb.model.AssetLicense createAssetLicense(
		long assetLicenseId) {
		return getService().createAssetLicense(assetLicenseId);
	}

	/**
	* Deletes the asset license with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetLicenseId the primary key of the asset license
	* @return the asset license that was removed
	* @throws PortalException if a asset license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense deleteAssetLicense(
		long assetLicenseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAssetLicense(assetLicenseId);
	}

	/**
	* Deletes the asset license from the database. Also notifies the appropriate model listeners.
	*
	* @param assetLicense the asset license
	* @return the asset license that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense deleteAssetLicense(
		com.liferay.osb.model.AssetLicense assetLicense)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAssetLicense(assetLicense);
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

	public static com.liferay.osb.model.AssetLicense fetchAssetLicense(
		long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAssetLicense(assetLicenseId);
	}

	/**
	* Returns the asset license with the primary key.
	*
	* @param assetLicenseId the primary key of the asset license
	* @return the asset license
	* @throws PortalException if a asset license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense getAssetLicense(
		long assetLicenseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetLicense(assetLicenseId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset licenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset licenses
	* @param end the upper bound of the range of asset licenses (not inclusive)
	* @return the range of asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetLicense> getAssetLicenses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetLicenses(start, end);
	}

	/**
	* Returns the number of asset licenses.
	*
	* @return the number of asset licenses
	* @throws SystemException if a system exception occurred
	*/
	public static int getAssetLicensesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetLicensesCount();
	}

	/**
	* Updates the asset license in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetLicense the asset license
	* @return the asset license that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense updateAssetLicense(
		com.liferay.osb.model.AssetLicense assetLicense)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAssetLicense(assetLicense);
	}

	/**
	* Updates the asset license in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetLicense the asset license
	* @param merge whether to merge the asset license with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset license that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetLicense updateAssetLicense(
		com.liferay.osb.model.AssetLicense assetLicense, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAssetLicense(assetLicense, merge);
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

	public static com.liferay.osb.model.AssetLicense addAssetLicense(
		long userId, java.lang.String className, long classPK,
		java.lang.String licenseId, java.lang.String name, int usageType,
		int licenseType, long licenseTypeAllotment, long lifetime)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addAssetLicense(userId, className, classPK, licenseId,
			name, usageType, licenseType, licenseTypeAllotment, lifetime);
	}

	public static void copyAssetLicenses(java.lang.String sourceClassName,
		long sourceClassPK, java.lang.String targetClassName, long targetClassPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.copyAssetLicenses(sourceClassName, sourceClassPK, targetClassName,
			targetClassPK);
	}

	public static void deleteAssetLicenses(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAssetLicenses(classNameId, classPK);
	}

	public static void deleteAssetLicenses(java.lang.String className,
		long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAssetLicenses(className, classPK);
	}

	public static com.liferay.osb.model.AssetLicense fetchAssetLicense(
		long classNameId, long classPK, int usageType, int licenseType,
		long licenseTypeAllotment, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchAssetLicense(classNameId, classPK, usageType,
			licenseType, licenseTypeAllotment, status);
	}

	public static com.liferay.osb.model.AssetLicense fetchAssetLicense(
		java.lang.String className, long classPK, int usageType,
		int licenseType, long licenseTypeAllotment, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchAssetLicense(className, classPK, usageType,
			licenseType, licenseTypeAllotment, status);
	}

	public static java.util.List<com.liferay.osb.model.AssetLicense> getAssetLicenses(
		long classNameId, long classPK, int usageType, int licenseType,
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetLicenses(classNameId, classPK, usageType,
			licenseType, status, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.AssetLicense> getAssetLicenses(
		long classNameId, long classPK, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetLicenses(classNameId, classPK, status, start, end,
			obc);
	}

	public static java.util.List<com.liferay.osb.model.AssetLicense> getAssetLicenses(
		java.lang.String className, long classPK, int usageType,
		int licenseType, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetLicenses(className, classPK, usageType,
			licenseType, status, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.AssetLicense> getAssetLicenses(
		java.lang.String className, long classPK, int status, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetLicenses(className, classPK, status, start, end, obc);
	}

	public static int getAssetLicensesCount(java.lang.String className,
		long classPK, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetLicensesCount(className, classPK, status);
	}

	public static int getAssetLicensesCount(java.lang.String className,
		long classPK, int usageType, int licenseType, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetLicensesCount(className, classPK, usageType,
			licenseType, status);
	}

	public static com.liferay.osb.model.AssetLicense updateAssetLicense(
		long assetLicenseId, java.lang.String name, int usageType,
		int licenseType, long licenseTypeAllotment, long lifetime, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAssetLicense(assetLicenseId, name, usageType,
			licenseType, licenseTypeAllotment, lifetime, status);
	}

	public static void updateAssetLicenses(long userId,
		java.lang.String className, long classPK,
		java.util.List<com.liferay.osb.model.AssetLicense> assetLicenses)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateAssetLicenses(userId, className, classPK, assetLicenses);
	}

	public static void clearService() {
		_service = null;
	}

	public static AssetLicenseLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AssetLicenseLocalService.class.getName());

			if (invokableLocalService instanceof AssetLicenseLocalService) {
				_service = (AssetLicenseLocalService)invokableLocalService;
			}
			else {
				_service = new AssetLicenseLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AssetLicenseLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AssetLicenseLocalService service) {
	}

	private static AssetLicenseLocalService _service;
}