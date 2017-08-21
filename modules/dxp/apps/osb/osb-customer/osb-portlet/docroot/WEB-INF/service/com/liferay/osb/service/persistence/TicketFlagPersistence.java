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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.exception.NoSuchTicketFlagException;
import com.liferay.osb.model.TicketFlag;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the ticket flag service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.TicketFlagPersistenceImpl
 * @see TicketFlagUtil
 * @generated
 */
@ProviderType
public interface TicketFlagPersistence extends BasePersistence<TicketFlag> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketFlagUtil} to access the ticket flag persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the ticket flags where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @return the matching ticket flags
	*/
	public java.util.List<TicketFlag> findByAEI_T(long accountEntryId, int type);

	/**
	* Returns a range of all the ticket flags where accountEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @return the range of matching ticket flags
	*/
	public java.util.List<TicketFlag> findByAEI_T(long accountEntryId,
		int type, int start, int end);

	/**
	* Returns an ordered range of all the ticket flags where accountEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket flags
	*/
	public java.util.List<TicketFlag> findByAEI_T(long accountEntryId,
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator);

	/**
	* Returns an ordered range of all the ticket flags where accountEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket flags
	*/
	public java.util.List<TicketFlag> findByAEI_T(long accountEntryId,
		int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag
	* @throws NoSuchTicketFlagException if a matching ticket flag could not be found
	*/
	public TicketFlag findByAEI_T_First(long accountEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator)
		throws NoSuchTicketFlagException;

	/**
	* Returns the first ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	*/
	public TicketFlag fetchByAEI_T_First(long accountEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator);

	/**
	* Returns the last ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag
	* @throws NoSuchTicketFlagException if a matching ticket flag could not be found
	*/
	public TicketFlag findByAEI_T_Last(long accountEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator)
		throws NoSuchTicketFlagException;

	/**
	* Returns the last ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	*/
	public TicketFlag fetchByAEI_T_Last(long accountEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator);

	/**
	* Returns the ticket flags before and after the current ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param ticketFlagId the primary key of the current ticket flag
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket flag
	* @throws NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	*/
	public TicketFlag[] findByAEI_T_PrevAndNext(long ticketFlagId,
		long accountEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator)
		throws NoSuchTicketFlagException;

	/**
	* Removes all the ticket flags where accountEntryId = &#63; and type = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	*/
	public void removeByAEI_T(long accountEntryId, int type);

	/**
	* Returns the number of ticket flags where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @return the number of matching ticket flags
	*/
	public int countByAEI_T(long accountEntryId, int type);

	/**
	* Returns all the ticket flags where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the matching ticket flags
	*/
	public java.util.List<TicketFlag> findByTEI_T(long ticketEntryId, int type);

	/**
	* Returns a range of all the ticket flags where ticketEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @return the range of matching ticket flags
	*/
	public java.util.List<TicketFlag> findByTEI_T(long ticketEntryId, int type,
		int start, int end);

	/**
	* Returns an ordered range of all the ticket flags where ticketEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket flags
	*/
	public java.util.List<TicketFlag> findByTEI_T(long ticketEntryId, int type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator);

	/**
	* Returns an ordered range of all the ticket flags where ticketEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket flags
	*/
	public java.util.List<TicketFlag> findByTEI_T(long ticketEntryId, int type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag
	* @throws NoSuchTicketFlagException if a matching ticket flag could not be found
	*/
	public TicketFlag findByTEI_T_First(long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator)
		throws NoSuchTicketFlagException;

	/**
	* Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	*/
	public TicketFlag fetchByTEI_T_First(long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator);

	/**
	* Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag
	* @throws NoSuchTicketFlagException if a matching ticket flag could not be found
	*/
	public TicketFlag findByTEI_T_Last(long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator)
		throws NoSuchTicketFlagException;

	/**
	* Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	*/
	public TicketFlag fetchByTEI_T_Last(long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator);

	/**
	* Returns the ticket flags before and after the current ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketFlagId the primary key of the current ticket flag
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket flag
	* @throws NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	*/
	public TicketFlag[] findByTEI_T_PrevAndNext(long ticketFlagId,
		long ticketEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator)
		throws NoSuchTicketFlagException;

	/**
	* Removes all the ticket flags where ticketEntryId = &#63; and type = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	*/
	public void removeByTEI_T(long ticketEntryId, int type);

	/**
	* Returns the number of ticket flags where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the number of matching ticket flags
	*/
	public int countByTEI_T(long ticketEntryId, int type);

	/**
	* Returns all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @return the matching ticket flags
	*/
	public java.util.List<TicketFlag> findByTEI_T_F(long ticketEntryId,
		int type, int flag);

	/**
	* Returns a range of all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @return the range of matching ticket flags
	*/
	public java.util.List<TicketFlag> findByTEI_T_F(long ticketEntryId,
		int type, int flag, int start, int end);

	/**
	* Returns an ordered range of all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket flags
	*/
	public java.util.List<TicketFlag> findByTEI_T_F(long ticketEntryId,
		int type, int flag, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator);

	/**
	* Returns an ordered range of all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket flags
	*/
	public java.util.List<TicketFlag> findByTEI_T_F(long ticketEntryId,
		int type, int flag, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag
	* @throws NoSuchTicketFlagException if a matching ticket flag could not be found
	*/
	public TicketFlag findByTEI_T_F_First(long ticketEntryId, int type,
		int flag,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator)
		throws NoSuchTicketFlagException;

	/**
	* Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	*/
	public TicketFlag fetchByTEI_T_F_First(long ticketEntryId, int type,
		int flag,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator);

	/**
	* Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag
	* @throws NoSuchTicketFlagException if a matching ticket flag could not be found
	*/
	public TicketFlag findByTEI_T_F_Last(long ticketEntryId, int type,
		int flag,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator)
		throws NoSuchTicketFlagException;

	/**
	* Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	*/
	public TicketFlag fetchByTEI_T_F_Last(long ticketEntryId, int type,
		int flag,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator);

	/**
	* Returns the ticket flags before and after the current ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* @param ticketFlagId the primary key of the current ticket flag
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket flag
	* @throws NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	*/
	public TicketFlag[] findByTEI_T_F_PrevAndNext(long ticketFlagId,
		long ticketEntryId, int type, int flag,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator)
		throws NoSuchTicketFlagException;

	/**
	* Returns all the ticket flags where ticketEntryId = &#63; and type = any &#63; and flag = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param types the types
	* @param flag the flag
	* @return the matching ticket flags
	*/
	public java.util.List<TicketFlag> findByTEI_T_F(long ticketEntryId,
		int[] types, int flag);

	/**
	* Returns a range of all the ticket flags where ticketEntryId = &#63; and type = any &#63; and flag = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param types the types
	* @param flag the flag
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @return the range of matching ticket flags
	*/
	public java.util.List<TicketFlag> findByTEI_T_F(long ticketEntryId,
		int[] types, int flag, int start, int end);

	/**
	* Returns an ordered range of all the ticket flags where ticketEntryId = &#63; and type = any &#63; and flag = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param types the types
	* @param flag the flag
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket flags
	*/
	public java.util.List<TicketFlag> findByTEI_T_F(long ticketEntryId,
		int[] types, int flag, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator);

	/**
	* Returns an ordered range of all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket flags
	*/
	public java.util.List<TicketFlag> findByTEI_T_F(long ticketEntryId,
		int[] types, int flag, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	*/
	public void removeByTEI_T_F(long ticketEntryId, int type, int flag);

	/**
	* Returns the number of ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @return the number of matching ticket flags
	*/
	public int countByTEI_T_F(long ticketEntryId, int type, int flag);

	/**
	* Returns the number of ticket flags where ticketEntryId = &#63; and type = any &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param types the types
	* @param flag the flag
	* @return the number of matching ticket flags
	*/
	public int countByTEI_T_F(long ticketEntryId, int[] types, int flag);

	/**
	* Returns the ticket flag where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63; or throws a {@link NoSuchTicketFlagException} if it could not be found.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the matching ticket flag
	* @throws NoSuchTicketFlagException if a matching ticket flag could not be found
	*/
	public TicketFlag findByU_AEI_TEI_T(long userId, long accountEntryId,
		long ticketEntryId, int type) throws NoSuchTicketFlagException;

	/**
	* Returns the ticket flag where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	*/
	public TicketFlag fetchByU_AEI_TEI_T(long userId, long accountEntryId,
		long ticketEntryId, int type);

	/**
	* Returns the ticket flag where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	*/
	public TicketFlag fetchByU_AEI_TEI_T(long userId, long accountEntryId,
		long ticketEntryId, int type, boolean retrieveFromCache);

	/**
	* Removes the ticket flag where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the ticket flag that was removed
	*/
	public TicketFlag removeByU_AEI_TEI_T(long userId, long accountEntryId,
		long ticketEntryId, int type) throws NoSuchTicketFlagException;

	/**
	* Returns the number of ticket flags where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the number of matching ticket flags
	*/
	public int countByU_AEI_TEI_T(long userId, long accountEntryId,
		long ticketEntryId, int type);

	/**
	* Caches the ticket flag in the entity cache if it is enabled.
	*
	* @param ticketFlag the ticket flag
	*/
	public void cacheResult(TicketFlag ticketFlag);

	/**
	* Caches the ticket flags in the entity cache if it is enabled.
	*
	* @param ticketFlags the ticket flags
	*/
	public void cacheResult(java.util.List<TicketFlag> ticketFlags);

	/**
	* Creates a new ticket flag with the primary key. Does not add the ticket flag to the database.
	*
	* @param ticketFlagId the primary key for the new ticket flag
	* @return the new ticket flag
	*/
	public TicketFlag create(long ticketFlagId);

	/**
	* Removes the ticket flag with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFlagId the primary key of the ticket flag
	* @return the ticket flag that was removed
	* @throws NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	*/
	public TicketFlag remove(long ticketFlagId)
		throws NoSuchTicketFlagException;

	public TicketFlag updateImpl(TicketFlag ticketFlag);

	/**
	* Returns the ticket flag with the primary key or throws a {@link NoSuchTicketFlagException} if it could not be found.
	*
	* @param ticketFlagId the primary key of the ticket flag
	* @return the ticket flag
	* @throws NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	*/
	public TicketFlag findByPrimaryKey(long ticketFlagId)
		throws NoSuchTicketFlagException;

	/**
	* Returns the ticket flag with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketFlagId the primary key of the ticket flag
	* @return the ticket flag, or <code>null</code> if a ticket flag with the primary key could not be found
	*/
	public TicketFlag fetchByPrimaryKey(long ticketFlagId);

	@Override
	public java.util.Map<java.io.Serializable, TicketFlag> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ticket flags.
	*
	* @return the ticket flags
	*/
	public java.util.List<TicketFlag> findAll();

	/**
	* Returns a range of all the ticket flags.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @return the range of ticket flags
	*/
	public java.util.List<TicketFlag> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ticket flags.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket flags
	*/
	public java.util.List<TicketFlag> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator);

	/**
	* Returns an ordered range of all the ticket flags.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket flags
	* @param end the upper bound of the range of ticket flags (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ticket flags
	*/
	public java.util.List<TicketFlag> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketFlag> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ticket flags from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ticket flags.
	*
	* @return the number of ticket flags
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}