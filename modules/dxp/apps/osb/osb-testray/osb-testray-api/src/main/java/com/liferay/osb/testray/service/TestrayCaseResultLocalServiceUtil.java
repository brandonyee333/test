/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.testray.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for TestrayCaseResult. This utility wraps
 * {@link com.liferay.osb.testray.service.impl.TestrayCaseResultLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestrayCaseResultLocalService
 * @see com.liferay.osb.testray.service.base.TestrayCaseResultLocalServiceBaseImpl
 * @see com.liferay.osb.testray.service.impl.TestrayCaseResultLocalServiceImpl
 * @generated
 */
@ProviderType
public class TestrayCaseResultLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.testray.service.impl.TestrayCaseResultLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasTestrayIssueTestrayCaseResult(
		long testrayIssueId, long testrayCaseResultId) {
		return getService()
				   .hasTestrayIssueTestrayCaseResult(testrayIssueId,
			testrayCaseResultId);
	}

	public static boolean hasTestrayIssueTestrayCaseResults(long testrayIssueId) {
		return getService().hasTestrayIssueTestrayCaseResults(testrayIssueId);
	}

	public static boolean hasTestraySubtaskTestrayCaseResult(
		long testraySubtaskId, long testrayCaseResultId) {
		return getService()
				   .hasTestraySubtaskTestrayCaseResult(testraySubtaskId,
			testrayCaseResultId);
	}

	public static boolean hasTestraySubtaskTestrayCaseResults(
		long testraySubtaskId) {
		return getService().hasTestraySubtaskTestrayCaseResults(testraySubtaskId);
	}

	/**
	* Adds the testray case result to the database. Also notifies the appropriate model listeners.
	*
	* @param testrayCaseResult the testray case result
	* @return the testray case result that was added
	*/
	public static com.liferay.osb.testray.model.TestrayCaseResult addTestrayCaseResult(
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {
		return getService().addTestrayCaseResult(testrayCaseResult);
	}

	/**
	* Creates a new testray case result with the primary key. Does not add the testray case result to the database.
	*
	* @param testrayCaseResultId the primary key for the new testray case result
	* @return the new testray case result
	*/
	public static com.liferay.osb.testray.model.TestrayCaseResult createTestrayCaseResult(
		long testrayCaseResultId) {
		return getService().createTestrayCaseResult(testrayCaseResultId);
	}

	/**
	* Deletes the testray case result from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayCaseResult the testray case result
	* @return the testray case result that was removed
	*/
	public static com.liferay.osb.testray.model.TestrayCaseResult deleteTestrayCaseResult(
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {
		return getService().deleteTestrayCaseResult(testrayCaseResult);
	}

	/**
	* Deletes the testray case result with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayCaseResultId the primary key of the testray case result
	* @return the testray case result that was removed
	* @throws PortalException if a testray case result with the primary key could not be found
	*/
	public static com.liferay.osb.testray.model.TestrayCaseResult deleteTestrayCaseResult(
		long testrayCaseResultId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteTestrayCaseResult(testrayCaseResultId);
	}

	public static com.liferay.osb.testray.model.TestrayCaseResult fetchTestrayCaseResult(
		long testrayCaseResultId) {
		return getService().fetchTestrayCaseResult(testrayCaseResultId);
	}

	/**
	* Returns the testray case result with the primary key.
	*
	* @param testrayCaseResultId the primary key of the testray case result
	* @return the testray case result
	* @throws PortalException if a testray case result with the primary key could not be found
	*/
	public static com.liferay.osb.testray.model.TestrayCaseResult getTestrayCaseResult(
		long testrayCaseResultId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getTestrayCaseResult(testrayCaseResultId);
	}

	/**
	* Updates the testray case result in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param testrayCaseResult the testray case result
	* @return the testray case result that was updated
	*/
	public static com.liferay.osb.testray.model.TestrayCaseResult updateTestrayCaseResult(
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {
		return getService().updateTestrayCaseResult(testrayCaseResult);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of testray case results.
	*
	* @return the number of testray case results
	*/
	public static int getTestrayCaseResultsCount() {
		return getService().getTestrayCaseResultsCount();
	}

	public static int getTestrayIssueTestrayCaseResultsCount(
		long testrayIssueId) {
		return getService()
				   .getTestrayIssueTestrayCaseResultsCount(testrayIssueId);
	}

	public static int getTestraySubtaskTestrayCaseResultsCount(
		long testraySubtaskId) {
		return getService()
				   .getTestraySubtaskTestrayCaseResultsCount(testraySubtaskId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayCaseResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayCaseResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the testray case results.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayCaseResultModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray case results
	* @param end the upper bound of the range of testray case results (not inclusive)
	* @return the range of testray case results
	*/
	public static java.util.List<com.liferay.osb.testray.model.TestrayCaseResult> getTestrayCaseResults(
		int start, int end) {
		return getService().getTestrayCaseResults(start, end);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayCaseResult> getTestrayIssueTestrayCaseResults(
		long testrayIssueId) {
		return getService().getTestrayIssueTestrayCaseResults(testrayIssueId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayCaseResult> getTestrayIssueTestrayCaseResults(
		long testrayIssueId, int start, int end) {
		return getService()
				   .getTestrayIssueTestrayCaseResults(testrayIssueId, start, end);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayCaseResult> getTestrayIssueTestrayCaseResults(
		long testrayIssueId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.testray.model.TestrayCaseResult> orderByComparator) {
		return getService()
				   .getTestrayIssueTestrayCaseResults(testrayIssueId, start,
			end, orderByComparator);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayCaseResult> getTestraySubtaskTestrayCaseResults(
		long testraySubtaskId) {
		return getService().getTestraySubtaskTestrayCaseResults(testraySubtaskId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayCaseResult> getTestraySubtaskTestrayCaseResults(
		long testraySubtaskId, int start, int end) {
		return getService()
				   .getTestraySubtaskTestrayCaseResults(testraySubtaskId,
			start, end);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayCaseResult> getTestraySubtaskTestrayCaseResults(
		long testraySubtaskId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.testray.model.TestrayCaseResult> orderByComparator) {
		return getService()
				   .getTestraySubtaskTestrayCaseResults(testraySubtaskId,
			start, end, orderByComparator);
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

	/**
	* Returns the testrayIssueIds of the testray issues associated with the testray case result.
	*
	* @param testrayCaseResultId the testrayCaseResultId of the testray case result
	* @return long[] the testrayIssueIds of testray issues associated with the testray case result
	*/
	public static long[] getTestrayIssuePrimaryKeys(long testrayCaseResultId) {
		return getService().getTestrayIssuePrimaryKeys(testrayCaseResultId);
	}

	/**
	* Returns the testraySubtaskIds of the testray subtasks associated with the testray case result.
	*
	* @param testrayCaseResultId the testrayCaseResultId of the testray case result
	* @return long[] the testraySubtaskIds of testray subtasks associated with the testray case result
	*/
	public static long[] getTestraySubtaskPrimaryKeys(long testrayCaseResultId) {
		return getService().getTestraySubtaskPrimaryKeys(testrayCaseResultId);
	}

	public static void addTestrayIssueTestrayCaseResult(long testrayIssueId,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {
		getService()
			.addTestrayIssueTestrayCaseResult(testrayIssueId, testrayCaseResult);
	}

	public static void addTestrayIssueTestrayCaseResult(long testrayIssueId,
		long testrayCaseResultId) {
		getService()
			.addTestrayIssueTestrayCaseResult(testrayIssueId,
			testrayCaseResultId);
	}

	public static void addTestrayIssueTestrayCaseResults(long testrayIssueId,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseResult> testrayCaseResults) {
		getService()
			.addTestrayIssueTestrayCaseResults(testrayIssueId,
			testrayCaseResults);
	}

	public static void addTestrayIssueTestrayCaseResults(long testrayIssueId,
		long[] testrayCaseResultIds) {
		getService()
			.addTestrayIssueTestrayCaseResults(testrayIssueId,
			testrayCaseResultIds);
	}

	public static void addTestraySubtaskTestrayCaseResult(
		long testraySubtaskId,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {
		getService()
			.addTestraySubtaskTestrayCaseResult(testraySubtaskId,
			testrayCaseResult);
	}

	public static void addTestraySubtaskTestrayCaseResult(
		long testraySubtaskId, long testrayCaseResultId) {
		getService()
			.addTestraySubtaskTestrayCaseResult(testraySubtaskId,
			testrayCaseResultId);
	}

	public static void addTestraySubtaskTestrayCaseResults(
		long testraySubtaskId,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseResult> testrayCaseResults) {
		getService()
			.addTestraySubtaskTestrayCaseResults(testraySubtaskId,
			testrayCaseResults);
	}

	public static void addTestraySubtaskTestrayCaseResults(
		long testraySubtaskId, long[] testrayCaseResultIds) {
		getService()
			.addTestraySubtaskTestrayCaseResults(testraySubtaskId,
			testrayCaseResultIds);
	}

	public static void clearTestrayIssueTestrayCaseResults(long testrayIssueId) {
		getService().clearTestrayIssueTestrayCaseResults(testrayIssueId);
	}

	public static void clearTestraySubtaskTestrayCaseResults(
		long testraySubtaskId) {
		getService().clearTestraySubtaskTestrayCaseResults(testraySubtaskId);
	}

	public static void deleteTestrayIssueTestrayCaseResult(
		long testrayIssueId,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {
		getService()
			.deleteTestrayIssueTestrayCaseResult(testrayIssueId,
			testrayCaseResult);
	}

	public static void deleteTestrayIssueTestrayCaseResult(
		long testrayIssueId, long testrayCaseResultId) {
		getService()
			.deleteTestrayIssueTestrayCaseResult(testrayIssueId,
			testrayCaseResultId);
	}

	public static void deleteTestrayIssueTestrayCaseResults(
		long testrayIssueId,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseResult> testrayCaseResults) {
		getService()
			.deleteTestrayIssueTestrayCaseResults(testrayIssueId,
			testrayCaseResults);
	}

	public static void deleteTestrayIssueTestrayCaseResults(
		long testrayIssueId, long[] testrayCaseResultIds) {
		getService()
			.deleteTestrayIssueTestrayCaseResults(testrayIssueId,
			testrayCaseResultIds);
	}

	public static void deleteTestraySubtaskTestrayCaseResult(
		long testraySubtaskId,
		com.liferay.osb.testray.model.TestrayCaseResult testrayCaseResult) {
		getService()
			.deleteTestraySubtaskTestrayCaseResult(testraySubtaskId,
			testrayCaseResult);
	}

	public static void deleteTestraySubtaskTestrayCaseResult(
		long testraySubtaskId, long testrayCaseResultId) {
		getService()
			.deleteTestraySubtaskTestrayCaseResult(testraySubtaskId,
			testrayCaseResultId);
	}

	public static void deleteTestraySubtaskTestrayCaseResults(
		long testraySubtaskId,
		java.util.List<com.liferay.osb.testray.model.TestrayCaseResult> testrayCaseResults) {
		getService()
			.deleteTestraySubtaskTestrayCaseResults(testraySubtaskId,
			testrayCaseResults);
	}

	public static void deleteTestraySubtaskTestrayCaseResults(
		long testraySubtaskId, long[] testrayCaseResultIds) {
		getService()
			.deleteTestraySubtaskTestrayCaseResults(testraySubtaskId,
			testrayCaseResultIds);
	}

	public static void setTestrayIssueTestrayCaseResults(long testrayIssueId,
		long[] testrayCaseResultIds) {
		getService()
			.setTestrayIssueTestrayCaseResults(testrayIssueId,
			testrayCaseResultIds);
	}

	public static void setTestraySubtaskTestrayCaseResults(
		long testraySubtaskId, long[] testrayCaseResultIds) {
		getService()
			.setTestraySubtaskTestrayCaseResults(testraySubtaskId,
			testrayCaseResultIds);
	}

	public static TestrayCaseResultLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<TestrayCaseResultLocalService, TestrayCaseResultLocalService> _serviceTracker =
		ServiceTrackerFactory.open(TestrayCaseResultLocalService.class);
}