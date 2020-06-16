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
 * Provides a wrapper for {@link WatsonChildLocalService}.
 *
 * @author Steven Smith
 * @see WatsonChildLocalService
 * @generated
 */
public class WatsonChildLocalServiceWrapper
	implements ServiceWrapper<WatsonChildLocalService>,
			   WatsonChildLocalService {

	public WatsonChildLocalServiceWrapper(
		WatsonChildLocalService watsonChildLocalService) {

		_watsonChildLocalService = watsonChildLocalService;
	}

	/**
	 * Adds the watson child to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonChild the watson child
	 * @return the watson child that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonChild addWatsonChild(
		com.liferay.watson.model.WatsonChild watsonChild) {

		return _watsonChildLocalService.addWatsonChild(watsonChild);
	}

	/**
	 * Creates a new watson child with the primary key. Does not add the watson child to the database.
	 *
	 * @param watsonChildId the primary key for the new watson child
	 * @return the new watson child
	 */
	@Override
	public com.liferay.watson.model.WatsonChild createWatsonChild(
		long watsonChildId) {

		return _watsonChildLocalService.createWatsonChild(watsonChildId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonChildLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the watson child with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonChildId the primary key of the watson child
	 * @return the watson child that was removed
	 * @throws PortalException if a watson child with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonChild deleteWatsonChild(
			long watsonChildId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonChildLocalService.deleteWatsonChild(watsonChildId);
	}

	/**
	 * Deletes the watson child from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonChild the watson child
	 * @return the watson child that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonChild deleteWatsonChild(
		com.liferay.watson.model.WatsonChild watsonChild) {

		return _watsonChildLocalService.deleteWatsonChild(watsonChild);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonChildLocalService.dynamicQuery();
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

		return _watsonChildLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonChildModelImpl</code>.
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

		return _watsonChildLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonChildModelImpl</code>.
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

		return _watsonChildLocalService.dynamicQuery(
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

		return _watsonChildLocalService.dynamicQueryCount(dynamicQuery);
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

		return _watsonChildLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonChild fetchWatsonChild(
		long watsonChildId) {

		return _watsonChildLocalService.fetchWatsonChild(watsonChildId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonChildLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonChildLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonChildLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonChildLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the watson child with the primary key.
	 *
	 * @param watsonChildId the primary key of the watson child
	 * @return the watson child
	 * @throws PortalException if a watson child with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonChild getWatsonChild(
			long watsonChildId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonChildLocalService.getWatsonChild(watsonChildId);
	}

	/**
	 * Returns a range of all the watson childs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonChildModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson childs
	 * @param end the upper bound of the range of watson childs (not inclusive)
	 * @return the range of watson childs
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonChild> getWatsonChilds(
		int start, int end) {

		return _watsonChildLocalService.getWatsonChilds(start, end);
	}

	/**
	 * Returns the number of watson childs.
	 *
	 * @return the number of watson childs
	 */
	@Override
	public int getWatsonChildsCount() {
		return _watsonChildLocalService.getWatsonChildsCount();
	}

	/**
	 * Updates the watson child in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonChild the watson child
	 * @return the watson child that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonChild updateWatsonChild(
		com.liferay.watson.model.WatsonChild watsonChild) {

		return _watsonChildLocalService.updateWatsonChild(watsonChild);
	}

	@Override
	public WatsonChildLocalService getWrappedService() {
		return _watsonChildLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonChildLocalService watsonChildLocalService) {

		_watsonChildLocalService = watsonChildLocalService;
	}

	private WatsonChildLocalService _watsonChildLocalService;

}