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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for LoopUserNotificationSubscription. This utility wraps
 * <code>com.liferay.osb.loop.service.impl.LoopUserNotificationSubscriptionLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see LoopUserNotificationSubscriptionLocalService
 * @generated
 */
public class LoopUserNotificationSubscriptionLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.loop.service.impl.LoopUserNotificationSubscriptionLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the loop user notification subscription to the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopUserNotificationSubscription the loop user notification subscription
	 * @return the loop user notification subscription that was added
	 */
	public static com.liferay.osb.loop.model.LoopUserNotificationSubscription
		addLoopUserNotificationSubscription(
			com.liferay.osb.loop.model.LoopUserNotificationSubscription
				loopUserNotificationSubscription) {

		return getService().addLoopUserNotificationSubscription(
			loopUserNotificationSubscription);
	}

	/**
	 * Creates a new loop user notification subscription with the primary key. Does not add the loop user notification subscription to the database.
	 *
	 * @param loopUserNotificationSubscriptionId the primary key for the new loop user notification subscription
	 * @return the new loop user notification subscription
	 */
	public static com.liferay.osb.loop.model.LoopUserNotificationSubscription
		createLoopUserNotificationSubscription(
			long loopUserNotificationSubscriptionId) {

		return getService().createLoopUserNotificationSubscription(
			loopUserNotificationSubscriptionId);
	}

	/**
	 * Deletes the loop user notification subscription with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopUserNotificationSubscriptionId the primary key of the loop user notification subscription
	 * @return the loop user notification subscription that was removed
	 * @throws PortalException if a loop user notification subscription with the primary key could not be found
	 */
	public static com.liferay.osb.loop.model.LoopUserNotificationSubscription
			deleteLoopUserNotificationSubscription(
				long loopUserNotificationSubscriptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteLoopUserNotificationSubscription(
			loopUserNotificationSubscriptionId);
	}

	/**
	 * Deletes the loop user notification subscription from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopUserNotificationSubscription the loop user notification subscription
	 * @return the loop user notification subscription that was removed
	 */
	public static com.liferay.osb.loop.model.LoopUserNotificationSubscription
		deleteLoopUserNotificationSubscription(
			com.liferay.osb.loop.model.LoopUserNotificationSubscription
				loopUserNotificationSubscription) {

		return getService().deleteLoopUserNotificationSubscription(
			loopUserNotificationSubscription);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopUserNotificationSubscriptionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopUserNotificationSubscriptionModelImpl</code>.
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

	public static com.liferay.osb.loop.model.LoopUserNotificationSubscription
		fetchLoopUserNotificationSubscription(
			long loopUserNotificationSubscriptionId) {

		return getService().fetchLoopUserNotificationSubscription(
			loopUserNotificationSubscriptionId);
	}

	public static com.liferay.osb.loop.model.LoopUserNotificationSubscription
		fetchLoopUserNotificationSubscription(
			long loopPersonId, long classNameId, long classPK,
			int deliveryType) {

		return getService().fetchLoopUserNotificationSubscription(
			loopPersonId, classNameId, classPK, deliveryType);
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
	 * Returns the loop user notification subscription with the primary key.
	 *
	 * @param loopUserNotificationSubscriptionId the primary key of the loop user notification subscription
	 * @return the loop user notification subscription
	 * @throws PortalException if a loop user notification subscription with the primary key could not be found
	 */
	public static com.liferay.osb.loop.model.LoopUserNotificationSubscription
			getLoopUserNotificationSubscription(
				long loopUserNotificationSubscriptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getLoopUserNotificationSubscription(
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
	public static java.util.List
		<com.liferay.osb.loop.model.LoopUserNotificationSubscription>
			getLoopUserNotificationSubscriptions(int start, int end) {

		return getService().getLoopUserNotificationSubscriptions(start, end);
	}

	/**
	 * Returns the number of loop user notification subscriptions.
	 *
	 * @return the number of loop user notification subscriptions
	 */
	public static int getLoopUserNotificationSubscriptionsCount() {
		return getService().getLoopUserNotificationSubscriptionsCount();
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
	 * Updates the loop user notification subscription in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param loopUserNotificationSubscription the loop user notification subscription
	 * @return the loop user notification subscription that was updated
	 */
	public static com.liferay.osb.loop.model.LoopUserNotificationSubscription
		updateLoopUserNotificationSubscription(
			com.liferay.osb.loop.model.LoopUserNotificationSubscription
				loopUserNotificationSubscription) {

		return getService().updateLoopUserNotificationSubscription(
			loopUserNotificationSubscription);
	}

	public static LoopUserNotificationSubscriptionLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<LoopUserNotificationSubscriptionLocalService,
		 LoopUserNotificationSubscriptionLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			LoopUserNotificationSubscriptionLocalService.class);

		ServiceTracker
			<LoopUserNotificationSubscriptionLocalService,
			 LoopUserNotificationSubscriptionLocalService> serviceTracker =
				new ServiceTracker
					<LoopUserNotificationSubscriptionLocalService,
					 LoopUserNotificationSubscriptionLocalService>(
						 bundle.getBundleContext(),
						 LoopUserNotificationSubscriptionLocalService.class,
						 null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}