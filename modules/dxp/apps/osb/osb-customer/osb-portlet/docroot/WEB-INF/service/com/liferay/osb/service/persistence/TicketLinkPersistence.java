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

import com.liferay.osb.exception.NoSuchTicketLinkException;
import com.liferay.osb.model.TicketLink;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the ticket link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.TicketLinkPersistenceImpl
 * @see TicketLinkUtil
 * @generated
 */
@ProviderType
public interface TicketLinkPersistence extends BasePersistence<TicketLink> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TicketLinkUtil} to access the ticket link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the ticket links where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket links
	*/
	public java.util.List<TicketLink> findByTicketEntryId(long ticketEntryId);

	/**
	* Returns a range of all the ticket links where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @return the range of matching ticket links
	*/
	public java.util.List<TicketLink> findByTicketEntryId(long ticketEntryId,
		int start, int end);

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket links
	*/
	public java.util.List<TicketLink> findByTicketEntryId(long ticketEntryId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator);

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket links
	*/
	public java.util.List<TicketLink> findByTicketEntryId(long ticketEntryId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket link in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket link
	* @throws NoSuchTicketLinkException if a matching ticket link could not be found
	*/
	public TicketLink findByTicketEntryId_First(long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator)
		throws NoSuchTicketLinkException;

	/**
	* Returns the first ticket link in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket link, or <code>null</code> if a matching ticket link could not be found
	*/
	public TicketLink fetchByTicketEntryId_First(long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator);

	/**
	* Returns the last ticket link in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket link
	* @throws NoSuchTicketLinkException if a matching ticket link could not be found
	*/
	public TicketLink findByTicketEntryId_Last(long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator)
		throws NoSuchTicketLinkException;

	/**
	* Returns the last ticket link in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket link, or <code>null</code> if a matching ticket link could not be found
	*/
	public TicketLink fetchByTicketEntryId_Last(long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator);

	/**
	* Returns the ticket links before and after the current ticket link in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketLinkId the primary key of the current ticket link
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket link
	* @throws NoSuchTicketLinkException if a ticket link with the primary key could not be found
	*/
	public TicketLink[] findByTicketEntryId_PrevAndNext(long ticketLinkId,
		long ticketEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator)
		throws NoSuchTicketLinkException;

	/**
	* Removes all the ticket links where ticketEntryId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	*/
	public void removeByTicketEntryId(long ticketEntryId);

	/**
	* Returns the number of ticket links where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket links
	*/
	public int countByTicketEntryId(long ticketEntryId);

	/**
	* Returns all the ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @return the matching ticket links
	*/
	public java.util.List<TicketLink> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId);

	/**
	* Returns a range of all the ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @return the range of matching ticket links
	*/
	public java.util.List<TicketLink> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end);

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket links
	*/
	public java.util.List<TicketLink> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator);

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket links
	*/
	public java.util.List<TicketLink> findByTEI_TSI(long ticketEntryId,
		long ticketSolutionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket link
	* @throws NoSuchTicketLinkException if a matching ticket link could not be found
	*/
	public TicketLink findByTEI_TSI_First(long ticketEntryId,
		long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator)
		throws NoSuchTicketLinkException;

	/**
	* Returns the first ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket link, or <code>null</code> if a matching ticket link could not be found
	*/
	public TicketLink fetchByTEI_TSI_First(long ticketEntryId,
		long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator);

	/**
	* Returns the last ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket link
	* @throws NoSuchTicketLinkException if a matching ticket link could not be found
	*/
	public TicketLink findByTEI_TSI_Last(long ticketEntryId,
		long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator)
		throws NoSuchTicketLinkException;

	/**
	* Returns the last ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket link, or <code>null</code> if a matching ticket link could not be found
	*/
	public TicketLink fetchByTEI_TSI_Last(long ticketEntryId,
		long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator);

	/**
	* Returns the ticket links before and after the current ticket link in the ordered set where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketLinkId the primary key of the current ticket link
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket link
	* @throws NoSuchTicketLinkException if a ticket link with the primary key could not be found
	*/
	public TicketLink[] findByTEI_TSI_PrevAndNext(long ticketLinkId,
		long ticketEntryId, long ticketSolutionId,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator)
		throws NoSuchTicketLinkException;

	/**
	* Removes all the ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	*/
	public void removeByTEI_TSI(long ticketEntryId, long ticketSolutionId);

	/**
	* Returns the number of ticket links where ticketEntryId = &#63; and ticketSolutionId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param ticketSolutionId the ticket solution ID
	* @return the number of matching ticket links
	*/
	public int countByTEI_TSI(long ticketEntryId, long ticketSolutionId);

	/**
	* Returns all the ticket links where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @return the matching ticket links
	*/
	public java.util.List<TicketLink> findByTEI_V(long ticketEntryId,
		int visibility);

	/**
	* Returns a range of all the ticket links where ticketEntryId = &#63; and visibility = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @return the range of matching ticket links
	*/
	public java.util.List<TicketLink> findByTEI_V(long ticketEntryId,
		int visibility, int start, int end);

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63; and visibility = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket links
	*/
	public java.util.List<TicketLink> findByTEI_V(long ticketEntryId,
		int visibility, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator);

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63; and visibility = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket links
	*/
	public java.util.List<TicketLink> findByTEI_V(long ticketEntryId,
		int visibility, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket link
	* @throws NoSuchTicketLinkException if a matching ticket link could not be found
	*/
	public TicketLink findByTEI_V_First(long ticketEntryId, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator)
		throws NoSuchTicketLinkException;

	/**
	* Returns the first ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket link, or <code>null</code> if a matching ticket link could not be found
	*/
	public TicketLink fetchByTEI_V_First(long ticketEntryId, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator);

	/**
	* Returns the last ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket link
	* @throws NoSuchTicketLinkException if a matching ticket link could not be found
	*/
	public TicketLink findByTEI_V_Last(long ticketEntryId, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator)
		throws NoSuchTicketLinkException;

	/**
	* Returns the last ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket link, or <code>null</code> if a matching ticket link could not be found
	*/
	public TicketLink fetchByTEI_V_Last(long ticketEntryId, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator);

	/**
	* Returns the ticket links before and after the current ticket link in the ordered set where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketLinkId the primary key of the current ticket link
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket link
	* @throws NoSuchTicketLinkException if a ticket link with the primary key could not be found
	*/
	public TicketLink[] findByTEI_V_PrevAndNext(long ticketLinkId,
		long ticketEntryId, int visibility,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator)
		throws NoSuchTicketLinkException;

	/**
	* Returns all the ticket links where ticketEntryId = &#63; and visibility = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @return the matching ticket links
	*/
	public java.util.List<TicketLink> findByTEI_V(long ticketEntryId,
		int[] visibilities);

	/**
	* Returns a range of all the ticket links where ticketEntryId = &#63; and visibility = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @return the range of matching ticket links
	*/
	public java.util.List<TicketLink> findByTEI_V(long ticketEntryId,
		int[] visibilities, int start, int end);

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63; and visibility = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket links
	*/
	public java.util.List<TicketLink> findByTEI_V(long ticketEntryId,
		int[] visibilities, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator);

	/**
	* Returns an ordered range of all the ticket links where ticketEntryId = &#63; and visibility = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket links
	*/
	public java.util.List<TicketLink> findByTEI_V(long ticketEntryId,
		int[] visibilities, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ticket links where ticketEntryId = &#63; and visibility = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	*/
	public void removeByTEI_V(long ticketEntryId, int visibility);

	/**
	* Returns the number of ticket links where ticketEntryId = &#63; and visibility = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibility the visibility
	* @return the number of matching ticket links
	*/
	public int countByTEI_V(long ticketEntryId, int visibility);

	/**
	* Returns the number of ticket links where ticketEntryId = &#63; and visibility = any &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param visibilities the visibilities
	* @return the number of matching ticket links
	*/
	public int countByTEI_V(long ticketEntryId, int[] visibilities);

	/**
	* Caches the ticket link in the entity cache if it is enabled.
	*
	* @param ticketLink the ticket link
	*/
	public void cacheResult(TicketLink ticketLink);

	/**
	* Caches the ticket links in the entity cache if it is enabled.
	*
	* @param ticketLinks the ticket links
	*/
	public void cacheResult(java.util.List<TicketLink> ticketLinks);

	/**
	* Creates a new ticket link with the primary key. Does not add the ticket link to the database.
	*
	* @param ticketLinkId the primary key for the new ticket link
	* @return the new ticket link
	*/
	public TicketLink create(long ticketLinkId);

	/**
	* Removes the ticket link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketLinkId the primary key of the ticket link
	* @return the ticket link that was removed
	* @throws NoSuchTicketLinkException if a ticket link with the primary key could not be found
	*/
	public TicketLink remove(long ticketLinkId)
		throws NoSuchTicketLinkException;

	public TicketLink updateImpl(TicketLink ticketLink);

	/**
	* Returns the ticket link with the primary key or throws a {@link NoSuchTicketLinkException} if it could not be found.
	*
	* @param ticketLinkId the primary key of the ticket link
	* @return the ticket link
	* @throws NoSuchTicketLinkException if a ticket link with the primary key could not be found
	*/
	public TicketLink findByPrimaryKey(long ticketLinkId)
		throws NoSuchTicketLinkException;

	/**
	* Returns the ticket link with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketLinkId the primary key of the ticket link
	* @return the ticket link, or <code>null</code> if a ticket link with the primary key could not be found
	*/
	public TicketLink fetchByPrimaryKey(long ticketLinkId);

	@Override
	public java.util.Map<java.io.Serializable, TicketLink> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the ticket links.
	*
	* @return the ticket links
	*/
	public java.util.List<TicketLink> findAll();

	/**
	* Returns a range of all the ticket links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @return the range of ticket links
	*/
	public java.util.List<TicketLink> findAll(int start, int end);

	/**
	* Returns an ordered range of all the ticket links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket links
	*/
	public java.util.List<TicketLink> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator);

	/**
	* Returns an ordered range of all the ticket links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket links
	* @param end the upper bound of the range of ticket links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ticket links
	*/
	public java.util.List<TicketLink> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<TicketLink> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the ticket links from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of ticket links.
	*
	* @return the number of ticket links
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}