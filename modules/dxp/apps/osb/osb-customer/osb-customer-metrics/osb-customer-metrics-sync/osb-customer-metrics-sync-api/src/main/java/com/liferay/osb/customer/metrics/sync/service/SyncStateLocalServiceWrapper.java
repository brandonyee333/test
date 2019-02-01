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

package com.liferay.osb.customer.metrics.sync.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link SyncStateLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see SyncStateLocalService
 * @generated
 */
@ProviderType
public class SyncStateLocalServiceWrapper implements SyncStateLocalService,
	ServiceWrapper<SyncStateLocalService> {
	public SyncStateLocalServiceWrapper(
		SyncStateLocalService syncStateLocalService) {
		_syncStateLocalService = syncStateLocalService;
	}

	@Override
	public com.liferay.osb.customer.metrics.sync.model.SyncState addSyncState(
		String modelName) {
		return _syncStateLocalService.addSyncState(modelName);
	}

	/**
	* Adds the sync state to the database. Also notifies the appropriate model listeners.
	*
	* @param syncState the sync state
	* @return the sync state that was added
	*/
	@Override
	public com.liferay.osb.customer.metrics.sync.model.SyncState addSyncState(
		com.liferay.osb.customer.metrics.sync.model.SyncState syncState) {
		return _syncStateLocalService.addSyncState(syncState);
	}

	/**
	* Creates a new sync state with the primary key. Does not add the sync state to the database.
	*
	* @param syncStateId the primary key for the new sync state
	* @return the new sync state
	*/
	@Override
	public com.liferay.osb.customer.metrics.sync.model.SyncState createSyncState(
		long syncStateId) {
		return _syncStateLocalService.createSyncState(syncStateId);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncStateLocalService.deletePersistedModel(persistedModel);
	}

	/**
	* Deletes the sync state with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param syncStateId the primary key of the sync state
	* @return the sync state that was removed
	* @throws PortalException if a sync state with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.customer.metrics.sync.model.SyncState deleteSyncState(
		long syncStateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncStateLocalService.deleteSyncState(syncStateId);
	}

	/**
	* Deletes the sync state from the database. Also notifies the appropriate model listeners.
	*
	* @param syncState the sync state
	* @return the sync state that was removed
	*/
	@Override
	public com.liferay.osb.customer.metrics.sync.model.SyncState deleteSyncState(
		com.liferay.osb.customer.metrics.sync.model.SyncState syncState) {
		return _syncStateLocalService.deleteSyncState(syncState);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _syncStateLocalService.dynamicQuery();
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
		return _syncStateLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.metrics.sync.model.impl.SyncStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _syncStateLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.metrics.sync.model.impl.SyncStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _syncStateLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _syncStateLocalService.dynamicQueryCount(dynamicQuery);
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
		return _syncStateLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.customer.metrics.sync.model.SyncState fetchSyncState(
		long syncStateId) {
		return _syncStateLocalService.fetchSyncState(syncStateId);
	}

	@Override
	public com.liferay.osb.customer.metrics.sync.model.SyncState fetchSyncState(
		String modelName) {
		return _syncStateLocalService.fetchSyncState(modelName);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _syncStateLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _syncStateLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _syncStateLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncStateLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the sync state with the primary key.
	*
	* @param syncStateId the primary key of the sync state
	* @return the sync state
	* @throws PortalException if a sync state with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.customer.metrics.sync.model.SyncState getSyncState(
		long syncStateId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncStateLocalService.getSyncState(syncStateId);
	}

	/**
	* Returns a range of all the sync states.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.customer.metrics.sync.model.impl.SyncStateModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of sync states
	* @param end the upper bound of the range of sync states (not inclusive)
	* @return the range of sync states
	*/
	@Override
	public java.util.List<com.liferay.osb.customer.metrics.sync.model.SyncState> getSyncStates(
		int start, int end) {
		return _syncStateLocalService.getSyncStates(start, end);
	}

	/**
	* Returns the number of sync states.
	*
	* @return the number of sync states
	*/
	@Override
	public int getSyncStatesCount() {
		return _syncStateLocalService.getSyncStatesCount();
	}

	@Override
	public com.liferay.osb.customer.metrics.sync.model.SyncState updateSyncState(
		String modelName, long lastRunTime)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _syncStateLocalService.updateSyncState(modelName, lastRunTime);
	}

	/**
	* Updates the sync state in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param syncState the sync state
	* @return the sync state that was updated
	*/
	@Override
	public com.liferay.osb.customer.metrics.sync.model.SyncState updateSyncState(
		com.liferay.osb.customer.metrics.sync.model.SyncState syncState) {
		return _syncStateLocalService.updateSyncState(syncState);
	}

	@Override
	public SyncStateLocalService getWrappedService() {
		return _syncStateLocalService;
	}

	@Override
	public void setWrappedService(SyncStateLocalService syncStateLocalService) {
		_syncStateLocalService = syncStateLocalService;
	}

	private SyncStateLocalService _syncStateLocalService;
}