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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LoopStreamEntryLocalService}.
 *
 * @author Ethan Bustad
 * @see LoopStreamEntryLocalService
 * @generated
 */
public class LoopStreamEntryLocalServiceWrapper
	implements LoopStreamEntryLocalService,
			   ServiceWrapper<LoopStreamEntryLocalService> {

	public LoopStreamEntryLocalServiceWrapper(
		LoopStreamEntryLocalService loopStreamEntryLocalService) {

		_loopStreamEntryLocalService = loopStreamEntryLocalService;
	}

	/**
	 * Adds the loop stream entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopStreamEntry the loop stream entry
	 * @return the loop stream entry that was added
	 */
	@Override
	public com.liferay.osb.loop.model.LoopStreamEntry addLoopStreamEntry(
		com.liferay.osb.loop.model.LoopStreamEntry loopStreamEntry) {

		return _loopStreamEntryLocalService.addLoopStreamEntry(loopStreamEntry);
	}

	/**
	 * Creates a new loop stream entry with the primary key. Does not add the loop stream entry to the database.
	 *
	 * @param loopStreamEntryId the primary key for the new loop stream entry
	 * @return the new loop stream entry
	 */
	@Override
	public com.liferay.osb.loop.model.LoopStreamEntry createLoopStreamEntry(
		long loopStreamEntryId) {

		return _loopStreamEntryLocalService.createLoopStreamEntry(
			loopStreamEntryId);
	}

	/**
	 * Deletes the loop stream entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopStreamEntryId the primary key of the loop stream entry
	 * @return the loop stream entry that was removed
	 * @throws PortalException if a loop stream entry with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.loop.model.LoopStreamEntry deleteLoopStreamEntry(
			long loopStreamEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopStreamEntryLocalService.deleteLoopStreamEntry(
			loopStreamEntryId);
	}

	/**
	 * Deletes the loop stream entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopStreamEntry the loop stream entry
	 * @return the loop stream entry that was removed
	 */
	@Override
	public com.liferay.osb.loop.model.LoopStreamEntry deleteLoopStreamEntry(
		com.liferay.osb.loop.model.LoopStreamEntry loopStreamEntry) {

		return _loopStreamEntryLocalService.deleteLoopStreamEntry(
			loopStreamEntry);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopStreamEntryLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loopStreamEntryLocalService.dynamicQuery();
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

		return _loopStreamEntryLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _loopStreamEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _loopStreamEntryLocalService.dynamicQuery(
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

		return _loopStreamEntryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _loopStreamEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.loop.model.LoopStreamEntry fetchLoopStreamEntry(
		long loopStreamEntryId) {

		return _loopStreamEntryLocalService.fetchLoopStreamEntry(
			loopStreamEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _loopStreamEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _loopStreamEntryLocalService.
			getIndexableActionableDynamicQuery();
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
	@Override
	public java.util.List<com.liferay.osb.loop.model.LoopStreamEntry>
		getLoopStreamEntries(int start, int end) {

		return _loopStreamEntryLocalService.getLoopStreamEntries(start, end);
	}

	/**
	 * Returns the number of loop stream entries.
	 *
	 * @return the number of loop stream entries
	 */
	@Override
	public int getLoopStreamEntriesCount() {
		return _loopStreamEntryLocalService.getLoopStreamEntriesCount();
	}

	/**
	 * Returns the loop stream entry with the primary key.
	 *
	 * @param loopStreamEntryId the primary key of the loop stream entry
	 * @return the loop stream entry
	 * @throws PortalException if a loop stream entry with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.loop.model.LoopStreamEntry getLoopStreamEntry(
			long loopStreamEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopStreamEntryLocalService.getLoopStreamEntry(
			loopStreamEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _loopStreamEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopStreamEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the loop stream entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param loopStreamEntry the loop stream entry
	 * @return the loop stream entry that was updated
	 */
	@Override
	public com.liferay.osb.loop.model.LoopStreamEntry updateLoopStreamEntry(
		com.liferay.osb.loop.model.LoopStreamEntry loopStreamEntry) {

		return _loopStreamEntryLocalService.updateLoopStreamEntry(
			loopStreamEntry);
	}

	@Override
	public LoopStreamEntryLocalService getWrappedService() {
		return _loopStreamEntryLocalService;
	}

	@Override
	public void setWrappedService(
		LoopStreamEntryLocalService loopStreamEntryLocalService) {

		_loopStreamEntryLocalService = loopStreamEntryLocalService;
	}

	private LoopStreamEntryLocalService _loopStreamEntryLocalService;

}