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

package com.liferay.osb.customer.admin.service.persistence;

import com.liferay.osb.customer.admin.model.ProjectSolution;
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
 * The persistence utility for the project solution service. This utility wraps <code>com.liferay.osb.customer.admin.service.persistence.impl.ProjectSolutionPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProjectSolutionPersistence
 * @generated
 */
public class ProjectSolutionUtil {

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
	public static void clearCache(ProjectSolution projectSolution) {
		getPersistence().clearCache(projectSolution);
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
	public static Map<Serializable, ProjectSolution> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ProjectSolution> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProjectSolution> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProjectSolution> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProjectSolution> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ProjectSolution update(ProjectSolution projectSolution) {
		return getPersistence().update(projectSolution);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ProjectSolution update(
		ProjectSolution projectSolution, ServiceContext serviceContext) {

		return getPersistence().update(projectSolution, serviceContext);
	}

	/**
	 * Caches the project solution in the entity cache if it is enabled.
	 *
	 * @param projectSolution the project solution
	 */
	public static void cacheResult(ProjectSolution projectSolution) {
		getPersistence().cacheResult(projectSolution);
	}

	/**
	 * Caches the project solutions in the entity cache if it is enabled.
	 *
	 * @param projectSolutions the project solutions
	 */
	public static void cacheResult(List<ProjectSolution> projectSolutions) {
		getPersistence().cacheResult(projectSolutions);
	}

	/**
	 * Creates a new project solution with the primary key. Does not add the project solution to the database.
	 *
	 * @param salesforceProjectKey the primary key for the new project solution
	 * @return the new project solution
	 */
	public static ProjectSolution create(String salesforceProjectKey) {
		return getPersistence().create(salesforceProjectKey);
	}

	/**
	 * Removes the project solution with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param salesforceProjectKey the primary key of the project solution
	 * @return the project solution that was removed
	 * @throws NoSuchProjectSolutionException if a project solution with the primary key could not be found
	 */
	public static ProjectSolution remove(String salesforceProjectKey)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchProjectSolutionException {

		return getPersistence().remove(salesforceProjectKey);
	}

	public static ProjectSolution updateImpl(ProjectSolution projectSolution) {
		return getPersistence().updateImpl(projectSolution);
	}

	/**
	 * Returns the project solution with the primary key or throws a <code>NoSuchProjectSolutionException</code> if it could not be found.
	 *
	 * @param salesforceProjectKey the primary key of the project solution
	 * @return the project solution
	 * @throws NoSuchProjectSolutionException if a project solution with the primary key could not be found
	 */
	public static ProjectSolution findByPrimaryKey(String salesforceProjectKey)
		throws com.liferay.osb.customer.admin.exception.
			NoSuchProjectSolutionException {

		return getPersistence().findByPrimaryKey(salesforceProjectKey);
	}

	/**
	 * Returns the project solution with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param salesforceProjectKey the primary key of the project solution
	 * @return the project solution, or <code>null</code> if a project solution with the primary key could not be found
	 */
	public static ProjectSolution fetchByPrimaryKey(
		String salesforceProjectKey) {

		return getPersistence().fetchByPrimaryKey(salesforceProjectKey);
	}

	/**
	 * Returns all the project solutions.
	 *
	 * @return the project solutions
	 */
	public static List<ProjectSolution> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the project solutions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectSolutionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of project solutions
	 * @param end the upper bound of the range of project solutions (not inclusive)
	 * @return the range of project solutions
	 */
	public static List<ProjectSolution> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the project solutions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectSolutionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of project solutions
	 * @param end the upper bound of the range of project solutions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of project solutions
	 */
	public static List<ProjectSolution> findAll(
		int start, int end,
		OrderByComparator<ProjectSolution> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the project solutions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProjectSolutionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of project solutions
	 * @param end the upper bound of the range of project solutions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of project solutions
	 */
	public static List<ProjectSolution> findAll(
		int start, int end,
		OrderByComparator<ProjectSolution> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the project solutions from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of project solutions.
	 *
	 * @return the number of project solutions
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ProjectSolutionPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ProjectSolutionPersistence, ProjectSolutionPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ProjectSolutionPersistence.class);

		ServiceTracker<ProjectSolutionPersistence, ProjectSolutionPersistence>
			serviceTracker =
				new ServiceTracker
					<ProjectSolutionPersistence, ProjectSolutionPersistence>(
						bundle.getBundleContext(),
						ProjectSolutionPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}