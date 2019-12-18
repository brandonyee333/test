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

import com.liferay.osb.customer.admin.model.SupportRegion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the support region service. This utility wraps <code>com.liferay.osb.customer.admin.service.persistence.impl.SupportRegionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportRegionPersistence
 * @generated
 */
public class SupportRegionUtil {

	/**
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
	public static void clearCache(SupportRegion supportRegion) {
		getPersistence().clearCache(supportRegion);
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
	public static Map<Serializable, SupportRegion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SupportRegion> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SupportRegion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SupportRegion> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SupportRegion> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SupportRegion update(SupportRegion supportRegion) {
		return getPersistence().update(supportRegion);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SupportRegion update(
		SupportRegion supportRegion, ServiceContext serviceContext) {

		return getPersistence().update(supportRegion, serviceContext);
	}

	/**
	 * Returns the support region where name = &#63; or throws a <code>NoSuchSupportRegionException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching support region
	 * @throws NoSuchSupportRegionException if a matching support region could not be found
	 */
	public static SupportRegion findByName(String name)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchSupportRegionException {

		return getPersistence().findByName(name);
	}

	/**
	 * Returns the support region where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching support region, or <code>null</code> if a matching support region could not be found
	 */
	public static SupportRegion fetchByName(String name) {
		return getPersistence().fetchByName(name);
	}

	/**
	 * Returns the support region where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching support region, or <code>null</code> if a matching support region could not be found
	 */
	public static SupportRegion fetchByName(
		String name, boolean useFinderCache) {

		return getPersistence().fetchByName(name, useFinderCache);
	}

	/**
	 * Removes the support region where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the support region that was removed
	 */
	public static SupportRegion removeByName(String name)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchSupportRegionException {

		return getPersistence().removeByName(name);
	}

	/**
	 * Returns the number of support regions where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching support regions
	 */
	public static int countByName(String name) {
		return getPersistence().countByName(name);
	}

	/**
	 * Caches the support region in the entity cache if it is enabled.
	 *
	 * @param supportRegion the support region
	 */
	public static void cacheResult(SupportRegion supportRegion) {
		getPersistence().cacheResult(supportRegion);
	}

	/**
	 * Caches the support regions in the entity cache if it is enabled.
	 *
	 * @param supportRegions the support regions
	 */
	public static void cacheResult(List<SupportRegion> supportRegions) {
		getPersistence().cacheResult(supportRegions);
	}

	/**
	 * Creates a new support region with the primary key. Does not add the support region to the database.
	 *
	 * @param supportRegionId the primary key for the new support region
	 * @return the new support region
	 */
	public static SupportRegion create(long supportRegionId) {
		return getPersistence().create(supportRegionId);
	}

	/**
	 * Removes the support region with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param supportRegionId the primary key of the support region
	 * @return the support region that was removed
	 * @throws NoSuchSupportRegionException if a support region with the primary key could not be found
	 */
	public static SupportRegion remove(long supportRegionId)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchSupportRegionException {

		return getPersistence().remove(supportRegionId);
	}

	public static SupportRegion updateImpl(SupportRegion supportRegion) {
		return getPersistence().updateImpl(supportRegion);
	}

	/**
	 * Returns the support region with the primary key or throws a <code>NoSuchSupportRegionException</code> if it could not be found.
	 *
	 * @param supportRegionId the primary key of the support region
	 * @return the support region
	 * @throws NoSuchSupportRegionException if a support region with the primary key could not be found
	 */
	public static SupportRegion findByPrimaryKey(long supportRegionId)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchSupportRegionException {

		return getPersistence().findByPrimaryKey(supportRegionId);
	}

	/**
	 * Returns the support region with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param supportRegionId the primary key of the support region
	 * @return the support region, or <code>null</code> if a support region with the primary key could not be found
	 */
	public static SupportRegion fetchByPrimaryKey(long supportRegionId) {
		return getPersistence().fetchByPrimaryKey(supportRegionId);
	}

	/**
	 * Returns all the support regions.
	 *
	 * @return the support regions
	 */
	public static List<SupportRegion> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the support regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportRegionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @return the range of support regions
	 */
	public static List<SupportRegion> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the support regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportRegionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support regions
	 */
	public static List<SupportRegion> findAll(
		int start, int end,
		OrderByComparator<SupportRegion> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the support regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportRegionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of support regions
	 */
	public static List<SupportRegion> findAll(
		int start, int end, OrderByComparator<SupportRegion> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the support regions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of support regions.
	 *
	 * @return the number of support regions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	 * Returns the primaryKeys of account entries associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @return long[] of the primaryKeys of account entries associated with the support region
	 */
	public static long[] getAccountEntryPrimaryKeys(long pk) {
		return getPersistence().getAccountEntryPrimaryKeys(pk);
	}

	/**
	 * Returns all the account entries associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @return the account entries associated with the support region
	 */
	public static List<com.liferay.osb.customer.admin.model.AccountEntry>
		getAccountEntries(long pk) {

		return getPersistence().getAccountEntries(pk);
	}

	/**
	 * Returns a range of all the account entries associated with the support region.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportRegionModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the support region
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @return the range of account entries associated with the support region
	 */
	public static List<com.liferay.osb.customer.admin.model.AccountEntry>
		getAccountEntries(long pk, int start, int end) {

		return getPersistence().getAccountEntries(pk, start, end);
	}

	/**
	 * Returns an ordered range of all the account entries associated with the support region.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportRegionModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the support region
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account entries associated with the support region
	 */
	public static List<com.liferay.osb.customer.admin.model.AccountEntry>
		getAccountEntries(
			long pk, int start, int end,
			OrderByComparator<com.liferay.osb.customer.admin.model.AccountEntry>
				orderByComparator) {

		return getPersistence().getAccountEntries(
			pk, start, end, orderByComparator);
	}

	/**
	 * Returns the number of account entries associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @return the number of account entries associated with the support region
	 */
	public static int getAccountEntriesSize(long pk) {
		return getPersistence().getAccountEntriesSize(pk);
	}

	/**
	 * Returns <code>true</code> if the account entry is associated with the support region.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPK the primary key of the account entry
	 * @return <code>true</code> if the account entry is associated with the support region; <code>false</code> otherwise
	 */
	public static boolean containsAccountEntry(long pk, long accountEntryPK) {
		return getPersistence().containsAccountEntry(pk, accountEntryPK);
	}

	/**
	 * Returns <code>true</code> if the support region has any account entries associated with it.
	 *
	 * @param pk the primary key of the support region to check for associations with account entries
	 * @return <code>true</code> if the support region has any account entries associated with it; <code>false</code> otherwise
	 */
	public static boolean containsAccountEntries(long pk) {
		return getPersistence().containsAccountEntries(pk);
	}

	/**
	 * Adds an association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPK the primary key of the account entry
	 */
	public static void addAccountEntry(long pk, long accountEntryPK) {
		getPersistence().addAccountEntry(pk, accountEntryPK);
	}

	/**
	 * Adds an association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntry the account entry
	 */
	public static void addAccountEntry(
		long pk,
		com.liferay.osb.customer.admin.model.AccountEntry accountEntry) {

		getPersistence().addAccountEntry(pk, accountEntry);
	}

	/**
	 * Adds an association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPKs the primary keys of the account entries
	 */
	public static void addAccountEntries(long pk, long[] accountEntryPKs) {
		getPersistence().addAccountEntries(pk, accountEntryPKs);
	}

	/**
	 * Adds an association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntries the account entries
	 */
	public static void addAccountEntries(
		long pk,
		List<com.liferay.osb.customer.admin.model.AccountEntry>
			accountEntries) {

		getPersistence().addAccountEntries(pk, accountEntries);
	}

	/**
	 * Clears all associations between the support region and its account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region to clear the associated account entries from
	 */
	public static void clearAccountEntries(long pk) {
		getPersistence().clearAccountEntries(pk);
	}

	/**
	 * Removes the association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPK the primary key of the account entry
	 */
	public static void removeAccountEntry(long pk, long accountEntryPK) {
		getPersistence().removeAccountEntry(pk, accountEntryPK);
	}

	/**
	 * Removes the association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntry the account entry
	 */
	public static void removeAccountEntry(
		long pk,
		com.liferay.osb.customer.admin.model.AccountEntry accountEntry) {

		getPersistence().removeAccountEntry(pk, accountEntry);
	}

	/**
	 * Removes the association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPKs the primary keys of the account entries
	 */
	public static void removeAccountEntries(long pk, long[] accountEntryPKs) {
		getPersistence().removeAccountEntries(pk, accountEntryPKs);
	}

	/**
	 * Removes the association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntries the account entries
	 */
	public static void removeAccountEntries(
		long pk,
		List<com.liferay.osb.customer.admin.model.AccountEntry>
			accountEntries) {

		getPersistence().removeAccountEntries(pk, accountEntries);
	}

	/**
	 * Sets the account entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntryPKs the primary keys of the account entries to be associated with the support region
	 */
	public static void setAccountEntries(long pk, long[] accountEntryPKs) {
		getPersistence().setAccountEntries(pk, accountEntryPKs);
	}

	/**
	 * Sets the account entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	 *
	 * @param pk the primary key of the support region
	 * @param accountEntries the account entries to be associated with the support region
	 */
	public static void setAccountEntries(
		long pk,
		List<com.liferay.osb.customer.admin.model.AccountEntry>
			accountEntries) {

		getPersistence().setAccountEntries(pk, accountEntries);
	}

	public static SupportRegionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<SupportRegionPersistence, SupportRegionPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(SupportRegionPersistence.class);

		ServiceTracker<SupportRegionPersistence, SupportRegionPersistence>
			serviceTracker =
				new ServiceTracker
					<SupportRegionPersistence, SupportRegionPersistence>(
						bundle.getBundleContext(),
						SupportRegionPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}