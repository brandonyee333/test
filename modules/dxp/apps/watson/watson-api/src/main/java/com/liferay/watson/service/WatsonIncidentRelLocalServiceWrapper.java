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
 * Provides a wrapper for {@link WatsonIncidentRelLocalService}.
 *
 * @author Steven Smith
 * @see WatsonIncidentRelLocalService
 * @generated
 */
public class WatsonIncidentRelLocalServiceWrapper
	implements ServiceWrapper<WatsonIncidentRelLocalService>,
			   WatsonIncidentRelLocalService {

	public WatsonIncidentRelLocalServiceWrapper(
		WatsonIncidentRelLocalService watsonIncidentRelLocalService) {

		_watsonIncidentRelLocalService = watsonIncidentRelLocalService;
	}

	/**
	 * Adds the watson incident rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRel the watson incident rel
	 * @return the watson incident rel that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonIncidentRel addWatsonIncidentRel(
		com.liferay.watson.model.WatsonIncidentRel watsonIncidentRel) {

		return _watsonIncidentRelLocalService.addWatsonIncidentRel(
			watsonIncidentRel);
	}

	/**
	 * Creates a new watson incident rel with the primary key. Does not add the watson incident rel to the database.
	 *
	 * @param watsonIncidentRelId the primary key for the new watson incident rel
	 * @return the new watson incident rel
	 */
	@Override
	public com.liferay.watson.model.WatsonIncidentRel createWatsonIncidentRel(
		long watsonIncidentRelId) {

		return _watsonIncidentRelLocalService.createWatsonIncidentRel(
			watsonIncidentRelId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonIncidentRelLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the watson incident rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRelId the primary key of the watson incident rel
	 * @return the watson incident rel that was removed
	 * @throws PortalException if a watson incident rel with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonIncidentRel deleteWatsonIncidentRel(
			long watsonIncidentRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonIncidentRelLocalService.deleteWatsonIncidentRel(
			watsonIncidentRelId);
	}

	/**
	 * Deletes the watson incident rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRel the watson incident rel
	 * @return the watson incident rel that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonIncidentRel deleteWatsonIncidentRel(
		com.liferay.watson.model.WatsonIncidentRel watsonIncidentRel) {

		return _watsonIncidentRelLocalService.deleteWatsonIncidentRel(
			watsonIncidentRel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonIncidentRelLocalService.dynamicQuery();
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

		return _watsonIncidentRelLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonIncidentRelModelImpl</code>.
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

		return _watsonIncidentRelLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonIncidentRelModelImpl</code>.
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

		return _watsonIncidentRelLocalService.dynamicQuery(
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

		return _watsonIncidentRelLocalService.dynamicQueryCount(dynamicQuery);
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

		return _watsonIncidentRelLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonIncidentRel fetchWatsonIncidentRel(
		long watsonIncidentRelId) {

		return _watsonIncidentRelLocalService.fetchWatsonIncidentRel(
			watsonIncidentRelId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonIncidentRelLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonIncidentRelLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonIncidentRelLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonIncidentRelLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the watson incident rel with the primary key.
	 *
	 * @param watsonIncidentRelId the primary key of the watson incident rel
	 * @return the watson incident rel
	 * @throws PortalException if a watson incident rel with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonIncidentRel getWatsonIncidentRel(
			long watsonIncidentRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonIncidentRelLocalService.getWatsonIncidentRel(
			watsonIncidentRelId);
	}

	/**
	 * Returns a range of all the watson incident rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonIncidentRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident rels
	 * @param end the upper bound of the range of watson incident rels (not inclusive)
	 * @return the range of watson incident rels
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonIncidentRel>
		getWatsonIncidentRels(int start, int end) {

		return _watsonIncidentRelLocalService.getWatsonIncidentRels(start, end);
	}

	/**
	 * Returns the number of watson incident rels.
	 *
	 * @return the number of watson incident rels
	 */
	@Override
	public int getWatsonIncidentRelsCount() {
		return _watsonIncidentRelLocalService.getWatsonIncidentRelsCount();
	}

	/**
	 * Updates the watson incident rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRel the watson incident rel
	 * @return the watson incident rel that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonIncidentRel updateWatsonIncidentRel(
		com.liferay.watson.model.WatsonIncidentRel watsonIncidentRel) {

		return _watsonIncidentRelLocalService.updateWatsonIncidentRel(
			watsonIncidentRel);
	}

	@Override
	public WatsonIncidentRelLocalService getWrappedService() {
		return _watsonIncidentRelLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonIncidentRelLocalService watsonIncidentRelLocalService) {

		_watsonIncidentRelLocalService = watsonIncidentRelLocalService;
	}

	private WatsonIncidentRelLocalService _watsonIncidentRelLocalService;

}