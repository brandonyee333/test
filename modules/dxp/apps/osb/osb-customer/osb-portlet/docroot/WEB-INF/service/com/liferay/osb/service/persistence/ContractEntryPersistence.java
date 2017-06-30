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

import com.liferay.osb.model.ContractEntry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the contract entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContractEntryPersistenceImpl
 * @see ContractEntryUtil
 * @generated
 */
public interface ContractEntryPersistence extends BasePersistence<ContractEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ContractEntryUtil} to access the contract entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the contract entry in the entity cache if it is enabled.
	*
	* @param contractEntry the contract entry
	*/
	public void cacheResult(com.liferay.osb.model.ContractEntry contractEntry);

	/**
	* Caches the contract entries in the entity cache if it is enabled.
	*
	* @param contractEntries the contract entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.ContractEntry> contractEntries);

	/**
	* Creates a new contract entry with the primary key. Does not add the contract entry to the database.
	*
	* @param contractEntryId the primary key for the new contract entry
	* @return the new contract entry
	*/
	public com.liferay.osb.model.ContractEntry create(long contractEntryId);

	/**
	* Removes the contract entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractEntryId the primary key of the contract entry
	* @return the contract entry that was removed
	* @throws com.liferay.osb.NoSuchContractEntryException if a contract entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractEntry remove(long contractEntryId)
		throws com.liferay.osb.NoSuchContractEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.ContractEntry updateImpl(
		com.liferay.osb.model.ContractEntry contractEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the contract entry with the primary key or throws a {@link com.liferay.osb.NoSuchContractEntryException} if it could not be found.
	*
	* @param contractEntryId the primary key of the contract entry
	* @return the contract entry
	* @throws com.liferay.osb.NoSuchContractEntryException if a contract entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractEntry findByPrimaryKey(
		long contractEntryId)
		throws com.liferay.osb.NoSuchContractEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the contract entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contractEntryId the primary key of the contract entry
	* @return the contract entry, or <code>null</code> if a contract entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractEntry fetchByPrimaryKey(
		long contractEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the contract entries where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the matching contract entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ContractEntry> findByCN_CP_T(
		long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the contract entries where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of contract entries
	* @param end the upper bound of the range of contract entries (not inclusive)
	* @return the range of matching contract entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ContractEntry> findByCN_CP_T(
		long classNameId, long classPK, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the contract entries where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of contract entries
	* @param end the upper bound of the range of contract entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching contract entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ContractEntry> findByCN_CP_T(
		long classNameId, long classPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract entry
	* @throws com.liferay.osb.NoSuchContractEntryException if a matching contract entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractEntry findByCN_CP_T_First(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchContractEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract entry, or <code>null</code> if a matching contract entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractEntry fetchByCN_CP_T_First(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contract entry
	* @throws com.liferay.osb.NoSuchContractEntryException if a matching contract entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractEntry findByCN_CP_T_Last(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchContractEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contract entry, or <code>null</code> if a matching contract entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractEntry fetchByCN_CP_T_Last(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the contract entries before and after the current contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param contractEntryId the primary key of the current contract entry
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next contract entry
	* @throws com.liferay.osb.NoSuchContractEntryException if a contract entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractEntry[] findByCN_CP_T_PrevAndNext(
		long contractEntryId, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchContractEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the contract entries.
	*
	* @return the contract entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ContractEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the contract entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of contract entries
	* @param end the upper bound of the range of contract entries (not inclusive)
	* @return the range of contract entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ContractEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the contract entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of contract entries
	* @param end the upper bound of the range of contract entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of contract entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ContractEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the contract entries where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCN_CP_T(long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the contract entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of contract entries where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the number of matching contract entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByCN_CP_T(long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of contract entries.
	*
	* @return the number of contract entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}