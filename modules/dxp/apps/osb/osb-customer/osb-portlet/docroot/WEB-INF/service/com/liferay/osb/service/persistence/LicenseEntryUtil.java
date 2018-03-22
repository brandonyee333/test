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

import com.liferay.osb.model.LicenseEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the license entry service. This utility wraps {@link com.liferay.osb.service.persistence.impl.LicenseEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntryPersistence
 * @see com.liferay.osb.service.persistence.impl.LicenseEntryPersistenceImpl
 * @generated
 */
@ProviderType
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
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
	public static LicenseEntry update(LicenseEntry licenseEntry,
		ServiceContext serviceContext) {
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @return the range of matching license entries
	*/
	public static List<LicenseEntry> findByProductEntryId(long productEntryId,
		int start, int end) {
		return getPersistence().findByProductEntryId(productEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the license entries where productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license entries
	*/
	public static List<LicenseEntry> findByProductEntryId(long productEntryId,
		int start, int end, OrderByComparator<LicenseEntry> orderByComparator) {
		return getPersistence()
				   .findByProductEntryId(productEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the license entries where productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license entries
	*/
	public static List<LicenseEntry> findByProductEntryId(long productEntryId,
		int start, int end, OrderByComparator<LicenseEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByProductEntryId(productEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first license entry in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license entry
	* @throws NoSuchLicenseEntryException if a matching license entry could not be found
	*/
	public static LicenseEntry findByProductEntryId_First(long productEntryId,
		OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseEntryException {
		return getPersistence()
				   .findByProductEntryId_First(productEntryId, orderByComparator);
	}

	/**
	* Returns the first license entry in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	*/
	public static LicenseEntry fetchByProductEntryId_First(
		long productEntryId, OrderByComparator<LicenseEntry> orderByComparator) {
		return getPersistence()
				   .fetchByProductEntryId_First(productEntryId,
			orderByComparator);
	}

	/**
	* Returns the last license entry in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license entry
	* @throws NoSuchLicenseEntryException if a matching license entry could not be found
	*/
	public static LicenseEntry findByProductEntryId_Last(long productEntryId,
		OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseEntryException {
		return getPersistence()
				   .findByProductEntryId_Last(productEntryId, orderByComparator);
	}

	/**
	* Returns the last license entry in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	*/
	public static LicenseEntry fetchByProductEntryId_Last(long productEntryId,
		OrderByComparator<LicenseEntry> orderByComparator) {
		return getPersistence()
				   .fetchByProductEntryId_Last(productEntryId, orderByComparator);
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
		throws com.liferay.osb.exception.NoSuchLicenseEntryException {
		return getPersistence()
				   .findByProductEntryId_PrevAndNext(licenseEntryId,
			productEntryId, orderByComparator);
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
	* Returns the license entry where productEntryId = &#63; and type = &#63; or throws a {@link NoSuchLicenseEntryException} if it could not be found.
	*
	* @param productEntryId the product entry ID
	* @param type the type
	* @return the matching license entry
	* @throws NoSuchLicenseEntryException if a matching license entry could not be found
	*/
	public static LicenseEntry findByPEI_T(long productEntryId,
		java.lang.String type)
		throws com.liferay.osb.exception.NoSuchLicenseEntryException {
		return getPersistence().findByPEI_T(productEntryId, type);
	}

	/**
	* Returns the license entry where productEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param productEntryId the product entry ID
	* @param type the type
	* @return the matching license entry, or <code>null</code> if a matching license entry could not be found
	*/
	public static LicenseEntry fetchByPEI_T(long productEntryId,
		java.lang.String type) {
		return getPersistence().fetchByPEI_T(productEntryId, type);
	}

	/**
	* Returns the license entry where productEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param productEntryId the product entry ID
	* @param type the type
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching license entry, or <code>null</code> if a matching license entry could not be found
	*/
	public static LicenseEntry fetchByPEI_T(long productEntryId,
		java.lang.String type, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByPEI_T(productEntryId, type, retrieveFromCache);
	}

	/**
	* Removes the license entry where productEntryId = &#63; and type = &#63; from the database.
	*
	* @param productEntryId the product entry ID
	* @param type the type
	* @return the license entry that was removed
	*/
	public static LicenseEntry removeByPEI_T(long productEntryId,
		java.lang.String type)
		throws com.liferay.osb.exception.NoSuchLicenseEntryException {
		return getPersistence().removeByPEI_T(productEntryId, type);
	}

	/**
	* Returns the number of license entries where productEntryId = &#63; and type = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param type the type
	* @return the number of matching license entries
	*/
	public static int countByPEI_T(long productEntryId, java.lang.String type) {
		return getPersistence().countByPEI_T(productEntryId, type);
	}

	/**
	* Returns all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @return the matching license entries
	*/
	public static List<LicenseEntry> findByPEI_PV(long productEntryId,
		int portalVersionMin) {
		return getPersistence().findByPEI_PV(productEntryId, portalVersionMin);
	}

	/**
	* Returns a range of all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @return the range of matching license entries
	*/
	public static List<LicenseEntry> findByPEI_PV(long productEntryId,
		int portalVersionMin, int start, int end) {
		return getPersistence()
				   .findByPEI_PV(productEntryId, portalVersionMin, start, end);
	}

	/**
	* Returns an ordered range of all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license entries
	*/
	public static List<LicenseEntry> findByPEI_PV(long productEntryId,
		int portalVersionMin, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator) {
		return getPersistence()
				   .findByPEI_PV(productEntryId, portalVersionMin, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license entries
	*/
	public static List<LicenseEntry> findByPEI_PV(long productEntryId,
		int portalVersionMin, int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPEI_PV(productEntryId, portalVersionMin, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license entry
	* @throws NoSuchLicenseEntryException if a matching license entry could not be found
	*/
	public static LicenseEntry findByPEI_PV_First(long productEntryId,
		int portalVersionMin, OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseEntryException {
		return getPersistence()
				   .findByPEI_PV_First(productEntryId, portalVersionMin,
			orderByComparator);
	}

	/**
	* Returns the first license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	*/
	public static LicenseEntry fetchByPEI_PV_First(long productEntryId,
		int portalVersionMin, OrderByComparator<LicenseEntry> orderByComparator) {
		return getPersistence()
				   .fetchByPEI_PV_First(productEntryId, portalVersionMin,
			orderByComparator);
	}

	/**
	* Returns the last license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license entry
	* @throws NoSuchLicenseEntryException if a matching license entry could not be found
	*/
	public static LicenseEntry findByPEI_PV_Last(long productEntryId,
		int portalVersionMin, OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseEntryException {
		return getPersistence()
				   .findByPEI_PV_Last(productEntryId, portalVersionMin,
			orderByComparator);
	}

	/**
	* Returns the last license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	*/
	public static LicenseEntry fetchByPEI_PV_Last(long productEntryId,
		int portalVersionMin, OrderByComparator<LicenseEntry> orderByComparator) {
		return getPersistence()
				   .fetchByPEI_PV_Last(productEntryId, portalVersionMin,
			orderByComparator);
	}

	/**
	* Returns the license entries before and after the current license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* @param licenseEntryId the primary key of the current license entry
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license entry
	* @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	*/
	public static LicenseEntry[] findByPEI_PV_PrevAndNext(long licenseEntryId,
		long productEntryId, int portalVersionMin,
		OrderByComparator<LicenseEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseEntryException {
		return getPersistence()
				   .findByPEI_PV_PrevAndNext(licenseEntryId, productEntryId,
			portalVersionMin, orderByComparator);
	}

	/**
	* Removes all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63; from the database.
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	*/
	public static void removeByPEI_PV(long productEntryId, int portalVersionMin) {
		getPersistence().removeByPEI_PV(productEntryId, portalVersionMin);
	}

	/**
	* Returns the number of license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	*
	* @param productEntryId the product entry ID
	* @param portalVersionMin the portal version min
	* @return the number of matching license entries
	*/
	public static int countByPEI_PV(long productEntryId, int portalVersionMin) {
		return getPersistence().countByPEI_PV(productEntryId, portalVersionMin);
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
		throws com.liferay.osb.exception.NoSuchLicenseEntryException {
		return getPersistence().remove(licenseEntryId);
	}

	public static LicenseEntry updateImpl(LicenseEntry licenseEntry) {
		return getPersistence().updateImpl(licenseEntry);
	}

	/**
	* Returns the license entry with the primary key or throws a {@link NoSuchLicenseEntryException} if it could not be found.
	*
	* @param licenseEntryId the primary key of the license entry
	* @return the license entry
	* @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	*/
	public static LicenseEntry findByPrimaryKey(long licenseEntryId)
		throws com.liferay.osb.exception.NoSuchLicenseEntryException {
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

	public static java.util.Map<java.io.Serializable, LicenseEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of license entries
	*/
	public static List<LicenseEntry> findAll(int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the license entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of license entries
	* @param end the upper bound of the range of license entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of license entries
	*/
	public static List<LicenseEntry> findAll(int start, int end,
		OrderByComparator<LicenseEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
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

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LicenseEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (LicenseEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					LicenseEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(LicenseEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static LicenseEntryPersistence _persistence;
}