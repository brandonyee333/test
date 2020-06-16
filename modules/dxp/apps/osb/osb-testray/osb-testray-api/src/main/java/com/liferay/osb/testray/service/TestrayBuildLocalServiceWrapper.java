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
 * Provides a wrapper for {@link TestrayBuildLocalService}.
 *
 * @author Ethan Bustad
 * @see TestrayBuildLocalService
 * @generated
 */
@ProviderType
public class TestrayBuildLocalServiceWrapper implements TestrayBuildLocalService,
	ServiceWrapper<TestrayBuildLocalService> {
	public TestrayBuildLocalServiceWrapper(
		TestrayBuildLocalService testrayBuildLocalService) {
		_testrayBuildLocalService = testrayBuildLocalService;
	}

	@Override
	public boolean hasTestrayCaseTestrayBuild(long testrayCaseId,
		long testrayBuildId) {
		return _testrayBuildLocalService.hasTestrayCaseTestrayBuild(testrayCaseId,
			testrayBuildId);
	}

	@Override
	public boolean hasTestrayCaseTestrayBuilds(long testrayCaseId) {
		return _testrayBuildLocalService.hasTestrayCaseTestrayBuilds(testrayCaseId);
	}

	/**
	* Adds the testray build to the database. Also notifies the appropriate model listeners.
	*
	* @param testrayBuild the testray build
	* @return the testray build that was added
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayBuild addTestrayBuild(
		com.liferay.osb.testray.model.TestrayBuild testrayBuild) {
		return _testrayBuildLocalService.addTestrayBuild(testrayBuild);
	}

	/**
	* Creates a new testray build with the primary key. Does not add the testray build to the database.
	*
	* @param testrayBuildId the primary key for the new testray build
	* @return the new testray build
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayBuild createTestrayBuild(
		long testrayBuildId) {
		return _testrayBuildLocalService.createTestrayBuild(testrayBuildId);
	}

	/**
	* Deletes the testray build from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayBuild the testray build
	* @return the testray build that was removed
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayBuild deleteTestrayBuild(
		com.liferay.osb.testray.model.TestrayBuild testrayBuild) {
		return _testrayBuildLocalService.deleteTestrayBuild(testrayBuild);
	}

	/**
	* Deletes the testray build with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testrayBuildId the primary key of the testray build
	* @return the testray build that was removed
	* @throws PortalException if a testray build with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayBuild deleteTestrayBuild(
		long testrayBuildId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayBuildLocalService.deleteTestrayBuild(testrayBuildId);
	}

	@Override
	public com.liferay.osb.testray.model.TestrayBuild fetchTestrayBuild(
		long testrayBuildId) {
		return _testrayBuildLocalService.fetchTestrayBuild(testrayBuildId);
	}

	/**
	* Returns the testray build with the primary key.
	*
	* @param testrayBuildId the primary key of the testray build
	* @return the testray build
	* @throws PortalException if a testray build with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayBuild getTestrayBuild(
		long testrayBuildId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayBuildLocalService.getTestrayBuild(testrayBuildId);
	}

	/**
	* Updates the testray build in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param testrayBuild the testray build
	* @return the testray build that was updated
	*/
	@Override
	public com.liferay.osb.testray.model.TestrayBuild updateTestrayBuild(
		com.liferay.osb.testray.model.TestrayBuild testrayBuild) {
		return _testrayBuildLocalService.updateTestrayBuild(testrayBuild);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _testrayBuildLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testrayBuildLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _testrayBuildLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayBuildLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testrayBuildLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of testray builds.
	*
	* @return the number of testray builds
	*/
	@Override
	public int getTestrayBuildsCount() {
		return _testrayBuildLocalService.getTestrayBuildsCount();
	}

	@Override
	public int getTestrayCaseTestrayBuildsCount(long testrayCaseId) {
		return _testrayBuildLocalService.getTestrayCaseTestrayBuildsCount(testrayCaseId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _testrayBuildLocalService.getOSGiServiceIdentifier();
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
		return _testrayBuildLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayBuildModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _testrayBuildLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayBuildModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _testrayBuildLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the testray builds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestrayBuildModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray builds
	* @param end the upper bound of the range of testray builds (not inclusive)
	* @return the range of testray builds
	*/
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayBuild> getTestrayBuilds(
		int start, int end) {
		return _testrayBuildLocalService.getTestrayBuilds(start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayBuild> getTestrayCaseTestrayBuilds(
		long testrayCaseId) {
		return _testrayBuildLocalService.getTestrayCaseTestrayBuilds(testrayCaseId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayBuild> getTestrayCaseTestrayBuilds(
		long testrayCaseId, int start, int end) {
		return _testrayBuildLocalService.getTestrayCaseTestrayBuilds(testrayCaseId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestrayBuild> getTestrayCaseTestrayBuilds(
		long testrayCaseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.testray.model.TestrayBuild> orderByComparator) {
		return _testrayBuildLocalService.getTestrayCaseTestrayBuilds(testrayCaseId,
			start, end, orderByComparator);
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
		return _testrayBuildLocalService.dynamicQueryCount(dynamicQuery);
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
		return _testrayBuildLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Returns the testrayCaseIds of the testray cases associated with the testray build.
	*
	* @param testrayBuildId the testrayBuildId of the testray build
	* @return long[] the testrayCaseIds of testray cases associated with the testray build
	*/
	@Override
	public long[] getTestrayCasePrimaryKeys(long testrayBuildId) {
		return _testrayBuildLocalService.getTestrayCasePrimaryKeys(testrayBuildId);
	}

	@Override
	public void addTestrayCaseTestrayBuild(long testrayCaseId,
		com.liferay.osb.testray.model.TestrayBuild testrayBuild) {
		_testrayBuildLocalService.addTestrayCaseTestrayBuild(testrayCaseId,
			testrayBuild);
	}

	@Override
	public void addTestrayCaseTestrayBuild(long testrayCaseId,
		long testrayBuildId) {
		_testrayBuildLocalService.addTestrayCaseTestrayBuild(testrayCaseId,
			testrayBuildId);
	}

	@Override
	public void addTestrayCaseTestrayBuilds(long testrayCaseId,
		java.util.List<com.liferay.osb.testray.model.TestrayBuild> testrayBuilds) {
		_testrayBuildLocalService.addTestrayCaseTestrayBuilds(testrayCaseId,
			testrayBuilds);
	}

	@Override
	public void addTestrayCaseTestrayBuilds(long testrayCaseId,
		long[] testrayBuildIds) {
		_testrayBuildLocalService.addTestrayCaseTestrayBuilds(testrayCaseId,
			testrayBuildIds);
	}

	@Override
	public void clearTestrayCaseTestrayBuilds(long testrayCaseId) {
		_testrayBuildLocalService.clearTestrayCaseTestrayBuilds(testrayCaseId);
	}

	@Override
	public void deleteTestrayCaseTestrayBuild(long testrayCaseId,
		com.liferay.osb.testray.model.TestrayBuild testrayBuild) {
		_testrayBuildLocalService.deleteTestrayCaseTestrayBuild(testrayCaseId,
			testrayBuild);
	}

	@Override
	public void deleteTestrayCaseTestrayBuild(long testrayCaseId,
		long testrayBuildId) {
		_testrayBuildLocalService.deleteTestrayCaseTestrayBuild(testrayCaseId,
			testrayBuildId);
	}

	@Override
	public void deleteTestrayCaseTestrayBuilds(long testrayCaseId,
		java.util.List<com.liferay.osb.testray.model.TestrayBuild> testrayBuilds) {
		_testrayBuildLocalService.deleteTestrayCaseTestrayBuilds(testrayCaseId,
			testrayBuilds);
	}

	@Override
	public void deleteTestrayCaseTestrayBuilds(long testrayCaseId,
		long[] testrayBuildIds) {
		_testrayBuildLocalService.deleteTestrayCaseTestrayBuilds(testrayCaseId,
			testrayBuildIds);
	}

	@Override
	public void setTestrayCaseTestrayBuilds(long testrayCaseId,
		long[] testrayBuildIds) {
		_testrayBuildLocalService.setTestrayCaseTestrayBuilds(testrayCaseId,
			testrayBuildIds);
	}

	@Override
	public TestrayBuildLocalService getWrappedService() {
		return _testrayBuildLocalService;
	}

	@Override
	public void setWrappedService(
		TestrayBuildLocalService testrayBuildLocalService) {
		_testrayBuildLocalService = testrayBuildLocalService;
	}

	private TestrayBuildLocalService _testrayBuildLocalService;
}