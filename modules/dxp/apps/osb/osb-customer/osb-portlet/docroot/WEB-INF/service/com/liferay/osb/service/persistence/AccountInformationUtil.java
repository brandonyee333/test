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

import com.liferay.osb.model.AccountInformation;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the account information service. This utility wraps {@link com.liferay.osb.service.persistence.impl.AccountInformationPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountInformationPersistence
 * @see com.liferay.osb.service.persistence.impl.AccountInformationPersistenceImpl
 * @generated
 */
@ProviderType
public class AccountInformationUtil {
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
	public static void clearCache(AccountInformation accountInformation) {
		getPersistence().clearCache(accountInformation);
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
	public static List<AccountInformation> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountInformation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountInformation> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<AccountInformation> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static AccountInformation update(
		AccountInformation accountInformation) {
		return getPersistence().update(accountInformation);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static AccountInformation update(
		AccountInformation accountInformation, ServiceContext serviceContext) {
		return getPersistence().update(accountInformation, serviceContext);
	}

	/**
	* Returns all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @return the matching account informations
	*/
	public static List<AccountInformation> findByAEI_API(long accountEntryId,
		long accountProjectId) {
		return getPersistence().findByAEI_API(accountEntryId, accountProjectId);
	}

	/**
	* Returns a range of all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @return the range of matching account informations
	*/
	public static List<AccountInformation> findByAEI_API(long accountEntryId,
		long accountProjectId, int start, int end) {
		return getPersistence()
				   .findByAEI_API(accountEntryId, accountProjectId, start, end);
	}

	/**
	* Returns an ordered range of all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account informations
	*/
	public static List<AccountInformation> findByAEI_API(long accountEntryId,
		long accountProjectId, int start, int end,
		OrderByComparator<AccountInformation> orderByComparator) {
		return getPersistence()
				   .findByAEI_API(accountEntryId, accountProjectId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account informations
	*/
	public static List<AccountInformation> findByAEI_API(long accountEntryId,
		long accountProjectId, int start, int end,
		OrderByComparator<AccountInformation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAEI_API(accountEntryId, accountProjectId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account information
	* @throws NoSuchAccountInformationException if a matching account information could not be found
	*/
	public static AccountInformation findByAEI_API_First(long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountInformationException {
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
	*/
	public static AccountInformation fetchByAEI_API_First(long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator) {
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
	* @throws NoSuchAccountInformationException if a matching account information could not be found
	*/
	public static AccountInformation findByAEI_API_Last(long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountInformationException {
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
	*/
	public static AccountInformation fetchByAEI_API_Last(long accountEntryId,
		long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator) {
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
	* @throws NoSuchAccountInformationException if a account information with the primary key could not be found
	*/
	public static AccountInformation[] findByAEI_API_PrevAndNext(
		long accountInformationId, long accountEntryId, long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountInformationException {
		return getPersistence()
				   .findByAEI_API_PrevAndNext(accountInformationId,
			accountEntryId, accountProjectId, orderByComparator);
	}

	/**
	* Removes all the account informations where accountEntryId = &#63; and accountProjectId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	*/
	public static void removeByAEI_API(long accountEntryId,
		long accountProjectId) {
		getPersistence().removeByAEI_API(accountEntryId, accountProjectId);
	}

	/**
	* Returns the number of account informations where accountEntryId = &#63; and accountProjectId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @return the number of matching account informations
	*/
	public static int countByAEI_API(long accountEntryId, long accountProjectId) {
		return getPersistence().countByAEI_API(accountEntryId, accountProjectId);
	}

	/**
	* Returns all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @return the matching account informations
	*/
	public static List<AccountInformation> findByAEI_NotAPI(
		long accountEntryId, long accountProjectId) {
		return getPersistence()
				   .findByAEI_NotAPI(accountEntryId, accountProjectId);
	}

	/**
	* Returns a range of all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @return the range of matching account informations
	*/
	public static List<AccountInformation> findByAEI_NotAPI(
		long accountEntryId, long accountProjectId, int start, int end) {
		return getPersistence()
				   .findByAEI_NotAPI(accountEntryId, accountProjectId, start,
			end);
	}

	/**
	* Returns an ordered range of all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account informations
	*/
	public static List<AccountInformation> findByAEI_NotAPI(
		long accountEntryId, long accountProjectId, int start, int end,
		OrderByComparator<AccountInformation> orderByComparator) {
		return getPersistence()
				   .findByAEI_NotAPI(accountEntryId, accountProjectId, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account informations
	*/
	public static List<AccountInformation> findByAEI_NotAPI(
		long accountEntryId, long accountProjectId, int start, int end,
		OrderByComparator<AccountInformation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByAEI_NotAPI(accountEntryId, accountProjectId, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first account information in the ordered set where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account information
	* @throws NoSuchAccountInformationException if a matching account information could not be found
	*/
	public static AccountInformation findByAEI_NotAPI_First(
		long accountEntryId, long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountInformationException {
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
	*/
	public static AccountInformation fetchByAEI_NotAPI_First(
		long accountEntryId, long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator) {
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
	* @throws NoSuchAccountInformationException if a matching account information could not be found
	*/
	public static AccountInformation findByAEI_NotAPI_Last(
		long accountEntryId, long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountInformationException {
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
	*/
	public static AccountInformation fetchByAEI_NotAPI_Last(
		long accountEntryId, long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator) {
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
	* @throws NoSuchAccountInformationException if a account information with the primary key could not be found
	*/
	public static AccountInformation[] findByAEI_NotAPI_PrevAndNext(
		long accountInformationId, long accountEntryId, long accountProjectId,
		OrderByComparator<AccountInformation> orderByComparator)
		throws com.liferay.osb.exception.NoSuchAccountInformationException {
		return getPersistence()
				   .findByAEI_NotAPI_PrevAndNext(accountInformationId,
			accountEntryId, accountProjectId, orderByComparator);
	}

	/**
	* Removes all the account informations where accountEntryId = &#63; and accountProjectId &ne; &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	*/
	public static void removeByAEI_NotAPI(long accountEntryId,
		long accountProjectId) {
		getPersistence().removeByAEI_NotAPI(accountEntryId, accountProjectId);
	}

	/**
	* Returns the number of account informations where accountEntryId = &#63; and accountProjectId &ne; &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @return the number of matching account informations
	*/
	public static int countByAEI_NotAPI(long accountEntryId,
		long accountProjectId) {
		return getPersistence()
				   .countByAEI_NotAPI(accountEntryId, accountProjectId);
	}

	/**
	* Returns the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; or throws a {@link NoSuchAccountInformationException} if it could not be found.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @return the matching account information
	* @throws NoSuchAccountInformationException if a matching account information could not be found
	*/
	public static AccountInformation findByAEI_API_FI(long accountEntryId,
		long accountProjectId, int fieldId)
		throws com.liferay.osb.exception.NoSuchAccountInformationException {
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
	*/
	public static AccountInformation fetchByAEI_API_FI(long accountEntryId,
		long accountProjectId, int fieldId) {
		return getPersistence()
				   .fetchByAEI_API_FI(accountEntryId, accountProjectId, fieldId);
	}

	/**
	* Returns the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching account information, or <code>null</code> if a matching account information could not be found
	*/
	public static AccountInformation fetchByAEI_API_FI(long accountEntryId,
		long accountProjectId, int fieldId, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByAEI_API_FI(accountEntryId, accountProjectId,
			fieldId, retrieveFromCache);
	}

	/**
	* Removes the account information where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @return the account information that was removed
	*/
	public static AccountInformation removeByAEI_API_FI(long accountEntryId,
		long accountProjectId, int fieldId)
		throws com.liferay.osb.exception.NoSuchAccountInformationException {
		return getPersistence()
				   .removeByAEI_API_FI(accountEntryId, accountProjectId, fieldId);
	}

	/**
	* Returns the number of account informations where accountEntryId = &#63; and accountProjectId = &#63; and fieldId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param accountProjectId the account project ID
	* @param fieldId the field ID
	* @return the number of matching account informations
	*/
	public static int countByAEI_API_FI(long accountEntryId,
		long accountProjectId, int fieldId) {
		return getPersistence()
				   .countByAEI_API_FI(accountEntryId, accountProjectId, fieldId);
	}

	/**
	* Caches the account information in the entity cache if it is enabled.
	*
	* @param accountInformation the account information
	*/
	public static void cacheResult(AccountInformation accountInformation) {
		getPersistence().cacheResult(accountInformation);
	}

	/**
	* Caches the account informations in the entity cache if it is enabled.
	*
	* @param accountInformations the account informations
	*/
	public static void cacheResult(List<AccountInformation> accountInformations) {
		getPersistence().cacheResult(accountInformations);
	}

	/**
	* Creates a new account information with the primary key. Does not add the account information to the database.
	*
	* @param accountInformationId the primary key for the new account information
	* @return the new account information
	*/
	public static AccountInformation create(long accountInformationId) {
		return getPersistence().create(accountInformationId);
	}

	/**
	* Removes the account information with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountInformationId the primary key of the account information
	* @return the account information that was removed
	* @throws NoSuchAccountInformationException if a account information with the primary key could not be found
	*/
	public static AccountInformation remove(long accountInformationId)
		throws com.liferay.osb.exception.NoSuchAccountInformationException {
		return getPersistence().remove(accountInformationId);
	}

	public static AccountInformation updateImpl(
		AccountInformation accountInformation) {
		return getPersistence().updateImpl(accountInformation);
	}

	/**
	* Returns the account information with the primary key or throws a {@link NoSuchAccountInformationException} if it could not be found.
	*
	* @param accountInformationId the primary key of the account information
	* @return the account information
	* @throws NoSuchAccountInformationException if a account information with the primary key could not be found
	*/
	public static AccountInformation findByPrimaryKey(long accountInformationId)
		throws com.liferay.osb.exception.NoSuchAccountInformationException {
		return getPersistence().findByPrimaryKey(accountInformationId);
	}

	/**
	* Returns the account information with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountInformationId the primary key of the account information
	* @return the account information, or <code>null</code> if a account information with the primary key could not be found
	*/
	public static AccountInformation fetchByPrimaryKey(
		long accountInformationId) {
		return getPersistence().fetchByPrimaryKey(accountInformationId);
	}

	public static java.util.Map<java.io.Serializable, AccountInformation> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the account informations.
	*
	* @return the account informations
	*/
	public static List<AccountInformation> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the account informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @return the range of account informations
	*/
	public static List<AccountInformation> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the account informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account informations
	*/
	public static List<AccountInformation> findAll(int start, int end,
		OrderByComparator<AccountInformation> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the account informations.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountInformationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account informations
	* @param end the upper bound of the range of account informations (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of account informations
	*/
	public static List<AccountInformation> findAll(int start, int end,
		OrderByComparator<AccountInformation> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the account informations from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of account informations.
	*
	* @return the number of account informations
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
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

	private static AccountInformationPersistence _persistence;
}