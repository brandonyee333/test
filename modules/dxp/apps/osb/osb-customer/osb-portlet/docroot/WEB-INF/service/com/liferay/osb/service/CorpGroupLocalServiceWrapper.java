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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CorpGroupLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CorpGroupLocalService
 * @generated
 */
public class CorpGroupLocalServiceWrapper implements CorpGroupLocalService,
	ServiceWrapper<CorpGroupLocalService> {
	public CorpGroupLocalServiceWrapper(
		CorpGroupLocalService corpGroupLocalService) {
		_corpGroupLocalService = corpGroupLocalService;
	}

	/**
	* Adds the corp group to the database. Also notifies the appropriate model listeners.
	*
	* @param corpGroup the corp group
	* @return the corp group that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpGroup addCorpGroup(
		com.liferay.osb.model.CorpGroup corpGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.addCorpGroup(corpGroup);
	}

	/**
	* Creates a new corp group with the primary key. Does not add the corp group to the database.
	*
	* @param corpGroupId the primary key for the new corp group
	* @return the new corp group
	*/
	public com.liferay.osb.model.CorpGroup createCorpGroup(long corpGroupId) {
		return _corpGroupLocalService.createCorpGroup(corpGroupId);
	}

	/**
	* Deletes the corp group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpGroupId the primary key of the corp group
	* @return the corp group that was removed
	* @throws PortalException if a corp group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpGroup deleteCorpGroup(long corpGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.deleteCorpGroup(corpGroupId);
	}

	/**
	* Deletes the corp group from the database. Also notifies the appropriate model listeners.
	*
	* @param corpGroup the corp group
	* @return the corp group that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpGroup deleteCorpGroup(
		com.liferay.osb.model.CorpGroup corpGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.deleteCorpGroup(corpGroup);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _corpGroupLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.CorpGroup fetchCorpGroup(long corpGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.fetchCorpGroup(corpGroupId);
	}

	/**
	* Returns the corp group with the primary key.
	*
	* @param corpGroupId the primary key of the corp group
	* @return the corp group
	* @throws PortalException if a corp group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpGroup getCorpGroup(long corpGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.getCorpGroup(corpGroupId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the corp groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of corp groups
	* @param end the upper bound of the range of corp groups (not inclusive)
	* @return the range of corp groups
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpGroup> getCorpGroups(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.getCorpGroups(start, end);
	}

	/**
	* Returns the number of corp groups.
	*
	* @return the number of corp groups
	* @throws SystemException if a system exception occurred
	*/
	public int getCorpGroupsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.getCorpGroupsCount();
	}

	/**
	* Updates the corp group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param corpGroup the corp group
	* @return the corp group that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpGroup updateCorpGroup(
		com.liferay.osb.model.CorpGroup corpGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.updateCorpGroup(corpGroup);
	}

	/**
	* Updates the corp group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param corpGroup the corp group
	* @param merge whether to merge the corp group with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the corp group that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpGroup updateCorpGroup(
		com.liferay.osb.model.CorpGroup corpGroup, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.updateCorpGroup(corpGroup, merge);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addCorpEntryCorpGroup(long corpEntryId, long corpGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpGroupLocalService.addCorpEntryCorpGroup(corpEntryId, corpGroupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addCorpEntryCorpGroup(long corpEntryId,
		com.liferay.osb.model.CorpGroup corpGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpGroupLocalService.addCorpEntryCorpGroup(corpEntryId, corpGroup);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addCorpEntryCorpGroups(long corpEntryId, long[] corpGroupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpGroupLocalService.addCorpEntryCorpGroups(corpEntryId, corpGroupIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addCorpEntryCorpGroups(long corpEntryId,
		java.util.List<com.liferay.osb.model.CorpGroup> CorpGroups)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpGroupLocalService.addCorpEntryCorpGroups(corpEntryId, CorpGroups);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void clearCorpEntryCorpGroups(long corpEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpGroupLocalService.clearCorpEntryCorpGroups(corpEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteCorpEntryCorpGroup(long corpEntryId, long corpGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpGroupLocalService.deleteCorpEntryCorpGroup(corpEntryId, corpGroupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteCorpEntryCorpGroup(long corpEntryId,
		com.liferay.osb.model.CorpGroup corpGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpGroupLocalService.deleteCorpEntryCorpGroup(corpEntryId, corpGroup);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteCorpEntryCorpGroups(long corpEntryId, long[] corpGroupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpGroupLocalService.deleteCorpEntryCorpGroups(corpEntryId,
			corpGroupIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteCorpEntryCorpGroups(long corpEntryId,
		java.util.List<com.liferay.osb.model.CorpGroup> CorpGroups)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpGroupLocalService.deleteCorpEntryCorpGroups(corpEntryId, CorpGroups);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpGroup> getCorpEntryCorpGroups(
		long corpEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.getCorpEntryCorpGroups(corpEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpGroup> getCorpEntryCorpGroups(
		long corpEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.getCorpEntryCorpGroups(corpEntryId,
			start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpGroup> getCorpEntryCorpGroups(
		long corpEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.getCorpEntryCorpGroups(corpEntryId,
			start, end, orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public int getCorpEntryCorpGroupsCount(long corpEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.getCorpEntryCorpGroupsCount(corpEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasCorpEntryCorpGroup(long corpEntryId, long corpGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.hasCorpEntryCorpGroup(corpEntryId,
			corpGroupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasCorpEntryCorpGroups(long corpEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.hasCorpEntryCorpGroups(corpEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void setCorpEntryCorpGroups(long corpEntryId, long[] corpGroupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpGroupLocalService.setCorpEntryCorpGroups(corpEntryId, corpGroupIds);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _corpGroupLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_corpGroupLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _corpGroupLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.CorpGroup addCorpGroup(long userId,
		java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.io.File logoFile, java.lang.String emailAddress,
		java.lang.String website,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.addCorpGroup(userId, name,
			descriptionMap, logoFile, emailAddress, website, serviceContext);
	}

	public com.liferay.osb.model.CorpGroup fetchOrganizationCorpGroup(
		long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.fetchOrganizationCorpGroup(organizationId);
	}

	public com.liferay.osb.model.CorpGroup getOrganizationCorpGroup(
		long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.getOrganizationCorpGroup(organizationId);
	}

	public java.util.List<com.liferay.osb.model.CorpGroup> getUserCorpGroups(
		long userId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.getUserCorpGroups(userId, status, start,
			end, obc);
	}

	public java.util.List<com.liferay.osb.model.CorpGroup> search(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.search(name, start, end, obc);
	}

	public java.util.List<com.liferay.osb.model.CorpGroup> search(
		java.lang.String name, long[] notCorpGroupIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.search(name, notCorpGroupIds, start, end,
			obc);
	}

	public int searchCount(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.searchCount(name);
	}

	public int searchCount(java.lang.String name, long[] notCorpGroupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.searchCount(name, notCorpGroupIds);
	}

	public com.liferay.osb.model.CorpGroup updateCorpGroup(long corpGroupId,
		java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.io.File logoFile, java.lang.String emailAddress,
		java.lang.String website,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpGroupLocalService.updateCorpGroup(corpGroupId, name,
			descriptionMap, logoFile, emailAddress, website, serviceContext);
	}

	public void updateSite(long corpGroupId, boolean autoFriendlyURL)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_corpGroupLocalService.updateSite(corpGroupId, autoFriendlyURL);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CorpGroupLocalService getWrappedCorpGroupLocalService() {
		return _corpGroupLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCorpGroupLocalService(
		CorpGroupLocalService corpGroupLocalService) {
		_corpGroupLocalService = corpGroupLocalService;
	}

	public CorpGroupLocalService getWrappedService() {
		return _corpGroupLocalService;
	}

	public void setWrappedService(CorpGroupLocalService corpGroupLocalService) {
		_corpGroupLocalService = corpGroupLocalService;
	}

	private CorpGroupLocalService _corpGroupLocalService;
}