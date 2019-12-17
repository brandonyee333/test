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

import com.liferay.osb.model.AccountEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the account entry service. This utility wraps {@link com.liferay.osb.service.persistence.impl.AccountEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryPersistence
 * @see com.liferay.osb.service.persistence.impl.AccountEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class AccountEntryUtil {
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
	public static void clearCache(AccountEntry accountEntry) {
		getPersistence().clearCache(accountEntry);
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
	public static List<AccountEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AccountEntry update(AccountEntry accountEntry) {
		return getPersistence().update(accountEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AccountEntry update(AccountEntry accountEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(accountEntry, serviceContext);
	}

	/**
	* Returns the account entry where koroneikiAccountKey = &#63; or throws a {@link NoSuchAccountEntryException} if it could not be found.
	*
	* @param koroneikiAccountKey the koroneiki account key
	* @return the matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByKoroneikiAccountKey(
		java.lang.String koroneikiAccountKey)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence().findByKoroneikiAccountKey(koroneikiAccountKey);
	}

	/**
	* Returns the account entry where koroneikiAccountKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param koroneikiAccountKey the koroneiki account key
	* @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public static AccountEntry fetchByKoroneikiAccountKey(
		java.lang.String koroneikiAccountKey) {
		return getPersistence().fetchByKoroneikiAccountKey(koroneikiAccountKey);
	}

	/**
	* Returns the account entry where koroneikiAccountKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param koroneikiAccountKey the koroneiki account key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public static AccountEntry fetchByKoroneikiAccountKey(
		java.lang.String koroneikiAccountKey, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByKoroneikiAccountKey(koroneikiAccountKey,
			retrieveFromCache);
	}

	/**
	* Removes the account entry where koroneikiAccountKey = &#63; from the database.
	*
	* @param koroneikiAccountKey the koroneiki account key
	* @return the account entry that was removed
	*/
	public static AccountEntry removeByKoroneikiAccountKey(
		java.lang.String koroneikiAccountKey)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence().removeByKoroneikiAccountKey(koroneikiAccountKey);
	}

	/**
	* Returns the number of account entries where koroneikiAccountKey = &#63;.
	*
	* @param koroneikiAccountKey the koroneiki account key
	* @return the number of matching account entries
	*/
	public static int countByKoroneikiAccountKey(
		java.lang.String koroneikiAccountKey) {
		return getPersistence().countByKoroneikiAccountKey(koroneikiAccountKey);
	}

	/**
	* Returns all the account entries where dossieraAccountKey = &#63;.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the matching account entries
	*/
	public static List<AccountEntry> findByDossieraAccountKey(
		java.lang.String dossieraAccountKey) {
		return getPersistence().findByDossieraAccountKey(dossieraAccountKey);
	}

	/**
	* Returns a range of all the account entries where dossieraAccountKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossieraAccountKey the dossiera account key
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public static List<AccountEntry> findByDossieraAccountKey(
		java.lang.String dossieraAccountKey, int start, int end) {
		return getPersistence()
				   .findByDossieraAccountKey(dossieraAccountKey, start, end);
	}

	/**
	* Returns an ordered range of all the account entries where dossieraAccountKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossieraAccountKey the dossiera account key
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByDossieraAccountKey(
		java.lang.String dossieraAccountKey, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {
		return getPersistence()
				   .findByDossieraAccountKey(dossieraAccountKey, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the account entries where dossieraAccountKey = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dossieraAccountKey the dossiera account key
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByDossieraAccountKey(
		java.lang.String dossieraAccountKey, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByDossieraAccountKey(dossieraAccountKey, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first account entry in the ordered set where dossieraAccountKey = &#63;.
	*
	* @param dossieraAccountKey the dossiera account key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByDossieraAccountKey_First(
		java.lang.String dossieraAccountKey,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence()
				   .findByDossieraAccountKey_First(dossieraAccountKey,
			orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where dossieraAccountKey = &#63;.
	*
	* @param dossieraAccountKey the dossiera account key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public static AccountEntry fetchByDossieraAccountKey_First(
		java.lang.String dossieraAccountKey,
		OrderByComparator<AccountEntry> orderByComparator) {
		return getPersistence()
				   .fetchByDossieraAccountKey_First(dossieraAccountKey,
			orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where dossieraAccountKey = &#63;.
	*
	* @param dossieraAccountKey the dossiera account key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByDossieraAccountKey_Last(
		java.lang.String dossieraAccountKey,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence()
				   .findByDossieraAccountKey_Last(dossieraAccountKey,
			orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where dossieraAccountKey = &#63;.
	*
	* @param dossieraAccountKey the dossiera account key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public static AccountEntry fetchByDossieraAccountKey_Last(
		java.lang.String dossieraAccountKey,
		OrderByComparator<AccountEntry> orderByComparator) {
		return getPersistence()
				   .fetchByDossieraAccountKey_Last(dossieraAccountKey,
			orderByComparator);
	}

	/**
	* Returns the account entries before and after the current account entry in the ordered set where dossieraAccountKey = &#63;.
	*
	* @param accountEntryId the primary key of the current account entry
	* @param dossieraAccountKey the dossiera account key
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public static AccountEntry[] findByDossieraAccountKey_PrevAndNext(
		long accountEntryId, java.lang.String dossieraAccountKey,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence()
				   .findByDossieraAccountKey_PrevAndNext(accountEntryId,
			dossieraAccountKey, orderByComparator);
	}

	/**
	* Removes all the account entries where dossieraAccountKey = &#63; from the database.
	*
	* @param dossieraAccountKey the dossiera account key
	*/
	public static void removeByDossieraAccountKey(
		java.lang.String dossieraAccountKey) {
		getPersistence().removeByDossieraAccountKey(dossieraAccountKey);
	}

	/**
	* Returns the number of account entries where dossieraAccountKey = &#63;.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the number of matching account entries
	*/
	public static int countByDossieraAccountKey(
		java.lang.String dossieraAccountKey) {
		return getPersistence().countByDossieraAccountKey(dossieraAccountKey);
	}

	/**
	* Caches the account entry in the entity cache if it is enabled.
	*
	* @param accountEntry the account entry
	*/
	public static void cacheResult(AccountEntry accountEntry) {
		getPersistence().cacheResult(accountEntry);
	}

	/**
	* Caches the account entries in the entity cache if it is enabled.
	*
	* @param accountEntries the account entries
	*/
	public static void cacheResult(List<AccountEntry> accountEntries) {
		getPersistence().cacheResult(accountEntries);
	}

	/**
	* Creates a new account entry with the primary key. Does not add the account entry to the database.
	*
	* @param accountEntryId the primary key for the new account entry
	* @return the new account entry
	*/
	public static AccountEntry create(long accountEntryId) {
		return getPersistence().create(accountEntryId);
	}

	/**
	* Removes the account entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry that was removed
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public static AccountEntry remove(long accountEntryId)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence().remove(accountEntryId);
	}

	public static AccountEntry updateImpl(AccountEntry accountEntry) {
		return getPersistence().updateImpl(accountEntry);
	}

	/**
	* Returns the account entry with the primary key or throws a {@link NoSuchAccountEntryException} if it could not be found.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public static AccountEntry findByPrimaryKey(long accountEntryId)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence().findByPrimaryKey(accountEntryId);
	}

	/**
	* Returns the account entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry, or <code>null</code> if a account entry with the primary key could not be found
	*/
	public static AccountEntry fetchByPrimaryKey(long accountEntryId) {
		return getPersistence().fetchByPrimaryKey(accountEntryId);
	}

	public static java.util.Map<java.io.Serializable, AccountEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the account entries.
	*
	* @return the account entries
	*/
	public static List<AccountEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the account entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of account entries
	*/
	public static List<AccountEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the account entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account entries
	*/
	public static List<AccountEntry> findAll(int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of account entries
	*/
	public static List<AccountEntry> findAll(int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the account entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of account entries.
	*
	* @return the number of account entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	* Returns the primaryKeys of support regions associated with the account entry.
	*
	* @param pk the primary key of the account entry
	* @return long[] of the primaryKeys of support regions associated with the account entry
	*/
	public static long[] getSupportRegionPrimaryKeys(long pk) {
		return getPersistence().getSupportRegionPrimaryKeys(pk);
	}

	/**
	* Returns all the support regions associated with the account entry.
	*
	* @param pk the primary key of the account entry
	* @return the support regions associated with the account entry
	*/
	public static List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk) {
		return getPersistence().getSupportRegions(pk);
	}

	/**
	* Returns a range of all the support regions associated with the account entry.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the account entry
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of support regions associated with the account entry
	*/
	public static List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end) {
		return getPersistence().getSupportRegions(pk, start, end);
	}

	/**
	* Returns an ordered range of all the support regions associated with the account entry.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the account entry
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support regions associated with the account entry
	*/
	public static List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end,
		OrderByComparator<com.liferay.osb.model.SupportRegion> orderByComparator) {
		return getPersistence()
				   .getSupportRegions(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of support regions associated with the account entry.
	*
	* @param pk the primary key of the account entry
	* @return the number of support regions associated with the account entry
	*/
	public static int getSupportRegionsSize(long pk) {
		return getPersistence().getSupportRegionsSize(pk);
	}

	/**
	* Returns <code>true</code> if the support region is associated with the account entry.
	*
	* @param pk the primary key of the account entry
	* @param supportRegionPK the primary key of the support region
	* @return <code>true</code> if the support region is associated with the account entry; <code>false</code> otherwise
	*/
	public static boolean containsSupportRegion(long pk, long supportRegionPK) {
		return getPersistence().containsSupportRegion(pk, supportRegionPK);
	}

	/**
	* Returns <code>true</code> if the account entry has any support regions associated with it.
	*
	* @param pk the primary key of the account entry to check for associations with support regions
	* @return <code>true</code> if the account entry has any support regions associated with it; <code>false</code> otherwise
	*/
	public static boolean containsSupportRegions(long pk) {
		return getPersistence().containsSupportRegions(pk);
	}

	/**
	* Adds an association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegionPK the primary key of the support region
	*/
	public static void addSupportRegion(long pk, long supportRegionPK) {
		getPersistence().addSupportRegion(pk, supportRegionPK);
	}

	/**
	* Adds an association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegion the support region
	*/
	public static void addSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion) {
		getPersistence().addSupportRegion(pk, supportRegion);
	}

	/**
	* Adds an association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegionPKs the primary keys of the support regions
	*/
	public static void addSupportRegions(long pk, long[] supportRegionPKs) {
		getPersistence().addSupportRegions(pk, supportRegionPKs);
	}

	/**
	* Adds an association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegions the support regions
	*/
	public static void addSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions) {
		getPersistence().addSupportRegions(pk, supportRegions);
	}

	/**
	* Clears all associations between the account entry and its support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry to clear the associated support regions from
	*/
	public static void clearSupportRegions(long pk) {
		getPersistence().clearSupportRegions(pk);
	}

	/**
	* Removes the association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegionPK the primary key of the support region
	*/
	public static void removeSupportRegion(long pk, long supportRegionPK) {
		getPersistence().removeSupportRegion(pk, supportRegionPK);
	}

	/**
	* Removes the association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegion the support region
	*/
	public static void removeSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion) {
		getPersistence().removeSupportRegion(pk, supportRegion);
	}

	/**
	* Removes the association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegionPKs the primary keys of the support regions
	*/
	public static void removeSupportRegions(long pk, long[] supportRegionPKs) {
		getPersistence().removeSupportRegions(pk, supportRegionPKs);
	}

	/**
	* Removes the association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegions the support regions
	*/
	public static void removeSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions) {
		getPersistence().removeSupportRegions(pk, supportRegions);
	}

	/**
	* Sets the support regions associated with the account entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegionPKs the primary keys of the support regions to be associated with the account entry
	*/
	public static void setSupportRegions(long pk, long[] supportRegionPKs) {
		getPersistence().setSupportRegions(pk, supportRegionPKs);
	}

	/**
	* Sets the support regions associated with the account entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegions the support regions to be associated with the account entry
	*/
	public static void setSupportRegions(long pk,
		List<com.liferay.osb.model.SupportRegion> supportRegions) {
		getPersistence().setSupportRegions(pk, supportRegions);
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AccountEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AccountEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AccountEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(AccountEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static AccountEntryPersistence _persistence;
}