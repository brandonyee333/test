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

package com.liferay.osb.testray.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TestrayRunLocalService}.
 *
 * @author Ethan Bustad
 * @see TestrayRunLocalService
 * @generated
 */
@ProviderType
public class TestrayRunLocalServiceWrapper
	implements TestrayRunLocalService, ServiceWrapper<TestrayRunLocalService> {

	public TestrayRunLocalServiceWrapper(
		TestrayRunLocalService testrayRunLocalService) {

		_testrayRunLocalService = testrayRunLocalService;
	}

	/**
	 * Adds the testray run to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRun the testray run
	 * @return the testray run that was added
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayRun addTestrayRun(
		com.liferay.osb.testray.model.TestrayRun testrayRun) {

		return _testrayRunLocalService.addTestrayRun(testrayRun);
	}

	/**
	 * Creates a new testray run with the primary key. Does not add the testray run to the database.
	 *
	 * @param testrayRunId the primary key for the new testray run
	 * @return the new testray run
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayRun createTestrayRun(
		long testrayRunId) {

		return _testrayRunLocalService.createTestrayRun(testrayRunId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayRunLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the testray run with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRunId the primary key of the testray run
	 * @return the testray run that was removed
	 * @throws PortalException if a testray run with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayRun deleteTestrayRun(
			long testrayRunId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayRunLocalService.deleteTestrayRun(testrayRunId);
	}

	/**
	 * Deletes the testray run from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRun the testray run
	 * @return the testray run that was removed
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayRun deleteTestrayRun(
		com.liferay.osb.testray.model.TestrayRun testrayRun) {

		return _testrayRunLocalService.deleteTestrayRun(testrayRun);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testrayRunLocalService.dynamicQuery();
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

		return _testrayRunLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayRunModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _testrayRunLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayRunModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _testrayRunLocalService.dynamicQuery(
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

		return _testrayRunLocalService.dynamicQueryCount(dynamicQuery);
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

		return _testrayRunLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.testray.model.TestrayRun fetchTestrayRun(
		long testrayRunId) {

		return _testrayRunLocalService.fetchTestrayRun(testrayRunId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _testrayRunLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _testrayRunLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _testrayRunLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayRunLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the testray run with the primary key.
	 *
	 * @param testrayRunId the primary key of the testray run
	 * @return the testray run
	 * @throws PortalException if a testray run with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayRun getTestrayRun(
			long testrayRunId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayRunLocalService.getTestrayRun(testrayRunId);
	}

	/**
	 * Returns a range of all the testray runs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayRunModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray runs
	 * @param end the upper bound of the range of testray runs (not inclusive)
	 * @return the range of testray runs
	 */
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayRun>
		getTestrayRuns(int start, int end) {

		return _testrayRunLocalService.getTestrayRuns(start, end);
	}

	/**
	 * Returns the number of testray runs.
	 *
	 * @return the number of testray runs
	 */
	@Override
	public int getTestrayRunsCount() {
		return _testrayRunLocalService.getTestrayRunsCount();
	}

	/**
	 * Updates the testray run in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRun the testray run
	 * @return the testray run that was updated
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayRun updateTestrayRun(
		com.liferay.osb.testray.model.TestrayRun testrayRun) {

		return _testrayRunLocalService.updateTestrayRun(testrayRun);
	}

	@Override
	public TestrayRunLocalService getWrappedService() {
		return _testrayRunLocalService;
	}

	@Override
	public void setWrappedService(
		TestrayRunLocalService testrayRunLocalService) {

		_testrayRunLocalService = testrayRunLocalService;
	}

	private TestrayRunLocalService _testrayRunLocalService;

}