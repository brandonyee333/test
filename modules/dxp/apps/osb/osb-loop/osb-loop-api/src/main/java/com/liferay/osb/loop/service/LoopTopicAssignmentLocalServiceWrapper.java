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
 * Provides a wrapper for {@link LoopTopicAssignmentLocalService}.
 *
 * @author Ethan Bustad
 * @see LoopTopicAssignmentLocalService
 * @generated
 */
public class LoopTopicAssignmentLocalServiceWrapper
	implements LoopTopicAssignmentLocalService,
			   ServiceWrapper<LoopTopicAssignmentLocalService> {

	public LoopTopicAssignmentLocalServiceWrapper(
		LoopTopicAssignmentLocalService loopTopicAssignmentLocalService) {

		_loopTopicAssignmentLocalService = loopTopicAssignmentLocalService;
	}

	/**
	 * Adds the loop topic assignment to the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopTopicAssignment the loop topic assignment
	 * @return the loop topic assignment that was added
	 */
	@Override
	public com.liferay.osb.loop.model.LoopTopicAssignment
		addLoopTopicAssignment(
			com.liferay.osb.loop.model.LoopTopicAssignment
				loopTopicAssignment) {

		return _loopTopicAssignmentLocalService.addLoopTopicAssignment(
			loopTopicAssignment);
	}

	/**
	 * Creates a new loop topic assignment with the primary key. Does not add the loop topic assignment to the database.
	 *
	 * @param loopTopicAssignmentId the primary key for the new loop topic assignment
	 * @return the new loop topic assignment
	 */
	@Override
	public com.liferay.osb.loop.model.LoopTopicAssignment
		createLoopTopicAssignment(long loopTopicAssignmentId) {

		return _loopTopicAssignmentLocalService.createLoopTopicAssignment(
			loopTopicAssignmentId);
	}

	/**
	 * Deletes the loop topic assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopTopicAssignmentId the primary key of the loop topic assignment
	 * @return the loop topic assignment that was removed
	 * @throws PortalException if a loop topic assignment with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.loop.model.LoopTopicAssignment
			deleteLoopTopicAssignment(long loopTopicAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopTopicAssignmentLocalService.deleteLoopTopicAssignment(
			loopTopicAssignmentId);
	}

	/**
	 * Deletes the loop topic assignment from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopTopicAssignment the loop topic assignment
	 * @return the loop topic assignment that was removed
	 */
	@Override
	public com.liferay.osb.loop.model.LoopTopicAssignment
		deleteLoopTopicAssignment(
			com.liferay.osb.loop.model.LoopTopicAssignment
				loopTopicAssignment) {

		return _loopTopicAssignmentLocalService.deleteLoopTopicAssignment(
			loopTopicAssignment);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopTopicAssignmentLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loopTopicAssignmentLocalService.dynamicQuery();
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

		return _loopTopicAssignmentLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopTopicAssignmentModelImpl</code>.
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

		return _loopTopicAssignmentLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopTopicAssignmentModelImpl</code>.
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

		return _loopTopicAssignmentLocalService.dynamicQuery(
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

		return _loopTopicAssignmentLocalService.dynamicQueryCount(dynamicQuery);
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

		return _loopTopicAssignmentLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.loop.model.LoopTopicAssignment
		fetchLoopTopicAssignment(long loopTopicAssignmentId) {

		return _loopTopicAssignmentLocalService.fetchLoopTopicAssignment(
			loopTopicAssignmentId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _loopTopicAssignmentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _loopTopicAssignmentLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the loop topic assignment with the primary key.
	 *
	 * @param loopTopicAssignmentId the primary key of the loop topic assignment
	 * @return the loop topic assignment
	 * @throws PortalException if a loop topic assignment with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.loop.model.LoopTopicAssignment
			getLoopTopicAssignment(long loopTopicAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopTopicAssignmentLocalService.getLoopTopicAssignment(
			loopTopicAssignmentId);
	}

	/**
	 * Returns a range of all the loop topic assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopTopicAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop topic assignments
	 * @param end the upper bound of the range of loop topic assignments (not inclusive)
	 * @return the range of loop topic assignments
	 */
	@Override
	public java.util.List<com.liferay.osb.loop.model.LoopTopicAssignment>
		getLoopTopicAssignments(int start, int end) {

		return _loopTopicAssignmentLocalService.getLoopTopicAssignments(
			start, end);
	}

	/**
	 * Returns the number of loop topic assignments.
	 *
	 * @return the number of loop topic assignments
	 */
	@Override
	public int getLoopTopicAssignmentsCount() {
		return _loopTopicAssignmentLocalService.getLoopTopicAssignmentsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _loopTopicAssignmentLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopTopicAssignmentLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the loop topic assignment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param loopTopicAssignment the loop topic assignment
	 * @return the loop topic assignment that was updated
	 */
	@Override
	public com.liferay.osb.loop.model.LoopTopicAssignment
		updateLoopTopicAssignment(
			com.liferay.osb.loop.model.LoopTopicAssignment
				loopTopicAssignment) {

		return _loopTopicAssignmentLocalService.updateLoopTopicAssignment(
			loopTopicAssignment);
	}

	@Override
	public LoopTopicAssignmentLocalService getWrappedService() {
		return _loopTopicAssignmentLocalService;
	}

	@Override
	public void setWrappedService(
		LoopTopicAssignmentLocalService loopTopicAssignmentLocalService) {

		_loopTopicAssignmentLocalService = loopTopicAssignmentLocalService;
	}

	private LoopTopicAssignmentLocalService _loopTopicAssignmentLocalService;

}