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

import com.liferay.osb.exception.NoSuchAccountEntryException;
import com.liferay.osb.model.AccountEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the account entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.AccountEntryPersistenceImpl
 * @see AccountEntryUtil
 * @generated
 */
@ProviderType
public interface AccountEntryPersistence extends BasePersistence<AccountEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountEntryUtil} to access the account entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the account entry in the entity cache if it is enabled.
	*
	* @param accountEntry the account entry
	*/
	public void cacheResult(AccountEntry accountEntry);

	/**
	* Caches the account entries in the entity cache if it is enabled.
	*
	* @param accountEntries the account entries
	*/
	public void cacheResult(java.util.List<AccountEntry> accountEntries);

	/**
	* Creates a new account entry with the primary key. Does not add the account entry to the database.
	*
	* @param accountEntryId the primary key for the new account entry
	* @return the new account entry
	*/
	public AccountEntry create(long accountEntryId);

	/**
	* Removes the account entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry that was removed
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public AccountEntry remove(long accountEntryId)
		throws NoSuchAccountEntryException;

	public AccountEntry updateImpl(AccountEntry accountEntry);

	/**
	* Returns the account entry with the primary key or throws a {@link NoSuchAccountEntryException} if it could not be found.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry
	* @throws NoSuchAccountEntryException if a account entry with the primary key could not be found
	*/
	public AccountEntry findByPrimaryKey(long accountEntryId)
		throws NoSuchAccountEntryException;

	/**
	* Returns the account entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountEntryId the primary key of the account entry
	* @return the account entry, or <code>null</code> if a account entry with the primary key could not be found
	*/
	public AccountEntry fetchByPrimaryKey(long accountEntryId);

	@Override
	public java.util.Map<java.io.Serializable, AccountEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the account entries.
	*
	* @return the account entries
	*/
	public java.util.List<AccountEntry> findAll();

	/**
	* Returns a range of all the account entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @return the range of account entries
	*/
	public java.util.List<AccountEntry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the account entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account entries
	*/
	public java.util.List<AccountEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator);

	/**
	* Returns an ordered range of all the account entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account entries
	* @param end the upper bound of the range of account entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of account entries
	*/
	public java.util.List<AccountEntry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEntry> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the account entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of account entries.
	*
	* @return the number of account entries
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}