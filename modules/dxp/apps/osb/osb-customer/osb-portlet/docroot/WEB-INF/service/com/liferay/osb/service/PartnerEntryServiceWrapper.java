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

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PartnerEntryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PartnerEntryService
 * @generated
 */
public class PartnerEntryServiceWrapper implements PartnerEntryService,
	ServiceWrapper<PartnerEntryService> {
	public PartnerEntryServiceWrapper(PartnerEntryService partnerEntryService) {
		_partnerEntryService = partnerEntryService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _partnerEntryService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_partnerEntryService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _partnerEntryService.invokeMethod(name, parameterTypes, arguments);
	}

	public com.liferay.osb.model.PartnerEntry getPartnerEntry(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryService.getPartnerEntry(partnerEntryId);
	}

	public java.util.List<com.liferay.osb.model.PartnerEntry> search(
		java.lang.String code, int[] statuses,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryService.search(code, statuses, params, andOperator,
			start, end);
	}

	public java.util.List<com.liferay.osb.model.PartnerEntry> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryService.search(keywords, params, start, end);
	}

	public int searchCount(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryService.searchCount(keywords);
	}

	public int searchCount(java.lang.String code, int[] statuses,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryService.searchCount(code, statuses, params,
			andOperator);
	}

	public int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _partnerEntryService.searchCount(keywords, params);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public PartnerEntryService getWrappedPartnerEntryService() {
		return _partnerEntryService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedPartnerEntryService(
		PartnerEntryService partnerEntryService) {
		_partnerEntryService = partnerEntryService;
	}

	public PartnerEntryService getWrappedService() {
		return _partnerEntryService;
	}

	public void setWrappedService(PartnerEntryService partnerEntryService) {
		_partnerEntryService = partnerEntryService;
	}

	private PartnerEntryService _partnerEntryService;
}