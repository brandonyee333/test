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

package com.liferay.watson.login.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.watson.login.exception.NoSuchEntryException;
import com.liferay.watson.login.model.WatsonTokenAuthEntry;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the watson token auth entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonTokenAuthEntryUtil
 * @generated
 */
@ProviderType
public interface WatsonTokenAuthEntryPersistence
	extends BasePersistence<WatsonTokenAuthEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonTokenAuthEntryUtil} to access the watson token auth entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, WatsonTokenAuthEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the watson token auth entry where userId = &#63; or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching watson token auth entry
	 * @throws NoSuchEntryException if a matching watson token auth entry could not be found
	 */
	public WatsonTokenAuthEntry findByUserId(long userId)
		throws NoSuchEntryException;

	/**
	 * Returns the watson token auth entry where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching watson token auth entry, or <code>null</code> if a matching watson token auth entry could not be found
	 */
	public WatsonTokenAuthEntry fetchByUserId(long userId);

	/**
	 * Returns the watson token auth entry where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching watson token auth entry, or <code>null</code> if a matching watson token auth entry could not be found
	 */
	public WatsonTokenAuthEntry fetchByUserId(
		long userId, boolean retrieveFromCache);

	/**
	 * Removes the watson token auth entry where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the watson token auth entry that was removed
	 */
	public WatsonTokenAuthEntry removeByUserId(long userId)
		throws NoSuchEntryException;

	/**
	 * Returns the number of watson token auth entries where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching watson token auth entries
	 */
	public int countByUserId(long userId);

	/**
	 * Returns all the watson token auth entries where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @return the matching watson token auth entries
	 */
	public java.util.List<WatsonTokenAuthEntry> findByC_U(
		long companyId, long userId);

	/**
	 * Returns a range of all the watson token auth entries where companyId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonTokenAuthEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of watson token auth entries
	 * @param end the upper bound of the range of watson token auth entries (not inclusive)
	 * @return the range of matching watson token auth entries
	 */
	public java.util.List<WatsonTokenAuthEntry> findByC_U(
		long companyId, long userId, int start, int end);

	/**
	 * Returns an ordered range of all the watson token auth entries where companyId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonTokenAuthEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of watson token auth entries
	 * @param end the upper bound of the range of watson token auth entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching watson token auth entries
	 */
	public java.util.List<WatsonTokenAuthEntry> findByC_U(
		long companyId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonTokenAuthEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the watson token auth entries where companyId = &#63; and userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonTokenAuthEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param start the lower bound of the range of watson token auth entries
	 * @param end the upper bound of the range of watson token auth entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching watson token auth entries
	 */
	public java.util.List<WatsonTokenAuthEntry> findByC_U(
		long companyId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonTokenAuthEntry>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Returns the first watson token auth entry in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching watson token auth entry
	 * @throws NoSuchEntryException if a matching watson token auth entry could not be found
	 */
	public WatsonTokenAuthEntry findByC_U_First(
			long companyId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator
				<WatsonTokenAuthEntry> orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the first watson token auth entry in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching watson token auth entry, or <code>null</code> if a matching watson token auth entry could not be found
	 */
	public WatsonTokenAuthEntry fetchByC_U_First(
		long companyId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonTokenAuthEntry>
			orderByComparator);

	/**
	 * Returns the last watson token auth entry in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching watson token auth entry
	 * @throws NoSuchEntryException if a matching watson token auth entry could not be found
	 */
	public WatsonTokenAuthEntry findByC_U_Last(
			long companyId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator
				<WatsonTokenAuthEntry> orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Returns the last watson token auth entry in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching watson token auth entry, or <code>null</code> if a matching watson token auth entry could not be found
	 */
	public WatsonTokenAuthEntry fetchByC_U_Last(
		long companyId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonTokenAuthEntry>
			orderByComparator);

	/**
	 * Returns the watson token auth entries before and after the current watson token auth entry in the ordered set where companyId = &#63; and userId = &#63;.
	 *
	 * @param watsonTokenAuthEntryId the primary key of the current watson token auth entry
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next watson token auth entry
	 * @throws NoSuchEntryException if a watson token auth entry with the primary key could not be found
	 */
	public WatsonTokenAuthEntry[] findByC_U_PrevAndNext(
			long watsonTokenAuthEntryId, long companyId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator
				<WatsonTokenAuthEntry> orderByComparator)
		throws NoSuchEntryException;

	/**
	 * Removes all the watson token auth entries where companyId = &#63; and userId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 */
	public void removeByC_U(long companyId, long userId);

	/**
	 * Returns the number of watson token auth entries where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId the user ID
	 * @return the number of matching watson token auth entries
	 */
	public int countByC_U(long companyId, long userId);

	/**
	 * Caches the watson token auth entry in the entity cache if it is enabled.
	 *
	 * @param watsonTokenAuthEntry the watson token auth entry
	 */
	public void cacheResult(WatsonTokenAuthEntry watsonTokenAuthEntry);

	/**
	 * Caches the watson token auth entries in the entity cache if it is enabled.
	 *
	 * @param watsonTokenAuthEntries the watson token auth entries
	 */
	public void cacheResult(
		java.util.List<WatsonTokenAuthEntry> watsonTokenAuthEntries);

	/**
	 * Creates a new watson token auth entry with the primary key. Does not add the watson token auth entry to the database.
	 *
	 * @param watsonTokenAuthEntryId the primary key for the new watson token auth entry
	 * @return the new watson token auth entry
	 */
	public WatsonTokenAuthEntry create(long watsonTokenAuthEntryId);

	/**
	 * Removes the watson token auth entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonTokenAuthEntryId the primary key of the watson token auth entry
	 * @return the watson token auth entry that was removed
	 * @throws NoSuchEntryException if a watson token auth entry with the primary key could not be found
	 */
	public WatsonTokenAuthEntry remove(long watsonTokenAuthEntryId)
		throws NoSuchEntryException;

	public WatsonTokenAuthEntry updateImpl(
		WatsonTokenAuthEntry watsonTokenAuthEntry);

	/**
	 * Returns the watson token auth entry with the primary key or throws a <code>NoSuchEntryException</code> if it could not be found.
	 *
	 * @param watsonTokenAuthEntryId the primary key of the watson token auth entry
	 * @return the watson token auth entry
	 * @throws NoSuchEntryException if a watson token auth entry with the primary key could not be found
	 */
	public WatsonTokenAuthEntry findByPrimaryKey(long watsonTokenAuthEntryId)
		throws NoSuchEntryException;

	/**
	 * Returns the watson token auth entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param watsonTokenAuthEntryId the primary key of the watson token auth entry
	 * @return the watson token auth entry, or <code>null</code> if a watson token auth entry with the primary key could not be found
	 */
	public WatsonTokenAuthEntry fetchByPrimaryKey(long watsonTokenAuthEntryId);

	/**
	 * Returns all the watson token auth entries.
	 *
	 * @return the watson token auth entries
	 */
	public java.util.List<WatsonTokenAuthEntry> findAll();

	/**
	 * Returns a range of all the watson token auth entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonTokenAuthEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson token auth entries
	 * @param end the upper bound of the range of watson token auth entries (not inclusive)
	 * @return the range of watson token auth entries
	 */
	public java.util.List<WatsonTokenAuthEntry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the watson token auth entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonTokenAuthEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson token auth entries
	 * @param end the upper bound of the range of watson token auth entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of watson token auth entries
	 */
	public java.util.List<WatsonTokenAuthEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonTokenAuthEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the watson token auth entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>WatsonTokenAuthEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson token auth entries
	 * @param end the upper bound of the range of watson token auth entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of watson token auth entries
	 */
	public java.util.List<WatsonTokenAuthEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonTokenAuthEntry>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the watson token auth entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of watson token auth entries.
	 *
	 * @return the number of watson token auth entries
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}