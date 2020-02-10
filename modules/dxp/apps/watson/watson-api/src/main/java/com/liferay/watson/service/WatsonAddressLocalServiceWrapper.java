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
 * Provides a wrapper for {@link WatsonAddressLocalService}.
 *
 * @author Steven Smith
 * @see WatsonAddressLocalService
 * @generated
 */
public class WatsonAddressLocalServiceWrapper
	implements ServiceWrapper<WatsonAddressLocalService>,
			   WatsonAddressLocalService {

	public WatsonAddressLocalServiceWrapper(
		WatsonAddressLocalService watsonAddressLocalService) {

		_watsonAddressLocalService = watsonAddressLocalService;
	}

	/**
	 * Adds the watson address to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonAddress the watson address
	 * @return the watson address that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonAddress addWatsonAddress(
		com.liferay.watson.model.WatsonAddress watsonAddress) {

		return _watsonAddressLocalService.addWatsonAddress(watsonAddress);
	}

	/**
	 * Creates a new watson address with the primary key. Does not add the watson address to the database.
	 *
	 * @param watsonAddressId the primary key for the new watson address
	 * @return the new watson address
	 */
	@Override
	public com.liferay.watson.model.WatsonAddress createWatsonAddress(
		long watsonAddressId) {

		return _watsonAddressLocalService.createWatsonAddress(watsonAddressId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonAddressLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the watson address with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonAddressId the primary key of the watson address
	 * @return the watson address that was removed
	 * @throws PortalException if a watson address with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonAddress deleteWatsonAddress(
			long watsonAddressId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonAddressLocalService.deleteWatsonAddress(watsonAddressId);
	}

	/**
	 * Deletes the watson address from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonAddress the watson address
	 * @return the watson address that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonAddress deleteWatsonAddress(
		com.liferay.watson.model.WatsonAddress watsonAddress) {

		return _watsonAddressLocalService.deleteWatsonAddress(watsonAddress);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonAddressLocalService.dynamicQuery();
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

		return _watsonAddressLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonAddressModelImpl</code>.
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

		return _watsonAddressLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonAddressModelImpl</code>.
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

		return _watsonAddressLocalService.dynamicQuery(
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

		return _watsonAddressLocalService.dynamicQueryCount(dynamicQuery);
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

		return _watsonAddressLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonAddress fetchWatsonAddress(
		long watsonAddressId) {

		return _watsonAddressLocalService.fetchWatsonAddress(watsonAddressId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonAddressLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonAddressLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonAddressLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonAddressLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the watson address with the primary key.
	 *
	 * @param watsonAddressId the primary key of the watson address
	 * @return the watson address
	 * @throws PortalException if a watson address with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonAddress getWatsonAddress(
			long watsonAddressId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonAddressLocalService.getWatsonAddress(watsonAddressId);
	}

	/**
	 * Returns a range of all the watson addresses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonAddressModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson addresses
	 * @param end the upper bound of the range of watson addresses (not inclusive)
	 * @return the range of watson addresses
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonAddress>
		getWatsonAddresses(int start, int end) {

		return _watsonAddressLocalService.getWatsonAddresses(start, end);
	}

	/**
	 * Returns the number of watson addresses.
	 *
	 * @return the number of watson addresses
	 */
	@Override
	public int getWatsonAddressesCount() {
		return _watsonAddressLocalService.getWatsonAddressesCount();
	}

	/**
	 * Updates the watson address in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonAddress the watson address
	 * @return the watson address that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonAddress updateWatsonAddress(
		com.liferay.watson.model.WatsonAddress watsonAddress) {

		return _watsonAddressLocalService.updateWatsonAddress(watsonAddress);
	}

	@Override
	public WatsonAddressLocalService getWrappedService() {
		return _watsonAddressLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonAddressLocalService watsonAddressLocalService) {

		_watsonAddressLocalService = watsonAddressLocalService;
	}

	private WatsonAddressLocalService _watsonAddressLocalService;

}