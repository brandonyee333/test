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

import com.liferay.osb.model.TicketFlag;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the ticket flag service. This utility wraps {@link com.liferay.osb.service.persistence.impl.TicketFlagPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketFlagPersistence
 * @see com.liferay.osb.service.persistence.impl.TicketFlagPersistenceImpl
 * @generated
 */
@ProviderType
public class TicketFlagUtil {
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
	public static void clearCache(TicketFlag ticketFlag) {
		getPersistence().clearCache(ticketFlag);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<TicketFlag> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TicketFlag> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TicketFlag> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TicketFlag> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TicketFlag update(TicketFlag ticketFlag) {
		return getPersistence().update(ticketFlag);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TicketFlag update(TicketFlag ticketFlag,
		ServiceContext serviceContext) {
		return getPersistence().update(ticketFlag, serviceContext);
	}

	/**
	* Returns all the ticket flags where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @return the matching ticket flags
	*/
	public static List<TicketFlag> findByAEI_T(long accountEntryId, int type) {
		return getPersistence().findByAEI_T(accountEntryId, type);
	}

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
	public static List<TicketFlag> findByAEI_T(long accountEntryId, int type,
		int start, int end) {
		return getPersistence().findByAEI_T(accountEntryId, type, start, end);
	}

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
	public static List<TicketFlag> findByAEI_T(long accountEntryId, int type,
		int start, int end, OrderByComparator<TicketFlag> orderByComparator) {
		return getPersistence()
				   .findByAEI_T(accountEntryId, type, start, end,
			orderByComparator);
	}

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
	public static List<TicketFlag> findByAEI_T(long accountEntryId, int type,
		int start, int end, OrderByComparator<TicketFlag> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAEI_T(accountEntryId, type, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag
	* @throws NoSuchTicketFlagException if a matching ticket flag could not be found
	*/
	public static TicketFlag findByAEI_T_First(long accountEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketFlagException {
		return getPersistence()
				   .findByAEI_T_First(accountEntryId, type, orderByComparator);
	}

	/**
	* Returns the first ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	*/
	public static TicketFlag fetchByAEI_T_First(long accountEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator) {
		return getPersistence()
				   .fetchByAEI_T_First(accountEntryId, type, orderByComparator);
	}

	/**
	* Returns the last ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag
	* @throws NoSuchTicketFlagException if a matching ticket flag could not be found
	*/
	public static TicketFlag findByAEI_T_Last(long accountEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketFlagException {
		return getPersistence()
				   .findByAEI_T_Last(accountEntryId, type, orderByComparator);
	}

	/**
	* Returns the last ticket flag in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	*/
	public static TicketFlag fetchByAEI_T_Last(long accountEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator) {
		return getPersistence()
				   .fetchByAEI_T_Last(accountEntryId, type, orderByComparator);
	}

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
	public static TicketFlag[] findByAEI_T_PrevAndNext(long ticketFlagId,
		long accountEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketFlagException {
		return getPersistence()
				   .findByAEI_T_PrevAndNext(ticketFlagId, accountEntryId, type,
			orderByComparator);
	}

	/**
	* Removes all the ticket flags where accountEntryId = &#63; and type = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	*/
	public static void removeByAEI_T(long accountEntryId, int type) {
		getPersistence().removeByAEI_T(accountEntryId, type);
	}

	/**
	* Returns the number of ticket flags where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @return the number of matching ticket flags
	*/
	public static int countByAEI_T(long accountEntryId, int type) {
		return getPersistence().countByAEI_T(accountEntryId, type);
	}

	/**
	* Returns all the ticket flags where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the matching ticket flags
	*/
	public static List<TicketFlag> findByTEI_T(long ticketEntryId, int type) {
		return getPersistence().findByTEI_T(ticketEntryId, type);
	}

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
	public static List<TicketFlag> findByTEI_T(long ticketEntryId, int type,
		int start, int end) {
		return getPersistence().findByTEI_T(ticketEntryId, type, start, end);
	}

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
	public static List<TicketFlag> findByTEI_T(long ticketEntryId, int type,
		int start, int end, OrderByComparator<TicketFlag> orderByComparator) {
		return getPersistence()
				   .findByTEI_T(ticketEntryId, type, start, end,
			orderByComparator);
	}

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
	public static List<TicketFlag> findByTEI_T(long ticketEntryId, int type,
		int start, int end, OrderByComparator<TicketFlag> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTEI_T(ticketEntryId, type, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag
	* @throws NoSuchTicketFlagException if a matching ticket flag could not be found
	*/
	public static TicketFlag findByTEI_T_First(long ticketEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketFlagException {
		return getPersistence()
				   .findByTEI_T_First(ticketEntryId, type, orderByComparator);
	}

	/**
	* Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	*/
	public static TicketFlag fetchByTEI_T_First(long ticketEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator) {
		return getPersistence()
				   .fetchByTEI_T_First(ticketEntryId, type, orderByComparator);
	}

	/**
	* Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag
	* @throws NoSuchTicketFlagException if a matching ticket flag could not be found
	*/
	public static TicketFlag findByTEI_T_Last(long ticketEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketFlagException {
		return getPersistence()
				   .findByTEI_T_Last(ticketEntryId, type, orderByComparator);
	}

	/**
	* Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	*/
	public static TicketFlag fetchByTEI_T_Last(long ticketEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator) {
		return getPersistence()
				   .fetchByTEI_T_Last(ticketEntryId, type, orderByComparator);
	}

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
	public static TicketFlag[] findByTEI_T_PrevAndNext(long ticketFlagId,
		long ticketEntryId, int type,
		OrderByComparator<TicketFlag> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketFlagException {
		return getPersistence()
				   .findByTEI_T_PrevAndNext(ticketFlagId, ticketEntryId, type,
			orderByComparator);
	}

	/**
	* Removes all the ticket flags where ticketEntryId = &#63; and type = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	*/
	public static void removeByTEI_T(long ticketEntryId, int type) {
		getPersistence().removeByTEI_T(ticketEntryId, type);
	}

	/**
	* Returns the number of ticket flags where ticketEntryId = &#63; and type = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the number of matching ticket flags
	*/
	public static int countByTEI_T(long ticketEntryId, int type) {
		return getPersistence().countByTEI_T(ticketEntryId, type);
	}

	/**
	* Returns all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @return the matching ticket flags
	*/
	public static List<TicketFlag> findByTEI_T_F(long ticketEntryId, int type,
		int flag) {
		return getPersistence().findByTEI_T_F(ticketEntryId, type, flag);
	}

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
	public static List<TicketFlag> findByTEI_T_F(long ticketEntryId, int type,
		int flag, int start, int end) {
		return getPersistence()
				   .findByTEI_T_F(ticketEntryId, type, flag, start, end);
	}

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
	public static List<TicketFlag> findByTEI_T_F(long ticketEntryId, int type,
		int flag, int start, int end,
		OrderByComparator<TicketFlag> orderByComparator) {
		return getPersistence()
				   .findByTEI_T_F(ticketEntryId, type, flag, start, end,
			orderByComparator);
	}

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
	public static List<TicketFlag> findByTEI_T_F(long ticketEntryId, int type,
		int flag, int start, int end,
		OrderByComparator<TicketFlag> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTEI_T_F(ticketEntryId, type, flag, start, end,
			orderByComparator, retrieveFromCache);
	}

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
	public static TicketFlag findByTEI_T_F_First(long ticketEntryId, int type,
		int flag, OrderByComparator<TicketFlag> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketFlagException {
		return getPersistence()
				   .findByTEI_T_F_First(ticketEntryId, type, flag,
			orderByComparator);
	}

	/**
	* Returns the first ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	*/
	public static TicketFlag fetchByTEI_T_F_First(long ticketEntryId, int type,
		int flag, OrderByComparator<TicketFlag> orderByComparator) {
		return getPersistence()
				   .fetchByTEI_T_F_First(ticketEntryId, type, flag,
			orderByComparator);
	}

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
	public static TicketFlag findByTEI_T_F_Last(long ticketEntryId, int type,
		int flag, OrderByComparator<TicketFlag> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketFlagException {
		return getPersistence()
				   .findByTEI_T_F_Last(ticketEntryId, type, flag,
			orderByComparator);
	}

	/**
	* Returns the last ticket flag in the ordered set where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	*/
	public static TicketFlag fetchByTEI_T_F_Last(long ticketEntryId, int type,
		int flag, OrderByComparator<TicketFlag> orderByComparator) {
		return getPersistence()
				   .fetchByTEI_T_F_Last(ticketEntryId, type, flag,
			orderByComparator);
	}

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
	public static TicketFlag[] findByTEI_T_F_PrevAndNext(long ticketFlagId,
		long ticketEntryId, int type, int flag,
		OrderByComparator<TicketFlag> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketFlagException {
		return getPersistence()
				   .findByTEI_T_F_PrevAndNext(ticketFlagId, ticketEntryId,
			type, flag, orderByComparator);
	}

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
	public static List<TicketFlag> findByTEI_T_F(long ticketEntryId,
		int[] types, int flag) {
		return getPersistence().findByTEI_T_F(ticketEntryId, types, flag);
	}

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
	public static List<TicketFlag> findByTEI_T_F(long ticketEntryId,
		int[] types, int flag, int start, int end) {
		return getPersistence()
				   .findByTEI_T_F(ticketEntryId, types, flag, start, end);
	}

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
	public static List<TicketFlag> findByTEI_T_F(long ticketEntryId,
		int[] types, int flag, int start, int end,
		OrderByComparator<TicketFlag> orderByComparator) {
		return getPersistence()
				   .findByTEI_T_F(ticketEntryId, types, flag, start, end,
			orderByComparator);
	}

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
	public static List<TicketFlag> findByTEI_T_F(long ticketEntryId,
		int[] types, int flag, int start, int end,
		OrderByComparator<TicketFlag> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTEI_T_F(ticketEntryId, types, flag, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	*/
	public static void removeByTEI_T_F(long ticketEntryId, int type, int flag) {
		getPersistence().removeByTEI_T_F(ticketEntryId, type, flag);
	}

	/**
	* Returns the number of ticket flags where ticketEntryId = &#63; and type = &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @param flag the flag
	* @return the number of matching ticket flags
	*/
	public static int countByTEI_T_F(long ticketEntryId, int type, int flag) {
		return getPersistence().countByTEI_T_F(ticketEntryId, type, flag);
	}

	/**
	* Returns the number of ticket flags where ticketEntryId = &#63; and type = any &#63; and flag = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param types the types
	* @param flag the flag
	* @return the number of matching ticket flags
	*/
	public static int countByTEI_T_F(long ticketEntryId, int[] types, int flag) {
		return getPersistence().countByTEI_T_F(ticketEntryId, types, flag);
	}

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
	public static TicketFlag findByU_AEI_TEI_T(long userId,
		long accountEntryId, long ticketEntryId, int type)
		throws com.liferay.osb.exception.NoSuchTicketFlagException {
		return getPersistence()
				   .findByU_AEI_TEI_T(userId, accountEntryId, ticketEntryId,
			type);
	}

	/**
	* Returns the ticket flag where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the matching ticket flag, or <code>null</code> if a matching ticket flag could not be found
	*/
	public static TicketFlag fetchByU_AEI_TEI_T(long userId,
		long accountEntryId, long ticketEntryId, int type) {
		return getPersistence()
				   .fetchByU_AEI_TEI_T(userId, accountEntryId, ticketEntryId,
			type);
	}

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
	public static TicketFlag fetchByU_AEI_TEI_T(long userId,
		long accountEntryId, long ticketEntryId, int type,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByU_AEI_TEI_T(userId, accountEntryId, ticketEntryId,
			type, retrieveFromCache);
	}

	/**
	* Removes the ticket flag where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the ticket flag that was removed
	*/
	public static TicketFlag removeByU_AEI_TEI_T(long userId,
		long accountEntryId, long ticketEntryId, int type)
		throws com.liferay.osb.exception.NoSuchTicketFlagException {
		return getPersistence()
				   .removeByU_AEI_TEI_T(userId, accountEntryId, ticketEntryId,
			type);
	}

	/**
	* Returns the number of ticket flags where userId = &#63; and accountEntryId = &#63; and ticketEntryId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param ticketEntryId the ticket entry ID
	* @param type the type
	* @return the number of matching ticket flags
	*/
	public static int countByU_AEI_TEI_T(long userId, long accountEntryId,
		long ticketEntryId, int type) {
		return getPersistence()
				   .countByU_AEI_TEI_T(userId, accountEntryId, ticketEntryId,
			type);
	}

	/**
	* Caches the ticket flag in the entity cache if it is enabled.
	*
	* @param ticketFlag the ticket flag
	*/
	public static void cacheResult(TicketFlag ticketFlag) {
		getPersistence().cacheResult(ticketFlag);
	}

	/**
	* Caches the ticket flags in the entity cache if it is enabled.
	*
	* @param ticketFlags the ticket flags
	*/
	public static void cacheResult(List<TicketFlag> ticketFlags) {
		getPersistence().cacheResult(ticketFlags);
	}

	/**
	* Creates a new ticket flag with the primary key. Does not add the ticket flag to the database.
	*
	* @param ticketFlagId the primary key for the new ticket flag
	* @return the new ticket flag
	*/
	public static TicketFlag create(long ticketFlagId) {
		return getPersistence().create(ticketFlagId);
	}

	/**
	* Removes the ticket flag with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketFlagId the primary key of the ticket flag
	* @return the ticket flag that was removed
	* @throws NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	*/
	public static TicketFlag remove(long ticketFlagId)
		throws com.liferay.osb.exception.NoSuchTicketFlagException {
		return getPersistence().remove(ticketFlagId);
	}

	public static TicketFlag updateImpl(TicketFlag ticketFlag) {
		return getPersistence().updateImpl(ticketFlag);
	}

	/**
	* Returns the ticket flag with the primary key or throws a {@link NoSuchTicketFlagException} if it could not be found.
	*
	* @param ticketFlagId the primary key of the ticket flag
	* @return the ticket flag
	* @throws NoSuchTicketFlagException if a ticket flag with the primary key could not be found
	*/
	public static TicketFlag findByPrimaryKey(long ticketFlagId)
		throws com.liferay.osb.exception.NoSuchTicketFlagException {
		return getPersistence().findByPrimaryKey(ticketFlagId);
	}

	/**
	* Returns the ticket flag with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketFlagId the primary key of the ticket flag
	* @return the ticket flag, or <code>null</code> if a ticket flag with the primary key could not be found
	*/
	public static TicketFlag fetchByPrimaryKey(long ticketFlagId) {
		return getPersistence().fetchByPrimaryKey(ticketFlagId);
	}

	public static java.util.Map<java.io.Serializable, TicketFlag> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ticket flags.
	*
	* @return the ticket flags
	*/
	public static List<TicketFlag> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<TicketFlag> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<TicketFlag> findAll(int start, int end,
		OrderByComparator<TicketFlag> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<TicketFlag> findAll(int start, int end,
		OrderByComparator<TicketFlag> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ticket flags from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ticket flags.
	*
	* @return the number of ticket flags
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static TicketFlagPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TicketFlagPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TicketFlagPersistence.class.getName());

			ReferenceRegistry.registerReference(TicketFlagUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static TicketFlagPersistence _persistence;
}