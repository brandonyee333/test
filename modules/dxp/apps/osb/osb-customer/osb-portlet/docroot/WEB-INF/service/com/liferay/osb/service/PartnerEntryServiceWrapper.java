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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PartnerEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see PartnerEntryService
 * @generated
 */
@ProviderType
public class PartnerEntryServiceWrapper implements PartnerEntryService,
	ServiceWrapper<PartnerEntryService> {
	public PartnerEntryServiceWrapper(PartnerEntryService partnerEntryService) {
		_partnerEntryService = partnerEntryService;
	}

	@Override
	public com.liferay.osb.model.PartnerEntry getPartnerEntry(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerEntryService.getPartnerEntry(partnerEntryId);
	}

	@Override
	public int searchCount(java.lang.String code, int[] statuses,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerEntryService.searchCount(code, statuses, params,
			andOperator);
	}

	@Override
	public int searchCount(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerEntryService.searchCount(keywords);
	}

	@Override
	public int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerEntryService.searchCount(keywords, params);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _partnerEntryService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _partnerEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.liferay.osb.model.PartnerEntry> search(
		java.lang.String code, int[] statuses,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerEntryService.search(code, statuses, params, andOperator,
			start, end);
	}

	@Override
	public java.util.List<com.liferay.osb.model.PartnerEntry> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return _partnerEntryService.search(keywords, params, start, end);
	}

	@Override
	public PartnerEntryService getWrappedService() {
		return _partnerEntryService;
	}

	@Override
	public void setWrappedService(PartnerEntryService partnerEntryService) {
		_partnerEntryService = partnerEntryService;
	}

	private PartnerEntryService _partnerEntryService;
}