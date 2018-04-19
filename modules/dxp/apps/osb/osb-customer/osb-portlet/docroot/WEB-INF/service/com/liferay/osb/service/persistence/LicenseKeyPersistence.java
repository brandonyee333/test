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

import com.liferay.osb.exception.NoSuchLicenseKeyException;
import com.liferay.osb.model.LicenseKey;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the license key service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.LicenseKeyPersistenceImpl
 * @see LicenseKeyUtil
 * @generated
 */
@ProviderType
public interface LicenseKeyPersistence extends BasePersistence<LicenseKey> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LicenseKeyUtil} to access the license key persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the license keies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching license keies
	*/
	public java.util.List<LicenseKey> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the license keies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public java.util.List<LicenseKey> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the license keies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public java.util.List<LicenseKey> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns an ordered range of all the license keies where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public java.util.List<LicenseKey> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first license key in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the first license key in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the last license key in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the last license key in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the license keies before and after the current license key in the ordered set where uuid = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public LicenseKey[] findByUuid_PrevAndNext(long licenseKeyId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Removes all the license keies where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of license keies where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching license keies
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns all the license keies where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @return the matching license keies
	*/
	public java.util.List<LicenseKey> findByLicenseKeySetId(
		long licenseKeySetId);

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
	public java.util.List<LicenseKey> findByLicenseKeySetId(
		long licenseKeySetId, int start, int end);

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
	public java.util.List<LicenseKey> findByLicenseKeySetId(
		long licenseKeySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public java.util.List<LicenseKey> findByLicenseKeySetId(
		long licenseKeySetId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByLicenseKeySetId_First(long licenseKeySetId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the first license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByLicenseKeySetId_First(long licenseKeySetId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the last license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByLicenseKeySetId_Last(long licenseKeySetId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the last license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByLicenseKeySetId_Last(long licenseKeySetId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the license keies before and after the current license key in the ordered set where licenseKeySetId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param licenseKeySetId the license key set ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public LicenseKey[] findByLicenseKeySetId_PrevAndNext(long licenseKeyId,
		long licenseKeySetId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Removes all the license keies where licenseKeySetId = &#63; from the database.
	*
	* @param licenseKeySetId the license key set ID
	*/
	public void removeByLicenseKeySetId(long licenseKeySetId);

	/**
	* Returns the number of license keies where licenseKeySetId = &#63;.
	*
	* @param licenseKeySetId the license key set ID
	* @return the number of matching license keies
	*/
	public int countByLicenseKeySetId(long licenseKeySetId);

	/**
	* Returns all the license keies where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching license keies
	*/
	public java.util.List<LicenseKey> findByAccountEntryId(long accountEntryId);

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
	public java.util.List<LicenseKey> findByAccountEntryId(
		long accountEntryId, int start, int end);

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
	public java.util.List<LicenseKey> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public java.util.List<LicenseKey> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first license key in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByAccountEntryId_First(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the first license key in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByAccountEntryId_First(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the last license key in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByAccountEntryId_Last(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the last license key in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByAccountEntryId_Last(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the license keies before and after the current license key in the ordered set where accountEntryId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public LicenseKey[] findByAccountEntryId_PrevAndNext(long licenseKeyId,
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Removes all the license keies where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	*/
	public void removeByAccountEntryId(long accountEntryId);

	/**
	* Returns the number of license keies where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching license keies
	*/
	public int countByAccountEntryId(long accountEntryId);

	/**
	* Returns all the license keies where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @return the matching license keies
	*/
	public java.util.List<LicenseKey> findByOfferingEntryId(
		long offeringEntryId);

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
	public java.util.List<LicenseKey> findByOfferingEntryId(
		long offeringEntryId, int start, int end);

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
	public java.util.List<LicenseKey> findByOfferingEntryId(
		long offeringEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public java.util.List<LicenseKey> findByOfferingEntryId(
		long offeringEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByOfferingEntryId_First(long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByOfferingEntryId_First(long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByOfferingEntryId_Last(long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByOfferingEntryId_Last(long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the license keies before and after the current license key in the ordered set where offeringEntryId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param offeringEntryId the offering entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public LicenseKey[] findByOfferingEntryId_PrevAndNext(long licenseKeyId,
		long offeringEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Removes all the license keies where offeringEntryId = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	*/
	public void removeByOfferingEntryId(long offeringEntryId);

	/**
	* Returns the number of license keies where offeringEntryId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @return the number of matching license keies
	*/
	public int countByOfferingEntryId(long offeringEntryId);

	/**
	* Returns all the license keies where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the matching license keies
	*/
	public java.util.List<LicenseKey> findByU_AEI(long userId,
		long accountEntryId);

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
	public java.util.List<LicenseKey> findByU_AEI(long userId,
		long accountEntryId, int start, int end);

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
	public java.util.List<LicenseKey> findByU_AEI(long userId,
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public java.util.List<LicenseKey> findByU_AEI(long userId,
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByU_AEI_First(long userId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the first license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByU_AEI_First(long userId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the last license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByU_AEI_Last(long userId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the last license key in the ordered set where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByU_AEI_Last(long userId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public LicenseKey[] findByU_AEI_PrevAndNext(long licenseKeyId, long userId,
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Removes all the license keies where userId = &#63; and accountEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	*/
	public void removeByU_AEI(long userId, long accountEntryId);

	/**
	* Returns the number of license keies where userId = &#63; and accountEntryId = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @return the number of matching license keies
	*/
	public int countByU_AEI(long userId, long accountEntryId);

	/**
	* Returns all the license keies where userId = &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param productId the product ID
	* @return the matching license keies
	*/
	public java.util.List<LicenseKey> findByU_PI(long userId,
		java.lang.String productId);

	/**
	* Returns a range of all the license keies where userId = &#63; and productId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param productId the product ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public java.util.List<LicenseKey> findByU_PI(long userId,
		java.lang.String productId, int start, int end);

	/**
	* Returns an ordered range of all the license keies where userId = &#63; and productId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param productId the product ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public java.util.List<LicenseKey> findByU_PI(long userId,
		java.lang.String productId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns an ordered range of all the license keies where userId = &#63; and productId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param productId the product ID
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public java.util.List<LicenseKey> findByU_PI(long userId,
		java.lang.String productId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first license key in the ordered set where userId = &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByU_PI_First(long userId, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the first license key in the ordered set where userId = &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByU_PI_First(long userId,
		java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the last license key in the ordered set where userId = &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByU_PI_Last(long userId, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the last license key in the ordered set where userId = &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByU_PI_Last(long userId, java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the license keies before and after the current license key in the ordered set where userId = &#63; and productId = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param userId the user ID
	* @param productId the product ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public LicenseKey[] findByU_PI_PrevAndNext(long licenseKeyId, long userId,
		java.lang.String productId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Removes all the license keies where userId = &#63; and productId = &#63; from the database.
	*
	* @param userId the user ID
	* @param productId the product ID
	*/
	public void removeByU_PI(long userId, java.lang.String productId);

	/**
	* Returns the number of license keies where userId = &#63; and productId = &#63;.
	*
	* @param userId the user ID
	* @param productId the product ID
	* @return the number of matching license keies
	*/
	public int countByU_PI(long userId, java.lang.String productId);

	/**
	* Returns all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param active the active
	* @return the matching license keies
	*/
	public java.util.List<LicenseKey> findByARLU_A(
		java.lang.String assetReceiptLicenseUuid, boolean active);

	/**
	* Returns a range of all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public java.util.List<LicenseKey> findByARLU_A(
		java.lang.String assetReceiptLicenseUuid, boolean active, int start,
		int end);

	/**
	* Returns an ordered range of all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public java.util.List<LicenseKey> findByARLU_A(
		java.lang.String assetReceiptLicenseUuid, boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns an ordered range of all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public java.util.List<LicenseKey> findByARLU_A(
		java.lang.String assetReceiptLicenseUuid, boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByARLU_A_First(
		java.lang.String assetReceiptLicenseUuid, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByARLU_A_First(
		java.lang.String assetReceiptLicenseUuid, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByARLU_A_Last(
		java.lang.String assetReceiptLicenseUuid, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByARLU_A_Last(
		java.lang.String assetReceiptLicenseUuid, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseUuid = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public LicenseKey[] findByARLU_A_PrevAndNext(long licenseKeyId,
		java.lang.String assetReceiptLicenseUuid, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Removes all the license keies where assetReceiptLicenseUuid = &#63; and active = &#63; from the database.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param active the active
	*/
	public void removeByARLU_A(java.lang.String assetReceiptLicenseUuid,
		boolean active);

	/**
	* Returns the number of license keies where assetReceiptLicenseUuid = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param active the active
	* @return the number of matching license keies
	*/
	public int countByARLU_A(java.lang.String assetReceiptLicenseUuid,
		boolean active);

	/**
	* Returns all the license keies where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @return the matching license keies
	*/
	public java.util.List<LicenseKey> findByOEI_CI(long offeringEntryId,
		long clusterId);

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
	public java.util.List<LicenseKey> findByOEI_CI(long offeringEntryId,
		long clusterId, int start, int end);

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
	public java.util.List<LicenseKey> findByOEI_CI(long offeringEntryId,
		long clusterId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public java.util.List<LicenseKey> findByOEI_CI(long offeringEntryId,
		long clusterId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByOEI_CI_First(long offeringEntryId, long clusterId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByOEI_CI_First(long offeringEntryId, long clusterId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByOEI_CI_Last(long offeringEntryId, long clusterId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByOEI_CI_Last(long offeringEntryId, long clusterId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public LicenseKey[] findByOEI_CI_PrevAndNext(long licenseKeyId,
		long offeringEntryId, long clusterId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Removes all the license keies where offeringEntryId = &#63; and clusterId = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	*/
	public void removeByOEI_CI(long offeringEntryId, long clusterId);

	/**
	* Returns the number of license keies where offeringEntryId = &#63; and clusterId = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @return the number of matching license keies
	*/
	public int countByOEI_CI(long offeringEntryId, long clusterId);

	/**
	* Returns all the license keies where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @return the matching license keies
	*/
	public java.util.List<LicenseKey> findByOEI_O(long orderEntryId,
		java.lang.String owner);

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
	public java.util.List<LicenseKey> findByOEI_O(long orderEntryId,
		java.lang.String owner, int start, int end);

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
	public java.util.List<LicenseKey> findByOEI_O(long orderEntryId,
		java.lang.String owner, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public java.util.List<LicenseKey> findByOEI_O(long orderEntryId,
		java.lang.String owner, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByOEI_O_First(long orderEntryId,
		java.lang.String owner,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the first license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByOEI_O_First(long orderEntryId,
		java.lang.String owner,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the last license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByOEI_O_Last(long orderEntryId,
		java.lang.String owner,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the last license key in the ordered set where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByOEI_O_Last(long orderEntryId,
		java.lang.String owner,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public LicenseKey[] findByOEI_O_PrevAndNext(long licenseKeyId,
		long orderEntryId, java.lang.String owner,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Removes all the license keies where orderEntryId = &#63; and owner = &#63; from the database.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	*/
	public void removeByOEI_O(long orderEntryId, java.lang.String owner);

	/**
	* Returns the number of license keies where orderEntryId = &#63; and owner = &#63;.
	*
	* @param orderEntryId the order entry ID
	* @param owner the owner
	* @return the number of matching license keies
	*/
	public int countByOEI_O(long orderEntryId, java.lang.String owner);

	/**
	* Returns all the license keies where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @return the matching license keies
	*/
	public java.util.List<LicenseKey> findByPI_SI(java.lang.String productId,
		java.lang.String serverId);

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
	public java.util.List<LicenseKey> findByPI_SI(java.lang.String productId,
		java.lang.String serverId, int start, int end);

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
	public java.util.List<LicenseKey> findByPI_SI(java.lang.String productId,
		java.lang.String serverId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public java.util.List<LicenseKey> findByPI_SI(java.lang.String productId,
		java.lang.String serverId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByPI_SI_First(java.lang.String productId,
		java.lang.String serverId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the first license key in the ordered set where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByPI_SI_First(java.lang.String productId,
		java.lang.String serverId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the last license key in the ordered set where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByPI_SI_Last(java.lang.String productId,
		java.lang.String serverId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the last license key in the ordered set where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByPI_SI_Last(java.lang.String productId,
		java.lang.String serverId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public LicenseKey[] findByPI_SI_PrevAndNext(long licenseKeyId,
		java.lang.String productId, java.lang.String serverId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Removes all the license keies where productId = &#63; and serverId = &#63; from the database.
	*
	* @param productId the product ID
	* @param serverId the server ID
	*/
	public void removeByPI_SI(java.lang.String productId,
		java.lang.String serverId);

	/**
	* Returns the number of license keies where productId = &#63; and serverId = &#63;.
	*
	* @param productId the product ID
	* @param serverId the server ID
	* @return the number of matching license keies
	*/
	public int countByPI_SI(java.lang.String productId,
		java.lang.String serverId);

	/**
	* Returns all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param complimentary the complimentary
	* @param active the active
	* @return the matching license keies
	*/
	public java.util.List<LicenseKey> findByARLU_C_A(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active);

	/**
	* Returns a range of all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public java.util.List<LicenseKey> findByARLU_C_A(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active, int start, int end);

	/**
	* Returns an ordered range of all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public java.util.List<LicenseKey> findByARLU_C_A(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns an ordered range of all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param complimentary the complimentary
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public java.util.List<LicenseKey> findByARLU_C_A(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByARLU_C_A_First(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByARLU_C_A_First(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByARLU_C_A_Last(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByARLU_C_A_Last(
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public LicenseKey[] findByARLU_C_A_PrevAndNext(long licenseKeyId,
		java.lang.String assetReceiptLicenseUuid, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Removes all the license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63; from the database.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param complimentary the complimentary
	* @param active the active
	*/
	public void removeByARLU_C_A(java.lang.String assetReceiptLicenseUuid,
		boolean complimentary, boolean active);

	/**
	* Returns the number of license keies where assetReceiptLicenseUuid = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param complimentary the complimentary
	* @param active the active
	* @return the number of matching license keies
	*/
	public int countByARLU_C_A(java.lang.String assetReceiptLicenseUuid,
		boolean complimentary, boolean active);

	/**
	* Returns all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @return the matching license keies
	*/
	public java.util.List<LicenseKey> findByOEI_CI_A(long offeringEntryId,
		long clusterId, boolean active);

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
	public java.util.List<LicenseKey> findByOEI_CI_A(long offeringEntryId,
		long clusterId, boolean active, int start, int end);

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
	public java.util.List<LicenseKey> findByOEI_CI_A(long offeringEntryId,
		long clusterId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public java.util.List<LicenseKey> findByOEI_CI_A(long offeringEntryId,
		long clusterId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

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
	public LicenseKey findByOEI_CI_A_First(long offeringEntryId,
		long clusterId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByOEI_CI_A_First(long offeringEntryId,
		long clusterId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public LicenseKey findByOEI_CI_A_Last(long offeringEntryId, long clusterId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByOEI_CI_A_Last(long offeringEntryId,
		long clusterId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public LicenseKey[] findByOEI_CI_A_PrevAndNext(long licenseKeyId,
		long offeringEntryId, long clusterId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Removes all the license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	*/
	public void removeByOEI_CI_A(long offeringEntryId, long clusterId,
		boolean active);

	/**
	* Returns the number of license keies where offeringEntryId = &#63; and clusterId = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param clusterId the cluster ID
	* @param active the active
	* @return the number of matching license keies
	*/
	public int countByOEI_CI_A(long offeringEntryId, long clusterId,
		boolean active);

	/**
	* Returns all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @return the matching license keies
	*/
	public java.util.List<LicenseKey> findByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active);

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
	public java.util.List<LicenseKey> findByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active, int start, int end);

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
	public java.util.List<LicenseKey> findByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public java.util.List<LicenseKey> findByOEI_C_A(long offeringEntryId,
		boolean complimentary, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

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
	public LicenseKey findByOEI_C_A_First(long offeringEntryId,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the first license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByOEI_C_A_First(long offeringEntryId,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public LicenseKey findByOEI_C_A_Last(long offeringEntryId,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the last license key in the ordered set where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByOEI_C_A_Last(long offeringEntryId,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public LicenseKey[] findByOEI_C_A_PrevAndNext(long licenseKeyId,
		long offeringEntryId, boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

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
	public java.util.List<LicenseKey> findByOEI_C_A(long[] offeringEntryIds,
		boolean complimentary, boolean active);

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
	public java.util.List<LicenseKey> findByOEI_C_A(long[] offeringEntryIds,
		boolean complimentary, boolean active, int start, int end);

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
	public java.util.List<LicenseKey> findByOEI_C_A(long[] offeringEntryIds,
		boolean complimentary, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public java.util.List<LicenseKey> findByOEI_C_A(long[] offeringEntryIds,
		boolean complimentary, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	*/
	public void removeByOEI_C_A(long offeringEntryId, boolean complimentary,
		boolean active);

	/**
	* Returns the number of license keies where offeringEntryId = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param complimentary the complimentary
	* @param active the active
	* @return the number of matching license keies
	*/
	public int countByOEI_C_A(long offeringEntryId, boolean complimentary,
		boolean active);

	/**
	* Returns the number of license keies where offeringEntryId = any &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryIds the offering entry IDs
	* @param complimentary the complimentary
	* @param active the active
	* @return the number of matching license keies
	*/
	public int countByOEI_C_A(long[] offeringEntryIds, boolean complimentary,
		boolean active);

	/**
	* Returns all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @return the matching license keies
	*/
	public java.util.List<LicenseKey> findByPEN_SI_A(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active);

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
	public java.util.List<LicenseKey> findByPEN_SI_A(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, int start, int end);

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
	public java.util.List<LicenseKey> findByPEN_SI_A(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public java.util.List<LicenseKey> findByPEN_SI_A(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

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
	public LicenseKey findByPEN_SI_A_First(java.lang.String productEntryName,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the first license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByPEN_SI_A_First(java.lang.String productEntryName,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public LicenseKey findByPEN_SI_A_Last(java.lang.String productEntryName,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the last license key in the ordered set where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByPEN_SI_A_Last(java.lang.String productEntryName,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public LicenseKey[] findByPEN_SI_A_PrevAndNext(long licenseKeyId,
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Removes all the license keies where productEntryName = &#63; and serverId = &#63; and active = &#63; from the database.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	*/
	public void removeByPEN_SI_A(java.lang.String productEntryName,
		java.lang.String serverId, boolean active);

	/**
	* Returns the number of license keies where productEntryName = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param productEntryName the product entry name
	* @param serverId the server ID
	* @param active the active
	* @return the number of matching license keies
	*/
	public int countByPEN_SI_A(java.lang.String productEntryName,
		java.lang.String serverId, boolean active);

	/**
	* Returns all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @return the matching license keies
	*/
	public java.util.List<LicenseKey> findByARLU_PI_SI_A(
		java.lang.String assetReceiptLicenseUuid, java.lang.String productId,
		java.lang.String serverId, boolean active);

	/**
	* Returns a range of all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of matching license keies
	*/
	public java.util.List<LicenseKey> findByARLU_PI_SI_A(
		java.lang.String assetReceiptLicenseUuid, java.lang.String productId,
		java.lang.String serverId, boolean active, int start, int end);

	/**
	* Returns an ordered range of all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license keies
	*/
	public java.util.List<LicenseKey> findByARLU_PI_SI_A(
		java.lang.String assetReceiptLicenseUuid, java.lang.String productId,
		java.lang.String serverId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns an ordered range of all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license keies
	*/
	public java.util.List<LicenseKey> findByARLU_PI_SI_A(
		java.lang.String assetReceiptLicenseUuid, java.lang.String productId,
		java.lang.String serverId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByARLU_PI_SI_A_First(
		java.lang.String assetReceiptLicenseUuid, java.lang.String productId,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the first license key in the ordered set where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByARLU_PI_SI_A_First(
		java.lang.String assetReceiptLicenseUuid, java.lang.String productId,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key
	* @throws NoSuchLicenseKeyException if a matching license key could not be found
	*/
	public LicenseKey findByARLU_PI_SI_A_Last(
		java.lang.String assetReceiptLicenseUuid, java.lang.String productId,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the last license key in the ordered set where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key, or <code>null</code> if a matching license key could not be found
	*/
	public LicenseKey fetchByARLU_PI_SI_A_Last(
		java.lang.String assetReceiptLicenseUuid, java.lang.String productId,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

	/**
	* Returns the license keies before and after the current license key in the ordered set where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param licenseKeyId the primary key of the current license key
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public LicenseKey[] findByARLU_PI_SI_A_PrevAndNext(long licenseKeyId,
		java.lang.String assetReceiptLicenseUuid, java.lang.String productId,
		java.lang.String serverId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Removes all the license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63; from the database.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	*/
	public void removeByARLU_PI_SI_A(java.lang.String assetReceiptLicenseUuid,
		java.lang.String productId, java.lang.String serverId, boolean active);

	/**
	* Returns the number of license keies where assetReceiptLicenseUuid = &#63; and productId = &#63; and serverId = &#63; and active = &#63;.
	*
	* @param assetReceiptLicenseUuid the asset receipt license uuid
	* @param productId the product ID
	* @param serverId the server ID
	* @param active the active
	* @return the number of matching license keies
	*/
	public int countByARLU_PI_SI_A(java.lang.String assetReceiptLicenseUuid,
		java.lang.String productId, java.lang.String serverId, boolean active);

	/**
	* Returns all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @return the matching license keies
	*/
	public java.util.List<LicenseKey> findByOEI_LET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active);

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
	public java.util.List<LicenseKey> findByOEI_LET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, int start, int end);

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
	public java.util.List<LicenseKey> findByOEI_LET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public java.util.List<LicenseKey> findByOEI_LET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

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
	public LicenseKey findByOEI_LET_C_A_First(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

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
	public LicenseKey fetchByOEI_LET_C_A_First(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public LicenseKey findByOEI_LET_C_A_Last(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

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
	public LicenseKey fetchByOEI_LET_C_A_Last(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public LicenseKey[] findByOEI_LET_C_A_PrevAndNext(long licenseKeyId,
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Removes all the license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	*/
	public void removeByOEI_LET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active);

	/**
	* Returns the number of license keies where offeringEntryId = &#63; and licenseEntryType = &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @return the number of matching license keies
	*/
	public int countByOEI_LET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active);

	/**
	* Returns all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @return the matching license keies
	*/
	public java.util.List<LicenseKey> findByOEI_NotLET_C_A(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active);

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
	public java.util.List<LicenseKey> findByOEI_NotLET_C_A(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active, int start, int end);

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
	public java.util.List<LicenseKey> findByOEI_NotLET_C_A(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public java.util.List<LicenseKey> findByOEI_NotLET_C_A(
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

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
	public LicenseKey findByOEI_NotLET_C_A_First(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

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
	public LicenseKey fetchByOEI_NotLET_C_A_First(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public LicenseKey findByOEI_NotLET_C_A_Last(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

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
	public LicenseKey fetchByOEI_NotLET_C_A_Last(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public LicenseKey[] findByOEI_NotLET_C_A_PrevAndNext(long licenseKeyId,
		long offeringEntryId, java.lang.String licenseEntryType,
		boolean complimentary, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator)
		throws NoSuchLicenseKeyException;

	/**
	* Removes all the license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63; from the database.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	*/
	public void removeByOEI_NotLET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active);

	/**
	* Returns the number of license keies where offeringEntryId = &#63; and licenseEntryType &ne; &#63; and complimentary = &#63; and active = &#63;.
	*
	* @param offeringEntryId the offering entry ID
	* @param licenseEntryType the license entry type
	* @param complimentary the complimentary
	* @param active the active
	* @return the number of matching license keies
	*/
	public int countByOEI_NotLET_C_A(long offeringEntryId,
		java.lang.String licenseEntryType, boolean complimentary, boolean active);

	/**
	* Caches the license key in the entity cache if it is enabled.
	*
	* @param licenseKey the license key
	*/
	public void cacheResult(LicenseKey licenseKey);

	/**
	* Caches the license keies in the entity cache if it is enabled.
	*
	* @param licenseKeies the license keies
	*/
	public void cacheResult(java.util.List<LicenseKey> licenseKeies);

	/**
	* Creates a new license key with the primary key. Does not add the license key to the database.
	*
	* @param licenseKeyId the primary key for the new license key
	* @return the new license key
	*/
	public LicenseKey create(long licenseKeyId);

	/**
	* Removes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key that was removed
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public LicenseKey remove(long licenseKeyId)
		throws NoSuchLicenseKeyException;

	public LicenseKey updateImpl(LicenseKey licenseKey);

	/**
	* Returns the license key with the primary key or throws a {@link NoSuchLicenseKeyException} if it could not be found.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key
	* @throws NoSuchLicenseKeyException if a license key with the primary key could not be found
	*/
	public LicenseKey findByPrimaryKey(long licenseKeyId)
		throws NoSuchLicenseKeyException;

	/**
	* Returns the license key with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key, or <code>null</code> if a license key with the primary key could not be found
	*/
	public LicenseKey fetchByPrimaryKey(long licenseKeyId);

	@Override
	public java.util.Map<java.io.Serializable, LicenseKey> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the license keies.
	*
	* @return the license keies
	*/
	public java.util.List<LicenseKey> findAll();

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
	public java.util.List<LicenseKey> findAll(int start, int end);

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
	public java.util.List<LicenseKey> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator);

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
	public java.util.List<LicenseKey> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKey> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the license keies from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of license keies.
	*
	* @return the number of license keies
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}