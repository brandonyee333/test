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

package com.liferay.watson.login.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for WatsonTokenAuthEntry. This utility wraps
 * <code>com.liferay.watson.login.service.impl.WatsonTokenAuthEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Steven Smith
 * @see WatsonTokenAuthEntryLocalService
 * @generated
 */
public class WatsonTokenAuthEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.watson.login.service.impl.WatsonTokenAuthEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.watson.login.model.WatsonTokenAuthEntry
		addWatsonTokenAuthEntry(
			com.liferay.portal.kernel.model.User user, String token,
			String loginIP) {

		return getService().addWatsonTokenAuthEntry(user, token, loginIP);
	}

	/**
	 * Adds the watson token auth entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonTokenAuthEntry the watson token auth entry
	 * @return the watson token auth entry that was added
	 */
	public static com.liferay.watson.login.model.WatsonTokenAuthEntry
		addWatsonTokenAuthEntry(
			com.liferay.watson.login.model.WatsonTokenAuthEntry
				watsonTokenAuthEntry) {

		return getService().addWatsonTokenAuthEntry(watsonTokenAuthEntry);
	}

	/**
	 * Creates a new watson token auth entry with the primary key. Does not add the watson token auth entry to the database.
	 *
	 * @param watsonTokenAuthEntryId the primary key for the new watson token auth entry
	 * @return the new watson token auth entry
	 */
	public static com.liferay.watson.login.model.WatsonTokenAuthEntry
		createWatsonTokenAuthEntry(long watsonTokenAuthEntryId) {

		return getService().createWatsonTokenAuthEntry(watsonTokenAuthEntryId);
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

	/**
	 * Deletes the watson token auth entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonTokenAuthEntryId the primary key of the watson token auth entry
	 * @return the watson token auth entry that was removed
	 * @throws PortalException if a watson token auth entry with the primary key could not be found
	 */
	public static com.liferay.watson.login.model.WatsonTokenAuthEntry
			deleteWatsonTokenAuthEntry(long watsonTokenAuthEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteWatsonTokenAuthEntry(watsonTokenAuthEntryId);
	}

	/**
	 * Deletes the watson token auth entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonTokenAuthEntry the watson token auth entry
	 * @return the watson token auth entry that was removed
	 */
	public static com.liferay.watson.login.model.WatsonTokenAuthEntry
		deleteWatsonTokenAuthEntry(
			com.liferay.watson.login.model.WatsonTokenAuthEntry
				watsonTokenAuthEntry) {

		return getService().deleteWatsonTokenAuthEntry(watsonTokenAuthEntry);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.login.model.impl.WatsonTokenAuthEntryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.login.model.impl.WatsonTokenAuthEntryModelImpl</code>.
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

	public static void extendWatsonTokenAuthEntry(
		com.liferay.portal.kernel.model.User user, String loginIP) {

		getService().extendWatsonTokenAuthEntry(user, loginIP);
	}

	public static com.liferay.watson.login.model.WatsonTokenAuthEntry
		fetchWatsonTokenAuthEntry(long watsonTokenAuthEntryId) {

		return getService().fetchWatsonTokenAuthEntry(watsonTokenAuthEntryId);
	}

	public static com.liferay.watson.login.model.WatsonTokenAuthEntry
		fetchWatsonTokenAuthEntry(com.liferay.portal.kernel.model.User user) {

		return getService().fetchWatsonTokenAuthEntry(user);
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
	 * Returns a range of all the watson token auth entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.login.model.impl.WatsonTokenAuthEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson token auth entries
	 * @param end the upper bound of the range of watson token auth entries (not inclusive)
	 * @return the range of watson token auth entries
	 */
	public static java.util.List
		<com.liferay.watson.login.model.WatsonTokenAuthEntry>
			getWatsonTokenAuthEntries(int start, int end) {

		return getService().getWatsonTokenAuthEntries(start, end);
	}

	/**
	 * Returns the number of watson token auth entries.
	 *
	 * @return the number of watson token auth entries
	 */
	public static int getWatsonTokenAuthEntriesCount() {
		return getService().getWatsonTokenAuthEntriesCount();
	}

	/**
	 * Returns the watson token auth entry with the primary key.
	 *
	 * @param watsonTokenAuthEntryId the primary key of the watson token auth entry
	 * @return the watson token auth entry
	 * @throws PortalException if a watson token auth entry with the primary key could not be found
	 */
	public static com.liferay.watson.login.model.WatsonTokenAuthEntry
			getWatsonTokenAuthEntry(long watsonTokenAuthEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getWatsonTokenAuthEntry(watsonTokenAuthEntryId);
	}

	public static boolean hasAuthenticatedSession(
		com.liferay.portal.kernel.model.User user) {

		return getService().hasAuthenticatedSession(user);
	}

	public static void invalidateWatsonAuthToken(
		com.liferay.portal.kernel.model.User user) {

		getService().invalidateWatsonAuthToken(user);
	}

	/**
	 * Updates the watson token auth entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonTokenAuthEntry the watson token auth entry
	 * @return the watson token auth entry that was updated
	 */
	public static com.liferay.watson.login.model.WatsonTokenAuthEntry
		updateWatsonTokenAuthEntry(
			com.liferay.watson.login.model.WatsonTokenAuthEntry
				watsonTokenAuthEntry) {

		return getService().updateWatsonTokenAuthEntry(watsonTokenAuthEntry);
	}

	public static int updateWatsonTokenAuthEntryStatus(
		com.liferay.portal.kernel.model.User user, String loginIP) {

		return getService().updateWatsonTokenAuthEntryStatus(user, loginIP);
	}

	public static int updateWatsonTokenAuthEntryStatus(
		com.liferay.portal.kernel.model.User user, String token,
		String loginIP) {

		return getService().updateWatsonTokenAuthEntryStatus(
			user, token, loginIP);
	}

	public static WatsonTokenAuthEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<WatsonTokenAuthEntryLocalService, WatsonTokenAuthEntryLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			WatsonTokenAuthEntryLocalService.class);

		ServiceTracker
			<WatsonTokenAuthEntryLocalService, WatsonTokenAuthEntryLocalService>
				serviceTracker =
					new ServiceTracker
						<WatsonTokenAuthEntryLocalService,
						 WatsonTokenAuthEntryLocalService>(
							 bundle.getBundleContext(),
							 WatsonTokenAuthEntryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}