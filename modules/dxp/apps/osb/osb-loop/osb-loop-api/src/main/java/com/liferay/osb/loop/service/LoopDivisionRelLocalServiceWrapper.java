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

package com.liferay.osb.loop.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LoopDivisionRelLocalService}.
 *
 * @author Ethan Bustad
 * @see LoopDivisionRelLocalService
 * @generated
 */
public class LoopDivisionRelLocalServiceWrapper
	implements LoopDivisionRelLocalService,
			   ServiceWrapper<LoopDivisionRelLocalService> {

	public LoopDivisionRelLocalServiceWrapper(
		LoopDivisionRelLocalService loopDivisionRelLocalService) {

		_loopDivisionRelLocalService = loopDivisionRelLocalService;
	}

	/**
	 * Adds the loop division rel to the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopDivisionRel the loop division rel
	 * @return the loop division rel that was added
	 */
	@Override
	public com.liferay.osb.loop.model.LoopDivisionRel addLoopDivisionRel(
		com.liferay.osb.loop.model.LoopDivisionRel loopDivisionRel) {

		return _loopDivisionRelLocalService.addLoopDivisionRel(loopDivisionRel);
	}

	/**
	 * Creates a new loop division rel with the primary key. Does not add the loop division rel to the database.
	 *
	 * @param loopDivisionRelId the primary key for the new loop division rel
	 * @return the new loop division rel
	 */
	@Override
	public com.liferay.osb.loop.model.LoopDivisionRel createLoopDivisionRel(
		long loopDivisionRelId) {

		return _loopDivisionRelLocalService.createLoopDivisionRel(
			loopDivisionRelId);
	}

	/**
	 * Deletes the loop division rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopDivisionRelId the primary key of the loop division rel
	 * @return the loop division rel that was removed
	 * @throws PortalException if a loop division rel with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.loop.model.LoopDivisionRel deleteLoopDivisionRel(
			long loopDivisionRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopDivisionRelLocalService.deleteLoopDivisionRel(
			loopDivisionRelId);
	}

	/**
	 * Deletes the loop division rel from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopDivisionRel the loop division rel
	 * @return the loop division rel that was removed
	 */
	@Override
	public com.liferay.osb.loop.model.LoopDivisionRel deleteLoopDivisionRel(
		com.liferay.osb.loop.model.LoopDivisionRel loopDivisionRel) {

		return _loopDivisionRelLocalService.deleteLoopDivisionRel(
			loopDivisionRel);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopDivisionRelLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loopDivisionRelLocalService.dynamicQuery();
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

		return _loopDivisionRelLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopDivisionRelModelImpl</code>.
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

		return _loopDivisionRelLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopDivisionRelModelImpl</code>.
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

		return _loopDivisionRelLocalService.dynamicQuery(
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

		return _loopDivisionRelLocalService.dynamicQueryCount(dynamicQuery);
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

		return _loopDivisionRelLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.loop.model.LoopDivisionRel fetchLoopDivisionRel(
		long loopDivisionRelId) {

		return _loopDivisionRelLocalService.fetchLoopDivisionRel(
			loopDivisionRelId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _loopDivisionRelLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _loopDivisionRelLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the loop division rel with the primary key.
	 *
	 * @param loopDivisionRelId the primary key of the loop division rel
	 * @return the loop division rel
	 * @throws PortalException if a loop division rel with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.loop.model.LoopDivisionRel getLoopDivisionRel(
			long loopDivisionRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopDivisionRelLocalService.getLoopDivisionRel(
			loopDivisionRelId);
	}

	/**
	 * Returns a range of all the loop division rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopDivisionRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop division rels
	 * @param end the upper bound of the range of loop division rels (not inclusive)
	 * @return the range of loop division rels
	 */
	@Override
	public java.util.List<com.liferay.osb.loop.model.LoopDivisionRel>
		getLoopDivisionRels(int start, int end) {

		return _loopDivisionRelLocalService.getLoopDivisionRels(start, end);
	}

	/**
	 * Returns the number of loop division rels.
	 *
	 * @return the number of loop division rels
	 */
	@Override
	public int getLoopDivisionRelsCount() {
		return _loopDivisionRelLocalService.getLoopDivisionRelsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _loopDivisionRelLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopDivisionRelLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the loop division rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param loopDivisionRel the loop division rel
	 * @return the loop division rel that was updated
	 */
	@Override
	public com.liferay.osb.loop.model.LoopDivisionRel updateLoopDivisionRel(
		com.liferay.osb.loop.model.LoopDivisionRel loopDivisionRel) {

		return _loopDivisionRelLocalService.updateLoopDivisionRel(
			loopDivisionRel);
	}

	@Override
	public LoopDivisionRelLocalService getWrappedService() {
		return _loopDivisionRelLocalService;
	}

	@Override
	public void setWrappedService(
		LoopDivisionRelLocalService loopDivisionRelLocalService) {

		_loopDivisionRelLocalService = loopDivisionRelLocalService;
	}

	private LoopDivisionRelLocalService _loopDivisionRelLocalService;

}