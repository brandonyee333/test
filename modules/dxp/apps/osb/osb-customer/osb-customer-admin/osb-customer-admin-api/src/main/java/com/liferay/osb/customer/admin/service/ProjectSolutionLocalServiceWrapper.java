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

package com.liferay.osb.customer.admin.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProjectSolutionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProjectSolutionLocalService
 * @generated
 */
public class ProjectSolutionLocalServiceWrapper
	implements ProjectSolutionLocalService,
			   ServiceWrapper<ProjectSolutionLocalService> {

	public ProjectSolutionLocalServiceWrapper(
		ProjectSolutionLocalService projectSolutionLocalService) {

		_projectSolutionLocalService = projectSolutionLocalService;
	}

	/**
	 * Adds the project solution to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProjectSolutionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param projectSolution the project solution
	 * @return the project solution that was added
	 */
	@Override
	public com.liferay.osb.customer.admin.model.ProjectSolution
		addProjectSolution(
			com.liferay.osb.customer.admin.model.ProjectSolution
				projectSolution) {

		return _projectSolutionLocalService.addProjectSolution(projectSolution);
	}

	@Override
	public com.liferay.osb.customer.admin.model.ProjectSolution
		addProjectSolution(String salesforceProjectKey, String value) {

		return _projectSolutionLocalService.addProjectSolution(
			salesforceProjectKey, value);
	}

	/**
	 * Creates a new project solution with the primary key. Does not add the project solution to the database.
	 *
	 * @param salesforceProjectKey the primary key for the new project solution
	 * @return the new project solution
	 */
	@Override
	public com.liferay.osb.customer.admin.model.ProjectSolution
		createProjectSolution(String salesforceProjectKey) {

		return _projectSolutionLocalService.createProjectSolution(
			salesforceProjectKey);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _projectSolutionLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the project solution from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProjectSolutionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param projectSolution the project solution
	 * @return the project solution that was removed
	 */
	@Override
	public com.liferay.osb.customer.admin.model.ProjectSolution
		deleteProjectSolution(
			com.liferay.osb.customer.admin.model.ProjectSolution
				projectSolution) {

		return _projectSolutionLocalService.deleteProjectSolution(
			projectSolution);
	}

	/**
	 * Deletes the project solution with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProjectSolutionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param salesforceProjectKey the primary key of the project solution
	 * @return the project solution that was removed
	 * @throws PortalException if a project solution with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.admin.model.ProjectSolution
			deleteProjectSolution(String salesforceProjectKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _projectSolutionLocalService.deleteProjectSolution(
			salesforceProjectKey);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _projectSolutionLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _projectSolutionLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.ProjectSolutionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _projectSolutionLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.ProjectSolutionModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _projectSolutionLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _projectSolutionLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _projectSolutionLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.customer.admin.model.ProjectSolution
		fetchProjectSolution(String salesforceProjectKey) {

		return _projectSolutionLocalService.fetchProjectSolution(
			salesforceProjectKey);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _projectSolutionLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _projectSolutionLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the project solution with the primary key.
	 *
	 * @param salesforceProjectKey the primary key of the project solution
	 * @return the project solution
	 * @throws PortalException if a project solution with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.customer.admin.model.ProjectSolution
			getProjectSolution(String salesforceProjectKey)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _projectSolutionLocalService.getProjectSolution(
			salesforceProjectKey);
	}

	/**
	 * Returns a range of all the project solutions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.customer.admin.model.impl.ProjectSolutionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of project solutions
	 * @param end the upper bound of the range of project solutions (not inclusive)
	 * @return the range of project solutions
	 */
	@Override
	public java.util.List<com.liferay.osb.customer.admin.model.ProjectSolution>
		getProjectSolutions(int start, int end) {

		return _projectSolutionLocalService.getProjectSolutions(start, end);
	}

	/**
	 * Returns the number of project solutions.
	 *
	 * @return the number of project solutions
	 */
	@Override
	public int getProjectSolutionsCount() {
		return _projectSolutionLocalService.getProjectSolutionsCount();
	}

	/**
	 * Updates the project solution in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect ProjectSolutionLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param projectSolution the project solution
	 * @return the project solution that was updated
	 */
	@Override
	public com.liferay.osb.customer.admin.model.ProjectSolution
		updateProjectSolution(
			com.liferay.osb.customer.admin.model.ProjectSolution
				projectSolution) {

		return _projectSolutionLocalService.updateProjectSolution(
			projectSolution);
	}

	@Override
	public com.liferay.osb.customer.admin.model.ProjectSolution
			updateProjectSolution(String salesforceProjectKey, String value)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _projectSolutionLocalService.updateProjectSolution(
			salesforceProjectKey, value);
	}

	@Override
	public ProjectSolutionLocalService getWrappedService() {
		return _projectSolutionLocalService;
	}

	@Override
	public void setWrappedService(
		ProjectSolutionLocalService projectSolutionLocalService) {

		_projectSolutionLocalService = projectSolutionLocalService;
	}

	private ProjectSolutionLocalService _projectSolutionLocalService;

}