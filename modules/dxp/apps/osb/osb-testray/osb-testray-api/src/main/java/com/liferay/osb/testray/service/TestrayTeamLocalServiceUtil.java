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
 * Provides the local service utility for TestrayTeam. This utility wraps
 * <code>com.liferay.osb.testray.service.impl.TestrayTeamLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Ethan Bustad
 * @see TestrayTeamLocalService
 * @generated
 */
@ProviderType
public class TestrayTeamLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.testray.service.impl.TestrayTeamLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the testray team to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayTeam the testray team
	 * @return the testray team that was added
	 */
	public static com.liferay.osb.testray.model.TestrayTeam addTestrayTeam(
		com.liferay.osb.testray.model.TestrayTeam testrayTeam) {

		return getService().addTestrayTeam(testrayTeam);
	}

	/**
	 * Creates a new testray team with the primary key. Does not add the testray team to the database.
	 *
	 * @param testrayTeamId the primary key for the new testray team
	 * @return the new testray team
	 */
	public static com.liferay.osb.testray.model.TestrayTeam createTestrayTeam(
		long testrayTeamId) {

		return getService().createTestrayTeam(testrayTeamId);
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
	 * Deletes the testray team with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayTeamId the primary key of the testray team
	 * @return the testray team that was removed
	 * @throws PortalException if a testray team with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestrayTeam deleteTestrayTeam(
			long testrayTeamId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteTestrayTeam(testrayTeamId);
	}

	/**
	 * Deletes the testray team from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayTeam the testray team
	 * @return the testray team that was removed
	 */
	public static com.liferay.osb.testray.model.TestrayTeam deleteTestrayTeam(
		com.liferay.osb.testray.model.TestrayTeam testrayTeam) {

		return getService().deleteTestrayTeam(testrayTeam);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayTeamModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayTeamModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.liferay.osb.testray.model.TestrayTeam fetchTestrayTeam(
		long testrayTeamId) {

		return getService().fetchTestrayTeam(testrayTeamId);
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
	 * Returns the testray team with the primary key.
	 *
	 * @param testrayTeamId the primary key of the testray team
	 * @return the testray team
	 * @throws PortalException if a testray team with the primary key could not be found
	 */
	public static com.liferay.osb.testray.model.TestrayTeam getTestrayTeam(
			long testrayTeamId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getTestrayTeam(testrayTeamId);
	}

	/**
	 * Returns a range of all the testray teams.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayTeamModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray teams
	 * @param end the upper bound of the range of testray teams (not inclusive)
	 * @return the range of testray teams
	 */
	public static java.util.List<com.liferay.osb.testray.model.TestrayTeam>
		getTestrayTeams(int start, int end) {

		return getService().getTestrayTeams(start, end);
	}

	/**
	 * Returns the number of testray teams.
	 *
	 * @return the number of testray teams
	 */
	public static int getTestrayTeamsCount() {
		return getService().getTestrayTeamsCount();
	}

	/**
	 * Updates the testray team in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayTeam the testray team
	 * @return the testray team that was updated
	 */
	public static com.liferay.osb.testray.model.TestrayTeam updateTestrayTeam(
		com.liferay.osb.testray.model.TestrayTeam testrayTeam) {

		return getService().updateTestrayTeam(testrayTeam);
	}

	public static TestrayTeamLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<TestrayTeamLocalService, TestrayTeamLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(TestrayTeamLocalService.class);

		ServiceTracker<TestrayTeamLocalService, TestrayTeamLocalService>
			serviceTracker =
				new ServiceTracker
					<TestrayTeamLocalService, TestrayTeamLocalService>(
						bundle.getBundleContext(),
						TestrayTeamLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}