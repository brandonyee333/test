/**
 * SPDX-FileCopyrightText: (c) 2023 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.type.virtual.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CPDVirtualSettingFileEntryService}.
 *
 * @author Marco Leo
 * @see CPDVirtualSettingFileEntryService
 * @generated
 */
public class CPDVirtualSettingFileEntryServiceWrapper
	implements CPDVirtualSettingFileEntryService,
			   ServiceWrapper<CPDVirtualSettingFileEntryService> {

	public CPDVirtualSettingFileEntryServiceWrapper() {
		this(null);
	}

	public CPDVirtualSettingFileEntryServiceWrapper(
		CPDVirtualSettingFileEntryService cpdVirtualSettingFileEntryService) {

		_cpdVirtualSettingFileEntryService = cpdVirtualSettingFileEntryService;
	}

	@Override
	public
		com.liferay.commerce.product.type.virtual.model.
			CPDVirtualSettingFileEntry addCPDefinitionVirtualSetting(
					long groupId, long cpDefinitionVirtualSettingId,
					long fileEntryId, String url, String version)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _cpdVirtualSettingFileEntryService.addCPDefinitionVirtualSetting(
			groupId, cpDefinitionVirtualSettingId, fileEntryId, url, version);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cpdVirtualSettingFileEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public CPDVirtualSettingFileEntryService getWrappedService() {
		return _cpdVirtualSettingFileEntryService;
	}

	@Override
	public void setWrappedService(
		CPDVirtualSettingFileEntryService cpdVirtualSettingFileEntryService) {

		_cpdVirtualSettingFileEntryService = cpdVirtualSettingFileEntryService;
	}

	private CPDVirtualSettingFileEntryService
		_cpdVirtualSettingFileEntryService;

}