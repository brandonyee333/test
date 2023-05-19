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

import com.liferay.osb.testray.model.TestrayRequirement;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

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
		long testrayCaseId, TestrayRequirement testrayRequirement) {

		getService().addTestrayCaseTestrayRequirement(
			testrayCaseId, testrayRequirement);
	}

	public static void addTestrayCaseTestrayRequirements(
		long testrayCaseId, List<TestrayRequirement> testrayRequirements) {

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
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayRequirementLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayRequirement the testray requirement
	 * @return the testray requirement that was added
	 */
	public static TestrayRequirement addTestrayRequirement(
		TestrayRequirement testrayRequirement) {

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
	public static TestrayRequirement createTestrayRequirement(
		long testrayRequirementId) {

		return getService().createTestrayRequirement(testrayRequirementId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static void deleteTestrayCaseTestrayRequirement(
		long testrayCaseId, long testrayRequirementId) {

		getService().deleteTestrayCaseTestrayRequirement(
			testrayCaseId, testrayRequirementId);
	}

	public static void deleteTestrayCaseTestrayRequirement(
		long testrayCaseId, TestrayRequirement testrayRequirement) {

		getService().deleteTestrayCaseTestrayRequirement(
			testrayCaseId, testrayRequirement);
	}

	public static void deleteTestrayCaseTestrayRequirements(
		long testrayCaseId, List<TestrayRequirement> testrayRequirements) {

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
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayRequirementLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayRequirementId the primary key of the testray requirement
	 * @return the testray requirement that was removed
	 * @throws PortalException if a testray requirement with the primary key could not be found
	 */
	public static TestrayRequirement deleteTestrayRequirement(
			long testrayRequirementId)
		throws PortalException {

		return getService().deleteTestrayRequirement(testrayRequirementId);
	}

	/**
	 * Deletes the testray requirement from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayRequirementLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayRequirement the testray requirement
	 * @return the testray requirement that was removed
	 */
	public static TestrayRequirement deleteTestrayRequirement(
		TestrayRequirement testrayRequirement) {

		return getService().deleteTestrayRequirement(testrayRequirement);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayRequirementModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayRequirementModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
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
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static TestrayRequirement fetchTestrayRequirement(
		long testrayRequirementId) {

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

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

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

	public static List<TestrayRequirement> getTestrayCaseTestrayRequirements(
		long testrayCaseId) {

		return getService().getTestrayCaseTestrayRequirements(testrayCaseId);
	}

	public static List<TestrayRequirement> getTestrayCaseTestrayRequirements(
		long testrayCaseId, int start, int end) {

		return getService().getTestrayCaseTestrayRequirements(
			testrayCaseId, start, end);
	}

	public static List<TestrayRequirement> getTestrayCaseTestrayRequirements(
		long testrayCaseId, int start, int end,
		OrderByComparator<TestrayRequirement> orderByComparator) {

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
	public static TestrayRequirement getTestrayRequirement(
			long testrayRequirementId)
		throws PortalException {

		return getService().getTestrayRequirement(testrayRequirementId);
	}

	/**
	 * Returns a range of all the testray requirements.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayRequirementModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray requirements
	 * @param end the upper bound of the range of testray requirements (not inclusive)
	 * @return the range of testray requirements
	 */
	public static List<TestrayRequirement> getTestrayRequirements(
		int start, int end) {

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
	 * <p>
	 * <strong>Important:</strong> Inspect TestrayRequirementLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param testrayRequirement the testray requirement
	 * @return the testray requirement that was updated
	 */
	public static TestrayRequirement updateTestrayRequirement(
		TestrayRequirement testrayRequirement) {

		return getService().updateTestrayRequirement(testrayRequirement);
	}

	public static TestrayRequirementLocalService getService() {
		return _service;
	}

	public static void setService(TestrayRequirementLocalService service) {
		_service = service;
	}

	private static volatile TestrayRequirementLocalService _service;

}