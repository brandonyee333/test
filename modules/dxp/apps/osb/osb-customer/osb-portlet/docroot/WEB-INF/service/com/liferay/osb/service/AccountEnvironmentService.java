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

import com.liferay.osb.model.AccountEnvironment;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.ObjectValuePair;

import java.io.File;

import java.util.List;
import java.util.Map;

/**
 * Provides the remote service interface for AccountEnvironment. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentServiceUtil
 * @see com.liferay.osb.service.base.AccountEnvironmentServiceBaseImpl
 * @see com.liferay.osb.service.impl.AccountEnvironmentServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AccountEnvironmentService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountEnvironmentServiceUtil} to access the account environment remote service. Add custom service methods to {@link com.liferay.osb.service.impl.AccountEnvironmentServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public AccountEnvironment addAccountEnvironment(long accountEntryId,
		long productEntryId, java.lang.String name, int envOS,
		java.lang.String envOSCustom, int envDB, int envJVM, int envAS,
		int envLFR, int envCommerce, int envBrowser, int envCS,
		java.lang.String envSearch,
		List<ObjectValuePair<java.lang.String, File>> files,
		List<java.lang.Integer> types) throws PortalException;

	public AccountEnvironment deleteAccountEnvironment(
		long accountEnvironmentId) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEnvironment getAccountEnvironment(long accountEnvironmentId)
		throws PortalException;

	public AccountEnvironment updateAccountEnvironment(
		long accountEnvironmentId, long productEntryId, java.lang.String name,
		int envOS, java.lang.String envOSCustom, int envDB, int envJVM,
		int envAS, int envLFR, int envCommerce, int envBrowser, int envCS,
		java.lang.String envSearch,
		List<ObjectValuePair<java.lang.String, File>> files,
		List<java.lang.Integer> types) throws PortalException;

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

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEnvironment> getAccountEnvironments(long accountEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<java.lang.String, List<AccountEnvironment>> getAccountEnvironmentsMap(
		long accountEntryId) throws PortalException;
}