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

import com.liferay.osb.customer.release.notes.model.ReleaseNotes;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the release notes service. This utility wraps <code>com.liferay.osb.customer.release.notes.service.persistence.impl.ReleaseNotesPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReleaseNotesPersistence
 * @generated
 */
public class ReleaseNotesUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ReleaseNotes releaseNotes) {
		getPersistence().clearCache(releaseNotes);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, ReleaseNotes> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ReleaseNotes> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ReleaseNotes> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ReleaseNotes> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ReleaseNotes> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ReleaseNotes update(ReleaseNotes releaseNotes) {
		return getPersistence().update(releaseNotes);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ReleaseNotes update(
		ReleaseNotes releaseNotes, ServiceContext serviceContext) {

		return getPersistence().update(releaseNotes, serviceContext);
	}

	/**
	 * Returns all the release noteses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching release noteses
	 */
	public static List<ReleaseNotes> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<ReleaseNotes> findByUuid(
		String uuid, int start, int end) {

		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<ReleaseNotes> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ReleaseNotes> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<ReleaseNotes> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<ReleaseNotes> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first release notes in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	public static ReleaseNotes findByUuid_First(
			String uuid, OrderByComparator<ReleaseNotes> orderByComparator)
		throws com.liferay.osb.customer.release.notes.exception.
			NoSuchReleaseNotesException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first release notes in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	public static ReleaseNotes fetchByUuid_First(
		String uuid, OrderByComparator<ReleaseNotes> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last release notes in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	public static ReleaseNotes findByUuid_Last(
			String uuid, OrderByComparator<ReleaseNotes> orderByComparator)
		throws com.liferay.osb.customer.release.notes.exception.
			NoSuchReleaseNotesException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last release notes in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	public static ReleaseNotes fetchByUuid_Last(
		String uuid, OrderByComparator<ReleaseNotes> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the release noteses before and after the current release notes in the ordered set where uuid = &#63;.
	 *
	 * @param releaseNotesId the primary key of the current release notes
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next release notes
	 * @throws NoSuchReleaseNotesException if a release notes with the primary key could not be found
	 */
	public static ReleaseNotes[] findByUuid_PrevAndNext(
			long releaseNotesId, String uuid,
			OrderByComparator<ReleaseNotes> orderByComparator)
		throws com.liferay.osb.customer.release.notes.exception.
			NoSuchReleaseNotesException {

		return getPersistence().findByUuid_PrevAndNext(
			releaseNotesId, uuid, orderByComparator);
	}

	/**
	 * Removes all the release noteses where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of release noteses where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching release noteses
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the release notes where name = &#63; or throws a <code>NoSuchReleaseNotesException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	public static ReleaseNotes findByName(String name)
		throws com.liferay.osb.customer.release.notes.exception.
			NoSuchReleaseNotesException {

		return getPersistence().findByName(name);
	}

	/**
	 * Returns the release notes where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	public static ReleaseNotes fetchByName(String name) {
		return getPersistence().fetchByName(name);
	}

	/**
	 * Returns the release notes where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	public static ReleaseNotes fetchByName(
		String name, boolean useFinderCache) {

		return getPersistence().fetchByName(name, useFinderCache);
	}

	/**
	 * Removes the release notes where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the release notes that was removed
	 */
	public static ReleaseNotes removeByName(String name)
		throws com.liferay.osb.customer.release.notes.exception.
			NoSuchReleaseNotesException {

		return getPersistence().removeByName(name);
	}

	/**
	 * Returns the number of release noteses where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching release noteses
	 */
	public static int countByName(String name) {
		return getPersistence().countByName(name);
	}

	/**
	 * Returns all the release noteses where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the matching release noteses
	 */
	public static List<ReleaseNotes> findByLikeName(String name) {
		return getPersistence().findByLikeName(name);
	}

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
	public static List<ReleaseNotes> findByLikeName(
		String name, int start, int end) {

		return getPersistence().findByLikeName(name, start, end);
	}

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
	public static List<ReleaseNotes> findByLikeName(
		String name, int start, int end,
		OrderByComparator<ReleaseNotes> orderByComparator) {

		return getPersistence().findByLikeName(
			name, start, end, orderByComparator);
	}

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
	public static List<ReleaseNotes> findByLikeName(
		String name, int start, int end,
		OrderByComparator<ReleaseNotes> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByLikeName(
			name, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first release notes in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	public static ReleaseNotes findByLikeName_First(
			String name, OrderByComparator<ReleaseNotes> orderByComparator)
		throws com.liferay.osb.customer.release.notes.exception.
			NoSuchReleaseNotesException {

		return getPersistence().findByLikeName_First(name, orderByComparator);
	}

	/**
	 * Returns the first release notes in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	public static ReleaseNotes fetchByLikeName_First(
		String name, OrderByComparator<ReleaseNotes> orderByComparator) {

		return getPersistence().fetchByLikeName_First(name, orderByComparator);
	}

	/**
	 * Returns the last release notes in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	public static ReleaseNotes findByLikeName_Last(
			String name, OrderByComparator<ReleaseNotes> orderByComparator)
		throws com.liferay.osb.customer.release.notes.exception.
			NoSuchReleaseNotesException {

		return getPersistence().findByLikeName_Last(name, orderByComparator);
	}

	/**
	 * Returns the last release notes in the ordered set where name LIKE &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	public static ReleaseNotes fetchByLikeName_Last(
		String name, OrderByComparator<ReleaseNotes> orderByComparator) {

		return getPersistence().fetchByLikeName_Last(name, orderByComparator);
	}

	/**
	 * Returns the release noteses before and after the current release notes in the ordered set where name LIKE &#63;.
	 *
	 * @param releaseNotesId the primary key of the current release notes
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next release notes
	 * @throws NoSuchReleaseNotesException if a release notes with the primary key could not be found
	 */
	public static ReleaseNotes[] findByLikeName_PrevAndNext(
			long releaseNotesId, String name,
			OrderByComparator<ReleaseNotes> orderByComparator)
		throws com.liferay.osb.customer.release.notes.exception.
			NoSuchReleaseNotesException {

		return getPersistence().findByLikeName_PrevAndNext(
			releaseNotesId, name, orderByComparator);
	}

	/**
	 * Removes all the release noteses where name LIKE &#63; from the database.
	 *
	 * @param name the name
	 */
	public static void removeByLikeName(String name) {
		getPersistence().removeByLikeName(name);
	}

	/**
	 * Returns the number of release noteses where name LIKE &#63;.
	 *
	 * @param name the name
	 * @return the number of matching release noteses
	 */
	public static int countByLikeName(String name) {
		return getPersistence().countByLikeName(name);
	}

	/**
	 * Returns the release notes where jiraIssueKeys = &#63; or throws a <code>NoSuchReleaseNotesException</code> if it could not be found.
	 *
	 * @param jiraIssueKeys the jira issue keys
	 * @return the matching release notes
	 * @throws NoSuchReleaseNotesException if a matching release notes could not be found
	 */
	public static ReleaseNotes findByJIRAIssueKeys(String jiraIssueKeys)
		throws com.liferay.osb.customer.release.notes.exception.
			NoSuchReleaseNotesException {

		return getPersistence().findByJIRAIssueKeys(jiraIssueKeys);
	}

	/**
	 * Returns the release notes where jiraIssueKeys = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param jiraIssueKeys the jira issue keys
	 * @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	public static ReleaseNotes fetchByJIRAIssueKeys(String jiraIssueKeys) {
		return getPersistence().fetchByJIRAIssueKeys(jiraIssueKeys);
	}

	/**
	 * Returns the release notes where jiraIssueKeys = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param jiraIssueKeys the jira issue keys
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	 */
	public static ReleaseNotes fetchByJIRAIssueKeys(
		String jiraIssueKeys, boolean useFinderCache) {

		return getPersistence().fetchByJIRAIssueKeys(
			jiraIssueKeys, useFinderCache);
	}

	/**
	 * Removes the release notes where jiraIssueKeys = &#63; from the database.
	 *
	 * @param jiraIssueKeys the jira issue keys
	 * @return the release notes that was removed
	 */
	public static ReleaseNotes removeByJIRAIssueKeys(String jiraIssueKeys)
		throws com.liferay.osb.customer.release.notes.exception.
			NoSuchReleaseNotesException {

		return getPersistence().removeByJIRAIssueKeys(jiraIssueKeys);
	}

	/**
	 * Returns the number of release noteses where jiraIssueKeys = &#63;.
	 *
	 * @param jiraIssueKeys the jira issue keys
	 * @return the number of matching release noteses
	 */
	public static int countByJIRAIssueKeys(String jiraIssueKeys) {
		return getPersistence().countByJIRAIssueKeys(jiraIssueKeys);
	}

	/**
	 * Caches the release notes in the entity cache if it is enabled.
	 *
	 * @param releaseNotes the release notes
	 */
	public static void cacheResult(ReleaseNotes releaseNotes) {
		getPersistence().cacheResult(releaseNotes);
	}

	/**
	 * Caches the release noteses in the entity cache if it is enabled.
	 *
	 * @param releaseNoteses the release noteses
	 */
	public static void cacheResult(List<ReleaseNotes> releaseNoteses) {
		getPersistence().cacheResult(releaseNoteses);
	}

	/**
	 * Creates a new release notes with the primary key. Does not add the release notes to the database.
	 *
	 * @param releaseNotesId the primary key for the new release notes
	 * @return the new release notes
	 */
	public static ReleaseNotes create(long releaseNotesId) {
		return getPersistence().create(releaseNotesId);
	}

	/**
	 * Removes the release notes with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param releaseNotesId the primary key of the release notes
	 * @return the release notes that was removed
	 * @throws NoSuchReleaseNotesException if a release notes with the primary key could not be found
	 */
	public static ReleaseNotes remove(long releaseNotesId)
		throws com.liferay.osb.customer.release.notes.exception.
			NoSuchReleaseNotesException {

		return getPersistence().remove(releaseNotesId);
	}

	public static ReleaseNotes updateImpl(ReleaseNotes releaseNotes) {
		return getPersistence().updateImpl(releaseNotes);
	}

	/**
	 * Returns the release notes with the primary key or throws a <code>NoSuchReleaseNotesException</code> if it could not be found.
	 *
	 * @param releaseNotesId the primary key of the release notes
	 * @return the release notes
	 * @throws NoSuchReleaseNotesException if a release notes with the primary key could not be found
	 */
	public static ReleaseNotes findByPrimaryKey(long releaseNotesId)
		throws com.liferay.osb.customer.release.notes.exception.
			NoSuchReleaseNotesException {

		return getPersistence().findByPrimaryKey(releaseNotesId);
	}

	/**
	 * Returns the release notes with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param releaseNotesId the primary key of the release notes
	 * @return the release notes, or <code>null</code> if a release notes with the primary key could not be found
	 */
	public static ReleaseNotes fetchByPrimaryKey(long releaseNotesId) {
		return getPersistence().fetchByPrimaryKey(releaseNotesId);
	}

	/**
	 * Returns all the release noteses.
	 *
	 * @return the release noteses
	 */
	public static List<ReleaseNotes> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<ReleaseNotes> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<ReleaseNotes> findAll(
		int start, int end, OrderByComparator<ReleaseNotes> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ReleaseNotes> findAll(
		int start, int end, OrderByComparator<ReleaseNotes> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the release noteses from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of release noteses.
	 *
	 * @return the number of release noteses
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ReleaseNotesPersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(ReleaseNotesPersistence persistence) {
		_persistence = persistence;
	}

	private static volatile ReleaseNotesPersistence _persistence;

}