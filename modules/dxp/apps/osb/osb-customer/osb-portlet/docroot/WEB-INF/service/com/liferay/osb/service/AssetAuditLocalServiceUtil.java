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
 * The utility for the asset audit local service. This utility wraps {@link com.liferay.osb.service.impl.AssetAuditLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetAuditLocalService
 * @see com.liferay.osb.service.base.AssetAuditLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AssetAuditLocalServiceImpl
 * @generated
 */
public class AssetAuditLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.AssetAuditLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the asset audit to the database. Also notifies the appropriate model listeners.
	*
	* @param assetAudit the asset audit
	* @return the asset audit that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetAudit addAssetAudit(
		com.liferay.osb.model.AssetAudit assetAudit)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addAssetAudit(assetAudit);
	}

	/**
	* Creates a new asset audit with the primary key. Does not add the asset audit to the database.
	*
	* @param assetAuditId the primary key for the new asset audit
	* @return the new asset audit
	*/
	public static com.liferay.osb.model.AssetAudit createAssetAudit(
		long assetAuditId) {
		return getService().createAssetAudit(assetAuditId);
	}

	/**
	* Deletes the asset audit with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param assetAuditId the primary key of the asset audit
	* @return the asset audit that was removed
	* @throws PortalException if a asset audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetAudit deleteAssetAudit(
		long assetAuditId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAssetAudit(assetAuditId);
	}

	/**
	* Deletes the asset audit from the database. Also notifies the appropriate model listeners.
	*
	* @param assetAudit the asset audit
	* @return the asset audit that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetAudit deleteAssetAudit(
		com.liferay.osb.model.AssetAudit assetAudit)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteAssetAudit(assetAudit);
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

	public static com.liferay.osb.model.AssetAudit fetchAssetAudit(
		long assetAuditId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchAssetAudit(assetAuditId);
	}

	/**
	* Returns the asset audit with the primary key.
	*
	* @param assetAuditId the primary key of the asset audit
	* @return the asset audit
	* @throws PortalException if a asset audit with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetAudit getAssetAudit(
		long assetAuditId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetAudit(assetAuditId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the asset audits.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of asset audits
	* @param end the upper bound of the range of asset audits (not inclusive)
	* @return the range of asset audits
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.AssetAudit> getAssetAudits(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetAudits(start, end);
	}

	/**
	* Returns the number of asset audits.
	*
	* @return the number of asset audits
	* @throws SystemException if a system exception occurred
	*/
	public static int getAssetAuditsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetAuditsCount();
	}

	/**
	* Updates the asset audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetAudit the asset audit
	* @return the asset audit that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetAudit updateAssetAudit(
		com.liferay.osb.model.AssetAudit assetAudit)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAssetAudit(assetAudit);
	}

	/**
	* Updates the asset audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param assetAudit the asset audit
	* @param merge whether to merge the asset audit with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the asset audit that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.AssetAudit updateAssetAudit(
		com.liferay.osb.model.AssetAudit assetAudit, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateAssetAudit(assetAudit, merge);
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

	public static void addAssetAudit(long userId,
		java.lang.String legalEntityName, java.lang.String className,
		long classPK, int type, int domain, java.lang.String currencyCode,
		double price)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addAssetAudit(userId, legalEntityName, className, classPK, type,
			domain, currencyCode, price);
	}

	public static void deleteAssetAudits()
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAssetAudits();
	}

	public static void deleteAssetAudits(java.lang.String className,
		long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAssetAudits(className, classPK);
	}

	public static java.util.List<com.liferay.portal.kernel.json.JSONObject> getAssetAuditPurchaseCountJSONObjects(
		java.util.Date createDateGT, java.util.Date createDateLT,
		java.lang.String vendorClassName, long vendorClassPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetAuditPurchaseCountJSONObjects(createDateGT,
			createDateLT, vendorClassName, vendorClassPK, start, end);
	}

	public static java.util.List<com.liferay.osb.model.AssetAudit> getAssetAudits(
		java.util.Date createDateGE, java.util.Date createDateLE,
		java.lang.String className, long classPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetAudits(createDateGE, createDateLE, className,
			classPK, type, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.AssetAudit> getAssetAudits(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAssetAudits(start, end, obc);
	}

	public static int getAssetAuditsCount(java.util.Date createDateGE,
		java.util.Date createDateLE, java.lang.String className, long classPK,
		int type) throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAssetAuditsCount(createDateGE, createDateLE, className,
			classPK, type);
	}

	public static void clearService() {
		_service = null;
	}

	public static AssetAuditLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					AssetAuditLocalService.class.getName());

			if (invokableLocalService instanceof AssetAuditLocalService) {
				_service = (AssetAuditLocalService)invokableLocalService;
			}
			else {
				_service = new AssetAuditLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(AssetAuditLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(AssetAuditLocalService service) {
	}

	private static AssetAuditLocalService _service;
}