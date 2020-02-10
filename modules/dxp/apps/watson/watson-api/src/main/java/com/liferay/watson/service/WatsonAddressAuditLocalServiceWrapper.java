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
 * Provides a wrapper for {@link WatsonAddressAuditLocalService}.
 *
 * @author Steven Smith
 * @see WatsonAddressAuditLocalService
 * @generated
 */
public class WatsonAddressAuditLocalServiceWrapper
	implements ServiceWrapper<WatsonAddressAuditLocalService>,
			   WatsonAddressAuditLocalService {

	public WatsonAddressAuditLocalServiceWrapper(
		WatsonAddressAuditLocalService watsonAddressAuditLocalService) {

		_watsonAddressAuditLocalService = watsonAddressAuditLocalService;
	}

	/**
	 * Adds the watson address audit to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonAddressAudit the watson address audit
	 * @return the watson address audit that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonAddressAudit addWatsonAddressAudit(
		com.liferay.watson.model.WatsonAddressAudit watsonAddressAudit) {

		return _watsonAddressAuditLocalService.addWatsonAddressAudit(
			watsonAddressAudit);
	}

	/**
	 * Creates a new watson address audit with the primary key. Does not add the watson address audit to the database.
	 *
	 * @param watsonAddressAuditId the primary key for the new watson address audit
	 * @return the new watson address audit
	 */
	@Override
	public com.liferay.watson.model.WatsonAddressAudit createWatsonAddressAudit(
		long watsonAddressAuditId) {

		return _watsonAddressAuditLocalService.createWatsonAddressAudit(
			watsonAddressAuditId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonAddressAuditLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the watson address audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonAddressAuditId the primary key of the watson address audit
	 * @return the watson address audit that was removed
	 * @throws PortalException if a watson address audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonAddressAudit deleteWatsonAddressAudit(
			long watsonAddressAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonAddressAuditLocalService.deleteWatsonAddressAudit(
			watsonAddressAuditId);
	}

	/**
	 * Deletes the watson address audit from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonAddressAudit the watson address audit
	 * @return the watson address audit that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonAddressAudit deleteWatsonAddressAudit(
		com.liferay.watson.model.WatsonAddressAudit watsonAddressAudit) {

		return _watsonAddressAuditLocalService.deleteWatsonAddressAudit(
			watsonAddressAudit);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonAddressAuditLocalService.dynamicQuery();
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

		return _watsonAddressAuditLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonAddressAuditModelImpl</code>.
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

		return _watsonAddressAuditLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonAddressAuditModelImpl</code>.
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

		return _watsonAddressAuditLocalService.dynamicQuery(
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

		return _watsonAddressAuditLocalService.dynamicQueryCount(dynamicQuery);
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

		return _watsonAddressAuditLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonAddressAudit fetchWatsonAddressAudit(
		long watsonAddressAuditId) {

		return _watsonAddressAuditLocalService.fetchWatsonAddressAudit(
			watsonAddressAuditId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonAddressAuditLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonAddressAuditLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonAddressAuditLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonAddressAuditLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the watson address audit with the primary key.
	 *
	 * @param watsonAddressAuditId the primary key of the watson address audit
	 * @return the watson address audit
	 * @throws PortalException if a watson address audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonAddressAudit getWatsonAddressAudit(
			long watsonAddressAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonAddressAuditLocalService.getWatsonAddressAudit(
			watsonAddressAuditId);
	}

	/**
	 * Returns a range of all the watson address audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonAddressAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson address audits
	 * @param end the upper bound of the range of watson address audits (not inclusive)
	 * @return the range of watson address audits
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonAddressAudit>
		getWatsonAddressAudits(int start, int end) {

		return _watsonAddressAuditLocalService.getWatsonAddressAudits(
			start, end);
	}

	/**
	 * Returns the number of watson address audits.
	 *
	 * @return the number of watson address audits
	 */
	@Override
	public int getWatsonAddressAuditsCount() {
		return _watsonAddressAuditLocalService.getWatsonAddressAuditsCount();
	}

	/**
	 * Updates the watson address audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonAddressAudit the watson address audit
	 * @return the watson address audit that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonAddressAudit updateWatsonAddressAudit(
		com.liferay.watson.model.WatsonAddressAudit watsonAddressAudit) {

		return _watsonAddressAuditLocalService.updateWatsonAddressAudit(
			watsonAddressAudit);
	}

	@Override
	public WatsonAddressAuditLocalService getWrappedService() {
		return _watsonAddressAuditLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonAddressAuditLocalService watsonAddressAuditLocalService) {

		_watsonAddressAuditLocalService = watsonAddressAuditLocalService;
	}

	private WatsonAddressAuditLocalService _watsonAddressAuditLocalService;

}