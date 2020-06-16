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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for TestrayCase. This utility wraps
 * <code>com.liferay.osb.testray.service.impl.TestrayCaseLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestrayCaseLocalService
 * @generated
 */
public class TestrayCaseLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.testray.service.impl.TestrayCaseLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addTestrayBuildTestrayCase(
		long testrayBuildId, long testrayCaseId) {

		getService().addTestrayBuildTestrayCase(testrayBuildId, testrayCaseId);
	}

	public static void addTestrayBuildTestrayCase(
		long testrayBuildId,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		getService().addTestrayBuildTestrayCase(testrayBuildId, testrayCase);
	}

	public static void addTestrayBuildTestrayCases(
		long testrayBuildId,
		java.util.List<com.liferay.osb.testray.model.TestrayCase>
			testrayCases) {

		getService().addTestrayBuildTestrayCases(testrayBuildId, testrayCases);
	}

	public static void addTestrayBuildTestrayCases(
		long testrayBuildId, long[] testrayCaseIds) {

		getService().addTestrayBuildTestrayCases(
			testrayBuildId, testrayCaseIds);
	}

	/**
	 * Adds the testray case to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCase the testray case
	 * @return the testray case that was added
	 */
	public static com.liferay.osb.testray.model.TestrayCase addTestrayCase(
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		return getService().addTestrayCase(testrayCase);
	}

	public static void addTestrayComponentTestrayCase(
		long testrayComponentId, long testrayCaseId) {

		getService().addTestrayComponentTestrayCase(
			testrayComponentId, testrayCaseId);
	}

	public static void addTestrayComponentTestrayCase(
		long testrayComponentId,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		getService().addTestrayComponentTestrayCase(
			testrayComponentId, testrayCase);
	}

	public static void addTestrayComponentTestrayCases(
		long testrayComponentId,
		java.util.List<com.liferay.osb.testray.model.TestrayCase>
			testrayCases) {

		getService().addTestrayComponentTestrayCases(
			testrayComponentId, testrayCases);
	}

	public static void addTestrayComponentTestrayCases(
		long testrayComponentId, long[] testrayCaseIds) {

		getService().addTestrayComponentTestrayCases(
			testrayComponentId, testrayCaseIds);
	}

	public static void addTestrayRequirementTestrayCase(
		long testrayRequirementId, long testrayCaseId) {

		getService().addTestrayRequirementTestrayCase(
			testrayRequirementId, testrayCaseId);
	}

	public static void addTestrayRequirementTestrayCase(
		long testrayRequirementId,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		getService().addTestrayRequirementTestrayCase(
			testrayRequirementId, testrayCase);
	}

	public static void addTestrayRequirementTestrayCases(
		long testrayRequirementId,
		java.util.List<com.liferay.osb.testray.model.TestrayCase>
			testrayCases) {

		getService().addTestrayRequirementTestrayCases(
			testrayRequirementId, testrayCases);
	}

	public static void addTestrayRequirementTestrayCases(
		long testrayRequirementId, long[] testrayCaseIds) {

		getService().addTestrayRequirementTestrayCases(
			testrayRequirementId, testrayCaseIds);
	}

	public static void addTestraySuiteTestrayCase(
		long testraySuiteId, long testrayCaseId) {

		getService().addTestraySuiteTestrayCase(testraySuiteId, testrayCaseId);
	}

	public static void addTestraySuiteTestrayCase(
		long testraySuiteId,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		getService().addTestraySuiteTestrayCase(testraySuiteId, testrayCase);
	}

	public static void addTestraySuiteTestrayCases(
		long testraySuiteId,
		java.util.List<com.liferay.osb.testray.model.TestrayCase>
			testrayCases) {

		getService().addTestraySuiteTestrayCases(testraySuiteId, testrayCases);
	}

	public static void addTestraySuiteTestrayCases(
		long testraySuiteId, long[] testrayCaseIds) {

		getService().addTestraySuiteTestrayCases(
			testraySuiteId, testrayCaseIds);
	}

	public static void clearTestrayBuildTestrayCases(long testrayBuildId) {
		getService().clearTestrayBuildTestrayCases(testrayBuildId);
	}

	public static void clearTestrayComponentTestrayCases(
		long testrayComponentId) {

		getService().clearTestrayComponentTestrayCases(testrayComponentId);
	}

	public static void clearTestrayRequirementTestrayCases(
		long testrayRequirementId) {

		getService().clearTestrayRequirementTestrayCases(testrayRequirementId);
	}

	public static void clearTestraySuiteTestrayCases(long testraySuiteId) {
		getService().clearTestraySuiteTestrayCases(testraySuiteId);
	}

	/**
	 * Creates a new testray case with the primary key. Does not add the testray case to the database.
	 *
	 * @param testrayCaseId the primary key for the new testray case
	 * @return the new testray case
	 */
	public static com.liferay.osb.testray.model.TestrayCase createTestrayCase(
		long testrayCaseId) {

		return getService().createTestrayCase(testrayCaseId);
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

	public static void deleteTestrayBuildTestrayCase(
		long testrayBuildId, long testrayCaseId) {

		getService().deleteTestrayBuildTestrayCase(
			testrayBuildId, testrayCaseId);
	}

	public static void deleteTestrayBuildTestrayCase(
		long testrayBuildId,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		getService().deleteTestrayBuildTestrayCase(testrayBuildId, testrayCase);
	}

	public static void deleteTestrayBuildTestrayCases(
		long testrayBuildId,
		java.util.List<com.liferay.osb.testray.model.TestrayCase>
			testrayCases) {

		getService().deleteTestrayBuildTestrayCases(
			testrayBuildId, testrayCases);
	}

	public static void deleteTestrayBuildTestrayCases(
		long testrayBuildId, long[] testrayCaseIds) {

		getService().deleteTestrayBuildTestrayCases(
			testrayBuildId, testrayCaseIds);
	}

	/**
	 * Deletes the testray case with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseId the primary key of the testray case
	 * @return the testray case that was removed
	 * @throws PortalException if a testray case with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestrayCase deleteTestrayCase(
			long testrayCaseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteTestrayCase(testrayCaseId);
	}

	/**
	 * Deletes the testray case from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCase the testray case
	 * @return the testray case that was removed
	 */
	public static com.liferay.osb.testray.model.TestrayCase deleteTestrayCase(
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		return getService().deleteTestrayCase(testrayCase);
	}

	public static void deleteTestrayComponentTestrayCase(
		long testrayComponentId, long testrayCaseId) {

		getService().deleteTestrayComponentTestrayCase(
			testrayComponentId, testrayCaseId);
	}

	public static void deleteTestrayComponentTestrayCase(
		long testrayComponentId,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		getService().deleteTestrayComponentTestrayCase(
			testrayComponentId, testrayCase);
	}

	public static void deleteTestrayComponentTestrayCases(
		long testrayComponentId,
		java.util.List<com.liferay.osb.testray.model.TestrayCase>
			testrayCases) {

		getService().deleteTestrayComponentTestrayCases(
			testrayComponentId, testrayCases);
	}

	public static void deleteTestrayComponentTestrayCases(
		long testrayComponentId, long[] testrayCaseIds) {

		getService().deleteTestrayComponentTestrayCases(
			testrayComponentId, testrayCaseIds);
	}

	public static void deleteTestrayRequirementTestrayCase(
		long testrayRequirementId, long testrayCaseId) {

		getService().deleteTestrayRequirementTestrayCase(
			testrayRequirementId, testrayCaseId);
	}

	public static void deleteTestrayRequirementTestrayCase(
		long testrayRequirementId,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		getService().deleteTestrayRequirementTestrayCase(
			testrayRequirementId, testrayCase);
	}

	public static void deleteTestrayRequirementTestrayCases(
		long testrayRequirementId,
		java.util.List<com.liferay.osb.testray.model.TestrayCase>
			testrayCases) {

		getService().deleteTestrayRequirementTestrayCases(
			testrayRequirementId, testrayCases);
	}

	public static void deleteTestrayRequirementTestrayCases(
		long testrayRequirementId, long[] testrayCaseIds) {

		getService().deleteTestrayRequirementTestrayCases(
			testrayRequirementId, testrayCaseIds);
	}

	public static void deleteTestraySuiteTestrayCase(
		long testraySuiteId, long testrayCaseId) {

		getService().deleteTestraySuiteTestrayCase(
			testraySuiteId, testrayCaseId);
	}

	public static void deleteTestraySuiteTestrayCase(
		long testraySuiteId,
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		getService().deleteTestraySuiteTestrayCase(testraySuiteId, testrayCase);
	}

	public static void deleteTestraySuiteTestrayCases(
		long testraySuiteId,
		java.util.List<com.liferay.osb.testray.model.TestrayCase>
			testrayCases) {

		getService().deleteTestraySuiteTestrayCases(
			testraySuiteId, testrayCases);
	}

	public static void deleteTestraySuiteTestrayCases(
		long testraySuiteId, long[] testrayCaseIds) {

		getService().deleteTestraySuiteTestrayCases(
			testraySuiteId, testrayCaseIds);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseModelImpl</code>.
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

	public static com.liferay.osb.testray.model.TestrayCase fetchTestrayCase(
		long testrayCaseId) {

		return getService().fetchTestrayCase(testrayCaseId);
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

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the testrayBuildIds of the testray builds associated with the testray case.
	 *
	 * @param testrayCaseId the testrayCaseId of the testray case
	 * @return long[] the testrayBuildIds of testray builds associated with the testray case
	 */
	public static long[] getTestrayBuildPrimaryKeys(long testrayCaseId) {
		return getService().getTestrayBuildPrimaryKeys(testrayCaseId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayBuildTestrayCases(long testrayBuildId) {

		return getService().getTestrayBuildTestrayCases(testrayBuildId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayBuildTestrayCases(long testrayBuildId, int start, int end) {

		return getService().getTestrayBuildTestrayCases(
			testrayBuildId, start, end);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayBuildTestrayCases(
			long testrayBuildId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayCase> orderByComparator) {

		return getService().getTestrayBuildTestrayCases(
			testrayBuildId, start, end, orderByComparator);
	}

	public static int getTestrayBuildTestrayCasesCount(long testrayBuildId) {
		return getService().getTestrayBuildTestrayCasesCount(testrayBuildId);
	}

	/**
	 * Returns the testray case with the primary key.
	 *
	 * @param testrayCaseId the primary key of the testray case
	 * @return the testray case
	 * @throws PortalException if a testray case with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestrayCase getTestrayCase(
			long testrayCaseId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTestrayCase(testrayCaseId);
	}

	/**
	 * Returns a range of all the testray cases.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray cases
	 * @param end the upper bound of the range of testray cases (not inclusive)
	 * @return the range of testray cases
	 */
	public static java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayCases(int start, int end) {

		return getService().getTestrayCases(start, end);
	}

	/**
	 * Returns the number of testray cases.
	 *
	 * @return the number of testray cases
	 */
	public static int getTestrayCasesCount() {
		return getService().getTestrayCasesCount();
	}

	/**
	 * Returns the testrayComponentIds of the testray components associated with the testray case.
	 *
	 * @param testrayCaseId the testrayCaseId of the testray case
	 * @return long[] the testrayComponentIds of testray components associated with the testray case
	 */
	public static long[] getTestrayComponentPrimaryKeys(long testrayCaseId) {
		return getService().getTestrayComponentPrimaryKeys(testrayCaseId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayComponentTestrayCases(long testrayComponentId) {

		return getService().getTestrayComponentTestrayCases(testrayComponentId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayComponentTestrayCases(
			long testrayComponentId, int start, int end) {

		return getService().getTestrayComponentTestrayCases(
			testrayComponentId, start, end);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayComponentTestrayCases(
			long testrayComponentId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayCase> orderByComparator) {

		return getService().getTestrayComponentTestrayCases(
			testrayComponentId, start, end, orderByComparator);
	}

	public static int getTestrayComponentTestrayCasesCount(
		long testrayComponentId) {

		return getService().getTestrayComponentTestrayCasesCount(
			testrayComponentId);
	}

	/**
	 * Returns the testrayRequirementIds of the testray requirements associated with the testray case.
	 *
	 * @param testrayCaseId the testrayCaseId of the testray case
	 * @return long[] the testrayRequirementIds of testray requirements associated with the testray case
	 */
	public static long[] getTestrayRequirementPrimaryKeys(long testrayCaseId) {
		return getService().getTestrayRequirementPrimaryKeys(testrayCaseId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayRequirementTestrayCases(long testrayRequirementId) {

		return getService().getTestrayRequirementTestrayCases(
			testrayRequirementId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayRequirementTestrayCases(
			long testrayRequirementId, int start, int end) {

		return getService().getTestrayRequirementTestrayCases(
			testrayRequirementId, start, end);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestrayRequirementTestrayCases(
			long testrayRequirementId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayCase> orderByComparator) {

		return getService().getTestrayRequirementTestrayCases(
			testrayRequirementId, start, end, orderByComparator);
	}

	public static int getTestrayRequirementTestrayCasesCount(
		long testrayRequirementId) {

		return getService().getTestrayRequirementTestrayCasesCount(
			testrayRequirementId);
	}

	/**
	 * Returns the testraySuiteIds of the testray suites associated with the testray case.
	 *
	 * @param testrayCaseId the testrayCaseId of the testray case
	 * @return long[] the testraySuiteIds of testray suites associated with the testray case
	 */
	public static long[] getTestraySuitePrimaryKeys(long testrayCaseId) {
		return getService().getTestraySuitePrimaryKeys(testrayCaseId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestraySuiteTestrayCases(long testraySuiteId) {

		return getService().getTestraySuiteTestrayCases(testraySuiteId);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestraySuiteTestrayCases(long testraySuiteId, int start, int end) {

		return getService().getTestraySuiteTestrayCases(
			testraySuiteId, start, end);
	}

	public static java.util.List<com.liferay.osb.testray.model.TestrayCase>
		getTestraySuiteTestrayCases(
			long testraySuiteId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.testray.model.TestrayCase> orderByComparator) {

		return getService().getTestraySuiteTestrayCases(
			testraySuiteId, start, end, orderByComparator);
	}

	public static int getTestraySuiteTestrayCasesCount(long testraySuiteId) {
		return getService().getTestraySuiteTestrayCasesCount(testraySuiteId);
	}

	public static boolean hasTestrayBuildTestrayCase(
		long testrayBuildId, long testrayCaseId) {

		return getService().hasTestrayBuildTestrayCase(
			testrayBuildId, testrayCaseId);
	}

	public static boolean hasTestrayBuildTestrayCases(long testrayBuildId) {
		return getService().hasTestrayBuildTestrayCases(testrayBuildId);
	}

	public static boolean hasTestrayComponentTestrayCase(
		long testrayComponentId, long testrayCaseId) {

		return getService().hasTestrayComponentTestrayCase(
			testrayComponentId, testrayCaseId);
	}

	public static boolean hasTestrayComponentTestrayCases(
		long testrayComponentId) {

		return getService().hasTestrayComponentTestrayCases(testrayComponentId);
	}

	public static boolean hasTestrayRequirementTestrayCase(
		long testrayRequirementId, long testrayCaseId) {

		return getService().hasTestrayRequirementTestrayCase(
			testrayRequirementId, testrayCaseId);
	}

	public static boolean hasTestrayRequirementTestrayCases(
		long testrayRequirementId) {

		return getService().hasTestrayRequirementTestrayCases(
			testrayRequirementId);
	}

	public static boolean hasTestraySuiteTestrayCase(
		long testraySuiteId, long testrayCaseId) {

		return getService().hasTestraySuiteTestrayCase(
			testraySuiteId, testrayCaseId);
	}

	public static boolean hasTestraySuiteTestrayCases(long testraySuiteId) {
		return getService().hasTestraySuiteTestrayCases(testraySuiteId);
	}

	public static void setTestrayBuildTestrayCases(
		long testrayBuildId, long[] testrayCaseIds) {

		getService().setTestrayBuildTestrayCases(
			testrayBuildId, testrayCaseIds);
	}

	public static void setTestrayComponentTestrayCases(
		long testrayComponentId, long[] testrayCaseIds) {

		getService().setTestrayComponentTestrayCases(
			testrayComponentId, testrayCaseIds);
	}

	public static void setTestrayRequirementTestrayCases(
		long testrayRequirementId, long[] testrayCaseIds) {

		getService().setTestrayRequirementTestrayCases(
			testrayRequirementId, testrayCaseIds);
	}

	public static void setTestraySuiteTestrayCases(
		long testraySuiteId, long[] testrayCaseIds) {

		getService().setTestraySuiteTestrayCases(
			testraySuiteId, testrayCaseIds);
	}

	/**
	 * Updates the testray case in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCase the testray case
	 * @return the testray case that was updated
	 */
	public static com.liferay.osb.testray.model.TestrayCase updateTestrayCase(
		com.liferay.osb.testray.model.TestrayCase testrayCase) {

		return getService().updateTestrayCase(testrayCase);
	}

	public static TestrayCaseLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestrayCaseLocalService, TestrayCaseLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TestrayCaseLocalService.class);

		ServiceTracker<TestrayCaseLocalService, TestrayCaseLocalService>
			serviceTracker =
				new ServiceTracker
					<TestrayCaseLocalService, TestrayCaseLocalService>(
						bundle.getBundleContext(),
						TestrayCaseLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}