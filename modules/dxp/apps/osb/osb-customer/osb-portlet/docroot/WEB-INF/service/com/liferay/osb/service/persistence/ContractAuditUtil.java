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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the contract audit service. This utility wraps {@link ContractAuditPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContractAuditPersistence
 * @see ContractAuditPersistenceImpl
 * @generated
 */
public class ContractAuditUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(ContractAudit contractAudit) {
		getPersistence().clearCache(contractAudit);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ContractAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ContractAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ContractAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static ContractAudit update(ContractAudit contractAudit,
		boolean merge) throws SystemException {
		return getPersistence().update(contractAudit, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static ContractAudit update(ContractAudit contractAudit,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(contractAudit, merge, serviceContext);
	}

	/**
	* Caches the contract audit in the entity cache if it is enabled.
	*
	* @param contractAudit the contract audit
	*/
	public static void cacheResult(
		com.liferay.osb.model.ContractAudit contractAudit) {
		getPersistence().cacheResult(contractAudit);
	}

	/**
	* Caches the contract audits in the entity cache if it is enabled.
	*
	* @param contractAudits the contract audits
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.ContractAudit> contractAudits) {
		getPersistence().cacheResult(contractAudits);
	}

	/**
	* Creates a new contract audit with the primary key. Does not add the contract audit to the database.
	*
	* @param contractAuditId the primary key for the new contract audit
	* @return the new contract audit
	*/
	public static com.liferay.osb.model.ContractAudit create(
		long contractAuditId) {
		return getPersistence().create(contractAuditId);
	}

	/**
	* Removes the contract audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractAuditId the primary key of the contract audit
	* @return the contract audit that was removed
	* @throws com.liferay.osb.NoSuchContractAuditException if a contract audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.ContractAudit remove(
		long contractAuditId)
		throws com.liferay.osb.NoSuchContractAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(contractAuditId);
	}

	public static com.liferay.osb.model.ContractAudit updateImpl(
		com.liferay.osb.model.ContractAudit contractAudit, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(contractAudit, merge);
	}

	/**
	* Returns the contract audit with the primary key or throws a {@link com.liferay.osb.NoSuchContractAuditException} if it could not be found.
	*
	* @param contractAuditId the primary key of the contract audit
	* @return the contract audit
	* @throws com.liferay.osb.NoSuchContractAuditException if a contract audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.ContractAudit findByPrimaryKey(
		long contractAuditId)
		throws com.liferay.osb.NoSuchContractAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(contractAuditId);
	}

	/**
	* Returns the contract audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contractAuditId the primary key of the contract audit
	* @return the contract audit, or <code>null</code> if a contract audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.ContractAudit fetchByPrimaryKey(
		long contractAuditId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(contractAuditId);
	}

	/**
	* Returns all the contract audits where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @return the matching contract audits
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.ContractAudit> findByU_CEI(
		long userId, long contractEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_CEI(userId, contractEntryId);
	}

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
	public static java.util.List<com.liferay.osb.model.ContractAudit> findByU_CEI(
		long userId, long contractEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_CEI(userId, contractEntryId, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.ContractAudit> findByU_CEI(
		long userId, long contractEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_CEI(userId, contractEntryId, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.ContractAudit findByU_CEI_First(
		long userId, long contractEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchContractAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_CEI_First(userId, contractEntryId, orderByComparator);
	}

	/**
	* Returns the first contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract audit, or <code>null</code> if a matching contract audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.ContractAudit fetchByU_CEI_First(
		long userId, long contractEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_CEI_First(userId, contractEntryId,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.ContractAudit findByU_CEI_Last(
		long userId, long contractEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchContractAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_CEI_Last(userId, contractEntryId, orderByComparator);
	}

	/**
	* Returns the last contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contract audit, or <code>null</code> if a matching contract audit could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.ContractAudit fetchByU_CEI_Last(
		long userId, long contractEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_CEI_Last(userId, contractEntryId, orderByComparator);
	}

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
	public static com.liferay.osb.model.ContractAudit[] findByU_CEI_PrevAndNext(
		long contractAuditId, long userId, long contractEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchContractAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_CEI_PrevAndNext(contractAuditId, userId,
			contractEntryId, orderByComparator);
	}

	/**
	* Returns all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class p k
	* @return the matching contract audits
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.ContractAudit> findByCEI_SCN_SCP(
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCEI_SCN_SCP(contractEntryId, signatoryClassNameId,
			signatoryClassPK);
	}

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
	public static java.util.List<com.liferay.osb.model.ContractAudit> findByCEI_SCN_SCP(
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCEI_SCN_SCP(contractEntryId, signatoryClassNameId,
			signatoryClassPK, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.ContractAudit> findByCEI_SCN_SCP(
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCEI_SCN_SCP(contractEntryId, signatoryClassNameId,
			signatoryClassPK, start, end, orderByComparator);
	}

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
	public static com.liferay.osb.model.ContractAudit findByCEI_SCN_SCP_First(
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchContractAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCEI_SCN_SCP_First(contractEntryId,
			signatoryClassNameId, signatoryClassPK, orderByComparator);
	}

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
	public static com.liferay.osb.model.ContractAudit fetchByCEI_SCN_SCP_First(
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCEI_SCN_SCP_First(contractEntryId,
			signatoryClassNameId, signatoryClassPK, orderByComparator);
	}

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
	public static com.liferay.osb.model.ContractAudit findByCEI_SCN_SCP_Last(
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchContractAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCEI_SCN_SCP_Last(contractEntryId,
			signatoryClassNameId, signatoryClassPK, orderByComparator);
	}

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
	public static com.liferay.osb.model.ContractAudit fetchByCEI_SCN_SCP_Last(
		long contractEntryId, long signatoryClassNameId, long signatoryClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByCEI_SCN_SCP_Last(contractEntryId,
			signatoryClassNameId, signatoryClassPK, orderByComparator);
	}

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
	public static com.liferay.osb.model.ContractAudit[] findByCEI_SCN_SCP_PrevAndNext(
		long contractAuditId, long contractEntryId, long signatoryClassNameId,
		long signatoryClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchContractAuditException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCEI_SCN_SCP_PrevAndNext(contractAuditId,
			contractEntryId, signatoryClassNameId, signatoryClassPK,
			orderByComparator);
	}

	/**
	* Returns all the contract audits.
	*
	* @return the contract audits
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.ContractAudit> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.osb.model.ContractAudit> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.ContractAudit> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the contract audits where userId = &#63; and contractEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_CEI(long userId, long contractEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_CEI(userId, contractEntryId);
	}

	/**
	* Removes all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63; from the database.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class p k
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeByCEI_SCN_SCP(contractEntryId, signatoryClassNameId,
			signatoryClassPK);
	}

	/**
	* Removes all the contract audits from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of contract audits where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @return the number of matching contract audits
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_CEI(long userId, long contractEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_CEI(userId, contractEntryId);
	}

	/**
	* Returns the number of contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class p k
	* @return the number of matching contract audits
	* @throws SystemException if a system exception occurred
	*/
	public static int countByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByCEI_SCN_SCP(contractEntryId, signatoryClassNameId,
			signatoryClassPK);
	}

	/**
	* Returns the number of contract audits.
	*
	* @return the number of contract audits
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ContractAuditPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ContractAuditPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					ContractAuditPersistence.class.getName());

			ReferenceRegistry.registerReference(ContractAuditUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(ContractAuditPersistence persistence) {
	}

	private static ContractAuditPersistence _persistence;
}