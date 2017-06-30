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
 * This class is a wrapper for {@link AssetAttachmentLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AssetAttachmentLocalService
 * @generated
 */
public class AssetAttachmentLocalServiceWrapper
	implements AssetAttachmentLocalService,
		ServiceWrapper<AssetAttachmentLocalService> {
	public AssetAttachmentLocalServiceWrapper(
		AssetAttachmentLocalService assetAttachmentLocalService) {
		_assetAttachmentLocalService = assetAttachmentLocalService;
	}

	/**
	* Adds the asset attachment to the database. Also notifies the appropriate model listeners.
	*
	* @param assetAttachment the asset attachment
	* @return the asset attachment that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment addAssetAttachment(
		com.liferay.osb.model.AssetAttachment assetAttachment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.addAssetAttachment(assetAttachment);
	}

	/**
	* Creates a new asset attachment with the primary key. Does not add the asset attachment to the database.
	*
	* @param assetAttachmentId the primary key for the new asset attachment
	* @return the new asset attachment
	*/
	public com.liferay.osb.model.AssetAttachment createAssetAttachment(
		long assetAttachmentId) {
		return _assetAttachmentLocalService.createAssetAttachment(assetAttachmentId);
	}

	/**
	* Deletes the asset attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetAttachmentId the primary key of the asset attachment
	* @return the asset attachment that was removed
	* @throws PortalException if a asset attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment deleteAssetAttachment(
		long assetAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.deleteAssetAttachment(assetAttachmentId);
	}

	/**
	* Deletes the asset attachment from the database. Also notifies the appropriate model listeners.
	*
	* @param assetAttachment the asset attachment
	* @return the asset attachment that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment deleteAssetAttachment(
		com.liferay.osb.model.AssetAttachment assetAttachment)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.deleteAssetAttachment(assetAttachment);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _assetAttachmentLocalService.dynamicQuery();
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
		return _assetAttachmentLocalService.dynamicQuery(dynamicQuery);
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
		return _assetAttachmentLocalService.dynamicQuery(dynamicQuery, start,
			end);
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
		return _assetAttachmentLocalService.dynamicQuery(dynamicQuery, start,
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
		return _assetAttachmentLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.osb.model.AssetAttachment fetchAssetAttachment(
		long assetAttachmentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.fetchAssetAttachment(assetAttachmentId);
	}

	/**
	* Returns the asset attachment with the primary key.
	*
	* @param assetAttachmentId the primary key of the asset attachment
	* @return the asset attachment
	* @throws PortalException if a asset attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment getAssetAttachment(
		long assetAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.getAssetAttachment(assetAttachmentId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset attachments
	* @param end the upper bound of the range of asset attachments (not inclusive)
	* @return the range of asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAttachment> getAssetAttachments(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.getAssetAttachments(start, end);
	}

	/**
	* Returns the number of asset attachments.
	*
	* @return the number of asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public int getAssetAttachmentsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.getAssetAttachmentsCount();
	}

	/**
	* Updates the asset attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetAttachment the asset attachment
	* @return the asset attachment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment updateAssetAttachment(
		com.liferay.osb.model.AssetAttachment assetAttachment)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.updateAssetAttachment(assetAttachment);
	}

	/**
	* Updates the asset attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetAttachment the asset attachment
	* @param merge whether to merge the asset attachment with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset attachment that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment updateAssetAttachment(
		com.liferay.osb.model.AssetAttachment assetAttachment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.updateAssetAttachment(assetAttachment,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _assetAttachmentLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_assetAttachmentLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _assetAttachmentLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.AssetAttachment addAssetAttachment(
		long userId, long classNameId, long classPK, java.lang.String fileName,
		int type, int rank, byte[] bytes)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.addAssetAttachment(userId,
			classNameId, classPK, fileName, type, rank, bytes);
	}

	public com.liferay.osb.model.AssetAttachment addAssetAttachment(
		long userId, long classNameId, long classPK, java.lang.String fileName,
		int type, int rank, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.addAssetAttachment(userId,
			classNameId, classPK, fileName, type, rank, file);
	}

	public com.liferay.osb.model.AssetAttachment addAssetAttachment(
		long userId, long classNameId, long classPK, java.lang.String fileName,
		int type, int rank, java.io.File file, byte[] bytes)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.addAssetAttachment(userId,
			classNameId, classPK, fileName, type, rank, file, bytes);
	}

	public com.liferay.osb.model.AssetAttachment addAssetAttachment(
		long userId, java.lang.String className, long classPK,
		java.lang.String fileName, int type, int rank, byte[] bytes)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.addAssetAttachment(userId,
			className, classPK, fileName, type, rank, bytes);
	}

	public com.liferay.osb.model.AssetAttachment addAssetAttachment(
		long userId, java.lang.String className, long classPK,
		java.lang.String fileName, int type, int rank, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.addAssetAttachment(userId,
			className, classPK, fileName, type, rank, file);
	}

	public com.liferay.osb.model.AssetAttachment copyAssetAttachment(
		long assetAttachmentId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.copyAssetAttachment(assetAttachmentId);
	}

	public com.liferay.osb.model.AssetAttachment copyAssetAttachment(
		long assetAttachmentId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.copyAssetAttachment(assetAttachmentId,
			classNameId, classPK);
	}

	public com.liferay.osb.model.AssetAttachment copyAssetAttachment(
		long assetAttachmentId, java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.copyAssetAttachment(assetAttachmentId,
			className, classPK);
	}

	public void deleteAssetAttachments(java.util.Date createDate,
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_assetAttachmentLocalService.deleteAssetAttachments(createDate,
			classNameId, classPK);
	}

	public void deleteAssetAttachments(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_assetAttachmentLocalService.deleteAssetAttachments(className, classPK);
	}

	public java.util.List<com.liferay.osb.model.AssetAttachment> getAssetAttachments(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.getAssetAttachments(className,
			classPK);
	}

	public java.util.List<com.liferay.osb.model.AssetAttachment> getAssetAttachments(
		java.lang.String className, long classPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.getAssetAttachments(className,
			classPK, type, start, end, obc);
	}

	public int getAssetAttachmentsCount(java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.getAssetAttachmentsCount(className,
			classPK);
	}

	public int getAssetAttachmentsCount(java.lang.String className,
		long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.getAssetAttachmentsCount(className,
			classPK, type);
	}

	public com.liferay.osb.model.AssetAttachment updateAssetAttachment(
		long assetAttachmentId, byte[] bytes)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.updateAssetAttachment(assetAttachmentId,
			bytes);
	}

	public com.liferay.osb.model.AssetAttachment updateAssetAttachment(
		long assetAttachmentId, java.io.File file)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.updateAssetAttachment(assetAttachmentId,
			file);
	}

	public com.liferay.osb.model.AssetAttachment updateAssetAttachment(
		long assetAttachmentId, java.lang.String className, long classPK,
		int rank)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _assetAttachmentLocalService.updateAssetAttachment(assetAttachmentId,
			className, classPK, rank);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AssetAttachmentLocalService getWrappedAssetAttachmentLocalService() {
		return _assetAttachmentLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAssetAttachmentLocalService(
		AssetAttachmentLocalService assetAttachmentLocalService) {
		_assetAttachmentLocalService = assetAttachmentLocalService;
	}

	public AssetAttachmentLocalService getWrappedService() {
		return _assetAttachmentLocalService;
	}

	public void setWrappedService(
		AssetAttachmentLocalService assetAttachmentLocalService) {
		_assetAttachmentLocalService = assetAttachmentLocalService;
	}

	private AssetAttachmentLocalService _assetAttachmentLocalService;
}