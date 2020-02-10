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

package com.liferay.wsrp.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.cluster.Clusterable;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.wsrp.model.WSRPConsumerPortlet;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for WSRPConsumerPortlet. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see WSRPConsumerPortletLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface WSRPConsumerPortletLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WSRPConsumerPortletLocalServiceUtil} to access the wsrp consumer portlet local service. Add custom service methods to <code>com.liferay.wsrp.service.impl.WSRPConsumerPortletLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public WSRPConsumerPortlet addWSRPConsumerPortlet(
			long wsrpConsumerId, String name, String portletHandle,
			ServiceContext serviceContext)
		throws PortalException;

	public WSRPConsumerPortlet addWSRPConsumerPortlet(
			String wsrpConsumerUuid, String name, String portletHandle,
			ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Adds the wsrp consumer portlet to the database. Also notifies the appropriate model listeners.
	 *
	 * @param wsrpConsumerPortlet the wsrp consumer portlet
	 * @return the wsrp consumer portlet that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public WSRPConsumerPortlet addWSRPConsumerPortlet(
		WSRPConsumerPortlet wsrpConsumerPortlet);

	/**
	 * Creates a new wsrp consumer portlet with the primary key. Does not add the wsrp consumer portlet to the database.
	 *
	 * @param wsrpConsumerPortletId the primary key for the new wsrp consumer portlet
	 * @return the new wsrp consumer portlet
	 */
	@Transactional(enabled = false)
	public WSRPConsumerPortlet createWSRPConsumerPortlet(
		long wsrpConsumerPortletId);

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	/**
	 * Deletes the wsrp consumer portlet with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param wsrpConsumerPortletId the primary key of the wsrp consumer portlet
	 * @return the wsrp consumer portlet that was removed
	 * @throws PortalException if a wsrp consumer portlet with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public WSRPConsumerPortlet deleteWSRPConsumerPortlet(
			long wsrpConsumerPortletId)
		throws PortalException;

	public void deleteWSRPConsumerPortlet(String wsrpConsumerPortletUuid)
		throws PortalException;

	/**
	 * Deletes the wsrp consumer portlet from the database. Also notifies the appropriate model listeners.
	 *
	 * @param wsrpConsumerPortlet the wsrp consumer portlet
	 * @return the wsrp consumer portlet that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public WSRPConsumerPortlet deleteWSRPConsumerPortlet(
			WSRPConsumerPortlet wsrpConsumerPortlet)
		throws PortalException;

	public void deleteWSRPConsumerPortlets(long wsrpConsumerId)
		throws PortalException;

	@Clusterable
	public void destroyWSRPConsumerPortlet(
		long wsrpConsumerPortletId, String wsrpConsumerPortletUuid, String url);

	public void destroyWSRPConsumerPortlets() throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WSRPConsumerPortlet fetchWSRPConsumerPortlet(
		long wsrpConsumerPortletId);

	/**
	 * Returns the wsrp consumer portlet with the matching UUID and company.
	 *
	 * @param uuid the wsrp consumer portlet's UUID
	 * @param companyId the primary key of the company
	 * @return the matching wsrp consumer portlet, or <code>null</code> if a matching wsrp consumer portlet could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WSRPConsumerPortlet fetchWSRPConsumerPortletByUuidAndCompanyId(
		String uuid, long companyId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		PortletDataContext portletDataContext);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Returns the wsrp consumer portlet with the primary key.
	 *
	 * @param wsrpConsumerPortletId the primary key of the wsrp consumer portlet
	 * @return the wsrp consumer portlet
	 * @throws PortalException if a wsrp consumer portlet with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WSRPConsumerPortlet getWSRPConsumerPortlet(
			long wsrpConsumerPortletId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WSRPConsumerPortlet getWSRPConsumerPortlet(
			long wsrpConsumerId, String portletHandle)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WSRPConsumerPortlet getWSRPConsumerPortlet(
			String wsrpConsumerPortletUuid)
		throws PortalException;

	/**
	 * Returns the wsrp consumer portlet with the matching UUID and company.
	 *
	 * @param uuid the wsrp consumer portlet's UUID
	 * @param companyId the primary key of the company
	 * @return the matching wsrp consumer portlet
	 * @throws PortalException if a matching wsrp consumer portlet could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public WSRPConsumerPortlet getWSRPConsumerPortletByUuidAndCompanyId(
			String uuid, long companyId)
		throws PortalException;

	/**
	 * Returns a range of all the wsrp consumer portlets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.wsrp.model.impl.WSRPConsumerPortletModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of wsrp consumer portlets
	 * @param end the upper bound of the range of wsrp consumer portlets (not inclusive)
	 * @return the range of wsrp consumer portlets
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WSRPConsumerPortlet> getWSRPConsumerPortlets(
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<WSRPConsumerPortlet> getWSRPConsumerPortlets(
		long wsrpConsumerId, int start, int end);

	/**
	 * Returns the number of wsrp consumer portlets.
	 *
	 * @return the number of wsrp consumer portlets
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWSRPConsumerPortletsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getWSRPConsumerPortletsCount(long wsrpConsumerId);

	@Clusterable
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void initFailedWSRPConsumerPortlets();

	@Clusterable
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void initWSRPConsumerPortlet(
			long companyId, long wsrpConsumerId, long wsrpConsumerPortletId,
			String wsrpConsumerPortletUuid, String name, String portletHandle)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public void initWSRPConsumerPortlets();

	public WSRPConsumerPortlet updateWSRPConsumerPortlet(
			long wsrpConsumerPortletId, String name)
		throws PortalException;

	/**
	 * Updates the wsrp consumer portlet in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param wsrpConsumerPortlet the wsrp consumer portlet
	 * @return the wsrp consumer portlet that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public WSRPConsumerPortlet updateWSRPConsumerPortlet(
		WSRPConsumerPortlet wsrpConsumerPortlet);

}