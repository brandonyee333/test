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

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.admin.exception.NoSuchProjectSolutionException;
import com.liferay.osb.customer.admin.model.ProjectSolution;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the project solution service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProjectSolutionUtil
 * @generated
 */
@ProviderType
public interface ProjectSolutionPersistence
	extends BasePersistence<ProjectSolution> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProjectSolutionUtil} to access the project solution persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, ProjectSolution> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Caches the project solution in the entity cache if it is enabled.
	 *
	 * @param projectSolution the project solution
	 */
	public void cacheResult(ProjectSolution projectSolution);

	/**
	 * Caches the project solutions in the entity cache if it is enabled.
	 *
	 * @param projectSolutions the project solutions
	 */
	public void cacheResult(java.util.List<ProjectSolution> projectSolutions);

	/**
	 * Creates a new project solution with the primary key. Does not add the project solution to the database.
	 *
	 * @param salesforceProjectKey the primary key for the new project solution
	 * @return the new project solution
	 */
	public ProjectSolution create(String salesforceProjectKey);

	/**
	 * Removes the project solution with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param salesforceProjectKey the primary key of the project solution
	 * @return the project solution that was removed
	 * @throws NoSuchProjectSolutionException if a project solution with the primary key could not be found
	 */
	public ProjectSolution remove(String salesforceProjectKey)
		throws NoSuchProjectSolutionException;

	public ProjectSolution updateImpl(ProjectSolution projectSolution);

	/**
	 * Returns the project solution with the primary key or throws a <code>NoSuchProjectSolutionException</code> if it could not be found.
	 *
	 * @param salesforceProjectKey the primary key of the project solution
	 * @return the project solution
	 * @throws NoSuchProjectSolutionException if a project solution with the primary key could not be found
	 */
	public ProjectSolution findByPrimaryKey(String salesforceProjectKey)
		throws NoSuchProjectSolutionException;

	/**
	 * Returns the project solution with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param salesforceProjectKey the primary key of the project solution
	 * @return the project solution, or <code>null</code> if a project solution with the primary key could not be found
	 */
	public ProjectSolution fetchByPrimaryKey(String salesforceProjectKey);

	/**
	 * Returns all the project solutions.
	 *
	 * @return the project solutions
	 */
	public java.util.List<ProjectSolution> findAll();

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
	public java.util.List<ProjectSolution> findAll(int start, int end);

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
	public java.util.List<ProjectSolution> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectSolution>
			orderByComparator);

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
	public java.util.List<ProjectSolution> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProjectSolution>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the project solutions from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of project solutions.
	 *
	 * @return the number of project solutions
	 */
	public int countAll();

}