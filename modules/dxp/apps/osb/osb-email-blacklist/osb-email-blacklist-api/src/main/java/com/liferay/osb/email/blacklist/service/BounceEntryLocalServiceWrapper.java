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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BounceEntryLocalService}.
 *
 * @author Jamie Sammons
 * @see BounceEntryLocalService
 * @generated
 */
public class BounceEntryLocalServiceWrapper
	implements BounceEntryLocalService,
			   ServiceWrapper<BounceEntryLocalService> {

	public BounceEntryLocalServiceWrapper(
		BounceEntryLocalService bounceEntryLocalService) {

		_bounceEntryLocalService = bounceEntryLocalService;
	}

	/**
	 * Adds the bounce entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param bounceEntry the bounce entry
	 * @return the bounce entry that was added
	 */
	@Override
	public com.liferay.osb.email.blacklist.model.BounceEntry addBounceEntry(
		com.liferay.osb.email.blacklist.model.BounceEntry bounceEntry) {

		return _bounceEntryLocalService.addBounceEntry(bounceEntry);
	}

	@Override
	public com.liferay.osb.email.blacklist.model.BounceEntry addBounceEntry(
			String emailAddress, java.util.Date bounceDate, String bounceType,
			String bounceSubtype)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bounceEntryLocalService.addBounceEntry(
			emailAddress, bounceDate, bounceType, bounceSubtype);
	}

	/**
	 * Creates a new bounce entry with the primary key. Does not add the bounce entry to the database.
	 *
	 * @param bounceEntryId the primary key for the new bounce entry
	 * @return the new bounce entry
	 */
	@Override
	public com.liferay.osb.email.blacklist.model.BounceEntry createBounceEntry(
		long bounceEntryId) {

		return _bounceEntryLocalService.createBounceEntry(bounceEntryId);
	}

	/**
	 * Deletes the bounce entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param bounceEntry the bounce entry
	 * @return the bounce entry that was removed
	 */
	@Override
	public com.liferay.osb.email.blacklist.model.BounceEntry deleteBounceEntry(
		com.liferay.osb.email.blacklist.model.BounceEntry bounceEntry) {

		return _bounceEntryLocalService.deleteBounceEntry(bounceEntry);
	}

	/**
	 * Deletes the bounce entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param bounceEntryId the primary key of the bounce entry
	 * @return the bounce entry that was removed
	 * @throws PortalException if a bounce entry with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.email.blacklist.model.BounceEntry deleteBounceEntry(
			long bounceEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bounceEntryLocalService.deleteBounceEntry(bounceEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bounceEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _bounceEntryLocalService.dynamicQuery();
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

		return _bounceEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.email.blacklist.model.impl.BounceEntryModelImpl</code>.
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

		return _bounceEntryLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.email.blacklist.model.impl.BounceEntryModelImpl</code>.
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

		return _bounceEntryLocalService.dynamicQuery(
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

		return _bounceEntryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _bounceEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.email.blacklist.model.BounceEntry fetchBounceEntry(
		long bounceEntryId) {

		return _bounceEntryLocalService.fetchBounceEntry(bounceEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _bounceEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public java.util.List<com.liferay.osb.email.blacklist.model.BounceEntry>
		getBounceEntries(
			java.util.Date bounceDateLT, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.email.blacklist.model.BounceEntry> obc) {

		return _bounceEntryLocalService.getBounceEntries(
			bounceDateLT, start, end, obc);
	}

	/**
	 * Returns a range of all the bounce entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.email.blacklist.model.impl.BounceEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of bounce entries
	 * @param end the upper bound of the range of bounce entries (not inclusive)
	 * @return the range of bounce entries
	 */
	@Override
	public java.util.List<com.liferay.osb.email.blacklist.model.BounceEntry>
		getBounceEntries(int start, int end) {

		return _bounceEntryLocalService.getBounceEntries(start, end);
	}

	/**
	 * Returns the number of bounce entries.
	 *
	 * @return the number of bounce entries
	 */
	@Override
	public int getBounceEntriesCount() {
		return _bounceEntryLocalService.getBounceEntriesCount();
	}

	/**
	 * Returns the bounce entry with the primary key.
	 *
	 * @param bounceEntryId the primary key of the bounce entry
	 * @return the bounce entry
	 * @throws PortalException if a bounce entry with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.email.blacklist.model.BounceEntry getBounceEntry(
			long bounceEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bounceEntryLocalService.getBounceEntry(bounceEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _bounceEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _bounceEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _bounceEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the bounce entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param bounceEntry the bounce entry
	 * @return the bounce entry that was updated
	 */
	@Override
	public com.liferay.osb.email.blacklist.model.BounceEntry updateBounceEntry(
		com.liferay.osb.email.blacklist.model.BounceEntry bounceEntry) {

		return _bounceEntryLocalService.updateBounceEntry(bounceEntry);
	}

	@Override
	public BounceEntryLocalService getWrappedService() {
		return _bounceEntryLocalService;
	}

	@Override
	public void setWrappedService(
		BounceEntryLocalService bounceEntryLocalService) {

		_bounceEntryLocalService = bounceEntryLocalService;
	}

	private BounceEntryLocalService _bounceEntryLocalService;

}