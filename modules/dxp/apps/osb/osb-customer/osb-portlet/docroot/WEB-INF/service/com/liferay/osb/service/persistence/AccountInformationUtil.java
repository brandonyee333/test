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

import com.liferay.osb.model.AccountInformation;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the account information service. This utility wraps {@link AccountInformationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountInformationPersistence
 * @see AccountInformationPersistenceImpl
 * @generated
 */
public class AccountInformationUtil {
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
	public static void clearCache(AccountInformation accountInformation) {
		getPersistence().clearCache(accountInformation);
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
	public static List<AccountInformation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountInformation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountInformation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AccountInformation update(
		AccountInformation accountInformation, boolean merge)
		throws SystemException {
		return getPersistence().update(accountInformation, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AccountInformation update(
		AccountInformation accountInformation, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(accountInformation, merge, serviceContext);
	}

	/**
	* Caches the account information in the entity cache if it is enabled.
	*
	* @param accountInformation the account information
	*/
	public static void cacheResult(
		com.liferay.osb.model.AccountInformation accountInformation) {
		getPersistence().cacheResult(accountInformation);
	}

	/**
	* Caches the account informations in the entity cache if it is enabled.
	*
	* @param accountInformations the account informations
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AccountInformation> accountInformations) {
		getPersistence().cacheResult(accountInformations);
	}

	/**
	* Creates a new account information with the primary key. Does not add the account information to the database.
	*
	* @param accountInformationId the primary key for the new account information
	* @return the new account information
	*/
	public static com.liferay.osb.model.AccountInformation create(
		long accountInformationId) {
		return getPersistence().create(accountInformationId);
	}

	/**
	* Removes the account information with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountInformationId the primary key of the account information
	* @return the account information that was removed
	* @throws com.liferay.osb.NoSuchAccountInformationException if a account information with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountInformation remove(
		long accountInformationId)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(accountInformationId);
	}

	public static com.liferay.osb.model.AccountInformation updateImpl(
		com.liferay.osb.model.AccountInformation accountInformation,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(accountInformation, merge);
	}

	/**
	* Returns the account information with the primary key or throws a {@link com.liferay.osb.NoSuchAccountInformationException} if it could not be found.
	*
	* @param accountInformationId the primary key of the account information
	* @return the account information
	* @throws com.liferay.osb.NoSuchAccountInformationException if a account information with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountInformation findByPrimaryKey(
		long accountInformationId)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(accountInformationId);
	}

	/**
	* Returns the account information with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountInformationId the primary key of the account information
	* @return the account information, or <code>null</code> if a account information with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountInformation fetchByPrimaryKey(
		long accountInformationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(accountInformationId);
	}

	/**
	* Returns all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @return the matching account informations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountInformation> findByAEI_API(
		long accountEntryId, long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAEI_API(accountEntryId, accountProjectId);
	}

	/**
	* Returns a range of all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @return the range of matching account informations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountInformation> findByAEI_API(
		long accountEntryId, long accountProjectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_API(accountEntryId, accountProjectId, start, end);
	}

	/**
	* Returns an ordered range of all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account informations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountInformation> findByAEI_API(
		long accountEntryId, long accountProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_API(accountEntryId, accountProjectId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account information
	* @throws com.liferay.osb.NoSuchAccountInformationException if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountInformation findByAEI_API_First(
		long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_API_First(accountEntryId, accountProjectId,
			orderByComparator);
	}

	/**
	* Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account information, or <code>null</code> if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountInformation fetchByAEI_API_First(
		long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAEI_API_First(accountEntryId, accountProjectId,
			orderByComparator);
	}

	/**
	* Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account information
	* @throws com.liferay.osb.NoSuchAccountInformationException if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountInformation findByAEI_API_Last(
		long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_API_Last(accountEntryId, accountProjectId,
			orderByComparator);
	}

	/**
	* Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account information, or <code>null</code> if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountInformation fetchByAEI_API_Last(
		long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAEI_API_Last(accountEntryId, accountProjectId,
			orderByComparator);
	}

	/**
	* Returns the account informations before and after the current account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountInformationId the primary key of the current account information
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account information
	* @throws com.liferay.osb.NoSuchAccountInformationException if a account information with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountInformation[] findByAEI_API_PrevAndNext(
		long accountInformationId, long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_API_PrevAndNext(accountInformationId,
			accountEntryId, accountProjectId, orderByComparator);
	}

	/**
	* Returns all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @return the matching account informations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountInformation> findByAEI_NotAPI(
		long accountEntryId, long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotAPI(accountEntryId, accountProjectId);
	}

	/**
	* Returns a range of all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @return the range of matching account informations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountInformation> findByAEI_NotAPI(
		long accountEntryId, long accountProjectId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotAPI(accountEntryId, accountProjectId, start,
			end);
	}

	/**
	* Returns an ordered range of all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account informations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountInformation> findByAEI_NotAPI(
		long accountEntryId, long accountProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotAPI(accountEntryId, accountProjectId, start,
			end, orderByComparator);
	}

	/**
	* Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account information
	* @throws com.liferay.osb.NoSuchAccountInformationException if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountInformation findByAEI_NotAPI_First(
		long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotAPI_First(accountEntryId, accountProjectId,
			orderByComparator);
	}

	/**
	* Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account information, or <code>null</code> if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountInformation fetchByAEI_NotAPI_First(
		long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAEI_NotAPI_First(accountEntryId, accountProjectId,
			orderByComparator);
	}

	/**
	* Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account information
	* @throws com.liferay.osb.NoSuchAccountInformationException if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountInformation findByAEI_NotAPI_Last(
		long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotAPI_Last(accountEntryId, accountProjectId,
			orderByComparator);
	}

	/**
	* Returns the last account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account information, or <code>null</code> if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountInformation fetchByAEI_NotAPI_Last(
		long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAEI_NotAPI_Last(accountEntryId, accountProjectId,
			orderByComparator);
	}

	/**
	* Returns the account informations before and after the current account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountInformationId the primary key of the current account information
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account information
	* @throws com.liferay.osb.NoSuchAccountInformationException if a account information with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountInformation[] findByAEI_NotAPI_PrevAndNext(
		long accountInformationId, long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_NotAPI_PrevAndNext(accountInformationId,
			accountEntryId, accountProjectId, orderByComparator);
	}

	/**
	* Returns the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; or throws a {@link com.liferay.osb.NoSuchAccountInformationException} if it could not be found.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @return the matching account information
	* @throws com.liferay.osb.NoSuchAccountInformationException if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountInformation findByAEI_API_FI(
		long accountEntryId, long accountProjectId, int fieldId)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAEI_API_FI(accountEntryId, accountProjectId, fieldId);
	}

	/**
	* Returns the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @return the matching account information, or <code>null</code> if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountInformation fetchByAEI_API_FI(
		long accountEntryId, long accountProjectId, int fieldId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAEI_API_FI(accountEntryId, accountProjectId, fieldId);
	}

	/**
	* Returns the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching account information, or <code>null</code> if a matching account information could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountInformation fetchByAEI_API_FI(
		long accountEntryId, long accountProjectId, int fieldId,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAEI_API_FI(accountEntryId, accountProjectId,
			fieldId, retrieveFromCache);
	}

	/**
	* Returns all the account informations.
	*
	* @return the account informations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountInformation> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the account informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @return the range of account informations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountInformation> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the account informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account informations
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountInformation> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the account informations where accountEntryId = &#63; and accountProjectId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAEI_API(long accountEntryId,
		long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAEI_API(accountEntryId, accountProjectId);
	}

	/**
	* Removes all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAEI_NotAPI(long accountEntryId,
		long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAEI_NotAPI(accountEntryId, accountProjectId);
	}

	/**
	* Removes the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @return the account information that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountInformation removeByAEI_API_FI(
		long accountEntryId, long accountProjectId, int fieldId)
		throws com.liferay.osb.NoSuchAccountInformationException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .removeByAEI_API_FI(accountEntryId, accountProjectId, fieldId);
	}

	/**
	* Removes all the account informations from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @return the number of matching account informations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAEI_API(long accountEntryId, long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAEI_API(accountEntryId, accountProjectId);
	}

	/**
	* Returns the number of account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @return the number of matching account informations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAEI_NotAPI(long accountEntryId,
		long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByAEI_NotAPI(accountEntryId, accountProjectId);
	}

	/**
	* Returns the number of account informations where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @return the number of matching account informations
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAEI_API_FI(long accountEntryId,
		long accountProjectId, int fieldId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByAEI_API_FI(accountEntryId, accountProjectId, fieldId);
	}

	/**
	* Returns the number of account informations.
	*
	* @return the number of account informations
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AccountInformationPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AccountInformationPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AccountInformationPersistence.class.getName());

			ReferenceRegistry.registerReference(AccountInformationUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AccountInformationPersistence persistence) {
	}

	private static AccountInformationPersistence _persistence;
}