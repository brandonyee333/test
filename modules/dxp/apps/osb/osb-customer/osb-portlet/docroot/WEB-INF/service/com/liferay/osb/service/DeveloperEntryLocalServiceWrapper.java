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
 * This class is a wrapper for {@link DeveloperEntryLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DeveloperEntryLocalService
 * @generated
 */
public class DeveloperEntryLocalServiceWrapper
	implements DeveloperEntryLocalService,
		ServiceWrapper<DeveloperEntryLocalService> {
	public DeveloperEntryLocalServiceWrapper(
		DeveloperEntryLocalService developerEntryLocalService) {
		_developerEntryLocalService = developerEntryLocalService;
	}

	/**
	* Adds the developer entry to the database. Also notifies the appropriate model listeners.
	*
	* @param developerEntry the developer entry
	* @return the developer entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry addDeveloperEntry(
		com.liferay.osb.model.DeveloperEntry developerEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.addDeveloperEntry(developerEntry);
	}

	/**
	* Creates a new developer entry with the primary key. Does not add the developer entry to the database.
	*
	* @param developerEntryId the primary key for the new developer entry
	* @return the new developer entry
	*/
	public com.liferay.osb.model.DeveloperEntry createDeveloperEntry(
		long developerEntryId) {
		return _developerEntryLocalService.createDeveloperEntry(developerEntryId);
	}

	/**
	* Deletes the developer entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param developerEntryId the primary key of the developer entry
	* @return the developer entry that was removed
	* @throws PortalException if a developer entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry deleteDeveloperEntry(
		long developerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.deleteDeveloperEntry(developerEntryId);
	}

	/**
	* Deletes the developer entry from the database. Also notifies the appropriate model listeners.
	*
	* @param developerEntry the developer entry
	* @return the developer entry that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry deleteDeveloperEntry(
		com.liferay.osb.model.DeveloperEntry developerEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.deleteDeveloperEntry(developerEntry);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _developerEntryLocalService.dynamicQuery();
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
		return _developerEntryLocalService.dynamicQuery(dynamicQuery);
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
		return _developerEntryLocalService.dynamicQuery(dynamicQuery, start, end);
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
		return _developerEntryLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _developerEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.DeveloperEntry fetchDeveloperEntry(
		long developerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.fetchDeveloperEntry(developerEntryId);
	}

	/**
	* Returns the developer entry with the primary key.
	*
	* @param developerEntryId the primary key of the developer entry
	* @return the developer entry
	* @throws PortalException if a developer entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry getDeveloperEntry(
		long developerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.getDeveloperEntry(developerEntryId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the developer entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of developer entries
	* @param end the upper bound of the range of developer entries (not inclusive)
	* @return the range of developer entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.DeveloperEntry> getDeveloperEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.getDeveloperEntries(start, end);
	}

	/**
	* Returns the number of developer entries.
	*
	* @return the number of developer entries
	* @throws SystemException if a system exception occurred
	*/
	public int getDeveloperEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.getDeveloperEntriesCount();
	}

	/**
	* Updates the developer entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param developerEntry the developer entry
	* @return the developer entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry updateDeveloperEntry(
		com.liferay.osb.model.DeveloperEntry developerEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.updateDeveloperEntry(developerEntry);
	}

	/**
	* Updates the developer entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param developerEntry the developer entry
	* @param merge whether to merge the developer entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the developer entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.DeveloperEntry updateDeveloperEntry(
		com.liferay.osb.model.DeveloperEntry developerEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.updateDeveloperEntry(developerEntry,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _developerEntryLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_developerEntryLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _developerEntryLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.DeveloperEntry addCompanyDeveloperEntry(
		long userId, java.lang.String emailAddress,
		java.lang.String legalEntityName, java.lang.String phoneNumber,
		java.lang.String faxNumber, java.lang.String street1,
		java.lang.String street2, java.lang.String street3,
		java.lang.String city, java.lang.String zip, long regionId,
		long countryId,
		java.util.Map<java.util.Locale, java.lang.String> profileDescriptionMap,
		java.lang.String profileEmailAddress, java.io.File profileLogo,
		java.lang.String profileWebsite, java.lang.String dossieraAccountKey,
		java.lang.String taxDocumentFileName, java.io.File taxDocumentFile,
		long tosContractEntryId, long developerContractEntryId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.addCompanyDeveloperEntry(userId,
			emailAddress, legalEntityName, phoneNumber, faxNumber, street1,
			street2, street3, city, zip, regionId, countryId,
			profileDescriptionMap, profileEmailAddress, profileLogo,
			profileWebsite, dossieraAccountKey, taxDocumentFileName,
			taxDocumentFile, tosContractEntryId, developerContractEntryId,
			serviceContext);
	}

	public com.liferay.osb.model.DeveloperEntry addUserDeveloperEntry(
		long userId, java.lang.String screenName,
		java.lang.String emailAddress, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		long contractEntryId, long countryId, java.lang.String phoneNumber,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.addUserDeveloperEntry(userId,
			screenName, emailAddress, firstName, middleName, lastName,
			contractEntryId, countryId, phoneNumber, serviceContext);
	}

	public com.liferay.osb.model.DeveloperEntry deleteUserDeveloperEntry(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.deleteUserDeveloperEntry(userId);
	}

	public com.liferay.osb.model.DeveloperEntry fetchDeveloperEntry(
		java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.fetchDeveloperEntry(dossieraAccountKey);
	}

	public com.liferay.osb.model.DeveloperEntry fetchUserDeveloperEntry(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.fetchUserDeveloperEntry(userId);
	}

	public java.util.List<com.liferay.osb.model.DeveloperEntry> getDeveloperEntries(
		int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.getDeveloperEntries(status, start,
			end);
	}

	public java.util.List<com.liferay.osb.model.DeveloperEntry> getDeveloperEntries(
		int type, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.getDeveloperEntries(type, status,
			start, end);
	}

	public java.util.List<com.liferay.osb.model.DeveloperEntry> getDeveloperEntries(
		long userId, int type, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.getDeveloperEntries(userId, type,
			status, start, end, obc);
	}

	public int getDeveloperEntriesCount(int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.getDeveloperEntriesCount(status);
	}

	public int getDeveloperEntriesCount(int type, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.getDeveloperEntriesCount(type, status);
	}

	public com.liferay.osb.model.DeveloperEntry getDeveloperEntryByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.getDeveloperEntryByGroupId(groupId);
	}

	public com.liferay.osb.model.DeveloperEntry getUserDeveloperEntry(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.getUserDeveloperEntry(userId);
	}

	public com.liferay.osb.model.DeveloperEntry updateCompanyDeveloperEntry(
		long userId, long developerEntryId, java.lang.String emailAddress,
		java.lang.String legalEntityName, java.lang.String phoneNumber,
		java.lang.String faxNumber, java.lang.String street1,
		java.lang.String street2, java.lang.String street3,
		java.lang.String city, java.lang.String zip, long regionId,
		long countryId, java.io.File profileLogo,
		java.util.Map<java.util.Locale, java.lang.String> profileDescriptionMap,
		java.lang.String profileEmailAddress, java.lang.String profileWebsite,
		java.lang.String taxDocumentFileName, java.io.File taxDocumentFile,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.updateCompanyDeveloperEntry(userId,
			developerEntryId, emailAddress, legalEntityName, phoneNumber,
			faxNumber, street1, street2, street3, city, zip, regionId,
			countryId, profileLogo, profileDescriptionMap, profileEmailAddress,
			profileWebsite, taxDocumentFileName, taxDocumentFile, serviceContext);
	}

	public com.liferay.osb.model.DeveloperEntry updateDeveloperEntry(
		long userId, long developerEntryId, java.lang.String domainName,
		int domainStatus, java.lang.String paymentFirstName,
		java.lang.String paymentLastName, java.lang.String paymentEmailAddress,
		java.util.Date subscriptionExpirationDate, int subscriptionStatus,
		double fatcaWithholdingPercentage,
		java.lang.String taxDocumentFileName, java.io.File taxDocumentFile)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.updateDeveloperEntry(userId,
			developerEntryId, domainName, domainStatus, paymentFirstName,
			paymentLastName, paymentEmailAddress, subscriptionExpirationDate,
			subscriptionStatus, fatcaWithholdingPercentage,
			taxDocumentFileName, taxDocumentFile);
	}

	public com.liferay.osb.model.DeveloperEntry updateDeveloperEntry(
		long developerEntryId, java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.updateDeveloperEntry(developerEntryId,
			dossieraAccountKey);
	}

	public com.liferay.osb.model.DeveloperEntry updateDeveloperEntry(
		long developerEntryId, java.lang.String paymentFirstName,
		java.lang.String paymentLastName, java.lang.String paymentEmailAddress)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.updateDeveloperEntry(developerEntryId,
			paymentFirstName, paymentLastName, paymentEmailAddress);
	}

	public com.liferay.osb.model.DeveloperEntry updateDeveloperEntryGoogleAnalyticsKey(
		long developerEntryId, java.lang.String googleAnalyticsKey)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.updateDeveloperEntryGoogleAnalyticsKey(developerEntryId,
			googleAnalyticsKey);
	}

	public com.liferay.osb.model.DeveloperEntry updateStatus(long userId,
		long developerEntryId, int status, java.lang.String statusMessage)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.updateStatus(userId,
			developerEntryId, status, statusMessage);
	}

	public com.liferay.osb.model.DeveloperEntry updateSubscription(
		long developerEntryId, java.util.Date subscriptionExpirationDate,
		int subscriptionStatus)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.updateSubscription(developerEntryId,
			subscriptionExpirationDate, subscriptionStatus);
	}

	public com.liferay.osb.model.DeveloperEntry updateUserDeveloperEntry(
		long userId, long developerEntryId, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.lang.String legalEntityName, java.lang.String phoneNumber,
		java.lang.String street1, java.lang.String street2,
		java.lang.String street3, java.lang.String city, java.lang.String zip,
		long regionId, long countryId, java.lang.String taxDocumentFileName,
		java.io.File taxDocumentFile)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _developerEntryLocalService.updateUserDeveloperEntry(userId,
			developerEntryId, firstName, middleName, lastName, legalEntityName,
			phoneNumber, street1, street2, street3, city, zip, regionId,
			countryId, taxDocumentFileName, taxDocumentFile);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public DeveloperEntryLocalService getWrappedDeveloperEntryLocalService() {
		return _developerEntryLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedDeveloperEntryLocalService(
		DeveloperEntryLocalService developerEntryLocalService) {
		_developerEntryLocalService = developerEntryLocalService;
	}

	public DeveloperEntryLocalService getWrappedService() {
		return _developerEntryLocalService;
	}

	public void setWrappedService(
		DeveloperEntryLocalService developerEntryLocalService) {
		_developerEntryLocalService = developerEntryLocalService;
	}

	private DeveloperEntryLocalService _developerEntryLocalService;
}