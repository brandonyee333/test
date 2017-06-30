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

import com.liferay.osb.model.SupportRegion;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the support region service. This utility wraps {@link SupportRegionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportRegionPersistence
 * @see SupportRegionPersistenceImpl
 * @generated
 */
public class SupportRegionUtil {
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
	public static void clearCache(SupportRegion supportRegion) {
		getPersistence().clearCache(supportRegion);
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
	public static List<SupportRegion> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SupportRegion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SupportRegion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static SupportRegion update(SupportRegion supportRegion,
		boolean merge) throws SystemException {
		return getPersistence().update(supportRegion, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static SupportRegion update(SupportRegion supportRegion,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(supportRegion, merge, serviceContext);
	}

	/**
	* Caches the support region in the entity cache if it is enabled.
	*
	* @param supportRegion the support region
	*/
	public static void cacheResult(
		com.liferay.osb.model.SupportRegion supportRegion) {
		getPersistence().cacheResult(supportRegion);
	}

	/**
	* Caches the support regions in the entity cache if it is enabled.
	*
	* @param supportRegions the support regions
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions) {
		getPersistence().cacheResult(supportRegions);
	}

	/**
	* Creates a new support region with the primary key. Does not add the support region to the database.
	*
	* @param supportRegionId the primary key for the new support region
	* @return the new support region
	*/
	public static com.liferay.osb.model.SupportRegion create(
		long supportRegionId) {
		return getPersistence().create(supportRegionId);
	}

	/**
	* Removes the support region with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportRegionId the primary key of the support region
	* @return the support region that was removed
	* @throws com.liferay.osb.NoSuchSupportRegionException if a support region with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportRegion remove(
		long supportRegionId)
		throws com.liferay.osb.NoSuchSupportRegionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(supportRegionId);
	}

	public static com.liferay.osb.model.SupportRegion updateImpl(
		com.liferay.osb.model.SupportRegion supportRegion, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(supportRegion, merge);
	}

	/**
	* Returns the support region with the primary key or throws a {@link com.liferay.osb.NoSuchSupportRegionException} if it could not be found.
	*
	* @param supportRegionId the primary key of the support region
	* @return the support region
	* @throws com.liferay.osb.NoSuchSupportRegionException if a support region with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportRegion findByPrimaryKey(
		long supportRegionId)
		throws com.liferay.osb.NoSuchSupportRegionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(supportRegionId);
	}

	/**
	* Returns the support region with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportRegionId the primary key of the support region
	* @return the support region, or <code>null</code> if a support region with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportRegion fetchByPrimaryKey(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(supportRegionId);
	}

	/**
	* Returns the support region where name = &#63; or throws a {@link com.liferay.osb.NoSuchSupportRegionException} if it could not be found.
	*
	* @param name the name
	* @return the matching support region
	* @throws com.liferay.osb.NoSuchSupportRegionException if a matching support region could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportRegion findByName(
		java.lang.String name)
		throws com.liferay.osb.NoSuchSupportRegionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name);
	}

	/**
	* Returns the support region where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @return the matching support region, or <code>null</code> if a matching support region could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportRegion fetchByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByName(name);
	}

	/**
	* Returns the support region where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching support region, or <code>null</code> if a matching support region could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportRegion fetchByName(
		java.lang.String name, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByName(name, retrieveFromCache);
	}

	/**
	* Returns all the support regions.
	*
	* @return the support regions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the support regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @return the range of support regions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the support regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support regions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the support region where name = &#63; from the database.
	*
	* @param name the name
	* @return the support region that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportRegion removeByName(
		java.lang.String name)
		throws com.liferay.osb.NoSuchSupportRegionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByName(name);
	}

	/**
	* Removes all the support regions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of support regions where name = &#63;.
	*
	* @param name the name
	* @return the number of matching support regions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByName(name);
	}

	/**
	* Returns the number of support regions.
	*
	* @return the number of support regions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Returns all the account entries associated with the support region.
	*
	* @param pk the primary key of the support region
	* @return the account entries associated with the support region
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getAccountEntries(pk);
	}

	/**
	* Returns a range of all the account entries associated with the support region.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the support region
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @return the range of account entries associated with the support region
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getAccountEntries(pk, start, end);
	}

	/**
	* Returns an ordered range of all the account entries associated with the support region.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the support region
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account entries associated with the support region
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .getAccountEntries(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of account entries associated with the support region.
	*
	* @param pk the primary key of the support region
	* @return the number of account entries associated with the support region
	* @throws SystemException if a system exception occurred
	*/
	public static int getAccountEntriesSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getAccountEntriesSize(pk);
	}

	/**
	* Returns <code>true</code> if the account entry is associated with the support region.
	*
	* @param pk the primary key of the support region
	* @param accountEntryPK the primary key of the account entry
	* @return <code>true</code> if the account entry is associated with the support region; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsAccountEntry(long pk, long accountEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsAccountEntry(pk, accountEntryPK);
	}

	/**
	* Returns <code>true</code> if the support region has any account entries associated with it.
	*
	* @param pk the primary key of the support region to check for associations with account entries
	* @return <code>true</code> if the support region has any account entries associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsAccountEntries(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsAccountEntries(pk);
	}

	/**
	* Adds an association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntryPK the primary key of the account entry
	* @throws SystemException if a system exception occurred
	*/
	public static void addAccountEntry(long pk, long accountEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addAccountEntry(pk, accountEntryPK);
	}

	/**
	* Adds an association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntry the account entry
	* @throws SystemException if a system exception occurred
	*/
	public static void addAccountEntry(long pk,
		com.liferay.osb.model.AccountEntry accountEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addAccountEntry(pk, accountEntry);
	}

	/**
	* Adds an association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntryPKs the primary keys of the account entries
	* @throws SystemException if a system exception occurred
	*/
	public static void addAccountEntries(long pk, long[] accountEntryPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addAccountEntries(pk, accountEntryPKs);
	}

	/**
	* Adds an association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntries the account entries
	* @throws SystemException if a system exception occurred
	*/
	public static void addAccountEntries(long pk,
		java.util.List<com.liferay.osb.model.AccountEntry> accountEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addAccountEntries(pk, accountEntries);
	}

	/**
	* Clears all associations between the support region and its account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region to clear the associated account entries from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearAccountEntries(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearAccountEntries(pk);
	}

	/**
	* Removes the association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntryPK the primary key of the account entry
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAccountEntry(long pk, long accountEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAccountEntry(pk, accountEntryPK);
	}

	/**
	* Removes the association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntry the account entry
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAccountEntry(long pk,
		com.liferay.osb.model.AccountEntry accountEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAccountEntry(pk, accountEntry);
	}

	/**
	* Removes the association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntryPKs the primary keys of the account entries
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAccountEntries(long pk, long[] accountEntryPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAccountEntries(pk, accountEntryPKs);
	}

	/**
	* Removes the association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntries the account entries
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAccountEntries(long pk,
		java.util.List<com.liferay.osb.model.AccountEntry> accountEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAccountEntries(pk, accountEntries);
	}

	/**
	* Sets the account entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntryPKs the primary keys of the account entries to be associated with the support region
	* @throws SystemException if a system exception occurred
	*/
	public static void setAccountEntries(long pk, long[] accountEntryPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setAccountEntries(pk, accountEntryPKs);
	}

	/**
	* Sets the account entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntries the account entries to be associated with the support region
	* @throws SystemException if a system exception occurred
	*/
	public static void setAccountEntries(long pk,
		java.util.List<com.liferay.osb.model.AccountEntry> accountEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setAccountEntries(pk, accountEntries);
	}

	/**
	* Returns all the partner entries associated with the support region.
	*
	* @param pk the primary key of the support region
	* @return the partner entries associated with the support region
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PartnerEntry> getPartnerEntries(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getPartnerEntries(pk);
	}

	/**
	* Returns a range of all the partner entries associated with the support region.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the support region
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @return the range of partner entries associated with the support region
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PartnerEntry> getPartnerEntries(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getPartnerEntries(pk, start, end);
	}

	/**
	* Returns an ordered range of all the partner entries associated with the support region.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the support region
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of partner entries associated with the support region
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PartnerEntry> getPartnerEntries(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .getPartnerEntries(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of partner entries associated with the support region.
	*
	* @param pk the primary key of the support region
	* @return the number of partner entries associated with the support region
	* @throws SystemException if a system exception occurred
	*/
	public static int getPartnerEntriesSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getPartnerEntriesSize(pk);
	}

	/**
	* Returns <code>true</code> if the partner entry is associated with the support region.
	*
	* @param pk the primary key of the support region
	* @param partnerEntryPK the primary key of the partner entry
	* @return <code>true</code> if the partner entry is associated with the support region; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsPartnerEntry(long pk, long partnerEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsPartnerEntry(pk, partnerEntryPK);
	}

	/**
	* Returns <code>true</code> if the support region has any partner entries associated with it.
	*
	* @param pk the primary key of the support region to check for associations with partner entries
	* @return <code>true</code> if the support region has any partner entries associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsPartnerEntries(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsPartnerEntries(pk);
	}

	/**
	* Adds an association between the support region and the partner entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntryPK the primary key of the partner entry
	* @throws SystemException if a system exception occurred
	*/
	public static void addPartnerEntry(long pk, long partnerEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addPartnerEntry(pk, partnerEntryPK);
	}

	/**
	* Adds an association between the support region and the partner entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntry the partner entry
	* @throws SystemException if a system exception occurred
	*/
	public static void addPartnerEntry(long pk,
		com.liferay.osb.model.PartnerEntry partnerEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addPartnerEntry(pk, partnerEntry);
	}

	/**
	* Adds an association between the support region and the partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntryPKs the primary keys of the partner entries
	* @throws SystemException if a system exception occurred
	*/
	public static void addPartnerEntries(long pk, long[] partnerEntryPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addPartnerEntries(pk, partnerEntryPKs);
	}

	/**
	* Adds an association between the support region and the partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntries the partner entries
	* @throws SystemException if a system exception occurred
	*/
	public static void addPartnerEntries(long pk,
		java.util.List<com.liferay.osb.model.PartnerEntry> partnerEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addPartnerEntries(pk, partnerEntries);
	}

	/**
	* Clears all associations between the support region and its partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region to clear the associated partner entries from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearPartnerEntries(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearPartnerEntries(pk);
	}

	/**
	* Removes the association between the support region and the partner entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntryPK the primary key of the partner entry
	* @throws SystemException if a system exception occurred
	*/
	public static void removePartnerEntry(long pk, long partnerEntryPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removePartnerEntry(pk, partnerEntryPK);
	}

	/**
	* Removes the association between the support region and the partner entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntry the partner entry
	* @throws SystemException if a system exception occurred
	*/
	public static void removePartnerEntry(long pk,
		com.liferay.osb.model.PartnerEntry partnerEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removePartnerEntry(pk, partnerEntry);
	}

	/**
	* Removes the association between the support region and the partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntryPKs the primary keys of the partner entries
	* @throws SystemException if a system exception occurred
	*/
	public static void removePartnerEntries(long pk, long[] partnerEntryPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removePartnerEntries(pk, partnerEntryPKs);
	}

	/**
	* Removes the association between the support region and the partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntries the partner entries
	* @throws SystemException if a system exception occurred
	*/
	public static void removePartnerEntries(long pk,
		java.util.List<com.liferay.osb.model.PartnerEntry> partnerEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removePartnerEntries(pk, partnerEntries);
	}

	/**
	* Sets the partner entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntryPKs the primary keys of the partner entries to be associated with the support region
	* @throws SystemException if a system exception occurred
	*/
	public static void setPartnerEntries(long pk, long[] partnerEntryPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setPartnerEntries(pk, partnerEntryPKs);
	}

	/**
	* Sets the partner entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntries the partner entries to be associated with the support region
	* @throws SystemException if a system exception occurred
	*/
	public static void setPartnerEntries(long pk,
		java.util.List<com.liferay.osb.model.PartnerEntry> partnerEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setPartnerEntries(pk, partnerEntries);
	}

	/**
	* Returns all the support teams associated with the support region.
	*
	* @param pk the primary key of the support region
	* @return the support teams associated with the support region
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getSupportTeams(pk);
	}

	/**
	* Returns a range of all the support teams associated with the support region.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the support region
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @return the range of support teams associated with the support region
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getSupportTeams(pk, start, end);
	}

	/**
	* Returns an ordered range of all the support teams associated with the support region.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the support region
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support teams associated with the support region
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .getSupportTeams(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of support teams associated with the support region.
	*
	* @param pk the primary key of the support region
	* @return the number of support teams associated with the support region
	* @throws SystemException if a system exception occurred
	*/
	public static int getSupportTeamsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getSupportTeamsSize(pk);
	}

	/**
	* Returns <code>true</code> if the support team is associated with the support region.
	*
	* @param pk the primary key of the support region
	* @param supportTeamPK the primary key of the support team
	* @return <code>true</code> if the support team is associated with the support region; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsSupportTeam(long pk, long supportTeamPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsSupportTeam(pk, supportTeamPK);
	}

	/**
	* Returns <code>true</code> if the support region has any support teams associated with it.
	*
	* @param pk the primary key of the support region to check for associations with support teams
	* @return <code>true</code> if the support region has any support teams associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsSupportTeams(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsSupportTeams(pk);
	}

	/**
	* Adds an association between the support region and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param supportTeamPK the primary key of the support team
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportTeam(long pk, long supportTeamPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addSupportTeam(pk, supportTeamPK);
	}

	/**
	* Adds an association between the support region and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param supportTeam the support team
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportTeam(long pk,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addSupportTeam(pk, supportTeam);
	}

	/**
	* Adds an association between the support region and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param supportTeamPKs the primary keys of the support teams
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportTeams(long pk, long[] supportTeamPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addSupportTeams(pk, supportTeamPKs);
	}

	/**
	* Adds an association between the support region and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param supportTeams the support teams
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportTeams(long pk,
		java.util.List<com.liferay.osb.model.SupportTeam> supportTeams)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addSupportTeams(pk, supportTeams);
	}

	/**
	* Clears all associations between the support region and its support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region to clear the associated support teams from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearSupportTeams(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearSupportTeams(pk);
	}

	/**
	* Removes the association between the support region and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param supportTeamPK the primary key of the support team
	* @throws SystemException if a system exception occurred
	*/
	public static void removeSupportTeam(long pk, long supportTeamPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeSupportTeam(pk, supportTeamPK);
	}

	/**
	* Removes the association between the support region and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param supportTeam the support team
	* @throws SystemException if a system exception occurred
	*/
	public static void removeSupportTeam(long pk,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeSupportTeam(pk, supportTeam);
	}

	/**
	* Removes the association between the support region and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param supportTeamPKs the primary keys of the support teams
	* @throws SystemException if a system exception occurred
	*/
	public static void removeSupportTeams(long pk, long[] supportTeamPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeSupportTeams(pk, supportTeamPKs);
	}

	/**
	* Removes the association between the support region and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param supportTeams the support teams
	* @throws SystemException if a system exception occurred
	*/
	public static void removeSupportTeams(long pk,
		java.util.List<com.liferay.osb.model.SupportTeam> supportTeams)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeSupportTeams(pk, supportTeams);
	}

	/**
	* Sets the support teams associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param supportTeamPKs the primary keys of the support teams to be associated with the support region
	* @throws SystemException if a system exception occurred
	*/
	public static void setSupportTeams(long pk, long[] supportTeamPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setSupportTeams(pk, supportTeamPKs);
	}

	/**
	* Sets the support teams associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param supportTeams the support teams to be associated with the support region
	* @throws SystemException if a system exception occurred
	*/
	public static void setSupportTeams(long pk,
		java.util.List<com.liferay.osb.model.SupportTeam> supportTeams)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setSupportTeams(pk, supportTeams);
	}

	public static SupportRegionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SupportRegionPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					SupportRegionPersistence.class.getName());

			ReferenceRegistry.registerReference(SupportRegionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(SupportRegionPersistence persistence) {
	}

	private static SupportRegionPersistence _persistence;
}