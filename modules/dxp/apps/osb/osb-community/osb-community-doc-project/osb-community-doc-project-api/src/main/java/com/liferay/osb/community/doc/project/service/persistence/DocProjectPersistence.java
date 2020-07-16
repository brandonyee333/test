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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.community.doc.project.exception.NoSuchDocProjectException;
import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the doc project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @see DocProjectUtil
 * @generated
 */
@ProviderType
public interface DocProjectPersistence extends BasePersistence<DocProject> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DocProjectUtil} to access the doc project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, DocProject> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the doc projects where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching doc projects
	 */
	public java.util.List<DocProject> findByUuid(String uuid);

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
	public java.util.List<DocProject> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<DocProject> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocProject>
			orderByComparator);

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
	public java.util.List<DocProject> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocProject>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first doc project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	public DocProject findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<DocProject>
				orderByComparator)
		throws NoSuchDocProjectException;

	/**
	 * Returns the first doc project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public DocProject fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DocProject>
			orderByComparator);

	/**
	 * Returns the last doc project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	public DocProject findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<DocProject>
				orderByComparator)
		throws NoSuchDocProjectException;

	/**
	 * Returns the last doc project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public DocProject fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<DocProject>
			orderByComparator);

	/**
	 * Returns the doc projects before and after the current doc project in the ordered set where uuid = &#63;.
	 *
	 * @param docProjectId the primary key of the current doc project
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next doc project
	 * @throws NoSuchDocProjectException if a doc project with the primary key could not be found
	 */
	public DocProject[] findByUuid_PrevAndNext(
			long docProjectId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator<DocProject>
				orderByComparator)
		throws NoSuchDocProjectException;

	/**
	 * Removes all the doc projects where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of doc projects where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching doc projects
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns the doc project where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchDocProjectException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	public DocProject findByUUID_G(String uuid, long groupId)
		throws NoSuchDocProjectException;

	/**
	 * Returns the doc project where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public DocProject fetchByUUID_G(String uuid, long groupId);

	/**
	 * Returns the doc project where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public DocProject fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache);

	/**
	 * Removes the doc project where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the doc project that was removed
	 */
	public DocProject removeByUUID_G(String uuid, long groupId)
		throws NoSuchDocProjectException;

	/**
	 * Returns the number of doc projects where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching doc projects
	 */
	public int countByUUID_G(String uuid, long groupId);

	/**
	 * Returns all the doc projects where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching doc projects
	 */
	public java.util.List<DocProject> findByUuid_C(String uuid, long companyId);

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
	public java.util.List<DocProject> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<DocProject> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocProject>
			orderByComparator);

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
	public java.util.List<DocProject> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocProject>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first doc project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	public DocProject findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<DocProject>
				orderByComparator)
		throws NoSuchDocProjectException;

	/**
	 * Returns the first doc project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public DocProject fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DocProject>
			orderByComparator);

	/**
	 * Returns the last doc project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	public DocProject findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<DocProject>
				orderByComparator)
		throws NoSuchDocProjectException;

	/**
	 * Returns the last doc project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public DocProject fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator<DocProject>
			orderByComparator);

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
	public DocProject[] findByUuid_C_PrevAndNext(
			long docProjectId, String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator<DocProject>
				orderByComparator)
		throws NoSuchDocProjectException;

	/**
	 * Removes all the doc projects where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of doc projects where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching doc projects
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns the doc project where groupId = &#63; or throws a <code>NoSuchDocProjectException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @return the matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	public DocProject findByGroupId(long groupId)
		throws NoSuchDocProjectException;

	/**
	 * Returns the doc project where groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public DocProject fetchByGroupId(long groupId);

	/**
	 * Returns the doc project where groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public DocProject fetchByGroupId(long groupId, boolean useFinderCache);

	/**
	 * Removes the doc project where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @return the doc project that was removed
	 */
	public DocProject removeByGroupId(long groupId)
		throws NoSuchDocProjectException;

	/**
	 * Returns the number of doc projects where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching doc projects
	 */
	public int countByGroupId(long groupId);

	/**
	 * Returns the doc project where name = &#63; or throws a <code>NoSuchDocProjectException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	public DocProject findByName(String name) throws NoSuchDocProjectException;

	/**
	 * Returns the doc project where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public DocProject fetchByName(String name);

	/**
	 * Returns the doc project where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public DocProject fetchByName(String name, boolean useFinderCache);

	/**
	 * Removes the doc project where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the doc project that was removed
	 */
	public DocProject removeByName(String name)
		throws NoSuchDocProjectException;

	/**
	 * Returns the number of doc projects where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching doc projects
	 */
	public int countByName(String name);

	/**
	 * Returns all the doc projects where unlisted = &#63; and status = &#63;.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @return the matching doc projects
	 */
	public java.util.List<DocProject> findByU_S(boolean unlisted, int status);

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
	public java.util.List<DocProject> findByU_S(
		boolean unlisted, int status, int start, int end);

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
	public java.util.List<DocProject> findByU_S(
		boolean unlisted, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocProject>
			orderByComparator);

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
	public java.util.List<DocProject> findByU_S(
		boolean unlisted, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocProject>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first doc project in the ordered set where unlisted = &#63; and status = &#63;.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	public DocProject findByU_S_First(
			boolean unlisted, int status,
			com.liferay.portal.kernel.util.OrderByComparator<DocProject>
				orderByComparator)
		throws NoSuchDocProjectException;

	/**
	 * Returns the first doc project in the ordered set where unlisted = &#63; and status = &#63;.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public DocProject fetchByU_S_First(
		boolean unlisted, int status,
		com.liferay.portal.kernel.util.OrderByComparator<DocProject>
			orderByComparator);

	/**
	 * Returns the last doc project in the ordered set where unlisted = &#63; and status = &#63;.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	public DocProject findByU_S_Last(
			boolean unlisted, int status,
			com.liferay.portal.kernel.util.OrderByComparator<DocProject>
				orderByComparator)
		throws NoSuchDocProjectException;

	/**
	 * Returns the last doc project in the ordered set where unlisted = &#63; and status = &#63;.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	public DocProject fetchByU_S_Last(
		boolean unlisted, int status,
		com.liferay.portal.kernel.util.OrderByComparator<DocProject>
			orderByComparator);

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
	public DocProject[] findByU_S_PrevAndNext(
			long docProjectId, boolean unlisted, int status,
			com.liferay.portal.kernel.util.OrderByComparator<DocProject>
				orderByComparator)
		throws NoSuchDocProjectException;

	/**
	 * Removes all the doc projects where unlisted = &#63; and status = &#63; from the database.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 */
	public void removeByU_S(boolean unlisted, int status);

	/**
	 * Returns the number of doc projects where unlisted = &#63; and status = &#63;.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @return the number of matching doc projects
	 */
	public int countByU_S(boolean unlisted, int status);

	/**
	 * Caches the doc project in the entity cache if it is enabled.
	 *
	 * @param docProject the doc project
	 */
	public void cacheResult(DocProject docProject);

	/**
	 * Caches the doc projects in the entity cache if it is enabled.
	 *
	 * @param docProjects the doc projects
	 */
	public void cacheResult(java.util.List<DocProject> docProjects);

	/**
	 * Creates a new doc project with the primary key. Does not add the doc project to the database.
	 *
	 * @param docProjectId the primary key for the new doc project
	 * @return the new doc project
	 */
	public DocProject create(long docProjectId);

	/**
	 * Removes the doc project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param docProjectId the primary key of the doc project
	 * @return the doc project that was removed
	 * @throws NoSuchDocProjectException if a doc project with the primary key could not be found
	 */
	public DocProject remove(long docProjectId)
		throws NoSuchDocProjectException;

	public DocProject updateImpl(DocProject docProject);

	/**
	 * Returns the doc project with the primary key or throws a <code>NoSuchDocProjectException</code> if it could not be found.
	 *
	 * @param docProjectId the primary key of the doc project
	 * @return the doc project
	 * @throws NoSuchDocProjectException if a doc project with the primary key could not be found
	 */
	public DocProject findByPrimaryKey(long docProjectId)
		throws NoSuchDocProjectException;

	/**
	 * Returns the doc project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param docProjectId the primary key of the doc project
	 * @return the doc project, or <code>null</code> if a doc project with the primary key could not be found
	 */
	public DocProject fetchByPrimaryKey(long docProjectId);

	/**
	 * Returns all the doc projects.
	 *
	 * @return the doc projects
	 */
	public java.util.List<DocProject> findAll();

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
	public java.util.List<DocProject> findAll(int start, int end);

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
	public java.util.List<DocProject> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocProject>
			orderByComparator);

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
	public java.util.List<DocProject> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<DocProject>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the doc projects from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of doc projects.
	 *
	 * @return the number of doc projects
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}