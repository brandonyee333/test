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

import com.liferay.osb.model.CorpProject;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * Provides the local service interface for RemoteCorpProject. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see RemoteCorpProjectLocalServiceUtil
 * @see com.liferay.osb.service.base.RemoteCorpProjectLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.RemoteCorpProjectLocalServiceImpl
 * @generated
 */
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface RemoteCorpProjectLocalService extends BaseLocalService,
	InvokableLocalService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link RemoteCorpProjectLocalServiceUtil} to access the remote corp project local service. Add custom service methods to {@link com.liferay.osb.service.impl.RemoteCorpProjectLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public CorpProject addCorpProject(long creatorUserId, long ownerUserId,
		java.lang.String dossieraProjectKey,
		java.lang.String salesforceProjectKey, java.lang.String name)
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

	public void addCorpProjectUsers(java.lang.String corpProjectUuid,
		long[] userIds) throws PortalException;

	public void addCorpProjectUsers(long corpProjectId, long[] userIds)
		throws PortalException;

	public void addUserCorpProjectRoles(java.lang.String corpProjectUuid,
		long[] userIds, long roleId) throws PortalException;

	public void addUserCorpProjectRoles(long corpProjectId, long[] userIds,
		long roleId) throws PortalException;

	public void deleteCorpProject(long corpProjectId) throws PortalException;

	public void updateCorpProject(long corpProjectId, java.lang.String name)
		throws PortalException;
}