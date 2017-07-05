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

import com.liferay.osb.model.ContractAudit;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the contract audit service. This utility wraps {@link com.liferay.osb.service.persistence.impl.ContractAuditPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContractAuditPersistence
 * @see com.liferay.osb.service.persistence.impl.ContractAuditPersistenceImpl
 * @generated
 */
@ProviderType
public class ContractAuditUtil {
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
	public static void clearCache(ContractAudit contractAudit) {
		getPersistence().clearCache(contractAudit);
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
	public static List<ContractAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ContractAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ContractAudit> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ContractAudit> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ContractAudit update(ContractAudit contractAudit) {
		return getPersistence().update(contractAudit);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ContractAudit update(ContractAudit contractAudit,
		ServiceContext serviceContext) {
		return getPersistence().update(contractAudit, serviceContext);
	}

	/**
	* Returns all the contract audits where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @return the matching contract audits
	*/
	public static List<ContractAudit> findByU_CEI(long userId,
		long contractEntryId) {
		return getPersistence().findByU_CEI(userId, contractEntryId);
	}

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
	public static List<ContractAudit> findByU_CEI(long userId,
		long contractEntryId, int start, int end) {
		return getPersistence().findByU_CEI(userId, contractEntryId, start, end);
	}

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
	public static List<ContractAudit> findByU_CEI(long userId,
		long contractEntryId, int start, int end,
		OrderByComparator<ContractAudit> orderByComparator) {
		return getPersistence()
				   .findByU_CEI(userId, contractEntryId, start, end,
			orderByComparator);
	}

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
	public static List<ContractAudit> findByU_CEI(long userId,
		long contractEntryId, int start, int end,
		OrderByComparator<ContractAudit> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByU_CEI(userId, contractEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first contract audit in the ordered set where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract audit
	* @throws NoSuchContractAuditException if a matching contract audit could not be found
	*/
	public static ContractAudit findByU_CEI_First(long userId,
		long contractEntryId, OrderByComparator<ContractAudit> orderByComparator)
		throws com.liferay.osb.exception.NoSuchContractAuditException {
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
	*/
	public static ContractAudit fetchByU_CEI_First(long userId,
		long contractEntryId, OrderByComparator<ContractAudit> orderByComparator) {
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
	* @throws NoSuchContractAuditException if a matching contract audit could not be found
	*/
	public static ContractAudit findByU_CEI_Last(long userId,
		long contractEntryId, OrderByComparator<ContractAudit> orderByComparator)
		throws com.liferay.osb.exception.NoSuchContractAuditException {
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
	*/
	public static ContractAudit fetchByU_CEI_Last(long userId,
		long contractEntryId, OrderByComparator<ContractAudit> orderByComparator) {
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
	* @throws NoSuchContractAuditException if a contract audit with the primary key could not be found
	*/
	public static ContractAudit[] findByU_CEI_PrevAndNext(
		long contractAuditId, long userId, long contractEntryId,
		OrderByComparator<ContractAudit> orderByComparator)
		throws com.liferay.osb.exception.NoSuchContractAuditException {
		return getPersistence()
				   .findByU_CEI_PrevAndNext(contractAuditId, userId,
			contractEntryId, orderByComparator);
	}

	/**
	* Removes all the contract audits where userId = &#63; and contractEntryId = &#63; from the database.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	*/
	public static void removeByU_CEI(long userId, long contractEntryId) {
		getPersistence().removeByU_CEI(userId, contractEntryId);
	}

	/**
	* Returns the number of contract audits where userId = &#63; and contractEntryId = &#63;.
	*
	* @param userId the user ID
	* @param contractEntryId the contract entry ID
	* @return the number of matching contract audits
	*/
	public static int countByU_CEI(long userId, long contractEntryId) {
		return getPersistence().countByU_CEI(userId, contractEntryId);
	}

	/**
	* Returns all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class pk
	* @return the matching contract audits
	*/
	public static List<ContractAudit> findByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK) {
		return getPersistence()
				   .findByCEI_SCN_SCP(contractEntryId, signatoryClassNameId,
			signatoryClassPK);
	}

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
	public static List<ContractAudit> findByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK, int start, int end) {
		return getPersistence()
				   .findByCEI_SCN_SCP(contractEntryId, signatoryClassNameId,
			signatoryClassPK, start, end);
	}

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
	public static List<ContractAudit> findByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK, int start, int end,
		OrderByComparator<ContractAudit> orderByComparator) {
		return getPersistence()
				   .findByCEI_SCN_SCP(contractEntryId, signatoryClassNameId,
			signatoryClassPK, start, end, orderByComparator);
	}

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
	public static List<ContractAudit> findByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK, int start, int end,
		OrderByComparator<ContractAudit> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCEI_SCN_SCP(contractEntryId, signatoryClassNameId,
			signatoryClassPK, start, end, orderByComparator, retrieveFromCache);
	}

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
	public static ContractAudit findByCEI_SCN_SCP_First(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK,
		OrderByComparator<ContractAudit> orderByComparator)
		throws com.liferay.osb.exception.NoSuchContractAuditException {
		return getPersistence()
				   .findByCEI_SCN_SCP_First(contractEntryId,
			signatoryClassNameId, signatoryClassPK, orderByComparator);
	}

	/**
	* Returns the first contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract audit, or <code>null</code> if a matching contract audit could not be found
	*/
	public static ContractAudit fetchByCEI_SCN_SCP_First(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK,
		OrderByComparator<ContractAudit> orderByComparator) {
		return getPersistence()
				   .fetchByCEI_SCN_SCP_First(contractEntryId,
			signatoryClassNameId, signatoryClassPK, orderByComparator);
	}

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
	public static ContractAudit findByCEI_SCN_SCP_Last(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK,
		OrderByComparator<ContractAudit> orderByComparator)
		throws com.liferay.osb.exception.NoSuchContractAuditException {
		return getPersistence()
				   .findByCEI_SCN_SCP_Last(contractEntryId,
			signatoryClassNameId, signatoryClassPK, orderByComparator);
	}

	/**
	* Returns the last contract audit in the ordered set where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contract audit, or <code>null</code> if a matching contract audit could not be found
	*/
	public static ContractAudit fetchByCEI_SCN_SCP_Last(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK,
		OrderByComparator<ContractAudit> orderByComparator) {
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
	* @param signatoryClassPK the signatory class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next contract audit
	* @throws NoSuchContractAuditException if a contract audit with the primary key could not be found
	*/
	public static ContractAudit[] findByCEI_SCN_SCP_PrevAndNext(
		long contractAuditId, long contractEntryId, long signatoryClassNameId,
		long signatoryClassPK,
		OrderByComparator<ContractAudit> orderByComparator)
		throws com.liferay.osb.exception.NoSuchContractAuditException {
		return getPersistence()
				   .findByCEI_SCN_SCP_PrevAndNext(contractAuditId,
			contractEntryId, signatoryClassNameId, signatoryClassPK,
			orderByComparator);
	}

	/**
	* Removes all the contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63; from the database.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class pk
	*/
	public static void removeByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK) {
		getPersistence()
			.removeByCEI_SCN_SCP(contractEntryId, signatoryClassNameId,
			signatoryClassPK);
	}

	/**
	* Returns the number of contract audits where contractEntryId = &#63; and signatoryClassNameId = &#63; and signatoryClassPK = &#63;.
	*
	* @param contractEntryId the contract entry ID
	* @param signatoryClassNameId the signatory class name ID
	* @param signatoryClassPK the signatory class pk
	* @return the number of matching contract audits
	*/
	public static int countByCEI_SCN_SCP(long contractEntryId,
		long signatoryClassNameId, long signatoryClassPK) {
		return getPersistence()
				   .countByCEI_SCN_SCP(contractEntryId, signatoryClassNameId,
			signatoryClassPK);
	}

	/**
	* Caches the contract audit in the entity cache if it is enabled.
	*
	* @param contractAudit the contract audit
	*/
	public static void cacheResult(ContractAudit contractAudit) {
		getPersistence().cacheResult(contractAudit);
	}

	/**
	* Caches the contract audits in the entity cache if it is enabled.
	*
	* @param contractAudits the contract audits
	*/
	public static void cacheResult(List<ContractAudit> contractAudits) {
		getPersistence().cacheResult(contractAudits);
	}

	/**
	* Creates a new contract audit with the primary key. Does not add the contract audit to the database.
	*
	* @param contractAuditId the primary key for the new contract audit
	* @return the new contract audit
	*/
	public static ContractAudit create(long contractAuditId) {
		return getPersistence().create(contractAuditId);
	}

	/**
	* Removes the contract audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractAuditId the primary key of the contract audit
	* @return the contract audit that was removed
	* @throws NoSuchContractAuditException if a contract audit with the primary key could not be found
	*/
	public static ContractAudit remove(long contractAuditId)
		throws com.liferay.osb.exception.NoSuchContractAuditException {
		return getPersistence().remove(contractAuditId);
	}

	public static ContractAudit updateImpl(ContractAudit contractAudit) {
		return getPersistence().updateImpl(contractAudit);
	}

	/**
	* Returns the contract audit with the primary key or throws a {@link NoSuchContractAuditException} if it could not be found.
	*
	* @param contractAuditId the primary key of the contract audit
	* @return the contract audit
	* @throws NoSuchContractAuditException if a contract audit with the primary key could not be found
	*/
	public static ContractAudit findByPrimaryKey(long contractAuditId)
		throws com.liferay.osb.exception.NoSuchContractAuditException {
		return getPersistence().findByPrimaryKey(contractAuditId);
	}

	/**
	* Returns the contract audit with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contractAuditId the primary key of the contract audit
	* @return the contract audit, or <code>null</code> if a contract audit with the primary key could not be found
	*/
	public static ContractAudit fetchByPrimaryKey(long contractAuditId) {
		return getPersistence().fetchByPrimaryKey(contractAuditId);
	}

	public static java.util.Map<java.io.Serializable, ContractAudit> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the contract audits.
	*
	* @return the contract audits
	*/
	public static List<ContractAudit> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ContractAudit> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ContractAudit> findAll(int start, int end,
		OrderByComparator<ContractAudit> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ContractAudit> findAll(int start, int end,
		OrderByComparator<ContractAudit> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the contract audits from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of contract audits.
	*
	* @return the number of contract audits
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
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

	private static ContractAuditPersistence _persistence;
}