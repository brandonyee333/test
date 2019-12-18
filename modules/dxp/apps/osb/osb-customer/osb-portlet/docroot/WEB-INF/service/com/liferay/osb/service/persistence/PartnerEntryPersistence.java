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

import com.liferay.osb.exception.NoSuchPartnerEntryException;
import com.liferay.osb.model.PartnerEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the partner entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.PartnerEntryPersistenceImpl
 * @see PartnerEntryUtil
 * @generated
 */
@ProviderType
public interface PartnerEntryPersistence extends BasePersistence<PartnerEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PartnerEntryUtil} to access the partner entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the partner entries where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @return the matching partner entries
	*/
	public java.util.List<PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId);

	/**
	* Returns a range of all the partner entries where parentPartnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @return the range of matching partner entries
	*/
	public java.util.List<PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId, int start, int end);

	/**
	* Returns an ordered range of all the partner entries where parentPartnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching partner entries
	*/
	public java.util.List<PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerEntry> orderByComparator);

	/**
	* Returns an ordered range of all the partner entries where parentPartnerEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching partner entries
	*/
	public java.util.List<PartnerEntry> findByParentPartnerEntryId(
		long parentPartnerEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first partner entry in the ordered set where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner entry
	* @throws NoSuchPartnerEntryException if a matching partner entry could not be found
	*/
	public PartnerEntry findByParentPartnerEntryId_First(
		long parentPartnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerEntry> orderByComparator)
		throws NoSuchPartnerEntryException;

	/**
	* Returns the first partner entry in the ordered set where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching partner entry, or <code>null</code> if a matching partner entry could not be found
	*/
	public PartnerEntry fetchByParentPartnerEntryId_First(
		long parentPartnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerEntry> orderByComparator);

	/**
	* Returns the last partner entry in the ordered set where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner entry
	* @throws NoSuchPartnerEntryException if a matching partner entry could not be found
	*/
	public PartnerEntry findByParentPartnerEntryId_Last(
		long parentPartnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerEntry> orderByComparator)
		throws NoSuchPartnerEntryException;

	/**
	* Returns the last partner entry in the ordered set where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching partner entry, or <code>null</code> if a matching partner entry could not be found
	*/
	public PartnerEntry fetchByParentPartnerEntryId_Last(
		long parentPartnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerEntry> orderByComparator);

	/**
	* Returns the partner entries before and after the current partner entry in the ordered set where parentPartnerEntryId = &#63;.
	*
	* @param partnerEntryId the primary key of the current partner entry
	* @param parentPartnerEntryId the parent partner entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next partner entry
	* @throws NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	*/
	public PartnerEntry[] findByParentPartnerEntryId_PrevAndNext(
		long partnerEntryId, long parentPartnerEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerEntry> orderByComparator)
		throws NoSuchPartnerEntryException;

	/**
	* Removes all the partner entries where parentPartnerEntryId = &#63; from the database.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	*/
	public void removeByParentPartnerEntryId(long parentPartnerEntryId);

	/**
	* Returns the number of partner entries where parentPartnerEntryId = &#63;.
	*
	* @param parentPartnerEntryId the parent partner entry ID
	* @return the number of matching partner entries
	*/
	public int countByParentPartnerEntryId(long parentPartnerEntryId);

	/**
	* Returns the partner entry where dossieraAccountKey = &#63; or throws a {@link NoSuchPartnerEntryException} if it could not be found.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the matching partner entry
	* @throws NoSuchPartnerEntryException if a matching partner entry could not be found
	*/
	public PartnerEntry findByDossieraAccountKey(
		java.lang.String dossieraAccountKey) throws NoSuchPartnerEntryException;

	/**
	* Returns the partner entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	*/
	public PartnerEntry fetchByDossieraAccountKey(
		java.lang.String dossieraAccountKey);

	/**
	* Returns the partner entry where dossieraAccountKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossieraAccountKey the dossiera account key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	*/
	public PartnerEntry fetchByDossieraAccountKey(
		java.lang.String dossieraAccountKey, boolean retrieveFromCache);

	/**
	* Removes the partner entry where dossieraAccountKey = &#63; from the database.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the partner entry that was removed
	*/
	public PartnerEntry removeByDossieraAccountKey(
		java.lang.String dossieraAccountKey) throws NoSuchPartnerEntryException;

	/**
	* Returns the number of partner entries where dossieraAccountKey = &#63;.
	*
	* @param dossieraAccountKey the dossiera account key
	* @return the number of matching partner entries
	*/
	public int countByDossieraAccountKey(java.lang.String dossieraAccountKey);

	/**
	* Returns the partner entry where code = &#63; or throws a {@link NoSuchPartnerEntryException} if it could not be found.
	*
	* @param code the code
	* @return the matching partner entry
	* @throws NoSuchPartnerEntryException if a matching partner entry could not be found
	*/
	public PartnerEntry findByCode(java.lang.String code)
		throws NoSuchPartnerEntryException;

	/**
	* Returns the partner entry where code = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param code the code
	* @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	*/
	public PartnerEntry fetchByCode(java.lang.String code);

	/**
	* Returns the partner entry where code = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param code the code
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching partner entry, or <code>null</code> if a matching partner entry could not be found
	*/
	public PartnerEntry fetchByCode(java.lang.String code,
		boolean retrieveFromCache);

	/**
	* Removes the partner entry where code = &#63; from the database.
	*
	* @param code the code
	* @return the partner entry that was removed
	*/
	public PartnerEntry removeByCode(java.lang.String code)
		throws NoSuchPartnerEntryException;

	/**
	* Returns the number of partner entries where code = &#63;.
	*
	* @param code the code
	* @return the number of matching partner entries
	*/
	public int countByCode(java.lang.String code);

	/**
	* Caches the partner entry in the entity cache if it is enabled.
	*
	* @param partnerEntry the partner entry
	*/
	public void cacheResult(PartnerEntry partnerEntry);

	/**
	* Caches the partner entries in the entity cache if it is enabled.
	*
	* @param partnerEntries the partner entries
	*/
	public void cacheResult(java.util.List<PartnerEntry> partnerEntries);

	/**
	* Creates a new partner entry with the primary key. Does not add the partner entry to the database.
	*
	* @param partnerEntryId the primary key for the new partner entry
	* @return the new partner entry
	*/
	public PartnerEntry create(long partnerEntryId);

	/**
	* Removes the partner entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry that was removed
	* @throws NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	*/
	public PartnerEntry remove(long partnerEntryId)
		throws NoSuchPartnerEntryException;

	public PartnerEntry updateImpl(PartnerEntry partnerEntry);

	/**
	* Returns the partner entry with the primary key or throws a {@link NoSuchPartnerEntryException} if it could not be found.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry
	* @throws NoSuchPartnerEntryException if a partner entry with the primary key could not be found
	*/
	public PartnerEntry findByPrimaryKey(long partnerEntryId)
		throws NoSuchPartnerEntryException;

	/**
	* Returns the partner entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry, or <code>null</code> if a partner entry with the primary key could not be found
	*/
	public PartnerEntry fetchByPrimaryKey(long partnerEntryId);

	@Override
	public java.util.Map<java.io.Serializable, PartnerEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the partner entries.
	*
	* @return the partner entries
	*/
	public java.util.List<PartnerEntry> findAll();

	/**
	* Returns a range of all the partner entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @return the range of partner entries
	*/
	public java.util.List<PartnerEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the partner entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of partner entries
	*/
	public java.util.List<PartnerEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerEntry> orderByComparator);

	/**
	* Returns an ordered range of all the partner entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of partner entries
	*/
	public java.util.List<PartnerEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PartnerEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the partner entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of partner entries.
	*
	* @return the number of partner entries
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}