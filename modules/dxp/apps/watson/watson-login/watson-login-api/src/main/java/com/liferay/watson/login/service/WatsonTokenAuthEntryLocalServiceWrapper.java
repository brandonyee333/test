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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WatsonTokenAuthEntryLocalService}.
 *
 * @author Steven Smith
 * @see WatsonTokenAuthEntryLocalService
 * @generated
 */
public class WatsonTokenAuthEntryLocalServiceWrapper
	implements ServiceWrapper<WatsonTokenAuthEntryLocalService>,
			   WatsonTokenAuthEntryLocalService {

	public WatsonTokenAuthEntryLocalServiceWrapper(
		WatsonTokenAuthEntryLocalService watsonTokenAuthEntryLocalService) {

		_watsonTokenAuthEntryLocalService = watsonTokenAuthEntryLocalService;
	}

	@Override
	public com.liferay.watson.login.model.WatsonTokenAuthEntry
		addWatsonTokenAuthEntry(
			com.liferay.portal.kernel.model.User user, String token,
			String loginIP) {

		return _watsonTokenAuthEntryLocalService.addWatsonTokenAuthEntry(
			user, token, loginIP);
	}

	/**
	 * Adds the watson token auth entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonTokenAuthEntry the watson token auth entry
	 * @return the watson token auth entry that was added
	 */
	@Override
	public com.liferay.watson.login.model.WatsonTokenAuthEntry
		addWatsonTokenAuthEntry(
			com.liferay.watson.login.model.WatsonTokenAuthEntry
				watsonTokenAuthEntry) {

		return _watsonTokenAuthEntryLocalService.addWatsonTokenAuthEntry(
			watsonTokenAuthEntry);
	}

	/**
	 * Creates a new watson token auth entry with the primary key. Does not add the watson token auth entry to the database.
	 *
	 * @param watsonTokenAuthEntryId the primary key for the new watson token auth entry
	 * @return the new watson token auth entry
	 */
	@Override
	public com.liferay.watson.login.model.WatsonTokenAuthEntry
		createWatsonTokenAuthEntry(long watsonTokenAuthEntryId) {

		return _watsonTokenAuthEntryLocalService.createWatsonTokenAuthEntry(
			watsonTokenAuthEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonTokenAuthEntryLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the watson token auth entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonTokenAuthEntryId the primary key of the watson token auth entry
	 * @return the watson token auth entry that was removed
	 * @throws PortalException if a watson token auth entry with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.login.model.WatsonTokenAuthEntry
			deleteWatsonTokenAuthEntry(long watsonTokenAuthEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonTokenAuthEntryLocalService.deleteWatsonTokenAuthEntry(
			watsonTokenAuthEntryId);
	}

	/**
	 * Deletes the watson token auth entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonTokenAuthEntry the watson token auth entry
	 * @return the watson token auth entry that was removed
	 */
	@Override
	public com.liferay.watson.login.model.WatsonTokenAuthEntry
		deleteWatsonTokenAuthEntry(
			com.liferay.watson.login.model.WatsonTokenAuthEntry
				watsonTokenAuthEntry) {

		return _watsonTokenAuthEntryLocalService.deleteWatsonTokenAuthEntry(
			watsonTokenAuthEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonTokenAuthEntryLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _watsonTokenAuthEntryLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _watsonTokenAuthEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _watsonTokenAuthEntryLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _watsonTokenAuthEntryLocalService.dynamicQueryCount(
			dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _watsonTokenAuthEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public void extendWatsonTokenAuthEntry(
		com.liferay.portal.kernel.model.User user, String loginIP) {

		_watsonTokenAuthEntryLocalService.extendWatsonTokenAuthEntry(
			user, loginIP);
	}

	@Override
	public com.liferay.watson.login.model.WatsonTokenAuthEntry
		fetchWatsonTokenAuthEntry(long watsonTokenAuthEntryId) {

		return _watsonTokenAuthEntryLocalService.fetchWatsonTokenAuthEntry(
			watsonTokenAuthEntryId);
	}

	@Override
	public com.liferay.watson.login.model.WatsonTokenAuthEntry
		fetchWatsonTokenAuthEntry(com.liferay.portal.kernel.model.User user) {

		return _watsonTokenAuthEntryLocalService.fetchWatsonTokenAuthEntry(
			user);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonTokenAuthEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonTokenAuthEntryLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonTokenAuthEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonTokenAuthEntryLocalService.getPersistedModel(
			primaryKeyObj);
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
	@Override
	public java.util.List<com.liferay.watson.login.model.WatsonTokenAuthEntry>
		getWatsonTokenAuthEntries(int start, int end) {

		return _watsonTokenAuthEntryLocalService.getWatsonTokenAuthEntries(
			start, end);
	}

	/**
	 * Returns the number of watson token auth entries.
	 *
	 * @return the number of watson token auth entries
	 */
	@Override
	public int getWatsonTokenAuthEntriesCount() {
		return _watsonTokenAuthEntryLocalService.
			getWatsonTokenAuthEntriesCount();
	}

	/**
	 * Returns the watson token auth entry with the primary key.
	 *
	 * @param watsonTokenAuthEntryId the primary key of the watson token auth entry
	 * @return the watson token auth entry
	 * @throws PortalException if a watson token auth entry with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.login.model.WatsonTokenAuthEntry
			getWatsonTokenAuthEntry(long watsonTokenAuthEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonTokenAuthEntryLocalService.getWatsonTokenAuthEntry(
			watsonTokenAuthEntryId);
	}

	@Override
	public boolean hasAuthenticatedSession(
		com.liferay.portal.kernel.model.User user) {

		return _watsonTokenAuthEntryLocalService.hasAuthenticatedSession(user);
	}

	@Override
	public void invalidateWatsonAuthToken(
		com.liferay.portal.kernel.model.User user) {

		_watsonTokenAuthEntryLocalService.invalidateWatsonAuthToken(user);
	}

	/**
	 * Updates the watson token auth entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonTokenAuthEntry the watson token auth entry
	 * @return the watson token auth entry that was updated
	 */
	@Override
	public com.liferay.watson.login.model.WatsonTokenAuthEntry
		updateWatsonTokenAuthEntry(
			com.liferay.watson.login.model.WatsonTokenAuthEntry
				watsonTokenAuthEntry) {

		return _watsonTokenAuthEntryLocalService.updateWatsonTokenAuthEntry(
			watsonTokenAuthEntry);
	}

	@Override
	public int updateWatsonTokenAuthEntryStatus(
		com.liferay.portal.kernel.model.User user, String loginIP) {

		return _watsonTokenAuthEntryLocalService.
			updateWatsonTokenAuthEntryStatus(user, loginIP);
	}

	@Override
	public int updateWatsonTokenAuthEntryStatus(
		com.liferay.portal.kernel.model.User user, String token,
		String loginIP) {

		return _watsonTokenAuthEntryLocalService.
			updateWatsonTokenAuthEntryStatus(user, token, loginIP);
	}

	@Override
	public WatsonTokenAuthEntryLocalService getWrappedService() {
		return _watsonTokenAuthEntryLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonTokenAuthEntryLocalService watsonTokenAuthEntryLocalService) {

		_watsonTokenAuthEntryLocalService = watsonTokenAuthEntryLocalService;
	}

	private WatsonTokenAuthEntryLocalService _watsonTokenAuthEntryLocalService;

}