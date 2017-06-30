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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the user profile history service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserProfileHistoryPersistenceImpl
 * @see UserProfileHistoryUtil
 * @generated
 */
public interface UserProfileHistoryPersistence extends BasePersistence<UserProfileHistory> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserProfileHistoryUtil} to access the user profile history persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the user profile history in the entity cache if it is enabled.
	*
	* @param userProfileHistory the user profile history
	*/
	public void cacheResult(
		com.liferay.osb.model.UserProfileHistory userProfileHistory);

	/**
	* Caches the user profile histories in the entity cache if it is enabled.
	*
	* @param userProfileHistories the user profile histories
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.UserProfileHistory> userProfileHistories);

	/**
	* Creates a new user profile history with the primary key. Does not add the user profile history to the database.
	*
	* @param userProfileHistoryId the primary key for the new user profile history
	* @return the new user profile history
	*/
	public com.liferay.osb.model.UserProfileHistory create(
		long userProfileHistoryId);

	/**
	* Removes the user profile history with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userProfileHistoryId the primary key of the user profile history
	* @return the user profile history that was removed
	* @throws com.liferay.osb.NoSuchUserProfileHistoryException if a user profile history with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.UserProfileHistory remove(
		long userProfileHistoryId)
		throws com.liferay.osb.NoSuchUserProfileHistoryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.UserProfileHistory updateImpl(
		com.liferay.osb.model.UserProfileHistory userProfileHistory,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user profile history with the primary key or throws a {@link com.liferay.osb.NoSuchUserProfileHistoryException} if it could not be found.
	*
	* @param userProfileHistoryId the primary key of the user profile history
	* @return the user profile history
	* @throws com.liferay.osb.NoSuchUserProfileHistoryException if a user profile history with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.UserProfileHistory findByPrimaryKey(
		long userProfileHistoryId)
		throws com.liferay.osb.NoSuchUserProfileHistoryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the user profile history with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param userProfileHistoryId the primary key of the user profile history
	* @return the user profile history, or <code>null</code> if a user profile history with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.UserProfileHistory fetchByPrimaryKey(
		long userProfileHistoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the user profile histories where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @return the matching user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.UserProfileHistory> findByU_C(
		long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.UserProfileHistory> findByU_C(
		long userId, long classNameId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.UserProfileHistory> findByU_C(
		long userId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.UserProfileHistory findByU_C_First(
		long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchUserProfileHistoryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first user profile history in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user profile history, or <code>null</code> if a matching user profile history could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.UserProfileHistory fetchByU_C_First(
		long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.UserProfileHistory findByU_C_Last(
		long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchUserProfileHistoryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last user profile history in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user profile history, or <code>null</code> if a matching user profile history could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.UserProfileHistory fetchByU_C_Last(
		long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.UserProfileHistory[] findByU_C_PrevAndNext(
		long userProfileHistoryId, long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchUserProfileHistoryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the user profile histories where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.UserProfileHistory> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.UserProfileHistory> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.UserProfileHistory> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.UserProfileHistory findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchUserProfileHistoryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first user profile history in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching user profile history, or <code>null</code> if a matching user profile history could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.UserProfileHistory fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.UserProfileHistory findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchUserProfileHistoryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last user profile history in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching user profile history, or <code>null</code> if a matching user profile history could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.UserProfileHistory fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.UserProfileHistory[] findByC_C_PrevAndNext(
		long userProfileHistoryId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchUserProfileHistoryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the user profile histories.
	*
	* @return the user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.UserProfileHistory> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.UserProfileHistory> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.UserProfileHistory> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the user profile histories where userId = &#63; and classNameId = &#63; from the database.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_C(long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the user profile histories where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the user profile histories from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user profile histories where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @return the number of matching user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_C(long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user profile histories where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of user profile histories.
	*
	* @return the number of user profile histories
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}