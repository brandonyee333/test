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

import com.liferay.osb.exception.NoSuchLicenseKeySetException;
import com.liferay.osb.model.LicenseKeySet;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the license key set service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.LicenseKeySetPersistenceImpl
 * @see LicenseKeySetUtil
 * @generated
 */
@ProviderType
public interface LicenseKeySetPersistence extends BasePersistence<LicenseKeySet> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LicenseKeySetUtil} to access the license key set persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the license key sets where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching license key sets
	*/
	public java.util.List<LicenseKeySet> findByAccountEntryId(
		long accountEntryId);

	/**
	* Returns a range of all the license key sets where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @return the range of matching license key sets
	*/
	public java.util.List<LicenseKeySet> findByAccountEntryId(
		long accountEntryId, int start, int end);

	/**
	* Returns an ordered range of all the license key sets where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license key sets
	*/
	public java.util.List<LicenseKeySet> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet> orderByComparator);

	/**
	* Returns an ordered range of all the license key sets where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license key sets
	*/
	public java.util.List<LicenseKeySet> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first license key set in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key set
	* @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	*/
	public LicenseKeySet findByAccountEntryId_First(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException;

	/**
	* Returns the first license key set in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key set, or <code>null</code> if a matching license key set could not be found
	*/
	public LicenseKeySet fetchByAccountEntryId_First(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet> orderByComparator);

	/**
	* Returns the last license key set in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key set
	* @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	*/
	public LicenseKeySet findByAccountEntryId_Last(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException;

	/**
	* Returns the last license key set in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key set, or <code>null</code> if a matching license key set could not be found
	*/
	public LicenseKeySet fetchByAccountEntryId_Last(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet> orderByComparator);

	/**
	* Returns the license key sets before and after the current license key set in the ordered set where accountEntryId = &#63;.
	*
	* @param licenseKeySetId the primary key of the current license key set
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key set
	* @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	*/
	public LicenseKeySet[] findByAccountEntryId_PrevAndNext(
		long licenseKeySetId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException;

	/**
	* Removes all the license key sets where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	*/
	public void removeByAccountEntryId(long accountEntryId);

	/**
	* Returns the number of license key sets where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching license key sets
	*/
	public int countByAccountEntryId(long accountEntryId);

	/**
	* Returns all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @return the matching license key sets
	*/
	public java.util.List<LicenseKeySet> findByU_AEI_N(long userId,
		long accountEntryId, java.lang.String name);

	/**
	* Returns a range of all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @return the range of matching license key sets
	*/
	public java.util.List<LicenseKeySet> findByU_AEI_N(long userId,
		long accountEntryId, java.lang.String name, int start, int end);

	/**
	* Returns an ordered range of all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching license key sets
	*/
	public java.util.List<LicenseKeySet> findByU_AEI_N(long userId,
		long accountEntryId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet> orderByComparator);

	/**
	* Returns an ordered range of all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching license key sets
	*/
	public java.util.List<LicenseKeySet> findByU_AEI_N(long userId,
		long accountEntryId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key set
	* @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	*/
	public LicenseKeySet findByU_AEI_N_First(long userId, long accountEntryId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException;

	/**
	* Returns the first license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching license key set, or <code>null</code> if a matching license key set could not be found
	*/
	public LicenseKeySet fetchByU_AEI_N_First(long userId, long accountEntryId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet> orderByComparator);

	/**
	* Returns the last license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key set
	* @throws NoSuchLicenseKeySetException if a matching license key set could not be found
	*/
	public LicenseKeySet findByU_AEI_N_Last(long userId, long accountEntryId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException;

	/**
	* Returns the last license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching license key set, or <code>null</code> if a matching license key set could not be found
	*/
	public LicenseKeySet fetchByU_AEI_N_Last(long userId, long accountEntryId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet> orderByComparator);

	/**
	* Returns the license key sets before and after the current license key set in the ordered set where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* @param licenseKeySetId the primary key of the current license key set
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next license key set
	* @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	*/
	public LicenseKeySet[] findByU_AEI_N_PrevAndNext(long licenseKeySetId,
		long userId, long accountEntryId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet> orderByComparator)
		throws NoSuchLicenseKeySetException;

	/**
	* Removes all the license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63; from the database.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	*/
	public void removeByU_AEI_N(long userId, long accountEntryId,
		java.lang.String name);

	/**
	* Returns the number of license key sets where userId = &#63; and accountEntryId = &#63; and name = &#63;.
	*
	* @param userId the user ID
	* @param accountEntryId the account entry ID
	* @param name the name
	* @return the number of matching license key sets
	*/
	public int countByU_AEI_N(long userId, long accountEntryId,
		java.lang.String name);

	/**
	* Caches the license key set in the entity cache if it is enabled.
	*
	* @param licenseKeySet the license key set
	*/
	public void cacheResult(LicenseKeySet licenseKeySet);

	/**
	* Caches the license key sets in the entity cache if it is enabled.
	*
	* @param licenseKeySets the license key sets
	*/
	public void cacheResult(java.util.List<LicenseKeySet> licenseKeySets);

	/**
	* Creates a new license key set with the primary key. Does not add the license key set to the database.
	*
	* @param licenseKeySetId the primary key for the new license key set
	* @return the new license key set
	*/
	public LicenseKeySet create(long licenseKeySetId);

	/**
	* Removes the license key set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKeySetId the primary key of the license key set
	* @return the license key set that was removed
	* @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	*/
	public LicenseKeySet remove(long licenseKeySetId)
		throws NoSuchLicenseKeySetException;

	public LicenseKeySet updateImpl(LicenseKeySet licenseKeySet);

	/**
	* Returns the license key set with the primary key or throws a {@link NoSuchLicenseKeySetException} if it could not be found.
	*
	* @param licenseKeySetId the primary key of the license key set
	* @return the license key set
	* @throws NoSuchLicenseKeySetException if a license key set with the primary key could not be found
	*/
	public LicenseKeySet findByPrimaryKey(long licenseKeySetId)
		throws NoSuchLicenseKeySetException;

	/**
	* Returns the license key set with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param licenseKeySetId the primary key of the license key set
	* @return the license key set, or <code>null</code> if a license key set with the primary key could not be found
	*/
	public LicenseKeySet fetchByPrimaryKey(long licenseKeySetId);

	@Override
	public java.util.Map<java.io.Serializable, LicenseKeySet> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the license key sets.
	*
	* @return the license key sets
	*/
	public java.util.List<LicenseKeySet> findAll();

	/**
	* Returns a range of all the license key sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @return the range of license key sets
	*/
	public java.util.List<LicenseKeySet> findAll(int start, int end);

	/**
	* Returns an ordered range of all the license key sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of license key sets
	*/
	public java.util.List<LicenseKeySet> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet> orderByComparator);

	/**
	* Returns an ordered range of all the license key sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link LicenseKeySetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of license key sets
	* @param end the upper bound of the range of license key sets (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of license key sets
	*/
	public java.util.List<LicenseKeySet> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseKeySet> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the license key sets from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of license key sets.
	*
	* @return the number of license key sets
	*/
	public int countAll();
}