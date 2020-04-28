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
 * Provides a wrapper for {@link LoopParticipantAssignmentLocalService}.
 *
 * @author Ethan Bustad
 * @see LoopParticipantAssignmentLocalService
 * @generated
 */
public class LoopParticipantAssignmentLocalServiceWrapper
	implements LoopParticipantAssignmentLocalService,
			   ServiceWrapper<LoopParticipantAssignmentLocalService> {

	public LoopParticipantAssignmentLocalServiceWrapper(
		LoopParticipantAssignmentLocalService
			loopParticipantAssignmentLocalService) {

		_loopParticipantAssignmentLocalService =
			loopParticipantAssignmentLocalService;
	}

	/**
	 * Adds the loop participant assignment to the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopParticipantAssignment the loop participant assignment
	 * @return the loop participant assignment that was added
	 */
	@Override
	public com.liferay.osb.loop.model.LoopParticipantAssignment
		addLoopParticipantAssignment(
			com.liferay.osb.loop.model.LoopParticipantAssignment
				loopParticipantAssignment) {

		return _loopParticipantAssignmentLocalService.
			addLoopParticipantAssignment(loopParticipantAssignment);
	}

	/**
	 * Creates a new loop participant assignment with the primary key. Does not add the loop participant assignment to the database.
	 *
	 * @param loopParticipantAssignmentId the primary key for the new loop participant assignment
	 * @return the new loop participant assignment
	 */
	@Override
	public com.liferay.osb.loop.model.LoopParticipantAssignment
		createLoopParticipantAssignment(long loopParticipantAssignmentId) {

		return _loopParticipantAssignmentLocalService.
			createLoopParticipantAssignment(loopParticipantAssignmentId);
	}

	/**
	 * Deletes the loop participant assignment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopParticipantAssignmentId the primary key of the loop participant assignment
	 * @return the loop participant assignment that was removed
	 * @throws PortalException if a loop participant assignment with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.loop.model.LoopParticipantAssignment
			deleteLoopParticipantAssignment(long loopParticipantAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopParticipantAssignmentLocalService.
			deleteLoopParticipantAssignment(loopParticipantAssignmentId);
	}

	/**
	 * Deletes the loop participant assignment from the database. Also notifies the appropriate model listeners.
	 *
	 * @param loopParticipantAssignment the loop participant assignment
	 * @return the loop participant assignment that was removed
	 */
	@Override
	public com.liferay.osb.loop.model.LoopParticipantAssignment
		deleteLoopParticipantAssignment(
			com.liferay.osb.loop.model.LoopParticipantAssignment
				loopParticipantAssignment) {

		return _loopParticipantAssignmentLocalService.
			deleteLoopParticipantAssignment(loopParticipantAssignment);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopParticipantAssignmentLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loopParticipantAssignmentLocalService.dynamicQuery();
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

		return _loopParticipantAssignmentLocalService.dynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopParticipantAssignmentModelImpl</code>.
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

		return _loopParticipantAssignmentLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopParticipantAssignmentModelImpl</code>.
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

		return _loopParticipantAssignmentLocalService.dynamicQuery(
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

		return _loopParticipantAssignmentLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _loopParticipantAssignmentLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.loop.model.LoopParticipantAssignment
		fetchLoopParticipantAssignment(long loopParticipantAssignmentId) {

		return _loopParticipantAssignmentLocalService.
			fetchLoopParticipantAssignment(loopParticipantAssignmentId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _loopParticipantAssignmentLocalService.
			getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _loopParticipantAssignmentLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the loop participant assignment with the primary key.
	 *
	 * @param loopParticipantAssignmentId the primary key of the loop participant assignment
	 * @return the loop participant assignment
	 * @throws PortalException if a loop participant assignment with the primary key could not be found
	 */
	@Override
	public com.liferay.osb.loop.model.LoopParticipantAssignment
			getLoopParticipantAssignment(long loopParticipantAssignmentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopParticipantAssignmentLocalService.
			getLoopParticipantAssignment(loopParticipantAssignmentId);
	}

	/**
	 * Returns a range of all the loop participant assignments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.osb.loop.model.impl.LoopParticipantAssignmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of loop participant assignments
	 * @param end the upper bound of the range of loop participant assignments (not inclusive)
	 * @return the range of loop participant assignments
	 */
	@Override
	public java.util.List<com.liferay.osb.loop.model.LoopParticipantAssignment>
		getLoopParticipantAssignments(int start, int end) {

		return _loopParticipantAssignmentLocalService.
			getLoopParticipantAssignments(start, end);
	}

	/**
	 * Returns the number of loop participant assignments.
	 *
	 * @return the number of loop participant assignments
	 */
	@Override
	public int getLoopParticipantAssignmentsCount() {
		return _loopParticipantAssignmentLocalService.
			getLoopParticipantAssignmentsCount();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _loopParticipantAssignmentLocalService.
			getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _loopParticipantAssignmentLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the loop participant assignment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param loopParticipantAssignment the loop participant assignment
	 * @return the loop participant assignment that was updated
	 */
	@Override
	public com.liferay.osb.loop.model.LoopParticipantAssignment
		updateLoopParticipantAssignment(
			com.liferay.osb.loop.model.LoopParticipantAssignment
				loopParticipantAssignment) {

		return _loopParticipantAssignmentLocalService.
			updateLoopParticipantAssignment(loopParticipantAssignment);
	}

	@Override
	public LoopParticipantAssignmentLocalService getWrappedService() {
		return _loopParticipantAssignmentLocalService;
	}

	@Override
	public void setWrappedService(
		LoopParticipantAssignmentLocalService
			loopParticipantAssignmentLocalService) {

		_loopParticipantAssignmentLocalService =
			loopParticipantAssignmentLocalService;
	}

	private LoopParticipantAssignmentLocalService
		_loopParticipantAssignmentLocalService;

}