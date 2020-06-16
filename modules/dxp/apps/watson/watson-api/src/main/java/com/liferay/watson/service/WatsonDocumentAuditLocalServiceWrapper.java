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
 * Provides a wrapper for {@link WatsonDocumentAuditLocalService}.
 *
 * @author Steven Smith
 * @see WatsonDocumentAuditLocalService
 * @generated
 */
public class WatsonDocumentAuditLocalServiceWrapper
	implements ServiceWrapper<WatsonDocumentAuditLocalService>,
			   WatsonDocumentAuditLocalService {

	public WatsonDocumentAuditLocalServiceWrapper(
		WatsonDocumentAuditLocalService watsonDocumentAuditLocalService) {

		_watsonDocumentAuditLocalService = watsonDocumentAuditLocalService;
	}

	/**
	 * Adds the watson document audit to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonDocumentAudit the watson document audit
	 * @return the watson document audit that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonDocumentAudit addWatsonDocumentAudit(
		com.liferay.watson.model.WatsonDocumentAudit watsonDocumentAudit) {

		return _watsonDocumentAuditLocalService.addWatsonDocumentAudit(
			watsonDocumentAudit);
	}

	/**
	 * Creates a new watson document audit with the primary key. Does not add the watson document audit to the database.
	 *
	 * @param watsonDocumentAuditId the primary key for the new watson document audit
	 * @return the new watson document audit
	 */
	@Override
	public com.liferay.watson.model.WatsonDocumentAudit
		createWatsonDocumentAudit(long watsonDocumentAuditId) {

		return _watsonDocumentAuditLocalService.createWatsonDocumentAudit(
			watsonDocumentAuditId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonDocumentAuditLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the watson document audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonDocumentAuditId the primary key of the watson document audit
	 * @return the watson document audit that was removed
	 * @throws PortalException if a watson document audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonDocumentAudit
			deleteWatsonDocumentAudit(long watsonDocumentAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonDocumentAuditLocalService.deleteWatsonDocumentAudit(
			watsonDocumentAuditId);
	}

	/**
	 * Deletes the watson document audit from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonDocumentAudit the watson document audit
	 * @return the watson document audit that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonDocumentAudit
		deleteWatsonDocumentAudit(
			com.liferay.watson.model.WatsonDocumentAudit watsonDocumentAudit) {

		return _watsonDocumentAuditLocalService.deleteWatsonDocumentAudit(
			watsonDocumentAudit);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonDocumentAuditLocalService.dynamicQuery();
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

		return _watsonDocumentAuditLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonDocumentAuditModelImpl</code>.
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

		return _watsonDocumentAuditLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonDocumentAuditModelImpl</code>.
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

		return _watsonDocumentAuditLocalService.dynamicQuery(
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

		return _watsonDocumentAuditLocalService.dynamicQueryCount(dynamicQuery);
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

		return _watsonDocumentAuditLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonDocumentAudit
		fetchWatsonDocumentAudit(long watsonDocumentAuditId) {

		return _watsonDocumentAuditLocalService.fetchWatsonDocumentAudit(
			watsonDocumentAuditId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonDocumentAuditLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonDocumentAuditLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonDocumentAuditLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonDocumentAuditLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns the watson document audit with the primary key.
	 *
	 * @param watsonDocumentAuditId the primary key of the watson document audit
	 * @return the watson document audit
	 * @throws PortalException if a watson document audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonDocumentAudit getWatsonDocumentAudit(
			long watsonDocumentAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonDocumentAuditLocalService.getWatsonDocumentAudit(
			watsonDocumentAuditId);
	}

	/**
	 * Returns a range of all the watson document audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonDocumentAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson document audits
	 * @param end the upper bound of the range of watson document audits (not inclusive)
	 * @return the range of watson document audits
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonDocumentAudit>
		getWatsonDocumentAudits(int start, int end) {

		return _watsonDocumentAuditLocalService.getWatsonDocumentAudits(
			start, end);
	}

	/**
	 * Returns the number of watson document audits.
	 *
	 * @return the number of watson document audits
	 */
	@Override
	public int getWatsonDocumentAuditsCount() {
		return _watsonDocumentAuditLocalService.getWatsonDocumentAuditsCount();
	}

	/**
	 * Updates the watson document audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonDocumentAudit the watson document audit
	 * @return the watson document audit that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonDocumentAudit
		updateWatsonDocumentAudit(
			com.liferay.watson.model.WatsonDocumentAudit watsonDocumentAudit) {

		return _watsonDocumentAuditLocalService.updateWatsonDocumentAudit(
			watsonDocumentAudit);
	}

	@Override
	public WatsonDocumentAuditLocalService getWrappedService() {
		return _watsonDocumentAuditLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonDocumentAuditLocalService watsonDocumentAuditLocalService) {

		_watsonDocumentAuditLocalService = watsonDocumentAuditLocalService;
	}

	private WatsonDocumentAuditLocalService _watsonDocumentAuditLocalService;

}