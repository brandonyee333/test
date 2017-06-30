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
 * The utility for the license entry local service. This utility wraps {@link com.liferay.osb.service.impl.LicenseEntryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntryLocalService
 * @see com.liferay.osb.service.base.LicenseEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.LicenseEntryLocalServiceImpl
 * @generated
 */
public class LicenseEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.LicenseEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the license entry to the database. Also notifies the appropriate model listeners.
	*
	* @param licenseEntry the license entry
	* @return the license entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseEntry addLicenseEntry(
		com.liferay.osb.model.LicenseEntry licenseEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addLicenseEntry(licenseEntry);
	}

	/**
	* Creates a new license entry with the primary key. Does not add the license entry to the database.
	*
	* @param licenseEntryId the primary key for the new license entry
	* @return the new license entry
	*/
	public static com.liferay.osb.model.LicenseEntry createLicenseEntry(
		long licenseEntryId) {
		return getService().createLicenseEntry(licenseEntryId);
	}

	/**
	* Deletes the license entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseEntryId the primary key of the license entry
	* @return the license entry that was removed
	* @throws PortalException if a license entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseEntry deleteLicenseEntry(
		long licenseEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteLicenseEntry(licenseEntryId);
	}

	/**
	* Deletes the license entry from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseEntry the license entry
	* @return the license entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseEntry deleteLicenseEntry(
		com.liferay.osb.model.LicenseEntry licenseEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteLicenseEntry(licenseEntry);
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

	public static com.liferay.osb.model.LicenseEntry fetchLicenseEntry(
		long licenseEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchLicenseEntry(licenseEntryId);
	}

	/**
	* Returns the license entry with the primary key.
	*
	* @param licenseEntryId the primary key of the license entry
	* @return the license entry
	* @throws PortalException if a license entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseEntry getLicenseEntry(
		long licenseEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLicenseEntry(licenseEntryId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the license entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @return the range of license entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.LicenseEntry> getLicenseEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLicenseEntries(start, end);
	}

	/**
	* Returns the number of license entries.
	*
	* @return the number of license entries
	* @throws SystemException if a system exception occurred
	*/
	public static int getLicenseEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLicenseEntriesCount();
	}

	/**
	* Updates the license entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param licenseEntry the license entry
	* @return the license entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseEntry updateLicenseEntry(
		com.liferay.osb.model.LicenseEntry licenseEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateLicenseEntry(licenseEntry);
	}

	/**
	* Updates the license entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param licenseEntry the license entry
	* @param merge whether to merge the license entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the license entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.LicenseEntry updateLicenseEntry(
		com.liferay.osb.model.LicenseEntry licenseEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateLicenseEntry(licenseEntry, merge);
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

	public static com.liferay.osb.model.LicenseEntry addLicenseEntry(
		long userId, long productEntryId, java.lang.String name,
		java.lang.String type, int portalVersionMin, int portalVersionMax)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addLicenseEntry(userId, productEntryId, name, type,
			portalVersionMin, portalVersionMax);
	}

	public static java.util.List<com.liferay.osb.model.LicenseEntry> getLicenseEntries(
		long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLicenseEntries(productEntryId);
	}

	public static java.util.List<com.liferay.osb.model.LicenseEntry> getLicenseEntries(
		long productEntryId, int portalVersion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLicenseEntries(productEntryId, portalVersion);
	}

	public static com.liferay.osb.model.LicenseEntry getLicenseEntry(
		long productEntryId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLicenseEntry(productEntryId, type);
	}

	public static com.liferay.osb.model.LicenseEntry updateLicenseEntry(
		long licenseEntryId, long productEntryId, java.lang.String name,
		java.lang.String type, int portalVersionMin, int portalVersionMax)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateLicenseEntry(licenseEntryId, productEntryId, name,
			type, portalVersionMin, portalVersionMax);
	}

	public static void clearService() {
		_service = null;
	}

	public static LicenseEntryLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					LicenseEntryLocalService.class.getName());

			if (invokableLocalService instanceof LicenseEntryLocalService) {
				_service = (LicenseEntryLocalService)invokableLocalService;
			}
			else {
				_service = new LicenseEntryLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(LicenseEntryLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(LicenseEntryLocalService service) {
	}

	private static LicenseEntryLocalService _service;
}