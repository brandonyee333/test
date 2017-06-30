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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the asset attachment service. This utility wraps {@link AssetAttachmentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetAttachmentPersistence
 * @see AssetAttachmentPersistenceImpl
 * @generated
 */
public class AssetAttachmentUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(AssetAttachment assetAttachment) {
		getPersistence().clearCache(assetAttachment);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<AssetAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AssetAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<AssetAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AssetAttachment update(AssetAttachment assetAttachment,
		boolean merge) throws SystemException {
		return getPersistence().update(assetAttachment, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static AssetAttachment update(AssetAttachment assetAttachment,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(assetAttachment, merge, serviceContext);
	}

	/**
	* Caches the asset attachment in the entity cache if it is enabled.
	*
	* @param assetAttachment the asset attachment
	*/
	public static void cacheResult(
		com.liferay.osb.model.AssetAttachment assetAttachment) {
		getPersistence().cacheResult(assetAttachment);
	}

	/**
	* Caches the asset attachments in the entity cache if it is enabled.
	*
	* @param assetAttachments the asset attachments
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.AssetAttachment> assetAttachments) {
		getPersistence().cacheResult(assetAttachments);
	}

	/**
	* Creates a new asset attachment with the primary key. Does not add the asset attachment to the database.
	*
	* @param assetAttachmentId the primary key for the new asset attachment
	* @return the new asset attachment
	*/
	public static com.liferay.osb.model.AssetAttachment create(
		long assetAttachmentId) {
		return getPersistence().create(assetAttachmentId);
	}

	/**
	* Removes the asset attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetAttachmentId the primary key of the asset attachment
	* @return the asset attachment that was removed
	* @throws com.liferay.osb.NoSuchAssetAttachmentException if a asset attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetAttachment remove(
		long assetAttachmentId)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(assetAttachmentId);
	}

	public static com.liferay.osb.model.AssetAttachment updateImpl(
		com.liferay.osb.model.AssetAttachment assetAttachment, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(assetAttachment, merge);
	}

	/**
	* Returns the asset attachment with the primary key or throws a {@link com.liferay.osb.NoSuchAssetAttachmentException} if it could not be found.
	*
	* @param assetAttachmentId the primary key of the asset attachment
	* @return the asset attachment
	* @throws com.liferay.osb.NoSuchAssetAttachmentException if a asset attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetAttachment findByPrimaryKey(
		long assetAttachmentId)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(assetAttachmentId);
	}

	/**
	* Returns the asset attachment with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param assetAttachmentId the primary key of the asset attachment
	* @return the asset attachment, or <code>null</code> if a asset attachment with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetAttachment fetchByPrimaryKey(
		long assetAttachmentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(assetAttachmentId);
	}

	/**
	* Returns all the asset attachments where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetAttachment> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAttachment> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAttachment> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C(classNameId, classPK, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAttachment findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the first asset attachment in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching asset attachment, or <code>null</code> if a matching asset attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetAttachment fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_First(classNameId, classPK, orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAttachment findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_Last(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last asset attachment in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching asset attachment, or <code>null</code> if a matching asset attachment could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetAttachment fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_Last(classNameId, classPK, orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAttachment[] findByC_C_PrevAndNext(
		long assetAttachmentId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_PrevAndNext(assetAttachmentId, classNameId,
			classPK, orderByComparator);
	}

	/**
	* Returns all the asset attachments where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetAttachment> findByC_C_T(
		long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C_T(classNameId, classPK, type);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAttachment> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_T(classNameId, classPK, type, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAttachment> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_T(classNameId, classPK, type, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAttachment findByC_C_T_First(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_T_First(classNameId, classPK, type,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAttachment fetchByC_C_T_First(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_T_First(classNameId, classPK, type,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAttachment findByC_C_T_Last(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_T_Last(classNameId, classPK, type,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAttachment fetchByC_C_T_Last(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_T_Last(classNameId, classPK, type,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAttachment[] findByC_C_T_PrevAndNext(
		long assetAttachmentId, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_T_PrevAndNext(assetAttachmentId, classNameId,
			classPK, type, orderByComparator);
	}

	/**
	* Returns all the asset attachments where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetAttachment> findByLtC_C_C(
		java.util.Date createDate, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByLtC_C_C(createDate, classNameId, classPK);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAttachment> findByLtC_C_C(
		java.util.Date createDate, long classNameId, long classPK, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtC_C_C(createDate, classNameId, classPK, start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAttachment> findByLtC_C_C(
		java.util.Date createDate, long classNameId, long classPK, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtC_C_C(createDate, classNameId, classPK, start, end,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAttachment findByLtC_C_C_First(
		java.util.Date createDate, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtC_C_C_First(createDate, classNameId, classPK,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAttachment fetchByLtC_C_C_First(
		java.util.Date createDate, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByLtC_C_C_First(createDate, classNameId, classPK,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAttachment findByLtC_C_C_Last(
		java.util.Date createDate, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtC_C_C_Last(createDate, classNameId, classPK,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAttachment fetchByLtC_C_C_Last(
		java.util.Date createDate, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByLtC_C_C_Last(createDate, classNameId, classPK,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAttachment[] findByLtC_C_C_PrevAndNext(
		long assetAttachmentId, java.util.Date createDate, long classNameId,
		long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByLtC_C_C_PrevAndNext(assetAttachmentId, createDate,
			classNameId, classPK, orderByComparator);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAttachment> findByC_C_F_T(
		long classNameId, long classPK, java.lang.String fileName, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_F_T(classNameId, classPK, fileName, type);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAttachment> findByC_C_F_T(
		long classNameId, long classPK, java.lang.String fileName, int type,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_F_T(classNameId, classPK, fileName, type, start,
			end);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAttachment> findByC_C_F_T(
		long classNameId, long classPK, java.lang.String fileName, int type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_F_T(classNameId, classPK, fileName, type, start,
			end, orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAttachment findByC_C_F_T_First(
		long classNameId, long classPK, java.lang.String fileName, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_F_T_First(classNameId, classPK, fileName, type,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAttachment fetchByC_C_F_T_First(
		long classNameId, long classPK, java.lang.String fileName, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_F_T_First(classNameId, classPK, fileName, type,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAttachment findByC_C_F_T_Last(
		long classNameId, long classPK, java.lang.String fileName, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_F_T_Last(classNameId, classPK, fileName, type,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAttachment fetchByC_C_F_T_Last(
		long classNameId, long classPK, java.lang.String fileName, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_F_T_Last(classNameId, classPK, fileName, type,
			orderByComparator);
	}

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
	public static com.liferay.osb.model.AssetAttachment[] findByC_C_F_T_PrevAndNext(
		long assetAttachmentId, long classNameId, long classPK,
		java.lang.String fileName, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchAssetAttachmentException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_F_T_PrevAndNext(assetAttachmentId, classNameId,
			classPK, fileName, type, orderByComparator);
	}

	/**
	* Returns all the asset attachments.
	*
	* @return the asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetAttachment> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.liferay.osb.model.AssetAttachment> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.osb.model.AssetAttachment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the asset attachments where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	* Removes all the asset attachments where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_C_T(long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_C_T(classNameId, classPK, type);
	}

	/**
	* Removes all the asset attachments where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByLtC_C_C(java.util.Date createDate,
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByLtC_C_C(createDate, classNameId, classPK);
	}

	/**
	* Removes all the asset attachments where classNameId = &#63; and classPK = &#63; and fileName = &#63; and type = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param fileName the file name
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_C_F_T(long classNameId, long classPK,
		java.lang.String fileName, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_C_F_T(classNameId, classPK, fileName, type);
	}

	/**
	* Removes all the asset attachments from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of asset attachments where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	* Returns the number of asset attachments where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the number of matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C_T(long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C_T(classNameId, classPK, type);
	}

	/**
	* Returns the number of asset attachments where createDate &lt; &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param createDate the create date
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int countByLtC_C_C(java.util.Date createDate,
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByLtC_C_C(createDate, classNameId, classPK);
	}

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
	public static int countByC_C_F_T(long classNameId, long classPK,
		java.lang.String fileName, int type)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByC_C_F_T(classNameId, classPK, fileName, type);
	}

	/**
	* Returns the number of asset attachments.
	*
	* @return the number of asset attachments
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AssetAttachmentPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AssetAttachmentPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					AssetAttachmentPersistence.class.getName());

			ReferenceRegistry.registerReference(AssetAttachmentUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(AssetAttachmentPersistence persistence) {
	}

	private static AssetAttachmentPersistence _persistence;
}