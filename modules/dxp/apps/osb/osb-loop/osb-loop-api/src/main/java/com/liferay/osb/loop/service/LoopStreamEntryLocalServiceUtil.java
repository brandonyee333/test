/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.loop.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for LoopStreamEntry. This utility wraps
 * <code>com.liferay.osb.loop.service.impl.LoopStreamEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see LoopStreamEntryLocalService
 * @generated
 */
public class LoopStreamEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.loop.service.impl.LoopStreamEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the loop stream entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopStreamEntry the loop stream entry
	 * @return the loop stream entry that was added
	 */
	public static com.liferay.osb.loop.model.LoopStreamEntry addLoopStreamEntry(
		com.liferay.osb.loop.model.LoopStreamEntry loopStreamEntry) {

		return getService().addLoopStreamEntry(loopStreamEntry);
	}

	/**
	 * Creates a new loop stream entry with the primary key. Does not add the loop stream entry to the database.
	 *
	 * @param loopStreamEntryId the primary key for the new loop stream entry
	 * @return the new loop stream entry
	 */
	public static com.liferay.osb.loop.model.LoopStreamEntry
		createLoopStreamEntry(long loopStreamEntryId) {

		return getService().createLoopStreamEntry(loopStreamEntryId);
	}

	/**
	 * Deletes the loop stream entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopStreamEntryId the primary key of the loop stream entry
	 * @return the loop stream entry that was removed
	 * @throws PortalException if a loop stream entry with the primary key could not be found
	 */
	public static com.liferay.osb.loop.model.LoopStreamEntry
			deleteLoopStreamEntry(long loopStreamEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteLoopStreamEntry(loopStreamEntryId);
	}

	/**
	 * Deletes the loop stream entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopStreamEntry the loop stream entry
	 * @return the loop stream entry that was removed
	 */
	public static com.liferay.osb.loop.model.LoopStreamEntry
		deleteLoopStreamEntry(
			com.liferay.osb.loop.model.LoopStreamEntry loopStreamEntry) {

		return getService().deleteLoopStreamEntry(loopStreamEntry);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopStreamEntryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopStreamEntryModelImpl</code>.
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

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

	public static com.liferay.osb.loop.model.LoopStreamEntry
		fetchLoopStreamEntry(long loopStreamEntryId) {

		return getService().fetchLoopStreamEntry(loopStreamEntryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the loop stream entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopStreamEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop stream entries
	 * @param end the upper bound of the range of loop stream entries (not inclusive)
	 * @return the range of loop stream entries
	 */
	public static java.util.List<com.liferay.osb.loop.model.LoopStreamEntry>
		getLoopStreamEntries(int start, int end) {

		return getService().getLoopStreamEntries(start, end);
	}

	/**
	 * Returns the number of loop stream entries.
	 *
	 * @return the number of loop stream entries
	 */
	public static int getLoopStreamEntriesCount() {
		return getService().getLoopStreamEntriesCount();
	}

	/**
	 * Returns the loop stream entry with the primary key.
	 *
	 * @param loopStreamEntryId the primary key of the loop stream entry
	 * @return the loop stream entry
	 * @throws PortalException if a loop stream entry with the primary key could not be found
	 */
	public static com.liferay.osb.loop.model.LoopStreamEntry getLoopStreamEntry(
			long loopStreamEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLoopStreamEntry(loopStreamEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the loop stream entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param loopStreamEntry the loop stream entry
	 * @return the loop stream entry that was updated
	 */
	public static com.liferay.osb.loop.model.LoopStreamEntry
		updateLoopStreamEntry(
			com.liferay.osb.loop.model.LoopStreamEntry loopStreamEntry) {

		return getService().updateLoopStreamEntry(loopStreamEntry);
	}

	public static LoopStreamEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<LoopStreamEntryLocalService, LoopStreamEntryLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			LoopStreamEntryLocalService.class);

		ServiceTracker<LoopStreamEntryLocalService, LoopStreamEntryLocalService>
			serviceTracker =
				new ServiceTracker
					<LoopStreamEntryLocalService, LoopStreamEntryLocalService>(
						bundle.getBundleContext(),
						LoopStreamEntryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}