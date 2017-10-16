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

package com.liferay.osb.customer.release.notes.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.notes.exception.NoSuchReleaseNotesException;
import com.liferay.osb.customer.release.notes.model.ReleaseNotes;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the release notes service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.customer.release.notes.service.persistence.impl.ReleaseNotesPersistenceImpl
 * @see ReleaseNotesUtil
 * @generated
 */
@ProviderType
public interface ReleaseNotesPersistence extends BasePersistence<ReleaseNotes> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ReleaseNotesUtil} to access the release notes persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the release noteses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching release noteses
	*/
	public java.util.List<ReleaseNotes> findByUuid(java.lang.String uuid);

	/**
	* Returns a range of all the release noteses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of release noteses
	* @param end the upper bound of the range of release noteses (not inclusive)
	* @return the range of matching release noteses
	*/
	public java.util.List<ReleaseNotes> findByUuid(java.lang.String uuid,
		int start, int end);

	/**
	* Returns an ordered range of all the release noteses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of release noteses
	* @param end the upper bound of the range of release noteses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching release noteses
	*/
	public java.util.List<ReleaseNotes> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes> orderByComparator);

	/**
	* Returns an ordered range of all the release noteses where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of release noteses
	* @param end the upper bound of the range of release noteses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching release noteses
	*/
	public java.util.List<ReleaseNotes> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first release notes in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching release notes
	* @throws NoSuchReleaseNotesException if a matching release notes could not be found
	*/
	public ReleaseNotes findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes> orderByComparator)
		throws NoSuchReleaseNotesException;

	/**
	* Returns the first release notes in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching release notes, or <code>null</code> if a matching release notes could not be found
	*/
	public ReleaseNotes fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes> orderByComparator);

	/**
	* Returns the last release notes in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching release notes
	* @throws NoSuchReleaseNotesException if a matching release notes could not be found
	*/
	public ReleaseNotes findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes> orderByComparator)
		throws NoSuchReleaseNotesException;

	/**
	* Returns the last release notes in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching release notes, or <code>null</code> if a matching release notes could not be found
	*/
	public ReleaseNotes fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes> orderByComparator);

	/**
	* Returns the release noteses before and after the current release notes in the ordered set where uuid = &#63;.
	*
	* @param releaseNotesId the primary key of the current release notes
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next release notes
	* @throws NoSuchReleaseNotesException if a release notes with the primary key could not be found
	*/
	public ReleaseNotes[] findByUuid_PrevAndNext(long releaseNotesId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes> orderByComparator)
		throws NoSuchReleaseNotesException;

	/**
	* Removes all the release noteses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of release noteses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching release noteses
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the release notes where name = &#63; or throws a {@link NoSuchReleaseNotesException} if it could not be found.
	*
	* @param name the name
	* @return the matching release notes
	* @throws NoSuchReleaseNotesException if a matching release notes could not be found
	*/
	public ReleaseNotes findByName(java.lang.String name)
		throws NoSuchReleaseNotesException;

	/**
	* Returns the release notes where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	*/
	public ReleaseNotes fetchByName(java.lang.String name);

	/**
	* Returns the release notes where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	*/
	public ReleaseNotes fetchByName(java.lang.String name,
		boolean retrieveFromCache);

	/**
	* Removes the release notes where name = &#63; from the database.
	*
	* @param name the name
	* @return the release notes that was removed
	*/
	public ReleaseNotes removeByName(java.lang.String name)
		throws NoSuchReleaseNotesException;

	/**
	* Returns the number of release noteses where name = &#63;.
	*
	* @param name the name
	* @return the number of matching release noteses
	*/
	public int countByName(java.lang.String name);

	/**
	* Returns the release notes where jiraIssueKeys = &#63; or throws a {@link NoSuchReleaseNotesException} if it could not be found.
	*
	* @param jiraIssueKeys the jira issue keys
	* @return the matching release notes
	* @throws NoSuchReleaseNotesException if a matching release notes could not be found
	*/
	public ReleaseNotes findByJIRAIssueKeys(java.lang.String jiraIssueKeys)
		throws NoSuchReleaseNotesException;

	/**
	* Returns the release notes where jiraIssueKeys = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param jiraIssueKeys the jira issue keys
	* @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	*/
	public ReleaseNotes fetchByJIRAIssueKeys(java.lang.String jiraIssueKeys);

	/**
	* Returns the release notes where jiraIssueKeys = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param jiraIssueKeys the jira issue keys
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	*/
	public ReleaseNotes fetchByJIRAIssueKeys(java.lang.String jiraIssueKeys,
		boolean retrieveFromCache);

	/**
	* Removes the release notes where jiraIssueKeys = &#63; from the database.
	*
	* @param jiraIssueKeys the jira issue keys
	* @return the release notes that was removed
	*/
	public ReleaseNotes removeByJIRAIssueKeys(java.lang.String jiraIssueKeys)
		throws NoSuchReleaseNotesException;

	/**
	* Returns the number of release noteses where jiraIssueKeys = &#63;.
	*
	* @param jiraIssueKeys the jira issue keys
	* @return the number of matching release noteses
	*/
	public int countByJIRAIssueKeys(java.lang.String jiraIssueKeys);

	/**
	* Returns all the release noteses where name LIKE &#63;.
	*
	* @param name the name
	* @return the matching release noteses
	*/
	public java.util.List<ReleaseNotes> findByLikeName(java.lang.String name);

	/**
	* Returns a range of all the release noteses where name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of release noteses
	* @param end the upper bound of the range of release noteses (not inclusive)
	* @return the range of matching release noteses
	*/
	public java.util.List<ReleaseNotes> findByLikeName(java.lang.String name,
		int start, int end);

	/**
	* Returns an ordered range of all the release noteses where name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of release noteses
	* @param end the upper bound of the range of release noteses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching release noteses
	*/
	public java.util.List<ReleaseNotes> findByLikeName(java.lang.String name,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes> orderByComparator);

	/**
	* Returns an ordered range of all the release noteses where name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of release noteses
	* @param end the upper bound of the range of release noteses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching release noteses
	*/
	public java.util.List<ReleaseNotes> findByLikeName(java.lang.String name,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first release notes in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching release notes
	* @throws NoSuchReleaseNotesException if a matching release notes could not be found
	*/
	public ReleaseNotes findByLikeName_First(java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes> orderByComparator)
		throws NoSuchReleaseNotesException;

	/**
	* Returns the first release notes in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching release notes, or <code>null</code> if a matching release notes could not be found
	*/
	public ReleaseNotes fetchByLikeName_First(java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes> orderByComparator);

	/**
	* Returns the last release notes in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching release notes
	* @throws NoSuchReleaseNotesException if a matching release notes could not be found
	*/
	public ReleaseNotes findByLikeName_Last(java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes> orderByComparator)
		throws NoSuchReleaseNotesException;

	/**
	* Returns the last release notes in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching release notes, or <code>null</code> if a matching release notes could not be found
	*/
	public ReleaseNotes fetchByLikeName_Last(java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes> orderByComparator);

	/**
	* Returns the release noteses before and after the current release notes in the ordered set where name LIKE &#63;.
	*
	* @param releaseNotesId the primary key of the current release notes
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next release notes
	* @throws NoSuchReleaseNotesException if a release notes with the primary key could not be found
	*/
	public ReleaseNotes[] findByLikeName_PrevAndNext(long releaseNotesId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes> orderByComparator)
		throws NoSuchReleaseNotesException;

	/**
	* Removes all the release noteses where name LIKE &#63; from the database.
	*
	* @param name the name
	*/
	public void removeByLikeName(java.lang.String name);

	/**
	* Returns the number of release noteses where name LIKE &#63;.
	*
	* @param name the name
	* @return the number of matching release noteses
	*/
	public int countByLikeName(java.lang.String name);

	/**
	* Caches the release notes in the entity cache if it is enabled.
	*
	* @param releaseNotes the release notes
	*/
	public void cacheResult(ReleaseNotes releaseNotes);

	/**
	* Caches the release noteses in the entity cache if it is enabled.
	*
	* @param releaseNoteses the release noteses
	*/
	public void cacheResult(java.util.List<ReleaseNotes> releaseNoteses);

	/**
	* Creates a new release notes with the primary key. Does not add the release notes to the database.
	*
	* @param releaseNotesId the primary key for the new release notes
	* @return the new release notes
	*/
	public ReleaseNotes create(long releaseNotesId);

	/**
	* Removes the release notes with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param releaseNotesId the primary key of the release notes
	* @return the release notes that was removed
	* @throws NoSuchReleaseNotesException if a release notes with the primary key could not be found
	*/
	public ReleaseNotes remove(long releaseNotesId)
		throws NoSuchReleaseNotesException;

	public ReleaseNotes updateImpl(ReleaseNotes releaseNotes);

	/**
	* Returns the release notes with the primary key or throws a {@link NoSuchReleaseNotesException} if it could not be found.
	*
	* @param releaseNotesId the primary key of the release notes
	* @return the release notes
	* @throws NoSuchReleaseNotesException if a release notes with the primary key could not be found
	*/
	public ReleaseNotes findByPrimaryKey(long releaseNotesId)
		throws NoSuchReleaseNotesException;

	/**
	* Returns the release notes with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param releaseNotesId the primary key of the release notes
	* @return the release notes, or <code>null</code> if a release notes with the primary key could not be found
	*/
	public ReleaseNotes fetchByPrimaryKey(long releaseNotesId);

	@Override
	public java.util.Map<java.io.Serializable, ReleaseNotes> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the release noteses.
	*
	* @return the release noteses
	*/
	public java.util.List<ReleaseNotes> findAll();

	/**
	* Returns a range of all the release noteses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of release noteses
	* @param end the upper bound of the range of release noteses (not inclusive)
	* @return the range of release noteses
	*/
	public java.util.List<ReleaseNotes> findAll(int start, int end);

	/**
	* Returns an ordered range of all the release noteses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of release noteses
	* @param end the upper bound of the range of release noteses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of release noteses
	*/
	public java.util.List<ReleaseNotes> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes> orderByComparator);

	/**
	* Returns an ordered range of all the release noteses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of release noteses
	* @param end the upper bound of the range of release noteses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of release noteses
	*/
	public java.util.List<ReleaseNotes> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the release noteses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of release noteses.
	*
	* @return the number of release noteses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}