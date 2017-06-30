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

import com.liferay.osb.model.AccountEnvironment;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the account environment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentPersistenceImpl
 * @see AccountEnvironmentUtil
 * @generated
 */
public interface AccountEnvironmentPersistence extends BasePersistence<AccountEnvironment> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountEnvironmentUtil} to access the account environment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the account environment in the entity cache if it is enabled.
	*
	* @param accountEnvironment the account environment
	*/
	public void cacheResult(
		com.liferay.osb.model.AccountEnvironment accountEnvironment);

	/**
	* Caches the account environments in the entity cache if it is enabled.
	*
	* @param accountEnvironments the account environments
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AccountEnvironment> accountEnvironments);

	/**
	* Creates a new account environment with the primary key. Does not add the account environment to the database.
	*
	* @param accountEnvironmentId the primary key for the new account environment
	* @return the new account environment
	*/
	public com.liferay.osb.model.AccountEnvironment create(
		long accountEnvironmentId);

	/**
	* Removes the account environment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironmentId the primary key of the account environment
	* @return the account environment that was removed
	* @throws com.liferay.osb.NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironment remove(
		long accountEnvironmentId)
		throws com.liferay.osb.NoSuchAccountEnvironmentException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AccountEnvironment updateImpl(
		com.liferay.osb.model.AccountEnvironment accountEnvironment,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account environment with the primary key or throws a {@link com.liferay.osb.NoSuchAccountEnvironmentException} if it could not be found.
	*
	* @param accountEnvironmentId the primary key of the account environment
	* @return the account environment
	* @throws com.liferay.osb.NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironment findByPrimaryKey(
		long accountEnvironmentId)
		throws com.liferay.osb.NoSuchAccountEnvironmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account environment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountEnvironmentId the primary key of the account environment
	* @return the account environment, or <code>null</code> if a account environment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironment fetchByPrimaryKey(
		long accountEnvironmentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account environments where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account environments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEnvironment> findByAccountEntryId(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the account environments where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account environments
	* @param end the upper bound of the range of account environments (not inclusive)
	* @return the range of matching account environments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEnvironment> findByAccountEntryId(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the account environments where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account environments
	* @param end the upper bound of the range of account environments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account environments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEnvironment> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account environment in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account environment
	* @throws com.liferay.osb.NoSuchAccountEnvironmentException if a matching account environment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironment findByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEnvironmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account environment in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account environment, or <code>null</code> if a matching account environment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironment fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account environment in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account environment
	* @throws com.liferay.osb.NoSuchAccountEnvironmentException if a matching account environment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironment findByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEnvironmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account environment in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account environment, or <code>null</code> if a matching account environment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironment fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account environments before and after the current account environment in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEnvironmentId the primary key of the current account environment
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account environment
	* @throws com.liferay.osb.NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironment[] findByAccountEntryId_PrevAndNext(
		long accountEnvironmentId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEnvironmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account environments where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @return the matching account environments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEnvironment> findByAEI_PEI(
		long accountEntryId, long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the account environments where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param start the lower bound of the range of account environments
	* @param end the upper bound of the range of account environments (not inclusive)
	* @return the range of matching account environments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEnvironment> findByAEI_PEI(
		long accountEntryId, long productEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the account environments where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param start the lower bound of the range of account environments
	* @param end the upper bound of the range of account environments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account environments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEnvironment> findByAEI_PEI(
		long accountEntryId, long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account environment
	* @throws com.liferay.osb.NoSuchAccountEnvironmentException if a matching account environment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironment findByAEI_PEI_First(
		long accountEntryId, long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEnvironmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account environment, or <code>null</code> if a matching account environment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironment fetchByAEI_PEI_First(
		long accountEntryId, long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account environment
	* @throws com.liferay.osb.NoSuchAccountEnvironmentException if a matching account environment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironment findByAEI_PEI_Last(
		long accountEntryId, long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEnvironmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account environment, or <code>null</code> if a matching account environment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironment fetchByAEI_PEI_Last(
		long accountEntryId, long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account environments before and after the current account environment in the ordered set where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* @param accountEnvironmentId the primary key of the current account environment
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account environment
	* @throws com.liferay.osb.NoSuchAccountEnvironmentException if a account environment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironment[] findByAEI_PEI_PrevAndNext(
		long accountEnvironmentId, long accountEntryId, long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountEnvironmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; or throws a {@link com.liferay.osb.NoSuchAccountEnvironmentException} if it could not be found.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param name the name
	* @return the matching account environment
	* @throws com.liferay.osb.NoSuchAccountEnvironmentException if a matching account environment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironment findByAEI_PEI_N(
		long accountEntryId, long productEntryId, java.lang.String name)
		throws com.liferay.osb.NoSuchAccountEnvironmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param name the name
	* @return the matching account environment, or <code>null</code> if a matching account environment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironment fetchByAEI_PEI_N(
		long accountEntryId, long productEntryId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param name the name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching account environment, or <code>null</code> if a matching account environment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironment fetchByAEI_PEI_N(
		long accountEntryId, long productEntryId, java.lang.String name,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account environments.
	*
	* @return the account environments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEnvironment> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the account environments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account environments
	* @param end the upper bound of the range of account environments (not inclusive)
	* @return the range of account environments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEnvironment> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the account environments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account environments
	* @param end the upper bound of the range of account environments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account environments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountEnvironment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account environments where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account environments where accountEntryId = &#63; and productEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAEI_PEI(long accountEntryId, long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the account environment where accountEntryId = &#63; and productEntryId = &#63; and name = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param name the name
	* @return the account environment that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountEnvironment removeByAEI_PEI_N(
		long accountEntryId, long productEntryId, java.lang.String name)
		throws com.liferay.osb.NoSuchAccountEnvironmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account environments from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account environments where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account environments
	* @throws SystemException if a system exception occurred
	*/
	public int countByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account environments where accountEntryId = &#63; and productEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @return the number of matching account environments
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI_PEI(long accountEntryId, long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account environments where accountEntryId = &#63; and productEntryId = &#63; and name = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param productEntryId the product entry ID
	* @param name the name
	* @return the number of matching account environments
	* @throws SystemException if a system exception occurred
	*/
	public int countByAEI_PEI_N(long accountEntryId, long productEntryId,
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account environments.
	*
	* @return the number of account environments
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}