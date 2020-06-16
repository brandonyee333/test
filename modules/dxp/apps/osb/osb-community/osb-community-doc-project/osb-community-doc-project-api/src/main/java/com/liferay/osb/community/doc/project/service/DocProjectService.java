/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(
	property = {
		"json.web.service.context.name=osbcommunity",
		"json.web.service.context.path=DocProject"
	},
	service = DocProjectService.class
)
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface DocProjectService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DocProjectServiceUtil} to access the doc project remote service. Add custom service methods to <code>com.liferay.osb.community.doc.project.service.impl.DocProjectServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public DocProject addDocProject(
			String name, String description, String iconFileName, File iconFile,
			boolean unlisted, String type, String typeSettings, int status,
			ServiceContext serviceContext)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public DocProject updateDocProject(
			long docProjectId, String name, String description,
			String iconFileName, File iconFile, boolean unlisted, String type,
			String typeSettings, int status, ServiceContext serviceContext)
		throws PortalException;

}