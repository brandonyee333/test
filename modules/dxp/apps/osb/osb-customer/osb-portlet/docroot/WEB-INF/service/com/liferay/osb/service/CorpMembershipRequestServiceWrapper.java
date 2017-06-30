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
 * This class is a wrapper for {@link CorpMembershipRequestService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       CorpMembershipRequestService
 * @generated
 */
public class CorpMembershipRequestServiceWrapper
	implements CorpMembershipRequestService,
		ServiceWrapper<CorpMembershipRequestService> {
	public CorpMembershipRequestServiceWrapper(
		CorpMembershipRequestService corpMembershipRequestService) {
		_corpMembershipRequestService = corpMembershipRequestService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _corpMembershipRequestService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_corpMembershipRequestService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _corpMembershipRequestService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public com.liferay.osb.model.CorpMembershipRequest updateStatus(
		long corpMembershipRequestId, int status)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _corpMembershipRequestService.updateStatus(corpMembershipRequestId,
			status);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CorpMembershipRequestService getWrappedCorpMembershipRequestService() {
		return _corpMembershipRequestService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCorpMembershipRequestService(
		CorpMembershipRequestService corpMembershipRequestService) {
		_corpMembershipRequestService = corpMembershipRequestService;
	}

	public CorpMembershipRequestService getWrappedService() {
		return _corpMembershipRequestService;
	}

	public void setWrappedService(
		CorpMembershipRequestService corpMembershipRequestService) {
		_corpMembershipRequestService = corpMembershipRequestService;
	}

	private CorpMembershipRequestService _corpMembershipRequestService;
}