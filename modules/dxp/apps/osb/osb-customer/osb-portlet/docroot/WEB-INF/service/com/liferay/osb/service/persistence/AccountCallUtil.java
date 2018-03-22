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

import com.liferay.osb.model.AccountCall;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the account call service. This utility wraps {@link com.liferay.osb.service.persistence.impl.AccountCallPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountCallPersistence
 * @see com.liferay.osb.service.persistence.impl.AccountCallPersistenceImpl
 * @generated
 */
@ProviderType
public class AccountCallUtil {
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
	public static void clearCache(AccountCall accountCall) {
		getPersistence().clearCache(accountCall);
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
	public static List<AccountCall> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountCall> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountCall> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AccountCall> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AccountCall update(AccountCall accountCall) {
		return getPersistence().update(accountCall);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AccountCall update(AccountCall accountCall,
		ServiceContext serviceContext) {
		return getPersistence().update(accountCall, serviceContext);
	}

	/**
	* Returns all the account calls where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account calls
	*/
	public static List<AccountCall> findByAccountEntryId(long accountEntryId) {
		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	* Returns a range of all the account calls where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account calls
	* @param end the upper bound of the range of account calls (not inclusive)
	* @return the range of matching account calls
	*/
	public static List<AccountCall> findByAccountEntryId(long accountEntryId,
		int start, int end) {
		return getPersistence().findByAccountEntryId(accountEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the account calls where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account calls
	* @param end the upper bound of the range of account calls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account calls
	*/
	public static List<AccountCall> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<AccountCall> orderByComparator) {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the account calls where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account calls
	* @param end the upper bound of the range of account calls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account calls
	*/
	public static List<AccountCall> findByAccountEntryId(long accountEntryId,
		int start, int end, OrderByComparator<AccountCall> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first account call in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account call
	* @throws NoSuchAccountCallException if a matching account call could not be found
	*/
	public static AccountCall findByAccountEntryId_First(long accountEntryId,
		OrderByComparator<AccountCall> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountCallException {
		return getPersistence()
				   .findByAccountEntryId_First(accountEntryId, orderByComparator);
	}

	/**
	* Returns the first account call in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account call, or <code>null</code> if a matching account call could not be found
	*/
	public static AccountCall fetchByAccountEntryId_First(long accountEntryId,
		OrderByComparator<AccountCall> orderByComparator) {
		return getPersistence()
				   .fetchByAccountEntryId_First(accountEntryId,
			orderByComparator);
	}

	/**
	* Returns the last account call in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account call
	* @throws NoSuchAccountCallException if a matching account call could not be found
	*/
	public static AccountCall findByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountCall> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountCallException {
		return getPersistence()
				   .findByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the last account call in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account call, or <code>null</code> if a matching account call could not be found
	*/
	public static AccountCall fetchByAccountEntryId_Last(long accountEntryId,
		OrderByComparator<AccountCall> orderByComparator) {
		return getPersistence()
				   .fetchByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the account calls before and after the current account call in the ordered set where accountEntryId = &#63;.
	*
	* @param accountCallId the primary key of the current account call
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account call
	* @throws NoSuchAccountCallException if a account call with the primary key could not be found
	*/
	public static AccountCall[] findByAccountEntryId_PrevAndNext(
		long accountCallId, long accountEntryId,
		OrderByComparator<AccountCall> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountCallException {
		return getPersistence()
				   .findByAccountEntryId_PrevAndNext(accountCallId,
			accountEntryId, orderByComparator);
	}

	/**
	* Removes all the account calls where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	*/
	public static void removeByAccountEntryId(long accountEntryId) {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	* Returns the number of account calls where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account calls
	*/
	public static int countByAccountEntryId(long accountEntryId) {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	* Caches the account call in the entity cache if it is enabled.
	*
	* @param accountCall the account call
	*/
	public static void cacheResult(AccountCall accountCall) {
		getPersistence().cacheResult(accountCall);
	}

	/**
	* Caches the account calls in the entity cache if it is enabled.
	*
	* @param accountCalls the account calls
	*/
	public static void cacheResult(List<AccountCall> accountCalls) {
		getPersistence().cacheResult(accountCalls);
	}

	/**
	* Creates a new account call with the primary key. Does not add the account call to the database.
	*
	* @param accountCallId the primary key for the new account call
	* @return the new account call
	*/
	public static AccountCall create(long accountCallId) {
		return getPersistence().create(accountCallId);
	}

	/**
	* Removes the account call with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountCallId the primary key of the account call
	* @return the account call that was removed
	* @throws NoSuchAccountCallException if a account call with the primary key could not be found
	*/
	public static AccountCall remove(long accountCallId)
		throws com.liferay.osb.exception.NoSuchAccountCallException {
		return getPersistence().remove(accountCallId);
	}

	public static AccountCall updateImpl(AccountCall accountCall) {
		return getPersistence().updateImpl(accountCall);
	}

	/**
	* Returns the account call with the primary key or throws a {@link NoSuchAccountCallException} if it could not be found.
	*
	* @param accountCallId the primary key of the account call
	* @return the account call
	* @throws NoSuchAccountCallException if a account call with the primary key could not be found
	*/
	public static AccountCall findByPrimaryKey(long accountCallId)
		throws com.liferay.osb.exception.NoSuchAccountCallException {
		return getPersistence().findByPrimaryKey(accountCallId);
	}

	/**
	* Returns the account call with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountCallId the primary key of the account call
	* @return the account call, or <code>null</code> if a account call with the primary key could not be found
	*/
	public static AccountCall fetchByPrimaryKey(long accountCallId) {
		return getPersistence().fetchByPrimaryKey(accountCallId);
	}

	public static java.util.Map<java.io.Serializable, AccountCall> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the account calls.
	*
	* @return the account calls
	*/
	public static List<AccountCall> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the account calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account calls
	* @param end the upper bound of the range of account calls (not inclusive)
	* @return the range of account calls
	*/
	public static List<AccountCall> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the account calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account calls
	* @param end the upper bound of the range of account calls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account calls
	*/
	public static List<AccountCall> findAll(int start, int end,
		OrderByComparator<AccountCall> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountCallModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account calls
	* @param end the upper bound of the range of account calls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of account calls
	*/
	public static List<AccountCall> findAll(int start, int end,
		OrderByComparator<AccountCall> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the account calls from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of account calls.
	*
	* @return the number of account calls
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static AccountCallPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AccountCallPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AccountCallPersistence.class.getName());

			ReferenceRegistry.registerReference(AccountCallUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static AccountCallPersistence _persistence;
}