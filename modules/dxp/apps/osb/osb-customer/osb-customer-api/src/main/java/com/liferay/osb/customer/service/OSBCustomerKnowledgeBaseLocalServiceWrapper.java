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

package com.liferay.osb.customer.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link OSBCustomerKnowledgeBaseLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see OSBCustomerKnowledgeBaseLocalService
 * @generated
 */
@ProviderType
public class OSBCustomerKnowledgeBaseLocalServiceWrapper
	implements OSBCustomerKnowledgeBaseLocalService,
		ServiceWrapper<OSBCustomerKnowledgeBaseLocalService> {
	public OSBCustomerKnowledgeBaseLocalServiceWrapper(
		OSBCustomerKnowledgeBaseLocalService osbCustomerKnowledgeBaseLocalService) {
		_osbCustomerKnowledgeBaseLocalService = osbCustomerKnowledgeBaseLocalService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _osbCustomerKnowledgeBaseLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public void updateOSBKnowledgeBase(long kbFolderId, java.io.File file,
		java.util.List<java.lang.String> urlTitles) throws java.lang.Exception {
		_osbCustomerKnowledgeBaseLocalService.updateOSBKnowledgeBase(kbFolderId,
			file, urlTitles);
	}

	@Override
	public OSBCustomerKnowledgeBaseLocalService getWrappedService() {
		return _osbCustomerKnowledgeBaseLocalService;
	}

	@Override
	public void setWrappedService(
		OSBCustomerKnowledgeBaseLocalService osbCustomerKnowledgeBaseLocalService) {
		_osbCustomerKnowledgeBaseLocalService = osbCustomerKnowledgeBaseLocalService;
	}

	private OSBCustomerKnowledgeBaseLocalService _osbCustomerKnowledgeBaseLocalService;
}