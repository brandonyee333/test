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

import com.liferay.osb.model.CorpProjectMessage;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the corp project message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectMessagePersistenceImpl
 * @see CorpProjectMessageUtil
 * @generated
 */
public interface CorpProjectMessagePersistence extends BasePersistence<CorpProjectMessage> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CorpProjectMessageUtil} to access the corp project message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the corp project message in the entity cache if it is enabled.
	*
	* @param corpProjectMessage the corp project message
	*/
	public void cacheResult(
		com.liferay.osb.model.CorpProjectMessage corpProjectMessage);

	/**
	* Caches the corp project messages in the entity cache if it is enabled.
	*
	* @param corpProjectMessages the corp project messages
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.CorpProjectMessage> corpProjectMessages);

	/**
	* Creates a new corp project message with the primary key. Does not add the corp project message to the database.
	*
	* @param corpProjectMessageId the primary key for the new corp project message
	* @return the new corp project message
	*/
	public com.liferay.osb.model.CorpProjectMessage create(
		long corpProjectMessageId);

	/**
	* Removes the corp project message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpProjectMessageId the primary key of the corp project message
	* @return the corp project message that was removed
	* @throws com.liferay.osb.NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProjectMessage remove(
		long corpProjectMessageId)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.CorpProjectMessage updateImpl(
		com.liferay.osb.model.CorpProjectMessage corpProjectMessage,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp project message with the primary key or throws a {@link com.liferay.osb.NoSuchCorpProjectMessageException} if it could not be found.
	*
	* @param corpProjectMessageId the primary key of the corp project message
	* @return the corp project message
	* @throws com.liferay.osb.NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProjectMessage findByPrimaryKey(
		long corpProjectMessageId)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp project message with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param corpProjectMessageId the primary key of the corp project message
	* @return the corp project message, or <code>null</code> if a corp project message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProjectMessage fetchByPrimaryKey(
		long corpProjectMessageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the corp project messages where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @return the matching corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpProjectMessage> findByCorpProjectId(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the corp project messages where corpProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param corpProjectId the corp project ID
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @return the range of matching corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpProjectMessage> findByCorpProjectId(
		long corpProjectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the corp project messages where corpProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param corpProjectId the corp project ID
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpProjectMessage> findByCorpProjectId(
		long corpProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message
	* @throws com.liferay.osb.NoSuchCorpProjectMessageException if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProjectMessage findByCorpProjectId_First(
		long corpProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message, or <code>null</code> if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProjectMessage fetchByCorpProjectId_First(
		long corpProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message
	* @throws com.liferay.osb.NoSuchCorpProjectMessageException if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProjectMessage findByCorpProjectId_Last(
		long corpProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message, or <code>null</code> if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProjectMessage fetchByCorpProjectId_Last(
		long corpProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp project messages before and after the current corp project message in the ordered set where corpProjectId = &#63;.
	*
	* @param corpProjectMessageId the primary key of the current corp project message
	* @param corpProjectId the corp project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next corp project message
	* @throws com.liferay.osb.NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProjectMessage[] findByCorpProjectId_PrevAndNext(
		long corpProjectMessageId, long corpProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the corp project messages where type = &#63;.
	*
	* @param type the type
	* @return the matching corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpProjectMessage> findByType(
		int type) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the corp project messages where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @return the range of matching corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpProjectMessage> findByType(
		int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the corp project messages where type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpProjectMessage> findByType(
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first corp project message in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message
	* @throws com.liferay.osb.NoSuchCorpProjectMessageException if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProjectMessage findByType_First(int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first corp project message in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project message, or <code>null</code> if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProjectMessage fetchByType_First(
		int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last corp project message in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message
	* @throws com.liferay.osb.NoSuchCorpProjectMessageException if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProjectMessage findByType_Last(int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last corp project message in the ordered set where type = &#63;.
	*
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project message, or <code>null</code> if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProjectMessage fetchByType_Last(int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp project messages before and after the current corp project message in the ordered set where type = &#63;.
	*
	* @param corpProjectMessageId the primary key of the current corp project message
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next corp project message
	* @throws com.liferay.osb.NoSuchCorpProjectMessageException if a corp project message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProjectMessage[] findByType_PrevAndNext(
		long corpProjectMessageId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp project message where corpProjectId = &#63; and type = &#63; or throws a {@link com.liferay.osb.NoSuchCorpProjectMessageException} if it could not be found.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @return the matching corp project message
	* @throws com.liferay.osb.NoSuchCorpProjectMessageException if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProjectMessage findByC_T(
		long corpProjectId, int type)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp project message where corpProjectId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @return the matching corp project message, or <code>null</code> if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProjectMessage fetchByC_T(
		long corpProjectId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp project message where corpProjectId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching corp project message, or <code>null</code> if a matching corp project message could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProjectMessage fetchByC_T(
		long corpProjectId, int type, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the corp project messages.
	*
	* @return the corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpProjectMessage> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the corp project messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @return the range of corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpProjectMessage> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the corp project messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpProjectMessage> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the corp project messages where corpProjectId = &#63; from the database.
	*
	* @param corpProjectId the corp project ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCorpProjectId(long corpProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the corp project messages where type = &#63; from the database.
	*
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByType(int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the corp project message where corpProjectId = &#63; and type = &#63; from the database.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @return the corp project message that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProjectMessage removeByC_T(
		long corpProjectId, int type)
		throws com.liferay.osb.NoSuchCorpProjectMessageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the corp project messages from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp project messages where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @return the number of matching corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public int countByCorpProjectId(long corpProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp project messages where type = &#63;.
	*
	* @param type the type
	* @return the number of matching corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public int countByType(int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp project messages where corpProjectId = &#63; and type = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @param type the type
	* @return the number of matching corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_T(long corpProjectId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp project messages.
	*
	* @return the number of corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}