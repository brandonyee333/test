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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the account entry language service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryLanguagePersistenceImpl
 * @see AccountEntryLanguageUtil
 * @generated
 */
public interface AccountEntryLanguagePersistence extends BasePersistence<AccountEntryLanguage> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountEntryLanguageUtil} to access the account entry language persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the account entry language in the entity cache if it is enabled.
	*
	* @param accountEntryLanguage the account entry language
	*/
	public void cacheResult(
		com.liferay.osb.model.AccountEntryLanguage accountEntryLanguage);

	/**
	* Caches the account entry languages in the entity cache if it is enabled.
	*
	* @param accountEntryLanguages the account entry languages
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AccountEntryLanguage> accountEntryLanguages);

	/**
	* Creates a new account entry language with the primary key. Does not add the account entry language to the database.
	*
	* @param accountEntryLanguageId the primary key for the new account entry language
	* @return the new account entry language
	*/
	public com.liferay.osb.model.AccountEntryLanguage create(
		long accountEntryLanguageId);

	/**
	* Removes the account entry language with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryLanguageId the primary key of the account entry language
	* @return the account entry language that was removed
	* @throws com.liferay.osb.NoSuchAccountEntryLanguageException if a account entry language with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEntryLanguage remove(
		long accountEntryLanguageId)
		throws com.liferay.osb.NoSuchAccountEntryLanguageException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AccountEntryLanguage updateImpl(
		com.liferay.osb.model.AccountEntryLanguage accountEntryLanguage,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account entry language with the primary key or throws a {@link com.liferay.osb.NoSuchAccountEntryLanguageException} if it could not be found.
	*
	* @param accountEntryLanguageId the primary key of the account entry language
	* @return the account entry language
	* @throws com.liferay.osb.NoSuchAccountEntryLanguageException if a account entry language with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEntryLanguage findByPrimaryKey(
		long accountEntryLanguageId)
		throws com.liferay.osb.NoSuchAccountEntryLanguageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account entry language with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountEntryLanguageId the primary key of the account entry language
	* @return the account entry language, or <code>null</code> if a account entry language with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEntryLanguage fetchByPrimaryKey(
		long accountEntryLanguageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account entry languages where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account entry languages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEntryLanguage> findByAccountEntryId(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountEntryLanguage> findByAccountEntryId(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountEntryLanguage> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account entry language in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry language
	* @throws com.liferay.osb.NoSuchAccountEntryLanguageException if a matching account entry language could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEntryLanguage findByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryLanguageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account entry language in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry language, or <code>null</code> if a matching account entry language could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEntryLanguage fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account entry language in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry language
	* @throws com.liferay.osb.NoSuchAccountEntryLanguageException if a matching account entry language could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEntryLanguage findByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryLanguageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account entry language in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry language, or <code>null</code> if a matching account entry language could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEntryLanguage fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.AccountEntryLanguage[] findByAccountEntryId_PrevAndNext(
		long accountEntryLanguageId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEntryLanguageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account entry languages.
	*
	* @return the account entry languages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEntryLanguage> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountEntryLanguage> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AccountEntryLanguage> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account entry languages where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account entry languages from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account entry languages where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account entry languages
	* @throws SystemException if a system exception occurred
	*/
	public int countByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account entry languages.
	*
	* @return the number of account entry languages
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}