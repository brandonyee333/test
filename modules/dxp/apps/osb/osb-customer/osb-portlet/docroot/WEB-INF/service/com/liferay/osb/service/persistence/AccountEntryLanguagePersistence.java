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

import com.liferay.osb.exception.NoSuchAccountEntryLanguageException;
import com.liferay.osb.model.AccountEntryLanguage;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the account entry language service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.AccountEntryLanguagePersistenceImpl
 * @see AccountEntryLanguageUtil
 * @generated
 */
@ProviderType
public interface AccountEntryLanguagePersistence extends BasePersistence<AccountEntryLanguage> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountEntryLanguageUtil} to access the account entry language persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the account entry languages where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account entry languages
	*/
	public java.util.List<AccountEntryLanguage> findByAccountEntryId(
		long accountEntryId);

	/**
	* Returns a range of all the account entry languages where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account entry languages
	* @param end the upper bound of the range of account entry languages (not inclusive)
	* @return the range of matching account entry languages
	*/
	public java.util.List<AccountEntryLanguage> findByAccountEntryId(
		long accountEntryId, int start, int end);

	/**
	* Returns an ordered range of all the account entry languages where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account entry languages
	* @param end the upper bound of the range of account entry languages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account entry languages
	*/
	public java.util.List<AccountEntryLanguage> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntryLanguage> orderByComparator);

	/**
	* Returns an ordered range of all the account entry languages where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account entry languages
	* @param end the upper bound of the range of account entry languages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account entry languages
	*/
	public java.util.List<AccountEntryLanguage> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntryLanguage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account entry language in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry language
	* @throws NoSuchAccountEntryLanguageException if a matching account entry language could not be found
	*/
	public AccountEntryLanguage findByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntryLanguage> orderByComparator)
		throws NoSuchAccountEntryLanguageException;

	/**
	* Returns the first account entry language in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account entry language, or <code>null</code> if a matching account entry language could not be found
	*/
	public AccountEntryLanguage fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntryLanguage> orderByComparator);

	/**
	* Returns the last account entry language in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry language
	* @throws NoSuchAccountEntryLanguageException if a matching account entry language could not be found
	*/
	public AccountEntryLanguage findByAccountEntryId_Last(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntryLanguage> orderByComparator)
		throws NoSuchAccountEntryLanguageException;

	/**
	* Returns the last account entry language in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account entry language, or <code>null</code> if a matching account entry language could not be found
	*/
	public AccountEntryLanguage fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntryLanguage> orderByComparator);

	/**
	* Returns the account entry languages before and after the current account entry language in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryLanguageId the primary key of the current account entry language
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account entry language
	* @throws NoSuchAccountEntryLanguageException if a account entry language with the primary key could not be found
	*/
	public AccountEntryLanguage[] findByAccountEntryId_PrevAndNext(
		long accountEntryLanguageId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntryLanguage> orderByComparator)
		throws NoSuchAccountEntryLanguageException;

	/**
	* Removes all the account entry languages where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	*/
	public void removeByAccountEntryId(long accountEntryId);

	/**
	* Returns the number of account entry languages where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account entry languages
	*/
	public int countByAccountEntryId(long accountEntryId);

	/**
	* Caches the account entry language in the entity cache if it is enabled.
	*
	* @param accountEntryLanguage the account entry language
	*/
	public void cacheResult(AccountEntryLanguage accountEntryLanguage);

	/**
	* Caches the account entry languages in the entity cache if it is enabled.
	*
	* @param accountEntryLanguages the account entry languages
	*/
	public void cacheResult(
		java.util.List<AccountEntryLanguage> accountEntryLanguages);

	/**
	* Creates a new account entry language with the primary key. Does not add the account entry language to the database.
	*
	* @param accountEntryLanguageId the primary key for the new account entry language
	* @return the new account entry language
	*/
	public AccountEntryLanguage create(long accountEntryLanguageId);

	/**
	* Removes the account entry language with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryLanguageId the primary key of the account entry language
	* @return the account entry language that was removed
	* @throws NoSuchAccountEntryLanguageException if a account entry language with the primary key could not be found
	*/
	public AccountEntryLanguage remove(long accountEntryLanguageId)
		throws NoSuchAccountEntryLanguageException;

	public AccountEntryLanguage updateImpl(
		AccountEntryLanguage accountEntryLanguage);

	/**
	* Returns the account entry language with the primary key or throws a {@link NoSuchAccountEntryLanguageException} if it could not be found.
	*
	* @param accountEntryLanguageId the primary key of the account entry language
	* @return the account entry language
	* @throws NoSuchAccountEntryLanguageException if a account entry language with the primary key could not be found
	*/
	public AccountEntryLanguage findByPrimaryKey(long accountEntryLanguageId)
		throws NoSuchAccountEntryLanguageException;

	/**
	* Returns the account entry language with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountEntryLanguageId the primary key of the account entry language
	* @return the account entry language, or <code>null</code> if a account entry language with the primary key could not be found
	*/
	public AccountEntryLanguage fetchByPrimaryKey(long accountEntryLanguageId);

	@Override
	public java.util.Map<java.io.Serializable, AccountEntryLanguage> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the account entry languages.
	*
	* @return the account entry languages
	*/
	public java.util.List<AccountEntryLanguage> findAll();

	/**
	* Returns a range of all the account entry languages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account entry languages
	* @param end the upper bound of the range of account entry languages (not inclusive)
	* @return the range of account entry languages
	*/
	public java.util.List<AccountEntryLanguage> findAll(int start, int end);

	/**
	* Returns an ordered range of all the account entry languages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account entry languages
	* @param end the upper bound of the range of account entry languages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account entry languages
	*/
	public java.util.List<AccountEntryLanguage> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntryLanguage> orderByComparator);

	/**
	* Returns an ordered range of all the account entry languages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account entry languages
	* @param end the upper bound of the range of account entry languages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of account entry languages
	*/
	public java.util.List<AccountEntryLanguage> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntryLanguage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the account entry languages from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of account entry languages.
	*
	* @return the number of account entry languages
	*/
	public int countAll();
}