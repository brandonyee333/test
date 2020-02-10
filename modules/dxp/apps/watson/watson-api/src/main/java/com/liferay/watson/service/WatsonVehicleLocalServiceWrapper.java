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
 * Provides a wrapper for {@link WatsonVehicleLocalService}.
 *
 * @author Steven Smith
 * @see WatsonVehicleLocalService
 * @generated
 */
public class WatsonVehicleLocalServiceWrapper
	implements ServiceWrapper<WatsonVehicleLocalService>,
			   WatsonVehicleLocalService {

	public WatsonVehicleLocalServiceWrapper(
		WatsonVehicleLocalService watsonVehicleLocalService) {

		_watsonVehicleLocalService = watsonVehicleLocalService;
	}

	/**
	 * Adds the watson vehicle to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonVehicle the watson vehicle
	 * @return the watson vehicle that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonVehicle addWatsonVehicle(
		com.liferay.watson.model.WatsonVehicle watsonVehicle) {

		return _watsonVehicleLocalService.addWatsonVehicle(watsonVehicle);
	}

	/**
	 * Creates a new watson vehicle with the primary key. Does not add the watson vehicle to the database.
	 *
	 * @param watsonVehicleId the primary key for the new watson vehicle
	 * @return the new watson vehicle
	 */
	@Override
	public com.liferay.watson.model.WatsonVehicle createWatsonVehicle(
		long watsonVehicleId) {

		return _watsonVehicleLocalService.createWatsonVehicle(watsonVehicleId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonVehicleLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the watson vehicle with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonVehicleId the primary key of the watson vehicle
	 * @return the watson vehicle that was removed
	 * @throws PortalException if a watson vehicle with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonVehicle deleteWatsonVehicle(
			long watsonVehicleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonVehicleLocalService.deleteWatsonVehicle(watsonVehicleId);
	}

	/**
	 * Deletes the watson vehicle from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonVehicle the watson vehicle
	 * @return the watson vehicle that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonVehicle deleteWatsonVehicle(
		com.liferay.watson.model.WatsonVehicle watsonVehicle) {

		return _watsonVehicleLocalService.deleteWatsonVehicle(watsonVehicle);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonVehicleLocalService.dynamicQuery();
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

		return _watsonVehicleLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonVehicleModelImpl</code>.
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

		return _watsonVehicleLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonVehicleModelImpl</code>.
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

		return _watsonVehicleLocalService.dynamicQuery(
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

		return _watsonVehicleLocalService.dynamicQueryCount(dynamicQuery);
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

		return _watsonVehicleLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonVehicle fetchWatsonVehicle(
		long watsonVehicleId) {

		return _watsonVehicleLocalService.fetchWatsonVehicle(watsonVehicleId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonVehicleLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonVehicleLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonVehicleLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonVehicleLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the watson vehicle with the primary key.
	 *
	 * @param watsonVehicleId the primary key of the watson vehicle
	 * @return the watson vehicle
	 * @throws PortalException if a watson vehicle with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonVehicle getWatsonVehicle(
			long watsonVehicleId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonVehicleLocalService.getWatsonVehicle(watsonVehicleId);
	}

	/**
	 * Returns a range of all the watson vehicles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonVehicleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson vehicles
	 * @param end the upper bound of the range of watson vehicles (not inclusive)
	 * @return the range of watson vehicles
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonVehicle>
		getWatsonVehicles(int start, int end) {

		return _watsonVehicleLocalService.getWatsonVehicles(start, end);
	}

	/**
	 * Returns the number of watson vehicles.
	 *
	 * @return the number of watson vehicles
	 */
	@Override
	public int getWatsonVehiclesCount() {
		return _watsonVehicleLocalService.getWatsonVehiclesCount();
	}

	/**
	 * Updates the watson vehicle in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonVehicle the watson vehicle
	 * @return the watson vehicle that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonVehicle updateWatsonVehicle(
		com.liferay.watson.model.WatsonVehicle watsonVehicle) {

		return _watsonVehicleLocalService.updateWatsonVehicle(watsonVehicle);
	}

	@Override
	public WatsonVehicleLocalService getWrappedService() {
		return _watsonVehicleLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonVehicleLocalService watsonVehicleLocalService) {

		_watsonVehicleLocalService = watsonVehicleLocalService;
	}

	private WatsonVehicleLocalService _watsonVehicleLocalService;

}