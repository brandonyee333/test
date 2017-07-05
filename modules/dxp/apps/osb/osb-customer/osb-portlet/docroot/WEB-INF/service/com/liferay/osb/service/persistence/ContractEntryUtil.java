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

import com.liferay.osb.model.ContractEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the contract entry service. This utility wraps {@link com.liferay.osb.service.persistence.impl.ContractEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContractEntryPersistence
 * @see com.liferay.osb.service.persistence.impl.ContractEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class ContractEntryUtil {
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
	public static void clearCache(ContractEntry contractEntry) {
		getPersistence().clearCache(contractEntry);
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
	public static List<ContractEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ContractEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ContractEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ContractEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ContractEntry update(ContractEntry contractEntry) {
		return getPersistence().update(contractEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ContractEntry update(ContractEntry contractEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(contractEntry, serviceContext);
	}

	/**
	* Returns all the contract entries where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @return the matching contract entries
	*/
	public static List<ContractEntry> findByCN_CP_T(long classNameId,
		long classPK, int type) {
		return getPersistence().findByCN_CP_T(classNameId, classPK, type);
	}

	/**
	* Returns a range of all the contract entries where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param start the lower bound of the range of contract entries
	* @param end the upper bound of the range of contract entries (not inclusive)
	* @return the range of matching contract entries
	*/
	public static List<ContractEntry> findByCN_CP_T(long classNameId,
		long classPK, int type, int start, int end) {
		return getPersistence()
				   .findByCN_CP_T(classNameId, classPK, type, start, end);
	}

	/**
	* Returns an ordered range of all the contract entries where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param start the lower bound of the range of contract entries
	* @param end the upper bound of the range of contract entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching contract entries
	*/
	public static List<ContractEntry> findByCN_CP_T(long classNameId,
		long classPK, int type, int start, int end,
		OrderByComparator<ContractEntry> orderByComparator) {
		return getPersistence()
				   .findByCN_CP_T(classNameId, classPK, type, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the contract entries where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param start the lower bound of the range of contract entries
	* @param end the upper bound of the range of contract entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching contract entries
	*/
	public static List<ContractEntry> findByCN_CP_T(long classNameId,
		long classPK, int type, int start, int end,
		OrderByComparator<ContractEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByCN_CP_T(classNameId, classPK, type, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract entry
	* @throws NoSuchContractEntryException if a matching contract entry could not be found
	*/
	public static ContractEntry findByCN_CP_T_First(long classNameId,
		long classPK, int type,
		OrderByComparator<ContractEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchContractEntryException {
		return getPersistence()
				   .findByCN_CP_T_First(classNameId, classPK, type,
			orderByComparator);
	}

	/**
	* Returns the first contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching contract entry, or <code>null</code> if a matching contract entry could not be found
	*/
	public static ContractEntry fetchByCN_CP_T_First(long classNameId,
		long classPK, int type,
		OrderByComparator<ContractEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCN_CP_T_First(classNameId, classPK, type,
			orderByComparator);
	}

	/**
	* Returns the last contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contract entry
	* @throws NoSuchContractEntryException if a matching contract entry could not be found
	*/
	public static ContractEntry findByCN_CP_T_Last(long classNameId,
		long classPK, int type,
		OrderByComparator<ContractEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchContractEntryException {
		return getPersistence()
				   .findByCN_CP_T_Last(classNameId, classPK, type,
			orderByComparator);
	}

	/**
	* Returns the last contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching contract entry, or <code>null</code> if a matching contract entry could not be found
	*/
	public static ContractEntry fetchByCN_CP_T_Last(long classNameId,
		long classPK, int type,
		OrderByComparator<ContractEntry> orderByComparator) {
		return getPersistence()
				   .fetchByCN_CP_T_Last(classNameId, classPK, type,
			orderByComparator);
	}

	/**
	* Returns the contract entries before and after the current contract entry in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param contractEntryId the primary key of the current contract entry
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next contract entry
	* @throws NoSuchContractEntryException if a contract entry with the primary key could not be found
	*/
	public static ContractEntry[] findByCN_CP_T_PrevAndNext(
		long contractEntryId, long classNameId, long classPK, int type,
		OrderByComparator<ContractEntry> orderByComparator)
		throws com.liferay.osb.exception.NoSuchContractEntryException {
		return getPersistence()
				   .findByCN_CP_T_PrevAndNext(contractEntryId, classNameId,
			classPK, type, orderByComparator);
	}

	/**
	* Removes all the contract entries where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	*/
	public static void removeByCN_CP_T(long classNameId, long classPK, int type) {
		getPersistence().removeByCN_CP_T(classNameId, classPK, type);
	}

	/**
	* Returns the number of contract entries where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @return the number of matching contract entries
	*/
	public static int countByCN_CP_T(long classNameId, long classPK, int type) {
		return getPersistence().countByCN_CP_T(classNameId, classPK, type);
	}

	/**
	* Caches the contract entry in the entity cache if it is enabled.
	*
	* @param contractEntry the contract entry
	*/
	public static void cacheResult(ContractEntry contractEntry) {
		getPersistence().cacheResult(contractEntry);
	}

	/**
	* Caches the contract entries in the entity cache if it is enabled.
	*
	* @param contractEntries the contract entries
	*/
	public static void cacheResult(List<ContractEntry> contractEntries) {
		getPersistence().cacheResult(contractEntries);
	}

	/**
	* Creates a new contract entry with the primary key. Does not add the contract entry to the database.
	*
	* @param contractEntryId the primary key for the new contract entry
	* @return the new contract entry
	*/
	public static ContractEntry create(long contractEntryId) {
		return getPersistence().create(contractEntryId);
	}

	/**
	* Removes the contract entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param contractEntryId the primary key of the contract entry
	* @return the contract entry that was removed
	* @throws NoSuchContractEntryException if a contract entry with the primary key could not be found
	*/
	public static ContractEntry remove(long contractEntryId)
		throws com.liferay.osb.exception.NoSuchContractEntryException {
		return getPersistence().remove(contractEntryId);
	}

	public static ContractEntry updateImpl(ContractEntry contractEntry) {
		return getPersistence().updateImpl(contractEntry);
	}

	/**
	* Returns the contract entry with the primary key or throws a {@link NoSuchContractEntryException} if it could not be found.
	*
	* @param contractEntryId the primary key of the contract entry
	* @return the contract entry
	* @throws NoSuchContractEntryException if a contract entry with the primary key could not be found
	*/
	public static ContractEntry findByPrimaryKey(long contractEntryId)
		throws com.liferay.osb.exception.NoSuchContractEntryException {
		return getPersistence().findByPrimaryKey(contractEntryId);
	}

	/**
	* Returns the contract entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param contractEntryId the primary key of the contract entry
	* @return the contract entry, or <code>null</code> if a contract entry with the primary key could not be found
	*/
	public static ContractEntry fetchByPrimaryKey(long contractEntryId) {
		return getPersistence().fetchByPrimaryKey(contractEntryId);
	}

	public static java.util.Map<java.io.Serializable, ContractEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the contract entries.
	*
	* @return the contract entries
	*/
	public static List<ContractEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the contract entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contract entries
	* @param end the upper bound of the range of contract entries (not inclusive)
	* @return the range of contract entries
	*/
	public static List<ContractEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the contract entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contract entries
	* @param end the upper bound of the range of contract entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of contract entries
	*/
	public static List<ContractEntry> findAll(int start, int end,
		OrderByComparator<ContractEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the contract entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ContractEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of contract entries
	* @param end the upper bound of the range of contract entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of contract entries
	*/
	public static List<ContractEntry> findAll(int start, int end,
		OrderByComparator<ContractEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the contract entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of contract entries.
	*
	* @return the number of contract entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ContractEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ContractEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					ContractEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(ContractEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static ContractEntryPersistence _persistence;
}