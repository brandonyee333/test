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

package com.liferay.osb.loop.token.auth.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for TokenAuthEntry. This utility wraps
 * <code>com.liferay.osb.loop.token.auth.service.impl.TokenAuthEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Bruno Farache
 * @see TokenAuthEntryLocalService
 * @generated
 */
public class TokenAuthEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.loop.token.auth.service.impl.TokenAuthEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.osb.loop.token.auth.model.TokenAuthEntry
			addTokenAuthEntry(long userId, String device)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addTokenAuthEntry(userId, device);
	}

	/**
	 * Adds the token auth entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param tokenAuthEntry the token auth entry
	 * @return the token auth entry that was added
	 */
	public static com.liferay.osb.loop.token.auth.model.TokenAuthEntry
		addTokenAuthEntry(
			com.liferay.osb.loop.token.auth.model.TokenAuthEntry
				tokenAuthEntry) {

		return getService().addTokenAuthEntry(tokenAuthEntry);
	}

	/**
	 * Creates a new token auth entry with the primary key. Does not add the token auth entry to the database.
	 *
	 * @param tokenAuthEntryId the primary key for the new token auth entry
	 * @return the new token auth entry
	 */
	public static com.liferay.osb.loop.token.auth.model.TokenAuthEntry
		createTokenAuthEntry(long tokenAuthEntryId) {

		return getService().createTokenAuthEntry(tokenAuthEntryId);
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
	 * Deletes the token auth entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tokenAuthEntryId the primary key of the token auth entry
	 * @return the token auth entry that was removed
	 * @throws PortalException if a token auth entry with the primary key could not be found
	 */
	public static com.liferay.osb.loop.token.auth.model.TokenAuthEntry
			deleteTokenAuthEntry(long tokenAuthEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteTokenAuthEntry(tokenAuthEntryId);
	}

	/**
	 * Deletes the token auth entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tokenAuthEntry the token auth entry
	 * @return the token auth entry that was removed
	 */
	public static com.liferay.osb.loop.token.auth.model.TokenAuthEntry
		deleteTokenAuthEntry(
			com.liferay.osb.loop.token.auth.model.TokenAuthEntry
				tokenAuthEntry) {

		return getService().deleteTokenAuthEntry(tokenAuthEntry);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.token.auth.model.impl.TokenAuthEntryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.token.auth.model.impl.TokenAuthEntryModelImpl</code>.
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

	public static com.liferay.osb.loop.token.auth.model.TokenAuthEntry
		fetchTokenAuthEntry(long tokenAuthEntryId) {

		return getService().fetchTokenAuthEntry(tokenAuthEntryId);
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
	 * Returns a range of all the token auth entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.token.auth.model.impl.TokenAuthEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of token auth entries
	 * @param end the upper bound of the range of token auth entries (not inclusive)
	 * @return the range of token auth entries
	 */
	public static java.util.List
		<com.liferay.osb.loop.token.auth.model.TokenAuthEntry>
			getTokenAuthEntries(int start, int end) {

		return getService().getTokenAuthEntries(start, end);
	}

	public static java.util.List
		<com.liferay.osb.loop.token.auth.model.TokenAuthEntry>
				getTokenAuthEntries(
					int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTokenAuthEntries(start, end, orderByComparator);
	}

	public static java.util.List
		<com.liferay.osb.loop.token.auth.model.TokenAuthEntry>
				getTokenAuthEntries(
					long userId, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTokenAuthEntries(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of token auth entries.
	 *
	 * @return the number of token auth entries
	 */
	public static int getTokenAuthEntriesCount() {
		return getService().getTokenAuthEntriesCount();
	}

	public static int getTokenAuthEntriesCount(long userId) {
		return getService().getTokenAuthEntriesCount(userId);
	}

	/**
	 * Returns the token auth entry with the primary key.
	 *
	 * @param tokenAuthEntryId the primary key of the token auth entry
	 * @return the token auth entry
	 * @throws PortalException if a token auth entry with the primary key could not be found
	 */
	public static com.liferay.osb.loop.token.auth.model.TokenAuthEntry
			getTokenAuthEntry(long tokenAuthEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTokenAuthEntry(tokenAuthEntryId);
	}

	public static com.liferay.osb.loop.token.auth.model.TokenAuthEntry
			getTokenAuthEntry(String token)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTokenAuthEntry(token);
	}

	public static void updateLoginDate(long tokenAuthEntryId, String loginIP)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateLoginDate(tokenAuthEntryId, loginIP);
	}

	/**
	 * Updates the token auth entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tokenAuthEntry the token auth entry
	 * @return the token auth entry that was updated
	 */
	public static com.liferay.osb.loop.token.auth.model.TokenAuthEntry
		updateTokenAuthEntry(
			com.liferay.osb.loop.token.auth.model.TokenAuthEntry
				tokenAuthEntry) {

		return getService().updateTokenAuthEntry(tokenAuthEntry);
	}

	public static TokenAuthEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TokenAuthEntryLocalService, TokenAuthEntryLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			TokenAuthEntryLocalService.class);

		ServiceTracker<TokenAuthEntryLocalService, TokenAuthEntryLocalService>
			serviceTracker =
				new ServiceTracker
					<TokenAuthEntryLocalService, TokenAuthEntryLocalService>(
						bundle.getBundleContext(),
						TokenAuthEntryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}