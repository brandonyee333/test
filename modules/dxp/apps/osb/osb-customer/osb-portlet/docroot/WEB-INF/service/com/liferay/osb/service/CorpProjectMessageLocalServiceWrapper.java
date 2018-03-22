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

package com.liferay.osb.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CorpProjectMessageLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectMessageLocalService
 * @generated
 */
@ProviderType
public class CorpProjectMessageLocalServiceWrapper
	implements CorpProjectMessageLocalService,
		ServiceWrapper<CorpProjectMessageLocalService> {
	public CorpProjectMessageLocalServiceWrapper(
		CorpProjectMessageLocalService corpProjectMessageLocalService) {
		_corpProjectMessageLocalService = corpProjectMessageLocalService;
	}

	/**
	* Adds the corp project message to the database. Also notifies the appropriate model listeners.
	*
	* @param corpProjectMessage the corp project message
	* @return the corp project message that was added
	*/
	@Override
	public com.liferay.osb.model.CorpProjectMessage addCorpProjectMessage(
		com.liferay.osb.model.CorpProjectMessage corpProjectMessage) {
		return _corpProjectMessageLocalService.addCorpProjectMessage(corpProjectMessage);
	}

	/**
	* Creates a new corp project message with the primary key. Does not add the corp project message to the database.
	*
	* @param corpProjectMessageId the primary key for the new corp project message
	* @return the new corp project message
	*/
	@Override
	public com.liferay.osb.model.CorpProjectMessage createCorpProjectMessage(
		long corpProjectMessageId) {
		return _corpProjectMessageLocalService.createCorpProjectMessage(corpProjectMessageId);
	}

	/**
	* Deletes the corp project message from the database. Also notifies the appropriate model listeners.
	*
	* @param corpProjectMessage the corp project message
	* @return the corp project message that was removed
	*/
	@Override
	public com.liferay.osb.model.CorpProjectMessage deleteCorpProjectMessage(
		com.liferay.osb.model.CorpProjectMessage corpProjectMessage) {
		return _corpProjectMessageLocalService.deleteCorpProjectMessage(corpProjectMessage);
	}

	/**
	* Deletes the corp project message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpProjectMessageId the primary key of the corp project message
	* @return the corp project message that was removed
	* @throws PortalException if a corp project message with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.CorpProjectMessage deleteCorpProjectMessage(
		long corpProjectMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _corpProjectMessageLocalService.deleteCorpProjectMessage(corpProjectMessageId);
	}

	@Override
	public com.liferay.osb.model.CorpProjectMessage fetchCorpProjectMessage(
		long corpProjectMessageId) {
		return _corpProjectMessageLocalService.fetchCorpProjectMessage(corpProjectMessageId);
	}

	/**
	* Returns the corp project message with the primary key.
	*
	* @param corpProjectMessageId the primary key of the corp project message
	* @return the corp project message
	* @throws PortalException if a corp project message with the primary key could not be found
	*/
	@Override
	public com.liferay.osb.model.CorpProjectMessage getCorpProjectMessage(
		long corpProjectMessageId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _corpProjectMessageLocalService.getCorpProjectMessage(corpProjectMessageId);
	}

	/**
	* Updates the corp project message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param corpProjectMessage the corp project message
	* @return the corp project message that was updated
	*/
	@Override
	public com.liferay.osb.model.CorpProjectMessage updateCorpProjectMessage(
		com.liferay.osb.model.CorpProjectMessage corpProjectMessage) {
		return _corpProjectMessageLocalService.updateCorpProjectMessage(corpProjectMessage);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return _corpProjectMessageLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _corpProjectMessageLocalService.dynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return _corpProjectMessageLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _corpProjectMessageLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _corpProjectMessageLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of corp project messages.
	*
	* @return the number of corp project messages
	*/
	@Override
	public int getCorpProjectMessagesCount() {
		return _corpProjectMessageLocalService.getCorpProjectMessagesCount();
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _corpProjectMessageLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _corpProjectMessageLocalService.getOSGiServiceIdentifier();
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
		return _corpProjectMessageLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _corpProjectMessageLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _corpProjectMessageLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the corp project messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.CorpProjectMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @return the range of corp project messages
	*/
	@Override
	public java.util.List<com.liferay.osb.model.CorpProjectMessage> getCorpProjectMessages(
		int start, int end) {
		return _corpProjectMessageLocalService.getCorpProjectMessages(start, end);
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
		return _corpProjectMessageLocalService.dynamicQueryCount(dynamicQuery);
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
		return _corpProjectMessageLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public void checkCorpProjects() throws java.lang.Exception {
		_corpProjectMessageLocalService.checkCorpProjects();
	}

	@Override
	public CorpProjectMessageLocalService getWrappedService() {
		return _corpProjectMessageLocalService;
	}

	@Override
	public void setWrappedService(
		CorpProjectMessageLocalService corpProjectMessageLocalService) {
		_corpProjectMessageLocalService = corpProjectMessageLocalService;
	}

	private CorpProjectMessageLocalService _corpProjectMessageLocalService;
}