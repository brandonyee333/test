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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TokenAuthEntryLocalService}.
 *
 * @author Bruno Farache
 * @see TokenAuthEntryLocalService
 * @generated
 */
public class TokenAuthEntryLocalServiceWrapper
	implements ServiceWrapper<TokenAuthEntryLocalService>,
			   TokenAuthEntryLocalService {

	public TokenAuthEntryLocalServiceWrapper(
		TokenAuthEntryLocalService tokenAuthEntryLocalService) {

		_tokenAuthEntryLocalService = tokenAuthEntryLocalService;
	}

	@Override
	public com.liferay.osb.loop.token.auth.model.TokenAuthEntry
			addTokenAuthEntry(long userId, String device)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tokenAuthEntryLocalService.addTokenAuthEntry(userId, device);
	}

	/**
	 * Adds the token auth entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param tokenAuthEntry the token auth entry
	 * @return the token auth entry that was added
	 */
	@Override
	public com.liferay.osb.loop.token.auth.model.TokenAuthEntry
		addTokenAuthEntry(
			com.liferay.osb.loop.token.auth.model.TokenAuthEntry
				tokenAuthEntry) {

		return _tokenAuthEntryLocalService.addTokenAuthEntry(tokenAuthEntry);
	}

	/**
	 * Creates a new token auth entry with the primary key. Does not add the token auth entry to the database.
	 *
	 * @param tokenAuthEntryId the primary key for the new token auth entry
	 * @return the new token auth entry
	 */
	@Override
	public com.liferay.osb.loop.token.auth.model.TokenAuthEntry
		createTokenAuthEntry(long tokenAuthEntryId) {

		return _tokenAuthEntryLocalService.createTokenAuthEntry(
			tokenAuthEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tokenAuthEntryLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the token auth entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tokenAuthEntryId the primary key of the token auth entry
	 * @return the token auth entry that was removed
	 * @throws PortalException if a token auth entry with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.loop.token.auth.model.TokenAuthEntry
			deleteTokenAuthEntry(long tokenAuthEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tokenAuthEntryLocalService.deleteTokenAuthEntry(
			tokenAuthEntryId);
	}

	/**
	 * Deletes the token auth entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param tokenAuthEntry the token auth entry
	 * @return the token auth entry that was removed
	 */
	@Override
	public com.liferay.osb.loop.token.auth.model.TokenAuthEntry
		deleteTokenAuthEntry(
			com.liferay.osb.loop.token.auth.model.TokenAuthEntry
				tokenAuthEntry) {

		return _tokenAuthEntryLocalService.deleteTokenAuthEntry(tokenAuthEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _tokenAuthEntryLocalService.dynamicQuery();
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

		return _tokenAuthEntryLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _tokenAuthEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _tokenAuthEntryLocalService.dynamicQuery(
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

		return _tokenAuthEntryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _tokenAuthEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.loop.token.auth.model.TokenAuthEntry
		fetchTokenAuthEntry(long tokenAuthEntryId) {

		return _tokenAuthEntryLocalService.fetchTokenAuthEntry(
			tokenAuthEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _tokenAuthEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _tokenAuthEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _tokenAuthEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tokenAuthEntryLocalService.getPersistedModel(primaryKeyObj);
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
	@Override
	public java.util.List<com.liferay.osb.loop.token.auth.model.TokenAuthEntry>
		getTokenAuthEntries(int start, int end) {

		return _tokenAuthEntryLocalService.getTokenAuthEntries(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.loop.token.auth.model.TokenAuthEntry>
			getTokenAuthEntries(
				int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tokenAuthEntryLocalService.getTokenAuthEntries(
			start, end, orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.loop.token.auth.model.TokenAuthEntry>
			getTokenAuthEntries(
				long userId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tokenAuthEntryLocalService.getTokenAuthEntries(
			userId, start, end, orderByComparator);
	}

	/**
	 * Returns the number of token auth entries.
	 *
	 * @return the number of token auth entries
	 */
	@Override
	public int getTokenAuthEntriesCount() {
		return _tokenAuthEntryLocalService.getTokenAuthEntriesCount();
	}

	@Override
	public int getTokenAuthEntriesCount(long userId) {
		return _tokenAuthEntryLocalService.getTokenAuthEntriesCount(userId);
	}

	/**
	 * Returns the token auth entry with the primary key.
	 *
	 * @param tokenAuthEntryId the primary key of the token auth entry
	 * @return the token auth entry
	 * @throws PortalException if a token auth entry with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.loop.token.auth.model.TokenAuthEntry
			getTokenAuthEntry(long tokenAuthEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tokenAuthEntryLocalService.getTokenAuthEntry(tokenAuthEntryId);
	}

	@Override
	public com.liferay.osb.loop.token.auth.model.TokenAuthEntry
			getTokenAuthEntry(String token)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _tokenAuthEntryLocalService.getTokenAuthEntry(token);
	}

	@Override
	public void updateLoginDate(long tokenAuthEntryId, String loginIP)
		throws com.liferay.portal.kernel.exception.PortalException {

		_tokenAuthEntryLocalService.updateLoginDate(tokenAuthEntryId, loginIP);
	}

	/**
	 * Updates the token auth entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param tokenAuthEntry the token auth entry
	 * @return the token auth entry that was updated
	 */
	@Override
	public com.liferay.osb.loop.token.auth.model.TokenAuthEntry
		updateTokenAuthEntry(
			com.liferay.osb.loop.token.auth.model.TokenAuthEntry
				tokenAuthEntry) {

		return _tokenAuthEntryLocalService.updateTokenAuthEntry(tokenAuthEntry);
	}

	@Override
	public TokenAuthEntryLocalService getWrappedService() {
		return _tokenAuthEntryLocalService;
	}

	@Override
	public void setWrappedService(
		TokenAuthEntryLocalService tokenAuthEntryLocalService) {

		_tokenAuthEntryLocalService = tokenAuthEntryLocalService;
	}

	private TokenAuthEntryLocalService _tokenAuthEntryLocalService;

}