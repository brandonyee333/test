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
 * Provides a wrapper for {@link WatsonChildAuditLocalService}.
 *
 * @author Steven Smith
 * @see WatsonChildAuditLocalService
 * @generated
 */
public class WatsonChildAuditLocalServiceWrapper
	implements ServiceWrapper<WatsonChildAuditLocalService>,
			   WatsonChildAuditLocalService {

	public WatsonChildAuditLocalServiceWrapper(
		WatsonChildAuditLocalService watsonChildAuditLocalService) {

		_watsonChildAuditLocalService = watsonChildAuditLocalService;
	}

	/**
	 * Adds the watson child audit to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonChildAudit the watson child audit
	 * @return the watson child audit that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonChildAudit addWatsonChildAudit(
		com.liferay.watson.model.WatsonChildAudit watsonChildAudit) {

		return _watsonChildAuditLocalService.addWatsonChildAudit(
			watsonChildAudit);
	}

	/**
	 * Creates a new watson child audit with the primary key. Does not add the watson child audit to the database.
	 *
	 * @param watsonChildAuditId the primary key for the new watson child audit
	 * @return the new watson child audit
	 */
	@Override
	public com.liferay.watson.model.WatsonChildAudit createWatsonChildAudit(
		long watsonChildAuditId) {

		return _watsonChildAuditLocalService.createWatsonChildAudit(
			watsonChildAuditId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonChildAuditLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the watson child audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonChildAuditId the primary key of the watson child audit
	 * @return the watson child audit that was removed
	 * @throws PortalException if a watson child audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonChildAudit deleteWatsonChildAudit(
			long watsonChildAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonChildAuditLocalService.deleteWatsonChildAudit(
			watsonChildAuditId);
	}

	/**
	 * Deletes the watson child audit from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonChildAudit the watson child audit
	 * @return the watson child audit that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonChildAudit deleteWatsonChildAudit(
		com.liferay.watson.model.WatsonChildAudit watsonChildAudit) {

		return _watsonChildAuditLocalService.deleteWatsonChildAudit(
			watsonChildAudit);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonChildAuditLocalService.dynamicQuery();
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

		return _watsonChildAuditLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonChildAuditModelImpl</code>.
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

		return _watsonChildAuditLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonChildAuditModelImpl</code>.
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

		return _watsonChildAuditLocalService.dynamicQuery(
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

		return _watsonChildAuditLocalService.dynamicQueryCount(dynamicQuery);
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

		return _watsonChildAuditLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonChildAudit fetchWatsonChildAudit(
		long watsonChildAuditId) {

		return _watsonChildAuditLocalService.fetchWatsonChildAudit(
			watsonChildAuditId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonChildAuditLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonChildAuditLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonChildAuditLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonChildAuditLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the watson child audit with the primary key.
	 *
	 * @param watsonChildAuditId the primary key of the watson child audit
	 * @return the watson child audit
	 * @throws PortalException if a watson child audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonChildAudit getWatsonChildAudit(
			long watsonChildAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonChildAuditLocalService.getWatsonChildAudit(
			watsonChildAuditId);
	}

	/**
	 * Returns a range of all the watson child audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonChildAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson child audits
	 * @param end the upper bound of the range of watson child audits (not inclusive)
	 * @return the range of watson child audits
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonChildAudit>
		getWatsonChildAudits(int start, int end) {

		return _watsonChildAuditLocalService.getWatsonChildAudits(start, end);
	}

	/**
	 * Returns the number of watson child audits.
	 *
	 * @return the number of watson child audits
	 */
	@Override
	public int getWatsonChildAuditsCount() {
		return _watsonChildAuditLocalService.getWatsonChildAuditsCount();
	}

	/**
	 * Updates the watson child audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonChildAudit the watson child audit
	 * @return the watson child audit that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonChildAudit updateWatsonChildAudit(
		com.liferay.watson.model.WatsonChildAudit watsonChildAudit) {

		return _watsonChildAuditLocalService.updateWatsonChildAudit(
			watsonChildAudit);
	}

	@Override
	public WatsonChildAuditLocalService getWrappedService() {
		return _watsonChildAuditLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonChildAuditLocalService watsonChildAuditLocalService) {

		_watsonChildAuditLocalService = watsonChildAuditLocalService;
	}

	private WatsonChildAuditLocalService _watsonChildAuditLocalService;

}