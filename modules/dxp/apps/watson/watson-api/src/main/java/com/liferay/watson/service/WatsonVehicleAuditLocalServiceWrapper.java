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

package com.liferay.watson.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WatsonVehicleAuditLocalService}.
 *
 * @author Steven Smith
 * @see WatsonVehicleAuditLocalService
 * @generated
 */
public class WatsonVehicleAuditLocalServiceWrapper
	implements ServiceWrapper<WatsonVehicleAuditLocalService>,
			   WatsonVehicleAuditLocalService {

	public WatsonVehicleAuditLocalServiceWrapper(
		WatsonVehicleAuditLocalService watsonVehicleAuditLocalService) {

		_watsonVehicleAuditLocalService = watsonVehicleAuditLocalService;
	}

	/**
	 * Adds the watson vehicle audit to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonVehicleAudit the watson vehicle audit
	 * @return the watson vehicle audit that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonVehicleAudit addWatsonVehicleAudit(
		com.liferay.watson.model.WatsonVehicleAudit watsonVehicleAudit) {

		return _watsonVehicleAuditLocalService.addWatsonVehicleAudit(
			watsonVehicleAudit);
	}

	/**
	 * Creates a new watson vehicle audit with the primary key. Does not add the watson vehicle audit to the database.
	 *
	 * @param watsonVehicleAuditId the primary key for the new watson vehicle audit
	 * @return the new watson vehicle audit
	 */
	@Override
	public com.liferay.watson.model.WatsonVehicleAudit createWatsonVehicleAudit(
		long watsonVehicleAuditId) {

		return _watsonVehicleAuditLocalService.createWatsonVehicleAudit(
			watsonVehicleAuditId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonVehicleAuditLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the watson vehicle audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonVehicleAuditId the primary key of the watson vehicle audit
	 * @return the watson vehicle audit that was removed
	 * @throws PortalException if a watson vehicle audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonVehicleAudit deleteWatsonVehicleAudit(
			long watsonVehicleAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonVehicleAuditLocalService.deleteWatsonVehicleAudit(
			watsonVehicleAuditId);
	}

	/**
	 * Deletes the watson vehicle audit from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonVehicleAudit the watson vehicle audit
	 * @return the watson vehicle audit that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonVehicleAudit deleteWatsonVehicleAudit(
		com.liferay.watson.model.WatsonVehicleAudit watsonVehicleAudit) {

		return _watsonVehicleAuditLocalService.deleteWatsonVehicleAudit(
			watsonVehicleAudit);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonVehicleAuditLocalService.dynamicQuery();
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

		return _watsonVehicleAuditLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonVehicleAuditModelImpl</code>.
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

		return _watsonVehicleAuditLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonVehicleAuditModelImpl</code>.
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

		return _watsonVehicleAuditLocalService.dynamicQuery(
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

		return _watsonVehicleAuditLocalService.dynamicQueryCount(dynamicQuery);
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

		return _watsonVehicleAuditLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonVehicleAudit fetchWatsonVehicleAudit(
		long watsonVehicleAuditId) {

		return _watsonVehicleAuditLocalService.fetchWatsonVehicleAudit(
			watsonVehicleAuditId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonVehicleAuditLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonVehicleAuditLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonVehicleAuditLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonVehicleAuditLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the watson vehicle audit with the primary key.
	 *
	 * @param watsonVehicleAuditId the primary key of the watson vehicle audit
	 * @return the watson vehicle audit
	 * @throws PortalException if a watson vehicle audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonVehicleAudit getWatsonVehicleAudit(
			long watsonVehicleAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonVehicleAuditLocalService.getWatsonVehicleAudit(
			watsonVehicleAuditId);
	}

	/**
	 * Returns a range of all the watson vehicle audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonVehicleAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson vehicle audits
	 * @param end the upper bound of the range of watson vehicle audits (not inclusive)
	 * @return the range of watson vehicle audits
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonVehicleAudit>
		getWatsonVehicleAudits(int start, int end) {

		return _watsonVehicleAuditLocalService.getWatsonVehicleAudits(
			start, end);
	}

	/**
	 * Returns the number of watson vehicle audits.
	 *
	 * @return the number of watson vehicle audits
	 */
	@Override
	public int getWatsonVehicleAuditsCount() {
		return _watsonVehicleAuditLocalService.getWatsonVehicleAuditsCount();
	}

	/**
	 * Updates the watson vehicle audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonVehicleAudit the watson vehicle audit
	 * @return the watson vehicle audit that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonVehicleAudit updateWatsonVehicleAudit(
		com.liferay.watson.model.WatsonVehicleAudit watsonVehicleAudit) {

		return _watsonVehicleAuditLocalService.updateWatsonVehicleAudit(
			watsonVehicleAudit);
	}

	@Override
	public WatsonVehicleAuditLocalService getWrappedService() {
		return _watsonVehicleAuditLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonVehicleAuditLocalService watsonVehicleAuditLocalService) {

		_watsonVehicleAuditLocalService = watsonVehicleAuditLocalService;
	}

	private WatsonVehicleAuditLocalService _watsonVehicleAuditLocalService;

}