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

import com.liferay.osb.exception.NoSuchAccountLinkException;
import com.liferay.osb.model.AccountLink;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the account link service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.AccountLinkPersistenceImpl
 * @see AccountLinkUtil
 * @generated
 */
@ProviderType
public interface AccountLinkPersistence extends BasePersistence<AccountLink> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountLinkUtil} to access the account link persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the account links where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account links
	*/
	public java.util.List<AccountLink> findByAccountEntryId(long accountEntryId);

	/**
	* Returns a range of all the account links where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account links
	* @param end the upper bound of the range of account links (not inclusive)
	* @return the range of matching account links
	*/
	public java.util.List<AccountLink> findByAccountEntryId(
		long accountEntryId, int start, int end);

	/**
	* Returns an ordered range of all the account links where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account links
	* @param end the upper bound of the range of account links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account links
	*/
	public java.util.List<AccountLink> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountLink> orderByComparator);

	/**
	* Returns an ordered range of all the account links where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account links
	* @param end the upper bound of the range of account links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account links
	*/
	public java.util.List<AccountLink> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountLink> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account link in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account link
	* @throws NoSuchAccountLinkException if a matching account link could not be found
	*/
	public AccountLink findByAccountEntryId_First(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountLink> orderByComparator)
		throws NoSuchAccountLinkException;

	/**
	* Returns the first account link in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account link, or <code>null</code> if a matching account link could not be found
	*/
	public AccountLink fetchByAccountEntryId_First(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountLink> orderByComparator);

	/**
	* Returns the last account link in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account link
	* @throws NoSuchAccountLinkException if a matching account link could not be found
	*/
	public AccountLink findByAccountEntryId_Last(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountLink> orderByComparator)
		throws NoSuchAccountLinkException;

	/**
	* Returns the last account link in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account link, or <code>null</code> if a matching account link could not be found
	*/
	public AccountLink fetchByAccountEntryId_Last(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountLink> orderByComparator);

	/**
	* Returns the account links before and after the current account link in the ordered set where accountEntryId = &#63;.
	*
	* @param accountLinkId the primary key of the current account link
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account link
	* @throws NoSuchAccountLinkException if a account link with the primary key could not be found
	*/
	public AccountLink[] findByAccountEntryId_PrevAndNext(long accountLinkId,
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountLink> orderByComparator)
		throws NoSuchAccountLinkException;

	/**
	* Removes all the account links where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	*/
	public void removeByAccountEntryId(long accountEntryId);

	/**
	* Returns the number of account links where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account links
	*/
	public int countByAccountEntryId(long accountEntryId);

	/**
	* Caches the account link in the entity cache if it is enabled.
	*
	* @param accountLink the account link
	*/
	public void cacheResult(AccountLink accountLink);

	/**
	* Caches the account links in the entity cache if it is enabled.
	*
	* @param accountLinks the account links
	*/
	public void cacheResult(java.util.List<AccountLink> accountLinks);

	/**
	* Creates a new account link with the primary key. Does not add the account link to the database.
	*
	* @param accountLinkId the primary key for the new account link
	* @return the new account link
	*/
	public AccountLink create(long accountLinkId);

	/**
	* Removes the account link with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountLinkId the primary key of the account link
	* @return the account link that was removed
	* @throws NoSuchAccountLinkException if a account link with the primary key could not be found
	*/
	public AccountLink remove(long accountLinkId)
		throws NoSuchAccountLinkException;

	public AccountLink updateImpl(AccountLink accountLink);

	/**
	* Returns the account link with the primary key or throws a {@link NoSuchAccountLinkException} if it could not be found.
	*
	* @param accountLinkId the primary key of the account link
	* @return the account link
	* @throws NoSuchAccountLinkException if a account link with the primary key could not be found
	*/
	public AccountLink findByPrimaryKey(long accountLinkId)
		throws NoSuchAccountLinkException;

	/**
	* Returns the account link with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountLinkId the primary key of the account link
	* @return the account link, or <code>null</code> if a account link with the primary key could not be found
	*/
	public AccountLink fetchByPrimaryKey(long accountLinkId);

	@Override
	public java.util.Map<java.io.Serializable, AccountLink> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the account links.
	*
	* @return the account links
	*/
	public java.util.List<AccountLink> findAll();

	/**
	* Returns a range of all the account links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account links
	* @param end the upper bound of the range of account links (not inclusive)
	* @return the range of account links
	*/
	public java.util.List<AccountLink> findAll(int start, int end);

	/**
	* Returns an ordered range of all the account links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account links
	* @param end the upper bound of the range of account links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account links
	*/
	public java.util.List<AccountLink> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountLink> orderByComparator);

	/**
	* Returns an ordered range of all the account links.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account links
	* @param end the upper bound of the range of account links (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of account links
	*/
	public java.util.List<AccountLink> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountLink> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the account links from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of account links.
	*
	* @return the number of account links
	*/
	public int countAll();
}