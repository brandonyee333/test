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

import com.liferay.osb.model.LicenseKey;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the license key service. This utility wraps {@link com.liferay.osb.service.persistence.impl.LicenseKeyPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyPersistence
 * @see com.liferay.osb.service.persistence.impl.LicenseKeyPersistenceImpl
 * @generated
 */
@ProviderType
public class LicenseKeyUtil {
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
	public static void clearCache(LicenseKey licenseKey) {
		getPersistence().clearCache(licenseKey);
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
	public static List<LicenseKey> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<LicenseKey> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<LicenseKey> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static LicenseKey update(LicenseKey licenseKey) {
		return getPersistence().update(licenseKey);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static LicenseKey update(LicenseKey licenseKey,
		ServiceContext serviceContext) {
		return getPersistence().update(licenseKey, serviceContext);
	}

	/**
	* Returns all the license keies where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @return the matching license keies
	*/
	public static List<LicenseKey> findByLicenseKeySetId(long licenseKeySetId) {
		return getPersistence().findByLicenseKeySetId(licenseKeySetId);
	}

	/**
	* Returns a range of all the license keies where licenseKeySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param licenseKeySetId the license key set ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public static List<LicenseKey> findByLicenseKeySetId(long licenseKeySetId,
		int start, int end) {
		return getPersistence()
				   .findByLicenseKeySetId(licenseKeySetId, start, end);
	}

	/**
	* Returns an ordered range of all the license keies where licenseKeySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param licenseKeySetId the license key set ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByLicenseKeySetId(long licenseKeySetId,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .findByLicenseKeySetId(licenseKeySetId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the license keies where licenseKeySetId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param licenseKeySetId the license key set ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByLicenseKeySetId(long licenseKeySetId,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLicenseKeySetId(licenseKeySetId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByLicenseKeySetId_First(long licenseKeySetId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByLicenseKeySetId_First(licenseKeySetId,
			orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByLicenseKeySetId_First(
		long licenseKeySetId, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByLicenseKeySetId_First(licenseKeySetId,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByLicenseKeySetId_Last(long licenseKeySetId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByLicenseKeySetId_Last(licenseKeySetId,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByLicenseKeySetId_Last(long licenseKeySetId,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByLicenseKeySetId_Last(licenseKeySetId,
			orderByComparator);
	}

	/**
	* Returns the license keies before and after the current license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public static LicenseKey[] findByLicenseKeySetId_PrevAndNext(
		long licenseKeyId, long licenseKeySetId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByLicenseKeySetId_PrevAndNext(licenseKeyId,
			licenseKeySetId, orderByComparator);
	}

	/**
	* Removes all the license keies where licenseKeySetId = &#63; from the database.
	*
	* @param licenseKeySetId the license key set ID
	*/
	public static void removeByLicenseKeySetId(long licenseKeySetId) {
		getPersistence().removeByLicenseKeySetId(licenseKeySetId);
	}

	/**
	* Returns the number of license keies where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @return the number of matching license keies
	*/
	public static int countByLicenseKeySetId(long licenseKeySetId) {
		return getPersistence().countByLicenseKeySetId(licenseKeySetId);
	}

	/**
	* Returns all the license keies where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching license keies
	*/
	public static List<LicenseKey> findByAccountEntryId(long accountEntryId) {
		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	* Returns a range of all the license keies where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public static List<LicenseKey> findByAccountEntryId(long accountEntryId,
		int start, int end) {
		return getPersistence().findByAccountEntryId(accountEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the license keies where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the license keies where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first license key in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByAccountEntryId_First(long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByAccountEntryId_First(accountEntryId, orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByAccountEntryId_First(accountEntryId,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the license keies before and after the current license key in the ordered set where accountEntryId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public static LicenseKey[] findByAccountEntryId_PrevAndNext(
		long licenseKeyId, long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByAccountEntryId_PrevAndNext(licenseKeyId,
			accountEntryId, orderByComparator);
	}

	/**
	* Removes all the license keies where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	*/
	public static void removeByAccountEntryId(long accountEntryId) {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	* Returns the number of license keies where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching license keies
	*/
	public static int countByAccountEntryId(long accountEntryId) {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	* Returns all the license keies where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @return the matching license keies
	*/
	public static List<LicenseKey> findByOfferingEntryId(long offeringEntryId) {
		return getPersistence().findByOfferingEntryId(offeringEntryId);
	}

	/**
	* Returns a range of all the license keies where offeringEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public static List<LicenseKey> findByOfferingEntryId(long offeringEntryId,
		int start, int end) {
		return getPersistence()
				   .findByOfferingEntryId(offeringEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByOfferingEntryId(long offeringEntryId,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .findByOfferingEntryId(offeringEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByOfferingEntryId(long offeringEntryId,
		int start, int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByOfferingEntryId(offeringEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByOfferingEntryId_First(long offeringEntryId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOfferingEntryId_First(offeringEntryId,
			orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByOfferingEntryId_First(
		long offeringEntryId, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByOfferingEntryId_First(offeringEntryId,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByOfferingEntryId_Last(long offeringEntryId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOfferingEntryId_Last(offeringEntryId,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByOfferingEntryId_Last(long offeringEntryId,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByOfferingEntryId_Last(offeringEntryId,
			orderByComparator);
	}

	/**
	* Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public static LicenseKey[] findByOfferingEntryId_PrevAndNext(
		long licenseKeyId, long offeringEntryId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOfferingEntryId_PrevAndNext(licenseKeyId,
			offeringEntryId, orderByComparator);
	}

	/**
	* Removes all the license keies where offeringEntryId = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	*/
	public static void removeByOfferingEntryId(long offeringEntryId) {
		getPersistence().removeByOfferingEntryId(offeringEntryId);
	}

	/**
	* Returns the number of license keies where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @return the number of matching license keies
	*/
	public static int countByOfferingEntryId(long offeringEntryId) {
		return getPersistence().countByOfferingEntryId(offeringEntryId);
	}

	/**
	* Returns all the license keies where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the matching license keies
	*/
	public static List<LicenseKey> findByU_AEI(long userId, long accountEntryId) {
		return getPersistence().findByU_AEI(userId, accountEntryId);
	}

	/**
	* Returns a range of all the license keies where userId = &#63; and accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public static List<LicenseKey> findByU_AEI(long userId,
		long accountEntryId, int start, int end) {
		return getPersistence().findByU_AEI(userId, accountEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the license keies where userId = &#63; and accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByU_AEI(long userId,
		long accountEntryId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .findByU_AEI(userId, accountEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the license keies where userId = &#63; and accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByU_AEI(long userId,
		long accountEntryId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_AEI(userId, accountEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByU_AEI_First(long userId,
		long accountEntryId, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByU_AEI_First(userId, accountEntryId, orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByU_AEI_First(long userId,
		long accountEntryId, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByU_AEI_First(userId, accountEntryId, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByU_AEI_Last(long userId, long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByU_AEI_Last(userId, accountEntryId, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByU_AEI_Last(long userId,
		long accountEntryId, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByU_AEI_Last(userId, accountEntryId, orderByComparator);
	}

	/**
	* Returns the license keies before and after the current license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public static LicenseKey[] findByU_AEI_PrevAndNext(long licenseKeyId,
		long userId, long accountEntryId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByU_AEI_PrevAndNext(licenseKeyId, userId,
			accountEntryId, orderByComparator);
	}

	/**
	* Removes all the license keies where userId = &#63; and accountEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	*/
	public static void removeByU_AEI(long userId, long accountEntryId) {
		getPersistence().removeByU_AEI(userId, accountEntryId);
	}

	/**
	* Returns the number of license keies where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the number of matching license keies
	*/
	public static int countByU_AEI(long userId, long accountEntryId) {
		return getPersistence().countByU_AEI(userId, accountEntryId);
	}

	/**
	* Returns all the license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @return the matching license keies
	*/
	public static List<LicenseKey> findByARLI_A(long assetReceiptLicenseId,
		boolean active) {
		return getPersistence().findByARLI_A(assetReceiptLicenseId, active);
	}

	/**
	* Returns a range of all the license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public static List<LicenseKey> findByARLI_A(long assetReceiptLicenseId,
		boolean active, int start, int end) {
		return getPersistence()
				   .findByARLI_A(assetReceiptLicenseId, active, start, end);
	}

	/**
	* Returns an ordered range of all the license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByARLI_A(long assetReceiptLicenseId,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .findByARLI_A(assetReceiptLicenseId, active, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByARLI_A(long assetReceiptLicenseId,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByARLI_A(assetReceiptLicenseId, active, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByARLI_A_First(long assetReceiptLicenseId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByARLI_A_First(assetReceiptLicenseId, active,
			orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByARLI_A_First(long assetReceiptLicenseId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByARLI_A_First(assetReceiptLicenseId, active,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByARLI_A_Last(long assetReceiptLicenseId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByARLI_A_Last(assetReceiptLicenseId, active,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByARLI_A_Last(long assetReceiptLicenseId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByARLI_A_Last(assetReceiptLicenseId, active,
			orderByComparator);
	}

	/**
	* Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public static LicenseKey[] findByARLI_A_PrevAndNext(long licenseKeyId,
		long assetReceiptLicenseId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByARLI_A_PrevAndNext(licenseKeyId,
			assetReceiptLicenseId, active, orderByComparator);
	}

	/**
	* Removes all the license keies where assetReceiptLicenseId = &#63; and active = &#63; from the database.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	*/
	public static void removeByARLI_A(long assetReceiptLicenseId, boolean active) {
		getPersistence().removeByARLI_A(assetReceiptLicenseId, active);
	}

	/**
	* Returns the number of license keies where assetReceiptLicenseId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param active the active
	* @return the number of matching license keies
	*/
	public static int countByARLI_A(long assetReceiptLicenseId, boolean active) {
		return getPersistence().countByARLI_A(assetReceiptLicenseId, active);
	}

	/**
	* Returns all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @return the matching license keies
	*/
	public static List<LicenseKey> findByOEI_CI(long offeringEntryId,
		long clusterId) {
		return getPersistence().findByOEI_CI(offeringEntryId, clusterId);
	}

	/**
	* Returns a range of all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_CI(long offeringEntryId,
		long clusterId, int start, int end) {
		return getPersistence()
				   .findByOEI_CI(offeringEntryId, clusterId, start, end);
	}

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_CI(long offeringEntryId,
		long clusterId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .findByOEI_CI(offeringEntryId, clusterId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_CI(long offeringEntryId,
		long clusterId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByOEI_CI(offeringEntryId, clusterId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByOEI_CI_First(long offeringEntryId,
		long clusterId, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOEI_CI_First(offeringEntryId, clusterId,
			orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByOEI_CI_First(long offeringEntryId,
		long clusterId, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByOEI_CI_First(offeringEntryId, clusterId,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByOEI_CI_Last(long offeringEntryId,
		long clusterId, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOEI_CI_Last(offeringEntryId, clusterId,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByOEI_CI_Last(long offeringEntryId,
		long clusterId, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByOEI_CI_Last(offeringEntryId, clusterId,
			orderByComparator);
	}

	/**
	* Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public static LicenseKey[] findByOEI_CI_PrevAndNext(long licenseKeyId,
		long offeringEntryId, long clusterId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOEI_CI_PrevAndNext(licenseKeyId, offeringEntryId,
			clusterId, orderByComparator);
	}

	/**
	* Removes all the license keies where offeringEntryId = &#63; and clusterId = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	*/
	public static void removeByOEI_CI(long offeringEntryId, long clusterId) {
		getPersistence().removeByOEI_CI(offeringEntryId, clusterId);
	}

	/**
	* Returns the number of license keies where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @return the number of matching license keies
	*/
	public static int countByOEI_CI(long offeringEntryId, long clusterId) {
		return getPersistence().countByOEI_CI(offeringEntryId, clusterId);
	}

	/**
	* Returns all the license keies where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @return the matching license keies
	*/
	public static List<LicenseKey> findByOEI_O(long orderEntryId,
		java.lang.String owner) {
		return getPersistence().findByOEI_O(orderEntryId, owner);
	}

	/**
	* Returns a range of all the license keies where orderEntryId = &#63; and owner = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_O(long orderEntryId,
		java.lang.String owner, int start, int end) {
		return getPersistence().findByOEI_O(orderEntryId, owner, start, end);
	}

	/**
	* Returns an ordered range of all the license keies where orderEntryId = &#63; and owner = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_O(long orderEntryId,
		java.lang.String owner, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .findByOEI_O(orderEntryId, owner, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the license keies where orderEntryId = &#63; and owner = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_O(long orderEntryId,
		java.lang.String owner, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByOEI_O(orderEntryId, owner, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByOEI_O_First(long orderEntryId,
		java.lang.String owner, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOEI_O_First(orderEntryId, owner, orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByOEI_O_First(long orderEntryId,
		java.lang.String owner, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByOEI_O_First(orderEntryId, owner, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByOEI_O_Last(long orderEntryId,
		java.lang.String owner, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOEI_O_Last(orderEntryId, owner, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByOEI_O_Last(long orderEntryId,
		java.lang.String owner, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByOEI_O_Last(orderEntryId, owner, orderByComparator);
	}

	/**
	* Returns the license keies before and after the current license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public static LicenseKey[] findByOEI_O_PrevAndNext(long licenseKeyId,
		long orderEntryId, java.lang.String owner,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOEI_O_PrevAndNext(licenseKeyId, orderEntryId, owner,
			orderByComparator);
	}

	/**
	* Removes all the license keies where orderEntryId = &#63; and owner = &#63; from the database.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	*/
	public static void removeByOEI_O(long orderEntryId, java.lang.String owner) {
		getPersistence().removeByOEI_O(orderEntryId, owner);
	}

	/**
	* Returns the number of license keies where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @return the number of matching license keies
	*/
	public static int countByOEI_O(long orderEntryId, java.lang.String owner) {
		return getPersistence().countByOEI_O(orderEntryId, owner);
	}

	/**
	* Returns all the license keies where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @return the matching license keies
	*/
	public static List<LicenseKey> findByPI_SI(java.lang.String productId,
		java.lang.String serverId) {
		return getPersistence().findByPI_SI(productId, serverId);
	}

	/**
	* Returns a range of all the license keies where productId = &#63; and serverId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public static List<LicenseKey> findByPI_SI(java.lang.String productId,
		java.lang.String serverId, int start, int end) {
		return getPersistence().findByPI_SI(productId, serverId, start, end);
	}

	/**
	* Returns an ordered range of all the license keies where productId = &#63; and serverId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByPI_SI(java.lang.String productId,
		java.lang.String serverId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .findByPI_SI(productId, serverId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the license keies where productId = &#63; and serverId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByPI_SI(java.lang.String productId,
		java.lang.String serverId, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPI_SI(productId, serverId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByPI_SI_First(java.lang.String productId,
		java.lang.String serverId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByPI_SI_First(productId, serverId, orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByPI_SI_First(java.lang.String productId,
		java.lang.String serverId,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByPI_SI_First(productId, serverId, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByPI_SI_Last(java.lang.String productId,
		java.lang.String serverId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByPI_SI_Last(productId, serverId, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByPI_SI_Last(java.lang.String productId,
		java.lang.String serverId,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByPI_SI_Last(productId, serverId, orderByComparator);
	}

	/**
	* Returns the license keies before and after the current license key in the ordered set where productId = &#63; and serverId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param productId the product ID
	* @param serverId the server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public static LicenseKey[] findByPI_SI_PrevAndNext(long licenseKeyId,
		java.lang.String productId, java.lang.String serverId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByPI_SI_PrevAndNext(licenseKeyId, productId, serverId,
			orderByComparator);
	}

	/**
	* Removes all the license keies where productId = &#63; and serverId = &#63; from the database.
	*
	* @param productId the product ID
	* @param serverId the server ID
	*/
	public static void removeByPI_SI(java.lang.String productId,
		java.lang.String serverId) {
		getPersistence().removeByPI_SI(productId, serverId);
	}

	/**
	* Returns the number of license keies where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @return the number of matching license keies
	*/
	public static int countByPI_SI(java.lang.String productId,
		java.lang.String serverId) {
		return getPersistence().countByPI_SI(productId, serverId);
	}

	/**
	* Returns all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @return the matching license keies
	*/
	public static List<LicenseKey> findByU_ARLI_PI(long userId,
		long assetReceiptLicenseId, java.lang.String productId) {
		return getPersistence()
				   .findByU_ARLI_PI(userId, assetReceiptLicenseId, productId);
	}

	/**
	* Returns a range of all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public static List<LicenseKey> findByU_ARLI_PI(long userId,
		long assetReceiptLicenseId, java.lang.String productId, int start,
		int end) {
		return getPersistence()
				   .findByU_ARLI_PI(userId, assetReceiptLicenseId, productId,
			start, end);
	}

	/**
	* Returns an ordered range of all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByU_ARLI_PI(long userId,
		long assetReceiptLicenseId, java.lang.String productId, int start,
		int end, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .findByU_ARLI_PI(userId, assetReceiptLicenseId, productId,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByU_ARLI_PI(long userId,
		long assetReceiptLicenseId, java.lang.String productId, int start,
		int end, OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_ARLI_PI(userId, assetReceiptLicenseId, productId,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByU_ARLI_PI_First(long userId,
		long assetReceiptLicenseId, java.lang.String productId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByU_ARLI_PI_First(userId, assetReceiptLicenseId,
			productId, orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByU_ARLI_PI_First(long userId,
		long assetReceiptLicenseId, java.lang.String productId,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByU_ARLI_PI_First(userId, assetReceiptLicenseId,
			productId, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByU_ARLI_PI_Last(long userId,
		long assetReceiptLicenseId, java.lang.String productId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByU_ARLI_PI_Last(userId, assetReceiptLicenseId,
			productId, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByU_ARLI_PI_Last(long userId,
		long assetReceiptLicenseId, java.lang.String productId,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByU_ARLI_PI_Last(userId, assetReceiptLicenseId,
			productId, orderByComparator);
	}

	/**
	* Returns the license keies before and after the current license key in the ordered set where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public static LicenseKey[] findByU_ARLI_PI_PrevAndNext(long licenseKeyId,
		long userId, long assetReceiptLicenseId, java.lang.String productId,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByU_ARLI_PI_PrevAndNext(licenseKeyId, userId,
			assetReceiptLicenseId, productId, orderByComparator);
	}

	/**
	* Removes all the license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63; from the database.
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	*/
	public static void removeByU_ARLI_PI(long userId,
		long assetReceiptLicenseId, java.lang.String productId) {
		getPersistence()
			.removeByU_ARLI_PI(userId, assetReceiptLicenseId, productId);
	}

	/**
	* Returns the number of license keies where userId = &#63; and assetReceiptLicenseId &gt; &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @return the number of matching license keies
	*/
	public static int countByU_ARLI_PI(long userId, long assetReceiptLicenseId,
		java.lang.String productId) {
		return getPersistence()
				   .countByU_ARLI_PI(userId, assetReceiptLicenseId, productId);
	}

	/**
	* Returns all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @return the matching license keies
	*/
	public static List<LicenseKey> findByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active) {
		return getPersistence()
				   .findByARLI_C_A(assetReceiptLicenseId, complimentary, active);
	}

	/**
	* Returns a range of all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public static List<LicenseKey> findByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active, int start, int end) {
		return getPersistence()
				   .findByARLI_C_A(assetReceiptLicenseId, complimentary,
			active, start, end);
	}

	/**
	* Returns an ordered range of all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .findByARLI_C_A(assetReceiptLicenseId, complimentary,
			active, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByARLI_C_A(assetReceiptLicenseId, complimentary,
			active, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByARLI_C_A_First(long assetReceiptLicenseId,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByARLI_C_A_First(assetReceiptLicenseId, complimentary,
			active, orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByARLI_C_A_First(long assetReceiptLicenseId,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByARLI_C_A_First(assetReceiptLicenseId, complimentary,
			active, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByARLI_C_A_Last(long assetReceiptLicenseId,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByARLI_C_A_Last(assetReceiptLicenseId, complimentary,
			active, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByARLI_C_A_Last(long assetReceiptLicenseId,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByARLI_C_A_Last(assetReceiptLicenseId, complimentary,
			active, orderByComparator);
	}

	/**
	* Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public static LicenseKey[] findByARLI_C_A_PrevAndNext(long licenseKeyId,
		long assetReceiptLicenseId, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByARLI_C_A_PrevAndNext(licenseKeyId,
			assetReceiptLicenseId, complimentary, active, orderByComparator);
	}

	/**
	* Removes all the license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63; from the database.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	*/
	public static void removeByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active) {
		getPersistence()
			.removeByARLI_C_A(assetReceiptLicenseId, complimentary, active);
	}

	/**
	* Returns the number of license keies where assetReceiptLicenseId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param complimentary the complimentary
	* @param active the active
	* @return the number of matching license keies
	*/
	public static int countByARLI_C_A(long assetReceiptLicenseId,
		boolean complimentary, boolean active) {
		return getPersistence()
				   .countByARLI_C_A(assetReceiptLicenseId, complimentary, active);
	}

	/**
	* Returns all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @return the matching license keies
	*/
	public static List<LicenseKey> findByOEI_CI_A(long offeringEntryId,
		long clusterId, boolean active) {
		return getPersistence()
				   .findByOEI_CI_A(offeringEntryId, clusterId, active);
	}

	/**
	* Returns a range of all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_CI_A(long offeringEntryId,
		long clusterId, boolean active, int start, int end) {
		return getPersistence()
				   .findByOEI_CI_A(offeringEntryId, clusterId, active, start,
			end);
	}

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_CI_A(long offeringEntryId,
		long clusterId, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .findByOEI_CI_A(offeringEntryId, clusterId, active, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_CI_A(long offeringEntryId,
		long clusterId, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByOEI_CI_A(offeringEntryId, clusterId, active, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByOEI_CI_A_First(long offeringEntryId,
		long clusterId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOEI_CI_A_First(offeringEntryId, clusterId, active,
			orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByOEI_CI_A_First(long offeringEntryId,
		long clusterId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByOEI_CI_A_First(offeringEntryId, clusterId, active,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByOEI_CI_A_Last(long offeringEntryId,
		long clusterId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOEI_CI_A_Last(offeringEntryId, clusterId, active,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByOEI_CI_A_Last(long offeringEntryId,
		long clusterId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByOEI_CI_A_Last(offeringEntryId, clusterId, active,
			orderByComparator);
	}

	/**
	* Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public static LicenseKey[] findByOEI_CI_A_PrevAndNext(long licenseKeyId,
		long offeringEntryId, long clusterId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOEI_CI_A_PrevAndNext(licenseKeyId, offeringEntryId,
			clusterId, active, orderByComparator);
	}

	/**
	* Removes all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	*/
	public static void removeByOEI_CI_A(long offeringEntryId, long clusterId,
		boolean active) {
		getPersistence().removeByOEI_CI_A(offeringEntryId, clusterId, active);
	}

	/**
	* Returns the number of license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @return the number of matching license keies
	*/
	public static int countByOEI_CI_A(long offeringEntryId, long clusterId,
		boolean active) {
		return getPersistence()
				   .countByOEI_CI_A(offeringEntryId, clusterId, active);
	}

	/**
	* Returns all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @return the matching license keies
	*/
	public static List<LicenseKey> findByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active) {
		return getPersistence()
				   .findByOEI_C_A(offeringEntryId, complimentary, active);
	}

	/**
	* Returns a range of all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active, int start, int end) {
		return getPersistence()
				   .findByOEI_C_A(offeringEntryId, complimentary, active,
			start, end);
	}

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .findByOEI_C_A(offeringEntryId, complimentary, active,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByOEI_C_A(offeringEntryId, complimentary, active,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByOEI_C_A_First(long offeringEntryId,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOEI_C_A_First(offeringEntryId, complimentary, active,
			orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByOEI_C_A_First(long offeringEntryId,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByOEI_C_A_First(offeringEntryId, complimentary,
			active, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByOEI_C_A_Last(long offeringEntryId,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOEI_C_A_Last(offeringEntryId, complimentary, active,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByOEI_C_A_Last(long offeringEntryId,
		boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByOEI_C_A_Last(offeringEntryId, complimentary, active,
			orderByComparator);
	}

	/**
	* Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public static LicenseKey[] findByOEI_C_A_PrevAndNext(long licenseKeyId,
		long offeringEntryId, boolean complimentary, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOEI_C_A_PrevAndNext(licenseKeyId, offeringEntryId,
			complimentary, active, orderByComparator);
	}

	/**
	* Returns all the license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryIds the offering entry IDs
	* @param complimentary the complimentary
	* @param active the active
	* @return the matching license keies
	*/
	public static List<LicenseKey> findByOEI_C_A(long[] offeringEntryIds,
		boolean complimentary, boolean active) {
		return getPersistence()
				   .findByOEI_C_A(offeringEntryIds, complimentary, active);
	}

	/**
	* Returns a range of all the license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryIds the offering entry IDs
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_C_A(long[] offeringEntryIds,
		boolean complimentary, boolean active, int start, int end) {
		return getPersistence()
				   .findByOEI_C_A(offeringEntryIds, complimentary, active,
			start, end);
	}

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryIds the offering entry IDs
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_C_A(long[] offeringEntryIds,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .findByOEI_C_A(offeringEntryIds, complimentary, active,
			start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_C_A(long[] offeringEntryIds,
		boolean complimentary, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByOEI_C_A(offeringEntryIds, complimentary, active,
			start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	*/
	public static void removeByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active) {
		getPersistence().removeByOEI_C_A(offeringEntryId, complimentary, active);
	}

	/**
	* Returns the number of license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @return the number of matching license keies
	*/
	public static int countByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active) {
		return getPersistence()
				   .countByOEI_C_A(offeringEntryId, complimentary, active);
	}

	/**
	* Returns the number of license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryIds the offering entry IDs
	* @param complimentary the complimentary
	* @param active the active
	* @return the number of matching license keies
	*/
	public static int countByOEI_C_A(long[] offeringEntryIds,
		boolean complimentary, boolean active) {
		return getPersistence()
				   .countByOEI_C_A(offeringEntryIds, complimentary, active);
	}

	/**
	* Returns all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @return the matching license keies
	*/
	public static List<LicenseKey> findByPEN_SI_A(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active) {
		return getPersistence()
				   .findByPEN_SI_A(productEntryName, serverId, active);
	}

	/**
	* Returns a range of all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public static List<LicenseKey> findByPEN_SI_A(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, int start, int end) {
		return getPersistence()
				   .findByPEN_SI_A(productEntryName, serverId, active, start,
			end);
	}

	/**
	* Returns an ordered range of all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByPEN_SI_A(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .findByPEN_SI_A(productEntryName, serverId, active, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByPEN_SI_A(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPEN_SI_A(productEntryName, serverId, active, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByPEN_SI_A_First(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByPEN_SI_A_First(productEntryName, serverId, active,
			orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByPEN_SI_A_First(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByPEN_SI_A_First(productEntryName, serverId, active,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByPEN_SI_A_Last(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByPEN_SI_A_Last(productEntryName, serverId, active,
			orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByPEN_SI_A_Last(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByPEN_SI_A_Last(productEntryName, serverId, active,
			orderByComparator);
	}

	/**
	* Returns the license keies before and after the current license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public static LicenseKey[] findByPEN_SI_A_PrevAndNext(long licenseKeyId,
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByPEN_SI_A_PrevAndNext(licenseKeyId, productEntryName,
			serverId, active, orderByComparator);
	}

	/**
	* Removes all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63; from the database.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	*/
	public static void removeByPEN_SI_A(java.lang.String productEntryName,
		java.lang.String serverId, boolean active) {
		getPersistence().removeByPEN_SI_A(productEntryName, serverId, active);
	}

	/**
	* Returns the number of license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @return the number of matching license keies
	*/
	public static int countByPEN_SI_A(java.lang.String productEntryName,
		java.lang.String serverId, boolean active) {
		return getPersistence()
				   .countByPEN_SI_A(productEntryName, serverId, active);
	}

	/**
	* Returns all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @return the matching license keies
	*/
	public static List<LicenseKey> findByARLI_PI_SI_A(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active) {
		return getPersistence()
				   .findByARLI_PI_SI_A(assetReceiptLicenseId, productId,
			serverId, active);
	}

	/**
	* Returns a range of all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public static List<LicenseKey> findByARLI_PI_SI_A(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active, int start, int end) {
		return getPersistence()
				   .findByARLI_PI_SI_A(assetReceiptLicenseId, productId,
			serverId, active, start, end);
	}

	/**
	* Returns an ordered range of all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByARLI_PI_SI_A(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .findByARLI_PI_SI_A(assetReceiptLicenseId, productId,
			serverId, active, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByARLI_PI_SI_A(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByARLI_PI_SI_A(assetReceiptLicenseId, productId,
			serverId, active, start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByARLI_PI_SI_A_First(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByARLI_PI_SI_A_First(assetReceiptLicenseId, productId,
			serverId, active, orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByARLI_PI_SI_A_First(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByARLI_PI_SI_A_First(assetReceiptLicenseId, productId,
			serverId, active, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByARLI_PI_SI_A_Last(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByARLI_PI_SI_A_Last(assetReceiptLicenseId, productId,
			serverId, active, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByARLI_PI_SI_A_Last(
		long assetReceiptLicenseId, java.lang.String productId,
		java.lang.String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByARLI_PI_SI_A_Last(assetReceiptLicenseId, productId,
			serverId, active, orderByComparator);
	}

	/**
	* Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public static LicenseKey[] findByARLI_PI_SI_A_PrevAndNext(
		long licenseKeyId, long assetReceiptLicenseId,
		java.lang.String productId, java.lang.String serverId, boolean active,
		OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByARLI_PI_SI_A_PrevAndNext(licenseKeyId,
			assetReceiptLicenseId, productId, serverId, active,
			orderByComparator);
	}

	/**
	* Removes all the license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63; from the database.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	*/
	public static void removeByARLI_PI_SI_A(long assetReceiptLicenseId,
		java.lang.String productId, java.lang.String serverId, boolean active) {
		getPersistence()
			.removeByARLI_PI_SI_A(assetReceiptLicenseId, productId, serverId,
			active);
	}

	/**
	* Returns the number of license keies where assetReceiptLicenseId = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseId the asset receipt license ID
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @return the number of matching license keies
	*/
	public static int countByARLI_PI_SI_A(long assetReceiptLicenseId,
		java.lang.String productId, java.lang.String serverId, boolean active) {
		return getPersistence()
				   .countByARLI_PI_SI_A(assetReceiptLicenseId, productId,
			serverId, active);
	}

	/**
	* Returns all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @return the matching license keies
	*/
	public static List<LicenseKey> findByOEI_LET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active) {
		return getPersistence()
				   .findByOEI_LET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active);
	}

	/**
	* Returns a range of all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_LET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, int start, int end) {
		return getPersistence()
				   .findByOEI_LET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, start, end);
	}

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_LET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .findByOEI_LET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_LET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByOEI_LET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByOEI_LET_C_A_First(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOEI_LET_C_A_First(offeringEntryId, licenseEntryType,
			complimentary, active, orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByOEI_LET_C_A_First(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByOEI_LET_C_A_First(offeringEntryId, licenseEntryType,
			complimentary, active, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByOEI_LET_C_A_Last(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOEI_LET_C_A_Last(offeringEntryId, licenseEntryType,
			complimentary, active, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByOEI_LET_C_A_Last(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByOEI_LET_C_A_Last(offeringEntryId, licenseEntryType,
			complimentary, active, orderByComparator);
	}

	/**
	* Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public static LicenseKey[] findByOEI_LET_C_A_PrevAndNext(
		long licenseKeyId, long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOEI_LET_C_A_PrevAndNext(licenseKeyId,
			offeringEntryId, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	* Removes all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	*/
	public static void removeByOEI_LET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active) {
		getPersistence()
			.removeByOEI_LET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active);
	}

	/**
	* Returns the number of license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @return the number of matching license keies
	*/
	public static int countByOEI_LET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active) {
		return getPersistence()
				   .countByOEI_LET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active);
	}

	/**
	* Returns all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @return the matching license keies
	*/
	public static List<LicenseKey> findByOEI_NotLET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active) {
		return getPersistence()
				   .findByOEI_NotLET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active);
	}

	/**
	* Returns a range of all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_NotLET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, int start, int end) {
		return getPersistence()
				   .findByOEI_NotLET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, start, end);
	}

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_NotLET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .findByOEI_NotLET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public static List<LicenseKey> findByOEI_NotLET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByOEI_NotLET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByOEI_NotLET_C_A_First(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOEI_NotLET_C_A_First(offeringEntryId,
			licenseEntryType, complimentary, active, orderByComparator);
	}

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByOEI_NotLET_C_A_First(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByOEI_NotLET_C_A_First(offeringEntryId,
			licenseEntryType, complimentary, active, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public static LicenseKey findByOEI_NotLET_C_A_Last(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOEI_NotLET_C_A_Last(offeringEntryId,
			licenseEntryType, complimentary, active, orderByComparator);
	}

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public static LicenseKey fetchByOEI_NotLET_C_A_Last(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence()
				   .fetchByOEI_NotLET_C_A_Last(offeringEntryId,
			licenseEntryType, complimentary, active, orderByComparator);
	}

	/**
	* Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public static LicenseKey[] findByOEI_NotLET_C_A_PrevAndNext(
		long licenseKeyId, long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, OrderByComparator<LicenseKey> orderByComparator)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence()
				   .findByOEI_NotLET_C_A_PrevAndNext(licenseKeyId,
			offeringEntryId, licenseEntryType, complimentary, active,
			orderByComparator);
	}

	/**
	* Removes all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	*/
	public static void removeByOEI_NotLET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active) {
		getPersistence()
			.removeByOEI_NotLET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active);
	}

	/**
	* Returns the number of license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @return the number of matching license keies
	*/
	public static int countByOEI_NotLET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active) {
		return getPersistence()
				   .countByOEI_NotLET_C_A(offeringEntryId, licenseEntryType,
			complimentary, active);
	}

	/**
	* Caches the license key in the entity cache if it is enabled.
	*
	* @param licenseKey the license key
	*/
	public static void cacheResult(LicenseKey licenseKey) {
		getPersistence().cacheResult(licenseKey);
	}

	/**
	* Caches the license keies in the entity cache if it is enabled.
	*
	* @param licenseKeies the license keies
	*/
	public static void cacheResult(List<LicenseKey> licenseKeies) {
		getPersistence().cacheResult(licenseKeies);
	}

	/**
	* Creates a new license key with the primary key. Does not add the license key to the database.
	*
	* @param licenseKeyId the primary key for the new license key
	* @return the new license key
	*/
	public static LicenseKey create(long licenseKeyId) {
		return getPersistence().create(licenseKeyId);
	}

	/**
	* Removes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key that was removed
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public static LicenseKey remove(long licenseKeyId)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence().remove(licenseKeyId);
	}

	public static LicenseKey updateImpl(LicenseKey licenseKey) {
		return getPersistence().updateImpl(licenseKey);
	}

	/**
	* Returns the license key with the primary key or throws a {@link NoSuchLicenseKeyException} if it could not be found.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public static LicenseKey findByPrimaryKey(long licenseKeyId)
		throws com.liferay.osb.exception.NoSuchLicenseKeyException {
		return getPersistence().findByPrimaryKey(licenseKeyId);
	}

	/**
	* Returns the license key with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key, or <code>null</code> if a license key with the primary key could not be found
	*/
	public static LicenseKey fetchByPrimaryKey(long licenseKeyId) {
		return getPersistence().fetchByPrimaryKey(licenseKeyId);
	}

	public static java.util.Map<java.io.Serializable, LicenseKey> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the license keies.
	*
	* @return the license keies
	*/
	public static List<LicenseKey> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the license keies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of license keies
	*/
	public static List<LicenseKey> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the license keies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of license keies
	*/
	public static List<LicenseKey> findAll(int start, int end,
		OrderByComparator<LicenseKey> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the license keies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of license keies
	*/
	public static List<LicenseKey> findAll(int start, int end,
		OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the license keies from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of license keies.
	*
	* @return the number of license keies
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static LicenseKeyPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (LicenseKeyPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					LicenseKeyPersistence.class.getName());

			ReferenceRegistry.registerReference(LicenseKeyUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static LicenseKeyPersistence _persistence;
}