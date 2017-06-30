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

import com.liferay.osb.model.CorpProject;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the corp project service. This utility wraps {@link CorpProjectPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectPersistence
 * @see CorpProjectPersistenceImpl
 * @generated
 */
public class CorpProjectUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(CorpProject corpProject) {
		getPersistence().clearCache(corpProject);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CorpProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CorpProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CorpProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static CorpProject update(CorpProject corpProject, boolean merge)
		throws SystemException {
		return getPersistence().update(corpProject, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static CorpProject update(CorpProject corpProject, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(corpProject, merge, serviceContext);
	}

	/**
	* Caches the corp project in the entity cache if it is enabled.
	*
	* @param corpProject the corp project
	*/
	public static void cacheResult(
		com.liferay.osb.model.CorpProject corpProject) {
		getPersistence().cacheResult(corpProject);
	}

	/**
	* Caches the corp projects in the entity cache if it is enabled.
	*
	* @param corpProjects the corp projects
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.CorpProject> corpProjects) {
		getPersistence().cacheResult(corpProjects);
	}

	/**
	* Creates a new corp project with the primary key. Does not add the corp project to the database.
	*
	* @param corpProjectId the primary key for the new corp project
	* @return the new corp project
	*/
	public static com.liferay.osb.model.CorpProject create(long corpProjectId) {
		return getPersistence().create(corpProjectId);
	}

	/**
	* Removes the corp project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpProjectId the primary key of the corp project
	* @return the corp project that was removed
	* @throws com.liferay.osb.NoSuchCorpProjectException if a corp project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject remove(long corpProjectId)
		throws com.liferay.osb.NoSuchCorpProjectException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(corpProjectId);
	}

	public static com.liferay.osb.model.CorpProject updateImpl(
		com.liferay.osb.model.CorpProject corpProject, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(corpProject, merge);
	}

	/**
	* Returns the corp project with the primary key or throws a {@link com.liferay.osb.NoSuchCorpProjectException} if it could not be found.
	*
	* @param corpProjectId the primary key of the corp project
	* @return the corp project
	* @throws com.liferay.osb.NoSuchCorpProjectException if a corp project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject findByPrimaryKey(
		long corpProjectId)
		throws com.liferay.osb.NoSuchCorpProjectException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(corpProjectId);
	}

	/**
	* Returns the corp project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param corpProjectId the primary key of the corp project
	* @return the corp project, or <code>null</code> if a corp project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject fetchByPrimaryKey(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(corpProjectId);
	}

	/**
	* Returns the corp project where dossieraProjectKey = &#63; or throws a {@link com.liferay.osb.NoSuchCorpProjectException} if it could not be found.
	*
	* @param dossieraProjectKey the dossiera project key
	* @return the matching corp project
	* @throws com.liferay.osb.NoSuchCorpProjectException if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject findByDossieraProjectKey(
		java.lang.String dossieraProjectKey)
		throws com.liferay.osb.NoSuchCorpProjectException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByDossieraProjectKey(dossieraProjectKey);
	}

	/**
	* Returns the corp project where dossieraProjectKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossieraProjectKey the dossiera project key
	* @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject fetchByDossieraProjectKey(
		java.lang.String dossieraProjectKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByDossieraProjectKey(dossieraProjectKey);
	}

	/**
	* Returns the corp project where dossieraProjectKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossieraProjectKey the dossiera project key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject fetchByDossieraProjectKey(
		java.lang.String dossieraProjectKey, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByDossieraProjectKey(dossieraProjectKey,
			retrieveFromCache);
	}

	/**
	* Returns all the corp projects where name LIKE &#63;.
	*
	* @param name the name
	* @return the matching corp projects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpProject> findByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name);
	}

	/**
	* Returns a range of all the corp projects where name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of corp projects
	* @param end the upper bound of the range of corp projects (not inclusive)
	* @return the range of matching corp projects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpProject> findByName(
		java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name, start, end);
	}

	/**
	* Returns an ordered range of all the corp projects where name LIKE &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param name the name
	* @param start the lower bound of the range of corp projects
	* @param end the upper bound of the range of corp projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching corp projects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpProject> findByName(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName(name, start, end, orderByComparator);
	}

	/**
	* Returns the first corp project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project
	* @throws com.liferay.osb.NoSuchCorpProjectException if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject findByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpProjectException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName_First(name, orderByComparator);
	}

	/**
	* Returns the first corp project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project, or <code>null</code> if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject fetchByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByName_First(name, orderByComparator);
	}

	/**
	* Returns the last corp project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project
	* @throws com.liferay.osb.NoSuchCorpProjectException if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject findByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpProjectException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByName_Last(name, orderByComparator);
	}

	/**
	* Returns the last corp project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project, or <code>null</code> if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject fetchByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByName_Last(name, orderByComparator);
	}

	/**
	* Returns the corp projects before and after the current corp project in the ordered set where name LIKE &#63;.
	*
	* @param corpProjectId the primary key of the current corp project
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next corp project
	* @throws com.liferay.osb.NoSuchCorpProjectException if a corp project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject[] findByName_PrevAndNext(
		long corpProjectId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpProjectException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByName_PrevAndNext(corpProjectId, name,
			orderByComparator);
	}

	/**
	* Returns the corp project where organizationId = &#63; or throws a {@link com.liferay.osb.NoSuchCorpProjectException} if it could not be found.
	*
	* @param organizationId the organization ID
	* @return the matching corp project
	* @throws com.liferay.osb.NoSuchCorpProjectException if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject findByOrganizationId(
		long organizationId)
		throws com.liferay.osb.NoSuchCorpProjectException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByOrganizationId(organizationId);
	}

	/**
	* Returns the corp project where organizationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param organizationId the organization ID
	* @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject fetchByOrganizationId(
		long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByOrganizationId(organizationId);
	}

	/**
	* Returns the corp project where organizationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param organizationId the organization ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject fetchByOrganizationId(
		long organizationId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByOrganizationId(organizationId, retrieveFromCache);
	}

	/**
	* Returns all the corp projects.
	*
	* @return the corp projects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpProject> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the corp projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of corp projects
	* @param end the upper bound of the range of corp projects (not inclusive)
	* @return the range of corp projects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpProject> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the corp projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of corp projects
	* @param end the upper bound of the range of corp projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of corp projects
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpProject> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the corp project where dossieraProjectKey = &#63; from the database.
	*
	* @param dossieraProjectKey the dossiera project key
	* @return the corp project that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject removeByDossieraProjectKey(
		java.lang.String dossieraProjectKey)
		throws com.liferay.osb.NoSuchCorpProjectException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByDossieraProjectKey(dossieraProjectKey);
	}

	/**
	* Removes all the corp projects where name LIKE &#63; from the database.
	*
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByName(name);
	}

	/**
	* Removes the corp project where organizationId = &#63; from the database.
	*
	* @param organizationId the organization ID
	* @return the corp project that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProject removeByOrganizationId(
		long organizationId)
		throws com.liferay.osb.NoSuchCorpProjectException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByOrganizationId(organizationId);
	}

	/**
	* Removes all the corp projects from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of corp projects where dossieraProjectKey = &#63;.
	*
	* @param dossieraProjectKey the dossiera project key
	* @return the number of matching corp projects
	* @throws SystemException if a system exception occurred
	*/
	public static int countByDossieraProjectKey(
		java.lang.String dossieraProjectKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByDossieraProjectKey(dossieraProjectKey);
	}

	/**
	* Returns the number of corp projects where name LIKE &#63;.
	*
	* @param name the name
	* @return the number of matching corp projects
	* @throws SystemException if a system exception occurred
	*/
	public static int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByName(name);
	}

	/**
	* Returns the number of corp projects where organizationId = &#63;.
	*
	* @param organizationId the organization ID
	* @return the number of matching corp projects
	* @throws SystemException if a system exception occurred
	*/
	public static int countByOrganizationId(long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByOrganizationId(organizationId);
	}

	/**
	* Returns the number of corp projects.
	*
	* @return the number of corp projects
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static CorpProjectPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CorpProjectPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					CorpProjectPersistence.class.getName());

			ReferenceRegistry.registerReference(CorpProjectUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(CorpProjectPersistence persistence) {
	}

	private static CorpProjectPersistence _persistence;
}