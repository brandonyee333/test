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
 * Provides a wrapper for {@link WatsonHistoryLocalService}.
 *
 * @author Steven Smith
 * @see WatsonHistoryLocalService
 * @generated
 */
public class WatsonHistoryLocalServiceWrapper
	implements ServiceWrapper<WatsonHistoryLocalService>,
			   WatsonHistoryLocalService {

	public WatsonHistoryLocalServiceWrapper(
		WatsonHistoryLocalService watsonHistoryLocalService) {

		_watsonHistoryLocalService = watsonHistoryLocalService;
	}

	/**
	 * Adds the watson history to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonHistory the watson history
	 * @return the watson history that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonHistory addWatsonHistory(
		com.liferay.watson.model.WatsonHistory watsonHistory) {

		return _watsonHistoryLocalService.addWatsonHistory(watsonHistory);
	}

	/**
	 * Creates a new watson history with the primary key. Does not add the watson history to the database.
	 *
	 * @param watsonHistoryId the primary key for the new watson history
	 * @return the new watson history
	 */
	@Override
	public com.liferay.watson.model.WatsonHistory createWatsonHistory(
		long watsonHistoryId) {

		return _watsonHistoryLocalService.createWatsonHistory(watsonHistoryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonHistoryLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the watson history with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonHistoryId the primary key of the watson history
	 * @return the watson history that was removed
	 * @throws PortalException if a watson history with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonHistory deleteWatsonHistory(
			long watsonHistoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonHistoryLocalService.deleteWatsonHistory(watsonHistoryId);
	}

	/**
	 * Deletes the watson history from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonHistory the watson history
	 * @return the watson history that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonHistory deleteWatsonHistory(
		com.liferay.watson.model.WatsonHistory watsonHistory) {

		return _watsonHistoryLocalService.deleteWatsonHistory(watsonHistory);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonHistoryLocalService.dynamicQuery();
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

		return _watsonHistoryLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonHistoryModelImpl</code>.
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

		return _watsonHistoryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonHistoryModelImpl</code>.
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

		return _watsonHistoryLocalService.dynamicQuery(
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

		return _watsonHistoryLocalService.dynamicQueryCount(dynamicQuery);
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

		return _watsonHistoryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonHistory fetchWatsonHistory(
		long watsonHistoryId) {

		return _watsonHistoryLocalService.fetchWatsonHistory(watsonHistoryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonHistoryLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonHistoryLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonHistoryLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonHistoryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the watson histories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonHistoryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson histories
	 * @param end the upper bound of the range of watson histories (not inclusive)
	 * @return the range of watson histories
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonHistory>
		getWatsonHistories(int start, int end) {

		return _watsonHistoryLocalService.getWatsonHistories(start, end);
	}

	/**
	 * Returns the number of watson histories.
	 *
	 * @return the number of watson histories
	 */
	@Override
	public int getWatsonHistoriesCount() {
		return _watsonHistoryLocalService.getWatsonHistoriesCount();
	}

	/**
	 * Returns the watson history with the primary key.
	 *
	 * @param watsonHistoryId the primary key of the watson history
	 * @return the watson history
	 * @throws PortalException if a watson history with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonHistory getWatsonHistory(
			long watsonHistoryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonHistoryLocalService.getWatsonHistory(watsonHistoryId);
	}

	/**
	 * Updates the watson history in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonHistory the watson history
	 * @return the watson history that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonHistory updateWatsonHistory(
		com.liferay.watson.model.WatsonHistory watsonHistory) {

		return _watsonHistoryLocalService.updateWatsonHistory(watsonHistory);
	}

	@Override
	public WatsonHistoryLocalService getWrappedService() {
		return _watsonHistoryLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonHistoryLocalService watsonHistoryLocalService) {

		_watsonHistoryLocalService = watsonHistoryLocalService;
	}

	private WatsonHistoryLocalService _watsonHistoryLocalService;

}