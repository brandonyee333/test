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

import com.liferay.osb.model.AccountCall;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the account call service. This utility wraps {@link AccountCallPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountCallPersistence
 * @see AccountCallPersistenceImpl
 * @generated
 */
public class AccountCallUtil {
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
	public static void clearCache(AccountCall accountCall) {
		getPersistence().clearCache(accountCall);
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
	public static List<AccountCall> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountCall> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountCall> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AccountCall update(AccountCall accountCall, boolean merge)
		throws SystemException {
		return getPersistence().update(accountCall, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AccountCall update(AccountCall accountCall, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(accountCall, merge, serviceContext);
	}

	/**
	* Caches the account call in the entity cache if it is enabled.
	*
	* @param accountCall the account call
	*/
	public static void cacheResult(
		com.liferay.osb.model.AccountCall accountCall) {
		getPersistence().cacheResult(accountCall);
	}

	/**
	* Caches the account calls in the entity cache if it is enabled.
	*
	* @param accountCalls the account calls
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AccountCall> accountCalls) {
		getPersistence().cacheResult(accountCalls);
	}

	/**
	* Creates a new account call with the primary key. Does not add the account call to the database.
	*
	* @param accountCallId the primary key for the new account call
	* @return the new account call
	*/
	public static com.liferay.osb.model.AccountCall create(long accountCallId) {
		return getPersistence().create(accountCallId);
	}

	/**
	* Removes the account call with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountCallId the primary key of the account call
	* @return the account call that was removed
	* @throws com.liferay.osb.NoSuchAccountCallException if a account call with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCall remove(long accountCallId)
		throws com.liferay.osb.NoSuchAccountCallException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(accountCallId);
	}

	public static com.liferay.osb.model.AccountCall updateImpl(
		com.liferay.osb.model.AccountCall accountCall, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(accountCall, merge);
	}

	/**
	* Returns the account call with the primary key or throws a {@link com.liferay.osb.NoSuchAccountCallException} if it could not be found.
	*
	* @param accountCallId the primary key of the account call
	* @return the account call
	* @throws com.liferay.osb.NoSuchAccountCallException if a account call with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCall findByPrimaryKey(
		long accountCallId)
		throws com.liferay.osb.NoSuchAccountCallException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(accountCallId);
	}

	/**
	* Returns the account call with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountCallId the primary key of the account call
	* @return the account call, or <code>null</code> if a account call with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCall fetchByPrimaryKey(
		long accountCallId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(accountCallId);
	}

	/**
	* Returns all the account calls where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account calls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCall> findByAccountEntryId(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	* Returns a range of all the account calls where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account calls
	* @param end the upper bound of the range of account calls (not inclusive)
	* @return the range of matching account calls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCall> findByAccountEntryId(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccountEntryId(accountEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the account calls where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account calls
	* @param end the upper bound of the range of account calls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account calls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCall> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first account call in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account call
	* @throws com.liferay.osb.NoSuchAccountCallException if a matching account call could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCall findByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCallException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_First(accountEntryId, orderByComparator);
	}

	/**
	* Returns the first account call in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account call, or <code>null</code> if a matching account call could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCall fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchAccountCallException if a matching account call could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCall findByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCallException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the last account call in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account call, or <code>null</code> if a matching account call could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCall fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
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
	* @throws com.liferay.osb.NoSuchAccountCallException if a account call with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountCall[] findByAccountEntryId_PrevAndNext(
		long accountCallId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountCallException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_PrevAndNext(accountCallId,
			accountEntryId, orderByComparator);
	}

	/**
	* Returns all the account calls.
	*
	* @return the account calls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCall> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the account calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account calls
	* @param end the upper bound of the range of account calls (not inclusive)
	* @return the range of account calls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCall> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the account calls.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account calls
	* @param end the upper bound of the range of account calls (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account calls
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountCall> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the account calls where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	* Removes all the account calls from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of account calls where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account calls
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	* Returns the number of account calls.
	*
	* @return the number of account calls
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
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

	/**
	 * @deprecated
	 */
	public void setPersistence(AccountCallPersistence persistence) {
	}

	private static AccountCallPersistence _persistence;
}