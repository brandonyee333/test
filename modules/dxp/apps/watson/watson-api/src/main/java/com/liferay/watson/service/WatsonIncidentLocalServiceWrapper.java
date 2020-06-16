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
 * Provides a wrapper for {@link WatsonIncidentLocalService}.
 *
 * @author Steven Smith
 * @see WatsonIncidentLocalService
 * @generated
 */
public class WatsonIncidentLocalServiceWrapper
	implements ServiceWrapper<WatsonIncidentLocalService>,
			   WatsonIncidentLocalService {

	public WatsonIncidentLocalServiceWrapper(
		WatsonIncidentLocalService watsonIncidentLocalService) {

		_watsonIncidentLocalService = watsonIncidentLocalService;
	}

	/**
	 * Adds the watson incident to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncident the watson incident
	 * @return the watson incident that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonIncident addWatsonIncident(
		com.liferay.watson.model.WatsonIncident watsonIncident) {

		return _watsonIncidentLocalService.addWatsonIncident(watsonIncident);
	}

	/**
	 * Creates a new watson incident with the primary key. Does not add the watson incident to the database.
	 *
	 * @param watsonIncidentId the primary key for the new watson incident
	 * @return the new watson incident
	 */
	@Override
	public com.liferay.watson.model.WatsonIncident createWatsonIncident(
		long watsonIncidentId) {

		return _watsonIncidentLocalService.createWatsonIncident(
			watsonIncidentId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonIncidentLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the watson incident with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentId the primary key of the watson incident
	 * @return the watson incident that was removed
	 * @throws PortalException if a watson incident with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonIncident deleteWatsonIncident(
			long watsonIncidentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonIncidentLocalService.deleteWatsonIncident(
			watsonIncidentId);
	}

	/**
	 * Deletes the watson incident from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncident the watson incident
	 * @return the watson incident that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonIncident deleteWatsonIncident(
		com.liferay.watson.model.WatsonIncident watsonIncident) {

		return _watsonIncidentLocalService.deleteWatsonIncident(watsonIncident);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonIncidentLocalService.dynamicQuery();
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

		return _watsonIncidentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonIncidentModelImpl</code>.
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

		return _watsonIncidentLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonIncidentModelImpl</code>.
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

		return _watsonIncidentLocalService.dynamicQuery(
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

		return _watsonIncidentLocalService.dynamicQueryCount(dynamicQuery);
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

		return _watsonIncidentLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonIncident fetchWatsonIncident(
		long watsonIncidentId) {

		return _watsonIncidentLocalService.fetchWatsonIncident(
			watsonIncidentId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonIncidentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonIncidentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonIncidentLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonIncidentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the watson incident with the primary key.
	 *
	 * @param watsonIncidentId the primary key of the watson incident
	 * @return the watson incident
	 * @throws PortalException if a watson incident with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonIncident getWatsonIncident(
			long watsonIncidentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonIncidentLocalService.getWatsonIncident(watsonIncidentId);
	}

	/**
	 * Returns a range of all the watson incidents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonIncidentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incidents
	 * @param end the upper bound of the range of watson incidents (not inclusive)
	 * @return the range of watson incidents
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonIncident>
		getWatsonIncidents(int start, int end) {

		return _watsonIncidentLocalService.getWatsonIncidents(start, end);
	}

	/**
	 * Returns the number of watson incidents.
	 *
	 * @return the number of watson incidents
	 */
	@Override
	public int getWatsonIncidentsCount() {
		return _watsonIncidentLocalService.getWatsonIncidentsCount();
	}

	/**
	 * Updates the watson incident in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncident the watson incident
	 * @return the watson incident that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonIncident updateWatsonIncident(
		com.liferay.watson.model.WatsonIncident watsonIncident) {

		return _watsonIncidentLocalService.updateWatsonIncident(watsonIncident);
	}

	@Override
	public WatsonIncidentLocalService getWrappedService() {
		return _watsonIncidentLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonIncidentLocalService watsonIncidentLocalService) {

		_watsonIncidentLocalService = watsonIncidentLocalService;
	}

	private WatsonIncidentLocalService _watsonIncidentLocalService;

}