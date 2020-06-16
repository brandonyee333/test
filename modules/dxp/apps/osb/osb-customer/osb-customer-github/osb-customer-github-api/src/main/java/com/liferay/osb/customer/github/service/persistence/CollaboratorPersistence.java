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

package com.liferay.osb.customer.github.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.github.exception.NoSuchCollaboratorException;
import com.liferay.osb.customer.github.model.Collaborator;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the collaborator service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.customer.github.service.persistence.impl.CollaboratorPersistenceImpl
 * @see CollaboratorUtil
 * @generated
 */
@ProviderType
public interface CollaboratorPersistence extends BasePersistence<Collaborator> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CollaboratorUtil} to access the collaborator persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the collaborators where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the matching collaborators
	*/
	public java.util.List<Collaborator> findByAccountEntryId(
		long accountEntryId);

	/**
	* Returns a range of all the collaborators where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @return the range of matching collaborators
	*/
	public java.util.List<Collaborator> findByAccountEntryId(
		long accountEntryId, int start, int end);

	/**
	* Returns an ordered range of all the collaborators where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching collaborators
	*/
	public java.util.List<Collaborator> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator);

	/**
	* Returns an ordered range of all the collaborators where accountEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param accountEntryId the account entry ID
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching collaborators
	*/
	public java.util.List<Collaborator> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first collaborator in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching collaborator
	* @throws NoSuchCollaboratorException if a matching collaborator could not be found
	*/
	public Collaborator findByAccountEntryId_First(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException;

	/**
	* Returns the first collaborator in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching collaborator, or <code>null</code> if a matching collaborator could not be found
	*/
	public Collaborator fetchByAccountEntryId_First(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator);

	/**
	* Returns the last collaborator in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching collaborator
	* @throws NoSuchCollaboratorException if a matching collaborator could not be found
	*/
	public Collaborator findByAccountEntryId_Last(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException;

	/**
	* Returns the last collaborator in the ordered set where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching collaborator, or <code>null</code> if a matching collaborator could not be found
	*/
	public Collaborator fetchByAccountEntryId_Last(long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator);

	/**
	* Returns the collaborators before and after the current collaborator in the ordered set where accountEntryId = &#63;.
	*
	* @param collaboratorId the primary key of the current collaborator
	* @param accountEntryId the account entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next collaborator
	* @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	*/
	public Collaborator[] findByAccountEntryId_PrevAndNext(
		long collaboratorId, long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException;

	/**
	* Removes all the collaborators where accountEntryId = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	*/
	public void removeByAccountEntryId(long accountEntryId);

	/**
	* Returns the number of collaborators where accountEntryId = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @return the number of matching collaborators
	*/
	public int countByAccountEntryId(long accountEntryId);

	/**
	* Returns all the collaborators where gitHubUserName = &#63;.
	*
	* @param gitHubUserName the git hub user name
	* @return the matching collaborators
	*/
	public java.util.List<Collaborator> findByGitHubUserName(
		java.lang.String gitHubUserName);

	/**
	* Returns a range of all the collaborators where gitHubUserName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gitHubUserName the git hub user name
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @return the range of matching collaborators
	*/
	public java.util.List<Collaborator> findByGitHubUserName(
		java.lang.String gitHubUserName, int start, int end);

	/**
	* Returns an ordered range of all the collaborators where gitHubUserName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gitHubUserName the git hub user name
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching collaborators
	*/
	public java.util.List<Collaborator> findByGitHubUserName(
		java.lang.String gitHubUserName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator);

	/**
	* Returns an ordered range of all the collaborators where gitHubUserName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gitHubUserName the git hub user name
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching collaborators
	*/
	public java.util.List<Collaborator> findByGitHubUserName(
		java.lang.String gitHubUserName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first collaborator in the ordered set where gitHubUserName = &#63;.
	*
	* @param gitHubUserName the git hub user name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching collaborator
	* @throws NoSuchCollaboratorException if a matching collaborator could not be found
	*/
	public Collaborator findByGitHubUserName_First(
		java.lang.String gitHubUserName,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException;

	/**
	* Returns the first collaborator in the ordered set where gitHubUserName = &#63;.
	*
	* @param gitHubUserName the git hub user name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching collaborator, or <code>null</code> if a matching collaborator could not be found
	*/
	public Collaborator fetchByGitHubUserName_First(
		java.lang.String gitHubUserName,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator);

	/**
	* Returns the last collaborator in the ordered set where gitHubUserName = &#63;.
	*
	* @param gitHubUserName the git hub user name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching collaborator
	* @throws NoSuchCollaboratorException if a matching collaborator could not be found
	*/
	public Collaborator findByGitHubUserName_Last(
		java.lang.String gitHubUserName,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException;

	/**
	* Returns the last collaborator in the ordered set where gitHubUserName = &#63;.
	*
	* @param gitHubUserName the git hub user name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching collaborator, or <code>null</code> if a matching collaborator could not be found
	*/
	public Collaborator fetchByGitHubUserName_Last(
		java.lang.String gitHubUserName,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator);

	/**
	* Returns the collaborators before and after the current collaborator in the ordered set where gitHubUserName = &#63;.
	*
	* @param collaboratorId the primary key of the current collaborator
	* @param gitHubUserName the git hub user name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next collaborator
	* @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	*/
	public Collaborator[] findByGitHubUserName_PrevAndNext(
		long collaboratorId, java.lang.String gitHubUserName,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException;

	/**
	* Removes all the collaborators where gitHubUserName = &#63; from the database.
	*
	* @param gitHubUserName the git hub user name
	*/
	public void removeByGitHubUserName(java.lang.String gitHubUserName);

	/**
	* Returns the number of collaborators where gitHubUserName = &#63;.
	*
	* @param gitHubUserName the git hub user name
	* @return the number of matching collaborators
	*/
	public int countByGitHubUserName(java.lang.String gitHubUserName);

	/**
	* Returns all the collaborators where status = &#63;.
	*
	* @param status the status
	* @return the matching collaborators
	*/
	public java.util.List<Collaborator> findByStatus(int status);

	/**
	* Returns a range of all the collaborators where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @return the range of matching collaborators
	*/
	public java.util.List<Collaborator> findByStatus(int status, int start,
		int end);

	/**
	* Returns an ordered range of all the collaborators where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching collaborators
	*/
	public java.util.List<Collaborator> findByStatus(int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator);

	/**
	* Returns an ordered range of all the collaborators where status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param status the status
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching collaborators
	*/
	public java.util.List<Collaborator> findByStatus(int status, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first collaborator in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching collaborator
	* @throws NoSuchCollaboratorException if a matching collaborator could not be found
	*/
	public Collaborator findByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException;

	/**
	* Returns the first collaborator in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching collaborator, or <code>null</code> if a matching collaborator could not be found
	*/
	public Collaborator fetchByStatus_First(int status,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator);

	/**
	* Returns the last collaborator in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching collaborator
	* @throws NoSuchCollaboratorException if a matching collaborator could not be found
	*/
	public Collaborator findByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException;

	/**
	* Returns the last collaborator in the ordered set where status = &#63;.
	*
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching collaborator, or <code>null</code> if a matching collaborator could not be found
	*/
	public Collaborator fetchByStatus_Last(int status,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator);

	/**
	* Returns the collaborators before and after the current collaborator in the ordered set where status = &#63;.
	*
	* @param collaboratorId the primary key of the current collaborator
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next collaborator
	* @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	*/
	public Collaborator[] findByStatus_PrevAndNext(long collaboratorId,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException;

	/**
	* Removes all the collaborators where status = &#63; from the database.
	*
	* @param status the status
	*/
	public void removeByStatus(int status);

	/**
	* Returns the number of collaborators where status = &#63;.
	*
	* @param status the status
	* @return the number of matching collaborators
	*/
	public int countByStatus(int status);

	/**
	* Returns the collaborator where accountEntryId = &#63; and gitHubUserName = &#63; or throws a {@link NoSuchCollaboratorException} if it could not be found.
	*
	* @param accountEntryId the account entry ID
	* @param gitHubUserName the git hub user name
	* @return the matching collaborator
	* @throws NoSuchCollaboratorException if a matching collaborator could not be found
	*/
	public Collaborator findByAEI_GHUN(long accountEntryId,
		java.lang.String gitHubUserName) throws NoSuchCollaboratorException;

	/**
	* Returns the collaborator where accountEntryId = &#63; and gitHubUserName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param gitHubUserName the git hub user name
	* @return the matching collaborator, or <code>null</code> if a matching collaborator could not be found
	*/
	public Collaborator fetchByAEI_GHUN(long accountEntryId,
		java.lang.String gitHubUserName);

	/**
	* Returns the collaborator where accountEntryId = &#63; and gitHubUserName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param accountEntryId the account entry ID
	* @param gitHubUserName the git hub user name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching collaborator, or <code>null</code> if a matching collaborator could not be found
	*/
	public Collaborator fetchByAEI_GHUN(long accountEntryId,
		java.lang.String gitHubUserName, boolean retrieveFromCache);

	/**
	* Removes the collaborator where accountEntryId = &#63; and gitHubUserName = &#63; from the database.
	*
	* @param accountEntryId the account entry ID
	* @param gitHubUserName the git hub user name
	* @return the collaborator that was removed
	*/
	public Collaborator removeByAEI_GHUN(long accountEntryId,
		java.lang.String gitHubUserName) throws NoSuchCollaboratorException;

	/**
	* Returns the number of collaborators where accountEntryId = &#63; and gitHubUserName = &#63;.
	*
	* @param accountEntryId the account entry ID
	* @param gitHubUserName the git hub user name
	* @return the number of matching collaborators
	*/
	public int countByAEI_GHUN(long accountEntryId,
		java.lang.String gitHubUserName);

	/**
	* Returns all the collaborators where gitHubUserName = &#63; and status = &#63;.
	*
	* @param gitHubUserName the git hub user name
	* @param status the status
	* @return the matching collaborators
	*/
	public java.util.List<Collaborator> findByGHUN_S(
		java.lang.String gitHubUserName, int status);

	/**
	* Returns a range of all the collaborators where gitHubUserName = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gitHubUserName the git hub user name
	* @param status the status
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @return the range of matching collaborators
	*/
	public java.util.List<Collaborator> findByGHUN_S(
		java.lang.String gitHubUserName, int status, int start, int end);

	/**
	* Returns an ordered range of all the collaborators where gitHubUserName = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gitHubUserName the git hub user name
	* @param status the status
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching collaborators
	*/
	public java.util.List<Collaborator> findByGHUN_S(
		java.lang.String gitHubUserName, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator);

	/**
	* Returns an ordered range of all the collaborators where gitHubUserName = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gitHubUserName the git hub user name
	* @param status the status
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching collaborators
	*/
	public java.util.List<Collaborator> findByGHUN_S(
		java.lang.String gitHubUserName, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first collaborator in the ordered set where gitHubUserName = &#63; and status = &#63;.
	*
	* @param gitHubUserName the git hub user name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching collaborator
	* @throws NoSuchCollaboratorException if a matching collaborator could not be found
	*/
	public Collaborator findByGHUN_S_First(java.lang.String gitHubUserName,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException;

	/**
	* Returns the first collaborator in the ordered set where gitHubUserName = &#63; and status = &#63;.
	*
	* @param gitHubUserName the git hub user name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching collaborator, or <code>null</code> if a matching collaborator could not be found
	*/
	public Collaborator fetchByGHUN_S_First(java.lang.String gitHubUserName,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator);

	/**
	* Returns the last collaborator in the ordered set where gitHubUserName = &#63; and status = &#63;.
	*
	* @param gitHubUserName the git hub user name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching collaborator
	* @throws NoSuchCollaboratorException if a matching collaborator could not be found
	*/
	public Collaborator findByGHUN_S_Last(java.lang.String gitHubUserName,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException;

	/**
	* Returns the last collaborator in the ordered set where gitHubUserName = &#63; and status = &#63;.
	*
	* @param gitHubUserName the git hub user name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching collaborator, or <code>null</code> if a matching collaborator could not be found
	*/
	public Collaborator fetchByGHUN_S_Last(java.lang.String gitHubUserName,
		int status,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator);

	/**
	* Returns the collaborators before and after the current collaborator in the ordered set where gitHubUserName = &#63; and status = &#63;.
	*
	* @param collaboratorId the primary key of the current collaborator
	* @param gitHubUserName the git hub user name
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next collaborator
	* @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	*/
	public Collaborator[] findByGHUN_S_PrevAndNext(long collaboratorId,
		java.lang.String gitHubUserName, int status,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator)
		throws NoSuchCollaboratorException;

	/**
	* Returns all the collaborators where gitHubUserName = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gitHubUserName the git hub user name
	* @param statuses the statuses
	* @return the matching collaborators
	*/
	public java.util.List<Collaborator> findByGHUN_S(
		java.lang.String gitHubUserName, int[] statuses);

	/**
	* Returns a range of all the collaborators where gitHubUserName = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gitHubUserName the git hub user name
	* @param statuses the statuses
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @return the range of matching collaborators
	*/
	public java.util.List<Collaborator> findByGHUN_S(
		java.lang.String gitHubUserName, int[] statuses, int start, int end);

	/**
	* Returns an ordered range of all the collaborators where gitHubUserName = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gitHubUserName the git hub user name
	* @param statuses the statuses
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching collaborators
	*/
	public java.util.List<Collaborator> findByGHUN_S(
		java.lang.String gitHubUserName, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator);

	/**
	* Returns an ordered range of all the collaborators where gitHubUserName = &#63; and status = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param gitHubUserName the git hub user name
	* @param status the status
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching collaborators
	*/
	public java.util.List<Collaborator> findByGHUN_S(
		java.lang.String gitHubUserName, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the collaborators where gitHubUserName = &#63; and status = &#63; from the database.
	*
	* @param gitHubUserName the git hub user name
	* @param status the status
	*/
	public void removeByGHUN_S(java.lang.String gitHubUserName, int status);

	/**
	* Returns the number of collaborators where gitHubUserName = &#63; and status = &#63;.
	*
	* @param gitHubUserName the git hub user name
	* @param status the status
	* @return the number of matching collaborators
	*/
	public int countByGHUN_S(java.lang.String gitHubUserName, int status);

	/**
	* Returns the number of collaborators where gitHubUserName = &#63; and status = any &#63;.
	*
	* @param gitHubUserName the git hub user name
	* @param statuses the statuses
	* @return the number of matching collaborators
	*/
	public int countByGHUN_S(java.lang.String gitHubUserName, int[] statuses);

	/**
	* Caches the collaborator in the entity cache if it is enabled.
	*
	* @param collaborator the collaborator
	*/
	public void cacheResult(Collaborator collaborator);

	/**
	* Caches the collaborators in the entity cache if it is enabled.
	*
	* @param collaborators the collaborators
	*/
	public void cacheResult(java.util.List<Collaborator> collaborators);

	/**
	* Creates a new collaborator with the primary key. Does not add the collaborator to the database.
	*
	* @param collaboratorId the primary key for the new collaborator
	* @return the new collaborator
	*/
	public Collaborator create(long collaboratorId);

	/**
	* Removes the collaborator with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param collaboratorId the primary key of the collaborator
	* @return the collaborator that was removed
	* @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	*/
	public Collaborator remove(long collaboratorId)
		throws NoSuchCollaboratorException;

	public Collaborator updateImpl(Collaborator collaborator);

	/**
	* Returns the collaborator with the primary key or throws a {@link NoSuchCollaboratorException} if it could not be found.
	*
	* @param collaboratorId the primary key of the collaborator
	* @return the collaborator
	* @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	*/
	public Collaborator findByPrimaryKey(long collaboratorId)
		throws NoSuchCollaboratorException;

	/**
	* Returns the collaborator with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param collaboratorId the primary key of the collaborator
	* @return the collaborator, or <code>null</code> if a collaborator with the primary key could not be found
	*/
	public Collaborator fetchByPrimaryKey(long collaboratorId);

	@Override
	public java.util.Map<java.io.Serializable, Collaborator> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the collaborators.
	*
	* @return the collaborators
	*/
	public java.util.List<Collaborator> findAll();

	/**
	* Returns a range of all the collaborators.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @return the range of collaborators
	*/
	public java.util.List<Collaborator> findAll(int start, int end);

	/**
	* Returns an ordered range of all the collaborators.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of collaborators
	*/
	public java.util.List<Collaborator> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator);

	/**
	* Returns an ordered range of all the collaborators.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CollaboratorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of collaborators
	* @param end the upper bound of the range of collaborators (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of collaborators
	*/
	public java.util.List<Collaborator> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the collaborators from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of collaborators.
	*
	* @return the number of collaborators
	*/
	public int countAll();
}