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

package com.liferay.osb.community.doc.project.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.community.doc.project.model.DocProject;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.io.File;

/**
 * Provides the remote service interface for DocProject. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Ryan Park
 * @see DocProjectServiceUtil
 * @see com.liferay.osb.community.doc.project.service.base.DocProjectServiceBaseImpl
 * @see com.liferay.osb.community.doc.project.service.impl.DocProjectServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=osbcommunity", "json.web.service.context.path=DocProject"}, service = DocProjectService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface DocProjectService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DocProjectServiceUtil} to access the doc project remote service. Add custom service methods to {@link com.liferay.osb.community.doc.project.service.impl.DocProjectServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public DocProject addDocProject(java.lang.String name,
		java.lang.String description, java.lang.String iconFileName,
		File iconFile, boolean unlisted, java.lang.String type,
		java.lang.String typeSettings, int status, ServiceContext serviceContext)
		throws PortalException;

	public DocProject updateDocProject(long docProjectId,
		java.lang.String name, java.lang.String description,
		java.lang.String iconFileName, File iconFile, boolean unlisted,
		java.lang.String type, java.lang.String typeSettings, int status,
		ServiceContext serviceContext) throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();
}