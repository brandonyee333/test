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
 * Provides a wrapper for {@link WatsonPersonAuditLocalService}.
 *
 * @author Steven Smith
 * @see WatsonPersonAuditLocalService
 * @generated
 */
public class WatsonPersonAuditLocalServiceWrapper
	implements ServiceWrapper<WatsonPersonAuditLocalService>,
			   WatsonPersonAuditLocalService {

	public WatsonPersonAuditLocalServiceWrapper(
		WatsonPersonAuditLocalService watsonPersonAuditLocalService) {

		_watsonPersonAuditLocalService = watsonPersonAuditLocalService;
	}

	/**
	 * Adds the watson person audit to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonPersonAudit the watson person audit
	 * @return the watson person audit that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonPersonAudit addWatsonPersonAudit(
		com.liferay.watson.model.WatsonPersonAudit watsonPersonAudit) {

		return _watsonPersonAuditLocalService.addWatsonPersonAudit(
			watsonPersonAudit);
	}

	/**
	 * Creates a new watson person audit with the primary key. Does not add the watson person audit to the database.
	 *
	 * @param watsonPersonAuditId the primary key for the new watson person audit
	 * @return the new watson person audit
	 */
	@Override
	public com.liferay.watson.model.WatsonPersonAudit createWatsonPersonAudit(
		long watsonPersonAuditId) {

		return _watsonPersonAuditLocalService.createWatsonPersonAudit(
			watsonPersonAuditId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonPersonAuditLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the watson person audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonPersonAuditId the primary key of the watson person audit
	 * @return the watson person audit that was removed
	 * @throws PortalException if a watson person audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonPersonAudit deleteWatsonPersonAudit(
			long watsonPersonAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonPersonAuditLocalService.deleteWatsonPersonAudit(
			watsonPersonAuditId);
	}

	/**
	 * Deletes the watson person audit from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonPersonAudit the watson person audit
	 * @return the watson person audit that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonPersonAudit deleteWatsonPersonAudit(
		com.liferay.watson.model.WatsonPersonAudit watsonPersonAudit) {

		return _watsonPersonAuditLocalService.deleteWatsonPersonAudit(
			watsonPersonAudit);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonPersonAuditLocalService.dynamicQuery();
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

		return _watsonPersonAuditLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonPersonAuditModelImpl</code>.
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

		return _watsonPersonAuditLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonPersonAuditModelImpl</code>.
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

		return _watsonPersonAuditLocalService.dynamicQuery(
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

		return _watsonPersonAuditLocalService.dynamicQueryCount(dynamicQuery);
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

		return _watsonPersonAuditLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonPersonAudit fetchWatsonPersonAudit(
		long watsonPersonAuditId) {

		return _watsonPersonAuditLocalService.fetchWatsonPersonAudit(
			watsonPersonAuditId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonPersonAuditLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonPersonAuditLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonPersonAuditLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonPersonAuditLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the watson person audit with the primary key.
	 *
	 * @param watsonPersonAuditId the primary key of the watson person audit
	 * @return the watson person audit
	 * @throws PortalException if a watson person audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonPersonAudit getWatsonPersonAudit(
			long watsonPersonAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonPersonAuditLocalService.getWatsonPersonAudit(
			watsonPersonAuditId);
	}

	/**
	 * Returns a range of all the watson person audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonPersonAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson person audits
	 * @param end the upper bound of the range of watson person audits (not inclusive)
	 * @return the range of watson person audits
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonPersonAudit>
		getWatsonPersonAudits(int start, int end) {

		return _watsonPersonAuditLocalService.getWatsonPersonAudits(start, end);
	}

	/**
	 * Returns the number of watson person audits.
	 *
	 * @return the number of watson person audits
	 */
	@Override
	public int getWatsonPersonAuditsCount() {
		return _watsonPersonAuditLocalService.getWatsonPersonAuditsCount();
	}

	/**
	 * Updates the watson person audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonPersonAudit the watson person audit
	 * @return the watson person audit that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonPersonAudit updateWatsonPersonAudit(
		com.liferay.watson.model.WatsonPersonAudit watsonPersonAudit) {

		return _watsonPersonAuditLocalService.updateWatsonPersonAudit(
			watsonPersonAudit);
	}

	@Override
	public WatsonPersonAuditLocalService getWrappedService() {
		return _watsonPersonAuditLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonPersonAuditLocalService watsonPersonAuditLocalService) {

		_watsonPersonAuditLocalService = watsonPersonAuditLocalService;
	}

	private WatsonPersonAuditLocalService _watsonPersonAuditLocalService;

}