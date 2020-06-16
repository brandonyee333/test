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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DocProjectService}.
 *
 * @author Ryan Park
 * @see DocProjectService
 * @generated
 */
@ProviderType
public class DocProjectServiceWrapper implements DocProjectService,
	ServiceWrapper<DocProjectService> {
	public DocProjectServiceWrapper(DocProjectService docProjectService) {
		_docProjectService = docProjectService;
	}

	@Override
	public com.liferay.osb.community.doc.project.model.DocProject addDocProject(
		java.lang.String name, java.lang.String description,
		java.lang.String iconFileName, java.io.File iconFile, boolean unlisted,
		java.lang.String type, java.lang.String typeSettings, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _docProjectService.addDocProject(name, description,
			iconFileName, iconFile, unlisted, type, typeSettings, status,
			serviceContext);
	}

	@Override
	public com.liferay.osb.community.doc.project.model.DocProject updateDocProject(
		long docProjectId, java.lang.String name, java.lang.String description,
		java.lang.String iconFileName, java.io.File iconFile, boolean unlisted,
		java.lang.String type, java.lang.String typeSettings, int status,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _docProjectService.updateDocProject(docProjectId, name,
			description, iconFileName, iconFile, unlisted, type, typeSettings,
			status, serviceContext);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _docProjectService.getOSGiServiceIdentifier();
	}

	@Override
	public DocProjectService getWrappedService() {
		return _docProjectService;
	}

	@Override
	public void setWrappedService(DocProjectService docProjectService) {
		_docProjectService = docProjectService;
	}

	private DocProjectService _docProjectService;
}