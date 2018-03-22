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

import com.liferay.osb.model.PartnerEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the partner entry service. This utility wraps {@link com.liferay.osb.service.persistence.impl.PartnerEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PartnerEntryPersistence
 * @see com.liferay.osb.service.persistence.impl.PartnerEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class PartnerEntryUtil {
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
	public static void clearCache(PartnerEntry partnerEntry) {
		getPersistence().clearCache(partnerEntry);
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
	public static List<PartnerEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<PartnerEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<PartnerEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<PartnerEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static PartnerEntry update(PartnerEntry partnerEntry) {
		return getPersistence().update(partnerEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static PartnerEntry update(PartnerEntry partnerEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(partnerEntry, serviceContext);
	}

	/**
	* Returns all the partner entries where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @return the matching partner entries
	*/
	public static List<PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId) {
		return getPersistence().findByParentPartnerEntryId(parentPartnerEntryId);
	}

	/**
	* Returns a range of all the partner entries where parentPartnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @return the range of matching partner entries
	*/
	public static List<PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId, int start, int end) {
		return getPersistence()
				   .findByParentPartnerEntryId(parentPartnerEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the partner entries where parentPartnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching partner entries
	*/
	public static List<PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId, int start, int end,
		OrderByComparator<PartnerEntry> orderByComparator) {
		return getPersistence()
				   .findByParentPartnerEntryId(parentPartnerEntryId, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the partner entries where parentPartnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching partner entries
	*/
	public static List<PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId, int start, int end,
		OrderByComparator<PartnerEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByParentPartnerEntryId(parentPartnerEntryId, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first partner entry in the ordered set where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner entry
	* @throws NoSuchPartnerEntryException if a matching partner entry could not be found
	*/
	public static PartnerEntry findByParentPartnerEntryId_First(
		long parentPartnerEntryId,
		OrderByComparator<PartnerEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchPartnerEntryException {
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
	*/
	public static PartnerEntry fetchByParentPartnerEntryId_First(
		long parentPartnerEntryId,
		OrderByComparator<PartnerEntry> orderByComparator) {
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
	* @throws NoSuchPartnerEntryException if a matching partner entry could not be found
	*/
	public static PartnerEntry findByParentPartnerEntryId_Last(
		long parentPartnerEntryId,
		OrderByComparator<PartnerEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchPartnerEntryException {
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
	*/
	public static PartnerEntry fetchByParentPartnerEntryId_Last(
		long parentPartnerEntryId,
		OrderByComparator<PartnerEntry> orderByComparator) {
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
	* @throws NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	*/
	public static PartnerEntry[] findByParentPartnerEntryId_PrevAndNext(
		long partnerEntryId, long parentPartnerEntryId,
		OrderByComparator<PartnerEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchPartnerEntryException {
		return getPersistence()
				   .findByParentPartnerEntryId_PrevAndNext(partnerEntryId,
			parentPartnerEntryId, orderByComparator);
	}

	/**
	* Removes all the partner entries where parentPartnerEntryId = &#63; from the database.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	*/
	public static void removeByParentPartnerEntryId(long parentPartnerEntryId) {
		getPersistence().removeByParentPartnerEntryId(parentPartnerEntryId);
	}

	/**
	* Returns the number of partner entries where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @return the number of matching partner entries
	*/
	public static int countByParentPartnerEntryId(long parentPartnerEntryId) {
		return getPersistence().countByParentPartnerEntryId(parentPartnerEntryId);
	}

	/**
	* Returns the partner entry where dossieraAccountKey = &#63; or throws a {@link NoSuchPartnerEntryException} if it could not be found.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the matching partner entry
	* @throws NoSuchPartnerEntryException if a matching partner entry could not be found
	*/
	public static PartnerEntry findByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.osb.exception.NoSuchPartnerEntryException {
		return getPersistence().findByDossieraAccountKey(dossieraAccountKey);
	}

	/**
	* Returns the partner entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	*/
	public static PartnerEntry fetchByDossieraAccountKey(
		java.lang.String dossieraAccountKey) {
		return getPersistence().fetchByDossieraAccountKey(dossieraAccountKey);
	}

	/**
	* Returns the partner entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossieraAccountKey the dossiera account key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	*/
	public static PartnerEntry fetchByDossieraAccountKey(
		java.lang.String dossieraAccountKey, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByDossieraAccountKey(dossieraAccountKey,
			retrieveFromCache);
	}

	/**
	* Removes the partner entry where dossieraAccountKey = &#63; from the database.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the partner entry that was removed
	*/
	public static PartnerEntry removeByDossieraAccountKey(
		java.lang.String dossieraAccountKey)
		throws com.liferay.osb.exception.NoSuchPartnerEntryException {
		return getPersistence().removeByDossieraAccountKey(dossieraAccountKey);
	}

	/**
	* Returns the number of partner entries where dossieraAccountKey = &#63;.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the number of matching partner entries
	*/
	public static int countByDossieraAccountKey(
		java.lang.String dossieraAccountKey) {
		return getPersistence().countByDossieraAccountKey(dossieraAccountKey);
	}

	/**
	* Returns the partner entry where code = &#63; or throws a {@link NoSuchPartnerEntryException} if it could not be found.
	*
	* @param code the code
	* @return the matching partner entry
	* @throws NoSuchPartnerEntryException if a matching partner entry could not be found
	*/
	public static PartnerEntry findByCode(java.lang.String code)
		throws com.liferay.osb.exception.NoSuchPartnerEntryException {
		return getPersistence().findByCode(code);
	}

	/**
	* Returns the partner entry where code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param code the code
	* @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	*/
	public static PartnerEntry fetchByCode(java.lang.String code) {
		return getPersistence().fetchByCode(code);
	}

	/**
	* Returns the partner entry where code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param code the code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	*/
	public static PartnerEntry fetchByCode(java.lang.String code,
		boolean retrieveFromCache) {
		return getPersistence().fetchByCode(code, retrieveFromCache);
	}

	/**
	* Removes the partner entry where code = &#63; from the database.
	*
	* @param code the code
	* @return the partner entry that was removed
	*/
	public static PartnerEntry removeByCode(java.lang.String code)
		throws com.liferay.osb.exception.NoSuchPartnerEntryException {
		return getPersistence().removeByCode(code);
	}

	/**
	* Returns the number of partner entries where code = &#63;.
	*
	* @param code the code
	* @return the number of matching partner entries
	*/
	public static int countByCode(java.lang.String code) {
		return getPersistence().countByCode(code);
	}

	/**
	* Caches the partner entry in the entity cache if it is enabled.
	*
	* @param partnerEntry the partner entry
	*/
	public static void cacheResult(PartnerEntry partnerEntry) {
		getPersistence().cacheResult(partnerEntry);
	}

	/**
	* Caches the partner entries in the entity cache if it is enabled.
	*
	* @param partnerEntries the partner entries
	*/
	public static void cacheResult(List<PartnerEntry> partnerEntries) {
		getPersistence().cacheResult(partnerEntries);
	}

	/**
	* Creates a new partner entry with the primary key. Does not add the partner entry to the database.
	*
	* @param partnerEntryId the primary key for the new partner entry
	* @return the new partner entry
	*/
	public static PartnerEntry create(long partnerEntryId) {
		return getPersistence().create(partnerEntryId);
	}

	/**
	* Removes the partner entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry that was removed
	* @throws NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	*/
	public static PartnerEntry remove(long partnerEntryId)
		throws com.liferay.osb.exception.NoSuchPartnerEntryException {
		return getPersistence().remove(partnerEntryId);
	}

	public static PartnerEntry updateImpl(PartnerEntry partnerEntry) {
		return getPersistence().updateImpl(partnerEntry);
	}

	/**
	* Returns the partner entry with the primary key or throws a {@link NoSuchPartnerEntryException} if it could not be found.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry
	* @throws NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	*/
	public static PartnerEntry findByPrimaryKey(long partnerEntryId)
		throws com.liferay.osb.exception.NoSuchPartnerEntryException {
		return getPersistence().findByPrimaryKey(partnerEntryId);
	}

	/**
	* Returns the partner entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry, or <code>null</code> if a partner entry with the primary key could not be found
	*/
	public static PartnerEntry fetchByPrimaryKey(long partnerEntryId) {
		return getPersistence().fetchByPrimaryKey(partnerEntryId);
	}

	public static java.util.Map<java.io.Serializable, PartnerEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the partner entries.
	*
	* @return the partner entries
	*/
	public static List<PartnerEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the partner entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @return the range of partner entries
	*/
	public static List<PartnerEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the partner entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of partner entries
	*/
	public static List<PartnerEntry> findAll(int start, int end,
		OrderByComparator<PartnerEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the partner entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of partner entries
	*/
	public static List<PartnerEntry> findAll(int start, int end,
		OrderByComparator<PartnerEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the partner entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of partner entries.
	*
	* @return the number of partner entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	* Returns the primaryKeys of support regions associated with the partner entry.
	*
	* @param pk the primary key of the partner entry
	* @return long[] of the primaryKeys of support regions associated with the partner entry
	*/
	public static long[] getSupportRegionPrimaryKeys(long pk) {
		return getPersistence().getSupportRegionPrimaryKeys(pk);
	}

	/**
	* Returns all the support regions associated with the partner entry.
	*
	* @param pk the primary key of the partner entry
	* @return the support regions associated with the partner entry
	*/
	public static List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk) {
		return getPersistence().getSupportRegions(pk);
	}

	/**
	* Returns a range of all the support regions associated with the partner entry.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the partner entry
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @return the range of support regions associated with the partner entry
	*/
	public static List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end) {
		return getPersistence().getSupportRegions(pk, start, end);
	}

	/**
	* Returns an ordered range of all the support regions associated with the partner entry.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the partner entry
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support regions associated with the partner entry
	*/
	public static List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end,
		OrderByComparator<com.liferay.osb.model.SupportRegion> orderByComparator) {
		return getPersistence()
				   .getSupportRegions(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of support regions associated with the partner entry.
	*
	* @param pk the primary key of the partner entry
	* @return the number of support regions associated with the partner entry
	*/
	public static int getSupportRegionsSize(long pk) {
		return getPersistence().getSupportRegionsSize(pk);
	}

	/**
	* Returns <code>true</code> if the support region is associated with the partner entry.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegionPK the primary key of the support region
	* @return <code>true</code> if the support region is associated with the partner entry; <code>false</code> otherwise
	*/
	public static boolean containsSupportRegion(long pk, long supportRegionPK) {
		return getPersistence().containsSupportRegion(pk, supportRegionPK);
	}

	/**
	* Returns <code>true</code> if the partner entry has any support regions associated with it.
	*
	* @param pk the primary key of the partner entry to check for associations with support regions
	* @return <code>true</code> if the partner entry has any support regions associated with it; <code>false</code> otherwise
	*/
	public static boolean containsSupportRegions(long pk) {
		return getPersistence().containsSupportRegions(pk);
	}

	/**
	* Adds an association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegionPK the primary key of the support region
	*/
	public static void addSupportRegion(long pk, long supportRegionPK) {
		getPersistence().addSupportRegion(pk, supportRegionPK);
	}

	/**
	* Adds an association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegion the support region
	*/
	public static void addSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion) {
		getPersistence().addSupportRegion(pk, supportRegion);
	}

	/**
	* Adds an association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegionPKs the primary keys of the support regions
	*/
	public static void addSupportRegions(long pk, long[] supportRegionPKs) {
		getPersistence().addSupportRegions(pk, supportRegionPKs);
	}

	/**
	* Adds an association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegions the support regions
	*/
	public static void addSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions) {
		getPersistence().addSupportRegions(pk, supportRegions);
	}

	/**
	* Clears all associations between the partner entry and its support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry to clear the associated support regions from
	*/
	public static void clearSupportRegions(long pk) {
		getPersistence().clearSupportRegions(pk);
	}

	/**
	* Removes the association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegionPK the primary key of the support region
	*/
	public static void removeSupportRegion(long pk, long supportRegionPK) {
		getPersistence().removeSupportRegion(pk, supportRegionPK);
	}

	/**
	* Removes the association between the partner entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegion the support region
	*/
	public static void removeSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion) {
		getPersistence().removeSupportRegion(pk, supportRegion);
	}

	/**
	* Removes the association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegionPKs the primary keys of the support regions
	*/
	public static void removeSupportRegions(long pk, long[] supportRegionPKs) {
		getPersistence().removeSupportRegions(pk, supportRegionPKs);
	}

	/**
	* Removes the association between the partner entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegions the support regions
	*/
	public static void removeSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions) {
		getPersistence().removeSupportRegions(pk, supportRegions);
	}

	/**
	* Sets the support regions associated with the partner entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegionPKs the primary keys of the support regions to be associated with the partner entry
	*/
	public static void setSupportRegions(long pk, long[] supportRegionPKs) {
		getPersistence().setSupportRegions(pk, supportRegionPKs);
	}

	/**
	* Sets the support regions associated with the partner entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the partner entry
	* @param supportRegions the support regions to be associated with the partner entry
	*/
	public static void setSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions) {
		getPersistence().setSupportRegions(pk, supportRegions);
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
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

	private static PartnerEntryPersistence _persistence;
}