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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for OfferingBundle. This utility wraps
 * {@link com.liferay.osb.service.impl.OfferingBundleLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see OfferingBundleLocalService
 * @see com.liferay.osb.service.base.OfferingBundleLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.OfferingBundleLocalServiceImpl
 * @generated
 */
@ProviderType
public class OfferingBundleLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.OfferingBundleLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasOfferingDefinitionOfferingBundle(
		long offeringDefinitionId, long offeringBundleId) {
		return getService()
				   .hasOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundleId);
	}

	public static boolean hasOfferingDefinitionOfferingBundles(
		long offeringDefinitionId) {
		return getService()
				   .hasOfferingDefinitionOfferingBundles(offeringDefinitionId);
	}

	/**
	* Adds the offering bundle to the database. Also notifies the appropriate model listeners.
	*
	* @param offeringBundle the offering bundle
	* @return the offering bundle that was added
	*/
	public static com.liferay.osb.model.OfferingBundle addOfferingBundle(
		com.liferay.osb.model.OfferingBundle offeringBundle) {
		return getService().addOfferingBundle(offeringBundle);
	}

	public static com.liferay.osb.model.OfferingBundle addOfferingBundle(
		long userId, java.lang.String name, long[] offeringDefinitionIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addOfferingBundle(userId, name, offeringDefinitionIds);
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
	* Deletes the offering bundle from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringBundle the offering bundle
	* @return the offering bundle that was removed
	*/
	public static com.liferay.osb.model.OfferingBundle deleteOfferingBundle(
		com.liferay.osb.model.OfferingBundle offeringBundle) {
		return getService().deleteOfferingBundle(offeringBundle);
	}

	/**
	* Deletes the offering bundle with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle that was removed
	* @throws PortalException if a offering bundle with the primary key could not be found
	*/
	public static com.liferay.osb.model.OfferingBundle deleteOfferingBundle(
		long offeringBundleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteOfferingBundle(offeringBundleId);
	}

	public static com.liferay.osb.model.OfferingBundle fetchOfferingBundle(
		long offeringBundleId) {
		return getService().fetchOfferingBundle(offeringBundleId);
	}

	/**
	* Returns the offering bundle with the primary key.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle
	* @throws PortalException if a offering bundle with the primary key could not be found
	*/
	public static com.liferay.osb.model.OfferingBundle getOfferingBundle(
		long offeringBundleId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOfferingBundle(offeringBundleId);
	}

	/**
	* Updates the offering bundle in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringBundle the offering bundle
	* @return the offering bundle that was updated
	*/
	public static com.liferay.osb.model.OfferingBundle updateOfferingBundle(
		com.liferay.osb.model.OfferingBundle offeringBundle) {
		return getService().updateOfferingBundle(offeringBundle);
	}

	public static com.liferay.osb.model.OfferingBundle updateOfferingBundle(
		long offeringBundleId, java.lang.String name,
		long[] offeringDefinitionIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateOfferingBundle(offeringBundleId, name,
			offeringDefinitionIds);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the number of offering bundles.
	*
	* @return the number of offering bundles
	*/
	public static int getOfferingBundlesCount() {
		return getService().getOfferingBundlesCount();
	}

	public static int getOfferingDefinitionOfferingBundlesCount(
		long offeringDefinitionId) {
		return getService()
				   .getOfferingDefinitionOfferingBundlesCount(offeringDefinitionId);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingBundleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingBundleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns a range of all the offering bundles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingBundleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of offering bundles
	* @param end the upper bound of the range of offering bundles (not inclusive)
	* @return the range of offering bundles
	*/
	public static java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		int start, int end) {
		return getService().getOfferingBundles(start, end);
	}

	public static java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingDefinitionOfferingBundles(
		long offeringDefinitionId) {
		return getService()
				   .getOfferingDefinitionOfferingBundles(offeringDefinitionId);
	}

	public static java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, int start, int end) {
		return getService()
				   .getOfferingDefinitionOfferingBundles(offeringDefinitionId,
			start, end);
	}

	public static java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.OfferingBundle> orderByComparator) {
		return getService()
				   .getOfferingDefinitionOfferingBundles(offeringDefinitionId,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Returns the offeringDefinitionIds of the offering definitions associated with the offering bundle.
	*
	* @param offeringBundleId the offeringBundleId of the offering bundle
	* @return long[] the offeringDefinitionIds of offering definitions associated with the offering bundle
	*/
	public static long[] getOfferingDefinitionPrimaryKeys(long offeringBundleId) {
		return getService().getOfferingDefinitionPrimaryKeys(offeringBundleId);
	}

	public static void addOfferingDefinitionOfferingBundle(
		long offeringDefinitionId,
		com.liferay.osb.model.OfferingBundle offeringBundle) {
		getService()
			.addOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundle);
	}

	public static void addOfferingDefinitionOfferingBundle(
		long offeringDefinitionId, long offeringBundleId) {
		getService()
			.addOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundleId);
	}

	public static void addOfferingDefinitionOfferingBundles(
		long offeringDefinitionId,
		java.util.List<com.liferay.osb.model.OfferingBundle> offeringBundles) {
		getService()
			.addOfferingDefinitionOfferingBundles(offeringDefinitionId,
			offeringBundles);
	}

	public static void addOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, long[] offeringBundleIds) {
		getService()
			.addOfferingDefinitionOfferingBundles(offeringDefinitionId,
			offeringBundleIds);
	}

	public static void clearOfferingDefinitionOfferingBundles(
		long offeringDefinitionId) {
		getService().clearOfferingDefinitionOfferingBundles(offeringDefinitionId);
	}

	public static void deleteOfferingDefinitionOfferingBundle(
		long offeringDefinitionId,
		com.liferay.osb.model.OfferingBundle offeringBundle) {
		getService()
			.deleteOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundle);
	}

	public static void deleteOfferingDefinitionOfferingBundle(
		long offeringDefinitionId, long offeringBundleId) {
		getService()
			.deleteOfferingDefinitionOfferingBundle(offeringDefinitionId,
			offeringBundleId);
	}

	public static void deleteOfferingDefinitionOfferingBundles(
		long offeringDefinitionId,
		java.util.List<com.liferay.osb.model.OfferingBundle> offeringBundles) {
		getService()
			.deleteOfferingDefinitionOfferingBundles(offeringDefinitionId,
			offeringBundles);
	}

	public static void deleteOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, long[] offeringBundleIds) {
		getService()
			.deleteOfferingDefinitionOfferingBundles(offeringDefinitionId,
			offeringBundleIds);
	}

	public static void setOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, long[] offeringBundleIds) {
		getService()
			.setOfferingDefinitionOfferingBundles(offeringDefinitionId,
			offeringBundleIds);
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

	private static OfferingBundleLocalService _service;
}