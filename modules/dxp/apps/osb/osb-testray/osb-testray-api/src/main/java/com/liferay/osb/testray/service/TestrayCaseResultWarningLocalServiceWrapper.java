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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TestrayCaseResultWarningLocalService}.
 *
 * @author Ethan Bustad
 * @see TestrayCaseResultWarningLocalService
 * @generated
 */
public class TestrayCaseResultWarningLocalServiceWrapper
	implements ServiceWrapper<TestrayCaseResultWarningLocalService>,
			   TestrayCaseResultWarningLocalService {

	public TestrayCaseResultWarningLocalServiceWrapper(
		TestrayCaseResultWarningLocalService
			testrayCaseResultWarningLocalService) {

		_testrayCaseResultWarningLocalService =
			testrayCaseResultWarningLocalService;
	}

	/**
	 * Adds the testray case result warning to the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResultWarning the testray case result warning
	 * @return the testray case result warning that was added
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCaseResultWarning
		addTestrayCaseResultWarning(
			com.liferay.osb.testray.model.TestrayCaseResultWarning
				testrayCaseResultWarning) {

		return _testrayCaseResultWarningLocalService.
			addTestrayCaseResultWarning(testrayCaseResultWarning);
	}

	/**
	 * Creates a new testray case result warning with the primary key. Does not add the testray case result warning to the database.
	 *
	 * @param testrayCaseResultWarningId the primary key for the new testray case result warning
	 * @return the new testray case result warning
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCaseResultWarning
		createTestrayCaseResultWarning(long testrayCaseResultWarningId) {

		return _testrayCaseResultWarningLocalService.
			createTestrayCaseResultWarning(testrayCaseResultWarningId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayCaseResultWarningLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the testray case result warning with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResultWarningId the primary key of the testray case result warning
	 * @return the testray case result warning that was removed
	 * @throws PortalException if a testray case result warning with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCaseResultWarning
			deleteTestrayCaseResultWarning(long testrayCaseResultWarningId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayCaseResultWarningLocalService.
			deleteTestrayCaseResultWarning(testrayCaseResultWarningId);
	}

	/**
	 * Deletes the testray case result warning from the database. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResultWarning the testray case result warning
	 * @return the testray case result warning that was removed
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCaseResultWarning
		deleteTestrayCaseResultWarning(
			com.liferay.osb.testray.model.TestrayCaseResultWarning
				testrayCaseResultWarning) {

		return _testrayCaseResultWarningLocalService.
			deleteTestrayCaseResultWarning(testrayCaseResultWarning);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _testrayCaseResultWarningLocalService.dynamicQuery();
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

		return _testrayCaseResultWarningLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseResultWarningModelImpl</code>.
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

		return _testrayCaseResultWarningLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseResultWarningModelImpl</code>.
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

		return _testrayCaseResultWarningLocalService.dynamicQuery(
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

		return _testrayCaseResultWarningLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _testrayCaseResultWarningLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.testray.model.TestrayCaseResultWarning
		fetchTestrayCaseResultWarning(long testrayCaseResultWarningId) {

		return _testrayCaseResultWarningLocalService.
			fetchTestrayCaseResultWarning(testrayCaseResultWarningId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _testrayCaseResultWarningLocalService.
			getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _testrayCaseResultWarningLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _testrayCaseResultWarningLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayCaseResultWarningLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns the testray case result warning with the primary key.
	 *
	 * @param testrayCaseResultWarningId the primary key of the testray case result warning
	 * @return the testray case result warning
	 * @throws PortalException if a testray case result warning with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCaseResultWarning
			getTestrayCaseResultWarning(long testrayCaseResultWarningId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _testrayCaseResultWarningLocalService.
			getTestrayCaseResultWarning(testrayCaseResultWarningId);
	}

	/**
	 * Returns a range of all the testray case result warnings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.testray.model.impl.TestrayCaseResultWarningModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of testray case result warnings
	 * @param end the upper bound of the range of testray case result warnings (not inclusive)
	 * @return the range of testray case result warnings
	 */
	@Override
	public java.util.List
		<com.liferay.osb.testray.model.TestrayCaseResultWarning>
			getTestrayCaseResultWarnings(int start, int end) {

		return _testrayCaseResultWarningLocalService.
			getTestrayCaseResultWarnings(start, end);
	}

	/**
	 * Returns the number of testray case result warnings.
	 *
	 * @return the number of testray case result warnings
	 */
	@Override
	public int getTestrayCaseResultWarningsCount() {
		return _testrayCaseResultWarningLocalService.
			getTestrayCaseResultWarningsCount();
	}

	/**
	 * Updates the testray case result warning in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param testrayCaseResultWarning the testray case result warning
	 * @return the testray case result warning that was updated
	 */
	@Override
	public com.liferay.osb.testray.model.TestrayCaseResultWarning
		updateTestrayCaseResultWarning(
			com.liferay.osb.testray.model.TestrayCaseResultWarning
				testrayCaseResultWarning) {

		return _testrayCaseResultWarningLocalService.
			updateTestrayCaseResultWarning(testrayCaseResultWarning);
	}

	@Override
	public TestrayCaseResultWarningLocalService getWrappedService() {
		return _testrayCaseResultWarningLocalService;
	}

	@Override
	public void setWrappedService(
		TestrayCaseResultWarningLocalService
			testrayCaseResultWarningLocalService) {

		_testrayCaseResultWarningLocalService =
			testrayCaseResultWarningLocalService;
	}

	private TestrayCaseResultWarningLocalService
		_testrayCaseResultWarningLocalService;

}