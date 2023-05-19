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

import com.liferay.osb.customer.admin.model.ProductEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the product entry service. This utility wraps <code>com.liferay.osb.customer.admin.service.persistence.impl.ProductEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductEntryPersistence
 * @generated
 */
public class ProductEntryUtil {

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
	public static void clearCache(ProductEntry productEntry) {
		getPersistence().clearCache(productEntry);
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
	public static Map<Serializable, ProductEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ProductEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProductEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProductEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProductEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProductEntry update(ProductEntry productEntry) {
		return getPersistence().update(productEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProductEntry update(
		ProductEntry productEntry, ServiceContext serviceContext) {

		return getPersistence().update(productEntry, serviceContext);
	}

	/**
	 * Returns the product entry where koroneikiProductKey = &#63; or throws a <code>NoSuchProductEntryException</code> if it could not be found.
	 *
	 * @param koroneikiProductKey the koroneiki product key
	 * @return the matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	public static ProductEntry findByKoroneikiProductKey(
			String koroneikiProductKey)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchProductEntryException {

		return getPersistence().findByKoroneikiProductKey(koroneikiProductKey);
	}

	/**
	 * Returns the product entry where koroneikiProductKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param koroneikiProductKey the koroneiki product key
	 * @return the matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public static ProductEntry fetchByKoroneikiProductKey(
		String koroneikiProductKey) {

		return getPersistence().fetchByKoroneikiProductKey(koroneikiProductKey);
	}

	/**
	 * Returns the product entry where koroneikiProductKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param koroneikiProductKey the koroneiki product key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public static ProductEntry fetchByKoroneikiProductKey(
		String koroneikiProductKey, boolean useFinderCache) {

		return getPersistence().fetchByKoroneikiProductKey(
			koroneikiProductKey, useFinderCache);
	}

	/**
	 * Removes the product entry where koroneikiProductKey = &#63; from the database.
	 *
	 * @param koroneikiProductKey the koroneiki product key
	 * @return the product entry that was removed
	 */
	public static ProductEntry removeByKoroneikiProductKey(
			String koroneikiProductKey)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchProductEntryException {

		return getPersistence().removeByKoroneikiProductKey(
			koroneikiProductKey);
	}

	/**
	 * Returns the number of product entries where koroneikiProductKey = &#63;.
	 *
	 * @param koroneikiProductKey the koroneiki product key
	 * @return the number of matching product entries
	 */
	public static int countByKoroneikiProductKey(String koroneikiProductKey) {
		return getPersistence().countByKoroneikiProductKey(koroneikiProductKey);
	}

	/**
	 * Returns the product entry where name = &#63; or throws a <code>NoSuchProductEntryException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	public static ProductEntry findByName(String name)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchProductEntryException {

		return getPersistence().findByName(name);
	}

	/**
	 * Returns the product entry where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public static ProductEntry fetchByName(String name) {
		return getPersistence().fetchByName(name);
	}

	/**
	 * Returns the product entry where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public static ProductEntry fetchByName(
		String name, boolean useFinderCache) {

		return getPersistence().fetchByName(name, useFinderCache);
	}

	/**
	 * Removes the product entry where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the product entry that was removed
	 */
	public static ProductEntry removeByName(String name)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchProductEntryException {

		return getPersistence().removeByName(name);
	}

	/**
	 * Returns the number of product entries where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching product entries
	 */
	public static int countByName(String name) {
		return getPersistence().countByName(name);
	}

	/**
	 * Returns all the product entries where environment = &#63;.
	 *
	 * @param environment the environment
	 * @return the matching product entries
	 */
	public static List<ProductEntry> findByEnvironment(int environment) {
		return getPersistence().findByEnvironment(environment);
	}

	/**
	 * Returns a range of all the product entries where environment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param environment the environment
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @return the range of matching product entries
	 */
	public static List<ProductEntry> findByEnvironment(
		int environment, int start, int end) {

		return getPersistence().findByEnvironment(environment, start, end);
	}

	/**
	 * Returns an ordered range of all the product entries where environment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param environment the environment
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product entries
	 */
	public static List<ProductEntry> findByEnvironment(
		int environment, int start, int end,
		OrderByComparator<ProductEntry> orderByComparator) {

		return getPersistence().findByEnvironment(
			environment, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product entries where environment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param environment the environment
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product entries
	 */
	public static List<ProductEntry> findByEnvironment(
		int environment, int start, int end,
		OrderByComparator<ProductEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByEnvironment(
			environment, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first product entry in the ordered set where environment = &#63;.
	 *
	 * @param environment the environment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	public static ProductEntry findByEnvironment_First(
			int environment, OrderByComparator<ProductEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchProductEntryException {

		return getPersistence().findByEnvironment_First(
			environment, orderByComparator);
	}

	/**
	 * Returns the first product entry in the ordered set where environment = &#63;.
	 *
	 * @param environment the environment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public static ProductEntry fetchByEnvironment_First(
		int environment, OrderByComparator<ProductEntry> orderByComparator) {

		return getPersistence().fetchByEnvironment_First(
			environment, orderByComparator);
	}

	/**
	 * Returns the last product entry in the ordered set where environment = &#63;.
	 *
	 * @param environment the environment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	public static ProductEntry findByEnvironment_Last(
			int environment, OrderByComparator<ProductEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchProductEntryException {

		return getPersistence().findByEnvironment_Last(
			environment, orderByComparator);
	}

	/**
	 * Returns the last product entry in the ordered set where environment = &#63;.
	 *
	 * @param environment the environment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public static ProductEntry fetchByEnvironment_Last(
		int environment, OrderByComparator<ProductEntry> orderByComparator) {

		return getPersistence().fetchByEnvironment_Last(
			environment, orderByComparator);
	}

	/**
	 * Returns the product entries before and after the current product entry in the ordered set where environment = &#63;.
	 *
	 * @param productEntryId the primary key of the current product entry
	 * @param environment the environment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product entry
	 * @throws NoSuchProductEntryException if a product entry with the primary key could not be found
	 */
	public static ProductEntry[] findByEnvironment_PrevAndNext(
			long productEntryId, int environment,
			OrderByComparator<ProductEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchProductEntryException {

		return getPersistence().findByEnvironment_PrevAndNext(
			productEntryId, environment, orderByComparator);
	}

	/**
	 * Removes all the product entries where environment = &#63; from the database.
	 *
	 * @param environment the environment
	 */
	public static void removeByEnvironment(int environment) {
		getPersistence().removeByEnvironment(environment);
	}

	/**
	 * Returns the number of product entries where environment = &#63;.
	 *
	 * @param environment the environment
	 * @return the number of matching product entries
	 */
	public static int countByEnvironment(int environment) {
		return getPersistence().countByEnvironment(environment);
	}

	/**
	 * Returns all the product entries where licenses = &#63;.
	 *
	 * @param licenses the licenses
	 * @return the matching product entries
	 */
	public static List<ProductEntry> findByLicenses(boolean licenses) {
		return getPersistence().findByLicenses(licenses);
	}

	/**
	 * Returns a range of all the product entries where licenses = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param licenses the licenses
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @return the range of matching product entries
	 */
	public static List<ProductEntry> findByLicenses(
		boolean licenses, int start, int end) {

		return getPersistence().findByLicenses(licenses, start, end);
	}

	/**
	 * Returns an ordered range of all the product entries where licenses = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param licenses the licenses
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product entries
	 */
	public static List<ProductEntry> findByLicenses(
		boolean licenses, int start, int end,
		OrderByComparator<ProductEntry> orderByComparator) {

		return getPersistence().findByLicenses(
			licenses, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product entries where licenses = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param licenses the licenses
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product entries
	 */
	public static List<ProductEntry> findByLicenses(
		boolean licenses, int start, int end,
		OrderByComparator<ProductEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByLicenses(
			licenses, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first product entry in the ordered set where licenses = &#63;.
	 *
	 * @param licenses the licenses
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	public static ProductEntry findByLicenses_First(
			boolean licenses, OrderByComparator<ProductEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchProductEntryException {

		return getPersistence().findByLicenses_First(
			licenses, orderByComparator);
	}

	/**
	 * Returns the first product entry in the ordered set where licenses = &#63;.
	 *
	 * @param licenses the licenses
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public static ProductEntry fetchByLicenses_First(
		boolean licenses, OrderByComparator<ProductEntry> orderByComparator) {

		return getPersistence().fetchByLicenses_First(
			licenses, orderByComparator);
	}

	/**
	 * Returns the last product entry in the ordered set where licenses = &#63;.
	 *
	 * @param licenses the licenses
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	public static ProductEntry findByLicenses_Last(
			boolean licenses, OrderByComparator<ProductEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchProductEntryException {

		return getPersistence().findByLicenses_Last(
			licenses, orderByComparator);
	}

	/**
	 * Returns the last product entry in the ordered set where licenses = &#63;.
	 *
	 * @param licenses the licenses
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public static ProductEntry fetchByLicenses_Last(
		boolean licenses, OrderByComparator<ProductEntry> orderByComparator) {

		return getPersistence().fetchByLicenses_Last(
			licenses, orderByComparator);
	}

	/**
	 * Returns the product entries before and after the current product entry in the ordered set where licenses = &#63;.
	 *
	 * @param productEntryId the primary key of the current product entry
	 * @param licenses the licenses
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product entry
	 * @throws NoSuchProductEntryException if a product entry with the primary key could not be found
	 */
	public static ProductEntry[] findByLicenses_PrevAndNext(
			long productEntryId, boolean licenses,
			OrderByComparator<ProductEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchProductEntryException {

		return getPersistence().findByLicenses_PrevAndNext(
			productEntryId, licenses, orderByComparator);
	}

	/**
	 * Removes all the product entries where licenses = &#63; from the database.
	 *
	 * @param licenses the licenses
	 */
	public static void removeByLicenses(boolean licenses) {
		getPersistence().removeByLicenses(licenses);
	}

	/**
	 * Returns the number of product entries where licenses = &#63;.
	 *
	 * @param licenses the licenses
	 * @return the number of matching product entries
	 */
	public static int countByLicenses(boolean licenses) {
		return getPersistence().countByLicenses(licenses);
	}

	/**
	 * Caches the product entry in the entity cache if it is enabled.
	 *
	 * @param productEntry the product entry
	 */
	public static void cacheResult(ProductEntry productEntry) {
		getPersistence().cacheResult(productEntry);
	}

	/**
	 * Caches the product entries in the entity cache if it is enabled.
	 *
	 * @param productEntries the product entries
	 */
	public static void cacheResult(List<ProductEntry> productEntries) {
		getPersistence().cacheResult(productEntries);
	}

	/**
	 * Creates a new product entry with the primary key. Does not add the product entry to the database.
	 *
	 * @param productEntryId the primary key for the new product entry
	 * @return the new product entry
	 */
	public static ProductEntry create(long productEntryId) {
		return getPersistence().create(productEntryId);
	}

	/**
	 * Removes the product entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productEntryId the primary key of the product entry
	 * @return the product entry that was removed
	 * @throws NoSuchProductEntryException if a product entry with the primary key could not be found
	 */
	public static ProductEntry remove(long productEntryId)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchProductEntryException {

		return getPersistence().remove(productEntryId);
	}

	public static ProductEntry updateImpl(ProductEntry productEntry) {
		return getPersistence().updateImpl(productEntry);
	}

	/**
	 * Returns the product entry with the primary key or throws a <code>NoSuchProductEntryException</code> if it could not be found.
	 *
	 * @param productEntryId the primary key of the product entry
	 * @return the product entry
	 * @throws NoSuchProductEntryException if a product entry with the primary key could not be found
	 */
	public static ProductEntry findByPrimaryKey(long productEntryId)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchProductEntryException {

		return getPersistence().findByPrimaryKey(productEntryId);
	}

	/**
	 * Returns the product entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productEntryId the primary key of the product entry
	 * @return the product entry, or <code>null</code> if a product entry with the primary key could not be found
	 */
	public static ProductEntry fetchByPrimaryKey(long productEntryId) {
		return getPersistence().fetchByPrimaryKey(productEntryId);
	}

	/**
	 * Returns all the product entries.
	 *
	 * @return the product entries
	 */
	public static List<ProductEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the product entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @return the range of product entries
	 */
	public static List<ProductEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the product entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of product entries
	 */
	public static List<ProductEntry> findAll(
		int start, int end, OrderByComparator<ProductEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the product entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of product entries
	 */
	public static List<ProductEntry> findAll(
		int start, int end, OrderByComparator<ProductEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the product entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of product entries.
	 *
	 * @return the number of product entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ProductEntryPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(ProductEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile ProductEntryPersistence _persistence;

}