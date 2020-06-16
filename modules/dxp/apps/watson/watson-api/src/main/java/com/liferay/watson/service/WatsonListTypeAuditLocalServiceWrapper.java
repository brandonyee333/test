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
 * Provides a wrapper for {@link WatsonListTypeAuditLocalService}.
 *
 * @author Steven Smith
 * @see WatsonListTypeAuditLocalService
 * @generated
 */
public class WatsonListTypeAuditLocalServiceWrapper
	implements ServiceWrapper<WatsonListTypeAuditLocalService>,
			   WatsonListTypeAuditLocalService {

	public WatsonListTypeAuditLocalServiceWrapper(
		WatsonListTypeAuditLocalService watsonListTypeAuditLocalService) {

		_watsonListTypeAuditLocalService = watsonListTypeAuditLocalService;
	}

	/**
	 * Adds the watson list type audit to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeAudit the watson list type audit
	 * @return the watson list type audit that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonListTypeAudit addWatsonListTypeAudit(
		com.liferay.watson.model.WatsonListTypeAudit watsonListTypeAudit) {

		return _watsonListTypeAuditLocalService.addWatsonListTypeAudit(
			watsonListTypeAudit);
	}

	/**
	 * Creates a new watson list type audit with the primary key. Does not add the watson list type audit to the database.
	 *
	 * @param watsonListTypeAuditId the primary key for the new watson list type audit
	 * @return the new watson list type audit
	 */
	@Override
	public com.liferay.watson.model.WatsonListTypeAudit
		createWatsonListTypeAudit(long watsonListTypeAuditId) {

		return _watsonListTypeAuditLocalService.createWatsonListTypeAudit(
			watsonListTypeAuditId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonListTypeAuditLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the watson list type audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeAuditId the primary key of the watson list type audit
	 * @return the watson list type audit that was removed
	 * @throws PortalException if a watson list type audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonListTypeAudit
			deleteWatsonListTypeAudit(long watsonListTypeAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonListTypeAuditLocalService.deleteWatsonListTypeAudit(
			watsonListTypeAuditId);
	}

	/**
	 * Deletes the watson list type audit from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeAudit the watson list type audit
	 * @return the watson list type audit that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonListTypeAudit
		deleteWatsonListTypeAudit(
			com.liferay.watson.model.WatsonListTypeAudit watsonListTypeAudit) {

		return _watsonListTypeAuditLocalService.deleteWatsonListTypeAudit(
			watsonListTypeAudit);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonListTypeAuditLocalService.dynamicQuery();
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

		return _watsonListTypeAuditLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonListTypeAuditModelImpl</code>.
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

		return _watsonListTypeAuditLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonListTypeAuditModelImpl</code>.
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

		return _watsonListTypeAuditLocalService.dynamicQuery(
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

		return _watsonListTypeAuditLocalService.dynamicQueryCount(dynamicQuery);
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

		return _watsonListTypeAuditLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonListTypeAudit
		fetchWatsonListTypeAudit(long watsonListTypeAuditId) {

		return _watsonListTypeAuditLocalService.fetchWatsonListTypeAudit(
			watsonListTypeAuditId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonListTypeAuditLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonListTypeAuditLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonListTypeAuditLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonListTypeAuditLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns the watson list type audit with the primary key.
	 *
	 * @param watsonListTypeAuditId the primary key of the watson list type audit
	 * @return the watson list type audit
	 * @throws PortalException if a watson list type audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonListTypeAudit getWatsonListTypeAudit(
			long watsonListTypeAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonListTypeAuditLocalService.getWatsonListTypeAudit(
			watsonListTypeAuditId);
	}

	/**
	 * Returns a range of all the watson list type audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonListTypeAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type audits
	 * @param end the upper bound of the range of watson list type audits (not inclusive)
	 * @return the range of watson list type audits
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonListTypeAudit>
		getWatsonListTypeAudits(int start, int end) {

		return _watsonListTypeAuditLocalService.getWatsonListTypeAudits(
			start, end);
	}

	/**
	 * Returns the number of watson list type audits.
	 *
	 * @return the number of watson list type audits
	 */
	@Override
	public int getWatsonListTypeAuditsCount() {
		return _watsonListTypeAuditLocalService.getWatsonListTypeAuditsCount();
	}

	/**
	 * Updates the watson list type audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeAudit the watson list type audit
	 * @return the watson list type audit that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonListTypeAudit
		updateWatsonListTypeAudit(
			com.liferay.watson.model.WatsonListTypeAudit watsonListTypeAudit) {

		return _watsonListTypeAuditLocalService.updateWatsonListTypeAudit(
			watsonListTypeAudit);
	}

	@Override
	public WatsonListTypeAuditLocalService getWrappedService() {
		return _watsonListTypeAuditLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonListTypeAuditLocalService watsonListTypeAuditLocalService) {

		_watsonListTypeAuditLocalService = watsonListTypeAuditLocalService;
	}

	private WatsonListTypeAuditLocalService _watsonListTypeAuditLocalService;

}