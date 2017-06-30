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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the corp project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectPersistenceImpl
 * @see CorpProjectUtil
 * @generated
 */
public interface CorpProjectPersistence extends BasePersistence<CorpProject> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CorpProjectUtil} to access the corp project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the corp project in the entity cache if it is enabled.
	*
	* @param corpProject the corp project
	*/
	public void cacheResult(com.liferay.osb.model.CorpProject corpProject);

	/**
	* Caches the corp projects in the entity cache if it is enabled.
	*
	* @param corpProjects the corp projects
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.CorpProject> corpProjects);

	/**
	* Creates a new corp project with the primary key. Does not add the corp project to the database.
	*
	* @param corpProjectId the primary key for the new corp project
	* @return the new corp project
	*/
	public com.liferay.osb.model.CorpProject create(long corpProjectId);

	/**
	* Removes the corp project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpProjectId the primary key of the corp project
	* @return the corp project that was removed
	* @throws com.liferay.osb.NoSuchCorpProjectException if a corp project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProject remove(long corpProjectId)
		throws com.liferay.osb.NoSuchCorpProjectException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.CorpProject updateImpl(
		com.liferay.osb.model.CorpProject corpProject, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp project with the primary key or throws a {@link com.liferay.osb.NoSuchCorpProjectException} if it could not be found.
	*
	* @param corpProjectId the primary key of the corp project
	* @return the corp project
	* @throws com.liferay.osb.NoSuchCorpProjectException if a corp project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProject findByPrimaryKey(
		long corpProjectId)
		throws com.liferay.osb.NoSuchCorpProjectException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param corpProjectId the primary key of the corp project
	* @return the corp project, or <code>null</code> if a corp project with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProject fetchByPrimaryKey(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp project where dossieraProjectKey = &#63; or throws a {@link com.liferay.osb.NoSuchCorpProjectException} if it could not be found.
	*
	* @param dossieraProjectKey the dossiera project key
	* @return the matching corp project
	* @throws com.liferay.osb.NoSuchCorpProjectException if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProject findByDossieraProjectKey(
		java.lang.String dossieraProjectKey)
		throws com.liferay.osb.NoSuchCorpProjectException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp project where dossieraProjectKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossieraProjectKey the dossiera project key
	* @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProject fetchByDossieraProjectKey(
		java.lang.String dossieraProjectKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp project where dossieraProjectKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossieraProjectKey the dossiera project key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProject fetchByDossieraProjectKey(
		java.lang.String dossieraProjectKey, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the corp projects where name LIKE &#63;.
	*
	* @param name the name
	* @return the matching corp projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpProject> findByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.CorpProject> findByName(
		java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.CorpProject> findByName(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first corp project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project
	* @throws com.liferay.osb.NoSuchCorpProjectException if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProject findByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpProjectException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first corp project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project, or <code>null</code> if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProject fetchByName_First(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last corp project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project
	* @throws com.liferay.osb.NoSuchCorpProjectException if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProject findByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpProjectException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last corp project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project, or <code>null</code> if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProject fetchByName_Last(
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.osb.model.CorpProject[] findByName_PrevAndNext(
		long corpProjectId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchCorpProjectException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp project where organizationId = &#63; or throws a {@link com.liferay.osb.NoSuchCorpProjectException} if it could not be found.
	*
	* @param organizationId the organization ID
	* @return the matching corp project
	* @throws com.liferay.osb.NoSuchCorpProjectException if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProject findByOrganizationId(
		long organizationId)
		throws com.liferay.osb.NoSuchCorpProjectException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp project where organizationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param organizationId the organization ID
	* @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProject fetchByOrganizationId(
		long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the corp project where organizationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param organizationId the organization ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProject fetchByOrganizationId(
		long organizationId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the corp projects.
	*
	* @return the corp projects
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpProject> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.CorpProject> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.CorpProject> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the corp project where dossieraProjectKey = &#63; from the database.
	*
	* @param dossieraProjectKey the dossiera project key
	* @return the corp project that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProject removeByDossieraProjectKey(
		java.lang.String dossieraProjectKey)
		throws com.liferay.osb.NoSuchCorpProjectException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the corp projects where name LIKE &#63; from the database.
	*
	* @param name the name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the corp project where organizationId = &#63; from the database.
	*
	* @param organizationId the organization ID
	* @return the corp project that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpProject removeByOrganizationId(
		long organizationId)
		throws com.liferay.osb.NoSuchCorpProjectException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the corp projects from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp projects where dossieraProjectKey = &#63;.
	*
	* @param dossieraProjectKey the dossiera project key
	* @return the number of matching corp projects
	* @throws SystemException if a system exception occurred
	*/
	public int countByDossieraProjectKey(java.lang.String dossieraProjectKey)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp projects where name LIKE &#63;.
	*
	* @param name the name
	* @return the number of matching corp projects
	* @throws SystemException if a system exception occurred
	*/
	public int countByName(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp projects where organizationId = &#63;.
	*
	* @param organizationId the organization ID
	* @return the number of matching corp projects
	* @throws SystemException if a system exception occurred
	*/
	public int countByOrganizationId(long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of corp projects.
	*
	* @return the number of corp projects
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}