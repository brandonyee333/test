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

package com.liferay.osb.loop.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for LoopAuditEntry. This utility wraps
 * {@link com.liferay.osb.loop.service.impl.LoopAuditEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see LoopAuditEntryLocalService
 * @see com.liferay.osb.loop.service.base.LoopAuditEntryLocalServiceBaseImpl
 * @see com.liferay.osb.loop.service.impl.LoopAuditEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class LoopAuditEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.loop.service.impl.LoopAuditEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the loop audit entry to the database. Also notifies the appropriate model listeners.
	*
	* @param loopAuditEntry the loop audit entry
	* @return the loop audit entry that was added
	*/
	public static com.liferay.osb.loop.model.LoopAuditEntry addLoopAuditEntry(
		com.liferay.osb.loop.model.LoopAuditEntry loopAuditEntry) {
		return getService().addLoopAuditEntry(loopAuditEntry);
	}

	/**
	* Creates a new loop audit entry with the primary key. Does not add the loop audit entry to the database.
	*
	* @param loopAuditEntryId the primary key for the new loop audit entry
	* @return the new loop audit entry
	*/
	public static com.liferay.osb.loop.model.LoopAuditEntry createLoopAuditEntry(
		long loopAuditEntryId) {
		return getService().createLoopAuditEntry(loopAuditEntryId);
	}

	/**
	* Deletes the loop audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopAuditEntryId the primary key of the loop audit entry
	* @return the loop audit entry that was removed
	* @throws PortalException if a loop audit entry with the primary key could not be found
	*/
	public static com.liferay.osb.loop.model.LoopAuditEntry deleteLoopAuditEntry(
		long loopAuditEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteLoopAuditEntry(loopAuditEntryId);
	}

	/**
	* Deletes the loop audit entry from the database. Also notifies the appropriate model listeners.
	*
	* @param loopAuditEntry the loop audit entry
	* @return the loop audit entry that was removed
	*/
	public static com.liferay.osb.loop.model.LoopAuditEntry deleteLoopAuditEntry(
		com.liferay.osb.loop.model.LoopAuditEntry loopAuditEntry) {
		return getService().deleteLoopAuditEntry(loopAuditEntry);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.osb.loop.model.LoopAuditEntry fetchLoopAuditEntry(
		long loopAuditEntryId) {
		return getService().fetchLoopAuditEntry(loopAuditEntryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns a range of all the loop audit entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopAuditEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop audit entries
	* @param end the upper bound of the range of loop audit entries (not inclusive)
	* @return the range of loop audit entries
	*/
	public static java.util.List<com.liferay.osb.loop.model.LoopAuditEntry> getLoopAuditEntries(
		int start, int end) {
		return getService().getLoopAuditEntries(start, end);
	}

	/**
	* Returns the number of loop audit entries.
	*
	* @return the number of loop audit entries
	*/
	public static int getLoopAuditEntriesCount() {
		return getService().getLoopAuditEntriesCount();
	}

	/**
	* Returns the loop audit entry with the primary key.
	*
	* @param loopAuditEntryId the primary key of the loop audit entry
	* @return the loop audit entry
	* @throws PortalException if a loop audit entry with the primary key could not be found
	*/
	public static com.liferay.osb.loop.model.LoopAuditEntry getLoopAuditEntry(
		long loopAuditEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getLoopAuditEntry(loopAuditEntryId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the loop audit entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param loopAuditEntry the loop audit entry
	* @return the loop audit entry that was updated
	*/
	public static com.liferay.osb.loop.model.LoopAuditEntry updateLoopAuditEntry(
		com.liferay.osb.loop.model.LoopAuditEntry loopAuditEntry) {
		return getService().updateLoopAuditEntry(loopAuditEntry);
	}

	public static LoopAuditEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<LoopAuditEntryLocalService, LoopAuditEntryLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(LoopAuditEntryLocalService.class);

		ServiceTracker<LoopAuditEntryLocalService, LoopAuditEntryLocalService> serviceTracker =
			new ServiceTracker<LoopAuditEntryLocalService, LoopAuditEntryLocalService>(bundle.getBundleContext(),
				LoopAuditEntryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}