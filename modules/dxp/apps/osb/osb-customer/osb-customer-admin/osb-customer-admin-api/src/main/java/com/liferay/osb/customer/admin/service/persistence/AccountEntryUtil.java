/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.admin.service.persistence;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the account entry service. This utility wraps <code>com.liferay.osb.customer.admin.service.persistence.impl.AccountEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryPersistence
 * @generated
 */
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
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, AccountEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
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

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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
	public static AccountEntry update(
		AccountEntry accountEntry, ServiceContext serviceContext) {

		return getPersistence().update(accountEntry, serviceContext);
	}

	/**
	 * Returns the account entry where koroneikiAccountKey = &#63; or throws a <code>NoSuchAccountEntryException</code> if it could not be found.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @return the matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	public static AccountEntry findByKoroneikiAccountKey(
			String koroneikiAccountKey)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEntryException {

		return getPersistence().findByKoroneikiAccountKey(koroneikiAccountKey);
	}

	/**
	 * Returns the account entry where koroneikiAccountKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	public static AccountEntry fetchByKoroneikiAccountKey(
		String koroneikiAccountKey) {

		return getPersistence().fetchByKoroneikiAccountKey(koroneikiAccountKey);
	}

	/**
	 * Returns the account entry where koroneikiAccountKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	public static AccountEntry fetchByKoroneikiAccountKey(
		String koroneikiAccountKey, boolean useFinderCache) {

		return getPersistence().fetchByKoroneikiAccountKey(
			koroneikiAccountKey, useFinderCache);
	}

	/**
	 * Removes the account entry where koroneikiAccountKey = &#63; from the database.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @return the account entry that was removed
	 */
	public static AccountEntry removeByKoroneikiAccountKey(
			String koroneikiAccountKey)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEntryException {

		return getPersistence().removeByKoroneikiAccountKey(
			koroneikiAccountKey);
	}

	/**
	 * Returns the number of account entries where koroneikiAccountKey = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @return the number of matching account entries
	 */
	public static int countByKoroneikiAccountKey(String koroneikiAccountKey) {
		return getPersistence().countByKoroneikiAccountKey(koroneikiAccountKey);
	}

	/**
	 * Returns all the account entries where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the matching account entries
	 */
	public static List<AccountEntry> findByDossieraAccountKey(
		String dossieraAccountKey) {

		return getPersistence().findByDossieraAccountKey(dossieraAccountKey);
	}

	/**
	 * Returns a range of all the account entries where dossieraAccountKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 */
	public static List<AccountEntry> findByDossieraAccountKey(
		String dossieraAccountKey, int start, int end) {

		return getPersistence().findByDossieraAccountKey(
			dossieraAccountKey, start, end);
	}

	/**
	 * Returns an ordered range of all the account entries where dossieraAccountKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 */
	public static List<AccountEntry> findByDossieraAccountKey(
		String dossieraAccountKey, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {

		return getPersistence().findByDossieraAccountKey(
			dossieraAccountKey, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the account entries where dossieraAccountKey = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account entries
	 */
	public static List<AccountEntry> findByDossieraAccountKey(
		String dossieraAccountKey, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByDossieraAccountKey(
			dossieraAccountKey, start, end, orderByComparator, useFinderCache);
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
			String dossieraAccountKey,
			OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEntryException {

		return getPersistence().findByDossieraAccountKey_First(
			dossieraAccountKey, orderByComparator);
	}

	/**
	 * Returns the first account entry in the ordered set where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	public static AccountEntry fetchByDossieraAccountKey_First(
		String dossieraAccountKey,
		OrderByComparator<AccountEntry> orderByComparator) {

		return getPersistence().fetchByDossieraAccountKey_First(
			dossieraAccountKey, orderByComparator);
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
			String dossieraAccountKey,
			OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEntryException {

		return getPersistence().findByDossieraAccountKey_Last(
			dossieraAccountKey, orderByComparator);
	}

	/**
	 * Returns the last account entry in the ordered set where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	public static AccountEntry fetchByDossieraAccountKey_Last(
		String dossieraAccountKey,
		OrderByComparator<AccountEntry> orderByComparator) {

		return getPersistence().fetchByDossieraAccountKey_Last(
			dossieraAccountKey, orderByComparator);
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
			long accountEntryId, String dossieraAccountKey,
			OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEntryException {

		return getPersistence().findByDossieraAccountKey_PrevAndNext(
			accountEntryId, dossieraAccountKey, orderByComparator);
	}

	/**
	 * Removes all the account entries where dossieraAccountKey = &#63; from the database.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 */
	public static void removeByDossieraAccountKey(String dossieraAccountKey) {
		getPersistence().removeByDossieraAccountKey(dossieraAccountKey);
	}

	/**
	 * Returns the number of account entries where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the number of matching account entries
	 */
	public static int countByDossieraAccountKey(String dossieraAccountKey) {
		return getPersistence().countByDossieraAccountKey(dossieraAccountKey);
	}

	/**
	 * Returns the account entry where corpProjectUuid = &#63; or throws a <code>NoSuchAccountEntryException</code> if it could not be found.
	 *
	 * @param corpProjectUuid the corp project uuid
	 * @return the matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	public static AccountEntry findByCorpProjectUuid(String corpProjectUuid)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEntryException {

		return getPersistence().findByCorpProjectUuid(corpProjectUuid);
	}

	/**
	 * Returns the account entry where corpProjectUuid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param corpProjectUuid the corp project uuid
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	public static AccountEntry fetchByCorpProjectUuid(String corpProjectUuid) {
		return getPersistence().fetchByCorpProjectUuid(corpProjectUuid);
	}

	/**
	 * Returns the account entry where corpProjectUuid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param corpProjectUuid the corp project uuid
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	public static AccountEntry fetchByCorpProjectUuid(
		String corpProjectUuid, boolean useFinderCache) {

		return getPersistence().fetchByCorpProjectUuid(
			corpProjectUuid, useFinderCache);
	}

	/**
	 * Removes the account entry where corpProjectUuid = &#63; from the database.
	 *
	 * @param corpProjectUuid the corp project uuid
	 * @return the account entry that was removed
	 */
	public static AccountEntry removeByCorpProjectUuid(String corpProjectUuid)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEntryException {

		return getPersistence().removeByCorpProjectUuid(corpProjectUuid);
	}

	/**
	 * Returns the number of account entries where corpProjectUuid = &#63;.
	 *
	 * @param corpProjectUuid the corp project uuid
	 * @return the number of matching account entries
	 */
	public static int countByCorpProjectUuid(String corpProjectUuid) {
		return getPersistence().countByCorpProjectUuid(corpProjectUuid);
	}

	/**
	 * Returns all the account entries where name LIKE &#63; and code LIKE &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @return the matching account entries
	 */
	public static List<AccountEntry> findByN_C(String name, String code) {
		return getPersistence().findByN_C(name, code);
	}

	/**
	 * Returns a range of all the account entries where name LIKE &#63; and code LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEntryModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param code the code
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @return the range of matching account entries
	 */
	public static List<AccountEntry> findByN_C(
		String name, String code, int start, int end) {

		return getPersistence().findByN_C(name, code, start, end);
	}

	/**
	 * Returns an ordered range of all the account entries where name LIKE &#63; and code LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEntryModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param code the code
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account entries
	 */
	public static List<AccountEntry> findByN_C(
		String name, String code, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator) {

		return getPersistence().findByN_C(
			name, code, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the account entries where name LIKE &#63; and code LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEntryModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param code the code
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account entries
	 */
	public static List<AccountEntry> findByN_C(
		String name, String code, int start, int end,
		OrderByComparator<AccountEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByN_C(
			name, code, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first account entry in the ordered set where name LIKE &#63; and code LIKE &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	public static AccountEntry findByN_C_First(
			String name, String code,
			OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEntryException {

		return getPersistence().findByN_C_First(name, code, orderByComparator);
	}

	/**
	 * Returns the first account entry in the ordered set where name LIKE &#63; and code LIKE &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	public static AccountEntry fetchByN_C_First(
		String name, String code,
		OrderByComparator<AccountEntry> orderByComparator) {

		return getPersistence().fetchByN_C_First(name, code, orderByComparator);
	}

	/**
	 * Returns the last account entry in the ordered set where name LIKE &#63; and code LIKE &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	public static AccountEntry findByN_C_Last(
			String name, String code,
			OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEntryException {

		return getPersistence().findByN_C_Last(name, code, orderByComparator);
	}

	/**
	 * Returns the last account entry in the ordered set where name LIKE &#63; and code LIKE &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	public static AccountEntry fetchByN_C_Last(
		String name, String code,
		OrderByComparator<AccountEntry> orderByComparator) {

		return getPersistence().fetchByN_C_Last(name, code, orderByComparator);
	}

	/**
	 * Returns the account entries before and after the current account entry in the ordered set where name LIKE &#63; and code LIKE &#63;.
	 *
	 * @param accountEntryId the primary key of the current account entry
	 * @param name the name
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	public static AccountEntry[] findByN_C_PrevAndNext(
			long accountEntryId, String name, String code,
			OrderByComparator<AccountEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEntryException {

		return getPersistence().findByN_C_PrevAndNext(
			accountEntryId, name, code, orderByComparator);
	}

	/**
	 * Removes all the account entries where name LIKE &#63; and code LIKE &#63; from the database.
	 *
	 * @param name the name
	 * @param code the code
	 */
	public static void removeByN_C(String name, String code) {
		getPersistence().removeByN_C(name, code);
	}

	/**
	 * Returns the number of account entries where name LIKE &#63; and code LIKE &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @return the number of matching account entries
	 */
	public static int countByN_C(String name, String code) {
		return getPersistence().countByN_C(name, code);
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
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEntryException {

		return getPersistence().remove(accountEntryId);
	}

	public static AccountEntry updateImpl(AccountEntry accountEntry) {
		return getPersistence().updateImpl(accountEntry);
	}

	/**
	 * Returns the account entry with the primary key or throws a <code>NoSuchAccountEntryException</code> if it could not be found.
	 *
	 * @param accountEntryId the primary key of the account entry
	 * @return the account entry
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	public static AccountEntry findByPrimaryKey(long accountEntryId)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchAccountEntryException {

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEntryModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account entries
	 */
	public static List<AccountEntry> findAll(
		int start, int end, OrderByComparator<AccountEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the account entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account entries
	 * @param end the upper bound of the range of account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of account entries
	 */
	public static List<AccountEntry> findAll(
		int start, int end, OrderByComparator<AccountEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
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

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AccountEntryPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(AccountEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile AccountEntryPersistence _persistence;

}