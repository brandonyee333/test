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
 * Provides a wrapper for {@link LoopUserNotificationEventLocalService}.
 *
 * @author Ethan Bustad
 * @see LoopUserNotificationEventLocalService
 * @generated
 */
public class LoopUserNotificationEventLocalServiceWrapper
	implements LoopUserNotificationEventLocalService,
			   ServiceWrapper<LoopUserNotificationEventLocalService> {

	public LoopUserNotificationEventLocalServiceWrapper(
		LoopUserNotificationEventLocalService
			loopUserNotificationEventLocalService) {

		_loopUserNotificationEventLocalService =
			loopUserNotificationEventLocalService;
	}

	/**
	 * Adds the loop user notification event to the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopUserNotificationEvent the loop user notification event
	 * @return the loop user notification event that was added
	 */
	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationEvent
		addLoopUserNotificationEvent(
			com.liferay.osb.loop.model.LoopUserNotificationEvent
				loopUserNotificationEvent) {

		return _loopUserNotificationEventLocalService.
			addLoopUserNotificationEvent(loopUserNotificationEvent);
	}

	/**
	 * Creates a new loop user notification event with the primary key. Does not add the loop user notification event to the database.
	 *
	 * @param loopUserNotificationEventId the primary key for the new loop user notification event
	 * @return the new loop user notification event
	 */
	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationEvent
		createLoopUserNotificationEvent(long loopUserNotificationEventId) {

		return _loopUserNotificationEventLocalService.
			createLoopUserNotificationEvent(loopUserNotificationEventId);
	}

	/**
	 * Deletes the loop user notification event with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopUserNotificationEventId the primary key of the loop user notification event
	 * @return the loop user notification event that was removed
	 * @throws PortalException if a loop user notification event with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationEvent
			deleteLoopUserNotificationEvent(long loopUserNotificationEventId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopUserNotificationEventLocalService.
			deleteLoopUserNotificationEvent(loopUserNotificationEventId);
	}

	/**
	 * Deletes the loop user notification event from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopUserNotificationEvent the loop user notification event
	 * @return the loop user notification event that was removed
	 */
	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationEvent
		deleteLoopUserNotificationEvent(
			com.liferay.osb.loop.model.LoopUserNotificationEvent
				loopUserNotificationEvent) {

		return _loopUserNotificationEventLocalService.
			deleteLoopUserNotificationEvent(loopUserNotificationEvent);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopUserNotificationEventLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loopUserNotificationEventLocalService.dynamicQuery();
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

		return _loopUserNotificationEventLocalService.dynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopUserNotificationEventModelImpl</code>.
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

		return _loopUserNotificationEventLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopUserNotificationEventModelImpl</code>.
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

		return _loopUserNotificationEventLocalService.dynamicQuery(
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

		return _loopUserNotificationEventLocalService.dynamicQueryCount(
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

		return _loopUserNotificationEventLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationEvent
		fetchLoopUserNotificationEvent(long loopUserNotificationEventId) {

		return _loopUserNotificationEventLocalService.
			fetchLoopUserNotificationEvent(loopUserNotificationEventId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _loopUserNotificationEventLocalService.
			getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _loopUserNotificationEventLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the loop user notification event with the primary key.
	 *
	 * @param loopUserNotificationEventId the primary key of the loop user notification event
	 * @return the loop user notification event
	 * @throws PortalException if a loop user notification event with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationEvent
			getLoopUserNotificationEvent(long loopUserNotificationEventId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopUserNotificationEventLocalService.
			getLoopUserNotificationEvent(loopUserNotificationEventId);
	}

	/**
	 * Returns a range of all the loop user notification events.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopUserNotificationEventModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification events
	 * @param end the upper bound of the range of loop user notification events (not inclusive)
	 * @return the range of loop user notification events
	 */
	@Override
	public java.util.List<com.liferay.osb.loop.model.LoopUserNotificationEvent>
		getLoopUserNotificationEvents(int start, int end) {

		return _loopUserNotificationEventLocalService.
			getLoopUserNotificationEvents(start, end);
	}

	/**
	 * Returns the number of loop user notification events.
	 *
	 * @return the number of loop user notification events
	 */
	@Override
	public int getLoopUserNotificationEventsCount() {
		return _loopUserNotificationEventLocalService.
			getLoopUserNotificationEventsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _loopUserNotificationEventLocalService.
			getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopUserNotificationEventLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the loop user notification event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param loopUserNotificationEvent the loop user notification event
	 * @return the loop user notification event that was updated
	 */
	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationEvent
		updateLoopUserNotificationEvent(
			com.liferay.osb.loop.model.LoopUserNotificationEvent
				loopUserNotificationEvent) {

		return _loopUserNotificationEventLocalService.
			updateLoopUserNotificationEvent(loopUserNotificationEvent);
	}

	@Override
	public LoopUserNotificationEventLocalService getWrappedService() {
		return _loopUserNotificationEventLocalService;
	}

	@Override
	public void setWrappedService(
		LoopUserNotificationEventLocalService
			loopUserNotificationEventLocalService) {

		_loopUserNotificationEventLocalService =
			loopUserNotificationEventLocalService;
	}

	private LoopUserNotificationEventLocalService
		_loopUserNotificationEventLocalService;

}