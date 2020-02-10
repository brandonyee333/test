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
 * Provides a wrapper for {@link WatsonListTypeLocalService}.
 *
 * @author Steven Smith
 * @see WatsonListTypeLocalService
 * @generated
 */
public class WatsonListTypeLocalServiceWrapper
	implements ServiceWrapper<WatsonListTypeLocalService>,
			   WatsonListTypeLocalService {

	public WatsonListTypeLocalServiceWrapper(
		WatsonListTypeLocalService watsonListTypeLocalService) {

		_watsonListTypeLocalService = watsonListTypeLocalService;
	}

	/**
	 * Adds the watson list type to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListType the watson list type
	 * @return the watson list type that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonListType addWatsonListType(
		com.liferay.watson.model.WatsonListType watsonListType) {

		return _watsonListTypeLocalService.addWatsonListType(watsonListType);
	}

	/**
	 * Creates a new watson list type with the primary key. Does not add the watson list type to the database.
	 *
	 * @param watsonListTypeId the primary key for the new watson list type
	 * @return the new watson list type
	 */
	@Override
	public com.liferay.watson.model.WatsonListType createWatsonListType(
		long watsonListTypeId) {

		return _watsonListTypeLocalService.createWatsonListType(
			watsonListTypeId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonListTypeLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the watson list type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeId the primary key of the watson list type
	 * @return the watson list type that was removed
	 * @throws PortalException if a watson list type with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonListType deleteWatsonListType(
			long watsonListTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonListTypeLocalService.deleteWatsonListType(
			watsonListTypeId);
	}

	/**
	 * Deletes the watson list type from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListType the watson list type
	 * @return the watson list type that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonListType deleteWatsonListType(
		com.liferay.watson.model.WatsonListType watsonListType) {

		return _watsonListTypeLocalService.deleteWatsonListType(watsonListType);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonListTypeLocalService.dynamicQuery();
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

		return _watsonListTypeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonListTypeModelImpl</code>.
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

		return _watsonListTypeLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonListTypeModelImpl</code>.
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

		return _watsonListTypeLocalService.dynamicQuery(
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

		return _watsonListTypeLocalService.dynamicQueryCount(dynamicQuery);
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

		return _watsonListTypeLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonListType fetchWatsonListType(
		long watsonListTypeId) {

		return _watsonListTypeLocalService.fetchWatsonListType(
			watsonListTypeId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonListTypeLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonListTypeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonListTypeLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonListTypeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the watson list type with the primary key.
	 *
	 * @param watsonListTypeId the primary key of the watson list type
	 * @return the watson list type
	 * @throws PortalException if a watson list type with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonListType getWatsonListType(
			long watsonListTypeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonListTypeLocalService.getWatsonListType(watsonListTypeId);
	}

	/**
	 * Returns a range of all the watson list types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonListTypeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list types
	 * @param end the upper bound of the range of watson list types (not inclusive)
	 * @return the range of watson list types
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonListType>
		getWatsonListTypes(int start, int end) {

		return _watsonListTypeLocalService.getWatsonListTypes(start, end);
	}

	/**
	 * Returns the number of watson list types.
	 *
	 * @return the number of watson list types
	 */
	@Override
	public int getWatsonListTypesCount() {
		return _watsonListTypeLocalService.getWatsonListTypesCount();
	}

	/**
	 * Updates the watson list type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListType the watson list type
	 * @return the watson list type that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonListType updateWatsonListType(
		com.liferay.watson.model.WatsonListType watsonListType) {

		return _watsonListTypeLocalService.updateWatsonListType(watsonListType);
	}

	@Override
	public WatsonListTypeLocalService getWrappedService() {
		return _watsonListTypeLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonListTypeLocalService watsonListTypeLocalService) {

		_watsonListTypeLocalService = watsonListTypeLocalService;
	}

	private WatsonListTypeLocalService _watsonListTypeLocalService;

}