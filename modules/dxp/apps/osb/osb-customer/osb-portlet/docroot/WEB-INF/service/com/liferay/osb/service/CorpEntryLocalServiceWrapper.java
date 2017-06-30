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
 * This class is a wrapper for {@link CorpEntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CorpEntryLocalService
 * @generated
 */
public class CorpEntryLocalServiceWrapper implements CorpEntryLocalService,
	ServiceWrapper<CorpEntryLocalService> {
	public CorpEntryLocalServiceWrapper(
		CorpEntryLocalService corpEntryLocalService) {
		_corpEntryLocalService = corpEntryLocalService;
	}

	/**
	* Adds the corp entry to the database. Also notifies the appropriate model listeners.
	*
	* @param corpEntry the corp entry
	* @return the corp entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry addCorpEntry(
		com.liferay.osb.model.CorpEntry corpEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.addCorpEntry(corpEntry);
	}

	/**
	* Creates a new corp entry with the primary key. Does not add the corp entry to the database.
	*
	* @param corpEntryId the primary key for the new corp entry
	* @return the new corp entry
	*/
	public com.liferay.osb.model.CorpEntry createCorpEntry(long corpEntryId) {
		return _corpEntryLocalService.createCorpEntry(corpEntryId);
	}

	/**
	* Deletes the corp entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpEntryId the primary key of the corp entry
	* @return the corp entry that was removed
	* @throws PortalException if a corp entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry deleteCorpEntry(long corpEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.deleteCorpEntry(corpEntryId);
	}

	/**
	* Deletes the corp entry from the database. Also notifies the appropriate model listeners.
	*
	* @param corpEntry the corp entry
	* @return the corp entry that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry deleteCorpEntry(
		com.liferay.osb.model.CorpEntry corpEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.deleteCorpEntry(corpEntry);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _corpEntryLocalService.dynamicQuery();
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
		return _corpEntryLocalService.dynamicQuery(dynamicQuery);
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
		return _corpEntryLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _corpEntryLocalService.dynamicQuery(dynamicQuery, start, end,
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
		return _corpEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.CorpEntry fetchCorpEntry(long corpEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.fetchCorpEntry(corpEntryId);
	}

	/**
	* Returns the corp entry with the primary key.
	*
	* @param corpEntryId the primary key of the corp entry
	* @return the corp entry
	* @throws PortalException if a corp entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry getCorpEntry(long corpEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.getCorpEntry(corpEntryId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the corp entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of corp entries
	* @param end the upper bound of the range of corp entries (not inclusive)
	* @return the range of corp entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpEntry> getCorpEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.getCorpEntries(start, end);
	}

	/**
	* Returns the number of corp entries.
	*
	* @return the number of corp entries
	* @throws SystemException if a system exception occurred
	*/
	public int getCorpEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.getCorpEntriesCount();
	}

	/**
	* Updates the corp entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param corpEntry the corp entry
	* @return the corp entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry updateCorpEntry(
		com.liferay.osb.model.CorpEntry corpEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.updateCorpEntry(corpEntry);
	}

	/**
	* Updates the corp entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param corpEntry the corp entry
	* @param merge whether to merge the corp entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the corp entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.CorpEntry updateCorpEntry(
		com.liferay.osb.model.CorpEntry corpEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.updateCorpEntry(corpEntry, merge);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addCorpGroupCorpEntry(long corpGroupId, long corpEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpEntryLocalService.addCorpGroupCorpEntry(corpGroupId, corpEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addCorpGroupCorpEntry(long corpGroupId,
		com.liferay.osb.model.CorpEntry corpEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpEntryLocalService.addCorpGroupCorpEntry(corpGroupId, corpEntry);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addCorpGroupCorpEntries(long corpGroupId, long[] corpEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpEntryLocalService.addCorpGroupCorpEntries(corpGroupId, corpEntryIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addCorpGroupCorpEntries(long corpGroupId,
		java.util.List<com.liferay.osb.model.CorpEntry> CorpEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpEntryLocalService.addCorpGroupCorpEntries(corpGroupId, CorpEntries);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void clearCorpGroupCorpEntries(long corpGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpEntryLocalService.clearCorpGroupCorpEntries(corpGroupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteCorpGroupCorpEntry(long corpGroupId, long corpEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpEntryLocalService.deleteCorpGroupCorpEntry(corpGroupId, corpEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteCorpGroupCorpEntry(long corpGroupId,
		com.liferay.osb.model.CorpEntry corpEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpEntryLocalService.deleteCorpGroupCorpEntry(corpGroupId, corpEntry);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteCorpGroupCorpEntries(long corpGroupId, long[] corpEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpEntryLocalService.deleteCorpGroupCorpEntries(corpGroupId,
			corpEntryIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteCorpGroupCorpEntries(long corpGroupId,
		java.util.List<com.liferay.osb.model.CorpEntry> CorpEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpEntryLocalService.deleteCorpGroupCorpEntries(corpGroupId,
			CorpEntries);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpEntry> getCorpGroupCorpEntries(
		long corpGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.getCorpGroupCorpEntries(corpGroupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpEntry> getCorpGroupCorpEntries(
		long corpGroupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.getCorpGroupCorpEntries(corpGroupId,
			start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.CorpEntry> getCorpGroupCorpEntries(
		long corpGroupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.getCorpGroupCorpEntries(corpGroupId,
			start, end, orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public int getCorpGroupCorpEntriesCount(long corpGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.getCorpGroupCorpEntriesCount(corpGroupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasCorpGroupCorpEntry(long corpGroupId, long corpEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.hasCorpGroupCorpEntry(corpGroupId,
			corpEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public boolean hasCorpGroupCorpEntries(long corpGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.hasCorpGroupCorpEntries(corpGroupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void setCorpGroupCorpEntries(long corpGroupId, long[] corpEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		_corpEntryLocalService.setCorpGroupCorpEntries(corpGroupId, corpEntryIds);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _corpEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_corpEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _corpEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.CorpEntry addCorpEntry(long userId,
		java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.io.File logoFile, java.lang.String street1,
		java.lang.String street2, java.lang.String street3,
		java.lang.String city, java.lang.String zip, long regionId,
		long countryId, java.lang.String contactEmailAddress,
		java.lang.String profileEmailAddress, java.lang.String phoneNumber,
		java.lang.String faxNumber, java.lang.String website,
		java.lang.String dossieraAccountKey,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.addCorpEntry(userId, name,
			descriptionMap, logoFile, street1, street2, street3, city, zip,
			regionId, countryId, contactEmailAddress, profileEmailAddress,
			phoneNumber, faxNumber, website, dossieraAccountKey, serviceContext);
	}

	public com.liferay.osb.model.CorpEntry fetchCorpEntry(
		java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.fetchCorpEntry(dossieraAccountKey);
	}

	public com.liferay.osb.model.CorpEntry fetchOrganizationCorpEntry(
		long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.fetchOrganizationCorpEntry(organizationId);
	}

	public com.liferay.osb.model.CorpEntry getCorpEntry(
		java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.getCorpEntry(dossieraAccountKey);
	}

	public java.util.List<com.liferay.osb.model.CorpEntry> getCorpGroupCorpEntries(
		long userId, long corpGroupId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.getCorpGroupCorpEntries(userId,
			corpGroupId, status, start, end, obc);
	}

	public com.liferay.osb.model.CorpEntry getOrganizationCorpEntry(
		long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.getOrganizationCorpEntry(organizationId);
	}

	public java.util.List<com.liferay.osb.model.CorpEntry> getUserCorpEntries(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.getUserCorpEntries(userId, start, end, obc);
	}

	public java.util.List<com.liferay.osb.model.CorpEntry> getUserCorpEntries(
		long userId, long roleId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.getUserCorpEntries(userId, roleId,
			status, start, end, obc);
	}

	public java.util.List<com.liferay.osb.model.CorpEntry> getUserCorpEntries(
		long userId, java.lang.String roleName, int type, int status,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.getUserCorpEntries(userId, roleName,
			type, status, start, end, obc);
	}

	public java.util.List<com.liferay.osb.model.CorpEntry> getUserCorpEntries(
		long userId, java.lang.String roleName, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.getUserCorpEntries(userId, roleName,
			status, start, end, obc);
	}

	public boolean hasUserCorpEntry(long userId, long corpEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.hasUserCorpEntry(userId, corpEntryId);
	}

	public boolean hasUserCorpEntryRole(long userId, long corpEntryId,
		long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.hasUserCorpEntryRole(userId, corpEntryId,
			roleId);
	}

	public boolean hasUserCorpEntryRole(long userId, long corpEntryId,
		java.lang.String roleName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.hasUserCorpEntryRole(userId, corpEntryId,
			roleName);
	}

	public com.liferay.osb.model.CorpEntry mergeCorpEntry(long userId,
		long corpEntryId, long mergeCorpEntryId, java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		long logoId, java.lang.String street1, java.lang.String street2,
		java.lang.String street3, java.lang.String city, java.lang.String zip,
		long regionId, long countryId, java.lang.String contactEmailAddress,
		java.lang.String profileEmailAddress, java.lang.String phoneNumber,
		java.lang.String faxNumber, java.lang.String website,
		java.lang.String dossieraAccountKey,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.mergeCorpEntry(userId, corpEntryId,
			mergeCorpEntryId, name, descriptionMap, logoId, street1, street2,
			street3, city, zip, regionId, countryId, contactEmailAddress,
			profileEmailAddress, phoneNumber, faxNumber, website,
			dossieraAccountKey, serviceContext);
	}

	public java.util.List<com.liferay.osb.model.CorpEntry> search(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.search(name, start, end, obc);
	}

	public java.util.List<com.liferay.osb.model.CorpEntry> search(
		java.lang.String name, int status, long[] notCorpEntryIds, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.search(name, status, notCorpEntryIds,
			start, end, obc);
	}

	public int searchCount(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.searchCount(name);
	}

	public int searchCount(java.lang.String name, int status,
		long[] notCorpEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.searchCount(name, status, notCorpEntryIds);
	}

	public com.liferay.osb.model.CorpEntry updateCorpEntry(long corpEntryId,
		java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.io.File logoFile, java.lang.String street1,
		java.lang.String street2, java.lang.String street3,
		java.lang.String city, java.lang.String zip, long regionId,
		long countryId, java.lang.String contactEmailAddress,
		java.lang.String profileEmailAddress, java.lang.String phoneNumber,
		java.lang.String faxNumber, java.lang.String website,
		java.lang.String dossieraAccountKey,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.updateCorpEntry(corpEntryId, name,
			descriptionMap, logoFile, street1, street2, street3, city, zip,
			regionId, countryId, contactEmailAddress, profileEmailAddress,
			phoneNumber, faxNumber, website, dossieraAccountKey, serviceContext);
	}

	public void updateSite(long corpEntryId, int status, boolean autoFriendlyURL)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_corpEntryLocalService.updateSite(corpEntryId, status, autoFriendlyURL);
	}

	public com.liferay.osb.model.CorpEntry updateStatus(long userId,
		long corpEntryId, int status, java.lang.String statusMessage)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpEntryLocalService.updateStatus(userId, corpEntryId, status,
			statusMessage);
	}

	public void validateMembership(long userId, long corpEntryId, long roleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_corpEntryLocalService.validateMembership(userId, corpEntryId, roleId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CorpEntryLocalService getWrappedCorpEntryLocalService() {
		return _corpEntryLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCorpEntryLocalService(
		CorpEntryLocalService corpEntryLocalService) {
		_corpEntryLocalService = corpEntryLocalService;
	}

	public CorpEntryLocalService getWrappedService() {
		return _corpEntryLocalService;
	}

	public void setWrappedService(CorpEntryLocalService corpEntryLocalService) {
		_corpEntryLocalService = corpEntryLocalService;
	}

	private CorpEntryLocalService _corpEntryLocalService;
}