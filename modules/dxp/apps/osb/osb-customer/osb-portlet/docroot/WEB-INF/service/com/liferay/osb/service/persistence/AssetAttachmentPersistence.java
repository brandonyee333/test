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

package com.liferay.osb.service.persistence;

import com.liferay.osb.model.AssetAttachment;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the asset attachment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetAttachmentPersistenceImpl
 * @see AssetAttachmentUtil
 * @generated
 */
public interface AssetAttachmentPersistence extends BasePersistence<AssetAttachment> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AssetAttachmentUtil} to access the asset attachment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the asset attachment in the entity cache if it is enabled.
	*
	* @param assetAttachment the asset attachment
	*/
	public void cacheResult(
		com.liferay.osb.model.AssetAttachment assetAttachment);

	/**
	* Caches the asset attachments in the entity cache if it is enabled.
	*
	* @param assetAttachments the asset attachments
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.AssetAttachment> assetAttachments);

	/**
	* Creates a new asset attachment with the primary key. Does not add the asset attachment to the database.
	*
	* @param assetAttachmentId the primary key for the new asset attachment
	* @return the new asset attachment
	*/
	public com.liferay.osb.model.AssetAttachment create(long assetAttachmentId);

	/**
	* Removes the asset attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetAttachmentId the primary key of the asset attachment
	* @return the asset attachment that was removed
	* @throws com.liferay.osb.NoSuchAssetAttachmentException if a asset attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment remove(long assetAttachmentId)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.AssetAttachment updateImpl(
		com.liferay.osb.model.AssetAttachment assetAttachment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset attachment with the primary key or throws a {@link com.liferay.osb.NoSuchAssetAttachmentException} if it could not be found.
	*
	* @param assetAttachmentId the primary key of the asset attachment
	* @return the asset attachment
	* @throws com.liferay.osb.NoSuchAssetAttachmentException if a asset attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment findByPrimaryKey(
		long assetAttachmentId)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset attachment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetAttachmentId the primary key of the asset attachment
	* @return the asset attachment, or <code>null</code> if a asset attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment fetchByPrimaryKey(
		long assetAttachmentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset attachments where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAttachment> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset attachments where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of asset attachments
	* @param end the upper bound of the range of asset attachments (not inclusive)
	* @return the range of matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAttachment> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset attachments where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of asset attachments
	* @param end the upper bound of the range of asset attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAttachment> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset attachment in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset attachment
	* @throws com.liferay.osb.NoSuchAssetAttachmentException if a matching asset attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset attachment in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset attachment, or <code>null</code> if a matching asset attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset attachment in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset attachment
	* @throws com.liferay.osb.NoSuchAssetAttachmentException if a matching asset attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset attachment in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset attachment, or <code>null</code> if a matching asset attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset attachments before and after the current asset attachment in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param assetAttachmentId the primary key of the current asset attachment
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset attachment
	* @throws com.liferay.osb.NoSuchAssetAttachmentException if a asset attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment[] findByC_C_PrevAndNext(
		long assetAttachmentId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset attachments where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAttachment> findByC_C_T(
		long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset attachments where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of asset attachments
	* @param end the upper bound of the range of asset attachments (not inclusive)
	* @return the range of matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAttachment> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset attachments where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of asset attachments
	* @param end the upper bound of the range of asset attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAttachment> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset attachment
	* @throws com.liferay.osb.NoSuchAssetAttachmentException if a matching asset attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment findByC_C_T_First(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset attachment, or <code>null</code> if a matching asset attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment fetchByC_C_T_First(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset attachment
	* @throws com.liferay.osb.NoSuchAssetAttachmentException if a matching asset attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment findByC_C_T_Last(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset attachment, or <code>null</code> if a matching asset attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment fetchByC_C_T_Last(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset attachments before and after the current asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param assetAttachmentId the primary key of the current asset attachment
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset attachment
	* @throws com.liferay.osb.NoSuchAssetAttachmentException if a asset attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment[] findByC_C_T_PrevAndNext(
		long assetAttachmentId, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset attachments where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAttachment> findByLtC_C_C(
		java.util.Date createDate, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset attachments where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of asset attachments
	* @param end the upper bound of the range of asset attachments (not inclusive)
	* @return the range of matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAttachment> findByLtC_C_C(
		java.util.Date createDate, long classNameId, long classPK, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset attachments where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of asset attachments
	* @param end the upper bound of the range of asset attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAttachment> findByLtC_C_C(
		java.util.Date createDate, long classNameId, long classPK, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset attachment in the ordered set where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset attachment
	* @throws com.liferay.osb.NoSuchAssetAttachmentException if a matching asset attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment findByLtC_C_C_First(
		java.util.Date createDate, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset attachment in the ordered set where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset attachment, or <code>null</code> if a matching asset attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment fetchByLtC_C_C_First(
		java.util.Date createDate, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset attachment in the ordered set where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset attachment
	* @throws com.liferay.osb.NoSuchAssetAttachmentException if a matching asset attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment findByLtC_C_C_Last(
		java.util.Date createDate, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset attachment in the ordered set where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset attachment, or <code>null</code> if a matching asset attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment fetchByLtC_C_C_Last(
		java.util.Date createDate, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset attachments before and after the current asset attachment in the ordered set where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param assetAttachmentId the primary key of the current asset attachment
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset attachment
	* @throws com.liferay.osb.NoSuchAssetAttachmentException if a asset attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment[] findByLtC_C_C_PrevAndNext(
		long assetAttachmentId, java.util.Date createDate, long classNameId,
		long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset attachments where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param fileName the file name
	* @param type the type
	* @return the matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAttachment> findByC_C_F_T(
		long classNameId, long classPK, java.lang.String fileName, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the asset attachments where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param fileName the file name
	* @param type the type
	* @param start the lower bound of the range of asset attachments
	* @param end the upper bound of the range of asset attachments (not inclusive)
	* @return the range of matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAttachment> findByC_C_F_T(
		long classNameId, long classPK, java.lang.String fileName, int type,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset attachments where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param fileName the file name
	* @param type the type
	* @param start the lower bound of the range of asset attachments
	* @param end the upper bound of the range of asset attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAttachment> findByC_C_F_T(
		long classNameId, long classPK, java.lang.String fileName, int type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param fileName the file name
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset attachment
	* @throws com.liferay.osb.NoSuchAssetAttachmentException if a matching asset attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment findByC_C_F_T_First(
		long classNameId, long classPK, java.lang.String fileName, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param fileName the file name
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset attachment, or <code>null</code> if a matching asset attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment fetchByC_C_F_T_First(
		long classNameId, long classPK, java.lang.String fileName, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param fileName the file name
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset attachment
	* @throws com.liferay.osb.NoSuchAssetAttachmentException if a matching asset attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment findByC_C_F_T_Last(
		long classNameId, long classPK, java.lang.String fileName, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param fileName the file name
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset attachment, or <code>null</code> if a matching asset attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment fetchByC_C_F_T_Last(
		long classNameId, long classPK, java.lang.String fileName, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the asset attachments before and after the current asset attachment in the ordered set where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63;.
	*
	* @param assetAttachmentId the primary key of the current asset attachment
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param fileName the file name
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next asset attachment
	* @throws com.liferay.osb.NoSuchAssetAttachmentException if a asset attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.AssetAttachment[] findByC_C_F_T_PrevAndNext(
		long assetAttachmentId, long classNameId, long classPK,
		java.lang.String fileName, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the asset attachments.
	*
	* @return the asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAttachment> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.AssetAttachment> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the asset attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset attachments
	* @param end the upper bound of the range of asset attachments (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.AssetAttachment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset attachments where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset attachments where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C_T(long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset attachments where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public void removeByLtC_C_C(java.util.Date createDate, long classNameId,
		long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset attachments where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param fileName the file name
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C_F_T(long classNameId, long classPK,
		java.lang.String fileName, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the asset attachments from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset attachments where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset attachments where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the number of matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C_T(long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset attachments where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public int countByLtC_C_C(java.util.Date createDate, long classNameId,
		long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset attachments where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param fileName the file name
	* @param type the type
	* @return the number of matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C_F_T(long classNameId, long classPK,
		java.lang.String fileName, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of asset attachments.
	*
	* @return the number of asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}