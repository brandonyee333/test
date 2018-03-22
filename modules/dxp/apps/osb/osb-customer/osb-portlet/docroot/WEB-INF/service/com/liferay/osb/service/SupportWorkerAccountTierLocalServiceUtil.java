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
 * Provides the local service utility for SupportWorkerAccountTier. This utility wraps
 * {@link com.liferay.osb.service.impl.SupportWorkerAccountTierLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SupportWorkerAccountTierLocalService
 * @see com.liferay.osb.service.base.SupportWorkerAccountTierLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.SupportWorkerAccountTierLocalServiceImpl
 * @generated
 */
@ProviderType
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
	*/
	public static com.liferay.osb.model.SupportWorkerAccountTier addSupportWorkerAccountTier(
		com.liferay.osb.model.SupportWorkerAccountTier supportWorkerAccountTier) {
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
	* Deletes the support worker account tier from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerAccountTier the support worker account tier
	* @return the support worker account tier that was removed
	*/
	public static com.liferay.osb.model.SupportWorkerAccountTier deleteSupportWorkerAccountTier(
		com.liferay.osb.model.SupportWorkerAccountTier supportWorkerAccountTier) {
		return getService()
				   .deleteSupportWorkerAccountTier(supportWorkerAccountTier);
	}

	/**
	* Deletes the support worker account tier with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerAccountTierId the primary key of the support worker account tier
	* @return the support worker account tier that was removed
	* @throws PortalException if a support worker account tier with the primary key could not be found
	*/
	public static com.liferay.osb.model.SupportWorkerAccountTier deleteSupportWorkerAccountTier(
		long supportWorkerAccountTierId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .deleteSupportWorkerAccountTier(supportWorkerAccountTierId);
	}

	public static com.liferay.osb.model.SupportWorkerAccountTier fetchSupportWorkerAccountTier(
		long supportWorkerAccountTierId) {
		return getService()
				   .fetchSupportWorkerAccountTier(supportWorkerAccountTierId);
	}

	/**
	* Returns the support worker account tier with the primary key.
	*
	* @param supportWorkerAccountTierId the primary key of the support worker account tier
	* @return the support worker account tier
	* @throws PortalException if a support worker account tier with the primary key could not be found
	*/
	public static com.liferay.osb.model.SupportWorkerAccountTier getSupportWorkerAccountTier(
		long supportWorkerAccountTierId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getSupportWorkerAccountTier(supportWorkerAccountTierId);
	}

	/**
	* Updates the support worker account tier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportWorkerAccountTier the support worker account tier
	* @return the support worker account tier that was updated
	*/
	public static com.liferay.osb.model.SupportWorkerAccountTier updateSupportWorkerAccountTier(
		com.liferay.osb.model.SupportWorkerAccountTier supportWorkerAccountTier) {
		return getService()
				   .updateSupportWorkerAccountTier(supportWorkerAccountTier);
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

	/**
	* Returns the number of support worker account tiers.
	*
	* @return the number of support worker account tiers
	*/
	public static int getSupportWorkerAccountTiersCount() {
		return getService().getSupportWorkerAccountTiersCount();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerAccountTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerAccountTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	/**
	* Returns a range of all the support worker account tiers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportWorkerAccountTierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support worker account tiers
	* @param end the upper bound of the range of support worker account tiers (not inclusive)
	* @return the range of support worker account tiers
	*/
	public static java.util.List<com.liferay.osb.model.SupportWorkerAccountTier> getSupportWorkerAccountTiers(
		int start, int end) {
		return getService().getSupportWorkerAccountTiers(start, end);
	}

	public static java.util.List<com.liferay.osb.model.SupportWorkerAccountTier> getSupportWorkerAccountTiers(
		long supportWorkerId) {
		return getService().getSupportWorkerAccountTiers(supportWorkerId);
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

	public static void setSupportWorkerAccountTiers(long supportWorkerId,
		int[] accountTiers) {
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

	private static SupportWorkerAccountTierLocalService _service;
}