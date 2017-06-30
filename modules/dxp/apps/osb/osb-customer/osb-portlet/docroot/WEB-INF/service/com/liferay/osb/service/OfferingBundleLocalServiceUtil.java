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
 * The utility for the offering bundle local service. This utility wraps {@link com.liferay.osb.service.impl.OfferingBundleLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingBundleLocalService
 * @see com.liferay.osb.service.base.OfferingBundleLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.OfferingBundleLocalServiceImpl
 * @generated
 */
public class OfferingBundleLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.OfferingBundleLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the offering bundle to the database. Also notifies the appropriate model listeners.
	*
	* @param offeringBundle the offering bundle
	* @return the offering bundle that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingBundle addOfferingBundle(
		com.liferay.osb.model.OfferingBundle offeringBundle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addOfferingBundle(offeringBundle);
	}

	/**
	* Creates a new offering bundle with the primary key. Does not add the offering bundle to the database.
	*
	* @param offeringBundleId the primary key for the new offering bundle
	* @return the new offering bundle
	*/
	public static com.liferay.osb.model.OfferingBundle createOfferingBundle(
		long offeringBundleId) {
		return getService().createOfferingBundle(offeringBundleId);
	}

	/**
	* Deletes the offering bundle with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle that was removed
	* @throws PortalException if a offering bundle with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingBundle deleteOfferingBundle(
		long offeringBundleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteOfferingBundle(offeringBundleId);
	}

	/**
	* Deletes the offering bundle from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringBundle the offering bundle
	* @return the offering bundle that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingBundle deleteOfferingBundle(
		com.liferay.osb.model.OfferingBundle offeringBundle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteOfferingBundle(offeringBundle);
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

	public static com.liferay.osb.model.OfferingBundle fetchOfferingBundle(
		long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchOfferingBundle(offeringBundleId);
	}

	/**
	* Returns the offering bundle with the primary key.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle
	* @throws PortalException if a offering bundle with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingBundle getOfferingBundle(
		long offeringBundleId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getOfferingBundle(offeringBundleId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the offering bundles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of offering bundles
	* @param end the upper bound of the range of offering bundles (not inclusive)
	* @return the range of offering bundles
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOfferingBundles(start, end);
	}

	/**
	* Returns the number of offering bundles.
	*
	* @return the number of offering bundles
	* @throws SystemException if a system exception occurred
	*/
	public static int getOfferingBundlesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOfferingBundlesCount();
	}

	/**
	* Updates the offering bundle in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringBundle the offering bundle
	* @return the offering bundle that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingBundle updateOfferingBundle(
		com.liferay.osb.model.OfferingBundle offeringBundle)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateOfferingBundle(offeringBundle);
	}

	/**
	* Updates the offering bundle in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringBundle the offering bundle
	* @param merge whether to merge the offering bundle with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the offering bundle that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingBundle updateOfferingBundle(
		com.liferay.osb.model.OfferingBundle offeringBundle, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateOfferingBundle(offeringBundle, merge);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addOfferingDefinitionOfferingBundle(
		long offeringDefinitionId, long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundleId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addOfferingDefinitionOfferingBundle(
		long offeringDefinitionId,
		com.liferay.osb.model.OfferingBundle offeringBundle)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundle);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, long[] offeringBundleIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addOfferingDefinitionOfferingBundles(offeringDefinitionId,
			offeringBundleIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addOfferingDefinitionOfferingBundles(
		long offeringDefinitionId,
		java.util.List<com.liferay.osb.model.OfferingBundle> OfferingBundles)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addOfferingDefinitionOfferingBundles(offeringDefinitionId,
			OfferingBundles);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void clearOfferingDefinitionOfferingBundles(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().clearOfferingDefinitionOfferingBundles(offeringDefinitionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteOfferingDefinitionOfferingBundle(
		long offeringDefinitionId, long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundleId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteOfferingDefinitionOfferingBundle(
		long offeringDefinitionId,
		com.liferay.osb.model.OfferingBundle offeringBundle)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundle);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, long[] offeringBundleIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteOfferingDefinitionOfferingBundles(offeringDefinitionId,
			offeringBundleIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteOfferingDefinitionOfferingBundles(
		long offeringDefinitionId,
		java.util.List<com.liferay.osb.model.OfferingBundle> OfferingBundles)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteOfferingDefinitionOfferingBundles(offeringDefinitionId,
			OfferingBundles);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingDefinitionOfferingBundles(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOfferingDefinitionOfferingBundles(offeringDefinitionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOfferingDefinitionOfferingBundles(offeringDefinitionId,
			start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOfferingDefinitionOfferingBundles(offeringDefinitionId,
			start, end, orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static int getOfferingDefinitionOfferingBundlesCount(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOfferingDefinitionOfferingBundlesCount(offeringDefinitionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasOfferingDefinitionOfferingBundle(
		long offeringDefinitionId, long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundleId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasOfferingDefinitionOfferingBundles(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasOfferingDefinitionOfferingBundles(offeringDefinitionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void setOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, long[] offeringBundleIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setOfferingDefinitionOfferingBundles(offeringDefinitionId,
			offeringBundleIds);
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

	public static com.liferay.osb.model.OfferingBundle addOfferingBundle(
		long userId, java.lang.String name, long[] offeringDefinitionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addOfferingBundle(userId, name, offeringDefinitionIds);
	}

	public static com.liferay.osb.model.OfferingBundle updateOfferingBundle(
		long offeringBundleId, java.lang.String name,
		long[] offeringDefinitionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateOfferingBundle(offeringBundleId, name,
			offeringDefinitionIds);
	}

	public static void clearService() {
		_service = null;
	}

	public static OfferingBundleLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					OfferingBundleLocalService.class.getName());

			if (invokableLocalService instanceof OfferingBundleLocalService) {
				_service = (OfferingBundleLocalService)invokableLocalService;
			}
			else {
				_service = new OfferingBundleLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(OfferingBundleLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(OfferingBundleLocalService service) {
	}

	private static OfferingBundleLocalService _service;
}