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

package com.liferay.osb.service.persistence;

import com.liferay.osb.model.TrainingLinkedUser;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the training linked user service. This utility wraps {@link TrainingLinkedUserPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingLinkedUserPersistence
 * @see TrainingLinkedUserPersistenceImpl
 * @generated
 */
public class TrainingLinkedUserUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(TrainingLinkedUser trainingLinkedUser) {
		getPersistence().clearCache(trainingLinkedUser);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TrainingLinkedUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TrainingLinkedUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TrainingLinkedUser> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static TrainingLinkedUser update(
		TrainingLinkedUser trainingLinkedUser, boolean merge)
		throws SystemException {
		return getPersistence().update(trainingLinkedUser, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static TrainingLinkedUser update(
		TrainingLinkedUser trainingLinkedUser, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(trainingLinkedUser, merge, serviceContext);
	}

	/**
	* Caches the training linked user in the entity cache if it is enabled.
	*
	* @param trainingLinkedUser the training linked user
	*/
	public static void cacheResult(
		com.liferay.osb.model.TrainingLinkedUser trainingLinkedUser) {
		getPersistence().cacheResult(trainingLinkedUser);
	}

	/**
	* Caches the training linked users in the entity cache if it is enabled.
	*
	* @param trainingLinkedUsers the training linked users
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingLinkedUser> trainingLinkedUsers) {
		getPersistence().cacheResult(trainingLinkedUsers);
	}

	/**
	* Creates a new training linked user with the primary key. Does not add the training linked user to the database.
	*
	* @param trainingLinkedUserId the primary key for the new training linked user
	* @return the new training linked user
	*/
	public static com.liferay.osb.model.TrainingLinkedUser create(
		long trainingLinkedUserId) {
		return getPersistence().create(trainingLinkedUserId);
	}

	/**
	* Removes the training linked user with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingLinkedUserId the primary key of the training linked user
	* @return the training linked user that was removed
	* @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a training linked user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLinkedUser remove(
		long trainingLinkedUserId)
		throws com.liferay.osb.NoSuchTrainingLinkedUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(trainingLinkedUserId);
	}

	public static com.liferay.osb.model.TrainingLinkedUser updateImpl(
		com.liferay.osb.model.TrainingLinkedUser trainingLinkedUser,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(trainingLinkedUser, merge);
	}

	/**
	* Returns the training linked user with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingLinkedUserException} if it could not be found.
	*
	* @param trainingLinkedUserId the primary key of the training linked user
	* @return the training linked user
	* @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a training linked user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLinkedUser findByPrimaryKey(
		long trainingLinkedUserId)
		throws com.liferay.osb.NoSuchTrainingLinkedUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(trainingLinkedUserId);
	}

	/**
	* Returns the training linked user with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingLinkedUserId the primary key of the training linked user
	* @return the training linked user, or <code>null</code> if a training linked user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLinkedUser fetchByPrimaryKey(
		long trainingLinkedUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(trainingLinkedUserId);
	}

	/**
	* Returns the training linked user where userId = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingLinkedUserException} if it could not be found.
	*
	* @param userId the user ID
	* @return the matching training linked user
	* @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a matching training linked user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLinkedUser findByUserId(
		long userId)
		throws com.liferay.osb.NoSuchTrainingLinkedUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns the training linked user where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @return the matching training linked user, or <code>null</code> if a matching training linked user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLinkedUser fetchByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId(userId);
	}

	/**
	* Returns the training linked user where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching training linked user, or <code>null</code> if a matching training linked user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLinkedUser fetchByUserId(
		long userId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId(userId, retrieveFromCache);
	}

	/**
	* Returns all the training linked users where primaryUserId = &#63;.
	*
	* @param primaryUserId the primary user ID
	* @return the matching training linked users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingLinkedUser> findByPrimaryUserId(
		long primaryUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryUserId(primaryUserId);
	}

	/**
	* Returns a range of all the training linked users where primaryUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param primaryUserId the primary user ID
	* @param start the lower bound of the range of training linked users
	* @param end the upper bound of the range of training linked users (not inclusive)
	* @return the range of matching training linked users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingLinkedUser> findByPrimaryUserId(
		long primaryUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryUserId(primaryUserId, start, end);
	}

	/**
	* Returns an ordered range of all the training linked users where primaryUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param primaryUserId the primary user ID
	* @param start the lower bound of the range of training linked users
	* @param end the upper bound of the range of training linked users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training linked users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingLinkedUser> findByPrimaryUserId(
		long primaryUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPrimaryUserId(primaryUserId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first training linked user in the ordered set where primaryUserId = &#63;.
	*
	* @param primaryUserId the primary user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training linked user
	* @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a matching training linked user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLinkedUser findByPrimaryUserId_First(
		long primaryUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingLinkedUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPrimaryUserId_First(primaryUserId, orderByComparator);
	}

	/**
	* Returns the first training linked user in the ordered set where primaryUserId = &#63;.
	*
	* @param primaryUserId the primary user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training linked user, or <code>null</code> if a matching training linked user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLinkedUser fetchByPrimaryUserId_First(
		long primaryUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPrimaryUserId_First(primaryUserId, orderByComparator);
	}

	/**
	* Returns the last training linked user in the ordered set where primaryUserId = &#63;.
	*
	* @param primaryUserId the primary user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training linked user
	* @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a matching training linked user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLinkedUser findByPrimaryUserId_Last(
		long primaryUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingLinkedUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPrimaryUserId_Last(primaryUserId, orderByComparator);
	}

	/**
	* Returns the last training linked user in the ordered set where primaryUserId = &#63;.
	*
	* @param primaryUserId the primary user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training linked user, or <code>null</code> if a matching training linked user could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLinkedUser fetchByPrimaryUserId_Last(
		long primaryUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPrimaryUserId_Last(primaryUserId, orderByComparator);
	}

	/**
	* Returns the training linked users before and after the current training linked user in the ordered set where primaryUserId = &#63;.
	*
	* @param trainingLinkedUserId the primary key of the current training linked user
	* @param primaryUserId the primary user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training linked user
	* @throws com.liferay.osb.NoSuchTrainingLinkedUserException if a training linked user with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLinkedUser[] findByPrimaryUserId_PrevAndNext(
		long trainingLinkedUserId, long primaryUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingLinkedUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPrimaryUserId_PrevAndNext(trainingLinkedUserId,
			primaryUserId, orderByComparator);
	}

	/**
	* Returns all the training linked users.
	*
	* @return the training linked users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingLinkedUser> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the training linked users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training linked users
	* @param end the upper bound of the range of training linked users (not inclusive)
	* @return the range of training linked users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingLinkedUser> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the training linked users.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training linked users
	* @param end the upper bound of the range of training linked users (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of training linked users
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingLinkedUser> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the training linked user where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @return the training linked user that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingLinkedUser removeByUserId(
		long userId)
		throws com.liferay.osb.NoSuchTrainingLinkedUserException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByUserId(userId);
	}

	/**
	* Removes all the training linked users where primaryUserId = &#63; from the database.
	*
	* @param primaryUserId the primary user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByPrimaryUserId(long primaryUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByPrimaryUserId(primaryUserId);
	}

	/**
	* Removes all the training linked users from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of training linked users where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching training linked users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of training linked users where primaryUserId = &#63;.
	*
	* @param primaryUserId the primary user ID
	* @return the number of matching training linked users
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPrimaryUserId(long primaryUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByPrimaryUserId(primaryUserId);
	}

	/**
	* Returns the number of training linked users.
	*
	* @return the number of training linked users
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TrainingLinkedUserPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TrainingLinkedUserPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TrainingLinkedUserPersistence.class.getName());

			ReferenceRegistry.registerReference(TrainingLinkedUserUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(TrainingLinkedUserPersistence persistence) {
	}

	private static TrainingLinkedUserPersistence _persistence;
}