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

import com.liferay.osb.model.PartnerEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the partner entry service. This utility wraps {@link PartnerEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PartnerEntryPersistence
 * @see PartnerEntryPersistenceImpl
 * @generated
 */
public class PartnerEntryUtil {
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
	public static void clearCache(PartnerEntry partnerEntry) {
		getPersistence().clearCache(partnerEntry);
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
	public static List<PartnerEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PartnerEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PartnerEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static PartnerEntry update(PartnerEntry partnerEntry, boolean merge)
		throws SystemException {
		return getPersistence().update(partnerEntry, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static PartnerEntry update(PartnerEntry partnerEntry, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(partnerEntry, merge, serviceContext);
	}

	/**
	* Caches the partner entry in the entity cache if it is enabled.
	*
	* @param partnerEntry the partner entry
	*/
	public static void cacheResult(
		com.liferay.osb.model.PartnerEntry partnerEntry) {
		getPersistence().cacheResult(partnerEntry);
	}

	/**
	* Caches the partner entries in the entity cache if it is enabled.
	*
	* @param partnerEntries the partner entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.PartnerEntry> partnerEntries) {
		getPersistence().cacheResult(partnerEntries);
	}

	/**
	* Creates a new partner entry with the primary key. Does not add the partner entry to the database.
	*
	* @param partnerEntryId the primary key for the new partner entry
	* @return the new partner entry
	*/
	public static com.liferay.osb.model.PartnerEntry create(long partnerEntryId) {
		return getPersistence().create(partnerEntryId);
	}

	/**
	* Removes the partner entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry that was removed
	* @throws com.liferay.osb.NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry remove(long partnerEntryId)
		throws com.liferay.osb.NoSuchPartnerEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(partnerEntryId);
	}

	public static com.liferay.osb.model.PartnerEntry updateImpl(
		com.liferay.osb.model.PartnerEntry partnerEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(partnerEntry, merge);
	}

	/**
	* Returns the partner entry with the primary key or throws a {@link com.liferay.osb.NoSuchPartnerEntryException} if it could not be found.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry
	* @throws com.liferay.osb.NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry findByPrimaryKey(
		long partnerEntryId)
		throws com.liferay.osb.NoSuchPartnerEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(partnerEntryId);
	}

	/**
	* Returns the partner entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry, or <code>null</code> if a partner entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry fetchByPrimaryKey(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(partnerEntryId);
	}

	/**
	* Returns all the partner entries where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @return the matching partner entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByParentPartnerEntryId(parentPartnerEntryId);
	}

	/**
	* Returns a range of all the partner entries where parentPartnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @return the range of matching partner entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByParentPartnerEntryId(parentPartnerEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the partner entries where parentPartnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching partner entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByParentPartnerEntryId(parentPartnerEntryId, start,
			end, orderByComparator);
	}

	/**
	* Returns the first partner entry in the ordered set where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner entry
	* @throws com.liferay.osb.NoSuchPartnerEntryException if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry findByParentPartnerEntryId_First(
		long parentPartnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByParentPartnerEntryId_First(parentPartnerEntryId,
			orderByComparator);
	}

	/**
	* Returns the first partner entry in the ordered set where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner entry, or <code>null</code> if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry fetchByParentPartnerEntryId_First(
		long parentPartnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByParentPartnerEntryId_First(parentPartnerEntryId,
			orderByComparator);
	}

	/**
	* Returns the last partner entry in the ordered set where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner entry
	* @throws com.liferay.osb.NoSuchPartnerEntryException if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry findByParentPartnerEntryId_Last(
		long parentPartnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByParentPartnerEntryId_Last(parentPartnerEntryId,
			orderByComparator);
	}

	/**
	* Returns the last partner entry in the ordered set where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner entry, or <code>null</code> if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry fetchByParentPartnerEntryId_Last(
		long parentPartnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByParentPartnerEntryId_Last(parentPartnerEntryId,
			orderByComparator);
	}

	/**
	* Returns the partner entries before and after the current partner entry in the ordered set where parentPartnerEntryId = &#63;.
	*
	* @param partnerEntryId the primary key of the current partner entry
	* @param parentPartnerEntryId the parent partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next partner entry
	* @throws com.liferay.osb.NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry[] findByParentPartnerEntryId_PrevAndNext(
		long partnerEntryId, long parentPartnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchPartnerEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByParentPartnerEntryId_PrevAndNext(partnerEntryId,
			parentPartnerEntryId, orderByComparator);
	}

	/**
	* Returns the partner entry where dossieraAccountKey = &#63; or throws a {@link com.liferay.osb.NoSuchPartnerEntryException} if it could not be found.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the matching partner entry
	* @throws com.liferay.osb.NoSuchPartnerEntryException if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry findByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.osb.NoSuchPartnerEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByDossieraAccountKey(dossieraAccountKey);
	}

	/**
	* Returns the partner entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry fetchByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByDossieraAccountKey(dossieraAccountKey);
	}

	/**
	* Returns the partner entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossieraAccountKey the dossiera account key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry fetchByDossieraAccountKey(
		java.lang.String dossieraAccountKey, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByDossieraAccountKey(dossieraAccountKey,
			retrieveFromCache);
	}

	/**
	* Returns the partner entry where code = &#63; or throws a {@link com.liferay.osb.NoSuchPartnerEntryException} if it could not be found.
	*
	* @param code the code
	* @return the matching partner entry
	* @throws com.liferay.osb.NoSuchPartnerEntryException if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry findByCode(
		java.lang.String code)
		throws com.liferay.osb.NoSuchPartnerEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCode(code);
	}

	/**
	* Returns the partner entry where code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param code the code
	* @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry fetchByCode(
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByCode(code);
	}

	/**
	* Returns the partner entry where code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param code the code
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry fetchByCode(
		java.lang.String code, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByCode(code, retrieveFromCache);
	}

	/**
	* Returns all the partner entries.
	*
	* @return the partner entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PartnerEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the partner entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @return the range of partner entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PartnerEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the partner entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of partner entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PartnerEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the partner entries where parentPartnerEntryId = &#63; from the database.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByParentPartnerEntryId(long parentPartnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByParentPartnerEntryId(parentPartnerEntryId);
	}

	/**
	* Removes the partner entry where dossieraAccountKey = &#63; from the database.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the partner entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry removeByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.osb.NoSuchPartnerEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByDossieraAccountKey(dossieraAccountKey);
	}

	/**
	* Removes the partner entry where code = &#63; from the database.
	*
	* @param code the code
	* @return the partner entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry removeByCode(
		java.lang.String code)
		throws com.liferay.osb.NoSuchPartnerEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByCode(code);
	}

	/**
	* Removes all the partner entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of partner entries where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @return the number of matching partner entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByParentPartnerEntryId(long parentPartnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByParentPartnerEntryId(parentPartnerEntryId);
	}

	/**
	* Returns the number of partner entries where dossieraAccountKey = &#63;.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the number of matching partner entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByDossieraAccountKey(dossieraAccountKey);
	}

	/**
	* Returns the number of partner entries where code = &#63;.
	*
	* @param code the code
	* @return the number of matching partner entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCode(java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCode(code);
	}

	/**
	* Returns the number of partner entries.
	*
	* @return the number of partner entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Returns all the support regions associated with the partner entry.
	*
	* @param pk the primary key of the partner entry
	* @return the support regions associated with the partner entry
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getSupportRegions(pk);
	}

	/**
	* Returns a range of all the support regions associated with the partner entry.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the partner entry
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @return the range of support regions associated with the partner entry
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getSupportRegions(pk, start, end);
	}

	/**
	* Returns an ordered range of all the support regions associated with the partner entry.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the partner entry
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support regions associated with the partner entry
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .getSupportRegions(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of support regions associated with the partner entry.
	*
	* @param pk the primary key of the partner entry
	* @return the number of support regions associated with the partner entry
	* @throws SystemException if a system exception occurred
	*/
	public static int getSupportRegionsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getSupportRegionsSize(pk);
	}

	/**
	* Returns <code>true</code> if the support region is associated with the partner entry.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegionPK the primary key of the support region
	* @return <code>true</code> if the support region is associated with the partner entry; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsSupportRegion(long pk, long supportRegionPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsSupportRegion(pk, supportRegionPK);
	}

	/**
	* Returns <code>true</code> if the partner entry has any support regions associated with it.
	*
	* @param pk the primary key of the partner entry to check for associations with support regions
	* @return <code>true</code> if the partner entry has any support regions associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsSupportRegions(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsSupportRegions(pk);
	}

	/**
	* Adds an association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegionPK the primary key of the support region
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegion(long pk, long supportRegionPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addSupportRegion(pk, supportRegionPK);
	}

	/**
	* Adds an association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegion the support region
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addSupportRegion(pk, supportRegion);
	}

	/**
	* Adds an association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegionPKs the primary keys of the support regions
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegions(long pk, long[] supportRegionPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addSupportRegions(pk, supportRegionPKs);
	}

	/**
	* Adds an association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegions the support regions
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegions(long pk,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addSupportRegions(pk, supportRegions);
	}

	/**
	* Clears all associations between the partner entry and its support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry to clear the associated support regions from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearSupportRegions(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearSupportRegions(pk);
	}

	/**
	* Removes the association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegionPK the primary key of the support region
	* @throws SystemException if a system exception occurred
	*/
	public static void removeSupportRegion(long pk, long supportRegionPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeSupportRegion(pk, supportRegionPK);
	}

	/**
	* Removes the association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegion the support region
	* @throws SystemException if a system exception occurred
	*/
	public static void removeSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeSupportRegion(pk, supportRegion);
	}

	/**
	* Removes the association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegionPKs the primary keys of the support regions
	* @throws SystemException if a system exception occurred
	*/
	public static void removeSupportRegions(long pk, long[] supportRegionPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeSupportRegions(pk, supportRegionPKs);
	}

	/**
	* Removes the association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegions the support regions
	* @throws SystemException if a system exception occurred
	*/
	public static void removeSupportRegions(long pk,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeSupportRegions(pk, supportRegions);
	}

	/**
	* Sets the support regions associated with the partner entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegionPKs the primary keys of the support regions to be associated with the partner entry
	* @throws SystemException if a system exception occurred
	*/
	public static void setSupportRegions(long pk, long[] supportRegionPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setSupportRegions(pk, supportRegionPKs);
	}

	/**
	* Sets the support regions associated with the partner entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegions the support regions to be associated with the partner entry
	* @throws SystemException if a system exception occurred
	*/
	public static void setSupportRegions(long pk,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setSupportRegions(pk, supportRegions);
	}

	public static PartnerEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (PartnerEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					PartnerEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(PartnerEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(PartnerEntryPersistence persistence) {
	}

	private static PartnerEntryPersistence _persistence;
}