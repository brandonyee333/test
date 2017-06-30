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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the app entry local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AppEntryLocalServiceUtil
 * @see com.liferay.osb.service.base.AppEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AppEntryLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AppEntryLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AppEntryLocalServiceUtil} to access the app entry local service. Add custom service methods to {@link com.liferay.osb.service.impl.AppEntryLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the app entry to the database. Also notifies the appropriate model listeners.
	*
	* @param appEntry the app entry
	* @return the app entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry addAppEntry(
		com.liferay.osb.model.AppEntry appEntry)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new app entry with the primary key. Does not add the app entry to the database.
	*
	* @param appEntryId the primary key for the new app entry
	* @return the new app entry
	*/
	public com.liferay.osb.model.AppEntry createAppEntry(long appEntryId);

	/**
	* Deletes the app entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param appEntryId the primary key of the app entry
	* @return the app entry that was removed
	* @throws PortalException if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry deleteAppEntry(long appEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the app entry from the database. Also notifies the appropriate model listeners.
	*
	* @param appEntry the app entry
	* @return the app entry that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry deleteAppEntry(
		com.liferay.osb.model.AppEntry appEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

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
		throws com.liferay.portal.kernel.exception.SystemException;

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
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.osb.model.AppEntry fetchAppEntry(long appEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the app entry with the primary key.
	*
	* @param appEntryId the primary key of the app entry
	* @return the app entry
	* @throws PortalException if a app entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.osb.model.AppEntry getAppEntry(long appEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the app entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of app entries
	* @param end the upper bound of the range of app entries (not inclusive)
	* @return the range of app entries
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.AppEntry> getAppEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of app entries.
	*
	* @return the number of app entries
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAppEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the app entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appEntry the app entry
	* @return the app entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry updateAppEntry(
		com.liferay.osb.model.AppEntry appEntry)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the app entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param appEntry the app entry
	* @param merge whether to merge the app entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the app entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AppEntry updateAppEntry(
		com.liferay.osb.model.AppEntry appEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	public com.liferay.osb.model.AppEntry addAppEntry(long userId,
		long developerEntryId, java.lang.String title,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String website, java.lang.String demoWebsite,
		java.lang.String documentationWebsite,
		java.lang.String referenceWebsite, java.lang.String sourceCodeWebsite,
		java.lang.String supportWebsite, boolean labs, int productType,
		java.lang.String version,
		java.util.Map<java.util.Locale, java.lang.String> changeLogMap,
		java.io.File iconFile, boolean paclEnabled,
		java.util.Map<java.util.Locale, java.lang.String> eulaContentMap,
		int licenseType, java.lang.String orderURL, boolean hidden,
		boolean portalRequired,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void addEntryResources(com.liferay.osb.model.AppEntry entry,
		boolean addCommunityPermissions, boolean addGuestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void addEntryResources(com.liferay.osb.model.AppEntry entry,
		java.lang.String[] communityPermissions,
		java.lang.String[] guestPermissions)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public java.io.File buildLiferayPackage(
		com.liferay.osb.model.AppPackage appPackage, boolean licensed)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void buildLiferayPackages(
		com.liferay.osb.model.AppVersion appVersion)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void expireAppEntries(long developerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.AppEntry> getAppEntries(
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.AppEntry> getAppEntries(
		long developerEntryId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.AppEntry> getAppEntries(
		long developerEntryId, java.lang.String title, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAppEntriesCount(int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAppEntriesCount(long developerEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAppEntriesCount(long developerEntryId, java.lang.String title)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.osb.model.AppEntry getAppEntry(java.lang.String title)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.AppEntry> getPrepackagedAppEntries(
		int compatibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getPrepackagedAppEntriesCount(int compatibility)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AppEntry incrementDownloadCount(long userId,
		long appVersionId, int domain)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.AppEntry> search(
		java.lang.String title,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(java.lang.String title,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AppEntry updateAppEntry(long userId,
		long appEntryId, long licenseLifetime, boolean supported,
		long[] standardQuantities, boolean standardUnlimited,
		long[] developerQuantities, boolean developerUnlimited, boolean trial)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AppEntry updateAppEntry(long userId,
		long appEntryId, java.lang.String title,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String website, java.lang.String demoWebsite,
		java.lang.String documentationWebsite,
		java.lang.String referenceWebsite, java.lang.String sourceCodeWebsite,
		java.lang.String supportWebsite, boolean labs, int productType,
		java.lang.String version, int versionOrder,
		java.util.Map<java.util.Locale, java.lang.String> changeLogMap,
		java.io.File iconFile, boolean paclEnabled, int releaseType,
		java.util.Map<java.util.Locale, java.lang.String> eulaContentMap,
		int licenseType, java.lang.String orderURL, boolean hidden,
		boolean portalRequired, int status,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AppEntry updateAppEntry(long appEntryId,
		java.lang.String version,
		java.util.Map<java.util.Locale, java.lang.String> changeLogMap,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void updateAsset(long userId,
		com.liferay.osb.model.AppEntry appEntry,
		com.liferay.osb.model.AppVersion appVersion, long[] assetCategoryIds,
		java.lang.String[] assetTagNames)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AppEntry updateStatus(long userId,
		long appEntryId, int status, java.lang.String statusMessage,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	public void validatePurchase(long appEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}