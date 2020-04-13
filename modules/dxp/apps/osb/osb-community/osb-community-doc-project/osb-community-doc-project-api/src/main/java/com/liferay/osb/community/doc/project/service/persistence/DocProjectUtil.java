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

package com.liferay.osb.community.doc.project.service.persistence;

import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the doc project service. This utility wraps <code>com.liferay.osb.community.doc.project.service.persistence.impl.DocProjectPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @see DocProjectPersistence
 * @generated
 */
public class DocProjectUtil {

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
	public static void clearCache(DocProject docProject) {
		getPersistence().clearCache(docProject);
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
	public static Map<Serializable, DocProject> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<DocProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DocProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DocProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DocProject> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DocProject update(DocProject docProject) {
		return getPersistence().update(docProject);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DocProject update(
		DocProject docProject, ServiceContext serviceContext) {

		return getPersistence().update(docProject, serviceContext);
	}

	/**
	 * Returns all the doc projects where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching doc projects
	 */
	public static List<DocProject> findByUuid(String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	 * Returns a range of all the doc projects where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @return the range of matching doc projects
	 */
	public static List<DocProject> findByUuid(String uuid, int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	 * Returns an ordered range of all the doc projects where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching doc projects
	 */
	public static List<DocProject> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<DocProject> orderByComparator) {

		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the doc projects where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching doc projects
	 */
	public static List<DocProject> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<DocProject> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid(
			uuid, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first doc project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	public static DocProject findByUuid_First(
			String uuid, OrderByComparator<DocProject> orderByComparator)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the first doc project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public static DocProject fetchByUuid_First(
		String uuid, OrderByComparator<DocProject> orderByComparator) {

		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	 * Returns the last doc project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	public static DocProject findByUuid_Last(
			String uuid, OrderByComparator<DocProject> orderByComparator)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the last doc project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public static DocProject fetchByUuid_Last(
		String uuid, OrderByComparator<DocProject> orderByComparator) {

		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	 * Returns the doc projects before and after the current doc project in the ordered set where uuid = &#63;.
	 *
	 * @param docProjectId the primary key of the current doc project
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next doc project
	 * @throws NoSuchDocProjectException if a doc project with the primary key could not be found
	 */
	public static DocProject[] findByUuid_PrevAndNext(
			long docProjectId, String uuid,
			OrderByComparator<DocProject> orderByComparator)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getPersistence().findByUuid_PrevAndNext(
			docProjectId, uuid, orderByComparator);
	}

	/**
	 * Removes all the doc projects where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public static void removeByUuid(String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	 * Returns the number of doc projects where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching doc projects
	 */
	public static int countByUuid(String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	 * Returns the doc project where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchDocProjectException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	public static DocProject findByUUID_G(String uuid, long groupId)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getPersistence().findByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the doc project where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public static DocProject fetchByUUID_G(String uuid, long groupId) {
		return getPersistence().fetchByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the doc project where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public static DocProject fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		return getPersistence().fetchByUUID_G(uuid, groupId, useFinderCache);
	}

	/**
	 * Removes the doc project where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the doc project that was removed
	 */
	public static DocProject removeByUUID_G(String uuid, long groupId)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getPersistence().removeByUUID_G(uuid, groupId);
	}

	/**
	 * Returns the number of doc projects where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching doc projects
	 */
	public static int countByUUID_G(String uuid, long groupId) {
		return getPersistence().countByUUID_G(uuid, groupId);
	}

	/**
	 * Returns all the doc projects where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching doc projects
	 */
	public static List<DocProject> findByUuid_C(String uuid, long companyId) {
		return getPersistence().findByUuid_C(uuid, companyId);
	}

	/**
	 * Returns a range of all the doc projects where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @return the range of matching doc projects
	 */
	public static List<DocProject> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return getPersistence().findByUuid_C(uuid, companyId, start, end);
	}

	/**
	 * Returns an ordered range of all the doc projects where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching doc projects
	 */
	public static List<DocProject> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DocProject> orderByComparator) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the doc projects where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching doc projects
	 */
	public static List<DocProject> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DocProject> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUuid_C(
			uuid, companyId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first doc project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	public static DocProject findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<DocProject> orderByComparator)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getPersistence().findByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the first doc project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public static DocProject fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<DocProject> orderByComparator) {

		return getPersistence().fetchByUuid_C_First(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last doc project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	public static DocProject findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<DocProject> orderByComparator)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getPersistence().findByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the last doc project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public static DocProject fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<DocProject> orderByComparator) {

		return getPersistence().fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);
	}

	/**
	 * Returns the doc projects before and after the current doc project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param docProjectId the primary key of the current doc project
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next doc project
	 * @throws NoSuchDocProjectException if a doc project with the primary key could not be found
	 */
	public static DocProject[] findByUuid_C_PrevAndNext(
			long docProjectId, String uuid, long companyId,
			OrderByComparator<DocProject> orderByComparator)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getPersistence().findByUuid_C_PrevAndNext(
			docProjectId, uuid, companyId, orderByComparator);
	}

	/**
	 * Removes all the doc projects where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public static void removeByUuid_C(String uuid, long companyId) {
		getPersistence().removeByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the number of doc projects where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching doc projects
	 */
	public static int countByUuid_C(String uuid, long companyId) {
		return getPersistence().countByUuid_C(uuid, companyId);
	}

	/**
	 * Returns the doc project where groupId = &#63; or throws a <code>NoSuchDocProjectException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @return the matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	public static DocProject findByGroupId(long groupId)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getPersistence().findByGroupId(groupId);
	}

	/**
	 * Returns the doc project where groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public static DocProject fetchByGroupId(long groupId) {
		return getPersistence().fetchByGroupId(groupId);
	}

	/**
	 * Returns the doc project where groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public static DocProject fetchByGroupId(
		long groupId, boolean useFinderCache) {

		return getPersistence().fetchByGroupId(groupId, useFinderCache);
	}

	/**
	 * Removes the doc project where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @return the doc project that was removed
	 */
	public static DocProject removeByGroupId(long groupId)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getPersistence().removeByGroupId(groupId);
	}

	/**
	 * Returns the number of doc projects where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching doc projects
	 */
	public static int countByGroupId(long groupId) {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	 * Returns the doc project where name = &#63; or throws a <code>NoSuchDocProjectException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	public static DocProject findByName(String name)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getPersistence().findByName(name);
	}

	/**
	 * Returns the doc project where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public static DocProject fetchByName(String name) {
		return getPersistence().fetchByName(name);
	}

	/**
	 * Returns the doc project where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public static DocProject fetchByName(String name, boolean useFinderCache) {
		return getPersistence().fetchByName(name, useFinderCache);
	}

	/**
	 * Removes the doc project where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the doc project that was removed
	 */
	public static DocProject removeByName(String name)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getPersistence().removeByName(name);
	}

	/**
	 * Returns the number of doc projects where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching doc projects
	 */
	public static int countByName(String name) {
		return getPersistence().countByName(name);
	}

	/**
	 * Returns all the doc projects where unlisted = &#63; and status = &#63;.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @return the matching doc projects
	 */
	public static List<DocProject> findByU_S(boolean unlisted, int status) {
		return getPersistence().findByU_S(unlisted, status);
	}

	/**
	 * Returns a range of all the doc projects where unlisted = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @return the range of matching doc projects
	 */
	public static List<DocProject> findByU_S(
		boolean unlisted, int status, int start, int end) {

		return getPersistence().findByU_S(unlisted, status, start, end);
	}

	/**
	 * Returns an ordered range of all the doc projects where unlisted = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching doc projects
	 */
	public static List<DocProject> findByU_S(
		boolean unlisted, int status, int start, int end,
		OrderByComparator<DocProject> orderByComparator) {

		return getPersistence().findByU_S(
			unlisted, status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the doc projects where unlisted = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching doc projects
	 */
	public static List<DocProject> findByU_S(
		boolean unlisted, int status, int start, int end,
		OrderByComparator<DocProject> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByU_S(
			unlisted, status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first doc project in the ordered set where unlisted = &#63; and status = &#63;.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	public static DocProject findByU_S_First(
			boolean unlisted, int status,
			OrderByComparator<DocProject> orderByComparator)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getPersistence().findByU_S_First(
			unlisted, status, orderByComparator);
	}

	/**
	 * Returns the first doc project in the ordered set where unlisted = &#63; and status = &#63;.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public static DocProject fetchByU_S_First(
		boolean unlisted, int status,
		OrderByComparator<DocProject> orderByComparator) {

		return getPersistence().fetchByU_S_First(
			unlisted, status, orderByComparator);
	}

	/**
	 * Returns the last doc project in the ordered set where unlisted = &#63; and status = &#63;.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	public static DocProject findByU_S_Last(
			boolean unlisted, int status,
			OrderByComparator<DocProject> orderByComparator)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getPersistence().findByU_S_Last(
			unlisted, status, orderByComparator);
	}

	/**
	 * Returns the last doc project in the ordered set where unlisted = &#63; and status = &#63;.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public static DocProject fetchByU_S_Last(
		boolean unlisted, int status,
		OrderByComparator<DocProject> orderByComparator) {

		return getPersistence().fetchByU_S_Last(
			unlisted, status, orderByComparator);
	}

	/**
	 * Returns the doc projects before and after the current doc project in the ordered set where unlisted = &#63; and status = &#63;.
	 *
	 * @param docProjectId the primary key of the current doc project
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next doc project
	 * @throws NoSuchDocProjectException if a doc project with the primary key could not be found
	 */
	public static DocProject[] findByU_S_PrevAndNext(
			long docProjectId, boolean unlisted, int status,
			OrderByComparator<DocProject> orderByComparator)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getPersistence().findByU_S_PrevAndNext(
			docProjectId, unlisted, status, orderByComparator);
	}

	/**
	 * Removes all the doc projects where unlisted = &#63; and status = &#63; from the database.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 */
	public static void removeByU_S(boolean unlisted, int status) {
		getPersistence().removeByU_S(unlisted, status);
	}

	/**
	 * Returns the number of doc projects where unlisted = &#63; and status = &#63;.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @return the number of matching doc projects
	 */
	public static int countByU_S(boolean unlisted, int status) {
		return getPersistence().countByU_S(unlisted, status);
	}

	/**
	 * Caches the doc project in the entity cache if it is enabled.
	 *
	 * @param docProject the doc project
	 */
	public static void cacheResult(DocProject docProject) {
		getPersistence().cacheResult(docProject);
	}

	/**
	 * Caches the doc projects in the entity cache if it is enabled.
	 *
	 * @param docProjects the doc projects
	 */
	public static void cacheResult(List<DocProject> docProjects) {
		getPersistence().cacheResult(docProjects);
	}

	/**
	 * Creates a new doc project with the primary key. Does not add the doc project to the database.
	 *
	 * @param docProjectId the primary key for the new doc project
	 * @return the new doc project
	 */
	public static DocProject create(long docProjectId) {
		return getPersistence().create(docProjectId);
	}

	/**
	 * Removes the doc project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param docProjectId the primary key of the doc project
	 * @return the doc project that was removed
	 * @throws NoSuchDocProjectException if a doc project with the primary key could not be found
	 */
	public static DocProject remove(long docProjectId)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getPersistence().remove(docProjectId);
	}

	public static DocProject updateImpl(DocProject docProject) {
		return getPersistence().updateImpl(docProject);
	}

	/**
	 * Returns the doc project with the primary key or throws a <code>NoSuchDocProjectException</code> if it could not be found.
	 *
	 * @param docProjectId the primary key of the doc project
	 * @return the doc project
	 * @throws NoSuchDocProjectException if a doc project with the primary key could not be found
	 */
	public static DocProject findByPrimaryKey(long docProjectId)
		throws com.liferay.osb.community.doc.project.exception.
			NoSuchDocProjectException {

		return getPersistence().findByPrimaryKey(docProjectId);
	}

	/**
	 * Returns the doc project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param docProjectId the primary key of the doc project
	 * @return the doc project, or <code>null</code> if a doc project with the primary key could not be found
	 */
	public static DocProject fetchByPrimaryKey(long docProjectId) {
		return getPersistence().fetchByPrimaryKey(docProjectId);
	}

	/**
	 * Returns all the doc projects.
	 *
	 * @return the doc projects
	 */
	public static List<DocProject> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the doc projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @return the range of doc projects
	 */
	public static List<DocProject> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the doc projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of doc projects
	 */
	public static List<DocProject> findAll(
		int start, int end, OrderByComparator<DocProject> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the doc projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of doc projects
	 */
	public static List<DocProject> findAll(
		int start, int end, OrderByComparator<DocProject> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the doc projects from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of doc projects.
	 *
	 * @return the number of doc projects
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static Set<String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static DocProjectPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<DocProjectPersistence, DocProjectPersistence>
		_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(DocProjectPersistence.class);

		ServiceTracker<DocProjectPersistence, DocProjectPersistence>
			serviceTracker =
				new ServiceTracker
					<DocProjectPersistence, DocProjectPersistence>(
						bundle.getBundleContext(), DocProjectPersistence.class,
						null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}