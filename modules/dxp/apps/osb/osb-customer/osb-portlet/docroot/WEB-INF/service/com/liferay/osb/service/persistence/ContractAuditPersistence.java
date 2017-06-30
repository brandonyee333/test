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

import com.liferay.osb.model.ContractAudit;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the contract audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContractAuditPersistenceImpl
 * @see ContractAuditUtil
 * @generated
 */
public interface ContractAuditPersistence extends BasePersistence<ContractAudit> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ContractAuditUtil} to access the contract audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the contract audit in the entity cache if it is enabled.
	*
	* @param contractAudit the contract audit
	*/
	public void cacheResult(com.liferay.osb.model.ContractAudit contractAudit);

	/**
	* Caches the contract audits in the entity cache if it is enabled.
	*
	* @param contractAudits the contract audits
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.ContractAudit> contractAudits);

	/**
	* Creates a new contract audit with the primary key. Does not add the contract audit to the database.
	*
	* @param contractAuditId the primary key for the new contract audit
	* @return the new contract audit
	*/
	public com.liferay.osb.model.ContractAudit create(long contractAuditId);

	/**
	* Removes the contract audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractAuditId the primary key of the contract audit
	* @return the contract audit that was removed
	* @throws com.liferay.osb.NoSuchContractAuditException if a contract audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit remove(long contractAuditId)
		throws com.liferay.osb.NoSuchContractAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.ContractAudit updateImpl(
		com.liferay.osb.model.ContractAudit contractAudit, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the contract audit with the primary key or throws a {@link com.liferay.osb.NoSuchContractAuditException} if it could not be found.
	*
	* @param contractAuditId the primary key of the contract audit
	* @return the contract audit
	* @throws com.liferay.osb.NoSuchContractAuditException if a contract audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit findByPrimaryKey(
		long contractAuditId)
		throws com.liferay.osb.NoSuchContractAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the contract audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contractAuditId the primary key of the contract audit
	* @return the contract audit, or <code>null</code> if a contract audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit fetchByPrimaryKey(
		long contractAuditId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the contract audits where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @return the matching contract audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ContractAudit> findByU_CEI(
		long userId, long contractEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the contract audits where userId = &#63; and contractEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @param start the lower bound of the range of contract audits
	* @param end the upper bound of the range of contract audits (not inclusive)
	* @return the range of matching contract audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ContractAudit> findByU_CEI(
		long userId, long contractEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the contract audits where userId = &#63; and contractEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @param start the lower bound of the range of contract audits
	* @param end the upper bound of the range of contract audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching contract audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ContractAudit> findByU_CEI(
		long userId, long contractEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract audit
	* @throws com.liferay.osb.NoSuchContractAuditException if a matching contract audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit findByU_CEI_First(long userId,
		long contractEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchContractAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract audit, or <code>null</code> if a matching contract audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit fetchByU_CEI_First(long userId,
		long contractEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contract audit
	* @throws com.liferay.osb.NoSuchContractAuditException if a matching contract audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit findByU_CEI_Last(long userId,
		long contractEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchContractAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contract audit, or <code>null</code> if a matching contract audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit fetchByU_CEI_Last(long userId,
		long contractEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the contract audits before and after the current contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	*
	* @param contractAuditId the primary key of the current contract audit
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next contract audit
	* @throws com.liferay.osb.NoSuchContractAuditException if a contract audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit[] findByU_CEI_PrevAndNext(
		long contractAuditId, long userId, long contractEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchContractAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class p k
	* @return the matching contract audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ContractAudit> findByCEI_SCN_SCP(
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class p k
	* @param start the lower bound of the range of contract audits
	* @param end the upper bound of the range of contract audits (not inclusive)
	* @return the range of matching contract audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ContractAudit> findByCEI_SCN_SCP(
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class p k
	* @param start the lower bound of the range of contract audits
	* @param end the upper bound of the range of contract audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching contract audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ContractAudit> findByCEI_SCN_SCP(
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract audit
	* @throws com.liferay.osb.NoSuchContractAuditException if a matching contract audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit findByCEI_SCN_SCP_First(
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchContractAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract audit, or <code>null</code> if a matching contract audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit fetchByCEI_SCN_SCP_First(
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contract audit
	* @throws com.liferay.osb.NoSuchContractAuditException if a matching contract audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit findByCEI_SCN_SCP_Last(
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchContractAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contract audit, or <code>null</code> if a matching contract audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit fetchByCEI_SCN_SCP_Last(
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the contract audits before and after the current contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractAuditId the primary key of the current contract audit
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next contract audit
	* @throws com.liferay.osb.NoSuchContractAuditException if a contract audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ContractAudit[] findByCEI_SCN_SCP_PrevAndNext(
		long contractAuditId, long contractEntryId, long signatoryClassNameId,
		long signatoryClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchContractAuditException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the contract audits.
	*
	* @return the contract audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ContractAudit> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the contract audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of contract audits
	* @param end the upper bound of the range of contract audits (not inclusive)
	* @return the range of contract audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ContractAudit> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the contract audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of contract audits
	* @param end the upper bound of the range of contract audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of contract audits
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ContractAudit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the contract audits where userId = &#63; and contractEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_CEI(long userId, long contractEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63; from the database.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class p k
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the contract audits from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of contract audits where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @return the number of matching contract audits
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_CEI(long userId, long contractEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class p k
	* @return the number of matching contract audits
	* @throws SystemException if a system exception occurred
	*/
	public int countByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of contract audits.
	*
	* @return the number of contract audits
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}