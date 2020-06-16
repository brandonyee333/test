/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.loop.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.exception.NoSuchLoopUserNotificationSubscriptionException;
import com.liferay.osb.loop.model.LoopUserNotificationSubscription;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the loop user notification subscription service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ethan Bustad
 * @see com.liferay.osb.loop.service.persistence.impl.LoopUserNotificationSubscriptionPersistenceImpl
 * @see LoopUserNotificationSubscriptionUtil
 * @generated
 */
@ProviderType
public interface LoopUserNotificationSubscriptionPersistence
	extends BasePersistence<LoopUserNotificationSubscription> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LoopUserNotificationSubscriptionUtil} to access the loop user notification subscription persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the loop user notification subscription where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63; and deliveryType = &#63; or throws a {@link NoSuchLoopUserNotificationSubscriptionException} if it could not be found.
	*
	* @param loopPersonId the loop person ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param deliveryType the delivery type
	* @return the matching loop user notification subscription
	* @throws NoSuchLoopUserNotificationSubscriptionException if a matching loop user notification subscription could not be found
	*/
	public LoopUserNotificationSubscription findByLPI_CNI_CP_DT(
		long loopPersonId, long classNameId, long classPK, int deliveryType)
		throws NoSuchLoopUserNotificationSubscriptionException;

	/**
	* Returns the loop user notification subscription where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63; and deliveryType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param loopPersonId the loop person ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param deliveryType the delivery type
	* @return the matching loop user notification subscription, or <code>null</code> if a matching loop user notification subscription could not be found
	*/
	public LoopUserNotificationSubscription fetchByLPI_CNI_CP_DT(
		long loopPersonId, long classNameId, long classPK, int deliveryType);

	/**
	* Returns the loop user notification subscription where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63; and deliveryType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param loopPersonId the loop person ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param deliveryType the delivery type
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching loop user notification subscription, or <code>null</code> if a matching loop user notification subscription could not be found
	*/
	public LoopUserNotificationSubscription fetchByLPI_CNI_CP_DT(
		long loopPersonId, long classNameId, long classPK, int deliveryType,
		boolean retrieveFromCache);

	/**
	* Removes the loop user notification subscription where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63; and deliveryType = &#63; from the database.
	*
	* @param loopPersonId the loop person ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param deliveryType the delivery type
	* @return the loop user notification subscription that was removed
	*/
	public LoopUserNotificationSubscription removeByLPI_CNI_CP_DT(
		long loopPersonId, long classNameId, long classPK, int deliveryType)
		throws NoSuchLoopUserNotificationSubscriptionException;

	/**
	* Returns the number of loop user notification subscriptions where loopPersonId = &#63; and classNameId = &#63; and classPK = &#63; and deliveryType = &#63;.
	*
	* @param loopPersonId the loop person ID
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param deliveryType the delivery type
	* @return the number of matching loop user notification subscriptions
	*/
	public int countByLPI_CNI_CP_DT(long loopPersonId, long classNameId,
		long classPK, int deliveryType);

	/**
	* Caches the loop user notification subscription in the entity cache if it is enabled.
	*
	* @param loopUserNotificationSubscription the loop user notification subscription
	*/
	public void cacheResult(
		LoopUserNotificationSubscription loopUserNotificationSubscription);

	/**
	* Caches the loop user notification subscriptions in the entity cache if it is enabled.
	*
	* @param loopUserNotificationSubscriptions the loop user notification subscriptions
	*/
	public void cacheResult(
		java.util.List<LoopUserNotificationSubscription> loopUserNotificationSubscriptions);

	/**
	* Creates a new loop user notification subscription with the primary key. Does not add the loop user notification subscription to the database.
	*
	* @param loopUserNotificationSubscriptionId the primary key for the new loop user notification subscription
	* @return the new loop user notification subscription
	*/
	public LoopUserNotificationSubscription create(
		long loopUserNotificationSubscriptionId);

	/**
	* Removes the loop user notification subscription with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopUserNotificationSubscriptionId the primary key of the loop user notification subscription
	* @return the loop user notification subscription that was removed
	* @throws NoSuchLoopUserNotificationSubscriptionException if a loop user notification subscription with the primary key could not be found
	*/
	public LoopUserNotificationSubscription remove(
		long loopUserNotificationSubscriptionId)
		throws NoSuchLoopUserNotificationSubscriptionException;

	public LoopUserNotificationSubscription updateImpl(
		LoopUserNotificationSubscription loopUserNotificationSubscription);

	/**
	* Returns the loop user notification subscription with the primary key or throws a {@link NoSuchLoopUserNotificationSubscriptionException} if it could not be found.
	*
	* @param loopUserNotificationSubscriptionId the primary key of the loop user notification subscription
	* @return the loop user notification subscription
	* @throws NoSuchLoopUserNotificationSubscriptionException if a loop user notification subscription with the primary key could not be found
	*/
	public LoopUserNotificationSubscription findByPrimaryKey(
		long loopUserNotificationSubscriptionId)
		throws NoSuchLoopUserNotificationSubscriptionException;

	/**
	* Returns the loop user notification subscription with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param loopUserNotificationSubscriptionId the primary key of the loop user notification subscription
	* @return the loop user notification subscription, or <code>null</code> if a loop user notification subscription with the primary key could not be found
	*/
	public LoopUserNotificationSubscription fetchByPrimaryKey(
		long loopUserNotificationSubscriptionId);

	@Override
	public java.util.Map<java.io.Serializable, LoopUserNotificationSubscription> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the loop user notification subscriptions.
	*
	* @return the loop user notification subscriptions
	*/
	public java.util.List<LoopUserNotificationSubscription> findAll();

	/**
	* Returns a range of all the loop user notification subscriptions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationSubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop user notification subscriptions
	* @param end the upper bound of the range of loop user notification subscriptions (not inclusive)
	* @return the range of loop user notification subscriptions
	*/
	public java.util.List<LoopUserNotificationSubscription> findAll(int start,
		int end);

	/**
	* Returns an ordered range of all the loop user notification subscriptions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationSubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop user notification subscriptions
	* @param end the upper bound of the range of loop user notification subscriptions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of loop user notification subscriptions
	*/
	public java.util.List<LoopUserNotificationSubscription> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationSubscription> orderByComparator);

	/**
	* Returns an ordered range of all the loop user notification subscriptions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LoopUserNotificationSubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop user notification subscriptions
	* @param end the upper bound of the range of loop user notification subscriptions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of loop user notification subscriptions
	*/
	public java.util.List<LoopUserNotificationSubscription> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LoopUserNotificationSubscription> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the loop user notification subscriptions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of loop user notification subscriptions.
	*
	* @return the number of loop user notification subscriptions
	*/
	public int countAll();
}