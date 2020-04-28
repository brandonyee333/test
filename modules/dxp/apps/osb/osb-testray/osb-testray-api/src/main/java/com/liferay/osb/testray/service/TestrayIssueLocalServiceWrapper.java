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
 * Provides a wrapper for {@link TestrayIssueLocalService}.
 *
 * @author Ethan Bustad
 * @see TestrayIssueLocalService
 * @generated
 */
public class TestrayIssueLocalServiceWrapper
	implements ServiceWrapper<TestrayIssueLocalService>,
			   TestrayIssueLocalService {

	public TestrayIssueLocalServiceWrapper(
		TestrayIssueLocalService testrayIssueLocalService) {

		_testrayIssueLocalService = testrayIssueLocalService;
	}

	@Override
	public void addTestrayCaseResultTestrayIssue(
		long testrayCaseResultId, long testrayIssueId) {

		_testrayIssueLocalService.addTestrayCaseResultTestrayIssue(
			testrayCaseResultId, testrayIssueId);
	}

	@Override
	public void addTestrayCaseResultTestrayIssue(
		long testrayCaseResultId,
		com.liferay.osb.testray.model.TestrayIssue testrayIssue) {

		_testrayIssueLocalService.addTestrayCaseResultTestrayIssue(
			testrayCaseResultId, testrayIssue);
	}

	@Override
	public void addTestrayCaseResultTestrayIssues(
		long testrayCaseResultId,
		java.util.List<com.liferay.osb.testray.model.TestrayIssue>
			testrayIssues) {

		_testrayIssueLocalService.addTestrayCaseResultTestrayIssues(
			testrayCaseResultId, testrayIssues);
	}

	@Override
	public void addTestrayCaseResultTestrayIssues(
		long testrayCaseResultId, long[] testrayIssueIds) {

		_testrayIssueLocalService.addTestrayCaseResultTestrayIssues(
			testrayCaseResultId, testrayIssueIds);
	}

	/**
	 * Adds the testray issue to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayIssue the testray issue
	 * @return the testray issue that was added
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayIssue addTestrayIssue(
		com.liferay.osb.testray.model.TestrayIssue testrayIssue) {

		return _testrayIssueLocalService.addTestrayIssue(testrayIssue);
	}

	@Override
	public void addTestraySubtaskTestrayIssue(
		long testraySubtaskId, long testrayIssueId) {

		_testrayIssueLocalService.addTestraySubtaskTestrayIssue(
			testraySubtaskId, testrayIssueId);
	}

	@Override
	public void addTestraySubtaskTestrayIssue(
		long testraySubtaskId,
		com.liferay.osb.testray.model.TestrayIssue testrayIssue) {

		_testrayIssueLocalService.addTestraySubtaskTestrayIssue(
			testraySubtaskId, testrayIssue);
	}

	@Override
	public void addTestraySubtaskTestrayIssues(
		long testraySubtaskId,
		java.util.List<com.liferay.osb.testray.model.TestrayIssue>
			testrayIssues) {

		_testrayIssueLocalService.addTestraySubtaskTestrayIssues(
			testraySubtaskId, testrayIssues);
	}

	@Override
	public void addTestraySubtaskTestrayIssues(
		long testraySubtaskId, long[] testrayIssueIds) {

		_testrayIssueLocalService.addTestraySubtaskTestrayIssues(
			testraySubtaskId, testrayIssueIds);
	}

	@Override
	public void clearTestrayCaseResultTestrayIssues(long testrayCaseResultId) {
		_testrayIssueLocalService.clearTestrayCaseResultTestrayIssues(
			testrayCaseResultId);
	}

	@Override
	public void clearTestraySubtaskTestrayIssues(long testraySubtaskId) {
		_testrayIssueLocalService.clearTestraySubtaskTestrayIssues(
			testraySubtaskId);
	}

	/**
	 * Creates a new testray issue with the primary key. Does not add the testray issue to the database.
	 *
	 * @param testrayIssueId the primary key for the new testray issue
	 * @return the new testray issue
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayIssue createTestrayIssue(
		long testrayIssueId) {

		return _testrayIssueLocalService.createTestrayIssue(testrayIssueId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayIssueLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public void deleteTestrayCaseResultTestrayIssue(
		long testrayCaseResultId, long testrayIssueId) {

		_testrayIssueLocalService.deleteTestrayCaseResultTestrayIssue(
			testrayCaseResultId, testrayIssueId);
	}

	@Override
	public void deleteTestrayCaseResultTestrayIssue(
		long testrayCaseResultId,
		com.liferay.osb.testray.model.TestrayIssue testrayIssue) {

		_testrayIssueLocalService.deleteTestrayCaseResultTestrayIssue(
			testrayCaseResultId, testrayIssue);
	}

	@Override
	public void deleteTestrayCaseResultTestrayIssues(
		long testrayCaseResultId,
		java.util.List<com.liferay.osb.testray.model.TestrayIssue>
			testrayIssues) {

		_testrayIssueLocalService.deleteTestrayCaseResultTestrayIssues(
			testrayCaseResultId, testrayIssues);
	}

	@Override
	public void deleteTestrayCaseResultTestrayIssues(
		long testrayCaseResultId, long[] testrayIssueIds) {

		_testrayIssueLocalService.deleteTestrayCaseResultTestrayIssues(
			testrayCaseResultId, testrayIssueIds);
	}

	/**
	 * Deletes the testray issue with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayIssueId the primary key of the testray issue
	 * @return the testray issue that was removed
	 * @throws PortalException if a testray issue with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayIssue deleteTestrayIssue(
			long testrayIssueId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayIssueLocalService.deleteTestrayIssue(testrayIssueId);
	}

	/**
	 * Deletes the testray issue from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayIssue the testray issue
	 * @return the testray issue that was removed
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayIssue deleteTestrayIssue(
		com.liferay.osb.testray.model.TestrayIssue testrayIssue) {

		return _testrayIssueLocalService.deleteTestrayIssue(testrayIssue);
	}

	@Override
	public void deleteTestraySubtaskTestrayIssue(
		long testraySubtaskId, long testrayIssueId) {

		_testrayIssueLocalService.deleteTestraySubtaskTestrayIssue(
			testraySubtaskId, testrayIssueId);
	}

	@Override
	public void deleteTestraySubtaskTestrayIssue(
		long testraySubtaskId,
		com.liferay.osb.testray.model.TestrayIssue testrayIssue) {

		_testrayIssueLocalService.deleteTestraySubtaskTestrayIssue(
			testraySubtaskId, testrayIssue);
	}

	@Override
	public void deleteTestraySubtaskTestrayIssues(
		long testraySubtaskId,
		java.util.List<com.liferay.osb.testray.model.TestrayIssue>
			testrayIssues) {

		_testrayIssueLocalService.deleteTestraySubtaskTestrayIssues(
			testraySubtaskId, testrayIssues);
	}

	@Override
	public void deleteTestraySubtaskTestrayIssues(
		long testraySubtaskId, long[] testrayIssueIds) {

		_testrayIssueLocalService.deleteTestraySubtaskTestrayIssues(
			testraySubtaskId, testrayIssueIds);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testrayIssueLocalService.dynamicQuery();
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

		return _testrayIssueLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayIssueModelImpl</code>.
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

		return _testrayIssueLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayIssueModelImpl</code>.
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

		return _testrayIssueLocalService.dynamicQuery(
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

		return _testrayIssueLocalService.dynamicQueryCount(dynamicQuery);
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

		return _testrayIssueLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.testray.model.TestrayIssue fetchTestrayIssue(
		long testrayIssueId) {

		return _testrayIssueLocalService.fetchTestrayIssue(testrayIssueId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _testrayIssueLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _testrayIssueLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _testrayIssueLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayIssueLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the testrayCaseResultIds of the testray case results associated with the testray issue.
	 *
	 * @param testrayIssueId the testrayIssueId of the testray issue
	 * @return long[] the testrayCaseResultIds of testray case results associated with the testray issue
	 */
	@Override
	public long[] getTestrayCaseResultPrimaryKeys(long testrayIssueId) {
		return _testrayIssueLocalService.getTestrayCaseResultPrimaryKeys(
			testrayIssueId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayCaseResultTestrayIssues(long testrayCaseResultId) {

		return _testrayIssueLocalService.getTestrayCaseResultTestrayIssues(
			testrayCaseResultId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayCaseResultTestrayIssues(
			long testrayCaseResultId, int start, int end) {

		return _testrayIssueLocalService.getTestrayCaseResultTestrayIssues(
			testrayCaseResultId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayCaseResultTestrayIssues(
			long testrayCaseResultId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayIssue>
					orderByComparator) {

		return _testrayIssueLocalService.getTestrayCaseResultTestrayIssues(
			testrayCaseResultId, start, end, orderByComparator);
	}

	@Override
	public int getTestrayCaseResultTestrayIssuesCount(
		long testrayCaseResultId) {

		return _testrayIssueLocalService.getTestrayCaseResultTestrayIssuesCount(
			testrayCaseResultId);
	}

	/**
	 * Returns the testray issue with the primary key.
	 *
	 * @param testrayIssueId the primary key of the testray issue
	 * @return the testray issue
	 * @throws PortalException if a testray issue with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayIssue getTestrayIssue(
			long testrayIssueId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayIssueLocalService.getTestrayIssue(testrayIssueId);
	}

	/**
	 * Returns a range of all the testray issues.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayIssueModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray issues
	 * @param end the upper bound of the range of testray issues (not inclusive)
	 * @return the range of testray issues
	 */
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestrayIssues(int start, int end) {

		return _testrayIssueLocalService.getTestrayIssues(start, end);
	}

	/**
	 * Returns the number of testray issues.
	 *
	 * @return the number of testray issues
	 */
	@Override
	public int getTestrayIssuesCount() {
		return _testrayIssueLocalService.getTestrayIssuesCount();
	}

	/**
	 * Returns the testraySubtaskIds of the testray subtasks associated with the testray issue.
	 *
	 * @param testrayIssueId the testrayIssueId of the testray issue
	 * @return long[] the testraySubtaskIds of testray subtasks associated with the testray issue
	 */
	@Override
	public long[] getTestraySubtaskPrimaryKeys(long testrayIssueId) {
		return _testrayIssueLocalService.getTestraySubtaskPrimaryKeys(
			testrayIssueId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestraySubtaskTestrayIssues(long testraySubtaskId) {

		return _testrayIssueLocalService.getTestraySubtaskTestrayIssues(
			testraySubtaskId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestraySubtaskTestrayIssues(
			long testraySubtaskId, int start, int end) {

		return _testrayIssueLocalService.getTestraySubtaskTestrayIssues(
			testraySubtaskId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayIssue>
		getTestraySubtaskTestrayIssues(
			long testraySubtaskId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayIssue>
					orderByComparator) {

		return _testrayIssueLocalService.getTestraySubtaskTestrayIssues(
			testraySubtaskId, start, end, orderByComparator);
	}

	@Override
	public int getTestraySubtaskTestrayIssuesCount(long testraySubtaskId) {
		return _testrayIssueLocalService.getTestraySubtaskTestrayIssuesCount(
			testraySubtaskId);
	}

	@Override
	public boolean hasTestrayCaseResultTestrayIssue(
		long testrayCaseResultId, long testrayIssueId) {

		return _testrayIssueLocalService.hasTestrayCaseResultTestrayIssue(
			testrayCaseResultId, testrayIssueId);
	}

	@Override
	public boolean hasTestrayCaseResultTestrayIssues(long testrayCaseResultId) {
		return _testrayIssueLocalService.hasTestrayCaseResultTestrayIssues(
			testrayCaseResultId);
	}

	@Override
	public boolean hasTestraySubtaskTestrayIssue(
		long testraySubtaskId, long testrayIssueId) {

		return _testrayIssueLocalService.hasTestraySubtaskTestrayIssue(
			testraySubtaskId, testrayIssueId);
	}

	@Override
	public boolean hasTestraySubtaskTestrayIssues(long testraySubtaskId) {
		return _testrayIssueLocalService.hasTestraySubtaskTestrayIssues(
			testraySubtaskId);
	}

	@Override
	public void setTestrayCaseResultTestrayIssues(
		long testrayCaseResultId, long[] testrayIssueIds) {

		_testrayIssueLocalService.setTestrayCaseResultTestrayIssues(
			testrayCaseResultId, testrayIssueIds);
	}

	@Override
	public void setTestraySubtaskTestrayIssues(
		long testraySubtaskId, long[] testrayIssueIds) {

		_testrayIssueLocalService.setTestraySubtaskTestrayIssues(
			testraySubtaskId, testrayIssueIds);
	}

	/**
	 * Updates the testray issue in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayIssue the testray issue
	 * @return the testray issue that was updated
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayIssue updateTestrayIssue(
		com.liferay.osb.testray.model.TestrayIssue testrayIssue) {

		return _testrayIssueLocalService.updateTestrayIssue(testrayIssue);
	}

	@Override
	public TestrayIssueLocalService getWrappedService() {
		return _testrayIssueLocalService;
	}

	@Override
	public void setWrappedService(
		TestrayIssueLocalService testrayIssueLocalService) {

		_testrayIssueLocalService = testrayIssueLocalService;
	}

	private TestrayIssueLocalService _testrayIssueLocalService;

}