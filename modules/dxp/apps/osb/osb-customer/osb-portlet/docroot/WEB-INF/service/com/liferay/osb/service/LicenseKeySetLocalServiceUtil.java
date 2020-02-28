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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for LicenseKeySet. This utility wraps
 * {@link com.liferay.osb.service.impl.LicenseKeySetLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeySetLocalService
 * @see com.liferay.osb.service.base.LicenseKeySetLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.LicenseKeySetLocalServiceImpl
 * @generated
 */
@ProviderType
public class LicenseKeySetLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.LicenseKeySetLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the license key set to the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKeySet the license key set
	* @return the license key set that was added
	*/
	public static com.liferay.osb.model.LicenseKeySet addLicenseKeySet(
		com.liferay.osb.model.LicenseKeySet licenseKeySet) {
		return getService().addLicenseKeySet(licenseKeySet);
	}

	public static com.liferay.osb.model.LicenseKeySet addLicenseKeySet(
		long userId, long accountEntryId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().addLicenseKeySet(userId, accountEntryId, name);
	}

	/**
	* Creates a new license key set with the primary key. Does not add the license key set to the database.
	*
	* @param licenseKeySetId the primary key for the new license key set
	* @return the new license key set
	*/
	public static com.liferay.osb.model.LicenseKeySet createLicenseKeySet(
		long licenseKeySetId) {
		return getService().createLicenseKeySet(licenseKeySetId);
	}

	/**
	* Deletes the license key set from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKeySet the license key set
	* @return the license key set that was removed
	* @throws PortalException
	*/
	public static com.liferay.osb.model.LicenseKeySet deleteLicenseKeySet(
		com.liferay.osb.model.LicenseKeySet licenseKeySet)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLicenseKeySet(licenseKeySet);
	}

	/**
	* Deletes the license key set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKeySetId the primary key of the license key set
	* @return the license key set that was removed
	* @throws PortalException if a license key set with the primary key could not be found
	*/
	public static com.liferay.osb.model.LicenseKeySet deleteLicenseKeySet(
		long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLicenseKeySet(licenseKeySetId);
	}

	public static com.liferay.osb.model.LicenseKeySet fetchLicenseKeySet(
		long licenseKeySetId) {
		return getService().fetchLicenseKeySet(licenseKeySetId);
	}

	/**
	* Returns the license key set with the primary key.
	*
	* @param licenseKeySetId the primary key of the license key set
	* @return the license key set
	* @throws PortalException if a license key set with the primary key could not be found
	*/
	public static com.liferay.osb.model.LicenseKeySet getLicenseKeySet(
		long licenseKeySetId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLicenseKeySet(licenseKeySetId);
	}

	/**
	* Updates the license key set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param licenseKeySet the license key set
	* @return the license key set that was updated
	*/
	public static com.liferay.osb.model.LicenseKeySet updateLicenseKeySet(
		com.liferay.osb.model.LicenseKeySet licenseKeySet) {
		return getService().updateLicenseKeySet(licenseKeySet);
	}

	public static com.liferay.osb.model.LicenseKeySet updateLicenseKeySet(
		long licenseKeySetId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().updateLicenseKeySet(licenseKeySetId, name);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static int getAccountEntryLicenseKeySetsCount(long accountEntryId) {
		return getService().getAccountEntryLicenseKeySetsCount(accountEntryId);
	}

	/**
	* Returns the number of license key sets.
	*
	* @return the number of license key sets
	*/
	public static int getLicenseKeySetsCount() {
		return getService().getLicenseKeySetsCount();
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.osb.model.LicenseKeySet> getAccountEntryLicenseKeySets(
		long accountEntryId, int start, int end) {
		return getService()
				   .getAccountEntryLicenseKeySets(accountEntryId, start, end);
	}

	/**
	* Returns a range of all the license key sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @return the range of license key sets
	*/
	public static java.util.List<com.liferay.osb.model.LicenseKeySet> getLicenseKeySets(
		int start, int end) {
		return getService().getLicenseKeySets(start, end);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static void clearService() {
		_service = null;
	}

	public static LicenseKeySetLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					LicenseKeySetLocalService.class.getName());

			if (invokableLocalService instanceof LicenseKeySetLocalService) {
				_service = (LicenseKeySetLocalService)invokableLocalService;
			}
			else {
				_service = new LicenseKeySetLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(LicenseKeySetLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static LicenseKeySetLocalService _service;
}