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

import com.liferay.osb.exception.NoSuchAccountEnvironmentException;
import com.liferay.osb.model.AccountEnvironment;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the account environment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.AccountEnvironmentPersistenceImpl
 * @see AccountEnvironmentUtil
 * @generated
 */
@ProviderType
public interface AccountEnvironmentPersistence extends BasePersistence<AccountEnvironment> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountEnvironmentUtil} to access the account environment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the account environments where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account environments
	*/
	public java.util.List<AccountEnvironment> findByAccountEntryId(
		long accountEntryId);

	/**
	* Returns a range of all the account environments where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account environments
	* @param end the upper bound of the range of account environments (not inclusive)
	* @return the range of matching account environments
	*/
	public java.util.List<AccountEnvironment> findByAccountEntryId(
		long accountEntryId, int start, int end);

	/**
	* Returns an ordered range of all the account environments where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account environments
	* @param end the upper bound of the range of account environments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account environments
	*/
	public java.util.List<AccountEnvironment> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEnvironment> orderByComparator);

	/**
	* Returns an ordered range of all the account environments where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account environments
	* @param end the upper bound of the range of account environments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account environments
	*/
	public java.util.List<AccountEnvironment> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEnvironment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account environment in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account environment
	* @throws NoSuchAccountEnvironmentException if a matching account environment could not be found
	*/
	public AccountEnvironment findByAccountEntryId_First(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEnvironment> orderByComparator)
		throws NoSuchAccountEnvironmentException;

	/**
	* Returns the first account environment in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account environment, or <code>null</code> if a matching account environment could not be found
	*/
	public AccountEnvironment fetchByAccountEntryId_First(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEnvironment> orderByComparator);

	/**
	* Returns the last account environment in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account environment
	* @throws NoSuchAccountEnvironmentException if a matching account environment could not be found
	*/
	public AccountEnvironment findByAccountEntryId_Last(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEnvironment> orderByComparator)
		throws NoSuchAccountEnvironmentException;

	/**
	* Returns the last account environment in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account environment, or <code>null</code> if a matching account environment could not be found
	*/
	public AccountEnvironment fetchByAccountEntryId_Last(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEnvironment> orderByComparator);

	/**
	* Returns the account environments before and after the current account environment in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEnvironmentId the primary key of the current account environment
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account environment
	* @throws NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	*/
	public AccountEnvironment[] findByAccountEntryId_PrevAndNext(
		long accountEnvironmentId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEnvironment> orderByComparator)
		throws NoSuchAccountEnvironmentException;

	/**
	* Removes all the account environments where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	*/
	public void removeByAccountEntryId(long accountEntryId);

	/**
	* Returns the number of account environments where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account environments
	*/
	public int countByAccountEntryId(long accountEntryId);

	/**
	* Returns all the account environments where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @return the matching account environments
	*/
	public java.util.List<AccountEnvironment> findByAEI_PEI(
		long accountEntryId, long productEntryId);

	/**
	* Returns a range of all the account environments where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param start the lower bound of the range of account environments
	* @param end the upper bound of the range of account environments (not inclusive)
	* @return the range of matching account environments
	*/
	public java.util.List<AccountEnvironment> findByAEI_PEI(
		long accountEntryId, long productEntryId, int start, int end);

	/**
	* Returns an ordered range of all the account environments where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param start the lower bound of the range of account environments
	* @param end the upper bound of the range of account environments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account environments
	*/
	public java.util.List<AccountEnvironment> findByAEI_PEI(
		long accountEntryId, long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEnvironment> orderByComparator);

	/**
	* Returns an ordered range of all the account environments where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param start the lower bound of the range of account environments
	* @param end the upper bound of the range of account environments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching account environments
	*/
	public java.util.List<AccountEnvironment> findByAEI_PEI(
		long accountEntryId, long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEnvironment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account environment
	* @throws NoSuchAccountEnvironmentException if a matching account environment could not be found
	*/
	public AccountEnvironment findByAEI_PEI_First(long accountEntryId,
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEnvironment> orderByComparator)
		throws NoSuchAccountEnvironmentException;

	/**
	* Returns the first account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account environment, or <code>null</code> if a matching account environment could not be found
	*/
	public AccountEnvironment fetchByAEI_PEI_First(long accountEntryId,
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEnvironment> orderByComparator);

	/**
	* Returns the last account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account environment
	* @throws NoSuchAccountEnvironmentException if a matching account environment could not be found
	*/
	public AccountEnvironment findByAEI_PEI_Last(long accountEntryId,
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEnvironment> orderByComparator)
		throws NoSuchAccountEnvironmentException;

	/**
	* Returns the last account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account environment, or <code>null</code> if a matching account environment could not be found
	*/
	public AccountEnvironment fetchByAEI_PEI_Last(long accountEntryId,
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEnvironment> orderByComparator);

	/**
	* Returns the account environments before and after the current account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* @param accountEnvironmentId the primary key of the current account environment
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account environment
	* @throws NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	*/
	public AccountEnvironment[] findByAEI_PEI_PrevAndNext(
		long accountEnvironmentId, long accountEntryId, long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEnvironment> orderByComparator)
		throws NoSuchAccountEnvironmentException;

	/**
	* Removes all the account environments where accountEntryId = &#63; and productEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	*/
	public void removeByAEI_PEI(long accountEntryId, long productEntryId);

	/**
	* Returns the number of account environments where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @return the number of matching account environments
	*/
	public int countByAEI_PEI(long accountEntryId, long productEntryId);

	/**
	* Returns the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; or throws a {@link NoSuchAccountEnvironmentException} if it could not be found.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param name the name
	* @return the matching account environment
	* @throws NoSuchAccountEnvironmentException if a matching account environment could not be found
	*/
	public AccountEnvironment findByAEI_PEI_N(long accountEntryId,
		long productEntryId, java.lang.String name)
		throws NoSuchAccountEnvironmentException;

	/**
	* Returns the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param name the name
	* @return the matching account environment, or <code>null</code> if a matching account environment could not be found
	*/
	public AccountEnvironment fetchByAEI_PEI_N(long accountEntryId,
		long productEntryId, java.lang.String name);

	/**
	* Returns the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching account environment, or <code>null</code> if a matching account environment could not be found
	*/
	public AccountEnvironment fetchByAEI_PEI_N(long accountEntryId,
		long productEntryId, java.lang.String name, boolean retrieveFromCache);

	/**
	* Removes the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param name the name
	* @return the account environment that was removed
	*/
	public AccountEnvironment removeByAEI_PEI_N(long accountEntryId,
		long productEntryId, java.lang.String name)
		throws NoSuchAccountEnvironmentException;

	/**
	* Returns the number of account environments where accountEntryId = &#63; and productEntryId = &#63; and name = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param name the name
	* @return the number of matching account environments
	*/
	public int countByAEI_PEI_N(long accountEntryId, long productEntryId,
		java.lang.String name);

	/**
	* Caches the account environment in the entity cache if it is enabled.
	*
	* @param accountEnvironment the account environment
	*/
	public void cacheResult(AccountEnvironment accountEnvironment);

	/**
	* Caches the account environments in the entity cache if it is enabled.
	*
	* @param accountEnvironments the account environments
	*/
	public void cacheResult(
		java.util.List<AccountEnvironment> accountEnvironments);

	/**
	* Creates a new account environment with the primary key. Does not add the account environment to the database.
	*
	* @param accountEnvironmentId the primary key for the new account environment
	* @return the new account environment
	*/
	public AccountEnvironment create(long accountEnvironmentId);

	/**
	* Removes the account environment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironmentId the primary key of the account environment
	* @return the account environment that was removed
	* @throws NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	*/
	public AccountEnvironment remove(long accountEnvironmentId)
		throws NoSuchAccountEnvironmentException;

	public AccountEnvironment updateImpl(AccountEnvironment accountEnvironment);

	/**
	* Returns the account environment with the primary key or throws a {@link NoSuchAccountEnvironmentException} if it could not be found.
	*
	* @param accountEnvironmentId the primary key of the account environment
	* @return the account environment
	* @throws NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	*/
	public AccountEnvironment findByPrimaryKey(long accountEnvironmentId)
		throws NoSuchAccountEnvironmentException;

	/**
	* Returns the account environment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountEnvironmentId the primary key of the account environment
	* @return the account environment, or <code>null</code> if a account environment with the primary key could not be found
	*/
	public AccountEnvironment fetchByPrimaryKey(long accountEnvironmentId);

	@Override
	public java.util.Map<java.io.Serializable, AccountEnvironment> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the account environments.
	*
	* @return the account environments
	*/
	public java.util.List<AccountEnvironment> findAll();

	/**
	* Returns a range of all the account environments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account environments
	* @param end the upper bound of the range of account environments (not inclusive)
	* @return the range of account environments
	*/
	public java.util.List<AccountEnvironment> findAll(int start, int end);

	/**
	* Returns an ordered range of all the account environments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account environments
	* @param end the upper bound of the range of account environments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account environments
	*/
	public java.util.List<AccountEnvironment> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEnvironment> orderByComparator);

	/**
	* Returns an ordered range of all the account environments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link AccountEnvironmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account environments
	* @param end the upper bound of the range of account environments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of account environments
	*/
	public java.util.List<AccountEnvironment> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountEnvironment> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the account environments from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of account environments.
	*
	* @return the number of account environments
	*/
	public int countAll();
}