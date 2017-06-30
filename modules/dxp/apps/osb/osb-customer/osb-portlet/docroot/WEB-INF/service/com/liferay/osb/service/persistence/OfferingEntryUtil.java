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

import com.liferay.osb.model.OfferingEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the offering entry service. This utility wraps {@link OfferingEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingEntryPersistence
 * @see OfferingEntryPersistenceImpl
 * @generated
 */
public class OfferingEntryUtil {
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
	public static void clearCache(OfferingEntry offeringEntry) {
		getPersistence().clearCache(offeringEntry);
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
	public static List<OfferingEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OfferingEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OfferingEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static OfferingEntry update(OfferingEntry offeringEntry,
		boolean merge) throws SystemException {
		return getPersistence().update(offeringEntry, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static OfferingEntry update(OfferingEntry offeringEntry,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(offeringEntry, merge, serviceContext);
	}

	/**
	* Caches the offering entry in the entity cache if it is enabled.
	*
	* @param offeringEntry the offering entry
	*/
	public static void cacheResult(
		com.liferay.osb.model.OfferingEntry offeringEntry) {
		getPersistence().cacheResult(offeringEntry);
	}

	/**
	* Caches the offering entries in the entity cache if it is enabled.
	*
	* @param offeringEntries the offering entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.OfferingEntry> offeringEntries) {
		getPersistence().cacheResult(offeringEntries);
	}

	/**
	* Creates a new offering entry with the primary key. Does not add the offering entry to the database.
	*
	* @param offeringEntryId the primary key for the new offering entry
	* @return the new offering entry
	*/
	public static com.liferay.osb.model.OfferingEntry create(
		long offeringEntryId) {
		return getPersistence().create(offeringEntryId);
	}

	/**
	* Removes the offering entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry that was removed
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry remove(
		long offeringEntryId)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(offeringEntryId);
	}

	public static com.liferay.osb.model.OfferingEntry updateImpl(
		com.liferay.osb.model.OfferingEntry offeringEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(offeringEntry, merge);
	}

	/**
	* Returns the offering entry with the primary key or throws a {@link com.liferay.osb.NoSuchOfferingEntryException} if it could not be found.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry findByPrimaryKey(
		long offeringEntryId)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(offeringEntryId);
	}

	/**
	* Returns the offering entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param offeringEntryId the primary key of the offering entry
	* @return the offering entry, or <code>null</code> if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry fetchByPrimaryKey(
		long offeringEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(offeringEntryId);
	}

	/**
	* Returns all the offering entries where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByAccountEntryId(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	* Returns a range of all the offering entries where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByAccountEntryId(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccountEntryId(accountEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the offering entries where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first offering entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry findByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_First(accountEntryId, orderByComparator);
	}

	/**
	* Returns the first offering entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry findByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry[] findByAccountEntryId_PrevAndNext(
		long offeringEntryId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_PrevAndNext(offeringEntryId,
			accountEntryId, orderByComparator);
	}

	/**
	* Returns all the offering entries where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @return the matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByOrderEntryId(
		long orderEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOrderEntryId(orderEntryId);
	}

	/**
	* Returns a range of all the offering entries where orderEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param orderEntryId the order entry ID
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByOrderEntryId(
		long orderEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOrderEntryId(orderEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the offering entries where orderEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param orderEntryId the order entry ID
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByOrderEntryId(
		long orderEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOrderEntryId(orderEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first offering entry in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry findByOrderEntryId_First(
		long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOrderEntryId_First(orderEntryId, orderByComparator);
	}

	/**
	* Returns the first offering entry in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry fetchByOrderEntryId_First(
		long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOrderEntryId_First(orderEntryId, orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry findByOrderEntryId_Last(
		long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOrderEntryId_Last(orderEntryId, orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry fetchByOrderEntryId_Last(
		long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry[] findByOrderEntryId_PrevAndNext(
		long offeringEntryId, long orderEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByOrderEntryId_PrevAndNext(offeringEntryId,
			orderEntryId, orderByComparator);
	}

	/**
	* Returns all the offering entries where version = &#63;.
	*
	* @param version the version
	* @return the matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByVersion(
		int version) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByVersion(version);
	}

	/**
	* Returns a range of all the offering entries where version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param version the version
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByVersion(
		int version, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByVersion(version, start, end);
	}

	/**
	* Returns an ordered range of all the offering entries where version = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param version the version
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByVersion(
		int version, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByVersion(version, start, end, orderByComparator);
	}

	/**
	* Returns the first offering entry in the ordered set where version = &#63;.
	*
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry findByVersion_First(
		int version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByVersion_First(version, orderByComparator);
	}

	/**
	* Returns the first offering entry in the ordered set where version = &#63;.
	*
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry fetchByVersion_First(
		int version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByVersion_First(version, orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where version = &#63;.
	*
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry findByVersion_Last(
		int version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByVersion_Last(version, orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where version = &#63;.
	*
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry fetchByVersion_Last(
		int version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByVersion_Last(version, orderByComparator);
	}

	/**
	* Returns the offering entries before and after the current offering entry in the ordered set where version = &#63;.
	*
	* @param offeringEntryId the primary key of the current offering entry
	* @param version the version
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry[] findByVersion_PrevAndNext(
		long offeringEntryId, int version,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByVersion_PrevAndNext(offeringEntryId, version,
			orderByComparator);
	}

	/**
	* Returns all the offering entries where supportEndDate = &#63;.
	*
	* @param supportEndDate the support end date
	* @return the matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findBySupportEndDate(
		java.util.Date supportEndDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySupportEndDate(supportEndDate);
	}

	/**
	* Returns a range of all the offering entries where supportEndDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportEndDate the support end date
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findBySupportEndDate(
		java.util.Date supportEndDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySupportEndDate(supportEndDate, start, end);
	}

	/**
	* Returns an ordered range of all the offering entries where supportEndDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportEndDate the support end date
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findBySupportEndDate(
		java.util.Date supportEndDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportEndDate(supportEndDate, start, end,
			orderByComparator);
	}

	/**
	* Returns the first offering entry in the ordered set where supportEndDate = &#63;.
	*
	* @param supportEndDate the support end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry findBySupportEndDate_First(
		java.util.Date supportEndDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportEndDate_First(supportEndDate, orderByComparator);
	}

	/**
	* Returns the first offering entry in the ordered set where supportEndDate = &#63;.
	*
	* @param supportEndDate the support end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry fetchBySupportEndDate_First(
		java.util.Date supportEndDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySupportEndDate_First(supportEndDate,
			orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where supportEndDate = &#63;.
	*
	* @param supportEndDate the support end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry findBySupportEndDate_Last(
		java.util.Date supportEndDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportEndDate_Last(supportEndDate, orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where supportEndDate = &#63;.
	*
	* @param supportEndDate the support end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry fetchBySupportEndDate_Last(
		java.util.Date supportEndDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySupportEndDate_Last(supportEndDate, orderByComparator);
	}

	/**
	* Returns the offering entries before and after the current offering entry in the ordered set where supportEndDate = &#63;.
	*
	* @param offeringEntryId the primary key of the current offering entry
	* @param supportEndDate the support end date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry[] findBySupportEndDate_PrevAndNext(
		long offeringEntryId, java.util.Date supportEndDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportEndDate_PrevAndNext(offeringEntryId,
			supportEndDate, orderByComparator);
	}

	/**
	* Returns all the offering entries where status = &#63;.
	*
	* @param status the status
	* @return the matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByStatus(
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus(status);
	}

	/**
	* Returns a range of all the offering entries where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByStatus(
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus(status, start, end);
	}

	/**
	* Returns an ordered range of all the offering entries where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByStatus(
		int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByStatus(status, start, end, orderByComparator);
	}

	/**
	* Returns the first offering entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry findByStatus_First(
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the first offering entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry fetchByStatus_First(
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByStatus_First(status, orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry findByStatus_Last(
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByStatus_Last(status, orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry fetchByStatus_Last(
		int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByStatus_Last(status, orderByComparator);
	}

	/**
	* Returns the offering entries before and after the current offering entry in the ordered set where status = &#63;.
	*
	* @param offeringEntryId the primary key of the current offering entry
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry[] findByStatus_PrevAndNext(
		long offeringEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByStatus_PrevAndNext(offeringEntryId, status,
			orderByComparator);
	}

	/**
	* Returns all the offering entries where accountEntryId = &#63; and status = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param status the status
	* @return the matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByAEI_S(
		long accountEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAEI_S(accountEntryId, status);
	}

	/**
	* Returns a range of all the offering entries where accountEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param status the status
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByAEI_S(
		long accountEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAEI_S(accountEntryId, status, start, end);
	}

	/**
	* Returns an ordered range of all the offering entries where accountEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param status the status
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByAEI_S(
		long accountEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_S(accountEntryId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the first offering entry in the ordered set where accountEntryId = &#63; and status = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry findByAEI_S_First(
		long accountEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_S_First(accountEntryId, status, orderByComparator);
	}

	/**
	* Returns the first offering entry in the ordered set where accountEntryId = &#63; and status = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry fetchByAEI_S_First(
		long accountEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAEI_S_First(accountEntryId, status, orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where accountEntryId = &#63; and status = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry findByAEI_S_Last(
		long accountEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_S_Last(accountEntryId, status, orderByComparator);
	}

	/**
	* Returns the last offering entry in the ordered set where accountEntryId = &#63; and status = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering entry, or <code>null</code> if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry fetchByAEI_S_Last(
		long accountEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAEI_S_Last(accountEntryId, status, orderByComparator);
	}

	/**
	* Returns the offering entries before and after the current offering entry in the ordered set where accountEntryId = &#63; and status = &#63;.
	*
	* @param offeringEntryId the primary key of the current offering entry
	* @param accountEntryId the account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering entry
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry[] findByAEI_S_PrevAndNext(
		long offeringEntryId, long accountEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_S_PrevAndNext(offeringEntryId, accountEntryId,
			status, orderByComparator);
	}

	/**
	* Returns all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @return the matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByU_AEI_OEI_T(
		long userId, long accountEntryId, long orderEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_AEI_OEI_T(userId, accountEntryId, orderEntryId, type);
	}

	/**
	* Returns a range of all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByU_AEI_OEI_T(
		long userId, long accountEntryId, long orderEntryId, int type,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_AEI_OEI_T(userId, accountEntryId, orderEntryId,
			type, start, end);
	}

	/**
	* Returns an ordered range of all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findByU_AEI_OEI_T(
		long userId, long accountEntryId, long orderEntryId, int type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_AEI_OEI_T(userId, accountEntryId, orderEntryId,
			type, start, end, orderByComparator);
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
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry findByU_AEI_OEI_T_First(
		long userId, long accountEntryId, long orderEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry fetchByU_AEI_OEI_T_First(
		long userId, long accountEntryId, long orderEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a matching offering entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry findByU_AEI_OEI_T_Last(
		long userId, long accountEntryId, long orderEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
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
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry fetchByU_AEI_OEI_T_Last(
		long userId, long accountEntryId, long orderEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchOfferingEntryException if a offering entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingEntry[] findByU_AEI_OEI_T_PrevAndNext(
		long offeringEntryId, long userId, long accountEntryId,
		long orderEntryId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_AEI_OEI_T_PrevAndNext(offeringEntryId, userId,
			accountEntryId, orderEntryId, type, orderByComparator);
	}

	/**
	* Returns all the offering entries.
	*
	* @return the offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the offering entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @return the range of offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the offering entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of offering entries
	* @param end the upper bound of the range of offering entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the offering entries where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	* Removes all the offering entries where orderEntryId = &#63; from the database.
	*
	* @param orderEntryId the order entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByOrderEntryId(long orderEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByOrderEntryId(orderEntryId);
	}

	/**
	* Removes all the offering entries where version = &#63; from the database.
	*
	* @param version the version
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByVersion(int version)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByVersion(version);
	}

	/**
	* Removes all the offering entries where supportEndDate = &#63; from the database.
	*
	* @param supportEndDate the support end date
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySupportEndDate(java.util.Date supportEndDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySupportEndDate(supportEndDate);
	}

	/**
	* Removes all the offering entries where status = &#63; from the database.
	*
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByStatus(status);
	}

	/**
	* Removes all the offering entries where accountEntryId = &#63; and status = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAEI_S(long accountEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAEI_S(accountEntryId, status);
	}

	/**
	* Removes all the offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_AEI_OEI_T(long userId, long accountEntryId,
		long orderEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByU_AEI_OEI_T(userId, accountEntryId, orderEntryId, type);
	}

	/**
	* Removes all the offering entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of offering entries where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	* Returns the number of offering entries where orderEntryId = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @return the number of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOrderEntryId(long orderEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByOrderEntryId(orderEntryId);
	}

	/**
	* Returns the number of offering entries where version = &#63;.
	*
	* @param version the version
	* @return the number of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByVersion(int version)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByVersion(version);
	}

	/**
	* Returns the number of offering entries where supportEndDate = &#63;.
	*
	* @param supportEndDate the support end date
	* @return the number of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySupportEndDate(java.util.Date supportEndDate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySupportEndDate(supportEndDate);
	}

	/**
	* Returns the number of offering entries where status = &#63;.
	*
	* @param status the status
	* @return the number of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByStatus(int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByStatus(status);
	}

	/**
	* Returns the number of offering entries where accountEntryId = &#63; and status = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param status the status
	* @return the number of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAEI_S(long accountEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAEI_S(accountEntryId, status);
	}

	/**
	* Returns the number of offering entries where userId = &#63; and accountEntryId = &#63; and orderEntryId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderEntryId the order entry ID
	* @param type the type
	* @return the number of matching offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_AEI_OEI_T(long userId, long accountEntryId,
		long orderEntryId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByU_AEI_OEI_T(userId, accountEntryId, orderEntryId,
			type);
	}

	/**
	* Returns the number of offering entries.
	*
	* @return the number of offering entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
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

	/**
	 * @deprecated
	 */
	public void setPersistence(OfferingEntryPersistence persistence) {
	}

	private static OfferingEntryPersistence _persistence;
}