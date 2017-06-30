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
 * The utility for the support worker account tier local service. This utility wraps {@link com.liferay.osb.service.impl.SupportWorkerAccountTierLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerAccountTierLocalService
 * @see com.liferay.osb.service.base.SupportWorkerAccountTierLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.SupportWorkerAccountTierLocalServiceImpl
 * @generated
 */
public class SupportWorkerAccountTierLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.SupportWorkerAccountTierLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the support worker account tier to the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerAccountTier the support worker account tier
	* @return the support worker account tier that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerAccountTier addSupportWorkerAccountTier(
		com.liferay.osb.model.SupportWorkerAccountTier supportWorkerAccountTier)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSupportWorkerAccountTier(supportWorkerAccountTier);
	}

	/**
	* Creates a new support worker account tier with the primary key. Does not add the support worker account tier to the database.
	*
	* @param supportWorkerAccountTierId the primary key for the new support worker account tier
	* @return the new support worker account tier
	*/
	public static com.liferay.osb.model.SupportWorkerAccountTier createSupportWorkerAccountTier(
		long supportWorkerAccountTierId) {
		return getService()
				   .createSupportWorkerAccountTier(supportWorkerAccountTierId);
	}

	/**
	* Deletes the support worker account tier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerAccountTierId the primary key of the support worker account tier
	* @return the support worker account tier that was removed
	* @throws PortalException if a support worker account tier with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerAccountTier deleteSupportWorkerAccountTier(
		long supportWorkerAccountTierId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteSupportWorkerAccountTier(supportWorkerAccountTierId);
	}

	/**
	* Deletes the support worker account tier from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerAccountTier the support worker account tier
	* @return the support worker account tier that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerAccountTier deleteSupportWorkerAccountTier(
		com.liferay.osb.model.SupportWorkerAccountTier supportWorkerAccountTier)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteSupportWorkerAccountTier(supportWorkerAccountTier);
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

	public static com.liferay.osb.model.SupportWorkerAccountTier fetchSupportWorkerAccountTier(
		long supportWorkerAccountTierId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchSupportWorkerAccountTier(supportWorkerAccountTierId);
	}

	/**
	* Returns the support worker account tier with the primary key.
	*
	* @param supportWorkerAccountTierId the primary key of the support worker account tier
	* @return the support worker account tier
	* @throws PortalException if a support worker account tier with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerAccountTier getSupportWorkerAccountTier(
		long supportWorkerAccountTierId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSupportWorkerAccountTier(supportWorkerAccountTierId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the support worker account tiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support worker account tiers
	* @param end the upper bound of the range of support worker account tiers (not inclusive)
	* @return the range of support worker account tiers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorkerAccountTier> getSupportWorkerAccountTiers(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportWorkerAccountTiers(start, end);
	}

	/**
	* Returns the number of support worker account tiers.
	*
	* @return the number of support worker account tiers
	* @throws SystemException if a system exception occurred
	*/
	public static int getSupportWorkerAccountTiersCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportWorkerAccountTiersCount();
	}

	/**
	* Updates the support worker account tier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerAccountTier the support worker account tier
	* @return the support worker account tier that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerAccountTier updateSupportWorkerAccountTier(
		com.liferay.osb.model.SupportWorkerAccountTier supportWorkerAccountTier)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateSupportWorkerAccountTier(supportWorkerAccountTier);
	}

	/**
	* Updates the support worker account tier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerAccountTier the support worker account tier
	* @param merge whether to merge the support worker account tier with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the support worker account tier that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportWorkerAccountTier updateSupportWorkerAccountTier(
		com.liferay.osb.model.SupportWorkerAccountTier supportWorkerAccountTier,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateSupportWorkerAccountTier(supportWorkerAccountTier,
			merge);
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

	public static java.util.List<com.liferay.osb.model.SupportWorkerAccountTier> getSupportWorkerAccountTiers(
		long supportWorkerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportWorkerAccountTiers(supportWorkerId);
	}

	public static void setSupportWorkerAccountTiers(long supportWorkerId,
		int[] accountTiers)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().setSupportWorkerAccountTiers(supportWorkerId, accountTiers);
	}

	public static void clearService() {
		_service = null;
	}

	public static SupportWorkerAccountTierLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SupportWorkerAccountTierLocalService.class.getName());

			if (invokableLocalService instanceof SupportWorkerAccountTierLocalService) {
				_service = (SupportWorkerAccountTierLocalService)invokableLocalService;
			}
			else {
				_service = new SupportWorkerAccountTierLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SupportWorkerAccountTierLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(SupportWorkerAccountTierLocalService service) {
	}

	private static SupportWorkerAccountTierLocalService _service;
}