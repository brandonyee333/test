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
 * Provides a wrapper for {@link WatsonReportAuditLocalService}.
 *
 * @author Steven Smith
 * @see WatsonReportAuditLocalService
 * @generated
 */
public class WatsonReportAuditLocalServiceWrapper
	implements ServiceWrapper<WatsonReportAuditLocalService>,
			   WatsonReportAuditLocalService {

	public WatsonReportAuditLocalServiceWrapper(
		WatsonReportAuditLocalService watsonReportAuditLocalService) {

		_watsonReportAuditLocalService = watsonReportAuditLocalService;
	}

	/**
	 * Adds the watson report audit to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonReportAudit the watson report audit
	 * @return the watson report audit that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonReportAudit addWatsonReportAudit(
		com.liferay.watson.model.WatsonReportAudit watsonReportAudit) {

		return _watsonReportAuditLocalService.addWatsonReportAudit(
			watsonReportAudit);
	}

	/**
	 * Creates a new watson report audit with the primary key. Does not add the watson report audit to the database.
	 *
	 * @param watsonReportAuditId the primary key for the new watson report audit
	 * @return the new watson report audit
	 */
	@Override
	public com.liferay.watson.model.WatsonReportAudit createWatsonReportAudit(
		long watsonReportAuditId) {

		return _watsonReportAuditLocalService.createWatsonReportAudit(
			watsonReportAuditId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonReportAuditLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the watson report audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonReportAuditId the primary key of the watson report audit
	 * @return the watson report audit that was removed
	 * @throws PortalException if a watson report audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonReportAudit deleteWatsonReportAudit(
			long watsonReportAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonReportAuditLocalService.deleteWatsonReportAudit(
			watsonReportAuditId);
	}

	/**
	 * Deletes the watson report audit from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonReportAudit the watson report audit
	 * @return the watson report audit that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonReportAudit deleteWatsonReportAudit(
		com.liferay.watson.model.WatsonReportAudit watsonReportAudit) {

		return _watsonReportAuditLocalService.deleteWatsonReportAudit(
			watsonReportAudit);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonReportAuditLocalService.dynamicQuery();
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

		return _watsonReportAuditLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonReportAuditModelImpl</code>.
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

		return _watsonReportAuditLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonReportAuditModelImpl</code>.
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

		return _watsonReportAuditLocalService.dynamicQuery(
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

		return _watsonReportAuditLocalService.dynamicQueryCount(dynamicQuery);
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

		return _watsonReportAuditLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonReportAudit fetchWatsonReportAudit(
		long watsonReportAuditId) {

		return _watsonReportAuditLocalService.fetchWatsonReportAudit(
			watsonReportAuditId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonReportAuditLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonReportAuditLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonReportAuditLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonReportAuditLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the watson report audit with the primary key.
	 *
	 * @param watsonReportAuditId the primary key of the watson report audit
	 * @return the watson report audit
	 * @throws PortalException if a watson report audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonReportAudit getWatsonReportAudit(
			long watsonReportAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonReportAuditLocalService.getWatsonReportAudit(
			watsonReportAuditId);
	}

	/**
	 * Returns a range of all the watson report audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonReportAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson report audits
	 * @param end the upper bound of the range of watson report audits (not inclusive)
	 * @return the range of watson report audits
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonReportAudit>
		getWatsonReportAudits(int start, int end) {

		return _watsonReportAuditLocalService.getWatsonReportAudits(start, end);
	}

	/**
	 * Returns the number of watson report audits.
	 *
	 * @return the number of watson report audits
	 */
	@Override
	public int getWatsonReportAuditsCount() {
		return _watsonReportAuditLocalService.getWatsonReportAuditsCount();
	}

	/**
	 * Updates the watson report audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonReportAudit the watson report audit
	 * @return the watson report audit that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonReportAudit updateWatsonReportAudit(
		com.liferay.watson.model.WatsonReportAudit watsonReportAudit) {

		return _watsonReportAuditLocalService.updateWatsonReportAudit(
			watsonReportAudit);
	}

	@Override
	public WatsonReportAuditLocalService getWrappedService() {
		return _watsonReportAuditLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonReportAuditLocalService watsonReportAuditLocalService) {

		_watsonReportAuditLocalService = watsonReportAuditLocalService;
	}

	private WatsonReportAuditLocalService _watsonReportAuditLocalService;

}