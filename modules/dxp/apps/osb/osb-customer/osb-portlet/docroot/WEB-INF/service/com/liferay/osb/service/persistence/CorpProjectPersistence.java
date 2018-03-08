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

import com.liferay.osb.exception.NoSuchCorpProjectException;
import com.liferay.osb.model.CorpProject;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the corp project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.CorpProjectPersistenceImpl
 * @see CorpProjectUtil
 * @generated
 */
@ProviderType
public interface CorpProjectPersistence extends BasePersistence<CorpProject> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CorpProjectUtil} to access the corp project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the corp projects where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching corp projects
	*/
	public java.util.List<CorpProject> findByUuid(java.lang.String uuid);

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
	public java.util.List<CorpProject> findByUuid(java.lang.String uuid,
		int start, int end);

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
	public java.util.List<CorpProject> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProject> orderByComparator);

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
	public java.util.List<CorpProject> findByUuid(java.lang.String uuid,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProject> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first corp project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project
	* @throws NoSuchCorpProjectException if a matching corp project could not be found
	*/
	public CorpProject findByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProject> orderByComparator)
		throws NoSuchCorpProjectException;

	/**
	* Returns the first corp project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project, or <code>null</code> if a matching corp project could not be found
	*/
	public CorpProject fetchByUuid_First(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProject> orderByComparator);

	/**
	* Returns the last corp project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project
	* @throws NoSuchCorpProjectException if a matching corp project could not be found
	*/
	public CorpProject findByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProject> orderByComparator)
		throws NoSuchCorpProjectException;

	/**
	* Returns the last corp project in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project, or <code>null</code> if a matching corp project could not be found
	*/
	public CorpProject fetchByUuid_Last(java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProject> orderByComparator);

	/**
	* Returns the corp projects before and after the current corp project in the ordered set where uuid = &#63;.
	*
	* @param corpProjectId the primary key of the current corp project
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next corp project
	* @throws NoSuchCorpProjectException if a corp project with the primary key could not be found
	*/
	public CorpProject[] findByUuid_PrevAndNext(long corpProjectId,
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProject> orderByComparator)
		throws NoSuchCorpProjectException;

	/**
	* Removes all the corp projects where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(java.lang.String uuid);

	/**
	* Returns the number of corp projects where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching corp projects
	*/
	public int countByUuid(java.lang.String uuid);

	/**
	* Returns the corp project where dossieraProjectKey = &#63; or throws a {@link NoSuchCorpProjectException} if it could not be found.
	*
	* @param dossieraProjectKey the dossiera project key
	* @return the matching corp project
	* @throws NoSuchCorpProjectException if a matching corp project could not be found
	*/
	public CorpProject findByDossieraProjectKey(
		java.lang.String dossieraProjectKey) throws NoSuchCorpProjectException;

	/**
	* Returns the corp project where dossieraProjectKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param dossieraProjectKey the dossiera project key
	* @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	*/
	public CorpProject fetchByDossieraProjectKey(
		java.lang.String dossieraProjectKey);

	/**
	* Returns the corp project where dossieraProjectKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param dossieraProjectKey the dossiera project key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	*/
	public CorpProject fetchByDossieraProjectKey(
		java.lang.String dossieraProjectKey, boolean retrieveFromCache);

	/**
	* Removes the corp project where dossieraProjectKey = &#63; from the database.
	*
	* @param dossieraProjectKey the dossiera project key
	* @return the corp project that was removed
	*/
	public CorpProject removeByDossieraProjectKey(
		java.lang.String dossieraProjectKey) throws NoSuchCorpProjectException;

	/**
	* Returns the number of corp projects where dossieraProjectKey = &#63;.
	*
	* @param dossieraProjectKey the dossiera project key
	* @return the number of matching corp projects
	*/
	public int countByDossieraProjectKey(java.lang.String dossieraProjectKey);

	/**
	* Returns all the corp projects where name LIKE &#63;.
	*
	* @param name the name
	* @return the matching corp projects
	*/
	public java.util.List<CorpProject> findByName(java.lang.String name);

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
	public java.util.List<CorpProject> findByName(java.lang.String name,
		int start, int end);

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
	public java.util.List<CorpProject> findByName(java.lang.String name,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProject> orderByComparator);

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
	public java.util.List<CorpProject> findByName(java.lang.String name,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProject> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first corp project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project
	* @throws NoSuchCorpProjectException if a matching corp project could not be found
	*/
	public CorpProject findByName_First(java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProject> orderByComparator)
		throws NoSuchCorpProjectException;

	/**
	* Returns the first corp project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching corp project, or <code>null</code> if a matching corp project could not be found
	*/
	public CorpProject fetchByName_First(java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProject> orderByComparator);

	/**
	* Returns the last corp project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project
	* @throws NoSuchCorpProjectException if a matching corp project could not be found
	*/
	public CorpProject findByName_Last(java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProject> orderByComparator)
		throws NoSuchCorpProjectException;

	/**
	* Returns the last corp project in the ordered set where name LIKE &#63;.
	*
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching corp project, or <code>null</code> if a matching corp project could not be found
	*/
	public CorpProject fetchByName_Last(java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProject> orderByComparator);

	/**
	* Returns the corp projects before and after the current corp project in the ordered set where name LIKE &#63;.
	*
	* @param corpProjectId the primary key of the current corp project
	* @param name the name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next corp project
	* @throws NoSuchCorpProjectException if a corp project with the primary key could not be found
	*/
	public CorpProject[] findByName_PrevAndNext(long corpProjectId,
		java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProject> orderByComparator)
		throws NoSuchCorpProjectException;

	/**
	* Removes all the corp projects where name LIKE &#63; from the database.
	*
	* @param name the name
	*/
	public void removeByName(java.lang.String name);

	/**
	* Returns the number of corp projects where name LIKE &#63;.
	*
	* @param name the name
	* @return the number of matching corp projects
	*/
	public int countByName(java.lang.String name);

	/**
	* Returns the corp project where organizationId = &#63; or throws a {@link NoSuchCorpProjectException} if it could not be found.
	*
	* @param organizationId the organization ID
	* @return the matching corp project
	* @throws NoSuchCorpProjectException if a matching corp project could not be found
	*/
	public CorpProject findByOrganizationId(long organizationId)
		throws NoSuchCorpProjectException;

	/**
	* Returns the corp project where organizationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param organizationId the organization ID
	* @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	*/
	public CorpProject fetchByOrganizationId(long organizationId);

	/**
	* Returns the corp project where organizationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param organizationId the organization ID
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching corp project, or <code>null</code> if a matching corp project could not be found
	*/
	public CorpProject fetchByOrganizationId(long organizationId,
		boolean retrieveFromCache);

	/**
	* Removes the corp project where organizationId = &#63; from the database.
	*
	* @param organizationId the organization ID
	* @return the corp project that was removed
	*/
	public CorpProject removeByOrganizationId(long organizationId)
		throws NoSuchCorpProjectException;

	/**
	* Returns the number of corp projects where organizationId = &#63;.
	*
	* @param organizationId the organization ID
	* @return the number of matching corp projects
	*/
	public int countByOrganizationId(long organizationId);

	/**
	* Caches the corp project in the entity cache if it is enabled.
	*
	* @param corpProject the corp project
	*/
	public void cacheResult(CorpProject corpProject);

	/**
	* Caches the corp projects in the entity cache if it is enabled.
	*
	* @param corpProjects the corp projects
	*/
	public void cacheResult(java.util.List<CorpProject> corpProjects);

	/**
	* Creates a new corp project with the primary key. Does not add the corp project to the database.
	*
	* @param corpProjectId the primary key for the new corp project
	* @return the new corp project
	*/
	public CorpProject create(long corpProjectId);

	/**
	* Removes the corp project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpProjectId the primary key of the corp project
	* @return the corp project that was removed
	* @throws NoSuchCorpProjectException if a corp project with the primary key could not be found
	*/
	public CorpProject remove(long corpProjectId)
		throws NoSuchCorpProjectException;

	public CorpProject updateImpl(CorpProject corpProject);

	/**
	* Returns the corp project with the primary key or throws a {@link NoSuchCorpProjectException} if it could not be found.
	*
	* @param corpProjectId the primary key of the corp project
	* @return the corp project
	* @throws NoSuchCorpProjectException if a corp project with the primary key could not be found
	*/
	public CorpProject findByPrimaryKey(long corpProjectId)
		throws NoSuchCorpProjectException;

	/**
	* Returns the corp project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param corpProjectId the primary key of the corp project
	* @return the corp project, or <code>null</code> if a corp project with the primary key could not be found
	*/
	public CorpProject fetchByPrimaryKey(long corpProjectId);

	@Override
	public java.util.Map<java.io.Serializable, CorpProject> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the corp projects.
	*
	* @return the corp projects
	*/
	public java.util.List<CorpProject> findAll();

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
	public java.util.List<CorpProject> findAll(int start, int end);

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
	public java.util.List<CorpProject> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProject> orderByComparator);

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
	public java.util.List<CorpProject> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CorpProject> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the corp projects from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of corp projects.
	*
	* @return the number of corp projects
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}