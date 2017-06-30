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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the offering definition local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingDefinitionLocalServiceUtil
 * @see com.liferay.osb.service.base.OfferingDefinitionLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.OfferingDefinitionLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface OfferingDefinitionLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OfferingDefinitionLocalServiceUtil} to access the offering definition local service. Add custom service methods to {@link com.liferay.osb.service.impl.OfferingDefinitionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the offering definition to the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @return the offering definition that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingDefinition addOfferingDefinition(
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Creates a new offering definition with the primary key. Does not add the offering definition to the database.
	*
	* @param offeringDefinitionId the primary key for the new offering definition
	* @return the new offering definition
	*/
	public com.liferay.osb.model.OfferingDefinition createOfferingDefinition(
		long offeringDefinitionId);

	/**
	* Deletes the offering definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition that was removed
	* @throws PortalException if a offering definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingDefinition deleteOfferingDefinition(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Deletes the offering definition from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @return the offering definition that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingDefinition deleteOfferingDefinition(
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

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
		throws com.liferay.portal.kernel.exception.SystemException;

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
		int end) throws com.liferay.portal.kernel.exception.SystemException;

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
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.osb.model.OfferingDefinition fetchOfferingDefinition(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the offering definition with the primary key.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition
	* @throws PortalException if a offering definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.osb.model.OfferingDefinition getOfferingDefinition(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of offering definitions.
	*
	* @return the number of offering definitions
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingDefinitionsCount()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the offering definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @return the offering definition that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingDefinition updateOfferingDefinition(
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Updates the offering definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @param merge whether to merge the offering definition with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the offering definition that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.OfferingDefinition updateOfferingDefinition(
		com.liferay.osb.model.OfferingDefinition offeringDefinition,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addOfferingBundleOfferingDefinition(long offeringBundleId,
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addOfferingBundleOfferingDefinition(long offeringBundleId,
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addOfferingBundleOfferingDefinitions(long offeringBundleId,
		long[] offeringDefinitionIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void addOfferingBundleOfferingDefinitions(long offeringBundleId,
		java.util.List<com.liferay.osb.model.OfferingDefinition> OfferingDefinitions)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void clearOfferingBundleOfferingDefinitions(long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteOfferingBundleOfferingDefinition(long offeringBundleId,
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteOfferingBundleOfferingDefinition(long offeringBundleId,
		com.liferay.osb.model.OfferingDefinition offeringDefinition)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteOfferingBundleOfferingDefinitions(long offeringBundleId,
		long[] offeringDefinitionIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void deleteOfferingBundleOfferingDefinitions(long offeringBundleId,
		java.util.List<com.liferay.osb.model.OfferingDefinition> OfferingDefinitions)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingBundleOfferingDefinitions(
		long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingBundleOfferingDefinitions(
		long offeringBundleId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingBundleOfferingDefinitions(
		long offeringBundleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingBundleOfferingDefinitionsCount(long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasOfferingBundleOfferingDefinition(long offeringBundleId,
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasOfferingBundleOfferingDefinitions(long offeringBundleId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* @throws SystemException if a system exception occurred
	*/
	public void setOfferingBundleOfferingDefinitions(long offeringBundleId,
		long[] offeringDefinitionIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier();

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier);

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	public com.liferay.osb.model.OfferingDefinition addOfferingDefinition(
		long userId, long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, long maxConcurrentUsers, long maxUsers,
		boolean supportTickets)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getOfferingDefinitions(
		long[] productEntryIds, long[] supportResponseIds, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingDefinitionsCount(long[] productEntryIds,
		long[] supportResponseIds)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getProductEntryOfferingDefinitions(
		long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public java.util.List<com.liferay.osb.model.OfferingDefinition> getSupportResponseOfferingDefinitions(
		long supportResponseId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.OfferingDefinition updateOfferingDefinition(
		long offeringDefinitionId, long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, long maxConcurrentUsers, long maxUsers,
		boolean supportTickets)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException;
}