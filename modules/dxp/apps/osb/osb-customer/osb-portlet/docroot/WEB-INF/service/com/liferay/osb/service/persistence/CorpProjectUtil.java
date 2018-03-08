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

import com.liferay.osb.model.CorpProject;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the corp project service. This utility wraps {@link com.liferay.osb.service.persistence.impl.CorpProjectPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectPersistence
 * @see com.liferay.osb.service.persistence.impl.CorpProjectPersistenceImpl
 * @generated
 */
@ProviderType
public class CorpProjectUtil {
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
	public static void clearCache(CorpProject corpProject) {
		getPersistence().clearCache(corpProject);
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
	public static List<CorpProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CorpProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CorpProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CorpProject> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CorpProject update(CorpProject corpProject) {
		return getPersistence().update(corpProject);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CorpProject update(CorpProject corpProject,
		ServiceContext serviceContext) {
		return getPersistence().update(corpProject, serviceContext);
	}

	/**
	* Returns all the corp projects where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching corp projects
	*/
	public static List<CorpProject> findByUuid(java.lang.String uuid) {
		return getPersistence().findByUuid(uuid);
	}

	/**
	* Returns a range of all the corp projects where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of corp projects
	* @param end the upper bound of the range of corp projects (not inclusive)
	* @return the range of matching corp projects
	*/
	public static List<CorpProject> findByUuid(java.lang.String uuid,
		int start, int end) {
		return getPersistence().findByUuid(uuid, start, end);
	}

	/**
	* Returns an ordered range of all the corp projects where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of corp projects
	* @param end the upper bound of the range of corp projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp projects
	*/
	public static List<CorpProject> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<CorpProject> orderByComparator) {
		return getPersistence().findByUuid(uuid, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the corp projects where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of corp projects
	* @param end the upper bound of the range of corp projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching corp projects
	*/
	public static List<CorpProject> findByUuid(java.lang.String uuid,
		int start, int end, OrderByComparator<CorpProject> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByUuid(uuid, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first corp project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project
	* @throws NoSuchCorpProjectException if a matching corp project could not be found
	*/
	public static CorpProject findByUuid_First(java.lang.String uuid,
		OrderByComparator<CorpProject> orderByComparator)
		throws com.liferay.osb.exception.NoSuchCorpProjectException {
		return getPersistence().findByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the first corp project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project, or <code>null</code> if a matching corp project could not be found
	*/
	public static CorpProject fetchByUuid_First(java.lang.String uuid,
		OrderByComparator<CorpProject> orderByComparator) {
		return getPersistence().fetchByUuid_First(uuid, orderByComparator);
	}

	/**
	* Returns the last corp project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project
	* @throws NoSuchCorpProjectException if a matching corp project could not be found
	*/
	public static CorpProject findByUuid_Last(java.lang.String uuid,
		OrderByComparator<CorpProject> orderByComparator)
		throws com.liferay.osb.exception.NoSuchCorpProjectException {
		return getPersistence().findByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the last corp project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project, or <code>null</code> if a matching corp project could not be found
	*/
	public static CorpProject fetchByUuid_Last(java.lang.String uuid,
		OrderByComparator<CorpProject> orderByComparator) {
		return getPersistence().fetchByUuid_Last(uuid, orderByComparator);
	}

	/**
	* Returns the corp projects before and after the current corp project in the ordered set where uuid = &#63;.
	*
	* @param corpProjectId the primary key of the current corp project
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next corp project
	* @throws NoSuchCorpProjectException if a corp project with the primary key could not be found
	*/
	public static CorpProject[] findByUuid_PrevAndNext(long corpProjectId,
		java.lang.String uuid, OrderByComparator<CorpProject> orderByComparator)
		throws com.liferay.osb.exception.NoSuchCorpProjectException {
		return getPersistence()
				   .findByUuid_PrevAndNext(corpProjectId, uuid,
			orderByComparator);
	}

	/**
	* Removes all the corp projects where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public static void removeByUuid(java.lang.String uuid) {
		getPersistence().removeByUuid(uuid);
	}

	/**
	* Returns the number of corp projects where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching corp projects
	*/
	public static int countByUuid(java.lang.String uuid) {
		return getPersistence().countByUuid(uuid);
	}

	/**
	* Returns the corp project where dossieraProjectKey = &#63; or throws a {@link NoSuchCorpProjectException} if it could not be found.
	*
	* @param dossieraProjectKey the dossiera project key
	* @return the matching corp project
	* @throws NoSuchCorpProjectException if a matching corp project could not be found
	*/
	public static CorpProject findByDossieraProjectKey(
		java.lang.String dossieraProjectKey)
		throws com.liferay.osb.exception.NoSuchCorpProjectException {
		return getPersistence().findByDossieraProjectKey(dossieraProjectKey);
	}

	/**
	* Returns the corp project where dossieraProjectKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossieraProjectKey the dossiera project key
	* @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	*/
	public static CorpProject fetchByDossieraProjectKey(
		java.lang.String dossieraProjectKey) {
		return getPersistence().fetchByDossieraProjectKey(dossieraProjectKey);
	}

	/**
	* Returns the corp project where dossieraProjectKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossieraProjectKey the dossiera project key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	*/
	public static CorpProject fetchByDossieraProjectKey(
		java.lang.String dossieraProjectKey, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByDossieraProjectKey(dossieraProjectKey,
			retrieveFromCache);
	}

	/**
	* Removes the corp project where dossieraProjectKey = &#63; from the database.
	*
	* @param dossieraProjectKey the dossiera project key
	* @return the corp project that was removed
	*/
	public static CorpProject removeByDossieraProjectKey(
		java.lang.String dossieraProjectKey)
		throws com.liferay.osb.exception.NoSuchCorpProjectException {
		return getPersistence().removeByDossieraProjectKey(dossieraProjectKey);
	}

	/**
	* Returns the number of corp projects where dossieraProjectKey = &#63;.
	*
	* @param dossieraProjectKey the dossiera project key
	* @return the number of matching corp projects
	*/
	public static int countByDossieraProjectKey(
		java.lang.String dossieraProjectKey) {
		return getPersistence().countByDossieraProjectKey(dossieraProjectKey);
	}

	/**
	* Returns all the corp projects where name LIKE &#63;.
	*
	* @param name the name
	* @return the matching corp projects
	*/
	public static List<CorpProject> findByName(java.lang.String name) {
		return getPersistence().findByName(name);
	}

	/**
	* Returns a range of all the corp projects where name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of corp projects
	* @param end the upper bound of the range of corp projects (not inclusive)
	* @return the range of matching corp projects
	*/
	public static List<CorpProject> findByName(java.lang.String name,
		int start, int end) {
		return getPersistence().findByName(name, start, end);
	}

	/**
	* Returns an ordered range of all the corp projects where name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of corp projects
	* @param end the upper bound of the range of corp projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp projects
	*/
	public static List<CorpProject> findByName(java.lang.String name,
		int start, int end, OrderByComparator<CorpProject> orderByComparator) {
		return getPersistence().findByName(name, start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the corp projects where name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of corp projects
	* @param end the upper bound of the range of corp projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching corp projects
	*/
	public static List<CorpProject> findByName(java.lang.String name,
		int start, int end, OrderByComparator<CorpProject> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByName(name, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first corp project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project
	* @throws NoSuchCorpProjectException if a matching corp project could not be found
	*/
	public static CorpProject findByName_First(java.lang.String name,
		OrderByComparator<CorpProject> orderByComparator)
		throws com.liferay.osb.exception.NoSuchCorpProjectException {
		return getPersistence().findByName_First(name, orderByComparator);
	}

	/**
	* Returns the first corp project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project, or <code>null</code> if a matching corp project could not be found
	*/
	public static CorpProject fetchByName_First(java.lang.String name,
		OrderByComparator<CorpProject> orderByComparator) {
		return getPersistence().fetchByName_First(name, orderByComparator);
	}

	/**
	* Returns the last corp project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project
	* @throws NoSuchCorpProjectException if a matching corp project could not be found
	*/
	public static CorpProject findByName_Last(java.lang.String name,
		OrderByComparator<CorpProject> orderByComparator)
		throws com.liferay.osb.exception.NoSuchCorpProjectException {
		return getPersistence().findByName_Last(name, orderByComparator);
	}

	/**
	* Returns the last corp project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project, or <code>null</code> if a matching corp project could not be found
	*/
	public static CorpProject fetchByName_Last(java.lang.String name,
		OrderByComparator<CorpProject> orderByComparator) {
		return getPersistence().fetchByName_Last(name, orderByComparator);
	}

	/**
	* Returns the corp projects before and after the current corp project in the ordered set where name LIKE &#63;.
	*
	* @param corpProjectId the primary key of the current corp project
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next corp project
	* @throws NoSuchCorpProjectException if a corp project with the primary key could not be found
	*/
	public static CorpProject[] findByName_PrevAndNext(long corpProjectId,
		java.lang.String name, OrderByComparator<CorpProject> orderByComparator)
		throws com.liferay.osb.exception.NoSuchCorpProjectException {
		return getPersistence()
				   .findByName_PrevAndNext(corpProjectId, name,
			orderByComparator);
	}

	/**
	* Removes all the corp projects where name LIKE &#63; from the database.
	*
	* @param name the name
	*/
	public static void removeByName(java.lang.String name) {
		getPersistence().removeByName(name);
	}

	/**
	* Returns the number of corp projects where name LIKE &#63;.
	*
	* @param name the name
	* @return the number of matching corp projects
	*/
	public static int countByName(java.lang.String name) {
		return getPersistence().countByName(name);
	}

	/**
	* Returns the corp project where organizationId = &#63; or throws a {@link NoSuchCorpProjectException} if it could not be found.
	*
	* @param organizationId the organization ID
	* @return the matching corp project
	* @throws NoSuchCorpProjectException if a matching corp project could not be found
	*/
	public static CorpProject findByOrganizationId(long organizationId)
		throws com.liferay.osb.exception.NoSuchCorpProjectException {
		return getPersistence().findByOrganizationId(organizationId);
	}

	/**
	* Returns the corp project where organizationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param organizationId the organization ID
	* @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	*/
	public static CorpProject fetchByOrganizationId(long organizationId) {
		return getPersistence().fetchByOrganizationId(organizationId);
	}

	/**
	* Returns the corp project where organizationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param organizationId the organization ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	*/
	public static CorpProject fetchByOrganizationId(long organizationId,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByOrganizationId(organizationId, retrieveFromCache);
	}

	/**
	* Removes the corp project where organizationId = &#63; from the database.
	*
	* @param organizationId the organization ID
	* @return the corp project that was removed
	*/
	public static CorpProject removeByOrganizationId(long organizationId)
		throws com.liferay.osb.exception.NoSuchCorpProjectException {
		return getPersistence().removeByOrganizationId(organizationId);
	}

	/**
	* Returns the number of corp projects where organizationId = &#63;.
	*
	* @param organizationId the organization ID
	* @return the number of matching corp projects
	*/
	public static int countByOrganizationId(long organizationId) {
		return getPersistence().countByOrganizationId(organizationId);
	}

	/**
	* Caches the corp project in the entity cache if it is enabled.
	*
	* @param corpProject the corp project
	*/
	public static void cacheResult(CorpProject corpProject) {
		getPersistence().cacheResult(corpProject);
	}

	/**
	* Caches the corp projects in the entity cache if it is enabled.
	*
	* @param corpProjects the corp projects
	*/
	public static void cacheResult(List<CorpProject> corpProjects) {
		getPersistence().cacheResult(corpProjects);
	}

	/**
	* Creates a new corp project with the primary key. Does not add the corp project to the database.
	*
	* @param corpProjectId the primary key for the new corp project
	* @return the new corp project
	*/
	public static CorpProject create(long corpProjectId) {
		return getPersistence().create(corpProjectId);
	}

	/**
	* Removes the corp project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpProjectId the primary key of the corp project
	* @return the corp project that was removed
	* @throws NoSuchCorpProjectException if a corp project with the primary key could not be found
	*/
	public static CorpProject remove(long corpProjectId)
		throws com.liferay.osb.exception.NoSuchCorpProjectException {
		return getPersistence().remove(corpProjectId);
	}

	public static CorpProject updateImpl(CorpProject corpProject) {
		return getPersistence().updateImpl(corpProject);
	}

	/**
	* Returns the corp project with the primary key or throws a {@link NoSuchCorpProjectException} if it could not be found.
	*
	* @param corpProjectId the primary key of the corp project
	* @return the corp project
	* @throws NoSuchCorpProjectException if a corp project with the primary key could not be found
	*/
	public static CorpProject findByPrimaryKey(long corpProjectId)
		throws com.liferay.osb.exception.NoSuchCorpProjectException {
		return getPersistence().findByPrimaryKey(corpProjectId);
	}

	/**
	* Returns the corp project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param corpProjectId the primary key of the corp project
	* @return the corp project, or <code>null</code> if a corp project with the primary key could not be found
	*/
	public static CorpProject fetchByPrimaryKey(long corpProjectId) {
		return getPersistence().fetchByPrimaryKey(corpProjectId);
	}

	public static java.util.Map<java.io.Serializable, CorpProject> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the corp projects.
	*
	* @return the corp projects
	*/
	public static List<CorpProject> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the corp projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of corp projects
	* @param end the upper bound of the range of corp projects (not inclusive)
	* @return the range of corp projects
	*/
	public static List<CorpProject> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the corp projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of corp projects
	* @param end the upper bound of the range of corp projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of corp projects
	*/
	public static List<CorpProject> findAll(int start, int end,
		OrderByComparator<CorpProject> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the corp projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link CorpProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of corp projects
	* @param end the upper bound of the range of corp projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of corp projects
	*/
	public static List<CorpProject> findAll(int start, int end,
		OrderByComparator<CorpProject> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the corp projects from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of corp projects.
	*
	* @return the number of corp projects
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static CorpProjectPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CorpProjectPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ServletContextUtil.getServletContextName(),
					CorpProjectPersistence.class.getName());

			ReferenceRegistry.registerReference(CorpProjectUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static CorpProjectPersistence _persistence;
}