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

package com.liferay.osb.customer.release.notes.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.release.notes.exception.NoSuchReleaseNotesException;
import com.liferay.osb.customer.release.notes.model.ReleaseNotes;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the release notes service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
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
	@Override
	public Map<Serializable, ReleaseNotes> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the release noteses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching release noteses
	 */
	public java.util.List<ReleaseNotes> findByUuid(String uuid);

	/**
	 * Returns a range of all the release noteses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReleaseNotesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @return the range of matching release noteses
	 */
	public java.util.List<ReleaseNotes> findByUuid(
		String uuid, int start, int end);

	/**
	 * Returns an ordered range of all the release noteses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReleaseNotesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching release noteses
	 */
	public java.util.List<ReleaseNotes> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes>
			orderByComparator);

	/**
	 * Returns an ordered range of all the release noteses where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReleaseNotesModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching release noteses
	 */
	public java.util.List<ReleaseNotes> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first release notes in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	public ReleaseNotes findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes>
				orderByComparator)
		throws NoSuchReleaseNotesException;

	/**
	 * Returns the first release notes in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	public ReleaseNotes fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes>
			orderByComparator);

	/**
	 * Returns the last release notes in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	public ReleaseNotes findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes>
				orderByComparator)
		throws NoSuchReleaseNotesException;

	/**
	 * Returns the last release notes in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	public ReleaseNotes fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes>
			orderByComparator);

	/**
	 * Returns the release noteses before and after the current release notes in the ordered set where uuid = &#63;.
	 *
	 * @param releaseNotesId the primary key of the current release notes
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next release notes
	 * @throws NoSuchReleaseNotesException if a release notes with the primary key could not be found
	 */
	public ReleaseNotes[] findByUuid_PrevAndNext(
			long releaseNotesId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes>
				orderByComparator)
		throws NoSuchReleaseNotesException;

	/**
	 * Removes all the release noteses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of release noteses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching release noteses
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the release notes where name = &#63; or throws a <code>NoSuchReleaseNotesException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	public ReleaseNotes findByName(String name)
		throws NoSuchReleaseNotesException;

	/**
	 * Returns the release notes where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	public ReleaseNotes fetchByName(String name);

	/**
	 * Returns the release notes where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	public ReleaseNotes fetchByName(String name, boolean useFinderCache);

	/**
	 * Removes the release notes where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the release notes that was removed
	 */
	public ReleaseNotes removeByName(String name)
		throws NoSuchReleaseNotesException;

	/**
	 * Returns the number of release noteses where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching release noteses
	 */
	public int countByName(String name);

	/**
	 * Returns all the release noteses where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the matching release noteses
	 */
	public java.util.List<ReleaseNotes> findByLikeName(String name);

	/**
	 * Returns a range of all the release noteses where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReleaseNotesModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @return the range of matching release noteses
	 */
	public java.util.List<ReleaseNotes> findByLikeName(
		String name, int start, int end);

	/**
	 * Returns an ordered range of all the release noteses where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReleaseNotesModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching release noteses
	 */
	public java.util.List<ReleaseNotes> findByLikeName(
		String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes>
			orderByComparator);

	/**
	 * Returns an ordered range of all the release noteses where name LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReleaseNotesModelImpl</code>.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching release noteses
	 */
	public java.util.List<ReleaseNotes> findByLikeName(
		String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first release notes in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	public ReleaseNotes findByLikeName_First(
			String name,
			com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes>
				orderByComparator)
		throws NoSuchReleaseNotesException;

	/**
	 * Returns the first release notes in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	public ReleaseNotes fetchByLikeName_First(
		String name,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes>
			orderByComparator);

	/**
	 * Returns the last release notes in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	public ReleaseNotes findByLikeName_Last(
			String name,
			com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes>
				orderByComparator)
		throws NoSuchReleaseNotesException;

	/**
	 * Returns the last release notes in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	public ReleaseNotes fetchByLikeName_Last(
		String name,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes>
			orderByComparator);

	/**
	 * Returns the release noteses before and after the current release notes in the ordered set where name LIKE &#63;.
	 *
	 * @param releaseNotesId the primary key of the current release notes
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next release notes
	 * @throws NoSuchReleaseNotesException if a release notes with the primary key could not be found
	 */
	public ReleaseNotes[] findByLikeName_PrevAndNext(
			long releaseNotesId, String name,
			com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes>
				orderByComparator)
		throws NoSuchReleaseNotesException;

	/**
	 * Removes all the release noteses where name LIKE &#63; from the database.
	 *
	 * @param name the name
	 */
	public void removeByLikeName(String name);

	/**
	 * Returns the number of release noteses where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the number of matching release noteses
	 */
	public int countByLikeName(String name);

	/**
	 * Returns the release notes where jiraIssueKeys = &#63; or throws a <code>NoSuchReleaseNotesException</code> if it could not be found.
	 *
	 * @param jiraIssueKeys the jira issue keys
	 * @return the matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	public ReleaseNotes findByJIRAIssueKeys(String jiraIssueKeys)
		throws NoSuchReleaseNotesException;

	/**
	 * Returns the release notes where jiraIssueKeys = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param jiraIssueKeys the jira issue keys
	 * @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	public ReleaseNotes fetchByJIRAIssueKeys(String jiraIssueKeys);

	/**
	 * Returns the release notes where jiraIssueKeys = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param jiraIssueKeys the jira issue keys
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	public ReleaseNotes fetchByJIRAIssueKeys(
		String jiraIssueKeys, boolean useFinderCache);

	/**
	 * Removes the release notes where jiraIssueKeys = &#63; from the database.
	 *
	 * @param jiraIssueKeys the jira issue keys
	 * @return the release notes that was removed
	 */
	public ReleaseNotes removeByJIRAIssueKeys(String jiraIssueKeys)
		throws NoSuchReleaseNotesException;

	/**
	 * Returns the number of release noteses where jiraIssueKeys = &#63;.
	 *
	 * @param jiraIssueKeys the jira issue keys
	 * @return the number of matching release noteses
	 */
	public int countByJIRAIssueKeys(String jiraIssueKeys);

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
	 * Returns the release notes with the primary key or throws a <code>NoSuchReleaseNotesException</code> if it could not be found.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReleaseNotesModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReleaseNotesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of release noteses
	 */
	public java.util.List<ReleaseNotes> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes>
			orderByComparator);

	/**
	 * Returns an ordered range of all the release noteses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ReleaseNotesModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of release noteses
	 * @param end the upper bound of the range of release noteses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of release noteses
	 */
	public java.util.List<ReleaseNotes> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ReleaseNotes>
			orderByComparator,
		boolean useFinderCache);

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
	public Set<String> getBadColumnNames();

}