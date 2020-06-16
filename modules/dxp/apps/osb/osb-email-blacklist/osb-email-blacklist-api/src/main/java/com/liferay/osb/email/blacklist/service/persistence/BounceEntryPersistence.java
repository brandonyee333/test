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

package com.liferay.osb.email.blacklist.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.email.blacklist.exception.NoSuchBounceEntryException;
import com.liferay.osb.email.blacklist.model.BounceEntry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the bounce entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jamie Sammons
 * @see BounceEntryUtil
 * @generated
 */
@ProviderType
public interface BounceEntryPersistence extends BasePersistence<BounceEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BounceEntryUtil} to access the bounce entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, BounceEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the bounce entries where bounceDate &lt; &#63;.
	 *
	 * @param bounceDate the bounce date
	 * @return the matching bounce entries
	 */
	public java.util.List<BounceEntry> findByLtBounceDate(Date bounceDate);

	/**
	 * Returns a range of all the bounce entries where bounceDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BounceEntryModelImpl</code>.
	 * </p>
	 *
	 * @param bounceDate the bounce date
	 * @param start the lower bound of the range of bounce entries
	 * @param end the upper bound of the range of bounce entries (not inclusive)
	 * @return the range of matching bounce entries
	 */
	public java.util.List<BounceEntry> findByLtBounceDate(
		Date bounceDate, int start, int end);

	/**
	 * Returns an ordered range of all the bounce entries where bounceDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BounceEntryModelImpl</code>.
	 * </p>
	 *
	 * @param bounceDate the bounce date
	 * @param start the lower bound of the range of bounce entries
	 * @param end the upper bound of the range of bounce entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bounce entries
	 */
	public java.util.List<BounceEntry> findByLtBounceDate(
		Date bounceDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BounceEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the bounce entries where bounceDate &lt; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BounceEntryModelImpl</code>.
	 * </p>
	 *
	 * @param bounceDate the bounce date
	 * @param start the lower bound of the range of bounce entries
	 * @param end the upper bound of the range of bounce entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching bounce entries
	 */
	public java.util.List<BounceEntry> findByLtBounceDate(
		Date bounceDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BounceEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first bounce entry in the ordered set where bounceDate &lt; &#63;.
	 *
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bounce entry
	 * @throws NoSuchBounceEntryException if a matching bounce entry could not be found
	 */
	public BounceEntry findByLtBounceDate_First(
			Date bounceDate,
			com.liferay.portal.kernel.util.OrderByComparator<BounceEntry>
				orderByComparator)
		throws NoSuchBounceEntryException;

	/**
	 * Returns the first bounce entry in the ordered set where bounceDate &lt; &#63;.
	 *
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bounce entry, or <code>null</code> if a matching bounce entry could not be found
	 */
	public BounceEntry fetchByLtBounceDate_First(
		Date bounceDate,
		com.liferay.portal.kernel.util.OrderByComparator<BounceEntry>
			orderByComparator);

	/**
	 * Returns the last bounce entry in the ordered set where bounceDate &lt; &#63;.
	 *
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bounce entry
	 * @throws NoSuchBounceEntryException if a matching bounce entry could not be found
	 */
	public BounceEntry findByLtBounceDate_Last(
			Date bounceDate,
			com.liferay.portal.kernel.util.OrderByComparator<BounceEntry>
				orderByComparator)
		throws NoSuchBounceEntryException;

	/**
	 * Returns the last bounce entry in the ordered set where bounceDate &lt; &#63;.
	 *
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bounce entry, or <code>null</code> if a matching bounce entry could not be found
	 */
	public BounceEntry fetchByLtBounceDate_Last(
		Date bounceDate,
		com.liferay.portal.kernel.util.OrderByComparator<BounceEntry>
			orderByComparator);

	/**
	 * Returns the bounce entries before and after the current bounce entry in the ordered set where bounceDate &lt; &#63;.
	 *
	 * @param bounceEntryId the primary key of the current bounce entry
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bounce entry
	 * @throws NoSuchBounceEntryException if a bounce entry with the primary key could not be found
	 */
	public BounceEntry[] findByLtBounceDate_PrevAndNext(
			long bounceEntryId, Date bounceDate,
			com.liferay.portal.kernel.util.OrderByComparator<BounceEntry>
				orderByComparator)
		throws NoSuchBounceEntryException;

	/**
	 * Removes all the bounce entries where bounceDate &lt; &#63; from the database.
	 *
	 * @param bounceDate the bounce date
	 */
	public void removeByLtBounceDate(Date bounceDate);

	/**
	 * Returns the number of bounce entries where bounceDate &lt; &#63;.
	 *
	 * @param bounceDate the bounce date
	 * @return the number of matching bounce entries
	 */
	public int countByLtBounceDate(Date bounceDate);

	/**
	 * Returns all the bounce entries where emailAddress = &#63; and bounceDate &ge; &#63;.
	 *
	 * @param emailAddress the email address
	 * @param bounceDate the bounce date
	 * @return the matching bounce entries
	 */
	public java.util.List<BounceEntry> findByEA_GtBD(
		String emailAddress, Date bounceDate);

	/**
	 * Returns a range of all the bounce entries where emailAddress = &#63; and bounceDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BounceEntryModelImpl</code>.
	 * </p>
	 *
	 * @param emailAddress the email address
	 * @param bounceDate the bounce date
	 * @param start the lower bound of the range of bounce entries
	 * @param end the upper bound of the range of bounce entries (not inclusive)
	 * @return the range of matching bounce entries
	 */
	public java.util.List<BounceEntry> findByEA_GtBD(
		String emailAddress, Date bounceDate, int start, int end);

	/**
	 * Returns an ordered range of all the bounce entries where emailAddress = &#63; and bounceDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BounceEntryModelImpl</code>.
	 * </p>
	 *
	 * @param emailAddress the email address
	 * @param bounceDate the bounce date
	 * @param start the lower bound of the range of bounce entries
	 * @param end the upper bound of the range of bounce entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching bounce entries
	 */
	public java.util.List<BounceEntry> findByEA_GtBD(
		String emailAddress, Date bounceDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BounceEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the bounce entries where emailAddress = &#63; and bounceDate &ge; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BounceEntryModelImpl</code>.
	 * </p>
	 *
	 * @param emailAddress the email address
	 * @param bounceDate the bounce date
	 * @param start the lower bound of the range of bounce entries
	 * @param end the upper bound of the range of bounce entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching bounce entries
	 */
	public java.util.List<BounceEntry> findByEA_GtBD(
		String emailAddress, Date bounceDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BounceEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first bounce entry in the ordered set where emailAddress = &#63; and bounceDate &ge; &#63;.
	 *
	 * @param emailAddress the email address
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bounce entry
	 * @throws NoSuchBounceEntryException if a matching bounce entry could not be found
	 */
	public BounceEntry findByEA_GtBD_First(
			String emailAddress, Date bounceDate,
			com.liferay.portal.kernel.util.OrderByComparator<BounceEntry>
				orderByComparator)
		throws NoSuchBounceEntryException;

	/**
	 * Returns the first bounce entry in the ordered set where emailAddress = &#63; and bounceDate &ge; &#63;.
	 *
	 * @param emailAddress the email address
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bounce entry, or <code>null</code> if a matching bounce entry could not be found
	 */
	public BounceEntry fetchByEA_GtBD_First(
		String emailAddress, Date bounceDate,
		com.liferay.portal.kernel.util.OrderByComparator<BounceEntry>
			orderByComparator);

	/**
	 * Returns the last bounce entry in the ordered set where emailAddress = &#63; and bounceDate &ge; &#63;.
	 *
	 * @param emailAddress the email address
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bounce entry
	 * @throws NoSuchBounceEntryException if a matching bounce entry could not be found
	 */
	public BounceEntry findByEA_GtBD_Last(
			String emailAddress, Date bounceDate,
			com.liferay.portal.kernel.util.OrderByComparator<BounceEntry>
				orderByComparator)
		throws NoSuchBounceEntryException;

	/**
	 * Returns the last bounce entry in the ordered set where emailAddress = &#63; and bounceDate &ge; &#63;.
	 *
	 * @param emailAddress the email address
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bounce entry, or <code>null</code> if a matching bounce entry could not be found
	 */
	public BounceEntry fetchByEA_GtBD_Last(
		String emailAddress, Date bounceDate,
		com.liferay.portal.kernel.util.OrderByComparator<BounceEntry>
			orderByComparator);

	/**
	 * Returns the bounce entries before and after the current bounce entry in the ordered set where emailAddress = &#63; and bounceDate &ge; &#63;.
	 *
	 * @param bounceEntryId the primary key of the current bounce entry
	 * @param emailAddress the email address
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bounce entry
	 * @throws NoSuchBounceEntryException if a bounce entry with the primary key could not be found
	 */
	public BounceEntry[] findByEA_GtBD_PrevAndNext(
			long bounceEntryId, String emailAddress, Date bounceDate,
			com.liferay.portal.kernel.util.OrderByComparator<BounceEntry>
				orderByComparator)
		throws NoSuchBounceEntryException;

	/**
	 * Removes all the bounce entries where emailAddress = &#63; and bounceDate &ge; &#63; from the database.
	 *
	 * @param emailAddress the email address
	 * @param bounceDate the bounce date
	 */
	public void removeByEA_GtBD(String emailAddress, Date bounceDate);

	/**
	 * Returns the number of bounce entries where emailAddress = &#63; and bounceDate &ge; &#63;.
	 *
	 * @param emailAddress the email address
	 * @param bounceDate the bounce date
	 * @return the number of matching bounce entries
	 */
	public int countByEA_GtBD(String emailAddress, Date bounceDate);

	/**
	 * Caches the bounce entry in the entity cache if it is enabled.
	 *
	 * @param bounceEntry the bounce entry
	 */
	public void cacheResult(BounceEntry bounceEntry);

	/**
	 * Caches the bounce entries in the entity cache if it is enabled.
	 *
	 * @param bounceEntries the bounce entries
	 */
	public void cacheResult(java.util.List<BounceEntry> bounceEntries);

	/**
	 * Creates a new bounce entry with the primary key. Does not add the bounce entry to the database.
	 *
	 * @param bounceEntryId the primary key for the new bounce entry
	 * @return the new bounce entry
	 */
	public BounceEntry create(long bounceEntryId);

	/**
	 * Removes the bounce entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param bounceEntryId the primary key of the bounce entry
	 * @return the bounce entry that was removed
	 * @throws NoSuchBounceEntryException if a bounce entry with the primary key could not be found
	 */
	public BounceEntry remove(long bounceEntryId)
		throws NoSuchBounceEntryException;

	public BounceEntry updateImpl(BounceEntry bounceEntry);

	/**
	 * Returns the bounce entry with the primary key or throws a <code>NoSuchBounceEntryException</code> if it could not be found.
	 *
	 * @param bounceEntryId the primary key of the bounce entry
	 * @return the bounce entry
	 * @throws NoSuchBounceEntryException if a bounce entry with the primary key could not be found
	 */
	public BounceEntry findByPrimaryKey(long bounceEntryId)
		throws NoSuchBounceEntryException;

	/**
	 * Returns the bounce entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param bounceEntryId the primary key of the bounce entry
	 * @return the bounce entry, or <code>null</code> if a bounce entry with the primary key could not be found
	 */
	public BounceEntry fetchByPrimaryKey(long bounceEntryId);

	/**
	 * Returns all the bounce entries.
	 *
	 * @return the bounce entries
	 */
	public java.util.List<BounceEntry> findAll();

	/**
	 * Returns a range of all the bounce entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BounceEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of bounce entries
	 * @param end the upper bound of the range of bounce entries (not inclusive)
	 * @return the range of bounce entries
	 */
	public java.util.List<BounceEntry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the bounce entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BounceEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of bounce entries
	 * @param end the upper bound of the range of bounce entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of bounce entries
	 */
	public java.util.List<BounceEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BounceEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the bounce entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>BounceEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of bounce entries
	 * @param end the upper bound of the range of bounce entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of bounce entries
	 */
	public java.util.List<BounceEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<BounceEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the bounce entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of bounce entries.
	 *
	 * @return the number of bounce entries
	 */
	public int countAll();

}