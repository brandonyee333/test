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

import com.liferay.osb.model.AccountEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the account entry service. This utility wraps {@link AccountEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryPersistence
 * @see AccountEntryPersistenceImpl
 * @generated
 */
public class AccountEntryUtil {
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
	public static void clearCache(AccountEntry accountEntry) {
		getPersistence().clearCache(accountEntry);
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
	public static List<AccountEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AccountEntry update(AccountEntry accountEntry, boolean merge)
		throws SystemException {
		return getPersistence().update(accountEntry, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AccountEntry update(AccountEntry accountEntry, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(accountEntry, merge, serviceContext);
	}

	/**
	* Caches the account entry in the entity cache if it is enabled.
	*
	* @param accountEntry the account entry
	*/
	public static void cacheResult(
		com.liferay.osb.model.AccountEntry accountEntry) {
		getPersistence().cacheResult(accountEntry);
	}

	/**
	* Caches the account entries in the entity cache if it is enabled.
	*
	* @param accountEntries the account entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AccountEntry> accountEntries) {
		getPersistence().cacheResult(accountEntries);
	}

	/**
	* Creates a new account entry with the primary key. Does not add the account entry to the database.
	*
	* @param accountEntryId the primary key for the new account entry
	* @return the new account entry
	*/
	public static com.liferay.osb.model.AccountEntry create(long accountEntryId) {
		return getPersistence().create(accountEntryId);
	}

	/**
	* Removes the account entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry that was removed
	* @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry remove(long accountEntryId)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(accountEntryId);
	}

	public static com.liferay.osb.model.AccountEntry updateImpl(
		com.liferay.osb.model.AccountEntry accountEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(accountEntry, merge);
	}

	/**
	* Returns the account entry with the primary key or throws a {@link com.liferay.osb.NoSuchAccountEntryException} if it could not be found.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByPrimaryKey(
		long accountEntryId)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(accountEntryId);
	}

	/**
	* Returns the account entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry, or <code>null</code> if a account entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByPrimaryKey(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(accountEntryId);
	}

	/**
	* Returns the account entry where corpProjectId = &#63; or throws a {@link com.liferay.osb.NoSuchAccountEntryException} if it could not be found.
	*
	* @param corpProjectId the corp project ID
	* @return the matching account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByCorpProjectId(
		long corpProjectId)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCorpProjectId(corpProjectId);
	}

	/**
	* Returns the account entry where corpProjectId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param corpProjectId the corp project ID
	* @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByCorpProjectId(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByCorpProjectId(corpProjectId);
	}

	/**
	* Returns the account entry where corpProjectId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param corpProjectId the corp project ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByCorpProjectId(
		long corpProjectId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCorpProjectId(corpProjectId, retrieveFromCache);
	}

	/**
	* Returns all the account entries where name = &#63;.
	*
	* @param name the name
	* @return the matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name);
	}

	/**
	* Returns a range of all the account entries where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByName(
		java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name, start, end);
	}

	/**
	* Returns an ordered range of all the account entries where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByName(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name, start, end, orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName_First(name, orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByName_First(name, orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName_Last(name, orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByName_Last(name, orderByComparator);
	}

	/**
	* Returns the account entries before and after the current account entry in the ordered set where name = &#63;.
	*
	* @param accountEntryId the primary key of the current account entry
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry[] findByName_PrevAndNext(
		long accountEntryId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByName_PrevAndNext(accountEntryId, name,
			orderByComparator);
	}

	/**
	* Returns the account entry where code = &#63; or throws a {@link com.liferay.osb.NoSuchAccountEntryException} if it could not be found.
	*
	* @param code the code
	* @return the matching account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByCode(
		java.lang.String code)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCode(code);
	}

	/**
	* Returns the account entry where code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param code the code
	* @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByCode(
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByCode(code);
	}

	/**
	* Returns the account entry where code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param code the code
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByCode(
		java.lang.String code, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByCode(code, retrieveFromCache);
	}

	/**
	* Returns all the account entries where redirectAccountEntryId = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @return the matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByRedirectAccountEntryId(
		long redirectAccountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRedirectAccountEntryId(redirectAccountEntryId);
	}

	/**
	* Returns a range of all the account entries where redirectAccountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByRedirectAccountEntryId(
		long redirectAccountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRedirectAccountEntryId(redirectAccountEntryId, start,
			end);
	}

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByRedirectAccountEntryId(
		long redirectAccountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRedirectAccountEntryId(redirectAccountEntryId, start,
			end, orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where redirectAccountEntryId = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByRedirectAccountEntryId_First(
		long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRedirectAccountEntryId_First(redirectAccountEntryId,
			orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where redirectAccountEntryId = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByRedirectAccountEntryId_First(
		long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByRedirectAccountEntryId_First(redirectAccountEntryId,
			orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where redirectAccountEntryId = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByRedirectAccountEntryId_Last(
		long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRedirectAccountEntryId_Last(redirectAccountEntryId,
			orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where redirectAccountEntryId = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByRedirectAccountEntryId_Last(
		long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByRedirectAccountEntryId_Last(redirectAccountEntryId,
			orderByComparator);
	}

	/**
	* Returns the account entries before and after the current account entry in the ordered set where redirectAccountEntryId = &#63;.
	*
	* @param accountEntryId the primary key of the current account entry
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry[] findByRedirectAccountEntryId_PrevAndNext(
		long accountEntryId, long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRedirectAccountEntryId_PrevAndNext(accountEntryId,
			redirectAccountEntryId, orderByComparator);
	}

	/**
	* Returns all the account entries where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @return the matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByPartnerEntryId(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPartnerEntryId(partnerEntryId);
	}

	/**
	* Returns a range of all the account entries where partnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByPartnerEntryId(
		long partnerEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPartnerEntryId(partnerEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the account entries where partnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByPartnerEntryId(
		long partnerEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPartnerEntryId(partnerEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByPartnerEntryId_First(
		long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPartnerEntryId_First(partnerEntryId, orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByPartnerEntryId_First(
		long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPartnerEntryId_First(partnerEntryId,
			orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByPartnerEntryId_Last(
		long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPartnerEntryId_Last(partnerEntryId, orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByPartnerEntryId_Last(
		long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPartnerEntryId_Last(partnerEntryId, orderByComparator);
	}

	/**
	* Returns the account entries before and after the current account entry in the ordered set where partnerEntryId = &#63;.
	*
	* @param accountEntryId the primary key of the current account entry
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry[] findByPartnerEntryId_PrevAndNext(
		long accountEntryId, long partnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPartnerEntryId_PrevAndNext(accountEntryId,
			partnerEntryId, orderByComparator);
	}

	/**
	* Returns all the account entries where highestSupportResponseId = &#63;.
	*
	* @param highestSupportResponseId the highest support response ID
	* @return the matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByHighestSupportResponseId(
		long highestSupportResponseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByHighestSupportResponseId(highestSupportResponseId);
	}

	/**
	* Returns a range of all the account entries where highestSupportResponseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param highestSupportResponseId the highest support response ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByHighestSupportResponseId(
		long highestSupportResponseId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByHighestSupportResponseId(highestSupportResponseId,
			start, end);
	}

	/**
	* Returns an ordered range of all the account entries where highestSupportResponseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param highestSupportResponseId the highest support response ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByHighestSupportResponseId(
		long highestSupportResponseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByHighestSupportResponseId(highestSupportResponseId,
			start, end, orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where highestSupportResponseId = &#63;.
	*
	* @param highestSupportResponseId the highest support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByHighestSupportResponseId_First(
		long highestSupportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByHighestSupportResponseId_First(highestSupportResponseId,
			orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where highestSupportResponseId = &#63;.
	*
	* @param highestSupportResponseId the highest support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByHighestSupportResponseId_First(
		long highestSupportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByHighestSupportResponseId_First(highestSupportResponseId,
			orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where highestSupportResponseId = &#63;.
	*
	* @param highestSupportResponseId the highest support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByHighestSupportResponseId_Last(
		long highestSupportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByHighestSupportResponseId_Last(highestSupportResponseId,
			orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where highestSupportResponseId = &#63;.
	*
	* @param highestSupportResponseId the highest support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByHighestSupportResponseId_Last(
		long highestSupportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByHighestSupportResponseId_Last(highestSupportResponseId,
			orderByComparator);
	}

	/**
	* Returns the account entries before and after the current account entry in the ordered set where highestSupportResponseId = &#63;.
	*
	* @param accountEntryId the primary key of the current account entry
	* @param highestSupportResponseId the highest support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry[] findByHighestSupportResponseId_PrevAndNext(
		long accountEntryId, long highestSupportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByHighestSupportResponseId_PrevAndNext(accountEntryId,
			highestSupportResponseId, orderByComparator);
	}

	/**
	* Returns all the account entries where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByU_T(
		long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_T(userId, type);
	}

	/**
	* Returns a range of all the account entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByU_T(
		long userId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_T(userId, type, start, end);
	}

	/**
	* Returns an ordered range of all the account entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByU_T(
		long userId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_T(userId, type, start, end, orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByU_T_First(
		long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_T_First(userId, type, orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByU_T_First(
		long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_T_First(userId, type, orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByU_T_Last(
		long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_T_Last(userId, type, orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByU_T_Last(
		long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_T_Last(userId, type, orderByComparator);
	}

	/**
	* Returns the account entries before and after the current account entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param accountEntryId the primary key of the current account entry
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry[] findByU_T_PrevAndNext(
		long accountEntryId, long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_T_PrevAndNext(accountEntryId, userId, type,
			orderByComparator);
	}

	/**
	* Returns all the account entries where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @return the matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByRAEI_S(
		long redirectAccountEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByRAEI_S(redirectAccountEntryId, status);
	}

	/**
	* Returns a range of all the account entries where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByRAEI_S(
		long redirectAccountEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRAEI_S(redirectAccountEntryId, status, start, end);
	}

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByRAEI_S(
		long redirectAccountEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRAEI_S(redirectAccountEntryId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByRAEI_S_First(
		long redirectAccountEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRAEI_S_First(redirectAccountEntryId, status,
			orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByRAEI_S_First(
		long redirectAccountEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByRAEI_S_First(redirectAccountEntryId, status,
			orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByRAEI_S_Last(
		long redirectAccountEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRAEI_S_Last(redirectAccountEntryId, status,
			orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByRAEI_S_Last(
		long redirectAccountEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByRAEI_S_Last(redirectAccountEntryId, status,
			orderByComparator);
	}

	/**
	* Returns the account entries before and after the current account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* @param accountEntryId the primary key of the current account entry
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry[] findByRAEI_S_PrevAndNext(
		long accountEntryId, long redirectAccountEntryId, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRAEI_S_PrevAndNext(accountEntryId,
			redirectAccountEntryId, status, orderByComparator);
	}

	/**
	* Returns all the account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param statuses the statuses
	* @return the matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByRAEI_S(
		long redirectAccountEntryId, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByRAEI_S(redirectAccountEntryId, statuses);
	}

	/**
	* Returns a range of all the account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param statuses the statuses
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByRAEI_S(
		long redirectAccountEntryId, int[] statuses, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRAEI_S(redirectAccountEntryId, statuses, start, end);
	}

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param statuses the statuses
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByRAEI_S(
		long redirectAccountEntryId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRAEI_S(redirectAccountEntryId, statuses, start, end,
			orderByComparator);
	}

	/**
	* Returns all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @return the matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int type, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRAEI_NotT_S(redirectAccountEntryId, type, status);
	}

	/**
	* Returns a range of all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int type, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRAEI_NotT_S(redirectAccountEntryId, type, status,
			start, end);
	}

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int type, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRAEI_NotT_S(redirectAccountEntryId, type, status,
			start, end, orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByRAEI_NotT_S_First(
		long redirectAccountEntryId, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRAEI_NotT_S_First(redirectAccountEntryId, type,
			status, orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByRAEI_NotT_S_First(
		long redirectAccountEntryId, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByRAEI_NotT_S_First(redirectAccountEntryId, type,
			status, orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByRAEI_NotT_S_Last(
		long redirectAccountEntryId, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRAEI_NotT_S_Last(redirectAccountEntryId, type,
			status, orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByRAEI_NotT_S_Last(
		long redirectAccountEntryId, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByRAEI_NotT_S_Last(redirectAccountEntryId, type,
			status, orderByComparator);
	}

	/**
	* Returns the account entries before and after the current account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* @param accountEntryId the primary key of the current account entry
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry[] findByRAEI_NotT_S_PrevAndNext(
		long accountEntryId, long redirectAccountEntryId, int type, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRAEI_NotT_S_PrevAndNext(accountEntryId,
			redirectAccountEntryId, type, status, orderByComparator);
	}

	/**
	* Returns all the account entries where redirectAccountEntryId = &#63; and type &ne; any &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param types the types
	* @param statuses the statuses
	* @return the matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int[] types, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRAEI_NotT_S(redirectAccountEntryId, types, statuses);
	}

	/**
	* Returns a range of all the account entries where redirectAccountEntryId = &#63; and type &ne; any &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param types the types
	* @param statuses the statuses
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int[] types, int[] statuses, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRAEI_NotT_S(redirectAccountEntryId, types, statuses,
			start, end);
	}

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and type &ne; any &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param types the types
	* @param statuses the statuses
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int[] types, int[] statuses, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByRAEI_NotT_S(redirectAccountEntryId, types, statuses,
			start, end, orderByComparator);
	}

	/**
	* Returns all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @return the matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByN_C_RAEI(
		java.lang.String name, java.lang.String code,
		long redirectAccountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByN_C_RAEI(name, code, redirectAccountEntryId);
	}

	/**
	* Returns a range of all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByN_C_RAEI(
		java.lang.String name, java.lang.String code,
		long redirectAccountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByN_C_RAEI(name, code, redirectAccountEntryId, start,
			end);
	}

	/**
	* Returns an ordered range of all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findByN_C_RAEI(
		java.lang.String name, java.lang.String code,
		long redirectAccountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByN_C_RAEI(name, code, redirectAccountEntryId, start,
			end, orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByN_C_RAEI_First(
		java.lang.String name, java.lang.String code,
		long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByN_C_RAEI_First(name, code, redirectAccountEntryId,
			orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByN_C_RAEI_First(
		java.lang.String name, java.lang.String code,
		long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByN_C_RAEI_First(name, code, redirectAccountEntryId,
			orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry findByN_C_RAEI_Last(
		java.lang.String name, java.lang.String code,
		long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByN_C_RAEI_Last(name, code, redirectAccountEntryId,
			orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry fetchByN_C_RAEI_Last(
		java.lang.String name, java.lang.String code,
		long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByN_C_RAEI_Last(name, code, redirectAccountEntryId,
			orderByComparator);
	}

	/**
	* Returns the account entries before and after the current account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* @param accountEntryId the primary key of the current account entry
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry
	* @throws com.liferay.osb.NoSuchAccountEntryException if a account entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry[] findByN_C_RAEI_PrevAndNext(
		long accountEntryId, java.lang.String name, java.lang.String code,
		long redirectAccountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByN_C_RAEI_PrevAndNext(accountEntryId, name, code,
			redirectAccountEntryId, orderByComparator);
	}

	/**
	* Returns all the account entries.
	*
	* @return the account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the account entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the account entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the account entry where corpProjectId = &#63; from the database.
	*
	* @param corpProjectId the corp project ID
	* @return the account entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry removeByCorpProjectId(
		long corpProjectId)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByCorpProjectId(corpProjectId);
	}

	/**
	* Removes all the account entries where name = &#63; from the database.
	*
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByName(name);
	}

	/**
	* Removes the account entry where code = &#63; from the database.
	*
	* @param code the code
	* @return the account entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntry removeByCode(
		java.lang.String code)
		throws com.liferay.osb.NoSuchAccountEntryException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByCode(code);
	}

	/**
	* Removes all the account entries where redirectAccountEntryId = &#63; from the database.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByRedirectAccountEntryId(
		long redirectAccountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByRedirectAccountEntryId(redirectAccountEntryId);
	}

	/**
	* Removes all the account entries where partnerEntryId = &#63; from the database.
	*
	* @param partnerEntryId the partner entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByPartnerEntryId(long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByPartnerEntryId(partnerEntryId);
	}

	/**
	* Removes all the account entries where highestSupportResponseId = &#63; from the database.
	*
	* @param highestSupportResponseId the highest support response ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByHighestSupportResponseId(
		long highestSupportResponseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByHighestSupportResponseId(highestSupportResponseId);
	}

	/**
	* Removes all the account entries where userId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_T(long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_T(userId, type);
	}

	/**
	* Removes all the account entries where redirectAccountEntryId = &#63; and status = &#63; from the database.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByRAEI_S(long redirectAccountEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByRAEI_S(redirectAccountEntryId, status);
	}

	/**
	* Removes all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63; from the database.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByRAEI_NotT_S(long redirectAccountEntryId,
		int type, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByRAEI_NotT_S(redirectAccountEntryId, type, status);
	}

	/**
	* Removes all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63; from the database.
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByN_C_RAEI(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByN_C_RAEI(name, code, redirectAccountEntryId);
	}

	/**
	* Removes all the account entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of account entries where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @return the number of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCorpProjectId(long corpProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCorpProjectId(corpProjectId);
	}

	/**
	* Returns the number of account entries where name = &#63;.
	*
	* @param name the name
	* @return the number of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByName(name);
	}

	/**
	* Returns the number of account entries where code = &#63;.
	*
	* @param code the code
	* @return the number of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCode(java.lang.String code)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCode(code);
	}

	/**
	* Returns the number of account entries where redirectAccountEntryId = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @return the number of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByRedirectAccountEntryId(long redirectAccountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByRedirectAccountEntryId(redirectAccountEntryId);
	}

	/**
	* Returns the number of account entries where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @return the number of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPartnerEntryId(long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByPartnerEntryId(partnerEntryId);
	}

	/**
	* Returns the number of account entries where highestSupportResponseId = &#63;.
	*
	* @param highestSupportResponseId the highest support response ID
	* @return the number of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByHighestSupportResponseId(
		long highestSupportResponseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByHighestSupportResponseId(highestSupportResponseId);
	}

	/**
	* Returns the number of account entries where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the number of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_T(long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_T(userId, type);
	}

	/**
	* Returns the number of account entries where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @return the number of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByRAEI_S(long redirectAccountEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByRAEI_S(redirectAccountEntryId, status);
	}

	/**
	* Returns the number of account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param statuses the statuses
	* @return the number of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByRAEI_S(long redirectAccountEntryId, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByRAEI_S(redirectAccountEntryId, statuses);
	}

	/**
	* Returns the number of account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @return the number of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByRAEI_NotT_S(long redirectAccountEntryId, int type,
		int status) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByRAEI_NotT_S(redirectAccountEntryId, type, status);
	}

	/**
	* Returns the number of account entries where redirectAccountEntryId = &#63; and type &ne; any &#63; and status = any &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param types the types
	* @param statuses the statuses
	* @return the number of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByRAEI_NotT_S(long redirectAccountEntryId,
		int[] types, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByRAEI_NotT_S(redirectAccountEntryId, types, statuses);
	}

	/**
	* Returns the number of account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @return the number of matching account entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByN_C_RAEI(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByN_C_RAEI(name, code, redirectAccountEntryId);
	}

	/**
	* Returns the number of account entries.
	*
	* @return the number of account entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Returns all the support regions associated with the account entry.
	*
	* @param pk the primary key of the account entry
	* @return the support regions associated with the account entry
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getSupportRegions(pk);
	}

	/**
	* Returns a range of all the support regions associated with the account entry.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the account entry
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of support regions associated with the account entry
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getSupportRegions(pk, start, end);
	}

	/**
	* Returns an ordered range of all the support regions associated with the account entry.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the account entry
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support regions associated with the account entry
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
	* Returns the number of support regions associated with the account entry.
	*
	* @param pk the primary key of the account entry
	* @return the number of support regions associated with the account entry
	* @throws SystemException if a system exception occurred
	*/
	public static int getSupportRegionsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getSupportRegionsSize(pk);
	}

	/**
	* Returns <code>true</code> if the support region is associated with the account entry.
	*
	* @param pk the primary key of the account entry
	* @param supportRegionPK the primary key of the support region
	* @return <code>true</code> if the support region is associated with the account entry; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsSupportRegion(long pk, long supportRegionPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsSupportRegion(pk, supportRegionPK);
	}

	/**
	* Returns <code>true</code> if the account entry has any support regions associated with it.
	*
	* @param pk the primary key of the account entry to check for associations with support regions
	* @return <code>true</code> if the account entry has any support regions associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsSupportRegions(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsSupportRegions(pk);
	}

	/**
	* Adds an association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegionPK the primary key of the support region
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegion(long pk, long supportRegionPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addSupportRegion(pk, supportRegionPK);
	}

	/**
	* Adds an association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegion the support region
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addSupportRegion(pk, supportRegion);
	}

	/**
	* Adds an association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegionPKs the primary keys of the support regions
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegions(long pk, long[] supportRegionPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addSupportRegions(pk, supportRegionPKs);
	}

	/**
	* Adds an association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegions the support regions
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegions(long pk,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addSupportRegions(pk, supportRegions);
	}

	/**
	* Clears all associations between the account entry and its support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry to clear the associated support regions from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearSupportRegions(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearSupportRegions(pk);
	}

	/**
	* Removes the association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegionPK the primary key of the support region
	* @throws SystemException if a system exception occurred
	*/
	public static void removeSupportRegion(long pk, long supportRegionPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeSupportRegion(pk, supportRegionPK);
	}

	/**
	* Removes the association between the account entry and the support region. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegion the support region
	* @throws SystemException if a system exception occurred
	*/
	public static void removeSupportRegion(long pk,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeSupportRegion(pk, supportRegion);
	}

	/**
	* Removes the association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegionPKs the primary keys of the support regions
	* @throws SystemException if a system exception occurred
	*/
	public static void removeSupportRegions(long pk, long[] supportRegionPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeSupportRegions(pk, supportRegionPKs);
	}

	/**
	* Removes the association between the account entry and the support regions. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegions the support regions
	* @throws SystemException if a system exception occurred
	*/
	public static void removeSupportRegions(long pk,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeSupportRegions(pk, supportRegions);
	}

	/**
	* Sets the support regions associated with the account entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegionPKs the primary keys of the support regions to be associated with the account entry
	* @throws SystemException if a system exception occurred
	*/
	public static void setSupportRegions(long pk, long[] supportRegionPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setSupportRegions(pk, supportRegionPKs);
	}

	/**
	* Sets the support regions associated with the account entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportRegions the support regions to be associated with the account entry
	* @throws SystemException if a system exception occurred
	*/
	public static void setSupportRegions(long pk,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setSupportRegions(pk, supportRegions);
	}

	/**
	* Returns all the support teams associated with the account entry.
	*
	* @param pk the primary key of the account entry
	* @return the support teams associated with the account entry
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getSupportTeams(pk);
	}

	/**
	* Returns a range of all the support teams associated with the account entry.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the account entry
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of support teams associated with the account entry
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getSupportTeams(pk, start, end);
	}

	/**
	* Returns an ordered range of all the support teams associated with the account entry.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the account entry
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support teams associated with the account entry
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
	* Returns the number of support teams associated with the account entry.
	*
	* @param pk the primary key of the account entry
	* @return the number of support teams associated with the account entry
	* @throws SystemException if a system exception occurred
	*/
	public static int getSupportTeamsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getSupportTeamsSize(pk);
	}

	/**
	* Returns <code>true</code> if the support team is associated with the account entry.
	*
	* @param pk the primary key of the account entry
	* @param supportTeamPK the primary key of the support team
	* @return <code>true</code> if the support team is associated with the account entry; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsSupportTeam(long pk, long supportTeamPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsSupportTeam(pk, supportTeamPK);
	}

	/**
	* Returns <code>true</code> if the account entry has any support teams associated with it.
	*
	* @param pk the primary key of the account entry to check for associations with support teams
	* @return <code>true</code> if the account entry has any support teams associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsSupportTeams(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsSupportTeams(pk);
	}

	/**
	* Adds an association between the account entry and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeamPK the primary key of the support team
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportTeam(long pk, long supportTeamPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addSupportTeam(pk, supportTeamPK);
	}

	/**
	* Adds an association between the account entry and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeam the support team
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportTeam(long pk,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addSupportTeam(pk, supportTeam);
	}

	/**
	* Adds an association between the account entry and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeamPKs the primary keys of the support teams
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportTeams(long pk, long[] supportTeamPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addSupportTeams(pk, supportTeamPKs);
	}

	/**
	* Adds an association between the account entry and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeams the support teams
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportTeams(long pk,
		java.util.List<com.liferay.osb.model.SupportTeam> supportTeams)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addSupportTeams(pk, supportTeams);
	}

	/**
	* Clears all associations between the account entry and its support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry to clear the associated support teams from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearSupportTeams(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearSupportTeams(pk);
	}

	/**
	* Removes the association between the account entry and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeamPK the primary key of the support team
	* @throws SystemException if a system exception occurred
	*/
	public static void removeSupportTeam(long pk, long supportTeamPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeSupportTeam(pk, supportTeamPK);
	}

	/**
	* Removes the association between the account entry and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeam the support team
	* @throws SystemException if a system exception occurred
	*/
	public static void removeSupportTeam(long pk,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeSupportTeam(pk, supportTeam);
	}

	/**
	* Removes the association between the account entry and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeamPKs the primary keys of the support teams
	* @throws SystemException if a system exception occurred
	*/
	public static void removeSupportTeams(long pk, long[] supportTeamPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeSupportTeams(pk, supportTeamPKs);
	}

	/**
	* Removes the association between the account entry and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeams the support teams
	* @throws SystemException if a system exception occurred
	*/
	public static void removeSupportTeams(long pk,
		java.util.List<com.liferay.osb.model.SupportTeam> supportTeams)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeSupportTeams(pk, supportTeams);
	}

	/**
	* Sets the support teams associated with the account entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeamPKs the primary keys of the support teams to be associated with the account entry
	* @throws SystemException if a system exception occurred
	*/
	public static void setSupportTeams(long pk, long[] supportTeamPKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setSupportTeams(pk, supportTeamPKs);
	}

	/**
	* Sets the support teams associated with the account entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeams the support teams to be associated with the account entry
	* @throws SystemException if a system exception occurred
	*/
	public static void setSupportTeams(long pk,
		java.util.List<com.liferay.osb.model.SupportTeam> supportTeams)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setSupportTeams(pk, supportTeams);
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

	/**
	 * @deprecated
	 */
	public void setPersistence(AccountEntryPersistence persistence) {
	}

	private static AccountEntryPersistence _persistence;
}