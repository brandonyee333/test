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
 * Provides a wrapper for {@link WatsonRelationshipAuditLocalService}.
 *
 * @author Steven Smith
 * @see WatsonRelationshipAuditLocalService
 * @generated
 */
public class WatsonRelationshipAuditLocalServiceWrapper
	implements ServiceWrapper<WatsonRelationshipAuditLocalService>,
			   WatsonRelationshipAuditLocalService {

	public WatsonRelationshipAuditLocalServiceWrapper(
		WatsonRelationshipAuditLocalService
			watsonRelationshipAuditLocalService) {

		_watsonRelationshipAuditLocalService =
			watsonRelationshipAuditLocalService;
	}

	/**
	 * Adds the watson relationship audit to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonRelationshipAudit the watson relationship audit
	 * @return the watson relationship audit that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonRelationshipAudit
		addWatsonRelationshipAudit(
			com.liferay.watson.model.WatsonRelationshipAudit
				watsonRelationshipAudit) {

		return _watsonRelationshipAuditLocalService.addWatsonRelationshipAudit(
			watsonRelationshipAudit);
	}

	/**
	 * Creates a new watson relationship audit with the primary key. Does not add the watson relationship audit to the database.
	 *
	 * @param watsonRelationshipAuditId the primary key for the new watson relationship audit
	 * @return the new watson relationship audit
	 */
	@Override
	public com.liferay.watson.model.WatsonRelationshipAudit
		createWatsonRelationshipAudit(long watsonRelationshipAuditId) {

		return _watsonRelationshipAuditLocalService.
			createWatsonRelationshipAudit(watsonRelationshipAuditId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonRelationshipAuditLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the watson relationship audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonRelationshipAuditId the primary key of the watson relationship audit
	 * @return the watson relationship audit that was removed
	 * @throws PortalException if a watson relationship audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonRelationshipAudit
			deleteWatsonRelationshipAudit(long watsonRelationshipAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonRelationshipAuditLocalService.
			deleteWatsonRelationshipAudit(watsonRelationshipAuditId);
	}

	/**
	 * Deletes the watson relationship audit from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonRelationshipAudit the watson relationship audit
	 * @return the watson relationship audit that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonRelationshipAudit
		deleteWatsonRelationshipAudit(
			com.liferay.watson.model.WatsonRelationshipAudit
				watsonRelationshipAudit) {

		return _watsonRelationshipAuditLocalService.
			deleteWatsonRelationshipAudit(watsonRelationshipAudit);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonRelationshipAuditLocalService.dynamicQuery();
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

		return _watsonRelationshipAuditLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonRelationshipAuditModelImpl</code>.
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

		return _watsonRelationshipAuditLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonRelationshipAuditModelImpl</code>.
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

		return _watsonRelationshipAuditLocalService.dynamicQuery(
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

		return _watsonRelationshipAuditLocalService.dynamicQueryCount(
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

		return _watsonRelationshipAuditLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonRelationshipAudit
		fetchWatsonRelationshipAudit(long watsonRelationshipAuditId) {

		return _watsonRelationshipAuditLocalService.
			fetchWatsonRelationshipAudit(watsonRelationshipAuditId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonRelationshipAuditLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonRelationshipAuditLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonRelationshipAuditLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonRelationshipAuditLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Returns the watson relationship audit with the primary key.
	 *
	 * @param watsonRelationshipAuditId the primary key of the watson relationship audit
	 * @return the watson relationship audit
	 * @throws PortalException if a watson relationship audit with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonRelationshipAudit
			getWatsonRelationshipAudit(long watsonRelationshipAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonRelationshipAuditLocalService.getWatsonRelationshipAudit(
			watsonRelationshipAuditId);
	}

	/**
	 * Returns a range of all the watson relationship audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonRelationshipAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson relationship audits
	 * @param end the upper bound of the range of watson relationship audits (not inclusive)
	 * @return the range of watson relationship audits
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonRelationshipAudit>
		getWatsonRelationshipAudits(int start, int end) {

		return _watsonRelationshipAuditLocalService.getWatsonRelationshipAudits(
			start, end);
	}

	/**
	 * Returns the number of watson relationship audits.
	 *
	 * @return the number of watson relationship audits
	 */
	@Override
	public int getWatsonRelationshipAuditsCount() {
		return _watsonRelationshipAuditLocalService.
			getWatsonRelationshipAuditsCount();
	}

	/**
	 * Updates the watson relationship audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonRelationshipAudit the watson relationship audit
	 * @return the watson relationship audit that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonRelationshipAudit
		updateWatsonRelationshipAudit(
			com.liferay.watson.model.WatsonRelationshipAudit
				watsonRelationshipAudit) {

		return _watsonRelationshipAuditLocalService.
			updateWatsonRelationshipAudit(watsonRelationshipAudit);
	}

	@Override
	public WatsonRelationshipAuditLocalService getWrappedService() {
		return _watsonRelationshipAuditLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonRelationshipAuditLocalService
			watsonRelationshipAuditLocalService) {

		_watsonRelationshipAuditLocalService =
			watsonRelationshipAuditLocalService;
	}

	private WatsonRelationshipAuditLocalService
		_watsonRelationshipAuditLocalService;

}