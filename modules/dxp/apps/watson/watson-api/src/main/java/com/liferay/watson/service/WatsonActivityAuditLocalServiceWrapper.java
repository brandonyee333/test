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
 * Provides a wrapper for {@link WatsonActivityAuditLocalService}.
 *
 * @author Steven Smith
 * @see WatsonActivityAuditLocalService
 * @generated
 */
public class WatsonActivityAuditLocalServiceWrapper
	implements ServiceWrapper<WatsonActivityAuditLocalService>,
			   WatsonActivityAuditLocalService {

	public WatsonActivityAuditLocalServiceWrapper(
		WatsonActivityAuditLocalService watsonActivityAuditLocalService) {

		_watsonActivityAuditLocalService = watsonActivityAuditLocalService;
	}

	/**
	 * Adds the watson activity audit to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonActivityAudit the watson activity audit
	 * @return the watson activity audit that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonActivityAudit addWatsonActivityAudit(
		com.liferay.watson.model.WatsonActivityAudit watsonActivityAudit) {

		return _watsonActivityAuditLocalService.addWatsonActivityAudit(
			watsonActivityAudit);
	}

	/**
	 * Creates a new watson activity audit with the primary key. Does not add the watson activity audit to the database.
	 *
	 * @param watsonActivityAuditId the primary key for the new watson activity audit
	 * @return the new watson activity audit
	 */
	@Override
	public com.liferay.watson.model.WatsonActivityAudit
		createWatsonActivityAudit(long watsonActivityAuditId) {

		return _watsonActivityAuditLocalService.createWatsonActivityAudit(
			watsonActivityAuditId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonActivityAuditLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the watson activity audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonActivityAuditId the primary key of the watson activity audit
	 * @return the watson activity audit that was removed
	 * @throws PortalException if a watson activity audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonActivityAudit
			deleteWatsonActivityAudit(long watsonActivityAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonActivityAuditLocalService.deleteWatsonActivityAudit(
			watsonActivityAuditId);
	}

	/**
	 * Deletes the watson activity audit from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonActivityAudit the watson activity audit
	 * @return the watson activity audit that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonActivityAudit
		deleteWatsonActivityAudit(
			com.liferay.watson.model.WatsonActivityAudit watsonActivityAudit) {

		return _watsonActivityAuditLocalService.deleteWatsonActivityAudit(
			watsonActivityAudit);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonActivityAuditLocalService.dynamicQuery();
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

		return _watsonActivityAuditLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonActivityAuditModelImpl</code>.
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

		return _watsonActivityAuditLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonActivityAuditModelImpl</code>.
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

		return _watsonActivityAuditLocalService.dynamicQuery(
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

		return _watsonActivityAuditLocalService.dynamicQueryCount(dynamicQuery);
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

		return _watsonActivityAuditLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonActivityAudit
		fetchWatsonActivityAudit(long watsonActivityAuditId) {

		return _watsonActivityAuditLocalService.fetchWatsonActivityAudit(
			watsonActivityAuditId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonActivityAuditLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonActivityAuditLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonActivityAuditLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonActivityAuditLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns the watson activity audit with the primary key.
	 *
	 * @param watsonActivityAuditId the primary key of the watson activity audit
	 * @return the watson activity audit
	 * @throws PortalException if a watson activity audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonActivityAudit getWatsonActivityAudit(
			long watsonActivityAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonActivityAuditLocalService.getWatsonActivityAudit(
			watsonActivityAuditId);
	}

	/**
	 * Returns a range of all the watson activity audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonActivityAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson activity audits
	 * @param end the upper bound of the range of watson activity audits (not inclusive)
	 * @return the range of watson activity audits
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonActivityAudit>
		getWatsonActivityAudits(int start, int end) {

		return _watsonActivityAuditLocalService.getWatsonActivityAudits(
			start, end);
	}

	/**
	 * Returns the number of watson activity audits.
	 *
	 * @return the number of watson activity audits
	 */
	@Override
	public int getWatsonActivityAuditsCount() {
		return _watsonActivityAuditLocalService.getWatsonActivityAuditsCount();
	}

	/**
	 * Updates the watson activity audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonActivityAudit the watson activity audit
	 * @return the watson activity audit that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonActivityAudit
		updateWatsonActivityAudit(
			com.liferay.watson.model.WatsonActivityAudit watsonActivityAudit) {

		return _watsonActivityAuditLocalService.updateWatsonActivityAudit(
			watsonActivityAudit);
	}

	@Override
	public WatsonActivityAuditLocalService getWrappedService() {
		return _watsonActivityAuditLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonActivityAuditLocalService watsonActivityAuditLocalService) {

		_watsonActivityAuditLocalService = watsonActivityAuditLocalService;
	}

	private WatsonActivityAuditLocalService _watsonActivityAuditLocalService;

}