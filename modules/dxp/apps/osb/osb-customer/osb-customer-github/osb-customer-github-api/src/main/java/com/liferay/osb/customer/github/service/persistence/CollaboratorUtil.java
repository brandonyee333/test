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

package com.liferay.osb.customer.github.service.persistence;

import com.liferay.osb.customer.github.model.Collaborator;
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
 * The persistence utility for the collaborator service. This utility wraps <code>com.liferay.osb.customer.github.service.persistence.impl.CollaboratorPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CollaboratorPersistence
 * @generated
 */
public class CollaboratorUtil {

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
	public static void clearCache(Collaborator collaborator) {
		getPersistence().clearCache(collaborator);
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
	public static Map<Serializable, Collaborator> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Collaborator> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Collaborator> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Collaborator> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<Collaborator> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static Collaborator update(Collaborator collaborator) {
		return getPersistence().update(collaborator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static Collaborator update(
		Collaborator collaborator, ServiceContext serviceContext) {

		return getPersistence().update(collaborator, serviceContext);
	}

	/**
	 * Returns all the collaborators where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching collaborators
	 */
	public static List<Collaborator> findByAccountEntryId(long accountEntryId) {
		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	 * Returns a range of all the collaborators where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @return the range of matching collaborators
	 */
	public static List<Collaborator> findByAccountEntryId(
		long accountEntryId, int start, int end) {

		return getPersistence().findByAccountEntryId(
			accountEntryId, start, end);
	}

	/**
	 * Returns an ordered range of all the collaborators where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching collaborators
	 */
	public static List<Collaborator> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<Collaborator> orderByComparator) {

		return getPersistence().findByAccountEntryId(
			accountEntryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the collaborators where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching collaborators
	 */
	public static List<Collaborator> findByAccountEntryId(
		long accountEntryId, int start, int end,
		OrderByComparator<Collaborator> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByAccountEntryId(
			accountEntryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first collaborator in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	public static Collaborator findByAccountEntryId_First(
			long accountEntryId,
			OrderByComparator<Collaborator> orderByComparator)
		throws com.liferay.osb.customer.github.exception.
			NoSuchCollaboratorException {

		return getPersistence().findByAccountEntryId_First(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the first collaborator in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	public static Collaborator fetchByAccountEntryId_First(
		long accountEntryId,
		OrderByComparator<Collaborator> orderByComparator) {

		return getPersistence().fetchByAccountEntryId_First(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the last collaborator in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	public static Collaborator findByAccountEntryId_Last(
			long accountEntryId,
			OrderByComparator<Collaborator> orderByComparator)
		throws com.liferay.osb.customer.github.exception.
			NoSuchCollaboratorException {

		return getPersistence().findByAccountEntryId_Last(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the last collaborator in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	public static Collaborator fetchByAccountEntryId_Last(
		long accountEntryId,
		OrderByComparator<Collaborator> orderByComparator) {

		return getPersistence().fetchByAccountEntryId_Last(
			accountEntryId, orderByComparator);
	}

	/**
	 * Returns the collaborators before and after the current collaborator in the ordered set where accountEntryId = &#63;.
	 *
	 * @param collaboratorId the primary key of the current collaborator
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next collaborator
	 * @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	 */
	public static Collaborator[] findByAccountEntryId_PrevAndNext(
			long collaboratorId, long accountEntryId,
			OrderByComparator<Collaborator> orderByComparator)
		throws com.liferay.osb.customer.github.exception.
			NoSuchCollaboratorException {

		return getPersistence().findByAccountEntryId_PrevAndNext(
			collaboratorId, accountEntryId, orderByComparator);
	}

	/**
	 * Removes all the collaborators where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	public static void removeByAccountEntryId(long accountEntryId) {
		getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	 * Returns the number of collaborators where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching collaborators
	 */
	public static int countByAccountEntryId(long accountEntryId) {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	 * Returns all the collaborators where gitHubUserName = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @return the matching collaborators
	 */
	public static List<Collaborator> findByGitHubUserName(
		String gitHubUserName) {

		return getPersistence().findByGitHubUserName(gitHubUserName);
	}

	/**
	 * Returns a range of all the collaborators where gitHubUserName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param gitHubUserName the git hub user name
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @return the range of matching collaborators
	 */
	public static List<Collaborator> findByGitHubUserName(
		String gitHubUserName, int start, int end) {

		return getPersistence().findByGitHubUserName(
			gitHubUserName, start, end);
	}

	/**
	 * Returns an ordered range of all the collaborators where gitHubUserName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param gitHubUserName the git hub user name
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching collaborators
	 */
	public static List<Collaborator> findByGitHubUserName(
		String gitHubUserName, int start, int end,
		OrderByComparator<Collaborator> orderByComparator) {

		return getPersistence().findByGitHubUserName(
			gitHubUserName, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the collaborators where gitHubUserName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param gitHubUserName the git hub user name
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching collaborators
	 */
	public static List<Collaborator> findByGitHubUserName(
		String gitHubUserName, int start, int end,
		OrderByComparator<Collaborator> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByGitHubUserName(
			gitHubUserName, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first collaborator in the ordered set where gitHubUserName = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	public static Collaborator findByGitHubUserName_First(
			String gitHubUserName,
			OrderByComparator<Collaborator> orderByComparator)
		throws com.liferay.osb.customer.github.exception.
			NoSuchCollaboratorException {

		return getPersistence().findByGitHubUserName_First(
			gitHubUserName, orderByComparator);
	}

	/**
	 * Returns the first collaborator in the ordered set where gitHubUserName = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	public static Collaborator fetchByGitHubUserName_First(
		String gitHubUserName,
		OrderByComparator<Collaborator> orderByComparator) {

		return getPersistence().fetchByGitHubUserName_First(
			gitHubUserName, orderByComparator);
	}

	/**
	 * Returns the last collaborator in the ordered set where gitHubUserName = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	public static Collaborator findByGitHubUserName_Last(
			String gitHubUserName,
			OrderByComparator<Collaborator> orderByComparator)
		throws com.liferay.osb.customer.github.exception.
			NoSuchCollaboratorException {

		return getPersistence().findByGitHubUserName_Last(
			gitHubUserName, orderByComparator);
	}

	/**
	 * Returns the last collaborator in the ordered set where gitHubUserName = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	public static Collaborator fetchByGitHubUserName_Last(
		String gitHubUserName,
		OrderByComparator<Collaborator> orderByComparator) {

		return getPersistence().fetchByGitHubUserName_Last(
			gitHubUserName, orderByComparator);
	}

	/**
	 * Returns the collaborators before and after the current collaborator in the ordered set where gitHubUserName = &#63;.
	 *
	 * @param collaboratorId the primary key of the current collaborator
	 * @param gitHubUserName the git hub user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next collaborator
	 * @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	 */
	public static Collaborator[] findByGitHubUserName_PrevAndNext(
			long collaboratorId, String gitHubUserName,
			OrderByComparator<Collaborator> orderByComparator)
		throws com.liferay.osb.customer.github.exception.
			NoSuchCollaboratorException {

		return getPersistence().findByGitHubUserName_PrevAndNext(
			collaboratorId, gitHubUserName, orderByComparator);
	}

	/**
	 * Removes all the collaborators where gitHubUserName = &#63; from the database.
	 *
	 * @param gitHubUserName the git hub user name
	 */
	public static void removeByGitHubUserName(String gitHubUserName) {
		getPersistence().removeByGitHubUserName(gitHubUserName);
	}

	/**
	 * Returns the number of collaborators where gitHubUserName = &#63;.
	 *
	 * @param gitHubUserName the git hub user name
	 * @return the number of matching collaborators
	 */
	public static int countByGitHubUserName(String gitHubUserName) {
		return getPersistence().countByGitHubUserName(gitHubUserName);
	}

	/**
	 * Returns all the collaborators where status = &#63;.
	 *
	 * @param status the status
	 * @return the matching collaborators
	 */
	public static List<Collaborator> findByStatus(int status) {
		return getPersistence().findByStatus(status);
	}

	/**
	 * Returns a range of all the collaborators where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @return the range of matching collaborators
	 */
	public static List<Collaborator> findByStatus(
		int status, int start, int end) {

		return getPersistence().findByStatus(status, start, end);
	}

	/**
	 * Returns an ordered range of all the collaborators where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching collaborators
	 */
	public static List<Collaborator> findByStatus(
		int status, int start, int end,
		OrderByComparator<Collaborator> orderByComparator) {

		return getPersistence().findByStatus(
			status, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the collaborators where status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param status the status
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching collaborators
	 */
	public static List<Collaborator> findByStatus(
		int status, int start, int end,
		OrderByComparator<Collaborator> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByStatus(
			status, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first collaborator in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	public static Collaborator findByStatus_First(
			int status, OrderByComparator<Collaborator> orderByComparator)
		throws com.liferay.osb.customer.github.exception.
			NoSuchCollaboratorException {

		return getPersistence().findByStatus_First(status, orderByComparator);
	}

	/**
	 * Returns the first collaborator in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	public static Collaborator fetchByStatus_First(
		int status, OrderByComparator<Collaborator> orderByComparator) {

		return getPersistence().fetchByStatus_First(status, orderByComparator);
	}

	/**
	 * Returns the last collaborator in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	public static Collaborator findByStatus_Last(
			int status, OrderByComparator<Collaborator> orderByComparator)
		throws com.liferay.osb.customer.github.exception.
			NoSuchCollaboratorException {

		return getPersistence().findByStatus_Last(status, orderByComparator);
	}

	/**
	 * Returns the last collaborator in the ordered set where status = &#63;.
	 *
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	public static Collaborator fetchByStatus_Last(
		int status, OrderByComparator<Collaborator> orderByComparator) {

		return getPersistence().fetchByStatus_Last(status, orderByComparator);
	}

	/**
	 * Returns the collaborators before and after the current collaborator in the ordered set where status = &#63;.
	 *
	 * @param collaboratorId the primary key of the current collaborator
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next collaborator
	 * @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	 */
	public static Collaborator[] findByStatus_PrevAndNext(
			long collaboratorId, int status,
			OrderByComparator<Collaborator> orderByComparator)
		throws com.liferay.osb.customer.github.exception.
			NoSuchCollaboratorException {

		return getPersistence().findByStatus_PrevAndNext(
			collaboratorId, status, orderByComparator);
	}

	/**
	 * Removes all the collaborators where status = &#63; from the database.
	 *
	 * @param status the status
	 */
	public static void removeByStatus(int status) {
		getPersistence().removeByStatus(status);
	}

	/**
	 * Returns the number of collaborators where status = &#63;.
	 *
	 * @param status the status
	 * @return the number of matching collaborators
	 */
	public static int countByStatus(int status) {
		return getPersistence().countByStatus(status);
	}

	/**
	 * Returns the collaborator where accountEntryId = &#63; and gitHubUserName = &#63; or throws a <code>NoSuchCollaboratorException</code> if it could not be found.
	 *
	 * @param accountEntryId the account entry ID
	 * @param gitHubUserName the git hub user name
	 * @return the matching collaborator
	 * @throws NoSuchCollaboratorException if a matching collaborator could not be found
	 */
	public static Collaborator findByAEI_GHUN(
			long accountEntryId, String gitHubUserName)
		throws com.liferay.osb.customer.github.exception.
			NoSuchCollaboratorException {

		return getPersistence().findByAEI_GHUN(accountEntryId, gitHubUserName);
	}

	/**
	 * Returns the collaborator where accountEntryId = &#63; and gitHubUserName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param gitHubUserName the git hub user name
	 * @return the matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	public static Collaborator fetchByAEI_GHUN(
		long accountEntryId, String gitHubUserName) {

		return getPersistence().fetchByAEI_GHUN(accountEntryId, gitHubUserName);
	}

	/**
	 * Returns the collaborator where accountEntryId = &#63; and gitHubUserName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param gitHubUserName the git hub user name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching collaborator, or <code>null</code> if a matching collaborator could not be found
	 */
	public static Collaborator fetchByAEI_GHUN(
		long accountEntryId, String gitHubUserName, boolean useFinderCache) {

		return getPersistence().fetchByAEI_GHUN(
			accountEntryId, gitHubUserName, useFinderCache);
	}

	/**
	 * Removes the collaborator where accountEntryId = &#63; and gitHubUserName = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param gitHubUserName the git hub user name
	 * @return the collaborator that was removed
	 */
	public static Collaborator removeByAEI_GHUN(
			long accountEntryId, String gitHubUserName)
		throws com.liferay.osb.customer.github.exception.
			NoSuchCollaboratorException {

		return getPersistence().removeByAEI_GHUN(
			accountEntryId, gitHubUserName);
	}

	/**
	 * Returns the number of collaborators where accountEntryId = &#63; and gitHubUserName = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param gitHubUserName the git hub user name
	 * @return the number of matching collaborators
	 */
	public static int countByAEI_GHUN(
		long accountEntryId, String gitHubUserName) {

		return getPersistence().countByAEI_GHUN(accountEntryId, gitHubUserName);
	}

	/**
	 * Caches the collaborator in the entity cache if it is enabled.
	 *
	 * @param collaborator the collaborator
	 */
	public static void cacheResult(Collaborator collaborator) {
		getPersistence().cacheResult(collaborator);
	}

	/**
	 * Caches the collaborators in the entity cache if it is enabled.
	 *
	 * @param collaborators the collaborators
	 */
	public static void cacheResult(List<Collaborator> collaborators) {
		getPersistence().cacheResult(collaborators);
	}

	/**
	 * Creates a new collaborator with the primary key. Does not add the collaborator to the database.
	 *
	 * @param collaboratorId the primary key for the new collaborator
	 * @return the new collaborator
	 */
	public static Collaborator create(long collaboratorId) {
		return getPersistence().create(collaboratorId);
	}

	/**
	 * Removes the collaborator with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param collaboratorId the primary key of the collaborator
	 * @return the collaborator that was removed
	 * @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	 */
	public static Collaborator remove(long collaboratorId)
		throws com.liferay.osb.customer.github.exception.
			NoSuchCollaboratorException {

		return getPersistence().remove(collaboratorId);
	}

	public static Collaborator updateImpl(Collaborator collaborator) {
		return getPersistence().updateImpl(collaborator);
	}

	/**
	 * Returns the collaborator with the primary key or throws a <code>NoSuchCollaboratorException</code> if it could not be found.
	 *
	 * @param collaboratorId the primary key of the collaborator
	 * @return the collaborator
	 * @throws NoSuchCollaboratorException if a collaborator with the primary key could not be found
	 */
	public static Collaborator findByPrimaryKey(long collaboratorId)
		throws com.liferay.osb.customer.github.exception.
			NoSuchCollaboratorException {

		return getPersistence().findByPrimaryKey(collaboratorId);
	}

	/**
	 * Returns the collaborator with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param collaboratorId the primary key of the collaborator
	 * @return the collaborator, or <code>null</code> if a collaborator with the primary key could not be found
	 */
	public static Collaborator fetchByPrimaryKey(long collaboratorId) {
		return getPersistence().fetchByPrimaryKey(collaboratorId);
	}

	/**
	 * Returns all the collaborators.
	 *
	 * @return the collaborators
	 */
	public static List<Collaborator> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the collaborators.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @return the range of collaborators
	 */
	public static List<Collaborator> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the collaborators.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of collaborators
	 */
	public static List<Collaborator> findAll(
		int start, int end, OrderByComparator<Collaborator> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the collaborators.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CollaboratorModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of collaborators
	 * @param end the upper bound of the range of collaborators (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of collaborators
	 */
	public static List<Collaborator> findAll(
		int start, int end, OrderByComparator<Collaborator> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the collaborators from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of collaborators.
	 *
	 * @return the number of collaborators
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CollaboratorPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CollaboratorPersistence, CollaboratorPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CollaboratorPersistence.class);

		ServiceTracker<CollaboratorPersistence, CollaboratorPersistence>
			serviceTracker =
				new ServiceTracker
					<CollaboratorPersistence, CollaboratorPersistence>(
						bundle.getBundleContext(),
						CollaboratorPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}