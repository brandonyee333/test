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

import com.liferay.osb.model.TicketInformation;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the ticket information service. This utility wraps {@link com.liferay.osb.service.persistence.impl.TicketInformationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TicketInformationPersistence
 * @see com.liferay.osb.service.persistence.impl.TicketInformationPersistenceImpl
 * @generated
 */
@ProviderType
public class TicketInformationUtil {
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
	public static void clearCache(TicketInformation ticketInformation) {
		getPersistence().clearCache(ticketInformation);
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
	public static List<TicketInformation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TicketInformation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TicketInformation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<TicketInformation> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static TicketInformation update(TicketInformation ticketInformation) {
		return getPersistence().update(ticketInformation);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static TicketInformation update(
		TicketInformation ticketInformation, ServiceContext serviceContext) {
		return getPersistence().update(ticketInformation, serviceContext);
	}

	/**
	* Returns all the ticket informations where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the matching ticket informations
	*/
	public static List<TicketInformation> findByTicketEntryId(
		long ticketEntryId) {
		return getPersistence().findByTicketEntryId(ticketEntryId);
	}

	/**
	* Returns a range of all the ticket informations where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket informations
	* @param end the upper bound of the range of ticket informations (not inclusive)
	* @return the range of matching ticket informations
	*/
	public static List<TicketInformation> findByTicketEntryId(
		long ticketEntryId, int start, int end) {
		return getPersistence().findByTicketEntryId(ticketEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the ticket informations where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket informations
	* @param end the upper bound of the range of ticket informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching ticket informations
	*/
	public static List<TicketInformation> findByTicketEntryId(
		long ticketEntryId, int start, int end,
		OrderByComparator<TicketInformation> orderByComparator) {
		return getPersistence()
				   .findByTicketEntryId(ticketEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket informations where ticketEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param ticketEntryId the ticket entry ID
	* @param start the lower bound of the range of ticket informations
	* @param end the upper bound of the range of ticket informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching ticket informations
	*/
	public static List<TicketInformation> findByTicketEntryId(
		long ticketEntryId, int start, int end,
		OrderByComparator<TicketInformation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByTicketEntryId(ticketEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first ticket information in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket information
	* @throws NoSuchTicketInformationException if a matching ticket information could not be found
	*/
	public static TicketInformation findByTicketEntryId_First(
		long ticketEntryId,
		OrderByComparator<TicketInformation> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketInformationException {
		return getPersistence()
				   .findByTicketEntryId_First(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the first ticket information in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching ticket information, or <code>null</code> if a matching ticket information could not be found
	*/
	public static TicketInformation fetchByTicketEntryId_First(
		long ticketEntryId,
		OrderByComparator<TicketInformation> orderByComparator) {
		return getPersistence()
				   .fetchByTicketEntryId_First(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the last ticket information in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket information
	* @throws NoSuchTicketInformationException if a matching ticket information could not be found
	*/
	public static TicketInformation findByTicketEntryId_Last(
		long ticketEntryId,
		OrderByComparator<TicketInformation> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketInformationException {
		return getPersistence()
				   .findByTicketEntryId_Last(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the last ticket information in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching ticket information, or <code>null</code> if a matching ticket information could not be found
	*/
	public static TicketInformation fetchByTicketEntryId_Last(
		long ticketEntryId,
		OrderByComparator<TicketInformation> orderByComparator) {
		return getPersistence()
				   .fetchByTicketEntryId_Last(ticketEntryId, orderByComparator);
	}

	/**
	* Returns the ticket informations before and after the current ticket information in the ordered set where ticketEntryId = &#63;.
	*
	* @param ticketInformationId the primary key of the current ticket information
	* @param ticketEntryId the ticket entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next ticket information
	* @throws NoSuchTicketInformationException if a ticket information with the primary key could not be found
	*/
	public static TicketInformation[] findByTicketEntryId_PrevAndNext(
		long ticketInformationId, long ticketEntryId,
		OrderByComparator<TicketInformation> orderByComparator)
		throws com.liferay.osb.exception.NoSuchTicketInformationException {
		return getPersistence()
				   .findByTicketEntryId_PrevAndNext(ticketInformationId,
			ticketEntryId, orderByComparator);
	}

	/**
	* Removes all the ticket informations where ticketEntryId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	*/
	public static void removeByTicketEntryId(long ticketEntryId) {
		getPersistence().removeByTicketEntryId(ticketEntryId);
	}

	/**
	* Returns the number of ticket informations where ticketEntryId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @return the number of matching ticket informations
	*/
	public static int countByTicketEntryId(long ticketEntryId) {
		return getPersistence().countByTicketEntryId(ticketEntryId);
	}

	/**
	* Returns the ticket information where ticketEntryId = &#63; and fieldId = &#63; or throws a {@link NoSuchTicketInformationException} if it could not be found.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fieldId the field ID
	* @return the matching ticket information
	* @throws NoSuchTicketInformationException if a matching ticket information could not be found
	*/
	public static TicketInformation findByTEI_FI(long ticketEntryId,
		long fieldId)
		throws com.liferay.osb.exception.NoSuchTicketInformationException {
		return getPersistence().findByTEI_FI(ticketEntryId, fieldId);
	}

	/**
	* Returns the ticket information where ticketEntryId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fieldId the field ID
	* @return the matching ticket information, or <code>null</code> if a matching ticket information could not be found
	*/
	public static TicketInformation fetchByTEI_FI(long ticketEntryId,
		long fieldId) {
		return getPersistence().fetchByTEI_FI(ticketEntryId, fieldId);
	}

	/**
	* Returns the ticket information where ticketEntryId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fieldId the field ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching ticket information, or <code>null</code> if a matching ticket information could not be found
	*/
	public static TicketInformation fetchByTEI_FI(long ticketEntryId,
		long fieldId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByTEI_FI(ticketEntryId, fieldId, retrieveFromCache);
	}

	/**
	* Removes the ticket information where ticketEntryId = &#63; and fieldId = &#63; from the database.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fieldId the field ID
	* @return the ticket information that was removed
	*/
	public static TicketInformation removeByTEI_FI(long ticketEntryId,
		long fieldId)
		throws com.liferay.osb.exception.NoSuchTicketInformationException {
		return getPersistence().removeByTEI_FI(ticketEntryId, fieldId);
	}

	/**
	* Returns the number of ticket informations where ticketEntryId = &#63; and fieldId = &#63;.
	*
	* @param ticketEntryId the ticket entry ID
	* @param fieldId the field ID
	* @return the number of matching ticket informations
	*/
	public static int countByTEI_FI(long ticketEntryId, long fieldId) {
		return getPersistence().countByTEI_FI(ticketEntryId, fieldId);
	}

	/**
	* Caches the ticket information in the entity cache if it is enabled.
	*
	* @param ticketInformation the ticket information
	*/
	public static void cacheResult(TicketInformation ticketInformation) {
		getPersistence().cacheResult(ticketInformation);
	}

	/**
	* Caches the ticket informations in the entity cache if it is enabled.
	*
	* @param ticketInformations the ticket informations
	*/
	public static void cacheResult(List<TicketInformation> ticketInformations) {
		getPersistence().cacheResult(ticketInformations);
	}

	/**
	* Creates a new ticket information with the primary key. Does not add the ticket information to the database.
	*
	* @param ticketInformationId the primary key for the new ticket information
	* @return the new ticket information
	*/
	public static TicketInformation create(long ticketInformationId) {
		return getPersistence().create(ticketInformationId);
	}

	/**
	* Removes the ticket information with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param ticketInformationId the primary key of the ticket information
	* @return the ticket information that was removed
	* @throws NoSuchTicketInformationException if a ticket information with the primary key could not be found
	*/
	public static TicketInformation remove(long ticketInformationId)
		throws com.liferay.osb.exception.NoSuchTicketInformationException {
		return getPersistence().remove(ticketInformationId);
	}

	public static TicketInformation updateImpl(
		TicketInformation ticketInformation) {
		return getPersistence().updateImpl(ticketInformation);
	}

	/**
	* Returns the ticket information with the primary key or throws a {@link NoSuchTicketInformationException} if it could not be found.
	*
	* @param ticketInformationId the primary key of the ticket information
	* @return the ticket information
	* @throws NoSuchTicketInformationException if a ticket information with the primary key could not be found
	*/
	public static TicketInformation findByPrimaryKey(long ticketInformationId)
		throws com.liferay.osb.exception.NoSuchTicketInformationException {
		return getPersistence().findByPrimaryKey(ticketInformationId);
	}

	/**
	* Returns the ticket information with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param ticketInformationId the primary key of the ticket information
	* @return the ticket information, or <code>null</code> if a ticket information with the primary key could not be found
	*/
	public static TicketInformation fetchByPrimaryKey(long ticketInformationId) {
		return getPersistence().fetchByPrimaryKey(ticketInformationId);
	}

	public static java.util.Map<java.io.Serializable, TicketInformation> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the ticket informations.
	*
	* @return the ticket informations
	*/
	public static List<TicketInformation> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the ticket informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket informations
	* @param end the upper bound of the range of ticket informations (not inclusive)
	* @return the range of ticket informations
	*/
	public static List<TicketInformation> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the ticket informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket informations
	* @param end the upper bound of the range of ticket informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of ticket informations
	*/
	public static List<TicketInformation> findAll(int start, int end,
		OrderByComparator<TicketInformation> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the ticket informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link TicketInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of ticket informations
	* @param end the upper bound of the range of ticket informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of ticket informations
	*/
	public static List<TicketInformation> findAll(int start, int end,
		OrderByComparator<TicketInformation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the ticket informations from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of ticket informations.
	*
	* @return the number of ticket informations
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static TicketInformationPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TicketInformationPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TicketInformationPersistence.class.getName());

			ReferenceRegistry.registerReference(TicketInformationUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static TicketInformationPersistence _persistence;
}