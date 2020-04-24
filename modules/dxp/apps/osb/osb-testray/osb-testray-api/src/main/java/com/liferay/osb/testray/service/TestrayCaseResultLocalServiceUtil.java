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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for TestrayCaseResult. This utility wraps
 * <code>com.liferay.osb.testray.service.impl.TestrayCaseResultLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestrayCaseResultLocalService
 * @generated
 */
@ProviderType
public class TestrayCaseResultLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.testray.service.impl.TestrayCaseResultLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the testray case result to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResult the testray case result
	 * @return the testray case result that was added
	 */
	public static com.liferay.osb.testray.model.TestrayCaseResult
		addTestrayCaseResult(
			com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {

		return getService().addTestrayCaseResult(testrayCaseResult);
	}

	public static void addTestrayIssueTestrayCaseResult(
		long testrayIssueId, long testrayCaseResultId) {

		getService().addTestrayIssueTestrayCaseResult(
			testrayIssueId, testrayCaseResultId);
	}

	public static void addTestrayIssueTestrayCaseResult(
		long testrayIssueId,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {

		getService().addTestrayIssueTestrayCaseResult(
			testrayIssueId, testrayCaseResult);
	}

	public static void addTestrayIssueTestrayCaseResults(
		long testrayIssueId,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults) {

		getService().addTestrayIssueTestrayCaseResults(
			testrayIssueId, testrayCaseResults);
	}

	public static void addTestrayIssueTestrayCaseResults(
		long testrayIssueId, long[] testrayCaseResultIds) {

		getService().addTestrayIssueTestrayCaseResults(
			testrayIssueId, testrayCaseResultIds);
	}

	public static void addTestraySubtaskTestrayCaseResult(
		long testraySubtaskId, long testrayCaseResultId) {

		getService().addTestraySubtaskTestrayCaseResult(
			testraySubtaskId, testrayCaseResultId);
	}

	public static void addTestraySubtaskTestrayCaseResult(
		long testraySubtaskId,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {

		getService().addTestraySubtaskTestrayCaseResult(
			testraySubtaskId, testrayCaseResult);
	}

	public static void addTestraySubtaskTestrayCaseResults(
		long testraySubtaskId,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults) {

		getService().addTestraySubtaskTestrayCaseResults(
			testraySubtaskId, testrayCaseResults);
	}

	public static void addTestraySubtaskTestrayCaseResults(
		long testraySubtaskId, long[] testrayCaseResultIds) {

		getService().addTestraySubtaskTestrayCaseResults(
			testraySubtaskId, testrayCaseResultIds);
	}

	public static void clearTestrayIssueTestrayCaseResults(
		long testrayIssueId) {

		getService().clearTestrayIssueTestrayCaseResults(testrayIssueId);
	}

	public static void clearTestraySubtaskTestrayCaseResults(
		long testraySubtaskId) {

		getService().clearTestraySubtaskTestrayCaseResults(testraySubtaskId);
	}

	/**
	 * Creates a new testray case result with the primary key. Does not add the testray case result to the database.
	 *
	 * @param testrayCaseResultId the primary key for the new testray case result
	 * @return the new testray case result
	 */
	public static com.liferay.osb.testray.model.TestrayCaseResult
		createTestrayCaseResult(long testrayCaseResultId) {

		return getService().createTestrayCaseResult(testrayCaseResultId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the testray case result with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResultId the primary key of the testray case result
	 * @return the testray case result that was removed
	 * @throws PortalException if a testray case result with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestrayCaseResult
			deleteTestrayCaseResult(long testrayCaseResultId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteTestrayCaseResult(testrayCaseResultId);
	}

	/**
	 * Deletes the testray case result from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResult the testray case result
	 * @return the testray case result that was removed
	 */
	public static com.liferay.osb.testray.model.TestrayCaseResult
		deleteTestrayCaseResult(
			com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {

		return getService().deleteTestrayCaseResult(testrayCaseResult);
	}

	public static void deleteTestrayIssueTestrayCaseResult(
		long testrayIssueId, long testrayCaseResultId) {

		getService().deleteTestrayIssueTestrayCaseResult(
			testrayIssueId, testrayCaseResultId);
	}

	public static void deleteTestrayIssueTestrayCaseResult(
		long testrayIssueId,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {

		getService().deleteTestrayIssueTestrayCaseResult(
			testrayIssueId, testrayCaseResult);
	}

	public static void deleteTestrayIssueTestrayCaseResults(
		long testrayIssueId,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults) {

		getService().deleteTestrayIssueTestrayCaseResults(
			testrayIssueId, testrayCaseResults);
	}

	public static void deleteTestrayIssueTestrayCaseResults(
		long testrayIssueId, long[] testrayCaseResultIds) {

		getService().deleteTestrayIssueTestrayCaseResults(
			testrayIssueId, testrayCaseResultIds);
	}

	public static void deleteTestraySubtaskTestrayCaseResult(
		long testraySubtaskId, long testrayCaseResultId) {

		getService().deleteTestraySubtaskTestrayCaseResult(
			testraySubtaskId, testrayCaseResultId);
	}

	public static void deleteTestraySubtaskTestrayCaseResult(
		long testraySubtaskId,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {

		getService().deleteTestraySubtaskTestrayCaseResult(
			testraySubtaskId, testrayCaseResult);
	}

	public static void deleteTestraySubtaskTestrayCaseResults(
		long testraySubtaskId,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseResult>
			testrayCaseResults) {

		getService().deleteTestraySubtaskTestrayCaseResults(
			testraySubtaskId, testrayCaseResults);
	}

	public static void deleteTestraySubtaskTestrayCaseResults(
		long testraySubtaskId, long[] testrayCaseResultIds) {

		getService().deleteTestraySubtaskTestrayCaseResults(
			testraySubtaskId, testrayCaseResultIds);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.osb.testray.model.TestrayCaseResult
		fetchTestrayCaseResult(long testrayCaseResultId) {

		return getService().fetchTestrayCaseResult(testrayCaseResultId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the testray case result with the primary key.
	 *
	 * @param testrayCaseResultId the primary key of the testray case result
	 * @return the testray case result
	 * @throws PortalException if a testray case result with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestrayCaseResult
			getTestrayCaseResult(long testrayCaseResultId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTestrayCaseResult(testrayCaseResultId);
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
	public static java.util.List
		<com.liferay.osb.testray.model.TestrayCaseResult> getTestrayCaseResults(
			int start, int end) {

		return getService().getTestrayCaseResults(start, end);
	}

	/**
	 * Returns the number of testray case results.
	 *
	 * @return the number of testray case results
	 */
	public static int getTestrayCaseResultsCount() {
		return getService().getTestrayCaseResultsCount();
	}

	/**
	 * Returns the testrayIssueIds of the testray issues associated with the testray case result.
	 *
	 * @param testrayCaseResultId the testrayCaseResultId of the testray case result
	 * @return long[] the testrayIssueIds of testray issues associated with the testray case result
	 */
	public static long[] getTestrayIssuePrimaryKeys(long testrayCaseResultId) {
		return getService().getTestrayIssuePrimaryKeys(testrayCaseResultId);
	}

	public static java.util.List
		<com.liferay.osb.testray.model.TestrayCaseResult>
			getTestrayIssueTestrayCaseResults(long testrayIssueId) {

		return getService().getTestrayIssueTestrayCaseResults(testrayIssueId);
	}

	public static java.util.List
		<com.liferay.osb.testray.model.TestrayCaseResult>
			getTestrayIssueTestrayCaseResults(
				long testrayIssueId, int start, int end) {

		return getService().getTestrayIssueTestrayCaseResults(
			testrayIssueId, start, end);
	}

	public static java.util.List
		<com.liferay.osb.testray.model.TestrayCaseResult>
			getTestrayIssueTestrayCaseResults(
				long testrayIssueId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.osb.testray.model.TestrayCaseResult>
						orderByComparator) {

		return getService().getTestrayIssueTestrayCaseResults(
			testrayIssueId, start, end, orderByComparator);
	}

	public static int getTestrayIssueTestrayCaseResultsCount(
		long testrayIssueId) {

		return getService().getTestrayIssueTestrayCaseResultsCount(
			testrayIssueId);
	}

	/**
	 * Returns the testraySubtaskIds of the testray subtasks associated with the testray case result.
	 *
	 * @param testrayCaseResultId the testrayCaseResultId of the testray case result
	 * @return long[] the testraySubtaskIds of testray subtasks associated with the testray case result
	 */
	public static long[] getTestraySubtaskPrimaryKeys(
		long testrayCaseResultId) {

		return getService().getTestraySubtaskPrimaryKeys(testrayCaseResultId);
	}

	public static java.util.List
		<com.liferay.osb.testray.model.TestrayCaseResult>
			getTestraySubtaskTestrayCaseResults(long testraySubtaskId) {

		return getService().getTestraySubtaskTestrayCaseResults(
			testraySubtaskId);
	}

	public static java.util.List
		<com.liferay.osb.testray.model.TestrayCaseResult>
			getTestraySubtaskTestrayCaseResults(
				long testraySubtaskId, int start, int end) {

		return getService().getTestraySubtaskTestrayCaseResults(
			testraySubtaskId, start, end);
	}

	public static java.util.List
		<com.liferay.osb.testray.model.TestrayCaseResult>
			getTestraySubtaskTestrayCaseResults(
				long testraySubtaskId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.osb.testray.model.TestrayCaseResult>
						orderByComparator) {

		return getService().getTestraySubtaskTestrayCaseResults(
			testraySubtaskId, start, end, orderByComparator);
	}

	public static int getTestraySubtaskTestrayCaseResultsCount(
		long testraySubtaskId) {

		return getService().getTestraySubtaskTestrayCaseResultsCount(
			testraySubtaskId);
	}

	public static boolean hasTestrayIssueTestrayCaseResult(
		long testrayIssueId, long testrayCaseResultId) {

		return getService().hasTestrayIssueTestrayCaseResult(
			testrayIssueId, testrayCaseResultId);
	}

	public static boolean hasTestrayIssueTestrayCaseResults(
		long testrayIssueId) {

		return getService().hasTestrayIssueTestrayCaseResults(testrayIssueId);
	}

	public static boolean hasTestraySubtaskTestrayCaseResult(
		long testraySubtaskId, long testrayCaseResultId) {

		return getService().hasTestraySubtaskTestrayCaseResult(
			testraySubtaskId, testrayCaseResultId);
	}

	public static boolean hasTestraySubtaskTestrayCaseResults(
		long testraySubtaskId) {

		return getService().hasTestraySubtaskTestrayCaseResults(
			testraySubtaskId);
	}

	public static void setTestrayIssueTestrayCaseResults(
		long testrayIssueId, long[] testrayCaseResultIds) {

		getService().setTestrayIssueTestrayCaseResults(
			testrayIssueId, testrayCaseResultIds);
	}

	public static void setTestraySubtaskTestrayCaseResults(
		long testraySubtaskId, long[] testrayCaseResultIds) {

		getService().setTestraySubtaskTestrayCaseResults(
			testraySubtaskId, testrayCaseResultIds);
	}

	/**
	 * Updates the testray case result in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResult the testray case result
	 * @return the testray case result that was updated
	 */
	public static com.liferay.osb.testray.model.TestrayCaseResult
		updateTestrayCaseResult(
			com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {

		return getService().updateTestrayCaseResult(testrayCaseResult);
	}

	public static TestrayCaseResultLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestrayCaseResultLocalService, TestrayCaseResultLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			TestrayCaseResultLocalService.class);

		ServiceTracker
			<TestrayCaseResultLocalService, TestrayCaseResultLocalService>
				serviceTracker =
					new ServiceTracker
						<TestrayCaseResultLocalService,
						 TestrayCaseResultLocalService>(
							 bundle.getBundleContext(),
							 TestrayCaseResultLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}