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
 * Provides a wrapper for {@link WatsonListTypeRelAuditLocalService}.
 *
 * @author Steven Smith
 * @see WatsonListTypeRelAuditLocalService
 * @generated
 */
public class WatsonListTypeRelAuditLocalServiceWrapper
	implements ServiceWrapper<WatsonListTypeRelAuditLocalService>,
			   WatsonListTypeRelAuditLocalService {

	public WatsonListTypeRelAuditLocalServiceWrapper(
		WatsonListTypeRelAuditLocalService watsonListTypeRelAuditLocalService) {

		_watsonListTypeRelAuditLocalService =
			watsonListTypeRelAuditLocalService;
	}

	/**
	 * Adds the watson list type rel audit to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeRelAudit the watson list type rel audit
	 * @return the watson list type rel audit that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonListTypeRelAudit
		addWatsonListTypeRelAudit(
			com.liferay.watson.model.WatsonListTypeRelAudit
				watsonListTypeRelAudit) {

		return _watsonListTypeRelAuditLocalService.addWatsonListTypeRelAudit(
			watsonListTypeRelAudit);
	}

	/**
	 * Creates a new watson list type rel audit with the primary key. Does not add the watson list type rel audit to the database.
	 *
	 * @param watsonListTypeRelAuditId the primary key for the new watson list type rel audit
	 * @return the new watson list type rel audit
	 */
	@Override
	public com.liferay.watson.model.WatsonListTypeRelAudit
		createWatsonListTypeRelAudit(long watsonListTypeRelAuditId) {

		return _watsonListTypeRelAuditLocalService.createWatsonListTypeRelAudit(
			watsonListTypeRelAuditId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonListTypeRelAuditLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the watson list type rel audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeRelAuditId the primary key of the watson list type rel audit
	 * @return the watson list type rel audit that was removed
	 * @throws PortalException if a watson list type rel audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonListTypeRelAudit
			deleteWatsonListTypeRelAudit(long watsonListTypeRelAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonListTypeRelAuditLocalService.deleteWatsonListTypeRelAudit(
			watsonListTypeRelAuditId);
	}

	/**
	 * Deletes the watson list type rel audit from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeRelAudit the watson list type rel audit
	 * @return the watson list type rel audit that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonListTypeRelAudit
		deleteWatsonListTypeRelAudit(
			com.liferay.watson.model.WatsonListTypeRelAudit
				watsonListTypeRelAudit) {

		return _watsonListTypeRelAuditLocalService.deleteWatsonListTypeRelAudit(
			watsonListTypeRelAudit);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonListTypeRelAuditLocalService.dynamicQuery();
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

		return _watsonListTypeRelAuditLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonListTypeRelAuditModelImpl</code>.
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

		return _watsonListTypeRelAuditLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonListTypeRelAuditModelImpl</code>.
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

		return _watsonListTypeRelAuditLocalService.dynamicQuery(
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

		return _watsonListTypeRelAuditLocalService.dynamicQueryCount(
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

		return _watsonListTypeRelAuditLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonListTypeRelAudit
		fetchWatsonListTypeRelAudit(long watsonListTypeRelAuditId) {

		return _watsonListTypeRelAuditLocalService.fetchWatsonListTypeRelAudit(
			watsonListTypeRelAuditId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonListTypeRelAuditLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonListTypeRelAuditLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonListTypeRelAuditLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonListTypeRelAuditLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns the watson list type rel audit with the primary key.
	 *
	 * @param watsonListTypeRelAuditId the primary key of the watson list type rel audit
	 * @return the watson list type rel audit
	 * @throws PortalException if a watson list type rel audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonListTypeRelAudit
			getWatsonListTypeRelAudit(long watsonListTypeRelAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonListTypeRelAuditLocalService.getWatsonListTypeRelAudit(
			watsonListTypeRelAuditId);
	}

	/**
	 * Returns a range of all the watson list type rel audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonListTypeRelAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type rel audits
	 * @param end the upper bound of the range of watson list type rel audits (not inclusive)
	 * @return the range of watson list type rel audits
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonListTypeRelAudit>
		getWatsonListTypeRelAudits(int start, int end) {

		return _watsonListTypeRelAuditLocalService.getWatsonListTypeRelAudits(
			start, end);
	}

	/**
	 * Returns the number of watson list type rel audits.
	 *
	 * @return the number of watson list type rel audits
	 */
	@Override
	public int getWatsonListTypeRelAuditsCount() {
		return _watsonListTypeRelAuditLocalService.
			getWatsonListTypeRelAuditsCount();
	}

	/**
	 * Updates the watson list type rel audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeRelAudit the watson list type rel audit
	 * @return the watson list type rel audit that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonListTypeRelAudit
		updateWatsonListTypeRelAudit(
			com.liferay.watson.model.WatsonListTypeRelAudit
				watsonListTypeRelAudit) {

		return _watsonListTypeRelAuditLocalService.updateWatsonListTypeRelAudit(
			watsonListTypeRelAudit);
	}

	@Override
	public WatsonListTypeRelAuditLocalService getWrappedService() {
		return _watsonListTypeRelAuditLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonListTypeRelAuditLocalService watsonListTypeRelAuditLocalService) {

		_watsonListTypeRelAuditLocalService =
			watsonListTypeRelAuditLocalService;
	}

	private WatsonListTypeRelAuditLocalService
		_watsonListTypeRelAuditLocalService;

}