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
 * Provides a wrapper for {@link TestrayRoutineLocalService}.
 *
 * @author Ethan Bustad
 * @see TestrayRoutineLocalService
 * @generated
 */
@ProviderType
public class TestrayRoutineLocalServiceWrapper
	implements TestrayRoutineLocalService,
			   ServiceWrapper<TestrayRoutineLocalService> {

	public TestrayRoutineLocalServiceWrapper(
		TestrayRoutineLocalService testrayRoutineLocalService) {

		_testrayRoutineLocalService = testrayRoutineLocalService;
	}

	/**
	 * Adds the testray routine to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRoutine the testray routine
	 * @return the testray routine that was added
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayRoutine addTestrayRoutine(
		com.liferay.osb.testray.model.TestrayRoutine testrayRoutine) {

		return _testrayRoutineLocalService.addTestrayRoutine(testrayRoutine);
	}

	/**
	 * Creates a new testray routine with the primary key. Does not add the testray routine to the database.
	 *
	 * @param testrayRoutineId the primary key for the new testray routine
	 * @return the new testray routine
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayRoutine createTestrayRoutine(
		long testrayRoutineId) {

		return _testrayRoutineLocalService.createTestrayRoutine(
			testrayRoutineId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayRoutineLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the testray routine with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRoutineId the primary key of the testray routine
	 * @return the testray routine that was removed
	 * @throws PortalException if a testray routine with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayRoutine deleteTestrayRoutine(
			long testrayRoutineId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayRoutineLocalService.deleteTestrayRoutine(
			testrayRoutineId);
	}

	/**
	 * Deletes the testray routine from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRoutine the testray routine
	 * @return the testray routine that was removed
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayRoutine deleteTestrayRoutine(
		com.liferay.osb.testray.model.TestrayRoutine testrayRoutine) {

		return _testrayRoutineLocalService.deleteTestrayRoutine(testrayRoutine);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testrayRoutineLocalService.dynamicQuery();
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

		return _testrayRoutineLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayRoutineModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _testrayRoutineLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayRoutineModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _testrayRoutineLocalService.dynamicQuery(
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

		return _testrayRoutineLocalService.dynamicQueryCount(dynamicQuery);
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

		return _testrayRoutineLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.testray.model.TestrayRoutine fetchTestrayRoutine(
		long testrayRoutineId) {

		return _testrayRoutineLocalService.fetchTestrayRoutine(
			testrayRoutineId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _testrayRoutineLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _testrayRoutineLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _testrayRoutineLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayRoutineLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the testray routine with the primary key.
	 *
	 * @param testrayRoutineId the primary key of the testray routine
	 * @return the testray routine
	 * @throws PortalException if a testray routine with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayRoutine getTestrayRoutine(
			long testrayRoutineId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayRoutineLocalService.getTestrayRoutine(testrayRoutineId);
	}

	/**
	 * Returns a range of all the testray routines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayRoutineModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray routines
	 * @param end the upper bound of the range of testray routines (not inclusive)
	 * @return the range of testray routines
	 */
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayRoutine>
		getTestrayRoutines(int start, int end) {

		return _testrayRoutineLocalService.getTestrayRoutines(start, end);
	}

	/**
	 * Returns the number of testray routines.
	 *
	 * @return the number of testray routines
	 */
	@Override
	public int getTestrayRoutinesCount() {
		return _testrayRoutineLocalService.getTestrayRoutinesCount();
	}

	/**
	 * Updates the testray routine in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayRoutine the testray routine
	 * @return the testray routine that was updated
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayRoutine updateTestrayRoutine(
		com.liferay.osb.testray.model.TestrayRoutine testrayRoutine) {

		return _testrayRoutineLocalService.updateTestrayRoutine(testrayRoutine);
	}

	@Override
	public TestrayRoutineLocalService getWrappedService() {
		return _testrayRoutineLocalService;
	}

	@Override
	public void setWrappedService(
		TestrayRoutineLocalService testrayRoutineLocalService) {

		_testrayRoutineLocalService = testrayRoutineLocalService;
	}

	private TestrayRoutineLocalService _testrayRoutineLocalService;

}