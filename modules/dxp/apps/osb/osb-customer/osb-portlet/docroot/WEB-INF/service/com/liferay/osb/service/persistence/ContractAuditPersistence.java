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

import com.liferay.osb.exception.NoSuchContractAuditException;
import com.liferay.osb.model.ContractAudit;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the contract audit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.ContractAuditPersistenceImpl
 * @see ContractAuditUtil
 * @generated
 */
@ProviderType
public interface ContractAuditPersistence extends BasePersistence<ContractAudit> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ContractAuditUtil} to access the contract audit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the contract audits where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @return the matching contract audits
	*/
	public java.util.List<ContractAudit> findByU_CEI(long userId,
		long contractEntryId);

	/**
	* Returns a range of all the contract audits where userId = &#63; and contractEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @param start the lower bound of the range of contract audits
	* @param end the upper bound of the range of contract audits (not inclusive)
	* @return the range of matching contract audits
	*/
	public java.util.List<ContractAudit> findByU_CEI(long userId,
		long contractEntryId, int start, int end);

	/**
	* Returns an ordered range of all the contract audits where userId = &#63; and contractEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @param start the lower bound of the range of contract audits
	* @param end the upper bound of the range of contract audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching contract audits
	*/
	public java.util.List<ContractAudit> findByU_CEI(long userId,
		long contractEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAudit> orderByComparator);

	/**
	* Returns an ordered range of all the contract audits where userId = &#63; and contractEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @param start the lower bound of the range of contract audits
	* @param end the upper bound of the range of contract audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching contract audits
	*/
	public java.util.List<ContractAudit> findByU_CEI(long userId,
		long contractEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAudit> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract audit
	* @throws NoSuchContractAuditException if a matching contract audit could not be found
	*/
	public ContractAudit findByU_CEI_First(long userId, long contractEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAudit> orderByComparator)
		throws NoSuchContractAuditException;

	/**
	* Returns the first contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract audit, or <code>null</code> if a matching contract audit could not be found
	*/
	public ContractAudit fetchByU_CEI_First(long userId, long contractEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAudit> orderByComparator);

	/**
	* Returns the last contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contract audit
	* @throws NoSuchContractAuditException if a matching contract audit could not be found
	*/
	public ContractAudit findByU_CEI_Last(long userId, long contractEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAudit> orderByComparator)
		throws NoSuchContractAuditException;

	/**
	* Returns the last contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contract audit, or <code>null</code> if a matching contract audit could not be found
	*/
	public ContractAudit fetchByU_CEI_Last(long userId, long contractEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAudit> orderByComparator);

	/**
	* Returns the contract audits before and after the current contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	*
	* @param contractAuditId the primary key of the current contract audit
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next contract audit
	* @throws NoSuchContractAuditException if a contract audit with the primary key could not be found
	*/
	public ContractAudit[] findByU_CEI_PrevAndNext(long contractAuditId,
		long userId, long contractEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAudit> orderByComparator)
		throws NoSuchContractAuditException;

	/**
	* Removes all the contract audits where userId = &#63; and contractEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	*/
	public void removeByU_CEI(long userId, long contractEntryId);

	/**
	* Returns the number of contract audits where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @return the number of matching contract audits
	*/
	public int countByU_CEI(long userId, long contractEntryId);

	/**
	* Returns all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class pk
	* @return the matching contract audits
	*/
	public java.util.List<ContractAudit> findByCEI_SCN_SCP(
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK);

	/**
	* Returns a range of all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class pk
	* @param start the lower bound of the range of contract audits
	* @param end the upper bound of the range of contract audits (not inclusive)
	* @return the range of matching contract audits
	*/
	public java.util.List<ContractAudit> findByCEI_SCN_SCP(
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK,
		int start, int end);

	/**
	* Returns an ordered range of all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class pk
	* @param start the lower bound of the range of contract audits
	* @param end the upper bound of the range of contract audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching contract audits
	*/
	public java.util.List<ContractAudit> findByCEI_SCN_SCP(
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAudit> orderByComparator);

	/**
	* Returns an ordered range of all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class pk
	* @param start the lower bound of the range of contract audits
	* @param end the upper bound of the range of contract audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching contract audits
	*/
	public java.util.List<ContractAudit> findByCEI_SCN_SCP(
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAudit> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract audit
	* @throws NoSuchContractAuditException if a matching contract audit could not be found
	*/
	public ContractAudit findByCEI_SCN_SCP_First(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAudit> orderByComparator)
		throws NoSuchContractAuditException;

	/**
	* Returns the first contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract audit, or <code>null</code> if a matching contract audit could not be found
	*/
	public ContractAudit fetchByCEI_SCN_SCP_First(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAudit> orderByComparator);

	/**
	* Returns the last contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contract audit
	* @throws NoSuchContractAuditException if a matching contract audit could not be found
	*/
	public ContractAudit findByCEI_SCN_SCP_Last(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAudit> orderByComparator)
		throws NoSuchContractAuditException;

	/**
	* Returns the last contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contract audit, or <code>null</code> if a matching contract audit could not be found
	*/
	public ContractAudit fetchByCEI_SCN_SCP_Last(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAudit> orderByComparator);

	/**
	* Returns the contract audits before and after the current contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractAuditId the primary key of the current contract audit
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next contract audit
	* @throws NoSuchContractAuditException if a contract audit with the primary key could not be found
	*/
	public ContractAudit[] findByCEI_SCN_SCP_PrevAndNext(long contractAuditId,
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAudit> orderByComparator)
		throws NoSuchContractAuditException;

	/**
	* Removes all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63; from the database.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class pk
	*/
	public void removeByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK);

	/**
	* Returns the number of contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class pk
	* @return the number of matching contract audits
	*/
	public int countByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK);

	/**
	* Caches the contract audit in the entity cache if it is enabled.
	*
	* @param contractAudit the contract audit
	*/
	public void cacheResult(ContractAudit contractAudit);

	/**
	* Caches the contract audits in the entity cache if it is enabled.
	*
	* @param contractAudits the contract audits
	*/
	public void cacheResult(java.util.List<ContractAudit> contractAudits);

	/**
	* Creates a new contract audit with the primary key. Does not add the contract audit to the database.
	*
	* @param contractAuditId the primary key for the new contract audit
	* @return the new contract audit
	*/
	public ContractAudit create(long contractAuditId);

	/**
	* Removes the contract audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractAuditId the primary key of the contract audit
	* @return the contract audit that was removed
	* @throws NoSuchContractAuditException if a contract audit with the primary key could not be found
	*/
	public ContractAudit remove(long contractAuditId)
		throws NoSuchContractAuditException;

	public ContractAudit updateImpl(ContractAudit contractAudit);

	/**
	* Returns the contract audit with the primary key or throws a {@link NoSuchContractAuditException} if it could not be found.
	*
	* @param contractAuditId the primary key of the contract audit
	* @return the contract audit
	* @throws NoSuchContractAuditException if a contract audit with the primary key could not be found
	*/
	public ContractAudit findByPrimaryKey(long contractAuditId)
		throws NoSuchContractAuditException;

	/**
	* Returns the contract audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contractAuditId the primary key of the contract audit
	* @return the contract audit, or <code>null</code> if a contract audit with the primary key could not be found
	*/
	public ContractAudit fetchByPrimaryKey(long contractAuditId);

	@Override
	public java.util.Map<java.io.Serializable, ContractAudit> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the contract audits.
	*
	* @return the contract audits
	*/
	public java.util.List<ContractAudit> findAll();

	/**
	* Returns a range of all the contract audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contract audits
	* @param end the upper bound of the range of contract audits (not inclusive)
	* @return the range of contract audits
	*/
	public java.util.List<ContractAudit> findAll(int start, int end);

	/**
	* Returns an ordered range of all the contract audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contract audits
	* @param end the upper bound of the range of contract audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of contract audits
	*/
	public java.util.List<ContractAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAudit> orderByComparator);

	/**
	* Returns an ordered range of all the contract audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractAuditModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contract audits
	* @param end the upper bound of the range of contract audits (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of contract audits
	*/
	public java.util.List<ContractAudit> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ContractAudit> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the contract audits from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of contract audits.
	*
	* @return the number of contract audits
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}