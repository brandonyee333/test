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
 * Provides a wrapper for {@link TestraySuiteLocalService}.
 *
 * @author Ethan Bustad
 * @see TestraySuiteLocalService
 * @generated
 */
@ProviderType
public class TestraySuiteLocalServiceWrapper implements TestraySuiteLocalService,
	ServiceWrapper<TestraySuiteLocalService> {
	public TestraySuiteLocalServiceWrapper(
		TestraySuiteLocalService testraySuiteLocalService) {
		_testraySuiteLocalService = testraySuiteLocalService;
	}

	@Override
	public boolean hasTestrayCaseTestraySuite(long testrayCaseId,
		long testraySuiteId) {
		return _testraySuiteLocalService.hasTestrayCaseTestraySuite(testrayCaseId,
			testraySuiteId);
	}

	@Override
	public boolean hasTestrayCaseTestraySuites(long testrayCaseId) {
		return _testraySuiteLocalService.hasTestrayCaseTestraySuites(testrayCaseId);
	}

	/**
	* Adds the testray suite to the database. Also notifies the appropriate model listeners.
	*
	* @param testraySuite the testray suite
	* @return the testray suite that was added
	*/
	@Override
	public com.liferay.osb.testray.model.TestraySuite addTestraySuite(
		com.liferay.osb.testray.model.TestraySuite testraySuite) {
		return _testraySuiteLocalService.addTestraySuite(testraySuite);
	}

	/**
	* Creates a new testray suite with the primary key. Does not add the testray suite to the database.
	*
	* @param testraySuiteId the primary key for the new testray suite
	* @return the new testray suite
	*/
	@Override
	public com.liferay.osb.testray.model.TestraySuite createTestraySuite(
		long testraySuiteId) {
		return _testraySuiteLocalService.createTestraySuite(testraySuiteId);
	}

	/**
	* Deletes the testray suite from the database. Also notifies the appropriate model listeners.
	*
	* @param testraySuite the testray suite
	* @return the testray suite that was removed
	*/
	@Override
	public com.liferay.osb.testray.model.TestraySuite deleteTestraySuite(
		com.liferay.osb.testray.model.TestraySuite testraySuite) {
		return _testraySuiteLocalService.deleteTestraySuite(testraySuite);
	}

	/**
	* Deletes the testray suite with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param testraySuiteId the primary key of the testray suite
	* @return the testray suite that was removed
	* @throws PortalException if a testray suite with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.testray.model.TestraySuite deleteTestraySuite(
		long testraySuiteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testraySuiteLocalService.deleteTestraySuite(testraySuiteId);
	}

	@Override
	public com.liferay.osb.testray.model.TestraySuite fetchTestraySuite(
		long testraySuiteId) {
		return _testraySuiteLocalService.fetchTestraySuite(testraySuiteId);
	}

	/**
	* Returns the testray suite with the primary key.
	*
	* @param testraySuiteId the primary key of the testray suite
	* @return the testray suite
	* @throws PortalException if a testray suite with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.testray.model.TestraySuite getTestraySuite(
		long testraySuiteId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testraySuiteLocalService.getTestraySuite(testraySuiteId);
	}

	/**
	* Updates the testray suite in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param testraySuite the testray suite
	* @return the testray suite that was updated
	*/
	@Override
	public com.liferay.osb.testray.model.TestraySuite updateTestraySuite(
		com.liferay.osb.testray.model.TestraySuite testraySuite) {
		return _testraySuiteLocalService.updateTestraySuite(testraySuite);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _testraySuiteLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testraySuiteLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _testraySuiteLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testraySuiteLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _testraySuiteLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public int getTestrayCaseTestraySuitesCount(long testrayCaseId) {
		return _testraySuiteLocalService.getTestrayCaseTestraySuitesCount(testrayCaseId);
	}

	/**
	* Returns the number of testray suites.
	*
	* @return the number of testray suites
	*/
	@Override
	public int getTestraySuitesCount() {
		return _testraySuiteLocalService.getTestraySuitesCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _testraySuiteLocalService.getOSGiServiceIdentifier();
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
		return _testraySuiteLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestraySuiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _testraySuiteLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestraySuiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _testraySuiteLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestraySuite> getTestrayCaseTestraySuites(
		long testrayCaseId) {
		return _testraySuiteLocalService.getTestrayCaseTestraySuites(testrayCaseId);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestraySuite> getTestrayCaseTestraySuites(
		long testrayCaseId, int start, int end) {
		return _testraySuiteLocalService.getTestrayCaseTestraySuites(testrayCaseId,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.testray.model.TestraySuite> getTestrayCaseTestraySuites(
		long testrayCaseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.testray.model.TestraySuite> orderByComparator) {
		return _testraySuiteLocalService.getTestrayCaseTestraySuites(testrayCaseId,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the testray suites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.testray.model.impl.TestraySuiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of testray suites
	* @param end the upper bound of the range of testray suites (not inclusive)
	* @return the range of testray suites
	*/
	@Override
	public java.util.List<com.liferay.osb.testray.model.TestraySuite> getTestraySuites(
		int start, int end) {
		return _testraySuiteLocalService.getTestraySuites(start, end);
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
		return _testraySuiteLocalService.dynamicQueryCount(dynamicQuery);
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
		return _testraySuiteLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	/**
	* Returns the testrayCaseIds of the testray cases associated with the testray suite.
	*
	* @param testraySuiteId the testraySuiteId of the testray suite
	* @return long[] the testrayCaseIds of testray cases associated with the testray suite
	*/
	@Override
	public long[] getTestrayCasePrimaryKeys(long testraySuiteId) {
		return _testraySuiteLocalService.getTestrayCasePrimaryKeys(testraySuiteId);
	}

	@Override
	public void addTestrayCaseTestraySuite(long testrayCaseId,
		com.liferay.osb.testray.model.TestraySuite testraySuite) {
		_testraySuiteLocalService.addTestrayCaseTestraySuite(testrayCaseId,
			testraySuite);
	}

	@Override
	public void addTestrayCaseTestraySuite(long testrayCaseId,
		long testraySuiteId) {
		_testraySuiteLocalService.addTestrayCaseTestraySuite(testrayCaseId,
			testraySuiteId);
	}

	@Override
	public void addTestrayCaseTestraySuites(long testrayCaseId,
		java.util.List<com.liferay.osb.testray.model.TestraySuite> testraySuites) {
		_testraySuiteLocalService.addTestrayCaseTestraySuites(testrayCaseId,
			testraySuites);
	}

	@Override
	public void addTestrayCaseTestraySuites(long testrayCaseId,
		long[] testraySuiteIds) {
		_testraySuiteLocalService.addTestrayCaseTestraySuites(testrayCaseId,
			testraySuiteIds);
	}

	@Override
	public void clearTestrayCaseTestraySuites(long testrayCaseId) {
		_testraySuiteLocalService.clearTestrayCaseTestraySuites(testrayCaseId);
	}

	@Override
	public void deleteTestrayCaseTestraySuite(long testrayCaseId,
		com.liferay.osb.testray.model.TestraySuite testraySuite) {
		_testraySuiteLocalService.deleteTestrayCaseTestraySuite(testrayCaseId,
			testraySuite);
	}

	@Override
	public void deleteTestrayCaseTestraySuite(long testrayCaseId,
		long testraySuiteId) {
		_testraySuiteLocalService.deleteTestrayCaseTestraySuite(testrayCaseId,
			testraySuiteId);
	}

	@Override
	public void deleteTestrayCaseTestraySuites(long testrayCaseId,
		java.util.List<com.liferay.osb.testray.model.TestraySuite> testraySuites) {
		_testraySuiteLocalService.deleteTestrayCaseTestraySuites(testrayCaseId,
			testraySuites);
	}

	@Override
	public void deleteTestrayCaseTestraySuites(long testrayCaseId,
		long[] testraySuiteIds) {
		_testraySuiteLocalService.deleteTestrayCaseTestraySuites(testrayCaseId,
			testraySuiteIds);
	}

	@Override
	public void setTestrayCaseTestraySuites(long testrayCaseId,
		long[] testraySuiteIds) {
		_testraySuiteLocalService.setTestrayCaseTestraySuites(testrayCaseId,
			testraySuiteIds);
	}

	@Override
	public TestraySuiteLocalService getWrappedService() {
		return _testraySuiteLocalService;
	}

	@Override
	public void setWrappedService(
		TestraySuiteLocalService testraySuiteLocalService) {
		_testraySuiteLocalService = testraySuiteLocalService;
	}

	private TestraySuiteLocalService _testraySuiteLocalService;
}