/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.loop.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LoopTopicLocalService}.
 *
 * @author Ethan Bustad
 * @see LoopTopicLocalService
 * @generated
 */
@ProviderType
public class LoopTopicLocalServiceWrapper implements LoopTopicLocalService,
	ServiceWrapper<LoopTopicLocalService> {
	public LoopTopicLocalServiceWrapper(
		LoopTopicLocalService loopTopicLocalService) {
		_loopTopicLocalService = loopTopicLocalService;
	}

	@Override
	public com.liferay.osb.loop.model.LoopTopic addLoopTopic(long userId,
		String name, String description)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopTopicLocalService.addLoopTopic(userId, name, description);
	}

	/**
	* Adds the loop topic to the database. Also notifies the appropriate model listeners.
	*
	* @param loopTopic the loop topic
	* @return the loop topic that was added
	*/
	@Override
	public com.liferay.osb.loop.model.LoopTopic addLoopTopic(
		com.liferay.osb.loop.model.LoopTopic loopTopic) {
		return _loopTopicLocalService.addLoopTopic(loopTopic);
	}

	/**
	* Creates a new loop topic with the primary key. Does not add the loop topic to the database.
	*
	* @param loopTopicId the primary key for the new loop topic
	* @return the new loop topic
	*/
	@Override
	public com.liferay.osb.loop.model.LoopTopic createLoopTopic(
		long loopTopicId) {
		return _loopTopicLocalService.createLoopTopic(loopTopicId);
	}

	/**
	* Deletes the loop topic with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param loopTopicId the primary key of the loop topic
	* @return the loop topic that was removed
	* @throws PortalException if a loop topic with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.loop.model.LoopTopic deleteLoopTopic(
		long loopTopicId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopTopicLocalService.deleteLoopTopic(loopTopicId);
	}

	/**
	* Deletes the loop topic from the database. Also notifies the appropriate model listeners.
	*
	* @param loopTopic the loop topic
	* @return the loop topic that was removed
	*/
	@Override
	public com.liferay.osb.loop.model.LoopTopic deleteLoopTopic(
		com.liferay.osb.loop.model.LoopTopic loopTopic) {
		return _loopTopicLocalService.deleteLoopTopic(loopTopic);
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopTopicLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _loopTopicLocalService.dynamicQuery();
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
		return _loopTopicLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopTopicModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _loopTopicLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopTopicModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _loopTopicLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
		return _loopTopicLocalService.dynamicQueryCount(dynamicQuery);
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
		return _loopTopicLocalService.dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public com.liferay.osb.loop.model.LoopTopic fetchLoopTopic(long loopTopicId) {
		return _loopTopicLocalService.fetchLoopTopic(loopTopicId);
	}

	@Override
	public com.liferay.osb.loop.model.LoopTopic fetchLoopTopic(long companyId,
		String name) {
		return _loopTopicLocalService.fetchLoopTopic(companyId, name);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _loopTopicLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _loopTopicLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the loop topic with the primary key.
	*
	* @param loopTopicId the primary key of the loop topic
	* @return the loop topic
	* @throws PortalException if a loop topic with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.loop.model.LoopTopic getLoopTopic(long loopTopicId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopTopicLocalService.getLoopTopic(loopTopicId);
	}

	@Override
	public com.liferay.osb.loop.model.LoopTopic getLoopTopic(long companyId,
		String name) throws com.liferay.portal.kernel.exception.PortalException {
		return _loopTopicLocalService.getLoopTopic(companyId, name);
	}

	/**
	* Returns a range of all the loop topics.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.loop.model.impl.LoopTopicModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of loop topics
	* @param end the upper bound of the range of loop topics (not inclusive)
	* @return the range of loop topics
	*/
	@Override
	public java.util.List<com.liferay.osb.loop.model.LoopTopic> getLoopTopics(
		int start, int end) {
		return _loopTopicLocalService.getLoopTopics(start, end);
	}

	/**
	* Returns the number of loop topics.
	*
	* @return the number of loop topics
	*/
	@Override
	public int getLoopTopicsCount() {
		return _loopTopicLocalService.getLoopTopicsCount();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public String getOSGiServiceIdentifier() {
		return _loopTopicLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _loopTopicLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public boolean isLoopTopic(long companyId, String name) {
		return _loopTopicLocalService.isLoopTopic(companyId, name);
	}

	@Override
	public void updateLoopTopic(long userId, String name, String description)
		throws com.liferay.portal.kernel.exception.PortalException {
		_loopTopicLocalService.updateLoopTopic(userId, name, description);
	}

	/**
	* Updates the loop topic in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param loopTopic the loop topic
	* @return the loop topic that was updated
	*/
	@Override
	public com.liferay.osb.loop.model.LoopTopic updateLoopTopic(
		com.liferay.osb.loop.model.LoopTopic loopTopic) {
		return _loopTopicLocalService.updateLoopTopic(loopTopic);
	}

	@Override
	public LoopTopicLocalService getWrappedService() {
		return _loopTopicLocalService;
	}

	@Override
	public void setWrappedService(LoopTopicLocalService loopTopicLocalService) {
		_loopTopicLocalService = loopTopicLocalService;
	}

	private LoopTopicLocalService _loopTopicLocalService;
}