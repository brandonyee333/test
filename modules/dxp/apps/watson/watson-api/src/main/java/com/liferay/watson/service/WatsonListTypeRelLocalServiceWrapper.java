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
 * Provides a wrapper for {@link WatsonListTypeRelLocalService}.
 *
 * @author Steven Smith
 * @see WatsonListTypeRelLocalService
 * @generated
 */
public class WatsonListTypeRelLocalServiceWrapper
	implements ServiceWrapper<WatsonListTypeRelLocalService>,
			   WatsonListTypeRelLocalService {

	public WatsonListTypeRelLocalServiceWrapper(
		WatsonListTypeRelLocalService watsonListTypeRelLocalService) {

		_watsonListTypeRelLocalService = watsonListTypeRelLocalService;
	}

	/**
	 * Adds the watson list type rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeRel the watson list type rel
	 * @return the watson list type rel that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonListTypeRel addWatsonListTypeRel(
		com.liferay.watson.model.WatsonListTypeRel watsonListTypeRel) {

		return _watsonListTypeRelLocalService.addWatsonListTypeRel(
			watsonListTypeRel);
	}

	/**
	 * Creates a new watson list type rel with the primary key. Does not add the watson list type rel to the database.
	 *
	 * @param watsonListTypeRelId the primary key for the new watson list type rel
	 * @return the new watson list type rel
	 */
	@Override
	public com.liferay.watson.model.WatsonListTypeRel createWatsonListTypeRel(
		long watsonListTypeRelId) {

		return _watsonListTypeRelLocalService.createWatsonListTypeRel(
			watsonListTypeRelId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonListTypeRelLocalService.deletePersistedModel(
			persistedModel);
	}

	/**
	 * Deletes the watson list type rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeRelId the primary key of the watson list type rel
	 * @return the watson list type rel that was removed
	 * @throws PortalException if a watson list type rel with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonListTypeRel deleteWatsonListTypeRel(
			long watsonListTypeRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonListTypeRelLocalService.deleteWatsonListTypeRel(
			watsonListTypeRelId);
	}

	/**
	 * Deletes the watson list type rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeRel the watson list type rel
	 * @return the watson list type rel that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonListTypeRel deleteWatsonListTypeRel(
		com.liferay.watson.model.WatsonListTypeRel watsonListTypeRel) {

		return _watsonListTypeRelLocalService.deleteWatsonListTypeRel(
			watsonListTypeRel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonListTypeRelLocalService.dynamicQuery();
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

		return _watsonListTypeRelLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonListTypeRelModelImpl</code>.
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

		return _watsonListTypeRelLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonListTypeRelModelImpl</code>.
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

		return _watsonListTypeRelLocalService.dynamicQuery(
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

		return _watsonListTypeRelLocalService.dynamicQueryCount(dynamicQuery);
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

		return _watsonListTypeRelLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonListTypeRel fetchWatsonListTypeRel(
		long watsonListTypeRelId) {

		return _watsonListTypeRelLocalService.fetchWatsonListTypeRel(
			watsonListTypeRelId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonListTypeRelLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonListTypeRelLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonListTypeRelLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonListTypeRelLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the watson list type rel with the primary key.
	 *
	 * @param watsonListTypeRelId the primary key of the watson list type rel
	 * @return the watson list type rel
	 * @throws PortalException if a watson list type rel with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonListTypeRel getWatsonListTypeRel(
			long watsonListTypeRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonListTypeRelLocalService.getWatsonListTypeRel(
			watsonListTypeRelId);
	}

	/**
	 * Returns a range of all the watson list type rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonListTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type rels
	 * @param end the upper bound of the range of watson list type rels (not inclusive)
	 * @return the range of watson list type rels
	 */
	@Override
	public java.util.List<com.liferay.watson.model.WatsonListTypeRel>
		getWatsonListTypeRels(int start, int end) {

		return _watsonListTypeRelLocalService.getWatsonListTypeRels(start, end);
	}

	/**
	 * Returns the number of watson list type rels.
	 *
	 * @return the number of watson list type rels
	 */
	@Override
	public int getWatsonListTypeRelsCount() {
		return _watsonListTypeRelLocalService.getWatsonListTypeRelsCount();
	}

	/**
	 * Updates the watson list type rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeRel the watson list type rel
	 * @return the watson list type rel that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonListTypeRel updateWatsonListTypeRel(
		com.liferay.watson.model.WatsonListTypeRel watsonListTypeRel) {

		return _watsonListTypeRelLocalService.updateWatsonListTypeRel(
			watsonListTypeRel);
	}

	@Override
	public WatsonListTypeRelLocalService getWrappedService() {
		return _watsonListTypeRelLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonListTypeRelLocalService watsonListTypeRelLocalService) {

		_watsonListTypeRelLocalService = watsonListTypeRelLocalService;
	}

	private WatsonListTypeRelLocalService _watsonListTypeRelLocalService;

}