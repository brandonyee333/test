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

import com.liferay.osb.model.OfferingBundle;

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
 * Provides the local service interface for OfferingBundle. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see OfferingBundleLocalServiceUtil
 * @see com.liferay.osb.service.base.OfferingBundleLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.OfferingBundleLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface OfferingBundleLocalService extends BaseLocalService,
	InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OfferingBundleLocalServiceUtil} to access the offering bundle local service. Add custom service methods to {@link com.liferay.osb.service.impl.OfferingBundleLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasOfferingDefinitionOfferingBundle(
		long offeringDefinitionId, long offeringBundleId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public boolean hasOfferingDefinitionOfferingBundles(
		long offeringDefinitionId);

	/**
	* Adds the offering bundle to the database. Also notifies the appropriate model listeners.
	*
	* @param offeringBundle the offering bundle
	* @return the offering bundle that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public OfferingBundle addOfferingBundle(OfferingBundle offeringBundle);

	public OfferingBundle addOfferingBundle(long userId, java.lang.String name,
		long[] offeringDefinitionIds) throws PortalException;

	/**
	* Creates a new offering bundle with the primary key. Does not add the offering bundle to the database.
	*
	* @param offeringBundleId the primary key for the new offering bundle
	* @return the new offering bundle
	*/
	public OfferingBundle createOfferingBundle(long offeringBundleId);

	/**
	* Deletes the offering bundle from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringBundle the offering bundle
	* @return the offering bundle that was removed
	*/
	@Indexable(type = IndexableType.DELETE)
	public OfferingBundle deleteOfferingBundle(OfferingBundle offeringBundle);

	/**
	* Deletes the offering bundle with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle that was removed
	* @throws PortalException if a offering bundle with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public OfferingBundle deleteOfferingBundle(long offeringBundleId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OfferingBundle fetchOfferingBundle(long offeringBundleId);

	/**
	* Returns the offering bundle with the primary key.
	*
	* @param offeringBundleId the primary key of the offering bundle
	* @return the offering bundle
	* @throws PortalException if a offering bundle with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public OfferingBundle getOfferingBundle(long offeringBundleId)
		throws PortalException;

	/**
	* Updates the offering bundle in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param offeringBundle the offering bundle
	* @return the offering bundle that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public OfferingBundle updateOfferingBundle(OfferingBundle offeringBundle);

	public OfferingBundle updateOfferingBundle(long offeringBundleId,
		java.lang.String name, long[] offeringDefinitionIds)
		throws PortalException;

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

	/**
	* Returns the number of offering bundles.
	*
	* @return the number of offering bundles
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingBundlesCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getOfferingDefinitionOfferingBundlesCount(
		long offeringDefinitionId);

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingBundleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.OfferingBundleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OfferingBundle> getOfferingBundles(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OfferingBundle> getOfferingDefinitionOfferingBundles(
		long offeringDefinitionId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OfferingBundle> getOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<OfferingBundle> getOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, int start, int end,
		OrderByComparator<OfferingBundle> orderByComparator);

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
	* Returns the offeringDefinitionIds of the offering definitions associated with the offering bundle.
	*
	* @param offeringBundleId the offeringBundleId of the offering bundle
	* @return long[] the offeringDefinitionIds of offering definitions associated with the offering bundle
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long[] getOfferingDefinitionPrimaryKeys(long offeringBundleId);

	public void addOfferingDefinitionOfferingBundle(long offeringDefinitionId,
		OfferingBundle offeringBundle);

	public void addOfferingDefinitionOfferingBundle(long offeringDefinitionId,
		long offeringBundleId);

	public void addOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, List<OfferingBundle> offeringBundles);

	public void addOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, long[] offeringBundleIds);

	public void clearOfferingDefinitionOfferingBundles(
		long offeringDefinitionId);

	public void deleteOfferingDefinitionOfferingBundle(
		long offeringDefinitionId, OfferingBundle offeringBundle);

	public void deleteOfferingDefinitionOfferingBundle(
		long offeringDefinitionId, long offeringBundleId);

	public void deleteOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, List<OfferingBundle> offeringBundles);

	public void deleteOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, long[] offeringBundleIds);

	public void setOfferingDefinitionOfferingBundles(
		long offeringDefinitionId, long[] offeringBundleIds);
}