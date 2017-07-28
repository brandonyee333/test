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

import com.liferay.osb.model.AccountEnvironmentAttachment;

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
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service interface for AccountEnvironmentAttachment. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentAttachmentLocalServiceUtil
 * @see com.liferay.osb.service.base.AccountEnvironmentAttachmentLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.AccountEnvironmentAttachmentLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AccountEnvironmentAttachmentLocalService
	extends BaseLocalService, InvokableLocalService, PersistedModelLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountEnvironmentAttachmentLocalServiceUtil} to access the account environment attachment local service. Add custom service methods to {@link com.liferay.osb.service.impl.AccountEnvironmentAttachmentLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Adds the account environment attachment to the database. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironmentAttachment the account environment attachment
	* @return the account environment attachment that was added
	*/
	@Indexable(type = IndexableType.REINDEX)
	public AccountEnvironmentAttachment addAccountEnvironmentAttachment(
		AccountEnvironmentAttachment accountEnvironmentAttachment);

	public AccountEnvironmentAttachment addAccountEnvironmentAttachment(
		long userId, long accountEnvironmentId,
		ObjectValuePair<java.lang.String, File> fileOVP, int type)
		throws PortalException;

	/**
	* Creates a new account environment attachment with the primary key. Does not add the account environment attachment to the database.
	*
	* @param accountEnvironmentAttachmentId the primary key for the new account environment attachment
	* @return the new account environment attachment
	*/
	public AccountEnvironmentAttachment createAccountEnvironmentAttachment(
		long accountEnvironmentAttachmentId);

	/**
	* Deletes the account environment attachment from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironmentAttachment the account environment attachment
	* @return the account environment attachment that was removed
	* @throws PortalException
	*/
	@Indexable(type = IndexableType.DELETE)
	public AccountEnvironmentAttachment deleteAccountEnvironmentAttachment(
		AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws PortalException;

	/**
	* Deletes the account environment attachment with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	* @return the account environment attachment that was removed
	* @throws PortalException if a account environment attachment with the primary key could not be found
	*/
	@Indexable(type = IndexableType.DELETE)
	public AccountEnvironmentAttachment deleteAccountEnvironmentAttachment(
		long accountEnvironmentAttachmentId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEnvironmentAttachment fetchAccountEnvironmentAttachment(
		long accountEnvironmentAttachmentId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEnvironmentAttachment fetchAccountEnvironmentAttachment(
		long accountEnvironmentId, int type);

	/**
	* Returns the account environment attachment with the primary key.
	*
	* @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	* @return the account environment attachment
	* @throws PortalException if a account environment attachment with the primary key could not be found
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEnvironmentAttachment getAccountEnvironmentAttachment(
		long accountEnvironmentAttachmentId) throws PortalException;

	/**
	* Updates the account environment attachment in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param accountEnvironmentAttachment the account environment attachment
	* @return the account environment attachment that was updated
	*/
	@Indexable(type = IndexableType.REINDEX)
	public AccountEnvironmentAttachment updateAccountEnvironmentAttachment(
		AccountEnvironmentAttachment accountEnvironmentAttachment);

	public AccountEnvironmentAttachment updateAccountEnvironmentAttachment(
		long accountEnvironmentAttachmentId, long accountEnvironmentId,
		ObjectValuePair<java.lang.String, File> fileOVP, int type)
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
	* Returns the number of account environment attachments.
	*
	* @return the number of account environment attachments
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getAccountEnvironmentAttachmentsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public File getFile(
		AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public InputStream getFileAsStream(
		AccountEnvironmentAttachment accountEnvironmentAttachment)
		throws PortalException;

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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountEnvironmentAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountEnvironmentAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Returns a range of all the account environment attachments.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.AccountEnvironmentAttachmentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of account environment attachments
	* @param end the upper bound of the range of account environment attachments (not inclusive)
	* @return the range of account environment attachments
	*/
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEnvironmentAttachment> getAccountEnvironmentAttachments(
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEnvironmentAttachment> getAccountEnvironmentAttachments(
		long accountEnvironmentId);

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

	public void addAccountEnvironmentAttachments(long userId,
		long accountEnvironmentId,
		List<ObjectValuePair<java.lang.String, File>> files,
		List<java.lang.Integer> types) throws PortalException;

	public void updateAccountEnvironmentAttachments(long userId,
		long accountEnvironmentId,
		List<ObjectValuePair<java.lang.String, File>> files,
		List<java.lang.Integer> types) throws PortalException;
}