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
 * The interface for the asset receipt license local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetReceiptLicenseLocalServiceUtil
 * @see com.liferay.osb.service.base.AssetReceiptLicenseLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AssetReceiptLicenseLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AssetReceiptLicenseLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetReceiptLicenseLocalServiceUtil} to access the asset receipt license local service. Add custom service methods to {@link com.liferay.osb.service.impl.AssetReceiptLicenseLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the asset receipt license to the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptLicense the asset receipt license
	* @return the asset receipt license that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense addAssetReceiptLicense(
		com.liferay.osb.model.AssetReceiptLicense assetReceiptLicense)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new asset receipt license with the primary key. Does not add the asset receipt license to the database.
	*
	* @param assetReceiptLicenseId the primary key for the new asset receipt license
	* @return the new asset receipt license
	*/
	public com.liferay.osb.model.AssetReceiptLicense createAssetReceiptLicense(
		long assetReceiptLicenseId);

	/**
	* Deletes the asset receipt license with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptLicenseId the primary key of the asset receipt license
	* @return the asset receipt license that was removed
	* @throws PortalException if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense deleteAssetReceiptLicense(
		long assetReceiptLicenseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the asset receipt license from the database. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptLicense the asset receipt license
	* @return the asset receipt license that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense deleteAssetReceiptLicense(
		com.liferay.osb.model.AssetReceiptLicense assetReceiptLicense)
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
	public com.liferay.osb.model.AssetReceiptLicense fetchAssetReceiptLicense(
		long assetReceiptLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset receipt license with the primary key.
	*
	* @param assetReceiptLicenseId the primary key of the asset receipt license
	* @return the asset receipt license
	* @throws PortalException if a asset receipt license with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.osb.model.AssetReceiptLicense getAssetReceiptLicense(
		long assetReceiptLicenseId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset receipt licenses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset receipt licenses
	* @param end the upper bound of the range of asset receipt licenses (not inclusive)
	* @return the range of asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getAssetReceiptLicenses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset receipt licenses.
	*
	* @return the number of asset receipt licenses
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetReceiptLicensesCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the asset receipt license in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptLicense the asset receipt license
	* @return the asset receipt license that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense updateAssetReceiptLicense(
		com.liferay.osb.model.AssetReceiptLicense assetReceiptLicense)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the asset receipt license in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetReceiptLicense the asset receipt license
	* @param merge whether to merge the asset receipt license with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset receipt license that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetReceiptLicense updateAssetReceiptLicense(
		com.liferay.osb.model.AssetReceiptLicense assetReceiptLicense,
		boolean merge)
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

	public com.liferay.osb.model.AssetReceiptLicense addAssetReceiptLicense(
		long userId, long assetReceiptId, long assetLicenseId,
		long assetEntryId, long ownerClassNameId, long ownerClassPK,
		long productClassNameId, long productClassPK,
		java.lang.String productId, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getActiveAssetReceiptLicenses(
		long assetReceiptId, int usageType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getActiveAssetReceiptLicensesCount(long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getActiveAssetReceiptLicensesCount(long assetReceiptId,
		int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getActiveAssetReceiptLicenseTypeAllotment(long assetReceiptId,
		int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.osb.model.AssetReceiptLicense getAssetReceiptLicense(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getAssetReceiptLicenses(
		long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getAssetReceiptLicenses(
		long assetReceiptId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getAssetReceiptLicenses(
		java.lang.String ownerClassName, long ownerClassPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getAssetReceiptLicenses(
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String productId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getAssetReceiptLicenses(
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String productClassName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getAssetReceiptLicenses(
		java.lang.String ownerClassName, long ownerClassPK,
		java.lang.String productId, int licenseType, long licenseLifetime,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetReceiptLicensesCount(long assetReceiptId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetReceiptLicensesCount(long ownerClassNameId,
		long ownerClassPK, java.lang.String productId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetReceiptLicensesCount(java.lang.String ownerClassName,
		long ownerClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetReceiptLicensesCount(java.lang.String ownerClassName,
		long ownerClassPK, java.lang.String productClassName)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAssetReceiptLicensesCount(java.lang.String ownerClassName,
		long ownerClassPK, java.lang.String productId, int licenseType,
		long licenseLifetime)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> getRenewedAssetReceiptLicenses(
		long assetReceiptId, int usageType, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getRenewedAssetReceiptLicensesCount(long assetReceiptId,
		int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long getRenewedAssetReceiptLicenseTypeAllotment(
		long assetReceiptId, int usageType)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasAssetReceiptLicense(long assetLicenseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.osb.model.AssetReceiptLicense> updateAssetReceiptLicenses(
		long assetReceiptId, int usageType, java.util.Date startDate,
		java.util.Date endDate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}