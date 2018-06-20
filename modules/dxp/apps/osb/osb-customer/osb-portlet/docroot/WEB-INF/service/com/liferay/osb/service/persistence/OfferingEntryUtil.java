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

import com.liferay.osb.model.OfferingEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the offering entry service. This utility wraps {@link com.liferay.osb.service.persistence.impl.OfferingEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingEntryPersistence
 * @see com.liferay.osb.service.persistence.impl.OfferingEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class OfferingEntryUtil {
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
	public static void clearCache(OfferingEntry offeringEntry) {
		getPersistence().clearCache(offeringEntry);
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
	public static List<OfferingEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OfferingEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OfferingEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OfferingEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OfferingEntry update(OfferingEntry offeringEntry) {
		return getPersistence().update(offeringEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OfferingEntry update(OfferingEntry offeringEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(offeringEntry, serviceContext);
	}

	/**
	* Returns all the offering entries where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching offering entries
	*/
	public static List<OfferingEntry> findByAccountEntryId(long accountEntryId) {
		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	* Returns a range of all the offering entries where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	*/
	public static List<OfferingEntry> findByAccountEntryId(
		long accountEntryId, int start, int end) {
		return getPersistence().findByAccountEntryId(accountEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the offering entries where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering entries
	*/
	public static List<OfferingEntry> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<OfferingEntry> orderByComparator) {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the offering entries where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching offering entries
	*/
	public static List<OfferingEntry> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<OfferingEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first offering entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry
	* @throws NoSuchOfferingEntryException if a matching offering entry could not be found
	*/
	public static OfferingEntry findByAccountEntryId_First(
		long accountEntryId, OrderByComparator<OfferingEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingEntryException {
		return getPersistence()
				   .findByAccountEntryId_First(accountEntryId, orderByComparator);
	}

	/**
	* Returns the first offering entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	*/
	public static OfferingEntry fetchByAccountEntryId_First(
		long accountEntryId, OrderByComparator<OfferingEntry> orderByComparator) {
		return getPersistence()
				   .fetchByAccountEntryId_First(accountEntryId,
			orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry
	* @throws NoSuchOfferingEntryException if a matching offering entry could not be found
	*/
	public static OfferingEntry findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingEntryException {
		return getPersistence()
				   .findByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	*/
	public static OfferingEntry fetchByAccountEntryId_Last(
		long accountEntryId, OrderByComparator<OfferingEntry> orderByComparator) {
		return getPersistence()
				   .fetchByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the offering entries before and after the current offering entry in the ordered set where accountEntryId = &#63;.
	*
	* @param offeringEntryId the primary key of the current offering entry
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering entry
	* @throws NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	*/
	public static OfferingEntry[] findByAccountEntryId_PrevAndNext(
		long offeringEntryId, long accountEntryId,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingEntryException {
		return getPersistence()
				   .findByAccountEntryId_PrevAndNext(offeringEntryId,
			accountEntryId, orderByComparator);
	}

	/**
	* Removes all the offering entries where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	*/
	public static void removeByAccountEntryId(long accountEntryId) {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	* Returns the number of offering entries where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching offering entries
	*/
	public static int countByAccountEntryId(long accountEntryId) {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	* Returns all the offering entries where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @return the matching offering entries
	*/
	public static List<OfferingEntry> findByOrderEntryId(long orderEntryId) {
		return getPersistence().findByOrderEntryId(orderEntryId);
	}

	/**
	* Returns a range of all the offering entries where orderEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param orderEntryId the order entry ID
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	*/
	public static List<OfferingEntry> findByOrderEntryId(long orderEntryId,
		int start, int end) {
		return getPersistence().findByOrderEntryId(orderEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the offering entries where orderEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param orderEntryId the order entry ID
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering entries
	*/
	public static List<OfferingEntry> findByOrderEntryId(long orderEntryId,
		int start, int end, OrderByComparator<OfferingEntry> orderByComparator) {
		return getPersistence()
				   .findByOrderEntryId(orderEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the offering entries where orderEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param orderEntryId the order entry ID
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching offering entries
	*/
	public static List<OfferingEntry> findByOrderEntryId(long orderEntryId,
		int start, int end, OrderByComparator<OfferingEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByOrderEntryId(orderEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first offering entry in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry
	* @throws NoSuchOfferingEntryException if a matching offering entry could not be found
	*/
	public static OfferingEntry findByOrderEntryId_First(long orderEntryId,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingEntryException {
		return getPersistence()
				   .findByOrderEntryId_First(orderEntryId, orderByComparator);
	}

	/**
	* Returns the first offering entry in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	*/
	public static OfferingEntry fetchByOrderEntryId_First(long orderEntryId,
		OrderByComparator<OfferingEntry> orderByComparator) {
		return getPersistence()
				   .fetchByOrderEntryId_First(orderEntryId, orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry
	* @throws NoSuchOfferingEntryException if a matching offering entry could not be found
	*/
	public static OfferingEntry findByOrderEntryId_Last(long orderEntryId,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingEntryException {
		return getPersistence()
				   .findByOrderEntryId_Last(orderEntryId, orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	*/
	public static OfferingEntry fetchByOrderEntryId_Last(long orderEntryId,
		OrderByComparator<OfferingEntry> orderByComparator) {
		return getPersistence()
				   .fetchByOrderEntryId_Last(orderEntryId, orderByComparator);
	}

	/**
	* Returns the offering entries before and after the current offering entry in the ordered set where orderEntryId = &#63;.
	*
	* @param offeringEntryId the primary key of the current offering entry
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering entry
	* @throws NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	*/
	public static OfferingEntry[] findByOrderEntryId_PrevAndNext(
		long offeringEntryId, long orderEntryId,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingEntryException {
		return getPersistence()
				   .findByOrderEntryId_PrevAndNext(offeringEntryId,
			orderEntryId, orderByComparator);
	}

	/**
	* Removes all the offering entries where orderEntryId = &#63; from the database.
	*
	* @param orderEntryId the order entry ID
	*/
	public static void removeByOrderEntryId(long orderEntryId) {
		getPersistence().removeByOrderEntryId(orderEntryId);
	}

	/**
	* Returns the number of offering entries where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @return the number of matching offering entries
	*/
	public static int countByOrderEntryId(long orderEntryId) {
		return getPersistence().countByOrderEntryId(orderEntryId);
	}

	/**
	* Returns all the offering entries where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @return the matching offering entries
	*/
	public static List<OfferingEntry> findByAEI_T(long accountEntryId, int type) {
		return getPersistence().findByAEI_T(accountEntryId, type);
	}

	/**
	* Returns a range of all the offering entries where accountEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	*/
	public static List<OfferingEntry> findByAEI_T(long accountEntryId,
		int type, int start, int end) {
		return getPersistence().findByAEI_T(accountEntryId, type, start, end);
	}

	/**
	* Returns an ordered range of all the offering entries where accountEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering entries
	*/
	public static List<OfferingEntry> findByAEI_T(long accountEntryId,
		int type, int start, int end,
		OrderByComparator<OfferingEntry> orderByComparator) {
		return getPersistence()
				   .findByAEI_T(accountEntryId, type, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the offering entries where accountEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching offering entries
	*/
	public static List<OfferingEntry> findByAEI_T(long accountEntryId,
		int type, int start, int end,
		OrderByComparator<OfferingEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAEI_T(accountEntryId, type, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first offering entry in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry
	* @throws NoSuchOfferingEntryException if a matching offering entry could not be found
	*/
	public static OfferingEntry findByAEI_T_First(long accountEntryId,
		int type, OrderByComparator<OfferingEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingEntryException {
		return getPersistence()
				   .findByAEI_T_First(accountEntryId, type, orderByComparator);
	}

	/**
	* Returns the first offering entry in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	*/
	public static OfferingEntry fetchByAEI_T_First(long accountEntryId,
		int type, OrderByComparator<OfferingEntry> orderByComparator) {
		return getPersistence()
				   .fetchByAEI_T_First(accountEntryId, type, orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry
	* @throws NoSuchOfferingEntryException if a matching offering entry could not be found
	*/
	public static OfferingEntry findByAEI_T_Last(long accountEntryId, int type,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingEntryException {
		return getPersistence()
				   .findByAEI_T_Last(accountEntryId, type, orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	*/
	public static OfferingEntry fetchByAEI_T_Last(long accountEntryId,
		int type, OrderByComparator<OfferingEntry> orderByComparator) {
		return getPersistence()
				   .fetchByAEI_T_Last(accountEntryId, type, orderByComparator);
	}

	/**
	* Returns the offering entries before and after the current offering entry in the ordered set where accountEntryId = &#63; and type = &#63;.
	*
	* @param offeringEntryId the primary key of the current offering entry
	* @param accountEntryId the account entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering entry
	* @throws NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	*/
	public static OfferingEntry[] findByAEI_T_PrevAndNext(
		long offeringEntryId, long accountEntryId, int type,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingEntryException {
		return getPersistence()
				   .findByAEI_T_PrevAndNext(offeringEntryId, accountEntryId,
			type, orderByComparator);
	}

	/**
	* Removes all the offering entries where accountEntryId = &#63; and type = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	*/
	public static void removeByAEI_T(long accountEntryId, int type) {
		getPersistence().removeByAEI_T(accountEntryId, type);
	}

	/**
	* Returns the number of offering entries where accountEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param type the type
	* @return the number of matching offering entries
	*/
	public static int countByAEI_T(long accountEntryId, int type) {
		return getPersistence().countByAEI_T(accountEntryId, type);
	}

	/**
	* Returns all the offering entries where accountEntryId = &#63; and productEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param type the type
	* @return the matching offering entries
	*/
	public static List<OfferingEntry> findByAEI_PEI_T(long accountEntryId,
		long productEntryId, int type) {
		return getPersistence()
				   .findByAEI_PEI_T(accountEntryId, productEntryId, type);
	}

	/**
	* Returns a range of all the offering entries where accountEntryId = &#63; and productEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param type the type
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	*/
	public static List<OfferingEntry> findByAEI_PEI_T(long accountEntryId,
		long productEntryId, int type, int start, int end) {
		return getPersistence()
				   .findByAEI_PEI_T(accountEntryId, productEntryId, type,
			start, end);
	}

	/**
	* Returns an ordered range of all the offering entries where accountEntryId = &#63; and productEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param type the type
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering entries
	*/
	public static List<OfferingEntry> findByAEI_PEI_T(long accountEntryId,
		long productEntryId, int type, int start, int end,
		OrderByComparator<OfferingEntry> orderByComparator) {
		return getPersistence()
				   .findByAEI_PEI_T(accountEntryId, productEntryId, type,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the offering entries where accountEntryId = &#63; and productEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param type the type
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching offering entries
	*/
	public static List<OfferingEntry> findByAEI_PEI_T(long accountEntryId,
		long productEntryId, int type, int start, int end,
		OrderByComparator<OfferingEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAEI_PEI_T(accountEntryId, productEntryId, type,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first offering entry in the ordered set where accountEntryId = &#63; and productEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry
	* @throws NoSuchOfferingEntryException if a matching offering entry could not be found
	*/
	public static OfferingEntry findByAEI_PEI_T_First(long accountEntryId,
		long productEntryId, int type,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingEntryException {
		return getPersistence()
				   .findByAEI_PEI_T_First(accountEntryId, productEntryId, type,
			orderByComparator);
	}

	/**
	* Returns the first offering entry in the ordered set where accountEntryId = &#63; and productEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	*/
	public static OfferingEntry fetchByAEI_PEI_T_First(long accountEntryId,
		long productEntryId, int type,
		OrderByComparator<OfferingEntry> orderByComparator) {
		return getPersistence()
				   .fetchByAEI_PEI_T_First(accountEntryId, productEntryId,
			type, orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where accountEntryId = &#63; and productEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry
	* @throws NoSuchOfferingEntryException if a matching offering entry could not be found
	*/
	public static OfferingEntry findByAEI_PEI_T_Last(long accountEntryId,
		long productEntryId, int type,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingEntryException {
		return getPersistence()
				   .findByAEI_PEI_T_Last(accountEntryId, productEntryId, type,
			orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where accountEntryId = &#63; and productEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	*/
	public static OfferingEntry fetchByAEI_PEI_T_Last(long accountEntryId,
		long productEntryId, int type,
		OrderByComparator<OfferingEntry> orderByComparator) {
		return getPersistence()
				   .fetchByAEI_PEI_T_Last(accountEntryId, productEntryId, type,
			orderByComparator);
	}

	/**
	* Returns the offering entries before and after the current offering entry in the ordered set where accountEntryId = &#63; and productEntryId = &#63; and type = &#63;.
	*
	* @param offeringEntryId the primary key of the current offering entry
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering entry
	* @throws NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	*/
	public static OfferingEntry[] findByAEI_PEI_T_PrevAndNext(
		long offeringEntryId, long accountEntryId, long productEntryId,
		int type, OrderByComparator<OfferingEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingEntryException {
		return getPersistence()
				   .findByAEI_PEI_T_PrevAndNext(offeringEntryId,
			accountEntryId, productEntryId, type, orderByComparator);
	}

	/**
	* Removes all the offering entries where accountEntryId = &#63; and productEntryId = &#63; and type = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param type the type
	*/
	public static void removeByAEI_PEI_T(long accountEntryId,
		long productEntryId, int type) {
		getPersistence().removeByAEI_PEI_T(accountEntryId, productEntryId, type);
	}

	/**
	* Returns the number of offering entries where accountEntryId = &#63; and productEntryId = &#63; and type = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param type the type
	* @return the number of matching offering entries
	*/
	public static int countByAEI_PEI_T(long accountEntryId,
		long productEntryId, int type) {
		return getPersistence()
				   .countByAEI_PEI_T(accountEntryId, productEntryId, type);
	}

	/**
	* Returns all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @return the matching offering entries
	*/
	public static List<OfferingEntry> findByU_AEI_OEI_T(long userId,
		long accountEntryId, long orderEntryId, int type) {
		return getPersistence()
				   .findByU_AEI_OEI_T(userId, accountEntryId, orderEntryId, type);
	}

	/**
	* Returns a range of all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	*/
	public static List<OfferingEntry> findByU_AEI_OEI_T(long userId,
		long accountEntryId, long orderEntryId, int type, int start, int end) {
		return getPersistence()
				   .findByU_AEI_OEI_T(userId, accountEntryId, orderEntryId,
			type, start, end);
	}

	/**
	* Returns an ordered range of all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering entries
	*/
	public static List<OfferingEntry> findByU_AEI_OEI_T(long userId,
		long accountEntryId, long orderEntryId, int type, int start, int end,
		OrderByComparator<OfferingEntry> orderByComparator) {
		return getPersistence()
				   .findByU_AEI_OEI_T(userId, accountEntryId, orderEntryId,
			type, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching offering entries
	*/
	public static List<OfferingEntry> findByU_AEI_OEI_T(long userId,
		long accountEntryId, long orderEntryId, int type, int start, int end,
		OrderByComparator<OfferingEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_AEI_OEI_T(userId, accountEntryId, orderEntryId,
			type, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry
	* @throws NoSuchOfferingEntryException if a matching offering entry could not be found
	*/
	public static OfferingEntry findByU_AEI_OEI_T_First(long userId,
		long accountEntryId, long orderEntryId, int type,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingEntryException {
		return getPersistence()
				   .findByU_AEI_OEI_T_First(userId, accountEntryId,
			orderEntryId, type, orderByComparator);
	}

	/**
	* Returns the first offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	*/
	public static OfferingEntry fetchByU_AEI_OEI_T_First(long userId,
		long accountEntryId, long orderEntryId, int type,
		OrderByComparator<OfferingEntry> orderByComparator) {
		return getPersistence()
				   .fetchByU_AEI_OEI_T_First(userId, accountEntryId,
			orderEntryId, type, orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry
	* @throws NoSuchOfferingEntryException if a matching offering entry could not be found
	*/
	public static OfferingEntry findByU_AEI_OEI_T_Last(long userId,
		long accountEntryId, long orderEntryId, int type,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingEntryException {
		return getPersistence()
				   .findByU_AEI_OEI_T_Last(userId, accountEntryId,
			orderEntryId, type, orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	*/
	public static OfferingEntry fetchByU_AEI_OEI_T_Last(long userId,
		long accountEntryId, long orderEntryId, int type,
		OrderByComparator<OfferingEntry> orderByComparator) {
		return getPersistence()
				   .fetchByU_AEI_OEI_T_Last(userId, accountEntryId,
			orderEntryId, type, orderByComparator);
	}

	/**
	* Returns the offering entries before and after the current offering entry in the ordered set where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* @param offeringEntryId the primary key of the current offering entry
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering entry
	* @throws NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	*/
	public static OfferingEntry[] findByU_AEI_OEI_T_PrevAndNext(
		long offeringEntryId, long userId, long accountEntryId,
		long orderEntryId, int type,
		OrderByComparator<OfferingEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingEntryException {
		return getPersistence()
				   .findByU_AEI_OEI_T_PrevAndNext(offeringEntryId, userId,
			accountEntryId, orderEntryId, type, orderByComparator);
	}

	/**
	* Removes all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	*/
	public static void removeByU_AEI_OEI_T(long userId, long accountEntryId,
		long orderEntryId, int type) {
		getPersistence()
			.removeByU_AEI_OEI_T(userId, accountEntryId, orderEntryId, type);
	}

	/**
	* Returns the number of offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @return the number of matching offering entries
	*/
	public static int countByU_AEI_OEI_T(long userId, long accountEntryId,
		long orderEntryId, int type) {
		return getPersistence()
				   .countByU_AEI_OEI_T(userId, accountEntryId, orderEntryId,
			type);
	}

	/**
	* Caches the offering entry in the entity cache if it is enabled.
	*
	* @param offeringEntry the offering entry
	*/
	public static void cacheResult(OfferingEntry offeringEntry) {
		getPersistence().cacheResult(offeringEntry);
	}

	/**
	* Caches the offering entries in the entity cache if it is enabled.
	*
	* @param offeringEntries the offering entries
	*/
	public static void cacheResult(List<OfferingEntry> offeringEntries) {
		getPersistence().cacheResult(offeringEntries);
	}

	/**
	* Creates a new offering entry with the primary key. Does not add the offering entry to the database.
	*
	* @param offeringEntryId the primary key for the new offering entry
	* @return the new offering entry
	*/
	public static OfferingEntry create(long offeringEntryId) {
		return getPersistence().create(offeringEntryId);
	}

	/**
	* Removes the offering entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry that was removed
	* @throws NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	*/
	public static OfferingEntry remove(long offeringEntryId)
		throws com.liferay.osb.exception.NoSuchOfferingEntryException {
		return getPersistence().remove(offeringEntryId);
	}

	public static OfferingEntry updateImpl(OfferingEntry offeringEntry) {
		return getPersistence().updateImpl(offeringEntry);
	}

	/**
	* Returns the offering entry with the primary key or throws a {@link NoSuchOfferingEntryException} if it could not be found.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry
	* @throws NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	*/
	public static OfferingEntry findByPrimaryKey(long offeringEntryId)
		throws com.liferay.osb.exception.NoSuchOfferingEntryException {
		return getPersistence().findByPrimaryKey(offeringEntryId);
	}

	/**
	* Returns the offering entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry, or <code>null</code> if a offering entry with the primary key could not be found
	*/
	public static OfferingEntry fetchByPrimaryKey(long offeringEntryId) {
		return getPersistence().fetchByPrimaryKey(offeringEntryId);
	}

	public static java.util.Map<java.io.Serializable, OfferingEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the offering entries.
	*
	* @return the offering entries
	*/
	public static List<OfferingEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the offering entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of offering entries
	*/
	public static List<OfferingEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the offering entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of offering entries
	*/
	public static List<OfferingEntry> findAll(int start, int end,
		OrderByComparator<OfferingEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the offering entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of offering entries
	*/
	public static List<OfferingEntry> findAll(int start, int end,
		OrderByComparator<OfferingEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the offering entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of offering entries.
	*
	* @return the number of offering entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static OfferingEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (OfferingEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					OfferingEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(OfferingEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static OfferingEntryPersistence _persistence;
}