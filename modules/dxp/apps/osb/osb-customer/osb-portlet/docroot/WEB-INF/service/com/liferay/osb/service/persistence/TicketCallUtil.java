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

import com.liferay.osb.model.TicketCall;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the ticket call service. This utility wraps {@link com.liferay.osb.service.persistence.impl.TicketCallPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketCallPersistence
 * @see com.liferay.osb.service.persistence.impl.TicketCallPersistenceImpl
 * @generated
 */
@ProviderType
public class TicketCallUtil {
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
	public static void clearCache(TicketCall ticketCall) {
		getPersistence().clearCache(ticketCall);
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
	public static List<TicketCall> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TicketCall> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TicketCall> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TicketCall> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TicketCall update(TicketCall ticketCall) {
		return getPersistence().update(ticketCall);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TicketCall update(TicketCall ticketCall,
		ServiceContext serviceContext) {
		return getPersistence().update(ticketCall, serviceContext);
	}

	/**
	* Caches the ticket call in the entity cache if it is enabled.
	*
	* @param ticketCall the ticket call
	*/
	public static void cacheResult(TicketCall ticketCall) {
		getPersistence().cacheResult(ticketCall);
	}

	/**
	* Caches the ticket calls in the entity cache if it is enabled.
	*
	* @param ticketCalls the ticket calls
	*/
	public static void cacheResult(List<TicketCall> ticketCalls) {
		getPersistence().cacheResult(ticketCalls);
	}

	/**
	* Creates a new ticket call with the primary key. Does not add the ticket call to the database.
	*
	* @param ticketCallId the primary key for the new ticket call
	* @return the new ticket call
	*/
	public static TicketCall create(long ticketCallId) {
		return getPersistence().create(ticketCallId);
	}

	/**
	* Removes the ticket call with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketCallId the primary key of the ticket call
	* @return the ticket call that was removed
	* @throws NoSuchTicketCallException if a ticket call with the primary key could not be found
	*/
	public static TicketCall remove(long ticketCallId)
		throws com.liferay.osb.exception.NoSuchTicketCallException {
		return getPersistence().remove(ticketCallId);
	}

	public static TicketCall updateImpl(TicketCall ticketCall) {
		return getPersistence().updateImpl(ticketCall);
	}

	/**
	* Returns the ticket call with the primary key or throws a {@link NoSuchTicketCallException} if it could not be found.
	*
	* @param ticketCallId the primary key of the ticket call
	* @return the ticket call
	* @throws NoSuchTicketCallException if a ticket call with the primary key could not be found
	*/
	public static TicketCall findByPrimaryKey(long ticketCallId)
		throws com.liferay.osb.exception.NoSuchTicketCallException {
		return getPersistence().findByPrimaryKey(ticketCallId);
	}

	/**
	* Returns the ticket call with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketCallId the primary key of the ticket call
	* @return the ticket call, or <code>null</code> if a ticket call with the primary key could not be found
	*/
	public static TicketCall fetchByPrimaryKey(long ticketCallId) {
		return getPersistence().fetchByPrimaryKey(ticketCallId);
	}

	public static java.util.Map<java.io.Serializable, TicketCall> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ticket calls.
	*
	* @return the ticket calls
	*/
	public static List<TicketCall> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ticket calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket calls
	* @param end the upper bound of the range of ticket calls (not inclusive)
	* @return the range of ticket calls
	*/
	public static List<TicketCall> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ticket calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket calls
	* @param end the upper bound of the range of ticket calls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket calls
	*/
	public static List<TicketCall> findAll(int start, int end,
		OrderByComparator<TicketCall> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket calls
	* @param end the upper bound of the range of ticket calls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ticket calls
	*/
	public static List<TicketCall> findAll(int start, int end,
		OrderByComparator<TicketCall> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ticket calls from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ticket calls.
	*
	* @return the number of ticket calls
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static TicketCallPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TicketCallPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TicketCallPersistence.class.getName());

			ReferenceRegistry.registerReference(TicketCallUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static TicketCallPersistence _persistence;
}