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

import com.liferay.osb.loop.token.auth.model.TokenAuthEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

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
	public static TokenAuthEntry addTokenAuthEntry(long userId, String device)
		throws PortalException {

		return getService().addTokenAuthEntry(userId, device);
	}

	/**
	 * Adds the token auth entry to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TokenAuthEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tokenAuthEntry the token auth entry
	 * @return the token auth entry that was added
	 */
	public static TokenAuthEntry addTokenAuthEntry(
		TokenAuthEntry tokenAuthEntry) {

		return getService().addTokenAuthEntry(tokenAuthEntry);
	}

	/**
	 * Creates a new token auth entry with the primary key. Does not add the token auth entry to the database.
	 *
	 * @param tokenAuthEntryId the primary key for the new token auth entry
	 * @return the new token auth entry
	 */
	public static TokenAuthEntry createTokenAuthEntry(long tokenAuthEntryId) {
		return getService().createTokenAuthEntry(tokenAuthEntryId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the token auth entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TokenAuthEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tokenAuthEntryId the primary key of the token auth entry
	 * @return the token auth entry that was removed
	 * @throws PortalException if a token auth entry with the primary key could not be found
	 */
	public static TokenAuthEntry deleteTokenAuthEntry(long tokenAuthEntryId)
		throws PortalException {

		return getService().deleteTokenAuthEntry(tokenAuthEntryId);
	}

	/**
	 * Deletes the token auth entry from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TokenAuthEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tokenAuthEntry the token auth entry
	 * @return the token auth entry that was removed
	 */
	public static TokenAuthEntry deleteTokenAuthEntry(
		TokenAuthEntry tokenAuthEntry) {

		return getService().deleteTokenAuthEntry(tokenAuthEntry);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
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
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static TokenAuthEntry fetchTokenAuthEntry(long tokenAuthEntryId) {
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
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

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
	public static List<TokenAuthEntry> getTokenAuthEntries(int start, int end) {
		return getService().getTokenAuthEntries(start, end);
	}

	public static List<TokenAuthEntry> getTokenAuthEntries(
			int start, int end, OrderByComparator orderByComparator)
		throws PortalException {

		return getService().getTokenAuthEntries(start, end, orderByComparator);
	}

	public static List<TokenAuthEntry> getTokenAuthEntries(
			long userId, int start, int end,
			OrderByComparator orderByComparator)
		throws PortalException {

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
	public static TokenAuthEntry getTokenAuthEntry(long tokenAuthEntryId)
		throws PortalException {

		return getService().getTokenAuthEntry(tokenAuthEntryId);
	}

	public static TokenAuthEntry getTokenAuthEntry(String token)
		throws PortalException {

		return getService().getTokenAuthEntry(token);
	}

	public static void updateLoginDate(long tokenAuthEntryId, String loginIP)
		throws PortalException {

		getService().updateLoginDate(tokenAuthEntryId, loginIP);
	}

	/**
	 * Updates the token auth entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TokenAuthEntryLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param tokenAuthEntry the token auth entry
	 * @return the token auth entry that was updated
	 */
	public static TokenAuthEntry updateTokenAuthEntry(
		TokenAuthEntry tokenAuthEntry) {

		return getService().updateTokenAuthEntry(tokenAuthEntry);
	}

	public static TokenAuthEntryLocalService getService() {
		return _service;
	}

	public static void setService(TokenAuthEntryLocalService service) {
		_service = service;
	}

	private static volatile TokenAuthEntryLocalService _service;

}