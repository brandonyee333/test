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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TestrayAssignmentLocalService}.
 *
 * @author Ethan Bustad
 * @see TestrayAssignmentLocalService
 * @generated
 */
@ProviderType
public class TestrayAssignmentLocalServiceWrapper
	implements TestrayAssignmentLocalService,
		ServiceWrapper<TestrayAssignmentLocalService> {
	public TestrayAssignmentLocalServiceWrapper(
		TestrayAssignmentLocalService testrayAssignmentLocalService) {
		_testrayAssignmentLocalService = testrayAssignmentLocalService;
	}

	/**
	* Adds the testray assignment to the database. Also notifies the appropriate model listeners.
	*
	* @param testrayAssignment the testray assignment
	* @return the testray assignment that was added
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayAssignment addTestrayAssignment(
		com.liferay.osb.testray.model.TestrayAssignment testrayAssignment) {
		return _testrayAssignmentLocalService.addTestrayAssignment(testrayAssignment);
	}

	/**
	* Creates a new testray assignment with the primary key. Does not add the testray assignment to the database.
	*
	* @param testrayAssignmentId the primary key for the new testray assignment
	* @return the new testray assignment
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayAssignment createTestrayAssignment(
		long testrayAssignmentId) {
		return _testrayAssignmentLocalService.createTestrayAssignment(testrayAssignmentId);
	}

	/**
	* Deletes the testray assignment from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayAssignment the testray assignment
	* @return the testray assignment that was removed
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayAssignment deleteTestrayAssignment(
		com.liferay.osb.testray.model.TestrayAssignment testrayAssignment) {
		return _testrayAssignmentLocalService.deleteTestrayAssignment(testrayAssignment);
	}

	/**
	* Deletes the testray assignment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayAssignmentId the primary key of the testray assignment
	* @return the testray assignment that was removed
	* @throws PortalException if a testray assignment with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayAssignment deleteTestrayAssignment(
		long testrayAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayAssignmentLocalService.deleteTestrayAssignment(testrayAssignmentId);
	}

	@Override
	public com.liferay.osb.testray.model.TestrayAssignment fetchTestrayAssignment(
		long testrayAssignmentId) {
		return _testrayAssignmentLocalService.fetchTestrayAssignment(testrayAssignmentId);
	}

	/**
	* Returns the testray assignment with the primary key.
	*
	* @param testrayAssignmentId the primary key of the testray assignment
	* @return the testray assignment
	* @throws PortalException if a testray assignment with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayAssignment getTestrayAssignment(
		long testrayAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayAssignmentLocalService.getTestrayAssignment(testrayAssignmentId);
	}

	/**
	* Updates the testray assignment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param testrayAssignment the testray assignment
	* @return the testray assignment that was updated
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayAssignment updateTestrayAssignment(
		com.liferay.osb.testray.model.TestrayAssignment testrayAssignment) {
		return _testrayAssignmentLocalService.updateTestrayAssignment(testrayAssignment);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _testrayAssignmentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testrayAssignmentLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _testrayAssignmentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayAssignmentLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayAssignmentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of testray assignments.
	*
	* @return the number of testray assignments
	*/
	@Override
	public int getTestrayAssignmentsCount() {
		return _testrayAssignmentLocalService.getTestrayAssignmentsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _testrayAssignmentLocalService.getOSGiServiceIdentifier();
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
		return _testrayAssignmentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _testrayAssignmentLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _testrayAssignmentLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns a range of all the testray assignments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayAssignmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray assignments
	* @param end the upper bound of the range of testray assignments (not inclusive)
	* @return the range of testray assignments
	*/
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayAssignment> getTestrayAssignments(
		int start, int end) {
		return _testrayAssignmentLocalService.getTestrayAssignments(start, end);
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
		return _testrayAssignmentLocalService.dynamicQueryCount(dynamicQuery);
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
		return _testrayAssignmentLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public TestrayAssignmentLocalService getWrappedService() {
		return _testrayAssignmentLocalService;
	}

	@Override
	public void setWrappedService(
		TestrayAssignmentLocalService testrayAssignmentLocalService) {
		_testrayAssignmentLocalService = testrayAssignmentLocalService;
	}

	private TestrayAssignmentLocalService _testrayAssignmentLocalService;
}