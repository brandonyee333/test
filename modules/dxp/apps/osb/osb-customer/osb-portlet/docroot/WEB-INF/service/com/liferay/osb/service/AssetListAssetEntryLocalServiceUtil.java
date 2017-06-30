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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the asset list asset entry local service. This utility wraps {@link com.liferay.osb.service.impl.AssetListAssetEntryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetListAssetEntryLocalService
 * @see com.liferay.osb.service.base.AssetListAssetEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AssetListAssetEntryLocalServiceImpl
 * @generated
 */
public class AssetListAssetEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AssetListAssetEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the asset list asset entry to the database. Also notifies the appropriate model listeners.
	*
	* @param assetListAssetEntry the asset list asset entry
	* @return the asset list asset entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry addAssetListAssetEntry(
		com.liferay.osb.model.AssetListAssetEntry assetListAssetEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAssetListAssetEntry(assetListAssetEntry);
	}

	/**
	* Creates a new asset list asset entry with the primary key. Does not add the asset list asset entry to the database.
	*
	* @param assetListAssetEntryId the primary key for the new asset list asset entry
	* @return the new asset list asset entry
	*/
	public static com.liferay.osb.model.AssetListAssetEntry createAssetListAssetEntry(
		long assetListAssetEntryId) {
		return getService().createAssetListAssetEntry(assetListAssetEntryId);
	}

	/**
	* Deletes the asset list asset entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetListAssetEntryId the primary key of the asset list asset entry
	* @return the asset list asset entry that was removed
	* @throws PortalException if a asset list asset entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry deleteAssetListAssetEntry(
		long assetListAssetEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAssetListAssetEntry(assetListAssetEntryId);
	}

	/**
	* Deletes the asset list asset entry from the database. Also notifies the appropriate model listeners.
	*
	* @param assetListAssetEntry the asset list asset entry
	* @return the asset list asset entry that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry deleteAssetListAssetEntry(
		com.liferay.osb.model.AssetListAssetEntry assetListAssetEntry)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAssetListAssetEntry(assetListAssetEntry);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.osb.model.AssetListAssetEntry fetchAssetListAssetEntry(
		long assetListAssetEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAssetListAssetEntry(assetListAssetEntryId);
	}

	/**
	* Returns the asset list asset entry with the primary key.
	*
	* @param assetListAssetEntryId the primary key of the asset list asset entry
	* @return the asset list asset entry
	* @throws PortalException if a asset list asset entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry getAssetListAssetEntry(
		long assetListAssetEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetListAssetEntry(assetListAssetEntryId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset list asset entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset list asset entries
	* @param end the upper bound of the range of asset list asset entries (not inclusive)
	* @return the range of asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetListAssetEntry> getAssetListAssetEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetListAssetEntries(start, end);
	}

	/**
	* Returns the number of asset list asset entries.
	*
	* @return the number of asset list asset entries
	* @throws SystemException if a system exception occurred
	*/
	public static int getAssetListAssetEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetListAssetEntriesCount();
	}

	/**
	* Updates the asset list asset entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetListAssetEntry the asset list asset entry
	* @return the asset list asset entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry updateAssetListAssetEntry(
		com.liferay.osb.model.AssetListAssetEntry assetListAssetEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAssetListAssetEntry(assetListAssetEntry);
	}

	/**
	* Updates the asset list asset entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetListAssetEntry the asset list asset entry
	* @param merge whether to merge the asset list asset entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset list asset entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetListAssetEntry updateAssetListAssetEntry(
		com.liferay.osb.model.AssetListAssetEntry assetListAssetEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAssetListAssetEntry(assetListAssetEntry, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.osb.model.AssetListAssetEntry addAssetListAssetEntry(
		long assetListId, long assetEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().addAssetListAssetEntry(assetListId, assetEntryId);
	}

	public static void deleteAssetListAssetListAssetEntry(long asestListId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAssetListAssetListAssetEntry(asestListId);
	}

	public static java.util.List<com.liferay.portlet.asset.model.AssetEntry> getAssetEntries(
		long assetListId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetEntries(assetListId);
	}

	public static java.util.List<com.liferay.osb.model.AssetListAssetEntry> getAssetListAssetEntries(
		long assetListId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetListAssetEntries(assetListId);
	}

	public static void setAssetListAssetEntries(long assetListId,
		java.util.List<com.liferay.portlet.asset.model.AssetEntry> assetEntries)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().setAssetListAssetEntries(assetListId, assetEntries);
	}

	public static void setAssetListAssetEntries(long assetListId,
		long[] assetEntryIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().setAssetListAssetEntries(assetListId, assetEntryIds);
	}

	public static void clearService() {
		_service = null;
	}

	public static AssetListAssetEntryLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AssetListAssetEntryLocalService.class.getName());

			if (invokableLocalService instanceof AssetListAssetEntryLocalService) {
				_service = (AssetListAssetEntryLocalService)invokableLocalService;
			}
			else {
				_service = new AssetListAssetEntryLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AssetListAssetEntryLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AssetListAssetEntryLocalService service) {
	}

	private static AssetListAssetEntryLocalService _service;
}