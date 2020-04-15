/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.github.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.github.exception.NoSuchCollaboratorException;
import com.liferay.osb.customer.github.model.Collaborator;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the collaborator service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
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
	@Override
	public Map<Serializable, Collaborator> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
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
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator>
			orderByComparator);

	/**
	 * Returns an ordered range of all the collaborators where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching collaborators
	 */
	public java.util.List<Collaborator> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first collaborator in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	public Collaborator findByAccountEntryId_First(
			long accountEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<Collaborator>
				orderByComparator)
		throws NoSuchCollaboratorException;

	/**
	 * Returns the first collaborator in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	public Collaborator fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator>
			orderByComparator);

	/**
	 * Returns the last collaborator in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	public Collaborator findByAccountEntryId_Last(
			long accountEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<Collaborator>
				orderByComparator)
		throws NoSuchCollaboratorException;

	/**
	 * Returns the last collaborator in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	public Collaborator fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator>
			orderByComparator);

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
			com.liferay.portal.kernel.util.OrderByComparator<Collaborator>
				orderByComparator)
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
		String gitHubUserName);

	/**
	 * Returns a range of all the collaborators where gitHubUserName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param gitHubUserName the git hub user name
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @return the range of matching collaborators
	 */
	public java.util.List<Collaborator> findByGitHubUserName(
		String gitHubUserName, int start, int end);

	/**
	 * Returns an ordered range of all the collaborators where gitHubUserName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param gitHubUserName the git hub user name
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching collaborators
	 */
	public java.util.List<Collaborator> findByGitHubUserName(
		String gitHubUserName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator>
			orderByComparator);

	/**
	 * Returns an ordered range of all the collaborators where gitHubUserName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param gitHubUserName the git hub user name
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching collaborators
	 */
	public java.util.List<Collaborator> findByGitHubUserName(
		String gitHubUserName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first collaborator in the ordered set where gitHubUserName = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	public Collaborator findByGitHubUserName_First(
			String gitHubUserName,
			com.liferay.portal.kernel.util.OrderByComparator<Collaborator>
				orderByComparator)
		throws NoSuchCollaboratorException;

	/**
	 * Returns the first collaborator in the ordered set where gitHubUserName = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	public Collaborator fetchByGitHubUserName_First(
		String gitHubUserName,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator>
			orderByComparator);

	/**
	 * Returns the last collaborator in the ordered set where gitHubUserName = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	public Collaborator findByGitHubUserName_Last(
			String gitHubUserName,
			com.liferay.portal.kernel.util.OrderByComparator<Collaborator>
				orderByComparator)
		throws NoSuchCollaboratorException;

	/**
	 * Returns the last collaborator in the ordered set where gitHubUserName = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	public Collaborator fetchByGitHubUserName_Last(
		String gitHubUserName,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator>
			orderByComparator);

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
			long collaboratorId, String gitHubUserName,
			com.liferay.portal.kernel.util.OrderByComparator<Collaborator>
				orderByComparator)
		throws NoSuchCollaboratorException;

	/**
	 * Removes all the collaborators where gitHubUserName = &#63; from the database.
	 *
	 * @param gitHubUserName the git hub user name
	 */
	public void removeByGitHubUserName(String gitHubUserName);

	/**
	 * Returns the number of collaborators where gitHubUserName = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @return the number of matching collaborators
	 */
	public int countByGitHubUserName(String gitHubUserName);

	/**
	 * Returns the collaborator where accountEntryId = &#63; and gitHubUserName = &#63; or throws a <code>NoSuchCollaboratorException</code> if it could not be found.
	 *
	 * @param accountEntryId the account entry ID
	 * @param gitHubUserName the git hub user name
	 * @return the matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	public Collaborator findByAEI_GHUN(
			long accountEntryId, String gitHubUserName)
		throws NoSuchCollaboratorException;

	/**
	 * Returns the collaborator where accountEntryId = &#63; and gitHubUserName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param gitHubUserName the git hub user name
	 * @return the matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	public Collaborator fetchByAEI_GHUN(
		long accountEntryId, String gitHubUserName);

	/**
	 * Returns the collaborator where accountEntryId = &#63; and gitHubUserName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param gitHubUserName the git hub user name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	public Collaborator fetchByAEI_GHUN(
		long accountEntryId, String gitHubUserName, boolean useFinderCache);

	/**
	 * Removes the collaborator where accountEntryId = &#63; and gitHubUserName = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param gitHubUserName the git hub user name
	 * @return the collaborator that was removed
	 */
	public Collaborator removeByAEI_GHUN(
			long accountEntryId, String gitHubUserName)
		throws NoSuchCollaboratorException;

	/**
	 * Returns the number of collaborators where accountEntryId = &#63; and gitHubUserName = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param gitHubUserName the git hub user name
	 * @return the number of matching collaborators
	 */
	public int countByAEI_GHUN(long accountEntryId, String gitHubUserName);

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
	 * Returns the collaborator with the primary key or throws a <code>NoSuchCollaboratorException</code> if it could not be found.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of collaborators
	 */
	public java.util.List<Collaborator> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator>
			orderByComparator);

	/**
	 * Returns an ordered range of all the collaborators.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of collaborators
	 */
	public java.util.List<Collaborator> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Collaborator>
			orderByComparator,
		boolean useFinderCache);

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