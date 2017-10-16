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

import com.liferay.osb.customer.release.notes.model.ReleaseNotes;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the release notes service. This utility wraps {@link com.liferay.osb.customer.release.notes.service.persistence.impl.ReleaseNotesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReleaseNotesPersistence
 * @see com.liferay.osb.customer.release.notes.service.persistence.impl.ReleaseNotesPersistenceImpl
 * @generated
 */
@ProviderType
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
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
	public static ReleaseNotes update(ReleaseNotes releaseNotes,
		ServiceContext serviceContext) {
		return getPersistence().update(releaseNotes, serviceContext);
	}

	/**
	* Returns all the release noteses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching release noteses
	*/
	public static List<ReleaseNotes> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

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
	public static List<ReleaseNotes> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

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
	public static List<ReleaseNotes> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<ReleaseNotes> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

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
	public static List<ReleaseNotes> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<ReleaseNotes> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first release notes in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching release notes
	* @throws NoSuchReleaseNotesException if a matching release notes could not be found
	*/
	public static ReleaseNotes findByUuid_First(java.lang.String uuid,
		OrderByComparator<ReleaseNotes> orderByComparator)
		throws com.liferay.osb.customer.release.notes.exception.NoSuchReleaseNotesException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first release notes in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching release notes, or <code>null</code> if a matching release notes could not be found
	*/
	public static ReleaseNotes fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<ReleaseNotes> orderByComparator) {
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
	public static ReleaseNotes findByUuid_Last(java.lang.String uuid,
		OrderByComparator<ReleaseNotes> orderByComparator)
		throws com.liferay.osb.customer.release.notes.exception.NoSuchReleaseNotesException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last release notes in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching release notes, or <code>null</code> if a matching release notes could not be found
	*/
	public static ReleaseNotes fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<ReleaseNotes> orderByComparator) {
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
	public static ReleaseNotes[] findByUuid_PrevAndNext(long releaseNotesId,
		java.lang.String uuid, OrderByComparator<ReleaseNotes> orderByComparator)
		throws com.liferay.osb.customer.release.notes.exception.NoSuchReleaseNotesException {
		return getPersistence()
				   .findByUuid_PrevAndNext(releaseNotesId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the release noteses where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of release noteses where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching release noteses
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the release notes where name = &#63; or throws a {@link NoSuchReleaseNotesException} if it could not be found.
	*
	* @param name the name
	* @return the matching release notes
	* @throws NoSuchReleaseNotesException if a matching release notes could not be found
	*/
	public static ReleaseNotes findByName(java.lang.String name)
		throws com.liferay.osb.customer.release.notes.exception.NoSuchReleaseNotesException {
		return getPersistence().findByName(name);
	}

	/**
	* Returns the release notes where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	*/
	public static ReleaseNotes fetchByName(java.lang.String name) {
		return getPersistence().fetchByName(name);
	}

	/**
	* Returns the release notes where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	*/
	public static ReleaseNotes fetchByName(java.lang.String name,
		boolean retrieveFromCache) {
		return getPersistence().fetchByName(name, retrieveFromCache);
	}

	/**
	* Removes the release notes where name = &#63; from the database.
	*
	* @param name the name
	* @return the release notes that was removed
	*/
	public static ReleaseNotes removeByName(java.lang.String name)
		throws com.liferay.osb.customer.release.notes.exception.NoSuchReleaseNotesException {
		return getPersistence().removeByName(name);
	}

	/**
	* Returns the number of release noteses where name = &#63;.
	*
	* @param name the name
	* @return the number of matching release noteses
	*/
	public static int countByName(java.lang.String name) {
		return getPersistence().countByName(name);
	}

	/**
	* Returns the release notes where jiraIssueKeys = &#63; or throws a {@link NoSuchReleaseNotesException} if it could not be found.
	*
	* @param jiraIssueKeys the jira issue keys
	* @return the matching release notes
	* @throws NoSuchReleaseNotesException if a matching release notes could not be found
	*/
	public static ReleaseNotes findByJIRAIssueKeys(
		java.lang.String jiraIssueKeys)
		throws com.liferay.osb.customer.release.notes.exception.NoSuchReleaseNotesException {
		return getPersistence().findByJIRAIssueKeys(jiraIssueKeys);
	}

	/**
	* Returns the release notes where jiraIssueKeys = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param jiraIssueKeys the jira issue keys
	* @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	*/
	public static ReleaseNotes fetchByJIRAIssueKeys(
		java.lang.String jiraIssueKeys) {
		return getPersistence().fetchByJIRAIssueKeys(jiraIssueKeys);
	}

	/**
	* Returns the release notes where jiraIssueKeys = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param jiraIssueKeys the jira issue keys
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching release notes, or <code>null</code> if a matching release notes could not be found
	*/
	public static ReleaseNotes fetchByJIRAIssueKeys(
		java.lang.String jiraIssueKeys, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByJIRAIssueKeys(jiraIssueKeys, retrieveFromCache);
	}

	/**
	* Removes the release notes where jiraIssueKeys = &#63; from the database.
	*
	* @param jiraIssueKeys the jira issue keys
	* @return the release notes that was removed
	*/
	public static ReleaseNotes removeByJIRAIssueKeys(
		java.lang.String jiraIssueKeys)
		throws com.liferay.osb.customer.release.notes.exception.NoSuchReleaseNotesException {
		return getPersistence().removeByJIRAIssueKeys(jiraIssueKeys);
	}

	/**
	* Returns the number of release noteses where jiraIssueKeys = &#63;.
	*
	* @param jiraIssueKeys the jira issue keys
	* @return the number of matching release noteses
	*/
	public static int countByJIRAIssueKeys(java.lang.String jiraIssueKeys) {
		return getPersistence().countByJIRAIssueKeys(jiraIssueKeys);
	}

	/**
	* Returns all the release noteses where name LIKE &#63;.
	*
	* @param name the name
	* @return the matching release noteses
	*/
	public static List<ReleaseNotes> findByLikeName(java.lang.String name) {
		return getPersistence().findByLikeName(name);
	}

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
	public static List<ReleaseNotes> findByLikeName(java.lang.String name,
		int start, int end) {
		return getPersistence().findByLikeName(name, start, end);
	}

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
	public static List<ReleaseNotes> findByLikeName(java.lang.String name,
		int start, int end, OrderByComparator<ReleaseNotes> orderByComparator) {
		return getPersistence()
				   .findByLikeName(name, start, end, orderByComparator);
	}

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
	public static List<ReleaseNotes> findByLikeName(java.lang.String name,
		int start, int end, OrderByComparator<ReleaseNotes> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByLikeName(name, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first release notes in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching release notes
	* @throws NoSuchReleaseNotesException if a matching release notes could not be found
	*/
	public static ReleaseNotes findByLikeName_First(java.lang.String name,
		OrderByComparator<ReleaseNotes> orderByComparator)
		throws com.liferay.osb.customer.release.notes.exception.NoSuchReleaseNotesException {
		return getPersistence().findByLikeName_First(name, orderByComparator);
	}

	/**
	* Returns the first release notes in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching release notes, or <code>null</code> if a matching release notes could not be found
	*/
	public static ReleaseNotes fetchByLikeName_First(java.lang.String name,
		OrderByComparator<ReleaseNotes> orderByComparator) {
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
	public static ReleaseNotes findByLikeName_Last(java.lang.String name,
		OrderByComparator<ReleaseNotes> orderByComparator)
		throws com.liferay.osb.customer.release.notes.exception.NoSuchReleaseNotesException {
		return getPersistence().findByLikeName_Last(name, orderByComparator);
	}

	/**
	* Returns the last release notes in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching release notes, or <code>null</code> if a matching release notes could not be found
	*/
	public static ReleaseNotes fetchByLikeName_Last(java.lang.String name,
		OrderByComparator<ReleaseNotes> orderByComparator) {
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
		long releaseNotesId, java.lang.String name,
		OrderByComparator<ReleaseNotes> orderByComparator)
		throws com.liferay.osb.customer.release.notes.exception.NoSuchReleaseNotesException {
		return getPersistence()
				   .findByLikeName_PrevAndNext(releaseNotesId, name,
			orderByComparator);
	}

	/**
	* Removes all the release noteses where name LIKE &#63; from the database.
	*
	* @param name the name
	*/
	public static void removeByLikeName(java.lang.String name) {
		getPersistence().removeByLikeName(name);
	}

	/**
	* Returns the number of release noteses where name LIKE &#63;.
	*
	* @param name the name
	* @return the number of matching release noteses
	*/
	public static int countByLikeName(java.lang.String name) {
		return getPersistence().countByLikeName(name);
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
		throws com.liferay.osb.customer.release.notes.exception.NoSuchReleaseNotesException {
		return getPersistence().remove(releaseNotesId);
	}

	public static ReleaseNotes updateImpl(ReleaseNotes releaseNotes) {
		return getPersistence().updateImpl(releaseNotes);
	}

	/**
	* Returns the release notes with the primary key or throws a {@link NoSuchReleaseNotesException} if it could not be found.
	*
	* @param releaseNotesId the primary key of the release notes
	* @return the release notes
	* @throws NoSuchReleaseNotesException if a release notes with the primary key could not be found
	*/
	public static ReleaseNotes findByPrimaryKey(long releaseNotesId)
		throws com.liferay.osb.customer.release.notes.exception.NoSuchReleaseNotesException {
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

	public static java.util.Map<java.io.Serializable, ReleaseNotes> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ReleaseNotesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of release noteses
	* @param end the upper bound of the range of release noteses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of release noteses
	*/
	public static List<ReleaseNotes> findAll(int start, int end,
		OrderByComparator<ReleaseNotes> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ReleaseNotes> findAll(int start, int end,
		OrderByComparator<ReleaseNotes> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
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

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ReleaseNotesPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ReleaseNotesPersistence, ReleaseNotesPersistence> _serviceTracker =
		ServiceTrackerFactory.open(ReleaseNotesPersistence.class);
}