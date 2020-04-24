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
 * Provides a wrapper for {@link TestrayCaseResultLocalService}.
 *
 * @author Ethan Bustad
 * @see TestrayCaseResultLocalService
 * @generated
 */
@ProviderType
public class TestrayCaseResultLocalServiceWrapper
	implements TestrayCaseResultLocalService,
			   ServiceWrapper<TestrayCaseResultLocalService> {

	public TestrayCaseResultLocalServiceWrapper(
		TestrayCaseResultLocalService testrayCaseResultLocalService) {

		_testrayCaseResultLocalService = testrayCaseResultLocalService;
	}

	/**
	 * Adds the testray case result to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResult the testray case result
	 * @return the testray case result that was added
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCaseResult addTestrayCaseResult(
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {

		return _testrayCaseResultLocalService.addTestrayCaseResult(
			testrayCaseResult);
	}

	@Override
	public void addTestrayIssueTestrayCaseResult(
		long testrayIssueId, long testrayCaseResultId) {

		_testrayCaseResultLocalService.addTestrayIssueTestrayCaseResult(
			testrayIssueId, testrayCaseResultId);
	}

	@Override
	public void addTestrayIssueTestrayCaseResult(
		long testrayIssueId,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {

		_testrayCaseResultLocalService.addTestrayIssueTestrayCaseResult(
			testrayIssueId, testrayCaseResult);
	}

	@Override
	public void addTestrayIssueTestrayCaseResults(
		long testrayIssueId,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults) {

		_testrayCaseResultLocalService.addTestrayIssueTestrayCaseResults(
			testrayIssueId, testrayCaseResults);
	}

	@Override
	public void addTestrayIssueTestrayCaseResults(
		long testrayIssueId, long[] testrayCaseResultIds) {

		_testrayCaseResultLocalService.addTestrayIssueTestrayCaseResults(
			testrayIssueId, testrayCaseResultIds);
	}

	@Override
	public void addTestraySubtaskTestrayCaseResult(
		long testraySubtaskId, long testrayCaseResultId) {

		_testrayCaseResultLocalService.addTestraySubtaskTestrayCaseResult(
			testraySubtaskId, testrayCaseResultId);
	}

	@Override
	public void addTestraySubtaskTestrayCaseResult(
		long testraySubtaskId,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {

		_testrayCaseResultLocalService.addTestraySubtaskTestrayCaseResult(
			testraySubtaskId, testrayCaseResult);
	}

	@Override
	public void addTestraySubtaskTestrayCaseResults(
		long testraySubtaskId,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults) {

		_testrayCaseResultLocalService.addTestraySubtaskTestrayCaseResults(
			testraySubtaskId, testrayCaseResults);
	}

	@Override
	public void addTestraySubtaskTestrayCaseResults(
		long testraySubtaskId, long[] testrayCaseResultIds) {

		_testrayCaseResultLocalService.addTestraySubtaskTestrayCaseResults(
			testraySubtaskId, testrayCaseResultIds);
	}

	@Override
	public void clearTestrayIssueTestrayCaseResults(long testrayIssueId) {
		_testrayCaseResultLocalService.clearTestrayIssueTestrayCaseResults(
			testrayIssueId);
	}

	@Override
	public void clearTestraySubtaskTestrayCaseResults(long testraySubtaskId) {
		_testrayCaseResultLocalService.clearTestraySubtaskTestrayCaseResults(
			testraySubtaskId);
	}

	/**
	 * Creates a new testray case result with the primary key. Does not add the testray case result to the database.
	 *
	 * @param testrayCaseResultId the primary key for the new testray case result
	 * @return the new testray case result
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCaseResult
		createTestrayCaseResult(long testrayCaseResultId) {

		return _testrayCaseResultLocalService.createTestrayCaseResult(
			testrayCaseResultId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayCaseResultLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the testray case result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResultId the primary key of the testray case result
	 * @return the testray case result that was removed
	 * @throws PortalException if a testray case result with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCaseResult
			deleteTestrayCaseResult(long testrayCaseResultId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayCaseResultLocalService.deleteTestrayCaseResult(
			testrayCaseResultId);
	}

	/**
	 * Deletes the testray case result from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResult the testray case result
	 * @return the testray case result that was removed
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCaseResult
		deleteTestrayCaseResult(
			com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {

		return _testrayCaseResultLocalService.deleteTestrayCaseResult(
			testrayCaseResult);
	}

	@Override
	public void deleteTestrayIssueTestrayCaseResult(
		long testrayIssueId, long testrayCaseResultId) {

		_testrayCaseResultLocalService.deleteTestrayIssueTestrayCaseResult(
			testrayIssueId, testrayCaseResultId);
	}

	@Override
	public void deleteTestrayIssueTestrayCaseResult(
		long testrayIssueId,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {

		_testrayCaseResultLocalService.deleteTestrayIssueTestrayCaseResult(
			testrayIssueId, testrayCaseResult);
	}

	@Override
	public void deleteTestrayIssueTestrayCaseResults(
		long testrayIssueId,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults) {

		_testrayCaseResultLocalService.deleteTestrayIssueTestrayCaseResults(
			testrayIssueId, testrayCaseResults);
	}

	@Override
	public void deleteTestrayIssueTestrayCaseResults(
		long testrayIssueId, long[] testrayCaseResultIds) {

		_testrayCaseResultLocalService.deleteTestrayIssueTestrayCaseResults(
			testrayIssueId, testrayCaseResultIds);
	}

	@Override
	public void deleteTestraySubtaskTestrayCaseResult(
		long testraySubtaskId, long testrayCaseResultId) {

		_testrayCaseResultLocalService.deleteTestraySubtaskTestrayCaseResult(
			testraySubtaskId, testrayCaseResultId);
	}

	@Override
	public void deleteTestraySubtaskTestrayCaseResult(
		long testraySubtaskId,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {

		_testrayCaseResultLocalService.deleteTestraySubtaskTestrayCaseResult(
			testraySubtaskId, testrayCaseResult);
	}

	@Override
	public void deleteTestraySubtaskTestrayCaseResults(
		long testraySubtaskId,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults) {

		_testrayCaseResultLocalService.deleteTestraySubtaskTestrayCaseResults(
			testraySubtaskId, testrayCaseResults);
	}

	@Override
	public void deleteTestraySubtaskTestrayCaseResults(
		long testraySubtaskId, long[] testrayCaseResultIds) {

		_testrayCaseResultLocalService.deleteTestraySubtaskTestrayCaseResults(
			testraySubtaskId, testrayCaseResultIds);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testrayCaseResultLocalService.dynamicQuery();
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

		return _testrayCaseResultLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _testrayCaseResultLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _testrayCaseResultLocalService.dynamicQuery(
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

		return _testrayCaseResultLocalService.dynamicQueryCount(dynamicQuery);
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

		return _testrayCaseResultLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.testray.model.TestrayCaseResult
		fetchTestrayCaseResult(long testrayCaseResultId) {

		return _testrayCaseResultLocalService.fetchTestrayCaseResult(
			testrayCaseResultId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _testrayCaseResultLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _testrayCaseResultLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _testrayCaseResultLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayCaseResultLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the testray case result with the primary key.
	 *
	 * @param testrayCaseResultId the primary key of the testray case result
	 * @return the testray case result
	 * @throws PortalException if a testray case result with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCaseResult getTestrayCaseResult(
			long testrayCaseResultId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayCaseResultLocalService.getTestrayCaseResult(
			testrayCaseResultId);
	}

	/**
	 * Returns a range of all the testray case results.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseResultModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case results
	 * @param end the upper bound of the range of testray case results (not inclusive)
	 * @return the range of testray case results
	 */
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
		getTestrayCaseResults(int start, int end) {

		return _testrayCaseResultLocalService.getTestrayCaseResults(start, end);
	}

	/**
	 * Returns the number of testray case results.
	 *
	 * @return the number of testray case results
	 */
	@Override
	public int getTestrayCaseResultsCount() {
		return _testrayCaseResultLocalService.getTestrayCaseResultsCount();
	}

	/**
	 * Returns the testrayIssueIds of the testray issues associated with the testray case result.
	 *
	 * @param testrayCaseResultId the testrayCaseResultId of the testray case result
	 * @return long[] the testrayIssueIds of testray issues associated with the testray case result
	 */
	@Override
	public long[] getTestrayIssuePrimaryKeys(long testrayCaseResultId) {
		return _testrayCaseResultLocalService.getTestrayIssuePrimaryKeys(
			testrayCaseResultId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
		getTestrayIssueTestrayCaseResults(long testrayIssueId) {

		return _testrayCaseResultLocalService.getTestrayIssueTestrayCaseResults(
			testrayIssueId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
		getTestrayIssueTestrayCaseResults(
			long testrayIssueId, int start, int end) {

		return _testrayCaseResultLocalService.getTestrayIssueTestrayCaseResults(
			testrayIssueId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
		getTestrayIssueTestrayCaseResults(
			long testrayIssueId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayCaseResult>
					orderByComparator) {

		return _testrayCaseResultLocalService.getTestrayIssueTestrayCaseResults(
			testrayIssueId, start, end, orderByComparator);
	}

	@Override
	public int getTestrayIssueTestrayCaseResultsCount(long testrayIssueId) {
		return _testrayCaseResultLocalService.
			getTestrayIssueTestrayCaseResultsCount(testrayIssueId);
	}

	/**
	 * Returns the testraySubtaskIds of the testray subtasks associated with the testray case result.
	 *
	 * @param testrayCaseResultId the testrayCaseResultId of the testray case result
	 * @return long[] the testraySubtaskIds of testray subtasks associated with the testray case result
	 */
	@Override
	public long[] getTestraySubtaskPrimaryKeys(long testrayCaseResultId) {
		return _testrayCaseResultLocalService.getTestraySubtaskPrimaryKeys(
			testrayCaseResultId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
		getTestraySubtaskTestrayCaseResults(long testraySubtaskId) {

		return _testrayCaseResultLocalService.
			getTestraySubtaskTestrayCaseResults(testraySubtaskId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
		getTestraySubtaskTestrayCaseResults(
			long testraySubtaskId, int start, int end) {

		return _testrayCaseResultLocalService.
			getTestraySubtaskTestrayCaseResults(testraySubtaskId, start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
		getTestraySubtaskTestrayCaseResults(
			long testraySubtaskId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayCaseResult>
					orderByComparator) {

		return _testrayCaseResultLocalService.
			getTestraySubtaskTestrayCaseResults(
				testraySubtaskId, start, end, orderByComparator);
	}

	@Override
	public int getTestraySubtaskTestrayCaseResultsCount(long testraySubtaskId) {
		return _testrayCaseResultLocalService.
			getTestraySubtaskTestrayCaseResultsCount(testraySubtaskId);
	}

	@Override
	public boolean hasTestrayIssueTestrayCaseResult(
		long testrayIssueId, long testrayCaseResultId) {

		return _testrayCaseResultLocalService.hasTestrayIssueTestrayCaseResult(
			testrayIssueId, testrayCaseResultId);
	}

	@Override
	public boolean hasTestrayIssueTestrayCaseResults(long testrayIssueId) {
		return _testrayCaseResultLocalService.hasTestrayIssueTestrayCaseResults(
			testrayIssueId);
	}

	@Override
	public boolean hasTestraySubtaskTestrayCaseResult(
		long testraySubtaskId, long testrayCaseResultId) {

		return _testrayCaseResultLocalService.
			hasTestraySubtaskTestrayCaseResult(
				testraySubtaskId, testrayCaseResultId);
	}

	@Override
	public boolean hasTestraySubtaskTestrayCaseResults(long testraySubtaskId) {
		return _testrayCaseResultLocalService.
			hasTestraySubtaskTestrayCaseResults(testraySubtaskId);
	}

	@Override
	public void setTestrayIssueTestrayCaseResults(
		long testrayIssueId, long[] testrayCaseResultIds) {

		_testrayCaseResultLocalService.setTestrayIssueTestrayCaseResults(
			testrayIssueId, testrayCaseResultIds);
	}

	@Override
	public void setTestraySubtaskTestrayCaseResults(
		long testraySubtaskId, long[] testrayCaseResultIds) {

		_testrayCaseResultLocalService.setTestraySubtaskTestrayCaseResults(
			testraySubtaskId, testrayCaseResultIds);
	}

	/**
	 * Updates the testray case result in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResult the testray case result
	 * @return the testray case result that was updated
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCaseResult
		updateTestrayCaseResult(
			com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {

		return _testrayCaseResultLocalService.updateTestrayCaseResult(
			testrayCaseResult);
	}

	@Override
	public TestrayCaseResultLocalService getWrappedService() {
		return _testrayCaseResultLocalService;
	}

	@Override
	public void setWrappedService(
		TestrayCaseResultLocalService testrayCaseResultLocalService) {

		_testrayCaseResultLocalService = testrayCaseResultLocalService;
	}

	private TestrayCaseResultLocalService _testrayCaseResultLocalService;

}