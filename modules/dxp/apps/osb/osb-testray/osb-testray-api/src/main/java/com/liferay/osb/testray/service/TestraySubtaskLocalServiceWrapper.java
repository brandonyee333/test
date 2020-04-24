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
 * Provides a wrapper for {@link TestraySubtaskLocalService}.
 *
 * @author Ethan Bustad
 * @see TestraySubtaskLocalService
 * @generated
 */
@ProviderType
public class TestraySubtaskLocalServiceWrapper
	implements TestraySubtaskLocalService,
			   ServiceWrapper<TestraySubtaskLocalService> {

	public TestraySubtaskLocalServiceWrapper(
		TestraySubtaskLocalService testraySubtaskLocalService) {

		_testraySubtaskLocalService = testraySubtaskLocalService;
	}

	@Override
	public void addTestrayCaseResultTestraySubtask(
		long testrayCaseResultId, long testraySubtaskId) {

		_testraySubtaskLocalService.addTestrayCaseResultTestraySubtask(
			testrayCaseResultId, testraySubtaskId);
	}

	@Override
	public void addTestrayCaseResultTestraySubtask(
		long testrayCaseResultId,
		com.liferay.osb.testray.model.TestraySubtask testraySubtask) {

		_testraySubtaskLocalService.addTestrayCaseResultTestraySubtask(
			testrayCaseResultId, testraySubtask);
	}

	@Override
	public void addTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId,
		java.util.List<com.liferay.osb.testray.model.TestraySubtask>
			testraySubtasks) {

		_testraySubtaskLocalService.addTestrayCaseResultTestraySubtasks(
			testrayCaseResultId, testraySubtasks);
	}

	@Override
	public void addTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId, long[] testraySubtaskIds) {

		_testraySubtaskLocalService.addTestrayCaseResultTestraySubtasks(
			testrayCaseResultId, testraySubtaskIds);
	}

	@Override
	public void addTestrayIssueTestraySubtask(
		long testrayIssueId, long testraySubtaskId) {

		_testraySubtaskLocalService.addTestrayIssueTestraySubtask(
			testrayIssueId, testraySubtaskId);
	}

	@Override
	public void addTestrayIssueTestraySubtask(
		long testrayIssueId,
		com.liferay.osb.testray.model.TestraySubtask testraySubtask) {

		_testraySubtaskLocalService.addTestrayIssueTestraySubtask(
			testrayIssueId, testraySubtask);
	}

	@Override
	public void addTestrayIssueTestraySubtasks(
		long testrayIssueId,
		java.util.List<com.liferay.osb.testray.model.TestraySubtask>
			testraySubtasks) {

		_testraySubtaskLocalService.addTestrayIssueTestraySubtasks(
			testrayIssueId, testraySubtasks);
	}

	@Override
	public void addTestrayIssueTestraySubtasks(
		long testrayIssueId, long[] testraySubtaskIds) {

		_testraySubtaskLocalService.addTestrayIssueTestraySubtasks(
			testrayIssueId, testraySubtaskIds);
	}

	/**
	 * Adds the testray subtask to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testraySubtask the testray subtask
	 * @return the testray subtask that was added
	 */
	@Override
	public com.liferay.osb.testray.model.TestraySubtask addTestraySubtask(
		com.liferay.osb.testray.model.TestraySubtask testraySubtask) {

		return _testraySubtaskLocalService.addTestraySubtask(testraySubtask);
	}

	@Override
	public void clearTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId) {

		_testraySubtaskLocalService.clearTestrayCaseResultTestraySubtasks(
			testrayCaseResultId);
	}

	@Override
	public void clearTestrayIssueTestraySubtasks(long testrayIssueId) {
		_testraySubtaskLocalService.clearTestrayIssueTestraySubtasks(
			testrayIssueId);
	}

	/**
	 * Creates a new testray subtask with the primary key. Does not add the testray subtask to the database.
	 *
	 * @param testraySubtaskId the primary key for the new testray subtask
	 * @return the new testray subtask
	 */
	@Override
	public com.liferay.osb.testray.model.TestraySubtask createTestraySubtask(
		long testraySubtaskId) {

		return _testraySubtaskLocalService.createTestraySubtask(
			testraySubtaskId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testraySubtaskLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public void deleteTestrayCaseResultTestraySubtask(
		long testrayCaseResultId, long testraySubtaskId) {

		_testraySubtaskLocalService.deleteTestrayCaseResultTestraySubtask(
			testrayCaseResultId, testraySubtaskId);
	}

	@Override
	public void deleteTestrayCaseResultTestraySubtask(
		long testrayCaseResultId,
		com.liferay.osb.testray.model.TestraySubtask testraySubtask) {

		_testraySubtaskLocalService.deleteTestrayCaseResultTestraySubtask(
			testrayCaseResultId, testraySubtask);
	}

	@Override
	public void deleteTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId,
		java.util.List<com.liferay.osb.testray.model.TestraySubtask>
			testraySubtasks) {

		_testraySubtaskLocalService.deleteTestrayCaseResultTestraySubtasks(
			testrayCaseResultId, testraySubtasks);
	}

	@Override
	public void deleteTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId, long[] testraySubtaskIds) {

		_testraySubtaskLocalService.deleteTestrayCaseResultTestraySubtasks(
			testrayCaseResultId, testraySubtaskIds);
	}

	@Override
	public void deleteTestrayIssueTestraySubtask(
		long testrayIssueId, long testraySubtaskId) {

		_testraySubtaskLocalService.deleteTestrayIssueTestraySubtask(
			testrayIssueId, testraySubtaskId);
	}

	@Override
	public void deleteTestrayIssueTestraySubtask(
		long testrayIssueId,
		com.liferay.osb.testray.model.TestraySubtask testraySubtask) {

		_testraySubtaskLocalService.deleteTestrayIssueTestraySubtask(
			testrayIssueId, testraySubtask);
	}

	@Override
	public void deleteTestrayIssueTestraySubtasks(
		long testrayIssueId,
		java.util.List<com.liferay.osb.testray.model.TestraySubtask>
			testraySubtasks) {

		_testraySubtaskLocalService.deleteTestrayIssueTestraySubtasks(
			testrayIssueId, testraySubtasks);
	}

	@Override
	public void deleteTestrayIssueTestraySubtasks(
		long testrayIssueId, long[] testraySubtaskIds) {

		_testraySubtaskLocalService.deleteTestrayIssueTestraySubtasks(
			testrayIssueId, testraySubtaskIds);
	}

	/**
	 * Deletes the testray subtask with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testraySubtaskId the primary key of the testray subtask
	 * @return the testray subtask that was removed
	 * @throws PortalException if a testray subtask with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestraySubtask deleteTestraySubtask(
			long testraySubtaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testraySubtaskLocalService.deleteTestraySubtask(
			testraySubtaskId);
	}

	/**
	 * Deletes the testray subtask from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testraySubtask the testray subtask
	 * @return the testray subtask that was removed
	 */
	@Override
	public com.liferay.osb.testray.model.TestraySubtask deleteTestraySubtask(
		com.liferay.osb.testray.model.TestraySubtask testraySubtask) {

		return _testraySubtaskLocalService.deleteTestraySubtask(testraySubtask);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testraySubtaskLocalService.dynamicQuery();
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

		return _testraySubtaskLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestraySubtaskModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _testraySubtaskLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestraySubtaskModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _testraySubtaskLocalService.dynamicQuery(
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

		return _testraySubtaskLocalService.dynamicQueryCount(dynamicQuery);
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

		return _testraySubtaskLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.testray.model.TestraySubtask fetchTestraySubtask(
		long testraySubtaskId) {

		return _testraySubtaskLocalService.fetchTestraySubtask(
			testraySubtaskId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _testraySubtaskLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _testraySubtaskLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _testraySubtaskLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testraySubtaskLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the testrayCaseResultIds of the testray case results associated with the testray subtask.
	 *
	 * @param testraySubtaskId the testraySubtaskId of the testray subtask
	 * @return long[] the testrayCaseResultIds of testray case results associated with the testray subtask
	 */
	@Override
	public long[] getTestrayCaseResultPrimaryKeys(long testraySubtaskId) {
		return _testraySubtaskLocalService.getTestrayCaseResultPrimaryKeys(
			testraySubtaskId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestrayCaseResultTestraySubtasks(long testrayCaseResultId) {

		return _testraySubtaskLocalService.getTestrayCaseResultTestraySubtasks(
			testrayCaseResultId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestrayCaseResultTestraySubtasks(
			long testrayCaseResultId, int start, int end) {

		return _testraySubtaskLocalService.getTestrayCaseResultTestraySubtasks(
			testrayCaseResultId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestrayCaseResultTestraySubtasks(
			long testrayCaseResultId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestraySubtask>
					orderByComparator) {

		return _testraySubtaskLocalService.getTestrayCaseResultTestraySubtasks(
			testrayCaseResultId, start, end, orderByComparator);
	}

	@Override
	public int getTestrayCaseResultTestraySubtasksCount(
		long testrayCaseResultId) {

		return _testraySubtaskLocalService.
			getTestrayCaseResultTestraySubtasksCount(testrayCaseResultId);
	}

	/**
	 * Returns the testrayIssueIds of the testray issues associated with the testray subtask.
	 *
	 * @param testraySubtaskId the testraySubtaskId of the testray subtask
	 * @return long[] the testrayIssueIds of testray issues associated with the testray subtask
	 */
	@Override
	public long[] getTestrayIssuePrimaryKeys(long testraySubtaskId) {
		return _testraySubtaskLocalService.getTestrayIssuePrimaryKeys(
			testraySubtaskId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestrayIssueTestraySubtasks(long testrayIssueId) {

		return _testraySubtaskLocalService.getTestrayIssueTestraySubtasks(
			testrayIssueId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestrayIssueTestraySubtasks(
			long testrayIssueId, int start, int end) {

		return _testraySubtaskLocalService.getTestrayIssueTestraySubtasks(
			testrayIssueId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestrayIssueTestraySubtasks(
			long testrayIssueId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestraySubtask>
					orderByComparator) {

		return _testraySubtaskLocalService.getTestrayIssueTestraySubtasks(
			testrayIssueId, start, end, orderByComparator);
	}

	@Override
	public int getTestrayIssueTestraySubtasksCount(long testrayIssueId) {
		return _testraySubtaskLocalService.getTestrayIssueTestraySubtasksCount(
			testrayIssueId);
	}

	/**
	 * Returns the testray subtask with the primary key.
	 *
	 * @param testraySubtaskId the primary key of the testray subtask
	 * @return the testray subtask
	 * @throws PortalException if a testray subtask with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestraySubtask getTestraySubtask(
			long testraySubtaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testraySubtaskLocalService.getTestraySubtask(testraySubtaskId);
	}

	/**
	 * Returns a range of all the testray subtasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestraySubtaskModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray subtasks
	 * @param end the upper bound of the range of testray subtasks (not inclusive)
	 * @return the range of testray subtasks
	 */
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestraySubtask>
		getTestraySubtasks(int start, int end) {

		return _testraySubtaskLocalService.getTestraySubtasks(start, end);
	}

	/**
	 * Returns the number of testray subtasks.
	 *
	 * @return the number of testray subtasks
	 */
	@Override
	public int getTestraySubtasksCount() {
		return _testraySubtaskLocalService.getTestraySubtasksCount();
	}

	@Override
	public boolean hasTestrayCaseResultTestraySubtask(
		long testrayCaseResultId, long testraySubtaskId) {

		return _testraySubtaskLocalService.hasTestrayCaseResultTestraySubtask(
			testrayCaseResultId, testraySubtaskId);
	}

	@Override
	public boolean hasTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId) {

		return _testraySubtaskLocalService.hasTestrayCaseResultTestraySubtasks(
			testrayCaseResultId);
	}

	@Override
	public boolean hasTestrayIssueTestraySubtask(
		long testrayIssueId, long testraySubtaskId) {

		return _testraySubtaskLocalService.hasTestrayIssueTestraySubtask(
			testrayIssueId, testraySubtaskId);
	}

	@Override
	public boolean hasTestrayIssueTestraySubtasks(long testrayIssueId) {
		return _testraySubtaskLocalService.hasTestrayIssueTestraySubtasks(
			testrayIssueId);
	}

	@Override
	public void setTestrayCaseResultTestraySubtasks(
		long testrayCaseResultId, long[] testraySubtaskIds) {

		_testraySubtaskLocalService.setTestrayCaseResultTestraySubtasks(
			testrayCaseResultId, testraySubtaskIds);
	}

	@Override
	public void setTestrayIssueTestraySubtasks(
		long testrayIssueId, long[] testraySubtaskIds) {

		_testraySubtaskLocalService.setTestrayIssueTestraySubtasks(
			testrayIssueId, testraySubtaskIds);
	}

	/**
	 * Updates the testray subtask in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testraySubtask the testray subtask
	 * @return the testray subtask that was updated
	 */
	@Override
	public com.liferay.osb.testray.model.TestraySubtask updateTestraySubtask(
		com.liferay.osb.testray.model.TestraySubtask testraySubtask) {

		return _testraySubtaskLocalService.updateTestraySubtask(testraySubtask);
	}

	@Override
	public TestraySubtaskLocalService getWrappedService() {
		return _testraySubtaskLocalService;
	}

	@Override
	public void setWrappedService(
		TestraySubtaskLocalService testraySubtaskLocalService) {

		_testraySubtaskLocalService = testraySubtaskLocalService;
	}

	private TestraySubtaskLocalService _testraySubtaskLocalService;

}