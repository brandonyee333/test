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

package com.liferay.osb.loop.service.persistence;

import com.liferay.osb.loop.model.LoopUserNotificationSubscription;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the loop user notification subscription service. This utility wraps <code>com.liferay.osb.loop.service.persistence.impl.LoopUserNotificationSubscriptionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see LoopUserNotificationSubscriptionPersistence
 * @generated
 */
public class LoopUserNotificationSubscriptionUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(
		LoopUserNotificationSubscription loopUserNotificationSubscription) {

		getPersistence().clearCache(loopUserNotificationSubscription);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, LoopUserNotificationSubscription>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LoopUserNotificationSubscription> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LoopUserNotificationSubscription> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LoopUserNotificationSubscription> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LoopUserNotificationSubscription> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LoopUserNotificationSubscription update(
		LoopUserNotificationSubscription loopUserNotificationSubscription) {

		return getPersistence().update(loopUserNotificationSubscription);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LoopUserNotificationSubscription update(
		LoopUserNotificationSubscription loopUserNotificationSubscription,
		ServiceContext serviceContext) {

		return getPersistence().update(
			loopUserNotificationSubscription, serviceContext);
	}

	/**
	 * Returns the loop user notification subscription where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63; and deliveryType = &#63; or throws a <code>NoSuchLoopUserNotificationSubscriptionException</code> if it could not be found.
	 *
	 * @param loopPersonId the loop person ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param deliveryType the delivery type
	 * @return the matching loop user notification subscription
	 * @throws NoSuchLoopUserNotificationSubscriptionException if a matching loop user notification subscription could not be found
	 */
	public static LoopUserNotificationSubscription findByLPI_CNI_CP_DT(
			long loopPersonId, long classNameId, long classPK, int deliveryType)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopUserNotificationSubscriptionException {

		return getPersistence().findByLPI_CNI_CP_DT(
			loopPersonId, classNameId, classPK, deliveryType);
	}

	/**
	 * Returns the loop user notification subscription where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63; and deliveryType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param loopPersonId the loop person ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param deliveryType the delivery type
	 * @return the matching loop user notification subscription, or <code>null</code> if a matching loop user notification subscription could not be found
	 */
	public static LoopUserNotificationSubscription fetchByLPI_CNI_CP_DT(
		long loopPersonId, long classNameId, long classPK, int deliveryType) {

		return getPersistence().fetchByLPI_CNI_CP_DT(
			loopPersonId, classNameId, classPK, deliveryType);
	}

	/**
	 * Returns the loop user notification subscription where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63; and deliveryType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param loopPersonId the loop person ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param deliveryType the delivery type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching loop user notification subscription, or <code>null</code> if a matching loop user notification subscription could not be found
	 */
	public static LoopUserNotificationSubscription fetchByLPI_CNI_CP_DT(
		long loopPersonId, long classNameId, long classPK, int deliveryType,
		boolean useFinderCache) {

		return getPersistence().fetchByLPI_CNI_CP_DT(
			loopPersonId, classNameId, classPK, deliveryType, useFinderCache);
	}

	/**
	 * Removes the loop user notification subscription where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63; and deliveryType = &#63; from the database.
	 *
	 * @param loopPersonId the loop person ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param deliveryType the delivery type
	 * @return the loop user notification subscription that was removed
	 */
	public static LoopUserNotificationSubscription removeByLPI_CNI_CP_DT(
			long loopPersonId, long classNameId, long classPK, int deliveryType)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopUserNotificationSubscriptionException {

		return getPersistence().removeByLPI_CNI_CP_DT(
			loopPersonId, classNameId, classPK, deliveryType);
	}

	/**
	 * Returns the number of loop user notification subscriptions where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63; and deliveryType = &#63;.
	 *
	 * @param loopPersonId the loop person ID
	 * @param classNameId the class name ID
	 * @param classPK the class pk
	 * @param deliveryType the delivery type
	 * @return the number of matching loop user notification subscriptions
	 */
	public static int countByLPI_CNI_CP_DT(
		long loopPersonId, long classNameId, long classPK, int deliveryType) {

		return getPersistence().countByLPI_CNI_CP_DT(
			loopPersonId, classNameId, classPK, deliveryType);
	}

	/**
	 * Caches the loop user notification subscription in the entity cache if it is enabled.
	 *
	 * @param loopUserNotificationSubscription the loop user notification subscription
	 */
	public static void cacheResult(
		LoopUserNotificationSubscription loopUserNotificationSubscription) {

		getPersistence().cacheResult(loopUserNotificationSubscription);
	}

	/**
	 * Caches the loop user notification subscriptions in the entity cache if it is enabled.
	 *
	 * @param loopUserNotificationSubscriptions the loop user notification subscriptions
	 */
	public static void cacheResult(
		List<LoopUserNotificationSubscription>
			loopUserNotificationSubscriptions) {

		getPersistence().cacheResult(loopUserNotificationSubscriptions);
	}

	/**
	 * Creates a new loop user notification subscription with the primary key. Does not add the loop user notification subscription to the database.
	 *
	 * @param loopUserNotificationSubscriptionId the primary key for the new loop user notification subscription
	 * @return the new loop user notification subscription
	 */
	public static LoopUserNotificationSubscription create(
		long loopUserNotificationSubscriptionId) {

		return getPersistence().create(loopUserNotificationSubscriptionId);
	}

	/**
	 * Removes the loop user notification subscription with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopUserNotificationSubscriptionId the primary key of the loop user notification subscription
	 * @return the loop user notification subscription that was removed
	 * @throws NoSuchLoopUserNotificationSubscriptionException if a loop user notification subscription with the primary key could not be found
	 */
	public static LoopUserNotificationSubscription remove(
			long loopUserNotificationSubscriptionId)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopUserNotificationSubscriptionException {

		return getPersistence().remove(loopUserNotificationSubscriptionId);
	}

	public static LoopUserNotificationSubscription updateImpl(
		LoopUserNotificationSubscription loopUserNotificationSubscription) {

		return getPersistence().updateImpl(loopUserNotificationSubscription);
	}

	/**
	 * Returns the loop user notification subscription with the primary key or throws a <code>NoSuchLoopUserNotificationSubscriptionException</code> if it could not be found.
	 *
	 * @param loopUserNotificationSubscriptionId the primary key of the loop user notification subscription
	 * @return the loop user notification subscription
	 * @throws NoSuchLoopUserNotificationSubscriptionException if a loop user notification subscription with the primary key could not be found
	 */
	public static LoopUserNotificationSubscription findByPrimaryKey(
			long loopUserNotificationSubscriptionId)
		throws com.liferay.osb.loop.exception.
			NoSuchLoopUserNotificationSubscriptionException {

		return getPersistence().findByPrimaryKey(
			loopUserNotificationSubscriptionId);
	}

	/**
	 * Returns the loop user notification subscription with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param loopUserNotificationSubscriptionId the primary key of the loop user notification subscription
	 * @return the loop user notification subscription, or <code>null</code> if a loop user notification subscription with the primary key could not be found
	 */
	public static LoopUserNotificationSubscription fetchByPrimaryKey(
		long loopUserNotificationSubscriptionId) {

		return getPersistence().fetchByPrimaryKey(
			loopUserNotificationSubscriptionId);
	}

	/**
	 * Returns all the loop user notification subscriptions.
	 *
	 * @return the loop user notification subscriptions
	 */
	public static List<LoopUserNotificationSubscription> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the loop user notification subscriptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationSubscriptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification subscriptions
	 * @param end the upper bound of the range of loop user notification subscriptions (not inclusive)
	 * @return the range of loop user notification subscriptions
	 */
	public static List<LoopUserNotificationSubscription> findAll(
		int start, int end) {

		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the loop user notification subscriptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationSubscriptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification subscriptions
	 * @param end the upper bound of the range of loop user notification subscriptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of loop user notification subscriptions
	 */
	public static List<LoopUserNotificationSubscription> findAll(
		int start, int end,
		OrderByComparator<LoopUserNotificationSubscription> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the loop user notification subscriptions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LoopUserNotificationSubscriptionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop user notification subscriptions
	 * @param end the upper bound of the range of loop user notification subscriptions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of loop user notification subscriptions
	 */
	public static List<LoopUserNotificationSubscription> findAll(
		int start, int end,
		OrderByComparator<LoopUserNotificationSubscription> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the loop user notification subscriptions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of loop user notification subscriptions.
	 *
	 * @return the number of loop user notification subscriptions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static LoopUserNotificationSubscriptionPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(
		LoopUserNotificationSubscriptionPersistence persistence) {

		_persistence = persistence;
	}

	private static volatile LoopUserNotificationSubscriptionPersistence
		_persistence;

}