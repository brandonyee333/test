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
 * The utility for the offering definition local service. This utility wraps {@link com.liferay.osb.service.impl.OfferingDefinitionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingDefinitionLocalService
 * @see com.liferay.osb.service.base.OfferingDefinitionLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.OfferingDefinitionLocalServiceImpl
 * @generated
 */
public class OfferingDefinitionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.OfferingDefinitionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the offering definition to the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @return the offering definition that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition addOfferingDefinition(
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addOfferingDefinition(offeringDefinition);
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
	* Deletes the offering definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition that was removed
	* @throws PortalException if a offering definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition deleteOfferingDefinition(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteOfferingDefinition(offeringDefinitionId);
	}

	/**
	* Deletes the offering definition from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @return the offering definition that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition deleteOfferingDefinition(
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteOfferingDefinition(offeringDefinition);
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

	public static com.liferay.osb.model.OfferingDefinition fetchOfferingDefinition(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchOfferingDefinition(offeringDefinitionId);
	}

	/**
	* Returns the offering definition with the primary key.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition
	* @throws PortalException if a offering definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition getOfferingDefinition(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getOfferingDefinition(offeringDefinitionId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the offering definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @return the range of offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOfferingDefinitions(start, end);
	}

	/**
	* Returns the number of offering definitions.
	*
	* @return the number of offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static int getOfferingDefinitionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOfferingDefinitionsCount();
	}

	/**
	* Updates the offering definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @return the offering definition that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition updateOfferingDefinition(
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateOfferingDefinition(offeringDefinition);
	}

	/**
	* Updates the offering definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @param merge whether to merge the offering definition with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the offering definition that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition updateOfferingDefinition(
		com.liferay.osb.model.OfferingDefinition offeringDefinition,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateOfferingDefinition(offeringDefinition, merge);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addOfferingBundleOfferingDefinition(
		long offeringBundleId, long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinitionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addOfferingBundleOfferingDefinition(
		long offeringBundleId,
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinition);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addOfferingBundleOfferingDefinitions(
		long offeringBundleId, long[] offeringDefinitionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addOfferingBundleOfferingDefinitions(offeringBundleId,
			offeringDefinitionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addOfferingBundleOfferingDefinitions(
		long offeringBundleId,
		java.util.List<com.liferay.osb.model.OfferingDefinition> OfferingDefinitions)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addOfferingBundleOfferingDefinitions(offeringBundleId,
			OfferingDefinitions);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void clearOfferingBundleOfferingDefinitions(
		long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().clearOfferingBundleOfferingDefinitions(offeringBundleId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteOfferingBundleOfferingDefinition(
		long offeringBundleId, long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinitionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteOfferingBundleOfferingDefinition(
		long offeringBundleId,
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinition);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteOfferingBundleOfferingDefinitions(
		long offeringBundleId, long[] offeringDefinitionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteOfferingBundleOfferingDefinitions(offeringBundleId,
			offeringDefinitionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteOfferingBundleOfferingDefinitions(
		long offeringBundleId,
		java.util.List<com.liferay.osb.model.OfferingDefinition> OfferingDefinitions)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteOfferingBundleOfferingDefinitions(offeringBundleId,
			OfferingDefinitions);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingBundleOfferingDefinitions(
		long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOfferingBundleOfferingDefinitions(offeringBundleId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingBundleOfferingDefinitions(
		long offeringBundleId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOfferingBundleOfferingDefinitions(offeringBundleId,
			start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingBundleOfferingDefinitions(
		long offeringBundleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOfferingBundleOfferingDefinitions(offeringBundleId,
			start, end, orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static int getOfferingBundleOfferingDefinitionsCount(
		long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOfferingBundleOfferingDefinitionsCount(offeringBundleId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasOfferingBundleOfferingDefinition(
		long offeringBundleId, long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasOfferingBundleOfferingDefinition(offeringBundleId,
			offeringDefinitionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasOfferingBundleOfferingDefinitions(
		long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasOfferingBundleOfferingDefinitions(offeringBundleId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void setOfferingBundleOfferingDefinitions(
		long offeringBundleId, long[] offeringDefinitionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setOfferingBundleOfferingDefinitions(offeringBundleId,
			offeringDefinitionIds);
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

	public static com.liferay.osb.model.OfferingDefinition addOfferingDefinition(
		long userId, long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, long maxConcurrentUsers, long maxUsers,
		boolean supportTickets)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addOfferingDefinition(userId, productEntryId,
			supportResponseId, productDescription, licenses, unlimitedLicenses,
			maxConcurrentUsers, maxUsers, supportTickets);
	}

	public static java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long[] productEntryIds, long[] supportResponseIds, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOfferingDefinitions(productEntryIds, supportResponseIds,
			start, end);
	}

	public static int getOfferingDefinitionsCount(long[] productEntryIds,
		long[] supportResponseIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOfferingDefinitionsCount(productEntryIds,
			supportResponseIds);
	}

	public static java.util.List<com.liferay.osb.model.OfferingDefinition> getProductEntryOfferingDefinitions(
		long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getProductEntryOfferingDefinitions(productEntryId);
	}

	public static java.util.List<com.liferay.osb.model.OfferingDefinition> getSupportResponseOfferingDefinitions(
		long supportResponseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSupportResponseOfferingDefinitions(supportResponseId);
	}

	public static com.liferay.osb.model.OfferingDefinition updateOfferingDefinition(
		long offeringDefinitionId, long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, long maxConcurrentUsers, long maxUsers,
		boolean supportTickets)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateOfferingDefinition(offeringDefinitionId,
			productEntryId, supportResponseId, productDescription, licenses,
			unlimitedLicenses, maxConcurrentUsers, maxUsers, supportTickets);
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

	/**
	 * @deprecated
	 */
	public void setService(OfferingDefinitionLocalService service) {
	}

	private static OfferingDefinitionLocalService _service;
}