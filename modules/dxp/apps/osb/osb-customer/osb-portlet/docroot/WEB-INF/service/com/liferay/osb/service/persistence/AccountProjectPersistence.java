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

import com.liferay.osb.model.AccountProject;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the account project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountProjectPersistenceImpl
 * @see AccountProjectUtil
 * @generated
 */
public interface AccountProjectPersistence extends BasePersistence<AccountProject> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountProjectUtil} to access the account project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the account project in the entity cache if it is enabled.
	*
	* @param accountProject the account project
	*/
	public void cacheResult(com.liferay.osb.model.AccountProject accountProject);

	/**
	* Caches the account projects in the entity cache if it is enabled.
	*
	* @param accountProjects the account projects
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AccountProject> accountProjects);

	/**
	* Creates a new account project with the primary key. Does not add the account project to the database.
	*
	* @param accountProjectId the primary key for the new account project
	* @return the new account project
	*/
	public com.liferay.osb.model.AccountProject create(long accountProjectId);

	/**
	* Removes the account project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountProjectId the primary key of the account project
	* @return the account project that was removed
	* @throws com.liferay.osb.NoSuchAccountProjectException if a account project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountProject remove(long accountProjectId)
		throws com.liferay.osb.NoSuchAccountProjectException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AccountProject updateImpl(
		com.liferay.osb.model.AccountProject accountProject, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account project with the primary key or throws a {@link com.liferay.osb.NoSuchAccountProjectException} if it could not be found.
	*
	* @param accountProjectId the primary key of the account project
	* @return the account project
	* @throws com.liferay.osb.NoSuchAccountProjectException if a account project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountProject findByPrimaryKey(
		long accountProjectId)
		throws com.liferay.osb.NoSuchAccountProjectException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param accountProjectId the primary key of the account project
	* @return the account project, or <code>null</code> if a account project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountProject fetchByPrimaryKey(
		long accountProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account projects where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching account projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountProject> findByAccountEntryId(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the account projects where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account projects
	* @param end the upper bound of the range of account projects (not inclusive)
	* @return the range of matching account projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountProject> findByAccountEntryId(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the account projects where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of account projects
	* @param end the upper bound of the range of account projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching account projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountProject> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account project in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account project
	* @throws com.liferay.osb.NoSuchAccountProjectException if a matching account project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountProject findByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountProjectException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first account project in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching account project, or <code>null</code> if a matching account project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountProject fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account project in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account project
	* @throws com.liferay.osb.NoSuchAccountProjectException if a matching account project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountProject findByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountProjectException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last account project in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching account project, or <code>null</code> if a matching account project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountProject fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the account projects before and after the current account project in the ordered set where accountEntryId = &#63;.
	*
	* @param accountProjectId the primary key of the current account project
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next account project
	* @throws com.liferay.osb.NoSuchAccountProjectException if a account project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AccountProject[] findByAccountEntryId_PrevAndNext(
		long accountProjectId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAccountProjectException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the account projects.
	*
	* @return the account projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountProject> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the account projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account projects
	* @param end the upper bound of the range of account projects (not inclusive)
	* @return the range of account projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountProject> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the account projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of account projects
	* @param end the upper bound of the range of account projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AccountProject> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account projects where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the account projects from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account projects where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching account projects
	* @throws SystemException if a system exception occurred
	*/
	public int countByAccountEntryId(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of account projects.
	*
	* @return the number of account projects
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}