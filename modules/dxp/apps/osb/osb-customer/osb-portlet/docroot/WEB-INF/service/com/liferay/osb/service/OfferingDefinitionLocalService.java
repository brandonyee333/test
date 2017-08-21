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

import com.liferay.osb.model.OfferingDefinition;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for OfferingDefinition. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see OfferingDefinitionLocalServiceUtil
 * @see com.liferay.osb.service.base.OfferingDefinitionLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.OfferingDefinitionLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface OfferingDefinitionLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OfferingDefinitionLocalServiceUtil} to access the offering definition local service. Add custom service methods to {@link com.liferay.osb.service.impl.OfferingDefinitionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasOfferingBundleOfferingDefinition(long offeringBundleId,
		long offeringDefinitionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasOfferingBundleOfferingDefinitions(long offeringBundleId);

	/**
	* Adds the offering definition to the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @return the offering definition that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public OfferingDefinition addOfferingDefinition(
		OfferingDefinition offeringDefinition);

	public OfferingDefinition addOfferingDefinition(long userId,
		long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, long maxConcurrentUsers, long maxUsers,
		boolean supportTickets) throws PortalException;

	/**
	* Creates a new offering definition with the primary key. Does not add the offering definition to the database.
	*
	* @param offeringDefinitionId the primary key for the new offering definition
	* @return the new offering definition
	*/
	public OfferingDefinition createOfferingDefinition(
		long offeringDefinitionId);

	/**
	* Deletes the offering definition from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @return the offering definition that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public OfferingDefinition deleteOfferingDefinition(
		OfferingDefinition offeringDefinition);

	/**
	* Deletes the offering definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition that was removed
	* @throws PortalException if a offering definition with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public OfferingDefinition deleteOfferingDefinition(
		long offeringDefinitionId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OfferingDefinition fetchOfferingDefinition(long offeringDefinitionId);

	/**
	* Returns the offering definition with the primary key.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition
	* @throws PortalException if a offering definition with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OfferingDefinition getOfferingDefinition(long offeringDefinitionId)
		throws PortalException;

	/**
	* Updates the offering definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinition the offering definition
	* @return the offering definition that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public OfferingDefinition updateOfferingDefinition(
		OfferingDefinition offeringDefinition);

	public OfferingDefinition updateOfferingDefinition(
		long offeringDefinitionId, long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, long maxConcurrentUsers, long maxUsers,
		boolean supportTickets) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	public DynamicQuery dynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	* @throws PortalException
	*/
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingBundleOfferingDefinitionsCount(long offeringBundleId);

	/**
	* Returns the number of offering definitions.
	*
	* @return the number of offering definitions
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingDefinitionsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingDefinitionsCount(long[] productEntryIds,
		long[] supportResponseIds);

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end);

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
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end, OrderByComparator<T> orderByComparator);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OfferingDefinition> getOfferingBundleOfferingDefinitions(
		long offeringBundleId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OfferingDefinition> getOfferingBundleOfferingDefinitions(
		long offeringBundleId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OfferingDefinition> getOfferingBundleOfferingDefinitions(
		long offeringBundleId, int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OfferingDefinition> getOfferingDefinitions(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OfferingDefinition> getOfferingDefinitions(
		long[] productEntryIds, long[] supportResponseIds, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OfferingDefinition> getProductEntryOfferingDefinitions(
		long productEntryId);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection);

	/**
	* Returns the offeringBundleIds of the offering bundles associated with the offering definition.
	*
	* @param offeringDefinitionId the offeringDefinitionId of the offering definition
	* @return long[] the offeringBundleIds of offering bundles associated with the offering definition
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getOfferingBundlePrimaryKeys(long offeringDefinitionId);

	public void addOfferingBundleOfferingDefinition(long offeringBundleId,
		OfferingDefinition offeringDefinition);

	public void addOfferingBundleOfferingDefinition(long offeringBundleId,
		long offeringDefinitionId);

	public void addOfferingBundleOfferingDefinitions(long offeringBundleId,
		List<OfferingDefinition> offeringDefinitions);

	public void addOfferingBundleOfferingDefinitions(long offeringBundleId,
		long[] offeringDefinitionIds);

	public void clearOfferingBundleOfferingDefinitions(long offeringBundleId);

	public void deleteOfferingBundleOfferingDefinition(long offeringBundleId,
		OfferingDefinition offeringDefinition);

	public void deleteOfferingBundleOfferingDefinition(long offeringBundleId,
		long offeringDefinitionId);

	public void deleteOfferingBundleOfferingDefinitions(long offeringBundleId,
		List<OfferingDefinition> offeringDefinitions);

	public void deleteOfferingBundleOfferingDefinitions(long offeringBundleId,
		long[] offeringDefinitionIds);

	public void setOfferingBundleOfferingDefinitions(long offeringBundleId,
		long[] offeringDefinitionIds);
}