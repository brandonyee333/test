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
 * Provides the local service utility for TestrayRequirement. This utility wraps
 * <code>com.liferay.osb.testray.service.impl.TestrayRequirementLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestrayRequirementLocalService
 * @generated
 */
@ProviderType
public class TestrayRequirementLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.testray.service.impl.TestrayRequirementLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static void addTestrayCaseTestrayRequirement(
		long testrayCaseId, long testrayRequirementId) {

		getService().addTestrayCaseTestrayRequirement(
			testrayCaseId, testrayRequirementId);
	}

	public static void addTestrayCaseTestrayRequirement(
		long testrayCaseId,
		com.liferay.osb.testray.model.TestrayRequirement testrayRequirement) {

		getService().addTestrayCaseTestrayRequirement(
			testrayCaseId, testrayRequirement);
	}

	public static void addTestrayCaseTestrayRequirements(
		long testrayCaseId,
		java.util.List<com.liferay.osb.testray.model.TestrayRequirement>
			testrayRequirements) {

		getService().addTestrayCaseTestrayRequirements(
			testrayCaseId, testrayRequirements);
	}

	public static void addTestrayCaseTestrayRequirements(
		long testrayCaseId, long[] testrayRequirementIds) {

		getService().addTestrayCaseTestrayRequirements(
			testrayCaseId, testrayRequirementIds);
	}

	/**
	 * Adds the testray requirement to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRequirement the testray requirement
	 * @return the testray requirement that was added
	 */
	public static com.liferay.osb.testray.model.TestrayRequirement
		addTestrayRequirement(
			com.liferay.osb.testray.model.TestrayRequirement
				testrayRequirement) {

		return getService().addTestrayRequirement(testrayRequirement);
	}

	public static void clearTestrayCaseTestrayRequirements(long testrayCaseId) {
		getService().clearTestrayCaseTestrayRequirements(testrayCaseId);
	}

	/**
	 * Creates a new testray requirement with the primary key. Does not add the testray requirement to the database.
	 *
	 * @param testrayRequirementId the primary key for the new testray requirement
	 * @return the new testray requirement
	 */
	public static com.liferay.osb.testray.model.TestrayRequirement
		createTestrayRequirement(long testrayRequirementId) {

		return getService().createTestrayRequirement(testrayRequirementId);
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

	public static void deleteTestrayCaseTestrayRequirement(
		long testrayCaseId, long testrayRequirementId) {

		getService().deleteTestrayCaseTestrayRequirement(
			testrayCaseId, testrayRequirementId);
	}

	public static void deleteTestrayCaseTestrayRequirement(
		long testrayCaseId,
		com.liferay.osb.testray.model.TestrayRequirement testrayRequirement) {

		getService().deleteTestrayCaseTestrayRequirement(
			testrayCaseId, testrayRequirement);
	}

	public static void deleteTestrayCaseTestrayRequirements(
		long testrayCaseId,
		java.util.List<com.liferay.osb.testray.model.TestrayRequirement>
			testrayRequirements) {

		getService().deleteTestrayCaseTestrayRequirements(
			testrayCaseId, testrayRequirements);
	}

	public static void deleteTestrayCaseTestrayRequirements(
		long testrayCaseId, long[] testrayRequirementIds) {

		getService().deleteTestrayCaseTestrayRequirements(
			testrayCaseId, testrayRequirementIds);
	}

	/**
	 * Deletes the testray requirement with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRequirementId the primary key of the testray requirement
	 * @return the testray requirement that was removed
	 * @throws PortalException if a testray requirement with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestrayRequirement
			deleteTestrayRequirement(long testrayRequirementId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteTestrayRequirement(testrayRequirementId);
	}

	/**
	 * Deletes the testray requirement from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRequirement the testray requirement
	 * @return the testray requirement that was removed
	 */
	public static com.liferay.osb.testray.model.TestrayRequirement
		deleteTestrayRequirement(
			com.liferay.osb.testray.model.TestrayRequirement
				testrayRequirement) {

		return getService().deleteTestrayRequirement(testrayRequirement);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayRequirementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayRequirementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.osb.testray.model.TestrayRequirement
		fetchTestrayRequirement(long testrayRequirementId) {

		return getService().fetchTestrayRequirement(testrayRequirementId);
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
	 * Returns the testrayCaseIds of the testray cases associated with the testray requirement.
	 *
	 * @param testrayRequirementId the testrayRequirementId of the testray requirement
	 * @return long[] the testrayCaseIds of testray cases associated with the testray requirement
	 */
	public static long[] getTestrayCasePrimaryKeys(long testrayRequirementId) {
		return getService().getTestrayCasePrimaryKeys(testrayRequirementId);
	}

	public static java.util.List
		<com.liferay.osb.testray.model.TestrayRequirement>
			getTestrayCaseTestrayRequirements(long testrayCaseId) {

		return getService().getTestrayCaseTestrayRequirements(testrayCaseId);
	}

	public static java.util.List
		<com.liferay.osb.testray.model.TestrayRequirement>
			getTestrayCaseTestrayRequirements(
				long testrayCaseId, int start, int end) {

		return getService().getTestrayCaseTestrayRequirements(
			testrayCaseId, start, end);
	}

	public static java.util.List
		<com.liferay.osb.testray.model.TestrayRequirement>
			getTestrayCaseTestrayRequirements(
				long testrayCaseId, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.osb.testray.model.TestrayRequirement>
						orderByComparator) {

		return getService().getTestrayCaseTestrayRequirements(
			testrayCaseId, start, end, orderByComparator);
	}

	public static int getTestrayCaseTestrayRequirementsCount(
		long testrayCaseId) {

		return getService().getTestrayCaseTestrayRequirementsCount(
			testrayCaseId);
	}

	/**
	 * Returns the testray requirement with the primary key.
	 *
	 * @param testrayRequirementId the primary key of the testray requirement
	 * @return the testray requirement
	 * @throws PortalException if a testray requirement with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestrayRequirement
			getTestrayRequirement(long testrayRequirementId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTestrayRequirement(testrayRequirementId);
	}

	/**
	 * Returns a range of all the testray requirements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayRequirementModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray requirements
	 * @param end the upper bound of the range of testray requirements (not inclusive)
	 * @return the range of testray requirements
	 */
	public static java.util.List
		<com.liferay.osb.testray.model.TestrayRequirement>
			getTestrayRequirements(int start, int end) {

		return getService().getTestrayRequirements(start, end);
	}

	/**
	 * Returns the number of testray requirements.
	 *
	 * @return the number of testray requirements
	 */
	public static int getTestrayRequirementsCount() {
		return getService().getTestrayRequirementsCount();
	}

	public static boolean hasTestrayCaseTestrayRequirement(
		long testrayCaseId, long testrayRequirementId) {

		return getService().hasTestrayCaseTestrayRequirement(
			testrayCaseId, testrayRequirementId);
	}

	public static boolean hasTestrayCaseTestrayRequirements(
		long testrayCaseId) {

		return getService().hasTestrayCaseTestrayRequirements(testrayCaseId);
	}

	public static void setTestrayCaseTestrayRequirements(
		long testrayCaseId, long[] testrayRequirementIds) {

		getService().setTestrayCaseTestrayRequirements(
			testrayCaseId, testrayRequirementIds);
	}

	/**
	 * Updates the testray requirement in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRequirement the testray requirement
	 * @return the testray requirement that was updated
	 */
	public static com.liferay.osb.testray.model.TestrayRequirement
		updateTestrayRequirement(
			com.liferay.osb.testray.model.TestrayRequirement
				testrayRequirement) {

		return getService().updateTestrayRequirement(testrayRequirement);
	}

	public static TestrayRequirementLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestrayRequirementLocalService, TestrayRequirementLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			TestrayRequirementLocalService.class);

		ServiceTracker
			<TestrayRequirementLocalService, TestrayRequirementLocalService>
				serviceTracker =
					new ServiceTracker
						<TestrayRequirementLocalService,
						 TestrayRequirementLocalService>(
							 bundle.getBundleContext(),
							 TestrayRequirementLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}