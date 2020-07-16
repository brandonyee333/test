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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DocProjectService}.
 *
 * @author Ryan Park
 * @see DocProjectService
 * @generated
 */
public class DocProjectServiceWrapper
	implements DocProjectService, ServiceWrapper<DocProjectService> {

	public DocProjectServiceWrapper(DocProjectService docProjectService) {
		_docProjectService = docProjectService;
	}

	@Override
	public com.liferay.osb.community.doc.project.model.DocProject addDocProject(
			String name, String description, String iconFileName,
			java.io.File iconFile, boolean unlisted, String type,
			String typeSettings, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _docProjectService.addDocProject(
			name, description, iconFileName, iconFile, unlisted, type,
			typeSettings, status, serviceContext);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _docProjectService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.osb.community.doc.project.model.DocProject
			updateDocProject(
				long docProjectId, String name, String description,
				String iconFileName, java.io.File iconFile, boolean unlisted,
				String type, String typeSettings, int status,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _docProjectService.updateDocProject(
			docProjectId, name, description, iconFileName, iconFile, unlisted,
			type, typeSettings, status, serviceContext);
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