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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.admin.exception.NoSuchAccountEntryException;
import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the account entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryUtil
 * @generated
 */
@ProviderType
public interface AccountEntryPersistence extends BasePersistence<AccountEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountEntryUtil} to access the account entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, AccountEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the account entry where koroneikiAccountKey = &#63; or throws a <code>NoSuchAccountEntryException</code> if it could not be found.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @return the matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	public AccountEntry findByKoroneikiAccountKey(String koroneikiAccountKey)
		throws NoSuchAccountEntryException;

	/**
	 * Returns the account entry where koroneikiAccountKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	public AccountEntry fetchByKoroneikiAccountKey(String koroneikiAccountKey);

	/**
	 * Returns the account entry where koroneikiAccountKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	public AccountEntry fetchByKoroneikiAccountKey(
		String koroneikiAccountKey, boolean useFinderCache);

	/**
	 * Removes the account entry where koroneikiAccountKey = &#63; from the database.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @return the account entry that was removed
	 */
	public AccountEntry removeByKoroneikiAccountKey(String koroneikiAccountKey)
		throws NoSuchAccountEntryException;

	/**
	 * Returns the number of account entries where koroneikiAccountKey = &#63;.
	 *
	 * @param koroneikiAccountKey the koroneiki account key
	 * @return the number of matching account entries
	 */
	public int countByKoroneikiAccountKey(String koroneikiAccountKey);

	/**
	 * Returns all the account entries where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the matching account entries
	 */
	public java.util.List<AccountEntry> findByDossieraAccountKey(
		String dossieraAccountKey);

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
	public java.util.List<AccountEntry> findByDossieraAccountKey(
		String dossieraAccountKey, int start, int end);

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
	public java.util.List<AccountEntry> findByDossieraAccountKey(
		String dossieraAccountKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry>
			orderByComparator);

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
	public java.util.List<AccountEntry> findByDossieraAccountKey(
		String dossieraAccountKey, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first account entry in the ordered set where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	public AccountEntry findByDossieraAccountKey_First(
			String dossieraAccountKey,
			com.liferay.portal.kernel.util.OrderByComparator<AccountEntry>
				orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	 * Returns the first account entry in the ordered set where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	public AccountEntry fetchByDossieraAccountKey_First(
		String dossieraAccountKey,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry>
			orderByComparator);

	/**
	 * Returns the last account entry in the ordered set where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	public AccountEntry findByDossieraAccountKey_Last(
			String dossieraAccountKey,
			com.liferay.portal.kernel.util.OrderByComparator<AccountEntry>
				orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	 * Returns the last account entry in the ordered set where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	public AccountEntry fetchByDossieraAccountKey_Last(
		String dossieraAccountKey,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry>
			orderByComparator);

	/**
	 * Returns the account entries before and after the current account entry in the ordered set where dossieraAccountKey = &#63;.
	 *
	 * @param accountEntryId the primary key of the current account entry
	 * @param dossieraAccountKey the dossiera account key
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account entry
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	public AccountEntry[] findByDossieraAccountKey_PrevAndNext(
			long accountEntryId, String dossieraAccountKey,
			com.liferay.portal.kernel.util.OrderByComparator<AccountEntry>
				orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	 * Removes all the account entries where dossieraAccountKey = &#63; from the database.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 */
	public void removeByDossieraAccountKey(String dossieraAccountKey);

	/**
	 * Returns the number of account entries where dossieraAccountKey = &#63;.
	 *
	 * @param dossieraAccountKey the dossiera account key
	 * @return the number of matching account entries
	 */
	public int countByDossieraAccountKey(String dossieraAccountKey);

	/**
	 * Returns the account entry where corpProjectUuid = &#63; or throws a <code>NoSuchAccountEntryException</code> if it could not be found.
	 *
	 * @param corpProjectUuid the corp project uuid
	 * @return the matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	public AccountEntry findByCorpProjectUuid(String corpProjectUuid)
		throws NoSuchAccountEntryException;

	/**
	 * Returns the account entry where corpProjectUuid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param corpProjectUuid the corp project uuid
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	public AccountEntry fetchByCorpProjectUuid(String corpProjectUuid);

	/**
	 * Returns the account entry where corpProjectUuid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param corpProjectUuid the corp project uuid
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	public AccountEntry fetchByCorpProjectUuid(
		String corpProjectUuid, boolean useFinderCache);

	/**
	 * Removes the account entry where corpProjectUuid = &#63; from the database.
	 *
	 * @param corpProjectUuid the corp project uuid
	 * @return the account entry that was removed
	 */
	public AccountEntry removeByCorpProjectUuid(String corpProjectUuid)
		throws NoSuchAccountEntryException;

	/**
	 * Returns the number of account entries where corpProjectUuid = &#63;.
	 *
	 * @param corpProjectUuid the corp project uuid
	 * @return the number of matching account entries
	 */
	public int countByCorpProjectUuid(String corpProjectUuid);

	/**
	 * Returns all the account entries where name LIKE &#63; and code LIKE &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @return the matching account entries
	 */
	public java.util.List<AccountEntry> findByN_C(String name, String code);

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
	public java.util.List<AccountEntry> findByN_C(
		String name, String code, int start, int end);

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
	public java.util.List<AccountEntry> findByN_C(
		String name, String code, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry>
			orderByComparator);

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
	public java.util.List<AccountEntry> findByN_C(
		String name, String code, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first account entry in the ordered set where name LIKE &#63; and code LIKE &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	public AccountEntry findByN_C_First(
			String name, String code,
			com.liferay.portal.kernel.util.OrderByComparator<AccountEntry>
				orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	 * Returns the first account entry in the ordered set where name LIKE &#63; and code LIKE &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	public AccountEntry fetchByN_C_First(
		String name, String code,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry>
			orderByComparator);

	/**
	 * Returns the last account entry in the ordered set where name LIKE &#63; and code LIKE &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry
	 * @throws NoSuchAccountEntryException if a matching account entry could not be found
	 */
	public AccountEntry findByN_C_Last(
			String name, String code,
			com.liferay.portal.kernel.util.OrderByComparator<AccountEntry>
				orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	 * Returns the last account entry in the ordered set where name LIKE &#63; and code LIKE &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account entry, or <code>null</code> if a matching account entry could not be found
	 */
	public AccountEntry fetchByN_C_Last(
		String name, String code,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry>
			orderByComparator);

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
	public AccountEntry[] findByN_C_PrevAndNext(
			long accountEntryId, String name, String code,
			com.liferay.portal.kernel.util.OrderByComparator<AccountEntry>
				orderByComparator)
		throws NoSuchAccountEntryException;

	/**
	 * Removes all the account entries where name LIKE &#63; and code LIKE &#63; from the database.
	 *
	 * @param name the name
	 * @param code the code
	 */
	public void removeByN_C(String name, String code);

	/**
	 * Returns the number of account entries where name LIKE &#63; and code LIKE &#63;.
	 *
	 * @param name the name
	 * @param code the code
	 * @return the number of matching account entries
	 */
	public int countByN_C(String name, String code);

	/**
	 * Caches the account entry in the entity cache if it is enabled.
	 *
	 * @param accountEntry the account entry
	 */
	public void cacheResult(AccountEntry accountEntry);

	/**
	 * Caches the account entries in the entity cache if it is enabled.
	 *
	 * @param accountEntries the account entries
	 */
	public void cacheResult(java.util.List<AccountEntry> accountEntries);

	/**
	 * Creates a new account entry with the primary key. Does not add the account entry to the database.
	 *
	 * @param accountEntryId the primary key for the new account entry
	 * @return the new account entry
	 */
	public AccountEntry create(long accountEntryId);

	/**
	 * Removes the account entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEntryId the primary key of the account entry
	 * @return the account entry that was removed
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	public AccountEntry remove(long accountEntryId)
		throws NoSuchAccountEntryException;

	public AccountEntry updateImpl(AccountEntry accountEntry);

	/**
	 * Returns the account entry with the primary key or throws a <code>NoSuchAccountEntryException</code> if it could not be found.
	 *
	 * @param accountEntryId the primary key of the account entry
	 * @return the account entry
	 * @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	 */
	public AccountEntry findByPrimaryKey(long accountEntryId)
		throws NoSuchAccountEntryException;

	/**
	 * Returns the account entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountEntryId the primary key of the account entry
	 * @return the account entry, or <code>null</code> if a account entry with the primary key could not be found
	 */
	public AccountEntry fetchByPrimaryKey(long accountEntryId);

	/**
	 * Returns all the account entries.
	 *
	 * @return the account entries
	 */
	public java.util.List<AccountEntry> findAll();

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
	public java.util.List<AccountEntry> findAll(int start, int end);

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
	public java.util.List<AccountEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry>
			orderByComparator);

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
	public java.util.List<AccountEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the account entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of account entries.
	 *
	 * @return the number of account entries
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}