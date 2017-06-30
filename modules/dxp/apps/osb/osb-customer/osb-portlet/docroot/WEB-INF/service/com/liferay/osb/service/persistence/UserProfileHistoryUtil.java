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

import com.liferay.osb.model.UserProfileHistory;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the user profile history service. This utility wraps {@link UserProfileHistoryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserProfileHistoryPersistence
 * @see UserProfileHistoryPersistenceImpl
 * @generated
 */
public class UserProfileHistoryUtil {
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
	public static void clearCache(UserProfileHistory userProfileHistory) {
		getPersistence().clearCache(userProfileHistory);
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
	public static List<UserProfileHistory> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<UserProfileHistory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<UserProfileHistory> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static UserProfileHistory update(
		UserProfileHistory userProfileHistory, boolean merge)
		throws SystemException {
		return getPersistence().update(userProfileHistory, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static UserProfileHistory update(
		UserProfileHistory userProfileHistory, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(userProfileHistory, merge, serviceContext);
	}

	/**
	* Caches the user profile history in the entity cache if it is enabled.
	*
	* @param userProfileHistory the user profile history
	*/
	public static void cacheResult(
		com.liferay.osb.model.UserProfileHistory userProfileHistory) {
		getPersistence().cacheResult(userProfileHistory);
	}

	/**
	* Caches the user profile histories in the entity cache if it is enabled.
	*
	* @param userProfileHistories the user profile histories
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.UserProfileHistory> userProfileHistories) {
		getPersistence().cacheResult(userProfileHistories);
	}

	/**
	* Creates a new user profile history with the primary key. Does not add the user profile history to the database.
	*
	* @param userProfileHistoryId the primary key for the new user profile history
	* @return the new user profile history
	*/
	public static com.liferay.osb.model.UserProfileHistory create(
		long userProfileHistoryId) {
		return getPersistence().create(userProfileHistoryId);
	}

	/**
	* Removes the user profile history with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userProfileHistoryId the primary key of the user profile history
	* @return the user profile history that was removed
	* @throws com.liferay.osb.NoSuchUserProfileHistoryException if a user profile history with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory remove(
		long userProfileHistoryId)
		throws com.liferay.osb.NoSuchUserProfileHistoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(userProfileHistoryId);
	}

	public static com.liferay.osb.model.UserProfileHistory updateImpl(
		com.liferay.osb.model.UserProfileHistory userProfileHistory,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(userProfileHistory, merge);
	}

	/**
	* Returns the user profile history with the primary key or throws a {@link com.liferay.osb.NoSuchUserProfileHistoryException} if it could not be found.
	*
	* @param userProfileHistoryId the primary key of the user profile history
	* @return the user profile history
	* @throws com.liferay.osb.NoSuchUserProfileHistoryException if a user profile history with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory findByPrimaryKey(
		long userProfileHistoryId)
		throws com.liferay.osb.NoSuchUserProfileHistoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(userProfileHistoryId);
	}

	/**
	* Returns the user profile history with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userProfileHistoryId the primary key of the user profile history
	* @return the user profile history, or <code>null</code> if a user profile history with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory fetchByPrimaryKey(
		long userProfileHistoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(userProfileHistoryId);
	}

	/**
	* Returns all the user profile histories where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @return the matching user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.UserProfileHistory> findByU_C(
		long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_C(userId, classNameId);
	}

	/**
	* Returns a range of all the user profile histories where userId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of user profile histories
	* @param end the upper bound of the range of user profile histories (not inclusive)
	* @return the range of matching user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.UserProfileHistory> findByU_C(
		long userId, long classNameId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_C(userId, classNameId, start, end);
	}

	/**
	* Returns an ordered range of all the user profile histories where userId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of user profile histories
	* @param end the upper bound of the range of user profile histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.UserProfileHistory> findByU_C(
		long userId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_C(userId, classNameId, start, end, orderByComparator);
	}

	/**
	* Returns the first user profile history in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user profile history
	* @throws com.liferay.osb.NoSuchUserProfileHistoryException if a matching user profile history could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory findByU_C_First(
		long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchUserProfileHistoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_C_First(userId, classNameId, orderByComparator);
	}

	/**
	* Returns the first user profile history in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user profile history, or <code>null</code> if a matching user profile history could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory fetchByU_C_First(
		long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_C_First(userId, classNameId, orderByComparator);
	}

	/**
	* Returns the last user profile history in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user profile history
	* @throws com.liferay.osb.NoSuchUserProfileHistoryException if a matching user profile history could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory findByU_C_Last(
		long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchUserProfileHistoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_C_Last(userId, classNameId, orderByComparator);
	}

	/**
	* Returns the last user profile history in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user profile history, or <code>null</code> if a matching user profile history could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory fetchByU_C_Last(
		long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_C_Last(userId, classNameId, orderByComparator);
	}

	/**
	* Returns the user profile histories before and after the current user profile history in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userProfileHistoryId the primary key of the current user profile history
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user profile history
	* @throws com.liferay.osb.NoSuchUserProfileHistoryException if a user profile history with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory[] findByU_C_PrevAndNext(
		long userProfileHistoryId, long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchUserProfileHistoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_C_PrevAndNext(userProfileHistoryId, userId,
			classNameId, orderByComparator);
	}

	/**
	* Returns all the user profile histories where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.UserProfileHistory> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	* Returns a range of all the user profile histories where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of user profile histories
	* @param end the upper bound of the range of user profile histories (not inclusive)
	* @return the range of matching user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.UserProfileHistory> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

	/**
	* Returns an ordered range of all the user profile histories where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of user profile histories
	* @param end the upper bound of the range of user profile histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.UserProfileHistory> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C(classNameId, classPK, start, end,
			orderByComparator);
	}

	/**
	* Returns the first user profile history in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user profile history
	* @throws com.liferay.osb.NoSuchUserProfileHistoryException if a matching user profile history could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchUserProfileHistoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the first user profile history in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user profile history, or <code>null</code> if a matching user profile history could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last user profile history in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user profile history
	* @throws com.liferay.osb.NoSuchUserProfileHistoryException if a matching user profile history could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchUserProfileHistoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_Last(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last user profile history in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user profile history, or <code>null</code> if a matching user profile history could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_Last(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the user profile histories before and after the current user profile history in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param userProfileHistoryId the primary key of the current user profile history
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next user profile history
	* @throws com.liferay.osb.NoSuchUserProfileHistoryException if a user profile history with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.UserProfileHistory[] findByC_C_PrevAndNext(
		long userProfileHistoryId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchUserProfileHistoryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_PrevAndNext(userProfileHistoryId, classNameId,
			classPK, orderByComparator);
	}

	/**
	* Returns all the user profile histories.
	*
	* @return the user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.UserProfileHistory> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the user profile histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of user profile histories
	* @param end the upper bound of the range of user profile histories (not inclusive)
	* @return the range of user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.UserProfileHistory> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the user profile histories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of user profile histories
	* @param end the upper bound of the range of user profile histories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.UserProfileHistory> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the user profile histories where userId = &#63; and classNameId = &#63; from the database.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_C(long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_C(userId, classNameId);
	}

	/**
	* Removes all the user profile histories where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	* Removes all the user profile histories from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of user profile histories where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @return the number of matching user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_C(long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_C(userId, classNameId);
	}

	/**
	* Returns the number of user profile histories where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	* Returns the number of user profile histories.
	*
	* @return the number of user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static UserProfileHistoryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (UserProfileHistoryPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					UserProfileHistoryPersistence.class.getName());

			ReferenceRegistry.registerReference(UserProfileHistoryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(UserProfileHistoryPersistence persistence) {
	}

	private static UserProfileHistoryPersistence _persistence;
}