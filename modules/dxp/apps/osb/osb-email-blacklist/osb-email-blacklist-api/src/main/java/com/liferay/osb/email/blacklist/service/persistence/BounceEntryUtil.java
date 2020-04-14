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

import com.liferay.osb.email.blacklist.model.BounceEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the bounce entry service. This utility wraps <code>com.liferay.osb.email.blacklist.service.persistence.impl.BounceEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Jamie Sammons
 * @see BounceEntryPersistence
 * @generated
 */
public class BounceEntryUtil {

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
	public static void clearCache(BounceEntry bounceEntry) {
		getPersistence().clearCache(bounceEntry);
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
	public static Map<Serializable, BounceEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<BounceEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<BounceEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<BounceEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<BounceEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static BounceEntry update(BounceEntry bounceEntry) {
		return getPersistence().update(bounceEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static BounceEntry update(
		BounceEntry bounceEntry, ServiceContext serviceContext) {

		return getPersistence().update(bounceEntry, serviceContext);
	}

	/**
	 * Returns all the bounce entries where bounceDate &lt; &#63;.
	 *
	 * @param bounceDate the bounce date
	 * @return the matching bounce entries
	 */
	public static List<BounceEntry> findByLtBounceDate(Date bounceDate) {
		return getPersistence().findByLtBounceDate(bounceDate);
	}

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
	public static List<BounceEntry> findByLtBounceDate(
		Date bounceDate, int start, int end) {

		return getPersistence().findByLtBounceDate(bounceDate, start, end);
	}

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
	public static List<BounceEntry> findByLtBounceDate(
		Date bounceDate, int start, int end,
		OrderByComparator<BounceEntry> orderByComparator) {

		return getPersistence().findByLtBounceDate(
			bounceDate, start, end, orderByComparator);
	}

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
	public static List<BounceEntry> findByLtBounceDate(
		Date bounceDate, int start, int end,
		OrderByComparator<BounceEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByLtBounceDate(
			bounceDate, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first bounce entry in the ordered set where bounceDate &lt; &#63;.
	 *
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bounce entry
	 * @throws NoSuchBounceEntryException if a matching bounce entry could not be found
	 */
	public static BounceEntry findByLtBounceDate_First(
			Date bounceDate, OrderByComparator<BounceEntry> orderByComparator)
		throws com.liferay.osb.email.blacklist.exception.
			NoSuchBounceEntryException {

		return getPersistence().findByLtBounceDate_First(
			bounceDate, orderByComparator);
	}

	/**
	 * Returns the first bounce entry in the ordered set where bounceDate &lt; &#63;.
	 *
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bounce entry, or <code>null</code> if a matching bounce entry could not be found
	 */
	public static BounceEntry fetchByLtBounceDate_First(
		Date bounceDate, OrderByComparator<BounceEntry> orderByComparator) {

		return getPersistence().fetchByLtBounceDate_First(
			bounceDate, orderByComparator);
	}

	/**
	 * Returns the last bounce entry in the ordered set where bounceDate &lt; &#63;.
	 *
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bounce entry
	 * @throws NoSuchBounceEntryException if a matching bounce entry could not be found
	 */
	public static BounceEntry findByLtBounceDate_Last(
			Date bounceDate, OrderByComparator<BounceEntry> orderByComparator)
		throws com.liferay.osb.email.blacklist.exception.
			NoSuchBounceEntryException {

		return getPersistence().findByLtBounceDate_Last(
			bounceDate, orderByComparator);
	}

	/**
	 * Returns the last bounce entry in the ordered set where bounceDate &lt; &#63;.
	 *
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bounce entry, or <code>null</code> if a matching bounce entry could not be found
	 */
	public static BounceEntry fetchByLtBounceDate_Last(
		Date bounceDate, OrderByComparator<BounceEntry> orderByComparator) {

		return getPersistence().fetchByLtBounceDate_Last(
			bounceDate, orderByComparator);
	}

	/**
	 * Returns the bounce entries before and after the current bounce entry in the ordered set where bounceDate &lt; &#63;.
	 *
	 * @param bounceEntryId the primary key of the current bounce entry
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next bounce entry
	 * @throws NoSuchBounceEntryException if a bounce entry with the primary key could not be found
	 */
	public static BounceEntry[] findByLtBounceDate_PrevAndNext(
			long bounceEntryId, Date bounceDate,
			OrderByComparator<BounceEntry> orderByComparator)
		throws com.liferay.osb.email.blacklist.exception.
			NoSuchBounceEntryException {

		return getPersistence().findByLtBounceDate_PrevAndNext(
			bounceEntryId, bounceDate, orderByComparator);
	}

	/**
	 * Removes all the bounce entries where bounceDate &lt; &#63; from the database.
	 *
	 * @param bounceDate the bounce date
	 */
	public static void removeByLtBounceDate(Date bounceDate) {
		getPersistence().removeByLtBounceDate(bounceDate);
	}

	/**
	 * Returns the number of bounce entries where bounceDate &lt; &#63;.
	 *
	 * @param bounceDate the bounce date
	 * @return the number of matching bounce entries
	 */
	public static int countByLtBounceDate(Date bounceDate) {
		return getPersistence().countByLtBounceDate(bounceDate);
	}

	/**
	 * Returns all the bounce entries where emailAddress = &#63; and bounceDate &ge; &#63;.
	 *
	 * @param emailAddress the email address
	 * @param bounceDate the bounce date
	 * @return the matching bounce entries
	 */
	public static List<BounceEntry> findByEA_GtBD(
		String emailAddress, Date bounceDate) {

		return getPersistence().findByEA_GtBD(emailAddress, bounceDate);
	}

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
	public static List<BounceEntry> findByEA_GtBD(
		String emailAddress, Date bounceDate, int start, int end) {

		return getPersistence().findByEA_GtBD(
			emailAddress, bounceDate, start, end);
	}

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
	public static List<BounceEntry> findByEA_GtBD(
		String emailAddress, Date bounceDate, int start, int end,
		OrderByComparator<BounceEntry> orderByComparator) {

		return getPersistence().findByEA_GtBD(
			emailAddress, bounceDate, start, end, orderByComparator);
	}

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
	public static List<BounceEntry> findByEA_GtBD(
		String emailAddress, Date bounceDate, int start, int end,
		OrderByComparator<BounceEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByEA_GtBD(
			emailAddress, bounceDate, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first bounce entry in the ordered set where emailAddress = &#63; and bounceDate &ge; &#63;.
	 *
	 * @param emailAddress the email address
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bounce entry
	 * @throws NoSuchBounceEntryException if a matching bounce entry could not be found
	 */
	public static BounceEntry findByEA_GtBD_First(
			String emailAddress, Date bounceDate,
			OrderByComparator<BounceEntry> orderByComparator)
		throws com.liferay.osb.email.blacklist.exception.
			NoSuchBounceEntryException {

		return getPersistence().findByEA_GtBD_First(
			emailAddress, bounceDate, orderByComparator);
	}

	/**
	 * Returns the first bounce entry in the ordered set where emailAddress = &#63; and bounceDate &ge; &#63;.
	 *
	 * @param emailAddress the email address
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching bounce entry, or <code>null</code> if a matching bounce entry could not be found
	 */
	public static BounceEntry fetchByEA_GtBD_First(
		String emailAddress, Date bounceDate,
		OrderByComparator<BounceEntry> orderByComparator) {

		return getPersistence().fetchByEA_GtBD_First(
			emailAddress, bounceDate, orderByComparator);
	}

	/**
	 * Returns the last bounce entry in the ordered set where emailAddress = &#63; and bounceDate &ge; &#63;.
	 *
	 * @param emailAddress the email address
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bounce entry
	 * @throws NoSuchBounceEntryException if a matching bounce entry could not be found
	 */
	public static BounceEntry findByEA_GtBD_Last(
			String emailAddress, Date bounceDate,
			OrderByComparator<BounceEntry> orderByComparator)
		throws com.liferay.osb.email.blacklist.exception.
			NoSuchBounceEntryException {

		return getPersistence().findByEA_GtBD_Last(
			emailAddress, bounceDate, orderByComparator);
	}

	/**
	 * Returns the last bounce entry in the ordered set where emailAddress = &#63; and bounceDate &ge; &#63;.
	 *
	 * @param emailAddress the email address
	 * @param bounceDate the bounce date
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching bounce entry, or <code>null</code> if a matching bounce entry could not be found
	 */
	public static BounceEntry fetchByEA_GtBD_Last(
		String emailAddress, Date bounceDate,
		OrderByComparator<BounceEntry> orderByComparator) {

		return getPersistence().fetchByEA_GtBD_Last(
			emailAddress, bounceDate, orderByComparator);
	}

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
	public static BounceEntry[] findByEA_GtBD_PrevAndNext(
			long bounceEntryId, String emailAddress, Date bounceDate,
			OrderByComparator<BounceEntry> orderByComparator)
		throws com.liferay.osb.email.blacklist.exception.
			NoSuchBounceEntryException {

		return getPersistence().findByEA_GtBD_PrevAndNext(
			bounceEntryId, emailAddress, bounceDate, orderByComparator);
	}

	/**
	 * Removes all the bounce entries where emailAddress = &#63; and bounceDate &ge; &#63; from the database.
	 *
	 * @param emailAddress the email address
	 * @param bounceDate the bounce date
	 */
	public static void removeByEA_GtBD(String emailAddress, Date bounceDate) {
		getPersistence().removeByEA_GtBD(emailAddress, bounceDate);
	}

	/**
	 * Returns the number of bounce entries where emailAddress = &#63; and bounceDate &ge; &#63;.
	 *
	 * @param emailAddress the email address
	 * @param bounceDate the bounce date
	 * @return the number of matching bounce entries
	 */
	public static int countByEA_GtBD(String emailAddress, Date bounceDate) {
		return getPersistence().countByEA_GtBD(emailAddress, bounceDate);
	}

	/**
	 * Caches the bounce entry in the entity cache if it is enabled.
	 *
	 * @param bounceEntry the bounce entry
	 */
	public static void cacheResult(BounceEntry bounceEntry) {
		getPersistence().cacheResult(bounceEntry);
	}

	/**
	 * Caches the bounce entries in the entity cache if it is enabled.
	 *
	 * @param bounceEntries the bounce entries
	 */
	public static void cacheResult(List<BounceEntry> bounceEntries) {
		getPersistence().cacheResult(bounceEntries);
	}

	/**
	 * Creates a new bounce entry with the primary key. Does not add the bounce entry to the database.
	 *
	 * @param bounceEntryId the primary key for the new bounce entry
	 * @return the new bounce entry
	 */
	public static BounceEntry create(long bounceEntryId) {
		return getPersistence().create(bounceEntryId);
	}

	/**
	 * Removes the bounce entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param bounceEntryId the primary key of the bounce entry
	 * @return the bounce entry that was removed
	 * @throws NoSuchBounceEntryException if a bounce entry with the primary key could not be found
	 */
	public static BounceEntry remove(long bounceEntryId)
		throws com.liferay.osb.email.blacklist.exception.
			NoSuchBounceEntryException {

		return getPersistence().remove(bounceEntryId);
	}

	public static BounceEntry updateImpl(BounceEntry bounceEntry) {
		return getPersistence().updateImpl(bounceEntry);
	}

	/**
	 * Returns the bounce entry with the primary key or throws a <code>NoSuchBounceEntryException</code> if it could not be found.
	 *
	 * @param bounceEntryId the primary key of the bounce entry
	 * @return the bounce entry
	 * @throws NoSuchBounceEntryException if a bounce entry with the primary key could not be found
	 */
	public static BounceEntry findByPrimaryKey(long bounceEntryId)
		throws com.liferay.osb.email.blacklist.exception.
			NoSuchBounceEntryException {

		return getPersistence().findByPrimaryKey(bounceEntryId);
	}

	/**
	 * Returns the bounce entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param bounceEntryId the primary key of the bounce entry
	 * @return the bounce entry, or <code>null</code> if a bounce entry with the primary key could not be found
	 */
	public static BounceEntry fetchByPrimaryKey(long bounceEntryId) {
		return getPersistence().fetchByPrimaryKey(bounceEntryId);
	}

	/**
	 * Returns all the bounce entries.
	 *
	 * @return the bounce entries
	 */
	public static List<BounceEntry> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<BounceEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<BounceEntry> findAll(
		int start, int end, OrderByComparator<BounceEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<BounceEntry> findAll(
		int start, int end, OrderByComparator<BounceEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the bounce entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of bounce entries.
	 *
	 * @return the number of bounce entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static BounceEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<BounceEntryPersistence, BounceEntryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(BounceEntryPersistence.class);

		ServiceTracker<BounceEntryPersistence, BounceEntryPersistence>
			serviceTracker =
				new ServiceTracker
					<BounceEntryPersistence, BounceEntryPersistence>(
						bundle.getBundleContext(), BounceEntryPersistence.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}