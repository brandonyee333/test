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
 * Provides a wrapper for {@link WatsonIncidentRelAuditLocalService}.
 *
 * @author Steven Smith
 * @see WatsonIncidentRelAuditLocalService
 * @generated
 */
public class WatsonIncidentRelAuditLocalServiceWrapper
	implements ServiceWrapper<WatsonIncidentRelAuditLocalService>,
			   WatsonIncidentRelAuditLocalService {

	public WatsonIncidentRelAuditLocalServiceWrapper(
		WatsonIncidentRelAuditLocalService watsonIncidentRelAuditLocalService) {

		_watsonIncidentRelAuditLocalService =
			watsonIncidentRelAuditLocalService;
	}

	/**
	 * Adds the watson incident rel audit to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRelAudit the watson incident rel audit
	 * @return the watson incident rel audit that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonIncidentRelAudit
		addWatsonIncidentRelAudit(
			com.liferay.watson.model.WatsonIncidentRelAudit
				watsonIncidentRelAudit) {

		return _watsonIncidentRelAuditLocalService.addWatsonIncidentRelAudit(
			watsonIncidentRelAudit);
	}

	/**
	 * Creates a new watson incident rel audit with the primary key. Does not add the watson incident rel audit to the database.
	 *
	 * @param watsonIncidentRelAuditId the primary key for the new watson incident rel audit
	 * @return the new watson incident rel audit
	 */
	@Override
	public com.liferay.watson.model.WatsonIncidentRelAudit
		createWatsonIncidentRelAudit(long watsonIncidentRelAuditId) {

		return _watsonIncidentRelAuditLocalService.createWatsonIncidentRelAudit(
			watsonIncidentRelAuditId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonIncidentRelAuditLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the watson incident rel audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRelAuditId the primary key of the watson incident rel audit
	 * @return the watson incident rel audit that was removed
	 * @throws PortalException if a watson incident rel audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonIncidentRelAudit
			deleteWatsonIncidentRelAudit(long watsonIncidentRelAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonIncidentRelAuditLocalService.deleteWatsonIncidentRelAudit(
			watsonIncidentRelAuditId);
	}

	/**
	 * Deletes the watson incident rel audit from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRelAudit the watson incident rel audit
	 * @return the watson incident rel audit that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonIncidentRelAudit
		deleteWatsonIncidentRelAudit(
			com.liferay.watson.model.WatsonIncidentRelAudit
				watsonIncidentRelAudit) {

		return _watsonIncidentRelAuditLocalService.deleteWatsonIncidentRelAudit(
			watsonIncidentRelAudit);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonIncidentRelAuditLocalService.dynamicQuery();
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

		return _watsonIncidentRelAuditLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonIncidentRelAuditModelImpl</code>.
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

		return _watsonIncidentRelAuditLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonIncidentRelAuditModelImpl</code>.
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

		return _watsonIncidentRelAuditLocalService.dynamicQuery(
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

		return _watsonIncidentRelAuditLocalService.dynamicQueryCount(
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

		return _watsonIncidentRelAuditLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonIncidentRelAudit
		fetchWatsonIncidentRelAudit(long watsonIncidentRelAuditId) {

		return _watsonIncidentRelAuditLocalService.fetchWatsonIncidentRelAudit(
			watsonIncidentRelAuditId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonIncidentRelAuditLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonIncidentRelAuditLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonIncidentRelAuditLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonIncidentRelAuditLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns the watson incident rel audit with the primary key.
	 *
	 * @param watsonIncidentRelAuditId the primary key of the watson incident rel audit
	 * @return the watson incident rel audit
	 * @throws PortalException if a watson incident rel audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonIncidentRelAudit
			getWatsonIncidentRelAudit(long watsonIncidentRelAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonIncidentRelAuditLocalService.getWatsonIncidentRelAudit(
			watsonIncidentRelAuditId);
	}

	/**
	 * Returns a range of all the watson incident rel audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonIncidentRelAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident rel audits
	 * @param end the upper bound of the range of watson incident rel audits (not inclusive)
	 * @return the range of watson incident rel audits
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonIncidentRelAudit>
		getWatsonIncidentRelAudits(int start, int end) {

		return _watsonIncidentRelAuditLocalService.getWatsonIncidentRelAudits(
			start, end);
	}

	/**
	 * Returns the number of watson incident rel audits.
	 *
	 * @return the number of watson incident rel audits
	 */
	@Override
	public int getWatsonIncidentRelAuditsCount() {
		return _watsonIncidentRelAuditLocalService.
			getWatsonIncidentRelAuditsCount();
	}

	/**
	 * Updates the watson incident rel audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRelAudit the watson incident rel audit
	 * @return the watson incident rel audit that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonIncidentRelAudit
		updateWatsonIncidentRelAudit(
			com.liferay.watson.model.WatsonIncidentRelAudit
				watsonIncidentRelAudit) {

		return _watsonIncidentRelAuditLocalService.updateWatsonIncidentRelAudit(
			watsonIncidentRelAudit);
	}

	@Override
	public WatsonIncidentRelAuditLocalService getWrappedService() {
		return _watsonIncidentRelAuditLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonIncidentRelAuditLocalService watsonIncidentRelAuditLocalService) {

		_watsonIncidentRelAuditLocalService =
			watsonIncidentRelAuditLocalService;
	}

	private WatsonIncidentRelAuditLocalService
		_watsonIncidentRelAuditLocalService;

}