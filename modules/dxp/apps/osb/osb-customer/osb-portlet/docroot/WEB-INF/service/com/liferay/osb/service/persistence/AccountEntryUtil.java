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
	* Returns the account entry where corpProjectUuid = &#63; or throws a {@link NoSuchAccountEntryException} if it could not be found.
	*
	* @param corpProjectUuid the corp project uuid
	* @return the matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByCorpProjectUuid(
		java.lang.String corpProjectUuid)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence().findByCorpProjectUuid(corpProjectUuid);
	}

	/**
	* Returns the account entry where corpProjectUuid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param corpProjectUuid the corp project uuid
	* @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public static AccountEntry fetchByCorpProjectUuid(
		java.lang.String corpProjectUuid) {
		return getPersistence().fetchByCorpProjectUuid(corpProjectUuid);
	}

	/**
	* Returns the account entry where corpProjectUuid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param corpProjectUuid the corp project uuid
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public static AccountEntry fetchByCorpProjectUuid(
		java.lang.String corpProjectUuid, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByCorpProjectUuid(corpProjectUuid, retrieveFromCache);
	}

	/**
	* Removes the account entry where corpProjectUuid = &#63; from the database.
	*
	* @param corpProjectUuid the corp project uuid
	* @return the account entry that was removed
	*/
	public static AccountEntry removeByCorpProjectUuid(
		java.lang.String corpProjectUuid)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence().removeByCorpProjectUuid(corpProjectUuid);
	}

	/**
	* Returns the number of account entries where corpProjectUuid = &#63;.
	*
	* @param corpProjectUuid the corp project uuid
	* @return the number of matching account entries
	*/
	public static int countByCorpProjectUuid(java.lang.String corpProjectUuid) {
		return getPersistence().countByCorpProjectUuid(corpProjectUuid);
	}

	/**
	* Returns the account entry where corpProjectId = &#63; or throws a {@link NoSuchAccountEntryException} if it could not be found.
	*
	* @param corpProjectId the corp project ID
	* @return the matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByCorpProjectId(long corpProjectId)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence().findByCorpProjectId(corpProjectId);
	}

	/**
	* Returns the account entry where corpProjectId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param corpProjectId the corp project ID
	* @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public static AccountEntry fetchByCorpProjectId(long corpProjectId) {
		return getPersistence().fetchByCorpProjectId(corpProjectId);
	}

	/**
	* Returns the account entry where corpProjectId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param corpProjectId the corp project ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public static AccountEntry fetchByCorpProjectId(long corpProjectId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByCorpProjectId(corpProjectId, retrieveFromCache);
	}

	/**
	* Removes the account entry where corpProjectId = &#63; from the database.
	*
	* @param corpProjectId the corp project ID
	* @return the account entry that was removed
	*/
	public static AccountEntry removeByCorpProjectId(long corpProjectId)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence().removeByCorpProjectId(corpProjectId);
	}

	/**
	* Returns the number of account entries where corpProjectId = &#63;.
	*
	* @param corpProjectId the corp project ID
	* @return the number of matching account entries
	*/
	public static int countByCorpProjectId(long corpProjectId) {
		return getPersistence().countByCorpProjectId(corpProjectId);
	}

	/**
	* Returns all the account entries where name = &#63;.
	*
	* @param name the name
	* @return the matching account entries
	*/
	public static List<AccountEntry> findByName(java.lang.String name) {
		return getPersistence().findByName(name);
	}

	/**
	* Returns a range of all the account entries where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public static List<AccountEntry> findByName(java.lang.String name,
		int start, int end) {
		return getPersistence().findByName(name, start, end);
	}

	/**
	* Returns an ordered range of all the account entries where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByName(java.lang.String name,
		int start, int end, OrderByComparator<AccountEntry> orderByComparator) {
		return getPersistence().findByName(name, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account entries where name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByName(java.lang.String name,
		int start, int end, OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByName(name, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first account entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByName_First(java.lang.String name,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence().findByName_First(name, orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public static AccountEntry fetchByName_First(java.lang.String name,
		OrderByComparator<AccountEntry> orderByComparator) {
		return getPersistence().fetchByName_First(name, orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByName_Last(java.lang.String name,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence().findByName_Last(name, orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where name = &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public static AccountEntry fetchByName_Last(java.lang.String name,
		OrderByComparator<AccountEntry> orderByComparator) {
		return getPersistence().fetchByName_Last(name, orderByComparator);
	}

	/**
	* Returns the account entries before and after the current account entry in the ordered set where name = &#63;.
	*
	* @param accountEntryId the primary key of the current account entry
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public static AccountEntry[] findByName_PrevAndNext(long accountEntryId,
		java.lang.String name, OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence()
				   .findByName_PrevAndNext(accountEntryId, name,
			orderByComparator);
	}

	/**
	* Removes all the account entries where name = &#63; from the database.
	*
	* @param name the name
	*/
	public static void removeByName(java.lang.String name) {
		getPersistence().removeByName(name);
	}

	/**
	* Returns the number of account entries where name = &#63;.
	*
	* @param name the name
	* @return the number of matching account entries
	*/
	public static int countByName(java.lang.String name) {
		return getPersistence().countByName(name);
	}

	/**
	* Returns the account entry where code = &#63; or throws a {@link NoSuchAccountEntryException} if it could not be found.
	*
	* @param code the code
	* @return the matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByCode(java.lang.String code)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence().findByCode(code);
	}

	/**
	* Returns the account entry where code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param code the code
	* @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public static AccountEntry fetchByCode(java.lang.String code) {
		return getPersistence().fetchByCode(code);
	}

	/**
	* Returns the account entry where code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param code the code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public static AccountEntry fetchByCode(java.lang.String code,
		boolean retrieveFromCache) {
		return getPersistence().fetchByCode(code, retrieveFromCache);
	}

	/**
	* Removes the account entry where code = &#63; from the database.
	*
	* @param code the code
	* @return the account entry that was removed
	*/
	public static AccountEntry removeByCode(java.lang.String code)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence().removeByCode(code);
	}

	/**
	* Returns the number of account entries where code = &#63;.
	*
	* @param code the code
	* @return the number of matching account entries
	*/
	public static int countByCode(java.lang.String code) {
		return getPersistence().countByCode(code);
	}

	/**
	* Returns all the account entries where redirectAccountEntryId = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @return the matching account entries
	*/
	public static List<AccountEntry> findByRedirectAccountEntryId(
		long redirectAccountEntryId) {
		return getPersistence()
				   .findByRedirectAccountEntryId(redirectAccountEntryId);
	}

	/**
	* Returns a range of all the account entries where redirectAccountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public static List<AccountEntry> findByRedirectAccountEntryId(
		long redirectAccountEntryId, int start, int end) {
		return getPersistence()
				   .findByRedirectAccountEntryId(redirectAccountEntryId, start,
			end);
	}

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByRedirectAccountEntryId(
		long redirectAccountEntryId, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {
		return getPersistence()
				   .findByRedirectAccountEntryId(redirectAccountEntryId, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByRedirectAccountEntryId(
		long redirectAccountEntryId, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRedirectAccountEntryId(redirectAccountEntryId, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first account entry in the ordered set where redirectAccountEntryId = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByRedirectAccountEntryId_First(
		long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
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
	*/
	public static AccountEntry fetchByRedirectAccountEntryId_First(
		long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator) {
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
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByRedirectAccountEntryId_Last(
		long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
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
	*/
	public static AccountEntry fetchByRedirectAccountEntryId_Last(
		long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator) {
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
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public static AccountEntry[] findByRedirectAccountEntryId_PrevAndNext(
		long accountEntryId, long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence()
				   .findByRedirectAccountEntryId_PrevAndNext(accountEntryId,
			redirectAccountEntryId, orderByComparator);
	}

	/**
	* Removes all the account entries where redirectAccountEntryId = &#63; from the database.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	*/
	public static void removeByRedirectAccountEntryId(
		long redirectAccountEntryId) {
		getPersistence().removeByRedirectAccountEntryId(redirectAccountEntryId);
	}

	/**
	* Returns the number of account entries where redirectAccountEntryId = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @return the number of matching account entries
	*/
	public static int countByRedirectAccountEntryId(long redirectAccountEntryId) {
		return getPersistence()
				   .countByRedirectAccountEntryId(redirectAccountEntryId);
	}

	/**
	* Returns all the account entries where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @return the matching account entries
	*/
	public static List<AccountEntry> findByPartnerEntryId(long partnerEntryId) {
		return getPersistence().findByPartnerEntryId(partnerEntryId);
	}

	/**
	* Returns a range of all the account entries where partnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public static List<AccountEntry> findByPartnerEntryId(long partnerEntryId,
		int start, int end) {
		return getPersistence().findByPartnerEntryId(partnerEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the account entries where partnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByPartnerEntryId(long partnerEntryId,
		int start, int end, OrderByComparator<AccountEntry> orderByComparator) {
		return getPersistence()
				   .findByPartnerEntryId(partnerEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the account entries where partnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param partnerEntryId the partner entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByPartnerEntryId(long partnerEntryId,
		int start, int end, OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPartnerEntryId(partnerEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first account entry in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByPartnerEntryId_First(long partnerEntryId,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence()
				   .findByPartnerEntryId_First(partnerEntryId, orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public static AccountEntry fetchByPartnerEntryId_First(
		long partnerEntryId, OrderByComparator<AccountEntry> orderByComparator) {
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
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByPartnerEntryId_Last(long partnerEntryId,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence()
				   .findByPartnerEntryId_Last(partnerEntryId, orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public static AccountEntry fetchByPartnerEntryId_Last(long partnerEntryId,
		OrderByComparator<AccountEntry> orderByComparator) {
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
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public static AccountEntry[] findByPartnerEntryId_PrevAndNext(
		long accountEntryId, long partnerEntryId,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence()
				   .findByPartnerEntryId_PrevAndNext(accountEntryId,
			partnerEntryId, orderByComparator);
	}

	/**
	* Removes all the account entries where partnerEntryId = &#63; from the database.
	*
	* @param partnerEntryId the partner entry ID
	*/
	public static void removeByPartnerEntryId(long partnerEntryId) {
		getPersistence().removeByPartnerEntryId(partnerEntryId);
	}

	/**
	* Returns the number of account entries where partnerEntryId = &#63;.
	*
	* @param partnerEntryId the partner entry ID
	* @return the number of matching account entries
	*/
	public static int countByPartnerEntryId(long partnerEntryId) {
		return getPersistence().countByPartnerEntryId(partnerEntryId);
	}

	/**
	* Returns all the account entries where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the matching account entries
	*/
	public static List<AccountEntry> findByU_T(long userId, int type) {
		return getPersistence().findByU_T(userId, type);
	}

	/**
	* Returns a range of all the account entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public static List<AccountEntry> findByU_T(long userId, int type,
		int start, int end) {
		return getPersistence().findByU_T(userId, type, start, end);
	}

	/**
	* Returns an ordered range of all the account entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByU_T(long userId, int type,
		int start, int end, OrderByComparator<AccountEntry> orderByComparator) {
		return getPersistence()
				   .findByU_T(userId, type, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param type the type
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByU_T(long userId, int type,
		int start, int end, OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_T(userId, type, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first account entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByU_T_First(long userId, int type,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence().findByU_T_First(userId, type, orderByComparator);
	}

	/**
	* Returns the first account entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public static AccountEntry fetchByU_T_First(long userId, int type,
		OrderByComparator<AccountEntry> orderByComparator) {
		return getPersistence().fetchByU_T_First(userId, type, orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByU_T_Last(long userId, int type,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence().findByU_T_Last(userId, type, orderByComparator);
	}

	/**
	* Returns the last account entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	*/
	public static AccountEntry fetchByU_T_Last(long userId, int type,
		OrderByComparator<AccountEntry> orderByComparator) {
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
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public static AccountEntry[] findByU_T_PrevAndNext(long accountEntryId,
		long userId, int type, OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence()
				   .findByU_T_PrevAndNext(accountEntryId, userId, type,
			orderByComparator);
	}

	/**
	* Removes all the account entries where userId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID
	* @param type the type
	*/
	public static void removeByU_T(long userId, int type) {
		getPersistence().removeByU_T(userId, type);
	}

	/**
	* Returns the number of account entries where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID
	* @param type the type
	* @return the number of matching account entries
	*/
	public static int countByU_T(long userId, int type) {
		return getPersistence().countByU_T(userId, type);
	}

	/**
	* Returns all the account entries where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @return the matching account entries
	*/
	public static List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int status) {
		return getPersistence().findByRAEI_S(redirectAccountEntryId, status);
	}

	/**
	* Returns a range of all the account entries where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public static List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int status, int start, int end) {
		return getPersistence()
				   .findByRAEI_S(redirectAccountEntryId, status, start, end);
	}

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int status, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {
		return getPersistence()
				   .findByRAEI_S(redirectAccountEntryId, status, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int status, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRAEI_S(redirectAccountEntryId, status, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first account entry in the ordered set where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByRAEI_S_First(long redirectAccountEntryId,
		int status, OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
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
	*/
	public static AccountEntry fetchByRAEI_S_First(
		long redirectAccountEntryId, int status,
		OrderByComparator<AccountEntry> orderByComparator) {
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
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByRAEI_S_Last(long redirectAccountEntryId,
		int status, OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
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
	*/
	public static AccountEntry fetchByRAEI_S_Last(long redirectAccountEntryId,
		int status, OrderByComparator<AccountEntry> orderByComparator) {
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
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public static AccountEntry[] findByRAEI_S_PrevAndNext(long accountEntryId,
		long redirectAccountEntryId, int status,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence()
				   .findByRAEI_S_PrevAndNext(accountEntryId,
			redirectAccountEntryId, status, orderByComparator);
	}

	/**
	* Returns all the account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param statuses the statuses
	* @return the matching account entries
	*/
	public static List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int[] statuses) {
		return getPersistence().findByRAEI_S(redirectAccountEntryId, statuses);
	}

	/**
	* Returns a range of all the account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param statuses the statuses
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public static List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int[] statuses, int start, int end) {
		return getPersistence()
				   .findByRAEI_S(redirectAccountEntryId, statuses, start, end);
	}

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param statuses the statuses
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int[] statuses, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {
		return getPersistence()
				   .findByRAEI_S(redirectAccountEntryId, statuses, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByRAEI_S(long redirectAccountEntryId,
		int[] statuses, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRAEI_S(redirectAccountEntryId, statuses, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the account entries where redirectAccountEntryId = &#63; and status = &#63; from the database.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	*/
	public static void removeByRAEI_S(long redirectAccountEntryId, int status) {
		getPersistence().removeByRAEI_S(redirectAccountEntryId, status);
	}

	/**
	* Returns the number of account entries where redirectAccountEntryId = &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param status the status
	* @return the number of matching account entries
	*/
	public static int countByRAEI_S(long redirectAccountEntryId, int status) {
		return getPersistence().countByRAEI_S(redirectAccountEntryId, status);
	}

	/**
	* Returns the number of account entries where redirectAccountEntryId = &#63; and status = any &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param statuses the statuses
	* @return the number of matching account entries
	*/
	public static int countByRAEI_S(long redirectAccountEntryId, int[] statuses) {
		return getPersistence().countByRAEI_S(redirectAccountEntryId, statuses);
	}

	/**
	* Returns all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @return the matching account entries
	*/
	public static List<AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int type, int status) {
		return getPersistence()
				   .findByRAEI_NotT_S(redirectAccountEntryId, type, status);
	}

	/**
	* Returns a range of all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public static List<AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int type, int status, int start, int end) {
		return getPersistence()
				   .findByRAEI_NotT_S(redirectAccountEntryId, type, status,
			start, end);
	}

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int type, int status, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {
		return getPersistence()
				   .findByRAEI_NotT_S(redirectAccountEntryId, type, status,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int type, int status, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRAEI_NotT_S(redirectAccountEntryId, type, status,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first account entry in the ordered set where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByRAEI_NotT_S_First(
		long redirectAccountEntryId, int type, int status,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
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
	*/
	public static AccountEntry fetchByRAEI_NotT_S_First(
		long redirectAccountEntryId, int type, int status,
		OrderByComparator<AccountEntry> orderByComparator) {
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
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByRAEI_NotT_S_Last(
		long redirectAccountEntryId, int type, int status,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
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
	*/
	public static AccountEntry fetchByRAEI_NotT_S_Last(
		long redirectAccountEntryId, int type, int status,
		OrderByComparator<AccountEntry> orderByComparator) {
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
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public static AccountEntry[] findByRAEI_NotT_S_PrevAndNext(
		long accountEntryId, long redirectAccountEntryId, int type, int status,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence()
				   .findByRAEI_NotT_S_PrevAndNext(accountEntryId,
			redirectAccountEntryId, type, status, orderByComparator);
	}

	/**
	* Returns all the account entries where redirectAccountEntryId = &#63; and type &ne; all &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param types the types
	* @param statuses the statuses
	* @return the matching account entries
	*/
	public static List<AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int[] types, int[] statuses) {
		return getPersistence()
				   .findByRAEI_NotT_S(redirectAccountEntryId, types, statuses);
	}

	/**
	* Returns a range of all the account entries where redirectAccountEntryId = &#63; and type &ne; all &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param types the types
	* @param statuses the statuses
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public static List<AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int[] types, int[] statuses, int start,
		int end) {
		return getPersistence()
				   .findByRAEI_NotT_S(redirectAccountEntryId, types, statuses,
			start, end);
	}

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and type &ne; all &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param types the types
	* @param statuses the statuses
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int[] types, int[] statuses, int start,
		int end, OrderByComparator<AccountEntry> orderByComparator) {
		return getPersistence()
				   .findByRAEI_NotT_S(redirectAccountEntryId, types, statuses,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByRAEI_NotT_S(
		long redirectAccountEntryId, int[] types, int[] statuses, int start,
		int end, OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByRAEI_NotT_S(redirectAccountEntryId, types, statuses,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63; from the database.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	*/
	public static void removeByRAEI_NotT_S(long redirectAccountEntryId,
		int type, int status) {
		getPersistence()
			.removeByRAEI_NotT_S(redirectAccountEntryId, type, status);
	}

	/**
	* Returns the number of account entries where redirectAccountEntryId = &#63; and type &ne; &#63; and status = &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param type the type
	* @param status the status
	* @return the number of matching account entries
	*/
	public static int countByRAEI_NotT_S(long redirectAccountEntryId, int type,
		int status) {
		return getPersistence()
				   .countByRAEI_NotT_S(redirectAccountEntryId, type, status);
	}

	/**
	* Returns the number of account entries where redirectAccountEntryId = &#63; and type &ne; all &#63; and status = any &#63;.
	*
	* @param redirectAccountEntryId the redirect account entry ID
	* @param types the types
	* @param statuses the statuses
	* @return the number of matching account entries
	*/
	public static int countByRAEI_NotT_S(long redirectAccountEntryId,
		int[] types, int[] statuses) {
		return getPersistence()
				   .countByRAEI_NotT_S(redirectAccountEntryId, types, statuses);
	}

	/**
	* Returns all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @return the matching account entries
	*/
	public static List<AccountEntry> findByN_C_RAEI(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId) {
		return getPersistence()
				   .findByN_C_RAEI(name, code, redirectAccountEntryId);
	}

	/**
	* Returns a range of all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of matching account entries
	*/
	public static List<AccountEntry> findByN_C_RAEI(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId, int start, int end) {
		return getPersistence()
				   .findByN_C_RAEI(name, code, redirectAccountEntryId, start,
			end);
	}

	/**
	* Returns an ordered range of all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByN_C_RAEI(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {
		return getPersistence()
				   .findByN_C_RAEI(name, code, redirectAccountEntryId, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entries
	*/
	public static List<AccountEntry> findByN_C_RAEI(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByN_C_RAEI(name, code, redirectAccountEntryId, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first account entry in the ordered set where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByN_C_RAEI_First(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
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
	*/
	public static AccountEntry fetchByN_C_RAEI_First(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator) {
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
	* @throws NoSuchAccountEntryException if a matching account entry could not be found
	*/
	public static AccountEntry findByN_C_RAEI_Last(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
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
	*/
	public static AccountEntry fetchByN_C_RAEI_Last(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator) {
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
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public static AccountEntry[] findByN_C_RAEI_PrevAndNext(
		long accountEntryId, java.lang.String name, java.lang.String code,
		long redirectAccountEntryId,
		OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountEntryException {
		return getPersistence()
				   .findByN_C_RAEI_PrevAndNext(accountEntryId, name, code,
			redirectAccountEntryId, orderByComparator);
	}

	/**
	* Removes all the account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63; from the database.
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	*/
	public static void removeByN_C_RAEI(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId) {
		getPersistence().removeByN_C_RAEI(name, code, redirectAccountEntryId);
	}

	/**
	* Returns the number of account entries where name LIKE &#63; and code LIKE &#63; and redirectAccountEntryId = &#63;.
	*
	* @param name the name
	* @param code the code
	* @param redirectAccountEntryId the redirect account entry ID
	* @return the number of matching account entries
	*/
	public static int countByN_C_RAEI(java.lang.String name,
		java.lang.String code, long redirectAccountEntryId) {
		return getPersistence()
				   .countByN_C_RAEI(name, code, redirectAccountEntryId);
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

	/**
	* Returns the primaryKeys of support teams associated with the account entry.
	*
	* @param pk the primary key of the account entry
	* @return long[] of the primaryKeys of support teams associated with the account entry
	*/
	public static long[] getSupportTeamPrimaryKeys(long pk) {
		return getPersistence().getSupportTeamPrimaryKeys(pk);
	}

	/**
	* Returns all the support teams associated with the account entry.
	*
	* @param pk the primary key of the account entry
	* @return the support teams associated with the account entry
	*/
	public static List<com.liferay.osb.model.SupportTeam> getSupportTeams(
		long pk) {
		return getPersistence().getSupportTeams(pk);
	}

	/**
	* Returns a range of all the support teams associated with the account entry.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the account entry
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of support teams associated with the account entry
	*/
	public static List<com.liferay.osb.model.SupportTeam> getSupportTeams(
		long pk, int start, int end) {
		return getPersistence().getSupportTeams(pk, start, end);
	}

	/**
	* Returns an ordered range of all the support teams associated with the account entry.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the account entry
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support teams associated with the account entry
	*/
	public static List<com.liferay.osb.model.SupportTeam> getSupportTeams(
		long pk, int start, int end,
		OrderByComparator<com.liferay.osb.model.SupportTeam> orderByComparator) {
		return getPersistence()
				   .getSupportTeams(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of support teams associated with the account entry.
	*
	* @param pk the primary key of the account entry
	* @return the number of support teams associated with the account entry
	*/
	public static int getSupportTeamsSize(long pk) {
		return getPersistence().getSupportTeamsSize(pk);
	}

	/**
	* Returns <code>true</code> if the support team is associated with the account entry.
	*
	* @param pk the primary key of the account entry
	* @param supportTeamPK the primary key of the support team
	* @return <code>true</code> if the support team is associated with the account entry; <code>false</code> otherwise
	*/
	public static boolean containsSupportTeam(long pk, long supportTeamPK) {
		return getPersistence().containsSupportTeam(pk, supportTeamPK);
	}

	/**
	* Returns <code>true</code> if the account entry has any support teams associated with it.
	*
	* @param pk the primary key of the account entry to check for associations with support teams
	* @return <code>true</code> if the account entry has any support teams associated with it; <code>false</code> otherwise
	*/
	public static boolean containsSupportTeams(long pk) {
		return getPersistence().containsSupportTeams(pk);
	}

	/**
	* Adds an association between the account entry and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeamPK the primary key of the support team
	*/
	public static void addSupportTeam(long pk, long supportTeamPK) {
		getPersistence().addSupportTeam(pk, supportTeamPK);
	}

	/**
	* Adds an association between the account entry and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeam the support team
	*/
	public static void addSupportTeam(long pk,
		com.liferay.osb.model.SupportTeam supportTeam) {
		getPersistence().addSupportTeam(pk, supportTeam);
	}

	/**
	* Adds an association between the account entry and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeamPKs the primary keys of the support teams
	*/
	public static void addSupportTeams(long pk, long[] supportTeamPKs) {
		getPersistence().addSupportTeams(pk, supportTeamPKs);
	}

	/**
	* Adds an association between the account entry and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeams the support teams
	*/
	public static void addSupportTeams(long pk,
		List<com.liferay.osb.model.SupportTeam> supportTeams) {
		getPersistence().addSupportTeams(pk, supportTeams);
	}

	/**
	* Clears all associations between the account entry and its support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry to clear the associated support teams from
	*/
	public static void clearSupportTeams(long pk) {
		getPersistence().clearSupportTeams(pk);
	}

	/**
	* Removes the association between the account entry and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeamPK the primary key of the support team
	*/
	public static void removeSupportTeam(long pk, long supportTeamPK) {
		getPersistence().removeSupportTeam(pk, supportTeamPK);
	}

	/**
	* Removes the association between the account entry and the support team. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeam the support team
	*/
	public static void removeSupportTeam(long pk,
		com.liferay.osb.model.SupportTeam supportTeam) {
		getPersistence().removeSupportTeam(pk, supportTeam);
	}

	/**
	* Removes the association between the account entry and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeamPKs the primary keys of the support teams
	*/
	public static void removeSupportTeams(long pk, long[] supportTeamPKs) {
		getPersistence().removeSupportTeams(pk, supportTeamPKs);
	}

	/**
	* Removes the association between the account entry and the support teams. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeams the support teams
	*/
	public static void removeSupportTeams(long pk,
		List<com.liferay.osb.model.SupportTeam> supportTeams) {
		getPersistence().removeSupportTeams(pk, supportTeams);
	}

	/**
	* Sets the support teams associated with the account entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeamPKs the primary keys of the support teams to be associated with the account entry
	*/
	public static void setSupportTeams(long pk, long[] supportTeamPKs) {
		getPersistence().setSupportTeams(pk, supportTeamPKs);
	}

	/**
	* Sets the support teams associated with the account entry, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the account entry
	* @param supportTeams the support teams to be associated with the account entry
	*/
	public static void setSupportTeams(long pk,
		List<com.liferay.osb.model.SupportTeam> supportTeams) {
		getPersistence().setSupportTeams(pk, supportTeams);
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