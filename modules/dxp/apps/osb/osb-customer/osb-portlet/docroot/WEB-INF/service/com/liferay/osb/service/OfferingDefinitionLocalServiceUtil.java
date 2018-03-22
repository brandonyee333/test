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
 * Provides the local service utility for OfferingDefinition. This utility wraps
 * {@link com.liferay.osb.service.impl.OfferingDefinitionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see OfferingDefinitionLocalService
 * @see com.liferay.osb.service.base.OfferingDefinitionLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.OfferingDefinitionLocalServiceImpl
 * @generated
 */
@ProviderType
public class OfferingDefinitionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.OfferingDefinitionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasOfferingBundleOfferingDefinition(
		long offeringBundleId, long offeringDefinitionId) {
		return getService()
				   .hasOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinitionId);
	}

	public static boolean hasOfferingBundleOfferingDefinitions(
		long offeringBundleId) {
		return getService()
				   .hasOfferingBundleOfferingDefinitions(offeringBundleId);
	}

	/**
	* Adds the offering definition to the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @return the offering definition that was added
	*/
	public static com.liferay.osb.model.OfferingDefinition addOfferingDefinition(
		com.liferay.osb.model.OfferingDefinition offeringDefinition) {
		return getService().addOfferingDefinition(offeringDefinition);
	}

	public static com.liferay.osb.model.OfferingDefinition addOfferingDefinition(
		long userId, long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, long maxConcurrentUsers, long maxUsers,
		boolean supportTickets)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addOfferingDefinition(userId, productEntryId,
			supportResponseId, productDescription, licenses, unlimitedLicenses,
			maxConcurrentUsers, maxUsers, supportTickets);
	}

	/**
	* Creates a new offering definition with the primary key. Does not add the offering definition to the database.
	*
	* @param offeringDefinitionId the primary key for the new offering definition
	* @return the new offering definition
	*/
	public static com.liferay.osb.model.OfferingDefinition createOfferingDefinition(
		long offeringDefinitionId) {
		return getService().createOfferingDefinition(offeringDefinitionId);
	}

	/**
	* Deletes the offering definition from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @return the offering definition that was removed
	*/
	public static com.liferay.osb.model.OfferingDefinition deleteOfferingDefinition(
		com.liferay.osb.model.OfferingDefinition offeringDefinition) {
		return getService().deleteOfferingDefinition(offeringDefinition);
	}

	/**
	* Deletes the offering definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition that was removed
	* @throws PortalException if a offering definition with the primary key could not be found
	*/
	public static com.liferay.osb.model.OfferingDefinition deleteOfferingDefinition(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteOfferingDefinition(offeringDefinitionId);
	}

	public static com.liferay.osb.model.OfferingDefinition fetchOfferingDefinition(
		long offeringDefinitionId) {
		return getService().fetchOfferingDefinition(offeringDefinitionId);
	}

	/**
	* Returns the offering definition with the primary key.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition
	* @throws PortalException if a offering definition with the primary key could not be found
	*/
	public static com.liferay.osb.model.OfferingDefinition getOfferingDefinition(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getOfferingDefinition(offeringDefinitionId);
	}

	/**
	* Updates the offering definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @return the offering definition that was updated
	*/
	public static com.liferay.osb.model.OfferingDefinition updateOfferingDefinition(
		com.liferay.osb.model.OfferingDefinition offeringDefinition) {
		return getService().updateOfferingDefinition(offeringDefinition);
	}

	public static com.liferay.osb.model.OfferingDefinition updateOfferingDefinition(
		long offeringDefinitionId, long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, long maxConcurrentUsers, long maxUsers,
		boolean supportTickets)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateOfferingDefinition(offeringDefinitionId,
			productEntryId, supportResponseId, productDescription, licenses,
			unlimitedLicenses, maxConcurrentUsers, maxUsers, supportTickets);
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

	public static int getOfferingBundleOfferingDefinitionsCount(
		long offeringBundleId) {
		return getService()
				   .getOfferingBundleOfferingDefinitionsCount(offeringBundleId);
	}

	/**
	* Returns the number of offering definitions.
	*
	* @return the number of offering definitions
	*/
	public static int getOfferingDefinitionsCount() {
		return getService().getOfferingDefinitionsCount();
	}

	public static int getOfferingDefinitionsCount(long[] productEntryIds,
		long[] supportResponseIds) {
		return getService()
				   .getOfferingDefinitionsCount(productEntryIds,
			supportResponseIds);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingBundleOfferingDefinitions(
		long offeringBundleId) {
		return getService()
				   .getOfferingBundleOfferingDefinitions(offeringBundleId);
	}

	public static java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingBundleOfferingDefinitions(
		long offeringBundleId, int start, int end) {
		return getService()
				   .getOfferingBundleOfferingDefinitions(offeringBundleId,
			start, end);
	}

	public static java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingBundleOfferingDefinitions(
		long offeringBundleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.OfferingDefinition> orderByComparator) {
		return getService()
				   .getOfferingBundleOfferingDefinitions(offeringBundleId,
			start, end, orderByComparator);
	}

	/**
	* Returns a range of all the offering definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @return the range of offering definitions
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		int start, int end) {
		return getService().getOfferingDefinitions(start, end);
	}

	public static java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long[] productEntryIds, long[] supportResponseIds, int start, int end) {
		return getService()
				   .getOfferingDefinitions(productEntryIds, supportResponseIds,
			start, end);
	}

	public static java.util.List<com.liferay.osb.model.OfferingDefinition> getProductEntryOfferingDefinitions(
		long productEntryId) {
		return getService().getProductEntryOfferingDefinitions(productEntryId);
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
	* Returns the offeringBundleIds of the offering bundles associated with the offering definition.
	*
	* @param offeringDefinitionId the offeringDefinitionId of the offering definition
	* @return long[] the offeringBundleIds of offering bundles associated with the offering definition
	*/
	public static long[] getOfferingBundlePrimaryKeys(long offeringDefinitionId) {
		return getService().getOfferingBundlePrimaryKeys(offeringDefinitionId);
	}

	public static void addOfferingBundleOfferingDefinition(
		long offeringBundleId,
		com.liferay.osb.model.OfferingDefinition offeringDefinition) {
		getService()
			.addOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinition);
	}

	public static void addOfferingBundleOfferingDefinition(
		long offeringBundleId, long offeringDefinitionId) {
		getService()
			.addOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinitionId);
	}

	public static void addOfferingBundleOfferingDefinitions(
		long offeringBundleId,
		java.util.List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions) {
		getService()
			.addOfferingBundleOfferingDefinitions(offeringBundleId,
			offeringDefinitions);
	}

	public static void addOfferingBundleOfferingDefinitions(
		long offeringBundleId, long[] offeringDefinitionIds) {
		getService()
			.addOfferingBundleOfferingDefinitions(offeringBundleId,
			offeringDefinitionIds);
	}

	public static void clearOfferingBundleOfferingDefinitions(
		long offeringBundleId) {
		getService().clearOfferingBundleOfferingDefinitions(offeringBundleId);
	}

	public static void deleteOfferingBundleOfferingDefinition(
		long offeringBundleId,
		com.liferay.osb.model.OfferingDefinition offeringDefinition) {
		getService()
			.deleteOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinition);
	}

	public static void deleteOfferingBundleOfferingDefinition(
		long offeringBundleId, long offeringDefinitionId) {
		getService()
			.deleteOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinitionId);
	}

	public static void deleteOfferingBundleOfferingDefinitions(
		long offeringBundleId,
		java.util.List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions) {
		getService()
			.deleteOfferingBundleOfferingDefinitions(offeringBundleId,
			offeringDefinitions);
	}

	public static void deleteOfferingBundleOfferingDefinitions(
		long offeringBundleId, long[] offeringDefinitionIds) {
		getService()
			.deleteOfferingBundleOfferingDefinitions(offeringBundleId,
			offeringDefinitionIds);
	}

	public static void setOfferingBundleOfferingDefinitions(
		long offeringBundleId, long[] offeringDefinitionIds) {
		getService()
			.setOfferingBundleOfferingDefinitions(offeringBundleId,
			offeringDefinitionIds);
	}

	public static void clearService() {
		_service = null;
	}

	public static OfferingDefinitionLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					OfferingDefinitionLocalService.class.getName());

			if (invokableLocalService instanceof OfferingDefinitionLocalService) {
				_service = (OfferingDefinitionLocalService)invokableLocalService;
			}
			else {
				_service = new OfferingDefinitionLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(OfferingDefinitionLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static OfferingDefinitionLocalService _service;
}