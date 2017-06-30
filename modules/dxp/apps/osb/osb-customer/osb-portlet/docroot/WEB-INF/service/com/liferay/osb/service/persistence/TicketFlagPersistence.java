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

import com.liferay.osb.model.TicketFlag;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ticket flag service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketFlagPersistenceImpl
 * @see TicketFlagUtil
 * @generated
 */
public interface TicketFlagPersistence extends BasePersistence<TicketFlag> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketFlagUtil} to access the ticket flag persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the ticket flag in the entity cache if it is enabled.
	*
	* @param ticketFlag the ticket flag
	*/
	public void cacheResult(com.liferay.osb.model.TicketFlag ticketFlag);

	/**
	* Caches the ticket flags in the entity cache if it is enabled.
	*
	* @param ticketFlags the ticket flags
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TicketFlag> ticketFlags);

	/**
	* Creates a new ticket flag with the primary key. Does not add the ticket flag to the database.
	*
	* @param ticketFlagId the primary key for the new ticket flag
	* @return the new ticket flag
	*/
	public com.liferay.osb.model.TicketFlag create(long ticketFlagId);

	/**
	* Removes the ticket flag with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFlagId the primary key of the ticket flag
	* @return the ticket flag that was removed
	* @throws com.liferay.osb.NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag remove(long ticketFlagId)
		throws com.liferay.osb.NoSuchTicketFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TicketFlag updateImpl(
		com.liferay.osb.model.TicketFlag ticketFlag, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket flag with the primary key or throws a {@link com.liferay.osb.NoSuchTicketFlagException} if it could not be found.
	*
	* @param ticketFlagId the primary key of the ticket flag
	* @return the ticket flag
	* @throws com.liferay.osb.NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag findByPrimaryKey(long ticketFlagId)
		throws com.liferay.osb.NoSuchTicketFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket flag with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketFlagId the primary key of the ticket flag
	* @return the ticket flag, or <code>null</code> if a ticket flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag fetchByPrimaryKey(long ticketFlagId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket flags where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> findByTicketEntryId(
		long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket flags where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @return the range of matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> findByTicketEntryId(
		long ticketEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket flags where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> findByTicketEntryId(
		long ticketEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket flag in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag
	* @throws com.liferay.osb.NoSuchTicketFlagException if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag findByTicketEntryId_First(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket flag in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag fetchByTicketEntryId_First(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket flag in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag
	* @throws com.liferay.osb.NoSuchTicketFlagException if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag findByTicketEntryId_Last(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket flag in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag fetchByTicketEntryId_Last(
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket flags before and after the current ticket flag in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketFlagId the primary key of the current ticket flag
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket flag
	* @throws com.liferay.osb.NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag[] findByTicketEntryId_PrevAndNext(
		long ticketFlagId, long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket flags where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @return the matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> findByAEI_T(
		long accountEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket flags where accountEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @return the range of matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> findByAEI_T(
		long accountEntryId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket flags where accountEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> findByAEI_T(
		long accountEntryId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag
	* @throws com.liferay.osb.NoSuchTicketFlagException if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag findByAEI_T_First(
		long accountEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag fetchByAEI_T_First(
		long accountEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag
	* @throws com.liferay.osb.NoSuchTicketFlagException if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag findByAEI_T_Last(
		long accountEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag fetchByAEI_T_Last(
		long accountEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket flags before and after the current ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param ticketFlagId the primary key of the current ticket flag
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket flag
	* @throws com.liferay.osb.NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag[] findByAEI_T_PrevAndNext(
		long ticketFlagId, long accountEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket flags where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> findByTEI_T(
		long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket flags where ticketEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @return the range of matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> findByTEI_T(
		long ticketEntryId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket flags where ticketEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> findByTEI_T(
		long ticketEntryId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag
	* @throws com.liferay.osb.NoSuchTicketFlagException if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag findByTEI_T_First(
		long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag fetchByTEI_T_First(
		long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag
	* @throws com.liferay.osb.NoSuchTicketFlagException if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag findByTEI_T_Last(
		long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag fetchByTEI_T_Last(
		long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket flags before and after the current ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketFlagId the primary key of the current ticket flag
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket flag
	* @throws com.liferay.osb.NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag[] findByTEI_T_PrevAndNext(
		long ticketFlagId, long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @return the matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> findByTEI_T_F(
		long ticketEntryId, int type, int flag)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @return the range of matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> findByTEI_T_F(
		long ticketEntryId, int type, int flag, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> findByTEI_T_F(
		long ticketEntryId, int type, int flag, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag
	* @throws com.liferay.osb.NoSuchTicketFlagException if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag findByTEI_T_F_First(
		long ticketEntryId, int type, int flag,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag fetchByTEI_T_F_First(
		long ticketEntryId, int type, int flag,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag
	* @throws com.liferay.osb.NoSuchTicketFlagException if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag findByTEI_T_F_Last(
		long ticketEntryId, int type, int flag,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag fetchByTEI_T_F_Last(
		long ticketEntryId, int type, int flag,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket flags before and after the current ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* @param ticketFlagId the primary key of the current ticket flag
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket flag
	* @throws com.liferay.osb.NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag[] findByTEI_T_F_PrevAndNext(
		long ticketFlagId, long ticketEntryId, int type, int flag,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTicketFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket flags where ticketEntryId = &#63; and type = any &#63; and flag = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param types the types
	* @param flag the flag
	* @return the matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> findByTEI_T_F(
		long ticketEntryId, int[] types, int flag)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket flags where ticketEntryId = &#63; and type = any &#63; and flag = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param types the types
	* @param flag the flag
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @return the range of matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> findByTEI_T_F(
		long ticketEntryId, int[] types, int flag, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket flags where ticketEntryId = &#63; and type = any &#63; and flag = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param types the types
	* @param flag the flag
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> findByTEI_T_F(
		long ticketEntryId, int[] types, int flag, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket flag where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63; or throws a {@link com.liferay.osb.NoSuchTicketFlagException} if it could not be found.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the matching ticket flag
	* @throws com.liferay.osb.NoSuchTicketFlagException if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag findByU_AEI_TEI_T(long userId,
		long accountEntryId, long ticketEntryId, int type)
		throws com.liferay.osb.NoSuchTicketFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket flag where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag fetchByU_AEI_TEI_T(long userId,
		long accountEntryId, long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the ticket flag where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag fetchByU_AEI_TEI_T(long userId,
		long accountEntryId, long ticketEntryId, int type,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the ticket flags.
	*
	* @return the ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the ticket flags.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @return the range of ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the ticket flags.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TicketFlag> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket flags where ticketEntryId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTicketEntryId(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket flags where accountEntryId = &#63; and type = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAEI_T(long accountEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket flags where ticketEntryId = &#63; and type = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTEI_T(long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @throws SystemException if a system exception occurred
	*/
	public void removeByTEI_T_F(long ticketEntryId, int type, int flag)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the ticket flag where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the ticket flag that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TicketFlag removeByU_AEI_TEI_T(long userId,
		long accountEntryId, long ticketEntryId, int type)
		throws com.liferay.osb.NoSuchTicketFlagException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the ticket flags from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket flags where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public int countByTicketEntryId(long ticketEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket flags where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @return the number of matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI_T(long accountEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket flags where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the number of matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public int countByTEI_T(long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @return the number of matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public int countByTEI_T_F(long ticketEntryId, int type, int flag)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket flags where ticketEntryId = &#63; and type = any &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param types the types
	* @param flag the flag
	* @return the number of matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public int countByTEI_T_F(long ticketEntryId, int[] types, int flag)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket flags where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the number of matching ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_AEI_TEI_T(long userId, long accountEntryId,
		long ticketEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of ticket flags.
	*
	* @return the number of ticket flags
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}