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

import com.liferay.osb.customer.admin.model.LicenseEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the license entry service. This utility wraps <code>com.liferay.osb.customer.admin.service.persistence.impl.LicenseEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntryPersistence
 * @generated
 */
public class LicenseEntryUtil {

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
	public static void clearCache(LicenseEntry licenseEntry) {
		getPersistence().clearCache(licenseEntry);
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
	public static Map<Serializable, LicenseEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<LicenseEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LicenseEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LicenseEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LicenseEntry update(LicenseEntry licenseEntry) {
		return getPersistence().update(licenseEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LicenseEntry update(
		LicenseEntry licenseEntry, ServiceContext serviceContext) {

		return getPersistence().update(licenseEntry, serviceContext);
	}

	/**
	 * Returns all the license entries where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching license entries
	 */
	public static List<LicenseEntry> findByProductEntryId(long productEntryId) {
		return getPersistence().findByProductEntryId(productEntryId);
	}

	/**
	 * Returns a range of all the license entries where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of matching license entries
	 */
	public static List<LicenseEntry> findByProductEntryId(
		long productEntryId, int start, int end) {

		return getPersistence().findByProductEntryId(
			productEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the license entries where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license entries
	 */
	public static List<LicenseEntry> findByProductEntryId(
		long productEntryId, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().findByProductEntryId(
			productEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license entries where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license entries
	 */
	public static List<LicenseEntry> findByProductEntryId(
		long productEntryId, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByProductEntryId(
			productEntryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public static LicenseEntry findByProductEntryId_First(
			long productEntryId,
			OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByProductEntryId_First(
			productEntryId, orderByComparator);
	}

	/**
	 * Returns the first license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public static LicenseEntry fetchByProductEntryId_First(
		long productEntryId,
		OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().fetchByProductEntryId_First(
			productEntryId, orderByComparator);
	}

	/**
	 * Returns the last license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public static LicenseEntry findByProductEntryId_Last(
			long productEntryId,
			OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByProductEntryId_Last(
			productEntryId, orderByComparator);
	}

	/**
	 * Returns the last license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public static LicenseEntry fetchByProductEntryId_Last(
		long productEntryId,
		OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().fetchByProductEntryId_Last(
			productEntryId, orderByComparator);
	}

	/**
	 * Returns the license entries before and after the current license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param licenseEntryId the primary key of the current license entry
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	public static LicenseEntry[] findByProductEntryId_PrevAndNext(
			long licenseEntryId, long productEntryId,
			OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByProductEntryId_PrevAndNext(
			licenseEntryId, productEntryId, orderByComparator);
	}

	/**
	 * Removes all the license entries where productEntryId = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 */
	public static void removeByProductEntryId(long productEntryId) {
		getPersistence().removeByProductEntryId(productEntryId);
	}

	/**
	 * Returns the number of license entries where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching license entries
	 */
	public static int countByProductEntryId(long productEntryId) {
		return getPersistence().countByProductEntryId(productEntryId);
	}

	/**
	 * Returns the license entry where productEntryId = &#63; and type = &#63; or throws a <code>NoSuchLicenseEntryException</code> if it could not be found.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @return the matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public static LicenseEntry findByPEI_T(long productEntryId, String type)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByPEI_T(productEntryId, type);
	}

	/**
	 * Returns the license entry where productEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @return the matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public static LicenseEntry fetchByPEI_T(long productEntryId, String type) {
		return getPersistence().fetchByPEI_T(productEntryId, type);
	}

	/**
	 * Returns the license entry where productEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public static LicenseEntry fetchByPEI_T(
		long productEntryId, String type, boolean useFinderCache) {

		return getPersistence().fetchByPEI_T(
			productEntryId, type, useFinderCache);
	}

	/**
	 * Removes the license entry where productEntryId = &#63; and type = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @return the license entry that was removed
	 */
	public static LicenseEntry removeByPEI_T(long productEntryId, String type)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchLicenseEntryException {

		return getPersistence().removeByPEI_T(productEntryId, type);
	}

	/**
	 * Returns the number of license entries where productEntryId = &#63; and type = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @return the number of matching license entries
	 */
	public static int countByPEI_T(long productEntryId, String type) {
		return getPersistence().countByPEI_T(productEntryId, type);
	}

	/**
	 * Returns all the license entries where productEntryId = &#63; and versionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param versionMin the version min
	 * @return the matching license entries
	 */
	public static List<LicenseEntry> findByPEI_V(
		long productEntryId, int versionMin) {

		return getPersistence().findByPEI_V(productEntryId, versionMin);
	}

	/**
	 * Returns a range of all the license entries where productEntryId = &#63; and versionMin &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param versionMin the version min
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of matching license entries
	 */
	public static List<LicenseEntry> findByPEI_V(
		long productEntryId, int versionMin, int start, int end) {

		return getPersistence().findByPEI_V(
			productEntryId, versionMin, start, end);
	}

	/**
	 * Returns an ordered range of all the license entries where productEntryId = &#63; and versionMin &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param versionMin the version min
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license entries
	 */
	public static List<LicenseEntry> findByPEI_V(
		long productEntryId, int versionMin, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().findByPEI_V(
			productEntryId, versionMin, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license entries where productEntryId = &#63; and versionMin &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param versionMin the version min
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license entries
	 */
	public static List<LicenseEntry> findByPEI_V(
		long productEntryId, int versionMin, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByPEI_V(
			productEntryId, versionMin, start, end, orderByComparator,
			useFinderCache);
	}

	/**
	 * Returns the first license entry in the ordered set where productEntryId = &#63; and versionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param versionMin the version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public static LicenseEntry findByPEI_V_First(
			long productEntryId, int versionMin,
			OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByPEI_V_First(
			productEntryId, versionMin, orderByComparator);
	}

	/**
	 * Returns the first license entry in the ordered set where productEntryId = &#63; and versionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param versionMin the version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public static LicenseEntry fetchByPEI_V_First(
		long productEntryId, int versionMin,
		OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().fetchByPEI_V_First(
			productEntryId, versionMin, orderByComparator);
	}

	/**
	 * Returns the last license entry in the ordered set where productEntryId = &#63; and versionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param versionMin the version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public static LicenseEntry findByPEI_V_Last(
			long productEntryId, int versionMin,
			OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByPEI_V_Last(
			productEntryId, versionMin, orderByComparator);
	}

	/**
	 * Returns the last license entry in the ordered set where productEntryId = &#63; and versionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param versionMin the version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public static LicenseEntry fetchByPEI_V_Last(
		long productEntryId, int versionMin,
		OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().fetchByPEI_V_Last(
			productEntryId, versionMin, orderByComparator);
	}

	/**
	 * Returns the license entries before and after the current license entry in the ordered set where productEntryId = &#63; and versionMin &le; &#63;.
	 *
	 * @param licenseEntryId the primary key of the current license entry
	 * @param productEntryId the product entry ID
	 * @param versionMin the version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	public static LicenseEntry[] findByPEI_V_PrevAndNext(
			long licenseEntryId, long productEntryId, int versionMin,
			OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByPEI_V_PrevAndNext(
			licenseEntryId, productEntryId, versionMin, orderByComparator);
	}

	/**
	 * Removes all the license entries where productEntryId = &#63; and versionMin &le; &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 * @param versionMin the version min
	 */
	public static void removeByPEI_V(long productEntryId, int versionMin) {
		getPersistence().removeByPEI_V(productEntryId, versionMin);
	}

	/**
	 * Returns the number of license entries where productEntryId = &#63; and versionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param versionMin the version min
	 * @return the number of matching license entries
	 */
	public static int countByPEI_V(long productEntryId, int versionMin) {
		return getPersistence().countByPEI_V(productEntryId, versionMin);
	}

	/**
	 * Caches the license entry in the entity cache if it is enabled.
	 *
	 * @param licenseEntry the license entry
	 */
	public static void cacheResult(LicenseEntry licenseEntry) {
		getPersistence().cacheResult(licenseEntry);
	}

	/**
	 * Caches the license entries in the entity cache if it is enabled.
	 *
	 * @param licenseEntries the license entries
	 */
	public static void cacheResult(List<LicenseEntry> licenseEntries) {
		getPersistence().cacheResult(licenseEntries);
	}

	/**
	 * Creates a new license entry with the primary key. Does not add the license entry to the database.
	 *
	 * @param licenseEntryId the primary key for the new license entry
	 * @return the new license entry
	 */
	public static LicenseEntry create(long licenseEntryId) {
		return getPersistence().create(licenseEntryId);
	}

	/**
	 * Removes the license entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry that was removed
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	public static LicenseEntry remove(long licenseEntryId)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchLicenseEntryException {

		return getPersistence().remove(licenseEntryId);
	}

	public static LicenseEntry updateImpl(LicenseEntry licenseEntry) {
		return getPersistence().updateImpl(licenseEntry);
	}

	/**
	 * Returns the license entry with the primary key or throws a <code>NoSuchLicenseEntryException</code> if it could not be found.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	public static LicenseEntry findByPrimaryKey(long licenseEntryId)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchLicenseEntryException {

		return getPersistence().findByPrimaryKey(licenseEntryId);
	}

	/**
	 * Returns the license entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry, or <code>null</code> if a license entry with the primary key could not be found
	 */
	public static LicenseEntry fetchByPrimaryKey(long licenseEntryId) {
		return getPersistence().fetchByPrimaryKey(licenseEntryId);
	}

	/**
	 * Returns all the license entries.
	 *
	 * @return the license entries
	 */
	public static List<LicenseEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of license entries
	 */
	public static List<LicenseEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of license entries
	 */
	public static List<LicenseEntry> findAll(
		int start, int end, OrderByComparator<LicenseEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of license entries
	 */
	public static List<LicenseEntry> findAll(
		int start, int end, OrderByComparator<LicenseEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the license entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of license entries.
	 *
	 * @return the number of license entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LicenseEntryPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(LicenseEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile LicenseEntryPersistence _persistence;

}