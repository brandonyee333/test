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

package com.liferay.osb.email.blacklist.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for BlacklistEntry. This utility wraps
 * <code>com.liferay.osb.email.blacklist.service.impl.BlacklistEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Jamie Sammons
 * @see BlacklistEntryLocalService
 * @generated
 */
public class BlacklistEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.email.blacklist.service.impl.BlacklistEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the blacklist entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param blacklistEntry the blacklist entry
	 * @return the blacklist entry that was added
	 */
	public static com.liferay.osb.email.blacklist.model.BlacklistEntry
		addBlacklistEntry(
			com.liferay.osb.email.blacklist.model.BlacklistEntry
				blacklistEntry) {

		return getService().addBlacklistEntry(blacklistEntry);
	}

	public static com.liferay.osb.email.blacklist.model.BlacklistEntry
			addBlacklistEntry(String emailAddress)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addBlacklistEntry(emailAddress);
	}

	/**
	 * Creates a new blacklist entry with the primary key. Does not add the blacklist entry to the database.
	 *
	 * @param blacklistEntryId the primary key for the new blacklist entry
	 * @return the new blacklist entry
	 */
	public static com.liferay.osb.email.blacklist.model.BlacklistEntry
		createBlacklistEntry(long blacklistEntryId) {

		return getService().createBlacklistEntry(blacklistEntryId);
	}

	/**
	 * Deletes the blacklist entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param blacklistEntry the blacklist entry
	 * @return the blacklist entry that was removed
	 */
	public static com.liferay.osb.email.blacklist.model.BlacklistEntry
		deleteBlacklistEntry(
			com.liferay.osb.email.blacklist.model.BlacklistEntry
				blacklistEntry) {

		return getService().deleteBlacklistEntry(blacklistEntry);
	}

	/**
	 * Deletes the blacklist entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param blacklistEntryId the primary key of the blacklist entry
	 * @return the blacklist entry that was removed
	 * @throws PortalException if a blacklist entry with the primary key could not be found
	 */
	public static com.liferay.osb.email.blacklist.model.BlacklistEntry
			deleteBlacklistEntry(long blacklistEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteBlacklistEntry(blacklistEntryId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.email.blacklist.model.impl.BlacklistEntryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.email.blacklist.model.impl.BlacklistEntryModelImpl</code>.
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

	public static com.liferay.osb.email.blacklist.model.BlacklistEntry
		fetchBlacklistEntry(long blacklistEntryId) {

		return getService().fetchBlacklistEntry(blacklistEntryId);
	}

	public static com.liferay.osb.email.blacklist.model.BlacklistEntry
		fetchBlacklistEntry(String emailAddress) {

		return getService().fetchBlacklistEntry(emailAddress);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the blacklist entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.email.blacklist.model.impl.BlacklistEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of blacklist entries
	 * @param end the upper bound of the range of blacklist entries (not inclusive)
	 * @return the range of blacklist entries
	 */
	public static java.util.List
		<com.liferay.osb.email.blacklist.model.BlacklistEntry>
			getBlacklistEntries(int start, int end) {

		return getService().getBlacklistEntries(start, end);
	}

	/**
	 * Returns the number of blacklist entries.
	 *
	 * @return the number of blacklist entries
	 */
	public static int getBlacklistEntriesCount() {
		return getService().getBlacklistEntriesCount();
	}

	/**
	 * Returns the blacklist entry with the primary key.
	 *
	 * @param blacklistEntryId the primary key of the blacklist entry
	 * @return the blacklist entry
	 * @throws PortalException if a blacklist entry with the primary key could not be found
	 */
	public static com.liferay.osb.email.blacklist.model.BlacklistEntry
			getBlacklistEntry(long blacklistEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getBlacklistEntry(blacklistEntryId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the blacklist entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param blacklistEntry the blacklist entry
	 * @return the blacklist entry that was updated
	 */
	public static com.liferay.osb.email.blacklist.model.BlacklistEntry
		updateBlacklistEntry(
			com.liferay.osb.email.blacklist.model.BlacklistEntry
				blacklistEntry) {

		return getService().updateBlacklistEntry(blacklistEntry);
	}

	public static BlacklistEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<BlacklistEntryLocalService, BlacklistEntryLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			BlacklistEntryLocalService.class);

		ServiceTracker<BlacklistEntryLocalService, BlacklistEntryLocalService>
			serviceTracker =
				new ServiceTracker
					<BlacklistEntryLocalService, BlacklistEntryLocalService>(
						bundle.getBundleContext(),
						BlacklistEntryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}