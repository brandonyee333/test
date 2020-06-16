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
 * Provides a wrapper for {@link LoopUserNotificationSubscriptionLocalService}.
 *
 * @author Ethan Bustad
 * @see LoopUserNotificationSubscriptionLocalService
 * @generated
 */
public class LoopUserNotificationSubscriptionLocalServiceWrapper
	implements LoopUserNotificationSubscriptionLocalService,
			   ServiceWrapper<LoopUserNotificationSubscriptionLocalService> {

	public LoopUserNotificationSubscriptionLocalServiceWrapper(
		LoopUserNotificationSubscriptionLocalService
			loopUserNotificationSubscriptionLocalService) {

		_loopUserNotificationSubscriptionLocalService =
			loopUserNotificationSubscriptionLocalService;
	}

	/**
	 * Adds the loop user notification subscription to the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopUserNotificationSubscription the loop user notification subscription
	 * @return the loop user notification subscription that was added
	 */
	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationSubscription
		addLoopUserNotificationSubscription(
			com.liferay.osb.loop.model.LoopUserNotificationSubscription
				loopUserNotificationSubscription) {

		return _loopUserNotificationSubscriptionLocalService.
			addLoopUserNotificationSubscription(
				loopUserNotificationSubscription);
	}

	/**
	 * Creates a new loop user notification subscription with the primary key. Does not add the loop user notification subscription to the database.
	 *
	 * @param loopUserNotificationSubscriptionId the primary key for the new loop user notification subscription
	 * @return the new loop user notification subscription
	 */
	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationSubscription
		createLoopUserNotificationSubscription(
			long loopUserNotificationSubscriptionId) {

		return _loopUserNotificationSubscriptionLocalService.
			createLoopUserNotificationSubscription(
				loopUserNotificationSubscriptionId);
	}

	/**
	 * Deletes the loop user notification subscription with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopUserNotificationSubscriptionId the primary key of the loop user notification subscription
	 * @return the loop user notification subscription that was removed
	 * @throws PortalException if a loop user notification subscription with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationSubscription
			deleteLoopUserNotificationSubscription(
				long loopUserNotificationSubscriptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopUserNotificationSubscriptionLocalService.
			deleteLoopUserNotificationSubscription(
				loopUserNotificationSubscriptionId);
	}

	/**
	 * Deletes the loop user notification subscription from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopUserNotificationSubscription the loop user notification subscription
	 * @return the loop user notification subscription that was removed
	 */
	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationSubscription
		deleteLoopUserNotificationSubscription(
			com.liferay.osb.loop.model.LoopUserNotificationSubscription
				loopUserNotificationSubscription) {

		return _loopUserNotificationSubscriptionLocalService.
			deleteLoopUserNotificationSubscription(
				loopUserNotificationSubscription);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopUserNotificationSubscriptionLocalService.
			deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loopUserNotificationSubscriptionLocalService.dynamicQuery();
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

		return _loopUserNotificationSubscriptionLocalService.dynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopUserNotificationSubscriptionModelImpl</code>.
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

		return _loopUserNotificationSubscriptionLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopUserNotificationSubscriptionModelImpl</code>.
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

		return _loopUserNotificationSubscriptionLocalService.dynamicQuery(
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

		return _loopUserNotificationSubscriptionLocalService.dynamicQueryCount(
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

		return _loopUserNotificationSubscriptionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationSubscription
		fetchLoopUserNotificationSubscription(
			long loopUserNotificationSubscriptionId) {

		return _loopUserNotificationSubscriptionLocalService.
			fetchLoopUserNotificationSubscription(
				loopUserNotificationSubscriptionId);
	}

	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationSubscription
		fetchLoopUserNotificationSubscription(
			long loopPersonId, long classNameId, long classPK,
			int deliveryType) {

		return _loopUserNotificationSubscriptionLocalService.
			fetchLoopUserNotificationSubscription(
				loopPersonId, classNameId, classPK, deliveryType);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _loopUserNotificationSubscriptionLocalService.
			getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _loopUserNotificationSubscriptionLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the loop user notification subscription with the primary key.
	 *
	 * @param loopUserNotificationSubscriptionId the primary key of the loop user notification subscription
	 * @return the loop user notification subscription
	 * @throws PortalException if a loop user notification subscription with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationSubscription
			getLoopUserNotificationSubscription(
				long loopUserNotificationSubscriptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopUserNotificationSubscriptionLocalService.
			getLoopUserNotificationSubscription(
				loopUserNotificationSubscriptionId);
	}

	/**
	 * Returns a range of all the loop user notification subscriptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopUserNotificationSubscriptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification subscriptions
	 * @param end the upper bound of the range of loop user notification subscriptions (not inclusive)
	 * @return the range of loop user notification subscriptions
	 */
	@Override
	public java.util.List
		<com.liferay.osb.loop.model.LoopUserNotificationSubscription>
			getLoopUserNotificationSubscriptions(int start, int end) {

		return _loopUserNotificationSubscriptionLocalService.
			getLoopUserNotificationSubscriptions(start, end);
	}

	/**
	 * Returns the number of loop user notification subscriptions.
	 *
	 * @return the number of loop user notification subscriptions
	 */
	@Override
	public int getLoopUserNotificationSubscriptionsCount() {
		return _loopUserNotificationSubscriptionLocalService.
			getLoopUserNotificationSubscriptionsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _loopUserNotificationSubscriptionLocalService.
			getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopUserNotificationSubscriptionLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the loop user notification subscription in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param loopUserNotificationSubscription the loop user notification subscription
	 * @return the loop user notification subscription that was updated
	 */
	@Override
	public com.liferay.osb.loop.model.LoopUserNotificationSubscription
		updateLoopUserNotificationSubscription(
			com.liferay.osb.loop.model.LoopUserNotificationSubscription
				loopUserNotificationSubscription) {

		return _loopUserNotificationSubscriptionLocalService.
			updateLoopUserNotificationSubscription(
				loopUserNotificationSubscription);
	}

	@Override
	public LoopUserNotificationSubscriptionLocalService getWrappedService() {
		return _loopUserNotificationSubscriptionLocalService;
	}

	@Override
	public void setWrappedService(
		LoopUserNotificationSubscriptionLocalService
			loopUserNotificationSubscriptionLocalService) {

		_loopUserNotificationSubscriptionLocalService =
			loopUserNotificationSubscriptionLocalService;
	}

	private LoopUserNotificationSubscriptionLocalService
		_loopUserNotificationSubscriptionLocalService;

}