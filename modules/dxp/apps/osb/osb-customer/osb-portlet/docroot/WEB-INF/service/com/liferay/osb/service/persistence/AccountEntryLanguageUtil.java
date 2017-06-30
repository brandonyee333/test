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

import com.liferay.osb.model.AccountEntryLanguage;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the account entry language service. This utility wraps {@link AccountEntryLanguagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryLanguagePersistence
 * @see AccountEntryLanguagePersistenceImpl
 * @generated
 */
public class AccountEntryLanguageUtil {
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
	public static void clearCache(AccountEntryLanguage accountEntryLanguage) {
		getPersistence().clearCache(accountEntryLanguage);
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
	public static List<AccountEntryLanguage> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AccountEntryLanguage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AccountEntryLanguage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AccountEntryLanguage update(
		AccountEntryLanguage accountEntryLanguage, boolean merge)
		throws SystemException {
		return getPersistence().update(accountEntryLanguage, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AccountEntryLanguage update(
		AccountEntryLanguage accountEntryLanguage, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(accountEntryLanguage, merge, serviceContext);
	}

	/**
	* Caches the account entry language in the entity cache if it is enabled.
	*
	* @param accountEntryLanguage the account entry language
	*/
	public static void cacheResult(
		com.liferay.osb.model.AccountEntryLanguage accountEntryLanguage) {
		getPersistence().cacheResult(accountEntryLanguage);
	}

	/**
	* Caches the account entry languages in the entity cache if it is enabled.
	*
	* @param accountEntryLanguages the account entry languages
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AccountEntryLanguage> accountEntryLanguages) {
		getPersistence().cacheResult(accountEntryLanguages);
	}

	/**
	* Creates a new account entry language with the primary key. Does not add the account entry language to the database.
	*
	* @param accountEntryLanguageId the primary key for the new account entry language
	* @return the new account entry language
	*/
	public static com.liferay.osb.model.AccountEntryLanguage create(
		long accountEntryLanguageId) {
		return getPersistence().create(accountEntryLanguageId);
	}

	/**
	* Removes the account entry language with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryLanguageId the primary key of the account entry language
	* @return the account entry language that was removed
	* @throws com.liferay.osb.NoSuchAccountEntryLanguageException if a account entry language with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntryLanguage remove(
		long accountEntryLanguageId)
		throws com.liferay.osb.NoSuchAccountEntryLanguageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(accountEntryLanguageId);
	}

	public static com.liferay.osb.model.AccountEntryLanguage updateImpl(
		com.liferay.osb.model.AccountEntryLanguage accountEntryLanguage,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(accountEntryLanguage, merge);
	}

	/**
	* Returns the account entry language with the primary key or throws a {@link com.liferay.osb.NoSuchAccountEntryLanguageException} if it could not be found.
	*
	* @param accountEntryLanguageId the primary key of the account entry language
	* @return the account entry language
	* @throws com.liferay.osb.NoSuchAccountEntryLanguageException if a account entry language with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntryLanguage findByPrimaryKey(
		long accountEntryLanguageId)
		throws com.liferay.osb.NoSuchAccountEntryLanguageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(accountEntryLanguageId);
	}

	/**
	* Returns the account entry language with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountEntryLanguageId the primary key of the account entry language
	* @return the account entry language, or <code>null</code> if a account entry language with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntryLanguage fetchByPrimaryKey(
		long accountEntryLanguageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(accountEntryLanguageId);
	}

	/**
	* Returns all the account entry languages where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account entry languages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntryLanguage> findByAccountEntryId(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	* Returns a range of all the account entry languages where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account entry languages
	* @param end the upper bound of the range of account entry languages (not inclusive)
	* @return the range of matching account entry languages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntryLanguage> findByAccountEntryId(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccountEntryId(accountEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the account entry languages where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account entry languages
	* @param end the upper bound of the range of account entry languages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entry languages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntryLanguage> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId(accountEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first account entry language in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry language
	* @throws com.liferay.osb.NoSuchAccountEntryLanguageException if a matching account entry language could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntryLanguage findByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryLanguageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_First(accountEntryId, orderByComparator);
	}

	/**
	* Returns the first account entry language in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry language, or <code>null</code> if a matching account entry language could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntryLanguage fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAccountEntryId_First(accountEntryId,
			orderByComparator);
	}

	/**
	* Returns the last account entry language in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry language
	* @throws com.liferay.osb.NoSuchAccountEntryLanguageException if a matching account entry language could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntryLanguage findByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryLanguageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the last account entry language in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry language, or <code>null</code> if a matching account entry language could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntryLanguage fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByAccountEntryId_Last(accountEntryId, orderByComparator);
	}

	/**
	* Returns the account entry languages before and after the current account entry language in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryLanguageId the primary key of the current account entry language
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry language
	* @throws com.liferay.osb.NoSuchAccountEntryLanguageException if a account entry language with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AccountEntryLanguage[] findByAccountEntryId_PrevAndNext(
		long accountEntryLanguageId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryLanguageException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountEntryId_PrevAndNext(accountEntryLanguageId,
			accountEntryId, orderByComparator);
	}

	/**
	* Returns all the account entry languages.
	*
	* @return the account entry languages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntryLanguage> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the account entry languages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account entry languages
	* @param end the upper bound of the range of account entry languages (not inclusive)
	* @return the range of account entry languages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntryLanguage> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the account entry languages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account entry languages
	* @param end the upper bound of the range of account entry languages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account entry languages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AccountEntryLanguage> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the account entry languages where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	* Removes all the account entry languages from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of account entry languages where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account entry languages
	* @throws SystemException if a system exception occurred
	*/
	public static int countByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	* Returns the number of account entry languages.
	*
	* @return the number of account entry languages
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AccountEntryLanguagePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AccountEntryLanguagePersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AccountEntryLanguagePersistence.class.getName());

			ReferenceRegistry.registerReference(AccountEntryLanguageUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AccountEntryLanguagePersistence persistence) {
	}

	private static AccountEntryLanguagePersistence _persistence;
}