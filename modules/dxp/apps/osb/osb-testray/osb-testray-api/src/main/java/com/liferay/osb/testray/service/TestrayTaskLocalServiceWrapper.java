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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TestrayTaskLocalService}.
 *
 * @author Ethan Bustad
 * @see TestrayTaskLocalService
 * @generated
 */
public class TestrayTaskLocalServiceWrapper
	implements ServiceWrapper<TestrayTaskLocalService>,
			   TestrayTaskLocalService {

	public TestrayTaskLocalServiceWrapper(
		TestrayTaskLocalService testrayTaskLocalService) {

		_testrayTaskLocalService = testrayTaskLocalService;
	}

	@Override
	public void addTestrayCaseTypeTestrayTask(
		long testrayCaseTypeId, long testrayTaskId) {

		_testrayTaskLocalService.addTestrayCaseTypeTestrayTask(
			testrayCaseTypeId, testrayTaskId);
	}

	@Override
	public void addTestrayCaseTypeTestrayTask(
		long testrayCaseTypeId,
		com.liferay.osb.testray.model.TestrayTask testrayTask) {

		_testrayTaskLocalService.addTestrayCaseTypeTestrayTask(
			testrayCaseTypeId, testrayTask);
	}

	@Override
	public void addTestrayCaseTypeTestrayTasks(
		long testrayCaseTypeId,
		java.util.List<com.liferay.osb.testray.model.TestrayTask>
			testrayTasks) {

		_testrayTaskLocalService.addTestrayCaseTypeTestrayTasks(
			testrayCaseTypeId, testrayTasks);
	}

	@Override
	public void addTestrayCaseTypeTestrayTasks(
		long testrayCaseTypeId, long[] testrayTaskIds) {

		_testrayTaskLocalService.addTestrayCaseTypeTestrayTasks(
			testrayCaseTypeId, testrayTaskIds);
	}

	/**
	 * Adds the testray task to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayTask the testray task
	 * @return the testray task that was added
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayTask addTestrayTask(
		com.liferay.osb.testray.model.TestrayTask testrayTask) {

		return _testrayTaskLocalService.addTestrayTask(testrayTask);
	}

	@Override
	public void clearTestrayCaseTypeTestrayTasks(long testrayCaseTypeId) {
		_testrayTaskLocalService.clearTestrayCaseTypeTestrayTasks(
			testrayCaseTypeId);
	}

	/**
	 * Creates a new testray task with the primary key. Does not add the testray task to the database.
	 *
	 * @param testrayTaskId the primary key for the new testray task
	 * @return the new testray task
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayTask createTestrayTask(
		long testrayTaskId) {

		return _testrayTaskLocalService.createTestrayTask(testrayTaskId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayTaskLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public void deleteTestrayCaseTypeTestrayTask(
		long testrayCaseTypeId, long testrayTaskId) {

		_testrayTaskLocalService.deleteTestrayCaseTypeTestrayTask(
			testrayCaseTypeId, testrayTaskId);
	}

	@Override
	public void deleteTestrayCaseTypeTestrayTask(
		long testrayCaseTypeId,
		com.liferay.osb.testray.model.TestrayTask testrayTask) {

		_testrayTaskLocalService.deleteTestrayCaseTypeTestrayTask(
			testrayCaseTypeId, testrayTask);
	}

	@Override
	public void deleteTestrayCaseTypeTestrayTasks(
		long testrayCaseTypeId,
		java.util.List<com.liferay.osb.testray.model.TestrayTask>
			testrayTasks) {

		_testrayTaskLocalService.deleteTestrayCaseTypeTestrayTasks(
			testrayCaseTypeId, testrayTasks);
	}

	@Override
	public void deleteTestrayCaseTypeTestrayTasks(
		long testrayCaseTypeId, long[] testrayTaskIds) {

		_testrayTaskLocalService.deleteTestrayCaseTypeTestrayTasks(
			testrayCaseTypeId, testrayTaskIds);
	}

	/**
	 * Deletes the testray task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayTaskId the primary key of the testray task
	 * @return the testray task that was removed
	 * @throws PortalException if a testray task with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayTask deleteTestrayTask(
			long testrayTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayTaskLocalService.deleteTestrayTask(testrayTaskId);
	}

	/**
	 * Deletes the testray task from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayTask the testray task
	 * @return the testray task that was removed
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayTask deleteTestrayTask(
		com.liferay.osb.testray.model.TestrayTask testrayTask) {

		return _testrayTaskLocalService.deleteTestrayTask(testrayTask);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testrayTaskLocalService.dynamicQuery();
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

		return _testrayTaskLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayTaskModelImpl</code>.
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

		return _testrayTaskLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayTaskModelImpl</code>.
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

		return _testrayTaskLocalService.dynamicQuery(
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

		return _testrayTaskLocalService.dynamicQueryCount(dynamicQuery);
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

		return _testrayTaskLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.testray.model.TestrayTask fetchTestrayTask(
		long testrayTaskId) {

		return _testrayTaskLocalService.fetchTestrayTask(testrayTaskId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _testrayTaskLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _testrayTaskLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _testrayTaskLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayTaskLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the testrayCaseTypeIds of the testray case types associated with the testray task.
	 *
	 * @param testrayTaskId the testrayTaskId of the testray task
	 * @return long[] the testrayCaseTypeIds of testray case types associated with the testray task
	 */
	@Override
	public long[] getTestrayCaseTypePrimaryKeys(long testrayTaskId) {
		return _testrayTaskLocalService.getTestrayCaseTypePrimaryKeys(
			testrayTaskId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayTask>
		getTestrayCaseTypeTestrayTasks(long testrayCaseTypeId) {

		return _testrayTaskLocalService.getTestrayCaseTypeTestrayTasks(
			testrayCaseTypeId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayTask>
		getTestrayCaseTypeTestrayTasks(
			long testrayCaseTypeId, int start, int end) {

		return _testrayTaskLocalService.getTestrayCaseTypeTestrayTasks(
			testrayCaseTypeId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayTask>
		getTestrayCaseTypeTestrayTasks(
			long testrayCaseTypeId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayTask> orderByComparator) {

		return _testrayTaskLocalService.getTestrayCaseTypeTestrayTasks(
			testrayCaseTypeId, start, end, orderByComparator);
	}

	@Override
	public int getTestrayCaseTypeTestrayTasksCount(long testrayCaseTypeId) {
		return _testrayTaskLocalService.getTestrayCaseTypeTestrayTasksCount(
			testrayCaseTypeId);
	}

	/**
	 * Returns the testray task with the primary key.
	 *
	 * @param testrayTaskId the primary key of the testray task
	 * @return the testray task
	 * @throws PortalException if a testray task with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayTask getTestrayTask(
			long testrayTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayTaskLocalService.getTestrayTask(testrayTaskId);
	}

	/**
	 * Returns a range of all the testray tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayTaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray tasks
	 * @param end the upper bound of the range of testray tasks (not inclusive)
	 * @return the range of testray tasks
	 */
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayTask>
		getTestrayTasks(int start, int end) {

		return _testrayTaskLocalService.getTestrayTasks(start, end);
	}

	/**
	 * Returns the number of testray tasks.
	 *
	 * @return the number of testray tasks
	 */
	@Override
	public int getTestrayTasksCount() {
		return _testrayTaskLocalService.getTestrayTasksCount();
	}

	@Override
	public boolean hasTestrayCaseTypeTestrayTask(
		long testrayCaseTypeId, long testrayTaskId) {

		return _testrayTaskLocalService.hasTestrayCaseTypeTestrayTask(
			testrayCaseTypeId, testrayTaskId);
	}

	@Override
	public boolean hasTestrayCaseTypeTestrayTasks(long testrayCaseTypeId) {
		return _testrayTaskLocalService.hasTestrayCaseTypeTestrayTasks(
			testrayCaseTypeId);
	}

	@Override
	public void setTestrayCaseTypeTestrayTasks(
		long testrayCaseTypeId, long[] testrayTaskIds) {

		_testrayTaskLocalService.setTestrayCaseTypeTestrayTasks(
			testrayCaseTypeId, testrayTaskIds);
	}

	/**
	 * Updates the testray task in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayTask the testray task
	 * @return the testray task that was updated
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayTask updateTestrayTask(
		com.liferay.osb.testray.model.TestrayTask testrayTask) {

		return _testrayTaskLocalService.updateTestrayTask(testrayTask);
	}

	@Override
	public TestrayTaskLocalService getWrappedService() {
		return _testrayTaskLocalService;
	}

	@Override
	public void setWrappedService(
		TestrayTaskLocalService testrayTaskLocalService) {

		_testrayTaskLocalService = testrayTaskLocalService;
	}

	private TestrayTaskLocalService _testrayTaskLocalService;

}