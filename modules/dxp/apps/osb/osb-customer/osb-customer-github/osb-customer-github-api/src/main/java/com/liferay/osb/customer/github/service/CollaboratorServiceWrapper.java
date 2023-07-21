/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.github.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CollaboratorService}.
 *
 * @author Brian Wing Shun Chan
 * @see CollaboratorService
 * @generated
 */
public class CollaboratorServiceWrapper
	implements CollaboratorService, ServiceWrapper<CollaboratorService> {

	public CollaboratorServiceWrapper(CollaboratorService collaboratorService) {
		_collaboratorService = collaboratorService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _collaboratorService.getOSGiServiceIdentifier();
	}

	@Override
	public CollaboratorService getWrappedService() {
		return _collaboratorService;
	}

	@Override
	public void setWrappedService(CollaboratorService collaboratorService) {
		_collaboratorService = collaboratorService;
	}

	private CollaboratorService _collaboratorService;

}