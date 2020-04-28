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
 * Provides a wrapper for {@link LoopAuditEntryLocalService}.
 *
 * @author Ethan Bustad
 * @see LoopAuditEntryLocalService
 * @generated
 */
public class LoopAuditEntryLocalServiceWrapper
	implements LoopAuditEntryLocalService,
			   ServiceWrapper<LoopAuditEntryLocalService> {

	public LoopAuditEntryLocalServiceWrapper(
		LoopAuditEntryLocalService loopAuditEntryLocalService) {

		_loopAuditEntryLocalService = loopAuditEntryLocalService;
	}

	/**
	 * Adds the loop audit entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopAuditEntry the loop audit entry
	 * @return the loop audit entry that was added
	 */
	@Override
	public com.liferay.osb.loop.model.LoopAuditEntry addLoopAuditEntry(
		com.liferay.osb.loop.model.LoopAuditEntry loopAuditEntry) {

		return _loopAuditEntryLocalService.addLoopAuditEntry(loopAuditEntry);
	}

	/**
	 * Creates a new loop audit entry with the primary key. Does not add the loop audit entry to the database.
	 *
	 * @param loopAuditEntryId the primary key for the new loop audit entry
	 * @return the new loop audit entry
	 */
	@Override
	public com.liferay.osb.loop.model.LoopAuditEntry createLoopAuditEntry(
		long loopAuditEntryId) {

		return _loopAuditEntryLocalService.createLoopAuditEntry(
			loopAuditEntryId);
	}

	/**
	 * Deletes the loop audit entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopAuditEntryId the primary key of the loop audit entry
	 * @return the loop audit entry that was removed
	 * @throws PortalException if a loop audit entry with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.loop.model.LoopAuditEntry deleteLoopAuditEntry(
			long loopAuditEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopAuditEntryLocalService.deleteLoopAuditEntry(
			loopAuditEntryId);
	}

	/**
	 * Deletes the loop audit entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopAuditEntry the loop audit entry
	 * @return the loop audit entry that was removed
	 */
	@Override
	public com.liferay.osb.loop.model.LoopAuditEntry deleteLoopAuditEntry(
		com.liferay.osb.loop.model.LoopAuditEntry loopAuditEntry) {

		return _loopAuditEntryLocalService.deleteLoopAuditEntry(loopAuditEntry);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopAuditEntryLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loopAuditEntryLocalService.dynamicQuery();
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

		return _loopAuditEntryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopAuditEntryModelImpl</code>.
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

		return _loopAuditEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopAuditEntryModelImpl</code>.
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

		return _loopAuditEntryLocalService.dynamicQuery(
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

		return _loopAuditEntryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _loopAuditEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.loop.model.LoopAuditEntry fetchLoopAuditEntry(
		long loopAuditEntryId) {

		return _loopAuditEntryLocalService.fetchLoopAuditEntry(
			loopAuditEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _loopAuditEntryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _loopAuditEntryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the loop audit entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopAuditEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop audit entries
	 * @param end the upper bound of the range of loop audit entries (not inclusive)
	 * @return the range of loop audit entries
	 */
	@Override
	public java.util.List<com.liferay.osb.loop.model.LoopAuditEntry>
		getLoopAuditEntries(int start, int end) {

		return _loopAuditEntryLocalService.getLoopAuditEntries(start, end);
	}

	/**
	 * Returns the number of loop audit entries.
	 *
	 * @return the number of loop audit entries
	 */
	@Override
	public int getLoopAuditEntriesCount() {
		return _loopAuditEntryLocalService.getLoopAuditEntriesCount();
	}

	/**
	 * Returns the loop audit entry with the primary key.
	 *
	 * @param loopAuditEntryId the primary key of the loop audit entry
	 * @return the loop audit entry
	 * @throws PortalException if a loop audit entry with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.loop.model.LoopAuditEntry getLoopAuditEntry(
			long loopAuditEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopAuditEntryLocalService.getLoopAuditEntry(loopAuditEntryId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _loopAuditEntryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopAuditEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the loop audit entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param loopAuditEntry the loop audit entry
	 * @return the loop audit entry that was updated
	 */
	@Override
	public com.liferay.osb.loop.model.LoopAuditEntry updateLoopAuditEntry(
		com.liferay.osb.loop.model.LoopAuditEntry loopAuditEntry) {

		return _loopAuditEntryLocalService.updateLoopAuditEntry(loopAuditEntry);
	}

	@Override
	public LoopAuditEntryLocalService getWrappedService() {
		return _loopAuditEntryLocalService;
	}

	@Override
	public void setWrappedService(
		LoopAuditEntryLocalService loopAuditEntryLocalService) {

		_loopAuditEntryLocalService = loopAuditEntryLocalService;
	}

	private LoopAuditEntryLocalService _loopAuditEntryLocalService;

}