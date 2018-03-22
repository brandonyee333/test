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

import com.liferay.osb.model.LicenseEntry;
import com.liferay.osb.model.LicenseKey;
import com.liferay.osb.model.LicenseKeySet;
import com.liferay.osb.model.OfferingEntry;
import com.liferay.osb.model.ProductEntry;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the local service interface for LicenseKey. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeyLocalServiceUtil
 * @see com.liferay.osb.service.base.LicenseKeyLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.LicenseKeyLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface LicenseKeyLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LicenseKeyLocalServiceUtil} to access the license key local service. Add custom service methods to {@link com.liferay.osb.service.impl.LicenseKeyLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the license key to the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKey the license key
	* @return the license key that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LicenseKey addLicenseKey(LicenseKey licenseKey);

	public LicenseKey addLicenseKey(long userId, LicenseKeySet licenseKeySet,
		java.lang.String name, OfferingEntry offeringEntry,
		LicenseEntry licenseEntry, ProductEntry productEntry,
		int productVersion, long clusterId, java.lang.String owner,
		int maxServers, int maxHttpSessions, java.lang.String description,
		java.lang.String[] hostNames, java.lang.String[] ipAddresses,
		java.lang.String[] macAddresses, java.lang.String[] serverIds,
		int startDateMonth, int startDateDay, int startDateYear,
		java.lang.String additionalInfo, boolean complimentary, boolean active)
		throws PortalException;

	public LicenseKey addLicenseKey(long userId, long assetReceiptLicenseId,
		java.lang.String licenseEntryType, java.lang.String productEntryName,
		java.lang.String productId, int productVersion, java.lang.String owner,
		long maxUsers, java.lang.String description,
		java.lang.String[] hostNames, java.lang.String[] ipAddresses,
		java.lang.String[] macAddresses, java.lang.String[] serverIds,
		Date startDate, Date expirationDate) throws PortalException;

	public LicenseKey addLicenseKey(long userId, long licenseKeySetId,
		java.lang.String name, long offeringEntryId, long licenseEntryId,
		long productEntryId, int productVersion, long clusterId,
		java.lang.String owner, int maxServers, int maxHttpSessions,
		java.lang.String description, java.lang.String[] hostNames,
		java.lang.String[] ipAddresses, java.lang.String[] macAddresses,
		java.lang.String[] serverIds, int startDateMonth, int startDateDay,
		int startDateYear, boolean complimentary, boolean active)
		throws PortalException;

	public LicenseKey addSingleUseLicenseKey(java.lang.String orderUuid,
		int productVersion, java.lang.String emailAddress,
		java.lang.String fullName, java.lang.String additionalInfo)
		throws java.lang.Exception;

	/**
	* Creates a new license key with the primary key. Does not add the license key to the database.
	*
	* @param licenseKeyId the primary key for the new license key
	* @return the new license key
	*/
	public LicenseKey createLicenseKey(long licenseKeyId);

	/**
	* Deletes the license key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKey the license key
	* @return the license key that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public LicenseKey deleteLicenseKey(LicenseKey licenseKey);

	/**
	* Deletes the license key with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key that was removed
	* @throws PortalException if a license key with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public LicenseKey deleteLicenseKey(long licenseKeyId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKey fetchLicenseKey(long licenseKeyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKey getFirstLicenseKey(long accountEntryId,
		OrderByComparator obc) throws PortalException;

	/**
	* Returns the license key with the primary key.
	*
	* @param licenseKeyId the primary key of the license key
	* @return the license key
	* @throws PortalException if a license key with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKey getLicenseKey(long licenseKeyId)
		throws PortalException;

	public LicenseKey renewLicenseKey(long userId, long licenseKeyId,
		Date startDate, int renewTime) throws java.lang.Exception;

	public LicenseKey renewLicenseKey(long userId, long licenseKeyId,
		Date startDate, Date expirationDate) throws PortalException;

	public LicenseKey renewTrialLicenseKey(long userId)
		throws java.lang.Exception;

	/**
	* Updates the license key in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param licenseKey the license key
	* @return the license key that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public LicenseKey updateLicenseKey(LicenseKey licenseKey);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetReceiptLicenseLicenseKeysCount(
		long assetReceiptLicenseId, boolean complimentary, boolean active);

	/**
	* Returns the number of license keies.
	*
	* @return the number of license keies
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getLicenseKeiesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingEntryGroupLicenseKeysCount(long[] offeringEntryIds,
		boolean complimentary, boolean active);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingEntryLicenseKeysCount(long offeringEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingEntryLicenseKeysCount(long offeringEntryId,
		boolean complimentary, boolean active);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingEntryLicenseKeysCount(long offeringEntryId,
		long clusterId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingEntryLicenseKeysCount(long offeringEntryId,
		long clusterId, boolean active);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getUserLicenseKeysCount(long userId, long accountEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(java.lang.Long createUserId, int createDateGTDay,
		int createDateGTMonth, int createDateGTYear, int createDateLTDay,
		int createDateLTMonth, int createDateLTYear,
		java.lang.Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear,
		java.lang.String accountEntryName, java.lang.String licenseKeySetName,
		int startDateGTDay, int startDateGTMonth, int startDateGTYear,
		int startDateLTDay, int startDateLTMonth, int startDateLTYear,
		long[] licenseEntryIds, long[] productEntryIds,
		java.lang.String productEntryName, java.lang.String productId,
		int[] productVersions, java.lang.String owner,
		java.lang.String description, java.lang.String hostName,
		java.lang.String ipAddress, java.lang.String macAddress,
		java.lang.String serverId, java.lang.String key,
		int expirationDateGTDay, int expirationDateGTMonth,
		int expirationDateGTYear, int expirationDateLTDay,
		int expirationDateLTMonth, int expirationDateLTYear,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getAccountEntryLicenseKeys(long accountEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getAssetReceiptLicenseLicenseKeys(
		long assetReceiptLicenseId, boolean active);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getAssetReceiptLicenseLicenseKeys(
		long assetReceiptLicenseId, boolean complimentary, boolean active);

	/**
	* Returns a range of all the license keies.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.LicenseKeyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of license keies
	* @param end the upper bound of the range of license keies (not inclusive)
	* @return the range of license keies
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeies(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeySetLicenseKeys(long licenseKeySetId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(java.lang.String productId,
		java.lang.String serverId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(long assetReceiptLicenseId,
		java.lang.String productId, java.lang.String serverId, boolean active,
		int start, int end, OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(long userId,
		java.lang.String productId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeys(long userId, long accountEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getLicenseKeysByName(
		java.lang.String productEntryName, java.lang.String serverId,
		boolean active, int start, int end, OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getOfferingEntryGroupLicenseKeys(
		long[] offeringEntryIds, boolean complimentary, boolean active,
		int start, int end, OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getOfferingEntryLicenseKeys(long offeringEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getOfferingEntryLicenseKeys(long offeringEntryId,
		boolean complimentary, boolean active);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getOfferingEntryLicenseKeys(long offeringEntryId,
		long clusterId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> getOfferingEntryLicenseKeys(long offeringEntryId,
		long clusterId, boolean active);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> search(java.lang.Long createUserId,
		int createDateGTDay, int createDateGTMonth, int createDateGTYear,
		int createDateLTDay, int createDateLTMonth, int createDateLTYear,
		java.lang.Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear,
		java.lang.String accountEntryName, java.lang.String licenseKeySetName,
		int startDateGTDay, int startDateGTMonth, int startDateGTYear,
		int startDateLTDay, int startDateLTMonth, int startDateLTYear,
		long[] licenseEntryIds, long[] productEntryIds,
		java.lang.String productEntryName, java.lang.String productId,
		int[] productVersions, java.lang.String owner,
		java.lang.String description, java.lang.String hostName,
		java.lang.String ipAddress, java.lang.String macAddress,
		java.lang.String serverId, java.lang.String key,
		int expirationDateGTDay, int expirationDateGTMonth,
		int expirationDateGTYear, int expirationDateLTDay,
		int expirationDateLTMonth, int expirationDateLTYear,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andSearch, int start, int end, OrderByComparator obc);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<LicenseKey> search(java.lang.String keywords,
		LinkedHashMap<java.lang.String, java.lang.Object> params, int start,
		int end, OrderByComparator obc);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	public void buyLicenseKey(long companyId, long userId)
		throws PortalException;

	public void sendRegisteredEmail(User user, LicenseKey licenseKey)
		throws java.lang.Exception;

	public void sendTrialRenewalNotificationEmail(
		java.lang.String emailAddress, long accountEntryId)
		throws java.lang.Exception;

	public void updateLicenseKey(long licenseKeyId, long accountEntryId,
		long offeringEntryId, long orderEntryId) throws PortalException;

	public void updateLicenseKey(long userId, long licenseKeyId,
		long assetReceiptLicenseId, boolean active) throws PortalException;

	public void updateLicenseKey(long userId, long licenseKeyId,
		long licenseKeySetId, long offeringEntryId, java.lang.String name,
		boolean active) throws PortalException;
}