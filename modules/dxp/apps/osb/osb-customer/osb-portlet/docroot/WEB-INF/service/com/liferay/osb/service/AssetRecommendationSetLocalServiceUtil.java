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
 * The utility for the asset recommendation set local service. This utility wraps {@link com.liferay.osb.service.impl.AssetRecommendationSetLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetRecommendationSetLocalService
 * @see com.liferay.osb.service.base.AssetRecommendationSetLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AssetRecommendationSetLocalServiceImpl
 * @generated
 */
public class AssetRecommendationSetLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AssetRecommendationSetLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the asset recommendation set to the database. Also notifies the appropriate model listeners.
	*
	* @param assetRecommendationSet the asset recommendation set
	* @return the asset recommendation set that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetRecommendationSet addAssetRecommendationSet(
		com.liferay.osb.model.AssetRecommendationSet assetRecommendationSet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAssetRecommendationSet(assetRecommendationSet);
	}

	/**
	* Creates a new asset recommendation set with the primary key. Does not add the asset recommendation set to the database.
	*
	* @param assetRecommendationSetId the primary key for the new asset recommendation set
	* @return the new asset recommendation set
	*/
	public static com.liferay.osb.model.AssetRecommendationSet createAssetRecommendationSet(
		long assetRecommendationSetId) {
		return getService()
				   .createAssetRecommendationSet(assetRecommendationSetId);
	}

	/**
	* Deletes the asset recommendation set with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetRecommendationSetId the primary key of the asset recommendation set
	* @return the asset recommendation set that was removed
	* @throws PortalException if a asset recommendation set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetRecommendationSet deleteAssetRecommendationSet(
		long assetRecommendationSetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .deleteAssetRecommendationSet(assetRecommendationSetId);
	}

	/**
	* Deletes the asset recommendation set from the database. Also notifies the appropriate model listeners.
	*
	* @param assetRecommendationSet the asset recommendation set
	* @return the asset recommendation set that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetRecommendationSet deleteAssetRecommendationSet(
		com.liferay.osb.model.AssetRecommendationSet assetRecommendationSet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAssetRecommendationSet(assetRecommendationSet);
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

	public static com.liferay.osb.model.AssetRecommendationSet fetchAssetRecommendationSet(
		long assetRecommendationSetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAssetRecommendationSet(assetRecommendationSetId);
	}

	/**
	* Returns the asset recommendation set with the primary key.
	*
	* @param assetRecommendationSetId the primary key of the asset recommendation set
	* @return the asset recommendation set
	* @throws PortalException if a asset recommendation set with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetRecommendationSet getAssetRecommendationSet(
		long assetRecommendationSetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetRecommendationSet(assetRecommendationSetId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset recommendation sets.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset recommendation sets
	* @param end the upper bound of the range of asset recommendation sets (not inclusive)
	* @return the range of asset recommendation sets
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetRecommendationSet> getAssetRecommendationSets(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetRecommendationSets(start, end);
	}

	/**
	* Returns the number of asset recommendation sets.
	*
	* @return the number of asset recommendation sets
	* @throws SystemException if a system exception occurred
	*/
	public static int getAssetRecommendationSetsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetRecommendationSetsCount();
	}

	/**
	* Updates the asset recommendation set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetRecommendationSet the asset recommendation set
	* @return the asset recommendation set that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetRecommendationSet updateAssetRecommendationSet(
		com.liferay.osb.model.AssetRecommendationSet assetRecommendationSet)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAssetRecommendationSet(assetRecommendationSet);
	}

	/**
	* Updates the asset recommendation set in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetRecommendationSet the asset recommendation set
	* @param merge whether to merge the asset recommendation set with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset recommendation set that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetRecommendationSet updateAssetRecommendationSet(
		com.liferay.osb.model.AssetRecommendationSet assetRecommendationSet,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateAssetRecommendationSet(assetRecommendationSet, merge);
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

	public static com.liferay.osb.model.AssetRecommendationSet addAssetRecommendationSet(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAssetRecommendationSet(className, classPK);
	}

	public static void deleteAssetRecommendationSet(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAssetRecommendationSet(className, classPK);
	}

	public static com.liferay.osb.model.AssetRecommendationSet fetchAssetRecommendationSet(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAssetRecommendationSet(className, classPK);
	}

	public static void updateAssetRecommendationSets(long userId,
		java.lang.String className, long classPK, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.updateAssetRecommendationSets(userId, className, classPK, type);
	}

	public static void clearService() {
		_service = null;
	}

	public static AssetRecommendationSetLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AssetRecommendationSetLocalService.class.getName());

			if (invokableLocalService instanceof AssetRecommendationSetLocalService) {
				_service = (AssetRecommendationSetLocalService)invokableLocalService;
			}
			else {
				_service = new AssetRecommendationSetLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AssetRecommendationSetLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AssetRecommendationSetLocalService service) {
	}

	private static AssetRecommendationSetLocalService _service;
}